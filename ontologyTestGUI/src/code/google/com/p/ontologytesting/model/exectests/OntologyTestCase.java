/*
 * OntologyTestCase.java
 * 
 * Created on 16-feb-2008, 14:44:25
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model.exectests;

import code.google.com.p.ontologytesting.model.*;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.*;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Saruskas
 */
public class OntologyTestCase implements OntologyTest{
    
    private Reasoner jenaInterface;   
    private OntologyTestInstantiation testInst = new OntologyTestInstantiation();
    private OntologyTestRetrieval testRet = new OntologyTestRetrieval();
    private OntologyTestRealization testRealiz = new OntologyTestRealization();
    private OntologyTestSatisfactibility testSatis = new OntologyTestSatisfactibility();
    private OntologyTestClassification testClas = new OntologyTestClassification();
    private OntologyTestSparql testSparql = new OntologyTestSparql();
    private InterfaceReasoner jena;
    private String patron1="",patron2="";

    protected void setUpOntology(ScenarioTest st, String ont, String ns) throws InvalidOntologyException{  

        ListIterator liClass;
        ListIterator liProperties;
        String[] ciClas,ciInd,piClas,piInd;
        patron1 = "[\\(|,|\n| ]";
        patron2 = "[\n| |\\)]";
        jenaInterface = new Reasoner();
        jena = jenaInterface.getReasoner();
        if(jenaInterface.isCargado()==true){
            jena.addReasoner(ont);
            Instancias instancias = st.getInstancias();
            List<ClassInstances> classInstances = instancias.getClassInstances();
            List<PropertyInstances> propertyInstances = instancias.getPropertyInstances();
            liClass = classInstances.listIterator();
            liProperties = propertyInstances.listIterator();
            while (liClass.hasNext()) {
                ClassInstances cla = (ClassInstances) liClass.next();
                String ci = cla.getClassInstance();
                ciClas = ci.split(patron1);
                ciInd = ciClas[1].split(patron2);
                jena.addInstanceClass(ns, ciClas[0], ciInd[0]);
            }
            while (liProperties.hasNext()) {
                PropertyInstances p = (PropertyInstances) liProperties.next();
                String pi = p.getPropertyInstance();
                piClas = pi.split(patron1);
                piInd = piClas[1].split(patron2);
                jena.addInstanceProperty(ns, piClas[0], piInd[0]);
            }       
        }
    }
    
    protected void tearDownOntology(){
        jena.deleteEntries();
    }
    
    @Override
    public void run(OntologyTestResult testresult, String ont, String ns, ScenarioTest scenariotest) throws InvalidOntologyException{ 
        setUpOntology(scenariotest, ont, ns);
        testInst.run(testresult, ns, ont, scenariotest);
        testRet.run(testresult, ont, ns, scenariotest);
        testRealiz.run(testresult, ont, ns, scenariotest);
        testSatis.run(testresult, ont, ns, scenariotest);
        testClas.run(testresult, ont, ns, scenariotest);
        testSparql.run(testresult, ont, ns, scenariotest);
        tearDownOntology(); 
    }
}    

