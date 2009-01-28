/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.gui.auxiliarclasess;

import code.google.com.p.ontologytesting.gui.menupanels.SeeTestJDialog;
import code.google.com.p.ontologytesting.gui.*;
import code.google.com.p.ontologytesting.gui.auxiliarpanels.ProgressControlJDialog;
import code.google.com.p.ontologytesting.model.CollectionTest;
import code.google.com.p.ontologytesting.model.ScenarioTest;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.InvalidOntologyException;
import code.google.com.p.ontologytesting.persistence.IOManagerImplementation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;

/**
 *
 * @author sara.garcia
 */
public class PopMenuTests implements ActionListener{

    private OpcionesMenu menu = new OpcionesMenu();
    private String testSelec="";
    private ScenarioTest s = new ScenarioTest();
    private URL editar,ejecutar,eliminarInst,eliminar,ver;

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        CollectionTest collection = CollectionTest.getInstance();
        ControladorTests controlador = ControladorTests.getInstance();
        ScenarioTest scenario = s.buscarScenario(collection.getScenariotest(), this.getTestSelec());
        if(source.getText().equals("Editar")){   
            menu.editarTest(scenario);
            controlador.prepararTest(scenario.getTipoTest().name());
        }else if(source.getText().equals("Eliminar Instancias")){
            int n = JOptionPane.showConfirmDialog(MainApplicationJFrame.getInstance(), "¿Realmente desea eliminar las instancias del test?", 
                "Eliminar Instancias Asociadas",JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION){
                if(scenario.eliminarInstancias()==true){
                    JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"Instancias Eliminadas",                                                  
                    "Confirm Message",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }else if(source.getText().equals("Ejecutar")){
            try{
                TreeResults.setTestSeleccionado(scenario.getNombre());
                ExecuteTest execTest = new ExecuteTest(scenario);
                ProgressControlJDialog progres = new ProgressControlJDialog(execTest);
                JProgressBar progresBar = progres.getProgressBar();
                progresBar.setValue(0);
                execTest.addPropertyChangeListener(new ProgressListener(progresBar,progres,true));
                execTest.execute();
                progres.setVisible(true); 
            }catch (InvalidOntologyException ex){
                JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"No se pudo ejecutar el test. Ontología no válida.",                                                  
                "Error Message",JOptionPane.ERROR_MESSAGE);
            }
        }else if(source.getText().equals("Ver")){
            SeeTestJDialog seeTestCompleted = menu.verTest(scenario);
            seeTestCompleted.setVisible(true);
        }else if(source.getText().equals("Eliminar")){
            int n = JOptionPane.showConfirmDialog(MainApplicationJFrame.getInstance(), "¿Realmente desea eliminar el test?", 
                "Eliminar Tests",JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION){
                menu.eliminarTest(scenario);
            }
        }
    }
    
    public JPopupMenu createPopupMenuForTests() {
        JMenuItem menuItem;  
        ver = this.getClass().getResource("images/document-print-preview.png");
        eliminar = this.getClass().getResource("images/eliminar.png");
        ejecutar = this.getClass().getResource("images/applications-system.png");
        editar = this.getClass().getResource("images/page_edit.png");
        eliminarInst = this.getClass().getResource("images/edit-clear.png");
        JPopupMenu popup = new JPopupMenu();
        menuItem = new JMenuItem("Editar");
        menuItem.setIcon(new ImageIcon(editar));
        menuItem.addActionListener(this);
        popup.add(menuItem);
        menuItem = new JMenuItem("Ejecutar");
        menuItem.setIcon(new ImageIcon(ejecutar));
        menuItem.addActionListener(this);
        popup.add(menuItem);
        menuItem = new JMenuItem("Eliminar Instancias");
        menuItem.setIcon(new ImageIcon(eliminarInst));
        menuItem.addActionListener(this);
        popup.add(menuItem);
        menuItem = new JMenuItem("Ver");
        menuItem.setIcon(new ImageIcon(ver));
        menuItem.addActionListener(this);
        popup.add(menuItem);
        menuItem = new JMenuItem("Eliminar");
        menuItem.setIcon(new ImageIcon(eliminar));
        menuItem.addActionListener(this);
        popup.add(menuItem);
        
        return popup;
    }
    
    public void setTestSelec(String testSelec){
        this.testSelec=testSelec;
    }
    
    public String getTestSelec(){
        return this.testSelec;
    }
}
