/*
 * ScenarioTest.java
 * 
 * Created on 16-feb-2008, 14:01:49
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontologyModel;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Saruskas
 */
public class ScenarioTest {
    
    public ArrayList<String> classInstances = new ArrayList<String>();
    public ArrayList<String> propertyInstances = new ArrayList<String>();
    public ArrayList<QueryOntology> tests = new ArrayList<QueryOntology>();
    public ArrayList<SparqlQueryOntology> sparql_test = new ArrayList<SparqlQueryOntology>();
    
    public ScenarioTest(List<String> classInstances, List<String> 
            propertyInstances, List<QueryOntology> tests, 
            List<SparqlQueryOntology> sparql_test){
        
        this.classInstances.addAll(classInstances);
        this.propertyInstances.addAll(propertyInstances);
        this.tests.addAll(tests);   
        this.sparql_test.addAll(sparql_test);
    
    }
    
    public ScenarioTest(){
        
        this.classInstances = new ArrayList<String>();
        this.propertyInstances = new ArrayList<String>();
        this.tests = new ArrayList<QueryOntology>();
        this.sparql_test = new ArrayList<SparqlQueryOntology>();
    
    }    
    
    public List<String> getClassInstances(){
        return classInstances;
    }
    
    public List<String> getPropertyInstances(){
        return propertyInstances;
    }    
    
    public List<QueryOntology> getTests(){
        return tests;
    }
    
    public List<SparqlQueryOntology> getSparqlTests(){
        return sparql_test;
    }
    
    public void setClassInstances(List<String> classInstances){
        classInstances.addAll(classInstances);
    }
    
    public void setPropertyInstances(List<String> propertyInstances){
        propertyInstances.addAll(propertyInstances);
    }    
    
    public void setOntologyQuerys(List<QueryOntology> tests){
        this.tests.addAll(tests);
    }    
    
    public void setSparqlOntologyQuerys(List<SparqlQueryOntology> sparql_test){
        this.sparql_test.addAll(sparql_test);
    }  

}
