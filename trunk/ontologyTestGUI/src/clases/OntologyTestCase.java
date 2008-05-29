/*
 * OntologyTestCase.java
 * 
 * Created on 16-feb-2008, 14:44:25
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import model.ClassInstances;
import model.CollectionTest;
import model.PropertyInstances;
import model.QueryOntology;
import model.ScenarioTest;
import tests.OntologyTests;
import org.mindswap.pellet.jena.PelletReasonerFactory;

/**
 *
 * @author Saruskas
 */
public class OntologyTestCase implements OntologyTest{
    
    public String ontologyname;
    private OntModel model;
    private OntClass nameclass;
    private Individual classValue, hasprop;
    private Property nameprop;
    private XMLDecoder decoder;
    private ArrayList al;
    private ScenarioTest qo;
    private CollectionTest collectionTest;
                    
    public OntologyTestCase(){
    }

    protected void setUpOntology(ScenarioTest st, String ont, String ns){
        
    try{
         decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("instances.xml")));
         collectionTest = (CollectionTest)decoder.readObject();
         ArrayList<ScenarioTest> scenarioTest = collectionTest.getScenariotest();
         QueryOntology query;
         ListIterator li,lq;
         li = scenarioTest.listIterator();
         while(li.hasNext()){
            qo = (ScenarioTest) li.next();
            ArrayList<QueryOntology> q = qo.getQueryTest();
            lq = q.listIterator();
            while(lq.hasNext()){
                query = (QueryOntology) lq.next();
            System.out.println("Query con xmldecoder: "+query.getQuery());
            System.out.println("Result con xmldecoder: "+query.getResultexpected());
            }
         }
         decoder.close();           
    }catch(FileNotFoundException e){
    }       
        
    ListIterator liClass,liProperties;   
    String ciClas[],ciInd[],piClas[],piInd[];    
        
    model = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC );
    model.read(ont);  
    model.prepare();
   
    List<ClassInstances> classInstances = st.getClassInstances();
    List<PropertyInstances> propertyInstances = st.getPropertyInstances();
  
    liClass = classInstances.listIterator();
    liProperties = propertyInstances.listIterator();
        
    while(liClass.hasNext()){
            ClassInstances cla = (ClassInstances) liClass.next();
            String ci = cla.getClassInstance();
            ciClas = ci.split("\\(");
            ciInd = ciClas[1].split("\\)");
            addInstanceClass(ns,ciClas[0],ciInd[0]);
    }
        
    while(liProperties.hasNext()){
            PropertyInstances p = (PropertyInstances) liProperties.next();
            String pi = p.getPropertyInstance();
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
    }
    
    private void runOntologyTest(OntologyTestResult testresult, String ns, 
            ScenarioTest scenariotest){
           
        ListIterator liQuery;
        String res[],clasF,indF;
        List<QueryOntology> queryTest = scenariotest.getQueryTest();
        
        String resObtenidoInst="",resQueryExpected="",resObtenidoSat="";
        QueryOntology qo = null;
                
        OntologyTests tests = new OntologyTests();
        liQuery = queryTest.listIterator();
        
        while(liQuery.hasNext()){
            
            if(liQuery.hasNext()){
                qo = (QueryOntology) liQuery.next();
                String query = qo.getQuery();
                resQueryExpected = qo.getResultexpected();
                res = query.split(",");
                clasF = res[0];
                indF = res[1];
                resObtenidoInst = tests.instantiation(ns, clasF, indF, model);
                //StringBuilder resObtenidoRet = tests.retieval(ns, clasF, model);
                //String resObtenidoReal = tests.realization(ns, indF, model); 
                //resObtenidoSat = tests.satisfactibility(ns,model,"Male","Person");
                //ArrayList result = tests.classification(ns,model,"tom");
                /*ListIterator l;
                l = result.listIterator();
                while(l.hasNext()){
                    System.out.println(l.next());
                }*/
            }
            
            if(!resObtenidoInst.equals(resQueryExpected)){
                testresult.addOntologyFailureQuery(qo, resObtenidoInst);
            }
        }
        
    } 

    public void run(OntologyTestResult testresult, CollectionTest baterytest) { 

        String ont = baterytest.getOntology();
        String ns = baterytest.getNamespace();
       
        ListIterator liScenario;
        ScenarioTest scenariotest;
        ArrayList<ScenarioTest> listscenario = baterytest.getScenariotest();
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
        ArrayList<OntologyTestFailure> failures = testresult.getOntologyTestFailureQuery();
        liFailures = failures.listIterator();
        
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
