/*
 * OntologyTestResult.java
 * 
 * Created on 16-feb-2008, 14:43:59
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

import java.util.ArrayList;

/**
 *
 * @author Saruskas
 */
public class OntologyTestResult extends Object{

    protected ArrayList<OntologyTestFailure> ontologyfailuresquerys;
    protected ArrayList<OntologyTestFailure> ontologyfailuressparql;
    protected ArrayList<OntologyTestPassed> ontologypassedquerys;
    protected ArrayList<OntologyTestPassed> ontologypassedsparql;
    
    public OntologyTestResult(){
        ontologyfailuresquerys = new ArrayList<OntologyTestFailure>();
        ontologyfailuressparql = new ArrayList<OntologyTestFailure>();
        ontologypassedquerys = new ArrayList<OntologyTestPassed>();
        ontologypassedsparql = new ArrayList<OntologyTestPassed>();
    }
    
    public void addOntologyFailureQuery(String testNameUsuario, String testName,QueryOntology query, String resObte){
    
        OntologyTestFailure ontologytestfailure = new OntologyTestFailure();
        ontologytestfailure.addOntologyTestFailureQuery(testNameUsuario, testName,query, resObte);
        ontologyfailuresquerys.add(ontologytestfailure);
    }
    
    public void addOntologyFailureSparql(String testNameUsuario, String testName, SparqlQueryOntology sparqlquery, 
            ArrayList<String> resObte){
    
        OntologyTestFailure ontologytestfailure = new OntologyTestFailure();
        ontologytestfailure.addOntologyTestFailureSparql(testNameUsuario, testName,sparqlquery,resObte);
        ontologyfailuressparql.add(ontologytestfailure);
    }
    
    public void addOntologyPassedTestQuery(String testNameUsuario, String testName){
        OntologyTestPassed ontologytestpassed = new OntologyTestPassed();
        ontologytestpassed.addOntologyPassedTestQuery(testNameUsuario, testName);
        ontologypassedquerys.add(ontologytestpassed);
    }
    
    public void addOntologyPassedTestSparql(String testNameUsuario, String testName){
        OntologyTestPassed ontologytestpassedsparql = new OntologyTestPassed();
        ontologytestpassedsparql.addOntologyPassedTestSparql(testNameUsuario, testName);
        ontologypassedsparql.add(ontologytestpassedsparql);
    }
    
    public ArrayList<OntologyTestFailure> getOntologyTestFailureQuery(){
        return this.ontologyfailuresquerys;
    }
   
    public ArrayList<OntologyTestFailure> getOntologyTestFailureSparql(){
        return this.ontologyfailuressparql;
    }
    
    public ArrayList<OntologyTestPassed> getOntologyTestPassedQuery(){
        return this.ontologypassedquerys;
    }
    
    public ArrayList<OntologyTestPassed> getOntologyTestPassedSparql(){
        return this.ontologypassedsparql;
    }
    
    public int ontologyFailuresCountQuery() {
    
         return ontologyfailuresquerys.size();
    }
    
     public int ontologyFailuresCountSparql() {
    
         return ontologyfailuressparql.size();
    }

    public boolean wasSuccessfulOntologyQuery() {
    
         return ontologyFailuresCountQuery() == 0;
    }
    
    public boolean wasSuccessfulOntologySparql() {
    
         return ontologyFailuresCountSparql() == 0;
    }
    
    public void createFileResults(ArrayList<OntologyTestFailure> ontologyfailures){
    
    }
        
}
    

