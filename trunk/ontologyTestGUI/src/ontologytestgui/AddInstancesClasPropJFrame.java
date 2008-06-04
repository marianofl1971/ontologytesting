/*
 * AddInstancesClasPropJFrame.java
 *
 * Created on 29 de mayo de 2008, 12:29
 */

package ontologytestgui;

import java.awt.Component;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.ClassInstances;
import model.CollectionTest;
import model.PropertyInstances;
/**
 *
 * @author  sara.garcia
 */
public class AddInstancesClasPropJFrame extends javax.swing.JFrame {

    static final int desktopWidth = 980;
    static final int desktopHeight = 580;
    static JFrame frame;
    private XMLDecoder decoder;
    private CollectionTest collectionTest;
    private AddComentJFrame commentPane;
    private ArrayList<ClassInstances> clasInst = new ArrayList<ClassInstances>();
    private ArrayList<PropertyInstances> propInst = new ArrayList<PropertyInstances>();
    
    /** Creates new form AddInstancesClasPropJFrame */
    public AddInstancesClasPropJFrame(int num) {
        initComponents();
        this.setSize(desktopWidth,desktopHeight);
        clasPanel.setLayout(new BoxLayout(clasPanel, BoxLayout.Y_AXIS));
        propPanel.setLayout(new BoxLayout(propPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i <= num; i++) {
            clasPanel.add(new CreateInstancesJPanel());
            propPanel.add(new CreateInstancesJPanel());
        }
        
    }    
    
    public AddInstancesClasPropJFrame(String textName) {
        
        initComponents();
        this.setSize(desktopWidth,desktopHeight);
        clasPanel.setLayout(new BoxLayout(clasPanel, BoxLayout.Y_AXIS));
        propPanel.setLayout(new BoxLayout(propPanel, BoxLayout.Y_AXIS));
        
        /*try{
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(textName )));
            collectionTest = (CollectionTest)decoder.readObject();
            ArrayList<ScenarioTest> scenarioTest = collectionTest.getScenariotest();
            ListIterator li;
            li = scenarioTest.listIterator();
            while(li.hasNext()){
                
                ScenarioTest st = (ScenarioTest) li.next();
                clasInst = st.getClassInstances();
                propInst = st.getPropertyInstances();
                ListIterator ci,pi;
           
                ci = clasInst.listIterator();
                pi = propInst.listIterator();
            
                while(ci.hasNext()){
                    CreateInstancesJPanel instClas = new CreateInstancesJPanel();
                    ClassInstances c = (ClassInstances) ci.next();
                    instClas.setInstance(c.getClassInstance());
                    commentPane = instClas.getComment();
                    commentPane.setComent(c.getComment());
                    instClas.setComment(commentPane);
                    clasPanel.add(instClas);
                }
                while(pi.hasNext()){
                    CreateInstancesJPanel instProp = new CreateInstancesJPanel();
                    PropertyInstances p = (PropertyInstances) pi.next();
                    instProp.setInstance(p.getPropertyInstance());
                    commentPane = instProp.getComment();
                    commentPane.setComent(p.getComment());
                    instProp.setComment(commentPane);
                    propPanel.add(instProp);
                }
            }
            decoder.close();    
        }catch(FileNotFoundException e){
        }*/
        
        try{
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(textName )));
            ArrayList al = (ArrayList) decoder.readObject();
            ListIterator li;
            li = al.listIterator();
            while(li.hasNext()){   
                ClassInstances cI = (ClassInstances) li.next();

                    CreateInstancesJPanel instClas = new CreateInstancesJPanel();
                    instClas.setInstance(cI.getClassInstance());
                    commentPane = instClas.getComment();
                    commentPane.setComent(cI.getComment());
                    instClas.setComment(commentPane);
                    clasPanel.add(instClas);
                }
            ArrayList la = (ArrayList) decoder.readObject();
            ListIterator il;
            il = la.listIterator();
            while(il.hasNext()){   
                PropertyInstances pI = (PropertyInstances) il.next();

                    CreateInstancesJPanel instProp = new CreateInstancesJPanel();
                    instProp.setInstance(pI.getPropertyInstance());
                    commentPane = instProp.getComment();
                    commentPane.setComent(pI.getComment());
                    instProp.setComment(commentPane);
                    propPanel.add(instProp);
                }
            decoder.close();    
        }catch(FileNotFoundException e){
        }         

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPanel = new javax.swing.JPanel();
        classScrollPane = new javax.swing.JScrollPane();
        clasPanel = new javax.swing.JPanel();
        propScrollPane = new javax.swing.JScrollPane();
        propPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        guardarInstButton = new javax.swing.JButton();
        cancelarInstButton = new javax.swing.JButton();
        limpiarInstButton = new javax.swing.JButton();
        newClasButton = new javax.swing.JButton();
        newPropButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Asociar Instancias");

