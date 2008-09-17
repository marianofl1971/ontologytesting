/*
 * AddSPARQLJPanel.java
 *
 * Created on 19 de mayo de 2008, 19:12
 */

package code.google.com.p.ontologytesting.guiNew;

import code.google.com.p.ontologytesting.jenainterfaz.Jena;
import code.google.com.p.ontologytesting.jenainterfaz.JenaInterface;
import code.google.com.p.ontologytesting.controller.Auxiliar;
import code.google.com.p.ontologytesting.model.*;
import code.google.com.p.ontologytesting.validations.ValidarTests;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author  Saruskas
 */
public class AddSPARQLJPanel extends javax.swing.JPanel {

    private boolean nombreVacio,testYaExiste,sinConsultas,
            ambosNecesarios,noHayInsatncias,queryValida,resultValido,sinTest,continuar;
    private JPanel panelTree;
    static final int desktopWidth = 700;
    static final int desktopHeight = 600;
    static JFrame frame;
    public static boolean seleccionado;
    public static ScenarioTest scenarioTestQuery;
    private int contador=0;
    private int index=0;
    private boolean isAntSelected=false, isSigSelected=false;
    private static List<SparqlQueryOntology> listAux;
    private Component comp;
    private ScenarioTest scenario,scenarioAEditar;
    private List<SparqlQueryOntology> listaDeConsultas;
    private int posListQuerysSel;
    private JenaInterface jenaInterface;
    private Jena jena;
    private int type;
    private boolean importado;
    private ValidarTests validarTest;
    private int tipo;
    
    
    /** Creates new form AddSPARQLJPanel */
    public AddSPARQLJPanel(int tipo) {
        initComponents();
        
        sparqlTextArea.setLineWrap(true);
        sparqlTextArea.setWrapStyleWord(true);
        testDescTextArea.setLineWrap(true);
        testDescTextArea.setWrapStyleWord(true);
        resultTextArea.setLineWrap(true);
        resultTextArea.setWrapStyleWord(true);
        
        antQueryButton.setEnabled(false);
        sigQueryButton.setEnabled(false);
        
        setTipo(type);
        setScenarioAEditar(null);
        setScenario(new ScenarioTest()); 
        Auxiliar.setTestSparql(this);
        setImportado(false);
        listaDeConsultas = new ArrayList<SparqlQueryOntology>();
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
        jButton1 = new javax.swing.JButton();
        guardarEjecutarJButton = new javax.swing.JButton();
        ejecutarJButton = new javax.swing.JButton();
        guardarJButton = new javax.swing.JButton();

        jLabel1.setText("Introduzca la consulta en SPARQL:");

        nuevaConsultaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/add.png"))); // NOI18N
        nuevaConsultaButton.setText("Añadir");
        nuevaConsultaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevaConsultaButtonActionPerformed(evt);
            }
        });

        limpiarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/paintbrush.png"))); // NOI18N
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
        limpiarResultButton.setText("Limpiar");
        limpiarResultButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarResultButtonActionPerformed(evt);
            }
        });

        borrarConsultaJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/delete.png"))); // NOI18N
        borrarConsultaJButton.setText("Borrar");
        borrarConsultaJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarConsultaJButtonActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/code/google/com/p/ontologytesting/images/information.png"))); // NOI18N
        jButton1.setText("Formatos Permitidos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 311, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(160, 160, 160))
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                        .add(limpiarButton)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(borrarConsultaJButton)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 96, Short.MAX_VALUE)
                                        .add(antQueryButton)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                        .add(sigQueryButton)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                        .add(nuevaConsultaButton))
                                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                            .add(layout.createSequentialGroup()
                                                .add(jLabel4)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 134, Short.MAX_VALUE))
                                            .add(layout.createSequentialGroup()
                                                .add(guardarJButton)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                                .add(ejecutarJButton)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)))
                                        .add(guardarEjecutarJButton))
                                    .add(limpiarResultButton)))
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 209, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(testNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 267, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 418, Short.MAX_VALUE)
                                .add(jButton1)))
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 251, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(593, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                        .add(380, 380, 380))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(testNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(11, 11, 11)
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(23, 23, 23)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel1)
                            .add(jLabel4)))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jButton1)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(antQueryButton)
                        .addContainerGap())
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .add(sigQueryButton)
                            .addContainerGap())
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(limpiarButton)
                                    .add(borrarConsultaJButton))
                                .addContainerGap(111, Short.MAX_VALUE))
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(limpiarResultButton)
                                    .add(nuevaConsultaButton))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 75, Short.MAX_VALUE)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(guardarEjecutarJButton)
                                    .add(ejecutarJButton)
                                    .add(guardarJButton))
                                .addContainerGap())))))
        );
    }// </editor-fold>//GEN-END:initComponents

