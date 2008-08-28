/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.gui;

/**
 *
 * @author sara.garcia
 */
import java.awt.*;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.*;
import javax.swing.event.*;
import code.google.com.p.ontologytesting.model.ClassInstances;
import code.google.com.p.ontologytesting.model.Instancias;
import code.google.com.p.ontologytesting.model.PropertyInstances;
import code.google.com.p.ontologytesting.model.ScenarioTest;

public class ListaFicheros extends JPanel implements ListSelectionListener {

    public static String getPathFicheroAbrir() {
        return pathFicheroAbrir;
    }
    public static void setPathFicheroAbrir(String aPathFicheroAbrir) {
        pathFicheroAbrir = aPathFicheroAbrir;
    } 
    private JTextArea descripcion = new JTextArea();
    private JList list;
    private JSplitPane splitPane;
    private String[] listaFicheros;
    private XMLDecoder decoder;
    private JScrollPane descripcionScrollPane;
    private static String pathFicheroAbrir;
    
    public ListaFicheros(String[] listaFicheros) {

        list = new JList(listaFicheros);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        
       
        JScrollPane listScrollPane = new JScrollPane(list);
        descripcion.setFont(descripcion.getFont().deriveFont(Font.BOLD));
        descripcion.setLineWrap(true);
        descripcion.setWrapStyleWord(true);
        
        descripcionScrollPane = new JScrollPane(descripcion);
        descripcionScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                   listScrollPane, descripcionScrollPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);

        Dimension minimumSize = new Dimension(100, 50);
        listScrollPane.setMinimumSize(minimumSize);
        descripcionScrollPane.setMinimumSize(minimumSize);

        splitPane.setPreferredSize(new Dimension(400, 200));
        updateLabel(listaFicheros[list.getSelectedIndex()]);
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        list = (JList)e.getSource();
        listaFicheros = AddInstancesJPanel.getFicheros();
        updateLabel(listaFicheros[list.getSelectedIndex()]);
    }
    
    public void updateLabel (String name) {
        
        String path = AddInstancesJPanel.getPathFichero();
        ListaFicheros.setPathFicheroAbrir(path.concat("\\").concat(name));
        ScenarioTest scenario = new ScenarioTest();
        try{
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(path.concat("\\").concat(name))));
            if(AbrirTestsJDialog.getFicherosComboBox()==0 || AbrirTestsJDialog.getFicherosComboBox()==2){
                scenario = (ScenarioTest) decoder.readObject();
                descripcion.setText("Nombre Test: "+scenario.getNombre()+
                        "\n\nDescripción: "+scenario.getDescripcion());
                descripcion.setEditable(false);         
            }else if(AbrirTestsJDialog.getFicherosComboBox()==1){ 
                Instancias inst = (Instancias) decoder.readObject();
                ArrayList<ClassInstances> clasInst = inst.getClassInstances();
                ArrayList<PropertyInstances> propInst = inst.getPropertyInstances();
                ListIterator cI,pI;
                cI = clasInst.listIterator();
                pI = propInst.listIterator();
                String deClase="";
                String dePropiedad="";
                while(cI.hasNext()){
                    ClassInstances clasInstance = (ClassInstances) cI.next();
                    if(deClase.equals("")){
                        deClase = clasInstance.getClassInstance()+"\n";
                    }else{
                        deClase=deClase+clasInstance.getClassInstance()+"\n";
                    }
                }
                while(pI.hasNext()){
                    PropertyInstances propInstance = (PropertyInstances) pI.next();
                    if(dePropiedad.equals("")){
                        dePropiedad = propInstance.getPropertyInstance()+"\n";
                    }else{
                        dePropiedad=dePropiedad+propInstance.getPropertyInstance()+"\n";
                    }
                }
                descripcion.setText("Instancias de Clase: \n\n"+deClase+
                        "\n\n\nInstancias de Propiedad: \n\n"+dePropiedad);
            }           
            decoder.close();    
        }catch(FileNotFoundException e){
        }
    }

    public JList getImageList() {
        return list;
    }

    public JSplitPane getSplitPane(){
        return splitPane;
    }

}
