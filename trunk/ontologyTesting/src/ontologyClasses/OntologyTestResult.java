/*
 * OntologyTestResult.java
 * 
 * Created on 16-feb-2008, 14:43:59
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontologyClasses;

import java.util.ArrayList;
import ontologyModel.QueryOntology;
import ontologyModel.SparqlQueryOntology;

/**
 *
 * @author Saruskas
 */
public class OntologyTestResult extends Object{

    protected ArrayList<OntologyTestFailure> ontologyfailures;
    
    public OntologyTestResult(){
        ontologyfailures = new ArrayList<OntologyTestFailure>();
    }
    
    public void addOntologyFailure(QueryOntology query, SparqlQueryOntology 
            sparql_query,String resObte, ArrayList<String> resSparqlObte){
    
    OntologyTestFailure ontologytestfailure = new OntologyTestFailure(query, 
            sparql_query, resObte, resSparqlObte);
    ontologyfailures.add(ontologytestfailure);
    }
    
    public ArrayList<OntologyTestFailure> getOntologyTestFailure(){
        return this.ontologyfailures;
    }
    
    public int ontologyFailuresCount() {
    
         return ontologyfailures.size();
    }

    public boolean wasSuccessfulOntology() {
    
         return ontologyFailuresCount() == 0;
    }
    
    public void createFileResults(ArrayList<OntologyTestFailure> ontologyfailures){
    
    }
        
}
    

