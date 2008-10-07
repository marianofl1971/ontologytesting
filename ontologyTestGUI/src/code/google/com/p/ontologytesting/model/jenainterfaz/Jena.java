/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model.jenainterfaz;

import code.google.com.p.ontologytesting.model.ExecQuerySparql;
import java.util.ArrayList;

/**
 *
 * @author sara.garcia
 */
public interface Jena {
    
    boolean addInstanceClass(String ns,String nameClass, String value);
    
    boolean addInstanceProperty(String ns,String nameProperty, String value);
    
    void addReasoner(String ontologia) throws ExceptionReadOntology;
    
    void deleteEntries();
    
    String instantiation(String ns, String className, String individualName) throws ExceptionReadOntology;
    
    ArrayList<String> retieval(String ns, String className);

    String realization(String ns, String individualName); 
    
    String satisfactibility(String ns, String concepto, String clase) throws ExceptionReadOntology;

    ArrayList<String> classification(String ns, String individuo) throws ExceptionReadOntology;
    
    ArrayList<ExecQuerySparql> testSPARQL(String queryStr, boolean formatHTML);
    
    void validarSparqlQuery(String query) throws Exception;

}

