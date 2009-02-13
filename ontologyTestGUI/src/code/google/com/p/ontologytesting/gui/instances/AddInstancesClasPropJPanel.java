/*
 * AddInstancesClasPropJDialog.java
 *
 * Created on 7 de junio de 2008, 19:55
 */

package code.google.com.p.ontologytesting.gui.instances;

import code.google.com.p.ontologytesting.gui.menupanels.AsociarInstanciasATestJDialog;
import code.google.com.p.ontologytesting.gui.*;
import code.google.com.p.ontologytesting.gui.auxiliarclasess.AniadirPanelDeAviso;
import code.google.com.p.ontologytesting.gui.auxiliarclasess.OpcionesMenu;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.*;
import code.google.com.p.ontologytesting.gui.tests.AddComentJDialog;
import code.google.com.p.ontologytesting.model.*;
import code.google.com.p.ontologytesting.persistence.*;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ListIterator;
import javax.swing.JDialog;
import javax.swing.WindowConstants;
import java.util.Locale;

/**
 *
 * @author  Saruskas
 */
public class AddInstancesClasPropJPanel extends javax.swing.JPanel{

    private AddComentJDialog commentPane;
    private ArrayList<ClassInstances> clasInst;
    private ArrayList<PropertyInstances> propInst;
    private int indexVect,tabActual=0,failGroupClas,failGroupProp,totalClas;
    private String nombreFichero;
    private Instancias instancias;
    private Reasoner jenaInterface;   
    private InterfaceReasoner jena;
    private String patron1,patron2;
    private ValidarTests validar;
    private String[] ciClas,ciInd;
    private ArrayList failClas,failProp;
    private String nombreInstancias;
    private CreateInstancesTextAreaJPanel conjunto;
    private String conjuntoClase,conjuntoProp,patron;
    private String[] clas,prop;
    private boolean instanciasInstGuardadas,editado,instanciaSinNombre,
            instanciaValida=true,queryValida=true,fromTest=false,continuar=true;
    private IOManagerImplementation persist = new IOManagerImplementation();
    private ScenarioTest scenario = new ScenarioTest();
    private OpcionesMenu menu;
    private AsociarInstanciasATestJDialog asociarInst;
    private AniadirPanelDeAviso panelAviso;
    
    //Constructor para editar un conjunto de instancias
    public AddInstancesClasPropJPanel(Instancias inst){
        initComponents();
        int contI=0,contP=0;
        panelAviso=new AniadirPanelDeAviso();
        classScrollPane.getVerticalScrollBar().setUnitIncrement(7);
        propScrollPane.getVerticalScrollBar().setUnitIncrement(7);
        textAreaScrollPane.getVerticalScrollBar().setUnitIncrement(7);
        continuar=true;
        clasPanel.setLayout(new BoxLayout(getClasPanel(), BoxLayout.Y_AXIS));
        propPanel.setLayout(new BoxLayout(getPropPanel(), BoxLayout.Y_AXIS));
        clasPropPanel.setLayout(new BorderLayout());
        clasPropPanel.add(new CreateInstancesTextAreaJPanel(),BorderLayout.WEST);

        CreateInstancesJPanel.setContadorClas(0);
        CreateInstancesJPanel.setContadorProp(0);
        setNomInstanciasTextField(inst.getNombre());
        setDescInstanciasTextArea(inst.getDescripcion());
        ListIterator ci,pi;
        clasInst = inst.getClassInstances();
        ci = clasInst.listIterator();
        while(ci.hasNext()){ 
            ClassInstances cI = (ClassInstances) ci.next();
            CreateInstancesJPanel instClas = new CreateInstancesJPanel(0);
            instClas.setInstance(cI.getClassInstance());
            commentPane = instClas.getComment();
            commentPane.setComent(cI.getComment());
            instClas.setComment(commentPane);
            clasPanel.add(instClas);
            contI++;
        }
  
        propInst = inst.getPropertyInstances();
        pi = propInst.listIterator();
        while(pi.hasNext()){
            PropertyInstances pI = (PropertyInstances) pi.next();
            CreateInstancesJPanel instProp = new CreateInstancesJPanel(1);
            instProp.setInstance(pI.getPropertyInstance());
            commentPane = instProp.getComment();
            commentPane.setComent(pI.getComment());
            instProp.setComment(commentPane);
            propPanel.add(instProp);
            contP++;
        }
         
        if(7-contI>0){
            for (int j = 0; j <= (7-contI); j++) {
                clasPanel.add(new CreateInstancesJPanel(0));
            }
        }
        if(7-contP>0){
            for (int k = 0; k <= (7-contP); k++) {
                propPanel.add(new CreateInstancesJPanel(1));
            }
        }
        setInstancias(new Instancias(inst));
        setEditado(true);  
    }

