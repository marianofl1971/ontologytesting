/*
 * ListAndResultsJPanel.java
 *
 * Created on 25 de septiembre de 2008, 13:03
 */

package code.google.com.p.ontologytesting.gui;

import code.google.com.p.ontologytesting.gui.menupanels.ListarTestsJPanel;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author  sara.garcia
 */
public class ListAndResultsJPanel extends javax.swing.JPanel {

    private ResultTestJPanel resultPanel;
    private ListAndTestsJPanel testsPanel;
    private static ListAndResultsJPanel listAndResult = null;
    private ListarTestsJPanel lista;
 
    private ListAndResultsJPanel() {
        initComponents();
        this.setSize(new Dimension(895,720));
        listAndTestsPanel.setLayout(new BorderLayout());
        resultTestsPanel.setLayout(new BorderLayout());
        resultPanel = ResultTestJPanel.getInstance();
        testsPanel = ListAndTestsJPanel.getInstance();
        lista = ListarTestsJPanel.getInstance();
        testsPanel.aniadirLista(lista);
        listAndTestsPanel.add(testsPanel,BorderLayout.CENTER);
        resultTestsPanel.add(resultPanel,BorderLayout.CENTER);
        this.splitPane.setDividerLocation(500);
    }
 
    private synchronized static void createListAndResultPanel() {
        if (listAndResult == null) { 
            listAndResult = new ListAndResultsJPanel();
        }
    }

    public static ListAndResultsJPanel getInstance() {
        if (listAndResult == null) createListAndResultPanel();
        return listAndResult;
    }
    
    public ListAndTestsJPanel getTestsPanel() {
        return this.testsPanel;
    }
    
    public void mostrarResultado(JScrollPane resultado){
        resultPanel.aniadirResultado(resultado);
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
        listAndTestsPanel = new javax.swing.JPanel();
        resultTestsPanel = new javax.swing.JPanel();

        splitPane.setDividerLocation(500);
        splitPane.setDividerSize(7);
        splitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        splitPane.setOneTouchExpandable(true);

        listAndTestsPanel.setPreferredSize(new java.awt.Dimension(895, 500));

        javax.swing.GroupLayout listAndTestsPanelLayout = new javax.swing.GroupLayout(listAndTestsPanel);
        listAndTestsPanel.setLayout(listAndTestsPanelLayout);
        listAndTestsPanelLayout.setHorizontalGroup(
            listAndTestsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 643, Short.MAX_VALUE)
        );
        listAndTestsPanelLayout.setVerticalGroup(
            listAndTestsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 473, Short.MAX_VALUE)
        );

        splitPane.setLeftComponent(listAndTestsPanel);

        javax.swing.GroupLayout resultTestsPanelLayout = new javax.swing.GroupLayout(resultTestsPanel);
        resultTestsPanel.setLayout(resultTestsPanelLayout);
        resultTestsPanelLayout.setHorizontalGroup(
            resultTestsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 643, Short.MAX_VALUE)
        );
        resultTestsPanelLayout.setVerticalGroup(
            resultTestsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        splitPane.setRightComponent(resultTestsPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel listAndTestsPanel;
    private javax.swing.JPanel resultTestsPanel;
    private javax.swing.JSplitPane splitPane;
    // End of variables declaration//GEN-END:variables

}
