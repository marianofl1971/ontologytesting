/*
 * ListarTestsJPanel.java
 *
 * Created on 8 de octubre de 2008, 14:14
 */

package code.google.com.p.ontologytesting.gui.menupanels;


import code.google.com.p.ontologytesting.gui.Configuration;
import code.google.com.p.ontologytesting.gui.MainApplicationJFrame;
import code.google.com.p.ontologytesting.gui.ResultTestJPanel;
import code.google.com.p.ontologytesting.gui.auxiliarclasess.*;
import code.google.com.p.ontologytesting.model.CollectionTest;
import code.google.com.p.ontologytesting.model.Instancias;
import code.google.com.p.ontologytesting.model.ScenarioTest;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.util.Locale;
/**
 *
 * @author  sara.garcia
 */
public class ListarTestsJPanel extends javax.swing.JPanel{

    private DefaultListModel modeloSimples,modeloSparql,modeloInstancias;
    private PopMenuTests popTest;
    private PopMenuInstances popInst;
    private static ListarTestsJPanel listTests = null;
    private JPanel panelResultAux;
    private OpcionesMenu opMenu = new OpcionesMenu();
    private ScenarioTest s = new ScenarioTest();
    private Instancias instancias = new Instancias();
    private ControladorTests controlador = ControladorTests.getInstance();
    
    private ListarTestsJPanel() {
        initComponents();
        popTest = new PopMenuTests();
        popInst = new PopMenuInstances();    
        tabbedTestsPanel.setSelectedIndex(0);
        testSparqlList.setSelectedIndex(0);
        instanciasList.setSelectedIndex(0);
        testSimplesList.setSelectedIndex(0);
    }
 
    private synchronized static void createListAndResultPanel() {
        if (listTests == null) { 
            listTests = new ListarTestsJPanel();
        }
    }

    public static ListarTestsJPanel getInstance() {
        if (listTests == null) createListAndResultPanel();
        return listTests;
    }

    public void aniadirTestSimple(List<ScenarioTest> scenario) {
        modeloSimples = new DefaultListModel();
        for(int i=0;i<scenario.size();i++){
            if(!scenario.get(i).getTipoTest().name().equals("SPARQL")){
                modeloSimples.addElement(scenario.get(i).getNombre()); 
            }
        }
        ArrayList<ScenarioTest> scenSimple = CollectionTest.getInstance().getScenariotest();
        if(scenSimple.size()>0){
            for(int i=0;i<scenSimple.size();i++){
                if(!scenSimple.get(i).getTipoTest().name().equals("SPARQL")){
                    if(!modeloSimples.contains(scenSimple.get(i).getNombre())){
                        modeloSimples.addElement(scenSimple.get(i).getNombre()); 
                    }
                }
            }
        }
        testSimplesList.setModel(modeloSimples);
        if(modeloSimples.size()>=0){
            testSimplesList.setSelectedIndex(0);
        }
        simplesPanel.validate();
    }

    public void aniadirTestSparql(List<ScenarioTest> scenario){ 
        modeloSparql = new DefaultListModel();
        for(int i=0;i<scenario.size();i++){
            if(scenario.get(i).getTipoTest().name().equals("SPARQL")){
                modeloSparql.addElement(scenario.get(i).getNombre());  
            }
        }
        ArrayList<ScenarioTest> scenSparql = CollectionTest.getInstance().getScenariotest();
        if(scenSparql.size()>0){
            for(int i=0;i<scenSparql.size();i++){ 
                if(scenSparql.get(i).getTipoTest().name().equals("SPARQL")){
                    if(!modeloSparql.contains(scenSparql.get(i).getNombre())){
                        modeloSparql.addElement(scenSparql.get(i).getNombre()); 
                    }
                }
            }
        }
        testSparqlList.setModel(modeloSparql);
        if(modeloSparql.size()>=0){
            testSparqlList.setSelectedIndex(0);
        }
        sparqlPanel.validate();
    }
  