private void nuevaConsultaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevaConsultaButtonActionPerformed
// TODO add your handling code here:
    String q = this.getSPARQLQuery();
    String r = this.getResultTextArea();
    this.inicializarVariables();
    
    this.consultaCompleta(q, r);
    this.consultaVacia(q, r);
    continuar = consultaOK(q, r);
    
    if(continuar==true){
        this.validarConsulta(q);
        this.validarResultado(r);
    }
    
    if(continuar==true){
        SparqlQueryOntology query = new SparqlQueryOntology(q,r);
        if((this.getListaDeConsultas().size()==0) || (posListQuerysSel==listaDeConsultas.size())){
            listaDeConsultas.add(query);
            this.prepararNuevaConsultaVacia();
            antQueryButton.setEnabled(true);
            posListQuerysSel++;
        }else{
            this.reemplazarConsulta(query, posListQuerysSel);
            this.prepararNuevaConsultaVacia();
            sigQueryButton.setEnabled(false);
            posListQuerysSel = listaDeConsultas.size();
        }
   }
}//GEN-LAST:event_nuevaConsultaButtonActionPerformed

public void reemplazarConsulta(SparqlQueryOntology query,int pos){
    listaDeConsultas.remove(pos);
    listaDeConsultas.add(pos, query);
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
        JOptionPane.showMessageDialog(this,"Debe introducir alguna consulta",
        "Warning Message",JOptionPane.WARNING_MESSAGE);
        continuar=false;
    }else if(ambosNecesarios==true){
        JOptionPane.showMessageDialog(this,"Ambos campos CONSULTA y RESULTADO ESPERADO son obligatorios",
        "Warning Message",JOptionPane.WARNING_MESSAGE);
        continuar=false;
    }else if(queryValida==false && resultValido==false){
        JOptionPane.showMessageDialog(this,"La consulta introducida y el resultado no son validos",
        "Warning Message",JOptionPane.WARNING_MESSAGE);
        continuar=false;
    }else if(queryValida==false){
        JOptionPane.showMessageDialog(this,"La consulta introducida no es valida",
        "Warning Message",JOptionPane.WARNING_MESSAGE);
        continuar=false;
    }else if(resultValido==false){
        JOptionPane.showMessageDialog(this,"El formato del resultado no es valido",
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
        JOptionPane.showMessageDialog(frame,"La consulta SPARQL no es valida",
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

private void limpiarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarButtonActionPerformed
// TODO add your handling code here:
    this.setSPARQLQuery("");
}//GEN-LAST:event_limpiarButtonActionPerformed

private void antQueryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_antQueryButtonActionPerformed
// TODO add your handling code here:
    int posSel = getPosListQuerysSel();
    SparqlQueryOntology sparql,sparqlAux;
    sparqlAux = new SparqlQueryOntology(getSPARQLQuery(),getResultTextArea());
    if(!getSPARQLQuery().equals("") && !getResultTextArea().equals("")){
        if(getListaDeConsultas().size()==posSel){
            getListaDeConsultas().add(sparqlAux);
            contador=1;
        }else{
                getListaDeConsultas().get(posSel).setQuerySparql(getSPARQLQuery());
                getListaDeConsultas().get(posSel).setResultexpected(getResultTextArea());
            }
        }
        sparql = getListaDeConsultas().get(posSel-1);
        setSPARQLQuery(sparql.getQuerySparql());
        setResultTextArea(sparql.getResultexpected());
        setPosListQuerysSel(getPosListQuerysSel()-1);
    if(getPosListQuerysSel()==0){
            getAntQueryButton().setEnabled(false);
    }
    if(getListaDeConsultas().size()>=1){
            getSigQueryButton().setEnabled(true);
    }
    if(contador==0){
        if(!getSPARQLQuery().equals("") && !getResultTextArea().equals("")){
            getListaDeConsultas().add(sparqlAux);
            contador=1;
        }
    }
    setIsAntSelected(true);
}//GEN-LAST:event_antQueryButtonActionPerformed

private void sigQueryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sigQueryButtonActionPerformed
// TODO add your handling code here:
    String q = this.getSPARQLQuery();
    String r = this.getResultTextArea();

    this.consultaCompleta(q, r);
    this.consultaVacia(q, r);
    this.validarConsulta(q);
    this.validarResultado(r);
    continuar = consultaOK(q, r);
    
    if(continuar==true){ 
        SparqlQueryOntology query = new SparqlQueryOntology(q,r);
        this.reemplazarConsulta(query, posListQuerysSel);
        SparqlQueryOntology querySig = listaDeConsultas.get(posListQuerysSel+1);
        this.prepararNuevaConsultaCompleta(querySig);
        posListQuerysSel++; 
        if(posListQuerysSel==listaDeConsultas.size()){
            sigQueryButton.setEnabled(false);
        }
    }   
}//GEN-LAST:event_sigQueryButtonActionPerformed

