/*
 * AddInstancesJPanel.java
 *
 * Created on 19 de mayo de 2008, 19:09
 */

package code.google.com.p.ontologytesting.gui;

import java.awt.Component;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import code.google.com.p.ontologytesting.model.ClassInstances;
import code.google.com.p.ontologytesting.model.PropertyInstances;
import code.google.com.p.ontologytesting.model.QueryOntology;
import code.google.com.p.ontologytesting.model.ScenarioTest;
import code.google.com.p.ontologytesting.model.SparqlQueryOntology;

/**
 *
 * @author  Saruskas
 */
public class AddInstancesJPanel extends javax.swing.JPanel {

    public static boolean isStateAsociar() {
        return stateAsociar;
    }
    public static void setStateAsociar(boolean aStateAsociar) {
        stateAsociar = aStateAsociar;
    }
    public static boolean isStateExaminar() {
        return stateExaminar;
    }
    public static void setStateExaminar(boolean aStateExaminar) {
        stateExaminar = aStateExaminar;
    }
    public static boolean isStateSeeInst() {
        return stateSeeInst;
    }
    public static void setStateSeeInst(boolean aStateSeeInst) {
        stateSeeInst = aStateSeeInst;
    }
    private static boolean stateNuevo;

    public static boolean isStateNuevo() {
        return stateNuevo;
    }
    public static void setStateNuevo(boolean aStateNuevo) {
        stateNuevo = aStateNuevo;
    }
    private static String[] ficheros;
    private static String pathFichero;
    public static String[] getFicheros() {
        return ficheros;
    }
    public static void setFicheros(String[] aFicheros) {
        ficheros = aFicheros;
    }
    public static String getPathFichero() {
        return pathFichero;
    }
    public static void setPathFichero(String aPathFichero) {
        pathFichero = aPathFichero;
    }
    private static boolean stateAbrirTest;
    public AddInstancesClasPropJDialog addInst;  
    private JFileChooser filechooser;
    private Component frame;
    private JFrame parent;
    private int index;
    private GroupTestsJPanel jpanel;
    static ArrayList<ScenarioTest> scte;
    private static boolean stateAsociar;
    private static boolean stateExaminar;
    private static boolean stateSeeInst;
    private XMLDecoder decoder;
    private String nameFile="";
    
    /** Creates new form AddInstancesJPanel */
    public AddInstancesJPanel(GroupTestsJPanel panel) {
        this.setGroupPanel(panel);
        initComponents();
    }
    
    public AddInstancesJPanel() {
        initComponents();
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
        examinarButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        asociarButton = new javax.swing.JButton();
        seeAsociadasButton = new javax.swing.JButton();
        SaveAndNewButton = new javax.swing.JButton();
        addTestExistButton = new javax.swing.JButton();
        seeTestButton = new javax.swing.JButton();

        jLabel1.setText("Seleccione las instancias que desea asociar:");

        examinarButton.setText("Examinar");
        examinarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                examinarButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Crear y asociar nuevas instancias:");

        asociarButton.setText("Asociar");
        asociarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asociarButtonActionPerformed(evt);
            }
        });

        seeAsociadasButton.setText("Ver instancias asociadas");
        seeAsociadasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seeAsociadasButtonActionPerformed(evt);
            }
        });

        SaveAndNewButton.setText("Añadir y Crear Nuevo");
        SaveAndNewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveAndNewButtonActionPerformed(evt);
            }
        });

        addTestExistButton.setText("Añadir Test Existente");
        addTestExistButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTestExistButtonActionPerformed(evt);
            }
        });

        seeTestButton.setText("Ver Tests Guardados");
        seeTestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seeTestButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 229, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(575, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                        .add(339, 339, 339))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(seeAsociadasButton)
                                .add(367, 367, 367)
                                .add(addTestExistButton)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(seeTestButton))
                            .add(examinarButton))
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(asociarButton)
                        .add(590, 590, 590)
                        .add(SaveAndNewButton)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(asociarButton)
                    .add(SaveAndNewButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(examinarButton)
                .add(5, 5, 5)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(seeAsociadasButton)
                    .add(seeTestButton)
                    .add(addTestExistButton))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleParent(this);
    }// </editor-fold>//GEN-END:initComponents

