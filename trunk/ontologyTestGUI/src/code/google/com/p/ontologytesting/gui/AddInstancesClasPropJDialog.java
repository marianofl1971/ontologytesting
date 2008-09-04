/*
 * AddInstancesClasPropJDialog.java
 *
 * Created on 7 de junio de 2008, 19:55
 */

package code.google.com.p.ontologytesting.gui;

import code.google.com.p.ontologytesting.exceptions.ExceptionReadOntology;
import code.google.com.p.ontologytesting.jenainterfaz.Jena;
import code.google.com.p.ontologytesting.jenainterfaz.JenaInterface;
import code.google.com.p.ontologytesting.model.Auxiliar;
import java.awt.Frame;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import code.google.com.p.ontologytesting.model.ClassInstances;
import code.google.com.p.ontologytesting.model.Instancias;
import code.google.com.p.ontologytesting.model.PropertyInstances;
import code.google.com.p.ontologytesting.model.ScenarioTest;
import code.google.com.p.ontologytesting.model.ValidarTests;
import java.awt.Color;
import java.awt.Component;
/**
 *
 * @author  Saruskas
 */
public class AddInstancesClasPropJDialog extends javax.swing.JDialog {

    static final int desktopWidth = 800;
    static final int desktopHeight = 580;
    static JFrame frame,parent;
    private XMLDecoder decoder;
    private AddComentJDialog commentPane;
    private ArrayList<ClassInstances> clasInst,clasFinal;
    private ArrayList<PropertyInstances> propInst,propFinal;
    private int indexVect;
    private String nombreFichero;
    public static boolean isSeleccionado() {
        return seleccionado;
    }
    public static void setSeleccionado(boolean aSeleccionado) {
        seleccionado = aSeleccionado;
    }
    Instancias instancias;
    public static boolean seleccionado;
    private int tabActual=0;
    private boolean queryValida=true;
    private JenaInterface jenaInterface = new JenaInterface();   
    private Jena jena;
    private boolean instanciaValida=true;
    private String patron1="[\\(|,|\n| ]",patron2="[,|\n| |\\)]";

    public AddInstancesClasPropJDialog(Frame parent, boolean modal,int num, int var){
        
        super(parent, modal);
        this.setTitle("Asociar Instancias");
        this.setModal(false);
        initComponents();
        setSeleccionado(true);
        clasPanel.setLayout(new BoxLayout(getClasPanel(), BoxLayout.Y_AXIS));
        propPanel.setLayout(new BoxLayout(getPropPanel(), BoxLayout.Y_AXIS));
        clasPropPanel.setLayout(new BoxLayout(clasPropPanel, BoxLayout.Y_AXIS));
        clasPropPanel.add(new CreateInstancesTextAreaJPanel(),0);

        if(var==0){
            for (int i = 0; i <= num; i++) {
                clasPanel.add(new CreateInstancesJPanel());
                propPanel.add(new CreateInstancesJPanel());
        } 
        }else if(var==1){
            clasFinal = new ArrayList<ClassInstances>();
            propFinal = new ArrayList<PropertyInstances>();
            int contI=0,contP=0;

            ListIterator ci,pi;

            clasInst = ContentMainJFrame.getInstancias().get(GroupTestsJPanel.getSelectedTabed()).getClassInstances();
            setNomInstanciasTextField(ContentMainJFrame.getInstancias().get(GroupTestsJPanel.getSelectedTabed()).getNombre());
            setDescInstanciasTextArea(ContentMainJFrame.getInstancias().get(GroupTestsJPanel.getSelectedTabed()).getDescripcion());

            ci = clasInst.listIterator();
            while(ci.hasNext()){ 
                ClassInstances cI = (ClassInstances) ci.next();
                CreateInstancesJPanel instClas = new CreateInstancesJPanel();
                instClas.setInstance(cI.getClassInstance());
                commentPane = instClas.getComment();
                commentPane.setComent(cI.getComment());
                instClas.setComment(commentPane);
                clasPanel.add(instClas);
                clasFinal.add(cI);
                contI++;
                if(AddInstancesJPanel.isStateSeeInst()==true){
                    commentPane.getComentTextArea().setEditable(false);
                    instClas.getInstanciaTextField().setEditable(false);
                }
            }

            propInst = ContentMainJFrame.getInstancias().get(GroupTestsJPanel.getSelectedTabed()).getPropertyInstances();

            pi = propInst.listIterator();
            while(pi.hasNext()){
                PropertyInstances pI = (PropertyInstances) pi.next();
                CreateInstancesJPanel instProp = new CreateInstancesJPanel();
                instProp.setInstance(pI.getPropertyInstance());
                commentPane = instProp.getComment();
                commentPane.setComent(pI.getComment());
                instProp.setComment(commentPane);
                propPanel.add(instProp);
                propFinal.add(pI);
                contP++;
                if(AddInstancesJPanel.isStateSeeInst()==true){
                    commentPane.getComentTextArea().setEditable(false);
                    instProp.getInstanciaTextField().setEditable(false);
                }
            }
            if(AddInstancesJPanel.isStateSeeInst()==false){
                for (int j = 0; j <= (num-contI); j++) {
                    clasPanel.add(new CreateInstancesJPanel());
                }
                for (int k = 0; k <= (num-contP); k++) {
                    propPanel.add(new CreateInstancesJPanel());
                }
            }else{
                formatosButton.setEnabled(false);
                guardarInstButton.setVisible(false);
                limpiarInstButton.setVisible(false);
                newClasButton.setVisible(false);
                newPropButton.setVisible(false);
                jButton1.setVisible(false);
            }
        }
    }
    
