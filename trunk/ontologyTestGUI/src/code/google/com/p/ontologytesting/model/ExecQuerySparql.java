/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

import java.util.ArrayList;

/**
 *
 * @author saruskas
 */
public class ExecQuerySparql {
    private ArrayList<String> datos = new ArrayList<String>();
    private String nombre;

    public ExecQuerySparql(){}
    
    public ExecQuerySparql(ArrayList<String> datos, String nombre){
        this.datos = datos;
        this.nombre=nombre;
    }
    
    public ExecQuerySparql(String nombre){
        this.nombre=nombre;
    }

    public ArrayList<String> getDatos() {
        return datos;
    }

    public void setDatos(ArrayList<String> datos) {
        this.datos = datos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
