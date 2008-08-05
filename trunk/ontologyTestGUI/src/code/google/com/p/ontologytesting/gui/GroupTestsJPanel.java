/*
 * GroupTestQueryJPanel.java
 *
 * Created on 29 de mayo de 2008, 9:02
 */

package code.google.com.p.ontologytesting.gui;

import code.google.com.p.ontologytesting.model.OntologyTestCase;
import code.google.com.p.ontologytesting.model.OntologyTestResult;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import code.google.com.p.ontologytesting.model.PropertyInstances;
import code.google.com.p.ontologytesting.model.ClassInstances;
import code.google.com.p.ontologytesting.model.CollectionTest;
import code.google.com.p.ontologytesting.model.Instancias;
import code.google.com.p.ontologytesting.model.QueryOntology;
import code.google.com.p.ontologytesting.model.ScenarioTest;
import code.google.com.p.ontologytesting.model.SparqlQueryOntology;

/**
 *
 * @author  sara.garcia
 */
public class GroupTestsJPanel extends javax.swing.JPanel {

    private static TestInstancesTFJPanel test;
    private static TestInstancesQueryJPanel test1;
    private static TestInstancesTextAreaJPanel test2;
    private static JPanel panelInst;
    private static JPanel panelClas;
    private static JPanel panelReal;
    private static JPanel panelRet;
    private static JPanel panelSat;
    private boolean ontologiaValida=false,nombreTestsValidos=false;
    private static boolean instTextName=true, retTextName=true, clasTextName=true,
            realTextName=true, satTextName=true;
    private static int actualSubTabInst=0, actualSubTabRet=0, actualSubTabClas=0,
            actualSubTabSat=0, actualSubTabReal=0;
    
    private static JPanel panelAyudaInst;
    private static int totalInst;
    private static JPanel panelAyudaClas;
    private static int totalClas;
    private static JPanel panelAyudaReal;
    private static int totalReal;
    private static JPanel panelAyudaRet;
    private static int totalRet;
    private static JPanel panelAyudaSat;
    private static int totalSat;
    
    static final int desktopWidth = 750;
    static final int desktopHeight = 600;
    static JFrame frame;

    public static ScenarioTest getScenarioTest() {
        return scenarioTest;
    }
    public static void setScenarioTest(ScenarioTest aScenarioTest) {
        scenarioTest = aScenarioTest;
    }
    public static JPanel getPanelTree() {
        return panelTree;
    }
    public static void setPanelTree(JPanel aPanelTree) {
        panelTree = aPanelTree;
    }

    public static int getActualSubTabInst() {
        return actualSubTabInst;
    }

    public static void setActualSubTabInst(int aActualSubTabInst) {
        actualSubTabInst = aActualSubTabInst;
    }

    public static int getActualSubTabRet() {
        return actualSubTabRet;
    }

    public static void setActualSubTabRet(int aActualSubTabRet) {
        actualSubTabRet = aActualSubTabRet;
    }

    public static int getActualSubTabClas() {
        return actualSubTabClas;
    }

    public static void setActualSubTabClas(int aActualSubTabClas) {
        actualSubTabClas = aActualSubTabClas;
    }

    public static int getActualSubTabSat() {
        return actualSubTabSat;
    }

    public static void setActualSubTabSat(int aActualSubTabSat) {
        actualSubTabSat = aActualSubTabSat;
    }

    public static int getActualSubTabReal() {
        return actualSubTabReal;
    }

    public static void setActualSubTabReal(int aActualSubTabReal) {
        actualSubTabReal = aActualSubTabReal;
    }
    private HelpJDialog frameHelp;
    public static boolean isState() {
        return seleccionado;
    }
    public static void setState(boolean aState) {
        seleccionado = aState;
    }
    public static boolean isNewState() {
        return seleccionado;
    }
    public static void setNewState(boolean aState) {
        seleccionado = aState;
    }
    public static boolean isDatosGuardados() {
        return datosGuardados;
    }
    public static void setDatosGuardados(boolean aDatosGuardados) {
        datosGuardados = aDatosGuardados;
    }
    public static OntologyTestResult getTestresult() {
        return testresult;
    }
    private AddInstancesJPanel addInstances;
    public static boolean seleccionado;
    private static boolean datosGuardados;
    private static OntologyTestResult testresult;
    private XMLDecoder decoder;
    private AddComentJDialog commentPane;
    private static ScenarioTest scenarioTest;
    private static JPanel panelTree;
    private static int nameTest=0;
    
    /** Creates new form GroupTestQueryJPanel */
    public GroupTestsJPanel(int num) {
        initComponents(); 
        setState(true);
        frameHelp = new HelpJDialog(frame,true); 
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(850, 600));
        testInstPanel.setLayout(new BoxLayout(testInstPanel, BoxLayout.Y_AXIS));
        testRetPanel.setLayout(new BoxLayout(testRetPanel, BoxLayout.Y_AXIS));
        testRealPanel.setLayout(new BoxLayout(testRealPanel, BoxLayout.Y_AXIS));
        testSatPanel.setLayout(new BoxLayout(testSatPanel, BoxLayout.Y_AXIS));
        testClasPanel.setLayout(new BoxLayout(testClasPanel, BoxLayout.Y_AXIS));
        opcionTextInstPanel.setLayout(new BoxLayout(getOpcionTextInstPanel(), BoxLayout.Y_AXIS));
        opcionTextRetPanel.setLayout(new BoxLayout(getOpcionTextRetPanel(), BoxLayout.Y_AXIS));
        opcionTextClasPanel.setLayout(new BoxLayout(getOpcionTextClasPanel(), BoxLayout.Y_AXIS));
        opcionTextSatPanel.setLayout(new BoxLayout(getOpcionTextSatPanel(), BoxLayout.Y_AXIS));
        opcionTextRealPanel.setLayout(new BoxLayout(getOpcionTextRealPanel(), BoxLayout.Y_AXIS));
        instAyudaPanel.setLayout(new BoxLayout(getInstAyudaPanel(), BoxLayout.Y_AXIS));
        retAyudaPanel.setLayout(new BoxLayout(getRetAyudaPanel(), BoxLayout.Y_AXIS));
        clasAyudaPanel.setLayout(new BoxLayout(getClasAyudaPanel(), BoxLayout.Y_AXIS));
        satAyudaPanel.setLayout(new BoxLayout(getSatAyudaPanel(), BoxLayout.Y_AXIS));
        realAyudaPanel.setLayout(new BoxLayout(getRealAyudaPanel(), BoxLayout.Y_AXIS));
        opcionTextInstPanel.add(new TestInstancesTextJPanel());    
        opcionTextRetPanel.add(new TestInstancesTextJPanel());
        opcionTextClasPanel.add(new TestInstancesTextJPanel());
        opcionTextSatPanel.add(new TestInstancesTextJPanel());
        opcionTextRealPanel.add(new TestInstancesTextJPanel());

        testInstPanel.add(new DescripcionJPanel(),0);
        testRetPanel.add(new DescripcionJPanel(),0);
        testRealPanel.add(new DescripcionJPanel(),0);
        testSatPanel.add(new DescripcionJPanel(),0);
        testClasPanel.add(new DescripcionJPanel(),0);
        
        for (int i = 0; i <= num; i++) {
            instAyudaPanel.add(new TestInstancesTFJPanel());
            retAyudaPanel.add(new TestInstancesTextAreaJPanel());
            realAyudaPanel.add(new TestInstancesQueryJPanel());
            satAyudaPanel.add(new TestInstancesTFJPanel());
            clasAyudaPanel.add(new TestInstancesTextAreaJPanel());
        }
                
