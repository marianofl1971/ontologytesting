/*
 * listAndTestsJPanel.java
 *
 * Created on 25 de septiembre de 2008, 11:39
 */

package code.google.com.p.ontologytesting.gui;

import code.google.com.p.ontologytesting.gui.auxiliarclasess.ButtonTabComponent;
import code.google.com.p.ontologytesting.gui.instances.AddInstancesClasPropJPanel;
import code.google.com.p.ontologytesting.gui.menupanels.ListarTestsJPanel;
import code.google.com.p.ontologytesting.gui.tests.AddSPARQLJPanel;
import code.google.com.p.ontologytesting.gui.tests.TestSimpleInstSat;
import code.google.com.p.ontologytesting.gui.tests.TestSimpleReal;
import code.google.com.p.ontologytesting.gui.tests.TestSimpleRetClas;
import code.google.com.p.ontologytesting.model.Instancias;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author  sara.garcia
 */
public class ListAndTestsJPanel extends javax.swing.JPanel {

    private static ListAndTestsJPanel listAndTest = null;
 
    private ListAndTestsJPanel() {
        initComponents();
        testsScrollPane.getVerticalScrollBar().setUnitIncrement(5);
        this.listTestPanel.setLayout(new BorderLayout());
        this.splitPane.setDividerLocation(235);
    }
 
    private synchronized static void createListAndTestPanel() {
        if (listAndTest == null) { 
            listAndTest = new ListAndTestsJPanel();
        }
    }
 
    public static ListAndTestsJPanel getInstance() {
        if (listAndTest == null) createListAndTestPanel();
        return listAndTest;
    }
    
    public void aniadirTest(JPanel panel, String name){
        getContentTabbedPane().add(panel);
        getContentTabbedPane().setTitleAt(getContentTabbedPane().getTabCount()-1, name);
        initTabComponent(getContentTabbedPane().getTabCount()-1);
        getContentTabbedPane().setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        getContentTabbedPane().setSelectedIndex(getContentTabbedPane().getTabCount()-1);
        getContentTabbedPane().validate();    
    }
    
    public boolean guardarTodosTests(){
        boolean continuar=false;
        if(getContentTabbedPane().getTabCount()>0){
            for(int i=0;i<getContentTabbedPane().getTabCount();i++){
                JPanel panel = (JPanel) getContentTabbedPane().getComponentAt(i);
                if(panel instanceof TestSimpleInstSat){
                    TestSimpleInstSat testSimpleInstSat = (TestSimpleInstSat)panel;
                    continuar = testSimpleInstSat.guardarTest();
                }else if(panel instanceof TestSimpleReal){
                    TestSimpleReal testSimpleReal = (TestSimpleReal)panel;
                    continuar = testSimpleReal.guardarTest();
                }else if(panel instanceof TestSimpleRetClas){
                    TestSimpleRetClas testSimpleRetClas = (TestSimpleRetClas)panel;
                    continuar = testSimpleRetClas.guardarTest();
                }else if(panel instanceof AddSPARQLJPanel){
                    AddSPARQLJPanel testSparql = (AddSPARQLJPanel)panel;
                    continuar = testSparql.guardarTest();
                }else if(panel instanceof AddInstancesClasPropJPanel){
                    AddInstancesClasPropJPanel instancias = (AddInstancesClasPropJPanel)panel;
                    continuar = instancias.prepararInstancias(true);
                }
            }
        }else return true;
        return continuar;
    }
    
    public void actualizarTestsAbiertos(String nombre, Instancias instancias){
        if(getContentTabbedPane().getTabCount()>0){
            for(int i=0;i<getContentTabbedPane().getTabCount();i++){
                JPanel panel = (JPanel) getContentTabbedPane().getComponentAt(i);
                if(panel instanceof TestSimpleInstSat){
                    TestSimpleInstSat testSimpleInstSat = (TestSimpleInstSat)panel;
                    if(testSimpleInstSat.getDescPanel().getNombreTextField().equals(nombre)){
                        testSimpleInstSat.getScenario().setInstancias(instancias);
                    }
                }else if(panel instanceof TestSimpleReal){
                    TestSimpleReal testSimpleReal = (TestSimpleReal)panel;
                    if(testSimpleReal.getDescPanel().getNombreTextField().equals(nombre)){
                        testSimpleReal.getScenario().setInstancias(instancias);
                    }
                }else if(panel instanceof TestSimpleRetClas){
                    TestSimpleRetClas testSimpleRetClas = (TestSimpleRetClas)panel;
                    if(testSimpleRetClas.getDescPanel().getNombreTextField().equals(nombre)){
                        testSimpleRetClas.getScenario().setInstancias(instancias);
                    }
                }else if(panel instanceof AddSPARQLJPanel){
                    AddSPARQLJPanel testSparql = (AddSPARQLJPanel)panel;
                    if(testSparql.getTestNameTextField().equals(nombre)){
                        testSparql.getScenario().setInstancias(instancias);
                    }
                }
            }
        }
    }
    
