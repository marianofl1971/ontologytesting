/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontologytestgui;

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
import model.ClassInstances;
import model.PropertyInstances;
import model.ScenarioTest;

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

        //Create a split pane with the two scroll panes in it.
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                   listScrollPane, descripcionScrollPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);

        //Provide minimum sizes for the two components in the split pane.
        Dimension minimumSize = new Dimension(100, 50);
        listScrollPane.setMinimumSize(minimumSize);
        descripcionScrollPane.setMinimumSize(minimumSize);

        //Provide a preferred size for the split pane.
        splitPane.setPreferredSize(new Dimension(400, 200));
        updateLabel(listaFicheros[list.getSelectedIndex()]);
    }
    
    //Listens to the list
    @Override
    public void valueChanged(ListSelectionEvent e) {
        list = (JList)e.getSource();
        listaFicheros = AddInstancesJPanel.getFicheros();
        updateLabel(listaFicheros[list.getSelectedIndex()]);
    }
    
    //Renders the selected image
    public void updateLabel (String name) {
        //ImageIcon icon = createImageIcon("images/" + name + ".gif");
        //picture.setIcon(icon);
        
        String path = AddInstancesJPanel.getPathFichero();
        ListaFicheros.setPathFicheroAbrir(path.concat(name));
        ScenarioTest scenario = new ScenarioTest();
        try{
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(path.concat(name))));
            if(path.contains("Simple Tests") || path.contains("Sparql Tests")){
                scenario = (ScenarioTest) decoder.readObject();
                descripcion.setText("Nombre Test: "+scenario.getNombre()+
                        "\n\nDescripción: "+scenario.getDescripcion());
                descripcion.setEditable(false);         
            }else if(path.contains("Instancias")){
                ArrayList<ClassInstances> clasInst = (ArrayList<ClassInstances>) decoder.readObject();
                ArrayList<PropertyInstances> propInst = (ArrayList<PropertyInstances>) decoder.readObject();
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
                descripcion.setText("Instancias de Clase: \n"+deClase+
                        "\nInstancias de Propiedad: \n"+dePropiedad);
            }           
            decoder.close();    
        }catch(FileNotFoundException e){
        }
        
        /*if  (icon != null) {
            picture.setText(null);
        } else {
            picture.setText("Image not found");
        }*/
    }

    //Used by SplitPaneDemo2
    public JList getImageList() {
        return list;
    }

    public JSplitPane getSplitPane(){
        return splitPane;
    }

   
    /** Returns an ImageIcon, or null if the path was invalid. */
    /*protected static ImageIcon createImageIcon(String path) {
       java.net.URL imgURL = SplitPaneDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }*/

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    /*public static void createAndShowListaFicheros() {

        //Create and set up the window.
        JFrame frame = new JFrame("Abrir Tests");
        ListaFicheros listaFich = new ListaFicheros();
        frame.getContentPane().add(listaFich.getSplitPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }  */   
}