    public AddInstancesClasPropJDialog(Frame parent, boolean modal,int num,
            ArrayList<ScenarioTest> scenarioT) {
        
        super(parent, modal);  
        this.setTitle("Asociar Instancias");
        this.setModal(false);
        clasFinal = new ArrayList<ClassInstances>();
        propFinal = new ArrayList<PropertyInstances>();
        int contI=0,contP=0;
        initComponents();
        setSeleccionado(true);
        clasPanel.setLayout(new BoxLayout(getClasPanel(), BoxLayout.Y_AXIS));
        propPanel.setLayout(new BoxLayout(getPropPanel(), BoxLayout.Y_AXIS));
        clasPropPanel.setLayout(new BoxLayout(clasPropPanel, BoxLayout.Y_AXIS));
        clasPropPanel.add(new CreateInstancesTextAreaJPanel(),0);

        ListIterator ci,pi;
        ScenarioTest sT = scenarioT.get(this.indexVect);
        setNomInstanciasTextField(sT.getInstancias().getNombre());
        setDescInstanciasTextArea(sT.getInstancias().getDescripcion());
        clasInst = sT.getInstancias().getClassInstances();
        ci = clasInst.listIterator();
        while(ci.hasNext()){ 
            ClassInstances cI = (ClassInstances) ci.next();
            CreateInstancesJPanel instClas = new CreateInstancesJPanel();
            instClas.setInstance(cI.getClassInstance());
            commentPane = instClas.getComment();
            commentPane.setComent(cI.getComment());
            instClas.setComment(commentPane);
            clasPanel.add(instClas);
            clasFinal.add(cI);
            contI++;
        }
  
        propInst = sT.getInstancias().getPropertyInstances();
        pi = propInst.listIterator();
        while(pi.hasNext()){
            PropertyInstances pI = (PropertyInstances) pi.next();
            CreateInstancesJPanel instProp = new CreateInstancesJPanel();
            instProp.setInstance(pI.getPropertyInstance());
            commentPane = instProp.getComment();
            commentPane.setComent(pI.getComment());
            instProp.setComment(commentPane);
            propPanel.add(instProp);
            propFinal.add(pI);
            contP++;
        }
            
        for (int j = 0; j <= (num-contI); j++) {
            clasPanel.add(new CreateInstancesJPanel());
        }
        for (int k = 0; k <= (num-contP); k++) {
            propPanel.add(new CreateInstancesJPanel());
        }
    }  
    
