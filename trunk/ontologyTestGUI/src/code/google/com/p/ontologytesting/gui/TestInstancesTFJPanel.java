/*
 * TestInstancesTFJPanel.java
 *
 * Created on 19 de mayo de 2008, 18:49
 */

package code.google.com.p.ontologytesting.gui;

import java.awt.Color;
import java.awt.Frame;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.WindowConstants;

/**
 *
 * @author  Saruskas
 */
public class TestInstancesTFJPanel extends javax.swing.JPanel{

    private AddComentJDialog frameComent;
    private Frame frame;
    private int posicion;
    private static ArrayList<TestInstancesTFJPanel> listaContenido;
    
    /** Creates new form TestInstancesTFJPanel */
    public TestInstancesTFJPanel(int i) {
        initComponents();
        posicion = i;
        ButtonGroup group = new ButtonGroup();
        group.add(trueRadioButton);
        group.add(falseRadioButton);
        frameComent = new AddComentJDialog(frame,true); 
        frameComent.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }
    
    public TestInstancesTFJPanel() {
        initComponents();
        ButtonGroup group = new ButtonGroup();
        group.add(trueRadioButton);
        group.add(falseRadioButton);
        frameComent = new AddComentJDialog(frame,true); 
        frameComent.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }
    
    public TestInstancesTFJPanel(String query,String result,String coment) {
        initComponents();
        ButtonGroup group = new ButtonGroup();
        group.add(trueRadioButton);
        group.add(falseRadioButton);
        if(result.equals("true")){
            trueRadioButton.setSelected(true);
        }else{
            falseRadioButton.setSelected(true);
        }
        queryTextField.setText(query);
        frameComent = new AddComentJDialog(frame,true); 
        frameComent.setComent(coment);
        frameComent.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        queryTextField = new javax.swing.JTextField();
        trueRadioButton = new javax.swing.JRadioButton();
        falseRadioButton = new javax.swing.JRadioButton();
        comentarioButton = new javax.swing.JButton();
        borrarButton = new javax.swing.JButton();
        duplicarButton = new javax.swing.JButton();

        queryTextField.setToolTipText("Consulta");
        queryTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                queryTextFieldMouseClicked(evt);
            }
        });

        trueRadioButton.setText("True");

        falseRadioButton.setText("False");

        comentarioButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/comment_add.png"))); // NOI18N
        comentarioButton.setText("Comentario");
        comentarioButton.setToolTipText("Añadir comentario");
        comentarioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comentarioButtonActionPerformed(evt);
            }
        });

        borrarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/delete.png"))); // NOI18N
        borrarButton.setText("Borrar");
        borrarButton.setToolTipText("Borrar");
        borrarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarButtonActionPerformed(evt);
            }
        });

        duplicarButton.setText("Duplicar");
        duplicarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                duplicarButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(queryTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 202, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(34, 34, 34)
                .add(trueRadioButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(falseRadioButton)
                .add(40, 40, 40)
                .add(comentarioButton)
                .add(30, 30, 30)
                .add(borrarButton)
                .add(32, 32, 32)
                .add(duplicarButton)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(queryTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(trueRadioButton)
                    .add(falseRadioButton)
                    .add(comentarioButton)
                    .add(borrarButton)
                    .add(duplicarButton))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void comentarioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comentarioButtonActionPerformed
// TODO add your handling code here:
    frameComent.setVisible(true);
}//GEN-LAST:event_comentarioButtonActionPerformed

private void borrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarButtonActionPerformed
// TODO add your handling code here:
    this.setVisible(false);
    int sel = GroupTestsJPanel.getSelectedTabed();
    if(sel==0){
        GroupTestsJPanel.getInstAyudaPanel().remove(this);
        GroupTestsJPanel.getInstAyudaPanel().add(new TestInstancesTFJPanel());
    }else if(sel==3){
        GroupTestsJPanel.getSatAyudaPanel().remove(this);
        GroupTestsJPanel.getSatAyudaPanel().add(new TestInstancesTFJPanel());
    }
}//GEN-LAST:event_borrarButtonActionPerformed

