/*
 * OntologyTestResult.java
 * 
 * Created on 16-feb-2008, 14:43:59
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model.exectests;

import code.google.com.p.ontologytesting.model.*;
import code.google.com.p.ontologytesting.model.ScenarioTest.TipoTest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Saruskas
 */
public class OntologyTestResult extends Object{

    protected List<OntologyTestFailure> ontologyfailuresquerys = new ArrayList<OntologyTestFailure>();
    protected List<OntologyTestFailure> ontologyfailuressparql = new ArrayList<OntologyTestFailure>();
    protected List<OntologyTestPassed> ontologypassedquerys = new ArrayList<OntologyTestPassed>();
    protected List<OntologyTestPassed> ontologypassedsparql = new ArrayList<OntologyTestPassed>();
    
    public OntologyTestResult(){
        ontologyfailuresquerys = new ArrayList<OntologyTestFailure>();
        ontologyfailuressparql = new ArrayList<OntologyTestFailure>();
        ontologypassedquerys = new ArrayList<OntologyTestPassed>();
        ontologypassedsparql = new ArrayList<OntologyTestPassed>();
    }
    
    public void addOntologyFailureQuery(String testNameUsuario, QueryOntology query, String resObte,TipoTest tipoTest){
    
        OntologyTestFailure ontologytestfailure = new OntologyTestFailure();
        ontologytestfailure.addOntologyTestFailureQuery(testNameUsuario, query, resObte,tipoTest);
        ontologyfailuresquerys.add(ontologytestfailure);
    }
    
    public void addOntologyFailureSparql(String testNameUsuario, SparqlQueryOntology sparqlquery, 
            List<ExecQuerySparql> resEspe, List<ExecQuerySparql> resObte, TipoTest tipoTest){
    
        OntologyTestFailure ontologytestfailure = new OntologyTestFailure();
        ontologytestfailure.addOntologyTestFailureSparql(testNameUsuario, sparqlquery,resEspe,resObte,tipoTest);
        ontologyfailuressparql.add(ontologytestfailure);
    }
    
    public void addOntologyPassedQuery(String testNameUsuario, QueryOntology query, String resObte, TipoTest tipoTest){
    
        OntologyTestPassed ontologytestpassed = new OntologyTestPassed();
        ontologytestpassed.addOntologyTestPassedQuery(testNameUsuario, query, resObte, tipoTest);
        ontologypassedquerys.add(ontologytestpassed);
    }
    
    public void addOntologyPassedSparql(String testNameUsuario, SparqlQueryOntology sparqlquery, 
            List<ExecQuerySparql> resEspe, List<ExecQuerySparql> resObte, TipoTest tipoTest){
    
        OntologyTestPassed ontologytestpassed = new OntologyTestPassed();
        ontologytestpassed.addOntologyTestPassedSparql(testNameUsuario, sparqlquery,resEspe,resObte, tipoTest);
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
    

