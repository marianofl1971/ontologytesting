/*
 * TestInstancesQueryJPanel.java
 *
 * Created on 19 de mayo de 2008, 18:59
 */

package code.google.com.p.ontologytesting.gui;

import code.google.com.p.ontologytesting.model.Auxiliar;
import java.awt.Color;
import java.awt.Frame;
import javax.swing.WindowConstants;

/**
 *
 * @author  Saruskas
 */
public class TestInstancesQueryJPanel extends javax.swing.JPanel {

    private AddComentJDialog frameComent;
    private Frame frame;
    private int posicion;
    
    
    /** Creates new form TestInstancesQueryJPanel */
    public TestInstancesQueryJPanel() {
        initComponents();
        int pos = Auxiliar.getContadorReal();
        this.setPosicion(pos);
        int cont = pos+1;
        Auxiliar.setContadorReal(cont);
        frameComent = new AddComentJDialog(frame,true); 
        frameComent.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }
    
    public TestInstancesQueryJPanel(String query, String result, String coment) {
        initComponents();
        
        queryTextField.setText(query);
        resultTextField.setText(result);
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
        comentarioButton = new javax.swing.JButton();
        borrarButton = new javax.swing.JButton();
        duplicarButton = new javax.swing.JButton();
        resultTextField = new javax.swing.JTextField();

        queryTextField.setToolTipText("Consulta");
        queryTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                queryTextFieldMouseClicked(evt);
            }
        });

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

        resultTextField.setToolTipText("Resultado esperado");
        resultTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resultTextFieldMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(queryTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 214, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(54, 54, 54)
                .add(resultTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 157, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 50, Short.MAX_VALUE)
                .add(comentarioButton)
                .add(39, 39, 39)
                .add(borrarButton)
                .add(33, 33, 33)
                .add(duplicarButton)
                .add(20, 20, 20))
        );

        layout.linkSize(new java.awt.Component[] {queryTextField, resultTextField}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(resultTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(queryTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(comentarioButton)
                    .add(borrarButton)
                    .add(duplicarButton))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(new java.awt.Component[] {queryTextField, resultTextField}, org.jdesktop.layout.GroupLayout.VERTICAL);

    }// </editor-fold>//GEN-END:initComponents

private void comentarioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comentarioButtonActionPerformed
// TODO add your handling code here:
    System.out.println("Pos "+this.getPosicion());
    frameComent.setVisible(true);
}//GEN-LAST:event_comentarioButtonActionPerformed

private void borrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarButtonActionPerformed
// TODO add your handling code here:
   this.setVisible(false);
   int tab = GroupTestsJPanel.getSelectedTabed();
   if(tab == 2){
       int finalPos=0;
       int p = this.getPosicion();
       int total = GroupTestsJPanel.getRealAyudaPanel().getComponentCount();
       for(int i=p+1;i<total;i++){
            TestInstancesQueryJPanel panel = (TestInstancesQueryJPanel) GroupTestsJPanel.getRealAyudaPanel().getComponent(i);
            int pos = panel.getPosicion();
            panel.setPosicion(pos-1);
            finalPos=pos-1;
       }
       GroupTestsJPanel.getRealAyudaPanel().remove(this);
       TestInstancesQueryJPanel pa = new TestInstancesQueryJPanel();
       pa.setPosicion(finalPos+1);
       GroupTestsJPanel.getRealAyudaPanel().add(pa);
   }
}//GEN-LAST:event_borrarButtonActionPerformed

private void duplicarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_duplicarButtonActionPerformed
// TODO add your handling code here:
     int tab = GroupTestsJPanel.getSelectedTabed();
     int pos = this.getPosicion();
     if(tab==2){
         int tam = GroupTestsJPanel.getRealAyudaPanel().getComponentCount();
         String query = this.getQuery();
         String result = this.getQueryResult();
         TestInstancesQueryJPanel panel = new TestInstancesQueryJPanel();
         panel.setQuery(query);
         panel.setQueryResult(result);
         panel.setComment(this.frameComent);
         panel.setPosicion(pos+1);
         if(pos+2==tam){
             GroupTestsJPanel.getRealAyudaPanel().add(panel);
         }else{
            GroupTestsJPanel.getRealAyudaPanel().add(panel, pos+2);
         }
         int total = GroupTestsJPanel.getRealAyudaPanel().getComponentCount();
         for(int i=pos+3;i<total;i++){
                TestInstancesQueryJPanel p = (TestInstancesQueryJPanel) GroupTestsJPanel.getRealAyudaPanel().getComponent(i);
                int po = p.getPosicion();
                p.setPosicion(po+1);
           }  
         GroupTestsJPanel.getRealAyudaPanel().getParent().validate();
         GroupTestsJPanel.getRealAyudaPanel().validate();
    }
}//GEN-LAST:event_duplicarButtonActionPerformed

private void queryTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_queryTextFieldMouseClicked
// TODO add your handling code here:
    getQueryTextField().setForeground(Color.BLACK);
    
    int pos = this.getPosicion();
    int tamReal = GroupTestsJPanel.getRealAyudaPanel().getComponentCount();
    int tab = GroupTestsJPanel.getSelectedTabed();
    if(tab == 2){
        if(pos+2==tamReal){
             for(int i=0;i<9;i++){
                GroupTestsJPanel.getRealAyudaPanel().add(new TestInstancesQueryJPanel());
             }
        }
        GroupTestsJPanel.getRealAyudaPanel().getParent().validate();
    }
}//GEN-LAST:event_queryTextFieldMouseClicked

private void resultTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultTextFieldMouseClicked
// TODO add your handling code here:
     getResultTextField().setForeground(Color.BLACK);
     
    int pos = this.getPosicion();
    int tamReal = GroupTestsJPanel.getRealAyudaPanel().getComponentCount();
    int tab = GroupTestsJPanel.getSelectedTabed();
    if(tab == 2){
        if(pos+2==tamReal){
             for(int i=0;i<9;i++){
                GroupTestsJPanel.getRealAyudaPanel().add(new TestInstancesQueryJPanel());
             }
        }
        GroupTestsJPanel.getRealAyudaPanel().getParent().validate();
    }
}//GEN-LAST:event_resultTextFieldMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton borrarButton;
    private javax.swing.JButton comentarioButton;
    private javax.swing.JButton duplicarButton;
    private javax.swing.JTextField queryTextField;
    private javax.swing.JTextField resultTextField;
    // End of variables declaration//GEN-END:variables

    public void setQuery(String query){
        getQueryTextField().setText(query);
    }
    
    public String getQuery(){
        return getQueryTextField().getText();
    }

    public void setQueryResult(String result){
        getResultTextField().setText(result);
    }
    
    public String getQueryResult(){
        return getResultTextField().getText();
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

    public javax.swing.JTextField getResultTextField() {
        return resultTextField;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}
