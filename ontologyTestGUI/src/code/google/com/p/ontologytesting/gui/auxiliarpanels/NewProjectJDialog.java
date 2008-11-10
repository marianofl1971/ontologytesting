/*
 * NewProjectJDialog.java
 *
 * Created on 6 de octubre de 2008, 11:46
 */

package code.google.com.p.ontologytesting.gui.auxiliarpanels;

import code.google.com.p.ontologytesting.gui.*;
import code.google.com.p.ontologytesting.gui.auxiliarclasess.AniadirPanelDeAviso;
import code.google.com.p.ontologytesting.model.CollectionTest;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.*;
import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author  sara.garcia
 */
public class NewProjectJDialog extends javax.swing.JDialog {

    private ProjectNameSituJPanel project = new ProjectNameSituJPanel();;
    private OntologyNameSituJPanel ontology = new OntologyNameSituJPanel();;
    private String nombreProy="", ubicProy="", ubicOnto="", namespaceOnto="";
    private Reasoner jenaInterface;
    private InterfaceReasoner jena;
    private boolean proyectoCreado=false;
    private AniadirPanelDeAviso panelAviso;
    
    /** Creates new form NewProjectJDialog */
    public NewProjectJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        panelAviso = new AniadirPanelDeAviso();
        this.setTitle("Nuevo Proyecto");
        project = new ProjectNameSituJPanel();
        ontology = new OntologyNameSituJPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(project, BorderLayout.CENTER);
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
        antButton = new javax.swing.JButton();
        sigButton = new javax.swing.JButton();
        terminarButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 611, Short.MAX_VALUE)
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        antButton.setText("< Anterior");
        antButton.setEnabled(false);
        antButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                antButtonActionPerformed(evt);
            }
        });

        sigButton.setText("Siguiente >");
        sigButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sigButtonActionPerformed(evt);
            }
        });

        terminarButton.setText("Terminar");
        terminarButton.setEnabled(false);
        terminarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminarButtonActionPerformed(evt);
            }
        });

        cancelarButton.setText("Cancelar");
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(271, Short.MAX_VALUE)
                .addComponent(antButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sigButton)
                .addGap(18, 18, 18)
                .addComponent(terminarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelarButton)
                .addContainerGap())
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarButton)
                    .addComponent(antButton)
                    .addComponent(sigButton)
                    .addComponent(terminarButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void antButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_antButtonActionPerformed
// TODO add your handling code here:
    antButton.setEnabled(false);
    terminarButton.setEnabled(false);
    sigButton.setEnabled(true);
    contentPanel.remove(0);
    contentPanel.add(project);
    contentPanel.getParent().validate();
}//GEN-LAST:event_antButtonActionPerformed

private void sigButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sigButtonActionPerformed
// TODO add your handling code here:
    antButton.setEnabled(true);
    terminarButton.setEnabled(true);
    sigButton.setEnabled(false);
    ubicProy = project.getUbicacionProyecto();
    nombreProy = project.getNombreProyectoTextField();
    if(nombreProy.equals("") || (ubicProy.equals(""))){
        panelAviso.warningAction("Todos los campos son obligatorios", this);
    }else{
        project.setNombreP(nombreProy);
        project.setUbicP(ubicProy);
        contentPanel.remove(0);
        contentPanel.add(ontology);
        contentPanel.getParent().validate();
    }
}//GEN-LAST:event_sigButtonActionPerformed

private void terminarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terminarButtonActionPerformed
// TODO add your handling code here:
    jenaInterface = new Reasoner();
    jena = jenaInterface.getReasoner();
    if(jenaInterface.isCargado()==true){ 
        ubicOnto = ontology.getUbicacionOnto();
        namespaceOnto = ontology.getNamespaceOntoTextField();
        if(ubicOnto.equals("") || namespaceOnto.equals("")){
            panelAviso.warningAction("Todos los campos son obligatorios", this);
        }else{
            if(!namespaceOnto.endsWith("#")){
                namespaceOnto = namespaceOnto.concat("#");
            }
            ontology.setUbicOnto(ubicOnto);
            ontology.setNsOnto(namespaceOnto);
             try{
                jena.addReasoner(ubicOnto);
                CollectionTest.getInstance().setNamespace(namespaceOnto);
                CollectionTest.getInstance().setOntology(ubicOnto);
                File directorio = new File(project.getCarpetaProyectoTextField());
                boolean res = directorio.mkdir(); 
                if(res==true){
                    MainApplicationJFrame.getInstance().setCarpetaProyecto(project.getCarpetaProyectoTextField());
                    MainApplicationJFrame.getInstance().setNombreProyecto(nombreProy);
                    setProyectoCreado(true);
                    panelAviso.confirmAction("Proyecto Creado", this);
                    this.setVisible(false);
                }else{
                    panelAviso.errorAction("No se puedo crear un directorio para el proyecto", this);
                }
            }catch(ExceptionReadOntology ex){
                JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"No se pudo crear el proyecto. La ontologia introducida no es valida.\n" +
                "Introduzca una ontologia valida.","Error Message",JOptionPane.ERROR_MESSAGE);
            }
        } 
    }else{
        JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"Error en la aplicación","Error Message",JOptionPane.ERROR_MESSAGE);
    }
}//GEN-LAST:event_terminarButtonActionPerformed

private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
// TODO add your handling code here:
    this.setVisible(false);
}//GEN-LAST:event_cancelarButtonActionPerformed

    public boolean getProyectoCreado() {
        return proyectoCreado;
    }

    public void setProyectoCreado(boolean proyectoCreado) {
        this.proyectoCreado = proyectoCreado;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton antButton;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton sigButton;
    private javax.swing.JButton terminarButton;
    // End of variables declaration//GEN-END:variables

}