    //Constructor para editar un conjunto de instancias
    public AddInstancesClasPropJPanel(ScenarioTest scenario){
        initComponents();
        int contI=0,contP=0;
        continuar=true;
        panelAviso=new AniadirPanelDeAviso();
        classScrollPane.getVerticalScrollBar().setUnitIncrement(7);
        propScrollPane.getVerticalScrollBar().setUnitIncrement(7);
        textAreaScrollPane.getVerticalScrollBar().setUnitIncrement(7);
        guardarButton.setEnabled(false);
        guardarButton.setVisible(false);
        setFromTest(true);
        clasPanel.setLayout(new BoxLayout(getClasPanel(), BoxLayout.Y_AXIS));
        propPanel.setLayout(new BoxLayout(getPropPanel(), BoxLayout.Y_AXIS));
        clasPropPanel.setLayout(new BorderLayout());
        clasPropPanel.add(new CreateInstancesTextAreaJPanel(),BorderLayout.WEST);

        Instancias inst = scenario.getInstancias();
        CreateInstancesJPanel.setContadorClas(0);
        CreateInstancesJPanel.setContadorProp(0);
        setNomInstanciasTextField(inst.getNombre());
        setDescInstanciasTextArea(inst.getDescripcion());
        ListIterator ci,pi;
        clasInst = inst.getClassInstances();
        ci = clasInst.listIterator();
        while(ci.hasNext()){ 
            ClassInstances cI = (ClassInstances) ci.next();
            CreateInstancesJPanel instClas = new CreateInstancesJPanel(0);
            instClas.setInstance(cI.getClassInstance());
            commentPane = instClas.getComment();
            commentPane.setComent(cI.getComment());
            instClas.setComment(commentPane);
            clasPanel.add(instClas);
            contI++;
        }
  
        propInst = inst.getPropertyInstances();
        pi = propInst.listIterator();
        while(pi.hasNext()){
            PropertyInstances pI = (PropertyInstances) pi.next();
            CreateInstancesJPanel instProp = new CreateInstancesJPanel(1);
            instProp.setInstance(pI.getPropertyInstance());
            commentPane = instProp.getComment();
            commentPane.setComent(pI.getComment());
            instProp.setComment(commentPane);
            propPanel.add(instProp);
            contP++;
        }
         
        if(7-contI>0){
            for (int j = 0; j <= (7-contI); j++) {
                clasPanel.add(new CreateInstancesJPanel(0));
            }
        }
        if(7-contP>0){
            for (int k = 0; k <= (7-contP); k++) {
                propPanel.add(new CreateInstancesJPanel(1));
            }
        }
        setScenario(scenario);
        setInstancias(new Instancias(scenario.getInstancias()));
        setEditado(true);  
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        contentDescPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descInstanciasTextArea = new code.google.com.p.ontologytesting.gui.JTextAreaCopypaste();
        nomInstanciasTextField = new code.google.com.p.ontologytesting.gui.JTextFieldCopyPaste();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        contentPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        guardarAsociarInstButton = new javax.swing.JButton();
        limpiarInstButton = new javax.swing.JButton();
        instancesTabbedPane = new javax.swing.JTabbedPane();
        classScrollPane = new javax.swing.JScrollPane();
        clasPanel = new javax.swing.JPanel();
        propScrollPane = new javax.swing.JScrollPane();
        propPanel = new javax.swing.JPanel();
        textAreaScrollPane = new javax.swing.JScrollPane();
        clasPropPanel = new javax.swing.JPanel();
        formatosButton = new javax.swing.JButton();
        borrarSelecButton = new javax.swing.JButton();
        soloAsociarButton = new javax.swing.JButton();
        guardarButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        descInstanciasTextArea.setColumns(20);
        descInstanciasTextArea.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        descInstanciasTextArea.setRows(5);
        jScrollPane1.setViewportView(descInstanciasTextArea);

        nomInstanciasTextField.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N

        jLabel2.setText(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Nombre_para_elconjunto_de_instancias:"));

        jLabel3.setText(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Descripción:"));

        jLabel1.setText(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Añada_las_instancias_que_quiera_a_su_ontología:"));

        guardarAsociarInstButton.setText(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Guardar_y_Asociar"));
        guardarAsociarInstButton.setToolTipText("Guardar y Asociar");
        guardarAsociarInstButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarAsociarInstButtonActionPerformed(evt);
            }
        });

        limpiarInstButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/paintbrush.png"))); // NOI18N
        limpiarInstButton.setToolTipText("Limpiar Selección");
        limpiarInstButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarInstButtonActionPerformed(evt);
            }
        });

        instancesTabbedPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                instancesTabbedPaneMouseClicked(evt);
            }
        });

        classScrollPane.setMaximumSize(new java.awt.Dimension(455, 422));
        classScrollPane.setMinimumSize(new java.awt.Dimension(455, 422));
        classScrollPane.setPreferredSize(new java.awt.Dimension(455, 422));

        clasPanel.setMaximumSize(new java.awt.Dimension(455, 422));
        clasPanel.setMinimumSize(new java.awt.Dimension(455, 422));

        javax.swing.GroupLayout clasPanelLayout = new javax.swing.GroupLayout(clasPanel);
        clasPanel.setLayout(clasPanelLayout);
        clasPanelLayout.setHorizontalGroup(
            clasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 691, Short.MAX_VALUE)
        );
        clasPanelLayout.setVerticalGroup(
            clasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 422, Short.MAX_VALUE)
        );

        classScrollPane.setViewportView(clasPanel);

        instancesTabbedPane.addTab(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Instancias_de_Clase"), classScrollPane);

        propScrollPane.setMaximumSize(new java.awt.Dimension(455, 422));
        propScrollPane.setMinimumSize(new java.awt.Dimension(455, 422));
        propScrollPane.setPreferredSize(new java.awt.Dimension(455, 422));

        propPanel.setMaximumSize(new java.awt.Dimension(455, 422));
        propPanel.setMinimumSize(new java.awt.Dimension(455, 422));

        javax.swing.GroupLayout propPanelLayout = new javax.swing.GroupLayout(propPanel);
        propPanel.setLayout(propPanelLayout);
        propPanelLayout.setHorizontalGroup(
            propPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 691, Short.MAX_VALUE)
        );
        propPanelLayout.setVerticalGroup(
            propPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 422, Short.MAX_VALUE)
        );

        propScrollPane.setViewportView(propPanel);

        instancesTabbedPane.addTab(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Instancias_de_Propiedad"), propScrollPane);

        javax.swing.GroupLayout clasPropPanelLayout = new javax.swing.GroupLayout(clasPropPanel);
        clasPropPanel.setLayout(clasPropPanelLayout);
        clasPropPanelLayout.setHorizontalGroup(
            clasPropPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 691, Short.MAX_VALUE)
        );
        clasPropPanelLayout.setVerticalGroup(
            clasPropPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );

        textAreaScrollPane.setViewportView(clasPropPanel);

        instancesTabbedPane.addTab(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Introducir_ambas_en_forma_de_texto"), textAreaScrollPane);

        formatosButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/information.png"))); // NOI18N
        formatosButton.setToolTipText("Formatos Permitidos");
        formatosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formatosButtonActionPerformed(evt);
            }
        });

        borrarSelecButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/user-trash.png"))); // NOI18N
        borrarSelecButton.setToolTipText("Borrar Selección");
        borrarSelecButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarSelecButtonActionPerformed(evt);
            }
        });

        soloAsociarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/accept.png"))); // NOI18N
        soloAsociarButton.setToolTipText("Asociar");
        soloAsociarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soloAsociarButtonActionPerformed(evt);
            }
        });

        guardarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/disk.png"))); // NOI18N
        guardarButton.setToolTipText("Guardar");
        guardarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                        .addComponent(formatosButton))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(limpiarInstButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(borrarSelecButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 196, Short.MAX_VALUE)
                        .addComponent(soloAsociarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(guardarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(guardarAsociarInstButton))
                    .addComponent(instancesTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE))
                .addContainerGap())
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(formatosButton))
                .addGap(1, 1, 1)
                .addComponent(instancesTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(borrarSelecButton)
                    .addComponent(limpiarInstButton)
                    .addComponent(soloAsociarButton)
                    .addComponent(guardarAsociarInstButton)
                    .addComponent(guardarButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout contentDescPanelLayout = new javax.swing.GroupLayout(contentDescPanel);
        contentDescPanel.setLayout(contentDescPanelLayout);
        contentDescPanelLayout.setHorizontalGroup(
            contentDescPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentDescPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentDescPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(contentPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, contentDescPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nomInstanciasTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        contentDescPanelLayout.setVerticalGroup(
            contentDescPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentDescPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentDescPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nomInstanciasTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(contentDescPanel, java.awt.BorderLayout.CENTER);
    }

private void guardarAsociarInstButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarAsociarInstButtonActionPerformed
    boolean res = false,asoc=false;
    continuar=true;
    res = prepararInstancias(true);
    asoc = soloAsociar(res);
    if(continuar==true && asoc==true){
        if(this.isFromTest()==false){
            if(asociarInst.isIsCancel()==true){
                continuar=false;
            }
        }
        if(continuar==true){
            if(res == true){
                ListAndTestsJPanel.getInstance().aniadirNombre(getInstancias().getNombre());
                panelAviso.confirmAction("Instancias Guardadas y Asociadas",MainApplicationJFrame.getInstance());
            }else{
                panelAviso.warningAction("Instancias Asociadas pero No Guardadas",MainApplicationJFrame.getInstance());
            }
        }
    }
}//GEN-LAST:event_guardarAsociarInstButtonActionPerformed

private void limpiarInstButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarInstButtonActionPerformed
// TODO add your handling code here:
    int auxClas=0,auxProp=0;
    if(getInstancesTabbedPane()==0){
        totalClas = getClasPanel().getComponentCount();
        for(int i=0; i<totalClas; i++){
            CreateInstancesJPanel panelInst = (CreateInstancesJPanel) getClasPanel().getComponent(i);
            if(panelInst.getSelectedCheckBox()==true){
                panelInst.setInstance("");
                AddComentJDialog comentPane = panelInst.getComment();
                comentPane.setComent("");
                auxClas=1;
            }
        }
        getClasPanel().validate();
        if(auxClas==0){
            JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"No ha seleccionado ninguna clase para limpiar",
                    "Warning Message",JOptionPane.INFORMATION_MESSAGE);
        }
    }else if(getInstancesTabbedPane()==1){
        int totalProp = getPropPanel().getComponentCount();
        for(int i=0; i<totalProp; i++){
            CreateInstancesJPanel panelProp = (CreateInstancesJPanel) getPropPanel().getComponent(i);
            if(panelProp.getSelectedCheckBox()==true){
                panelProp.setInstance("");
                AddComentJDialog comentPane = panelProp.getComment();
                comentPane.setComent("");
                auxProp=1;
            }
        }
        getPropPanel().validate();
        if(auxProp==0){
            JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"No ha seleccionado ninguna propiedad para limpiar",
                    "Warning Message",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}//GEN-LAST:event_limpiarInstButtonActionPerformed

private void instancesTabbedPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_instancesTabbedPaneMouseClicked
// TODO add your handling code here:   
    if(getInstancesTabbedPane()!=getTabActual()){
        if(getInstancesTabbedPane()==2){
            copiarAInstancesTextArea();
        }else{
            copiarAInstancesAyuda();
        }
    }
    CreateInstancesJPanel.setTab(getInstancesTabbedPane());
    setTabActual(getInstancesTabbedPane());
}//GEN-LAST:event_instancesTabbedPaneMouseClicked

private void formatosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formatosButtonActionPerformed
// TODO add your handling code here:
    FormatInstancesJDialog format = new FormatInstancesJDialog(MainApplicationJFrame.getInstance(),true);
    format.setLocationRelativeTo(MainApplicationJFrame.getInstance());
    format.setVisible(true);
}//GEN-LAST:event_formatosButtonActionPerformed

private void borrarSelecButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarSelecButtonActionPerformed
// TODO add your handling code here:
    int auxClas=0,auxProp=0;
    if(getInstancesTabbedPane()==0){
        totalClas = getClasPanel().getComponentCount();
        for(int i=0; i<totalClas; i++){
            CreateInstancesJPanel panelInst = (CreateInstancesJPanel) getClasPanel().getComponent(i);
            if(panelInst.getSelectedCheckBox()==true){
                this.getClasPanel().remove(panelInst);
                decrementarPosicion(i,0);
                CreateInstancesJPanel nuevo = new CreateInstancesJPanel(0);
                this.getClasPanel().add(nuevo);
                i--;
                auxClas=1;
            }
        }
        getClasPanel().validate();
        if(auxClas==0){
            JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"No ha seleccionado ninguna clase para borrar",
                    "Warning Message",JOptionPane.INFORMATION_MESSAGE);
        }
    }else if(getInstancesTabbedPane()==1){
        int totalProp = getClasPanel().getComponentCount();
        for(int i=0; i<totalProp; i++){
            CreateInstancesJPanel panelProp = (CreateInstancesJPanel) getPropPanel().getComponent(i);
            if(panelProp.getSelectedCheckBox()==true){
                this.getPropPanel().remove(panelProp);
                decrementarPosicion(i,1);
                CreateInstancesJPanel nuevo = new CreateInstancesJPanel(1);
                this.getPropPanel().add(nuevo);
                i--;
                auxProp=1;
            }
        }
        getPropPanel().validate();
        if(auxProp==0){
            JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"No ha seleccionado ninguna propiedad para borrar",
                    "Warning Message",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}//GEN-LAST:event_borrarSelecButtonActionPerformed

private void soloAsociarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soloAsociarButtonActionPerformed
// TODO add your handling code here:
    continuar=true;
    boolean res = prepararInstancias(false);
    if(continuar==true){
        boolean resAsoc = soloAsociar(res);
        if(isFromTest()==true){
            if(resAsoc==true){
                panelAviso.confirmAction("Instancias Asociadas",MainApplicationJFrame.getInstance());
            }else{
                panelAviso.errorAction("Instancias No Asociadas", MainApplicationJFrame.getInstance());
            }
        }else if(resAsoc==true){
            panelAviso.confirmAction("Instancias Asociadas",MainApplicationJFrame.getInstance());
        }
    }
}//GEN-LAST:event_soloAsociarButtonActionPerformed


