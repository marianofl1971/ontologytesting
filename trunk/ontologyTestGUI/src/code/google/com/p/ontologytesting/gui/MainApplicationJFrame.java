/*
 * MainApplicationJFrame.java
 *
 * Created on 11 de octubre de 2008, 12:33
 */

package code.google.com.p.ontologytesting.gui;

import code.google.com.p.ontologytesting.gui.auxiliarpanels.*;
import code.google.com.p.ontologytesting.gui.menupanels.*;
import code.google.com.p.ontologytesting.gui.instances.*;
import code.google.com.p.ontologytesting.gui.auxiliarclasess.*;
import code.google.com.p.ontologytesting.gui.tests.*;
import code.google.com.p.ontologytesting.model.*;
import code.google.com.p.ontologytesting.model.ScenarioTest.TipoTest;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.ExceptionReadOntology;
import code.google.com.p.ontologytesting.persistence.LoadTest;
import code.google.com.p.ontologytesting.persistence.SaveTest;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author  saruskas
 */
public class MainApplicationJFrame extends javax.swing.JFrame {
    
    private ListAndResultsJPanel panelTest;
    private ControladorTests controlador;
    private ScenarioTest s = new ScenarioTest();
    private String carpetaProyecto,nombreProyecto;
    private SaveTest saveTest = new SaveTest();
    private LoadTest loadTest = new LoadTest();
    private OpcionesMenu opMenu = new OpcionesMenu();
    private static MainApplicationJFrame mainApp = null;
    private boolean proyectoGuardado=false;
    private AniadirPanelDeAviso panelAviso;
    private FileChooserSelector utils;
    private XMLDecoder decoder;
    private CollectionTest collection;
    private TestSimpleInstSat testInstSat;
    private TestSimpleReal testReal;
    private TestSimpleRetClas testRetClas;
    private AddSPARQLJPanel testSparql;
    private JPanel panelActual;
    
    /** Creates new form MainApplicationJFrame */
    private MainApplicationJFrame() {
        initComponents();
        panelAviso = new AniadirPanelDeAviso();
        this.setTitle("EVALUADOR DE ONTOLOGIAS");
        this.setSize(new Dimension(895,720));
        controlador = ControladorTests.getInstance();
        panelTest = ListAndResultsJPanel.getInstance();
        contentTestsJPanel.setLayout(new BorderLayout());
        //panelTest = ListAndResultsJPanel.getInstance();
        //"http://www.owl-ontologies.com/family.owl#"
        //http://nlp.shef.ac.uk/abraxas/ontologies/animals.owl
        //http://www.semanticweb.org/ontologies/2008/1/Ontology1202481514781.owl
        //C:\\Users\\saruskas\\Desktop\\Imple OntologyTestGui\\ontologyTestGUI\\data\\family.owl
        //C:\\Documents and Settings\\sara_garcia\\Escritorio\\PFC\\Imple OntologyTestGui\\ontologyTestGUI\\data\\family.owl
        
          /*PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
          SELECT ?subject ?object 
          WHERE {?subject rdfs:subClassOf ?object }*/
         
    }
 
    private synchronized static void createListAndTestPanel() {
        if (mainApp == null) { 
            mainApp = new MainApplicationJFrame();
        }
    }
 
