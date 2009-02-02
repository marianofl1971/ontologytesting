/*
 * ScenarioTest.java
 * 
 * Created on 16-feb-2008, 14:01:49
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

import code.google.com.p.ontologytesting.model.exectests.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Saruskas
 */

public class ScenarioTest implements Serializable{

    public enum TipoTest{
        INST,RET,REAL,SAT,CLAS,SPARQL;
    }
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
        this.tipoTest=tipoTest;
    }
    
    public ScenarioTest(){  
        this.instancias = new Instancias();
        this.queryTest = new ArrayList<QueryOntology>();
        this.sparqlQuerys = new ArrayList<SparqlQueryOntology>();
        this.nombre = "";
        this.descripcion = "";
        this.tipoTest=null;
    }
    
    public ScenarioTest(ScenarioTest nuevo){
        this.descripcion=nuevo.getDescripcion();
        this.nombre =nuevo.getNombre();
        this.instancias=nuevo.getInstancias();
        if(nuevo.getQueryTest().size()>0){
            for(int i=0; i<nuevo.getQueryTest().size();i++){
                this.queryTest.add(new QueryOntology(nuevo.getQueryTest().get(i)));
            }
        }
        if(nuevo.getSparqlQuerys().size()>0){
            for(int i=0; i<nuevo.getSparqlQuerys().size();i++){
                this.sparqlQuerys.add(new SparqlQueryOntology(nuevo.getSparqlQuerys().get(i)));
            }
        }
        this.setTipoTest(nuevo.getTipoTest());
    }
    
    public boolean tieneInstanciasAsociadas(){
        List<ClassInstances> clasI = this.getInstancias().getClassInstances();
        List<PropertyInstances> propI = this.getInstancias().getPropertyInstances();

        if(clasI.size()==0 && propI.size()==0){
            return false;
        }else{
            return true;
        }
    }
    
    public ScenarioTest buscarScenario(List<ScenarioTest> scenario, String name){
        for(int i=0;i<scenario.size();i++){
            String n = scenario.get(i).getNombre();
            if(n.equals(name)){
                return scenario.get(i);
            }
        }
        return null;
    }
    
    public boolean eliminarInstancias(){
        this.getInstancias().getClassInstances().clear();
        this.getInstancias().getPropertyInstances().clear();
        this.getInstancias().setDescripcion("");
        this.getInstancias().setNombre("");
        return true;
    }
    
    public boolean testYaExiste(List<ScenarioTest> lista,String nombre){
        ListIterator li;
        li = lista.listIterator();
        while(li.hasNext()){
            ScenarioTest s = (ScenarioTest) li.next();
            String n = s.getNombre();
            if(n.equals(nombre)){
                return true;
            }
        }
        return false;
    }
    
    public OntologyTestCase getOntologyTestCase(){
        OntologyTestCase ontologyTestCase = null;
        if(this.getTipoTest().name().equals("INST")){
            ontologyTestCase = new OntologyTestInstantiation();
        }else if(this.getTipoTest().name().equals("RET")){
            ontologyTestCase = new OntologyTestRetrieval();   
        }else if(this.getTipoTest().name().equals("REAL")){
            ontologyTestCase = new OntologyTestRealization();
        }else if(this.getTipoTest().name().equals("SAT")){
            ontologyTestCase = new OntologyTestSatisfactibility();
        }else if(this.getTipoTest().name().equals("CLAS")){
            ontologyTestCase = new OntologyTestClassification();
        }else if(this.getTipoTest().name().equals("SPARQL")){
            ontologyTestCase = new OntologyTestSparql();
        }
        return ontologyTestCase;
    }

    public TipoTest getTipoTest() {
        return tipoTest;
    }

    public void setTipoTest(TipoTest tipoTest) {
        this.tipoTest = tipoTest;
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
                    return true;
                }
            }
            return false;
        }else return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        hash = 61 * hash + (this.descripcion != null ? this.descripcion.hashCode() : 0);
        hash = 61 * hash + (this.instancias != null ? this.instancias.hashCode() : 0);
        hash = 61 * hash + (this.queryTest != null ? this.queryTest.hashCode() : 0);
        hash = 61 * hash + (this.sparqlQuerys != null ? this.sparqlQuerys.hashCode() : 0);
        return hash;
    }
    
    public boolean esVacio(){
        if(this.getNombre().equals("") && this.getDescripcion().equals("")
                && this.getInstancias().esVacio()==true && this.getQueryTest().size()==0
                && this.getSparqlQuerys().size()==0){
                return true;
        }else{
            return false;
        }
    }

}
