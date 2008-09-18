/*
 * AddInstancesClasPropJDialog.java
 *
 * Created on 7 de junio de 2008, 19:55
 */

package code.google.com.p.ontologytesting.guiNew;

import code.google.com.p.ontologytesting.exceptions.ExceptionReadOntology;
import code.google.com.p.ontologytesting.guiNew.AddComentJDialog;
import code.google.com.p.ontologytesting.jenainterfaz.*;
import code.google.com.p.ontologytesting.controller.Auxiliar;
import code.google.com.p.ontologytesting.model.*;
import code.google.com.p.ontologytesting.validations.ValidarTests;
import code.google.com.p.ontologytesting.persistence.SaveTest;
import java.awt.Frame;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.io.File;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author  Saruskas
 */
public class AddInstancesClasPropJDialog extends javax.swing.JDialog {

    static final int desktopWidth = 800;
    static final int desktopHeight = 580;
    static JFrame frame,parent;
    private AddComentJDialog commentPane;
    private List<ClassInstances> clasInst,clasFinal;
    private List<PropertyInstances> propInst,propFinal;
    private int indexVect;
    private String nombreFichero;
    private Instancias instancias;
    public static boolean seleccionado;
    private int tabActual=0;
    private boolean queryValida=true;
    private JenaInterface jenaInterface;   
    private Jena jena;
    private boolean instanciaValida=true;
    private String patron1,patron2;
    private ValidarTests validar;
    private String[] ciClas,ciInd;
    private ArrayList failClas,failProp;
    private int failGroupClas,failGroupProp;
    private boolean instanciaSinNombre;
    private int totalClas;
    private String nombreInstancias;
    private CreateInstancesTextAreaJPanel conjunto;
    private String conjuntoClase,conjuntoProp,patron;
    private String[] clas,prop;
    private boolean instanciasInstGuardadas;
    private int typePanel;
    private Auxiliar auxiliar;
    private Instancias instanciasAEditar;
    private boolean editado;
    private SaveTest saveTest;

    public AddInstancesClasPropJDialog(Frame parent, boolean modal,int typePanel){
        
        super(parent, modal);
        this.setTitle("Asociar Instancias");
        this.setModal(false);
        setTypePanel(typePanel);
        initComponents();
        setSeleccionado(true);
        clasPanel.setLayout(new BoxLayout(getClasPanel(), BoxLayout.Y_AXIS));
        propPanel.setLayout(new BoxLayout(getPropPanel(), BoxLayout.Y_AXIS));
        clasPropPanel.setLayout(new BoxLayout(clasPropPanel, BoxLayout.Y_AXIS));
        clasPropPanel.add(new CreateInstancesTextAreaJPanel(),0);

        for (int i = 0; i <= 10; i++) {
            clasPanel.add(new CreateInstancesJPanel(0));  
            propPanel.add(new CreateInstancesJPanel(1));
        }
        
        setInstancias(new Instancias());
        setInstanciasAEditar(null);
        setEditado(false);
    } 
    
    public AddInstancesClasPropJDialog(Frame parent, boolean modal,Instancias inst,int typePanel){
        
        super(parent, modal);
        this.setTitle("Asociar Instancias");
        this.setModal(false);
        clasFinal = new ArrayList<ClassInstances>();
        propFinal = new ArrayList<PropertyInstances>();
        int contI=0,contP=0;
        initComponents();
        setTypePanel(typePanel);
        setInstanciasInstGuardadas(false);
        clasPanel.setLayout(new BoxLayout(getClasPanel(), BoxLayout.Y_AXIS));
        propPanel.setLayout(new BoxLayout(getPropPanel(), BoxLayout.Y_AXIS));
        clasPropPanel.setLayout(new BoxLayout(clasPropPanel, BoxLayout.Y_AXIS));
        clasPropPanel.add(new CreateInstancesTextAreaJPanel(),0);

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
            clasFinal.add(cI);
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
            propFinal.add(pI);
            contP++;
        }
          
        for (int j = 0; j <= (10-contI); j++) {
            clasPanel.add(new CreateInstancesJPanel(0));
        }
        for (int k = 0; k <= (10-contP); k++) {
            propPanel.add(new CreateInstancesJPanel(1));
        }
        
