
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
import java.util.List;
import java.util.ListIterator;
import javax.swing.JPanel;
import javax.swing.tree.DefaultTreeCellRenderer;

public class TreeResults extends JPanel {

    private JEditorPane htmlPane;
    private static boolean DEBUG = false;
    private DefaultMutableTreeNode inst,ret,clas,sat,real,sparql;
    private DefaultMutableTreeNode inst_hijo,ret_hijo,clas_hijo, sat_hijo,real_hijo,sparql_hijo;
    private static OntologyTestFailure actualScenarioFailure;

    public TreeResults(final OntologyTestResult testresult) {

        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Resultado de sus Tests");
        createNodes(top,testresult);

        JTree tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                                   (e.getPath().getLastPathComponent());
                Object nodeInfo = node.getUserObject();
                if (node.isLeaf()) {
                    String test = (String) nodeInfo;
                    int size = test.length();
                    test = test.substring(0,size-9);
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
            String texto = new String((String)node.getUserObject());
	    super.getTreeCellRendererComponent(pTree, pValue, pIsSelected,
                     pIsExpanded, pIsLeaf, pRow, pHasFocus);
                 if (texto.contains("passed"))
                    setForeground(Color.green);
                 else if (texto.contains("failed"))
                    setForeground(Color.red);
                 return (this);
	}
        });

        JScrollPane treeView = new JScrollPane(tree);

        htmlPane = new JEditorPane();
        htmlPane.setEditable(false);
        JScrollPane htmlView = new JScrollPane(htmlPane);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(treeView);
        splitPane.setBottomComponent(htmlView);

        Dimension minimumSize = new Dimension(100, 100);
        htmlView.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(200); 

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
        List<OntologyTestFailure> failures = testresult.getOntologyTestFailureQuery();
        List<OntologyTestFailure> failuresSparql = testresult.getOntologyTestFailureSparql();   
        
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
        
        inst=null;
        ret=null;
        clas=null;
        sat=null;
        real=null; 
        sparql=null;
        inst_hijo=null;
        ret_hijo=null;
        clas_hijo=null;
        sat_hijo=null;
        real_hijo=null;
        sparql_hijo=null;
        
        ListIterator liFailures,liSparql, liPassedQuery, liPassedSparql;
        List<OntologyTestFailure> failures = testresult.getOntologyTestFailureQuery();
        List<OntologyTestFailure> failuresSparql = testresult.getOntologyTestFailureSparql();
        List<OntologyTestPassed> passedTests = testresult.getOntologyTestPassedQuery();
        List<OntologyTestPassed> passedTestsSparql = testresult.getOntologyTestPassedSparql();
        
        List<String> list_clas = new ArrayList<String>(), list_ret = new ArrayList<String>(), 
                list_inst = new ArrayList<String>(), list_sat = new ArrayList<String>(), 
                list_real = new ArrayList<String>(),list_sparql = new ArrayList<String>();
        liFailures = failures.listIterator();
        liSparql = failuresSparql.listIterator();
        liPassedQuery = passedTests.listIterator();
        liPassedSparql = passedTestsSparql.listIterator();
        int var_inst=0, var_sat=0, var_ret=0, var_real=0, var_clas=0, var_sparql=0;
        String inst_hijo_var = "", ret_hijo_var="", real_hijo_var="",sat_hijo_var="", 
                clas_hijo_var="", sparql_hijo_var="";
        
        if(liFailures.hasNext()){
        while(liFailures.hasNext()){ 
            OntologyTestFailure otf = (OntologyTestFailure) liFailures.next();   
            if(otf.getTestName().equals("Instanciación")){
                if(var_inst==0){
                    var_inst=1;
                    inst = new DefaultMutableTreeNode("Tests de Instanciación");
                    top.add(inst);
                    inst_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (failed)"));
                    inst.add(inst_hijo);
                    inst_hijo_var = otf.getTestNameUsuario();
                    list_inst.add(inst_hijo_var);
                }else{
                    if(!inst_hijo.equals(otf.getTestNameUsuario())){
                        inst_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (failed)"));
                        inst_hijo_var = otf.getTestNameUsuario();
                        if(!list_inst.contains(inst_hijo_var))
                        {
                            inst.add(inst_hijo);
                            list_inst.add(inst_hijo_var);
                        }
                    }
                }
            }else if(otf.getTestName().equals("Retrieval")){
                if(var_ret==0){
                    var_ret=1;
                    ret = new DefaultMutableTreeNode("Tests de Retrieval");
                    top.add(ret);
                    ret_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (failed)"));
                    ret.add(ret_hijo);
                    ret_hijo_var = otf.getTestNameUsuario();
                    list_ret.add(ret_hijo_var);
                }else{
                    ret_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (failed)"));
                    ret_hijo_var = otf.getTestNameUsuario();
                    if(!list_ret.contains(ret_hijo_var))
                        {
                            ret.add(ret_hijo);
                            list_ret.add(ret_hijo_var);
                        }
                }
            }else if(otf.getTestName().equals("Realización")){
                if(var_real==0){
                    var_real=1;
                    real = new DefaultMutableTreeNode("Tests de Realización");
                    top.add(real);
                    real_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (failed)"));
                    real.add(real_hijo);
                    real_hijo_var = otf.getTestNameUsuario();
                    list_real.add(real_hijo_var);
                }else{
                    real_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (failed)"));
                    real_hijo_var = otf.getTestNameUsuario();
                    if(!list_real.contains(real_hijo_var))
                        {
                            real.add(real_hijo);
                            list_real.add(real_hijo_var);
                        }
                }
            }else if(otf.getTestName().equals("Clasificación")){
                if(var_clas==0){
                    var_clas=1;
                    clas = new DefaultMutableTreeNode("Tests de Clasificación");
                    top.add(clas);
                    clas_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (failed)"));
                    clas.add(clas_hijo);
                    clas_hijo_var = otf.getTestNameUsuario();
                    list_clas.add(clas_hijo_var);
                }else{
                    clas_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (failed)"));
                    clas_hijo_var = otf.getTestNameUsuario();
                    if(!list_clas.contains(clas_hijo_var))
                        {
                            clas.add(clas_hijo);
                            list_clas.add(clas_hijo_var);
                        }
                }
            }else if(otf.getTestName().equals("Satisfactibilidad")){
                if(var_sat==0){
                    var_sat=1;
                    sat = new DefaultMutableTreeNode("Tests de Satisfactibilidad");
                    top.add(sat);
                    sat_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (failed)"));
                    sat.add(sat_hijo);
                    sat_hijo_var = otf.getTestNameUsuario();
                    list_sat.add(sat_hijo_var);
                }else{
                        sat_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (failed)"));
                        sat_hijo_var = otf.getTestNameUsuario();
                        if(!list_sat.contains(sat_hijo_var))
                        {
                            sat.add(sat_hijo);
                            list_sat.add(sat_hijo_var);
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
                    inst = new DefaultMutableTreeNode("Tests de Instanciación");
                    top.add(inst);
                    inst_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (passed)"));
                    inst_hijo_var = otf.getTestNameUsuario();
                    list_inst.add(inst_hijo_var);
                }else{
                    if(!inst_hijo.equals(otf.getTestNameUsuario())){
                        inst_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (passed)"));
                        inst_hijo_var=otf.getTestNameUsuario();
                        if(!list_inst.contains(inst_hijo_var))
                        {
                            inst.add(inst_hijo);
                            list_inst.add(inst_hijo_var);
                        }
                    }
                }
            }else if(otf.getTestName().equals("Retrieval")){
                if(var_ret==0){
                    var_ret=1;
                    ret = new DefaultMutableTreeNode("Tests de Retrieval");
                    top.add(ret);
                    ret_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (passed)"));
                    ret.add(ret_hijo);
                    ret_hijo_var = otf.getTestNameUsuario();
                    list_ret.add(ret_hijo_var);
                }else{
                    ret_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (passed)"));
                    ret_hijo_var = otf.getTestNameUsuario();
                    if(!list_ret.contains(ret_hijo_var))
                        {
                            ret.add(ret_hijo);
                            list_ret.add(ret_hijo_var);
                        }
                }
            }else if(otf.getTestName().equals("Realización")){
                if(var_real==0){
                    var_real=1;
                    real = new DefaultMutableTreeNode("Tests de Realización");
                    top.add(real);
                    real_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (passed)"));
                    real.add(real_hijo);
                    real_hijo_var = otf.getTestNameUsuario();
                    list_real.add(real_hijo_var);
                }else{
                    real_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (passed)"));
                    real_hijo_var = otf.getTestNameUsuario();
                    if(!list_real.contains(real_hijo_var))
                        {
                            real.add(real_hijo);
                            list_real.add(real_hijo_var);
                        }
                }
            }else if(otf.getTestName().equals("Clasificación")){
                if(var_clas==0){
                    var_clas=1;
                    clas = new DefaultMutableTreeNode("Tests de Clasificación");
                    top.add(clas);
                    clas_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (passed)"));
                    clas.add(clas_hijo);
                    clas_hijo_var = otf.getTestNameUsuario();
                    list_clas.add(clas_hijo_var);
                }else{
                    clas_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (passed)"));
                    clas_hijo_var = otf.getTestNameUsuario();
                    if(!list_clas.contains(clas_hijo_var))
                        {
                            clas.add(clas_hijo);
                            list_clas.add(clas_hijo_var);
                        }
                }
            }else if(otf.getTestName().equals("Satisfactibilidad")){
                if(var_sat==0){
                    var_sat=1;
                    sat = new DefaultMutableTreeNode("Tests de Satisfactibilidad");
                    top.add(sat);
                    sat_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (passed)"));
                    sat.add(sat_hijo);
                    sat_hijo_var = otf.getTestNameUsuario();
                    list_sat.add(sat_hijo_var);
                }else{
                        sat_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (passed)"));
                        sat_hijo_var = otf.getTestNameUsuario();
                        if(!list_sat.contains(sat_hijo_var))
                        {
                            sat.add(sat_hijo);
                            list_sat.add(sat_hijo_var);
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
                sparql_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (failed)"));
                sparql.add(sparql_hijo);
                sparql_hijo_var = otf.getTestNameUsuario();
                list_sparql.add(sparql_hijo_var);
            }else{
                if(!sparql_hijo.equals(otf.getTestNameUsuario())){
                    sparql_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (failed)"));
                    sparql_hijo_var = otf.getTestNameUsuario();
                    if(!list_sparql.contains(sparql_hijo_var))
                    {
                        sparql.add(sparql_hijo);
                        list_sparql.add(sparql_hijo_var);
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
                sparql_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (passed)"));
                sparql.add(sparql_hijo);
                sparql_hijo_var = otf.getTestNameUsuario();
                list_sparql.add(sparql_hijo_var);
            }else{
                if(!sparql_hijo.equals(otf.getTestNameUsuario())){
                    sparql_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (passed)"));
                    sparql_hijo_var = otf.getTestNameUsuario();
                    if(!list_sparql.contains(sparql_hijo_var))
                    {
                        sparql.add(sparql_hijo);
                        list_sparql.add(sparql_hijo_var);
                    }
                 }
             }
        }
        }
    }
    
    public static OntologyTestFailure getActualScenarioFailure() {
        return actualScenarioFailure;
    }
    
    public static void setActualScenarioFailure(OntologyTestFailure aActualScenarioFailure) {
        actualScenarioFailure = aActualScenarioFailure;
    }   
}