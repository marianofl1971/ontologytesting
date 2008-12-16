/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

import java.util.StringTokenizer;


/**
 *
 * @author sara.garcia
 */
public class ValidarTests {
    
    public boolean validarQuery(String query){
        String regexp1 = "([\\w|-|_]+){1}";
        StringTokenizer stTexto = new StringTokenizer(query.trim());
        StringBuffer buf = new StringBuffer();
        while (stTexto.hasMoreElements()){
            buf.append(stTexto.nextElement());
        }
        if(!buf.toString().matches(regexp1)){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean validarResultado(String res){
        String regexp1 = "[\\w|,|.|\\s|-|_]+";
        StringTokenizer stTexto = new StringTokenizer(res.trim());
        StringBuffer buf = new StringBuffer();
        while (stTexto.hasMoreElements()){
            buf.append(stTexto.nextElement());
        }
        if(!buf.toString().matches(regexp1)){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean validarResultadoInstSatis(String res){
        if(!res.trim().equals("true") && !res.trim().equals("false")){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean validarQueryInstSatis(String query){
        String regexp1 = "([\\w|-|_]+[\\s|,|.]{1}[\\w|_|-]+){1}";
        String regexp2 = "([\\w|_|-]+[(]{1}[\\w|_|-]+[)]{1}){1}";
        
        StringTokenizer stTexto = new StringTokenizer(query.trim());
        StringBuffer buf = new StringBuffer();
        while (stTexto.hasMoreElements()){
            buf.append(stTexto.nextElement());
        }
        if(!buf.toString().matches(regexp2) && !buf.toString().matches(regexp1)){
            return false;
        }else{ 
            return true;
        }
    }
    
    public boolean validarInstanciaClase(String query){
        String regexp1 = "[\\w|_|-]+[,|.]{1}[\\w|_|-]+";
        String regexp2 = "([\\w|_|-]+[(]{1}[\\w|_|-]+[)]{1}){1}";
        
        StringTokenizer stTexto = new StringTokenizer(query.trim());
        StringBuffer buf = new StringBuffer();
        while (stTexto.hasMoreElements()){
            buf.append(stTexto.nextElement());
        }
        if(!buf.toString().matches(regexp2) && !buf.toString().matches(regexp1)){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean validarInstanciaPropiedad(String query){
        String regexp1 = "([\\w|_|-]+[(]{1}[\\w|_|-]+[,|.]{1}[\\w|_|-]+[)]{1}){1}";
        
        StringTokenizer stTexto = new StringTokenizer(query.trim());
        StringBuffer buf = new StringBuffer();
        while (stTexto.hasMoreElements()){
            buf.append(stTexto.nextElement());
        }
        if(!buf.toString().matches(regexp1)){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean validarSparqlResult(String query){
        String patron = ("[\\;|\\n|\\t|\\v|\\s|_|-]");
        String[] res = query.split(patron);
        for(int i=0;i<res.length;i++){
            if(validarQuerySparql(res[i])==false){
                return false;
            }
        }
        return true;    
    }
    
    public boolean validarQuerySparql(String query){
        String regexp1 = "[\\w|_|-]+[(]{1}([[\\w|_|-]+[,.]])+[)]{1}[;]?";
        
        if(!query.matches(regexp1)){
            return false;
        }else{
            return true;
        }   
    }
    
}