        instanciasAEditar = new Instancias(inst);
        setInstancias(inst);
        setEditado(true);
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        guardarInstButton = new javax.swing.JButton();
        cancelarInstButton = new javax.swing.JButton();
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
        contentDescPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descInstanciasTextArea = new javax.swing.JTextArea();
        nomInstanciasTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Añada las instancias que quiera a su ontología:");

        guardarInstButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/disk.png"))); // NOI18N
        guardarInstButton.setText("Guardar y Asociar");
        guardarInstButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarInstButtonActionPerformed(evt);
            }
        });

        cancelarInstButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/cancel.png"))); // NOI18N
        cancelarInstButton.setText("Cancelar");
        cancelarInstButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarInstButtonActionPerformed(evt);
            }
        });

        limpiarInstButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/paintbrush.png"))); // NOI18N
        limpiarInstButton.setText(" Limpiar Seleccion");
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

        instancesTabbedPane.addTab("Instancias de Clase", classScrollPane);

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

        instancesTabbedPane.addTab("Instancias de Propiedad", propScrollPane);

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

        instancesTabbedPane.addTab("Introducir ambas en forma de texto", textAreaScrollPane);

        formatosButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/information.png"))); // NOI18N
        formatosButton.setText("Formatos Permitidos");
        formatosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formatosButtonActionPerformed(evt);
            }
        });

        borrarSelecButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/delete.png"))); // NOI18N
        borrarSelecButton.setText("Borrar Seleccion");
        borrarSelecButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarSelecButtonActionPerformed(evt);
            }
        });

        soloAsociarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/accept.png"))); // NOI18N
        soloAsociarButton.setText("Asociar");
        soloAsociarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soloAsociarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(instancesTabbedPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, contentPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 222, Short.MAX_VALUE)
                        .addComponent(formatosButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, contentPanelLayout.createSequentialGroup()
                        .addComponent(cancelarInstButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 384, Short.MAX_VALUE)
                        .addComponent(soloAsociarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(guardarInstButton))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(limpiarInstButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 451, Short.MAX_VALUE)
                        .addComponent(borrarSelecButton)))
                .addContainerGap())
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(formatosButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(instancesTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(borrarSelecButton)
                    .addComponent(limpiarInstButton))
                .addGap(18, 18, 18)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarInstButton)
                    .addComponent(guardarInstButton)
                    .addComponent(soloAsociarButton)))
        );

        descInstanciasTextArea.setColumns(20);
        descInstanciasTextArea.setRows(5);
        jScrollPane1.setViewportView(descInstanciasTextArea);

        jLabel2.setText("Nombre para el conjunto de instancias:");

        jLabel3.setText("Descripción:");

        javax.swing.GroupLayout contentDescPanelLayout = new javax.swing.GroupLayout(contentDescPanel);
        contentDescPanel.setLayout(contentDescPanelLayout);
        contentDescPanelLayout.setHorizontalGroup(
            contentDescPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentDescPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentDescPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentDescPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nomInstanciasTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(222, Short.MAX_VALUE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setText("Complete los siguientes campos:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(290, Short.MAX_VALUE))
            .addComponent(contentDescPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(contentDescPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(194, 194, 194))
        );

        setSize(new java.awt.Dimension(751, 691));
    }// </editor-fold>//GEN-END:initComponents

private void guardarInstButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarInstButtonActionPerformed
 
    jenaInterface = new JenaInterface();
    jena = jenaInterface.getJena();
    saveTest = new SaveTest();
    jena.addReasoner(MainApplication.getCollection().getOntology());
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
        getInstancias().setType("Instancias");
        auxiliar = new Auxiliar();

        File dir = new File(MainApplication.getProyecto());
        String[] ficheros = dir.list();
        int aux=0;
        for(int i=0; i<ficheros.length;i++){
            if(aux==0){
                if(ficheros[i].equals(getInstancias().getNombre())){
                    if(auxiliar.mismasInstancias(this.getInstancias(), this.getInstanciasAEditar())==false){
                        Object[] options = {"Sobreescribir", "Cancelar"};
                        int n = JOptionPane.showOptionDialog(frame, "El conjunto de instancias ha sido modificado. ¿Que desea hacer?", 
                                "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                        if (n == JOptionPane.YES_OPTION) {
                            saveTest.saveInstanciasInMemory(getInstancias());
                            guardar(getTypePanel());
                            aux=1;
                            setInstanciasAEditar(new Instancias(getInstancias()));
                            setInstancias(new Instancias(getInstancias()));
                        }else{
                            aux=1;
                        }
                    }
                }
            }
        }
        if(aux==0){
            saveTest.saveInstanciasInMemory(getInstancias());
            if(getTypePanel()==0 || getTypePanel()==3){
                Auxiliar.getTestSimpleInstSat().getScenario().setInstancias(getInstancias());
            }else if(getTypePanel()==1 || getTypePanel()==4){
                Auxiliar.getTestSimpleRetClas().getScenario().setInstancias(getInstancias());
            }else if(getTypePanel()==2){
                Auxiliar.getTestSimpleReal().getScenario().setInstancias(getInstancias());
            }else if(getTypePanel()==5){
                Auxiliar.getTestSparql().getScenario().setInstancias(getInstancias());
            }
        }
        this.setVisible(false);
    } else if (instanciaSinNombre == true) {
        JOptionPane.showMessageDialog(frame, "El nombre para el conjunto de instancias" 
                + "es obligatorio.", "Warning Message", JOptionPane.WARNING_MESSAGE);
    } else if (instanciaValida == false) {
        JOptionPane.showMessageDialog(frame, "Las instancias marcadas" + 
                "en rojo no se corresponden con la definicion de su ontologia. " 
                + "Por favor, reviselas.", "Warning Message", JOptionPane.WARNING_MESSAGE);
    } else if (queryValida == false) {
        JOptionPane.showMessageDialog(frame, "El formato de las instancias marcadas" 
                + "en rojo es incorrecto. Por favor, revise la documentación para ver" 
                + "los formatos permitidos.", "Warning Message", JOptionPane.WARNING_MESSAGE);
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
}//GEN-LAST:event_guardarInstButtonActionPerformed

public void guardar(int typePanel){
    if(typePanel==0 || typePanel==3){
        Auxiliar.getTestSimpleInstSat().getScenario().setInstancias(getInstancias());
        Auxiliar.getTestSimpleInstSat().getScenarioAEditar().setInstancias(getInstanciasAEditar());
        saveTest = new SaveTest();
        saveTest.replaceTestLocally(Auxiliar.getTestSimpleInstSat().getScenario());
    }else if(typePanel==1 || typePanel==4){
        Auxiliar.getTestSimpleRetClas().getScenario().setInstancias(getInstancias());
        Auxiliar.getTestSimpleRetClas().getScenarioAEditar().setInstancias(getInstanciasAEditar());
        saveTest = new SaveTest();
        saveTest.replaceTestLocally(Auxiliar.getTestSimpleRetClas().getScenario());
    }else if(typePanel==2){
        Auxiliar.getTestSimpleReal().getScenario().setInstancias(getInstancias());
        Auxiliar.getTestSimpleReal().getScenarioAEditar().setInstancias(getInstanciasAEditar());
        saveTest = new SaveTest();
        saveTest.replaceTestLocally(Auxiliar.getTestSimpleReal().getScenario());
    }else if(typePanel==5){
        Auxiliar.getTestSparql().getScenario().setInstancias(getInstancias());
        Auxiliar.getTestSparql().getScenarioAEditar().setInstancias(getInstanciasAEditar());
        saveTest = new SaveTest();
        saveTest.replaceTestLocally(Auxiliar.getTestSparql().getScenario());
    }
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
                    boolean v = jena.addInstanceClass(MainApplication.getCollection().getNamespace(), ciClas[0], ciInd[0]);
                    if (v == false) {
                        conjunto.getClaseArea().setForeground(Color.RED);
                        instanciaValida=false;
                    } else {
                        conjunto.getClaseArea().setForeground(Color.BLACK);
                    }
                }
                if (validar.validarInstanciaClase(clas[i]) == true && isInstanciaValida() == true) {
                    ClassInstances cI = new ClassInstances(clas[i]);
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
                    boolean v = jena.addInstanceProperty(MainApplication.getCollection().getNamespace(), ciClas[0], ciInd[0]);
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
                    boolean v = jena.addInstanceClass(MainApplication.getCollection().getNamespace(), ciClas[0], ciInd[0]);
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
                        ClassInstances cI = new ClassInstances(query, coment);
                        clasInst.add(cI);
                    } else if (!query.equals("") && coment.equals("")) {
                        ClassInstances cI = new ClassInstances(query);
                        clasInst.add(cI);
                    }
                    failClas.add(i, 0);
                }else if (validar.validarInstanciaClase(query) == false) {
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
                    boolean v = jena.addInstanceProperty(MainApplication.getCollection().getNamespace(), ciClas[0], ciInd[0]);
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
                        PropertyInstances pI = new PropertyInstances(query, coment);
                        propInst.add(pI);
                    } else if (!query.equals("") && coment.equals("")) {
                        PropertyInstances pI = new PropertyInstances(query);
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

public boolean yaExisteInstancia(String nombre){
    ArrayList<ScenarioTest> listaEsce = MainApplication.getCollection().getScenariotest();
        for(int i=0;i<listaEsce.size();i++){
            ScenarioTest scenario = listaEsce.get(i);
            String n = scenario.getInstancias().getNombre();
            if(n.equals(nombre)){
                return true;
            }
        }
    return false;
}

public void crearArchivoDeTests(String nombreFichero){
    
    try{
        XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new 
                            FileOutputStream(nombreFichero)));
        e.writeObject(MainApplication.getCollection());
        e.close();
    }catch (FileNotFoundException ex) {
        ex.printStackTrace();
     }
} 

