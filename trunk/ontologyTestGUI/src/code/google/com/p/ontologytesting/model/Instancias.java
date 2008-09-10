/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sara.garcia
 */
public class Instancias {

    private String nombre="";
    private String descripcion="";
    private String type="Instancias";
    private List<ClassInstances> classInstances;
    private List<PropertyInstances> propertyInstances;
    
    public Instancias(){
        this.nombre="";
        this.descripcion="";
        this.type="Instancias";
        this.classInstances = new ArrayList<ClassInstances>();
        this.propertyInstances = new ArrayList<PropertyInstances>();
    }
    
    public Instancias(String nombre, String descripcion, String type,
            List<ClassInstances> clasInstances, List<PropertyInstances> propInstances){
        this.nombre=nombre;
        this.type=type;
        this.descripcion=descripcion;
        this.classInstances.addAll(clasInstances);
        this.propertyInstances.addAll(propInstances);
    }
    
    public List<ClassInstances> getClassInstances() {
        return classInstances;
    }

    public void setClassInstances(List<ClassInstances> classInstances) {
        this.classInstances = classInstances;
    }

    public List<PropertyInstances> getPropertyInstances() {
        return propertyInstances;
    }

    public void setPropertyInstances(List<PropertyInstances> propertyInstances) {
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
