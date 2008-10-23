/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author saruskas
 */
public class ExecQuerySparql {
    private List<String> datos = new ArrayList<String>();
    private String nombreSelect="";

    public ExecQuerySparql(){
        datos = new ArrayList<String>();
        nombreSelect="";
    }
    
    public List<String> getDatos() {
        return datos;
    }

    public void setDatos(List<String> datos) {
        this.datos = datos;
    }

    public String getNombreSelect() {
        return nombreSelect;
    }

    public void setNombreSelect(String nombreSelect) {
        this.nombreSelect = nombreSelect;
    }
    
}
