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
    private List<ClassInstances> classInstances = new ArrayList<ClassInstances>();
    private List<PropertyInstances> propertyInstances = new ArrayList<PropertyInstances>();
    
    public Instancias(){
        this.nombre="";
        this.descripcion="";
        this.type="Instancias";
        this.classInstances = new ArrayList<ClassInstances>();
        this.propertyInstances = new ArrayList<PropertyInstances>();
    }
    
    public Instancias(Instancias instancias){
        this.nombre=instancias.getNombre();
        this.descripcion=instancias.getDescripcion();
        this.type=instancias.getType();
        this.classInstances = instancias.getClassInstances();
        this.propertyInstances = instancias.getPropertyInstances();
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
    
    @Override
    public boolean equals(Object object){
        if((object!=null) && (object instanceof Instancias) ) {
            Instancias comp = (Instancias)object;
            if(this.getClassInstances().size()==comp.getClassInstances().size()){
                for(int i=0;i<this.getClassInstances().size();i++){
                    if(!this.getClassInstances().get(i).equals(comp.getClassInstances().get(i))){
                        return false;
                    }
                }
            }else return false;
            if(this.getPropertyInstances().size()==comp.getPropertyInstances().size()){
                for(int i=0;i<this.getPropertyInstances().size();i++){
                    if(!this.getPropertyInstances().get(i).equals(comp.getPropertyInstances().get(i))){
                        return false;
                    }
                }
            }else return false;
            if(this.getNombre().equals(comp.getNombre())){
               if(this.getDescripcion().equals(comp.getDescripcion())){
                    if(this.getType().equals(comp.getType())){
                        return true;
                    }
               }
            }
            return false;
        }else return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        hash = 53 * hash + (this.descripcion != null ? this.descripcion.hashCode() : 0);
        hash = 53 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 53 * hash + (this.classInstances != null ? this.classInstances.hashCode() : 0);
        hash = 53 * hash + (this.propertyInstances != null ? this.propertyInstances.hashCode() : 0);
        return hash;
    }
    
    public boolean hayInstancias(){
        if(this.getClassInstances().size()>0 || this.getPropertyInstances().size()>0){
            return true;
        }
        return false;
    }
    
    public boolean esVacio(){
        if(this.getNombre().equals("") && this.getDescripcion().equals("") && 
                this.getClassInstances().size()==0 && this.getPropertyInstances().size()==0){
            return true;
        }else{
            return false;
        }
    }
    
}