    public DefaultListModel actualizarModeloInstancias(DefaultListModel modelo){
        ArrayList<Instancias> inst = CollectionTest.getInstance().getInstancias();
        if(inst.size()>0){
            for(int i=0;i<inst.size();i++){
                if(!modelo.contains(inst.get(i).getNombre())){
                    modelo.addElement(inst.get(i).getNombre()); 
                }
            }
        }
        return modelo;
    }
    
    public void aniadirInstancias(List<Instancias> instancias){ 
        modeloInstancias = new DefaultListModel();
        for(int i=0;i<instancias.size();i++){
            modeloInstancias.addElement(instancias.get(i).getNombre());  
        } 
        modeloInstancias = this.actualizarModeloInstancias(modeloInstancias);
        instanciasList.setModel(modeloInstancias);
        if(modeloInstancias.size()>=0){
            instanciasList.setSelectedIndex(0);
        }
        instanciasPanel.validate();
    }
    
    public void aniadirTreeResult(JScrollPane treeView){
        panelResultAux = new JPanel();
        panelResultAux.setLayout(new BoxLayout(panelResultAux, BoxLayout.Y_AXIS));
        tabbedTestsPanel.add(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Ejecución:_")+TreeResults.getTestSeleccionado(),panelResultAux);
        initTabComponent(tabbedTestsPanel.getTabCount()-1);
        panelResultAux.add(treeView);
        tabbedTestsPanel.setSelectedIndex(tabbedTestsPanel.getTabCount()-1);
        tabbedTestsPanel.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        panelResultAux.validate();
    }
    
    private void initTabComponent(int i) {
        tabbedTestsPanel.setTabComponentAt(i,new ButtonTabComponent(tabbedTestsPanel,false,true));
    }
    
    public void eliminarDatosGuardados(){
        modeloSimples = new DefaultListModel();
        testSimplesList.setModel(modeloSimples);
        simplesPanel.validate();
        modeloSparql = new DefaultListModel();
        testSparqlList.setModel(modeloSparql);
        sparqlPanel.validate();
        modeloInstancias = new DefaultListModel();
        instanciasList.setModel(modeloInstancias);
        instanciasPanel.validate();
        int tabCount = tabbedTestsPanel.getTabCount();
        for(int i=tabCount-1; i>2; i--){
            tabbedTestsPanel.remove(i);
            ResultTestJPanel.getInstance().eliminarResultados(i-3);  
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        tabbedTestsPanel = new javax.swing.JTabbedPane();
        simplesPanel = new javax.swing.JPanel();
        testSimpleListPanel = new javax.swing.JPanel();
        listTestSimpleScrollPane = new javax.swing.JScrollPane();
        testSimplesList = new javax.swing.JList();
        sparqlPanel = new javax.swing.JPanel();
        testSparqlPanel = new javax.swing.JPanel();
        listSparqlScrollPane = new javax.swing.JScrollPane();
        testSparqlList = new javax.swing.JList();
        instanciasPanel = new javax.swing.JPanel();
        instanciasContentPanel = new javax.swing.JPanel();
        instanciasScrollPane = new javax.swing.JScrollPane();
        instanciasList = new javax.swing.JList();

        tabbedTestsPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabbedTestsPanelMouseClicked(evt);
            }
        });

