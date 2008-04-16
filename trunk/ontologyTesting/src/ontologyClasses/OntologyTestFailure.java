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
import ontologyModel.QueryOntology;

/**
 *
 * @author Saruskas
 */
public class OntologyTestFailure extends Object{

    protected String fresqueryobtenido;
    protected String fquery;
    protected String fcomment;
    protected String fresultexpected;
    
    public OntologyTestFailure(QueryOntology query, String resQueryObte){
        this.fquery = query.getQuery();
        this.fcomment = query.getComment();
        this.fresultexpected = query.getResultExpected();
        this.fresqueryobtenido = resQueryObte;
    }
    
    public String getResultQueryObtenido() {    
         return this.fresqueryobtenido;    
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
}
