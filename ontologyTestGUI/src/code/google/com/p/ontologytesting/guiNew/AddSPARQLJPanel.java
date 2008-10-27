/*
 * AddSPARQLJPanel.java
 *
 * Created on 19 de mayo de 2008, 19:12
 */

package code.google.com.p.ontologytesting.guiNew;

import code.google.com.p.ontologytesting.model.jenainterfaz.*;
import code.google.com.p.ontologytesting.model.*;
import code.google.com.p.ontologytesting.persistence.SaveTest;
import code.google.com.p.ontologytesting.model.ValidarTests;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author  Saruskas
 */
public class AddSPARQLJPanel extends javax.swing.JPanel {

    private boolean testSinNombre,testYaExiste,sinConsultas,
            ambosNecesarios,continuarSinInstancias,queryValida,resultValido,continuar;
    private ScenarioTest scenario,scenarioAEditar;
    private List<SparqlQueryOntology> listaDeConsultas;
    private int posListQuerysSel;
    private JenaInterface jenaInterface;
    private Jena jena;
    private ValidarTests validarTest;
    private SaveTest saveTest;
    private AddInstancesClasPropJDialog addInst;
    private ControladorTests controlador;
    private Utils utils;
    private OpcionesMenu menu;
    
    public AddSPARQLJPanel(ScenarioTest s) {
        initComponents();
        controlador = ControladorTests.getInstance();
        listaDeConsultas = new ArrayList<SparqlQueryOntology>();
        sparqlTextArea.setLineWrap(true);
        sparqlTextArea.setWrapStyleWord(true);
        testDescTextArea.setLineWrap(true);
        testDescTextArea.setWrapStyleWord(true);
        resultTextArea.setLineWrap(true);
        resultTextArea.setWrapStyleWord(true);
        List<SparqlQueryOntology> lista = s.getSparqlQuerys();
        if(lista.size()>0){
            for(int i=0; i<lista.size();i++){
                listaDeConsultas.add(lista.get(i));
            }
            if(lista.size()>1){
                antQueryButton.setEnabled(false);
                sigQueryButton.setEnabled(true);
            }else{
                antQueryButton.setEnabled(false);
                sigQueryButton.setEnabled(false);
            }
            this.prepararNuevaConsultaCompleta(lista.get(0));
            this.setTestNameTextField(s.getNombre());
            this.setTestDescTextArea(s.getDescripcion());
        }
        this.inicializarVariables();
        utils = new Utils();
        menu = new OpcionesMenu();
        scenarioAEditar = new ScenarioTest(s);
        setScenario(s);
        setPosListQuerysSel(0);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        sparqlTextArea = new javax.swing.JTextArea();
        sigQueryButton = new javax.swing.JButton();
        antQueryButton = new javax.swing.JButton();
        limpiarResultButton = new javax.swing.JButton();
        borrarConsultaJButton = new javax.swing.JButton();
        formatosPermitidos = new javax.swing.JButton();
        guardarEjecutarJButton = new javax.swing.JButton();
        ejecutarJButton = new javax.swing.JButton();
        guardarJButton = new javax.swing.JButton();
        asociarInstanciasButton = new javax.swing.JButton();

        jLabel1.setText("Introduzca la consulta en SPARQL:");

        nuevaConsultaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/add.png"))); // NOI18N
        nuevaConsultaButton.setText("Añadir");
        nuevaConsultaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevaConsultaButtonActionPerformed(evt);
            }
        });

        limpiarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/paintbrush.png"))); // NOI18N
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

        sparqlTextArea.setColumns(20);
        sparqlTextArea.setRows(5);
        jScrollPane1.setViewportView(sparqlTextArea);

        sigQueryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/resultset_next.png"))); // NOI18N
        sigQueryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sigQueryButtonActionPerformed(evt);
            }
        });

        antQueryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/resultset_previous.png"))); // NOI18N
        antQueryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                antQueryButtonActionPerformed(evt);
            }
        });

        limpiarResultButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/paintbrush.png"))); // NOI18N
        limpiarResultButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarResultButtonActionPerformed(evt);
            }
        });

        borrarConsultaJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/delete.png"))); // NOI18N
        borrarConsultaJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarConsultaJButtonActionPerformed(evt);
            }
        });

        formatosPermitidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/information.png"))); // NOI18N
        formatosPermitidos.setText("Formatos Permitidos");
        formatosPermitidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formatosPermitidosActionPerformed(evt);
            }
        });

        guardarEjecutarJButton.setText("Guardar y Ejecutar");
        guardarEjecutarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarEjecutarJButtonActionPerformed(evt);
            }
        });

        ejecutarJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/cog.png"))); // NOI18N
        ejecutarJButton.setText("Ejecutar");
        ejecutarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarJButtonActionPerformed(evt);
            }
        });

        guardarJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/disk.png"))); // NOI18N
        guardarJButton.setText("Guardar");
        guardarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarJButtonActionPerformed(evt);
            }
        });

        asociarInstanciasButton.setText("Asociar Instancias");
        asociarInstanciasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asociarInstanciasButtonActionPerformed(evt);
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
                            .add(testNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 267, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 251, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(limpiarButton)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(borrarConsultaJButton)
                                        .add(118, 118, 118)
                                        .add(antQueryButton)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(sigQueryButton)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(nuevaConsultaButton))
                                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 311, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane2)
                                        .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel4)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                        .add(limpiarResultButton)
                                        .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 336, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(asociarInstanciasButton)))))
                        .add(12, 12, 12))
                    .add(layout.createSequentialGroup()
                        .add(guardarJButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(ejecutarJButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(guardarEjecutarJButton)
                        .addContainerGap(467, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 209, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 375, Short.MAX_VALUE)
                        .add(formatosPermitidos)
                        .add(53, 53, 53))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(27, 27, 27)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(formatosPermitidos))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(layout.createSequentialGroup()
                        .add(testNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(11, 11, 11)
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(asociarInstanciasButton))
                .add(23, 23, 23)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jLabel4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(limpiarButton)
                                .add(borrarConsultaJButton))
                            .add(nuevaConsultaButton)
                            .add(sigQueryButton)
                            .add(antQueryButton))
                        .add(38, 38, 38)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(guardarJButton)
                            .add(ejecutarJButton)
                            .add(guardarEjecutarJButton)))
                    .add(limpiarResultButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

private void nuevaConsultaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevaConsultaButtonActionPerformed
// TODO add your handling code here:
    String q = this.getSPARQLQuery();
    String r = this.getResultTextArea();
    this.inicializarVariables();
    
    this.consultaVacia(q, r);
    if(sinConsultas==false){
        this.consultaCompleta(q, r);

        continuar = consultaOK(q, r);

        if(continuar==true){
            this.validarConsulta(q);
            if(continuar==true){
                this.validarResultado(r);
            }
        }

        if(continuar==true){
            SparqlQueryOntology query = new SparqlQueryOntology(q,r);
            if((this.getListaDeConsultas().size()==0) || (posListQuerysSel==listaDeConsultas.size())){
                listaDeConsultas.add(query);
                this.prepararNuevaConsultaVacia();
                antQueryButton.setEnabled(true);
                sigQueryButton.setEnabled(false);
                posListQuerysSel++;
            }else{
                this.reemplazarConsulta(query, posListQuerysSel);
                this.prepararNuevaConsultaVacia();
                sigQueryButton.setEnabled(false);
                posListQuerysSel = listaDeConsultas.size();
                antQueryButton.setEnabled(true);
            }
       }else if(resultValido==false){
            JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"El formato del resultado no es valido",
            "Warning Message",JOptionPane.WARNING_MESSAGE);
            continuar=false;
       }
    }else{
        JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"Actualmente tiene una consulta nueva para añadir",
        "Warning Message",JOptionPane.WARNING_MESSAGE);
    }
}//GEN-LAST:event_nuevaConsultaButtonActionPerformed