private void cancelarInstButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarInstButtonActionPerformed
// TODO add your handling code here:
    this.setVisible(false);
}//GEN-LAST:event_cancelarInstButtonActionPerformed

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
            JOptionPane.showMessageDialog(frame,"No ha seleccionado ninguna clase para limpiar",
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
            JOptionPane.showMessageDialog(frame,"No ha seleccionado ninguna propiedad para limpiar",
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
    setTabActual(getInstancesTabbedPane());
}//GEN-LAST:event_instancesTabbedPaneMouseClicked

private void formatosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formatosButtonActionPerformed
// TODO add your handling code here:
    FormatInstancesJDialog format = new FormatInstancesJDialog(frame,true);
    format.setModal(false);
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
                AddInstancesClasPropJDialog.getClasPanel().remove(panelInst);
                decrementarPosicion(i,0);
                CreateInstancesJPanel nuevo = new CreateInstancesJPanel(0);
                AddInstancesClasPropJDialog.getClasPanel().add(nuevo);
                i--;
                auxClas=1;
            }
        }
        getClasPanel().validate();
        if(auxClas==0){
            JOptionPane.showMessageDialog(frame,"No ha seleccionado ninguna clase para borrar",
                    "Warning Message",JOptionPane.INFORMATION_MESSAGE);
        }
    }else if(getInstancesTabbedPane()==1){
        int totalProp = getClasPanel().getComponentCount();
        for(int i=0; i<totalProp; i++){
            CreateInstancesJPanel panelProp = (CreateInstancesJPanel) getPropPanel().getComponent(i);
            if(panelProp.getSelectedCheckBox()==true){
                AddInstancesClasPropJDialog.getPropPanel().remove(panelProp);
                decrementarPosicion(i,1);
                CreateInstancesJPanel nuevo = new CreateInstancesJPanel(1);
                AddInstancesClasPropJDialog.getPropPanel().add(nuevo);
                i--;
                auxProp=1;
            }
        }
        getPropPanel().validate();
        if(auxProp==0){
            JOptionPane.showMessageDialog(frame,"No ha seleccionado ninguna propiedad para borrar",
                    "Warning Message",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}//GEN-LAST:event_borrarSelecButtonActionPerformed

private void soloAsociarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soloAsociarButtonActionPerformed
// TODO add your handling code here:

    jenaInterface = new JenaInterface();
    jena = jenaInterface.getJena();
    jena.addReasoner(MainApplication.getCollection().getOntology());
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
    setInstancias(new Instancias());
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
        getInstancias().setType("Instancias");
        if(getTypePanel()==0 || getTypePanel()==3){
            Auxiliar.getTestSimpleInstSat().getScenario().setInstancias(getInstancias());
        }else if(getTypePanel()==1 || getTypePanel()==4){
            Auxiliar.getTestSimpleRetClas().getScenario().setInstancias(getInstancias());
        }else if(getTypePanel()==2){
            Auxiliar.getTestSimpleReal().getScenario().setInstancias(getInstancias());
        }else if(getTypePanel()==5){
            Auxiliar.getTestSparql().getScenario().setInstancias(getInstancias());
        }
        JOptionPane.showMessageDialog(this, "Instancias asociadas al test", 
        "Confirm Message", JOptionPane.INFORMATION_MESSAGE);
        this.setVisible(false);
    } else if (instanciaSinNombre == true) {
        JOptionPane.showMessageDialog(frame, "El nombre para el conjunto de instancias" 
                + "es obligatorio.", "Warning Message", JOptionPane.WARNING_MESSAGE);
    } else if (instanciaValida == false) {
        JOptionPane.showMessageDialog(frame, "Las instancias marcadas" + 
                "en rojo no se corresponden con la definicion de su ontologia. " 
                + "Por favor, reviselas.", "Warning Message", JOptionPane.WARNING_MESSAGE);
    } else if (queryValida == false) {
        JOptionPane.showMessageDialog(frame, "El formato de las instancias marcadas" 
                + "en rojo es incorrecto. Por favor, revise la documentación para ver" 
                + "los formatos permitidos.", "Warning Message", JOptionPane.WARNING_MESSAGE);
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
}//GEN-LAST:event_soloAsociarButtonActionPerformed

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
        Auxiliar.setContadorClas(posN+1);
    }else if(type==1){
        int pos=0,posN=0;
        int totalProp = getPropPanel().getComponentCount();
        for(int i=j; i<totalProp; i++){
            CreateInstancesJPanel panelProp = (CreateInstancesJPanel) getPropPanel().getComponent(i);
            pos = panelProp.getPosicion();
            posN = pos-1;
            panelProp.setPosicion(posN);
        } 
        Auxiliar.setContadorProp(posN+1);
    }
}

