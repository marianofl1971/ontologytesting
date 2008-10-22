/*
 * ListarTestsInstanciasJPanel.java
 *
 * Created on 21 de octubre de 2008, 10:54
 */

package code.google.com.p.ontologytesting.guiNew;

import code.google.com.p.ontologytesting.model.ClassInstances;
import code.google.com.p.ontologytesting.model.Instancias;
import code.google.com.p.ontologytesting.model.PropertyInstances;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;
import code.google.com.p.ontologytesting.model.ScenarioTest;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 *
 * @author  sara.garcia
 */
public class ListarTestsInstanciasJPanel extends javax.swing.JPanel implements ListSelectionListener{

    private JSplitPane splitPane = null;
    private List<ScenarioTest> listaFicheros = new ArrayList<ScenarioTest>();
    private List<Instancias> listaFicherosInst = new ArrayList<Instancias>();
    private List<ScenarioTest> listaTests = new ArrayList<ScenarioTest>();
    private static String pathFicheroAbrir ="";
    private Utils util = new Utils();
    private List<ScenarioTest> listaScenarios = new ArrayList<ScenarioTest>();
    private ScenarioTest scenarioSelect = new ScenarioTest();
    private List<ScenarioTest> l = new ArrayList<ScenarioTest>();
    private List<Instancias> listaInstancias = new ArrayList<Instancias>();
    private Instancias instanciaSelect = new Instancias();
    private List<Instancias> linst = new ArrayList<Instancias>();
    private boolean isTest=false;
    private DefaultListModel modeloTests,modeloInstancias;
    
    /** Creates new form ListarTestsInstanciasJPanel */
    public ListarTestsInstanciasJPanel() {
        initComponents();
    }
    
    public ListarTestsInstanciasJPanel(List<ScenarioTest> listaFich,List<Instancias> listaInst, boolean isTest) {
        initComponents();
        this.setIsTest(isTest);
        modeloTests = new DefaultListModel();
        modeloInstancias = new DefaultListModel();
        if(isTest==true){
            this.setListaFicheros(listaFich);
            for(int i=0;i<listaFich.size();i++){
                String nombre = listaFich.get(i).getNombre();
                modeloTests.addElement(nombre);
            }
            list.setModel(modeloTests);
        }else{
            this.setListaFicherosInst(listaInst);
            for(int i=0;i<listaInst.size();i++){
                String nombre = listaInst.get(i).getNombre();
                modeloInstancias.addElement(nombre);
            }
            list.setModel(modeloInstancias);
        }

        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
       
        descripcion.setFont(descripcion.getFont().deriveFont(Font.BOLD));
        descripcion.setLineWrap(true);
        descripcion.setWrapStyleWord(true);
        
        descScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        Dimension minimumSize = new Dimension(100, 50);
        listScrollPane.setMinimumSize(minimumSize);
        descScrollPane.setMinimumSize(minimumSize);

        if(isTest==true){
            if(listaFich.size()>0){
                setScenarioSelect(util.buscarScenario(this.getListaFicheros(),modeloTests.get(list.getLeadSelectionIndex()).toString()));
                l.add(getScenarioSelect());
                this.setListaDeScenarios(l);
                updateLabel(modeloTests.get(list.getLeadSelectionIndex()).toString());
            }
        }else{
            if(listaInst.size()>0){
                setInstanciaSelect(util.buscarInstancias(this.getListaFicherosInst(), modeloInstancias.get(list.getLeadSelectionIndex()).toString()));
                linst.add(getInstanciaSelect());
                this.setListaInstancias(linst);
                updateLabel(modeloInstancias.get(list.getLeadSelectionIndex()).toString());
            }
        }
        
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        l = new ArrayList<ScenarioTest>();
        linst = new ArrayList<Instancias>();
        Object[] object = list.getSelectedValues();
        for(int i=0;i<object.length;i++){
            if(this.getIsTest()==false){
                setInstanciaSelect(util.buscarInstancias(this.getListaFicherosInst(),(String) object[i]));
                if(util.instanciaYaExiste(linst,(String) object[i])==false){
                    linst.add(getInstanciaSelect());
                }
            }else{
                setScenarioSelect(util.buscarScenario(this.getListaFicheros(),(String) object[i]));
                if(util.testYaExiste(l,(String) object[i])==false){
                    l.add(getScenarioSelect());
                }
            }  
        }
        if(this.getIsTest()==true){
            this.setListaDeScenarios(l);
            updateLabel(modeloTests.get(list.getLeadSelectionIndex()).toString());
        }else{
            this.setListaInstancias(linst);
            updateLabel(modeloInstancias.get(list.getLeadSelectionIndex()).toString());
        }
        
    }
    
