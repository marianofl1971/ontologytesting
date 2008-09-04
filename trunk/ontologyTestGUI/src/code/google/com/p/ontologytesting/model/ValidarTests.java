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
        if(!consulta.trim().matches(regexp1)){
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
        if(!consulta.trim().matches(regexp1)){
            return false;
        }
        if(!resultado.trim().matches(regexp1)){
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
        if(!consulta.trim().matches(regexp1)){
            return false;
        }
        if(!resultado.matches(regexp2)){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean validarQuery(String query){
        String regexp1 = "([a-zA-Z]+){1}";
        
        if(!query.trim().matches(regexp1)){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean validarResultado(String res){
        String regexp1 = "[a-zA-Z|,|.|\\s]+";
        
        if(!res.matches(regexp1)){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean validarResultadoInstSatis(String res){
        
        if(!res.equals("true") && !res.equals("false")){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean validarQueryInstSatis(String query){
        String regexp1 = "([a-zA-Z]+[\\s|,|.]{1}[a-zA-Z]+){1}";
        String regexp2 = "([a-zA-Z]+[(]{1}[a-zA-Z]+[)]{1}){1}";
        
        if(!query.matches(regexp2) && !query.matches(regexp1)){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean validarInstanciaClase(String query){
        String regexp1 = "([a-zA-Z]+[,|.]{1}[a-zA-Z]+){1}";
        String regexp2 = "([a-zA-Z]+[(]{1}[a-zA-Z]+[)]{1}){1}";
        
        if(!query.matches(regexp2) && !query.matches(regexp1)){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean validarInstanciaPropiedad(String query){
        String regexp1 = "([a-zA-Z]+[(]{1}[a-zA-Z]+[,|.]{1}[a-zA-Z]+[)]{1}){1}";
        
        if(!query.matches(regexp1)){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean validarSparqlTest(String query){
        String patron = ("[\\;|\\n|\\t|\\v|\\s]");
        
        String[] res = query.split(patron);
        for(int i=0;i<res.length;i++){
            if(validarQuerySparql(res[i])==false){
                return false;
            }
        }
        return true;    
    }
    
    public boolean validarQuerySparql(String query){
        String regexp1 = "[a-zA-Z]+[(]{1}([[a-zA-Z]+[,.]])+[)]{1}[;]?";
        
        if(!query.matches(regexp1)){
            return false;
        }else{
            return true;
        }   
    }
    
}
