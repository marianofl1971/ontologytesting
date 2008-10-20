/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.guiNew;

import code.google.com.p.ontologytesting.model.CollectionTest;
import code.google.com.p.ontologytesting.model.Instancias;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.WindowConstants;

/**
 *
 * @author sara.garcia
 */
public class PopMenuInstances implements ActionListener{

    private String instSelec="";
    private OpcionesMenu menu = new OpcionesMenu();
    private Utils utils = new Utils();
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        CollectionTest collection = CollectionTest.getInstance();
        Instancias inst = utils.buscarInstancias(collection.getInstancias(), this.getInstSelec());
        if(source.getText().equals("Editar")){   
            AddInstancesClasPropJDialog addInst = new AddInstancesClasPropJDialog(panel, false, inst);
            addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            addInst.setVisible(true);
        }else if(source.getText().equals("Asociar a un Test")){
            AsociarInstanciasATestJDialog asociarInst = new AsociarInstanciasATestJDialog(frame, true, inst);
            asociarInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            asociarInst.setVisible(true);
        }else if(source.getText().equals("Ver")){
            frame = new JFrame();
            SeeTestJDialog seeTestCompleted = new SeeTestJDialog(frame, false, inst);
            seeTestCompleted.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            seeTestCompleted.setVisible(true);
        }else if(source.getText().equals("Eliminar")){
            menu.eliminarInstancias(inst);
        }
    }
    
    public JPopupMenu createPopupMenuForInstances() {
        JMenuItem menuItem;  
        JPopupMenu popup = new JPopupMenu();
        menuItem = new JMenuItem("Editar");
        menuItem.addActionListener(this);
        popup.add(menuItem);
        menuItem = new JMenuItem("Asociar a un Test");
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
    
    public void setInstSelec(String instSelec){
        this.instSelec=instSelec;
    }
    
    public String getInstSelec(){
        return this.instSelec;
    }

}