    public static MainApplicationJFrame getInstance() {
        if (mainApp == null) createListAndTestPanel();
        return mainApp;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentTestsJPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        nuevoProyectoMenuItem = new javax.swing.JMenuItem();
        abrirProyectoMenuItem = new javax.swing.JMenuItem();
        guardarProyectoMenuItem = new javax.swing.JMenuItem();
        guardarProyectoComoMenuItem = new javax.swing.JMenuItem();
        salirMenuItem = new javax.swing.JMenuItem();
        testsMenu = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        nuevoTestInstMenuItem = new javax.swing.JMenuItem();
        nuevoTestRecMenuItem = new javax.swing.JMenuItem();
        nuevoTestRealMenuItem = new javax.swing.JMenuItem();
        nuevoTestSatMenuItem = new javax.swing.JMenuItem();
        nuevoTestClasMenuItem = new javax.swing.JMenuItem();
        nuevoTestSparqlMenuItem = new javax.swing.JMenuItem();
        importarTestsMenuItem = new javax.swing.JMenuItem();
        editarTestsMenuItem = new javax.swing.JMenuItem();
        verTestsMenuItem = new javax.swing.JMenuItem();
        instanciasMenu = new javax.swing.JMenu();
        nuevoInstanciasMenuItem = new javax.swing.JMenuItem();
        importarInstanciasMenuItem = new javax.swing.JMenuItem();
        editarInstanciasMenuItem = new javax.swing.JMenuItem();
        verInstanciasMenuItem = new javax.swing.JMenuItem();
        ejecutarMenu = new javax.swing.JMenu();
        selecTestMenuItem = new javax.swing.JMenuItem();
        ejecutarTodosMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout contentTestsJPanelLayout = new javax.swing.GroupLayout(contentTestsJPanel);
        contentTestsJPanel.setLayout(contentTestsJPanelLayout);
        contentTestsJPanelLayout.setHorizontalGroup(
            contentTestsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 718, Short.MAX_VALUE)
        );
        contentTestsJPanelLayout.setVerticalGroup(
            contentTestsJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 452, Short.MAX_VALUE)
        );

        fileMenu.setText("Proyecto");

        nuevoProyectoMenuItem.setText("Nuevo");
        nuevoProyectoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoProyectoMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(nuevoProyectoMenuItem);

        abrirProyectoMenuItem.setText("Abrir");
        abrirProyectoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirProyectoMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(abrirProyectoMenuItem);

        guardarProyectoMenuItem.setText("Guardar");
        guardarProyectoMenuItem.setEnabled(false);
        guardarProyectoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarProyectoMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(guardarProyectoMenuItem);

        guardarProyectoComoMenuItem.setText("Guardar Como");
        guardarProyectoComoMenuItem.setEnabled(false);
        guardarProyectoComoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarProyectoComoMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(guardarProyectoComoMenuItem);

        salirMenuItem.setText("Salir");
        salirMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(salirMenuItem);

        menuBar.add(fileMenu);

        testsMenu.setText("Tests");

        jMenu3.setText("Nuevo");

        jMenu4.setText("Test Simple");

        nuevoTestInstMenuItem.setText("Test de Instanciación");
        nuevoTestInstMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoTestInstMenuItemActionPerformed(evt);
            }
        });
        jMenu4.add(nuevoTestInstMenuItem);

        nuevoTestRecMenuItem.setText("Test de Recuperación");
        nuevoTestRecMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoTestRecMenuItemActionPerformed(evt);
            }
        });
        jMenu4.add(nuevoTestRecMenuItem);

        nuevoTestRealMenuItem.setText("Test de Realización");
        nuevoTestRealMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoTestRealMenuItemActionPerformed(evt);
            }
        });
        jMenu4.add(nuevoTestRealMenuItem);

        nuevoTestSatMenuItem.setText("Test de Satisfactibilidad");
        nuevoTestSatMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoTestSatMenuItemActionPerformed(evt);
            }
        });
        jMenu4.add(nuevoTestSatMenuItem);

        nuevoTestClasMenuItem.setText("Test de Clasificación");
        nuevoTestClasMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoTestClasMenuItemActionPerformed(evt);
            }
        });
        jMenu4.add(nuevoTestClasMenuItem);

        jMenu3.add(jMenu4);

        nuevoTestSparqlMenuItem.setText("Test Sparql");
        nuevoTestSparqlMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoTestSparqlMenuItemActionPerformed(evt);
            }
        });
        jMenu3.add(nuevoTestSparqlMenuItem);

        testsMenu.add(jMenu3);

        importarTestsMenuItem.setText("Importar");
        importarTestsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importarTestsMenuItemActionPerformed(evt);
            }
        });
        testsMenu.add(importarTestsMenuItem);

        editarTestsMenuItem.setText("Editar");
        editarTestsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarTestsMenuItemActionPerformed(evt);
            }
        });
        testsMenu.add(editarTestsMenuItem);

        verTestsMenuItem.setText("Ver");
        verTestsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verTestsMenuItemActionPerformed(evt);
            }
        });
        testsMenu.add(verTestsMenuItem);

        menuBar.add(testsMenu);

        instanciasMenu.setText("Instancias");
        instanciasMenu.setEnabled(false);

        nuevoInstanciasMenuItem.setText("Nuevo");
        nuevoInstanciasMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoInstanciasMenuItemActionPerformed(evt);
            }
        });
        instanciasMenu.add(nuevoInstanciasMenuItem);

        importarInstanciasMenuItem.setText("Importar");
        importarInstanciasMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importarInstanciasMenuItemActionPerformed(evt);
            }
        });
        instanciasMenu.add(importarInstanciasMenuItem);

        editarInstanciasMenuItem.setText("Editar");
        editarInstanciasMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarInstanciasMenuItemActionPerformed(evt);
            }
        });
        instanciasMenu.add(editarInstanciasMenuItem);

        verInstanciasMenuItem.setText("Ver");
        verInstanciasMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verInstanciasMenuItemActionPerformed(evt);
            }
        });
        instanciasMenu.add(verInstanciasMenuItem);

        menuBar.add(instanciasMenu);

        ejecutarMenu.setText("Ejecutar");
        ejecutarMenu.setEnabled(false);

        selecTestMenuItem.setText("Seleccionar test");
        selecTestMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecTestMenuItemActionPerformed(evt);
            }
        });
        ejecutarMenu.add(selecTestMenuItem);

        ejecutarTodosMenuItem.setText("Todos los Tests");
        ejecutarTodosMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarTodosMenuItemActionPerformed(evt);
            }
        });
        ejecutarMenu.add(ejecutarTodosMenuItem);

        menuBar.add(ejecutarMenu);

        helpMenu.setText("Ayuda");

        contentsMenuItem.setText("Contenidos");
        contentsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contentsMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setText("Acerca de");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentTestsJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentTestsJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardarProyectoComoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarProyectoComoMenuItemActionPerformed
        utils = new FileChooserSelector();
        try {
            utils.fileChooser(false, true);
            File fichero = utils.getFileSelected();
            boolean guardado = saveTest.saveProject(true,this.getCarpetaProyecto(),this.getNombreProyecto(),fichero);
            if(guardado==true){
                panelAviso.confirmAction("Proyecto guardado", this); 
                this.setProyectoGuardado(true);
            }else{
                panelAviso.errorAction("Error. Proyecto no guardado",this);                                                
            }
        }catch (FileNotFoundException ex) {
            panelAviso.errorAction("Error. No se encontró el archivo especificado",this);              
        }
        
}//GEN-LAST:event_guardarProyectoComoMenuItemActionPerformed

