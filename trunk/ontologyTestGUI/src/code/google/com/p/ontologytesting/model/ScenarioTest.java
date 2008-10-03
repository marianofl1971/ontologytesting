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
    private Instancias instancias = new Instancias();
    private List<QueryOntology> queryTest = new ArrayList<QueryOntology>();
    private List<SparqlQueryOntology> sparqlQuerys = new ArrayList<SparqlQueryOntology>();

    public ScenarioTest(Instancias instancias, List<QueryOntology> tests, String testName,
            List<SparqlQueryOntology> sparqlquerys,String nombre,String descripcion){

        this.testName=testName;
        this.sparqlQuerys.addAll(sparqlquerys);
        this.queryTest.addAll(tests);   
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
    
    public ScenarioTest(ScenarioTest nuevo){
        this.setDescripcion(nuevo.getDescripcion());
        this.setNombre(nuevo.getNombre());
        this.setInstancias(nuevo.getInstancias());
        this.setQueryTest(nuevo.getQueryTest());
        this.setSparqlQuerys(nuevo.getSparqlQuerys());
        this.setTestName(nuevo.getTestName());
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

    @Override
    public boolean equals(Object object){
        if((object != null) && (object instanceof ScenarioTest)){
            ScenarioTest s2 = (ScenarioTest) object;
            for(int i=0; i<this.getQueryTest().size(); i++){
                if(!this.getQueryTest().get(i).equals(s2.getQueryTest().get(i)))
                    return false;
            }
            for(int i=0; i<this.getSparqlQuerys().size(); i++){
                if(!this.getSparqlQuerys().get(i).equals(s2.getSparqlQuerys().get(i)))
                    return false;
            }
            if(!this.getInstancias().equals(s2.getInstancias())){
                return false;
            }
            if(this.getDescripcion().equals(s2.getDescripcion())){
                if(this.getNombre().equals(s2.getNombre())){
                    if(this.getTestName().equals(s2.getTestName())){
                            return true;
                    }
                }
            }
            return false;
        }else return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.testName != null ? this.testName.hashCode() : 0);
        hash = 61 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        hash = 61 * hash + (this.descripcion != null ? this.descripcion.hashCode() : 0);
        hash = 61 * hash + (this.instancias != null ? this.instancias.hashCode() : 0);
        hash = 61 * hash + (this.queryTest != null ? this.queryTest.hashCode() : 0);
        hash = 61 * hash + (this.sparqlQuerys != null ? this.sparqlQuerys.hashCode() : 0);
        return hash;
    }
}
