/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.gui.auxiliarclasess;

import code.google.com.p.ontologytesting.gui.Configuration;
import code.google.com.p.ontologytesting.gui.MainApplicationJFrame;
import code.google.com.p.ontologytesting.model.exectests.*;
import code.google.com.p.ontologytesting.model.*;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.InterfaceReasoner;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.Reasoner;
import java.awt.Toolkit;
import javax.swing.SwingWorker;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author sara.garcia
 */
public class ExecuteTest extends SwingWorker<OntologyTestResult, Void>{
    
    private OntologyTestResult testResult = new OntologyTestResult();
    private List<ScenarioTest> listScenario = null;
    private ScenarioTest scenario = null;
    private String name;
    private Reasoner jenaInterface;   
    private InterfaceReasoner jena;
    private String ont = CollectionTest.getInstance().getOntology();
    private String ns = CollectionTest.getInstance().getNamespace();
    
    private ExecuteTest() {}
    
    public ExecuteTest(ScenarioTest scenario) {
        this.scenario = scenario;
    }
    
    public ExecuteTest(List<ScenarioTest> listScenario) {
        this.listScenario = listScenario;
    }

    @Override
    protected OntologyTestResult doInBackground() throws Exception {
        setProgress(0);
        if(scenario!=null){
            testResult = execOneTest(scenario,testResult);
            setName(scenario.getNombre());
        }else if(listScenario!=null){
            testResult = execBateryTest(listScenario,testResult);
            setName(java.util.ResourceBundle.getBundle(Configuration.getPropiedades().getProperty("IDIOMA"),new Locale(Configuration.getPropiedades().getProperty("LOCALE"))).getString("Bateria_de_Tests"));
        } 
        setProgress(100);
        return testResult;
    }

    private OntologyTestResult execOneTest(ScenarioTest scenario, OntologyTestResult testResult){     
        if(this.isCancelled()==false){
            jenaInterface = new Reasoner();
            jena = jenaInterface.getReasoner();
            if(jenaInterface.isCargado()==true){
                OntologyTestCase ontologyTestCase = scenario.getOntologyTestCase();
                jena.addReasoner(ont);
                InterfaceReasoner jenaAux = ontologyTestCase.setUpOntology(scenario, ont, ns, jena);
                ontologyTestCase.run(testResult, ont, ns, scenario, jenaAux);
                ontologyTestCase.tearDownOntology(jenaAux);
            }
        }
        return testResult;
    }
    
    private OntologyTestResult execBateryTest(List<ScenarioTest> listScenario,OntologyTestResult testResult){ 
        int size = listScenario.size();
        int div = 100/size;
        for(int i=0;i<size;i++){
            ScenarioTest scen = listScenario.get(i);
            if(this.isCancelled()==false){
                execOneTest(scen,testResult);
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