public boolean soloAsociar(boolean res){ 
    if(res==true){
        if(isFromTest()==false){
            asociarInst = new AsociarInstanciasATestJDialog(null, true, this.getInstancias(),true);
            asociarInst.setLocationRelativeTo(MainApplicationJFrame.getInstance());
            asociarInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            asociarInst.setVisible(true);
            return asociarInst.isAsociadas();
        }else{
            getScenario().setInstancias(getInstancias());
            persist.replaceScenarioLocally(getScenario());
            return true;
        }
    }else{
        return false;
    }
}

private void guardarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarButtonActionPerformed
// TODO add your handling code here:
//GEN-LAST:event_guardarButtonActionPerformed
    continuar=true;
    boolean result = prepararInstancias(true);
    if(continuar==true){
        if(result==true){
            ListAndTestsJPanel.getInstance().aniadirNombre(getInstancias().getNombre());
            panelAviso.confirmAction("Instancias Guardadas",MainApplicationJFrame.getInstance());
        }else{
            panelAviso.errorAction("Instancias No Guardadas",MainApplicationJFrame.getInstance());
        }
    }
}

public boolean prepararInstancias(boolean guardar){
    menu = new OpcionesMenu();
    jenaInterface = new Reasoner();
    jena = jenaInterface.getReasoner();
    if(jenaInterface.isCargado()==true){
        persist = new IOManagerImplementation();
        try{
            jena.addReasoner(CollectionTest.getInstance().getOntology());
        }catch(InvalidOntologyException ex){
            instanciaValida=false;
        }
        validar = new ValidarTests();
        ciClas = null;
        ciInd = null;
        failClas = new ArrayList();
        failProp = new ArrayList();
        failGroupClas = 0;
        failGroupProp = 0;
        setInstanciaValida(true);
        clasInst = new ArrayList<ClassInstances>();
        propInst = new ArrayList<PropertyInstances>();
        instanciaSinNombre = false;
        totalClas = getClasPanel().getComponentCount();
        nombreInstancias = nomInstanciasTextField.getText();

        queryValida=true;  
        patron1="[\\(|,]";
        patron2="[,|\\)]"; 
        patron = "\\\n";

        if(getInstancesTabbedPane() != 2){
            preparrarInstConAyuda();
        }else{
            prepararInstSinAyuda();
        }
        if (queryValida==true && instanciaValida==true && instanciaSinNombre==false){
            getInstancias().setClassInstances(clasInst);
            getInstancias().setPropertyInstances(propInst);
            getInstancias().setDescripcion(getDescInstanciasTextArea());
            getInstancias().setNombre(getNomInstanciasTextField());
            if(guardar==true){
                if(persist.instanciasYaGuardadas(getInstancias())==true){
                    Object[] options = {"Sobreescribir", "Cancelar"};
                    int n = JOptionPane.showOptionDialog(MainApplicationJFrame.getInstance(), "El conjunto de instancias '"+getInstancias().getNombre()+"' ya existe. ¿Qué desea hacer?", 
                            "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                    if (n == JOptionPane.YES_OPTION){
                        if(this.isFromTest()==false){
                            persist.replaceInstanciasLocally(getInstancias());
                            this.setInstancias(new Instancias(this.getInstancias()));
                        }else{
                            persist.replaceInstanciasLocally(new Instancias(getInstancias()));
                            this.setInstancias(new Instancias(this.getInstancias()));
                        }
                    }else return continuar=false;
                }else{
                    if(persist.instanciasExisten(getInstancias())==false){
                        if(isFromTest()==true){
                            persist.saveInstanciasInMemory(new Instancias(getInstancias()));
                        }else{
                            persist.saveInstanciasInMemory(getInstancias());
                        }
                    }
                    this.setInstancias(new Instancias(this.getInstancias()));
                }
                menu.actualizarListaDeInstancias();
            }
            return true;
        } else if (instanciaSinNombre == true) {
            JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(), "El nombre para el conjunto de instancias" 
                    + "es obligatorio.", "Warning Message", JOptionPane.WARNING_MESSAGE);
            continuar=false;
        } else if (instanciaValida == false) {
            JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(), "Error en las instancias marcadas en rojo. Posibles causas:"+"\r\n"+"1. Las instancias no se corresponden con la definicion de su ontologia. " 
                    + "\r\n"+"2. La Base de Hechos no es consistente.", "Warning Message", JOptionPane.WARNING_MESSAGE);
            continuar=false;
        } else if (queryValida == false) {
            JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(), "El formato de las instancias marcadas" 
                    + "en rojo es incorrecto. "+"\r\n"+"Por favor, revise la documentación para ver" 
                    + "los formatos permitidos.", "Warning Message", JOptionPane.WARNING_MESSAGE);
            continuar=false;
            if (getInstancesTabbedPane() != 2) {
                CreateInstancesJPanel panelInst = null;
                for (int j = 0; j < failClas.size(); j++) {
                    if (failClas.get(j).equals(1)) {
                        panelInst = (CreateInstancesJPanel) getClasPanel().getComponent(j);
                        panelInst.getInstanciaTextField().setForeground(Color.RED);
                    }
                }
                for (int j = 0; j < failProp.size(); j++) {
                    if (failProp.get(j).equals(1)) {
                        panelInst = (CreateInstancesJPanel) getPropPanel().getComponent(j);
                        panelInst.getInstanciaTextField().setForeground(Color.RED);
                    }
                }
            } else {
                conjunto = (CreateInstancesTextAreaJPanel) clasPropPanel.getComponent(0);
                if (failGroupClas == 1 && failGroupProp == 1) {
                    conjunto.getPropiedadArea().setForeground(Color.RED);
                    conjunto.getClaseArea().setForeground(Color.RED);
                } else if (failGroupClas == 1) {
                    conjunto.getClaseArea().setForeground(Color.RED);
                } else {
                    conjunto.getPropiedadArea().setForeground(Color.RED);
                }
            }
        }
    }
    return false;
}

