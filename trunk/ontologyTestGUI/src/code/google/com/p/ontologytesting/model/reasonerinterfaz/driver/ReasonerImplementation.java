/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model.reasonerinterfaz.driver;

import code.google.com.p.ontologytesting.model.reasonerinterfaz.*;
import code.google.com.p.ontologytesting.model.exectests.*;
import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.sparql.syntax.Element;
import org.mindswap.pellet.jena.PelletQueryExecution;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import org.mindswap.pellet.exceptions.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 *
 * @author sara.garcia
 */
public class ReasonerImplementation implements InterfaceReasoner{

    private static OntModel model;
    private OntClass nameclass;
    private Property nameprop;
    
    public ReasonerImplementation(){}
    
    @Override
    public void selectReasoner() {
        model = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC );
    }
    
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
    public void addReasoner(String ontologia) throws InvalidOntologyException{
        selectReasoner();
        ontologia = "file:".concat(ontologia);
        try {
            model.read(ontologia);  
            model.prepare(); 
            model.validate();
        }catch(Exception e){
            throw new InvalidOntologyException();
        }
    }

    //Saber si un individuo pertenece a una clase
    @Override
    public String instantiation(String ns, String className, 
            String individualName) throws InvalidOntologyException{    
        OntClass ontClass = model.getOntClass(ns + className);
        if(ontClass==null) return "La clase introducida no es una instancia " +
                "para el modelo";
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
            throw new InvalidOntologyException();
        }
        return "false";
    } 
    
    //Saber TODOS los individuos que pertenecen a una clase
    //TODOS los individuos que son instancias de un concepto    
    @Override
    public ArrayList<String> retieval(String ns, String className){
        ArrayList<String> rval = new ArrayList<String>();
        OntClass ontClass = model.getOntClass(ns + className);    
        if(ontClass==null) return rval=null;
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
            throw new InvalidOntologyException();
        }
    }    

    //Saber la clase mas exacta a la que pertenece un individuo
    //Dado un individuo, encontrar el concepto más específico que lo contiene
    @Override
    public String realization(String ns, String individualName){
        Individual individual = model.getIndividual(ns + individualName);
        if(individual==null) return "El individuo introducido no es una instancia " +
                "para el modelo";
        try{
            Resource resource = individual.getRDFType(true); 
            String className = resource.toString().substring
                    (resource.toString().indexOf("#")+1);
            return className;
        }catch(InconsistentOntologyException in){
            throw new InvalidOntologyException();
        }
    }  
    
    //Saber si se puede añadir un concepto
    @Override
    public String satisfactibility(String ns, String concepto, String clase) 
            throws InvalidOntologyException{
       OntClass ontClass = model.getOntClass(ns+clase);
       if(ontClass==null) return "La clase introducida no es una " +
               "instancia para el modelo";
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
            throw new InvalidOntologyException();
        }
    }
    
    //Dado un individuo, deducir todas las clases a las que pertenece
    @Override
    public ArrayList<String> classification(String ns, String individuo) 
            throws InvalidOntologyException{
        Individual individual = model.getIndividual(ns + individuo);
        String pertenece;
        ArrayList<String> clases = new ArrayList<String>();
        if(individual==null) return clases = null;
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
                pertenece = instantiation(ns, instanceName[1], individuo);
                if (pertenece.equals("true")) {
                    clases.add(instanceName[1]);
                }
            }
        }       
        return clases;
    }
    
    @Override
    public ArrayList<ExecQuerySparql> testSPARQL(String queryStr, boolean formatHTML){
        Query query=null;
        String expReg = "([\\?]{1}[a-zA-Z]+)";
        ArrayList<String> sel = new ArrayList<String>();
        try{
            query = QueryFactory.create(queryStr);
            Element patern = query.getQueryPattern();
        String p = patern.toString();
        String[] consulta = p.split("(\\s)");
        for(int i=0; i<consulta.length;i++){
            if(consulta[i].matches(expReg)){
                sel.add(consulta[i].substring(1));
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

                    if(this.perteneceALista(var,lista)==false){
                        e.setNombreSelect(var);
                        lista.add(e);
                        lista.get(i).getDatos().add(dato);
                    }else{
                        ExecQuerySparql eq = this.seleccionarLista(var, lista);
                        eq.getDatos().add(dato);
                    }  
                }
            }
        }
        return lista;
        }catch(InconsistentOntologyException in){
            throw new InvalidOntologyException();
        }
        }catch (QueryParseException ex){
            throw new InvalidOntologyException();
        }
    }
    
    @Override
    public boolean validarSparqlQuery(String query){   
        Query queryStr = QueryFactory.create(query);
        if(!queryStr.isSelectType()){
            return false;
        }
        return true;   
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
