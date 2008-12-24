/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.gui.auxiliarclasess;

import code.google.com.p.ontologytesting.gui.MainApplicationJFrame;
import code.google.com.p.ontologytesting.model.exectests.*;
import code.google.com.p.ontologytesting.model.*;
import java.awt.Toolkit;
import javax.swing.SwingWorker;
import java.util.List;
import javax.swing.JOptionPane;

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
        setProgress(0);
        if(scenario!=null){
            treeResult = execOneTest(scenario);
            setName(scenario.getNombre());
        }else if(listScenario!=null){
            treeResult = execBateryTest(listScenario);
            setName("Batería de Tests");
        } 
        setProgress(100);
        return treeResult;
    }

    private OntologyTestResult execOneTest(ScenarioTest scenario){     
        OntologyTestCase testCase = new OntologyTestCase();
        testResult = new OntologyTestResult();
        if(this.isCancelled()==false){
            testCase.run(testResult, CollectionTest.getInstance(), scenario);   
        }
        return testResult;
    }
    
    private OntologyTestResult execBateryTest(List<ScenarioTest> listScenario){ 
        OntologyTestCase testCase = new OntologyTestCase();
        testResult = new OntologyTestResult();
        int size = listScenario.size();
        int div = 100/size;
        for(int i=0;i<size;i++){
            if(this.isCancelled()==false){
                testCase.run(testResult, CollectionTest.getInstance(), listScenario.get(i));
                setProgress(getProgress()+div);
            }
        }
        return testResult;
    }
    
    @Override
    protected void done() {
        if(this.isCancelled()==false){
            Toolkit.getDefaultToolkit().beep();
            OntologyTestResult treeResult = null;
            try {
                treeResult = get();
            } catch (Exception ignore) {
                ignore.printStackTrace();
            }
            if(treeResult!=null){
                new TreeResults(treeResult,getName()); 
                JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(), "Acción Realizada","Confirm Message",JOptionPane.INFORMATION_MESSAGE);
            }else{
                this.cancel(true);
                this.setProgress(100);
                JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(), "Error de Ejecución","Erro Message",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