        contentPanel.add(introduccionPanel,BorderLayout.NORTH);
        addInstances = new AddInstancesJPanel(this);
        contentPanel.add(testsTabbedPane,BorderLayout.CENTER);
        contentPanel.add(addInstances,BorderLayout.SOUTH);
    }
    
    public GroupTestsJPanel(String path) {
        
        initComponents(); 
        int pos=0;
        setNewState(true);
        frameHelp = new HelpJDialog(frame,true);
        
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(desktopWidth, desktopHeight));
        labelInstPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        instAyudaPanel.setLayout(new BoxLayout(getInstAyudaPanel(), BoxLayout.Y_AXIS));
        retAyudaPanel.setLayout(new BoxLayout(getRetAyudaPanel(), BoxLayout.Y_AXIS));
        clasAyudaPanel.setLayout(new BoxLayout(getClasAyudaPanel(), BoxLayout.Y_AXIS));
        satAyudaPanel.setLayout(new BoxLayout(getSatAyudaPanel(), BoxLayout.Y_AXIS));
        realAyudaPanel.setLayout(new BoxLayout(getRealAyudaPanel(), BoxLayout.Y_AXIS));
        opcionTextInstPanel.add(new TestInstancesTextJPanel());    
        opcionTextRetPanel.add(new TestInstancesTextAreaJPanel());
        opcionTextClasPanel.add(new TestInstancesTextAreaJPanel());
        opcionTextSatPanel.add(new TestInstancesTextJPanel());
        opcionTextRealPanel.add(new TestInstancesTextJPanel());

        try{
        decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(path)));
        CollectionTest cT = (CollectionTest) decoder.readObject();
        MainJPanel.setCollectionTest(cT);
        ArrayList<ScenarioTest> scenario = MainJPanel.getCollectionTest().getScenariotest();
        ListIterator it;
        it = scenario.listIterator();
        while(it.hasNext()){
                ScenarioTest s = (ScenarioTest) it.next();
                ArrayList<QueryOntology> qO = s.getQueryTest();
                
                ListIterator qi;
                qi = qO.listIterator();
                while(qi.hasNext()){   
                    QueryOntology cI = (QueryOntology) qi.next();
                    TestInstancesTFJPanel testTF = new TestInstancesTFJPanel();
                    pos++;
                    TestInstancesQueryJPanel testQuery = new TestInstancesQueryJPanel();
                    if(s.getTestName().equals("Instanciación") || s.getTestName().equals("Satisfactibilidad")){   
                        testTF.setQuery(cI.getQuery());
                        String resultExp = cI.getResultexpected();
                        if(resultExp.equals("true")){
                            testTF.setTrueTest(true);
                        }else{
                            testTF.setFalseTest(true);
                        }
                        commentPane = testTF.getComment();
                        commentPane.setComent(cI.getComment());
                        testTF.setComment(commentPane);
                    }else{
                        testQuery.setQuery(cI.getQuery());
                        testQuery.setQueryResult(cI.getResultexpected());
                        commentPane = testQuery.getComment();
                        commentPane.setComent(cI.getComment());
                        testQuery.setComment(commentPane);
                    }
                    if(s.getTestName().equals("Instanciación")){
                        instAyudaPanel.add(testTF);
                    }else if(s.getTestName().equals("Retrieval")){
                        retAyudaPanel.add(testQuery);
                    }else if(s.getTestName().equals("Realización")){
                        realAyudaPanel.add(testQuery);
                    }else if(s.getTestName().equals("Satisfactibilidad")){
                        satAyudaPanel.add(testTF);
                    }else if(s.getTestName().equals("Clasificación")){
                        clasAyudaPanel.add(testQuery);
                    }
                }           
        }
            decoder.close();    
        }catch(FileNotFoundException e){
        } 

        if(instAyudaPanel.getComponentCount()<8){
            while(instAyudaPanel.getComponentCount()<8){
                instAyudaPanel.add(new TestInstancesTFJPanel());
                pos++;
            }
        }
        if(retAyudaPanel.getComponentCount()<8){
            while(retAyudaPanel.getComponentCount()<8){
                retAyudaPanel.add(new TestInstancesTextAreaJPanel());
            }
        }
        if(realAyudaPanel.getComponentCount()<8){
            while(realAyudaPanel.getComponentCount()<8){
                realAyudaPanel.add(new TestInstancesQueryJPanel());
            }
        }
        if(satAyudaPanel.getComponentCount()<8){
            while(satAyudaPanel.getComponentCount()<8){
                satAyudaPanel.add(new TestInstancesTFJPanel());
                pos++;
            }
        }
        if(clasAyudaPanel.getComponentCount()<8){
            while(clasAyudaPanel.getComponentCount()<8){
                clasAyudaPanel.add(new TestInstancesTextAreaJPanel());
            }
        }
        
        contentPanel.add(introduccionPanel,BorderLayout.NORTH);
        addInstances = new AddInstancesJPanel(this);
        contentPanel.add(testsTabbedPane,BorderLayout.CENTER);
        contentPanel.add(addInstances,BorderLayout.SOUTH);  
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
        testsTabbedPane = new javax.swing.JTabbedPane();
        test1ScrollPane = new javax.swing.JScrollPane();
        testInstPanel = new javax.swing.JPanel();
        contentInstTabedPanel = new javax.swing.JPanel();
        tabbedPaneInst = new javax.swing.JTabbedPane();
        opcionAyudaInstPanel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        instAyudaPanel = new javax.swing.JPanel();
        labelInstPanel = new javax.swing.JPanel();
        labelInstLabel = new javax.swing.JLabel();
        opcionTextInstPanel = new javax.swing.JPanel();
        test2ScrollPane = new javax.swing.JScrollPane();
        testRetPanel = new javax.swing.JPanel();
        contentRetTabedPanel = new javax.swing.JPanel();
        tabbedPaneRet = new javax.swing.JTabbedPane();
        opcionAyudaRetPanel = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        retAyudaPanel = new javax.swing.JPanel();
        labelInstPanel2 = new javax.swing.JPanel();
        labelInstLabel1 = new javax.swing.JLabel();
        opcionTextRetPanel = new javax.swing.JPanel();
        test3ScrollPane = new javax.swing.JScrollPane();
        testRealPanel = new javax.swing.JPanel();
        contentRealTabedPanel = new javax.swing.JPanel();
        tabbedPaneReal = new javax.swing.JTabbedPane();
        opcionAyudaRealPanel = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        realAyudaPanel = new javax.swing.JPanel();
        labelInstPanel4 = new javax.swing.JPanel();
        labelInstLabel4 = new javax.swing.JLabel();
        opcionTextRealPanel = new javax.swing.JPanel();
        test4ScrollPane = new javax.swing.JScrollPane();
        testSatPanel = new javax.swing.JPanel();
        contentSatTabedPanel = new javax.swing.JPanel();
        tabbedPaneSat = new javax.swing.JTabbedPane();
        opcionAyudaSatPanel = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        satAyudaPanel = new javax.swing.JPanel();
        labelInstPanel6 = new javax.swing.JPanel();
        labelInstLabel6 = new javax.swing.JLabel();
        opcionTextSatPanel = new javax.swing.JPanel();
        test5ScrollPane = new javax.swing.JScrollPane();
        testClasPanel = new javax.swing.JPanel();
        contentClasTabedPanel = new javax.swing.JPanel();
        tabbedPaneClas = new javax.swing.JTabbedPane();
        opcionAyudaClasPanel = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        clasAyudaPanel = new javax.swing.JPanel();
        labelInstPanel8 = new javax.swing.JPanel();
        labelInstLabel8 = new javax.swing.JLabel();
        opcionTextClasPanel = new javax.swing.JPanel();
        introduccionPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        helpButton = new javax.swing.JButton();

        setName("GroupTestsJPanel"); // NOI18N

        testsTabbedPane.setToolTipText("");
        testsTabbedPane.setMinimumSize(new java.awt.Dimension(700, 550));

        test1ScrollPane.setToolTipText("Deduce si el individuo pertenece a la clase dada.");
        test1ScrollPane.setMinimumSize(new java.awt.Dimension(700, 550));

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
                .addContainerGap(95, Short.MAX_VALUE))
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
                .addContainerGap(293, Short.MAX_VALUE))
        );
        instAyudaPanelLayout.setVerticalGroup(
            instAyudaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(instAyudaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(213, Short.MAX_VALUE))
        );

        jScrollPane6.setViewportView(instAyudaPanel);

        javax.swing.GroupLayout opcionAyudaInstPanelLayout = new javax.swing.GroupLayout(opcionAyudaInstPanel);
        opcionAyudaInstPanel.setLayout(opcionAyudaInstPanelLayout);
        opcionAyudaInstPanelLayout.setHorizontalGroup(
            opcionAyudaInstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcionAyudaInstPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 807, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        opcionAyudaInstPanelLayout.setVerticalGroup(
            opcionAyudaInstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, opcionAyudaInstPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPaneInst.addTab("Formato ayuda", opcionAyudaInstPanel);

        javax.swing.GroupLayout opcionTextInstPanelLayout = new javax.swing.GroupLayout(opcionTextInstPanel);
        opcionTextInstPanel.setLayout(opcionTextInstPanelLayout);
        opcionTextInstPanelLayout.setHorizontalGroup(
            opcionTextInstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 845, Short.MAX_VALUE)
        );
        opcionTextInstPanelLayout.setVerticalGroup(
            opcionTextInstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 284, Short.MAX_VALUE)
        );

        tabbedPaneInst.addTab("Formato Texto", opcionTextInstPanel);

        javax.swing.GroupLayout contentInstTabedPanelLayout = new javax.swing.GroupLayout(contentInstTabedPanel);
        contentInstTabedPanel.setLayout(contentInstTabedPanelLayout);
        contentInstTabedPanelLayout.setHorizontalGroup(
            contentInstTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
            .addGroup(contentInstTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tabbedPaneInst, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE))
        );
        contentInstTabedPanelLayout.setVerticalGroup(
            contentInstTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
            .addGroup(contentInstTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tabbedPaneInst, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout testInstPanelLayout = new javax.swing.GroupLayout(testInstPanel);
        testInstPanel.setLayout(testInstPanelLayout);
        testInstPanelLayout.setHorizontalGroup(
            testInstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(testInstPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentInstTabedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(245, Short.MAX_VALUE))
        );
        testInstPanelLayout.setVerticalGroup(
            testInstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(testInstPanelLayout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(contentInstTabedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(142, Short.MAX_VALUE))
        );

        test1ScrollPane.setViewportView(testInstPanel);
        testInstPanel.getAccessibleContext().setAccessibleName("0");

        testsTabbedPane.addTab("Instanciación", test1ScrollPane);

        test2ScrollPane.setToolTipText("Deduce TODOS los individuos que pertenecen a una clase dada.");

        tabbedPaneRet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabbedPaneRetMouseClicked(evt);
            }
        });

        labelInstLabel1.setText("                      CONSULTAS                               RESULTADO ESPERADO ");

        javax.swing.GroupLayout labelInstPanel2Layout = new javax.swing.GroupLayout(labelInstPanel2);
        labelInstPanel2.setLayout(labelInstPanel2Layout);
        labelInstPanel2Layout.setHorizontalGroup(
            labelInstPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelInstPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );
        labelInstPanel2Layout.setVerticalGroup(
            labelInstPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, labelInstPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelInstLabel1)
                .addContainerGap())
        );

        javax.swing.GroupLayout retAyudaPanelLayout = new javax.swing.GroupLayout(retAyudaPanel);
        retAyudaPanel.setLayout(retAyudaPanelLayout);
        retAyudaPanelLayout.setHorizontalGroup(
            retAyudaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(retAyudaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(287, Short.MAX_VALUE))
        );
        retAyudaPanelLayout.setVerticalGroup(
            retAyudaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(retAyudaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(207, Short.MAX_VALUE))
        );

        jScrollPane7.setViewportView(retAyudaPanel);

        javax.swing.GroupLayout opcionAyudaRetPanelLayout = new javax.swing.GroupLayout(opcionAyudaRetPanel);
        opcionAyudaRetPanel.setLayout(opcionAyudaRetPanelLayout);
        opcionAyudaRetPanelLayout.setHorizontalGroup(
            opcionAyudaRetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcionAyudaRetPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
                .addContainerGap())
        );
        opcionAyudaRetPanelLayout.setVerticalGroup(
            opcionAyudaRetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcionAyudaRetPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        tabbedPaneRet.addTab("Formato ayuda", opcionAyudaRetPanel);

        javax.swing.GroupLayout opcionTextRetPanelLayout = new javax.swing.GroupLayout(opcionTextRetPanel);
        opcionTextRetPanel.setLayout(opcionTextRetPanelLayout);
        opcionTextRetPanelLayout.setHorizontalGroup(
            opcionTextRetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 821, Short.MAX_VALUE)
        );
        opcionTextRetPanelLayout.setVerticalGroup(
            opcionTextRetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        tabbedPaneRet.addTab("Formato Texto", opcionTextRetPanel);

        javax.swing.GroupLayout contentRetTabedPanelLayout = new javax.swing.GroupLayout(contentRetTabedPanel);
        contentRetTabedPanel.setLayout(contentRetTabedPanelLayout);
        contentRetTabedPanelLayout.setHorizontalGroup(
            contentRetTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
            .addGroup(contentRetTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tabbedPaneRet, javax.swing.GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE))
        );
        contentRetTabedPanelLayout.setVerticalGroup(
            contentRetTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 326, Short.MAX_VALUE)
            .addGroup(contentRetTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentRetTabedPanelLayout.createSequentialGroup()
                    .addComponent(tabbedPaneRet, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout testRetPanelLayout = new javax.swing.GroupLayout(testRetPanel);
        testRetPanel.setLayout(testRetPanelLayout);
        testRetPanelLayout.setHorizontalGroup(
            testRetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(testRetPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentRetTabedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(263, 263, 263))
        );
        testRetPanelLayout.setVerticalGroup(
            testRetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(testRetPanelLayout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(contentRetTabedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(157, Short.MAX_VALUE))
        );

        test2ScrollPane.setViewportView(testRetPanel);
        testRetPanel.getAccessibleContext().setAccessibleName("1");

        testsTabbedPane.addTab("Retrieval", test2ScrollPane);

        test3ScrollPane.setToolTipText("Deduce dado un individuo, cual es la clase más exacta a la que pertenece.");

        tabbedPaneReal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabbedPaneRealMouseClicked(evt);
            }
        });

        labelInstLabel4.setText("                      CONSULTAS                               RESULTADO ESPERADO");

        javax.swing.GroupLayout labelInstPanel4Layout = new javax.swing.GroupLayout(labelInstPanel4);
        labelInstPanel4.setLayout(labelInstPanel4Layout);
        labelInstPanel4Layout.setHorizontalGroup(
            labelInstPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelInstPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );
        labelInstPanel4Layout.setVerticalGroup(
            labelInstPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, labelInstPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelInstLabel4)
                .addContainerGap())
        );

        javax.swing.GroupLayout realAyudaPanelLayout = new javax.swing.GroupLayout(realAyudaPanel);
        realAyudaPanel.setLayout(realAyudaPanelLayout);
        realAyudaPanelLayout.setHorizontalGroup(
            realAyudaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(realAyudaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(296, Short.MAX_VALUE))
        );
        realAyudaPanelLayout.setVerticalGroup(
            realAyudaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(realAyudaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(204, Short.MAX_VALUE))
        );

        jScrollPane8.setViewportView(realAyudaPanel);

        javax.swing.GroupLayout opcionAyudaRealPanelLayout = new javax.swing.GroupLayout(opcionAyudaRealPanel);
        opcionAyudaRealPanel.setLayout(opcionAyudaRealPanelLayout);
        opcionAyudaRealPanelLayout.setHorizontalGroup(
            opcionAyudaRealPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcionAyudaRealPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
                .addContainerGap())
        );
        opcionAyudaRealPanelLayout.setVerticalGroup(
            opcionAyudaRealPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcionAyudaRealPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        tabbedPaneReal.addTab("Formato ayuda", opcionAyudaRealPanel);

        javax.swing.GroupLayout opcionTextRealPanelLayout = new javax.swing.GroupLayout(opcionTextRealPanel);
        opcionTextRealPanel.setLayout(opcionTextRealPanelLayout);
        opcionTextRealPanelLayout.setHorizontalGroup(
            opcionTextRealPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 830, Short.MAX_VALUE)
        );
        opcionTextRealPanelLayout.setVerticalGroup(
            opcionTextRealPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );

        tabbedPaneReal.addTab("Formato Texto", opcionTextRealPanel);

        javax.swing.GroupLayout contentRealTabedPanelLayout = new javax.swing.GroupLayout(contentRealTabedPanel);
        contentRealTabedPanel.setLayout(contentRealTabedPanelLayout);
        contentRealTabedPanelLayout.setHorizontalGroup(
            contentRealTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 835, Short.MAX_VALUE)
            .addGroup(contentRealTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tabbedPaneReal, javax.swing.GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE))
        );
        contentRealTabedPanelLayout.setVerticalGroup(
            contentRealTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
            .addGroup(contentRealTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentRealTabedPanelLayout.createSequentialGroup()
                    .addComponent(tabbedPaneReal, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout testRealPanelLayout = new javax.swing.GroupLayout(testRealPanel);
        testRealPanel.setLayout(testRealPanelLayout);
        testRealPanelLayout.setHorizontalGroup(
            testRealPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(testRealPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentRealTabedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(234, Short.MAX_VALUE))
        );
        testRealPanelLayout.setVerticalGroup(
            testRealPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(testRealPanelLayout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(contentRealTabedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(164, Short.MAX_VALUE))
        );

        test3ScrollPane.setViewportView(testRealPanel);

        testsTabbedPane.addTab("Realización", test3ScrollPane);

        test4ScrollPane.setToolTipText("Deduce si se puede o no añadir una nueva instancia de clase.");

        tabbedPaneSat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabbedPaneSatMouseClicked(evt);
            }
        });

        labelInstLabel6.setText("                      CONSULTAS                               RESULTADO ESPERADO  ");

        javax.swing.GroupLayout labelInstPanel6Layout = new javax.swing.GroupLayout(labelInstPanel6);
        labelInstPanel6.setLayout(labelInstPanel6Layout);
        labelInstPanel6Layout.setHorizontalGroup(
            labelInstPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelInstPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );
        labelInstPanel6Layout.setVerticalGroup(
            labelInstPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelInstPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout satAyudaPanelLayout = new javax.swing.GroupLayout(satAyudaPanel);
        satAyudaPanel.setLayout(satAyudaPanelLayout);
        satAyudaPanelLayout.setHorizontalGroup(
            satAyudaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(satAyudaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(297, Short.MAX_VALUE))
        );
        satAyudaPanelLayout.setVerticalGroup(
            satAyudaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(satAyudaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(208, Short.MAX_VALUE))
        );

        jScrollPane9.setViewportView(satAyudaPanel);

        javax.swing.GroupLayout opcionAyudaSatPanelLayout = new javax.swing.GroupLayout(opcionAyudaSatPanel);
        opcionAyudaSatPanel.setLayout(opcionAyudaSatPanelLayout);
        opcionAyudaSatPanelLayout.setHorizontalGroup(
            opcionAyudaSatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcionAyudaSatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
                .addContainerGap())
        );
        opcionAyudaSatPanelLayout.setVerticalGroup(
            opcionAyudaSatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcionAyudaSatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        tabbedPaneSat.addTab("Formato ayuda", opcionAyudaSatPanel);

        javax.swing.GroupLayout opcionTextSatPanelLayout = new javax.swing.GroupLayout(opcionTextSatPanel);
        opcionTextSatPanel.setLayout(opcionTextSatPanelLayout);
        opcionTextSatPanelLayout.setHorizontalGroup(
            opcionTextSatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 831, Short.MAX_VALUE)
        );
        opcionTextSatPanelLayout.setVerticalGroup(
            opcionTextSatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );

        tabbedPaneSat.addTab("Formato Texto", opcionTextSatPanel);

        javax.swing.GroupLayout contentSatTabedPanelLayout = new javax.swing.GroupLayout(contentSatTabedPanel);
        contentSatTabedPanel.setLayout(contentSatTabedPanelLayout);
        contentSatTabedPanelLayout.setHorizontalGroup(
            contentSatTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 836, Short.MAX_VALUE)
            .addGroup(contentSatTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tabbedPaneSat, javax.swing.GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE))
        );
        contentSatTabedPanelLayout.setVerticalGroup(
            contentSatTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 336, Short.MAX_VALUE)
            .addGroup(contentSatTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentSatTabedPanelLayout.createSequentialGroup()
                    .addComponent(tabbedPaneSat, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(20, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout testSatPanelLayout = new javax.swing.GroupLayout(testSatPanel);
        testSatPanel.setLayout(testSatPanelLayout);
        testSatPanelLayout.setHorizontalGroup(
            testSatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(testSatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentSatTabedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(233, Short.MAX_VALUE))
        );
        testSatPanelLayout.setVerticalGroup(
            testSatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(testSatPanelLayout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(contentSatTabedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(191, Short.MAX_VALUE))
        );

        test4ScrollPane.setViewportView(testSatPanel);

        testsTabbedPane.addTab("Satisfactibilidad", test4ScrollPane);

        test5ScrollPane.setToolTipText("Deduce, dado un individuo, TODAS las clases a las que pertenece.");

        tabbedPaneClas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabbedPaneClasMouseClicked(evt);
            }
        });

        labelInstLabel8.setText("                      CONSULTAS                               RESULTADO ESPERADO");

        javax.swing.GroupLayout labelInstPanel8Layout = new javax.swing.GroupLayout(labelInstPanel8);
        labelInstPanel8.setLayout(labelInstPanel8Layout);
        labelInstPanel8Layout.setHorizontalGroup(
            labelInstPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelInstPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(104, Short.MAX_VALUE))
        );
        labelInstPanel8Layout.setVerticalGroup(
            labelInstPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelInstPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout clasAyudaPanelLayout = new javax.swing.GroupLayout(clasAyudaPanel);
        clasAyudaPanel.setLayout(clasAyudaPanelLayout);
        clasAyudaPanelLayout.setHorizontalGroup(
            clasAyudaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clasAyudaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(296, Short.MAX_VALUE))
        );
        clasAyudaPanelLayout.setVerticalGroup(
            clasAyudaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clasAyudaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(203, Short.MAX_VALUE))
        );

        jScrollPane10.setViewportView(clasAyudaPanel);

        javax.swing.GroupLayout opcionAyudaClasPanelLayout = new javax.swing.GroupLayout(opcionAyudaClasPanel);
        opcionAyudaClasPanel.setLayout(opcionAyudaClasPanelLayout);
        opcionAyudaClasPanelLayout.setHorizontalGroup(
            opcionAyudaClasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcionAyudaClasPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
                .addContainerGap())
        );
        opcionAyudaClasPanelLayout.setVerticalGroup(
            opcionAyudaClasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(opcionAyudaClasPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        tabbedPaneClas.addTab("Formato ayuda", opcionAyudaClasPanel);

        javax.swing.GroupLayout opcionTextClasPanelLayout = new javax.swing.GroupLayout(opcionTextClasPanel);
        opcionTextClasPanel.setLayout(opcionTextClasPanelLayout);
        opcionTextClasPanelLayout.setHorizontalGroup(
            opcionTextClasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 830, Short.MAX_VALUE)
        );
        opcionTextClasPanelLayout.setVerticalGroup(
            opcionTextClasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 302, Short.MAX_VALUE)
        );

        tabbedPaneClas.addTab("Formato Texto", opcionTextClasPanel);

        javax.swing.GroupLayout contentClasTabedPanelLayout = new javax.swing.GroupLayout(contentClasTabedPanel);
        contentClasTabedPanel.setLayout(contentClasTabedPanelLayout);
        contentClasTabedPanelLayout.setHorizontalGroup(
            contentClasTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 835, Short.MAX_VALUE)
            .addGroup(contentClasTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tabbedPaneClas, javax.swing.GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE))
        );
        contentClasTabedPanelLayout.setVerticalGroup(
            contentClasTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 341, Short.MAX_VALUE)
            .addGroup(contentClasTabedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(contentClasTabedPanelLayout.createSequentialGroup()
                    .addComponent(tabbedPaneClas, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout testClasPanelLayout = new javax.swing.GroupLayout(testClasPanel);
        testClasPanel.setLayout(testClasPanelLayout);
        testClasPanelLayout.setHorizontalGroup(
            testClasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(testClasPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentClasTabedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(234, Short.MAX_VALUE))
        );
        testClasPanelLayout.setVerticalGroup(
            testClasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(testClasPanelLayout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(contentClasTabedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(204, Short.MAX_VALUE))
        );

        test5ScrollPane.setViewportView(testClasPanel);

        testsTabbedPane.addTab("Clasificación", test5ScrollPane);

        jLabel1.setText("Complete los tests que desee realizar ");

        helpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/help.gif"))); // NOI18N
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout introduccionPanelLayout = new javax.swing.GroupLayout(introduccionPanel);
        introduccionPanel.setLayout(introduccionPanelLayout);
        introduccionPanelLayout.setHorizontalGroup(
            introduccionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, introduccionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 301, Short.MAX_VALUE)
                .addComponent(helpButton)
                .addContainerGap())
        );
        introduccionPanelLayout.setVerticalGroup(
            introduccionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, introduccionPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(introduccionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(helpButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(introduccionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(testsTabbedPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE))
                .addContainerGap())
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(introduccionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(testsTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getAccessibleContext().setAccessibleName("GroupTestsJPanel");
    }// </editor-fold>//GEN-END:initComponents

private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
// TODO add your handling code here:
    frameHelp.setVisible(true);
}//GEN-LAST:event_helpButtonActionPerformed

private void tabbedPaneInstMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabbedPaneInstMouseClicked
// TODO add your handling code here:
    int subTab = getTabbedPaneInst();
    if(getActualSubTabInst()!=subTab){
        if(subTab==1){
            copiarDeAyudaATexto(0);
        }else{
            copiarDeTextoAAyuda(0);
        }
        setActualSubTabInst(subTab);
    }
}//GEN-LAST:event_tabbedPaneInstMouseClicked

private void tabbedPaneRetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabbedPaneRetMouseClicked
// TODO add your handling code here:
    int subTab = getTabbedPaneRet();
    if(getActualSubTabRet()!=subTab){
        if(subTab==1){
            copiarDeAyudaATexto(1);
        }else{
            copiarDeTextoAAyuda(1);
        }
        setActualSubTabRet(subTab);
    }
}//GEN-LAST:event_tabbedPaneRetMouseClicked

private void tabbedPaneRealMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabbedPaneRealMouseClicked
// TODO add your handling code here:
    int subTab = getTabbedPaneReal();
    if(getActualSubTabReal()!=subTab){
        if(subTab==1){
            copiarDeAyudaATexto(2);
        }else{
            copiarDeTextoAAyuda(2);
        }
        setActualSubTabReal(subTab);
    }
}//GEN-LAST:event_tabbedPaneRealMouseClicked

private void tabbedPaneSatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabbedPaneSatMouseClicked
// TODO add your handling code here:
    int subTab = getTabbedPaneSat();
    if(getActualSubTabSat()!=subTab){
        if(subTab==1){
            copiarDeAyudaATexto(3);
        }else{
            copiarDeTextoAAyuda(3);
        }
        setActualSubTabSat(subTab);
    }
}//GEN-LAST:event_tabbedPaneSatMouseClicked

