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
import java.util.List;

/**
 *
 * @author Saruskas
 */
public class OntologyTestResult extends Object{

    protected List<OntologyTestFailure> ontologyfailuresquerys;
    protected List<OntologyTestFailure> ontologyfailuressparql;
    protected List<OntologyTestPassed> ontologypassedquerys;
    protected List<OntologyTestPassed> ontologypassedsparql;
    
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
            List<ExecQuerySparql> resEspe, List<ExecQuerySparql> resObte){
    
        OntologyTestFailure ontologytestfailure = new OntologyTestFailure();
        ontologytestfailure.addOntologyTestFailureSparql(testNameUsuario, testName,sparqlquery,resEspe,resObte);
        ontologyfailuressparql.add(ontologytestfailure);
    }
    
    public void addOntologyPassedQuery(String testNameUsuario, String testName,QueryOntology query, String resObte){
    
        OntologyTestPassed ontologytestpassed = new OntologyTestPassed();
        ontologytestpassed.addOntologyTestPassedQuery(testNameUsuario, testName,query, resObte);
        ontologypassedquerys.add(ontologytestpassed);
    }
    
    public void addOntologyPassedSparql(String testNameUsuario, String testName, SparqlQueryOntology sparqlquery, 
            List<ExecQuerySparql> resEspe, List<ExecQuerySparql> resObte){
    
        OntologyTestPassed ontologytestpassed = new OntologyTestPassed();
        ontologytestpassed.addOntologyTestPassedSparql(testNameUsuario, testName,sparqlquery,resEspe,resObte);
        ontologypassedsparql.add(ontologytestpassed);
    }
    
    public List<OntologyTestFailure> getOntologyTestFailureQuery(){
        return this.ontologyfailuresquerys;
    }
   
    public List<OntologyTestFailure> getOntologyTestFailureSparql(){
        return this.ontologyfailuressparql;
    }
    
    public List<OntologyTestPassed> getOntologyTestPassedQuery(){
        return this.ontologypassedquerys;
    }
    
    public List<OntologyTestPassed> getOntologyTestPassedSparql(){
        return this.ontologypassedsparql;
    }
    
}
    

