/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

import code.google.com.p.ontologytesting.gui.AddSPARQLJPanel;
import code.google.com.p.ontologytesting.gui.Configuration;
import code.google.com.p.ontologytesting.gui.ContentMainJFrame;
import code.google.com.p.ontologytesting.gui.GroupTestsJPanel;
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
    
    public void crearArchivoDeInstancias(Instancias instancias){
    
        String nombreArch=null;
        String nameInstances=null;

        nombreArch = JOptionPane.showInputDialog(null,"Introduzca el nombre para el " +
                    "archivo con este conjunto de instancias","Nombre del archivo",
                    JOptionPane.QUESTION_MESSAGE);

        if(nombreArch.endsWith(".xml")){
            nameInstances=nombreArch;
        }else{
            nameInstances=nombreArch.concat(".xml");
        }

        try{
            XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new 
                                FileOutputStream(Configuration.getPathInstancias()+"/"+nameInstances)));
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
    
    
}
