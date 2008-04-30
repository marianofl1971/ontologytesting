/*
 * addQuerySPARQLJPanel.java
 *
 * Created on 24 de abril de 2008, 10:53
 */

package ontologytestinggui;

import java.awt.Component;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author  sara_garcia
 */
public class addQuerySPARQLJPanel extends javax.swing.JPanel {

    public Component frame;
    public saveCommentJDialog addcomment = new saveCommentJDialog(new JFrame(),true);
    public JFileChooser filechooser = new JFileChooser();
    
    /** Creates new form addQuerySPARQLJPanel */
    public addQuerySPARQLJPanel() {
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

        jLabel23 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        queryTextArea = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();
        addComentButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();

        jLabel23.setText("Introduzca la consulta SPARQL que desee realizar:");

        queryTextArea.setColumns(20);
        queryTextArea.setRows(5);
        jScrollPane1.setViewportView(queryTextArea);

        jButton4.setText("Limpiar");

        jButton2.setText("Ejecutar");

        helpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagesgui/log.gif"))); // NOI18N
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        addComentButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagesgui/comment.png"))); // NOI18N
        addComentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addComentButtonActionPerformed(evt);
            }
        });

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagesgui/save.png"))); // NOI18N
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 344, Short.MAX_VALUE)
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addComentButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(helpButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addComentButton, helpButton, saveButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(35, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(helpButton)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(saveButton)
                                .addComponent(addComentButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addComentButton, helpButton, saveButton});

    }// </editor-fold>//GEN-END:initComponents

private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
// TODO add your handling code here:
    saveToFile();
}//GEN-LAST:event_saveButtonActionPerformed

private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_helpButtonActionPerformed

private void addComentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addComentButtonActionPerformed
// TODO add your handling code here:
    addcomment.setTitle("Comentar consulta SPARQL");
    addcomment.setVisible(true);
}//GEN-LAST:event_addComentButtonActionPerformed

private void saveToFile(){
    int returnVal = filechooser.showSaveDialog(frame);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = filechooser.getSelectedFile();
        file.setWritable(true);
        try
        {
            FileWriter fichero = new FileWriter(file.getPath());
            PrintWriter pw = new PrintWriter(fichero);
            //Escribir las instancias
            //pw.println("Linea ");
            pw.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        //log.append("Saving: " + file.getName() + ".");
    } else {
        //log.append("Save command cancelled by user.");
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addComentButton;
    private javax.swing.JButton helpButton;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea queryTextArea;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JTextArea getJTextArea1() {
        return queryTextArea;
    }

    public void setQuerySPARQLTextField(String querySPARQL){
        queryTextArea.setText(querySPARQL);
    }

}