public void prepararInstSinAyuda(){
    conjunto = (CreateInstancesTextAreaJPanel) clasPropPanel.getComponent(0);
    conjuntoClase = conjunto.getClaseTextArea().trim();
    conjuntoProp = conjunto.getPropiedadTextArea().trim();
    clas = conjuntoClase.split(patron);
    prop = conjuntoProp.split(patron);
    
    if (nombreInstancias.equals("")) {
        instanciaSinNombre=true;
    }else{
        for (int i = 0; i < clas.length; i++) {
            if (!clas[i].equals("")) {
                if (validar.validarInstanciaClase(clas[i]) == true) {
                    ciClas = clas[i].split(patron1);
                    ciInd = ciClas[1].split(patron2);
                    boolean v = jena.addInstanceClass(CollectionTest.getInstance().getNamespace(), ciClas[0].trim(), ciInd[0].trim());
                    if (v == false) {
                        conjunto.getClaseArea().setForeground(Color.RED);
                        instanciaValida=false;
                    } else {
                        conjunto.getClaseArea().setForeground(Color.BLACK);
                    }
                }
                if (validar.validarInstanciaClase(clas[i]) == true && isInstanciaValida() == true) {
                    ClassInstances cI = new ClassInstances(clas[i].trim());
                    clasInst.add(cI);
                    failClas.add(i, 0);
                } else {
                    failClas.add(i, 1);
                    failGroupClas = 1;
                    queryValida=false;
                }
            }
        }
        for (int i = 0; i < prop.length; i++) {
            if (!prop[i].equals("")) {
                if (validar.validarInstanciaPropiedad(prop[i]) == true) {
                    ciClas = prop[i].split(patron1);
                    ciInd = ciClas[1].split(patron2);
                    boolean v = jena.addInstanceProperty(CollectionTest.getInstance().getNamespace(), ciClas[0], ciInd[0]);
                    if (v == false) {
                        conjunto.getPropiedadArea().setForeground(Color.RED);
                        instanciaValida=false;
                    } else {
                        conjunto.getPropiedadArea().setForeground(Color.BLACK);
                    }
                }
                if (validar.validarInstanciaPropiedad(prop[i]) == true && isInstanciaValida() == true) {
                    PropertyInstances pI = new PropertyInstances(prop[i]);
                    propInst.add(pI);
                    failProp.add(i, 0);
                } else {
                    failProp.add(i, 1);
                    failGroupProp = 1;
                    queryValida=false;
                }
            }
        }
    }
}

