/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.persistence;

import code.google.com.p.ontologytesting.guiNew.ListarTestsJPanel;
import code.google.com.p.ontologytesting.guiNew.MainApplicationJFrame;
import code.google.com.p.ontologytesting.model.CollectionTest;
import code.google.com.p.ontologytesting.model.Instancias;
import code.google.com.p.ontologytesting.model.ScenarioTest;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author saruskas
 */
public class SaveTest {
    private CollectionTest collection;
    private ListarTestsJPanel listInst;
    private XMLEncoder e;
    
    public boolean saveProject(boolean as) throws FileNotFoundException{
        collection = CollectionTest.getInstance();
        if(as==true){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int seleccion = fileChooser.showSaveDialog(MainApplicationJFrame.getInstance());
            if(seleccion == JFileChooser.APPROVE_OPTION){
                File fichero = fileChooser.getSelectedFile();
                e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fichero)));
                e.writeObject(collection);
                e.close();
                return true;
            }
        }else{
            e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("C:\\Documents and Settings\\sara_garcia\\Escritorio\\Mi Proyecto\\ProyectoPrueba")));
            //e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(MainApplicationJFrame.getInstance().getCarpetaProyecto()+"/"+MainApplicationJFrame.getInstance().getNombreProyecto())));
            e.writeObject(collection);
            e.close();
            return true;
        }
        return false;
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
    
    public void saveTestInMemory(ScenarioTest scenario){
        if(testYaGuardado(scenario)==false){
            CollectionTest.getInstance().getScenariotest().add(scenario);
        }
    }
    
    public boolean testYaGuardado(ScenarioTest scen){
        List<ScenarioTest> scenario = CollectionTest.getInstance().getScenariotest();
        for(int i=0; i<scenario.size(); i++){
            if(scen.getNombre().equals(scenario.get(i).getNombre())){
                return true;
            }
        }
        return false;
    }
    
    public boolean replaceScenarioLocally(ScenarioTest scen){
        List<ScenarioTest> scenario = CollectionTest.getInstance().getScenariotest();
        for(int i=0; i<scenario.size(); i++){
            if(scen.getNombre().equals(scenario.get(i).getNombre())){
                scenario.remove(i);
                scenario.add(scen);
                return true;
            }
        }
        return false;
    }
    
    public void actualizarListaDeTestsSimples(List<ScenarioTest> scenario){
        listInst = ListarTestsJPanel.getInstance();
        listInst.aniadirTestSimple(scenario);
    }
    
    public void actualizarListaDeTestsSparql(List<ScenarioTest> scenario){
        listInst = ListarTestsJPanel.getInstance();
        listInst.aniadirTestSparql(scenario);
    }
}
