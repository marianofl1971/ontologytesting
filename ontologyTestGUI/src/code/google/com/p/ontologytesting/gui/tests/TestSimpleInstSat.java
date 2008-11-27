/*
 * contentInstTabedPanel.java
 *
 * Created on 11 de septiembre de 2008, 18:14
 */

package code.google.com.p.ontologytesting.gui.tests;

import code.google.com.p.ontologytesting.gui.auxiliarclasess.*;
import code.google.com.p.ontologytesting.gui.*;
import code.google.com.p.ontologytesting.model.*;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.ExceptionReadOntology;
import code.google.com.p.ontologytesting.persistence.*;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author  saruskas
 */
public class TestSimpleInstSat extends javax.swing.JPanel{

    private ValidarTests validarTests;
    private TestInstancesTFJPanel test;
    private DescripcionJPanel descPanel = null;
    private boolean testSinNombre,validoInst,ambosNecesarios,continuarSinInstancias,continuar,addInst;
    private int actualSubTabInst=0,totalInst=0,hayUnaConsulta=0;
    private JPanel panelAyudaInst;
    private List inst;
    private TestInstancesTFJPanel pInst;
    private List<QueryOntology> queryTest;
    private TestInstancesTextJPanel texto;
    private ScenarioTest scenario;
    private IOManagerImplementation persist = new IOManagerImplementation();
    private String nombreTest = "",descTest = "";
    private ControladorTests controlador;
    private OpcionesMenu menu;
    private ValidarConsultas validarConsultas = new ValidarConsultas();
    private QueryOntology testQuery;
    private AniadirPanelDeAviso panelAviso;
    private ScenarioTest scenarioActual = new ScenarioTest();
    