private void duplicarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_duplicarButtonActionPerformed
// TODO add your handling code here:
    listaContenido = new ArrayList<TestInstancesTFJPanel>();
    int sel = GroupTestsJPanel.getSelectedTabed();
    String query = this.getQuery();
    String result = this.isTestTrue();
    String testComent = this.frameComent.getComent();
    int count = GroupTestsJPanel.getInstAyudaPanel().getComponentCount();
    for(int i=1;i<count;i++){
        TestInstancesTFJPanel comp = (TestInstancesTFJPanel) GroupTestsJPanel.getInstAyudaPanel().getComponent(i);
        if(!comp.getQuery().equals("") && !comp.isTestFalse().equals(comp.isTestTrue())){
            listaContenido.add(i-1,comp);
        }
    }
    if(sel==0){
        TestInstancesTFJPanel sig = (TestInstancesTFJPanel) GroupTestsJPanel.getInstAyudaPanel().getComponent(this.getPosicion()+1);
        if(sig.getQuery().equals("") && sig.isTestFalse().equals(sig.isTestTrue())){
            sig.setQuery(query);
            if(result.equals("true")){
                sig.setTrueTest(true);
            }else{
                sig.setFalseTest(true);
            }
            sig.getComment().setComent(testComent);
            sig.setPosicion(posicion+1);
        }else{
            for(int j=0;j<listaContenido.size();j++){
                TestInstancesTFJPanel testComp = listaContenido.get(j);
                String q = testComp.getQuery();
                String r = testComp.isTestTrue();
                String t = testComp.frameComent.getComent();
                TestInstancesTFJPanel aux = (TestInstancesTFJPanel) GroupTestsJPanel.getInstAyudaPanel().getComponent(posicion+1);
                aux.setQuery(q);
                if(r.equals("true")){
                    aux.setTrueTest(true);
                }else{
                    aux.setFalseTest(true);
                }
                aux.getComment().setComent(t);
                aux.setPosicion(posicion++);
                posicion++;
            }
        }
    }else if(sel==3){
        //GroupTestsJPanel.getSatAyudaPanel().add(new TestInstancesTFJPanel(query,result,testComent));
    }
}//GEN-LAST:event_duplicarButtonActionPerformed

private void queryTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_queryTextFieldMouseClicked
// TODO add your handling code here:
    getQueryTextField().setForeground(Color.BLACK);
}//GEN-LAST:event_queryTextFieldMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton borrarButton;
    private javax.swing.JButton comentarioButton;
    private javax.swing.JButton duplicarButton;
    private javax.swing.JRadioButton falseRadioButton;
    private javax.swing.JTextField queryTextField;
    private javax.swing.JRadioButton trueRadioButton;
    // End of variables declaration//GEN-END:variables
    
    public void setQuery(String query){
        getQueryTextField().setText(query);
    }
    
    public String getQuery(){
        return getQueryTextField().getText();
    }

    public String isTestTrue() {
        if(trueRadioButton.isSelected()){
            return "true";
        }else{
            return "false";
        }
    }
    
    public String isTestFalse() {
        if(falseRadioButton.isSelected()){
            return "true";
        }else{
            return "false";
        }
    }    

    public void setTrueTest(boolean testT) {
        trueRadioButton.setSelected(testT);
    }

    public void setFalseTest(boolean testF) {
        falseRadioButton.setSelected(testF);
    }
    
    public AddComentJDialog getComment() {
        return frameComent;
    }

    public void setComment(AddComentJDialog comment) {
        this.frameComent = comment;
    }

    public javax.swing.JTextField getQueryTextField() {
        return queryTextField;
    }
    
    public int getPosicion() {
        return posicion;
    }
    
    public void setPosicion(int aPosicion) {
        posicion = aPosicion;
    }
}
