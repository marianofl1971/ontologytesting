/*
 * ContentMainJFrame.java
 *
 * Created on 25 de junio de 2008, 1:27
 */

package ontologytestgui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import model.ClassInstances;
import model.PropertyInstances;

/**
 *
 * @author  sara.garcia
 */
public class ContentMainJFrame extends javax.swing.JFrame {

    public static ArrayList getPaginas() {
        return paginas;
    }
    public static void setPaginas(ArrayList aPaginas) {
        paginas = aPaginas;
    }
    public static int getActual() {
        return actual;
    }
    public static void setActual(int aActual) {
        actual = aActual;
    }
    public static ArrayList<ArrayList<ClassInstances>> getConjuntoClassInstances() {
        return conjuntoClassInstances;
    }
    public static void setConjuntoClassInstances(ArrayList<ClassInstances> aConjuntoClassInstances, int pos) {
        conjuntoClassInstances.add(pos, aConjuntoClassInstances);
    }
    public static ArrayList<ArrayList<PropertyInstances>> getConjuntoPropInstances() {
        return conjuntoPropInstances;
    }
    public static void setConjuntoPropInstances(ArrayList<PropertyInstances> aConjuntoPropInstances, int pos) {
        conjuntoPropInstances.add(pos,aConjuntoPropInstances);
    }
    private static ArrayList conjuntoClassInstances = new ArrayList<ClassInstances>();
    private static ArrayList conjuntoPropInstances = new ArrayList<PropertyInstances>();
    private MainJPanel mainPanel = new MainJPanel();
    private GroupTestsJPanel groupTests;
    private JFrame frame;
    private AddInstancesClasPropJDialog addInstances = new AddInstancesClasPropJDialog(frame,true,8,0);
    private AddSPARQLJPanel sparql;
    private JLabel label = new JLabel("RESULTADO DE SUS PRUEBAS");
    private static ArrayList paginas = new ArrayList();
    private static int actual=0;
    
    /** Creates new form ContentMainJFrame */
    public ContentMainJFrame() {
        initComponents();
        for (int i = 0; i <= 4; i++) {
            conjuntoClassInstances.add(new ArrayList<ClassInstances>());
            conjuntoPropInstances.add(new ArrayList<PropertyInstances>());
        }
        contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setTitle("EVALUADOR DE ONTOLOGÍAS");
        this.setSize(new Dimension(980,700));
        contentPanel.add(mainPanel);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentPanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        siguienteButton = new javax.swing.JButton();
        anteriorButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 913, Short.MAX_VALUE)
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 531, Short.MAX_VALUE)
        );

        siguienteButton.setText("Siguiente");
        siguienteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteButtonActionPerformed(evt);
            }
        });

        anteriorButton.setText("Anterior");
        anteriorButton.setEnabled(false);
        anteriorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anteriorButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(741, 741, 741)
                .addComponent(anteriorButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(siguienteButton)
                .addGap(18, 18, 18))
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 913, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(547, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(siguienteButton)
                    .addComponent(anteriorButton))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(70, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void siguienteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteButtonActionPerformed
// TODO add your handling code here:
    completarArrayOrden();
    if(ContentMainJFrame.getActual()==0){
            if(MainJPanel.getFisicalOntologyTextField().equals("") || 
                    MainJPanel.getNamespaceOntologyTextField().equals("")){
                
                JOptionPane.showMessageDialog(frame,"Ambos campos ubicación " +
                        "física y namespace son obligatorios","Warning Message",JOptionPane.WARNING_MESSAGE);

            }else{
            if(paginas.get(0).equals(1)){
                groupTests = new GroupTestsJPanel(8);
                contentPanel.remove(mainPanel);
                contentPanel.add(groupTests);
                AddSPARQLJPanel.setSeleccionado(false);
                anteriorButton.setEnabled(true);
                ContentMainJFrame.setActual(1);
                this.validate();
            }else if(paginas.get(1).equals(1)){
                contentPanel.remove(mainPanel);
                contentPanel.add(addInstances.getContentPanel());
                anteriorButton.setEnabled(true);
                ContentMainJFrame.setActual(2);
                this.validate();
            }else if(paginas.get(2).equals(1)){
                sparql = new AddSPARQLJPanel();
                AddSPARQLJPanel.setSPARQLQuery("" +
                        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                        "SELECT ?subject ?object " +
                        "FROM <data/family.owl>" +
                        "WHERE " +
                        "{?subject rdfs:subClassOf ?object }");
                contentPanel.remove(mainPanel);
                contentPanel.add(sparql);
                AddSPARQLJPanel.setSeleccionado(true);
                anteriorButton.setEnabled(true);
                ContentMainJFrame.setActual(3);
                this.validate();
            }else{
                JOptionPane.showMessageDialog(frame,"Debe seleccionar alguna " +
                        "opción para continuar","Warning Message",JOptionPane.WARNING_MESSAGE);
            }
            }
    }else{
        if(ContentMainJFrame.getActual()==1){
            if(paginas.get(1).equals(1)){
                contentPanel.remove(groupTests);
                contentPanel.add(addInstances.getContentPanel());
                anteriorButton.setEnabled(true);
                ContentMainJFrame.setActual(2);
                this.validate();
            }else if(paginas.get(2).equals(1)){
                sparql = new AddSPARQLJPanel();
                AddSPARQLJPanel.setSPARQLQuery("" +
                        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                        "SELECT ?subject ?object " +
                        "FROM <data/family.owl>" +
                        "WHERE " +
                        "{?subject rdfs:subClassOf ?object }");
                contentPanel.remove(groupTests);
                contentPanel.add(sparql);
                AddSPARQLJPanel.setSeleccionado(true);
                anteriorButton.setEnabled(true);
                ContentMainJFrame.setActual(3);
                this.validate();
            }else{
                groupTests = new GroupTestsJPanel(8);
                contentPanel.remove(groupTests);
                contentPanel.add(label);
                groupTests.guardarDatos();
                anteriorButton.setEnabled(true);
                ContentMainJFrame.setActual(4);
                this.validate();
            }
        }else if(ContentMainJFrame.getActual()==2){     
            if(paginas.get(2).equals(1)){
                sparql = new AddSPARQLJPanel();
                AddSPARQLJPanel.setSPARQLQuery("" +
                        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                        "SELECT ?subject ?object " +
                        "FROM <data/family.owl>" +
                        "WHERE " +
                        "{?subject rdfs:subClassOf ?object }");
                contentPanel.remove(addInstances.getContentPanel());
                contentPanel.add(sparql);
                anteriorButton.setEnabled(true);
                ContentMainJFrame.setActual(3);
                this.validate();
            }else{
            }
        }else if(ContentMainJFrame.getActual()==3){
                groupTests = new GroupTestsJPanel(8);
                groupTests.guardarDatos();
                contentPanel.remove(sparql);
                contentPanel.add(label);
                AddSPARQLJPanel.setSeleccionado(true);
                anteriorButton.setEnabled(true);
                ContentMainJFrame.setActual(4);
                this.validate();
        }else if(ContentMainJFrame.getActual()==4){
        }
    }
}//GEN-LAST:event_siguienteButtonActionPerformed

