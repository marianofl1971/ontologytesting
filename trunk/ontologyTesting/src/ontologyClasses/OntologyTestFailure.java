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

    protected String fresobtenido;
    protected String fquery;
    protected String fcomment;
    protected ArrayList<String> fsparql_resultexpected;
    protected String fresultexpected;
    protected String fsparql_query;
    
    public OntologyTestFailure(QueryOntology query, SparqlQueryOntology sparql_query, 
            String resObte){
        this.fquery = query.getQuery();
        this.fcomment = query.getComment();
        this.fresultexpected = query.getResultExpected();
        this.fresobtenido = resObte;
        this.fsparql_query = sparql_query.getSparqlQuery();
        this.fsparql_resultexpected.addAll(sparql_query.getResultExpected());
    }
    
    public String getResultObtenido() {    
         return this.fresobtenido;
         
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