private void asociarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asociarButtonActionPerformed
// TODO add your handling code here:
    
   AddInstancesJPanel.setStateAsociar(true);
   AddInstancesJPanel.setStateExaminar(false);
   AddInstancesJPanel.setStateSeeInst(false);
   int var=0;
   if(AddSPARQLJPanel.isSeleccionado()==false) {
        ArrayList<ClassInstances> clasInst = new ArrayList<ClassInstances>();
        ArrayList<PropertyInstances> propInst = new ArrayList<PropertyInstances>();
        clasInst = ContentMainJFrame.getConjuntoClassInstances().get(GroupTestsJPanel.getSelectedTabed());
        propInst = ContentMainJFrame.getConjuntoPropInstances().get(GroupTestsJPanel.getSelectedTabed());
        if(!clasInst.isEmpty() || !propInst.isEmpty()){
            var=1;
        } 
        if(var==0){
            addInst = new AddInstancesClasPropJDialog(parent,true,8,0);
            addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            addInst.setVisible(true);
        }else{
            addInst = new AddInstancesClasPropJDialog(parent,true,8,1);
            addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            addInst.setVisible(true);
        }
   }else{
        //ScenarioTest sT = AddSPARQLJPanel.getScenarioTestQuery();
        ArrayList<ClassInstances> clasInst = new ArrayList<ClassInstances>();
        ArrayList<PropertyInstances> propInst = new ArrayList<PropertyInstances>();
        //clasInst = sT.getClassInstances();
        //propInst = sT.getPropertyInstances();
        clasInst = ContentMainJFrame.getConjuntoClassInstances().get(GroupTestsJPanel.getSelectedTabed());
        propInst = ContentMainJFrame.getConjuntoPropInstances().get(GroupTestsJPanel.getSelectedTabed());
        if(!clasInst.isEmpty() || !propInst.isEmpty()){
            var=1;
        }
        if(var==0){
            addInst = new AddInstancesClasPropJDialog(parent,true,8,0);
            addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            addInst.setVisible(true);
        }else{
            addInst = new AddInstancesClasPropJDialog(parent,true,8,1);
            addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            addInst.setVisible(true);
        }
   }
        
}//GEN-LAST:event_asociarButtonActionPerformed

private void examinarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_examinarButtonActionPerformed
// TODO add your handling code here:
    
    AddInstancesJPanel.setStateExaminar(true);
    AddInstancesJPanel.setStateAsociar(false);
        filechooser = new JFileChooser(Configuration.getPathInstancias());
        int option = filechooser.showOpenDialog(frame);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = filechooser.getSelectedFile();
            nameFile = selectedFile.getPath();
            addInst = new AddInstancesClasPropJDialog(parent,true,nameFile);
            addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            addInst.setVisible(true);
        }
}//GEN-LAST:event_examinarButtonActionPerformed

private void seeAsociadasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seeAsociadasButtonActionPerformed
// TODO add your handling code here:
    
    int var=0;
    AddInstancesJPanel.setStateExaminar(false);
    AddInstancesJPanel.setStateAsociar(false);
    AddInstancesJPanel.setStateSeeInst(true);
    //ScenarioTest scenarioTest = new ScenarioTest();
    ArrayList<ClassInstances> clasInst = new ArrayList<ClassInstances>();
    ArrayList<PropertyInstances> propInst = new ArrayList<PropertyInstances>();
    
    /*if(AddSPARQLJPanel.isSeleccionado()==true){
        scenarioTest = AddSPARQLJPanel.getScenarioTestQuery();
        clasInst = scenarioTest.getClassInstances();
        propInst = scenarioTest.getPropertyInstances();
    }else{
        clasInst = ContentMainJFrame.getConjuntoClassInstances().get(GroupTestsJPanel.getSelectedTabed());
        propInst = ContentMainJFrame.getConjuntoPropInstances().get(GroupTestsJPanel.getSelectedTabed());    
    }*/
    
    clasInst = ContentMainJFrame.getConjuntoClassInstances().get(GroupTestsJPanel.getSelectedTabed());
    propInst = ContentMainJFrame.getConjuntoPropInstances().get(GroupTestsJPanel.getSelectedTabed());
      
    if(!clasInst.isEmpty() || !propInst.isEmpty()){
        var=1;
    }
    
    if(var==0){
        addInst = new AddInstancesClasPropJDialog(parent,true,8,0);
        addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        addInst.setVisible(true);
    }else{
        AddInstancesJPanel.setStateAsociar(false);
        AddInstancesJPanel.setStateSeeInst(true);
        AddInstancesJPanel.setStateExaminar(false);
        addInst = new AddInstancesClasPropJDialog(parent,true,8,1);
        addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        addInst.setVisible(true);
    }
}//GEN-LAST:event_seeAsociadasButtonActionPerformed