public void preparrarInstConAyuda(){ 
    if (nombreInstancias.equals("")) {
        instanciaSinNombre = true;
    } else {
        for (int i = 0; i < totalClas; i++) {
            CreateInstancesJPanel panelInst = (CreateInstancesJPanel) getClasPanel().getComponent(i);
            String query = panelInst.getQuery();
            AddComentJDialog comentPane = panelInst.getComment();
            String coment = comentPane.getComent();
            if (!query.equals("")) {
                if (validar.validarInstanciaClase(query) == true) {
                    ciClas = query.split(patron1);
                    ciInd = ciClas[1].split(patron2);
                    boolean v = jena.addInstanceClass(CollectionTest.getInstance().getNamespace(), ciClas[0].trim(), ciInd[0].trim());
                    if (v == false) {
                        panelInst.getInstanciaTextField().setForeground(Color.RED);
                        instanciaValida=false;
                    } else {
                        panelInst.getInstanciaTextField().setForeground(Color.BLACK);
                    }
                }
            }
            if(!query.equals("")){
                if (validar.validarInstanciaClase(query) == true && isInstanciaValida() == true) {
                    if (!query.equals("") && !coment.equals("")) {
                        ClassInstances cI = new ClassInstances(query.trim(), coment);
                        clasInst.add(cI);
                    } else if (!query.equals("") && coment.equals("")) {
                        ClassInstances cI = new ClassInstances(query.trim());
                        clasInst.add(cI);
                    }
                    failClas.add(i, 0);
                }else if (validar.validarInstanciaClase(query.trim()) == false) {
                if (!query.equals("")) {
                    failClas.add(i, 1);
                    queryValida=false;
                }
                } else {
                    failClas.add(i, 0);
                }
            }else{
                failClas.add(i, 0);
            }
        }
        int totalProp = getPropPanel().getComponentCount();
        for (int i = 0; i < totalProp; i++) {
            CreateInstancesJPanel panelInst = (CreateInstancesJPanel) getPropPanel().getComponent(i);
            String query = panelInst.getQuery().trim();
            AddComentJDialog comentPane = panelInst.getComment();
            String coment = comentPane.getComent();
            if (!query.equals("")) {
                if (validar.validarInstanciaPropiedad(query) == true) {
                    ciClas = query.split(patron1);
                    ciInd = ciClas[1].split(patron2);
                    boolean v = jena.addInstanceProperty(CollectionTest.getInstance().getNamespace(), ciClas[0].trim(), ciInd[0].trim());
                    if (v == false) {
                        panelInst.getInstanciaTextField().setForeground(Color.RED);
                        instanciaValida=false;
                    } else {
                        panelInst.getInstanciaTextField().setForeground(Color.BLACK);
                    }
                }
            }
            if(!query.equals("")){
                if (validar.validarInstanciaPropiedad(query) == true && isInstanciaValida() == true && instanciaSinNombre == false) {
                    if (!query.equals("") && !coment.equals("")) {
                        PropertyInstances pI = new PropertyInstances(query.trim(), coment);
                        propInst.add(pI);
                    } else if (!query.equals("") && coment.equals("")) {
                        PropertyInstances pI = new PropertyInstances(query.trim());
                        propInst.add(pI);
                    } 
                    failProp.add(i, 0);
                } else if (validar.validarInstanciaPropiedad(query) == false) {
                    if (!query.equals("")) {
                        failProp.add(i, 1);
                        queryValida=false;
                    }
                } else {
                    failProp.add(i, 0);
                }
            }else{
                failProp.add(i, 0);
            }
        }
    }
}

