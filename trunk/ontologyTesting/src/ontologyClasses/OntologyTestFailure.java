/*
 * OntologyTestFailure.java
 * 
 * Created on 16-feb-2008, 14:44:38
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontologyClasses;

import java.util.ArrayList;
import java.util.List;
import ontologyModel.QueryOntology;
import ontologyModel.SparqlQueryOntology;

/**
 *
 * @author Saruskas
 */
public class OntologyTestFailure extends Object{

    protected String fresqueryobtenido;
    protected ArrayList<String> fressparqlobtenido = new ArrayList<String>();
    protected String fquery;
    protected String fcomment;
    protected ArrayList<String> fsparql_resultexpected = new ArrayList<String>();
    protected String fresultexpected;
    protected String fsparql_query;
    
    public OntologyTestFailure(QueryOntology query, SparqlQueryOntology sparql_query, 
            String resQueryObte, ArrayList<String> resSparqlObte){
        this.fquery = query.getQuery();
        this.fcomment = query.getComment();
        this.fresultexpected = query.getResultExpected();
        this.fresqueryobtenido = resQueryObte;
        this.fsparql_resultexpected.addAll(sparql_query.getResultExpected());
        this.fsparql_query = sparql_query.getSparqlQuery();
        this.fressparqlobtenido.addAll(resSparqlObte);
    }
    
    public String getResultQueryObtenido() {    
         return this.fresqueryobtenido;    
     }  
    
     public List<String> getResultSparqlObtenido() {    
         return this.fressparqlobtenido;    
     }
    
    public String getfComment(){
        return this.fcomment;
    }
    
    public String getfQuery(){
        return this.fquery;
    }
    
    public String getfResultExpected(){
        return this.fresultexpected;
    }
    
    public String getfSparqlQuery(){
        return this.fsparql_query;
    }
    
    public List<String> getfSparqlResultExpected(){
        return this.fsparql_resultexpected;
    }
}