private void limpiarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarButtonActionPerformed
// TODO add your handling code here:
    this.setSPARQLQuery("");
}//GEN-LAST:event_limpiarButtonActionPerformed

private void antQueryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_antQueryButtonActionPerformed
// TODO add your handling code here:
    inicializarVariables();
    String q = this.getSPARQLQuery();
    String r = this.getResultTextArea();

    this.consultaVacia(q, r);
    if(sinConsultas==false){
        this.consultaCompleta(q, r);
        continuar = consultaOK(q, r);
        if(continuar==true){
            this.validarConsulta(q);
            if(continuar==true){
                this.validarResultado(r);
            }
        } 
    }
    
    if(continuar==true){
        SparqlQueryOntology query = new SparqlQueryOntology(q,r);
        SparqlQueryOntology querySig = listaDeConsultas.get(posListQuerysSel-1);
        if(posListQuerysSel==listaDeConsultas.size()){
           listaDeConsultas.add(query); 
           this.prepararNuevaConsultaCompleta(querySig);
           sigQueryButton.setEnabled(true);
        }else{
            this.reemplazarConsulta(query, posListQuerysSel);
            this.prepararNuevaConsultaCompleta(querySig);
            sigQueryButton.setEnabled(true);
        }
        posListQuerysSel--; 
        if(posListQuerysSel==0){
            antQueryButton.setEnabled(false);
        }
    }   
}//GEN-LAST:event_antQueryButtonActionPerformed

