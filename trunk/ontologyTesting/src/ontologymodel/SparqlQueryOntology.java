/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontologymodel;

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
    }
    
    public String getSparqlQuery(){
        return query_sparql;
    }
    
    public void setSparqlQuery(String query_sparql){
        this.query_sparql=query_sparql;
    }
    
    public String getResultExpected(){
        return resultexpected;
    }
    
    public void setResultExpected(String resultexpected){
        this.resultexpected=resultexpected;
    }

    public String getComment(){
        return comment;
    }
    
    public void setComment(String comment){
        this.comment=comment;
    }    
}