    public TestSimpleInstSat(ScenarioTest s){
        initComponents();
        TestInstancesTFJPanel.setContadorInstSat(0);
        panelAviso = new AniadirPanelDeAviso();
        controlador = ControladorTests.getInstance();
        descripcionJPanel.add(new DescripcionJPanel(),BorderLayout.WEST);
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
        if(cont<5){
            for (int i = cont; i <= 5; i++) {  
                instAyudaPanel.add(new TestInstancesTFJPanel());   
            }
        }
        menu = new OpcionesMenu();
        setScenario(s);
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
        contentGuardarEjecutarPanel = new javax.swing.JPanel();
        guardarButton = new javax.swing.JButton();
        ejecutarButton = new javax.swing.JButton();
        guardarEjecutarButton = new javax.swing.JButton();
        addInstanciasButton = new javax.swing.JButton();
        descripcionJPanel = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        contentInstTabedPanel.setLayout(new java.awt.BorderLayout());

        tabbedPaneInst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabbedPaneInstMouseClicked(evt);
            }
        });

        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        labelInstLabel.setText("                      CONSULTAS                               RESULTADO ESPERADO");

        javax.swing.GroupLayout labelInstPanelLayout = new javax.swing.GroupLayout(labelInstPanel);
        labelInstPanel.setLayout(labelInstPanelLayout);
        labelInstPanelLayout.setHorizontalGroup(
            labelInstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelInstPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );
        labelInstPanelLayout.setVerticalGroup(
            labelInstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelInstPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstLabel)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout instAyudaPanelLayout = new javax.swing.GroupLayout(instAyudaPanel);
        instAyudaPanel.setLayout(instAyudaPanelLayout);
        instAyudaPanelLayout.setHorizontalGroup(
            instAyudaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(instAyudaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );
        instAyudaPanelLayout.setVerticalGroup(
            instAyudaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(instAyudaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(498, Short.MAX_VALUE))
        );

        jScrollPane6.setViewportView(instAyudaPanel);

        javax.swing.GroupLayout opcionAyudaInstPanelLayout = new javax.swing.GroupLayout(opcionAyudaInstPanel);
        opcionAyudaInstPanel.setLayout(opcionAyudaInstPanelLayout);
        opcionAyudaInstPanelLayout.setHorizontalGroup(
            opcionAyudaInstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcionAyudaInstPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        opcionAyudaInstPanelLayout.setVerticalGroup(
            opcionAyudaInstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcionAyudaInstPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPaneInst.addTab("Formato ayuda", opcionAyudaInstPanel);

        javax.swing.GroupLayout opcionTextInstPanelLayout = new javax.swing.GroupLayout(opcionTextInstPanel);
        opcionTextInstPanel.setLayout(opcionTextInstPanelLayout);
        opcionTextInstPanelLayout.setHorizontalGroup(
            opcionTextInstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 571, Short.MAX_VALUE)
        );
        opcionTextInstPanelLayout.setVerticalGroup(
            opcionTextInstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 325, Short.MAX_VALUE)
        );

        tabbedPaneInst.addTab("Formato Texto", opcionTextInstPanel);

        contentInstTabedPanel.add(tabbedPaneInst, java.awt.BorderLayout.CENTER);

        guardarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/disk.png"))); // NOI18N
        guardarButton.setToolTipText("Guardar");
        guardarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarButtonActionPerformed(evt);
            }
        });

        ejecutarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/cog.png"))); // NOI18N
        ejecutarButton.setToolTipText("Ejecutar");
        ejecutarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarButtonActionPerformed(evt);
            }
        });

        guardarEjecutarButton.setText("Guardar y Ejecutar");
        guardarEjecutarButton.setToolTipText("Guardar y Ejecutar");
        guardarEjecutarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarEjecutarButtonActionPerformed(evt);
            }
        });

        addInstanciasButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/add.png"))); // NOI18N
        addInstanciasButton.setText("Instancias");
        addInstanciasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addInstanciasButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contentGuardarEjecutarPanelLayout = new javax.swing.GroupLayout(contentGuardarEjecutarPanel);
        contentGuardarEjecutarPanel.setLayout(contentGuardarEjecutarPanelLayout);
        contentGuardarEjecutarPanelLayout.setHorizontalGroup(
            contentGuardarEjecutarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentGuardarEjecutarPanelLayout.createSequentialGroup()
                .addContainerGap(214, Short.MAX_VALUE)
                .addComponent(guardarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ejecutarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(guardarEjecutarButton)
                .addGap(18, 18, 18)
                .addComponent(addInstanciasButton)
                .addContainerGap())
        );
        contentGuardarEjecutarPanelLayout.setVerticalGroup(
            contentGuardarEjecutarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentGuardarEjecutarPanelLayout.createSequentialGroup()
                .addGroup(contentGuardarEjecutarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addInstanciasButton)
                    .addComponent(guardarEjecutarButton)
                    .addComponent(ejecutarButton)
                    .addComponent(guardarButton))
                .addContainerGap())
        );

        contentInstTabedPanel.add(contentGuardarEjecutarPanel, java.awt.BorderLayout.PAGE_START);

        add(contentInstTabedPanel, java.awt.BorderLayout.CENTER);

        descripcionJPanel.setLayout(new java.awt.BorderLayout());
        add(descripcionJPanel, java.awt.BorderLayout.PAGE_START);
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

private void addInstanciasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addInstanciasButtonActionPerformed
// TODO add your handling code here:
    addInst=true;
    if(persist.testYaGuardado(getScenario())==false){
        guardarTest();
    }
    menu.editarInstancias(this.getScenario());
}//GEN-LAST:event_addInstanciasButtonActionPerformed

private void guardarButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
// TODO add your handling code here:
    guardarTest();
}

private void ejecutarButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
    persist = new IOManagerImplementation();
    if(getTabbedPaneInst()==0){
        copiarTestAScenarioDesdeAyuda();
    }else if(getTabbedPaneInst()==1){
        copiarTestAScenarioDesdeSinAyuda();
    }
    if(continuar==true){
        if(continuarSinInstancias==true){
            this.realizarAccion(false, true);
        }else{
            MainApplicationJFrame.getInstance().cargarInstancia(this.getScenario().getInstancias(),"Asociar Instancias a Test");
        }
    }
}

private void guardarEjecutarButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                      
// TODO add your handling code here:
    persist = new IOManagerImplementation();
    if(getTabbedPaneInst()==0){
        copiarTestAScenarioDesdeAyuda();
    }else if(getTabbedPaneInst()==1){
        copiarTestAScenarioDesdeSinAyuda();
    }
    if(continuar==true){
        if(continuarSinInstancias==true){
            realizarAccion(true, true);
        }else{
            MainApplicationJFrame.getInstance().cargarInstancia(this.getScenario().getInstancias(),"Asociar Instancias a Test");
        }
    }
}

