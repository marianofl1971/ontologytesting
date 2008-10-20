/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.guiNew;

/**
 *
 * @author sara.garcia
 */
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;
import code.google.com.p.ontologytesting.model.ScenarioTest;
import java.util.ArrayList;

public class ListaFicheros extends JPanel implements ListSelectionListener {

    private JTextArea descripcion = new JTextArea();
    private JList list = new JList();
    private JSplitPane splitPane = null;
    private List<ScenarioTest> listaFicheros = new ArrayList<ScenarioTest>();
    private List<ScenarioTest> listaTests = new ArrayList<ScenarioTest>();
    private String[] lista = null;
    private JScrollPane descripcionScrollPane = null;
    private static String pathFicheroAbrir ="";
    private Utils util = new Utils();
    private List<ScenarioTest> listaScenarios = new ArrayList<ScenarioTest>();
    private ScenarioTest scenarioSelect = new ScenarioTest();
    private List<ScenarioTest> l = new ArrayList<ScenarioTest>();
    private List<String> listaDeNombres = new ArrayList<String>();
    
    public ListaFicheros(List<ScenarioTest> listaFich) {
        
        this.setListaFicheros(listaFich);
        for(int i=0;i<listaFich.size();i++){
            String nombre = listaFich.get(i).getNombre();
            listaDeNombres.add(nombre);
        }
        int tam = listaDeNombres.size();
        lista = new String[tam];
        for(int j=0;j<tam;j++){
            lista[j] = listaDeNombres.get(j);
        }
        list = new JList(lista);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
       
        JScrollPane listScrollPane = new JScrollPane(list);
        descripcion.setFont(descripcion.getFont().deriveFont(Font.BOLD));
        descripcion.setLineWrap(true);
        descripcion.setWrapStyleWord(true);
        
        descripcionScrollPane = new JScrollPane(descripcion);
        descripcionScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,listScrollPane, descripcionScrollPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(170);

        Dimension minimumSize = new Dimension(100, 50);
        listScrollPane.setMinimumSize(minimumSize);
        descripcionScrollPane.setMinimumSize(minimumSize);

        splitPane.setPreferredSize(new Dimension(500, 200));
        if(listaFich.size()!=0){
            setScenarioSelect(util.buscarScenario(this.getListaFicheros(), lista[list.getSelectedIndex()]));
            l.add(getScenarioSelect());
            this.setListaDeScenarios(l);
            updateLabel(lista[list.getSelectedIndex()]);
        }
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        l = new ArrayList<ScenarioTest>();
        Object[] object = list.getSelectedValues();
        for(int i=0;i<object.length;i++){
            setScenarioSelect(util.buscarScenario(this.getListaFicheros(),(String) object[i]));
            if(util.testYaExiste(l,(String) object[i])==false){
                l.add(getScenarioSelect());
            }
        }
        this.setListaDeScenarios(l);
        updateLabel(lista[list.getSelectedIndex()]);
    }
    
    public void updateLabel (String name) { 
        ScenarioTest scenario = util.buscarScenario(this.getListaFicheros(),name);
        this.setScenarioSelect(scenario);
        descripcion.setText("Nombre Test: "+scenario.getNombre()+
        "\n\nDescripción: "+scenario.getDescripcion());
        descripcion.setEditable(false);         
        /*Instancias inst = scenario.getInstancias();
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
                "\n\nInstancias de Propiedad: \n\n"+dePropiedad);*/
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

}