private void SaveAndNewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveAndNewButtonActionPerformed
// TODO add your handling code here:
    AddInstancesJPanel.setStateNuevo(true);
    ScenarioTest scenarioSparql = new ScenarioTest();
    if(AddSPARQLJPanel.isSeleccionado()==true){
        ArrayList<SparqlQueryOntology> listSparqlQuerys = AddSPARQLJPanel.getListSparqlQuerys();
        SparqlQueryOntology query = new SparqlQueryOntology();
        ArrayList<ClassInstances> clasFinal = new ArrayList<ClassInstances>();
        ArrayList<PropertyInstances> propFinal = new ArrayList<PropertyInstances>();
        ArrayList<ClassInstances> vaciaClase = new ArrayList<ClassInstances>();
        ArrayList<PropertyInstances> vaciaPropiedad = new ArrayList<PropertyInstances>();
        if(!AddSPARQLJPanel.getSPARQLQuery().equals("") && !AddSPARQLJPanel.getResultTextArea().equals("")){
               
            query.setQuerySparql(AddSPARQLJPanel.getSPARQLQuery());
            query.setResultexpected(AddSPARQLJPanel.getResultTextArea());
            listSparqlQuerys.add(query);
            scenarioSparql.setNombre(AddSPARQLJPanel.getTestNameTextField());
            scenarioSparql.setTestName("sparql");
            scenarioSparql.setDescripcion(AddSPARQLJPanel.getTestDescTextArea());
            scenarioSparql.setSparqlQuerys(listSparqlQuerys);
            /*
            AddSPARQLJPanel.getScenarioTestQuery().setNombre(AddSPARQLJPanel.getTestNameTextField());
            AddSPARQLJPanel.getScenarioTestQuery().setTestName("sparql");
            AddSPARQLJPanel.getScenarioTestQuery().setDescripcion(AddSPARQLJPanel.getTestDescTextArea());
            AddSPARQLJPanel.getScenarioTestQuery().setSparqlQuerys(listSparqlQuerys);*/
    
            clasFinal = ContentMainJFrame.getConjuntoClassInstances().get(GroupTestsJPanel.getSelectedTabed());
            propFinal = ContentMainJFrame.getConjuntoPropInstances().get(GroupTestsJPanel.getSelectedTabed());
            scenarioSparql.setClassInstances(clasFinal);
            scenarioSparql.setPropertyInstances(propFinal);
            ContentMainJFrame.getConjuntoClassInstances().set(GroupTestsJPanel.getSelectedTabed(), vaciaClase);
            ContentMainJFrame.getConjuntoPropInstances().set(GroupTestsJPanel.getSelectedTabed(), vaciaPropiedad);
            
            AddSPARQLJPanel.setTestDescTextArea("");
            AddSPARQLJPanel.setTestNameTextField("");
            AddSPARQLJPanel.setSPARQLQuery("");
            AddSPARQLJPanel.setResultTextArea("");
        
            //ScenarioTest scenario = new ScenarioTest();
            //AddSPARQLJPanel.setScenarioTestQuery(scenario);
            ArrayList<ScenarioTest> scenarioT = MainJPanel.getCollectionTest().getScenariotest();
            if(scenarioT.size()==0){
                scenarioT.add(scenarioSparql);
                MainJPanel.getCollectionTest().setScenariotest(scenarioT);
            }else{
                MainJPanel.getCollectionTest().getScenariotest().add(scenarioSparql);
            }
            //scenarioT.add(AddSPARQLJPanel.getScenarioTestQuery());
            //MainJPanel.getCollectionTest().setScenariotest(scenarioT);
            listSparqlQuerys = new ArrayList<SparqlQueryOntology>(); 
            AddSPARQLJPanel.setListSparqlQuerys(listSparqlQuerys);
        }else if(AddSPARQLJPanel.getSPARQLQuery().equals("") || AddSPARQLJPanel.getResultTextArea().equals("")){
            JOptionPane.showMessageDialog(frame,"Ambos campos CONSULTA y RESULTADO ESPERADO " +
                "son obligatorios.", "Warning Message",JOptionPane.WARNING_MESSAGE);
        }
        AddSPARQLJPanel.setContadorAnt(-1);
        AddSPARQLJPanel.setContadorSig(1);
        AddSPARQLJPanel.setAntQueryButton(false);
    }else{
        GroupTestsJPanel.asociarInstancias(GroupTestsJPanel.getSelectedTabed());   
    }
}//GEN-LAST:event_SaveAndNewButtonActionPerformed

