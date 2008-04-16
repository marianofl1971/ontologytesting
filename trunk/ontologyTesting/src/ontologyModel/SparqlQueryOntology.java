/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontologyModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sara_garcia
 */
public class SparqlQueryOntology {

    public ArrayList<String> resultexpected = new ArrayList<String>();
    public String query_sparql;
    
    public SparqlQueryOntology(String query_sparql, List<String> resultexpected){
        this.query_sparql=query_sparql;
        this.resultexpected.addAll(resultexpected);
    }
    
    public SparqlQueryOntology(){
    }
    
    public String getSparqlQuery(){
        return query_sparql;
    }
    
    public void setSparqlQuery(String query_sparql){
        this.query_sparql=query_sparql;
    }
    
    public List<String> getResultExpected(){
        return resultexpected;
    }
    
    public void setResultExpected(List<String> resultexpected){
        this.resultexpected.addAll(resultexpected);
    }
}
