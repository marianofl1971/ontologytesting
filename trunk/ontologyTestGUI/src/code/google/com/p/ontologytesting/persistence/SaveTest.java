/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.persistence;

import code.google.com.p.ontologytesting.guiNew.ListarTestsJPanel;
import code.google.com.p.ontologytesting.model.CollectionTest;
import code.google.com.p.ontologytesting.model.Instancias;
import code.google.com.p.ontologytesting.model.ScenarioTest;
import java.awt.Component;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.JFileChooser;

/**
 *
 * @author saruskas
 */
public class SaveTest {
    private Component frame;
    private CollectionTest collection;
    private ListarTestsJPanel listInst;
    
    public void saveProject() throws FileNotFoundException{
        /*collection = CollectionTest.getInstance();
        XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new 
        FileOutputStream(MainApplication.getProyecto()+"/"+MainApplication.getNombreProyecto())));
        e.writeObject(collection);
        e.close();*/
    }
    
    public boolean saveProjectAs() throws FileNotFoundException{
        collection = CollectionTest.getInstance();
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showSaveDialog(frame);
        if(seleccion == JFileChooser.APPROVE_OPTION){
            File fichero = fileChooser.getSelectedFile();
            XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new 
            FileOutputStream(fichero)));
            e.writeObject(collection);
            e.close();
            return true;
        }else{
            return false;
        }
    }
    
    public boolean saveTestLocally(ScenarioTest scenarioTest){
        List<ScenarioTest> scenario = CollectionTest.getInstance().getScenariotest();
        if(scenario.add(scenarioTest)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean replaceTestLocally(ScenarioTest scenarioTest){
        List<ScenarioTest> scenario = CollectionTest.getInstance().getScenariotest();
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
        /*XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new 
        FileOutputStream(MainApplication.getProyecto()+"/"+scenarioTest.getNombre())));
        e.writeObject(scenarioTest);
        e.close();*/
    }
    
    public void saveInstanciasInMemory(Instancias instancias){
        if(instanciasYaGuardadas(instancias)==false){
            CollectionTest.getInstance().getInstancias().add(instancias);
        }
    }
    
    public boolean instanciasYaGuardadas(Instancias inst){
        List<Instancias> instancias = CollectionTest.getInstance().getInstancias();
        for(int i=0; i<instancias.size(); i++){
            if(inst.getNombre().equals(instancias.get(i).getNombre())){
                return true;
            }
        }
        return false;
    }
    
    public boolean replaceInstanciasLocally(Instancias inst){
        List<Instancias> instancias = CollectionTest.getInstance().getInstancias();
        for(int i=0; i<instancias.size(); i++){
            if(inst.getNombre().equals(instancias.get(i).getNombre())){
                instancias.remove(i);
                instancias.add(inst);
                return true;
            }
        }
        return false;
    }
    
    public void actualizarListaDeInstancias(){
        listInst = ListarTestsJPanel.getInstance();
        listInst.aniadirInstancias(CollectionTest.getInstance().getInstancias());
    }
}