private void nuevoProyectoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                      
// TODO add your handling code here:
    //Realmente me crea la collection al crar el proyecto, aqui lo quitaria
    //collection.setNamespace("http://www.owl-ontologies.com/family.owl#");
    //collection.setOntology("C:\\Documents and Settings\\sara_garcia\\Escritorio\\PFC\\Imple OntologyTestGui\\ontologyTestGUI\\data\\family.owl");
    NewProjectJDialog newProject = new NewProjectJDialog(this,true);
    newProject.setLocationRelativeTo(this);
    newProject.setVisible(true);
    if(newProject.getProyectoCreado()==true){
        this.inicializarContadores();
        guardarProyectoComoMenuItem.setEnabled(true);
        guardarProyectoMenuItem.setEnabled(true);
        instanciasMenu.setEnabled(true);
        testsMenu.setEnabled(true);
        ejecutarMenu.setEnabled(true);
        contentTestsJPanel.add(panelTest,BorderLayout.CENTER);
        this.validate();
    }

}

private void salirMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirMenuItemActionPerformed
// TODO add your handling code here:
    int n = JOptionPane.showConfirmDialog(this, "¿Desea abandonar la aplicación?", 
                "Salir",JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION){
            this.dispose();
            System.exit(0);
        } 

}//GEN-LAST:event_salirMenuItemActionPerformed

private void nuevoTestInstMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoTestInstMenuItemActionPerformed
// TODO add your handling code here:
    s = new ScenarioTest(TipoTest.INST);
    aniadirNuevoTest(s);
}//GEN-LAST:event_nuevoTestInstMenuItemActionPerformed

private void nuevoTestRecMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoTestRecMenuItemActionPerformed
// TODO add your handling code here:
    s = new ScenarioTest(TipoTest.RET);
    aniadirNuevoTest(s);
}//GEN-LAST:event_nuevoTestRecMenuItemActionPerformed


private void nuevoTestRealMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoTestRealMenuItemActionPerformed
// TODO add your handling code here:
    s = new ScenarioTest(TipoTest.REAL);
    aniadirNuevoTest(s);
}//GEN-LAST:event_nuevoTestRealMenuItemActionPerformed

private void nuevoTestSatMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoTestSatMenuItemActionPerformed
// TODO add your handling code here:
    s = new ScenarioTest(TipoTest.SAT);
    aniadirNuevoTest(s);
}//GEN-LAST:event_nuevoTestSatMenuItemActionPerformed


