/*
 * createInstancesJPanel.java
 *
 * Created on 24 de abril de 2008, 11:03
 */

package ontologytestinggui;

/**
 *
 * @author  sara_garcia
 */
public class createInstancesJPanel extends javax.swing.JPanel {

    /** Creates new form createInstancesJPanel */
    public createInstancesJPanel() {
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

        classTextField = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        selClassCheckBox = new javax.swing.JCheckBox();

        jButton2.setText("Duplicar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(classTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(35, 35, 35)
                .addComponent(selClassCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(classTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(selClassCheckBox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField classTextField;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox selClassCheckBox;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JTextField getClassTextField() {
        return classTextField;
    }

    public void setClassTextField(javax.swing.JTextField classTextField) {
        this.classTextField = classTextField;
    }

    public javax.swing.JCheckBox getSelClassCheckBox() {
        return selClassCheckBox;
    }

    public void setSelClassCheckBox(javax.swing.JCheckBox selClassCheckBox) {
        this.selClassCheckBox = selClassCheckBox;
    }

}