void decrementarPosicion(int j,int type){
    if(type==0){
        int pos=0,posN=0;
        totalClas = getClasPanel().getComponentCount();
        for(int i=j; i<totalClas; i++){
            CreateInstancesJPanel panelInst = (CreateInstancesJPanel) getClasPanel().getComponent(i);
            pos = panelInst.getPosicion();
            posN = pos-1;
            panelInst.setPosicion(posN);
        } 
        CreateInstancesJPanel.setContadorClas(posN+1);
    }else if(type==1){
        int pos=0,posN=0;
        int totalProp = getPropPanel().getComponentCount();
        for(int i=j; i<totalProp; i++){
            CreateInstancesJPanel panelProp = (CreateInstancesJPanel) getPropPanel().getComponent(i);
            pos = panelProp.getPosicion();
            posN = pos-1;
            panelProp.setPosicion(posN);
        } 
        CreateInstancesJPanel.setContadorProp(posN+1);
    }
}

public void asociarDialogoInstancias(AddInstancesClasPropJPanel addInst){
    JDialog dialog = new JDialog(MainApplicationJFrame.getInstance(),false);
    dialog.setSize(new Dimension(620,540));
    dialog.getContentPane().add(addInst);
    dialog.setTitle(java.util.ResourceBundle.getBundle("code/google/com/p/ontologytesting/gui/internacionalization/Spanish").getString("Asociar_Instancias"));
    dialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    dialog.setLocationRelativeTo(MainApplicationJFrame.getInstance());
    dialog.setVisible(true);
    dialog.validate();
}