private void sigQueryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sigQueryButtonActionPerformed
// TODO add your handling code here:
    inicializarVariables();
    String q = this.getSPARQLQuery();
    String r = this.getResultTextArea();
    this.consultaVacia(q, r);
    if(sinConsultas==false){
        this.consultaCompleta(q, r);
        continuar = consultaOK(q, r);
        if(continuar==true){
            this.validarConsulta(q);
            if(continuar==true){
                this.validarResultado(r);
            }
        } 
    }
    if(continuar==true){ 
        SparqlQueryOntology query = new SparqlQueryOntology(q,r);
        this.reemplazarConsulta(query, posListQuerysSel);
        SparqlQueryOntology querySig = listaDeConsultas.get(posListQuerysSel+1);
        this.prepararNuevaConsultaCompleta(querySig);
        posListQuerysSel++; 
        if(posListQuerysSel==listaDeConsultas.size()-1){
            sigQueryButton.setEnabled(false);
        }
        if(posListQuerysSel<listaDeConsultas.size()){
            antQueryButton.setEnabled(true);
        }
    }   
}//GEN-LAST:event_sigQueryButtonActionPerformed

private void limpiarResultButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarResultButtonActionPerformed
// TODO add your handling code here:
    this.setResultTextArea("");
}//GEN-LAST:event_limpiarResultButtonActionPerformed

private void borrarConsultaJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarConsultaJButtonActionPerformed
// TODO add your handling code here:
    inicializarVariables();
    if(listaDeConsultas.size()==0 || (this.getSPARQLQuery().equals("") && this.getResultTextArea().equals(""))){
        JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"No hay ninguna consulta que borrar",
        "Warning Message",JOptionPane.WARNING_MESSAGE);
    }else if(listaDeConsultas.size()==1){
        this.prepararNuevaConsultaVacia();
        setListaDeConsultas(new ArrayList<SparqlQueryOntology>());
        setPosListQuerysSel(0);
    }else{
        if(posListQuerysSel==listaDeConsultas.size()-1){
            SparqlQueryOntology querySig = listaDeConsultas.get(posListQuerysSel-1);
            listaDeConsultas.remove(posListQuerysSel);
            this.prepararNuevaConsultaCompleta(querySig);
            sigQueryButton.setEnabled(false);
            posListQuerysSel--;
        
        }else{
            SparqlQueryOntology querySig = listaDeConsultas.get(posListQuerysSel+1);
            this.prepararNuevaConsultaCompleta(querySig);
            listaDeConsultas.remove(posListQuerysSel);
        }
        if(posListQuerysSel==listaDeConsultas.size()-1){
            sigQueryButton.setEnabled(false);
        }
        if(listaDeConsultas.size()==1){
            sigQueryButton.setEnabled(false);
            antQueryButton.setEnabled(false);
        } 
    }
    
    
}//GEN-LAST:event_borrarConsultaJButtonActionPerformed