private void nuevoTestClasMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoTestClasMenuItemActionPerformed
// TODO add your handling code here:
    s = new ScenarioTest(TipoTest.CLAS);
    aniadirNuevoTest(s);
}//GEN-LAST:event_nuevoTestClasMenuItemActionPerformed

private void nuevoTestSparqlMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoTestSparqlMenuItemActionPerformed
// TODO add your handling code here:
    s = new ScenarioTest(TipoTest.SPARQL);
    aniadirNuevoTest(s);
}//GEN-LAST:event_nuevoTestSparqlMenuItemActionPerformed

private void importarTestsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importarTestsMenuItemActionPerformed
// TODO add your handling code here:
    this.importarTestsInstancias(true);
}//GEN-LAST:event_importarTestsMenuItemActionPerformed

private void editarTestsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarTestsMenuItemActionPerformed
// TODO add your handling code here:
    boolean res = listaTestsInstanciasVacia(true); 
    if(res==true){
        this.editarVerTestsInstancias(true);
    }
}//GEN-LAST:event_editarTestsMenuItemActionPerformed

private void verTestsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verTestsMenuItemActionPerformed
// TODO add your handling code here:
    boolean res = listaTestsInstanciasVacia(true); 
    if(res==true){
        this.editarVerTestsInstancias(true);
    }
}//GEN-LAST:event_verTestsMenuItemActionPerformed

private void nuevoInstanciasMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoInstanciasMenuItemActionPerformed
// TODO add your handling code here:
    AddInstancesClasPropJDialog nuevoInst = new AddInstancesClasPropJDialog(this, true);
    nuevoInst.setLocationRelativeTo(this);
    nuevoInst.setVisible(true);
    nuevoInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
}//GEN-LAST:event_nuevoInstanciasMenuItemActionPerformed

private void importarInstanciasMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importarInstanciasMenuItemActionPerformed
// TODO add your handling code here:
    this.importarTestsInstancias(false);
}//GEN-LAST:event_importarInstanciasMenuItemActionPerformed

private void editarInstanciasMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarInstanciasMenuItemActionPerformed
// TODO add your handling code here:
    boolean res = listaTestsInstanciasVacia(false); 
    if(res==true){
        this.editarVerTestsInstancias(false);
    }
}//GEN-LAST:event_editarInstanciasMenuItemActionPerformed

private void verInstanciasMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verInstanciasMenuItemActionPerformed
// TODO add your handling code here:
    boolean res = listaTestsInstanciasVacia(false); 
    if(res==true){
        this.editarVerTestsInstancias(false);
    }
}//GEN-LAST:event_verInstanciasMenuItemActionPerformed

private void ejecutarTodosMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejecutarTodosMenuItemActionPerformed
// TODO add your handling code here:
    if(CollectionTest.getInstance().getScenariotest().size()>0){
        try{
            TreeResults.setTestSeleccionado("Todos los Tests");
            opMenu.ejecutarTodosLosTests();
            panelAviso.confirmAction("Tests ejecutados", this);
        }catch(ExceptionReadOntology ex){
            panelAviso.errorAction("Error ejecutando los tests",this);  
        }
    }else{
        panelAviso.errorAction("Su lista de tests está vacía",this);  
    }
}//GEN-LAST:event_ejecutarTodosMenuItemActionPerformed

private void guardarProyectoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarProyectoMenuItemActionPerformed
// TODO add your handling code here:
    try {
        boolean guardado = saveTest.saveProject(false,this.getCarpetaProyecto(),this.getNombreProyecto(),null);
        if(guardado==true){
            panelAviso.confirmAction("Proyecto guardado", this);
        }else{
            panelAviso.errorAction("Proyecto no guardado",this);  
        }
    } catch (FileNotFoundException ex) {
        panelAviso.errorAction("No se encontró el archivo especificado", this);
    }
}//GEN-LAST:event_guardarProyectoMenuItemActionPerformed