        javax.swing.GroupLayout clasPanelLayout = new javax.swing.GroupLayout(clasPanel);
        clasPanel.setLayout(clasPanelLayout);
        clasPanelLayout.setHorizontalGroup(
            clasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );
        clasPanelLayout.setVerticalGroup(
            clasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 579, Short.MAX_VALUE)
        );

        classScrollPane.setViewportView(clasPanel);

        javax.swing.GroupLayout propPanelLayout = new javax.swing.GroupLayout(propPanel);
        propPanel.setLayout(propPanelLayout);
        propPanelLayout.setHorizontalGroup(
            propPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );
        propPanelLayout.setVerticalGroup(
            propPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 579, Short.MAX_VALUE)
        );

        propScrollPane.setViewportView(propPanel);

        jLabel1.setText("Añada las instancias que quiera a su ontología:");

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 12));
        jLabel2.setText("Instancias de Clase:");

        jLabel3.setFont(new java.awt.Font("Arial Black", 0, 12));
        jLabel3.setText("Instancias de Propiedad:");

        guardarInstButton.setText("Asociar");
        guardarInstButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarInstButtonActionPerformed(evt);
            }
        });

        cancelarInstButton.setText("Cancelar");
        cancelarInstButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarInstButtonActionPerformed(evt);
            }
        });

        limpiarInstButton.setText("Limpiar");
        limpiarInstButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarInstButtonActionPerformed(evt);
            }
        });

        newClasButton.setText("Nueva clase");
        newClasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newClasButtonActionPerformed(evt);
            }
        });

        newPropButton.setText("Nueva propiedad");
        newPropButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPropButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPanelLayout = new javax.swing.GroupLayout(JPanel);
        JPanel.setLayout(JPanelLayout);
        JPanelLayout.setHorizontalGroup(
            JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(classScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JPanelLayout.createSequentialGroup()
                        .addComponent(limpiarInstButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newClasButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newPropButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(propScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(JPanelLayout.createSequentialGroup()
                            .addComponent(cancelarInstButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(guardarInstButton))))
                .addGap(40, 40, 40))
        );

        JPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelarInstButton, guardarInstButton});

        JPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {classScrollPane, propScrollPane});

        JPanelLayout.setVerticalGroup(
            JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(propScrollPane, 0, 0, Short.MAX_VALUE)
                    .addComponent(classScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardarInstButton)
                    .addComponent(cancelarInstButton)
                    .addComponent(limpiarInstButton)
                    .addComponent(newClasButton)
                    .addComponent(newPropButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cancelarInstButton, guardarInstButton, limpiarInstButton, newClasButton, newPropButton});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 992, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(JPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 992, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 552, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(JPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void guardarInstButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarInstButtonActionPerformed
// TODO add your handling code here:
  
    int totalClas = clasPanel.getComponentCount();
    for(int i=0; i<totalClas; i++){
        CreateInstancesJPanel panelInst = (CreateInstancesJPanel) clasPanel.getComponent(i);
        String query = panelInst.getQuery();
        AddComentJFrame comentPane = panelInst.getComment();
        String coment = comentPane.getComent();
            ClassInstances cI = new ClassInstances(query,coment);
            clasInst.add(cI);
    }
    int totalProp = propPanel.getComponentCount();
    for(int i=0; i< totalProp; i++){
        CreateInstancesJPanel panelInst = (CreateInstancesJPanel) propPanel.getComponent(i);
        String query = panelInst.getQuery();
        AddComentJFrame comentPane = panelInst.getComment();
        String coment = comentPane.getComent();
            PropertyInstances pI = new PropertyInstances(query,coment);
            propInst.add(pI);
    }
    
    Component comp = null;
    int n = JOptionPane.showConfirmDialog(comp, "¿Quiere guardar este conjunto de" +
           " instancias para futuras pruebas?", "Guardar Instancias",
           JOptionPane.YES_NO_OPTION);
   if (n == JOptionPane.YES_OPTION) {
    String nombreArch=null;
    String nameInstances=null;
    
    nombreArch = JOptionPane.showInputDialog(null,"Introduzca el nombre para el " +
            "archivo con este conjunto de instancias","Nombre del archivo",
            JOptionPane.QUESTION_MESSAGE);
            
    if(nombreArch.endsWith(".xml")){
        nameInstances=nombreArch;
    }else{
        nameInstances=nombreArch.concat(".xml");
    }
            
    try {
        XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new 
                    FileOutputStream(nameInstances)));
        e.writeObject(clasInst);
        e.writeObject(propInst);
        e.close();
     } catch (FileNotFoundException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
     }
    this.setVisible(false);
   }else{
        this.setVisible(false);
   }
}//GEN-LAST:event_guardarInstButtonActionPerformed

