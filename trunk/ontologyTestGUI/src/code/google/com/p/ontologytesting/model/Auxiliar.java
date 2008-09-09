/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

import code.google.com.p.ontologytesting.gui.AddSPARQLJPanel;
import code.google.com.p.ontologytesting.gui.Configuration;
import code.google.com.p.ontologytesting.gui.ContentMainJFrame;
import code.google.com.p.ontologytesting.gui.GroupTestsJPanel;
import java.awt.Component;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author sara.garcia
 */
public class Auxiliar {

    private boolean yaEligioGuardarInstancias=false;
    private static int contadorClas=0,contadorProp=0;
    private Component frame;
    
    public void crearArchivoDeInstancias(Instancias instancias){
    
        try{ 
            if(!instancias.getNombre().equals("")){
                XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new 
                FileOutputStream(Configuration.getPathInstancias()+"/"+instancias.getNombre()+".xml")));
                e.writeObject(instancias);
                e.close();
                JOptionPane.showMessageDialog(frame,"Las instancias han sido guardadas con el " +
                        "nombre que les asigno",
                        "Warning Message",JOptionPane.WARNING_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(frame,"Debe introducir un nombre para el " +
                        "conjunto de instancias.","Warning Message",JOptionPane.WARNING_MESSAGE);
            }
        }catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }    
    }
    
    public void crearArchivoDeInstancias(String nombreFichero, Instancias instancias){
        
    try{
        XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new 
                            FileOutputStream(nombreFichero)));
        e.writeObject(instancias);
        e.close();
    }catch (FileNotFoundException ex) {
        ex.printStackTrace();
     }
}
    
    public void setInstances(Instancias instancias){  
        
        int tab = GroupTestsJPanel.getSelectedTabed();
        if(AddSPARQLJPanel.isSeleccionado()==false){
            ContentMainJFrame.getInstancias().set(tab, instancias);
        }else{
            ContentMainJFrame.getInstancias().set(5, instancias);
        }
    }
    
    public void setYaEligioGuardarInstancias(boolean selec) {
        yaEligioGuardarInstancias = selec;
    }
    
    public boolean getYaEligioGuardarInstancias() {
        return yaEligioGuardarInstancias;
    }
    
    public static int getContadorClas() {
        return contadorClas;
    }
    
    public static void setContadorClas(int aContador) {
        contadorClas = aContador;
    }

    public static int getContadorProp() {
        return contadorProp;
    }

    public static void setContadorProp(int aContadorProp) {
        contadorProp = aContadorProp;
    }
    
}