private void abrirProyectoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirProyectoMenuItemActionPerformed
// TODO add your handling code here: 
    AbrirProyectoJDialog abrirP = new AbrirProyectoJDialog(MainApplicationJFrame.getInstance(), true);
    try {
        utils = new FileChooserSelector();
        utils.fileChooser(true, true);
        decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(FileChooserSelector.getPathSelected())));
        collection = (CollectionTest) decoder.readObject();
        loadTest.prepareProject(collection);
        abrirP.setNamespaceText(CollectionTest.getInstance().getNamespace());
        abrirP.getUbicacionFisicaTextField().setText(CollectionTest.getInstance().getOntology());
        abrirP.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        abrirP.setLocationRelativeTo(MainApplicationJFrame.getInstance());
        abrirP.setVisible(true);
        if(abrirP.isProyectoCargado()==true){
            this.inicializarContadores();
            guardarProyectoComoMenuItem.setEnabled(true);
            guardarProyectoMenuItem.setEnabled(true);
            instanciasMenu.setEnabled(true);
            testsMenu.setEnabled(true);
            ejecutarMenu.setEnabled(true);
            contentTestsJPanel.add(panelTest,BorderLayout.CENTER);
            ControladorTests.getInstance().inicializarGuardados();
            ControladorTests.getInstance().inicializarSeleccionados();
            this.validate();
        }
    } catch (FileNotFoundException ex) {
        panelAviso.errorAction("No se encontró el archivo especificado", MainApplicationJFrame.getInstance());
    } catch (ClassCastException ex) {
        panelAviso.errorAction("Proyecto no válido", MainApplicationJFrame.getInstance());
    } catch (NoSuchElementException ex) {
        panelAviso.errorAction("Proyecto no válido", MainApplicationJFrame.getInstance());
    }
}//GEN-LAST:event_abrirProyectoMenuItemActionPerformed

private void selecTestMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selecTestMenuItemActionPerformed
// TODO add your handling code here:
    boolean res = listaTestsInstanciasVacia(true); 
    if(res==true){
        ListarTestsInstanciasJPanel listar = new ListarTestsInstanciasJPanel(CollectionTest.getInstance().getScenariotest(),CollectionTest.getInstance().getInstancias(),true);
        EjecutarTestJDialog ejecutarTest = new EjecutarTestJDialog(this,true,listar);
        ejecutarTest.setLocationRelativeTo(this);
        ejecutarTest.setVisible(true);
        ejecutarTest.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }
}//GEN-LAST:event_selecTestMenuItemActionPerformed

private void contentsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contentsMenuItemActionPerformed
// TODO add your handling code here:
    HelpJDialog help = new HelpJDialog(this,true);
    help.setLocationRelativeTo(this);
    help.setVisible(true);
}//GEN-LAST:event_contentsMenuItemActionPerformed
    
public void importarTestsInstancias(boolean impTest){
    ImportarTestsInstJDialog abrirTests = new ImportarTestsInstJDialog(this,true,impTest);
    abrirTests.setLocationRelativeTo(this);
    abrirTests.setVisible(true);
    abrirTests.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
}

public void editarVerTestsInstancias(boolean verEditTest){
    ListarTestsInstanciasJPanel listar = new ListarTestsInstanciasJPanel(CollectionTest.getInstance().getScenariotest(),CollectionTest.getInstance().getInstancias(),verEditTest);
    EditarVerTestInstanciasJDialog editarVerTestInst = new EditarVerTestInstanciasJDialog(this,true,listar,verEditTest);
    editarVerTestInst.setLocationRelativeTo(this);
    editarVerTestInst.setVisible(true);
    editarVerTestInst.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
}

public boolean listaTestsInstanciasVacia(boolean test){
    if(test==true){
        if(CollectionTest.getInstance().getScenariotest().size()==0){
            panelAviso.errorAction("Su lista de tests está vacía",this);  
        }else return true;
    }else{
        if(CollectionTest.getInstance().getInstancias().size()==0){
            panelAviso.errorAction("Su lista de instancias está vacía",this);  
        }else return true;
    }
    return false;
}

public void aniadirNuevoTest(ScenarioTest s){
    this.inicializarContadores();
    boolean res = false;
    int type = 0;
    if(s.getTipoTest().name().equals("INST") || s.getTipoTest().name().equals("SAT")){
        type = 0;
    }else if(s.getTipoTest().name().equals("RET") || s.getTipoTest().name().equals("CLAS")){
        type = 1;
    }else if(s.getTipoTest().name().equals("REAL")){
        type = 2;
    }else if(s.getTipoTest().name().equals("SPARQL")){
        type = 3;
    }
    if(controlador.algunTestSinGuardar()==false){
        controlador.prepararTest(s.getTipoTest().name());
        cargarTest(type,true,s);
    }else{
        int n = JOptionPane.showConfirmDialog(this, "¿Guardar los cambios realizados al test?", 
                "Guardar Tests",JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION){
                    controlador.prepararTest(s.getTipoTest().name());
                    res = obtenerPanelAGuardar();
                    cargarTest(type,res,s);
            }else{
                controlador.prepararTest(s.getTipoTest().name());
                cargarTest(type,true,s);
            }
    }
}

