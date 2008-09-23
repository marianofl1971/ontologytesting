/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.controller;

import code.google.com.p.ontologytesting.guiNew.*;
import code.google.com.p.ontologytesting.model.*;
import code.google.com.p.ontologytesting.persistence.SaveTest;
import java.awt.BorderLayout;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author saruskas
 */
public class MenuOperations {
    
    private SeeTestJDialog seeTest;
    private ScenarioTest scenario;
    private MenuOperations menuOp;
    private AddInstancesClasPropJDialog addInst;
    private JFileChooser filechooser;
    private Auxiliar auxiliar;
    private XMLDecoder decoder;
    private SaveTest saveTest;
    private Instancias instancias;
    private JFrame frame;

    public boolean importarTest() throws FileNotFoundException,ClassCastException{
        filechooser = new JFileChooser(MainApplication.getProyecto());
        saveTest = new SaveTest();
        int option = filechooser.showOpenDialog(MainApplication.getContentTestsJPanel());
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
    
    public void explorarTests() throws FileNotFoundException,ClassCastException{
        frame = new JFrame();
        filechooser = new JFileChooser(MainApplication.getProyecto());
        int option = filechooser.showOpenDialog(MainApplication.getContentTestsJPanel());                                                  
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = filechooser.getSelectedFile();
            String nameFile = selectedFile.getPath();
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(nameFile)));
            ScenarioTest s = (ScenarioTest) decoder.readObject();
            seeTest = new SeeTestJDialog(frame, false, s);
            seeTest.setVisible(true);
        }
    }
    
    public void explorarInstancias() throws FileNotFoundException,ClassCastException{
        filechooser = new JFileChooser(MainApplication.getProyecto());                                               
        frame = new JFrame();
        int option = filechooser.showOpenDialog(MainApplication.getContentTestsJPanel());
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = filechooser.getSelectedFile();
            String nameFile = selectedFile.getPath();
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(nameFile)));
            Instancias inst = (Instancias) decoder.readObject();
            seeTest = new SeeTestJDialog(frame, false, inst);
            seeTest.setVisible(true);
        }
    }
    
    public boolean verTests(){
        frame = new JFrame();
        int sel = ControladorTests.testSeleccionado();
        if(sel!=10){
            if(sel==0 || sel==3){
                scenario = Auxiliar.getTestSimpleInstSat().getScenario();
            }else if(sel==1 || sel==4){
                scenario = Auxiliar.getTestSimpleRetClas().getScenario();
            }else if(sel==2){
                scenario = Auxiliar.getTestSimpleReal().getScenario();
            }else if(sel==5){
                scenario = Auxiliar.getTestSparql().getScenario();
            } 
            seeTest = new SeeTestJDialog(frame, false, scenario);
            seeTest.setVisible(true);
            return true;
        }else{
            return false;
        }
    }
    
    public boolean verInstancias(){
        frame = new JFrame();
        int sel = ControladorTests.testSeleccionado();
        if(sel!=10){
            if(sel==0 || sel==3){
                instancias = Auxiliar.getTestSimpleInstSat().getScenario().getInstancias();
            }else if(sel==1 || sel==4){
                instancias = Auxiliar.getTestSimpleRetClas().getScenario().getInstancias();
            }else if(sel==2){
                instancias = Auxiliar.getTestSimpleReal().getScenario().getInstancias();
            }else if(sel==5){
                instancias = Auxiliar.getTestSparql().getScenario().getInstancias();
            }
            seeTest = new SeeTestJDialog(frame, false, instancias);
            seeTest.setVisible(true);
            return true;
        }else{
            return false;
        }
    }
    
    public boolean openProject() throws FileNotFoundException,ClassCastException{
        filechooser = new JFileChooser();
        int option = filechooser.showOpenDialog(MainApplication.getContentTestsJPanel());
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = filechooser.getSelectedFile();
            String nameFile = selectedFile.getPath();
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(nameFile)));
            CollectionTest collection = (CollectionTest) decoder.readObject();
            MainApplication.setCollection(collection);
            return true;
        }else{
            return false;
        }
    }
    
    public boolean importarInstancias(ScenarioTest s) throws FileNotFoundException,ClassCastException{
        filechooser = new JFileChooser(MainApplication.getProyecto());
        saveTest = new SaveTest();
        int option = filechooser.showOpenDialog(MainApplication.getContentTestsJPanel());
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = filechooser.getSelectedFile();
            String nameFile = selectedFile.getPath();
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(nameFile)));
            instancias = (Instancias) decoder.readObject();
            s.setInstancias(instancias);
            saveTest.replaceTestLocally(s);
            return true;
        }else return false;   
    }
    
    public boolean editarInstanciasAsociadasTest(){
        frame = new JFrame();
        int sel = ControladorTests.testSeleccionado();
        if(sel==0 || sel==3){
            instancias = Auxiliar.getTestSimpleInstSat().getScenario().getInstancias();
        }else if(sel==1 || sel==4){
            instancias = Auxiliar.getTestSimpleRetClas().getScenario().getInstancias();
        }else if(sel==2){
            instancias = Auxiliar.getTestSimpleReal().getScenario().getInstancias();
        }else if(sel==5){
            instancias = Auxiliar.getTestSparql().getScenario().getInstancias();
        }
        if(instancias.getPropertyInstances().size()==0 &&  instancias.getClassInstances().size()==0){
            return false;
        }else{
            addInst = new AddInstancesClasPropJDialog(frame,true,instancias,sel);
            addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            addInst.setVisible(true);
            return true;
        }
    }
    
    public void editarTests() throws FileNotFoundException,ClassCastException{
        auxiliar = new Auxiliar();
        ControladorTests.inicializarGuardados();
        ControladorTests.inicializarSeleccionados();
        filechooser = new JFileChooser(MainApplication.getProyecto());
        int option = filechooser.showOpenDialog(MainApplication.getContentTestsJPanel());
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = filechooser.getSelectedFile();
            String nameFile = selectedFile.getPath();
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(nameFile)));
            ScenarioTest s = (ScenarioTest) decoder.readObject();
            String tipoTest = s.getTestName();
            auxiliar.editarTest(s, tipoTest);

        }   
    }

    public void editarInstancias(){
        frame = new JFrame();
        int cont = MainApplication.getContentTestsJPanel().getComponentCount();
        if(cont==0){
            auxiliar = new Auxiliar();
            filechooser = new JFileChooser(MainApplication.getProyecto());
            int option = filechooser.showOpenDialog(MainApplication.getContentTestsJPanel());
            if (option == JFileChooser.APPROVE_OPTION) {
                File selectedFile = filechooser.getSelectedFile();
                String nameFile = selectedFile.getPath();
                try {
                    decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(nameFile)));
                    Instancias inst = (Instancias) decoder.readObject();
                    addInst = new AddInstancesClasPropJDialog(frame, true, inst);
                    addInst.setVisible(true);
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(MainApplication.getContentTestsJPanel(),"No se pudo abrir el archivo",                                                  
                    "Error Message",JOptionPane.ERROR_MESSAGE);
                } catch(ClassCastException ce){
                    JOptionPane.showMessageDialog(MainApplication.getContentTestsJPanel(),"El archivo no es compatible con la accion que " +
                    "desea realizar","Error Message",JOptionPane.ERROR_MESSAGE);
                } 
            }   
        }else{
            menuOp = new MenuOperations();
            boolean edit = menuOp.editarInstanciasAsociadasTest();
            if(edit==false){
                JOptionPane.showMessageDialog(MainApplication.getContentTestsJPanel(),"Este test no tiene instancias asociadas",
                "Information Message",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void importarInstancias(){
        int cont = MainApplication.getContentTestsJPanel().getComponentCount();
        if(cont==0){
            JOptionPane.showMessageDialog(MainApplication.getContentTestsJPanel(),"No hay ningun test abierto al que " +
                    "importar las instancias","Warning Message",JOptionPane.INFORMATION_MESSAGE);
        }else{
            int sel = ControladorTests.testSeleccionado();
            if(sel==0 || sel==3){
                scenario = Auxiliar.getTestSimpleInstSat().getScenario();
            }else if(sel==1 || sel==4){
                scenario = Auxiliar.getTestSimpleRetClas().getScenario();
            }else if(sel==2){
                scenario = Auxiliar.getTestSimpleReal().getScenario();
            }else if(sel==5){
                scenario = Auxiliar.getTestSparql().getScenario();
            } 
            menuOp = new MenuOperations();
            try{
                if(menuOp.importarInstancias(scenario)){
                    JOptionPane.showMessageDialog(MainApplication.getContentTestsJPanel(),"Las instancias se ha importado correctamente",
                    "Information Message",JOptionPane.INFORMATION_MESSAGE);
                }
            }catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(MainApplication.getContentTestsJPanel(),"No se ha importado el test",                                                  
                "Error Message",JOptionPane.ERROR_MESSAGE);
            }catch(ClassCastException ce){
                JOptionPane.showMessageDialog(MainApplication.getContentTestsJPanel(),"El archivo no es compatible con la accion que " +
                "desea realizar","Error Message",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void aniadirTestsInst(){ 
        int cont = MainApplication.getContentTestsJPanel().getComponentCount();
        if(cont==0){
            MainApplication.getContentTestsJPanel().add(new TestSimpleInstSat(0),BorderLayout.NORTH);
        }else{
            MainApplication.getContentTestsJPanel().remove(0);
            MainApplication.getContentTestsJPanel().add(new TestSimpleInstSat(0),BorderLayout.NORTH);
        }
        MainApplication.getContentTestsJPanel().getParent().validate();
    }
    
    public void aniadirTestsSat(){ 
    int cont = MainApplication.getContentTestsJPanel().getComponentCount();
    if(cont==0){
        MainApplication.getContentTestsJPanel().add(new TestSimpleInstSat(3),BorderLayout.NORTH);
    }else{
        MainApplication.getContentTestsJPanel().remove(0);
        MainApplication.getContentTestsJPanel().add(new TestSimpleInstSat(3),BorderLayout.NORTH);
    }
    MainApplication.getContentTestsJPanel().getParent().validate();
}

    public void aniadirTestsRet(){ 
        int cont = MainApplication.getContentTestsJPanel().getComponentCount();
        if(cont==0){
            MainApplication.getContentTestsJPanel().add(new TestSimpleRetClas(1),BorderLayout.CENTER);
        }else{
            MainApplication.getContentTestsJPanel().remove(0);
            MainApplication.getContentTestsJPanel().add(new TestSimpleRetClas(1),BorderLayout.CENTER);
        }
        MainApplication.getContentTestsJPanel().getParent().validate();
    }

    public void aniadirTestsClas(){ 
        int cont = MainApplication.getContentTestsJPanel().getComponentCount();
        if(cont==0){
            MainApplication.getContentTestsJPanel().add(new TestSimpleRetClas(4),BorderLayout.NORTH);
        }else{
            MainApplication.getContentTestsJPanel().remove(0);
            MainApplication.getContentTestsJPanel().add(new TestSimpleRetClas(4),BorderLayout.NORTH);
        }
        MainApplication.getContentTestsJPanel().getParent().validate();
    }

    public void aniadirTestsSparql(){ 
        int cont = MainApplication.getContentTestsJPanel().getComponentCount();
        if(cont==0){
            MainApplication.getContentTestsJPanel().add(new AddSPARQLJPanel(5),BorderLayout.NORTH);
        }else{
            MainApplication.getContentTestsJPanel().remove(0);
            MainApplication.getContentTestsJPanel().add(new AddSPARQLJPanel(5),BorderLayout.NORTH);
        }
        MainApplication.getContentTestsJPanel().getParent().validate();
    }

    public void aniadirTestsReal(){ 
        int cont = MainApplication.getContentTestsJPanel().getComponentCount();
        if(cont==0){
            MainApplication.getContentTestsJPanel().add(new TestSimpleReal(2),BorderLayout.NORTH);
        }else{
            MainApplication.getContentTestsJPanel().remove(0);
            MainApplication.getContentTestsJPanel().add(new TestSimpleReal(2),BorderLayout.NORTH);
        }
        MainApplication.getContentTestsJPanel().getParent().validate();
    }

}
