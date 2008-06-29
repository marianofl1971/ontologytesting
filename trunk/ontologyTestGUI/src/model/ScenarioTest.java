/*
 * ScenarioTest.java
 * 
 * Created on 16-feb-2008, 14:01:49
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Saruskas
 */
public class ScenarioTest {
    
    private String testName;
    private String nombre;
    private String descripcion;
    private ArrayList<ClassInstances> classInstances = new ArrayList<ClassInstances>();
    private ArrayList<PropertyInstances> propertyInstances = new ArrayList<PropertyInstances>();
    private ArrayList<QueryOntology> queryTest = new ArrayList<QueryOntology>();
    private ArrayList<SparqlQueryOntology> sparqlQuerys = new 
            ArrayList<SparqlQueryOntology>();
    
    public ScenarioTest(List<ClassInstances> classInstances, List<PropertyInstances> 
            propertyInstances, List<QueryOntology> tests, String testName,
            List<SparqlQueryOntology> sparqlquerys,String nombre,String descripcion){
        
        this.classInstances.addAll(classInstances);
        this.propertyInstances.addAll(propertyInstances);
        this.sparqlQuerys.addAll(sparqlquerys);
        this.queryTest.addAll(tests);   
        this.testName=testName;   
        this.nombre=nombre;
        this.descripcion=descripcion;
    }
    
    public ScenarioTest(List<ClassInstances> classInstances, List<PropertyInstances> 
            propertyInstances, List<QueryOntology> tests, String testName){
        
        this.classInstances.addAll(classInstances);
        this.propertyInstances.addAll(propertyInstances);
        this.queryTest.addAll(tests);   
        this.testName=testName;  
    }    
    
    public ScenarioTest(List<ClassInstances> classInstances, List<PropertyInstances> 
            propertyInstances, String testName, List<SparqlQueryOntology> 
            sparqlquerys,String nombre,String descripcion){
        
        this.classInstances.addAll(classInstances);
        this.propertyInstances.addAll(propertyInstances);
        this.sparqlQuerys.addAll(sparqlquerys);   
        this.testName=testName; 
        this.nombre=nombre;
        this.descripcion=descripcion;
    }    
    
    public ScenarioTest(){
        
        this.classInstances = new ArrayList<ClassInstances>();
        this.propertyInstances = new ArrayList<PropertyInstances>();
        this.queryTest = new ArrayList<QueryOntology>();
        this.testName=null;
        this.sparqlQuerys=new ArrayList<SparqlQueryOntology>();
        this.nombre=null;
        this.descripcion=null;
    
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String test_name) {
        this.testName = test_name;
    }

    public ArrayList<ClassInstances> getClassInstances() {
        return classInstances;
    }

    public void setClassInstances(ArrayList<ClassInstances> classInstances) {
        this.classInstances = classInstances;
    }

    public ArrayList<PropertyInstances> getPropertyInstances() {
        return propertyInstances;
    }

    public void setPropertyInstances(ArrayList<PropertyInstances> propertyInstances) {
        this.propertyInstances = propertyInstances;
    }

    public ArrayList<QueryOntology> getQueryTest() {
        return queryTest;
    }

    public void setQueryTest(ArrayList<QueryOntology> queryTest) {
        this.queryTest = queryTest;
    }

    public ArrayList<SparqlQueryOntology> getSparqlQuerys() {
        return sparqlQuerys;
    }

    public void setSparqlQuerys(ArrayList<SparqlQueryOntology> sparqlQuerys) {
        this.sparqlQuerys = sparqlQuerys;
    } 
    
    public void clearClasInstances(){
        this.classInstances.clear();
    }
    
    public void clearPropInstances(){
        this.classInstances.clear();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
