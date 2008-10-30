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

    private String resultexpected="";
    private String querySparql="";

    
    public SparqlQueryOntology(String querySparql, String resultexpected){
        this.querySparql=querySparql;
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

    public String getQuerySparql() {
        return querySparql;
    }

    public void setQuerySparql(String querySparql) {
        this.querySparql = querySparql;
    }
    
    @Override
    public boolean equals(Object object){
        if((object!=null) && (object instanceof SparqlQueryOntology) ) {
            SparqlQueryOntology comp = (SparqlQueryOntology)object;
            if(this.getQuerySparql().equals(comp.getQuerySparql())){
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
        hash = 11 * hash + (this.querySparql != null ? this.querySparql.hashCode() : 0);
        return hash;
    }

}