private void formatosPermitidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN FIRST:event_formatosPermitidosActionPerformed
// TODO add your handling code here:
    FormatTestsJDialog format = new FormatTestsJDialog(null,true,5);
    format.setLocationRelativeTo(MainApplicationJFrame.getInstance());
    format.setModal(false);
    format.setVisible(true);
}                                                  

private void asociarInstanciasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asociarInstanciasButtonActionPerformed
// TODO add your handling code here:
    addInst = new AddInstancesClasPropJDialog(null,true,this.getScenario());
    addInst.setLocationRelativeTo(MainApplicationJFrame.getInstance());
    addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    addInst.setVisible(true);
}//GEN-LAST:event_asociarInstanciasButtonActionPerformed

private void guardarJButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
// TODO add your handling code here:
    prepararGuardar();
    if(continuar==true){
        if(continuarSinInstancias==true){
            this.realizarAccion(true, false);
        }else{
            addInst = new AddInstancesClasPropJDialog(null,true,this.getScenario());
            addInst.setLocationRelativeTo(MainApplicationJFrame.getInstance());
            addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            addInst.setVisible(true);
        }
    }
}                                              

private void guardarEjecutarJButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                       
// TODO add your handling code here:
    prepararGuardar();
    if(continuar==true){
        if(continuarSinInstancias==true){
            this.realizarAccion(true, true);
        }else{
            addInst = new AddInstancesClasPropJDialog(null,true,this.getScenario());
            addInst.setLocationRelativeTo(MainApplicationJFrame.getInstance());
            addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            addInst.setVisible(true);
        }
    }
}                                                      

private void ejecutarJButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                
// TODO add your handling code here:
    prepararGuardar();
    if(continuar==true){
        if(continuarSinInstancias==true){
            this.realizarAccion(false, true);
        }else{
            addInst = new AddInstancesClasPropJDialog(null,true,this.getScenario());
            addInst.setLocationRelativeTo(MainApplicationJFrame.getInstance());
            addInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            addInst.setVisible(true);
        }
    }
}                                               

public void realizarAccion(boolean guardar, boolean ejecutar){
    saveTest = new SaveTest();
    if(testYaExiste==true){
        if(guardar==true){
            if((this.getScenarioAEditar() != null) && (scenario.equals(this.getScenarioAEditar())==false)
                        && (this.getScenario().getNombre().equals(this.getScenarioAEditar().getNombre()))){
                Object[] options = {"Sobreescribir", "Cancelar"};
                int n = JOptionPane.showOptionDialog(MainApplicationJFrame.getInstance(), "El test ya existe o ha sido modificado. ¿Que desea hacer?", 
                        "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
                if (n == JOptionPane.YES_OPTION) {
                    saveTest.replaceScenarioLocally(scenario);
                    setScenarioAEditar(new ScenarioTest(scenario));
                    setScenario(new ScenarioTest(scenario));
                    controlador.setTestSparqlGuardado(true);
                    JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"El test ha sido sobreescrito",
                    "Confirm Message",JOptionPane.INFORMATION_MESSAGE); 
                }
            }else{
                saveTest.saveTestInMemory(this.getScenario());
                controlador.setTestSparqlGuardado(true);
                JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"No se han producido cambios en el test",
                "Confirm Message",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if(ejecutar==true){
            boolean res = menu.ejecutarUnTest(this.getScenario());
            if(res==true){
                JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"Test ejecutado",                                                  
                "Confirm Message",JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"No se pudo ejecutar el test",                                                  
                "Error Message",JOptionPane.ERROR_MESSAGE);
            }
        }
     }else{
        if(guardar==true){
            saveTest.saveTestInMemory(scenario);
            setScenarioAEditar(new ScenarioTest(scenario));
            setScenario(new ScenarioTest(scenario));
            controlador.setTestSparqlGuardado(true);
            JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"El test ha sido guardado",
            "Confirm Message",JOptionPane.INFORMATION_MESSAGE);
        }
        if(ejecutar==true){
            boolean res = menu.ejecutarUnTest(this.getScenario());
            if(res==true){
                JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"Test ejecutado",                                                  
                "Confirm Message",JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"No se pudo ejecutar el test",                                                  
                "Error Message",JOptionPane.ERROR_MESSAGE);
            }
        }
    }  
    saveTest.actualizarListaDeTestsSparql(CollectionTest.getInstance().getScenariotest());  
}

