/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.guiNew;

import code.google.com.p.ontologytesting.model.CollectionTest;
import code.google.com.p.ontologytesting.model.ScenarioTest;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.WindowConstants;

/**
 *
 * @author sara.garcia
 */
public class PopMenuTests implements ActionListener{

    private Utils utils = new Utils();
    private OpcionesMenu menu = new OpcionesMenu();
    private JFrame frame = new JFrame();
    private String testSelec="";
    
    public void setTestSelec(String testSelec){
        this.testSelec=testSelec;
    }
    
    public String getTestSelec(){
        return this.testSelec;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        CollectionTest collection = CollectionTest.getInstance();
        ScenarioTest scenario = utils.buscarScenario(collection.getScenariotest(), this.getTestSelec());
        if(source.getText().equals("Editar")){   
            menu.editarTest(scenario);
        }else if(source.getText().equals("Ejecutar")){
            menu.ejecutarUnTest(scenario);
        }else if(source.getText().equals("Ver")){
            frame = new JFrame();
            SeeTestJDialog seeTestCompleted = new SeeTestJDialog(frame, true, scenario);
            //seeTestCompleted.setLocationRelativeTo(this);
            seeTestCompleted.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            seeTestCompleted.setVisible(true);
        }else if(source.getText().equals("Eliminar")){
            menu.eliminarTest(scenario);
        }
    }
    
    public JPopupMenu createPopupMenuForTests() {
        JMenuItem menuItem;  
        //Create the popup menu.
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
}
