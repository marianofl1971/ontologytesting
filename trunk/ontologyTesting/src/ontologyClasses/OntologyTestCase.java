/*
 * OntologyTestCase.java
 * 
 * Created on 16-feb-2008, 14:44:25
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontologyClasses;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import ontologyModel.CollectionTest;
import ontologyModel.QueryOntology;
import ontologyModel.ScenarioTest;
import ontologyModel.SparqlQueryOntology;
import ontologyTests.ontologyTests;
import org.mindswap.pellet.jena.PelletReasonerFactory;

/**
 *
 * @author Saruskas
 */
public class OntologyTestCase implements OntologyTest{
    
    public String ontologyname;
    private OntModel model;
    private Model model_q;
    private OntClass nameclass;
    private Individual classValue, hasprop;
    private Property nameprop;
    private XMLDecoder decoder,dec;
    private ScenarioTest scenarioTest;
    private CollectionTest collectionTest;
    private ResultSet results;
    private Query sparql_q;
    private QueryExecution qe;
                    
    public OntologyTestCase(){
    }

    protected void setUpOntology(ScenarioTest st, String ont, String ns){
        
    try{
         decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("instances.xml")));
         scenarioTest = (ScenarioTest)decoder.readObject();
         decoder.close();           
    }catch(FileNotFoundException e){
    }         
      
    /* SPARQL Query
    InputStream in = null;
    model_q = ModelFactory.createMemModelMaker().createModel("");
        try {
            in = new FileInputStream(new File("data/family.owl"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OntologyTestCase.class.getName()).log(Level.SEVERE, null, ex);
        }
    model_q.read(in,null); 
    try {
        in.close();
    } catch (IOException ex) {
        Logger.getLogger(OntologyTestCase.class.getName()).log(Level.SEVERE, null, ex);
    }*/
        
    ListIterator liClass,liProperties;   
    String ciClas[],ciInd[],piClas[],piInd[];    
    
    model = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC );
    model.read(ont);  
    model.prepare();
   
    List<String> classInstances = scenarioTest.getClassInstances();
    List<String> propertyInstances = scenarioTest.getPropertyInstances();
    //List<String> classInstances = st.getClassInstances();
    //List<String> propertyInstances = st.getPropertyInstances();
  
    liClass = classInstances.listIterator();
    liProperties = propertyInstances.listIterator();
        
    while(liClass.hasNext()){
            String ci = (String) liClass.next();
            ciClas = ci.split("\\(");
            ciInd = ciClas[1].split("\\)");
            addInstanceClass(ns,ciClas[0],ciInd[0]);
    }
        
    while(liProperties.hasNext()){
            String pi = (String) liProperties.next();
            piClas = pi.split("\\(");
            piInd = piClas[1].split("\\)");
            addInstanceProperty(ns,piClas[0],piInd[0]);
    }
       
    }
    
    protected void tearDownOntology(){
        nameclass.remove();
        nameprop.removeProperties();
        classValue.remove();
        hasprop.remove();    
        // Important - free up resources used running the query
        //qe.close();
    }
    
    private void runOntologyTest(OntologyTestResult testresult, String ns, 
            ScenarioTest scenariotest){
           
        ListIterator liQuery,liSparqlQuery,liSparqlQueryRes = null;
        String res[],clasF,indF;
        List<QueryOntology> queryTest = scenariotest.getTests();
        //List<SparqlQueryOntology> sparqlQueryTest = scenariotest.getSparqlTests();
        //List<String> resSparqlExpected = null;
        String resObtenidoInst="",resQueryExpected="";
        //SparqlQueryOntology sparql_qo = null;
        QueryOntology qo = null;
        //ArrayList<String> res_sparql_q = new ArrayList<String>();
        //boolean sparql_correct=true;
                
        ontologyTests tests = new ontologyTests();
        liQuery = queryTest.listIterator();
        //liSparqlQuery = sparqlQueryTest.listIterator();

        
        //while((liQuery.hasNext()) || (liSparqlQuery.hasNext())){
        while(liQuery.hasNext()){
            
            if(liQuery.hasNext()){
                qo = (QueryOntology) liQuery.next();
                String query = qo.getQuery();
                resQueryExpected = qo.getResultExpected();
                res = query.split(",");
                clasF = res[0];
                indF = res[1];
                resObtenidoInst = tests.instantiation(ns, clasF, indF, model);
                //String resObtenidoRet = tests.retieval(ns, clasF, model);
                //String resObtenidoReal = tests.realization(ns, indF, model); 
            }
            /*if(liSparqlQuery.hasNext()){
                sparql_qo = (SparqlQueryOntology) liSparqlQuery.next();
                String sparql_query = sparql_qo.getSparqlQuery();
                resSparqlExpected = sparql_qo.getResultExpected();
                sparql_q = QueryFactory.create(sparql_query);
                qe = QueryExecutionFactory.create(sparql_q, model_q);
                results = qe.execSelect();
                res_sparql_q = (ArrayList<String>) results.getResultVars();
                liSparqlQueryRes = res_sparql_q.listIterator();
            }           
            
            while(liSparqlQueryRes.hasNext()){
                if(!resSparqlExpected.contains(liSparqlQueryRes.next())){
                    sparql_correct=false;
                }
            }*/
            
            if(!resObtenidoInst.equals(resQueryExpected)){
                  testresult.addOntologyFailure(qo, resObtenidoInst);
            }
        }
        
    } 

    public void run(OntologyTestResult testresult, CollectionTest baterytest) { 

        String ont = baterytest.getOntology();
        String ns = baterytest.getNamespace();
       
        ListIterator liScenario;
        ScenarioTest scenariotest;
        ArrayList<ScenarioTest> listscenario = baterytest.getScenarioTest();
        liScenario = listscenario.listIterator();
 
        while(liScenario.hasNext()){
            scenariotest = (ScenarioTest) liScenario.next();
            setUpOntology(scenariotest, ont, ns);
            runOntologyTest(testresult,ns,scenariotest);
            tearDownOntology(); 
        }
        showResultTests(testresult);
   
    }
    
    public void addInstanceClass(String ns,String nameClass, String value){
        nameclass = model.createClass(ns + nameClass);
        classValue = model.createIndividual(ns + value,nameclass);
    }
    
    public void addInstanceProperty(String ns,String nameProperty, String value){
        nameprop = model.createProperty(ns + nameProperty);
        hasprop = model.createIndividual(ns + value,nameprop);
    }

    public void showResultTests(OntologyTestResult testresult){
        
        ListIterator liFailures;
        ArrayList<OntologyTestFailure> failures = testresult.getOntologyTestFailure();
        liFailures = failures.listIterator();
        
        // Output query results	
        //ResultSetFormatter.out(System.out, results, sparql_q);
        
        if(liFailures.hasNext()){
          System.out.println("De las pruebas introducidas han fallado las siguientes:");
        while(liFailures.hasNext()){
            OntologyTestFailure otf = (OntologyTestFailure) liFailures.next();
            System.out.println("De la query introducida " +otf.getfQuery());
            System.out.println("Se esperaba obtener : " +otf.getfResultExpected());
            System.out.println("Pero se obtuvo: " +otf.getResultQueryObtenido());
            }
        }else{
            System.out.println("No se han producido errores.");
        }
    }
        
}