        testSimplesList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        testSimplesList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                testSimplesListMouseClicked(evt);
            }
        });
        testSimplesList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                testSimplesListValueChanged(evt);
            }
        });
        listTestSimpleScrollPane.setViewportView(testSimplesList);

        javax.swing.GroupLayout testSimpleListPanelLayout = new javax.swing.GroupLayout(testSimpleListPanel);
        testSimpleListPanel.setLayout(testSimpleListPanelLayout);
        testSimpleListPanelLayout.setHorizontalGroup(
            testSimpleListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listTestSimpleScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
        );
        testSimpleListPanelLayout.setVerticalGroup(
            testSimpleListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listTestSimpleScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout simplesPanelLayout = new javax.swing.GroupLayout(simplesPanel);
        simplesPanel.setLayout(simplesPanelLayout);
        simplesPanelLayout.setHorizontalGroup(
            simplesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 171, Short.MAX_VALUE)
            .addGroup(simplesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(testSimpleListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        simplesPanelLayout.setVerticalGroup(
            simplesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
            .addGroup(simplesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(testSimpleListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))); // NOI18N
        tabbedTestsPanel.addTab(bundle.getString("Tests_Simples"), simplesPanel); // NOI18N

        testSparqlList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        testSparqlList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                testSparqlListMouseClicked(evt);
            }
        });
        testSparqlList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                testSparqlListValueChanged(evt);
            }
        });
        listSparqlScrollPane.setViewportView(testSparqlList);

        javax.swing.GroupLayout testSparqlPanelLayout = new javax.swing.GroupLayout(testSparqlPanel);
        testSparqlPanel.setLayout(testSparqlPanelLayout);
        testSparqlPanelLayout.setHorizontalGroup(
            testSparqlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listSparqlScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
        );
        testSparqlPanelLayout.setVerticalGroup(
            testSparqlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listSparqlScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout sparqlPanelLayout = new javax.swing.GroupLayout(sparqlPanel);
        sparqlPanel.setLayout(sparqlPanelLayout);
        sparqlPanelLayout.setHorizontalGroup(
            sparqlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(testSparqlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        sparqlPanelLayout.setVerticalGroup(
            sparqlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(testSparqlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabbedTestsPanel.addTab(bundle.getString("Tests_Sparql"), sparqlPanel); // NOI18N

        instanciasList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        instanciasList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                instanciasListMouseClicked(evt);
            }
        });
        instanciasList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                instanciasListValueChanged(evt);
            }
        });
        instanciasScrollPane.setViewportView(instanciasList);

        javax.swing.GroupLayout instanciasContentPanelLayout = new javax.swing.GroupLayout(instanciasContentPanel);
        instanciasContentPanel.setLayout(instanciasContentPanelLayout);
        instanciasContentPanelLayout.setHorizontalGroup(
            instanciasContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(instanciasScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
        );
        instanciasContentPanelLayout.setVerticalGroup(
            instanciasContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(instanciasScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout instanciasPanelLayout = new javax.swing.GroupLayout(instanciasPanel);
        instanciasPanel.setLayout(instanciasPanelLayout);
        instanciasPanelLayout.setHorizontalGroup(
            instanciasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(instanciasContentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        instanciasPanelLayout.setVerticalGroup(
            instanciasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(instanciasContentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tabbedTestsPanel.addTab(bundle.getString("Instancias"), instanciasPanel); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedTestsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedTestsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
        );
    }

private void testSimplesListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_testSimplesListValueChanged
// TODO add your handling code here:
    JList lista = (JList) evt.getSource();
    if(lista.getSize()!=null && modeloSimples.getSize()>0){
        if(lista.getLeadSelectionIndex()<modeloSimples.getSize()){
            popTest.setTestSelec(modeloSimples.get(lista.getLeadSelectionIndex()).toString());
            MouseListener popupListener = new PopupListener(popTest.createPopupMenuForTests());
            lista.addMouseListener(popupListener);
        }
    }
}//GEN-LAST:event_testSimplesListValueChanged

private void testSparqlListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_testSparqlListValueChanged
// TODO add your handling code here:
    JList lista = (JList) evt.getSource();
    if(lista.getSize()!=null && modeloSparql.getSize()>0){
        if(lista.getLeadSelectionIndex()<modeloSparql.getSize()){
            popTest.setTestSelec(modeloSparql.get(lista.getLeadSelectionIndex()).toString());
            MouseListener popupListener = new PopupListener(popTest.createPopupMenuForTests());
            lista.addMouseListener(popupListener);
        }
    }
}//GEN-LAST:event_testSparqlListValueChanged

private void instanciasListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_instanciasListValueChanged
// TODO add your handling code here:
    JList lista = (JList) evt.getSource();
    if(lista.getSize()!=null && modeloInstancias.getSize()>0){
        if(lista.getLeadSelectionIndex()<modeloInstancias.getSize()){
            popInst.setInstSelec(modeloInstancias.get(lista.getLeadSelectionIndex()).toString());
            MouseListener popupListener = new PopupListener(popInst.createPopupMenuForInstances());
            lista.addMouseListener(popupListener);
        }
    }
}//GEN-LAST:event_instanciasListValueChanged

private void tabbedTestsPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabbedTestsPanelMouseClicked
// TODO add your handling code here:
    int index = tabbedTestsPanel.getSelectedIndex();
    testSparqlList.clearSelection();
    instanciasList.clearSelection();
    testSimplesList.clearSelection();
    if(index==0){
        if(modeloSimples!=null){
            if(modeloSimples.size()>=0){
                testSimplesList.setSelectedIndex(0);
            }
        }
    }else if(index==1){
        if(modeloSparql!=null){
            if(modeloSparql.size()>=0){
                testSparqlList.setSelectedIndex(0);
            }
        }
    }else if(index==2){
        if(modeloInstancias!=null){
            if(modeloInstancias.size()>=0){
                instanciasList.setSelectedIndex(0);
            }
        }
    }
}//GEN-LAST:event_tabbedTestsPanelMouseClicked

private void testSimplesListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_testSimplesListMouseClicked
// TODO add your handling code here:
    JList lista = (JList) evt.getSource();
    if(lista.getSelectedValue()!=null){
        if(lista.getSize()!=null && modeloSimples.size()>0){
            ScenarioTest scenario = s.buscarScenario(CollectionTest.getInstance().getScenariotest(), modeloSimples.get(lista.getLeadSelectionIndex()).toString());
            if(evt.getClickCount()==2){
                opMenu.editarTest(scenario);
                controlador.prepararTest(scenario.getTipoTest().name());
            }
        }
    }
}//GEN-LAST:event_testSimplesListMouseClicked

