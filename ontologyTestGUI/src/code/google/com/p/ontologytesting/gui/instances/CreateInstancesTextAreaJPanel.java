/*
 * CreateInstancesTextAreaJPanel.java
 *
 * Created on 1 de agosto de 2008, 12:22
 */

package code.google.com.p.ontologytesting.gui.instances;

import code.google.com.p.ontologytesting.gui.Configuration;
import java.awt.Color;

/**
 *
 * @author  sara.garcia
 */
public class CreateInstancesTextAreaJPanel extends javax.swing.JPanel {

    /** Creates new form CreateInstancesTextAreaJPanel */
    public CreateInstancesTextAreaJPanel() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        claseTextArea = new javax.swing.JTextArea();
        jScrollPane17 = new javax.swing.JScrollPane();
        propiedadTextArea = new javax.swing.JTextArea();

        jLabel1.setText(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA")).getString("______________INSTANCIAS_DE_CLASE______________________________________________INSTANCIAS_DE_PROPIEDAD__")); // NOI18N

        claseTextArea.setColumns(20);
        claseTextArea.setFont(new java.awt.Font("Arial", 0, 13));
        claseTextArea.setRows(5);
        claseTextArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                claseTextAreaMouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(claseTextArea);

        propiedadTextArea.setColumns(20);
        propiedadTextArea.setFont(new java.awt.Font("Arial", 0, 13));
        propiedadTextArea.setRows(5);
        propiedadTextArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                propiedadTextAreaMouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(propiedadTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane16, jScrollPane17});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane16, jScrollPane17});

    }

private void claseTextAreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_claseTextAreaMouseClicked
// TODO add your handling code here:
    getClaseArea().setForeground(Color.BLACK);
}//GEN-LAST:event_claseTextAreaMouseClicked

private void propiedadTextAreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_propiedadTextAreaMouseClicked
// TODO add your handling code here:
    getPropiedadArea().setForeground(Color.BLACK);
}//GEN-LAST:event_propiedadTextAreaMouseClicked

public String getClaseTextArea() {
    return getClaseArea().getText();
}

public void setClaseTextArea(String aclaseTextArea) {
    getClaseArea().setText(aclaseTextArea);
}

public String getPropiedadTextArea() {
    return getPropiedadArea().getText();
}

public void setPropiedadTextArea(String apropiedadTextArea) {
    getPropiedadArea().setText(apropiedadTextArea);
}

public javax.swing.JTextArea getClaseArea() {
    return claseTextArea;
}

public void setClaseArea(javax.swing.JTextArea claseTextArea) {
    this.claseTextArea = claseTextArea;
}

public javax.swing.JTextArea getPropiedadArea() {
    return propiedadTextArea;
}

public void setPropiedadTextArea(javax.swing.JTextArea propiedadTextArea) {
    this.propiedadTextArea = propiedadTextArea;
}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea claseTextArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JTextArea propiedadTextArea;
    // End of variables declaration//GEN-END:variables

}
