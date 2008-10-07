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
    
    public boolean validarQuery(String query){
        String regexp1 = "([\\w]+){1}";
        
        if(!query.trim().matches(regexp1)){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean validarResultado(String res){
        String regexp1 = "[\\w|,|.|\\s]+";
        
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
        String regexp1 = "([\\w]+[\\s|,|.]{1}[\\w]+){1}";
        String regexp2 = "([\\w]+[(]{1}[\\w]+[)]{1}){1}";
        
        if(!query.matches(regexp2) && !query.matches(regexp1)){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean validarInstanciaClase(String query){
        String regexp1 = "[\\w]+[,|.]{1}[\\w]+";
        String regexp2 = "([\\w]+[(]{1}[\\w]+[)]{1}){1}";
        
            if(!query.trim().matches(regexp2) && !query.trim().matches(regexp1)){
                return false;
            }else{
                return true;
            }
    }
    
    public boolean validarInstanciaPropiedad(String query){
        String regexp1 = "([\\w]+[(]{1}[\\w]+[,|.]{1}[\\w]+[)]{1}){1}";
        
        if(!query.trim().matches(regexp1)){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean validarSparqlResult(String query){
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
        String regexp1 = "[\\w]+[(]{1}([[\\w]+[,.]])+[)]{1}[;]?";
        
        if(!query.matches(regexp1)){
            return false;
        }else{
            return true;
        }   
    }
    
}
