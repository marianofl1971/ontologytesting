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
import code.google.com.p.ontologytesting.gui.tests.TestSimpleInstSat;
import code.google.com.p.ontologytesting.gui.tests.TestSimpleRetClas;
import code.google.com.p.ontologytesting.gui.tests.AddSPARQLJPanel;
import code.google.com.p.ontologytesting.gui.tests.TestSimpleReal;
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
    private ListAndResultsJPanel panelTest;
    private ListarTestsJPanel listInst;
    
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
        new TreeResults(testResult,listScenario.get(0).getNombre());
    }

    public void ejecutarTodosLosTests() throws ExceptionReadOntology{
        testCase = new OntologyTestCase();
        testResult = new OntologyTestResult();
        testCase.run(testResult, CollectionTest.getInstance());
        new TreeResults(testResult, CollectionTest.getInstance().getScenariotest().get(0).getNombre());
    }
    
    public void editarTest(ScenarioTest scenario){
        panelTest = ListAndResultsJPanel.getInstance();
        if(scenario.getTipoTest().name().equals("INST") || scenario.getTipoTest().name().equals("SAT")){
            panelTest.getTestsPanel().aniadirTest(new TestSimpleInstSat(scenario));
        }else if(scenario.getTipoTest().name().equals("RET") || scenario.getTipoTest().name().equals("CLAS")){
            panelTest.getTestsPanel().aniadirTest(new TestSimpleRetClas(scenario));
        }else if(scenario.getTipoTest().name().equals("REAL")){
            panelTest.getTestsPanel().aniadirTest(new TestSimpleReal(scenario));
        }else if(scenario.getTipoTest().name().equals("SPARQL")){
            panelTest.getTestsPanel().aniadirTest(new AddSPARQLJPanel(scenario));
        }   
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
  
}
