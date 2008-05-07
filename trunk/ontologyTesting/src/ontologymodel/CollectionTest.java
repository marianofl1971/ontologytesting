/*
 * CollectionTest.java
 * 
 * Created on 16-feb-2008, 14:39:46
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontologymodel;

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
    
    public CollectionTest(List<ScenarioTest> scenariotest, 
            List<SparqlQueryOntology> sparqlquerys, String ontology, 
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
    
    public String getOntology(){
        return ontology;
    }
    
    public String getNamespace(){
        return namespace;
    }
    
    public void setOntology(String ontology){
        this.ontology=ontology;
    }
    
    public void setNamespace(String namespace){
        this.namespace=namespace;
    }
    
    public ArrayList<ScenarioTest> getScenarioTest(){
        return scenariotest;
    }   
    
    public void setScenarioTest(List<ScenarioTest> scenariotest){
        this.scenariotest.addAll(scenariotest);
    }    
    
}