private void tabbedPaneClasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabbedPaneClasMouseClicked
// TODO add your handling code here:
    int subTab = getTabbedPaneClas();
    if(getActualSubTabClas()!=subTab){
        if(subTab==1){
            copiarDeAyudaATexto(4);
        }else{
            copiarDeTextoAAyuda(4);
        }
        setActualSubTabClas(subTab);
    }
}//GEN-LAST:event_tabbedPaneClasMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JPanel clasAyudaPanel;
    private javax.swing.JPanel contentClasTabedPanel;
    private javax.swing.JPanel contentInstTabedPanel;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel contentRealTabedPanel;
    private javax.swing.JPanel contentRetTabedPanel;
    private javax.swing.JPanel contentSatTabedPanel;
    private javax.swing.JButton helpButton;
    private static javax.swing.JPanel instAyudaPanel;
    private javax.swing.JPanel introduccionPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel labelInstLabel;
    private javax.swing.JLabel labelInstLabel1;
    private javax.swing.JLabel labelInstLabel4;
    private javax.swing.JLabel labelInstLabel6;
    private javax.swing.JLabel labelInstLabel8;
    private javax.swing.JPanel labelInstPanel;
    private javax.swing.JPanel labelInstPanel2;
    private javax.swing.JPanel labelInstPanel4;
    private javax.swing.JPanel labelInstPanel6;
    private javax.swing.JPanel labelInstPanel8;
    private javax.swing.JPanel opcionAyudaClasPanel;
    private javax.swing.JPanel opcionAyudaInstPanel;
    private javax.swing.JPanel opcionAyudaRealPanel;
    private javax.swing.JPanel opcionAyudaRetPanel;
    private javax.swing.JPanel opcionAyudaSatPanel;
    private static javax.swing.JPanel opcionTextClasPanel;
    private static javax.swing.JPanel opcionTextInstPanel;
    private static javax.swing.JPanel opcionTextRealPanel;
    private static javax.swing.JPanel opcionTextRetPanel;
    private static javax.swing.JPanel opcionTextSatPanel;
    private static javax.swing.JPanel realAyudaPanel;
    private static javax.swing.JPanel retAyudaPanel;
    private static javax.swing.JPanel satAyudaPanel;
    private static javax.swing.JTabbedPane tabbedPaneClas;
    private static javax.swing.JTabbedPane tabbedPaneInst;
    private static javax.swing.JTabbedPane tabbedPaneReal;
    private static javax.swing.JTabbedPane tabbedPaneRet;
    private static javax.swing.JTabbedPane tabbedPaneSat;
    private javax.swing.JScrollPane test1ScrollPane;
    private javax.swing.JScrollPane test2ScrollPane;
    private javax.swing.JScrollPane test3ScrollPane;
    private javax.swing.JScrollPane test4ScrollPane;
    private javax.swing.JScrollPane test5ScrollPane;
    private static javax.swing.JPanel testClasPanel;
    private static javax.swing.JPanel testInstPanel;
    private static javax.swing.JPanel testRealPanel;
    private static javax.swing.JPanel testRetPanel;
    private static javax.swing.JPanel testSatPanel;
    private static javax.swing.JTabbedPane testsTabbedPane;
    // End of variables declaration//GEN-END:variables

