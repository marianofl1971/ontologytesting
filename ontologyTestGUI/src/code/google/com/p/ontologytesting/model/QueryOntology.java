/*
 * QueryOntology.java
 * 
 * Created on 16-feb-2008, 13:55:21
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

import java.io.Serializable;

/**
 *
 * @author Saruskas
 */
public class QueryOntology extends SparqlQueryOntology implements Serializable {
    
    private String comment="";
    
    public QueryOntology(String query, String resultexpected,String comment){
        this.query=query;
        this.resultexpected=resultexpected;
        this.comment=comment;
    }
    
    public QueryOntology(QueryOntology queryOnto){
        this.query=queryOnto.getQuery();
        this.resultexpected=queryOnto.getResultexpected();
        this.comment=queryOnto.getComment();
    }

    public QueryOntology(){
        this("","","");
    }
    
    public QueryOntology(String query, String resultexpected){
        this.query=query;
        this.resultexpected=resultexpected;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    @Override
    public boolean equals(Object object){
        if((object!=null) && (object instanceof SparqlQueryOntology) ) {
            QueryOntology comp = (QueryOntology)object;
            if(this.getQuery().equals(comp.getQuery())){
               if(this.getResultexpected().equals(comp.getResultexpected())){
                    if(this.getComment().equals(comp.getComment())){
                        return true;
                    }
               }
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.query != null ? this.query.hashCode() : 0);
        hash = 29 * hash + (this.resultexpected != null ? this.resultexpected.hashCode() : 0);
        hash = 29 * hash + (this.comment != null ? this.comment.hashCode() : 0);
        return hash;
    }
}