    public AddInstancesClasPropJDialog(Frame parent, boolean modal,int num,
        ScenarioTest sT) {
        
        super(parent, modal);
        this.setTitle("Asociar Instancias");
        this.setModal(false);
        clasFinal = new ArrayList<ClassInstances>();
        propFinal = new ArrayList<PropertyInstances>();
        int contI=0,contP=0;
        initComponents();
        setSeleccionado(true);
        clasPanel.setLayout(new BoxLayout(getClasPanel(), BoxLayout.Y_AXIS));
        propPanel.setLayout(new BoxLayout(getPropPanel(), BoxLayout.Y_AXIS));
        clasPropPanel.setLayout(new BoxLayout(clasPropPanel, BoxLayout.Y_AXIS));
        clasPropPanel.add(new CreateInstancesTextAreaJPanel(),0);

        setNomInstanciasTextField(sT.getInstancias().getNombre());
        setDescInstanciasTextArea(sT.getInstancias().getDescripcion());
        ListIterator ci,pi;
        clasInst = sT.getInstancias().getClassInstances();
        ci = clasInst.listIterator();
        while(ci.hasNext()){ 
            ClassInstances cI = (ClassInstances) ci.next();
            CreateInstancesJPanel instClas = new CreateInstancesJPanel();
            instClas.setInstance(cI.getClassInstance());
            commentPane = instClas.getComment();
            commentPane.setComent(cI.getComment());
            instClas.setComment(commentPane);
            clasPanel.add(instClas);
            clasFinal.add(cI);
            contI++;
        }
  
        propInst = sT.getInstancias().getPropertyInstances();
        pi = propInst.listIterator();
        while(pi.hasNext()){
            PropertyInstances pI = (PropertyInstances) pi.next();
            CreateInstancesJPanel instProp = new CreateInstancesJPanel();
            instProp.setInstance(pI.getPropertyInstance());
            commentPane = instProp.getComment();
            commentPane.setComent(pI.getComment());
            instProp.setComment(commentPane);
            propPanel.add(instProp);
            propFinal.add(pI);
            contP++;
        }
            
        for (int j = 0; j <= (num-contI); j++) {
            clasPanel.add(new CreateInstancesJPanel());
        }
        for (int k = 0; k <= (num-contP); k++) {
            propPanel.add(new CreateInstancesJPanel());
        }
    }
    
