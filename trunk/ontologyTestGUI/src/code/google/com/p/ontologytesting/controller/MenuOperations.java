/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.guiNew;

import code.google.com.p.ontologytesting.model.Instancias;
import code.google.com.p.ontologytesting.model.ScenarioTest;
import code.google.com.p.ontologytesting.persistence.SaveTest;
import java.awt.Component;
import java.awt.Frame;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author saruskas
 */
public class MenuOperations {

    private JFileChooser filechooser;
    private Component frame;
    private XMLDecoder decoder;
    private SaveTest saveTest;

    public boolean importarTest() throws FileNotFoundException{
        filechooser = new JFileChooser(MainApplication.getProyecto());
        saveTest = new SaveTest();
        int option = filechooser.showOpenDialog(frame);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = filechooser.getSelectedFile();
            String nameFile = selectedFile.getPath();
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(nameFile)));
            ScenarioTest s = (ScenarioTest) decoder.readObject();
            if(saveTest.saveTestLocally(s)){
               return true;
            }
        }   
        return false;
    }
    
    public boolean importarInstancias(ScenarioTest s) throws FileNotFoundException{
        filechooser = new JFileChooser(MainApplication.getProyecto());
        saveTest = new SaveTest();
        int option = filechooser.showOpenDialog(frame);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = filechooser.getSelectedFile();
            String nameFile = selectedFile.getPath();
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(nameFile)));
            Instancias instancias = (Instancias) decoder.readObject();
            s.setInstancias(instancias);
            saveTest.replaceTestLocally(s);
            return true;
        }else return false;   
    }
    
   
    
    public void editarTestDeRecuperacion(ScenarioTest s){
    
    }
    
    public void editarTestDeRealizacion(ScenarioTest s){
    
    }
    
    public void editarTestDeSatisfactibilidad(ScenarioTest s){
    
    }
    
    public void editarTestDeClasificacion(ScenarioTest s){
    
    }
    
    
    public void editarTestSparql(){
    
    }
    
    public void editarInstancias(){
    
    }
}
