/*
 * TextInstancesTextAreaJPanel.java
 *
 * Created on 30 de julio de 2008, 18:03
 */

package code.google.com.p.ontologytesting.gui.tests;

import java.awt.Color;
import java.awt.Frame;
import javax.swing.WindowConstants;

/**
 *
 * @author  saruskas
 */
public class TestInstancesTextAreaJPanel extends javax.swing.JPanel {

    private AddComentJDialog frameComent;
    private Frame frame;
    private int posicion;
    private static int contadorRetClas=0;
    
    /** Creates new form TextInstancesTextAreaJPanel */
    public TestInstancesTextAreaJPanel() {
        initComponents();
        int pos = TestInstancesTextAreaJPanel.getContadorRetClas();
        this.setPosicion(pos);
        int cont = pos+1;
        frame = new Frame();
        TestInstancesTextAreaJPanel.setContadorRetClas(cont);
        frameComent = new AddComentJDialog(frame,true); 
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
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTextArea = new javax.swing.JTextArea();
        comentarioButton = new javax.swing.JButton();
        borrarButton = new javax.swing.JButton();
        duplicarButton = new javax.swing.JButton();

        queryTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                queryTextFieldMouseClicked(evt);
            }
        });

        resultTextArea.setColumns(20);
        resultTextArea.setRows(5);
        resultTextArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resultTextAreaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(resultTextArea);

        comentarioButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/comment_add.png"))); // NOI18N
        comentarioButton.setToolTipText("Añadir comentario");
        comentarioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comentarioButtonActionPerformed(evt);
            }
        });

        borrarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/user-trash.png"))); // NOI18N
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(queryTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comentarioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(borrarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(duplicarButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(queryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(borrarButton)
                    .addComponent(comentarioButton)
                    .addComponent(duplicarButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void comentarioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comentarioButtonActionPerformed
// TODO add your handling code here:
    frameComent.setVisible(true);
}//GEN-LAST:event_comentarioButtonActionPerformed

private void borrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarButtonActionPerformed
// TODO add your handling code here:
    this.setVisible(false);
    int finalPos=0;
    int p = this.getPosicion();
    int total = this.getParent().getComponentCount();
    for(int i=p+1;i<total;i++){
        TestInstancesTextAreaJPanel panel = (TestInstancesTextAreaJPanel) this.getParent().getComponent(i);
        int pos = panel.getPosicion();
        panel.setPosicion(pos-1);
        finalPos=pos-1;
    }
    TestInstancesTextAreaJPanel pa = new TestInstancesTextAreaJPanel();
    pa.setPosicion(finalPos+1);
    this.getParent().add(pa);
    this.getParent().remove(this);
}//GEN-LAST:event_borrarButtonActionPerformed

private void duplicarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_duplicarButtonActionPerformed
// TODO add your handling code here:
     int tam = this.getParent().getComponentCount();
     String query = this.getQuery();
     String result = this.getQueryResult();
     TestInstancesTextAreaJPanel panel = new TestInstancesTextAreaJPanel();
     panel.setQuery(query);
     panel.setQueryResult(result);
     panel.setComment(this.frameComent);
     int pos = this.getPosicion();
     panel.setPosicion(pos+1);
     if(pos+2==tam){
         this.getParent().add(panel);
     }else{
        this.getParent().add(panel, pos+2);
     }
     int total = this.getParent().getComponentCount();
     for(int i=pos+3;i<total;i++){
            TestInstancesTextAreaJPanel p = (TestInstancesTextAreaJPanel) this.getParent().getComponent(i);
            int po = p.getPosicion();
            p.setPosicion(po+1);
       }  
     this.getParent().validate();   
}//GEN-LAST:event_duplicarButtonActionPerformed

private void queryTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_queryTextFieldMouseClicked
// TODO add your handling code here:
    getQueryTextField().setForeground(Color.BLACK);
    
    int pos = this.getPosicion();
    int tamInst = this.getParent().getComponentCount();
    if(pos+2==tamInst){
         for(int i=0;i<9;i++){
            this.getParent().add(new TestInstancesTextAreaJPanel());
         }
    }
    this.getParent().getParent().validate();
}//GEN-LAST:event_queryTextFieldMouseClicked

private void resultTextAreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultTextAreaMouseClicked
// TODO add your handling code here:
    getQueryTextField().setForeground(Color.BLACK);
    
    int pos = this.getPosicion();
    int tamInst = this.getParent().getComponentCount();
    if(pos+2==tamInst){
         for(int i=0;i<9;i++){
            this.getParent().add(new TestInstancesTextAreaJPanel());
         }
    }
    this.getParent().getParent().validate();
}//GEN-LAST:event_resultTextAreaMouseClicked

public boolean consultaCompletaEnAyuda(){
    if((!this.getQuery().equals("") && this.getQueryResult().equals("")) ||
        this.getQuery().equals("") && !this.getQueryResult().equals("")){
        return false;
    }else{
        return true;
    }
}

public boolean panelVacio(){
    if(this.getQuery().equals("") && this.getQueryResult().equals("")){
        return true;
    }else{
        return false;
    }
}

public AddComentJDialog getComment() {
    return frameComent;
}

public void setComment(AddComentJDialog comment) {
    this.frameComent = comment;
}

public void setQuery(String query){
    getQueryTextField().setText(query);
}

public String getQuery(){
    return getQueryTextField().getText();
}

public void setQueryResult(String result){
    getResultTextArea().setText(result);
}

public String getQueryResult(){
    return getResultTextArea().getText();
}

public javax.swing.JTextField getQueryTextField() {
    return queryTextField;
}

public javax.swing.JTextArea getResultTextArea() {
    return resultTextArea;
}

public int getPosicion() {
    return posicion;
}

public void setPosicion(int posicion) {
    this.posicion = posicion;
}

public static int getContadorRetClas() {
    return contadorRetClas;
}

public static void setContadorRetClas(int aContadorRetClas) {
    contadorRetClas = aContadorRetClas;
}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton borrarButton;
    private javax.swing.JButton comentarioButton;
    private javax.swing.JButton duplicarButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField queryTextField;
    private javax.swing.JTextArea resultTextArea;
    // End of variables declaration//GEN-END:variables

}