public void copiarAInstancesTextArea(){
    StringBuffer bufClase = new StringBuffer();
    StringBuffer bufProp = new StringBuffer();
    conjunto = (CreateInstancesTextAreaJPanel) clasPropPanel.getComponent(0);
    clasInst = new ArrayList<ClassInstances>();
    propInst = new ArrayList<PropertyInstances>();

    totalClas = getClasPanel().getComponentCount();
    
    for(int i=0; i<totalClas; i++){
        CreateInstancesJPanel panelInst = (CreateInstancesJPanel) getClasPanel().getComponent(i);
        String query = panelInst.getQuery();
        if(!query.equals("")){
            bufClase.append(query).append("\n");
        }
    }
    int totalProp = getPropPanel().getComponentCount();
    for(int i=0; i< totalProp; i++){
        CreateInstancesJPanel panelInst = (CreateInstancesJPanel) getPropPanel().getComponent(i);
        String query = panelInst.getQuery();

        if(!query.equals("")){
            bufProp.append(query).append("\n");
        }
    }
    conjunto.setClaseTextArea(bufClase.toString());
    conjunto.setPropiedadTextArea(bufProp.toString());
    
    
    int tamClas = this.getClasPanel().getComponentCount();
    int tamProp = this.getPropPanel().getComponentCount();
    for(int i=0;i<tamClas;i++){
        CreateInstancesJPanel panelClas = (CreateInstancesJPanel) this.getClasPanel().getComponent(i);
        panelClas.setInstance("");
        panelClas.getComment().setComent("");
    }
    for(int i=0;i<tamProp;i++){
        CreateInstancesJPanel panelProp = (CreateInstancesJPanel) this.getPropPanel().getComponent(i);
        panelProp.setInstance("");
        panelProp.getComment().setComent("");
    }
    this.validate();   
}

