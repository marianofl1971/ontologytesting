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

    public enum TipoTest{
        INST(0),RET(1),REAL(2),SAT(3),CLAS(4),SPARQL(5);
        private int tipo;
        TipoTest(int tipo){
            this.tipo=tipo;
        }
        public int getTipo(){
            return this.tipo;
        }
        public void setTipo(int tipo){
            this.tipo=tipo;
        }
    }
    private String testName="";
    private String nombre="";
    private String descripcion="";
    private Instancias instancias = new Instancias();
    private List<QueryOntology> queryTest = new ArrayList<QueryOntology>();
    private List<SparqlQueryOntology> sparqlQuerys = new ArrayList<SparqlQueryOntology>();
    private TipoTest tipoTest;

    
    public ScenarioTest(TipoTest tipoTest){  
        this.instancias = new Instancias();
        this.queryTest = new ArrayList<QueryOntology>();
        this.sparqlQuerys = new ArrayList<SparqlQueryOntology>();
        this.nombre = "";
        this.descripcion = "";
        this.testName="";
        this.tipoTest=tipoTest;
    }
    
    public ScenarioTest(ScenarioTest nuevo){
        this.setDescripcion(nuevo.getDescripcion());
        this.setNombre(nuevo.getNombre());
        this.setInstancias(nuevo.getInstancias());
        this.setQueryTest(nuevo.getQueryTest());
        this.setSparqlQuerys(nuevo.getSparqlQuerys());
        //this.setTestName(nuevo.getTestName());
    }
    
    public ScenarioTest(){
    }

    public TipoTest getTipoTest() {
        return tipoTest;
    }

    public void setTipoTest(TipoTest tipoTest) {
        this.tipoTest = tipoTest;
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
            if(this.getQueryTest().size()==s2.getQueryTest().size()){
                for(int i=0; i<this.getQueryTest().size(); i++){
                    if(!this.getQueryTest().get(i).equals(s2.getQueryTest().get(i)))
                        return false;
                }
            }else return false;
            if(this.getSparqlQuerys().size()==s2.getSparqlQuerys().size()){
                for(int i=0; i<this.getSparqlQuerys().size(); i++){
                    if(!this.getSparqlQuerys().get(i).equals(s2.getSparqlQuerys().get(i)))
                        return false;
                }
            }else return false;
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
    
    public void esVacio(){
        
    }

}
