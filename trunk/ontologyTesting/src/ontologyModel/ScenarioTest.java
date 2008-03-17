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
    
    private ArrayList<String> classInstances = new ArrayList<String>();
    private ArrayList<String> propertyInstances = new ArrayList<String>();
    private ArrayList<QueryOntology> tests = new ArrayList<QueryOntology>();
    
    public ScenarioTest(List<String> classInstances, List<String> 
            propertyInstances, List<QueryOntology> tests){
        
        this.classInstances.addAll(classInstances);
        this.propertyInstances.addAll(propertyInstances);
        this.tests.addAll(tests);     
    
    }
    
    public ScenarioTest(){
        
        this.classInstances = new ArrayList<String>();
        this.propertyInstances = new ArrayList<String>();
        this.tests = new ArrayList<QueryOntology>();
    
    }    
    
    public List<String> getClassInstances(){
        return this.classInstances;
    }
    
    public List<String> getPropertyInstances(){
        return this.propertyInstances;
    }    
    
    public List<QueryOntology> getTests(){
        return this.tests;
    }
    
    public void setClassInstances(List<String> classInstances){
        this.classInstances.addAll(classInstances);
    }
    
    public void setPropertyInstances(List<String> propertyInstances){
        this.propertyInstances.addAll(propertyInstances);
    }    
    
    public void setOntologyQuerys(List<QueryOntology> tests){
        this.tests.addAll(tests);
    }      

}