    public AddInstancesClasPropJDialog(Frame parent, boolean modal,String textName) {
        
        super(parent, modal);
        this.setTitle("Asociar Instancias");
        this.setModal(false);
        setNombreFichero(textName);
        setSeleccionado(true);
        initComponents();
        int contP=0,contC=0;
        clasFinal = new ArrayList<ClassInstances>();
        propFinal = new ArrayList<PropertyInstances>();
        ArrayList<ClassInstances> al;
        ArrayList<PropertyInstances> la;
        Instancias inst;
        ListIterator li,il;
        clasPanel.setLayout(new BoxLayout(getClasPanel(), BoxLayout.Y_AXIS));
        propPanel.setLayout(new BoxLayout(getPropPanel(), BoxLayout.Y_AXIS));  
        clasPropPanel.setLayout(new BoxLayout(clasPropPanel, BoxLayout.Y_AXIS));
        clasPropPanel.add(new CreateInstancesTextAreaJPanel(),0);
        try{
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(textName)));
            inst = (Instancias) decoder.readObject();
            setDescInstanciasTextArea(inst.getDescripcion());
            setNomInstanciasTextField(inst.getNombre());
            al = inst.getClassInstances();
            li = al.listIterator();
            while(li.hasNext()){   
                ClassInstances cI = (ClassInstances) li.next();
                CreateInstancesJPanel instClas = new CreateInstancesJPanel();
                contC++;
                instClas.setInstance(cI.getClassInstance());
                commentPane = instClas.getComment();
                commentPane.setComent(cI.getComment());
                instClas.setComment(commentPane);
                clasPanel.add(instClas);
                clasFinal.add(cI);
            }
            la = inst.getPropertyInstances();
            il = la.listIterator();
            while(il.hasNext()){   
                PropertyInstances pI = (PropertyInstances) il.next();
                CreateInstancesJPanel instProp = new CreateInstancesJPanel();
                contP++;
                instProp.setInstance(pI.getPropertyInstance());
                commentPane = instProp.getComment();
                commentPane.setComent(pI.getComment());
                instProp.setComment(commentPane);
                propPanel.add(instProp);
                propFinal.add(pI);
            }
            decoder.close();    
        }catch(FileNotFoundException e){
            e.printStackTrace();
        } 
        if(contC<8){
            for (int j=0; j<=(8-contC); j++) {
                clasPanel.add(new CreateInstancesJPanel());
            }
        }
        if(contP<8){
            for (int k=0; k<=(8-contP); k++) {
                propPanel.add(new CreateInstancesJPanel());
            }
        }
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
        newClasButton = new javax.swing.JButton();
        newPropButton = new javax.swing.JButton();
        instancesTabbedPane = new javax.swing.JTabbedPane();
        classScrollPane = new javax.swing.JScrollPane();
        clasPanel = new javax.swing.JPanel();
        propScrollPane = new javax.swing.JScrollPane();
        propPanel = new javax.swing.JPanel();
        textAreaScrollPane = new javax.swing.JScrollPane();
        clasPropPanel = new javax.swing.JPanel();
        formatosButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        contentDescPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descInstanciasTextArea = new javax.swing.JTextArea();
        nomInstanciasTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Añada las instancias que quiera a su ontología:");

        guardarInstButton.setText("Asociar");
        guardarInstButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarInstButtonActionPerformed(evt);
            }
        });

        cancelarInstButton.setText("Cancelar");
        cancelarInstButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarInstButtonActionPerformed(evt);
            }
        });

        limpiarInstButton.setText(" Limpiar Seleccion");
        limpiarInstButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarInstButtonActionPerformed(evt);
            }
        });

        newClasButton.setText("Nueva clase");
        newClasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newClasButtonActionPerformed(evt);
            }
        });

        newPropButton.setText("Nueva propiedad");
        newPropButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPropButtonActionPerformed(evt);
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

        formatosButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/help.gif"))); // NOI18N
        formatosButton.setText("Formatos Permitidos");
        formatosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formatosButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Borrar Seleccion");

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(instancesTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 230, Short.MAX_VALUE)
                        .addComponent(formatosButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                        .addComponent(limpiarInstButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 281, Short.MAX_VALUE)
                        .addComponent(newClasButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newPropButton))
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(cancelarInstButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 581, Short.MAX_VALUE)
                        .addComponent(guardarInstButton)))
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
                    .addComponent(newPropButton)
                    .addComponent(newClasButton)
                    .addComponent(limpiarInstButton)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarInstButton)
                    .addComponent(guardarInstButton)))
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
                .addContainerGap(230, Short.MAX_VALUE))
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
                .addContainerGap(298, Short.MAX_VALUE))
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
        try {
// TODO add your handling code here:
            jena = jenaInterface.getJena();
            Auxiliar auxiliar = new Auxiliar();
            jena.addReasoner("file:".concat(MainJPanel.getFisicalOntologyTextField()));
            ValidarTests validar = new ValidarTests();
            String[] ciClas = null;
            String[] ciInd = null;
            ArrayList failClas = new ArrayList();
            ArrayList failProp = new ArrayList();
            int failGroupClas = 0;
            int failGroupProp = 0;
            setInstanciaValida(true);
            clasInst = new ArrayList<ClassInstances>();
            propInst = new ArrayList<PropertyInstances>();
            instancias = new Instancias();
            int aux = 0;
            boolean instanciaSinNombre = false;
            int totalClas = getClasPanel().getComponentCount();

            String nombreInstancias = nomInstanciasTextField.getText();
            if (getInstancesTabbedPane() != 2) {
                setQueryValida(true);
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
                                boolean v = jena.addInstanceClass(MainJPanel.getNamespaceOntologyTextField(), ciClas[0], ciInd[0]);
                                if (v == false) {
                                    panelInst.getInstanciaTextField().setForeground(Color.RED);
                                    setInstanciaValida(false);
                                } else {
                                    panelInst.getInstanciaTextField().setForeground(Color.BLACK);
                                }
                            }
                        }

                        if (validar.validarInstanciaClase(query) == true && isInstanciaValida() == true) {
                            if (!query.equals("") && !coment.equals("")) {
                                ClassInstances cI = new ClassInstances(query, coment);
                                clasInst.add(cI);
                            } else if (!query.equals("") && coment.equals("")) {
                                ClassInstances cI = new ClassInstances(query);
                                clasInst.add(cI);
                            } else if (query.equals("") && !coment.equals("")) {
                                aux = 1;
                            }
                            failClas.add(i, 0);
                        } else if (validar.validarInstanciaClase(query) == false) {
                            if (!query.equals("")) {
                                failClas.add(i, 1);
                                setQueryValida(false);
                            }
                        } else {
                            failClas.add(i, 0);
                        }
                    }
                    int totalProp = getPropPanel().getComponentCount();
                    for (int i = 0; i < totalProp; i++) {
                        CreateInstancesJPanel panelInst = (CreateInstancesJPanel) getPropPanel().getComponent(i);
                        String query = panelInst.getQuery();
                        AddComentJDialog comentPane = panelInst.getComment();
                        String coment = comentPane.getComent();
                        if (!query.equals("")) {
                            if (validar.validarInstanciaPropiedad(query) == true) {
                                ciClas = query.split(patron1);
                                ciInd = ciClas[1].split(patron2);
                                boolean v = jena.addInstanceProperty(MainJPanel.getNamespaceOntologyTextField(), ciClas[0], ciInd[0]);
                                if (v == false) {
                                    panelInst.getInstanciaTextField().setForeground(Color.RED);
                                    setInstanciaValida(false);
                                } else {
                                    panelInst.getInstanciaTextField().setForeground(Color.BLACK);
                                }
                            }
                        }

                        if (validar.validarInstanciaPropiedad(query) == true && isInstanciaValida() == true && instanciaSinNombre == false) {
                            if (!query.equals("") && !coment.equals("")) {
                                PropertyInstances pI = new PropertyInstances(query, coment);
                                propInst.add(pI);
                            } else if (!query.equals("") && coment.equals("")) {
                                PropertyInstances pI = new PropertyInstances(query);
                                propInst.add(pI);
                            } else if (query.equals("") && !coment.equals("")) {
                                aux = 1;
                            }
                            failProp.add(i, 0);
                        } else if (validar.validarInstanciaPropiedad(query) == false) {
                            if (!query.equals("")) {
                                failProp.add(i, 1);
                                setQueryValida(false);
                            }
                        } else {
                            failProp.add(i, 0);
                        }
                    }
                }
            } else {
                setQueryValida(true);
                String patron = "\\\n";
                CreateInstancesTextAreaJPanel conjunto = (CreateInstancesTextAreaJPanel) clasPropPanel.getComponent(0);
                String conjuntoClase = conjunto.getClaseTextArea().trim();
                String conjuntoProp = conjunto.getPropiedadTextArea().trim();
                String[] clas = conjuntoClase.split(patron);
                String[] prop = conjuntoProp.split(patron);
                if (nombreInstancias.equals("")) {
                    instanciaSinNombre = true;
                } else {
                    for (int i = 0; i < clas.length; i++) {
                        if (!clas[i].equals("")) {
                            if (validar.validarInstanciaClase(clas[i]) == true) {
                                ciClas = clas[i].split(patron1);
                                ciInd = ciClas[1].split(patron2);
                                boolean v = jena.addInstanceClass(MainJPanel.getNamespaceOntologyTextField(), ciClas[0], ciInd[0]);
                                if (v == false) {
                                    conjunto.getClaseArea().setForeground(Color.RED);
                                    setInstanciaValida(false);
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
                                setQueryValida(false);
                            }
                        }
                    }
                    for (int i = 0; i < prop.length; i++) {
                        if (!prop[i].equals("")) {
                            if (validar.validarInstanciaPropiedad(prop[i]) == true) {
                                ciClas = prop[i].split(patron1);
                                ciInd = ciClas[1].split(patron2);
                                boolean v = jena.addInstanceProperty(MainJPanel.getNamespaceOntologyTextField(), ciClas[0], ciInd[0]);
                                if (v == false) {
                                    conjunto.getPropiedadArea().setForeground(Color.RED);
                                    setInstanciaValida(false);
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
                                setQueryValida(false);
                            }
                        }
                    }
                }
            }

            if (isQueryValida() == true && isInstanciaValida() == true && instanciaSinNombre == false) {
                if (aux == 1) {
                    JOptionPane.showMessageDialog(frame, "Si no añade ninguna instancia a sus comentarios," + "éstos se perderán", "Warning Message", JOptionPane.WARNING_MESSAGE);
                } else {
                    instancias.setClassInstances(clasInst);
                    instancias.setPropertyInstances(propInst);
                    instancias.setDescripcion(getDescInstanciasTextArea());
                    instancias.setNombre(getNomInstanciasTextField());
                    instancias.setType("Instancias");
                    if (AddInstancesJPanel.isStateExaminar() == true) {
                        if (compararListaClase(clasInst, clasFinal) && compararListaPropiedad(propInst, propFinal)) {
                            setInstances(instancias);
                            this.setVisible(false);
                        } else {
                            Object[] options = {"Sobreescribir", "Crear nuevo", "No guardar"};
                            int n = JOptionPane.showOptionDialog(frame, "El conjunto de " + "instancias ha cambiado. ¿Qué desea hacer?", "Question", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
                            if (n == JOptionPane.YES_OPTION) {
                                setInstances(instancias);
                                crearArchivoDeInstancias(AddInstancesJPanel.getArchivoSeleccionado());
                                this.setVisible(false);
                            } else if (n == JOptionPane.NO_OPTION) {
                                crearArchivoDeInstancias(instancias);
                                setInstances(instancias);
                                this.setVisible(false);
                            } else if (n == JOptionPane.CANCEL_OPTION) {
                                setInstances(instancias);
                                this.setVisible(false);
                            }
                            auxiliar.setYaEligioGuardarInstancias(true);
                        }
                    } else if (AddInstancesJPanel.isStateAsociar() == true) {
                        if ((clasInst.size() > 0 || propInst.size() > 0) && (clasFinal == null || propFinal == null)) {
                            Component comp = null;
                            int n = JOptionPane.showConfirmDialog(comp, "¿Quiere guardar " + "este conjunto de instancias para futuras pruebas?", "Guardar Instancias", JOptionPane.YES_NO_OPTION);
                            if (n == JOptionPane.YES_OPTION) {
                                auxiliar.crearArchivoDeInstancias(instancias);
                                auxiliar.setInstances(instancias);
                                this.setVisible(false);
                            } else {
                                auxiliar.setInstances(instancias);
                                this.setVisible(false);
                            }
                        } else if ((clasInst.size() > 0 || propInst.size() > 0) && (!compararListaClase(clasInst, clasFinal) || !compararListaPropiedad(propInst, propFinal))) {
                            Object[] options = {"Sobreescribir", "Crear nuevo", "No guardar"};
                            int n = JOptionPane.showOptionDialog(frame, "El conjunto de " + "instancias ha cambiado. ¿Qué desea hacer?", "Question", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
                            if (n == JOptionPane.YES_OPTION) {
                                setInstances(instancias);
                                crearArchivoDeInstancias(AddInstancesJPanel.getArchivoSeleccionado());
                                this.setVisible(false);
                            } else if (n == JOptionPane.NO_OPTION) {
                                crearArchivoDeInstancias(instancias);
                                setInstances(instancias);
                                this.setVisible(false);
                            } else if (n == JOptionPane.CANCEL_OPTION) {
                                setInstances(instancias);
                                this.setVisible(false);
                            }
                        } else {
                            this.setVisible(false);
                        }
                    } else {
                        this.setVisible(false);
                    }
                }
            } else if (instanciaSinNombre == true) {
                JOptionPane.showMessageDialog(frame, "El nombre para el conjunto de instancias" + "es obligatorio.", "Warning Message", JOptionPane.WARNING_MESSAGE);
            } else if (isInstanciaValida() == false) {
                JOptionPane.showMessageDialog(frame, "Las instancias marcadas" + "en rojo no se corresponden con la definicion de su ontologia. " + "Por favor, reviselas.", "Warning Message", JOptionPane.WARNING_MESSAGE);
            } else if (isQueryValida() == false) {
                JOptionPane.showMessageDialog(frame, "El formato de las instancias marcadas" + "en rojo es incorrecto. Por favor, revise la documentación para ver" + "los formatos permitidos.", "Warning Message", JOptionPane.WARNING_MESSAGE);
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
                    CreateInstancesTextAreaJPanel conjunto = (CreateInstancesTextAreaJPanel) clasPropPanel.getComponent(0);
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
        } catch (ExceptionReadOntology ex) {
            JOptionPane.showMessageDialog(frame,"No se pueden asociar las instancias. La ontologia" +
                    "no se puede leer.", "Warning Message",JOptionPane.WARNING_MESSAGE);
        }
}//GEN-LAST:event_guardarInstButtonActionPerformed

public void crearArchivoDeInstancias(Instancias instancias){
    
    String nombreArch=null;
    String nameInstances=null;
    
    nombreArch = JOptionPane.showInputDialog(null,"Introduzca el nombre para el " +
                "archivo con este conjunto de instancias","Nombre del archivo",
                JOptionPane.QUESTION_MESSAGE);
            
    if(nombreArch.endsWith(".xml")){
        nameInstances=nombreArch;
    }else{
        nameInstances=nombreArch.concat(".xml");
    }
            
    try{
        XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new 
                            FileOutputStream(Configuration.getPathInstancias()+"/"+nameInstances)));
        e.writeObject(instancias);
        e.close();
    }catch (FileNotFoundException ex) {
        ex.printStackTrace();
     }
}

public boolean yaExisteInstancia(String nombre){
    ArrayList<ScenarioTest> listaEsce = MainJPanel.getCollectionTest().getScenariotest();
        for(int i=0;i<listaEsce.size();i++){
            ScenarioTest scenario = listaEsce.get(i);
            String n = scenario.getInstancias().getNombre();
            if(n.equals(nombre)){
                return true;
            }
        }
    return false;
}

public void crearArchivoDeInstancias(String nombreFichero){
        
    try{
        XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new 
                            FileOutputStream(nombreFichero)));
        e.writeObject(instancias);
        e.close();
    }catch (FileNotFoundException ex) {
        ex.printStackTrace();
     }
}

