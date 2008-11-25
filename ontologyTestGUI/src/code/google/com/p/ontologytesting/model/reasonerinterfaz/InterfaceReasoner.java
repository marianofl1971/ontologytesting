/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model.reasonerinterfaz;

import code.google.com.p.ontologytesting.model.exectests.ExecQuerySparql;
import java.util.List;

/**
 *
 * @author sara.garcia
 */
public interface InterfaceReasoner {
    
    boolean addInstanceClass(String ns,String nameClass, String value);
    
    boolean addInstanceProperty(String ns,String nameProperty, String value);
    
    void addReasoner(String ontologia) throws ExceptionReadOntology;
    
    void deleteEntries();
    
    String instantiation(String ns, String className, String individualName) throws ExceptionReadOntology;
    
    List<String> retieval(String ns, String className);

    String realization(String ns, String individualName); 
    
    String satisfactibility(String ns, String concepto, String clase) throws ExceptionReadOntology;

    List<String> classification(String ns, String individuo) throws ExceptionReadOntology;
    
    List<ExecQuerySparql> testSPARQL(String queryStr, boolean formatHTML);
    
    boolean validarSparqlQuery(String query);

}