    public void updateLabel (String name) { 
        if(this.getIsTest()==false){
            Instancias inst = util.buscarInstancias(this.getListaFicherosInst(),name);
            this.setInstanciaSelect(inst);
            List<ClassInstances> clasInst = inst.getClassInstances();
            List<PropertyInstances> propInst = inst.getPropertyInstances();
            ListIterator cI,pI;
            cI = clasInst.listIterator();
            pI = propInst.listIterator();
            String deClase="";
            String dePropiedad="";
            while(cI.hasNext()){
                ClassInstances clasInstance = (ClassInstances) cI.next();
                if(deClase.equals("")){
                    deClase = clasInstance.getClassInstance()+"\n";
                }else{
                    deClase=deClase+clasInstance.getClassInstance()+"\n";
                }
            }
            while(pI.hasNext()){
                PropertyInstances propInstance = (PropertyInstances) pI.next();
                if(dePropiedad.equals("")){
                    dePropiedad = propInstance.getPropertyInstance()+"\n";
                }else{
                    dePropiedad=dePropiedad+propInstance.getPropertyInstance()+"\n";
                }
            }
            descripcion.setText("Instancias de Clase: \n\n"+deClase+
                    "\n\nInstancias de Propiedad: \n\n"+dePropiedad);
        }else{
            ScenarioTest scenario = util.buscarScenario(this.getListaFicheros(),name);
            this.setScenarioSelect(scenario);
            descripcion.setText("Nombre Test: "+scenario.getNombre()+
            "\n\nDescripción: "+scenario.getDescripcion());
            descripcion.setEditable(false);         
        }
    }              

    public List<ScenarioTest> obtenerListaDeTests(ArrayList<ScenarioTest> scenario){
        for(int i=0; i<scenario.size();i++){
            listaTests.add(scenario.get(i));
        }
        return listaTests;
    }
    
    public JList getImageList() {
        return list;
    }

    public JSplitPane getSplitPane(){
        return splitPane;
    }
    
    public static String getPathFicheroAbrir() {
        return pathFicheroAbrir;
    }
    
    public static void setPathFicheroAbrir(String aPathFicheroAbrir) {
        pathFicheroAbrir = aPathFicheroAbrir;
    }

    public List<ScenarioTest> getListaFicheros() {
        return listaFicheros;
    }

    public void setListaFicheros(List<ScenarioTest> listaFicheros) {
        this.listaFicheros = listaFicheros;
    }

    public List<ScenarioTest> getListaDeScenarios() {
        return listaScenarios;
    }

    public void setListaDeScenarios(List<ScenarioTest> scenarioActual) {
        this.listaScenarios = scenarioActual;
    }

    public ScenarioTest getScenarioSelect() {
        return scenarioSelect;
    }

    public void setScenarioSelect(ScenarioTest scenarioSelect) {
        this.scenarioSelect = scenarioSelect;
    }

    public List<Instancias> getListaInstancias() {
        return listaInstancias;
    }

    public void setListaInstancias(List<Instancias> listaInstancias) {
        this.listaInstancias = listaInstancias;
    }

    public Instancias getInstanciaSelect() {
        return instanciaSelect;
    }

    public void setInstanciaSelect(Instancias instanciaSelect) {
        this.instanciaSelect = instanciaSelect;
    }

    public List<Instancias> getLinst() {
        return linst;
    }

    public void setLinst(List<Instancias> linst) {
        this.linst = linst;
    }

    public boolean getIsTest() {
        return isTest;
    }

    public void setIsTest(boolean isTest) {
        this.isTest = isTest;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        descScrollPane = new javax.swing.JScrollPane();
        descripcion = new javax.swing.JTextArea();
        listScrollPane = new javax.swing.JScrollPane();
        list = new javax.swing.JList();

        jSplitPane1.setDividerLocation(170);
        jSplitPane1.setDividerSize(6);
        jSplitPane1.setOneTouchExpandable(true);
        jSplitPane1.setPreferredSize(new java.awt.Dimension(500, 200));

        descripcion.setColumns(20);
        descripcion.setRows(5);
        descScrollPane.setViewportView(descripcion);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 264, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(descScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 229, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(descScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(jPanel2);

        listScrollPane.setViewportView(list);

        jSplitPane1.setLeftComponent(listScrollPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane descScrollPane;
    private javax.swing.JTextArea descripcion;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JList list;
    private javax.swing.JScrollPane listScrollPane;
    // End of variables declaration//GEN-END:variables

    public List<Instancias> getListaFicherosInst() {
        return listaFicherosInst;
    }

    public void setListaFicherosInst(List<Instancias> listaFicherosInst) {
        this.listaFicherosInst = listaFicherosInst;
    }

}
