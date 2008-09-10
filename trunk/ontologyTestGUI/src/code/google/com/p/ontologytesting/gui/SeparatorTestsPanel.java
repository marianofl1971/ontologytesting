/*
 * SeparatorTestsPanel.java
 *
 * Created on 29 de agosto de 2008, 14:33
 */

package code.google.com.p.ontologytesting.gui;

import java.awt.BorderLayout;

/**
 *
 * @author  sara.garcia
 */
public class SeparatorTestsPanel extends javax.swing.JPanel {

    /** Creates new form SeparatorTestsPanel */
    public SeparatorTestsPanel() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        volverButton = new javax.swing.JButton();
        ejecutarButton = new javax.swing.JButton();

        volverButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/arrow_left.png"))); // NOI18N
        volverButton.setText("Volver");
        volverButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverButtonActionPerformed(evt);
            }
        });

        ejecutarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/cog.png"))); // NOI18N
        ejecutarButton.setText("Ejectuar Tests");
        ejecutarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(volverButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addComponent(ejecutarButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(volverButton)
                    .addComponent(ejecutarButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void volverButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverButtonActionPerformed
// TODO add your handling code here:
    ContentMainJFrame.getContentPanel().remove(0);
    ContentMainJFrame.getSeparadorPanel().remove(0);
    ContentMainJFrame.getSeparadorPanel().add(new SeparatorExitJPanel(),BorderLayout.EAST);
    ContentMainJFrame.getContentPanel().add(ContentMainJFrame.getMainPanel());
    ContentMainJFrame.getSeparador().setVisible(false);
    ContentMainJFrame.getContentPanel().validate();
    ContentMainJFrame.getSeparadorPanel().validate();
    MainJPanel.setSimpleTestSelect(false);
    MainJPanel.setSparqlTestsSelect(false);
    AddSPARQLJPanel.setSeleccionado(false);
}//GEN-LAST:event_volverButtonActionPerformed

private void ejecutarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejecutarButtonActionPerformed
// TODO add your handling code here:
    
    if(AddSPARQLJPanel.isSeleccionado()==false){
        GroupTestsJPanel group = (GroupTestsJPanel) ContentMainJFrame.getContentPanel().getComponent(0);
        if(group.guardarDatos()==true){
            ContentMainJFrame.getContentPanel().remove(0);
            ContentMainJFrame.getSeparadorPanel().remove(0);
            ContentMainJFrame.getContentPanel().add(GroupTestsJPanel.getPanelTree());
            ContentMainJFrame.getContentPanel().validate();
            ContentMainJFrame.getContentPanel().getParent().validate();
            ContentMainJFrame.getSeparadorPanel().add(new SeparatorResultTestsActionJPanel());
            ContentMainJFrame.getSeparadorPanel().validate();
            ContentMainJFrame.getSeparadorPanel().getParent().validate();
        }
    }else{
        AddSPARQLJPanel group = (AddSPARQLJPanel) ContentMainJFrame.getContentPanel().getComponent(0);
        if(group.guardarDatos()==true){
            ContentMainJFrame.getContentPanel().remove(0);
            ContentMainJFrame.getSeparadorPanel().remove(0);
            ContentMainJFrame.getContentPanel().add(group.getPanelTree());
            ContentMainJFrame.getContentPanel().validate();
            ContentMainJFrame.getContentPanel().getParent().validate();
            ContentMainJFrame.getSeparadorPanel().add(new SeparatorResultTestsActionJPanel());
            ContentMainJFrame.getSeparadorPanel().validate();
            ContentMainJFrame.getSeparadorPanel().getParent().validate();
        }
    }
}//GEN-LAST:event_ejecutarButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ejecutarButton;
    private javax.swing.JButton volverButton;
    // End of variables declaration//GEN-END:variables

}