public void guardarDatos(){
      
    OntologyTestCase testcase = new OntologyTestCase();
    testresult = new OntologyTestResult();

    String ontologyFisical=MainJPanel.getFisicalOntologyTextField();
    
    //if(ontologyFisical.endsWith(".owl")){
        //setOntologiaValida(true);
    String ontologyURI = MainJPanel.getNamespaceOntologyTextField();
    
    MainJPanel.getCollectionTest().setOntology("file:".concat(ontologyFisical));
    if(ontologyURI.endsWith("#")){
        MainJPanel.getCollectionTest().setNamespace(ontologyURI);
    }else{
        MainJPanel.getCollectionTest().setNamespace(ontologyURI.concat("#"));
    }   
    
    if(AddSPARQLJPanel.isSeleccionado()==false){
        for(int j=0;j<5;j++){
            GroupTestsJPanel.asociarInstancias(j);
        }
    }
    
    if(AddSPARQLJPanel.isSeleccionado()==true){
        if(AddSPARQLJPanel.getSPARQLQuery().equals("")){
                ArrayList<ScenarioTest> scT = MainJPanel.getCollectionTest().getScenariotest();
                ScenarioTest scenarioSparql = new ScenarioTest();
                scenarioSparql.setTestName("sparql");
                scenarioSparql.setDescripcion(AddSPARQLJPanel.getTestDescTextArea());
                scenarioSparql.setNombre(AddSPARQLJPanel.getTestNameTextField());
                ArrayList<SparqlQueryOntology> listQuerys = AddSPARQLJPanel.getListSparqlQuerys();
                scenarioSparql.setSparqlQuerys(listQuerys);
                if(scT.size()==0){
                    scT.add(scenarioSparql);
                    MainJPanel.getCollectionTest().setScenariotest(scT);
                }else{
                    MainJPanel.getCollectionTest().getScenariotest().add(scenarioSparql);
                }
                AddInstancesJPanel.setStateNuevo(false);
            }else{
                ScenarioTest scenarioSparql = new ScenarioTest();
                ArrayList<SparqlQueryOntology> listSparqlQuerys = AddSPARQLJPanel.getListSparqlQuerys();
                if(!AddSPARQLJPanel.getSPARQLQuery().equals("") && !AddSPARQLJPanel.getResultTextArea().equals("")){
                    
                    SparqlQueryOntology query = new SparqlQueryOntology(AddSPARQLJPanel.getSPARQLQuery(),
                            AddSPARQLJPanel.getResultTextArea());
                    
                    if(AddSPARQLJPanel.getListSparqlQuerys().size()==AddSPARQLJPanel.getPosListQuerysSel()){
                        listSparqlQuerys.add(query);
                    }else if(GroupTestsJPanel.inListSparqlQuerys(query)==false){
                        listSparqlQuerys.remove(AddSPARQLJPanel.getPosListQuerysSel());
                        listSparqlQuerys.add(AddSPARQLJPanel.getPosListQuerysSel(),query);
                    }
                    
                    scenarioSparql.setNombre(AddSPARQLJPanel.getTestNameTextField());
                    scenarioSparql.setTestName("sparql");
                    scenarioSparql.setDescripcion(AddSPARQLJPanel.getTestDescTextArea());
                    scenarioSparql.setSparqlQuerys(listSparqlQuerys);
                    ArrayList<ScenarioTest> st = MainJPanel.getCollectionTest().getScenariotest();
                    if(st.size()==0){
                        st.add(scenarioSparql);
                        MainJPanel.getCollectionTest().setScenariotest(st);
                    }else{
                        MainJPanel.getCollectionTest().getScenariotest().add(scenarioSparql);
                    }    
            }
        }
    }
    
    if(todosTienenNombre()==true){
        setNombreTestsValidos(true);
        Component comp = null;
        int n = JOptionPane.showConfirmDialog(comp, "¿Quiere guardar estos tests " +
            "para futuras pruebas?", "Guardar Tests",JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION){
            ArrayList<ScenarioTest> scenarioT = MainJPanel.getCollectionTest().getScenariotest();
            try{ 
                for(int i=0;i<scenarioT.size();i++){
                    int val=1;
                    if(AddSPARQLJPanel.isSeleccionado()==false){
                        if(!scenarioT.get(i).getNombre().equals("")){
                            XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new 
                            FileOutputStream(Configuration.getPathTestSimples()+"/"+scenarioT.get(i).getNombre()+".xml")));
                            e.writeObject(scenarioT.get(i));
                            e.close();
                        }
                    }else{
                        if(!scenarioT.get(i).getNombre().equals("")){
                            XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new 
                            FileOutputStream(Configuration.getPathTestSparql()+"/"
                            +scenarioT.get(i).getNombre().concat("_"+val).concat(".xml"))));
                            val++;
                            e.writeObject(scenarioT.get(i)); 
                            e.close();
                        }
                    }
                }
            }catch (FileNotFoundException ex) {
                //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            testcase.run(testresult, MainJPanel.getCollectionTest());
            JPanel panel = new TreeResults(testresult);
            GroupTestsJPanel.setPanelTree(panel);
            GroupTestsJPanel.setDatosGuardados(true);
        }else{
            testcase.run(testresult, MainJPanel.getCollectionTest());
            JPanel panel = new TreeResults(testresult);
            GroupTestsJPanel.setPanelTree(panel);
            GroupTestsJPanel.setDatosGuardados(true); 
        }
        }else{
            setNombreTestsValidos(false);
        }
    /*}else{
            setOntologiaValida(false);
    }*/
}   

public boolean isScenarioEmpty(ScenarioTest scenarioTest){
    
    ArrayList<ClassInstances> classInstances = scenarioTest.getInstancias().getClassInstances();
    ArrayList<PropertyInstances> propertyInstances = scenarioTest.getInstancias().getPropertyInstances();
    ArrayList<QueryOntology> queryTest = scenarioTest.getQueryTest();
    ArrayList<SparqlQueryOntology> sparqlQuerys = scenarioTest.getSparqlQuerys();
    if(classInstances.isEmpty() && propertyInstances.isEmpty() && 
        queryTest.isEmpty() && sparqlQuerys.isEmpty()){
        return true;
     }else{
        return false;
     }
}
    
