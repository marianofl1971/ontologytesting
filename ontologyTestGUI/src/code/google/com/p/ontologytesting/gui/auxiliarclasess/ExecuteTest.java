/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.gui.auxiliarclasess;

import code.google.com.p.ontologytesting.model.*;
import javax.swing.SwingWorker;
import java.util.List;

/**
 *
 * @author sara.garcia
 */
public class ExecuteTest extends SwingWorker<OntologyTestResult, Void>{
    
    private OntologyTestResult testResult;
    private List<ScenarioTest> listScenario = null;
    private ScenarioTest scenario = null;
    private String name;
    
    private ExecuteTest() {}
    
    public ExecuteTest(ScenarioTest scenario) {
        this.scenario = scenario;
    }
    
    public ExecuteTest(List<ScenarioTest> listScenario) {
        this.listScenario = listScenario;
    }

    @Override
    protected OntologyTestResult doInBackground() throws Exception {
        OntologyTestResult treeResult = new OntologyTestResult();
        if(scenario!=null){
            treeResult = execOneTest(scenario);
            setName(scenario.getNombre());
        }else if(listScenario!=null){
            treeResult = execBateryTest(listScenario);
            setName("Batería de Tests");
        } 
        return treeResult;
    }

    private OntologyTestResult execOneTest(ScenarioTest scenario){ 
        OntologyTestCase testCase = new OntologyTestCase();
        testResult = new OntologyTestResult();
        testCase.runScenario(testResult, CollectionTest.getInstance(), scenario);   
        return testResult;
    }
    
    private OntologyTestResult execBateryTest(List<ScenarioTest> listScenario){ 
        OntologyTestCase testCase = new OntologyTestCase();
        testResult = new OntologyTestResult();
        testCase.runListaScenario(testResult, CollectionTest.getInstance(), listScenario);
        return testResult;
    }
    
    @Override
    protected void done() {
        OntologyTestResult treeResult = null;
        try {
            treeResult = get();
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
        new TreeResults(treeResult,getName()); 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}