public void crearArchivoDeTests(String nombreFichero){
    
    try{
        XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new 
                            FileOutputStream(nombreFichero)));
        e.writeObject(MainJPanel.getCollectionTest());
        e.close();
    }catch (FileNotFoundException ex) {
        ex.printStackTrace();
     }
}

private boolean compararListaClase(ArrayList<ClassInstances> arrayList1, 
        ArrayList<ClassInstances> arrayList2) {
      
    if(arrayList1!=null && arrayList2!=null){
        if(arrayList1.size() == arrayList2.size()){
            for(int i=0;i<arrayList1.size();i++){
                String clasInstance1 = arrayList1.get(i).getClassInstance();
                String coment1 = arrayList1.get(i).getComment();
                String clasInstance2 = arrayList2.get(i).getClassInstance();
                String coment2 = arrayList2.get(i).getComment();
                if(!clasInstance1.equals(clasInstance2)){
                    return false;
                }
                if(!coment1.equals(coment2)){
                    return false;
                }
            }
        }else{
            return false;
        }
    }else{
        return false;
    }
    return true;
}  

private boolean compararListaPropiedad(ArrayList<PropertyInstances> arrayList1, 
        ArrayList<PropertyInstances> arrayList2) {
         
    if(arrayList1.size() == arrayList2.size()){
        for(int i=0;i<arrayList1.size();i++){
            String clasInstance1 = arrayList1.get(i).getPropertyInstance();
            String coment1 = arrayList1.get(i).getComment();
            String clasInstance2 = arrayList2.get(i).getPropertyInstance();
            String coment2 = arrayList2.get(i).getComment();
            if(!clasInstance1.equals(clasInstance2)){
                return false;
            }
            if(!coment1.equals(coment2)){
                return false;
            }
        }
    }else{
        return false;
    }
    return true;
} 

