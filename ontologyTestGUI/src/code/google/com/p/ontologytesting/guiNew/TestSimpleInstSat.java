/*
 * contentInstTabedPanel.java
 *
 * Created on 11 de septiembre de 2008, 18:14
 */

package code.google.com.p.ontologytesting.guiNew;

import code.google.com.p.ontologytesting.model.*;
import code.google.com.p.ontologytesting.persistence.SaveTest;
import code.google.com.p.ontologytesting.model.jenainterfaz.*;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;


/**
 *
 * @author  saruskas
 */
public class TestSimpleInstSat extends javax.swing.JPanel implements PropertyChangeListener {

    private static ValidarTests validarTests;
    private static TestInstancesTFJPanel test;
    private static DescripcionJPanel descPanel = null;
    private static boolean testSinNombre;
    private static int actualSubTabInst=0;
    private static boolean validoInst;
    private static JPanel panelAyudaInst;
    private static int totalInst;
    static final int desktopWidth = 750;
    static final int desktopHeight = 600;
    static JFrame frame;
    private static boolean ambosNecesarios;
    public static boolean seleccionado;
    private static List inst;
    private static boolean continuarSinInstancias;
    private static boolean testYaExiste;
    private static int hayUnaConsulta=0;
    private Component comp;
    private static TestInstancesTFJPanel pInst;
    private static List<QueryOntology> queryTest;
    private static TestInstancesTextJPanel texto;
    private ScenarioTest scenario;
    private boolean continuar;
    private SaveTest saveTest;
    private AddInstancesClasPropJDialog addInst;
    private String nombreTest = "",descTest = "";
    private static OntologyTestResult testresult;
    private OntologyTestCase testcase;
    private ScenarioTest scenarioAEditar;
    private boolean importado;
    private boolean soloEjecutar;
    private ProgressMonitor progressMonitor;
    private Task task;
    private ControladorTests controlador;
    private Utils utils;
    
    /** Creates new form contentInstTabedPanel */
    /*public TestSimpleInstSat(int type) {
        initComponents();
        TestInstancesTFJPanel.setContadorInstSat(0);
        descripcionJPanel.setLayout(new FlowLayout());
        descripcionJPanel.add(new DescripcionJPanel());
        opcionTextInstPanel.setLayout(new BoxLayout(getOpcionTextInstPanel(), BoxLayout.Y_AXIS));
        instAyudaPanel.setLayout(new BoxLayout(getInstAyudaPanel(), BoxLayout.Y_AXIS));
        for (int i = 1; i <= 10; i++) {  
            instAyudaPanel.add(new TestInstancesTFJPanel());   
        }
        opcionTextInstPanel.add(new TestInstancesTextJPanel());
        setScenarioAEditar(null);
        if(type==0){
            setScenario(new ScenarioTest(TipoTest.INST)); 
        }else{
            setScenario(new ScenarioTest(TipoTest.SAT)); 
        }
        setImportado(false);
        setSoloEjecutar(false);
    }*/
    
