/*
 * AddSPARQLJPanel.java
 *
 * Created on 19 de mayo de 2008, 19:12
 */

package ontologytestgui;

import java.awt.FlowLayout;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.ScenarioTest;
import model.SparqlQueryOntology;

/**
 *
 * @author  Saruskas
 */
public class AddSPARQLJPanel extends javax.swing.JPanel {

    static final int desktopWidth = 700;
    static final int desktopHeight = 600;
    static JFrame frame;
    public static boolean isSeleccionado() {
        return seleccionado;
    }
    public static void setSeleccionado(boolean aSeleccionado) {
        seleccionado = aSeleccionado;
    }
    public static boolean seleccionado;
    public static ScenarioTest scenarioTestQuery;
    private static ArrayList<SparqlQueryOntology> listSparqlQuerys;
    public static int getContadorAnt() {
        return contadorAnt;
    }
    public static void setContadorAnt(int aContadorAnt) {
        contadorAnt = aContadorAnt;
    }
    public static int getContadorSig() {
        return contadorSig;
    }
    public static void setContadorSig(int aContadorSig) {
        contadorSig = aContadorSig;
    }
    public static void setAntQueryButton(boolean state) {
        antQueryButton.setEnabled(state);
    }
    private JFileChooser filechooser;
    private XMLDecoder decoder;
    private static int contadorAnt = -1;
    private static int contadorSig = 1;
    
