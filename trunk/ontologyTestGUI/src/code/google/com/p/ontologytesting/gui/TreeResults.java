
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.gui;

/**
 *
 * @author sara.garcia
 */
import code.google.com.p.ontologytesting.model.OntologyTestFailure;
import code.google.com.p.ontologytesting.model.OntologyTestResult;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ListIterator;

public class TreeResults extends JFrame {

    public static OntologyTestFailure getActualScenarioFailure() {
        return actualScenarioFailure;
    }
    public static void setActualScenarioFailure(OntologyTestFailure aActualScenarioFailure) {
        actualScenarioFailure = aActualScenarioFailure;
    }    
    private JEditorPane htmlPane;
    private static boolean DEBUG = false;
    private DefaultMutableTreeNode category = null;
    private DefaultMutableTreeNode test = null;
    private static OntologyTestFailure actualScenarioFailure;

    public TreeResults(final OntologyTestResult testresult) {

        //Create the nodes.
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Resultado de sus Tests");
        createNodes(top,testresult);

        //Create a tree that allows one selection at a time.
        JTree tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        //Listen for when the selection changes.
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                                   (e.getPath().getLastPathComponent());
                Object nodeInfo = node.getUserObject();
                if (node.isLeaf()) {
                    String test = (String) nodeInfo;
                    displaySimpleTest(test,testresult);
                } else {
                }
                if (DEBUG) {
                    System.out.println(nodeInfo.toString());
                }
            }
        });

        //Create the scroll pane and add the tree to it. 
        JScrollPane treeView = new JScrollPane(tree);

        //Create the HTML viewing pane.
        htmlPane = new JEditorPane();
        htmlPane.setEditable(false);
        JScrollPane htmlView = new JScrollPane(htmlPane);

        //Add the scroll panes to a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(treeView);
        splitPane.setBottomComponent(htmlView);

        Dimension minimumSize = new Dimension(100, 50);
        htmlView.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(100); //XXX: ignored in some releases
                                           //of Swing. bug 4101306
        //workaround for bug 4101306:
        //treeView.setPreferredSize(new Dimension(100, 100)); 

        splitPane.setPreferredSize(new Dimension(500, 300));

        //Add the split pane to this frame
        getContentPane().add(splitPane);
    }

    private void displaySimpleTest(String test, OntologyTestResult testresult) {
        
        ListIterator liFailures,liSparql;
        ArrayList<OntologyTestFailure> failures = testresult.getOntologyTestFailureQuery();
        ArrayList<OntologyTestFailure> failuresSparql = testresult.getOntologyTestFailureSparql();
        liFailures = failures.listIterator();
        liSparql = failuresSparql.listIterator();
        if(liFailures.hasNext()){
            while(liFailures.hasNext()){
                OntologyTestFailure ontoFailure = (OntologyTestFailure) liFailures.next();
                if(ontoFailure.getTestNameUsuario().equals(test)){
                    htmlPane.setText(ontoFailure.toString());  
                }
        
            }
        }
    }
    
    private void createNodes(DefaultMutableTreeNode top, OntologyTestResult testresult) {
        
        ListIterator liFailures,liSparql;
        ArrayList<OntologyTestFailure> failures = testresult.getOntologyTestFailureQuery();
        ArrayList<OntologyTestFailure> failuresSparql = testresult.getOntologyTestFailureSparql();
        liFailures = failures.listIterator();
        liSparql = failuresSparql.listIterator();
        if(liFailures.hasNext()){
        while(liFailures.hasNext()){
            int var=0;
            OntologyTestFailure otf = (OntologyTestFailure) liFailures.next();
            
            if(otf.getTestName().equals("Instanciación")){
                if(var==0){
                    var=1;
                    category = new DefaultMutableTreeNode("Tests de Instanciación");
                    top.add(category);
                    test = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    category.add(test);
                }else{
                    test = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    category.add(test);
                }
            }else if(otf.getTestName().equals("Retrieval")){
                if(var==0){
                    var=1;
                    category = new DefaultMutableTreeNode("Tests de Retrieval");
                    top.add(category);
                    test = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    category.add(test);
                }else{
                    test = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    category.add(test);
                }
            }else if(otf.getTestName().equals("Realización")){
                if(var==0){
                    var=1;
                    category = new DefaultMutableTreeNode("Tests de Realización");
                    top.add(category);
                    test = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    category.add(test);
                }else{
                    test = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    category.add(test);
                }
            }else if(otf.getTestName().equals("Clasificación")){
                if(var==0){
                    var=1;
                    category = new DefaultMutableTreeNode("Tests de Clasificación");
                    top.add(category);
                    test = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    category.add(test);
                }else{
                    test = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    category.add(test);
                }
            }else if(otf.getTestName().equals("Satisfactibilidad")){
                if(var==0){
                    var=1;
                    category = new DefaultMutableTreeNode("Tests de Satisfactibilidad");
                    top.add(category);
                    test = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    category.add(test);
                }else{
                    test = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    category.add(test);
                }
            }
        }
        }else{
            System.out.println("No se han producido errores.");
        }
        
        /*if(liSparql.hasNext()){
          System.out.println("De las pruebas introducidas han fallado las siguientes:");
        while(liSparql.hasNext()){
                OntologyTestFailure otf = (OntologyTestFailure) liSparql.next();
                System.out.println("De la query introducida " +otf.getfSparqlQuery());
                System.out.println("Se esperaba obtener : " +otf.getfResultSparqlExpected());
                System.out.println("Pero se obtuvo: " +otf.getResultSparqlQueryObtenido());      
            }
        }else{
            System.out.println("No se han producido errores.");
        } */
    }

    public static void main(String[] args) {
        OntologyTestResult testresult = new OntologyTestResult();
        JFrame frame = new TreeResults(testresult);
 
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {System.exit(0);} 
        });  
 
        frame.pack();
        frame.setVisible(true);
    }
}