/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model.jenainterfaz.driverjena;

import code.google.com.p.ontologytesting.model.jenainterfaz.*;
import code.google.com.p.ontologytesting.model.ExecQuerySparql;
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
import com.hp.hpl.jena.shared.JenaException;
import com.hp.hpl.jena.sparql.syntax.Element;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.mindswap.pellet.jena.PelletQueryExecution;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import org.mindswap.pellet.exceptions.*;

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
    public boolean addInstanceClass(String ns, String nameClass, String value) {
        Iterator it = model.listNamedClasses();
        String[] clas = null;
        ArrayList<String> clases = new ArrayList<String>();
        while(it.hasNext()){
            clas = it.next().toString().split("#");
            for(int i=0;i<clas.length;i++){
                clases.add(clas[i]);
            }
        }
        if(clases.contains(nameClass)){
            nameclass = model.createClass(ns + nameClass);
            model.createIndividual(ns + value,nameclass);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean addInstanceProperty(String ns, String nameProperty, String value) {
        Iterator it = model.listObjectProperties();
        String[] prop = null;
        ArrayList<String> propiedades = new ArrayList<String>();
        while(it.hasNext()){
            prop = it.next().toString().split("#");
            for(int i=0;i<prop.length;i++){
                propiedades.add(prop[i]);
            }
        }
        if(propiedades.contains(nameProperty)){
            nameprop = model.createProperty(ns + nameProperty);
            model.createIndividual(ns + value,nameprop);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void deleteEntries() {
        model.removeAll();
    }

    @Override
    public void addReasoner(String ontologia) throws ExceptionReadOntology{
        ontologia = "file:".concat(ontologia);
        try {
            model.read(ontologia);  
            model.prepare(); //Aqui salta la inconsistencia
            model.validate();
        }catch(InconsistentOntologyException e){
            new ExceptionReadOntology("La ontologia introducida no es valida." +
            "\nSolo pueden realizarse tests sobre documentos owl consistentes");
        }catch(JenaException je){
            throw new ExceptionReadOntology();
        }
    }

    //Saber si un individuo pertenece a una clase
    @Override
    public String instantiation(String ns, String className, String individualName) throws ExceptionReadOntology{
        
        OntClass ontClass = model.getOntClass(ns + className);

        if(ontClass==null) 
        {
            return "La clase introducida no es una instancia para el modelo";
        }
      
        try{
        Iterator it = ontClass.listInstances();       
              
        while(it.hasNext()){
            String instanceName = it.next().toString();
            instanceName = instanceName.substring(instanceName.indexOf("#")+1);
            if(individualName.equals(instanceName)) {
                return "true";
            }
        }
        }catch(InconsistentOntologyException in){
            throw new ExceptionReadOntology();
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
        try{
            Iterator it = ontClass.listInstances();
            while(it.hasNext())
            {
                String instanceName=it.next().toString();
                instanceName=instanceName.substring(instanceName.indexOf("#")+1);
                rval.add(instanceName);
            }
            return rval;
        }catch(InconsistentOntologyException in){
            throw new ExceptionReadOntology();
        }
    }    

    //Saber la clase mas exacta a la que pertenece un individuo
    //Dado un individuo, encontrar el concepto más específico que lo contiene
    @Override
    public String realization(String ns, String individualName){
         
        Individual individual = model.getIndividual(ns + individualName);
        
        if(individual==null) return "El individuo introducido no es una instancia para el modelo";
        try{
            Resource resource = individual.getRDFType(true); 
            String className = resource.toString().substring(resource.toString().indexOf("#")+1);

            return className;
        }catch(InconsistentOntologyException in){
            throw new ExceptionReadOntology();
        }
    }  
    
    //Saber si se puede añadir un concepto
    @Override
    public String satisfactibility(String ns, String concepto, String clase) throws ExceptionReadOntology{
      
       OntClass ontClass = model.getOntClass(ns+clase);
       if(ontClass==null){
            return "La clase introducida no es una instancia para el modelo";
       }
       try{
           Iterator it = ontClass.listDisjointWith();
           ArrayList<String> conjuntoDisj = new ArrayList<String>();

           while(it.hasNext()){
                String disjunta = it.next().toString();
                disjunta = disjunta.substring(disjunta.indexOf("#")+1);
                conjuntoDisj.add(disjunta);
            }

           ArrayList<String> clasesConcepto = classification(ns,concepto);

           if(clasesConcepto==null){
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
       }catch(InconsistentOntologyException in){
            throw new ExceptionReadOntology();
        }
    }
    
    //Dado un individuo, deducir todas las clases a las que pertenece
    @Override
    public ArrayList<String> classification(String ns, String individuo) throws ExceptionReadOntology{
        
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
                if (pertenece.equals("true")) {
                    clases.add(instanceName[1].toString());
                }
            }
        }       
        return clases;
    }
    
    //Validar las consultas de tipo select x,y,z from ...
    //incluir mostrar el resultado de x de y de z por separado.
    //El formato del resultado seria algo de tipo:
    //x(a,b)
    //y(c,d,e)
    //z(f)
    @Override
    public ArrayList<ExecQuerySparql> testSPARQL(String queryStr, boolean formatHTML){

        String expReg = "([\\?]{1}[a-zA-Z]+)";
        int cont=0;
        ArrayList<String> sel = new ArrayList<String>();
        Query query = QueryFactory.create(queryStr);
        Element patern = query.getQueryPattern();
        String p = patern.toString();
        String[] consulta = p.split("(\\s)");
        for(int i=0; i<consulta.length;i++){
            if(consulta[i].matches(expReg)){
                sel.add(consulta[i].substring(1));
                cont++;
            }
        }

        try{
        QueryExecution qexec = new PelletQueryExecution(query, model);
        ResultSet results = qexec.execSelect();

        List resultVars = query.getResultVars();

        ArrayList<ExecQuerySparql> lista = new ArrayList<ExecQuerySparql>();
        while(results.hasNext()){
            QuerySolution binding = results.nextSolution();
            for(int i = 0; i < resultVars.size(); i++) {
                ExecQuerySparql e = new ExecQuerySparql();
                
                String var = (String) resultVars.get(i);
                RDFNode result = binding.get(var);
                if(result!=null){
                    String aux = result.toString();
                    String dato = aux.substring(aux.indexOf("#")+1);

                    if(perteneceALista(var,lista)==false){
                        e.setNombreSelect(var);
                        lista.add(e);
                        lista.get(i).getDatos().add(dato);
                    }else{
                        ExecQuerySparql eq = seleccionarLista(var, lista);
                        eq.getDatos().add(dato);
                    }  
                }
            }
        }
        return lista;
        }catch(InconsistentOntologyException in){
            throw new ExceptionReadOntology();
        }
    }
    
    @Override
    public void validarSparqlQuery(String query) throws Exception{   
        Query queryStr = QueryFactory.create(query);
        if(!queryStr.isSelectType()){
            throw new Exception();
        }
    }
    
    public boolean perteneceALista(String nombre, ArrayList<ExecQuerySparql> lista){
        for(int i=0;i<lista.size();i++){
            String nombreAux = lista.get(i).getNombreSelect();
            if(nombreAux.equals(nombre)){
                return true;
            }
        }
        return false;
    }
    
    public ExecQuerySparql seleccionarLista(String nombreSelect,ArrayList<ExecQuerySparql> lista){
        for(int i=0;i<lista.size();i++){
            if(lista.get(i).getNombreSelect().equals(nombreSelect)){
                return lista.get(i);
            }
        }
        return new ExecQuerySparql();
    }

}
