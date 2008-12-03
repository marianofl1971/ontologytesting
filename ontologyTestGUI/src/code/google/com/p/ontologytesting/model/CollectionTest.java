/*
 * CollectionTest.java
 * 
 * Created on 16-feb-2008, 14:39:46
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Saruskas
 */
public class CollectionTest implements Serializable{

    private ArrayList<ScenarioTest> scenariotest = new ArrayList<ScenarioTest>();
    private ArrayList<Instancias> instancias = new ArrayList<Instancias>();
    private String ontology="";
    private String namespace="";
    private static CollectionTest collection = null;
 
    public CollectionTest() {
        this.scenariotest = new ArrayList<ScenarioTest>();
        this.ontology = "";
        this.namespace = "";
        this.instancias= new ArrayList<Instancias>();
    }
 
    public synchronized static void createCollection() {
        if (collection == null) { 
            collection = new CollectionTest();
        }
    }
 
    public static CollectionTest getInstance() {
        if (collection == null) createCollection();
        return collection;
    }
    
    public void destroy(){
        this.scenariotest = new ArrayList<ScenarioTest>();
        this.ontology = "";
        this.namespace = "";
        this.instancias= new ArrayList<Instancias>();
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

    public ArrayList<Instancias> getInstancias() {
        return instancias;
    }

    public void setInstancias(ArrayList<Instancias> instancias) {
        this.instancias = instancias;
    }
    
}