public void reemplazarConsulta(SparqlQueryOntology query,int pos){
    if(pos==listaDeConsultas.size()){
        listaDeConsultas.add(query);
    }else{
        listaDeConsultas.remove(pos);
        listaDeConsultas.add(pos, query);
    }
}

public void prepararNuevaConsultaVacia(){
    this.setSPARQLQuery("");
    this.setResultTextArea("");
}

public void prepararNuevaConsultaCompleta(SparqlQueryOntology query){
    this.setSPARQLQuery(query.getQuerySparql());
    this.setResultTextArea(query.getResultexpected());
}

public void consultaVacia(String q, String r){
    if(q.equals("") && r.equals("")){
        sinConsultas=true;
    }
}

public void consultaCompleta(String query, String result){
    if((query.equals("") && !result.equals("")) || (!query.equals("") && result.equals(""))){
        ambosNecesarios=true;
    }
}

public boolean consultaOK(String query, String result){
    if(resultValido==true && ambosNecesarios==false && sinConsultas==false){
        continuar=true;
    }else if(sinConsultas==true){
        JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"Debe introducir alguna consulta",
        "Warning Message",JOptionPane.WARNING_MESSAGE);
        continuar=false;
    }else if(ambosNecesarios==true){
        JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"Ambos campos CONSULTA y RESULTADO ESPERADO son obligatorios",
        "Warning Message",JOptionPane.WARNING_MESSAGE);
        continuar=false;
    }else if(queryValida==false && resultValido==false){
        JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"La consulta introducida y el resultado no son validos",
        "Warning Message",JOptionPane.WARNING_MESSAGE);
        continuar=false;
    }else if(queryValida==false){
        JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"La consulta introducida no es valida",
        "Warning Message",JOptionPane.WARNING_MESSAGE);
        continuar=false;
    }else if(resultValido==false){
        JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"El formato del resultado no es valido",
        "Warning Message",JOptionPane.WARNING_MESSAGE);
        continuar=false;
    }
    return continuar;
}

public void validarConsulta(String query){
    jenaInterface = new JenaInterface();   
    jena = jenaInterface.getJena();
    try{
        jena.validarSparqlQuery(query); 
    }catch(Exception ex){
        JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"La consulta SPARQL no es valida",
        "Warning Message",JOptionPane.WARNING_MESSAGE);
        queryValida=false;
        continuar=false;
    }
}

public void validarResultado(String resul){
    validarTest = new ValidarTests();
    if(!validarTest.validarSparqlResult(resul)){
        resultValido=false;
        continuar=false;
    }
}

public void prepararGuardar(){
    inicializarVariables();
    String nombreTest = this.getTestNameTextField();
    String descTest = this.getTestDescTextArea();
    String query = this.getSPARQLQuery();
    String result = this.getResultTextArea();
    SparqlQueryOntology q = new SparqlQueryOntology(query,result);
    if(utils.testYaExiste(CollectionTest.getInstance().getScenariotest(),nombreTest)==true){
        testYaExiste=true;
    }
    if(testVacio(nombreTest)==true){
        testSinNombre=true;
    }
    //if(!this.inListSparqlQuerys(q)){
        this.consultaCompleta(query, result);
        this.consultaVacia(query, result);
        continuar = this.consultaOK(query, result);
        if(continuar==true){
            this.validarConsulta(query);
            if(continuar==true){
                this.validarResultado(result);
                if(continuar==true){
                    this.reemplazarConsulta(q, posListQuerysSel);
                }else{
                    JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"El formato del resultado no es valido",
                    "Warning Message",JOptionPane.WARNING_MESSAGE);
                    continuar=false;
                }
            }
        }
    //}
    if(continuar==true){
        if(testSinNombre==false && listaDeConsultas.size()>0 && continuar==true){  
            boolean res = scenario.preguntarSiContinuarSinInstancias();
            if(res==true){
                scenario.setDescripcion(descTest);
                scenario.setNombre(nombreTest);
                List<SparqlQueryOntology> querys = new ArrayList<SparqlQueryOntology>(this.getListaDeConsultas());
                scenario.setSparqlQuerys(querys); 
            }
        }else if(testSinNombre==true){
                JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"El nombre del test es obligatorio",
                "Warning Message",JOptionPane.WARNING_MESSAGE);
                continuar=false;
        }else if(listaDeConsultas.size()==0 && testSinNombre==false){
            JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"Al menos debe introducir una consulta " +
            "para guardar el test.","Warning Message",JOptionPane.WARNING_MESSAGE);
            continuar=false;
        }
    }
}