private void limpiarResultButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarResultButtonActionPerformed
// TODO add your handling code here:
    this.setResultTextArea("");
}//GEN-LAST:event_limpiarResultButtonActionPerformed

private void borrarConsultaJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarConsultaJButtonActionPerformed
// TODO add your handling code here: 

}//GEN-LAST:event_borrarConsultaJButtonActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    FormatTestsJDialog format = new FormatTestsJDialog(frame,true);
    format.setModal(false);
    format.setVisible(true);
}//GEN-LAST:event_jButton1ActionPerformed

private void guardarJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarJButtonActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_guardarJButtonActionPerformed

private void guardarEjecutarJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarEjecutarJButtonActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_guardarEjecutarJButtonActionPerformed

private void ejecutarJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejecutarJButtonActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_ejecutarJButtonActionPerformed

public void inicializarVariables(){
    noHayInsatncias=false;
    resultValido=true;
    queryValida=true;
    nombreVacio=false;
    testYaExiste=false;
    sinConsultas=false;
    sinTest=false;
    continuar=true;
    ambosNecesarios=false;
}    

public void guardarDatos(){

        jenaInterface = new JenaInterface();   
        jena = jenaInterface.getJena();
         


    }   

    public static boolean testYaExiste(String nombre){
        ListIterator li;
        ArrayList<ScenarioTest> lista = MainApplication.getCollection().getScenariotest();
        li = lista.listIterator();
        while(li.hasNext()){
            ScenarioTest s = (ScenarioTest) li.next();
            String n = s.getNombre();
            if(n.equals(nombre)){
                return true;
            }
        }
        return false;
    }

    public boolean inListSparqlQuerys(SparqlQueryOntology query){
        String queryq = query.getQuerySparql();
        String queryres = query.getResultexpected();
        SparqlQueryOntology sparql = this.getListaDeConsultas().get(this.getPosListQuerysSel());
        String qquery = sparql.getQuerySparql();
        String qresult = sparql.getResultexpected();
        if(!qquery.equals(queryq) || !qresult.equals(queryres)){
            return false;
        }
        return true;
    }

    public boolean tieneInstanciasAsociadas(ScenarioTest scenario){
        Instancias inst = scenario.getInstancias();
        List<ClassInstances> clasI = inst.getClassInstances();
        List<PropertyInstances> propI = inst.getPropertyInstances();

        if(clasI.size()==0 && propI.size()==0){
            return false;
        }else{
            return true;
        }
    }

    public JPanel getPanelTree() {
        return panelTree;
    }

    public void setPanelTree(JPanel aPanelTree) {
        panelTree = aPanelTree;
    }

    public boolean pertenece(SparqlQueryOntology sparql){
        for(int i=0; i<getListaDeConsultas().size();i++){
            String query = getListaDeConsultas().get(i).getQuerySparql();
            String res = getListaDeConsultas().get(i).getResultexpected();
            if(query.equals(sparql.getQuerySparql()) && res.equals(sparql.getResultexpected())){
                return true;
            }
        }
        return false;
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isIsAntSelected() {
        return isAntSelected;
    }

    public void setIsAntSelected(boolean isAntSelected) {
        this.isAntSelected = isAntSelected;
    }

    public boolean isIsSigSelected() {
        return isSigSelected;
    }

    public void setIsSigSelected(boolean isSigSelected) {
        this.isSigSelected = isSigSelected;
    }

    public javax.swing.JButton getAntQueryButton() {
        return antQueryButton;
    }

    public  javax.swing.JButton getSigQueryButton() {
        return sigQueryButton;
    }

    public javax.swing.JTextArea getSparqlTextArea() {
        return sparqlTextArea;
    }

    public static boolean isSeleccionado() {
        return seleccionado;
    }

    public static void setSeleccionado(boolean aSeleccionado) {
        seleccionado = aSeleccionado;
    }

    public void setAntQueryButton(boolean state) {
        getAntQueryButton().setEnabled(state);
    }

    public void setSigQueryButton(boolean state) {
        getSigQueryButton().setEnabled(state);
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

    public int getTipo() {
        return type;
    }

    public void setTipo(int type) {
        this.type = type;
    }

    public boolean getImportado() {
        return importado;
    }

    public void setImportado(boolean importado) {
        this.importado = importado;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton antQueryButton;
    private javax.swing.JButton borrarConsultaJButton;
    private javax.swing.JButton ejecutarJButton;
    private javax.swing.JButton guardarEjecutarJButton;
    private javax.swing.JButton guardarJButton;
    private javax.swing.JButton jButton1;
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
