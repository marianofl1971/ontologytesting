/*
 * OntologyTestResult.java
 * 
 * Created on 16-feb-2008, 14:43:59
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;

import java.util.ArrayList;
import model.QueryOntology;
import model.SparqlQueryOntology;

/**
 *
 * @author Saruskas
 */
public class OntologyTestResult extends Object{

    protected ArrayList<OntologyTestFailure> ontologyfailuresquerys;
    protected ArrayList<OntologyTestFailure> ontologyfailuressparql;
    
    public OntologyTestResult(){
        ontologyfailuresquerys = new ArrayList<OntologyTestFailure>();
        ontologyfailuressparql = new ArrayList<OntologyTestFailure>();
    }
    
    public void addOntologyFailureQuery(QueryOntology query, String resObte){
    
        OntologyTestFailure ontologytestfailure = new OntologyTestFailure();
        ontologytestfailure.addOntologyTestFailureQuery(query, resObte);
        ontologyfailuresquerys.add(ontologytestfailure);
    }
    
    public void addOntologyFailureSparql(SparqlQueryOntology sparqlquery, String resObte){
    
        OntologyTestFailure ontologytestfailure = new OntologyTestFailure();
        ontologytestfailure.addOntologyTestFailureSparql(sparqlquery, resObte);
        ontologyfailuressparql.add(ontologytestfailure);
    }
    
    public ArrayList<OntologyTestFailure> getOntologyTestFailureQuery(){
        return this.ontologyfailuresquerys;
    }
   
    public ArrayList<OntologyTestFailure> getOntologyTestFailureSparql(){
        return this.ontologyfailuressparql;
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
    

