/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.guiNew.auxiliarclasess;

/**
 *
 * @author sara.garcia
 */
import code.google.com.p.ontologytesting.guiNew.menupanels.SeeTestJDialog;
import code.google.com.p.ontologytesting.guiNew.*;
import code.google.com.p.ontologytesting.guiNew.tests.TestSimpleInstSat;
import code.google.com.p.ontologytesting.guiNew.tests.TestSimpleRetClas;
import code.google.com.p.ontologytesting.guiNew.tests.AddSPARQLJPanel;
import code.google.com.p.ontologytesting.guiNew.tests.TestSimpleReal;
import code.google.com.p.ontologytesting.model.*;
import code.google.com.p.ontologytesting.model.jenainterfaz.ExceptionReadOntology;
import code.google.com.p.ontologytesting.persistence.SaveTest;
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
    private SaveTest saveTest;
    
    public void ejecutarUnTest(ScenarioTest scenario) throws ExceptionReadOntology{
        testCase = new OntologyTestCase();
        testResult = new OntologyTestResult();
        testCase.runScenario(testResult, CollectionTest.getInstance(), scenario);   
        new TreeResults(testResult);
    }

    public void ejecutarBateriaTests(List<ScenarioTest> listScenario) throws ExceptionReadOntology{
        testCase = new OntologyTestCase();
        testResult = new OntologyTestResult();
        testCase.runListaScenario(testResult, CollectionTest.getInstance(), listScenario);
        new TreeResults(testResult);
    }

    public void ejecutarTodosLosTests() throws ExceptionReadOntology{
        testCase = new OntologyTestCase();
        testResult = new OntologyTestResult();
        testCase.run(testResult, CollectionTest.getInstance());
        new TreeResults(testResult);
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
    
    /*JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"La ontologia introducida no es valida." +
            "\nSolo pueden realizarse tests sobre documentos owl consistentes","Error Message",JOptionPane.ERROR_MESSAGE);*/
    
    public void eliminarTest(ScenarioTest scenario){
        saveTest = new SaveTest();
        CollectionTest.getInstance().getScenariotest().remove(scenario);
        saveTest.actualizarListaDeTestsSimples(CollectionTest.getInstance().getScenariotest());
        saveTest.actualizarListaDeTestsSparql(CollectionTest.getInstance().getScenariotest());
    }
    
    public void eliminarInstancias(Instancias inst){
        saveTest = new SaveTest();
        CollectionTest.getInstance().getInstancias().remove(inst);
        saveTest.actualizarListaDeInstancias();
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
  
}
