/*
 * AbrirProyectoJDialog.java
 *
 * Created on 24 de octubre de 2008, 12:52
 */

package code.google.com.p.ontologytesting.gui.auxiliarpanels;

import code.google.com.p.ontologytesting.gui.*;
import code.google.com.p.ontologytesting.gui.auxiliarclasess.FileChooserSelector;
import code.google.com.p.ontologytesting.gui.auxiliarclasess.LoadOntology;
import code.google.com.p.ontologytesting.gui.auxiliarclasess.ProgressListener;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.InvalidOntologyException;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.Reasoner;
import code.google.com.p.ontologytesting.persistence.*;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author  sara.garcia
 */
public class AbrirProyectoJDialog extends javax.swing.JDialog{

    private IOManagerImplementation persist = new IOManagerImplementation();
    private boolean proyectoCargado = false;
    private FileChooserSelector utils;
    private ProgressControlJDialog progres;
    private Reasoner jena = new Reasoner();
    
    /** Creates new form AbrirProyectoJDialog */
    public AbrirProyectoJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        aceptarButton.requestFocus();
        utils = new FileChooserSelector();
        this.setTitle(java.util.ResourceBundle.getBundle(Configuration.getInstance().cargarDriver().getProperty("IDIOMA")).getString("Ubicación_de_la_Ontología_y_el_Namespace"));
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(MainApplicationJFrame.getInstance());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ubicacionFisicaTextField = new javax.swing.JTextField();
        examinarButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        namespaceTextField = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();
        aceptarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText(java.util.ResourceBundle.getBundle(Configuration.getInstance().cargarDriver().getProperty("IDIOMA")).getString("Datos_del_Proyecto"));

        jLabel2.setText(java.util.ResourceBundle.getBundle(Configuration.getInstance().cargarDriver().getProperty("IDIOMA")).getString("Compruebe_que_los_siguientes_datos_son_correctos._En_caso_contrario,_realice_las_modificaciones_oportunas:"));

        jLabel3.setText(java.util.ResourceBundle.getBundle(Configuration.getInstance().cargarDriver().getProperty("IDIOMA")).getString("Ubicación_de_la_ontología_con_la_que_va_a_trabajar:"));

        examinarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/folder_explore.png"))); // NOI18N
        examinarButton.setText(java.util.ResourceBundle.getBundle(Configuration.getInstance().cargarDriver().getProperty("IDIOMA")).getString("Examinar"));
        examinarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                examinarButtonActionPerformed(evt);
            }
        });

        jLabel4.setText(java.util.ResourceBundle.getBundle(Configuration.getInstance().cargarDriver().getProperty("IDIOMA")).getString("Namespace_de_la_ontologia:"));

        cancelButton.setText(java.util.ResourceBundle.getBundle(Configuration.getInstance().cargarDriver().getProperty("IDIOMA")).getString("Cancelar"));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        aceptarButton.setText(java.util.ResourceBundle.getBundle(Configuration.getInstance().cargarDriver().getProperty("IDIOMA")).getString("Aceptar"));
        aceptarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namespaceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ubicacionFisicaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(examinarButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(aceptarButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(cancelButton)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ubicacionFisicaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(examinarButton))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(namespaceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(aceptarButton))
                .addContainerGap())
        );

        pack();
    }

private void aceptarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarButtonActionPerformed
// TODO add your handling code here:
    jena.getReasoner();
    if(this.getUbicacionFisica().equals("") || this.getNamespaceText().equals("")){
            JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"Todos los campos son obligatorios",                                                  
            "Warning Message",JOptionPane.WARNING_MESSAGE); 
    }else{
        try{
            if(jena.isCargado()==false){
                JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"Error en la Aplicación","Error Message",JOptionPane.ERROR_MESSAGE);
            }else{
                boolean res = persist.loadProject(this.getUbicacionFisica(), this.getNamespaceText());
                if(res==true){
                    LoadOntology loadOnto = new LoadOntology(this.getUbicacionFisica(),this);
                    setProgres(new ProgressControlJDialog(loadOnto));
                    JProgressBar progresBar = getProgres().getProgressBar();
                    loadOnto.addPropertyChangeListener(new ProgressListener(progresBar,getProgres(),false));
                    progresBar.setIndeterminate(true);
                    loadOnto.execute();
                    getProgres().setVisible(true); 
                }
            }
        }catch (InvalidOntologyException ex){
            JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"No se pudo crear el proyecto. La ontologia introducida no es valida.\n" +
            "Introduzca una ontologia valida.","Error Message",JOptionPane.ERROR_MESSAGE);
        }
    }
}//GEN-LAST:event_aceptarButtonActionPerformed

private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
// TODO add your handling code here:
    this.setVisible(false);
}//GEN-LAST:event_cancelButtonActionPerformed

private void examinarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_examinarButtonActionPerformed
// TODO add your handling code here:
    if(utils.fileChooser(true, true, false)==true){
        this.getUbicacionFisicaTextField().setText(FileChooserSelector.getPathSelected());
    }
}//GEN-LAST:event_examinarButtonActionPerformed
    
    public String getNamespaceText() {
        return namespaceTextField.getText();
    }

    public void setNamespaceText(String namespaceTextField) {
        this.namespaceTextField.setText(namespaceTextField);
    }

    public String getUbicacionFisica() {
        return ubicacionFisicaTextField.getText();
    }
    
    public JTextField getUbicacionFisicaTextField() {
        return ubicacionFisicaTextField;
    }

    public void setUbicacionFisica(String ubicacionFisicaTextField) {
        this.ubicacionFisicaTextField.setText(ubicacionFisicaTextField);
    }
    
    public boolean isProyectoCargado() {
        return proyectoCargado;
    }

    public void setProyectoCargado(boolean proyectoCargado) {
        this.proyectoCargado = proyectoCargado;
    }
    
    public ProgressControlJDialog getProgres() {
        return progres;
    }

    public void setProgres(ProgressControlJDialog progres) {
        this.progres = progres;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton examinarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField namespaceTextField;
    private javax.swing.JTextField ubicacionFisicaTextField;
    // End of variables declaration//GEN-END:variables

}
