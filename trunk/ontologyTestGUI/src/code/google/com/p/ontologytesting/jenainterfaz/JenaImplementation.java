/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.jenainterfaz;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.mindswap.pellet.exceptions.UnsupportedFeatureException;
import org.mindswap.pellet.jena.PelletQueryExecution;
import org.mindswap.pellet.jena.PelletReasonerFactory;

/**
 *
 * @author sara.garcia
 */
public class JenaImplementation implements Jena{

    public final OntModel model = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC );
    private OntClass nameclass;
    private Property nameprop;
    
    public JenaImplementation(){}
    
    @Override
    public void addInstanceClass(String ns, String nameClass, String value) {
        nameclass = model.createClass(ns + nameClass);
        model.createIndividual(ns + value,nameclass);
    }

    @Override
    public void addInstanceProperty(String ns, String nameProperty, String value) {
        nameprop = model.createProperty(ns + nameProperty);
        model.createIndividual(ns + value,nameprop);
    }

    @Override
    public void deleteEntries() {
        model.removeAll();
    }

    @Override
    public void addReasoner(String ontologia) {
        model.read(ontologia);  
        model.prepare();
    }

        //Saber si un individuo pertenece a una clase
    @Override
    public String instantiation(String ns, String className, String individualName){
        
        OntClass ontClass = model.getOntClass(ns + className);

        if(ontClass==null) 
        {
            return "La clase introducida no es una instancia para el modelo";
        }
      
        Iterator it = ontClass.listInstances();       
              
        while(it.hasNext()){
            String instanceName = it.next().toString();
            instanceName = instanceName.substring(instanceName.indexOf("#")+1);
            if(individualName.equals(instanceName)) {
                return "true";
            }
        }
        return "false";
    } 
    
    //Saber TODOS los individuos que pertenecen a una clase
    //TODOS los individuos que son instancias de un concepto    
    @Override
    public ArrayList<String> retieval(String ns, String className){
        
        ArrayList<String> rval = new ArrayList<String>();
        OntClass ontClass = model.getOntClass(ns + className);    
        if(ontClass==null) 
        {
            rval = null;
            return rval;
        }
        Iterator it = ontClass.listInstances();
        while(it.hasNext())
        {
            String instanceName=it.next().toString();
            instanceName=instanceName.substring(instanceName.indexOf("#")+1);
            rval.add(instanceName);
        }
        return rval;
    }    

    //Saber la clase mas exacta a la que pertenece un individuo
    //Dado un individuo, encontrar el concepto más específico que lo contiene
    @Override
    public String realization(String ns, String individualName){
         
        Individual individual = model.getIndividual(ns + individualName);
        
        if(individual==null) return "El individuo introducido no es una instancia para el modelo";
        
        Resource resource = individual.getRDFType(true); 
        String className = resource.toString().substring(resource.toString().indexOf("#")+1);
        
        return className;
    }  
    
    //Saber si se puede añadir un concepto
    @Override
    public String satisfactibility(String ns, String concepto, String clase){
      
       OntClass ontClass = model.getOntClass(ns+clase);
       if(ontClass==null){
            return "La clase introducida no es una instancia para el modelo";
       }
       Iterator it = ontClass.listDisjointWith();
       ArrayList<String> conjuntoDisj = new ArrayList<String>();
       
       while(it.hasNext()){
            String disjunta = it.next().toString();
            disjunta = disjunta.substring(disjunta.indexOf("#")+1);
            conjuntoDisj.add(disjunta);
        }
       
       ArrayList<String> clasesConcepto = classification(ns,concepto);

       if(clasesConcepto.equals("")){
            return "true";
       }else{
           for(int i=0;i<clasesConcepto.size();i++){
            for(int k=0;k<conjuntoDisj.size();k++){
                if(clasesConcepto.contains(conjuntoDisj.get(k)))
                    return "false";
                }
            }
           }
       return "true";
    }
    
    //Dado un individuo, deducir todas las clases a las que pertenece
    @Override
    public ArrayList<String> classification(String ns, String individuo){
        
        Individual individual = model.getIndividual(ns + individuo);
        String pertenece;
        ArrayList<String> clases = new ArrayList<String>();
        
        if(individual==null){
            clases = null;
            return clases;
        }

        Iterator it = model.listNamedClasses();
        Iterator itaux = model.listObjectProperties();
        ArrayList<String[]> arrayProp = new ArrayList<String[]>();
        while(itaux.hasNext()){
            arrayProp.add(itaux.next().toString().split("#"));
        }
        while(it.hasNext()){
            int aux=0;
            String[] instanceName = it.next().toString().split("#");
            for(int i=0; i<arrayProp.size(); i++){
                String[] deProp = arrayProp.get(i);
                if(deProp[0].equals(instanceName[0]) && deProp[1].equals(instanceName[1])){
                    aux=1;
                }
            }
            if(aux==0){
                pertenece = instantiation(ns, instanceName[1].toString(), individuo);
                if(pertenece.equals("true")){
                    clases.add(instanceName[1].toString());
                }
            }
        }       
        return clases;
    }
    
    @Override
    public ArrayList<String> testSPARQL(String queryStr, boolean formatHTML){
        
        Query query = QueryFactory.create(queryStr);
        if (!query.isSelectType()) {
        	throw new UnsupportedFeatureException("Only SELECT supported for this example");
        }
        
        // create an empty ontology model using Pellet spec     
        model.setStrictMode(false);
       
        if(query.getGraphURIs().size()==0)
            throw new UnsupportedFeatureException("SPARQL query must have a FROM clause for this example");
        for (Iterator iter = query.getGraphURIs().iterator(); iter.hasNext();) {
        	String sourceURI = (String) iter.next();
        	model.read(sourceURI);
        }
        
	QueryExecution qexec = new PelletQueryExecution(query, model);
        ResultSet results = qexec.execSelect();
        // create a node formatter
        //NodeFormatter formatter = new NodeFormatter(model, formatHTML); 
        // variables used in select
        List resultVars = query.getResultVars();
        ArrayList<String> res = new ArrayList<String>();
        // store the formatted results an a table 
        //TableData table = new TableData( resultVars );
        while( results.hasNext() ) {
            QuerySolution binding = results.nextSolution();
            //List formattedBinding = new ArrayList();
            for(int i = 0; i < resultVars.size(); i++) {
                String var = (String) resultVars.get(i);
                RDFNode result = binding.get(var);
                String aux = result.toString();
                if(!res.contains(aux.substring(aux.indexOf("#")+1))){
                    if(!aux.substring(aux.indexOf("#")+1).equals("Nothing") && 
                            !aux.substring(aux.indexOf("#")+1).equals("Thing")){
                        res.add(aux.substring(aux.indexOf("#")+1));
                    }
                }       
            }
        }
        return res;
    }
}