public void copiarAInstancesAyuda(){
    patron="[\\n]";
    conjunto = (CreateInstancesTextAreaJPanel) clasPropPanel.getComponent(0);
    conjuntoClase = conjunto.getClaseTextArea().trim();
    conjuntoProp = conjunto.getPropiedadTextArea().trim();
    clas = conjuntoClase.split(patron);
    prop = conjuntoProp.split(patron);
    int tamClas = this.getClasPanel().getComponentCount();
    int tamProp = this.getPropPanel().getComponentCount();
    int contClas=0,contProp=0;
    for(int i=0;i<clas.length;i++){
        if(!clas[i].equals("")){
            if(i<tamClas){
                CreateInstancesJPanel panelClas = (CreateInstancesJPanel) this.getClasPanel().getComponent(contClas);
                panelClas.setInstance(clas[i].trim());
                contClas++;
            }else{
                if(contClas<tamClas){
                    CreateInstancesJPanel panelClas = (CreateInstancesJPanel) this.getClasPanel().getComponent(contClas);
                    panelClas.setInstance(clas[i].trim());
                    contClas++;
                }else{
                    CreateInstancesJPanel panelClas = new CreateInstancesJPanel(0);
                    panelClas.setInstance(clas[i].trim());
                    this.getClasPanel().add(panelClas);
                }
            }
        }
    }
    this.validate();
    for(int i=0;i<prop.length;i++){
        if(!prop[i].equals("")){
            if(i<tamProp){
                CreateInstancesJPanel panelProp = (CreateInstancesJPanel) this.getPropPanel().getComponent(contProp);
                panelProp.setInstance(prop[i].trim());
                contProp++;
            }else{
                if(contProp<tamProp){
                    CreateInstancesJPanel panelProp = (CreateInstancesJPanel) this.getPropPanel().getComponent(contProp);
                    panelProp.setInstance(prop[i].trim());
                    contProp++;
                }else{
                    CreateInstancesJPanel panelProp = new CreateInstancesJPanel(1);
                    panelProp.setInstance(prop[i].trim());
                    this.getPropPanel().add(panelProp);
                }
            }
        }
    }   
    this.validate();
}

    public int getIndexVect() {
        return indexVect;
    }

    public void setIndexVect(int indexVect) {
        this.indexVect = indexVect;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public String getNombreFichero() {
        return nombreFichero;
    }

    public void setNombreFichero(String nombreFichero) {
        this.nombreFichero = nombreFichero;
    }

    public int getInstancesTabbedPane() {
        return instancesTabbedPane.getSelectedIndex();
    }

    public int getTabActual() {
        return tabActual;
    }

    public void setTabActual(int tabActual) {
        this.tabActual = tabActual;
    }

    public String getDescInstanciasTextArea() {
        return descInstanciasTextArea.getText();
    }

    public void setDescInstanciasTextArea(String adescInstanciasTextArea) {
        descInstanciasTextArea.setText(adescInstanciasTextArea);
    }

    public String getNomInstanciasTextField() {
        return nomInstanciasTextField.getText();
    }

    public void setNomInstanciasTextField(String anomInstanciasTextField) {
        nomInstanciasTextField.setText(anomInstanciasTextField);
    }

    public javax.swing.JTextField getNombreTextField() {
        return nomInstanciasTextField;
    }

    public javax.swing.JTextArea getDescTextField() {
        return descInstanciasTextArea;
    }
    
    public boolean isQueryValida() {
        return queryValida;
    }

    public void setQueryValida(boolean queryValida) {
        this.queryValida = queryValida;
    }

    public boolean isInstanciaValida() {
        return instanciaValida;
    }

    public void setInstanciaValida(boolean instanciaValida) {
        this.instanciaValida = instanciaValida;
    }

    public javax.swing.JPanel getClasPanel() {
        return clasPanel;
    }

    public javax.swing.JPanel getPropPanel() {
        return propPanel;
    }

    public boolean getInstanciasInstGuardadas() {
        return instanciasInstGuardadas;
    }

    public void setInstanciasInstGuardadas(boolean instanciasGuardadas) {
        this.instanciasInstGuardadas = instanciasGuardadas;
    }

    public Instancias getInstancias() {
        return instancias;
    }

    public void setInstancias(Instancias instancias) {
        this.instancias = instancias;
    }

    public boolean isEditado() {
        return editado;
    }

    public void setEditado(boolean editado) {
        this.editado = editado;
    }
    
    public ScenarioTest getScenario() {
        return scenario;
    }

    public void setScenario(ScenarioTest scenario) {
        this.scenario = scenario;
    }
    
    public boolean isFromTest() {
        return fromTest;
    }

    public void setFromTest(boolean fromTest) {
        this.fromTest = fromTest;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton borrarSelecButton;
    private javax.swing.JPanel clasPanel;
    private javax.swing.JPanel clasPropPanel;
    private javax.swing.JScrollPane classScrollPane;
    private javax.swing.JPanel contentDescPanel;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JTextArea descInstanciasTextArea;
    private javax.swing.JButton formatosButton;
    private javax.swing.JButton guardarAsociarInstButton;
    private javax.swing.JButton guardarButton;
    private javax.swing.JTabbedPane instancesTabbedPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton limpiarInstButton;
    private javax.swing.JTextField nomInstanciasTextField;
    private javax.swing.JPanel propPanel;
    private javax.swing.JScrollPane propScrollPane;
    private javax.swing.JButton soloAsociarButton;
    private javax.swing.JScrollPane textAreaScrollPane;
    // End of variables declaration//GEN-END:variables

}
