/*
 * CollectionTest.java
 * 
 * Created on 16-feb-2008, 14:39:46
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Saruskas
 */
public class CollectionTest {

    private ArrayList<ScenarioTest> scenariotest = new ArrayList<ScenarioTest>();
    private String ontology;
    private String namespace;
    
    public CollectionTest(List<ScenarioTest> scenariotest, String ontology, 
            String namespace){
        
        this.scenariotest.addAll(scenariotest);
        this.ontology = ontology;
        this.namespace = namespace;
    }
    
    public CollectionTest(){
        
        this.scenariotest = new ArrayList<ScenarioTest>();
        this.ontology = "";
        this.namespace = "";
    }

    public ArrayList<ScenarioTest> getScenariotest() {
        return scenariotest;
    }

    public void setScenariotest(ArrayList<ScenarioTest> scenariotest) {
        this.scenariotest = scenariotest;
    }

    public String getOntology() {
        return ontology;
    }

    public void setOntology(String ontology) {
        this.ontology = ontology;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
    
 
    
}
