/*
 * AddOntologyJDialog.java
 *
 * Created on 12 de septiembre de 2008, 12:45
 */

package code.google.com.p.ontologytesting.guiNew;

import code.google.com.p.ontologytesting.model.jenainterfaz.*;;
import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author  saruskas
 */
public class AddOntologyJDialog extends javax.swing.JDialog {
    
    private JFileChooser filechooser;
    private Component frame;
    private boolean ontologiaAsociada=false;
    private JenaInterface jenaInterface;
    private Jena jena;

    /** Creates new form AddOntologyJDialog */
    public AddOntologyJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Añadir Ontología");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        fisicalOntologyTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        examinarFisicalButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        namespaceOntologyTextField = new javax.swing.JTextField();
        aceptarButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        fisicalOntologyTextField.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
                fisicalOntologyTextFieldAncestorResized(evt);
            }
        });

        jLabel3.setText("Introduzca la ubicación de la ontología a evaluar:");

        examinarFisicalButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/folder_explore.png"))); // NOI18N
        examinarFisicalButton.setText("Examinar");
        examinarFisicalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                examinarFisicalButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Introduzca el namespace de la misma:");

        aceptarButton.setText("Aceptar");
        aceptarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancelar");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                        .addGap(23, 23, 23))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(fisicalOntologyTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(examinarFisicalButton)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(namespaceOntologyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(134, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 415, Short.MAX_VALUE)
                        .addComponent(aceptarButton)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fisicalOntologyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(examinarFisicalButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(namespaceOntologyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptarButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 601, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 221, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void examinarFisicalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_examinarFisicalButtonActionPerformed
// TODO add your handling code here:
    openFile(getFisicalOntologyTextField());
}//GEN-LAST:event_examinarFisicalButtonActionPerformed

private void fisicalOntologyTextFieldAncestorResized(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_fisicalOntologyTextFieldAncestorResized
// TODO add your handling code here:
}//GEN-LAST:event_fisicalOntologyTextFieldAncestorResized

private void aceptarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarButtonActionPerformed
// TODO add your handling code here:
    String ns = getNamespaceOntology();
    String onto = getFisicalOntology();
    if(!ns.equals("") && !onto.equals("")){
        if(!ns.endsWith("#")){
            ns = ns.concat("#");
        }
        jenaInterface = new JenaInterface();
        jena = jenaInterface.getJena();
        try{
            jena.addReasoner(onto);
            MainApplication.getCollection().setOntology(onto);
            MainApplication.getCollection().setNamespace(ns);
            setOntologiaAsociada(true);
            this.setVisible(false);
            JOptionPane.showMessageDialog(this.getParent(),"La ontologia ha sido guardada",
            "Confirm Message",JOptionPane.INFORMATION_MESSAGE);
        }catch(ExceptionReadOntology ex){
            new ExceptionReadOntology("La ontologia introducida no es valida." +
            "\nSolo pueden realizarse tests sobre documentos owl consistentes");
        }
    }else{
        setOntologiaAsociada(false);
        JOptionPane.showMessageDialog(this,"Ambos campos son obligatorios",
        "Warning Message",JOptionPane.WARNING_MESSAGE);
    }
}//GEN-LAST:event_aceptarButtonActionPerformed

private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
// TODO add your handling code here:
    this.setVisible(false);//GEN-LAST:event_cancelButtonActionPerformed
}

private void openFile(JTextField textfield){
    filechooser = new JFileChooser(".\\data");
    int option = filechooser.showOpenDialog(frame);
    if (option == JFileChooser.APPROVE_OPTION) {
      File selectedFile = filechooser.getSelectedFile();
      textfield.setText(selectedFile.getAbsolutePath());
    }
}

public  JTextField getFisicalOntologyTextField() {
    return fisicalOntologyTextField;
}

public  String getNamespaceOntology() {
    return namespaceOntologyTextField.getText();
}

public  String getFisicalOntology() {
    return fisicalOntologyTextField.getText();
}

public void setFisicalOntology(String fisicalOnto) {
    fisicalOntologyTextField.setText(fisicalOnto);
}

public void setNamespaceOntology(String nsOnto) {
    fisicalOntologyTextField.setText(nsOnto);
}

public boolean getOntologiaAsociada() {
    return ontologiaAsociada;
}

public void setOntologiaAsociada(boolean ontologiaAsociada) {
    this.ontologiaAsociada = ontologiaAsociada;
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton examinarFisicalButton;
    private javax.swing.JTextField fisicalOntologyTextField;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField namespaceOntologyTextField;
    // End of variables declaration//GEN-END:variables

    

}
