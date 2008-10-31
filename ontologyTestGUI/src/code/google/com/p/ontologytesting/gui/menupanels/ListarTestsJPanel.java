/*
 * ListarTestsJPanel.java
 *
 * Created on 8 de octubre de 2008, 14:14
 */

package code.google.com.p.ontologytesting.gui.menupanels;


import code.google.com.p.ontologytesting.gui.auxiliarclasess.PopMenuInstances;
import code.google.com.p.ontologytesting.gui.auxiliarclasess.PopMenuTests;
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
    
    private ListarTestsJPanel() {
        initComponents();
        testSimplesList.setSelectedIndex(0);
        testSparqlList.setSelectedIndex(0);
        instanciasList.setSelectedIndex(0);
        popTest = new PopMenuTests();
        popInst = new PopMenuInstances();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        
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
        if(modeloSimples.size()>=0){
            testSimplesList.setSelectedIndex(0);
        }
        testSimplesList.setModel(modeloSimples);
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
        if(modeloSparql.size()>=0){
            testSparqlList.setSelectedIndex(0);
        }
        testSparqlList.setModel(modeloSparql);
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
        if(modeloInstancias.size()>=0){
            instanciasList.setSelectedIndex(0);
        }
        instanciasList.setModel(modeloInstancias);
        instanciasPanel.validate();
    }
    
    public void aniadirTreeResult(JScrollPane treeView){
        panelResultAux = new JPanel();
        panelResultAux.setLayout(new BoxLayout(panelResultAux, BoxLayout.Y_AXIS));
        if(resultsPanel.getComponentCount()>0){
            tabbedTestsPanel.add(panelResultAux);
            tabbedTestsPanel.setTitleAt(tabbedTestsPanel.getComponentCount()-1, "Resultados Ejecución");
            panelResultAux.add(treeView);
            panelResultAux.validate();
        }else{
            resultsPanel.add(treeView);
            resultsPanel.validate();
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
        resultsPanel = new javax.swing.JPanel();
        instanciasPanel = new javax.swing.JPanel();
        instanciasContentPanel = new javax.swing.JPanel();
        instanciasScrollPane = new javax.swing.JScrollPane();
        instanciasList = new javax.swing.JList();

        testSimplesList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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

        tabbedTestsPanel.addTab("Tests Simples", simplesPanel);

        testSparqlList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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

        tabbedTestsPanel.addTab("Tests Sparql", sparqlPanel);

        javax.swing.GroupLayout resultsPanelLayout = new javax.swing.GroupLayout(resultsPanel);
        resultsPanel.setLayout(resultsPanelLayout);
        resultsPanelLayout.setHorizontalGroup(
            resultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 171, Short.MAX_VALUE)
        );
        resultsPanelLayout.setVerticalGroup(
            resultsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        tabbedTestsPanel.addTab("Resultados Ejecución", resultsPanel);

        instanciasList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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

        tabbedTestsPanel.addTab("Instancias", instanciasPanel);

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
    }// </editor-fold>//GEN-END:initComponents

private void testSimplesListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_testSimplesListValueChanged
// TODO add your handling code here:
    JList lista = (JList) evt.getSource();//GEN-LAST:event_testSimplesListValueChanged
    if(modeloSimples.getSize()>0){
        popTest.setTestSelec(modeloSimples.get(lista.getLeadSelectionIndex()).toString());
        MouseListener popupListener = new PopupListener(popTest.createPopupMenuForTests());
        lista.addMouseListener(popupListener);
    }
}

private void testSparqlListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_testSparqlListValueChanged
// TODO add your handling code here:
    JList lista = (JList) evt.getSource();//GEN-LAST:event_testSparqlListValueChanged
    if(modeloSparql.getSize()>0){
        popTest.setTestSelec(modeloSparql.get(lista.getLeadSelectionIndex()).toString());
        MouseListener popupListener = new PopupListener(popTest.createPopupMenuForTests());
        lista.addMouseListener(popupListener);
    }
}

private void instanciasListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_instanciasListValueChanged
// TODO add your handling code here:
    JList lista = (JList) evt.getSource();//GEN-LAST:event_instanciasListValueChanged
    if(modeloInstancias.getSize()>0){
        popInst.setInstSelec(modeloInstancias.get(lista.getLeadSelectionIndex()).toString());
        MouseListener popupListener = new PopupListener(popInst.createPopupMenuForInstances());
        lista.addMouseListener(popupListener);
    }
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel instanciasContentPanel;
    private javax.swing.JList instanciasList;
    private javax.swing.JPanel instanciasPanel;
    private javax.swing.JScrollPane instanciasScrollPane;
    private javax.swing.JScrollPane listSparqlScrollPane;
    private javax.swing.JScrollPane listTestSimpleScrollPane;
    private javax.swing.JPanel resultsPanel;
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
