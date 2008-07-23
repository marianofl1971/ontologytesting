
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
import code.google.com.p.ontologytesting.model.OntologyTestPassed;
import code.google.com.p.ontologytesting.model.OntologyTestResult;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import java.awt.*;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.tree.DefaultTreeCellRenderer;

public class TreeResults extends JPanel {

    public static OntologyTestFailure getActualScenarioFailure() {
        return actualScenarioFailure;
    }
    public static void setActualScenarioFailure(OntologyTestFailure aActualScenarioFailure) {
        actualScenarioFailure = aActualScenarioFailure;
    }    
    private JEditorPane htmlPane;
    private static boolean DEBUG = false;
    private DefaultMutableTreeNode inst=null, ret=null, clas=null, sat=null, 
            real=null, sparql=null;
    private DefaultMutableTreeNode inst_hijo=null, ret_hijo=null, clas_hijo=null, 
            sat_hijo=null, real_hijo=null, sparql_hijo=null;
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
        
        tree.setCellRenderer(new DefaultTreeCellRenderer()
        {
            @Override
             public Component getTreeCellRendererComponent(JTree pTree,
                 Object pValue, boolean pIsSelected, boolean pIsExpanded,
                 boolean pIsLeaf, int pRow, boolean pHasFocus)
             {
	    DefaultMutableTreeNode node = (DefaultMutableTreeNode)pValue;
	    super.getTreeCellRendererComponent(pTree, pValue, pIsSelected,
                     pIsExpanded, pIsLeaf, pRow, pHasFocus);
                 if (node.isRoot())
                    setBackgroundSelectionColor(Color.red);
                 else if (node.getChildCount() > 0)
                    setBackgroundSelectionColor(Color.yellow);
                 else if (pIsLeaf)
                    setBackgroundSelectionColor(Color.green);
                 return (this);
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

        Dimension minimumSize = new Dimension(100, 100);
        htmlView.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(200); //XXX: ignored in some releases
                                           //of Swing. bug 4101306
        //workaround for bug 4101306:
        //treeView.setPreferredSize(new Dimension(100, 100)); 

        splitPane.setPreferredSize(new Dimension(960, 590));

        //Add the split pane to this frame
        this.add(splitPane);
    }

    private void displaySimpleTest(String test, OntologyTestResult testresult) {
        
        htmlPane.setContentType("text/html");
        ListIterator liFailures,liSparql;
        String resultado="";
        int var=0;
        ArrayList<OntologyTestFailure> failures = testresult.getOntologyTestFailureQuery();
        ArrayList<OntologyTestFailure> failuresSparql = testresult.getOntologyTestFailureSparql();   
        
        liFailures = failures.listIterator();
        liSparql = failuresSparql.listIterator(); 
                
        if(liFailures.hasNext()){
            while(liFailures.hasNext()){
                OntologyTestFailure ontoFailure = (OntologyTestFailure) liFailures.next();
                if(ontoFailure.getTestNameUsuario().equals(test)){
                    if(var==0){
                        resultado = "<html>Las pruebas que han fallado en este test son: <br><br>";
                    }
                    var=1;
                    resultado = resultado + ontoFailure.showSimpleTest() + "<br><br>";
                }
        
            }
         }
         if(var==0){
            resultado = "<html>No se han producido errores en este test. <br><br>";
         }
        
         if(liSparql.hasNext()){
            while(liSparql.hasNext()){
                OntologyTestFailure ontoFailure = (OntologyTestFailure) liSparql.next();
                if(ontoFailure.getTestNameUsuario().equals(test)){
                    if(var==0){
                        resultado = "<html>Las pruebas que han fallado en este test son: <br><br>";
                    }
                    var=1;
                    resultado = resultado + ontoFailure.showSparqlTest() + "<br><br>";
                 }
            }
          }
        
        htmlPane.setText(resultado+"</html>");  
    }
    
    private void createNodes(DefaultMutableTreeNode top, OntologyTestResult testresult) {
        
        ListIterator liFailures,liSparql, liPassedQuery, liPassedSparql;
        ArrayList<OntologyTestFailure> failures = testresult.getOntologyTestFailureQuery();
        ArrayList<OntologyTestFailure> failuresSparql = testresult.getOntologyTestFailureSparql();
        ArrayList<OntologyTestPassed> passedTests = testresult.getOntologyTestPassedQuery();
        ArrayList<OntologyTestPassed> passedTestsSparql = testresult.getOntologyTestPassedSparql();
        
        ArrayList<String> list_clas = new ArrayList<String>(), list_ret = new ArrayList<String>(), 
                list_inst = new ArrayList<String>(), list_sat = new ArrayList<String>(), 
                list_real = new ArrayList<String>(),list_sparql = new ArrayList<String>();
        liFailures = failures.listIterator();
        liSparql = failuresSparql.listIterator();
        liPassedQuery = passedTests.listIterator();
        liPassedSparql = passedTestsSparql.listIterator();
        int var_inst=0, var_sat=0, var_ret=0, var_real=0, var_clas=0, var_sparql=0;
        if(liFailures.hasNext()){
        while(liFailures.hasNext()){ 
            OntologyTestFailure otf = (OntologyTestFailure) liFailures.next();   
            if(otf.getTestName().equals("Instanciación")){
                if(var_inst==0){
                    var_inst=1;
                    inst = new DefaultMutableTreeNode("Tests de Instanciación (failed)");
                    top.add(inst);
                    inst_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    inst.add(inst_hijo);
                    list_inst.add(inst_hijo.toString());
                }else{
                    if(!inst_hijo.equals(otf.getTestNameUsuario())){
                        inst_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                        if(!list_inst.contains(inst_hijo.toString()))
                        {
                            inst.add(inst_hijo);
                            list_inst.add(inst_hijo.toString());
                        }
                    }
                }
            }else if(otf.getTestName().equals("Retrieval")){
                if(var_ret==0){
                    var_ret=1;
                    ret = new DefaultMutableTreeNode("Tests de Retrieval (failed)");
                    top.add(ret);
                    ret_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    ret.add(ret_hijo);
                    list_ret.add(ret_hijo.toString());
                }else{
                    ret_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    if(!list_ret.contains(ret_hijo.toString()))
                        {
                            ret.add(ret_hijo);
                            list_ret.add(ret_hijo.toString());
                        }
                }
            }else if(otf.getTestName().equals("Realización")){
                if(var_real==0){
                    var_real=1;
                    real = new DefaultMutableTreeNode("Tests de Realización (failed)");
                    top.add(real);
                    real_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    real.add(real_hijo);
                    list_real.add(real_hijo.toString());
                }else{
                    real_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    if(!list_real.contains(real_hijo.toString()))
                        {
                            real.add(real_hijo);
                            list_real.add(real_hijo.toString());
                        }
                }
            }else if(otf.getTestName().equals("Clasificación")){
                if(var_clas==0){
                    var_clas=1;
                    clas = new DefaultMutableTreeNode("Tests de Clasificación (failed)");
                    top.add(clas);
                    clas_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    clas.add(clas_hijo);
                    list_clas.add(clas_hijo.toString());
                }else{
                    clas_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    if(!list_clas.contains(clas_hijo.toString()))
                        {
                            clas.add(clas_hijo);
                            list_clas.add(clas_hijo.toString());
                        }
                }
            }else if(otf.getTestName().equals("Satisfactibilidad")){
                if(var_sat==0){
                    var_sat=1;
                    sat = new DefaultMutableTreeNode("Tests de Satisfactibilidad (failed)");
                    top.add(sat);
                    sat_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    sat.add(sat_hijo);
                    list_sat.add(sat_hijo.toString());
                }else{
                        sat_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                        if(!list_sat.contains(sat_hijo.toString()))
                        {
                            sat.add(sat_hijo);
                            list_sat.add(sat_hijo.toString());
                        }    
                }
            }
        }
        }else{
            System.out.println("No se han producido errores.");
        }
        
        if(liPassedQuery.hasNext()){
        while(liPassedQuery.hasNext()){ 
            OntologyTestPassed otf = (OntologyTestPassed) liPassedQuery.next();   
            if(otf.getTestName().equals("Instanciación")){
                if(var_inst==0){
                    var_inst=1;
                    inst = new DefaultMutableTreeNode("Tests de Instanciación (passed)");
                    top.add(inst);
                    inst_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    inst.add(inst_hijo);
                    list_inst.add(inst_hijo.toString());
                }else{
                    if(!inst_hijo.equals(otf.getTestNameUsuario())){
                        inst_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                        if(!list_inst.contains(inst_hijo.toString()))
                        {
                            inst.add(inst_hijo);
                            list_inst.add(inst_hijo.toString());
                        }
                    }
                }
            }else if(otf.getTestName().equals("Retrieval")){
                if(var_ret==0){
                    var_ret=1;
                    ret = new DefaultMutableTreeNode("Tests de Retrieval (passed)");
                    top.add(ret);
                    ret_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    ret.add(ret_hijo);
                    list_ret.add(ret_hijo.toString());
                }else{
                    ret_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    if(!list_ret.contains(ret_hijo.toString()))
                        {
                            ret.add(ret_hijo);
                            list_ret.add(ret_hijo.toString());
                        }
                }
            }else if(otf.getTestName().equals("Realización")){
                if(var_real==0){
                    var_real=1;
                    real = new DefaultMutableTreeNode("Tests de Realización (passed)");
                    top.add(real);
                    real_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    real.add(real_hijo);
                    list_real.add(real_hijo.toString());
                }else{
                    real_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    if(!list_real.contains(real_hijo.toString()))
                        {
                            real.add(real_hijo);
                            list_real.add(real_hijo.toString());
                        }
                }
            }else if(otf.getTestName().equals("Clasificación")){
                if(var_clas==0){
                    var_clas=1;
                    clas = new DefaultMutableTreeNode("Tests de Clasificación (passed)");
                    top.add(clas);
                    clas_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    clas.add(clas_hijo);
                    list_clas.add(clas_hijo.toString());
                }else{
                    clas_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    if(!list_clas.contains(clas_hijo.toString()))
                        {
                            clas.add(clas_hijo);
                            list_clas.add(clas_hijo.toString());
                        }
                }
            }else if(otf.getTestName().equals("Satisfactibilidad")){
                if(var_sat==0){
                    var_sat=1;
                    sat = new DefaultMutableTreeNode("Tests de Satisfactibilidad (passed)");
                    top.add(sat);
                    sat_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    sat.add(sat_hijo);
                    list_sat.add(sat_hijo.toString());
                }else{
                        sat_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                        if(!list_sat.contains(sat_hijo.toString()))
                        {
                            sat.add(sat_hijo);
                            list_sat.add(sat_hijo.toString());
                        }    
                }
            }
        }
        }else{
            System.out.println("No se han producido errores.");
        }
        
        if(liSparql.hasNext()){
        while(liSparql.hasNext()){
            
            OntologyTestFailure otf = (OntologyTestFailure) liSparql.next();

            if(var_sparql==0){
                var_sparql=1;
                sparql = new DefaultMutableTreeNode("Tests SPARQL");
                top.add(sparql);
                sparql_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                sparql.add(sparql_hijo);
                list_sparql.add(sparql_hijo.toString());
            }else{
                if(!sparql_hijo.equals(otf.getTestNameUsuario())){
                    sparql_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    if(!list_sparql.contains(sparql_hijo.toString()))
                    {
                        sparql.add(sparql_hijo);
                        list_sparql.add(sparql_hijo.toString());
                    }
                 }
             }
        }
        }
        
        if(liPassedSparql.hasNext()){
        while(liPassedSparql.hasNext()){
            
            OntologyTestPassed otf = (OntologyTestPassed) liPassedSparql.next();

            if(var_sparql==0){
                var_sparql=1;
                sparql = new DefaultMutableTreeNode("Tests SPARQL");
                top.add(sparql);
                sparql_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                sparql.add(sparql_hijo);
                list_sparql.add(sparql_hijo.toString());
            }else{
                if(!sparql_hijo.equals(otf.getTestNameUsuario())){
                    sparql_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario());
                    if(!list_sparql.contains(sparql_hijo.toString()))
                    {
                        sparql.add(sparql_hijo);
                        list_sparql.add(sparql_hijo.toString());
                    }
                 }
             }
        }
        }
    }
}