private void cancelarInstButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarInstButtonActionPerformed
// TODO add your handling code here:
    int totalClas = clasPanel.getComponentCount();
    for(int i=0; i<totalClas; i++){
        CreateInstancesJPanel panelInst = (CreateInstancesJPanel) clasPanel.getComponent(i);
        panelInst.setInstance("");
        AddComentJFrame comentPane = panelInst.getComment();
        comentPane.setComent("");
        ClassInstances cI = new ClassInstances();
        clasInst.add(cI);
    }
    int totalProp = clasPanel.getComponentCount();
    for(int i=0; i<totalProp; i++){
        CreateInstancesJPanel panelInst = (CreateInstancesJPanel) propPanel.getComponent(i);
        panelInst.setInstance("");
        AddComentJFrame comentPane = panelInst.getComment();
       comentPane.setComent("");
        PropertyInstances pI = new PropertyInstances();
        propInst.add(pI);
    } 
   this.setVisible(false);
}//GEN-LAST:event_cancelarInstButtonActionPerformed

private void limpiarInstButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarInstButtonActionPerformed
// TODO add your handling code here:
    int totalClas = clasPanel.getComponentCount();
    for(int i=0; i<totalClas; i++){
        CreateInstancesJPanel panelInst = (CreateInstancesJPanel) clasPanel.getComponent(i);
        panelInst.setInstance("");
        AddComentJFrame comentPane = panelInst.getComment();
        comentPane.setComent("");
        ClassInstances cI = new ClassInstances();
        clasInst.add(cI);
    }
    int totalProp = clasPanel.getComponentCount();
    for(int i=0; i<totalProp; i++){
        CreateInstancesJPanel panelInst = (CreateInstancesJPanel) propPanel.getComponent(i);
        panelInst.setInstance("");
        AddComentJFrame comentPane = panelInst.getComment();
        comentPane.setComent("");
        PropertyInstances pI = new PropertyInstances();
        propInst.add(pI);
    }
}//GEN-LAST:event_limpiarInstButtonActionPerformed

private void newClasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newClasButtonActionPerformed
// TODO add your handling code here:
    clasPanel.add(new CreateInstancesJPanel());
}//GEN-LAST:event_newClasButtonActionPerformed

private void newPropButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPropButtonActionPerformed
// TODO add your handling code here:
    propPanel.add(new CreateInstancesJPanel());
}//GEN-LAST:event_newPropButtonActionPerformed

    public ArrayList<ClassInstances> getClassInstances(){
        
        int totalClas = clasPanel.getComponentCount();
        for(int i=0; i<totalClas; i++){
            CreateInstancesJPanel panelInst = (CreateInstancesJPanel) clasPanel.getComponent(i);
            String query = panelInst.getQuery();
            AddComentJFrame comentPane = panelInst.getComment();
            String coment = comentPane.getComent();
            ClassInstances cI = new ClassInstances(query,coment);
            clasInst.add(cI);
        }
        return clasInst;
    }
    
    public ArrayList<PropertyInstances> getPropertyInstances(){
        
        int totalProp = propPanel.getComponentCount();
        for(int i=0; i< totalProp; i++){
            CreateInstancesJPanel panelInst = (CreateInstancesJPanel) propPanel.getComponent(i);
            String query = panelInst.getQuery();
            AddComentJFrame comentPane = panelInst.getComment();
            String coment = comentPane.getComent();
            PropertyInstances pI = new PropertyInstances(query,coment);
            propInst.add(pI);
        }
        return propInst;
    }
    
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    /*private static void createAndShowGUI() {
        //JFrame.setDefaultLookAndFeelDecorated(true);
        //Create and set up the window.
        frame = new JFrame("Asociar Instancias");
        frame.getContentPane().add(new AddInstancesClasPropJPanel(4));
        frame.setSize(desktopWidth,desktopHeight); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }*/    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanel;
    private javax.swing.JButton cancelarInstButton;
    private javax.swing.JPanel clasPanel;
    private javax.swing.JScrollPane classScrollPane;
    private javax.swing.JButton guardarInstButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton limpiarInstButton;
    private javax.swing.JButton newClasButton;
    private javax.swing.JButton newPropButton;
    private javax.swing.JPanel propPanel;
    private javax.swing.JScrollPane propScrollPane;
    // End of variables declaration//GEN-END:variables

}
