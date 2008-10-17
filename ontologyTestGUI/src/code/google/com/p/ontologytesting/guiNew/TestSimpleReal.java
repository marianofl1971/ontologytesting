/*
 * TestSimpleReal.java
 *
 * Created on 15 de septiembre de 2008, 11:27
 */

package code.google.com.p.ontologytesting.guiNew;

import code.google.com.p.ontologytesting.model.*;
import code.google.com.p.ontologytesting.persistence.SaveTest;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author  sara.garcia
 */
public class TestSimpleReal extends javax.swing.JPanel {

    private static ValidarTests validarTests;
    private static TestInstancesQueryJPanel test;
    private static DescripcionJPanel descPanel = null;
    private static boolean testSinNombre;
    private static int actualSubTabReal=0;
    private static boolean validoReal;
    private static JPanel panelAyudaReal;
    private static int totalReal;
    private static JFrame frame;
    private static boolean ambosNecesarios;
    public static boolean seleccionado;
    private static List real;
    private static boolean continuarSinInstancias;
    private static boolean testYaExiste;
    private static int hayUnaConsulta=0;
    private Component comp;
    private static List<QueryOntology> queryTest;
    private static TestInstancesTextJPanel texto;
    private ScenarioTest scenario;
    private boolean continuar;
    private SaveTest saveTest;
    private AddInstancesClasPropJDialog addInst;
    private String nombreTest = "",descTest = "";
    private ScenarioTest scenarioAEditar;
    private ControladorTests controlador;
    private Utils utils;
    private OpcionesMenu menu;
    
    /** Creates new form TestSimpleReal */
    /*public TestSimpleReal() {
        initComponents();
        TestInstancesQueryJPanel.setContadorReal(0);
        descripcionJPanel.setLayout(new FlowLayout());
        descripcionJPanel.add(new DescripcionJPanel());
        opcionTextRealPanel.setLayout(new BoxLayout(getOpcionTextRealPanel(), BoxLayout.Y_AXIS));
        realAyudaPanel.setLayout(new BoxLayout(getRealAyudaPanel(), BoxLayout.Y_AXIS));
        for (int i = 1; i <= 10; i++) {  
            realAyudaPanel.add(new TestInstancesQueryJPanel());   
        }
        opcionTextRealPanel.add(new TestInstancesTextJPanel());
        setScenarioAEditar(null);
        setScenario(new ScenarioTest(TipoTest.REAL)); 
        setImportado(false);
        setSoloEjecutar(false);
    }*/
    
