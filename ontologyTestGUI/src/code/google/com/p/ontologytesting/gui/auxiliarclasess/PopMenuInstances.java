/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.gui.auxiliarclasess;

import code.google.com.p.ontologytesting.gui.menupanels.AsociarInstanciasATestJDialog;
import code.google.com.p.ontologytesting.gui.menupanels.SeeTestJDialog;
import code.google.com.p.ontologytesting.gui.*;
import code.google.com.p.ontologytesting.gui.auxiliarpanels.RenombrarJDialog;
import code.google.com.p.ontologytesting.model.CollectionTest;
import code.google.com.p.ontologytesting.model.Instancias;
import code.google.com.p.ontologytesting.persistence.IOManagerImplementation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.WindowConstants;

/**
 *
 * @author sara.garcia
 */
public class PopMenuInstances implements ActionListener{

    private String instSelec="";
    private OpcionesMenu menu = new OpcionesMenu();
    private Instancias instancias = new Instancias();
    private URL editar,asociar,ver,eliminar;
    

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        CollectionTest collection = CollectionTest.getInstance();
        Instancias inst = instancias.buscarInstancias(collection.getInstancias(), this.getInstSelec());
        if(source.getText().equals("Renombrar")){   
            RenombrarJDialog renombrar = new RenombrarJDialog(MainApplicationJFrame.getInstance(),true,inst);
            renombrar.setVisible(true);
            menu.actualizarListaDeInstancias();
            menu.actualizarListaDeTestsSimples();
            menu.actualizarListaDeTestsSparql();
        }else if(source.getText().equals("Editar")){   
            MainApplicationJFrame.getInstance().cargarInstancia(inst, inst.getNombre());
        }else if(source.getText().equals("Asociar a un Test")){
            AsociarInstanciasATestJDialog asociarInst = new AsociarInstanciasATestJDialog(null, true, inst,false);
            asociarInst.setLocationRelativeTo(MainApplicationJFrame.getInstance());
            asociarInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            asociarInst.setVisible(true);
        }else if(source.getText().equals("Ver")){
            SeeTestJDialog seeTestCompleted = menu.verInstancias(inst);
            seeTestCompleted.setVisible(true);
        }else if(source.getText().equals("Eliminar")){
            int n = JOptionPane.showConfirmDialog(MainApplicationJFrame.getInstance(), "¿Realmente desea eliminar el conjunto de isntancias?", 
                "Eliminar Instancia",JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION){
                menu.eliminarInstancias(inst);
                ListAndTestsJPanel.getInstance().borrarInstancias(inst.getNombre());
            }  
        }
    }
    
    public JPopupMenu createPopupMenuForInstances() {
        JMenuItem menuItem; 
        ver = this.getClass().getResource("images/document-print-preview.png");
        eliminar = this.getClass().getResource("images/eliminar.png");
        asociar = this.getClass().getResource("images/add.png");
        editar = this.getClass().getResource("images/page_edit.png");
        JPopupMenu popup = new JPopupMenu();
        menuItem = new JMenuItem("Renombrar");
        //menuItem.setIcon(new ImageIcon(editar));
        menuItem.addActionListener(this);
        popup.add(menuItem);
        menuItem = new JMenuItem("Editar");
        menuItem.setIcon(new ImageIcon(editar));
        menuItem.addActionListener(this);
        popup.add(menuItem);
        menuItem = new JMenuItem("Asociar a un Test");
        menuItem.setIcon(new ImageIcon(asociar));
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
    
    public void setInstSelec(String instSelec){
        this.instSelec=instSelec;
    }
    
    public String getInstSelec(){
        return this.instSelec;
    }

}

