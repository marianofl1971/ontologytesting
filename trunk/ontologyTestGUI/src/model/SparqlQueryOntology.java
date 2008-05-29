/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author sara_garcia
 */
public class SparqlQueryOntology {

    private String resultexpected;
    private String query_sparql;
    private String comment;
    
    public SparqlQueryOntology(String query_sparql, String resultexpected,
            String comment){
        this.query_sparql=query_sparql;
        this.resultexpected = resultexpected;
        this.comment = comment;
    }
    
    public SparqlQueryOntology(){
        this.query_sparql=null;
        this.resultexpected = null;
        this.comment = null;
    }

    public String getResultexpected() {
        return resultexpected;
    }

    public void setResultexpected(String resultexpected) {
        this.resultexpected = resultexpected;
    }

    public String getQuery_sparql() {
        return query_sparql;
    }

    public void setQuery_sparql(String query_sparql) {
        this.query_sparql = query_sparql;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
}