private void addTestExistButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTestExistButtonActionPerformed
// TODO add your handling code here:
    if(AddSPARQLJPanel.isSeleccionado()==false){
    filechooser = new JFileChooser(Configuration.getPathTestSimples());
    setStateAbrirTest(true);
    
      int option = filechooser.showOpenDialog(frame);
      if (option == JFileChooser.APPROVE_OPTION) {
          File selectedFile = filechooser.getSelectedFile();
          nameFile = selectedFile.getPath();
      }   

    DescripcionJPanel descPanel = null;
    TestInstancesTFJPanel test = null;
    TestInstancesQueryJPanel test1 = null;
    
    JPanel panelInst = GroupTestsJPanel.getTestInstPanel();
    JPanel panelClas = GroupTestsJPanel.getTestClasPanel();
    JPanel panelReal = GroupTestsJPanel.getTestRealPanel();
    JPanel panelRet = GroupTestsJPanel.getTestRetPanel();
    JPanel panelSat = GroupTestsJPanel.getTestSatPanel();
    
    JPanel panelAyudaInst = GroupTestsJPanel.getInstAyudaPanel();
    JPanel panelAyudaClas = GroupTestsJPanel.getClasAyudaPanel();
    JPanel panelAyudaReal = GroupTestsJPanel.getRealAyudaPanel();
    JPanel panelAyudaRet = GroupTestsJPanel.getRetAyudaPanel();
    JPanel panelAyudaSat = GroupTestsJPanel.getSatAyudaPanel();

    int cont=1;
    try{
        decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(nameFile)));
        ScenarioTest s = (ScenarioTest) decoder.readObject();
        String nombre = s.getNombre();
        String descrip = s.getDescripcion();  
        String tab = s.getTestName();
        ArrayList<QueryOntology> qO = s.getQueryTest(); 
        ArrayList<ClassInstances> clasI = s.getClassInstances();
        ArrayList<PropertyInstances> propI = s.getPropertyInstances();
        ListIterator qi,ci,pi;
        qi = qO.listIterator();
        ci =  clasI.listIterator();
        pi = propI.listIterator();
        int ind = GroupTestsJPanel.getSelectedTabed();
        ContentMainJFrame.getConjuntoClassInstances().set(ind, clasI);
        ContentMainJFrame.getConjuntoPropInstances().set(ind, propI);
        while(qi.hasNext()){   
            QueryOntology cI = (QueryOntology) qi.next();
            if(tab.equals("Instanciación")){
                descPanel = (DescripcionJPanel) panelInst.getComponent(0);
                descPanel.setNombreTextField(nombre);
                descPanel.setDescTextArea(descrip);
                test = (TestInstancesTFJPanel) panelAyudaInst.getComponent(cont);
                AddComentJDialog comentPane = test.getComment();
                comentPane.setComent(cI.getComment());
                test.setComment(comentPane);
                test.setQuery(cI.getQuery());
                String res = cI.getResultexpected();
                if(res.equals("true")){
                    test.setTrueTest(true);
                }else{
                    test.setFalseTest(true);
                }
                cont++;
            }else if(tab.equals("Retrieval")){
                descPanel = (DescripcionJPanel) panelRet.getComponent(0);
                descPanel.setNombreTextField(nombre);
                descPanel.setDescTextArea(descrip);
                test1 = (TestInstancesQueryJPanel) panelAyudaRet.getComponent(cont);
                AddComentJDialog comentPane = test1.getComment();
                comentPane.setComent(cI.getComment());
                test1.setComment(comentPane);
                test1.setQuery(cI.getQuery());
                test1.setQueryResult(cI.getResultexpected());
                cont++;
            }else if(tab.equals("Realización")){
                descPanel = (DescripcionJPanel) panelReal.getComponent(0);
                descPanel.setNombreTextField(nombre);
                descPanel.setDescTextArea(descrip);
                test1 = (TestInstancesQueryJPanel) panelAyudaReal.getComponent(cont);
                AddComentJDialog comentPane = test1.getComment();
                comentPane.setComent(cI.getComment());
                test1.setComment(comentPane);
                test1.setQuery(cI.getQuery());
                test1.setQueryResult(cI.getResultexpected());
                cont++;
            }else if(tab.equals("Satisfactibilidad")){
                descPanel = (DescripcionJPanel) panelSat.getComponent(0);
                descPanel.setNombreTextField(nombre);
                descPanel.setDescTextArea(descrip);
                test = (TestInstancesTFJPanel) panelAyudaSat.getComponent(cont);
                AddComentJDialog comentPane = test.getComment();
                comentPane.setComent(cI.getComment());
                test.setComment(comentPane);
                test.setQuery(cI.getQuery());
                String res = cI.getResultexpected();
                if(res.equals("true")){
                    test.setTrueTest(true);
                }else{
                    test.setFalseTest(true);
                }
                cont++;
            }else if(tab.equals("Clasificación")){
                descPanel = (DescripcionJPanel) panelClas.getComponent(0);
                descPanel.setNombreTextField(nombre);
                descPanel.setDescTextArea(descrip);
                test1 = (TestInstancesQueryJPanel) panelAyudaClas.getComponent(cont);
                AddComentJDialog comentPane = test1.getComment();
                comentPane.setComent(cI.getComment());
                test1.setComment(comentPane);
                test1.setQuery(cI.getQuery());
                test1.setQueryResult(cI.getResultexpected());
                cont++;
            }
        }  
    decoder.close();    
    }catch(FileNotFoundException e){
    }
    
    }else{
        
        filechooser = new JFileChooser(Configuration.getPathTestSparql());
        setStateAbrirTest(true);
    
        int option = filechooser.showOpenDialog(frame);
        if (option == JFileChooser.APPROVE_OPTION) {
          File selectedFile = filechooser.getSelectedFile();
          nameFile = selectedFile.getPath();
        }   

        try{
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(nameFile)));
            ScenarioTest s = (ScenarioTest) decoder.readObject();
            String nombre = s.getNombre();
            String descrip = s.getDescripcion();  
            ArrayList<SparqlQueryOntology> qO = s.getSparqlQuerys();
            ArrayList<ClassInstances> clasI = s.getClassInstances();
            ArrayList<PropertyInstances> propI = s.getPropertyInstances();
            int ind = GroupTestsJPanel.getSelectedTabed();
            ContentMainJFrame.getConjuntoClassInstances().set(ind, clasI);
            ContentMainJFrame.getConjuntoPropInstances().set(ind, propI);
            
            AddSPARQLJPanel.setTestNameTextField(nombre);
            AddSPARQLJPanel.setTestDescTextArea(descrip);
            AddSPARQLJPanel.setListSparqlQuerys(qO);
            AddSPARQLJPanel.setPosListQuerysSel(0);
            AddSPARQLJPanel.setSPARQLQuery(AddSPARQLJPanel.getListSparqlQuerys().get(0).getQuerySparql());
            AddSPARQLJPanel.setResultTextArea(AddSPARQLJPanel.getListSparqlQuerys().get(0).getResultexpected());
            
            int tam = qO.size();
            if(tam >=2){
                AddSPARQLJPanel.setContadorAnt(-1);
                AddSPARQLJPanel.setContadorSig(1);
                AddSPARQLJPanel.setSigQueryButton(true);
            }
            
        decoder.close();    
        }catch(FileNotFoundException e){
        }
    }
}//GEN-LAST:event_addTestExistButtonActionPerformed

private void seeTestButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seeTestButtonActionPerformed
// TODO add your handling code here:
    AbrirTestsJDialog testDialog = new AbrirTestsJDialog(parent,true);
    testDialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    testDialog.setVisible(true);

}//GEN-LAST:event_seeTestButtonActionPerformed

public void setSelectedTabbed(int index){
    this.index = index;
}

public int getSelectedTabbed(){
    return this.index;
}

public void setGroupPanel(GroupTestsJPanel jpanel){
    this.jpanel=jpanel;
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SaveAndNewButton;
    private javax.swing.JButton addTestExistButton;
    private javax.swing.JButton asociarButton;
    private javax.swing.JButton examinarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton seeAsociadasButton;
    private javax.swing.JButton seeTestButton;
    // End of variables declaration//GEN-END:variables

    public static boolean isStateAbrirTest() {
        return stateAbrirTest;
    }

    public static void setStateAbrirTest(boolean astateAbrirTest) {
        stateAbrirTest = astateAbrirTest;
    }

}
