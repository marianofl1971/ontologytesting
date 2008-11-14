/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

import java.io.Serializable;

/**
 *
 * @author sara_garcia
 */
public class SparqlQueryOntology implements Serializable{

    protected String resultexpected="";
    protected String query="";

    
    public SparqlQueryOntology(String querySparql, String resultexpected){
        this.query=querySparql;
        this.resultexpected = resultexpected;
    }
    
    public SparqlQueryOntology(){
        this("","");
    }

    public String getResultexpected() {
        return resultexpected;
    }

    public void setResultexpected(String resultexpected) {
        this.resultexpected = resultexpected;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String querySparql) {
        this.query = querySparql;
    }
    
    @Override
    public boolean equals(Object object){
        if((object!=null) && (object instanceof SparqlQueryOntology) ) {
            SparqlQueryOntology comp = (SparqlQueryOntology)object;
            if(this.getQuery().equals(comp.getQuery())){
               if(this.getResultexpected().equals(comp.getResultexpected())){
                    return true;
               }
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + (this.resultexpected != null ? this.resultexpected.hashCode() : 0);
        hash = 11 * hash + (this.query != null ? this.query.hashCode() : 0);
        return hash;
    }

}
