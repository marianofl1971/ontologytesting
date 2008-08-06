/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

/**
 *
 * @author sara.garcia
 */
public class ValidarTests {

    public boolean validarTestInstanciacion(QueryOntology query){
    
        String regexp1 = "([a-zA-Z]+[\\s|,|.]{1}[a-zA-Z]+){1}";
        String regexp2 = "([a-zA-Z]+[(]{1}[a-zA-Z]+[)]{1}){1}";

        String consulta = query.getQuery();
        String resultado = query.getResultexpected();
        if(!consulta.matches(regexp1) && !consulta.matches(regexp2)){
            return false;
        }
        if(!resultado.equals("true") && !resultado.equals("false")){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean validarTestRetrieval(QueryOntology query){
        String regexp1 = "([a-zA-Z]+){1}";
        String regexp2 = "[a-zA-Z|,|.|\\s]+";
        
        String consulta = query.getQuery();
        String resultado = query.getResultexpected();
        if(!consulta.matches(regexp1)){
            return false;
        }
        if(!resultado.matches(regexp2)){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean validarTestRealizacion(QueryOntology query){
        String regexp1 = "([a-zA-Z]+){1}";

        String consulta = query.getQuery();
        String resultado = query.getResultexpected();
        if(!consulta.matches(regexp1)){
            return false;
        }
        if(!resultado.matches(regexp1)){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean validarTestSatisfactibilidad(QueryOntology query){
        String regexp1 = "([a-zA-Z]+[\\s|,|.]{1}[a-zA-Z]+){1}";
        String regexp2 = "([a-zA-Z]+[(]{1}[a-zA-Z]+[)]{1}){1}";

        String consulta = query.getQuery();
        String resultado = query.getResultexpected();
        if(!consulta.matches(regexp1) && !consulta.matches(regexp2)){
            return false;
        }
        if(!resultado.equals("true") && !resultado.equals("false")){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean validarTestClasificacion(QueryOntology query){
        String regexp1 = "([a-zA-Z]+){1}";
        String regexp2 = "[a-zA-Z|,|.|\\s]+";

        String consulta = query.getQuery();
        String resultado = query.getResultexpected();
        if(!consulta.matches(regexp1)){
            return false;
        }
        if(!resultado.matches(regexp2)){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean validarQuery(String query){
        String regexp1 = "([a-zA-Z]+[\\s|,|.]{1}[a-zA-Z]+){1}";
        String regexp2 = "([a-zA-Z]+[(]{1}[a-zA-Z]+[)]{1}){1}";
        
        if(!query.matches(regexp2) && !query.matches(regexp1)){
            return false;
        }else{
            return true;
        }
    }
}