public void copiarAInstancesTextArea(){
    String textoClase="",textoProp="";
    
    conjunto = (CreateInstancesTextAreaJPanel) clasPropPanel.getComponent(0);
    clasInst = new ArrayList<ClassInstances>();
    propInst = new ArrayList<PropertyInstances>();

    totalClas = getClasPanel().getComponentCount();
    
    for(int i=0; i<totalClas; i++){
        CreateInstancesJPanel panelInst = (CreateInstancesJPanel) getClasPanel().getComponent(i);
        String query = panelInst.getQuery();

        if(!query.equals("")){
            if(textoClase.equals("")){
                textoClase = query+"\n";
            }else{
                textoClase = textoClase+query+"\n";
            }
        }
    }
    int totalProp = getPropPanel().getComponentCount();
    for(int i=0; i< totalProp; i++){
        CreateInstancesJPanel panelInst = (CreateInstancesJPanel) getPropPanel().getComponent(i);
        String query = panelInst.getQuery();

        if(!query.equals("")){
            if(textoProp.equals("")){
                textoProp = query+"\n";
            }else{
                textoProp = textoProp+query+"\n";
            }
        }
    }
    conjunto.setClaseTextArea(textoClase);
    conjunto.setPropiedadTextArea(textoProp);
    
    
    int tamClas = AddInstancesClasPropJDialog.getClasPanel().getComponentCount();
    int tamProp = AddInstancesClasPropJDialog.getPropPanel().getComponentCount();
    for(int i=0;i<tamClas;i++){
        CreateInstancesJPanel panelClas = (CreateInstancesJPanel) AddInstancesClasPropJDialog.getClasPanel().getComponent(i);
        panelClas.setInstance("");
        panelClas.getComment().setComent("");
    }
    for(int i=0;i<tamProp;i++){
        CreateInstancesJPanel panelProp = (CreateInstancesJPanel) AddInstancesClasPropJDialog.getPropPanel().getComponent(i);
        panelProp.setInstance("");
        panelProp.getComment().setComent("");
    }
    this.validate();   
}