    /** Creates new form AddSPARQLJPanel */
    public AddSPARQLJPanel() {
        initComponents();
        
        antQueryButton.setEnabled(false);
        sigQueryButton.setEnabled(false);
        listSparqlQuerys = new ArrayList<SparqlQueryOntology>();
        scenarioTestQuery = new ScenarioTest();
        ArrayList<ScenarioTest> scenarioT = MainJPanel.getCollectionTest().getScenariotest();
        scenarioT.add(scenarioTestQuery);
        MainJPanel.getCollectionTest().setScenariotest(scenarioT);
        
        instancesPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        instancesPanel.add(new AddInstancesJPanel());
        setSeleccionado(true);
        GroupTestsJPanel.setState(false);
        GroupTestsJPanel.setNewState(false); 
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
        nuevaConsultaButton = new javax.swing.JButton();
        limpiarButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        testNameTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        testDescTextArea = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        resultTextArea = new javax.swing.JTextArea();
        añadirConsultaButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        sparqlTextArea = new javax.swing.JTextArea();
        instancesPanel = new javax.swing.JPanel();
        sigQueryButton = new javax.swing.JButton();
        antQueryButton = new javax.swing.JButton();

        jLabel1.setText("Introduzca la consulta en SPARQL:");

        nuevaConsultaButton.setText("Nueva Consulta");
        nuevaConsultaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevaConsultaButtonActionPerformed(evt);
            }
        });

        limpiarButton.setText("Limpiar");
        limpiarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Introduzca el nombre del test:");

        jLabel3.setText("Añada una descripción para el test:");

        testDescTextArea.setColumns(20);
        testDescTextArea.setRows(5);
        jScrollPane2.setViewportView(testDescTextArea);

        jLabel4.setText("Resultado esperado:");

        resultTextArea.setColumns(20);
        resultTextArea.setRows(5);
        jScrollPane3.setViewportView(resultTextArea);

        añadirConsultaButton.setText("Añadir existente");
        añadirConsultaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                añadirConsultaButtonActionPerformed(evt);
            }
        });

        sparqlTextArea.setColumns(20);
        sparqlTextArea.setRows(5);
        jScrollPane1.setViewportView(sparqlTextArea);

        org.jdesktop.layout.GroupLayout instancesPanelLayout = new org.jdesktop.layout.GroupLayout(instancesPanel);
        instancesPanel.setLayout(instancesPanelLayout);
        instancesPanelLayout.setHorizontalGroup(
            instancesPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 743, Short.MAX_VALUE)
        );
        instancesPanelLayout.setVerticalGroup(
            instancesPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 153, Short.MAX_VALUE)
        );

        sigQueryButton.setText(">>");
        sigQueryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sigQueryButtonActionPerformed(evt);
            }
        });

        antQueryButton.setText("<<");
        antQueryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                antQueryButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 495, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(testNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 267, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 224, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 251, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                                    .add(layout.createSequentialGroup()
                                        .add(limpiarButton)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .add(antQueryButton)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(sigQueryButton)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(nuevaConsultaButton)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(añadirConsultaButton))
                                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 563, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(18, 18, 18)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 303, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jLabel4)))))
                    .add(instancesPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel2)
                .add(11, 11, 11)
                .add(testNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jLabel4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                .add(6, 6, 6)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(limpiarButton)
                    .add(añadirConsultaButton)
                    .add(nuevaConsultaButton)
                    .add(sigQueryButton)
                    .add(antQueryButton))
                .add(8, 8, 8)
                .add(instancesPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

private void nuevaConsultaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevaConsultaButtonActionPerformed
// TODO add your handling code here:
    SparqlQueryOntology query = new SparqlQueryOntology();
    if(!AddSPARQLJPanel.getSPARQLQuery().equals("") && !AddSPARQLJPanel.getResultTextArea().equals("")){
        query.setQuerySparql(AddSPARQLJPanel.getSPARQLQuery());
        query.setResultexpected(AddSPARQLJPanel.getResultTextArea());
        getListSparqlQuerys().add(query);
        AddSPARQLJPanel.setResultTextArea("");
        AddSPARQLJPanel.setSPARQLQuery("");
    }else if(AddSPARQLJPanel.getSPARQLQuery().equals("") || AddSPARQLJPanel.getResultTextArea().equals("")){
        JOptionPane.showMessageDialog(frame,"Ambos campos CONSULTA y RESULTADO ESPERADO " +
                "son obligatorios.", "Warning Message",JOptionPane.WARNING_MESSAGE);
    }
    int contadorQ = AddSPARQLJPanel.getContadorAnt();
    int contadorS = AddSPARQLJPanel.getContadorSig();
    int cont = contadorQ+1;
    int contS = contadorS+1;
    antQueryButton.setEnabled(true);
    sigQueryButton.setEnabled(false);
    setContadorAnt(cont);
    setContadorSig(contS);
}//GEN-LAST:event_nuevaConsultaButtonActionPerformed

private void limpiarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarButtonActionPerformed
// TODO add your handling code here:
    AddSPARQLJPanel.setSPARQLQuery("");
    AddSPARQLJPanel.setResultTextArea("");
}//GEN-LAST:event_limpiarButtonActionPerformed

private void añadirConsultaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_añadirConsultaButtonActionPerformed
// TODO add your handling code here:
    filechooser = new JFileChooser("./Sparql Tests/");
    String nameFile="";
      int option = filechooser.showOpenDialog(frame);
      if (option == JFileChooser.APPROVE_OPTION) {
          File selectedFile = filechooser.getSelectedFile();
          nameFile = selectedFile.getAbsolutePath();
      }   

    try{
        decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(nameFile)));
        SparqlQueryOntology qo = (SparqlQueryOntology) decoder.readObject();
        AddSPARQLJPanel.setSPARQLQuery(qo.getQuerySparql());
        decoder.close();    
    }catch(FileNotFoundException e){
    }
}//GEN-LAST:event_añadirConsultaButtonActionPerformed

