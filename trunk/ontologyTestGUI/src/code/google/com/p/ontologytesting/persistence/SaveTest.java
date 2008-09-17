/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.persistence;

import code.google.com.p.ontologytesting.guiNew.MainApplication;
import code.google.com.p.ontologytesting.model.Instancias;
import code.google.com.p.ontologytesting.model.ScenarioTest;
import java.awt.Component;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author saruskas
 */
public class SaveTest {
    private Component frame;
    
    public boolean saveTestLocally(ScenarioTest scenarioTest){
        ArrayList<ScenarioTest> scenario = MainApplication.getCollection().getScenariotest();
        if(scenario.add(scenarioTest)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean replaceTestLocally(ScenarioTest scenarioTest){
        ArrayList<ScenarioTest> scenario = MainApplication.getCollection().getScenariotest();
        for(int i=0; i<scenario.size(); i++){
            if(scenarioTest.getNombre().equals(scenario.get(i).getNombre())){
                scenario.remove(i);
                scenario.add(scenarioTest);
                return true;
            }
        }
        return false;
    }
    
    public void saveTestInMemory(ScenarioTest scenarioTest) throws FileNotFoundException{
        XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new 
        FileOutputStream(MainApplication.getProyecto()+"/"+scenarioTest.getNombre())));
        e.writeObject(scenarioTest);
        e.close();
    }
    
    public void saveInstanciasInMemory(Instancias instancias){
        try{ 
            if(!instancias.getNombre().equals("")){
                XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new 
                FileOutputStream(MainApplication.getProyecto()+"/"+instancias.getNombre())));
                e.writeObject(instancias);
                e.close();
                JOptionPane.showMessageDialog(frame,"Instancias guardadas con el " +
                        "nombre que les asigno y asociadas al test",
                        "Information Message",JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(frame,"Debe introducir un nombre para el " +
                        "conjunto de instancias.","Warning Message",JOptionPane.WARNING_MESSAGE);
            }
        }catch (FileNotFoundException ex) {
           JOptionPane.showMessageDialog(frame,"No se ha podido guardar el archivo","Error Message",JOptionPane.ERROR_MESSAGE);
        } 
    }

}
