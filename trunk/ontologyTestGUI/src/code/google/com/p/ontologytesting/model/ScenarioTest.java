/*
 * ScenarioTest.java
 * 
 * Created on 16-feb-2008, 14:01:49
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
public class ScenarioTest {
    
    private String testName="";
    private String nombre="";
    private String descripcion="";
    private Instancias instancias;
    private List<QueryOntology> queryTest;
    private List<SparqlQueryOntology> sparqlQuerys;
    
    public ScenarioTest(Instancias instancias, List<QueryOntology> tests, String testName,
            List<SparqlQueryOntology> sparqlquerys,String nombre,String descripcion){

        this.sparqlQuerys.addAll(sparqlquerys);
        this.queryTest.addAll(tests);   
        this.testName=testName;   
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.instancias = instancias;
    }
    
    public ScenarioTest(Instancias instancias, List<QueryOntology> tests, 
            String testName){
        
        this.instancias = instancias;
        this.queryTest.addAll(tests);   
        this.testName=testName;  
    }    
    
    public ScenarioTest(Instancias instancias, String testName, 
            List<SparqlQueryOntology> sparqlquerys, String nombre,
            String descripcion){
        
        this.instancias = instancias;
        this.sparqlQuerys.addAll(sparqlquerys);   
        this.testName=testName; 
        this.nombre=nombre;
        this.descripcion=descripcion;
    }    
    
    public ScenarioTest(){  
        this.instancias = new Instancias();
        this.queryTest = new ArrayList<QueryOntology>();
        this.testName = "";
        this.sparqlQuerys = new ArrayList<SparqlQueryOntology>();
        this.nombre = "";
        this.descripcion = "";
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String test_name) {
        this.testName = test_name;
    }

    public List<QueryOntology> getQueryTest() {
        return queryTest;
    }

    public void setQueryTest(List<QueryOntology> queryTest) {
        this.queryTest = queryTest;
    }

    public List<SparqlQueryOntology> getSparqlQuerys() {
        return sparqlQuerys;
    }

    public void setSparqlQuerys(List<SparqlQueryOntology> sparqlQuerys) {
        this.sparqlQuerys = sparqlQuerys;
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

    public Instancias getInstancias() {
        return instancias;
    }

    public void setInstancias(Instancias instancias) {
        this.instancias = instancias;
    }

}
