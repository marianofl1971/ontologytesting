/*
 * MainJPanel.java
 *
 * Created on 25 de junio de 2008, 1:27
 */

package ontologytestgui;

import java.awt.Component;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

/**
 *
 * @author  sara.garcia
 */
public class MainJPanel extends javax.swing.JPanel {

    public static void setExistsTestsCheckBox(Boolean aExistsTestsCheckBox) {
        newTestChekBox.setSelected(aExistsTestsCheckBox);
    }
    public static void setNewInstancesCheckBox(Boolean aNewInstancesCheckBox) {
        newInstancesCheckBox.setSelected(aNewInstancesCheckBox);
    }
    public static void setNewTestCheckBox(Boolean aNewTestCheckBox) {
        newTestChekBox.setSelected(aNewTestCheckBox);
    }
    public static void setSparqlCheckBox(Boolean aSparqlCheckBox) {
        sparqlCheckBox.setSelected(aSparqlCheckBox);
    }
    public static String getFisicalOntologyTextField() {
        return fisicalOntologyTextField.getText();
    }
    public static String getNamespaceOntologyTextField() {
        return namespaceOntologyTextField.getText();
    }
    public static boolean isSeleccionado() {
        return seleccionado;
    }
    public static void setSeleccionado(boolean aSeleccionado) {
        seleccionado = aSeleccionado;
    }
    public static javax.swing.JTextField getPathTestTextField() {
        return pathTestTextField;
    }
    public static String getPath(){
        return pathTestTextField.getText();
    }
    private JFileChooser filechooser;
    private Component frame;
    public static boolean seleccionado;

    /** Creates new form MainJPanel */
    public MainJPanel() {
        initComponents();
        setSeleccionado(true);
        ButtonGroup group = new ButtonGroup();
        group.add(newTestChekBox);
        group.add(existsTestsCheckBox);
        addTestPanel.setEnabled(false);
        namespaceOntologyTextField.setText("http://www.owl-ontologies.com/family.owl#");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fisicalOntologyTextField = new javax.swing.JTextField();
        examinarFisicalButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        namespaceOntologyTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        newTestChekBox = new javax.swing.JRadioButton();
        existsTestsCheckBox = new javax.swing.JRadioButton();
        addTestPanel = new javax.swing.JPanel();
        abrirTestLabel = new javax.swing.JLabel();
        pathTestTextField = new javax.swing.JTextField();
        examinarTestButton = new javax.swing.JButton();
        newInstancesCheckBox = new javax.swing.JCheckBox();
        sparqlCheckBox = new javax.swing.JCheckBox();

        jLabel1.setText("¡¡BIENVENIDO AL EVALUADOR DE ONTOLOGÍAS!!");

        jLabel2.setText("Rellene los siguientes campos:");

        jLabel3.setText("Introduzca la ubicación de la ontología a evaluar.");

        examinarFisicalButton.setText("Examinar");
        examinarFisicalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                examinarFisicalButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Introduzca el namespace de la misma:");

        jLabel5.setText("Seleccione las acciones que desea realizar:");

        newTestChekBox.setSelected(true);
        newTestChekBox.setText("Crear tests");
        newTestChekBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newTestChekBoxActionPerformed(evt);
            }
        });

        existsTestsCheckBox.setText("Trabajar con test existentes");
        existsTestsCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                existsTestsCheckBoxActionPerformed(evt);
            }
        });

        abrirTestLabel.setText("Seleccione el test:");
        abrirTestLabel.setEnabled(false);

        pathTestTextField.setEnabled(false);

        examinarTestButton.setText("Examinar");
        examinarTestButton.setEnabled(false);
        examinarTestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                examinarTestButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addTestPanelLayout = new javax.swing.GroupLayout(addTestPanel);
        addTestPanel.setLayout(addTestPanelLayout);
        addTestPanelLayout.setHorizontalGroup(
            addTestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addTestPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(addTestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addTestPanelLayout.createSequentialGroup()
                        .addComponent(pathTestTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(examinarTestButton))
                    .addComponent(abrirTestLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        addTestPanelLayout.setVerticalGroup(
            addTestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addTestPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(abrirTestLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addTestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pathTestTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(examinarTestButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        newInstancesCheckBox.setText("Crear conjunto de instancias");

        sparqlCheckBox.setText("Realizar consultas SPARQL");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sparqlCheckBox)
                    .addComponent(newInstancesCheckBox)
                    .addComponent(addTestPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(existsTestsCheckBox)
                    .addComponent(newTestChekBox)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(namespaceOntologyTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fisicalOntologyTextField, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addComponent(examinarFisicalButton))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(146, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fisicalOntologyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(examinarFisicalButton))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(namespaceOntologyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(newTestChekBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(existsTestsCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addTestPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newInstancesCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sparqlCheckBox)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("card2");
    }// </editor-fold>//GEN-END:initComponents
  
private void existsTestsCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_existsTestsCheckBoxActionPerformed
// TODO add your handling code here:
    addTestPanel.setEnabled(true);
    abrirTestLabel.setEnabled(true);
    pathTestTextField.setEnabled(true);
    examinarTestButton.setEnabled(true);
}//GEN-LAST:event_existsTestsCheckBoxActionPerformed

private void newTestChekBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newTestChekBoxActionPerformed
// TODO add your handling code here:
    addTestPanel.setEnabled(false);
    abrirTestLabel.setEnabled(false);
    pathTestTextField.setEnabled(false);
    examinarTestButton.setEnabled(false);    
}//GEN-LAST:event_newTestChekBoxActionPerformed

private void examinarFisicalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_examinarFisicalButtonActionPerformed
// TODO add your handling code here:
    openFile(fisicalOntologyTextField);
}//GEN-LAST:event_examinarFisicalButtonActionPerformed

private void examinarTestButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_examinarTestButtonActionPerformed
// TODO add your handling code here:
    openFile(pathTestTextField);
}//GEN-LAST:event_examinarTestButtonActionPerformed


private void openFile(JTextField textfield){
      filechooser = new JFileChooser("./");
      int option = filechooser.showOpenDialog(frame);
      if (option == JFileChooser.APPROVE_OPTION) {
          File selectedFile = filechooser.getSelectedFile();
          textfield.setText(selectedFile.getPath());
          String nameFile = selectedFile.getName();
      }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JLabel abrirTestLabel;
    private javax.swing.JPanel addTestPanel;
    private javax.swing.JButton examinarFisicalButton;
    private javax.swing.JButton examinarTestButton;
    private static javax.swing.JRadioButton existsTestsCheckBox;
    private static javax.swing.JTextField fisicalOntologyTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private static javax.swing.JTextField namespaceOntologyTextField;
    private static javax.swing.JCheckBox newInstancesCheckBox;
    private static javax.swing.JRadioButton newTestChekBox;
    private static javax.swing.JTextField pathTestTextField;
    private static javax.swing.JCheckBox sparqlCheckBox;
    // End of variables declaration//GEN-END:variables

    public static boolean getExistsTestsState() {
        return existsTestsCheckBox.isSelected();
    }

    public static boolean getNewInstancesState() {
        return newInstancesCheckBox.isSelected();
    }

    public static boolean getNewTestState() {
        return newTestChekBox.isSelected();
    }

    public static boolean getSparqlState() {
        return sparqlCheckBox.isSelected();
    }

}