private void cancelarInstButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarInstButtonActionPerformed
// TODO add your handling code here:
    this.setVisible(false);
}//GEN-LAST:event_cancelarInstButtonActionPerformed

private void limpiarInstButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarInstButtonActionPerformed
// TODO add your handling code here:
    int totalClas = getClasPanel().getComponentCount();
    for(int i=0; i<totalClas; i++){
        CreateInstancesJPanel panelInst = (CreateInstancesJPanel) getClasPanel().getComponent(i);
        panelInst.setInstance("");
        AddComentJDialog comentPane = panelInst.getComment();
        comentPane.setComent("");
        ClassInstances cI = new ClassInstances();
        clasInst.add(cI);
    }
    int totalProp = getClasPanel().getComponentCount();
    for(int i=0; i<totalProp; i++){
        CreateInstancesJPanel panelInst = (CreateInstancesJPanel) getPropPanel().getComponent(i);
        panelInst.setInstance("");
        AddComentJDialog comentPane = panelInst.getComment();
        comentPane.setComent("");
        PropertyInstances pI = new PropertyInstances();
        propInst.add(pI);
    }
}//GEN-LAST:event_limpiarInstButtonActionPerformed

private void newClasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newClasButtonActionPerformed
// TODO add your handling code here:
        getClasPanel().add(new CreateInstancesJPanel());
    this.validate();
}//GEN-LAST:event_newClasButtonActionPerformed