private void testSparqlListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_testSparqlListMouseClicked
// TODO add your handling code here:
    JList lista = (JList) evt.getSource();
    if(lista.getSelectedValue()!=null){
        if(lista.getSize()!=null && modeloSparql.size()>0){
            ScenarioTest scenario = s.buscarScenario(CollectionTest.getInstance().getScenariotest(), modeloSparql.get(lista.getLeadSelectionIndex()).toString());
            if(evt.getClickCount()==2){
                opMenu.editarTest(scenario);
                controlador.prepararTest(scenario.getTipoTest().name());  
            }
        }
    }
}//GEN-LAST:event_testSparqlListMouseClicked

private void instanciasListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_instanciasListMouseClicked
// TODO add your handling code here:
    JList lista = (JList) evt.getSource();
    if(lista.getSelectedValue()!=null){
        if(lista.getSize()!=null && modeloInstancias.size()>0){
            Instancias inst = instancias.buscarInstancias(CollectionTest.getInstance().getInstancias(),modeloInstancias.get(lista.getLeadSelectionIndex()).toString());
            if(evt.getClickCount()==2){
                MainApplicationJFrame.getInstance().cargarInstancia(inst,inst.getNombre());
            }
        }
    }
}//GEN-LAST:event_instanciasListMouseClicked
    
    public JTabbedPane getTabbedTestPane() {
        return tabbedTestsPanel;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel instanciasContentPanel;
    private javax.swing.JList instanciasList;
    private javax.swing.JPanel instanciasPanel;
    private javax.swing.JScrollPane instanciasScrollPane;
    private javax.swing.JScrollPane listSparqlScrollPane;
    private javax.swing.JScrollPane listTestSimpleScrollPane;
    private javax.swing.JPanel simplesPanel;
    private javax.swing.JPanel sparqlPanel;
    private javax.swing.JTabbedPane tabbedTestsPanel;
    private javax.swing.JPanel testSimpleListPanel;
    private javax.swing.JList testSimplesList;
    private javax.swing.JList testSparqlList;
    private javax.swing.JPanel testSparqlPanel;
    // End of variables declaration//GEN-END:variables

    static class PopupListener extends MouseAdapter {
        JPopupMenu popup;

        PopupListener(JPopupMenu popupMenu) {
            popup = popupMenu;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popup.show(e.getComponent(),
                           e.getX(), e.getY());
            }
        }
    }

}