    public TestSimpleReal(ScenarioTest s){
        initComponents();
        TestInstancesQueryJPanel.setContadorReal(0);
        descripcionJPanel.setLayout(new FlowLayout());
        descripcionJPanel.add(new DescripcionJPanel());
        opcionTextRealPanel.setLayout(new BoxLayout(getOpcionTextRealPanel(), BoxLayout.Y_AXIS));
        realAyudaPanel.setLayout(new BoxLayout(getRealAyudaPanel(), BoxLayout.Y_AXIS));
        controlador = ControladorTests.getInstance();
        opcionTextRealPanel.add(new TestInstancesTextJPanel());
        int cont=1;
        List<QueryOntology> listaQuerys = s.getQueryTest(); 
        ListIterator qi;
        qi = listaQuerys.listIterator();
        
        descPanel = (DescripcionJPanel) descripcionJPanel.getComponent(0);
        descPanel.setNombreTextField(s.getNombre());
        descPanel.setDescTextArea(s.getDescripcion());
        while(qi.hasNext()){ 
            QueryOntology cI = (QueryOntology) qi.next();
            TestInstancesQueryJPanel panelInstances = new TestInstancesQueryJPanel();
            AddComentJDialog comentPane = panelInstances.getComment();
            comentPane.setComent(cI.getComment());
            panelInstances.setComment(comentPane);
            panelInstances.setQuery(cI.getQuery());
            panelInstances.setQueryResult(cI.getResultexpected());
            realAyudaPanel.add(panelInstances,cont);
            cont++;
        }
        if(cont<10){
            for (int i = cont; i <= 10; i++) {  
                realAyudaPanel.add(new TestInstancesQueryJPanel());   
            }
        }
        utils = new Utils();
        menu = new OpcionesMenu();
        scenarioAEditar = new ScenarioTest(s);
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

        contentRealTabedPanel = new javax.swing.JPanel();
        tabbedPaneReal = new javax.swing.JTabbedPane();
        opcionAyudaRealPanel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        realAyudaPanel = new javax.swing.JPanel();
        labelRealPanel = new javax.swing.JPanel();
        labelRealLabel = new javax.swing.JLabel();
        opcionTextRealPanel = new javax.swing.JPanel();
        asociarInstanciasButton = new javax.swing.JButton();
        descripcionJPanel = new javax.swing.JPanel();
        contentGuardarEjecutarPanel = new javax.swing.JPanel();
        guardarButton = new javax.swing.JButton();
        guardarEjecutarButton = new javax.swing.JButton();
        ejecutarButton = new javax.swing.JButton();

        tabbedPaneReal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabbedPaneRealMouseClicked(evt);
            }
        });

        labelRealLabel.setText("                      CONSULTAS                               RESULTADO ESPERADO");

        javax.swing.GroupLayout labelRealPanelLayout = new javax.swing.GroupLayout(labelRealPanel);
        labelRealPanel.setLayout(labelRealPanelLayout);
        labelRealPanelLayout.setHorizontalGroup(
            labelRealPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelRealPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelRealLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(135, Short.MAX_VALUE))
        );
        labelRealPanelLayout.setVerticalGroup(
            labelRealPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelRealPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelRealLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout realAyudaPanelLayout = new javax.swing.GroupLayout(realAyudaPanel);
        realAyudaPanel.setLayout(realAyudaPanelLayout);
        realAyudaPanelLayout.setHorizontalGroup(
            realAyudaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(realAyudaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelRealPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(431, Short.MAX_VALUE))
        );
        realAyudaPanelLayout.setVerticalGroup(
            realAyudaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(realAyudaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelRealPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(501, Short.MAX_VALUE))
        );

        jScrollPane6.setViewportView(realAyudaPanel);

        javax.swing.GroupLayout opcionAyudaRealPanelLayout = new javax.swing.GroupLayout(opcionAyudaRealPanel);
        opcionAyudaRealPanel.setLayout(opcionAyudaRealPanelLayout);
        opcionAyudaRealPanelLayout.setHorizontalGroup(
            opcionAyudaRealPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcionAyudaRealPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 803, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        opcionAyudaRealPanelLayout.setVerticalGroup(
            opcionAyudaRealPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opcionAyudaRealPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE))
        );

        tabbedPaneReal.addTab("Formato ayuda", opcionAyudaRealPanel);

        javax.swing.GroupLayout opcionTextRealPanelLayout = new javax.swing.GroupLayout(opcionTextRealPanel);
        opcionTextRealPanel.setLayout(opcionTextRealPanelLayout);
        opcionTextRealPanelLayout.setHorizontalGroup(
            opcionTextRealPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 804, Short.MAX_VALUE)
        );
        opcionTextRealPanelLayout.setVerticalGroup(
            opcionTextRealPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 362, Short.MAX_VALUE)
        );

        tabbedPaneReal.addTab("Formato Texto", opcionTextRealPanel);

        asociarInstanciasButton.setText("Asociar Instancias");
        asociarInstanciasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asociarInstanciasButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contentRealTabedPanelLayout = new javax.swing.GroupLayout(contentRealTabedPanel);
        contentRealTabedPanel.setLayout(contentRealTabedPanelLayout);
        contentRealTabedPanelLayout.setHorizontalGroup(
            contentRealTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentRealTabedPanelLayout.createSequentialGroup()
                .addContainerGap(621, Short.MAX_VALUE)
                .addComponent(asociarInstanciasButton)
                .addGap(79, 79, 79))
            .addGroup(contentRealTabedPanelLayout.createSequentialGroup()
                .addComponent(tabbedPaneReal, javax.swing.GroupLayout.PREFERRED_SIZE, 809, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentRealTabedPanelLayout.setVerticalGroup(
            contentRealTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentRealTabedPanelLayout.createSequentialGroup()
                .addComponent(asociarInstanciasButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabbedPaneReal, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout descripcionJPanelLayout = new javax.swing.GroupLayout(descripcionJPanel);
        descripcionJPanel.setLayout(descripcionJPanelLayout);
        descripcionJPanelLayout.setHorizontalGroup(
            descripcionJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 621, Short.MAX_VALUE)
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

        guardarEjecutarButton.setText("Guardar y Ejecutar");
        guardarEjecutarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarEjecutarButtonActionPerformed(evt);
            }
        });

        ejecutarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/cog.png"))); // NOI18N
        ejecutarButton.setText("Ejecutar");
        ejecutarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contentGuardarEjecutarPanelLayout = new javax.swing.GroupLayout(contentGuardarEjecutarPanel);
        contentGuardarEjecutarPanel.setLayout(contentGuardarEjecutarPanelLayout);
        contentGuardarEjecutarPanelLayout.setHorizontalGroup(
            contentGuardarEjecutarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentGuardarEjecutarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(guardarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ejecutarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(guardarEjecutarButton)
                .addContainerGap(251, Short.MAX_VALUE))
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(descripcionJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(contentRealTabedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentGuardarEjecutarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(descripcionJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentRealTabedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(contentGuardarEjecutarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void tabbedPaneRealMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabbedPaneRealMouseClicked
// TODO add your handling code here:
    int subTab = getTabbedPaneReal();
    if(getActualSubTabReal()!=subTab){
        if(subTab==1){
            copiarDeAyudaATexto();
        }else{
            copiarDeTextoAAyuda();
        }
        setActualSubTabReal(subTab);
    }
}//GEN-LAST:event_tabbedPaneRealMouseClicked

private void guardarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarButtonActionPerformed
// TODO add your handling code here:
    if(getTabbedPaneReal()==0){
        copiarTestAScenarioDesdeAyuda();
    }else if(getTabbedPaneReal()==1){
        copiarTestAScenarioDesdeSinAyuda();
    }
    if(continuar==true){
        if(continuarSinInstancias==true){
            this.realizarAccion(true, false);
        }else{
            addInst = new AddInstancesClasPropJDialog(this,true,this.getScenario());
            addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            addInst.setVisible(true);
        }
    }
}//GEN-LAST:event_guardarButtonActionPerformed

private void guardarEjecutarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarEjecutarButtonActionPerformed
// TODO add your handling code here:
    if(getTabbedPaneReal()==0){
        copiarTestAScenarioDesdeAyuda();
    }else if(getTabbedPaneReal()==1){
        copiarTestAScenarioDesdeSinAyuda();
    }
    if(continuar==true){
        if(continuarSinInstancias==true){
            this.realizarAccion(true, true);
        }else{
            addInst = new AddInstancesClasPropJDialog(this,true,this.getScenario());
            addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            addInst.setVisible(true);
        }
    } 
}//GEN-LAST:event_guardarEjecutarButtonActionPerformed

private void ejecutarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejecutarButtonActionPerformed
// TODO add your handling code here:
   if(getTabbedPaneReal()==0){
        copiarTestAScenarioDesdeAyuda();
    }else if(getTabbedPaneReal()==1){
        copiarTestAScenarioDesdeSinAyuda();
    }
    if(continuar==true){
        if(continuarSinInstancias==true){
            this.realizarAccion(false, true);
        }else{
            addInst = new AddInstancesClasPropJDialog(this,true,this.getScenario());
            addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            addInst.setVisible(true);
        }
    }
}//GEN-LAST:event_ejecutarButtonActionPerformed

private void asociarInstanciasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asociarInstanciasButtonActionPerformed
// TODO add your handling code here:
    addInst = new AddInstancesClasPropJDialog(this,true,this.getScenario());//GEN-LAST:event_asociarInstanciasButtonActionPerformed
    addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    addInst.setVisible(true);
}

public void realizarAccion(boolean guardar, boolean ejecutar){  
    saveTest = new SaveTest();
    if(testYaExiste==true){
        if(guardar==true){
            if(this.getScenarioAEditar() != null && scenario.equals(this.getScenarioAEditar())==false
                        && this.getScenario().getNombre().equals(this.getScenarioAEditar().getNombre())){
                Object[] options = {"Sobreescribir", "Cancelar"};
                int n = JOptionPane.showOptionDialog(frame, "El test ya existe o ha sido modificado. ¿Que desea hacer?", 
                        "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if (n == JOptionPane.YES_OPTION) {
                    saveTest.replaceScenarioLocally(scenario);
                    setScenarioAEditar(new ScenarioTest(scenario));
                    setScenario(new ScenarioTest(scenario));
                    controlador.setTestRealGuardado(true);
                    JOptionPane.showMessageDialog(this.getParent(),"El test ha sido sobreescrito",
                    "Confirm Message",JOptionPane.INFORMATION_MESSAGE);
                }
            }else{
                saveTest.saveTestInMemory(scenario);
                controlador.setTestRealGuardado(true);
                JOptionPane.showMessageDialog(this.getParent(),"No se han producido cambios en el test",
                "Confirm Message",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if(ejecutar==true){
            menu.ejecutarUnTest(this.getScenario());
        }
     }else{
        if(guardar==true){
            saveTest.saveTestInMemory(scenario);
            setScenarioAEditar(new ScenarioTest(scenario));
            setScenario(new ScenarioTest(scenario));
            controlador.setTestRealGuardado(true);
            JOptionPane.showMessageDialog(this.getParent(),"El test ha sido guardado",
            "Confirm Message",JOptionPane.INFORMATION_MESSAGE);
        }
        if(ejecutar==true){
            menu.ejecutarUnTest(this.getScenario());
        }
    }
    saveTest.actualizarListaDeTestsSimples();
}

public void inicializarVariables(){
    ambosNecesarios=false;
    continuarSinInstancias=true;
    testYaExiste=false;
    testSinNombre=false;
    validoReal=true;
    hayUnaConsulta=0;
    continuar=true;
}

public static ScenarioTest scenarioTestExistente(String nombre){
    ListIterator li;
    List<ScenarioTest> lista = CollectionTest.getInstance().getScenariotest();
    li = lista.listIterator();
    while(li.hasNext()){
        ScenarioTest s = (ScenarioTest) li.next();
        String n = s.getNombre();
        if(n.equals(nombre)){
            return s;
        }
    }
    return null;
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

public boolean consultaCompletaEnAyuda(TestInstancesQueryJPanel test){
    if((!test.getQuery().equals("") && test.getQueryResult().equals("")) ||
        test.getQuery().equals("") && !test.getQueryResult().equals("")){
        return false;
    }else{
        return true;
    }
}

public boolean panelVacio(TestInstancesQueryJPanel test){
    if(test.getQuery().equals("") && test.getQueryResult().equals("")){
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
    
    panelAyudaReal= this.getRealAyudaPanel();
    totalReal = panelAyudaReal.getComponentCount();
    validarTests = new ValidarTests();
    
    int cont=0;
    
    real = new ArrayList();
    getReal().add(0,0);
    descPanel = (DescripcionJPanel) descripcionJPanel.getComponent(0);
    nombreTest = descPanel.getNombreTextField();
    descTest = descPanel.getDescTextArea();
    if(utils.testYaExiste(nombreTest)==true){
        testYaExiste=true;
    }
    if(testVacio(nombreTest)==true){
        testSinNombre=true;
    }else{
        for(int i=1;i<totalReal;i++){
            test = (TestInstancesQueryJPanel) panelAyudaReal.getComponent(i);
            if(panelVacio(test)==false){
                if(ambosNecesarios==false){
                    if(consultaCompletaEnAyuda(test)==false){
                        ambosNecesarios=true;
                    }else{
                        String query = test.getQuery();
                        String resExpT = test.getQueryResult();
                        AddComentJDialog comentPane = test.getComment();
                        String coment = comentPane.getComent();
                        hayUnaConsulta=1;
                        QueryOntology testQuery = new QueryOntology(query,resExpT,coment);
                        if(validarTests.validarQuery(testQuery.getQuery())==true){
                            queryTest.add(testQuery);
                            cont++;
                            getReal().add(i, 0);
                        }else{
                            getReal().add(i, 1);
                            validoReal=false;
                        }
                    }
                }
            }
        }
    }
    
    if(testSinNombre==false && validoReal==true && ambosNecesarios==false
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
    }else if(validoReal==false){
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
    texto = (TestInstancesTextJPanel) getOpcionTextRealPanel().getComponent(0);
    conjuntoQuerys = texto.getConsultaQuery();
    conjuntoResult = texto.getResultadoEsperado();
    conjuntoComent = texto.getComentTextArea();
    cQuery = conjuntoQuerys.split("\\\n");
    cResult = conjuntoResult.split("\\\n");
    cComent = conjuntoComent.split("\\\n");
    
    nombreTest = descPanel.getNombreTextField();
    descTest = descPanel.getDescTextArea();
    
    real = new ArrayList();
    getReal().add(0,0);
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
                            if(validarTests.validarQuery(cQuery[i])==true &&
                                    validarTests.validarQuery(cResult[i])==true){
                                if(cComent.length!=0 && i!=cComent.length && i<=cComent.length){
                                    testQuery = new QueryOntology(cQuery[i],cResult[i],cComent[i]);
                                }else{
                                    testQuery = new QueryOntology(cQuery[i],cResult[i]);
                                }
                                queryTest.add(testQuery);
                                getReal().add(i, 0);
                            }else if(validarTests.validarQuery(cQuery[i])==false &&
                                    validarTests.validarQuery(cResult[i])==true){
                                getReal().add(i, 1);
                                validoReal=false;
                            }else if(validarTests.validarQuery(cQuery[i])==true &&
                                    validarTests.validarQuery(cResult[i])==false){
                                getReal().add(i, 2);
                                validoReal=false;
                            }else{
                                getReal().add(i, 3);
                                validoReal=false;
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
    if(testSinNombre==false && validoReal==true && ambosNecesarios==false
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
    }else if(validoReal==false){
        JOptionPane.showMessageDialog(this.getParent(),"El formato de los datos marcados en rojo no es correcto." +
        "\nPor favor, consulte la ayuda acerca del formato " +
        "de las consultas y el resultado.","Warning Message",JOptionPane.WARNING_MESSAGE);
        formatoIncorrecto();
        continuar=false;
    }
}

public void formatoIncorrecto(){
    ValidarConsultas validar = new ValidarConsultas();
    if(TestSimpleReal.getActualSubTabReal()==0){
        if(validar.comprovarErrorEnAyudaReal(panelAyudaReal)==false){
        }
    }else{
        if(validar.comprovarErrorQuerysReal(opcionTextRealPanel)==false){
        }
    }
}

public void copiarDeAyudaATexto(){
     
    test = null;
    TestInstancesTextJPanel t;
    String conjuntoQuerysReal="";
    String conjuntoResExpReal="";
    String conjuntoComentReal="";

    panelAyudaReal = this.getRealAyudaPanel();
    totalReal = panelAyudaReal.getComponentCount();

   for(int i=1;i<totalReal;i++){
        test = (TestInstancesQueryJPanel) panelAyudaReal.getComponent(i);
            if(!test.getQuery().equals("") || !test.getQueryResult().equals("")){
                String query = test.getQuery();
                String queryExp = test.getQueryResult();
                AddComentJDialog comentPane = test.getComment();
                String coment = comentPane.getComent();
                if(!query.equals("") || !queryExp.equals("")){
                    if(conjuntoQuerysReal.equals("")){
                        conjuntoQuerysReal = query.concat("\n");
                    }else{
                        conjuntoQuerysReal = conjuntoQuerysReal.concat(query).concat("\n");
                    }
                    if(conjuntoResExpReal.equals("")){
                        conjuntoResExpReal = queryExp.concat("\n");
                    }else{
                        conjuntoResExpReal = conjuntoResExpReal.concat(queryExp).concat("\n");
                    }
                    if(conjuntoComentReal.equals("")){
                        conjuntoComentReal = coment.concat("\n");
                    }else{
                        conjuntoComentReal = conjuntoComentReal.concat(coment).concat("\n");
                    }
                } 
            }
    }
    t = (TestInstancesTextJPanel) getOpcionTextRealPanel().getComponent(0);
    t.setConsultaQuery(conjuntoQuerysReal);
    t.setResultadoEsperado(conjuntoResExpReal);
    t.setComentTextArea(conjuntoComentReal);
    int c = realAyudaPanel.getComponentCount();
    TestInstancesQueryJPanel.setContadorReal(0);
    for(int i=1;i<c;i++){
        realAyudaPanel.remove(realAyudaPanel.getComponent(i));
        realAyudaPanel.add(new TestInstancesQueryJPanel(),i); 
    }
    realAyudaPanel.validate();
    t.validate();
}

public void copiarDeTextoAAyuda(){

    test = null;

    panelAyudaReal = this.getRealAyudaPanel();
    totalReal = panelAyudaReal.getComponentCount();

    TestInstancesTextJPanel ti;

    String conjuntoQuerysReal="",conjuntoResultReal="",conjuntoComentReal="";
    String cQuery[],cResult[],cComent[];

    ti = (TestInstancesTextJPanel) getOpcionTextRealPanel().getComponent(0);
        conjuntoQuerysReal = ti.getConsultaQuery().trim();
        conjuntoResultReal = ti.getResultadoEsperado().trim();
        conjuntoComentReal = ti.getComentTextArea().trim();
        cQuery = conjuntoQuerysReal.split("\\\n");
        cResult = conjuntoResultReal.split("\\\n");
        cComent = conjuntoComentReal.split("\\\n");
        int tamI = getRealAyudaPanel().getComponentCount();
        int tamInst = tamI-1;
        int j=1;
        if(!conjuntoQuerysReal.equals("")){
        for(int i=0; i<cQuery.length; i++){
            if(!cQuery[i].equals("")){
                if(i<tamInst){
                    test = (TestInstancesQueryJPanel) panelAyudaReal.getComponent(j);
                    test.setQuery(cQuery[i]);
                    j++;
                }else{
                    if(j<tamInst){
                        test = (TestInstancesQueryJPanel) panelAyudaReal.getComponent(j);
                        test.setQuery(cQuery[i]);
                        j++;
                    }else{
                        TestInstancesQueryJPanel instP = new TestInstancesQueryJPanel();
                        instP.setQuery(cQuery[i]);
                        getRealAyudaPanel().add(instP);
                    }
                }
            }
        }
        test.validate();
        }
        j=1;
        if(!conjuntoResultReal.equals("")){
        for(int i=0; i<cResult.length; i++){
            String res = cResult[i];
            if(!res.equals("")){
                if(i<tamInst){
                    test = (TestInstancesQueryJPanel) panelAyudaReal.getComponent(j);
                    test.setQueryResult(res);
                    j++;
                }else{
                    if(j<tamInst){
                        test = (TestInstancesQueryJPanel) panelAyudaReal.getComponent(j);
                        test.setQueryResult(res);
                        j++;
                    }else{
                        TestInstancesQueryJPanel instP = new TestInstancesQueryJPanel();
                        instP.setQueryResult(res);
                        getRealAyudaPanel().add(instP);
                    }
                }
            }
        }
        test.validate();
        }
        j=1;
        if(!conjuntoComentReal.equals("")){
        for(int i=0; i<cComent.length; i++){
            if(!cComent[i].equals("")){
                if(i<tamInst){
                    test = (TestInstancesQueryJPanel) panelAyudaReal.getComponent(j);
                    AddComentJDialog comentPane = test.getComment();
                    comentPane.setComent(cComent[i]);
                    test.setComment(comentPane);
                    j++;
                }else{
                    if(j<tamInst){
                        test = (TestInstancesQueryJPanel) panelAyudaReal.getComponent(j);
                        AddComentJDialog comentPane = test.getComment();
                        comentPane.setComent(cComent[i]);
                        test.setComment(comentPane);
                        j++;
                    }else{
                        TestInstancesQueryJPanel instP = new TestInstancesQueryJPanel();
                        AddComentJDialog comentPane = instP.getComment();
                        comentPane.setComent(cComent[i]);
                        instP.setComment(comentPane);
                        getRealAyudaPanel().add(instP);
                    }
                }
            }
        }  
        test.validate();
        }
}

public static List getReal() {
    return real;
}

public static int getActualSubTabReal() {
    return actualSubTabReal;
}

public static void setActualSubTabReal(int aActualSubTabReal) {
    actualSubTabReal = aActualSubTabReal;
}

public JPanel getRealAyudaPanel() {
    return realAyudaPanel;
}

public JPanel getOpcionTextRealPanel() {
        return opcionTextRealPanel;
}

public ScenarioTest getScenario() {
    return scenario;
}

public void setScenario(ScenarioTest aScenarioInst) {
    scenario = aScenarioInst;
}

public  int getTabbedPaneReal() {
    return tabbedPaneReal.getSelectedIndex();
}

public JPanel panelActualReal(){
    return this.getRealAyudaPanel();
}

public ScenarioTest getScenarioAEditar() {
    return scenarioAEditar;
}

public void setScenarioAEditar(ScenarioTest scenarioAEditar) {
    this.scenarioAEditar = scenarioAEditar;
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton asociarInstanciasButton;
    private javax.swing.JPanel contentGuardarEjecutarPanel;
    private javax.swing.JPanel contentRealTabedPanel;
    private javax.swing.JPanel descripcionJPanel;
    private javax.swing.JButton ejecutarButton;
    private javax.swing.JButton guardarButton;
    private javax.swing.JButton guardarEjecutarButton;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel labelRealLabel;
    private javax.swing.JPanel labelRealPanel;
    private javax.swing.JPanel opcionAyudaRealPanel;
    private javax.swing.JPanel opcionTextRealPanel;
    private javax.swing.JPanel realAyudaPanel;
    private javax.swing.JTabbedPane tabbedPaneReal;
    // End of variables declaration//GEN-END:variables

}
