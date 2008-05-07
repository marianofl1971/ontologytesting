/*
 * ScenarioTest.java
 * 
 * Created on 16-feb-2008, 14:01:49
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
public class ScenarioTest {
    
    private String test_name;
    private ArrayList<String> classInstances = new ArrayList<String>();
    private ArrayList<String> propertyInstances = new ArrayList<String>();
    private ArrayList<QueryOntology> queryTest = new ArrayList<QueryOntology>();
    private ArrayList<SparqlQueryOntology> sparqlQuerys = new 
            ArrayList<SparqlQueryOntology>();
    
    public ScenarioTest(List<String> classInstances, List<String> 
            propertyInstances, List<QueryOntology> tests, String test_name,
            List<SparqlQueryOntology> sparqlquerys){
        
        this.classInstances.addAll(classInstances);
        this.propertyInstances.addAll(propertyInstances);
        this.sparqlQuerys.addAll(sparqlquerys);
        this.queryTest.addAll(tests);   
        this.test_name=test_name;   
    }
    
    public ScenarioTest(List<String> classInstances, List<String> 
            propertyInstances, List<QueryOntology> tests, String test_name){
        
        this.classInstances.addAll(classInstances);
        this.propertyInstances.addAll(propertyInstances);
        this.queryTest.addAll(tests);   
        this.test_name=test_name;   
    }    
    
    public ScenarioTest(List<String> classInstances, List<String> 
            propertyInstances, String test_name, List<SparqlQueryOntology> 
            sparqlquerys){
        
        this.classInstances.addAll(classInstances);
        this.propertyInstances.addAll(propertyInstances);
        this.sparqlQuerys.addAll(sparqlquerys);   
        this.test_name=test_name;   
    }    
    
    public ScenarioTest(){
        
        this.classInstances = new ArrayList<String>();
        this.propertyInstances = new ArrayList<String>();
        this.queryTest = new ArrayList<QueryOntology>();
        this.test_name=null;
    
    }    
    
    public List<String> getClassInstances(){
        return classInstances;
    }
    
    public List<String> getPropertyInstances(){
        return propertyInstances;
    }    
    
    public List<QueryOntology> getTests(){
        return queryTest;
    }

    public ArrayList<SparqlQueryOntology> getSparqlQueryOntologys(){
        return sparqlQuerys;
    }
    
    public String getTestName(){
        return test_name;
    }    
    
    public void setClassInstances(List<String> classInstances){
        classInstances.addAll(classInstances);
    }
    
    public void setPropertyInstances(List<String> propertyInstances){
        propertyInstances.addAll(propertyInstances);
    }    
    
    public void setOntologyQuerys(List<QueryOntology> tests){
        this.queryTest.addAll(tests);
    }    
    
    public void setTestName(String test_name){
        this.test_name=test_name;
    } 
    
    public void setSparqlQueryOntologys(List<SparqlQueryOntology> sparqlquerys){
        this.sparqlQuerys.addAll(sparqlquerys);
    }    

}
