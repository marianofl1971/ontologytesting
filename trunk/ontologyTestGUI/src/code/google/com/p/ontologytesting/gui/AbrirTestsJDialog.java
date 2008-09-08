/*
 * AbrirTestsJDialog.java
 *
 * Created on 14 de julio de 2008, 13:48
 */

package code.google.com.p.ontologytesting.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.WindowConstants;

/**
 *
 * @author  sara.garcia
 */
public class AbrirTestsJDialog extends javax.swing.JDialog {

    private File dir;
    private ListaFicheros listaFicheros;
    
    /** Creates new form AbrirTestsJDialog */
    public AbrirTestsJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setModal(false);
        this.setTitle("Descripción Tests");
        
        AddInstancesJPanel.setPathFichero(Configuration.getPathTestSimples());
        dir = new File(Configuration.getPathTestSimples());
        AddInstancesJPanel.setFicheros(dir.list());

        ficherosComboBox.addItem("Simple Tests");
        ficherosComboBox.addItem("Instancias");
        ficherosComboBox.addItem("Sparql Tests");
        ficherosComboBox.setSelectedIndex(0);
        
        contentPanel.setLayout(new FlowLayout());
        listaFicheros = new ListaFicheros(AddInstancesJPanel.getFicheros());
        contentPanel.add(listaFicheros.getSplitPane());
        
        ficherosComboBox.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
            
            if( getFicherosComboBox()==0){
                dir = new File(Configuration.getPathTestSimples());
                AddInstancesJPanel.setPathFichero(Configuration.getPathTestSimples());  
            }else if(getFicherosComboBox()==1){
                dir = new File(Configuration.getPathInstancias());
                AddInstancesJPanel.setPathFichero(Configuration.getPathInstancias());   
            }else if(getFicherosComboBox()==2){
                dir = new File(Configuration.getPathTestSparql());
                AddInstancesJPanel.setPathFichero(Configuration.getPathTestSparql());
            }
            AddInstancesJPanel.setFicheros(dir.list());
            
            contentPanel.setLayout(new FlowLayout());
            contentPanel.remove(listaFicheros.getSplitPane());
            contentPanel.getParent().validate();
            
            listaFicheros = new ListaFicheros(AddInstancesJPanel.getFicheros());
            contentPanel.add(listaFicheros.getSplitPane());
            contentPanel.getParent().validate();      
            
	}
	});
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
        contentPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        ficherosComboBox = new javax.swing.JComboBox();
        abrirButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 438, Short.MAX_VALUE)
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 233, Short.MAX_VALUE)
        );

        jLabel2.setText("Seleccione el test para ver su descripción y abrirlo:");

        ficherosComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ficherosComboBoxActionPerformed(evt);
            }
        });

        abrirButton.setText("Abrir");
        abrirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirButtonActionPerformed(evt);
            }
        });

        cancelarButton.setText("Salir");
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ficherosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(358, Short.MAX_VALUE)
                .addComponent(cancelarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(abrirButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ficherosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(abrirButton)
                    .addComponent(cancelarButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void ficherosComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ficherosComboBoxActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_ficherosComboBoxActionPerformed

private void abrirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirButtonActionPerformed
// TODO add your handling code here:  
    VistaTestJFrame vistaDialog = new VistaTestJFrame(ListaFicheros.getPathFicheroAbrir());
    vistaDialog.setTitle(ListaFicheros.getPathFicheroAbrir());
    vistaDialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    vistaDialog.setVisible(true);
}//GEN-LAST:event_abrirButtonActionPerformed

private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
// TODO add your handling code here:
    this.setVisible(false);
}//GEN-LAST:event_cancelarButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abrirButton;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JPanel contentPanel;
    private static javax.swing.JComboBox ficherosComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

    public static int getFicherosComboBox() {
        return ficherosComboBox.getSelectedIndex();
    }
}