public boolean obtenerPanelAGuardar(){
    JPanel panel = getPanelActual();
    boolean res = false;
    if(panel instanceof TestSimpleInstSat){
        testInstSat = (TestSimpleInstSat) panel;
        res = testInstSat.guardarTest();
    }else if(panel instanceof TestSimpleRetClas){
        testRetClas = (TestSimpleRetClas) panel;
        res = testRetClas.guardarTest();
    }else if(panel instanceof TestSimpleReal){
        testReal = (TestSimpleReal) panel;
        res = testReal.guardarTest();
    }else if(panel instanceof AddSPARQLJPanel){
        testSparql = (AddSPARQLJPanel) panel;
        res = testSparql.guardarTest();
    }
    return res;
}

public void cargarTest(int type,boolean res,ScenarioTest s){
    if(type==0){
        if(res==true){
            testInstSat = new TestSimpleInstSat(s);
            panelTest.getTestsPanel().aniadirTest(testInstSat);
            setPanelActual(testInstSat);
        }
    }else if(type==1){
        if(res==true){
            testRetClas = new TestSimpleRetClas(s);
            panelTest.getTestsPanel().aniadirTest(testRetClas);
            setPanelActual(testRetClas);
        }
    }else if(type==2){
        if(res==true){
             testReal = new TestSimpleReal(s);
             panelTest.getTestsPanel().aniadirTest(testReal);
             setPanelActual(testReal);
        }
    }else if(type==3){
        if(res==true){
            testSparql = new AddSPARQLJPanel(s);
            panelTest.getTestsPanel().aniadirTest(testSparql);
            setPanelActual(testSparql);
        }
    }
}

public void inicializarContadores(){
    CreateInstancesJPanel.setContadorClas(0);
    CreateInstancesJPanel.setContadorProp(0);
    TestInstancesQueryJPanel.setContadorReal(0);
    TestInstancesTFJPanel.setContadorInstSat(0);
    TestInstancesTextAreaJPanel.setContadorRetClas(0);
}

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainApplicationJFrame main = MainApplicationJFrame.getInstance();
                main.setLocationRelativeTo(null);
                main.setVisible(true);
            }
        });
    }
    
    public String getCarpetaProyecto() {
        return carpetaProyecto;
    }

    public void setCarpetaProyecto(String carpetaProyecto) {
        this.carpetaProyecto = carpetaProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public boolean isProyectoGuardado() {
        return proyectoGuardado;
    }

    public void setProyectoGuardado(boolean proyectoGuardado) {
        this.proyectoGuardado = proyectoGuardado;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem abrirProyectoMenuItem;
    private javax.swing.JPanel contentTestsJPanel;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem editarInstanciasMenuItem;
    private javax.swing.JMenuItem editarTestsMenuItem;
    private javax.swing.JMenu ejecutarMenu;
    private javax.swing.JMenuItem ejecutarTodosMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem guardarProyectoComoMenuItem;
    private javax.swing.JMenuItem guardarProyectoMenuItem;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem importarInstanciasMenuItem;
    private javax.swing.JMenuItem importarTestsMenuItem;
    private javax.swing.JMenu instanciasMenu;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem nuevoInstanciasMenuItem;
    private javax.swing.JMenuItem nuevoProyectoMenuItem;
    private javax.swing.JMenuItem nuevoTestClasMenuItem;
    private javax.swing.JMenuItem nuevoTestInstMenuItem;
    private javax.swing.JMenuItem nuevoTestRealMenuItem;
    private javax.swing.JMenuItem nuevoTestRecMenuItem;
    private javax.swing.JMenuItem nuevoTestSatMenuItem;
    private javax.swing.JMenuItem nuevoTestSparqlMenuItem;
    private javax.swing.JMenuItem salirMenuItem;
    private javax.swing.JMenuItem selecTestMenuItem;
    private javax.swing.JMenu testsMenu;
    private javax.swing.JMenuItem verInstanciasMenuItem;
    private javax.swing.JMenuItem verTestsMenuItem;
    // End of variables declaration//GEN-END:variables

    public JPanel getPanelActual() {
        return panelActual;
    }

    public void setPanelActual(JPanel panelActual) {
        this.panelActual = panelActual;
    }

}