private void newPropButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPropButtonActionPerformed
// TODO add your handling code here:
        getPropPanel().add(new CreateInstancesJPanel());
    this.validate();
}//GEN-LAST:event_newPropButtonActionPerformed

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


private void setInstances(Instancias instancias)
{  
    int tab = GroupTestsJPanel.getSelectedTabed();
    ContentMainJFrame.getInstancias().set(tab, instancias);
}

public void copiarAInstancesTextArea(){
    String textoClase="",textoProp="";
    
    CreateInstancesTextAreaJPanel conjunto = (CreateInstancesTextAreaJPanel) clasPropPanel.getComponent(0);
    clasInst = new ArrayList<ClassInstances>();
    propInst = new ArrayList<PropertyInstances>();
    int aux=0;
    int totalClas = getClasPanel().getComponentCount();
    
    for(int i=0; i<totalClas; i++){
        CreateInstancesJPanel panelInst = (CreateInstancesJPanel) getClasPanel().getComponent(i);
        String query = panelInst.getQuery();
        AddComentJDialog comentPane = panelInst.getComment();
        String coment = comentPane.getComent();
        if(!query.equals("")){
            if(textoClase.equals("")){
                textoClase = query+"\n";
            }else{
                textoClase = textoClase+query+"\n";
            }
        }else if(query.equals("") && !coment.equals("")){
            aux=1;
        }
    }
    int totalProp = getPropPanel().getComponentCount();
    for(int i=0; i< totalProp; i++){
        CreateInstancesJPanel panelInst = (CreateInstancesJPanel) getPropPanel().getComponent(i);
        String query = panelInst.getQuery();
        AddComentJDialog comentPane = panelInst.getComment();
        String coment = comentPane.getComent();
        if(!query.equals("")){
            if(textoProp.equals("")){
                textoProp = query+"\n";
            }else{
                textoProp = textoProp+query+"\n";
            }
        }else if(query.equals("") && !coment.equals("")){
            aux=1;
        }
    }
    conjunto.setClaseTextArea(textoClase);
    conjunto.setPropiedadTextArea(textoProp);
    if(AddInstancesJPanel.isStateSeeInst()==true){
        conjunto.getClaseArea().setEditable(false);
        conjunto.getPropiedadArea().setEditable(false);
    }
    this.validate();
    
    for(int i=0;i<totalClas;i++){
            getClasPanel().remove(getClasPanel().getComponent(i));
            getClasPanel().add(new CreateInstancesJPanel(),i);
    }
        getClasPanel().validate();
    for(int i=0;i<totalProp;i++){
            getPropPanel().remove(getPropPanel().getComponent(i));
            getPropPanel().add(new CreateInstancesJPanel(),i);
    }
        getPropPanel().validate();
    
}

