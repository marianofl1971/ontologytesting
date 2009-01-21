package code.google.com.p.ontologytesting.gui.menupanels;

import code.google.com.p.ontologytesting.gui.Configuration;
import code.google.com.p.ontologytesting.gui.MainApplicationJFrame;
import code.google.com.p.ontologytesting.gui.auxiliarclasess.OpcionesMenu;
import code.google.com.p.ontologytesting.model.*;
import code.google.com.p.ontologytesting.persistence.IOManagerImplementation;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.Locale;

/**
 *
 * @author  sara.garcia
 */
public class AsociarInstanciasATestJDialog extends javax.swing.JDialog {

    private ListarTestsInstanciasJPanel listaFicheros;
    private Instancias instancias = new Instancias();
    private OpcionesMenu opMenu = new OpcionesMenu();
    private List<ScenarioTest> scenariosSeleccionados = new ArrayList<ScenarioTest>();
    private IOManagerImplementation persist = new IOManagerImplementation();
    private boolean isCancel=false,asociadas=false,guardar;
    
    /** Creates new form AsociarInstanciasATestJDialog */
    public AsociarInstanciasATestJDialog(Frame parent, boolean modal,Instancias inst,boolean guardar) {
        super(parent, modal);
        initComponents();
        aceptarButton.requestFocus();
        contentPanel.setLayout(new FlowLayout());  
        contentPanel.add(new ListarTestsInstanciasJPanel());
        contentPanel.getParent().validate();
        this.setGuardar(guardar);
        this.setInstancias(inst);
        this.setLocationRelativeTo(MainApplicationJFrame.getInstance());
        this.prepararImport(CollectionTest.getInstance().getScenariotest());
        this.setTitle(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Asociar_Instancias_a_Test"));
    }

    public void prepararImport(List<ScenarioTest> listaTests){   
        listaFicheros = new ListarTestsInstanciasJPanel(listaTests,null,true);
        contentPanel.remove(0);
        contentPanel.add(listaFicheros);
        contentPanel.getParent().validate(); 
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
        contentPanel = new javax.swing.JPanel();
        verTestButton = new javax.swing.JButton();
        aceptarButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Asociar_Instancias_-_Tests"));

        jLabel2.setText(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Seleccione_el_test_al_que_desea_importar_las_instancias:"));

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 475, Short.MAX_VALUE)
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 243, Short.MAX_VALUE)
        );

        verTestButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/document-print-preview.png"))); // NOI18N
        verTestButton.setText(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Ver_Test_Completo"));
        verTestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verTestButtonActionPerformed(evt);
            }
        });

        aceptarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/add.png"))); // NOI18N
        aceptarButton.setText(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Asociar"));
        aceptarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarButtonActionPerformed(evt);
            }
        });

        cancelarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/cancel.png"))); // NOI18N
        cancelarButton.setText(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Cancelar"));
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
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(verTestButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(aceptarButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cancelarButton)))))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(verTestButton)
                    .addComponent(aceptarButton)
                    .addComponent(cancelarButton))
                .addContainerGap())
        );

        pack();
    }

private void verTestButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verTestButtonActionPerformed
// TODO add your handling code here:
    if(listaFicheros.getScenarioSelect().esVacio()==false){
        SeeTestJDialog seeTest = opMenu.verTest(listaFicheros.getScenarioSelect());
        seeTest.setLocationRelativeTo(this);
        seeTest.setVisible(true);
    }
}//GEN-LAST:event_verTestButtonActionPerformed

private void aceptarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarButtonActionPerformed
// TODO add your handling code here:
    List<ScenarioTest> scenImp = listaFicheros.getListaDeScenarios();
    this.setScenariosSeleccionados(scenImp);
    if(scenImp.size()>0){
        for(int i=0;i<scenImp.size();i++){
            Instancias inst = new Instancias(this.getInstancias());
            scenImp.get(i).setInstancias(inst);
            asociadas = persist.replaceScenarioLocally(scenImp.get(i));
        }
        if(asociadas==true){
            if(isGuardar()==false){
                JOptionPane.showMessageDialog(this,"Instancias Asociadas",                                                  
                "Confirm Message",JOptionPane.INFORMATION_MESSAGE);
            }
            this.setVisible(false);
            this.setAsociadas(asociadas);
        }
    }
}//GEN-LAST:event_aceptarButtonActionPerformed

private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
// TODO add your handling code here:
    this.setVisible(false);//GEN-LAST:event_cancelarButtonActionPerformed
    this.setIsCancel(true);
    this.setAsociadas(false);
}

public Instancias getInstancias() {
    return instancias;
}

public void setInstancias(Instancias instancias) {
    this.instancias = instancias;
}

public List<ScenarioTest> getScenariosSeleccionados() {
    return scenariosSeleccionados;
}

public void setScenariosSeleccionados(List<ScenarioTest> scenariosSeleccionados) {
    this.scenariosSeleccionados = scenariosSeleccionados;
}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarButton;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton verTestButton;
    // End of variables declaration//GEN-END:variables

    public boolean isIsCancel() {
        return isCancel;
    }

    public void setIsCancel(boolean isCancel) {
        this.isCancel = isCancel;
    }

    public boolean isAsociadas() {
        return asociadas;
    }

    public void setAsociadas(boolean asociadas) {
        this.asociadas = asociadas;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }

}
