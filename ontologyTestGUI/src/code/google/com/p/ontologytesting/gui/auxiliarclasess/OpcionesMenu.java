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
import code.google.com.p.ontologytesting.gui.instances.AddInstancesClasPropJPanel;
import code.google.com.p.ontologytesting.gui.menupanels.ListarTestsJPanel;
import code.google.com.p.ontologytesting.model.*;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.ExceptionReadOntology;
import java.util.List;
import javax.swing.WindowConstants;

/**
 *
 * @author sara.garcia
 */
public class OpcionesMenu {

    private OntologyTestResult testResult;
    private OntologyTestCase testCase;
    private ListarTestsJPanel listInst;
    private static ScenarioTest scenarioActual = new ScenarioTest();
    private ScenarioTest scenarioAEditar = new ScenarioTest();
    
    public void ejecutarUnTest(ScenarioTest scenario) throws ExceptionReadOntology{
        testCase = new OntologyTestCase();
        testResult = new OntologyTestResult();
        testCase.runScenario(testResult, CollectionTest.getInstance(), scenario);   
        new TreeResults(testResult,scenario.getNombre()); 
    }

    public void ejecutarBateriaTests(List<ScenarioTest> listScenario) throws ExceptionReadOntology{
        testCase = new OntologyTestCase();
        testResult = new OntologyTestResult();
        testCase.runListaScenario(testResult, CollectionTest.getInstance(), listScenario);
        new TreeResults(testResult,"Batería de Tests");
    }

    public void ejecutarTodosLosTests() throws ExceptionReadOntology{
        testCase = new OntologyTestCase();
        testResult = new OntologyTestResult();
        testCase.run(testResult, CollectionTest.getInstance());
        new TreeResults(testResult, "Todos los Test");
    }
    
    public void editarTest(ScenarioTest scenario){
        OpcionesMenu.setScenarioActual(scenario);
        scenarioAEditar = new ScenarioTest(scenario);
        if(scenarioAEditar.getTipoTest().name().equals("INST") || scenarioAEditar.getTipoTest().name().equals("SAT")){
            MainApplicationJFrame.getInstance().cargarTest(0, scenarioAEditar);
        }else if(scenarioAEditar.getTipoTest().name().equals("RET") || scenarioAEditar.getTipoTest().name().equals("CLAS")){
            MainApplicationJFrame.getInstance().cargarTest(1, scenarioAEditar);
        }else if(scenarioAEditar.getTipoTest().name().equals("REAL")){
            MainApplicationJFrame.getInstance().cargarTest(2, scenarioAEditar);
        }else if(scenarioAEditar.getTipoTest().name().equals("SPARQL")){
            MainApplicationJFrame.getInstance().cargarTest(3, scenarioAEditar);
        }   
    }
    
    public void editarInstancias(AddInstancesClasPropJPanel inst){
        MainApplicationJFrame.getInstance().cargarInstancia(inst,inst.getNomInstanciasTextField());
    }
    
    public void eliminarTest(ScenarioTest scenario){
        CollectionTest.getInstance().getScenariotest().remove(scenario);
        this.actualizarListaDeTestsSimples(CollectionTest.getInstance().getScenariotest());
        this.actualizarListaDeTestsSparql(CollectionTest.getInstance().getScenariotest());
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
    
    public void actualizarListaDeTestsSimples(List<ScenarioTest> scenario){
        listInst = ListarTestsJPanel.getInstance();
        listInst.aniadirTestSimple(scenario);
    }
    
    public void actualizarListaDeTestsSparql(List<ScenarioTest> scenario){
        listInst = ListarTestsJPanel.getInstance();
        listInst.aniadirTestSparql(scenario);
    }
    
    public void actualizarListaDeInstancias(){
        listInst = ListarTestsJPanel.getInstance();
        listInst.aniadirInstancias(CollectionTest.getInstance().getInstancias());
    }

    public static ScenarioTest getScenarioActual() {
        return scenarioActual;
    }

    public static void setScenarioActual(ScenarioTest ascenarioActual) {
        scenarioActual = ascenarioActual;
    }
}
