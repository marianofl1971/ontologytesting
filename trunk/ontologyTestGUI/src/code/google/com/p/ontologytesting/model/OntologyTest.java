/*
 * OntologyTest.java
 * 
 * Created on 16-feb-2008, 13:53:30
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

import code.google.com.p.ontologytesting.exceptions.ExceptionReadOntology;

/**
 *
 * @author Saruskas
 */
public interface OntologyTest {
    
    public void run(OntologyTestResult testresult, CollectionTest baterytest) throws ExceptionReadOntology;
    
    public void runScenario(OntologyTestResult testresult, CollectionTest baterytest, ScenarioTest scenario) throws ExceptionReadOntology;

}