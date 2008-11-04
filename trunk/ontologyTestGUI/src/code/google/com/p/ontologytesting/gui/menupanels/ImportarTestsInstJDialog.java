/*
 * AbrirTestsJDialog.java
 *
 * Created on 14 de julio de 2008, 13:48
 */

package code.google.com.p.ontologytesting.gui.menupanels;

import code.google.com.p.ontologytesting.gui.auxiliarclasess.AniadirPanelDeAviso;
import code.google.com.p.ontologytesting.gui.auxiliarclasess.OpcionesMenu;
import code.google.com.p.ontologytesting.gui.auxiliarclasess.FileChooserSelector;
import code.google.com.p.ontologytesting.model.CollectionTest;
import code.google.com.p.ontologytesting.model.Instancias;
import code.google.com.p.ontologytesting.model.ScenarioTest;
import code.google.com.p.ontologytesting.persistence.SaveTest;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author  sara.garcia
 */
public class ImportarTestsInstJDialog extends javax.swing.JDialog {

    private ListarTestsInstanciasJPanel listaFicheros = new ListarTestsInstanciasJPanel();
    private XMLDecoder decoder;
    private CollectionTest collection;
    private List<ScenarioTest> scenarioSparql = new ArrayList<ScenarioTest>();
    private List<ScenarioTest> scenarioSimple = new ArrayList<ScenarioTest>();
    private List<Instancias> instancias = new ArrayList<Instancias>();
    private ListarTestsJPanel listT;
    private SaveTest saveTest = new SaveTest();
    private OpcionesMenu opMenu = new OpcionesMenu();
    private boolean importarTest=false;
    private SeeTestJDialog verTest = null;
    private AniadirPanelDeAviso panelAviso;
    private FileChooserSelector utils;
    
    /** Creates new form AbrirTestsJDialog */
    public ImportarTestsInstJDialog(Frame parent, boolean modal,boolean impTest) {
        super(parent, modal);
        initComponents();
        utils = new FileChooserSelector();
        panelAviso = new AniadirPanelDeAviso();
        contentPanel.setLayout(new FlowLayout());  
        contentPanel.add(new ListarTestsInstanciasJPanel());
        contentPanel.getParent().validate(); 
        this.setImportarTest(impTest);
        listT = ListarTestsJPanel.getInstance();
        this.setTitle("Importar");
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
        abrirButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        pathProyectoTextField = new javax.swing.JTextField();
        examinarButton = new javax.swing.JButton();
        importarButton = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 537, Short.MAX_VALUE)
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 252, Short.MAX_VALUE)
        );

        abrirButton.setText("Ver Completo");
        abrirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirButtonActionPerformed(evt);
            }
        });

        cancelarButton.setText("Cancelar");
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Ubicación del Proyecto");

        jLabel3.setText("Seleccione el proyecto que contiene los tests/instancias con los que desea trabajar:");

        examinarButton.setText("Examinar");
        examinarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                examinarButtonActionPerformed(evt);
            }
        });

        importarButton.setText("Importar");
        importarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(pathProyectoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(examinarButton)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(abrirButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(importarButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cancelarButton))
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE))
                                .addGap(21, 21, 21)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pathProyectoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(examinarButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarButton)
                    .addComponent(importarButton)
                    .addComponent(abrirButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void abrirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirButtonActionPerformed
// TODO add your handling code here:  
    ScenarioTest scenSelect = listaFicheros.getScenarioSelect();
    Instancias instSelect = listaFicheros.getInstanciaSelect();
    if(this.isImportarTest()==true && scenSelect.esVacio()==false){
        verTest = opMenu.verTest(scenSelect);
        verTest.setLocationRelativeTo(this);
        verTest.setVisible(true);
    }else if(this.isImportarTest()==false && instSelect.esVacio()==false){
        verTest = opMenu.verInstancias(instSelect);
        verTest.setLocationRelativeTo(this);
        verTest.setVisible(true);
    } 
}//GEN-LAST:event_abrirButtonActionPerformed

private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
// TODO add your handling code here:
    this.setVisible(false);
}//GEN-LAST:event_cancelarButtonActionPerformed

private void examinarButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
// TODO add your handling code here:
    utils.fileChooser(true, true);
    this.getPathProyectoTextField().setText(FileChooserSelector.getPathSelected());
    try{
        decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(this.getPathProyect())));
        collection = (CollectionTest) decoder.readObject();
        if(this.isImportarTest()==true){
            listaFicheros = new ListarTestsInstanciasJPanel(collection.getScenariotest(),null,true);
        }else{
            listaFicheros = new ListarTestsInstanciasJPanel(null,collection.getInstancias(),false);
        }
        decoder.close(); 
        contentPanel.remove(0);
        contentPanel.add(listaFicheros);
        contentPanel.getParent().validate(); 
    }catch(FileNotFoundException e){
        panelAviso.errorAction("No se encontró el archivo especificado",this);
    }catch(ClassCastException e){
        panelAviso.errorAction("Proyecto no válido",this);
    }catch(NoSuchElementException e){
        panelAviso.errorAction("Proyecto no válido",this);
    }  
}

private void importarButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
// TODO add your handling code here:
    int aux=0;
    if(this.isImportarTest()==true){
        List<ScenarioTest> scenImp = listaFicheros.getListaDeScenarios();
        if(scenImp.size()!=0){
            for(int i=0;i<scenImp.size();i++){
                if(scenImp.get(i).getTipoTest().name().equals("SPARQL")){
                    scenarioSparql.add(scenImp.get(i));
                }else{
                    scenarioSimple.add(scenImp.get(i));
                }
                saveTest.saveTestInMemory(scenImp.get(i));
            }
        }
        if(scenarioSparql.size()>0){
            listT.aniadirTestSparql(scenarioSparql);
            panelAviso.confirmAction("Tests importados",this);  
            aux=1;
            this.setVisible(false);
        }
        if(scenarioSimple.size()>0){
            listT.aniadirTestSimple(scenarioSimple);
            if(aux==0){
                panelAviso.confirmAction("Tests importados",this);  
            }
            this.setVisible(false);
        } 
    }else{
        List<Instancias> instImp = listaFicheros.getListaInstancias();
        if(instImp.size()>0){
            for(int i=0;i<instImp.size();i++){
                instancias.add(instImp.get(i));
                saveTest.saveInstanciasInMemory(instImp.get(i));
            }
            listT.aniadirInstancias(instancias);
            panelAviso.confirmAction("Instancias importadas",this); 
            this.setVisible(false);
        }
    }
}
    
    public javax.swing.JTextField getPathProyectoTextField() {
        return pathProyectoTextField;
    }

    public String getPathProyect() {
        return pathProyectoTextField.getText();
    }
    
    public boolean isImportarTest() {
        return importarTest;
    }

    public void setImportarTest(boolean importarTest) {
        this.importarTest = importarTest;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abrirButton;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JButton examinarButton;
    private javax.swing.JButton importarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField pathProyectoTextField;
    // End of variables declaration//GEN-END:variables

}
