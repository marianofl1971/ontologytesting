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

    private List<ScenarioTest> scenariotest = new ArrayList<ScenarioTest>();
    private List<Instancias> instancias = new ArrayList<Instancias>();
    private String ontology="";
    private String namespace="";
    private static CollectionTest collection = null;
 
    private CollectionTest() {
        this.scenariotest = new ArrayList<ScenarioTest>();
        this.ontology = "";
        this.namespace = "";
        this.instancias= new ArrayList<Instancias>();
    }
 
    private synchronized static void createCollection() {
        if (collection == null) { 
            collection = new CollectionTest();
        }
    }
 
    public static CollectionTest getInstance() {
        if (collection == null) createCollection();
        return collection;
    }
    
    public List<ScenarioTest> getScenariotest() {
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

    public List<Instancias> getInstancias() {
        return instancias;
    }

    public void setInstancias(ArrayList<Instancias> instancias) {
        this.instancias = instancias;
    }
    
}
