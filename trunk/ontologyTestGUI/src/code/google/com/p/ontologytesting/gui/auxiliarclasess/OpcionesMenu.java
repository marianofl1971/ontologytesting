/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.gui.auxiliarclasess;

/**
 *
 * @author sara.garcia
 */
import code.google.com.p.ontologytesting.gui.menupanels.SeeTestJDialog;
import code.google.com.p.ontologytesting.gui.*;
import code.google.com.p.ontologytesting.gui.menupanels.ListarTestsJPanel;
import code.google.com.p.ontologytesting.model.*;
import java.util.List;
import javax.swing.WindowConstants;

/**
 *
 * @author sara.garcia
 */
public class OpcionesMenu {

    private ListarTestsJPanel listInst;
    private ScenarioTest scenarioActual = new ScenarioTest();
    private ScenarioTest scenarioAEditar = new ScenarioTest();
    
    public void editarTest(ScenarioTest scenario){
        setScenarioActual(scenario);
        scenarioAEditar = new ScenarioTest(scenario);
        if(scenarioAEditar.getTipoTest().name().equals("INST") || scenarioAEditar.getTipoTest().name().equals("SAT")){
            MainApplicationJFrame.getInstance().cargarTest(0, scenarioAEditar);
        }else if(scenarioAEditar.getTipoTest().name().equals("RET") || scenarioAEditar.getTipoTest().name().equals("CLAS")){
            MainApplicationJFrame.getInstance().cargarTest(1, scenarioAEditar);
        }else if(scenarioAEditar.getTipoTest().name().equals("REAL")){
            MainApplicationJFrame.getInstance().cargarTest(2, scenarioAEditar);
        }else if(scenarioAEditar.getTipoTest().name().equals("SPARQL")){
            MainApplicationJFrame.getInstance().cargarTest(5, scenarioAEditar);
        }   
    }
    
    /*public void editarInstancias(Instancias inst){
        String nombre = "";
        if(!inst.getNombre().equals("")){
            nombre = inst.getNombre();
        }else{
            nombre = "Editar instancias";
        }
        MainApplicationJFrame.getInstance().cargarInstancia(inst,nombre);
    }*/
    
    public void editarInstancias(ScenarioTest scenario){
        String nombre = "";
        if(!scenario.getInstancias().getNombre().equals("")){
            nombre = scenario.getInstancias().getNombre();
        }else{
            nombre = "Asociar instancias";
        }
        MainApplicationJFrame.getInstance().cargarInstancia(scenario,nombre);
    }
    
    public void eliminarTest(ScenarioTest scenario){
        CollectionTest.getInstance().getScenariotest().remove(scenario);
        this.actualizarListaDeTestsSimples();
        this.actualizarListaDeTestsSparql();
    }
    
    public void eliminarInstancias(Instancias inst){
        CollectionTest.getInstance().getInstancias().remove(inst);
        this.actualizarListaDeInstancias();
    }
    
    public SeeTestJDialog verTest(ScenarioTest scenario){
        SeeTestJDialog seeTestCompleted = new SeeTestJDialog(null, true, scenario);
        seeTestCompleted.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        seeTestCompleted.setLocationRelativeTo(MainApplicationJFrame.getInstance());
        return seeTestCompleted;
    }
    
    public SeeTestJDialog verInstancias(Instancias instancias){
        SeeTestJDialog seeTestCompleted = new SeeTestJDialog(null, true, instancias);
        seeTestCompleted.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        seeTestCompleted.setLocationRelativeTo(MainApplicationJFrame.getInstance());
        return seeTestCompleted;
    }
    
    public void actualizarListaDeTestsSimples(){
        listInst = ListarTestsJPanel.getInstance();
        listInst.aniadirTestSimple(CollectionTest.getInstance().getScenariotest());
    }
    
    public void actualizarListaDeTestsSparql(){
        listInst = ListarTestsJPanel.getInstance();
        listInst.aniadirTestSparql(CollectionTest.getInstance().getScenariotest());
    }
    
    public void actualizarListaDeInstancias(){
        listInst = ListarTestsJPanel.getInstance();
        listInst.aniadirInstancias(CollectionTest.getInstance().getInstancias());
    }

    public ScenarioTest getScenarioActual() {
        return scenarioActual;
    }

    public void setScenarioActual(ScenarioTest ascenarioActual) {
        scenarioActual = ascenarioActual;
    }
}
