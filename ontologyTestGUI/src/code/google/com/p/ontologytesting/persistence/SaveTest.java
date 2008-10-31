/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.persistence;

import code.google.com.p.ontologytesting.gui.auxiliarpanels.AbrirProyectoJDialog;
import code.google.com.p.ontologytesting.gui.menupanels.ListarTestsJPanel;
import code.google.com.p.ontologytesting.gui.MainApplicationJFrame;
import code.google.com.p.ontologytesting.gui.auxiliarclasess.FileChooserSelector;
import code.google.com.p.ontologytesting.model.*;
import code.google.com.p.ontologytesting.model.jenainterfaz.ExceptionReadOntology;
import code.google.com.p.ontologytesting.model.jenainterfaz.Jena;
import code.google.com.p.ontologytesting.model.jenainterfaz.JenaInterface;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.NoSuchElementException;
import javax.swing.WindowConstants;

    
/**
 *
 * @author saruskas
 */
public class SaveTest {
    private CollectionTest collection;
    private XMLDecoder decoder;
    private ListarTestsJPanel listInst;
    private XMLEncoder e;
    private FileChooserSelector utils;
    private JenaInterface jenaInterface;
    private Jena jena;
    
    public boolean saveProject(boolean as) throws FileNotFoundException{
        utils = new FileChooserSelector();
        if(as==true){
            utils.fileChooser(false, true);
            File fichero = utils.getFileSelected();
            e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fichero)));
            e.writeObject(CollectionTest.getInstance());
            e.close();
            return true;
        }else{
            //e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("C:\\Documents and Settings\\sara_garcia\\Escritorio\\Mi Proyecto\\ProyectoPrueba")));
            e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(MainApplicationJFrame.getInstance().getCarpetaProyecto()+"/"+MainApplicationJFrame.getInstance().getNombreProyecto())));
            e.writeObject(CollectionTest.getInstance());
            e.close();
            return true;
        }
    }
    
    public void prepareLoadProject(AbrirProyectoJDialog abrirP) throws FileNotFoundException,
        ClassCastException,NoSuchElementException{
        utils = new FileChooserSelector();
        utils.fileChooser(true, true);
        //try {
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(utils.getPathSelected())));
            collection = (CollectionTest) decoder.readObject();
            CollectionTest.getInstance().setInstancias(collection.getInstancias());
            CollectionTest.getInstance().setNamespace(collection.getNamespace());
            CollectionTest.getInstance().setOntology(collection.getOntology());
            CollectionTest.getInstance().setScenariotest(collection.getScenariotest());
            abrirP.setNamespaceText(CollectionTest.getInstance().getNamespace());
            abrirP.getUbicacionFisicaTextField().setText(CollectionTest.getInstance().getOntology());
            abrirP.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            abrirP.setLocationRelativeTo(MainApplicationJFrame.getInstance());
            abrirP.setVisible(true);
        /*} catch(FileNotFoundException ex) {
            panelAviso.errorAction("No se encontró el archivo especificado", MainApplicationJFrame.getInstance());
        }catch(ClassCastException ex){
            panelAviso.errorAction("Proyecto no válido", MainApplicationJFrame.getInstance());
        }catch(NoSuchElementException ex){
            panelAviso.errorAction("Proyecto no válido", MainApplicationJFrame.getInstance());
        }*/
    }  
    
    public void finishLoadProject(String ubicOnto,String namespaceOnto) throws ExceptionReadOntology{
        jenaInterface = new JenaInterface();                                              
        jena = jenaInterface.getJena();
        if(!namespaceOnto.endsWith("#")){
            namespaceOnto = namespaceOnto.concat("#");
        }
        CollectionTest.getInstance().setOntology(ubicOnto);
        CollectionTest.getInstance().setNamespace(namespaceOnto);
        //try{
            jena.addReasoner(ubicOnto);
            CollectionTest.getInstance().setNamespace(namespaceOnto);
            CollectionTest.getInstance().setOntology(ubicOnto);
            this.actualizarListaDeInstancias();
            this.actualizarListaDeTestsSimples(CollectionTest.getInstance().getScenariotest());
            this.actualizarListaDeTestsSparql(CollectionTest.getInstance().getScenariotest());
        /*}catch(ExceptionReadOntology ex){
            throw new ExceptionReadOntology("No se pudo crear el proyecto. La ontologia introducida no es valida.\n" +
                    "Introduzca una ontologia valida.");
        }*/
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
                instancias.add(i,inst);
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
                scenario.add(i,scen);
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
