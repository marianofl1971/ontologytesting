/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.jenainterfaz;

import code.google.com.p.ontologytesting.exceptions.ExceptionReadOntology;
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
import com.hp.hpl.jena.sparql.syntax.Element;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.mindswap.pellet.jena.PelletQueryExecution;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import org.mindswap.pellet.utils.QNameProvider;

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
        try {
            model.read(ontologia);  
            model.prepare();
            model.validate();
        }catch(Exception e){
            throw new ExceptionReadOntology();
        }
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
    
    //Validar las consultas de tipo select x,y,z from ...
    //incluir mostrar el resultado de x de y de z por separado.
    //El formato del resultado seria algo de tipo:
    //x(a,b)
    //y(c,d,e)
    //z(f)
    @Override
    public ArrayList<ExecQuerySparql> testSPARQL(String queryStr, boolean formatHTML){

        ArrayList<String> res = new ArrayList<String>();

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

        QueryExecution qexec = new PelletQueryExecution(query, model);
        ResultSet results = qexec.execSelect();
       
        // Create a node formatter
        //NodeFormatter formatter = new NodeFormatter(model, formatHTML); 
        //Variables used in SELECT
        List resultVars = query.getResultVars();
        res = new ArrayList<String>();
        //Store the formatted results an a table 
        //TableData table = new TableData( resultVars );
        ArrayList<ExecQuerySparql> lista = new ArrayList<ExecQuerySparql>();
        while(results.hasNext()){
            QuerySolution binding = results.nextSolution();
            for(int i = 0; i < resultVars.size(); i++) {
                ExecQuerySparql e = new ExecQuerySparql();
                
                String var = (String) resultVars.get(i);
                RDFNode result = binding.get(var);
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
                
                if(!res.contains(dato)){
                    if(!dato.equals("Nothing") && 
                            !dato.equals("Thing")){
                        res.add(dato);
                    }
                }       
            }
        }

        /*ArrayList<String> obtenido = new ArrayList<String>();
        String contenidoObtenido = "";

        int tam = lista.get(0).getDatos().size();
        for(int s=0;s<tam;s++){
            for(int t=0; t<lista.size(); t++){
                String valor = lista.get(t).getDatos().get(s);
                contenidoObtenido = contenidoObtenido+valor+";";
            } 
            obtenido.add(contenidoObtenido);
            contenidoObtenido = "";
        }
        
        ArrayList<ExecQuerySparql> listaFinal = new ArrayList<ExecQuerySparql>();
        ExecQuerySparql exeqQ = new ExecQuerySparql();
        for(int k=0;k<obtenido.size();k++){
            String datoAux = obtenido.get(k);
            String[] dato = datoAux.split(";");
            if(perteneceALista(dato[0], listaFinal)==false){
                exeqQ.setNombreSelect(dato[0]);
                exeqQ.getDatos().add(k, dato[1]);
                listaFinal.add(exeqQ);
            }else{
                ExecQuerySparql listaEx = seleccionarLista(dato[0], listaFinal);
                listaEx.getDatos().add(dato[1]);
            }
        }
        for(int k=0;k<listaFinal.size();k++){
                    ExecQuerySparql eqy = listaFinal.get(k);
                    System.out.println("Dato "+eqy.getNombreSelect());
                    for(int s=0;s<eqy.getDatos().size();s++){
                        System.out.println("Contenido "+eqy.getDatos().get(s));
                    }
                    System.out.println("----------------------------------");
        }

        
        for(int k=0;k<lista.size();k++){
                    ExecQuerySparql eqy = lista.get(k);
                    System.out.println("Select "+eqy.getNombreSelect());
                    for(int s=0;s<eqy.getDatos().size();s++){
                        System.out.println("Dato "+eqy.getDatos().get(s));
                    }
                    System.out.println("----------------------------------");
        }
        NodeFormatter formatter = new NodeFormatter(model, formatHTML); 
        addDefaultQNames(formatter.getQNames());               
        List resultV = query.getResultVars();       
        TableData table = new TableData( resultV );
        while( results.hasNext() ) {
            QuerySolution binding = results.nextSolution();
            List formattedBinding = new ArrayList();
            for(int i = 0; i < resultV.size(); i++) {
                String var = (String) resultV.get(i);
                RDFNode result = binding.get(var);
                                
                formattedBinding.add(formatter.format(result));                
            }          
            table.add(formattedBinding);
        }*/
        
        return lista;
    }
    
    @Override
    public void validarSparqlQuery(String query) throws Exception{   
        Query queryStr = QueryFactory.create(query);
        if(!queryStr.isSelectType()){
            throw new Exception();
        }
    }
    
    /*@Override
    public void validarSparqlQuerySelect(String query) throws Exception{ 
        Query queryStr = QueryFactory.create(query);
        
    }*/
    
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
    
    private static void addDefaultQNames(QNameProvider qnames) {
        qnames.setMapping("tce-service", "http://www.flacp.fujitsulabs.com/tce/ontologies/2004/03/service.owl#");
        qnames.setMapping("tce-object", "http://www.flacp.fujitsulabs.com/tce/ontologies/2004/03/object.owl#");

        String owls = "http://www.daml.org/services/owl-s/";
        String[] versions = {"0.9", "1.0", "1.1"};
        String[] add = {"-0.9", "-1.0", ""};
        String[] files = {"Service", "Profile", "Process", "Grounding"};
        for(int version = 0; version < versions.length; version++) {
            for(int file = 0; file < files.length; file++) {
                String prefix = files[file].toLowerCase() + add[version];
                String uri = owls + versions[version] + "/" + files[file] + ".owl#";
                qnames.setMapping(prefix, uri);
            }
        }

    }

}
