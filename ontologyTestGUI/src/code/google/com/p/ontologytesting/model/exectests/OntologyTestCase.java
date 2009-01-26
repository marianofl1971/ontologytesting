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

    public InterfaceReasoner setUpOntology(ScenarioTest st, String ont, String ns) throws InvalidOntologyException{  
        Reasoner jenaInterface;   
        InterfaceReasoner jena;
        ListIterator liClass;
        ListIterator liProperties;
        String[] ciClas,ciInd,piClas,piInd;
        String patron1 = "[\\(|,|\n| ]";
        String patron2 = "[\n| |\\)]";
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
        return jena;
    }
    
    public void tearDownOntology(InterfaceReasoner jena){
        jena.deleteEntries();
    }
    
    @Override
    public void run(OntologyTestResult testresult, String ont, String ns, ScenarioTest scenariotest) throws InvalidOntologyException{ 
        OntologyTestInstantiation testInst = new OntologyTestInstantiation();
        OntologyTestRetrieval testRet = new OntologyTestRetrieval();
        OntologyTestRealization testRealiz = new OntologyTestRealization();
        OntologyTestSatisfactibility testSatis = new OntologyTestSatisfactibility();
        OntologyTestClassification testClas = new OntologyTestClassification();
        OntologyTestSparql testSparql = new OntologyTestSparql();
        
        InterfaceReasoner j = setUpOntology(scenariotest, ont, ns);
        testInst.run(testresult, ns, ont, scenariotest,j);
        testRet.run(testresult, ont, ns, scenariotest,j);
        testRealiz.run(testresult, ont, ns, scenariotest,j);
        testSatis.run(testresult, ns, ont, scenariotest,j);
        testClas.run(testresult, ont, ns, scenariotest,j);
        testSparql.run(testresult, ont, ns, scenariotest,j);
        tearDownOntology(j); 
    }
}    