public boolean testVacio(String nombre){
    if(nombre.equals("")){
        return true;
    }else{
        return false;
    }
}

public void inicializarVariables(){
    continuarSinInstancias=true;
    resultValido=true;
    queryValida=true;
    testSinNombre=false;
    testYaExiste=false;
    sinConsultas=false;
    continuar=true;
    ambosNecesarios=false;
}      
    
    public List<SparqlQueryOntology> getListaDeConsultas() {
        return listaDeConsultas;
    }

    public void setListaDeConsultas(List<SparqlQueryOntology> aListaDeConsultas) {
        listaDeConsultas = aListaDeConsultas;
    }

    public String getResultTextArea() {
        return resultTextArea.getText();
    }

    public void setResultTextArea(String aResultTextArea) {
        resultTextArea.setText(aResultTextArea);
    }

    public String getSPARQLQuery() {
        return getSparqlTextArea().getText();
    }

    public void setSPARQLQuery(String aSparqlTextArea) {
        getSparqlTextArea().setText(aSparqlTextArea);
    }

    public String getTestDescTextArea() {
        return testDescTextArea.getText();
    }

    public void setTestDescTextArea(String aTestDescTextArea) {
        testDescTextArea.setText(aTestDescTextArea);
    }

    public String getTestNameTextField() {
        return testNameTextField.getText();
    }

    public void setTestNameTextField(String aTestNameTextField) {
        testNameTextField.setText(aTestNameTextField);
    }

    public javax.swing.JTextArea getSparqlTextArea() {
        return sparqlTextArea;
    }

    public void setAntQueryButton(boolean state) {
        antQueryButton.setEnabled(state);
    }

    public void setSigQueryButton(boolean state) {
        sigQueryButton.setEnabled(state);
    }

    public int getPosListQuerysSel() {
            return posListQuerysSel;
    }

    public void setPosListQuerysSel(int aPosListQuerysSel) {
        posListQuerysSel = aPosListQuerysSel;
    }

    public ScenarioTest getScenario() {
            return scenario;
        }

    public void setScenario(ScenarioTest scenario) {
        this.scenario = scenario;
    }

    public ScenarioTest getScenarioAEditar() {
        return scenarioAEditar;
    }

    public void setScenarioAEditar(ScenarioTest scenarioAEditar) {
        this.scenarioAEditar = scenarioAEditar;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton antQueryButton;
    private javax.swing.JButton asociarInstanciasButton;
    private javax.swing.JButton borrarConsultaJButton;
    private javax.swing.JButton ejecutarJButton;
    private javax.swing.JButton formatosPermitidos;
    private javax.swing.JButton guardarEjecutarJButton;
    private javax.swing.JButton guardarJButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton limpiarButton;
    private javax.swing.JButton limpiarResultButton;
    private javax.swing.JButton nuevaConsultaButton;
    private javax.swing.JTextArea resultTextArea;
    private javax.swing.JButton sigQueryButton;
    private javax.swing.JTextArea sparqlTextArea;
    private javax.swing.JTextArea testDescTextArea;
    private javax.swing.JTextField testNameTextField;
    // End of variables declaration//GEN-END:variables


}