public boolean guardarTest(){
    if(getTabbedPaneInst()==0){
        copiarTestAScenarioDesdeAyuda();
    }else if(getTabbedPaneInst()==1){
        copiarTestAScenarioDesdeSinAyuda();
    }
    if(continuar==true){
        if(continuarSinInstancias==true){
           realizarAccion(true, false);
        }else{
            MainApplicationJFrame.getInstance().cargarInstancia(this.getScenario().getInstancias(),"Asociar Instancias a Test");
        }
    }
    return continuar;
}

public void realizarAccion(boolean guardar, boolean ejecutar){
    persist = new IOManagerImplementation();
    if(guardar==true){
        if(persist.testYaGuardado(getScenario())==true){
            persist.replaceScenarioLocally(getScenario());
        }else{
            persist.saveTestInMemory(getScenario());
            controlador.setTestInstSatGuardado(true);
        }
        this.setScenarioActual(new ScenarioTest(scenario));
    }
    if(ejecutar==true){
        try{
            ExecuteTest execTest = new ExecuteTest(getScenario());
            execTest.execute();
        }catch (ExceptionReadOntology ex){
            panelAviso.errorAction("No se pudo ejecutar el test. Ontología no válida", MainApplicationJFrame.getInstance());
        }
    }
    if(guardar==true && ejecutar==true){
        panelAviso.confirmAction("Test Guardado y Ejecutado", MainApplicationJFrame.getInstance());
    }else if(guardar==true){
        if(addInst==false){
            panelAviso.confirmAction("Test Guardado", MainApplicationJFrame.getInstance());
        }
    }else{ 
        panelAviso.confirmAction("Test Ejecutado", MainApplicationJFrame.getInstance());
    }
    menu.actualizarListaDeTestsSimples(CollectionTest.getInstance().getScenariotest());
}

public void inicializarVariables(){
    ambosNecesarios=false;
    continuarSinInstancias=true;
    testSinNombre=false;
    validoInst=true;
    hayUnaConsulta=0;
    continuar=true;
}

public void copiarTestAScenarioDesdeAyuda(){

    inicializarVariables();

    queryTest = new ArrayList<QueryOntology>();
    test = null; 
    
    panelAyudaInst = this.getInstAyudaPanel();
    totalInst = panelAyudaInst.getComponentCount();
    validarTests = new ValidarTests();
    
    inst = new ArrayList();
    this.inst.add(0,0);
    validarConsultas.setListInst(this.inst);
    descPanel = (DescripcionJPanel) descripcionJPanel.getComponent(0);
    nombreTest = descPanel.getNombreTextField();
    descTest = descPanel.getDescTextArea();
    if(descPanel.testSinNombre()==true){
        testSinNombre=true;
    }else{
        for(int i=1;i<totalInst;i++){
            test = (TestInstancesTFJPanel) panelAyudaInst.getComponent(i);
            if(test.panelVacio()==false){
                if(ambosNecesarios==false){
                    if(test.consultaCompletaEnAyuda()==false){
                        ambosNecesarios=true;
                    }else{
                        String query = test.getQuery();
                        String resExpT = test.isTestTrue();
                        AddComentJDialog comentPane = test.getComment();
                        String coment = comentPane.getComent();
                        hayUnaConsulta=1;
                        testQuery = new QueryOntology(query,resExpT,coment);
                        if(validarTests.validarQueryInstSatis(testQuery.getQuery())==true){
                            queryTest.add(testQuery);
                            this.inst.add(i, 0);
                            validarConsultas.setListInst(this.inst);
                        }else{
                            this.inst.add(i, 1);
                            validarConsultas.setListInst(this.inst);
                            validoInst=false;
                        }
                    }
                }
            }
        }
    }
    
    if(testSinNombre==false && validoInst==true && ambosNecesarios==false
                && hayUnaConsulta==1){  
        continuarSinInstancias = this.preguntarSiContinuarSinInstancias();
        if(continuarSinInstancias==true){
            this.getScenario().setDescripcion(descTest);
            this.getScenario().setNombre(nombreTest);
            this.getScenario().setQueryTest(queryTest); 
        }
    }else {
        comprobarDatosErroneos(true);
    }
}

