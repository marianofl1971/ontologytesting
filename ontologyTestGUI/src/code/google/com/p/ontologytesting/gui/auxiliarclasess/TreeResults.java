
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.gui.auxiliarclasess;

/**
 *
 * @author sara.garcia
 */
import code.google.com.p.ontologytesting.gui.menupanels.ListarTestsJPanel;
import code.google.com.p.ontologytesting.gui.*;
import code.google.com.p.ontologytesting.model.exectests.*;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class TreeResults extends JPanel {
    
    private JEditorPane editor = new JEditorPane();
    private DefaultMutableTreeNode inst,ret,clas,sat,real,sparql;
    private DefaultMutableTreeNode inst_hijo,ret_hijo,clas_hijo, sat_hijo,real_hijo,sparql_hijo;
    private static String testSeleccionado;
    private JScrollPane resultsView,treeView;
    private ListarTestsJPanel listT;
    private boolean instFail=false,recFail=false,realFail=false,satFail=false,clasFail=false,sparqlFail=false;
    private URL iconFail,iconOk,iconFailTree,iconOkTree;
    int contador=0;
    
    public TreeResults(){
    }
    
    public TreeResults(final OntologyTestResult testResult, String testName) {
        int aux=0;
        DefaultMutableTreeNode top = new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Resultado_de_sus_Tests"));
        createNodes(top,testResult);
        listT = ListarTestsJPanel.getInstance();
        JTree tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) (e.getPath().getLastPathComponent());
                Object nodeInfo = node.getUserObject();
                if (node.isLeaf()) {
                    String test = (String) nodeInfo;
                    int size = test.length();
                    test = test.substring(0,size-9);
                    editor = displaySimpleTest(test,testResult);
                    editor.setCaretPosition(0);
                    setTestSeleccionado(test);
                } 
            }
        });

        tree.setCellRenderer(new DefaultTreeCellRenderer()
        {
            @Override
             public Component getTreeCellRendererComponent(JTree pTree,Object pValue, boolean pIsSelected, boolean pIsExpanded,boolean pIsLeaf, int pRow, boolean pHasFocus)
             {
                iconFail = this.getClass().getResource("images/flag_red.gif"); 
                iconOk = this.getClass().getResource("images/flag_green.gif"); 
                iconFailTree = this.getClass().getResource("images/action_delete.png"); 
                iconOkTree = this.getClass().getResource("images/action_check.png");
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)pValue;
                String texto = ((String)node.getUserObject());
                super.getTreeCellRendererComponent(pTree, pValue, pIsSelected,pIsExpanded, pIsLeaf, pRow, pHasFocus);
                if(texto.equals("Tests de Instanciación")){
                    if(iconOkTree!=null && iconFailTree!=null){
                        if(instFail==true){
                            setIcon(new ImageIcon(iconFailTree));
                        }else{
                            setIcon(new ImageIcon(iconOkTree));
                        }
                    }
                }else if(texto.equals("Tests de Recuperación")){
                    if(iconOkTree!=null && iconFailTree!=null){
                        if(recFail==true){
                            setIcon(new ImageIcon(iconFailTree));
                        }else{
                            setIcon(new ImageIcon(iconOkTree));
                        }
                    }
                }else if(texto.equals("Tests de Realización")){
                    if(iconOkTree!=null && iconFailTree!=null){
                        if(realFail==true){
                            setIcon(new ImageIcon(iconFailTree));
                        }else{
                            setIcon(new ImageIcon(iconOkTree));
                        }
                    }
                }else if(texto.equals("Tests de Satisfactibilidad")){
                    if(iconOkTree!=null && iconFailTree!=null){
                        if(satFail==true){
                            setIcon(new ImageIcon(iconFailTree));
                        }else{
                            setIcon(new ImageIcon(iconOkTree));
                        }
                    }
                }else if(texto.equals("Tests de Clasificación")){
                    if(iconOkTree!=null && iconFailTree!=null){
                        if(clasFail==true){
                            setIcon(new ImageIcon(iconFailTree));
                        }else{
                            setIcon(new ImageIcon(iconOkTree));
                        }
                    }
                }else if(texto.equals("Tests SPARQL")){
                    if(iconOkTree!=null && iconFailTree!=null){
                        if(sparqlFail==true){
                            setIcon(new ImageIcon(iconFailTree));
                        }else{
                            setIcon(new ImageIcon(iconOkTree));
                        }
                    }
                }else if (texto.contains("passed")){
                    setForeground(Color.green);
                    if(iconOk!=null){
                        setIcon(new ImageIcon(iconOk));
                    }
                }else if (texto.contains("failed")){
                    setForeground(Color.red);
                    if(iconFail!=null){
                        setIcon(new ImageIcon(iconFail));
                    }
                }
                return (this);
             }
         });

        this.expandAll(tree, true);
        setTestSeleccionado(testName);
        if(aux==0){
            editor = displaySimpleTest(testName, testResult);
        }
        treeView = new JScrollPane(tree);
        editor.setEditable(false);
        resultsView = new JScrollPane(editor);
        editor.setCaretPosition(0);

        ListAndResultsJPanel listAndRes = ListAndResultsJPanel.getInstance();
        listT.aniadirTreeResult(treeView);
        listAndRes.mostrarResultado(resultsView);
    }
    
    public void expandAll(JTree tree, boolean expand) {
        TreeNode root = (TreeNode)tree.getModel().getRoot();
        expandAll(tree, new TreePath(root), expand);
    }
    
    private void expandAll(JTree tree, TreePath parent, boolean expand) {
        TreeNode node = (TreeNode)parent.getLastPathComponent();
        if (node.getChildCount() >= 0) {
            for (Enumeration e=node.children(); e.hasMoreElements(); ) {
                TreeNode n = (TreeNode)e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                expandAll(tree, path, expand);
            }
        }
        if (expand) {
            tree.expandPath(parent);
        } else {
            tree.collapsePath(parent);
        }
    }

    
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = TreeResults.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public JEditorPane displaySimpleTest(String test, OntologyTestResult testresult) {
        
        StringBuffer resultado = new StringBuffer();
        editor.setContentType("text/html");
        editor.setCaretPosition(0);
        ListIterator liFailures,liSparql,liFailuresPassed,liSparqlPassed;
        int var=0;
        List<OntologyTestFailure> failures = testresult.getOntologyTestFailureQuery();
        List<OntologyTestFailure> failuresSparql = testresult.getOntologyTestFailureSparql();
        
        List<OntologyTestPassed> passed = testresult.getOntologyTestPassedQuery();
        List<OntologyTestPassed> passedSparql = testresult.getOntologyTestPassedSparql();
        
        liFailures = failures.listIterator();
        liSparql = failuresSparql.listIterator();
        
        liFailuresPassed = passed.listIterator();
        liSparqlPassed = passedSparql.listIterator();
                
        if(liFailures.hasNext()){
            while(liFailures.hasNext()){
                OntologyTestFailure ontoFailure = (OntologyTestFailure) liFailures.next();
                if(ontoFailure.getTestNameUsuario().equals(test)){
                    if(var==0){
                        resultado.append("<html>Las pruebas que han <b>fallado</b> en este test son: <br><br>");
                    }
                    var=1;
                    resultado.append(ontoFailure.showSimpleTest()).append("<br><br>");
                }
        
            }
         }
         
         var=0;
         if(liFailuresPassed.hasNext()){
            while(liFailuresPassed.hasNext()){
                OntologyTestPassed ontoPassed = (OntologyTestPassed) liFailuresPassed.next();
                if(ontoPassed.getTestNameUsuario().equals(test)){
                    if(var==0){
                        resultado.append("<html>Las pruebas que han <b>pasado</b> en este test son: <br><br>");
                    }
                    var=1;
                    resultado.append(ontoPassed.showSimpleTest()).append("<br><br>");
                }
        
            }
         }
         
         var=0;
         if(liSparql.hasNext()){
            while(liSparql.hasNext()){
                OntologyTestFailure ontoFailure = (OntologyTestFailure) liSparql.next();
                if(ontoFailure.getTestNameUsuario().equals(test)){
                    if(var==0){
                        resultado.append("<html>Las pruebas que han <b>fallado</b> en este test son: <br><br>");
                    } 
                    var=1;
                    resultado.append(ontoFailure.showSparqlTest()).append("<br><br>");
                 }
            }
          }
         
        var=0;
        if(liSparqlPassed.hasNext()){
            while(liSparqlPassed.hasNext()){
                OntologyTestPassed ontoPassed = (OntologyTestPassed) liSparqlPassed.next();
                if(ontoPassed.getTestNameUsuario().equals(test)){
                    if(var==0){
                        resultado.append("<html>Las pruebas que han <b>pasado</b> en este test son: <br><br>");
                    }
                    var=1;
                    resultado.append(ontoPassed.showSparqlTest()).append("<br><br>");
                 }
            }
          }
        
        editor.setText(resultado.toString()+"</html>");  
        return editor;
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

        while(liFailures.hasNext()){ 
            OntologyTestFailure otf = (OntologyTestFailure) liFailures.next();   
            if(otf.getTipoTest().name().equals("INST")){
                if(var_inst==0){
                    var_inst=1;
                    inst = new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Tests_de_Instanciación"));
                    contador++;
                    top.add(inst);
                    inst_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (failed)"));
                    inst.add(inst_hijo);
                    inst_hijo_var = otf.getTestNameUsuario();
                    list_inst.add(inst_hijo_var);
                    instFail=true;
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
            }else if(otf.getTipoTest().name().equals("RET")){
                if(var_ret==0){
                    var_ret=1;
                    ret = new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Tests_de_Recuperación"));
                    contador++;
                    top.add(ret);
                    ret_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (failed)"));
                    ret.add(ret_hijo);
                    ret_hijo_var = otf.getTestNameUsuario();
                    list_ret.add(ret_hijo_var);
                    recFail=true;
                }else{
                    ret_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (failed)"));
                    ret_hijo_var = otf.getTestNameUsuario();
                    if(!list_ret.contains(ret_hijo_var))
                        {
                            ret.add(ret_hijo);
                            list_ret.add(ret_hijo_var);
                        }
                }
            }else if(otf.getTipoTest().name().equals("REAL")){
                if(var_real==0){
                    var_real=1;
                    real = new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Tests_de_Realización"));
                    contador++;
                    top.add(real);
                    real_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (failed)"));
                    real.add(real_hijo);
                    real_hijo_var = otf.getTestNameUsuario();
                    list_real.add(real_hijo_var);
                    realFail=true;
                }else{
                    real_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (failed)"));
                    real_hijo_var = otf.getTestNameUsuario();
                    if(!list_real.contains(real_hijo_var))
                        {
                            real.add(real_hijo);
                            list_real.add(real_hijo_var);
                        }
                }
            }else if(otf.getTipoTest().name().equals("CLAS")){
                if(var_clas==0){
                    var_clas=1;
                    clas = new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Tests_de_Clasificación"));
                    contador++;
                    top.add(clas);
                    clas_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (failed)"));
                    clas.add(clas_hijo);
                    clas_hijo_var = otf.getTestNameUsuario();
                    list_clas.add(clas_hijo_var);
                    clasFail=true;
                }else{
                    clas_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (failed)"));
                    clas_hijo_var = otf.getTestNameUsuario();
                    if(!list_clas.contains(clas_hijo_var))
                        {
                            clas.add(clas_hijo);
                            list_clas.add(clas_hijo_var);
                        }
                }
            }else if(otf.getTipoTest().name().equals("SAT")){
                if(var_sat==0){
                    var_sat=1;
                    sat = new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Tests_de_Satisfactibilidad"));
                    contador++;
                    top.add(sat);
                    sat_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (failed)"));
                    sat.add(sat_hijo);
                    sat_hijo_var = otf.getTestNameUsuario();
                    list_sat.add(sat_hijo_var);
                    satFail=true;
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
        
        while(liPassedQuery.hasNext()){ 
            OntologyTestPassed otf = (OntologyTestPassed) liPassedQuery.next();     
            if(otf.getTipoTest().name().equals("INST")){
                if(var_inst==0){
                    var_inst=1;
                    inst = new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Tests_de_Instanciación"));
                    contador++;
                    top.add(inst);
                    inst_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (passed)"));
                    inst.add(inst_hijo);
                    inst_hijo_var = otf.getTestNameUsuario();
                    list_inst.add(inst_hijo_var);
                }else{
                    if(!inst_hijo.equals(otf.getTestNameUsuario())){
                        inst_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (passed)"));
                        inst_hijo_var = otf.getTestNameUsuario();
                        if(!list_inst.contains(inst_hijo_var))
                        {
                            inst.add(inst_hijo);
                            list_inst.add(inst_hijo_var);
                        }
                    }
                }
            }else if(otf.getTipoTest().name().equals("RET")){
                if(var_ret==0){
                    var_ret=1;
                    ret = new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Tests_de_Recuperación"));
                    contador++;
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
            }else if(otf.getTipoTest().name().equals("REAL")){
                if(var_real==0){
                    var_real=1;
                    real = new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Tests_de_Realización"));
                    contador++;
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
            }else if(otf.getTipoTest().name().equals("CLAS")){
                if(var_clas==0){
                    var_clas=1;
                    clas = new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Tests_de_Clasificación"));
                    contador++;
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
            }else if(otf.getTipoTest().name().equals("SAT")){
                if(var_sat==0){
                    var_sat=1;
                    sat = new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Tests_de_Satisfactibilidad"));
                    contador++;
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
        
        if(liSparql.hasNext()){
        while(liSparql.hasNext()){
            
            OntologyTestFailure otf = (OntologyTestFailure) liSparql.next();

            if(var_sparql==0){
                var_sparql=1;
                sparql = new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Tests_SPARQL"));
                contador++;
                top.add(sparql);
                sparql_hijo = new DefaultMutableTreeNode(otf.getTestNameUsuario().concat(" (failed)"));
                sparql.add(sparql_hijo);
                sparql_hijo_var = otf.getTestNameUsuario();
                list_sparql.add(sparql_hijo_var);
                sparqlFail=true;
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

        while(liPassedSparql.hasNext()){
            OntologyTestPassed otf = (OntologyTestPassed) liPassedSparql.next();
            if(var_sparql==0){
                var_sparql=1;
                sparql = new DefaultMutableTreeNode(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Tests_SPARQL"));
                contador++;
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
    
    public static String getTestSeleccionado() {
        return testSeleccionado;
    }

    public static void setTestSeleccionado(String aTestSeleccionado) {
        testSeleccionado = aTestSeleccionado;
    }

    public JScrollPane getResultsView() {
        return this.resultsView;
    }

    public JScrollPane getTreeView() {
        return this.treeView;
    }
     
}