/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

import java.util.ArrayList;

/**
 *
 * @author sara.garcia
 */
public class Instancias {

    private String nombre;
    private String descripcion;
    private String type="Instancias";
    private ArrayList<ClassInstances> classInstances = new ArrayList<ClassInstances>();
    private ArrayList<PropertyInstances> propertyInstances = new ArrayList<PropertyInstances>();
    
    public Instancias(){
        this.nombre="";
        this.descripcion="";
        this.type="";
        this.classInstances = new ArrayList<ClassInstances>();
        this.propertyInstances = new ArrayList<PropertyInstances>();
    }
    
    public Instancias(String nombre, String descripcion, String type,
            ArrayList<ClassInstances> clasInstances, ArrayList<PropertyInstances> propInstances){
        this.nombre=nombre;
        this.type=type;
        this.descripcion=descripcion;
        this.classInstances.addAll(clasInstances);
        this.propertyInstances.addAll(propInstances);
    }
    
    public ArrayList<ClassInstances> getClassInstances() {
        return classInstances;
    }

    public void setClassInstances(ArrayList<ClassInstances> classInstances) {
        this.classInstances = classInstances;
    }

    public ArrayList<PropertyInstances> getPropertyInstances() {
        return propertyInstances;
    }

    public void setPropertyInstances(ArrayList<PropertyInstances> propertyInstances) {
        this.propertyInstances = propertyInstances;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type=type;
    }
}
