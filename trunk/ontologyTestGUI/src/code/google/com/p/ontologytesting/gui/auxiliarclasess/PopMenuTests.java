/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.gui.auxiliarclasess;

import code.google.com.p.ontologytesting.gui.menupanels.SeeTestJDialog;
import code.google.com.p.ontologytesting.gui.*;
import code.google.com.p.ontologytesting.model.CollectionTest;
import code.google.com.p.ontologytesting.model.ScenarioTest;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.ExceptionReadOntology;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

/**
 *
 * @author sara.garcia
 */
public class PopMenuTests implements ActionListener{

    private OpcionesMenu menu = new OpcionesMenu();
    private String testSelec="";
    private ScenarioTest s = new ScenarioTest();

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        CollectionTest collection = CollectionTest.getInstance();
        ControladorTests controlador = ControladorTests.getInstance();
        ScenarioTest scenario = s.buscarScenario(collection.getScenariotest(), this.getTestSelec());
        if(source.getText().equals("Editar")){   
            menu.editarTest(scenario);
            controlador.prepararTest(scenario.getTipoTest().name());
        }else if(source.getText().equals("Ejecutar")){
            try{
                TreeResults.setTestSeleccionado(scenario.getNombre());
                ExecuteTest execTest = new ExecuteTest(scenario);
                execTest.execute();
                JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"Test ejecutado",                                                  
                "Confirm Message",JOptionPane.INFORMATION_MESSAGE);
            }catch (ExceptionReadOntology ex){
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
        JPopupMenu popup = new JPopupMenu();
        menuItem = new JMenuItem("Editar");
        menuItem.addActionListener(this);
        popup.add(menuItem);
        menuItem = new JMenuItem("Ejecutar");
        menuItem.addActionListener(this);
        popup.add(menuItem);
        menuItem = new JMenuItem("Ver");
        menuItem.addActionListener(this);
        popup.add(menuItem);
        menuItem = new JMenuItem("Eliminar");
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
