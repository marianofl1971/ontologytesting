/*
 * instantiationTest.java
 * 
 * Created on 21-feb-2008, 10:47:18
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tests;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;
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
 * @author sara_garcia
 */
public class OntologyTests {
    
    //Saber si un individuo pertenece a una clase
    public String instantiation(String ns, String className, String individualName, 
            OntModel model){
        
        OntClass ontClass = model.getOntClass(ns + className);

        if(ontClass==null) 
        {
            return "El individuo introducido no es una instancia para el modelo";
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
    public ArrayList<String> retieval(String ns, String className, OntModel model){
        
        OntClass ontClass = model.getOntClass(ns + className);         
        Iterator it = ontClass.listInstances();
        ArrayList<String> rval = new ArrayList<String>();
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
    public String realization(String ns, String individualName, OntModel model){
         
        Individual individual = model.getIndividual(ns + individualName);
        
        if(individual==null) return "El individuo introducido no es una instancia para el modelo";
        
        Resource resource = individual.getRDFType(true); 
        String className = resource.toString().substring(resource.toString().indexOf("#")+1);
        
        return className;
    }  
    
    //Saber si se puede añadir un concepto
    public String satisfactibility(String ns, OntModel model, String concepto, 
            String clase){
      
       OntClass ontClass = model.getOntClass(ns+clase);
       Iterator it = ontClass.listDisjointWith();
       ArrayList<String> conjuntoDisj = new ArrayList<String>();
       
       while(it.hasNext()){
            String disjunta = it.next().toString();
            disjunta = disjunta.substring(disjunta.indexOf("#")+1);
            conjuntoDisj.add(disjunta);
        }
       
       ArrayList<String> clasesConcepto = classification(ns,model,concepto);

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
    public ArrayList<String> classification(String ns, OntModel model, String individuo){
        
        String pertenece;
        ArrayList<String> clases = new ArrayList<String>();
        Iterator it = model.listNamedClasses();

        while(it.hasNext()){
            String[] instanceName = it.next().toString().split("#");
            pertenece = instantiation(ns, instanceName[1].toString(), individuo, model);
            if(pertenece.equals("true")){
                clases.add(instanceName[1].toString());
            }
        }       
        return clases;
    }
    
    public ArrayList<String> testSPARQL(String queryStr, boolean formatHTML, OntModel model){
        
        Query query = QueryFactory.create(queryStr);
        if (!query.isSelectType()) {
        	throw new UnsupportedFeatureException("Only SELECT supported for this example");
        }
        // create an empty ontology model using Pellet spec
        model = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC );        
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
                /*for(int k=0; k<res.size();k++){
                    System.out.println("aaaaa "+res.get(k));
                }*/
                                
                //formattedBinding.add(formatter.format(result));                
            }
            //table.add(formattedBinding);
        }
        //table.print(System.out, formatHTML);
        return res;
    }
    
}