public static void asociarInstancias(int sel){
       
    ScenarioTest scenario = new ScenarioTest();
    int aux=0;
    String nombreTest = "",descTest = "";
    ArrayList<QueryOntology> queryTest1 = new ArrayList<QueryOntology>();
    ArrayList<QueryOntology> queryTest2 = new ArrayList<QueryOntology>();
    ArrayList<QueryOntology> queryTest3 = new ArrayList<QueryOntology>();
    ArrayList<QueryOntology> queryTest4 = new ArrayList<QueryOntology>();
    ArrayList<QueryOntology> queryTest5 = new ArrayList<QueryOntology>();
    DescripcionJPanel descPanel = null;
    test = null;
    test1 = null;
    test2 = null;
    
    panelInst = getTestInstPanel();   
    panelClas = getTestClasPanel();   
    panelReal = getTestRealPanel();    
    panelRet = getTestRetPanel();    
    panelSat = getTestSatPanel();   
    
    panelAyudaInst = GroupTestsJPanel.getInstAyudaPanel();
    totalInst = panelAyudaInst.getComponentCount();
    panelAyudaClas = GroupTestsJPanel.getClasAyudaPanel();
    totalClas = panelAyudaClas.getComponentCount();
    panelAyudaReal = GroupTestsJPanel.getRealAyudaPanel();
    totalReal = panelAyudaReal.getComponentCount();
    panelAyudaRet = GroupTestsJPanel.getRetAyudaPanel();
    totalRet = panelAyudaRet.getComponentCount();
    panelAyudaSat = GroupTestsJPanel.getSatAyudaPanel();
    totalSat = panelAyudaSat.getComponentCount();
    
    TestInstancesTextJPanel texto;
    
    int var=0,cont=0;
    String conjuntoQuerys;
    String conjuntoResult;
    String conjuntoComent;
        String[] cQuery;
        String[] cResult;
        String[] cComent;
    
    if(sel==0){
        if(getTabbedPaneInst()==0){
            descPanel = (DescripcionJPanel) panelInst.getComponent(0);
                for(int i=1;i<totalInst;i++){
                if(getInstTextName()==true){
                test = (TestInstancesTFJPanel) panelAyudaInst.getComponent(i);
                    if(!test.getQuery().equals("") && !test.isTestFalse().equals(test.isTestTrue())){
                        if(descPanel.getNombreTextField().equals("")){
                                setInstTextName(false);
                        }else{
                        if(var==0){
                            nombreTest = descPanel.getNombreTextField();
                            descTest = descPanel.getDescTextArea();
                            scenario.setDescripcion(descTest);
                            scenario.setNombre(nombreTest);
                        }
                        String query = test.getQuery();
                        String resExpT = test.isTestTrue();
                        String resExpF = test.isTestFalse();
                        AddComentJDialog comentPane = test.getComment();
                        String coment = comentPane.getComent();
                        if(!query.equals("") && !resExpT.equals(resExpF)){
                            if(var==0){
                                scenario.setTestName("Instanciación");
                                var=1;
                            }
                            QueryOntology testQuery = new QueryOntology(query,resExpT,coment);
                            queryTest1.add(testQuery);
                            scenario.setQueryTest(queryTest1);
                            cont++;
                            aux=1;
                        }else if((!query.equals("") && resExpT.equals(resExpF)) || ((query.equals("") && !resExpT.equals(resExpF)))){
                            JOptionPane.showMessageDialog(frame,"Ambos campos CONSULTA y RESULTADO ESPERADO " +
                            "son obligatorios.","Warning Message",JOptionPane.WARNING_MESSAGE);
                        }else{
                            i++;
                        }
                    } 
                }
                }
                }
            if(getInstTextName()==true){
                if(AddInstancesJPanel.isStateNuevo()==true){
                    int c = instAyudaPanel.getComponentCount();
                    for (int i = 1; i < c; i++) {
                        instAyudaPanel.remove(instAyudaPanel.getComponent(i));
                        instAyudaPanel.add(new TestInstancesTFJPanel(),i); 
                    }
                    instAyudaPanel.validate();
                }
                if(aux==1){
                    if(AddInstancesJPanel.isStateNuevo()==true){
                        descPanel.setDescTextArea("");
                        descPanel.setNombreTextField("");
                    }
                    Instancias instancias = ContentMainJFrame.getInstancias().get(sel);                   
                    scenario.setInstancias(instancias);
                    ArrayList<ScenarioTest> st = MainJPanel.getCollectionTest().getScenariotest();
                    if(st.size()==0){
                        st.add(scenario);
                        MainJPanel.getCollectionTest().setScenariotest(st);
                    }else{
                        MainJPanel.getCollectionTest().getScenariotest().add(scenario);
                    }
                    ContentMainJFrame.setInstancias(sel,new Instancias());
                }
        }
        }else{
            descPanel = (DescripcionJPanel) panelInst.getComponent(0);
            texto = (TestInstancesTextJPanel) getOpcionTextInstPanel().getComponent(0);
            conjuntoQuerys = texto.getConsultaTextArea();
            conjuntoResult = texto.getResultadoEsperadoTextArea();
            conjuntoComent = texto.getComentTextArea();
            if(descPanel.getNombreTextField().equals("") && !conjuntoQuerys.equals("")){
                        setInstTextName(false);
            }else{
                cQuery = conjuntoQuerys.split("\\\n");
                cResult = conjuntoResult.split("\\\n");
                cComent = conjuntoComent.split("\\\n");
                if((conjuntoQuerys.equals("") && !conjuntoResult.equals("")) || 
                    (!conjuntoQuerys.equals("") && conjuntoResult.equals(""))){
                    JOptionPane.showMessageDialog(frame,"Ambos campos CONSULTA y RESULTADO ESPERADO " +
                            "son obligatorios.","Warning Message",JOptionPane.WARNING_MESSAGE);
                }else if(!conjuntoQuerys.equals("") && !conjuntoResult.equals("")){
                    if(var==0){
                        nombreTest = descPanel.getNombreTextField();
                        descTest = descPanel.getDescTextArea();
                        scenario.setDescripcion(descTest);
                        scenario.setNombre(nombreTest);
                        scenario.setTestName("Instanciación");
                        var=1;
                    }
                    QueryOntology testQuery = new QueryOntology();
                    for(int i=0; i<cQuery.length;i++){
                        if(cComent.length!=0 && i!=cComent.length && i<=cComent.length){
                            testQuery = new QueryOntology(cQuery[i],cResult[i],cComent[i]);
                        }else{
                            testQuery = new QueryOntology(cQuery[i],cResult[i]);
                        }
                        queryTest1.add(testQuery);
                        scenario.setQueryTest(queryTest1);
                        aux=1;
                    }
                }
                if(aux==1){
                    if(AddInstancesJPanel.isStateNuevo()==true){
                        descPanel.setDescTextArea("");
                        descPanel.setNombreTextField("");
                        texto.setComentTextArea("");
                        texto.setConsultaTextArea("");
                        texto.setResultadoEsperadoTextArea("");
                    }
                    Instancias instancias = ContentMainJFrame.getInstancias().get(sel);
                    scenario.setInstancias(instancias);
                    ArrayList<ScenarioTest> st = MainJPanel.getCollectionTest().getScenariotest();
                    if(st.size()==0){
                        st.add(scenario);
                        MainJPanel.getCollectionTest().setScenariotest(st);
                    }else{
                        MainJPanel.getCollectionTest().getScenariotest().add(scenario);
                    }
                    ContentMainJFrame.setInstancias(sel,new Instancias());
                }
            }
        }
      var=0;
    }else if(sel==1){
        if(getTabbedPaneRet()==0){
            for(int i=1;i<totalRet;i++){
                if(getRetTextName()==true){
                test2 = (TestInstancesTextAreaJPanel) panelAyudaRet.getComponent(i);
                descPanel = (DescripcionJPanel) panelRet.getComponent(0);
                if(!test2.getQuery().equals("") && !test2.getQueryResult().equals("")){
                    if(descPanel.getNombreTextField().equals("")){
                        setRetTextName(false);
                    }else{
                        if(var==0){
                            nombreTest = descPanel.getNombreTextField();
                            descTest = descPanel.getDescTextArea();
                            scenario.setDescripcion(descTest);
                            scenario.setNombre(nombreTest);
                        }
                        String query = test2.getQuery();
                        String queryExp = test2.getQueryResult();
                        AddComentJDialog comentPane = test2.getComment();
                        String coment = comentPane.getComent();
                        if(!query.equals("") && !queryExp.equals("")){
                            if(var==0){
                                scenario.setTestName("Retrieval");
                                var=1;
                            }
                            aux=1;
                            QueryOntology testQuery = new QueryOntology(query,queryExp,coment);
                            queryTest2.add(testQuery);
                            scenario.setQueryTest(queryTest2);
                            cont++;
                            aux=1;
                        }else if((!query.equals("") && queryExp.equals("")) || 
                                (query.equals("") && !queryExp.equals(""))){
                                JOptionPane.showMessageDialog(frame,"Ambos campos CONSULTA " +
                                "y RESULTADO ESPERADO son obligatorios.",
                                "Warning Message",JOptionPane.WARNING_MESSAGE);
                        }else{
                            i++;
                        }
                    }
                    
                }
            }
            }
            if(getRetTextName()==true){
            if(AddInstancesJPanel.isStateNuevo()==true){
                int c = retAyudaPanel.getComponentCount();
                for (int i = 1; i < c; i++) {
                    retAyudaPanel.remove(retAyudaPanel.getComponent(i));
                    retAyudaPanel.add(new TestInstancesTextAreaJPanel(),i); 
                }
                retAyudaPanel.validate();
            }
        
            if(aux==1){
            if(AddInstancesJPanel.isStateNuevo()==true){
                descPanel.setDescTextArea("");
                descPanel.setNombreTextField("");
            }
            Instancias instancias = ContentMainJFrame.getInstancias().get(sel);
            scenario.setInstancias(instancias);
            ArrayList<ScenarioTest> st = MainJPanel.getCollectionTest().getScenariotest();
            if(st.size()==0){
                st.add(scenario);
                MainJPanel.getCollectionTest().setScenariotest(st);
            }else{
                MainJPanel.getCollectionTest().getScenariotest().add(scenario);
            }
                ContentMainJFrame.setInstancias(sel,new Instancias());
            }
        }
        }else{
            descPanel = (DescripcionJPanel) panelRet.getComponent(0);
            texto = (TestInstancesTextJPanel) getOpcionTextRetPanel().getComponent(0);
            conjuntoQuerys = texto.getConsultaTextArea();
            conjuntoResult = texto.getResultadoEsperadoTextArea();
            conjuntoComent = texto.getComentTextArea();
            if(descPanel.getNombreTextField().equals("") && !conjuntoQuerys.equals("")){
                        setRetTextName(false);
            }else{
            cQuery = conjuntoQuerys.split("\\\n");
            cResult = conjuntoResult.split("\\\n");
            cComent = conjuntoComent.split("\\\n");
            if((conjuntoQuerys.equals("") && !conjuntoResult.equals("")) || 
                    (!conjuntoQuerys.equals("") && conjuntoResult.equals(""))){
                    JOptionPane.showMessageDialog(frame,"Ambos campos CONSULTA y RESULTADO ESPERADO " +
                            "son obligatorios.","Warning Message",JOptionPane.WARNING_MESSAGE);
            }else if(!conjuntoQuerys.equals("") && !conjuntoResult.equals("")){
                if(var==0){
                    nombreTest = descPanel.getNombreTextField();
                    descTest = descPanel.getDescTextArea();
                    scenario.setDescripcion(descTest);
                    scenario.setNombre(nombreTest);
                    scenario.setTestName("Retrieval");
                    var=1;
                }
                QueryOntology testQuery = new QueryOntology();
                for(int i=0; i<cQuery.length;i++){
                    if(cComent.length!=0 && i!=cComent.length && i<=cComent.length){
                        testQuery = new QueryOntology(cQuery[i],cResult[i],cComent[i]);
                    }else{
                        testQuery = new QueryOntology(cQuery[i],cResult[i]);
                    }
                    queryTest1.add(testQuery);
                    scenario.setQueryTest(queryTest1);
                    aux=1;
                }
            }
            if(aux==1){
                if(AddInstancesJPanel.isStateNuevo()==true){
                    descPanel.setDescTextArea("");
                    descPanel.setNombreTextField("");
                    texto.setComentTextArea("");
                    texto.setConsultaTextArea("");
                    texto.setResultadoEsperadoTextArea("");
                }
                Instancias instancias = ContentMainJFrame.getInstancias().get(sel);
                scenario.setInstancias(instancias);
                ArrayList<ScenarioTest> st = MainJPanel.getCollectionTest().getScenariotest();
                if(st.size()==0){
                    st.add(scenario);
                    MainJPanel.getCollectionTest().setScenariotest(st);
                }else{
                    MainJPanel.getCollectionTest().getScenariotest().add(scenario);
                }
                ContentMainJFrame.setInstancias(sel,new Instancias());
            }
        }
        }
    }else if(sel==2){
        if(getTabbedPaneReal()==0){
            for(int i=1;i<totalReal;i++){
                if(getRealTextName()==true){
                test1 = (TestInstancesQueryJPanel) panelAyudaReal.getComponent(i);
                descPanel = (DescripcionJPanel) panelReal.getComponent(0);
                if(!test1.getQuery().equals("") && !test1.getQueryResult().equals("")){
                    if(descPanel.getNombreTextField().equals("")){
                        setRealTextName(false);
                    }else{
                        if(var==0){
                            nombreTest = descPanel.getNombreTextField();
                            descTest = descPanel.getDescTextArea();
                            scenario.setDescripcion(descTest);
                            scenario.setNombre(nombreTest);
                        }
                        String query = test1.getQuery();
                        String queryExp = test1.getQueryResult();
                        AddComentJDialog comentPane = test1.getComment();
                        String coment = comentPane.getComent();
                        if(!query.equals("") && !queryExp.equals("")){
                            if(var==0){
                                scenario.setTestName("Realización");
                                var=1;
                            }
                            aux=1;
                            QueryOntology testQuery = new QueryOntology(query,queryExp,coment);
                            queryTest3.add(testQuery);
                            scenario.setQueryTest(queryTest3);
                            cont++;
                            aux=1;
                        }else if((!query.equals("") && queryExp.equals("")) || (query.equals("") && !queryExp.equals(""))){
                            JOptionPane.showMessageDialog(frame,"Ambos campos CONSULTA " +
                                "y RESULTADO ESPERADO son obligatorios.",
                                "Warning Message",JOptionPane.WARNING_MESSAGE);
                        }else{
                            i++;
                        }
                    }   
                }
            }
            }
        if(getRealTextName()==true){
        if(AddInstancesJPanel.isStateNuevo()==true){
            int c = realAyudaPanel.getComponentCount();
            for (int i = 1; i < c; i++) {
                realAyudaPanel.remove(realAyudaPanel.getComponent(i));
                realAyudaPanel.add(new TestInstancesQueryJPanel(),i); 
            }
            realAyudaPanel.validate();
        }
        if(aux==1){
            if(AddInstancesJPanel.isStateNuevo()==true){
                descPanel.setDescTextArea("");
                descPanel.setNombreTextField("");
        }
        Instancias instancias = ContentMainJFrame.getInstancias().get(sel);
        scenario.setInstancias(instancias);
        ArrayList<ScenarioTest> st = MainJPanel.getCollectionTest().getScenariotest();
        if(st.size()==0){
            st.add(scenario);
            MainJPanel.getCollectionTest().setScenariotest(st);
        }else{
            MainJPanel.getCollectionTest().getScenariotest().add(scenario);
        }
            ContentMainJFrame.setInstancias(sel,new Instancias());
        }
        }
        }else{
            descPanel = (DescripcionJPanel) panelReal.getComponent(0);
            texto = (TestInstancesTextJPanel) getOpcionTextRealPanel().getComponent(0);
            conjuntoQuerys = texto.getConsultaTextArea();
            conjuntoResult = texto.getResultadoEsperadoTextArea();
            conjuntoComent = texto.getComentTextArea();
            if(descPanel.getNombreTextField().equals("") && !conjuntoQuerys.equals("")){
                        setRealTextName(false);
            }else{
            cQuery = conjuntoQuerys.split("\\\n");
            cResult = conjuntoResult.split("\\\n");
            cComent = conjuntoComent.split("\\\n");
            if((conjuntoQuerys.equals("") && !conjuntoResult.equals("")) || 
                    (!conjuntoQuerys.equals("") && conjuntoResult.equals(""))){
                    JOptionPane.showMessageDialog(frame,"Ambos campos CONSULTA y RESULTADO ESPERADO " +
                            "son obligatorios.","Warning Message",JOptionPane.WARNING_MESSAGE);
            }else if(!conjuntoQuerys.equals("") && !conjuntoResult.equals("")){
                if(var==0){
                    nombreTest = descPanel.getNombreTextField();
                    descTest = descPanel.getDescTextArea();
                    scenario.setDescripcion(descTest);
                    scenario.setNombre(nombreTest);
                    scenario.setTestName("Realización");
                    var=1;
                }
                QueryOntology testQuery = new QueryOntology();
                for(int i=0; i<cQuery.length;i++){
                    if(cComent.length!=0 && i!=cComent.length && i<=cComent.length){
                        testQuery = new QueryOntology(cQuery[i],cResult[i],cComent[i]);
                    }else{
                        testQuery = new QueryOntology(cQuery[i],cResult[i]);
                    }
                    queryTest1.add(testQuery);
                    scenario.setQueryTest(queryTest1);
                    aux=1;
                }
            }
            if(aux==1){
                if(AddInstancesJPanel.isStateNuevo()==true){
                    descPanel.setDescTextArea("");
                    descPanel.setNombreTextField("");
                    texto.setComentTextArea("");
                    texto.setConsultaTextArea("");
                    texto.setResultadoEsperadoTextArea("");
                }
                Instancias instancias = ContentMainJFrame.getInstancias().get(sel);
                scenario.setInstancias(instancias);
                ArrayList<ScenarioTest> st = MainJPanel.getCollectionTest().getScenariotest();
                if(st.size()==0){
                    st.add(scenario);
                    MainJPanel.getCollectionTest().setScenariotest(st);
                }else{
                    MainJPanel.getCollectionTest().getScenariotest().add(scenario);
                }
                ContentMainJFrame.setInstancias(sel,new Instancias());
            }
        }
        }
    }else if(sel==3){
        if(getTabbedPaneSat()==0){
            for(int i=1;i<totalSat;i++){
                if(getSatTextName()==true){
                test = (TestInstancesTFJPanel) panelAyudaSat.getComponent(i);
                descPanel = (DescripcionJPanel) panelSat.getComponent(0);
                if(!test.getQuery().equals("") && !test.isTestFalse().equals(test.isTestTrue())){
                    if(descPanel.getNombreTextField().equals("")){
                        setSatTextName(false);
                    }else{
                        if(var==0){
                            nombreTest = descPanel.getNombreTextField();
                            descTest = descPanel.getDescTextArea();
                            scenario.setDescripcion(descTest);
                            scenario.setNombre(nombreTest);
                        }
                        String query = test.getQuery();
                        String resExpT = test.isTestTrue();
                        String resExpF = test.isTestFalse();
                        AddComentJDialog comentPane = test.getComment();
                        String coment = comentPane.getComent();
                        if(!query.equals("") && !resExpT.equals(resExpF)){
                            if(var==0){
                                scenario.setTestName("Satisfactibilidad");
                                var=1;
                            }
                            aux=1;
                            QueryOntology testQuery = new QueryOntology(query,resExpT,coment);
                            queryTest4.add(testQuery);
                            scenario.setQueryTest(queryTest4);
                            cont++;
                            aux=1;
                        }else if((!query.equals("") && resExpT.equals(resExpF)) || 
                                ((query.equals("") && !resExpT.equals(resExpF)))){
                            JOptionPane.showMessageDialog(frame,"Ambos campos CONSULTA " +
                            "y RESULTADO ESPERADO son obligatorios.","Warning Message",
                            JOptionPane.WARNING_MESSAGE);
                        }else{
                            i++;
                        }
                    }     
                }
            }
            }
        if(getSatTextName()==true){
        if(AddInstancesJPanel.isStateNuevo()==true){
            int c = satAyudaPanel.getComponentCount();
            for (int i = 1; i < c; i++) {
                satAyudaPanel.remove(satAyudaPanel.getComponent(i));
                satAyudaPanel.add(new TestInstancesTFJPanel(),i); 
            }
            satAyudaPanel.validate();
        }
        if(aux==1){
        if(AddInstancesJPanel.isStateNuevo()==true){
            descPanel.setDescTextArea("");
            descPanel.setNombreTextField("");
        }
        Instancias instancias = ContentMainJFrame.getInstancias().get(sel);
        scenario.setInstancias(instancias);
        ArrayList<ScenarioTest> st = MainJPanel.getCollectionTest().getScenariotest();
        if(st.size()==0){
            st.add(scenario);
            MainJPanel.getCollectionTest().setScenariotest(st);
        }else{
            MainJPanel.getCollectionTest().getScenariotest().add(scenario);
        }
            ContentMainJFrame.setInstancias(sel,new Instancias());
        }
        }
        }else{
            descPanel = (DescripcionJPanel) panelSat.getComponent(0);
            texto = (TestInstancesTextJPanel) getOpcionTextSatPanel().getComponent(0);
            conjuntoQuerys = texto.getConsultaTextArea();
            conjuntoResult = texto.getResultadoEsperadoTextArea();
            conjuntoComent = texto.getComentTextArea();
            if(descPanel.getNombreTextField().equals("") && !conjuntoQuerys.equals("")){
                        setSatTextName(false);
            }else{
            cQuery = conjuntoQuerys.split("\\\n");
            cResult = conjuntoResult.split("\\\n");
            cComent = conjuntoComent.split("\\\n");
            if((conjuntoQuerys.equals("") && !conjuntoResult.equals("")) || 
                    (!conjuntoQuerys.equals("") && conjuntoResult.equals(""))){
                    JOptionPane.showMessageDialog(frame,"Ambos campos CONSULTA y RESULTADO ESPERADO " +
                            "son obligatorios.","Warning Message",JOptionPane.WARNING_MESSAGE);
            }else if(!conjuntoQuerys.equals("") && !conjuntoResult.equals("")){
                if(var==0){
                    nombreTest = descPanel.getNombreTextField();
                    descTest = descPanel.getDescTextArea();
                    scenario.setDescripcion(descTest);
                    scenario.setNombre(nombreTest);
                    scenario.setTestName("Satisfactibilidad");
                    var=1;
                }
                QueryOntology testQuery = new QueryOntology();
                for(int i=0; i<cQuery.length;i++){
                    if(cComent.length!=0 && i!=cComent.length && i<=cComent.length){
                        testQuery = new QueryOntology(cQuery[i],cResult[i],cComent[i]);
                    }else{
                        testQuery = new QueryOntology(cQuery[i],cResult[i]);
                    }
                    queryTest1.add(testQuery);
                    scenario.setQueryTest(queryTest1);
                    aux=1;
                }
            }
            if(aux==1){
                if(AddInstancesJPanel.isStateNuevo()==true){
                    descPanel.setDescTextArea("");
                    descPanel.setNombreTextField("");
                    texto.setComentTextArea("");
                    texto.setConsultaTextArea("");
                    texto.setResultadoEsperadoTextArea("");
                }
                Instancias instancias = ContentMainJFrame.getInstancias().get(sel);
                scenario.setInstancias(instancias);
                ArrayList<ScenarioTest> st = MainJPanel.getCollectionTest().getScenariotest();
                if(st.size()==0){
                    st.add(scenario);
                    MainJPanel.getCollectionTest().setScenariotest(st);
                }else{
                    MainJPanel.getCollectionTest().getScenariotest().add(scenario);
                }
                ContentMainJFrame.setInstancias(sel,new Instancias());
            }
        }
        }
    }else if(sel==4){
        if(getTabbedPaneClas()==0){
            for(int i=1;i<totalClas;i++){
                if(getClasTextName()==true){
                test2 = (TestInstancesTextAreaJPanel) panelAyudaClas.getComponent(i);
                descPanel = (DescripcionJPanel) panelClas.getComponent(0);
                if(!test2.getQuery().equals("") || !test2.getQueryResult().equals("")){
                    if(descPanel.getNombreTextField().equals("")){
                        setClasTextName(false);
                    }else{
                        if(var==0){
                            nombreTest = descPanel.getNombreTextField();
                            descTest = descPanel.getDescTextArea();
                            scenario.setDescripcion(descTest);
                            scenario.setNombre(nombreTest);
                        }
                        String query = test2.getQuery();
                        String queryExp = test2.getQueryResult();
                        AddComentJDialog comentPane = test2.getComment();
                        String coment = comentPane.getComent();
                        if(!query.equals("") && !queryExp.equals("")){
                            if(var==0){
                                scenario.setTestName("Clasificación");
                                var=1;
                            }
                            aux=1;
                            QueryOntology testQuery = new QueryOntology(query,queryExp,coment);
                            queryTest5.add(testQuery);
                            scenario.setQueryTest(queryTest5);
                            cont++;
                            aux=1;
                        }else if((!query.equals("") && queryExp.equals("")) || (query.equals("") && !queryExp.equals(""))){
                            JOptionPane.showMessageDialog(frame,"Ambos campos CONSULTA " +
                                "y RESULTADO ESPERADO son obligatorios.",
                                "Warning Message",JOptionPane.WARNING_MESSAGE);
                        }else{
                            i++;
                        }
                    }    
                }
            }
            }
        if(getClasTextName()==true){
        if(AddInstancesJPanel.isStateNuevo()==true){
            int c = clasAyudaPanel.getComponentCount();
            for (int i = 1; i < c; i++) {
                clasAyudaPanel.remove(clasAyudaPanel.getComponent(i));
                clasAyudaPanel.add(new TestInstancesTextAreaJPanel(),i); 
            }
            clasAyudaPanel.validate();
        }
        if(aux==1){
        if(AddInstancesJPanel.isStateNuevo()==true){
            descPanel.setDescTextArea("");
            descPanel.setNombreTextField("");
        }
        Instancias instancias = ContentMainJFrame.getInstancias().get(sel);
        scenario.setInstancias(instancias);
        ArrayList<ScenarioTest> st = MainJPanel.getCollectionTest().getScenariotest();
        if(st.size()==0){
            st.add(scenario);
            MainJPanel.getCollectionTest().setScenariotest(st);
        }else{
            MainJPanel.getCollectionTest().getScenariotest().add(scenario);
        }
        ContentMainJFrame.setInstancias(sel,new Instancias());
        }  
        }
    }else{
            descPanel = (DescripcionJPanel) panelClas.getComponent(0);
            texto = (TestInstancesTextJPanel) getOpcionTextClasPanel().getComponent(0);
            conjuntoQuerys = texto.getConsultaTextArea();
            conjuntoResult = texto.getResultadoEsperadoTextArea();
            conjuntoComent = texto.getComentTextArea();
            if(descPanel.getNombreTextField().equals("") && !conjuntoQuerys.equals("") ){
                        setClasTextName(false);
            }else{ 
            cQuery = conjuntoQuerys.split("\\\n");
            cResult = conjuntoResult.split("\\\n");
            cComent = conjuntoComent.split("\\\n");
            if((conjuntoQuerys.equals("") && !conjuntoResult.equals("")) || 
                    (!conjuntoQuerys.equals("") && conjuntoResult.equals(""))){
                    JOptionPane.showMessageDialog(frame,"Ambos campos CONSULTA y RESULTADO ESPERADO " +
                            "son obligatorios.","Warning Message",JOptionPane.WARNING_MESSAGE);
            }else if(!conjuntoQuerys.equals("") && !conjuntoResult.equals("")){
                if(var==0){
                    nombreTest = descPanel.getNombreTextField();
                    descTest = descPanel.getDescTextArea();
                    scenario.setDescripcion(descTest);
                    scenario.setNombre(nombreTest);
                    scenario.setTestName("Clasificación");
                    var=1;
                }
                QueryOntology testQuery = new QueryOntology();
                for(int i=0; i<cQuery.length;i++){
                    if(cComent.length!=0 && i!=cComent.length && i<=cComent.length){
                        testQuery = new QueryOntology(cQuery[i],cResult[i],cComent[i]);
                    }else{
                        testQuery = new QueryOntology(cQuery[i],cResult[i]);
                    }
                    queryTest1.add(testQuery);
                    scenario.setQueryTest(queryTest1);
                    aux=1;
                }
            }
            if(aux==1){
                if(AddInstancesJPanel.isStateNuevo()==true){
                    descPanel.setDescTextArea("");
                    descPanel.setNombreTextField("");
                    texto.setComentTextArea("");
                    texto.setConsultaTextArea("");
                    texto.setResultadoEsperadoTextArea("");
                }
                Instancias instancias = ContentMainJFrame.getInstancias().get(sel);
                scenario.setInstancias(instancias);
                ArrayList<ScenarioTest> st = MainJPanel.getCollectionTest().getScenariotest();
                if(st.size()==0){
                    st.add(scenario);
                    MainJPanel.getCollectionTest().setScenariotest(st);
                }else{
                    MainJPanel.getCollectionTest().getScenariotest().add(scenario);
                }
                ContentMainJFrame.setInstancias(sel,new Instancias());
            }
        }
    }
  }
}