public void copiarAInstancesAyuda(){
    patron="[\\n|\\t|\\s]";
    conjunto = (CreateInstancesTextAreaJPanel) clasPropPanel.getComponent(0);
    conjuntoClase = conjunto.getClaseTextArea().toString().trim();
    conjuntoProp = conjunto.getPropiedadTextArea().trim();
    clas = conjuntoClase.split(patron);
    prop = conjuntoProp.split(patron);
    int tamClas = AddInstancesClasPropJDialog.getClasPanel().getComponentCount();
    int tamProp = AddInstancesClasPropJDialog.getPropPanel().getComponentCount();
    int contClas=0,contProp=0;
    for(int i=0;i<clas.length;i++){
        if(!clas[i].equals("")){
            if(i<tamClas){
                CreateInstancesJPanel panelClas = (CreateInstancesJPanel) AddInstancesClasPropJDialog.getClasPanel().getComponent(contClas);
                panelClas.setInstance(clas[i]);
                contClas++;
            }else{
                if(contClas<tamClas){
                    CreateInstancesJPanel panelClas = (CreateInstancesJPanel) AddInstancesClasPropJDialog.getClasPanel().getComponent(contClas);
                    panelClas.setInstance(clas[i]);
                    contClas++;
                }else{
                    CreateInstancesJPanel panelClas = new CreateInstancesJPanel(0);
                    panelClas.setInstance(clas[i]);
                    AddInstancesClasPropJDialog.getClasPanel().add(panelClas);
                }
            }
        }
    }
    this.validate();
    for(int i=0;i<prop.length;i++){
        if(!prop[i].equals("")){
            if(i<tamProp){
                CreateInstancesJPanel panelProp = (CreateInstancesJPanel) AddInstancesClasPropJDialog.getPropPanel().getComponent(contProp);
                panelProp.setInstance(prop[i]);
                contProp++;
            }else{
                if(contProp<tamProp){
                    CreateInstancesJPanel panelProp = (CreateInstancesJPanel) AddInstancesClasPropJDialog.getPropPanel().getComponent(contProp);
                    panelProp.setInstance(prop[i]);
                    contProp++;
                }else{
                    CreateInstancesJPanel panelProp = new CreateInstancesJPanel(1);
                    panelProp.setInstance(prop[i]);
                    AddInstancesClasPropJDialog.getPropPanel().add(panelProp);
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

    public static int getInstancesTabbedPane() {
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

    public static javax.swing.JPanel getClasPanel() {
        return clasPanel;
    }

    public static javax.swing.JPanel getPropPanel() {
        return propPanel;
    }
    
    public static boolean isSeleccionado() {
        return seleccionado;
    }
    public static void setSeleccionado(boolean aSeleccionado) {
        seleccionado = aSeleccionado;
    }

    public boolean getInstanciasInstGuardadas() {
        return instanciasInstGuardadas;
    }

    public void setInstanciasInstGuardadas(boolean instanciasGuardadas) {
        this.instanciasInstGuardadas = instanciasGuardadas;
    }

    public int getTypePanel() {
        return typePanel;
    }

    public void setTypePanel(int typePanel) {
        this.typePanel = typePanel;
    }

    public Instancias getInstanciasAEditar() {
        return instanciasAEditar;
    }

    public void setInstanciasAEditar(Instancias instanciasAEditar) {
        this.instanciasAEditar = instanciasAEditar;
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton borrarSelecButton;
    private javax.swing.JButton cancelarInstButton;
    private static javax.swing.JPanel clasPanel;
    private javax.swing.JPanel clasPropPanel;
    private javax.swing.JScrollPane classScrollPane;
    private javax.swing.JPanel contentDescPanel;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JTextArea descInstanciasTextArea;
    private javax.swing.JButton formatosButton;
    private javax.swing.JButton guardarInstButton;
    private static javax.swing.JTabbedPane instancesTabbedPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton limpiarInstButton;
    private javax.swing.JTextField nomInstanciasTextField;
    private static javax.swing.JPanel propPanel;
    private javax.swing.JScrollPane propScrollPane;
    private javax.swing.JButton soloAsociarButton;
    private javax.swing.JScrollPane textAreaScrollPane;
    // End of variables declaration//GEN-END:variables

    

}
