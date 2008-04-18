/*
 * QueryOntology.java
 * 
 * Created on 16-feb-2008, 13:55:21
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontologyModel;

/**
 *
 * @author Saruskas
 */
public class QueryOntology {
    
    private String query;
    private String resultexpected;
    private String comment;

    
    public QueryOntology(String query, String resultexpected,String comment){
        this.query=query;
        this.resultexpected=resultexpected;
        this.comment=comment;
    }

    public QueryOntology(){
        this.query=null;
        this.resultexpected=null;
        this.comment=null;
    }
    
    public String getQuery(){
        return query;
    }
    
    public void setQuery(String query){
        this.query=query;
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