private void antQueryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_antQueryButtonActionPerformed
// TODO add your handling code here:
    
    SparqlQueryOntology sparql;
    
    sparql = getListSparqlQuerys().get(AddSPARQLJPanel.getContadorAnt());
    AddSPARQLJPanel.setSPARQLQuery(sparql.getQuerySparql());
    AddSPARQLJPanel.setResultTextArea(sparql.getResultexpected());  
    int cont = AddSPARQLJPanel.getContadorAnt();
    int c = cont-1;
    if(c == -1){
        antQueryButton.setEnabled(false);
        AddSPARQLJPanel.setContadorSig(1);
        AddSPARQLJPanel.setContadorAnt(c);
        sigQueryButton.setEnabled(true);
    }else{
        AddSPARQLJPanel.setContadorAnt(c);
        AddSPARQLJPanel.setContadorSig(c+2);
        sigQueryButton.setEnabled(true);
    }
}//GEN-LAST:event_antQueryButtonActionPerformed

private void sigQueryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sigQueryButtonActionPerformed
// TODO add your handling code here:
    int cont;
    cont = AddSPARQLJPanel.getContadorSig();

    int size = getListSparqlQuerys().size();
    if(size <= cont){
        AddSPARQLJPanel.setSPARQLQuery("");
        AddSPARQLJPanel.setResultTextArea("");
        sigQueryButton.setEnabled(false);
        AddSPARQLJPanel.setContadorAnt(cont-1);
    }else{
        SparqlQueryOntology sparql = getListSparqlQuerys().get(cont);
        AddSPARQLJPanel.setSPARQLQuery(sparql.getQuerySparql());
        AddSPARQLJPanel.setResultTextArea(sparql.getResultexpected());  
        int var = AddSPARQLJPanel.getContadorSig()+1;
        AddSPARQLJPanel.setContadorSig(var);
        AddSPARQLJPanel.setContadorAnt(var-2);
    }
        antQueryButton.setEnabled(true);
}//GEN-LAST:event_sigQueryButtonActionPerformed

  private static void createAndShowGUI() {
        //JFrame.setDefaultLookAndFeelDecorated(true);
        //Create and set up the window.
        frame = new JFrame("Test de Prueba");
        frame.getContentPane().add(new AddSPARQLJPanel()); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton antQueryButton;
    private javax.swing.JButton añadirConsultaButton;
    private javax.swing.JPanel instancesPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton limpiarButton;
    private javax.swing.JButton nuevaConsultaButton;
    private static javax.swing.JTextArea resultTextArea;
    private javax.swing.JButton sigQueryButton;
    private static javax.swing.JTextArea sparqlTextArea;
    private static javax.swing.JTextArea testDescTextArea;
    private static javax.swing.JTextField testNameTextField;
    // End of variables declaration//GEN-END:variables
    
    public static String getResultTextArea() {
        return resultTextArea.getText();
    }

    public static void setResultTextArea(String aResultTextArea) {
        resultTextArea.setText(aResultTextArea);
    }

    public static String getSPARQLQuery() {
        return sparqlTextArea.getText();
    }

    public static void setSPARQLQuery(String aSparqlTextArea) {
        sparqlTextArea.setText(aSparqlTextArea);
    }

    public static String getTestDescTextArea() {
        return testDescTextArea.getText();
    }

    public static void setTestDescTextArea(String aTestDescTextArea) {
        testDescTextArea.setText(aTestDescTextArea);
    }

    public static String getTestNameTextField() {
        return testNameTextField.getText();
    }

    public static void setTestNameTextField(String aTestNameTextField) {
        testNameTextField.setText(aTestNameTextField);
    }

    public static ScenarioTest getScenarioTestQuery() {
        return scenarioTestQuery;
    }

    public static void setScenarioTestQuery(ScenarioTest ascenarioTestQuery) {
       scenarioTestQuery = ascenarioTestQuery;
    }
    
    public static ArrayList<SparqlQueryOntology> getListSparqlQuerys() {
        return listSparqlQuerys;
    }

    public static void setListSparqlQuerys(ArrayList<SparqlQueryOntology> aListSparqlQuerys) {
        listSparqlQuerys = aListSparqlQuerys;
    }
}