public void copiarAInstancesAyuda(){
    String patron="\\\n";
    CreateInstancesTextAreaJPanel conjunto = (CreateInstancesTextAreaJPanel) clasPropPanel.getComponent(0);
    String conjuntoClase = conjunto.getClaseTextArea().trim();
    String conjuntoProp = conjunto.getPropiedadTextArea().trim();
    String[] clas = conjuntoClase.split(patron);
    String[] prop = conjuntoProp.split(patron);
    int j=0;
    for(int i=0;i<clas.length;i++){
        if(!clas[i].equals("")){
            CreateInstancesJPanel panelInst = (CreateInstancesJPanel) getClasPanel().getComponent(j);
            panelInst.setInstance(clas[i]);
            if(AddInstancesJPanel.isStateSeeInst()==true){
                panelInst.getInstanciaTextField().setEditable(false);
            }
            j++;
        }
    }
    j=0;
    for(int i=0;i<prop.length;i++){
        if(!prop[i].equals("")){
            CreateInstancesJPanel panelProp = (CreateInstancesJPanel) getPropPanel().getComponent(j);
            panelProp.setInstance(prop[i]);
            if(AddInstancesJPanel.isStateSeeInst()==true){
                panelProp.getInstanciaTextField().setEditable(false);
            }
            j++;
        }
    }      
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton limpiarInstButton;
    private javax.swing.JButton newClasButton;
    private javax.swing.JButton newPropButton;
    private javax.swing.JTextField nomInstanciasTextField;
    private static javax.swing.JPanel propPanel;
    private javax.swing.JScrollPane propScrollPane;
    private javax.swing.JScrollPane textAreaScrollPane;
    // End of variables declaration//GEN-END:variables

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

}
