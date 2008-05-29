/*
 * OntologyTestFailure.java
 * 
 * Created on 16-feb-2008, 14:44:38
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;

import model.QueryOntology;
import model.SparqlQueryOntology;

/**
 *
 * @author Saruskas
 */
public class OntologyTestFailure extends Object{

    protected String fresqueryobtenido;
    protected String fquery;
    protected String fcommentquery;
    protected String fresultexpected;
    protected String fresultsparqlexpected;
    protected String fressparqlobtenido;
    protected String fcommentsparqlquery;
    protected String fquerysparql;
    
    public OntologyTestFailure(QueryOntology query, String resQueryObte, 
            SparqlQueryOntology querysparql, String resSparqlQueryObte){
        this.fquery = query.getQuery();
        this.fcommentquery = query.getComment();
        this.fresultexpected = query.getResultexpected();
        this.fresqueryobtenido = resQueryObte;
        this.fquerysparql = querysparql.getQuery_sparql();
        this.fressparqlobtenido = querysparql.getComment();
        this.fresultsparqlexpected = querysparql.getResultexpected();
        this.fressparqlobtenido = resSparqlQueryObte;
    }
    
    public OntologyTestFailure(){
    }   
    
    void addOntologyTestFailureQuery(QueryOntology query, String resQueryObte){
        this.fquery = query.getQuery();
        this.fcommentquery = query.getComment();
        this.fresultexpected = query.getResultexpected();
        this.fresqueryobtenido = resQueryObte;
    }
    
    void addOntologyTestFailureSparql(SparqlQueryOntology querysparql, String resQueryObte){
        this.fquerysparql = querysparql.getQuery_sparql();
        this.fressparqlobtenido = querysparql.getComment();
        this.fresultsparqlexpected = querysparql.getResultexpected();
        this.fressparqlobtenido = resQueryObte;
    }
    
    public String getResultQueryObtenido() {    
         return this.fresqueryobtenido;    
     }  
    
    public String getResultSparqlQueryObtenido() {    
         return this.fressparqlobtenido;    
     }
    
    public String getQueryfComment(){
        return this.fcommentquery;
    }
    
    public String getSparqlfComment(){
        return this.fcommentsparqlquery;
    }
    
    public String getfQuery(){
        return this.fquery;
    }
    
    public String getfSparqlQuery(){
        return this.fquerysparql;
    }
    
    public String getfResultExpected(){
        return this.fresultexpected;
    }
    
    public String getfResultSparqlExpected(){
        return this.fresultsparqlexpected;
    }
    
}
