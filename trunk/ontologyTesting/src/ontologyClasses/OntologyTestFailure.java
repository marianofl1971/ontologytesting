/*
 * OntologyTestFailure.java
 * 
 * Created on 16-feb-2008, 14:44:38
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontologyClasses;

import ontologyModel.QueryOntology;

/**
 *
 * @author Saruskas
 */
public class OntologyTestFailure extends Object{

    protected String fresobtenido;
    protected String fquery;
    protected String fcomment;
    protected String fresultexpected;
    
    public OntologyTestFailure(QueryOntology query, String resObte){
        this.fquery = query.getQuery();
        this.fcomment = query.getComment();
        this.fresultexpected = query.getResultExpected();
        this.fresobtenido = resObte;
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
}