public boolean preguntarSiContinuarSinInstancias(){
    if(addInst==false){
        if(scenario.tieneInstanciasAsociadas()==false){
            int n = JOptionPane.showConfirmDialog(MainApplicationJFrame.getInstance(), "El test no tiene instancias asociadas. " +
                    "¿Desea continuar?", "Warning Message",JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.NO_OPTION){
                continuarSinInstancias=false;
            }else if(n == JOptionPane.YES_OPTION){
                continuarSinInstancias=true;
            }
        }
    }else continuarSinInstancias=true;
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
    this.inst.add(0,0);
    validarConsultas.setListInst(this.inst);
    if(descPanel.testSinNombre()==true){
        testSinNombre=true;
    }else{
        if(!conjuntoQuerys.equals("") && !conjuntoResult.equals("")){
            testQuery = new QueryOntology();
            int tamQ = cQuery.length;
            int tamR = cResult.length;
            if(tamQ==tamR){
                for(int i=0; i<tamQ;i++){
                    if(texto.consultaCompletaEnSinAyuda(cQuery[i], cResult[i])==true){
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
                                this.inst.add(i, 0);
                                validarConsultas.setListInst(this.inst);
                            }else if(validarTests.validarQueryInstSatis(cQuery[i])==false &&
                                    validarTests.validarResultadoInstSatis(cResult[i])==true){
                                this.inst.add(i, 1);
                                validarConsultas.setListInst(this.inst);
                                validoInst=false;
                            }else if(validarTests.validarQueryInstSatis(cQuery[i])==true &&
                                    validarTests.validarResultadoInstSatis(cResult[i])==false){
                                this.inst.add(i, 2);
                                validarConsultas.setListInst(this.inst);
                                validoInst=false;
                            }else{
                                this.inst.add(i, 3);
                                validarConsultas.setListInst(this.inst);
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
        continuarSinInstancias = this.preguntarSiContinuarSinInstancias();
        if(continuarSinInstancias==true){
            this.getScenario().setDescripcion(descTest);
            this.getScenario().setNombre(nombreTest);
            this.getScenario().setQueryTest(queryTest);
        }
    }else {
        comprobarDatosErroneos(false);
    }
}

public void comprobarDatosErroneos(boolean ayuda){
    if(testSinNombre==true){
            panelAviso.warningAction("El nombre del test es obligatorio", MainApplicationJFrame.getInstance());
            continuar=false;
    }else if(ambosNecesarios==true){
        panelAviso.warningAction("Ambos campos CONSULTA y RESULTADO ESPERADO son obligatorios", MainApplicationJFrame.getInstance());
        continuar=false;
    }else if(hayUnaConsulta==0 && testSinNombre==false){
        panelAviso.warningAction("Al menos debe introducir una consulta para guardar el test", MainApplicationJFrame.getInstance());
        continuar=false;
    }else if(validoInst==false){
        if(ayuda==true){
            validarConsultas.formatoIncorrecto(panelAyudaInst, this.getTabbedPaneInst(),0);
        }else{
            validarConsultas.formatoIncorrecto(texto, this.getTabbedPaneInst(),0);
        }
        continuar=false;
    }
}

public void copiarDeAyudaATexto(){
     
    test = null;
    TestInstancesTextJPanel t;
    StringBuffer conjuntoQuerysInst=new StringBuffer();
    StringBuffer conjuntoResExpInst=new StringBuffer();
    StringBuffer conjuntoComentInst=new StringBuffer();

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
                    conjuntoQuerysInst.append(query).append("\n");
                    conjuntoResExpInst.append(resExpT).append("\n");
                    conjuntoComentInst.append(coment).append("\n");
                } 
            }
    }
    t = (TestInstancesTextJPanel) getOpcionTextInstPanel().getComponent(0);
    t.setConsultaQuery(conjuntoQuerysInst.toString());
    t.setResultadoEsperado(conjuntoResExpInst.toString());
    t.setComentTextArea(conjuntoComentInst.toString());
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

public int getActualSubTabInst() {
    return actualSubTabInst;
}

public void setActualSubTabInst(int aActualSubTabInst) {
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addInstanciasButton;
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

    public ScenarioTest getScenarioActual() {
        return scenarioActual;
    }

    public void setScenarioActual(ScenarioTest scenarioActual) {
        this.scenarioActual = scenarioActual;
    }

    
}
