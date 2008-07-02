/*
 * AddInstancesJPanel.java
 *
 * Created on 19 de mayo de 2008, 19:09
 */

package ontologytestgui;

import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import model.ClassInstances;
import model.PropertyInstances;
import model.ScenarioTest;

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

        SaveAndNewButton.setText("Guardar y crear nuevo");
        SaveAndNewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveAndNewButtonActionPerformed(evt);
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
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 229, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(asociarButton))
                        .addContainerGap(320, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(339, 339, 339))
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(seeAsociadasButton)
                                .add(159, 159, 159)
                                .add(SaveAndNewButton))
                            .add(examinarButton))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(asociarButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(examinarButton)
                .add(5, 5, 5)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(seeAsociadasButton)
                    .add(SaveAndNewButton))
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
   int i=0;
   
   if(AddSPARQLJPanel.isSeleccionado()==false) {     
       ArrayList<ScenarioTest> scenarioTest = MainJPanel.getCollectionTest().getScenariotest();
        i = jpanel.getSelectedTabed();
        ScenarioTest sT = scenarioTest.get(i);
    
        ArrayList<ClassInstances> clasInst = new ArrayList<ClassInstances>();
        ArrayList<PropertyInstances> propInst = new ArrayList<PropertyInstances>();
        clasInst = sT.getClassInstances();
        propInst = sT.getPropertyInstances();
        if(!clasInst.isEmpty() || !propInst.isEmpty()){
            var=1;
        }
        if(var==0){
            addInst = new AddInstancesClasPropJDialog(parent,true,7);
            addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            addInst.setVisible(true);
        }else{
            addInst = new AddInstancesClasPropJDialog(parent,true,7,i,scenarioTest);
            addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            addInst.setVisible(true);
        }
   }else{
        ScenarioTest sT = AddSPARQLJPanel.getScenarioTestQuery();
        ArrayList<ClassInstances> clasInst = new ArrayList<ClassInstances>();
        ArrayList<PropertyInstances> propInst = new ArrayList<PropertyInstances>();
        clasInst = sT.getClassInstances();
        propInst = sT.getPropertyInstances();
        if(!clasInst.isEmpty() || !propInst.isEmpty()){
            var=1;
        }
        if(var==0){
            addInst = new AddInstancesClasPropJDialog(parent,true,7);
            addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            addInst.setVisible(true);
        }else{
            addInst = new AddInstancesClasPropJDialog(parent,true,7,sT);
            addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            addInst.setVisible(true);
        }
   }
        
}//GEN-LAST:event_asociarButtonActionPerformed

private void examinarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_examinarButtonActionPerformed
// TODO add your handling code here:
    
    AddInstancesJPanel.setStateExaminar(true);
    AddInstancesJPanel.setStateAsociar(false);
    AddInstancesJPanel.setStateSeeInst(false);
    ArrayList<ScenarioTest> scenarioTest = MainJPanel.getCollectionTest().getScenariotest();
    int i=0;
    
    if(AddSPARQLJPanel.isSeleccionado()==false){
        i = jpanel.getSelectedTabed();
    }else{
        i = scenarioTest.size()-1;
    }
        filechooser = new JFileChooser("./");
        int option = filechooser.showOpenDialog(frame);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = filechooser.getSelectedFile();
            String nameFile = selectedFile.getName();
            addInst = new AddInstancesClasPropJDialog(parent,true,nameFile,i);
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
    ArrayList<ScenarioTest> scenarioTest = MainJPanel.getCollectionTest().getScenariotest();
    int i=0;
    
    if(AddSPARQLJPanel.isSeleccionado()==false){
        i = jpanel.getSelectedTabed();
    }else{
        i = scenarioTest.size()-1;
    }
    
    ScenarioTest sT = scenarioTest.get(i);
    
    ArrayList<ClassInstances> clasInst = new ArrayList<ClassInstances>();
    ArrayList<PropertyInstances> propInst = new ArrayList<PropertyInstances>();
    clasInst = sT.getClassInstances();
    propInst = sT.getPropertyInstances();
    if(!clasInst.isEmpty() || !propInst.isEmpty()){
        var=1;
    }
    
    if(var==0){
        addInst = new AddInstancesClasPropJDialog(parent,true,7);
        addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        addInst.setVisible(true);
    }else{
        AddInstancesJPanel.setStateAsociar(false);
        AddInstancesJPanel.setStateSeeInst(true);
        AddInstancesJPanel.setStateExaminar(false);
        addInst = new AddInstancesClasPropJDialog(parent,true,7,i,scenarioTest);
        addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        addInst.setVisible(true);
    }
}//GEN-LAST:event_seeAsociadasButtonActionPerformed

private void SaveAndNewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveAndNewButtonActionPerformed
// TODO add your handling code here:
    /*if(AddSPARQLJPanel.isSeleccionado()==true){
        SparqlQueryOntology query = new SparqlQueryOntology();
        if(!this.getSPARQLQuery().equals("") && !this.getResultTextArea().equals("")){
            query.setQuerySparql(this.getSPARQLQuery());
            query.setResultexpected(this.getResultTextArea());
            listSparqlQuerys.add(query);
            getScenarioTestQuery().setNombre(this.getTestNameTextField());
            getScenarioTestQuery().setTestName("sparql");
            getScenarioTestQuery().setDescripcion(this.getTestDescTextArea());
            getScenarioTestQuery().setSparqlQuerys(listSparqlQuerys);
    
            this.setTestDescTextArea("");
            this.setTestNameTextField("");
            this.setSPARQLQuery("");
            this.setResultTextArea("");
        
            scenarioTestQuery = new ScenarioTest();
            ArrayList<ScenarioTest> scenarioT = MainJPanel.getCollectionTest().getScenariotest();
            scenarioT.add(scenarioTestQuery);
            MainJPanel.getCollectionTest().setScenariotest(scenarioT);
            listSparqlQuerys = new ArrayList<SparqlQueryOntology>(); 
        }else if(this.getSPARQLQuery().equals("") || this.getResultTextArea().equals("")){
            JOptionPane.showMessageDialog(frame,"Ambos campos CONSULTA y RESULTADO ESPERADO " +
                "son obligatorios.", "Warning Message",JOptionPane.WARNING_MESSAGE);
        }
    }*/
}//GEN-LAST:event_SaveAndNewButtonActionPerformed

private void openFile(JTextField textfield){
    
    ArrayList<ScenarioTest> scenarioTest = MainJPanel.getCollectionTest().getScenariotest();
    int i=0;
      if(AddSPARQLJPanel.isSeleccionado()==false) {     
            i = jpanel.getSelectedTabed();
      }else{
            i = scenarioTest.size()-1;
      }
      filechooser = new JFileChooser("./");
      int option = filechooser.showOpenDialog(frame);
      if (option == JFileChooser.APPROVE_OPTION) {
          File selectedFile = filechooser.getSelectedFile();
          textfield.setText(selectedFile.getPath());
          String nameFile = selectedFile.getName();
          addInst = new AddInstancesClasPropJDialog(parent,true,nameFile,i);
          addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
          addInst.setVisible(true);
      }
}

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
    private javax.swing.JButton asociarButton;
    private javax.swing.JButton examinarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton seeAsociadasButton;
    // End of variables declaration//GEN-END:variables

}