public static boolean inListSparqlQuerys(SparqlQueryOntology query){
    String queryq = query.getQuerySparql();
    String queryres = query.getResultexpected();
    SparqlQueryOntology sparql = AddSPARQLJPanel.getListSparqlQuerys().get(AddSPARQLJPanel.getPosListQuerysSel());
    String qquery = sparql.getQuerySparql();
    String qresult = sparql.getResultexpected();
    if(!qquery.equals(queryq) || !qresult.equals(queryres)){
        return false;
    }
    return true;
}


public static JPanel getTestClasPanel() {
    return testClasPanel;
}

public static JPanel getTestInstPanel() {
    return testInstPanel;
}

public static JPanel getTestRealPanel() {
    return testRealPanel;
}

public static JPanel getTestRetPanel() {
    return testRetPanel;
}

public static JPanel getTestSatPanel() {
    return testSatPanel;
}
    
public static int getSelectedTabed(){
    if(AddSPARQLJPanel.isSeleccionado()==true){
        return 5;
    }else{
        return testsTabbedPane.getSelectedIndex();
    }
}

public JPanel getContentPanel() {
    return contentPanel;
}

    public static JPanel getClasAyudaPanel() {
        return clasAyudaPanel;
    }

    public static JPanel getInstAyudaPanel() {
        return instAyudaPanel;
    }

    public static JPanel getRealAyudaPanel() {
        return realAyudaPanel;
    }

    public static JPanel getRetAyudaPanel() {
        return retAyudaPanel;
    }

    public static JPanel getSatAyudaPanel() {
        return satAyudaPanel;
    }

    public static int getTabbedPaneClas() {
        return tabbedPaneClas.getSelectedIndex();
    }
    
    public static int getTabbedPaneInst() {
        return tabbedPaneInst.getSelectedIndex();
    }

    public static int getTabbedPaneReal() {
        return tabbedPaneReal.getSelectedIndex();
    }

    public static int getTabbedPaneRet() {
        return tabbedPaneRet.getSelectedIndex();
    }

    public static int getTabbedPaneSat() {
        return tabbedPaneSat.getSelectedIndex();
    }

    public void copiarDeAyudaATexto(int tab){
        
        TestInstancesTextJPanel texto;
        String conjuntoQuerysInst="", conjuntoQuerysRet="", conjuntoQuerysReal="",
                conjuntoQuerysSat="", conjuntoQuerysClas="";
        String conjuntoResExpInst="", conjuntoResExpRet="", conjuntoResExpReal="", 
                conjuntoResExpSat="", conjuntoResExpClas="";
        String conjuntoComentInst="", conjuntoComentRet="", conjuntoComentReal="", 
                conjuntoComentSat="", conjuntoComentClas="";
        
        
        test = null;
        test1 = null; 
        test2 = null;
    
        panelAyudaInst = GroupTestsJPanel.getInstAyudaPanel();
        totalInst = panelAyudaInst.getComponentCount();
        panelAyudaClas = GroupTestsJPanel.getClasAyudaPanel();
        totalClas = panelAyudaClas.getComponentCount();
        panelAyudaReal = GroupTestsJPanel.getRealAyudaPanel();
        totalReal = panelAyudaReal.getComponentCount();
        panelAyudaRet = GroupTestsJPanel.getRetAyudaPanel();
        totalRet = panelAyudaRet.getComponentCount();
        panelAyudaSat = GroupTestsJPanel.getSatAyudaPanel();
        totalSat = panelAyudaSat.getComponentCount();
        
        if(tab==0){
            for(int i=1;i<totalInst;i++){
                test = (TestInstancesTFJPanel) panelAyudaInst.getComponent(i);
                if(!test.getQuery().equals("") || !test.isTestFalse().equals(test.isTestTrue())){
                    String query = test.getQuery();
                    String resExpT = test.isTestTrue();
                    String resExpF = test.isTestFalse();
                    AddComentJDialog comentPane = test.getComment();
                    String coment = comentPane.getComent();
                    if(!query.equals("") && !resExpT.equals(resExpF)){
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
            texto = (TestInstancesTextJPanel) getOpcionTextInstPanel().getComponent(0);
            texto.setConsultaTextArea(conjuntoQuerysInst);
            texto.setResultadoEsperadoTextArea(conjuntoResExpInst);
            texto.setComentTextArea(conjuntoComentInst);
            int c = instAyudaPanel.getComponentCount();
            for(int i=1;i<c;i++){
                instAyudaPanel.remove(instAyudaPanel.getComponent(i));
                instAyudaPanel.add(new TestInstancesTFJPanel(),i); 
            }
            instAyudaPanel.validate();
        }else if(tab==1){
            for(int i=1;i<totalRet;i++){
                test2 = (TestInstancesTextAreaJPanel) panelAyudaRet.getComponent(i);
                if(!test2.getQuery().equals("") || !test2.getQueryResult().equals("")){
                    String query = test2.getQuery();
                    String queryExp = test2.getQueryResult();
                    AddComentJDialog comentPane = test2.getComment();
                    String coment = comentPane.getComent();
                    String[] conjFinal = queryExp.split("\n| |,");
                         
                    if(!query.equals("") && !queryExp.equals("")){
                        if(conjuntoQuerysRet.equals("")){
                            conjuntoQuerysRet = query.concat("\n");
                        }else{
                            conjuntoQuerysRet = conjuntoQuerysRet.concat(query).concat("\n");
                        }  
                        if(conjFinal.length==1){
                            conjuntoResExpRet = conjuntoResExpRet.concat(conjFinal[0]);
                        }else{
                            for(int j=0;j<conjFinal.length;j++){
                                if(conjuntoResExpRet.equals("")){
                                    conjuntoResExpRet = conjFinal[j]+",";
                                }else{
                                    if(j==conjFinal.length-1){
                                        conjuntoResExpRet = conjuntoResExpRet.concat(conjFinal[j]);
                                    }else{
                                        conjuntoResExpRet = conjuntoResExpRet.concat(conjFinal[j])+",";
                                    }
                                }
                            } 
                        }
                        conjuntoResExpRet = conjuntoResExpRet.concat("\n");
                        if(conjuntoComentRet.equals("")){
                            conjuntoComentRet = coment.concat("\n");
                        }else{
                            conjuntoComentRet = conjuntoComentRet.concat(coment).concat("\n");
                        }
                    } 
                }
            }
            texto = (TestInstancesTextJPanel) getOpcionTextRetPanel().getComponent(0);
            texto.setConsultaTextArea(conjuntoQuerysRet);
            texto.setResultadoEsperadoTextArea(conjuntoResExpRet);
            texto.setComentTextArea(conjuntoComentRet);
            int c = retAyudaPanel.getComponentCount();
            for(int i=1;i<c;i++){
                retAyudaPanel.remove(retAyudaPanel.getComponent(i));
                retAyudaPanel.add(new TestInstancesTextAreaJPanel(),i); 
            }
            retAyudaPanel.validate();
        }else if(tab==2){
            for(int i=1;i<totalReal;i++){
                test1 = (TestInstancesQueryJPanel) panelAyudaReal.getComponent(i);
                if(!test1.getQuery().equals("") || !test1.getQueryResult().equals("")){
                    String query = test1.getQuery();
                    String queryExp = test1.getQueryResult();
                    AddComentJDialog comentPane = test1.getComment();
                    String coment = comentPane.getComent();
                    if(!query.equals("") && !queryExp.equals("")){
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
            texto = (TestInstancesTextJPanel) getOpcionTextRealPanel().getComponent(0);
            texto.setConsultaTextArea(conjuntoQuerysReal);
            texto.setResultadoEsperadoTextArea(conjuntoResExpReal);
            texto.setComentTextArea(conjuntoComentReal);
            int c = realAyudaPanel.getComponentCount();
            for(int i=1;i<c;i++){
                realAyudaPanel.remove(realAyudaPanel.getComponent(i));
                realAyudaPanel.add(new TestInstancesQueryJPanel(),i); 
            }
            realAyudaPanel.validate();
        }else if(tab==3){
            for(int i=1;i<totalSat;i++){
                test = (TestInstancesTFJPanel) panelAyudaSat.getComponent(i);
                if(!test.getQuery().equals("") || !test.isTestFalse().equals(test.isTestTrue())){
                    String query = test.getQuery();
                    String resExpT = test.isTestTrue();
                    String resExpF = test.isTestFalse();
                    AddComentJDialog comentPane = test.getComment();
                    String coment = comentPane.getComent();
                    if(!query.equals("") && !resExpT.equals(resExpF)){
                        if(conjuntoQuerysSat.equals("")){
                            conjuntoQuerysSat = query.concat("\n");
                        }else{
                            conjuntoQuerysSat = conjuntoQuerysSat.concat(query).concat("\n");
                        }
                        if(conjuntoResExpSat.equals("")){
                            conjuntoResExpSat = resExpT.concat("\n");
                        }else{
                            conjuntoResExpSat = conjuntoResExpSat.concat(resExpT).concat("\n");
                        }
                        if(conjuntoComentSat.equals("")){
                            conjuntoComentSat = coment.concat("\n");
                        }else{
                            conjuntoComentSat = conjuntoComentSat.concat(coment).concat("\n");
                        }
                    } 
                }
            }
            texto = (TestInstancesTextJPanel) getOpcionTextSatPanel().getComponent(0);
            texto.setConsultaTextArea(conjuntoQuerysSat);
            texto.setResultadoEsperadoTextArea(conjuntoResExpSat);
            texto.setComentTextArea(conjuntoComentSat);
            int c = satAyudaPanel.getComponentCount();
            for(int i=1;i<c;i++){
                satAyudaPanel.remove(satAyudaPanel.getComponent(i));
                satAyudaPanel.add(new TestInstancesTFJPanel(),i); 
            }
            satAyudaPanel.validate();
        }else if(tab==4){
            for(int i=1;i<totalClas;i++){
                test2 = (TestInstancesTextAreaJPanel) panelAyudaClas.getComponent(i);
                if(!test2.getQuery().equals("") || !test2.getQueryResult().equals("")){
                    String query = test2.getQuery();
                    String queryExp = test2.getQueryResult();
                    AddComentJDialog comentPane = test2.getComment();
                    String coment = comentPane.getComent();
                    String[] conjFinal = queryExp.split("\n| |,");
                         
                    if(!query.equals("") && !queryExp.equals("")){
                        if(conjuntoQuerysClas.equals("")){
                            conjuntoQuerysClas = query.concat("\n");
                        }else{
                            conjuntoQuerysClas = conjuntoQuerysClas.concat(query).concat("\n");
                        }  
                        if(conjFinal.length==1){
                            conjuntoResExpClas = conjuntoResExpClas.concat(conjFinal[0]);
                        }else{
                            for(int j=0;j<conjFinal.length;j++){
                                if(conjuntoResExpClas.equals("")){
                                    conjuntoResExpClas = conjFinal[j]+",";
                                }else{
                                    if(j==conjFinal.length-1){
                                        conjuntoResExpClas = conjuntoResExpClas.concat(conjFinal[j]);
                                    }else{
                                        conjuntoResExpClas = conjuntoResExpClas.concat(conjFinal[j])+",";
                                    }
                                }
                            } 
                        }
                        conjuntoResExpClas = conjuntoResExpClas.concat("\n");
                        if(conjuntoComentClas.equals("")){
                            conjuntoComentClas = coment.concat("\n");
                        }else{
                            conjuntoComentClas = conjuntoComentClas.concat(coment).concat("\n");
                        }
                    } 
                }
            }
            texto = (TestInstancesTextJPanel) getOpcionTextClasPanel().getComponent(0);
            texto.setConsultaTextArea(conjuntoQuerysClas);
            texto.setResultadoEsperadoTextArea(conjuntoResExpClas);
            texto.setComentTextArea(conjuntoComentClas);
            int c = clasAyudaPanel.getComponentCount();
            for(int i=1;i<c;i++){
                clasAyudaPanel.remove(clasAyudaPanel.getComponent(i));
                clasAyudaPanel.add(new TestInstancesTextAreaJPanel(),i); 
            }
            clasAyudaPanel.validate();
        }
    }
    
    public void copiarDeTextoAAyuda(int tab){

    test = null;
    test1 = null;
    test2 = null;
    
    panelAyudaInst = GroupTestsJPanel.getInstAyudaPanel();
    totalInst = panelAyudaInst.getComponentCount();
    panelAyudaClas = GroupTestsJPanel.getClasAyudaPanel();
    totalClas = panelAyudaClas.getComponentCount();
    panelAyudaReal = GroupTestsJPanel.getRealAyudaPanel();
    totalReal = panelAyudaReal.getComponentCount();
    panelAyudaRet = GroupTestsJPanel.getRetAyudaPanel();
    totalRet = panelAyudaRet.getComponentCount();
    panelAyudaSat = GroupTestsJPanel.getSatAyudaPanel();
    totalSat = panelAyudaSat.getComponentCount();

    TestInstancesTextJPanel texto;
    
    String conjuntoQuerysInst="", conjuntoQuerysRet="", conjuntoQuerysReal="",
                conjuntoQuerysSat="", conjuntoQuerysClas="";
    String conjuntoResultInst="", conjuntoResultRet="", conjuntoResultReal="", 
                conjuntoResultSat="", conjuntoResultClas="";
    String conjuntoComentInst="", conjuntoComentRet="", conjuntoComentReal="", 
                conjuntoComentSat="", conjuntoComentClas="";
    String cQuery[],cResult[],cComent[];

    if(tab==0){
        texto = (TestInstancesTextJPanel) getOpcionTextInstPanel().getComponent(0);
        conjuntoQuerysInst = texto.getConsultaTextArea().trim();
        conjuntoResultInst = texto.getResultadoEsperadoTextArea().trim();
        conjuntoComentInst = texto.getComentTextArea().trim();
        cQuery = conjuntoQuerysInst.split("\\\n");
        cResult = conjuntoResultInst.split("\\\n");
        cComent = conjuntoComentInst.split("\\\n");
        int j=1;
        for(int i=0; i<cQuery.length; i++){
            if(!cQuery[i].equals("")){
                test = (TestInstancesTFJPanel) panelAyudaInst.getComponent(j);
                test.setQuery(cQuery[i]);
                j++;
            }
        }
        j=1;
        for(int i=0; i<cResult.length; i++){
            String res = cResult[i];
            if(!res.equals("")){
                test = (TestInstancesTFJPanel) panelAyudaInst.getComponent(j);
                if(res.equals("true")){
                    test.setTrueTest(true);
                }else{
                    test.setFalseTest(true);
                }
                j++;
            }
        }
        j=1;
        for(int i=0; i<cComent.length; i++){
            if(!cComent[i].equals("")){
                test = (TestInstancesTFJPanel) panelAyudaInst.getComponent(j);
                AddComentJDialog comentPane = test.getComment();
                comentPane.setComent(cComent[i]);
                test.setComment(comentPane);
                j++;
            }
        }
     }else if(tab==1){
        texto = (TestInstancesTextJPanel) getOpcionTextRetPanel().getComponent(0);
        conjuntoQuerysRet = texto.getConsultaTextArea();
        conjuntoResultRet = texto.getResultadoEsperadoTextArea();
        conjuntoComentRet = texto.getComentTextArea();
        cQuery = conjuntoQuerysRet.split("\\\n");
        cResult = conjuntoResultRet.split("\\\n");
        cComent = conjuntoComentRet.split("\\\n");
        int j=1;
        for(int i=0; i<cQuery.length; i++){
            if(!cQuery[i].equals("")){
                test2 = (TestInstancesTextAreaJPanel) panelAyudaRet.getComponent(j);
                test2.setQuery(cQuery[i]);
                j++;
            }
        }
        j=1;
        for(int i=0; i<cResult.length; i++){
            String res = cResult[i];
            if(!res.equals("")){
                test2 = (TestInstancesTextAreaJPanel) panelAyudaRet.getComponent(j);
                if(res.equals(",") || res.endsWith(" ")){
                    test2.setQueryResult("\n");
                }else{
                    test2.setQueryResult(res);
                }
                j++;
            }
        }
        j=1;
        for(int i=0; i<cComent.length; i++){
            if(!cComent[i].equals("")){
                test2 = (TestInstancesTextAreaJPanel) panelAyudaRet.getComponent(j);
                AddComentJDialog comentPane = test2.getComment();
                comentPane.setComent(cComent[i]);
                test2.setComment(comentPane);
                j++;
            }
        }
      }else if(tab==2){
        texto = (TestInstancesTextJPanel) getOpcionTextRealPanel().getComponent(0);
        conjuntoQuerysReal = texto.getConsultaTextArea().trim();
        conjuntoResultReal = texto.getResultadoEsperadoTextArea().trim();
        conjuntoComentReal = texto.getComentTextArea().trim();
        cQuery = conjuntoQuerysReal.split("\\\n");
        cResult = conjuntoResultReal.split("\\\n");
        cComent = conjuntoComentReal.split("\\\n");
        int j=1;
        for(int i=0; i<cQuery.length; i++){
            if(!cQuery[i].equals("")){
                test1 = (TestInstancesQueryJPanel) panelAyudaReal.getComponent(j);
                test1.setQuery(cQuery[i]);
                j++;
            }
        }
        j=1;
        for(int i=0; i<cResult.length; i++){
            String res = cResult[i];
            if(!res.equals("")){
                test1 = (TestInstancesQueryJPanel) panelAyudaReal.getComponent(j);
                test1.setQueryResult(res);
                j++;
            }
        }
        j=1;
        for(int i=0; i<cComent.length; i++){
            if(!cComent[i].equals("")){
                test1 = (TestInstancesQueryJPanel) panelAyudaReal.getComponent(j);
                AddComentJDialog comentPane = test1.getComment();
                comentPane.setComent(cComent[i]);
                test1.setComment(comentPane);
                j++;
            }
        }  
      }else if(tab==3){
        texto = (TestInstancesTextJPanel) getOpcionTextSatPanel().getComponent(0);
        conjuntoQuerysSat = texto.getConsultaTextArea().trim();
        conjuntoResultSat = texto.getResultadoEsperadoTextArea().trim();
        conjuntoComentSat = texto.getComentTextArea().trim();
        cQuery = conjuntoQuerysSat.split("\\\n");
        cResult = conjuntoResultSat.split("\\\n");
        cComent = conjuntoComentSat.split("\\\n");
        int j=1;
        for(int i=0; i<cQuery.length; i++){
            if(!cQuery[i].equals("")){
                test = (TestInstancesTFJPanel) panelAyudaSat.getComponent(j);
                test.setQuery(cQuery[i]);
                j++;
            }
        }
        j=1;
        for(int i=0; i<cResult.length; i++){
            String res = cResult[i];
            if(!res.equals("")){
                test = (TestInstancesTFJPanel) panelAyudaSat.getComponent(j);
                if(res.equals("true")){
                    test.setTrueTest(true);
                }else{
                    test.setFalseTest(true);
                }
                j++;
            }
        }
        j=1;
        for(int i=0; i<cComent.length; i++){
            if(!cComent[i].equals("")){
                test = (TestInstancesTFJPanel) panelAyudaSat.getComponent(j);
                AddComentJDialog comentPane = test.getComment();
                comentPane.setComent(cComent[i]);
                test.setComment(comentPane);
                j++;
            }
        }
      }else if(tab==4){
        texto = (TestInstancesTextJPanel) getOpcionTextClasPanel().getComponent(0);
        conjuntoQuerysClas = texto.getConsultaTextArea().trim();
        conjuntoResultClas = texto.getResultadoEsperadoTextArea().trim();
        conjuntoComentClas = texto.getComentTextArea().trim();
        cQuery = conjuntoQuerysClas.split("\\\n");
        cResult = conjuntoResultClas.split("\\\n");
        cComent = conjuntoComentClas.split("\\\n");
        int j=1;
        for(int i=0; i<cQuery.length; i++){
            if(!cQuery[i].equals("")){
                test2 = (TestInstancesTextAreaJPanel) panelAyudaClas.getComponent(j);
                test2.setQuery(cQuery[i]);
                j++;
            }
        }
        j=1;
        for(int i=0; i<cResult.length; i++){
            String res = cResult[i];
            if(!res.equals("")){
                test2 = (TestInstancesTextAreaJPanel) panelAyudaClas.getComponent(j);
                test2.setQueryResult(res);
                j++;
            }
        }
        j=1;
        for(int i=0; i<cComent.length; i++){
            if(!cComent[i].equals("")){
                test2 = (TestInstancesTextAreaJPanel) panelAyudaClas.getComponent(j);
                AddComentJDialog comentPane = test2.getComment();
                comentPane.setComent(cComent[i]);
                test2.setComment(comentPane);
                j++;
            }
        }     
      }
    }

    public static JPanel getOpcionTextClasPanel() {
        return opcionTextClasPanel;
    }

    public static JPanel getOpcionTextInstPanel() {
        return opcionTextInstPanel;
    }

    public static JPanel getOpcionTextRealPanel() {
        return opcionTextRealPanel;
    }

    public static JPanel getOpcionTextRetPanel() {
        return opcionTextRetPanel;
    }

    public static JPanel getOpcionTextSatPanel() {
        return opcionTextSatPanel;
    }

    public boolean isOntologiaValida() {
        return ontologiaValida;
    }

    public void setOntologiaValida(boolean ontologiaValida) {
        this.ontologiaValida = ontologiaValida;
    }

    public static boolean getInstTextName() {
        return instTextName;
    }

    public static void setInstTextName(boolean ainstTextName) {
        instTextName = ainstTextName;
    }

    public static boolean getRetTextName() {
        return retTextName;
    }

    public static void setRetTextName(boolean aretTextName) {
        retTextName = aretTextName;
    }

    public static boolean getClasTextName() {
        return clasTextName;
    }

    public static void setClasTextName(boolean aclasTextName) {
        clasTextName = aclasTextName;
    }

    public static boolean getRealTextName() {
        return realTextName;
    }

    public static void setRealTextName(boolean arealTextName) {
        realTextName = arealTextName;
    }

    public static boolean getSatTextName() {
        return satTextName;
    }

    public static void setSatTextName(boolean asatTextName) {
        satTextName = asatTextName;
    }

    public static int getNameTest() {
        return nameTest;
    }

    public static void setNameTest(int anameTest) {
        nameTest = anameTest;
    }

    private boolean todosTienenNombre() {
        if(getInstTextName()==true && getRetTextName()==true && getRealTextName()==true
                && getSatTextName()==true && getClasTextName()==true){
                return true;
        }else{
            return false;
        }
    }

    public boolean getNombreTestsValidos() {
        return nombreTestsValidos;
    }

    public void setNombreTestsValidos(boolean nombreTestsValidos) {
        this.nombreTestsValidos = nombreTestsValidos;
    }
    
}