    public void aniadirNombre(String name){
        getContentTabbedPane().setTitleAt(this.contentTabbedPane.getSelectedIndex(), name);
    }
    
    public String obtenerTipo(int tab){
        int index = getContentTabbedPane().getSelectedIndex();
        JPanel panel = (JPanel) getContentTabbedPane().getComponentAt(index);
        if(panel instanceof TestSimpleInstSat){
            TestSimpleInstSat testSimpleInstSat = (TestSimpleInstSat)panel;
            return testSimpleInstSat.getScenario().getTipoTest().name();
        }else if(panel instanceof TestSimpleReal){
            TestSimpleReal testSimpleReal = (TestSimpleReal)panel;
            return testSimpleReal.getScenario().getTipoTest().name();
        }else if(panel instanceof TestSimpleRetClas){
            TestSimpleRetClas testSimpleRetClas = (TestSimpleRetClas)panel;
            return testSimpleRetClas.getScenario().getTipoTest().name();
        }else{
            return "Sin Nombre";
        }
    }
    
    public void borrarTest(String name){
        int tabCount = getContentTabbedPane().getTabCount();
        for(int i=0;i<tabCount;i++){
            String title = getContentTabbedPane().getTitleAt(i);
            if(title.equals(name)){
                getContentTabbedPane().remove(i);
                tabCount--;
                i--;
            }
        }
    }
    
    public void borrarInstancias(String name){
        int tabCount = getContentTabbedPane().getTabCount();
        for(int i=0;i<tabCount;i++){
            String title = getContentTabbedPane().getTitleAt(i);
            if(title.equals(name)){
                getContentTabbedPane().remove(i);
                tabCount--;
                i--;
            }
        }
    }
    
    public void eliminarTests(){
        getContentTabbedPane().removeAll();
        getContentTabbedPane().validate();
    }
    
    private void initTabComponent(int i) {
        getContentTabbedPane().setTabComponentAt(i,new ButtonTabComponent(getContentTabbedPane(),false,false));
    }
    
    public void aniadirLista(ListarTestsJPanel lista){
         this.listTestPanel.add(lista,BorderLayout.CENTER); 
         this.validate();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitPane = new javax.swing.JSplitPane();
        listTestPanel = new javax.swing.JPanel();
        testsScrollPane = new javax.swing.JScrollPane();
        testsPanel = new javax.swing.JPanel();
        contentTabbedPane = new javax.swing.JTabbedPane();

        setLayout(new java.awt.BorderLayout());

        splitPane.setDividerSize(7);
        splitPane.setOneTouchExpandable(true);

        javax.swing.GroupLayout listTestPanelLayout = new javax.swing.GroupLayout(listTestPanel);
        listTestPanel.setLayout(listTestPanelLayout);
        listTestPanelLayout.setHorizontalGroup(
            listTestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        listTestPanelLayout.setVerticalGroup(
            listTestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        splitPane.setLeftComponent(listTestPanel);

        testsPanel.setLayout(new java.awt.BorderLayout());
        testsPanel.add(contentTabbedPane, java.awt.BorderLayout.CENTER);

        testsScrollPane.setViewportView(testsPanel);

        splitPane.setRightComponent(testsScrollPane);

        add(splitPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane contentTabbedPane;
    private javax.swing.JPanel listTestPanel;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JPanel testsPanel;
    private javax.swing.JScrollPane testsScrollPane;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JTabbedPane getContentTabbedPane() {
        return contentTabbedPane;
    }


}
