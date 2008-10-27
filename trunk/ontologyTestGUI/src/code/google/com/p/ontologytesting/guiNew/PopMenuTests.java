/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.guiNew;

import code.google.com.p.ontologytesting.model.CollectionTest;
import code.google.com.p.ontologytesting.model.ScenarioTest;
import code.google.com.p.ontologytesting.persistence.SaveTest;
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

    private Utils utils = new Utils();
    private SaveTest saveTest =  new SaveTest();
    private OpcionesMenu menu = new OpcionesMenu();
    private String testSelec="";

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        CollectionTest collection = CollectionTest.getInstance();
        ControladorTests controlador = ControladorTests.getInstance();
        ScenarioTest scenario = utils.buscarScenario(collection.getScenariotest(), this.getTestSelec());
        if(source.getText().equals("Editar")){   
            controlador.prepararTest(scenario.getTipoTest().getTipo());
            if(controlador.algunTestSinGuardar()==false){
                menu.editarTest(scenario);
                controlador.prepararTest(scenario.getTipoTest().getTipo());
            }else{
                int n = JOptionPane.showConfirmDialog(MainApplicationJFrame.getInstance(), "¿Guardar los cambios realizados al test?", 
                "Guardar Tests",JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION){
                    saveTest.replaceScenarioLocally(scenario);
                }
                menu.editarTest(scenario);
                controlador.prepararTest(scenario.getTipoTest().getTipo());
            }
        }else if(source.getText().equals("Ejecutar")){
            boolean res = menu.ejecutarUnTest(scenario);
            if(res==true){
                JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"Test ejecutado",                                                  
                "Confirm Message",JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"No se pudo ejecutar el test",                                                  
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
