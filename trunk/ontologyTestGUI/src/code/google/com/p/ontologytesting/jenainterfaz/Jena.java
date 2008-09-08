/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.jenainterfaz;

import code.google.com.p.ontologytesting.exceptions.*;
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
    
    String instantiation(String ns, String className, String individualName);
    
    ArrayList<String> retieval(String ns, String className);

    String realization(String ns, String individualName); 
    
    String satisfactibility(String ns, String concepto, String clase);

    ArrayList<String> classification(String ns, String individuo);
    
    ArrayList<ExecQuerySparql> testSPARQL(String queryStr, boolean formatHTML);
    
    void validarSparqlQuery(String query) throws Exception;

}

