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
    boolean namepropIsUsed=false;
    boolean nameclasIsUsed=false;
                    
    public OntologyTestCase(){
    }

    protected void setUpOntology(ScenarioTest st, String ont, String ns){  
        
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
        if(nameclasIsUsed==true){
            nameclass.remove();
            classValue.remove();
        }
        if(namepropIsUsed==true){
            nameprop.removeProperties();
            hasprop.remove();
        }    
    }
    
    private void runOntologyTest(OntologyTestResult testresult, String ns, 
            ScenarioTest scenariotest){
           
        ListIterator liQuery;
        String res[],clasF="",indF="",concepto="",loincluye="";
        String testName = scenariotest.getTestName();
        List<QueryOntology> queryTest = scenariotest.getQueryTest();
        
        String resObtenidoInst="",resQueryExpected="",resObtenidoClas="",resObtenidoRet="",
                resObtenidoRealiz="",resObtenidoSatisf="";
        QueryOntology qo = null;
                
        OntologyTests tests = new OntologyTests();
        liQuery = queryTest.listIterator();
        
        while(liQuery.hasNext()){
            
            if(liQuery.hasNext()){
                qo = (QueryOntology) liQuery.next();
                String query = qo.getQuery();
                resQueryExpected = qo.getResultexpected();
                if(testName.equals("Instanciación")){
                    res = query.split(",");
                    clasF = res[0];
                    indF = res[1];
                    resObtenidoInst = tests.instantiation(ns, clasF, indF, model);
                    if(!resObtenidoInst.equals(resQueryExpected)){
                        testresult.addOntologyFailureQuery(testName,qo, resObtenidoInst);
                    }
                }else if(testName.equals("Retrieval")){
                    resObtenidoRet = tests.retieval(ns, query, model);
                    if(!resObtenidoRet.equals(resQueryExpected)){
                        testresult.addOntologyFailureQuery(testName,qo, resObtenidoRet);
                    }
                }else if(testName.equals("Realización")){
                    resObtenidoRealiz = tests.realization(ns, query, model);
                    if(!resObtenidoRealiz.equals(resQueryExpected)){
                        testresult.addOntologyFailureQuery(testName,qo, resObtenidoRealiz);
                    }
                }/*else if(testName.equals("Satisfactibilidad")){
                    res = query.split(",");
                    concepto = res[0];
                    loincluye = res[1];
                    resObtenidoSatisf = tests.satisfactibility(ns, model, concepto, loincluye);
                    if(!resObtenidoSatisf.equals(resQueryExpected)){
                        testresult.addOntologyFailureQuery(testName,qo, resObtenidoSatisf);
                    }
                }*/else if(testName.equals("Clasificación")){
                    resObtenidoClas = tests.classification(ns, model, query);
                    if(!resObtenidoClas.equals(resQueryExpected)){
                        testresult.addOntologyFailureQuery(testName,qo, resObtenidoClas);
                    }
                }
            }
            
            
        }
        
    } 

    @Override
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
        nameclasIsUsed=true;
    }
    
    public void addInstanceProperty(String ns,String nameProperty, String value){
        nameprop = model.createProperty(ns + nameProperty);
        hasprop = model.createIndividual(ns + value,nameprop);
        namepropIsUsed=true;
    }

    public void showResultTests(OntologyTestResult testresult){
        
        ListIterator liFailures;
        ArrayList<OntologyTestFailure> failures = testresult.getOntologyTestFailureQuery();
        liFailures = failures.listIterator();
        
        if(liFailures.hasNext()){
          System.out.println("De las pruebas introducidas han fallado las siguientes:");
        while(liFailures.hasNext()){
            OntologyTestFailure otf = (OntologyTestFailure) liFailures.next();
            
            if(otf.getTestName().equals("Instanciación")){
                System.out.println("HAN FALLADO DE LOS TEST DE INSTANCIACION:");
            }else if(otf.getTestName().equals("Retrieval")){
                System.out.println("HAN FALLADO DE LOS TEST RETRIEVAL:");
            }else if(otf.getTestName().equals("Realización")){
                System.out.println("HAN FALLADO DE LOS TESTS DE REALIZACIÓN:");
            }else if(otf.getTestName().equals("Clasificación")){
                System.out.println("HAN FALLADO DE LOS TESTS DE CLASIFICACIÓN:");
            }else if(otf.getTestName().equals("Satisfactibilidad")){
                System.out.println("HAN FALLADO DE LOS TESTS DE SATISFACTIBILIDAD:");
            }
            System.out.println("De la query introducida " +otf.getfQuery());
            System.out.println("Se esperaba obtener : " +otf.getfResultExpected());
            System.out.println("Pero se obtuvo: " +otf.getResultQueryObtenido());
            }
        }else{
            System.out.println("No se han producido errores.");
        }
    }
        
}
