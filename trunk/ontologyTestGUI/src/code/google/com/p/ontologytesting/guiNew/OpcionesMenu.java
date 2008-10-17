/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.guiNew;

/**
 *
 * @author sara.garcia
 */
import code.google.com.p.ontologytesting.model.CollectionTest;
import code.google.com.p.ontologytesting.model.OntologyTestCase;
import code.google.com.p.ontologytesting.model.OntologyTestResult;
import code.google.com.p.ontologytesting.model.ScenarioTest;
import code.google.com.p.ontologytesting.model.jenainterfaz.ExceptionReadOntology;
import code.google.com.p.ontologytesting.persistence.SaveTest;
import java.util.List;

/**
 *
 * @author sara.garcia
 */
public class OpcionesMenu {
    
    private static OntologyTestResult testResult;
    private OntologyTestCase testCase;
    private ListAndResultsJPanel panelTest;
    private SaveTest saveTest;
    
    public void ejecutarUnTest(ScenarioTest scenario){
        testCase = new OntologyTestCase();
        testResult = new OntologyTestResult();
        try{
            testCase.runScenario(testResult, CollectionTest.getInstance(), scenario);   
            new TreeResults(testResult);
        } catch (ExceptionReadOntology ex) {
            throw new ExceptionReadOntology("La ontologia introducida no es valida." +
            "\nSolo pueden realizarse tests sobre documentos owl consistentes");
        }
    }

    public void ejecutarBateriaTests(List<ScenarioTest> listScenario){
        testCase = new OntologyTestCase();
        testResult = new OntologyTestResult();
        try{
            testCase.runListaScenario(testResult, CollectionTest.getInstance(), listScenario);
            new TreeResults(testResult);
        } catch (ExceptionReadOntology ex) {
            throw new ExceptionReadOntology("La ontologia introducida no es valida." +
            "\nSolo pueden realizarse tests sobre documentos owl consistentes");
        }
    }

    public void ejecutarTodosLosTests(){
        testCase = new OntologyTestCase();
        testResult = new OntologyTestResult();
        try{
            testCase.run(testResult, CollectionTest.getInstance());
            new TreeResults(testResult);
        } catch (ExceptionReadOntology ex) {
            throw new ExceptionReadOntology("La ontologia introducida no es valida." +
            "\nSolo pueden realizarse tests sobre documentos owl consistentes");
        }
    }
    
    public void editarTest(ScenarioTest scenario){
        panelTest = ListAndResultsJPanel.getInstance();
        if(scenario.getTipoTest().getTipo()==0 || scenario.getTipoTest().getTipo()==3){
            panelTest.getTestsPanel().aniadirTest(new TestSimpleInstSat(scenario));
        }else if(scenario.getTipoTest().getTipo()==1 || scenario.getTipoTest().getTipo()==4){
            panelTest.getTestsPanel().aniadirTest(new TestSimpleRetClas(scenario));
        }else if(scenario.getTipoTest().getTipo()==2){
            panelTest.getTestsPanel().aniadirTest(new TestSimpleReal(scenario));
        }else if(scenario.getTipoTest().getTipo()==5){
            panelTest.getTestsPanel().aniadirTest(new AddSPARQLJPanel(scenario));
        }   
    }
    
    public void eliminarTest(ScenarioTest scenario){
        saveTest = new SaveTest();
        CollectionTest.getInstance().getScenariotest().remove(scenario);
        saveTest.actualizarListaDeTestsSimples();
        saveTest.actualizarListaDeTestsSparql();
    }
  
}