    public TestSimpleInstSat(ScenarioTest s){
        initComponents();
        TestInstancesTFJPanel.setContadorInstSat(0);
        controlador = ControladorTests.getInstance();
        descripcionJPanel.setLayout(new FlowLayout());
        descripcionJPanel.add(new DescripcionJPanel());
        opcionTextInstPanel.setLayout(new BoxLayout(getOpcionTextInstPanel(), BoxLayout.Y_AXIS));
        instAyudaPanel.setLayout(new BoxLayout(getInstAyudaPanel(), BoxLayout.Y_AXIS));
        
        opcionTextInstPanel.add(new TestInstancesTextJPanel());
        int cont=1;
        List<QueryOntology> listaQuerys = s.getQueryTest(); 
        ListIterator qi;
        qi = listaQuerys.listIterator();
        descPanel = (DescripcionJPanel) descripcionJPanel.getComponent(0);
        descPanel.setNombreTextField(s.getNombre());
        descPanel.setDescTextArea(s.getDescripcion());
        while(qi.hasNext()){ 
            QueryOntology cI = (QueryOntology) qi.next();
            TestInstancesTFJPanel panelInstances = new TestInstancesTFJPanel();
            AddComentJDialog comentPane = panelInstances.getComment();
            comentPane.setComent(cI.getComment());
            panelInstances.setComment(comentPane);
            panelInstances.setQuery(cI.getQuery());
            String res = cI.getResultexpected();
            if(res.equals("true")){
                panelInstances.setTrueTest(true);
            }else{
                panelInstances.setFalseTest(true);
            }
            instAyudaPanel.add(panelInstances,cont);
            cont++;
        }
        if(cont<10){
            for (int i = cont; i <= 10; i++) {  
                instAyudaPanel.add(new TestInstancesTFJPanel());   
            }
        }
        utils = new Utils();
        scenarioAEditar = new ScenarioTest(s);
        setScenario(s);
        setImportado(true);
        setSoloEjecutar(false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentInstTabedPanel = new javax.swing.JPanel();
        tabbedPaneInst = new javax.swing.JTabbedPane();
        opcionAyudaInstPanel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        instAyudaPanel = new javax.swing.JPanel();
        labelInstPanel = new javax.swing.JPanel();
        labelInstLabel = new javax.swing.JLabel();
        opcionTextInstPanel = new javax.swing.JPanel();
        descripcionJPanel = new javax.swing.JPanel();
        contentGuardarEjecutarPanel = new javax.swing.JPanel();
        guardarButton = new javax.swing.JButton();
        ejecutarButton = new javax.swing.JButton();
        guardarEjecutarButton = new javax.swing.JButton();

        tabbedPaneInst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabbedPaneInstMouseClicked(evt);
            }
        });

        labelInstLabel.setText("                      CONSULTAS                               RESULTADO ESPERADO");

        javax.swing.GroupLayout labelInstPanelLayout = new javax.swing.GroupLayout(labelInstPanel);
        labelInstPanel.setLayout(labelInstPanelLayout);
        labelInstPanelLayout.setHorizontalGroup(
            labelInstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelInstPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(135, Short.MAX_VALUE))
        );
        labelInstPanelLayout.setVerticalGroup(
            labelInstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelInstPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout instAyudaPanelLayout = new javax.swing.GroupLayout(instAyudaPanel);
        instAyudaPanel.setLayout(instAyudaPanelLayout);
        instAyudaPanelLayout.setHorizontalGroup(
            instAyudaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(instAyudaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(431, Short.MAX_VALUE))
        );
        instAyudaPanelLayout.setVerticalGroup(
            instAyudaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(instAyudaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(501, Short.MAX_VALUE))
        );

        jScrollPane6.setViewportView(instAyudaPanel);

        javax.swing.GroupLayout opcionAyudaInstPanelLayout = new javax.swing.GroupLayout(opcionAyudaInstPanel);
        opcionAyudaInstPanel.setLayout(opcionAyudaInstPanelLayout);
        opcionAyudaInstPanelLayout.setHorizontalGroup(
            opcionAyudaInstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcionAyudaInstPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 803, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        opcionAyudaInstPanelLayout.setVerticalGroup(
            opcionAyudaInstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcionAyudaInstPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE))
        );

        tabbedPaneInst.addTab("Formato ayuda", opcionAyudaInstPanel);

        javax.swing.GroupLayout opcionTextInstPanelLayout = new javax.swing.GroupLayout(opcionTextInstPanel);
        opcionTextInstPanel.setLayout(opcionTextInstPanelLayout);
        opcionTextInstPanelLayout.setHorizontalGroup(
            opcionTextInstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 825, Short.MAX_VALUE)
        );
        opcionTextInstPanelLayout.setVerticalGroup(
            opcionTextInstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );

        tabbedPaneInst.addTab("Formato Texto", opcionTextInstPanel);

        javax.swing.GroupLayout contentInstTabedPanelLayout = new javax.swing.GroupLayout(contentInstTabedPanel);
        contentInstTabedPanel.setLayout(contentInstTabedPanelLayout);
        contentInstTabedPanelLayout.setHorizontalGroup(
            contentInstTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 832, Short.MAX_VALUE)
            .addGroup(contentInstTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentInstTabedPanelLayout.createSequentialGroup()
                    .addComponent(tabbedPaneInst, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        contentInstTabedPanelLayout.setVerticalGroup(
            contentInstTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
            .addGroup(contentInstTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentInstTabedPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(tabbedPaneInst, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout descripcionJPanelLayout = new javax.swing.GroupLayout(descripcionJPanel);
        descripcionJPanel.setLayout(descripcionJPanelLayout);
        descripcionJPanelLayout.setHorizontalGroup(
            descripcionJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 822, Short.MAX_VALUE)
        );
        descripcionJPanelLayout.setVerticalGroup(
            descripcionJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 145, Short.MAX_VALUE)
        );

        guardarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/disk.png"))); // NOI18N
        guardarButton.setText("Guardar");
        guardarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarButtonActionPerformed(evt);
            }
        });

        ejecutarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/cog.png"))); // NOI18N
        ejecutarButton.setText("Ejecutar");
        ejecutarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarButtonActionPerformed(evt);
            }
        });

        guardarEjecutarButton.setText("Guardar y Ejecutar");
        guardarEjecutarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarEjecutarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contentGuardarEjecutarPanelLayout = new javax.swing.GroupLayout(contentGuardarEjecutarPanel);
        contentGuardarEjecutarPanel.setLayout(contentGuardarEjecutarPanelLayout);
        contentGuardarEjecutarPanelLayout.setHorizontalGroup(
            contentGuardarEjecutarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentGuardarEjecutarPanelLayout.createSequentialGroup()
                .addContainerGap(463, Short.MAX_VALUE)
                .addComponent(guardarButton)
                .addGap(18, 18, 18)
                .addComponent(ejecutarButton)
                .addGap(18, 18, 18)
                .addComponent(guardarEjecutarButton)
                .addGap(16, 16, 16))
        );
        contentGuardarEjecutarPanelLayout.setVerticalGroup(
            contentGuardarEjecutarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentGuardarEjecutarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(guardarButton)
                .addComponent(ejecutarButton)
                .addComponent(guardarEjecutarButton))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(contentGuardarEjecutarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(descripcionJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(contentInstTabedPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(descripcionJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(contentInstTabedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentGuardarEjecutarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

private void tabbedPaneInstMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabbedPaneInstMouseClicked
// TODO add your handling code here:
    int subTab = getTabbedPaneInst();
    if(getActualSubTabInst()!=subTab){
        if(subTab==1){
            copiarDeAyudaATexto();
        }else{
            copiarDeTextoAAyuda();
        }
        setActualSubTabInst(subTab);
    }
}//GEN-LAST:event_tabbedPaneInstMouseClicked

private void guardarButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
// TODO add your handling code here:

}

private void ejecutarButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
// TODO add your handling code here:
    setSoloEjecutar(true);
    saveTest = new SaveTest();
    testcase = new OntologyTestCase();
    testresult = new OntologyTestResult();
    if(getTabbedPaneInst()==0){
        copiarTestAScenarioDesdeAyuda();
    }else if(getTabbedPaneInst()==1){
        copiarTestAScenarioDesdeSinAyuda();
    }
    if(continuar==true){
        if(continuarSinInstancias==true){
            ejecutar(0);
        }else{
            addInst = new AddInstancesClasPropJDialog(frame,true,this.getScenario());
            addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            addInst.setVisible(true);
        }
    }
}

private void guardarEjecutarButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                      
// TODO add your handling code here:

}


public boolean guardarTest(){
    if(getTabbedPaneInst()==0){
        copiarTestAScenarioDesdeAyuda();
    }else if(getTabbedPaneInst()==1){
        copiarTestAScenarioDesdeSinAyuda();
    }
    if(continuar==true){
        if(continuarSinInstancias==true){
            guardar();
            return true;
        }else{
            addInst = new AddInstancesClasPropJDialog(frame,true,this.getScenario());
            addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            addInst.setVisible(true);
            return false;
        }
    }else{
        return false;
    }   
}

public void guardar(){
    saveTest = new SaveTest();
    if(testYaExiste==true || isImportado()==true){
        if(scenario.equals(this.getScenarioAEditar())==false){
            Object[] options = {"Sobreescribir", "Cancelar"};
            int n = JOptionPane.showOptionDialog(frame, "El test ya existe o ha sido modificado. ¿Que desea hacer?", 
                    "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if (n == JOptionPane.YES_OPTION) {
                try {
                    saveTest.saveTestInMemory(scenario);
                    if(testYaExiste==true){
                        saveTest.replaceTestLocally(scenario);
                    }else{
                        saveTest.saveTestLocally(scenario);
                    }
                    setScenarioAEditar(new ScenarioTest(scenario));
                    setScenario(new ScenarioTest(scenario));
                    controlador.setTestInstSatGuardado(true);
                    JOptionPane.showMessageDialog(this.getParent(),"El test ha sido sobreescrito",
                    "Confirm Message",JOptionPane.INFORMATION_MESSAGE);
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(this.getParent(),"El test no ha sido guardado",
                    "Error Message",JOptionPane.ERROR_MESSAGE);
                }
            }else if (n == JOptionPane.NO_OPTION) {
            }
        }else{
            controlador.setTestInstSatGuardado(true);
            JOptionPane.showMessageDialog(this.getParent(),"No se han producido cambios en el test",
            "Confirm Message",JOptionPane.INFORMATION_MESSAGE);
        }
        setImportado(false);
    }else{ 
        try {
            saveTest.saveTestInMemory(scenario);
            saveTest.saveTestLocally(scenario);
            setScenarioAEditar(new ScenarioTest(scenario));
            setScenario(new ScenarioTest(scenario));
            controlador.setTestInstSatGuardado(true);
            JOptionPane.showMessageDialog(this.getParent(),"El test ha sido guardado",
            "Confirm Message",JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this.getParent(),"El test no ha sido guardado",
            "Error Message",JOptionPane.ERROR_MESSAGE);
        }
    }
}


public void ejecutar(int cuantos){
    if(isSoloEjecutar()==true){
        if(scenario.equals(this.getScenarioAEditar())==false){
            saveTest.replaceTestLocally(scenario);
        }else{
            saveTest.saveTestLocally(scenario);
        }
    }
    try{
        if(cuantos==0){
            testcase.runScenario(testresult, CollectionTest.getInstance(), this.getScenario());   
        }else if(cuantos==1){
            testcase.run(testresult, CollectionTest.getInstance());
        }
        new TreeResults(testresult);
        //JScrollPane scrollResults = panel.getResultsView();
        //JScrollPane scrollTree = panel.getTreeView();
        //resultTestPanel.aniadirResultado(scrollResults);
        //ListarTestsJPanel.aniadirTreeResult(scrollTree);
        //resultTestPanel.validate();
        /*resultTests.getContentPanelResults().add(panel);
        resultTests.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        resultTests.setVisible(true);
        resultTests.validate();*/
    } catch (ExceptionReadOntology ex) {
        new ExceptionReadOntology("La ontologia introducida no es valida." +
        "\nSolo pueden realizarse tests sobre documentos owl consistentes");
    }
}

public void guardarYEjecutar(){  
    if(testYaExiste==true || isImportado()==true){
        if(scenario.equals(this.getScenarioAEditar())==false){
            Object[] options = {"Sobreescribir", "Cancelar"};
            int n = JOptionPane.showOptionDialog(frame, "El test ya existe o ha sido modificado. ¿Que desea hacer?", 
                    "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if (n == JOptionPane.YES_OPTION) {
                try {
                    saveTest.saveTestInMemory(scenario);
                    if(testYaExiste==true){
                        saveTest.replaceTestLocally(scenario);
                    }else{
                        saveTest.saveTestLocally(scenario);
                    }
                    setScenarioAEditar(new ScenarioTest(scenario));
                    setScenario(new ScenarioTest(scenario));
                    controlador.setTestInstSatGuardado(true);
                    JOptionPane.showMessageDialog(this.getParent(),"El test ha sido sobreescrito",
                    "Confirm Message",JOptionPane.INFORMATION_MESSAGE);
                    ejecutar(0);
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(this.getParent(),"El test no ha sido guardado",
                    "Error Message",JOptionPane.ERROR_MESSAGE);
                }
            }else if (n == JOptionPane.NO_OPTION) {
            }
        }else{
            controlador.setTestInstSatGuardado(true);
            JOptionPane.showMessageDialog(this.getParent(),"No se han producido cambios en el test",
            "Confirm Message",JOptionPane.INFORMATION_MESSAGE);
            ejecutar(0);
        }
        setImportado(false);
     }else{
        try {
            saveTest.saveTestInMemory(scenario);
            saveTest.saveTestLocally(scenario);
            setScenarioAEditar(new ScenarioTest(scenario));
            setScenario(new ScenarioTest(scenario));
            controlador.setTestInstSatGuardado(true);
            JOptionPane.showMessageDialog(this.getParent(),"El test ha sido guardado",
            "Confirm Message",JOptionPane.INFORMATION_MESSAGE);
            ejecutar(0);
        }catch (FileNotFoundException ex){
            JOptionPane.showMessageDialog(this.getParent(),"El test no ha sido guardado",
            "Error Message",JOptionPane.ERROR_MESSAGE);
        }
    }    
}

public void inicializarVariables(){
    ambosNecesarios=false;
    continuarSinInstancias=true;
    testYaExiste=false;
    testSinNombre=false;
    validoInst=true;
    hayUnaConsulta=0;
    continuar=true;
}

public boolean testVacio(String nombre){
    if(nombre.equals("")){
        return true;
    }else{
        return false;
    }
}

public boolean consultaCompletaEnSinAyuda(String consulta,String resultado){
    if((!consulta.equals("") && resultado.equals("")) || (consulta.equals("") && !resultado.equals(""))){
        return false;
    }else{
        return true;
    }
}

public boolean consultaCompletaEnAyuda(TestInstancesTFJPanel test){
    if((!test.getQuery().equals("") && test.ningunoSeleccionado()==true) ||
        test.getQuery().equals("") && test.ningunoSeleccionado()==false){
        return false;
    }else{
        return true;
    }
}

public boolean panelVacio(TestInstancesTFJPanel test){
    if(test.getQuery().equals("") && test.ningunoSeleccionado()==true){
        return true;
    }else{
        return false;
    }
}

public boolean tieneInstanciasAsociadas(ScenarioTest scenario){
    Instancias instancias = scenario.getInstancias();
    List<ClassInstances> clasI = instancias.getClassInstances();
    List<PropertyInstances> propI = instancias.getPropertyInstances();
    
    if(clasI.size()==0 && propI.size()==0){
        return false;
    }else{
        return true;
    }
}

public void copiarTestAScenarioDesdeAyuda(){

    inicializarVariables();

    queryTest = new ArrayList<QueryOntology>();
    test = null; 
    
    panelAyudaInst = this.getInstAyudaPanel();
    totalInst = panelAyudaInst.getComponentCount();
    validarTests = new ValidarTests();
    
    int cont=0;
    
    inst = new ArrayList();
    getInst().add(0,0);
    descPanel = (DescripcionJPanel) descripcionJPanel.getComponent(0);
    nombreTest = descPanel.getNombreTextField();
    descTest = descPanel.getDescTextArea();
    if(utils.testYaExiste(nombreTest)==true){
        testYaExiste=true;
    }
    if(testVacio(nombreTest)==true){
        testSinNombre=true;
    }else{
        for(int i=1;i<totalInst;i++){
            test = (TestInstancesTFJPanel) panelAyudaInst.getComponent(i);
            if(panelVacio(test)==false){
                if(ambosNecesarios==false){
                    if(consultaCompletaEnAyuda(test)==false){
                        ambosNecesarios=true;
                    }else{
                        String query = test.getQuery();
                        String resExpT = test.isTestTrue();
                        AddComentJDialog comentPane = test.getComment();
                        String coment = comentPane.getComent();
                        hayUnaConsulta=1;
                        QueryOntology testQuery = new QueryOntology(query,resExpT,coment);
                        if(validarTests.validarQueryInstSatis(testQuery.getQuery())==true){
                            queryTest.add(testQuery);
                            cont++;
                            getInst().add(i, 0);
                        }else{
                            getInst().add(i, 1);
                            validoInst=false;
                        }
                    }
                }
            }
        }
    }
    
    if(testSinNombre==false && validoInst==true && ambosNecesarios==false
                && hayUnaConsulta==1){  
        preguntarSiContinuarSinInstancias(scenario);
        if(continuarSinInstancias==true){
            scenario.setDescripcion(descTest);
            scenario.setNombre(nombreTest);
            scenario.setQueryTest(queryTest); 
        }
    }else if(testSinNombre==true){
            JOptionPane.showMessageDialog(this.getParent(),"El nombre del test es obligatorio",
            "Warning Message",JOptionPane.WARNING_MESSAGE);
            continuar=false;
    }else if(ambosNecesarios==true){
        JOptionPane.showMessageDialog(this.getParent(),"Ambos campos CONSULTA y RESULTADO ESPERADO son" +
        "obligatorios","Warning Message",JOptionPane.WARNING_MESSAGE);
        continuar=false;
    }else if(hayUnaConsulta==0 && testSinNombre==false){
        JOptionPane.showMessageDialog(this.getParent(),"Al menos debe introducir una consulta " +
        "para guardar el test.","Warning Message",JOptionPane.WARNING_MESSAGE);
        continuar=false;
    }else if(validoInst==false){
        JOptionPane.showMessageDialog(this.getParent(),"El formato de " +
        "los datos marcados en rojo no es correcto." +
        "\nPor favor, consulte la ayuda acerca del formato " +
        "de las consultas y el resultado.","Warning Message",JOptionPane.WARNING_MESSAGE);
        formatoIncorrecto();
        continuar=false;
    }
}


public boolean preguntarSiContinuarSinInstancias(ScenarioTest scen){
    if(tieneInstanciasAsociadas(scen)==false){
        int n = JOptionPane.showConfirmDialog(comp, "El test no tiene instancias asociadas. " +
                "¿Desea continuar?", "Warning Message",JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.NO_OPTION){
            continuarSinInstancias=false;
        }else if(n == JOptionPane.YES_OPTION){
            continuarSinInstancias=true;
        }
    }

    return continuarSinInstancias;
}

public void copiarTestAScenarioDesdeSinAyuda(){
      
    inicializarVariables();
    
    String conjuntoQuerys;
    String conjuntoResult;
    String conjuntoComent;
    String[] cQuery;
    String[] cResult;
    String[] cComent;
    texto = null;
    
    validarTests = new ValidarTests();
    queryTest = new ArrayList<QueryOntology>();
    descPanel = (DescripcionJPanel) descripcionJPanel.getComponent(0);
    texto = (TestInstancesTextJPanel) getOpcionTextInstPanel().getComponent(0);
    conjuntoQuerys = texto.getConsultaQuery();
    conjuntoResult = texto.getResultadoEsperado();
    conjuntoComent = texto.getComentTextArea();
    cQuery = conjuntoQuerys.split("\\\n");
    cResult = conjuntoResult.split("\\\n");
    cComent = conjuntoComent.split("\\\n");
    
    nombreTest = descPanel.getNombreTextField();
    descTest = descPanel.getDescTextArea();
    
    inst = new ArrayList();
    getInst().add(0,0);
    if(utils.testYaExiste(nombreTest)==true){
        testYaExiste=true;
    }
    if(testVacio(nombreTest)==true){
        testSinNombre=true;
    }else{
        if(!conjuntoQuerys.equals("") && !conjuntoResult.equals("")){
            QueryOntology testQuery = new QueryOntology();
            int tamQ = cQuery.length;
            int tamR = cResult.length;
            if(tamQ==tamR){
                for(int i=0; i<tamQ;i++){
                    if(consultaCompletaEnSinAyuda(cQuery[i], cResult[i])==true){
                        if(continuar=true){
                            hayUnaConsulta=1;
                            if(validarTests.validarQueryInstSatis(cQuery[i])==true &&
                                    validarTests.validarResultadoInstSatis(cResult[i])==true){
                                if(cComent.length!=0 && i!=cComent.length && i<=cComent.length){
                                    testQuery = new QueryOntology(cQuery[i],cResult[i],cComent[i]);
                                }else{
                                    testQuery = new QueryOntology(cQuery[i],cResult[i]);
                                }
                                queryTest.add(testQuery);
                                getInst().add(i, 0);
                            }else if(validarTests.validarQueryInstSatis(cQuery[i])==false &&
                                    validarTests.validarResultadoInstSatis(cResult[i])==true){
                                getInst().add(i, 1);
                                validoInst=false;
                            }else if(validarTests.validarQueryInstSatis(cQuery[i])==true &&
                                    validarTests.validarResultadoInstSatis(cResult[i])==false){
                                getInst().add(i, 2);
                                validoInst=false;
                            }else{
                                getInst().add(i, 3);
                                validoInst=false;
                            }
                        }
                    }else{
                        ambosNecesarios=true;
                        continuar=false;
                    }
                } 
            }else{
                ambosNecesarios=true;
            }
        }else if((!conjuntoQuerys.equals("") && conjuntoResult.equals("")) || 
                (conjuntoQuerys.equals("") && !conjuntoResult.equals(""))){
            ambosNecesarios=true;
        }
    }
    if(testSinNombre==false && validoInst==true && ambosNecesarios==false
        && hayUnaConsulta==1){
        preguntarSiContinuarSinInstancias(scenario);
        if(continuarSinInstancias==true){
            scenario.setDescripcion(descTest);
            scenario.setNombre(nombreTest);
            scenario.setQueryTest(queryTest);
        }
    }else if(testSinNombre==true){
        JOptionPane.showMessageDialog(this.getParent(),"El nombre del test es obligatorio",
        "Warning Message",JOptionPane.WARNING_MESSAGE);
        continuar=false;
    }else if(ambosNecesarios==true){
        JOptionPane.showMessageDialog(this.getParent(),"Ambos campos CONSULTA y RESULTADO ESPERADO son" +
        "obligatorios","Warning Message",JOptionPane.WARNING_MESSAGE);
        continuar=false;
    }else if(hayUnaConsulta==0 && testSinNombre==false){
        JOptionPane.showMessageDialog(this.getParent(),"Al menos debe introducir una consulta " +
        "para guardar el test.","Warning Message",JOptionPane.WARNING_MESSAGE);
        continuar=false;
    }else if(validoInst==false){
        JOptionPane.showMessageDialog(this.getParent(),"El formato de los datos marcados en rojo no es correcto." +
        "\nPor favor, consulte la ayuda acerca del formato " +
        "de las consultas y el resultado.","Warning Message",JOptionPane.WARNING_MESSAGE);
        formatoIncorrecto();
        continuar=false;
    }
}

public void formatoIncorrecto(){
    ValidarConsultas validar = new ValidarConsultas();
    if(TestSimpleInstSat.getActualSubTabInst()==0){
        if(validar.comprovarErrorEnAyudaInst(panelAyudaInst)==false){
        }
    }else{
        if(validar.comprovarErrorQuerysInst(opcionTextInstPanel)==false){
        }
    }
}

public void copiarDeAyudaATexto(){
     
    test = null;
    TestInstancesTextJPanel t;
    String conjuntoQuerysInst="";
    String conjuntoResExpInst="";
    String conjuntoComentInst="";

    panelAyudaInst = this.getInstAyudaPanel();
    totalInst = panelAyudaInst.getComponentCount();

    for(int i=1;i<totalInst;i++){
        test = (TestInstancesTFJPanel) panelAyudaInst.getComponent(i);
            if(!test.getQuery().equals("") || !test.isTestFalse().equals(test.isTestTrue())){
                String query = test.getQuery();
                String resExpT = test.isTestTrue();
                String resExpF = test.isTestFalse();
                AddComentJDialog comentPane = test.getComment();
                String coment = comentPane.getComent();
                if(!query.equals("") || !resExpT.equals(resExpF)){
                    if(conjuntoQuerysInst.equals("")){
                        conjuntoQuerysInst = query.concat("\n");
                    }else{
                        conjuntoQuerysInst = conjuntoQuerysInst.concat(query).concat("\n");
                    }
                    if(conjuntoResExpInst.equals("")){
                        conjuntoResExpInst = resExpT.concat("\n");
                    }else{
                        conjuntoResExpInst = conjuntoResExpInst.concat(resExpT).concat("\n");
                    }
                    if(conjuntoComentInst.equals("")){
                        conjuntoComentInst = coment.concat("\n");
                    }else{
                        conjuntoComentInst = conjuntoComentInst.concat(coment).concat("\n");
                    }
                } 
            }
    }
    t = (TestInstancesTextJPanel) getOpcionTextInstPanel().getComponent(0);
    t.setConsultaQuery(conjuntoQuerysInst);
    t.setResultadoEsperado(conjuntoResExpInst);
    t.setComentTextArea(conjuntoComentInst);
    int c = getInstAyudaPanel().getComponentCount();
    TestInstancesTFJPanel.setContadorInstSat(0);
    for(int i=1;i<c;i++){
            getInstAyudaPanel().remove(getInstAyudaPanel().getComponent(i));
        pInst = new TestInstancesTFJPanel();
            getInstAyudaPanel().add(pInst,i); 
    }
    getInstAyudaPanel().validate();
    t.validate();
}

public void copiarDeTextoAAyuda(){

    test = null;

    panelAyudaInst = this.getInstAyudaPanel();
    totalInst = panelAyudaInst.getComponentCount();

    TestInstancesTextJPanel ti;

    String conjuntoQuerysInst="",conjuntoResultInst="",conjuntoComentInst="";
    String cQuery[],cResult[],cComent[];

    ti = (TestInstancesTextJPanel) getOpcionTextInstPanel().getComponent(0);
    conjuntoQuerysInst = ti.getConsultaQuery().trim();
    conjuntoResultInst = ti.getResultadoEsperado().trim();
    conjuntoComentInst = ti.getComentTextArea().trim();
    cQuery = conjuntoQuerysInst.split("\\n");
    cResult = conjuntoResultInst.split("\\n");
    cComent = conjuntoComentInst.split("\\n");
    int tamI = this.getInstAyudaPanel().getComponentCount();
    int tamInst = tamI-1;
    int j=1;
    
    if(!conjuntoQuerysInst.equals("")){
        for(int i=0; i<cQuery.length; i++){
            if(!cQuery[i].equals("")){
                if(i<tamInst){
                    test = (TestInstancesTFJPanel) panelAyudaInst.getComponent(j);
                    test.setQuery(cQuery[i]);
                    j++;
                }else{
                    if(j<tamInst){
                        test = (TestInstancesTFJPanel) panelAyudaInst.getComponent(j);
                        test.setQuery(cQuery[i]);
                        j++;
                    }else{
                        TestInstancesTFJPanel instP = new TestInstancesTFJPanel();
                        instP.setQuery(cQuery[i]);
                        this.getInstAyudaPanel().add(instP);
                    }
                }
            }else{
                j++;
            }
        }
        test.validate();
    }
    j=1;
    if(!conjuntoResultInst.equals("")){
        for(int i=0; i<cResult.length; i++){
            String res = cResult[i];
            if(!res.equals("")){
                if(i<tamInst){
                    test = (TestInstancesTFJPanel) panelAyudaInst.getComponent(j);
                    if(res.equals("true")){
                        test.setTrueTest(true);
                    }else{
                        test.setFalseTest(true);
                    }
                    j++;
                }else{
                    if(j<tamInst){
                        test = (TestInstancesTFJPanel) panelAyudaInst.getComponent(j);
                        if(res.equals("true")){
                            test.setTrueTest(true);
                        }else{
                            test.setFalseTest(true);
                        }
                        j++;
                    }else{
                        TestInstancesTFJPanel instP = new TestInstancesTFJPanel();
                        if(res.equals("true")){
                            instP.setTrueTest(true);
                        }else{
                            instP.setFalseTest(true);
                        }
                        this.getInstAyudaPanel().add(instP);
                    }
                }
            }else{
                j++;
            }
        }
        test.validate();
    }
    j=1;
    if(!conjuntoComentInst.equals("")){
        for(int i=0; i<cComent.length; i++){
            if(!cComent[i].equals("")){
                if(i<tamInst){
                    test = (TestInstancesTFJPanel) panelAyudaInst.getComponent(j);
                    AddComentJDialog comentPane = test.getComment();
                    comentPane.setComent(cComent[i]);
                    test.setComment(comentPane);
                    j++;
                }else{
                    if(j<tamInst){
                        test = (TestInstancesTFJPanel) panelAyudaInst.getComponent(j);
                        AddComentJDialog comentPane = test.getComment();
                        comentPane.setComent(cComent[i]);
                        test.setComment(comentPane);
                        j++;
                    }else{
                        TestInstancesTFJPanel instP = new TestInstancesTFJPanel();
                        AddComentJDialog comentPane = instP.getComment();
                        comentPane.setComent(cComent[i]);
                        instP.setComment(comentPane);
                        this.getInstAyudaPanel().add(instP);
                    }
                }
            }
        }
        test.validate();
    }
}

public static List getInst() {
    return inst;
}

public static int getActualSubTabInst() {
    return actualSubTabInst;
}

public static void setActualSubTabInst(int aActualSubTabInst) {
    actualSubTabInst = aActualSubTabInst;
}

public JPanel getInstAyudaPanel() {
    return instAyudaPanel;
}

public JPanel getOpcionTextInstPanel() {
    return opcionTextInstPanel;
}

public ScenarioTest getScenario() {
    return scenario;
}

public void setScenario(ScenarioTest aScenarioInst) {
    scenario = aScenarioInst;
}

public  int getTabbedPaneInst() {
    return tabbedPaneInst.getSelectedIndex();
}

public JPanel panelActualInstSat(){
    return this.getInstAyudaPanel();
}

public ScenarioTest getScenarioAEditar() {
    return scenarioAEditar;
}

public void setScenarioAEditar(ScenarioTest scenarioAEditar) {
    this.scenarioAEditar = scenarioAEditar;
}

public boolean isImportado() {
    return importado;
}

public void setImportado(boolean importado) {
    this.importado = importado;
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentGuardarEjecutarPanel;
    private javax.swing.JPanel contentInstTabedPanel;
    private javax.swing.JPanel descripcionJPanel;
    private javax.swing.JButton ejecutarButton;
    private javax.swing.JButton guardarButton;
    private javax.swing.JButton guardarEjecutarButton;
    private javax.swing.JPanel instAyudaPanel;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel labelInstLabel;
    private javax.swing.JPanel labelInstPanel;
    private javax.swing.JPanel opcionAyudaInstPanel;
    private javax.swing.JPanel opcionTextInstPanel;
    private javax.swing.JTabbedPane tabbedPaneInst;
    // End of variables declaration//GEN-END:variables

    public boolean isSoloEjecutar() {
        return soloEjecutar;
    }

    public void setSoloEjecutar(boolean soloEjecutar) {
        this.soloEjecutar = soloEjecutar;
    }

    class Task extends SwingWorker<Void, Void> {
        @Override
        public Void doInBackground() {
            Random random = new Random();
            int progress = 0;
            setProgress(0);
            try {
                Thread.sleep(1000);
                while (progress < 100 && !isCancelled()) {
                    //Sleep for up to one second.
                    Thread.sleep(random.nextInt(1000));
                    //Make random progress.
                    progress += random.nextInt(10);
                    setProgress(Math.min(progress, 100));
                }
            } catch (InterruptedException ignore) {}
            return null;
        }

        @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep();
            progressMonitor.setProgress(0);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress".equals(evt.getPropertyName()) ) {
            int progress = (Integer) evt.getNewValue();
            progressMonitor.setProgress(progress);
            String message =
                String.format("Completed %d%%.\n", progress);
            progressMonitor.setNote(message);
            //taskOutput.append(message);
            if (progressMonitor.isCanceled() || task.isDone()) {
                Toolkit.getDefaultToolkit().beep();
                if (progressMonitor.isCanceled()) {
                    task.cancel(true);
                    //taskOutput.append("Ejecución cancelada.\n");
                } else {
                    //taskOutput.append("Ejecución completada.\n");
                }
            }
        }
    }
    
    public TestSimpleInstSat getPanel(){
        return this;
    }

}