public void completarArrayOrden(){
    
    if(MainJPanel.getNewTestState()==true){
        paginas.add(0, 1);
    }else{
        paginas.add(0, 0);
    }
    if(MainJPanel.getNewInstancesState()==true){
        paginas.add(1, 1);
    }else{
        paginas.add(1, 0);
    }
    if(MainJPanel.getSparqlState()==true){
        paginas.add(2, 1);
    }else{
        paginas.add(2, 0);
    }
}

private void anteriorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anteriorButtonActionPerformed
// TODO add your handling code here:
    completarArrayOrden();
    if(ContentMainJFrame.getActual()==0){
        anteriorButton.setEnabled(false);
    }else{
        if(ContentMainJFrame.getActual()==1){
                contentPanel.remove(groupTests);
                contentPanel.add(mainPanel);
                anteriorButton.setEnabled(false);
                ContentMainJFrame.setActual(0);
                this.validate();
        }else if(ContentMainJFrame.getActual()==2){            
            if(paginas.get(0).equals(1)){
                contentPanel.remove(addInstances.getContentPanel());
                contentPanel.add(groupTests);
                AddSPARQLJPanel.setSeleccionado(false);
                anteriorButton.setEnabled(true);
                ContentMainJFrame.setActual(1);
                this.validate();
            }else{
                contentPanel.remove(addInstances.getContentPanel());
                contentPanel.add(mainPanel);
                anteriorButton.setEnabled(false);
                ContentMainJFrame.setActual(0);
                this.validate();
            }
        }else if(ContentMainJFrame.getActual()==3){
            if(paginas.get(1).equals(1)){
                contentPanel.remove(sparql);
                contentPanel.add(addInstances.getContentPanel());
                AddSPARQLJPanel.setSeleccionado(false);
                anteriorButton.setEnabled(true);
                ContentMainJFrame.setActual(2);
                this.validate();
            }else if(paginas.get(0).equals(1)){
                contentPanel.remove(sparql);
                contentPanel.add(groupTests);
                AddSPARQLJPanel.setSeleccionado(false);
                anteriorButton.setEnabled(true);
                ContentMainJFrame.setActual(1);
                this.validate();
            }else{
                contentPanel.remove(sparql);
                contentPanel.add(mainPanel);
                anteriorButton.setEnabled(false);
                ContentMainJFrame.setActual(0);
                this.validate();
            }
        }else if(ContentMainJFrame.getActual()==4){
            if(paginas.get(2).equals(1)){
                contentPanel.remove(label);
                contentPanel.add(sparql);
                anteriorButton.setEnabled(true);
                ContentMainJFrame.setActual(3);
                this.validate();
            }else if(paginas.get(1).equals(1)){
                contentPanel.remove(label);
                contentPanel.add(addInstances.getContentPanel());
                AddSPARQLJPanel.setSeleccionado(false);
                anteriorButton.setEnabled(true);
                ContentMainJFrame.setActual(2);
                this.validate();
            }else if(paginas.get(0).equals(1)){
                contentPanel.remove(label);
                contentPanel.add(groupTests);
                AddSPARQLJPanel.setSeleccionado(false);
                anteriorButton.setEnabled(true);
                ContentMainJFrame.setActual(1);
                this.validate();
            }else{
                contentPanel.remove(label);
                contentPanel.add(mainPanel);
                anteriorButton.setEnabled(false);
                ContentMainJFrame.setActual(0);
                this.validate();
            }
        }
    }
}//GEN-LAST:event_anteriorButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ContentMainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton anteriorButton;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton siguienteButton;
    // End of variables declaration//GEN-END:variables

}

