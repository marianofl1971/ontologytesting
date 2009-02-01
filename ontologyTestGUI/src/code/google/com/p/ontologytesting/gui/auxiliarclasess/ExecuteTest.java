/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.gui.auxiliarclasess;

import code.google.com.p.ontologytesting.gui.MainApplicationJFrame;
import code.google.com.p.ontologytesting.model.exectests.*;
import code.google.com.p.ontologytesting.model.*;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.InterfaceReasoner;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.Reasoner;
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
    private Reasoner jenaInterface;   
    private InterfaceReasoner jena;
    private OntologyTestCase f;
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
        testResult = new OntologyTestResult();
        if(this.isCancelled()==false){
            jenaInterface = new Reasoner();
            jena = jenaInterface.getReasoner();
            if(jenaInterface.isCargado()==true){
                if(scenario.getTipoTest().name().equals("INST")){
                    f = new OntologyTestInstantiation();
                    InterfaceReasoner jenaAux = f.setUpOntology(scenario, ont, ns, jena);
                    f.run(testResult, ont, ns, scenario, jenaAux);
                    f.tearDownOntology(jenaAux);
                }else if(scenario.getTipoTest().name().equals("RET")){
                    f = new OntologyTestRetrieval();
                    InterfaceReasoner jenaAux = f.setUpOntology(scenario, ont, ns, jena);
                    f.run(testResult, ont, ns, scenario, jenaAux);
                    f.tearDownOntology(jenaAux);    
                }else if(scenario.getTipoTest().name().equals("REAL")){
                    f = new OntologyTestRealization();
                    InterfaceReasoner jenaAux = f.setUpOntology(scenario, ont, ns, jena);
                    f.run(testResult, ont, ns, scenario, jenaAux);
                    f.tearDownOntology(jenaAux);
                }else if(scenario.getTipoTest().name().equals("SAT")){
                    f = new OntologyTestSatisfactibility();
                    InterfaceReasoner jenaAux = f.setUpOntology(scenario, ont, ns, jena);
                    f.run(testResult, ont, ns, scenario, jenaAux);
                    f.tearDownOntology(jenaAux);
                }else if(scenario.getTipoTest().name().equals("CLAS")){
                    f = new OntologyTestClassification();
                    InterfaceReasoner jenaAux = f.setUpOntology(scenario, ont, ns, jena);
                    f.run(testResult, ont, ns, scenario, jenaAux);
                    f.tearDownOntology(jenaAux);
                }else if(scenario.getTipoTest().name().equals("SPARQL")){
                    f = new OntologyTestSparql();
                    InterfaceReasoner jenaAux = f.setUpOntology(scenario, ont, ns, jena);
                    f.run(testResult, ont, ns, scenario, jenaAux);
                    f.tearDownOntology(jenaAux);
                }
            }
        }
        return testResult;
    }
    
    private OntologyTestResult execBateryTest(List<ScenarioTest> listScenario){ 
        testResult = new OntologyTestResult();
        int size = listScenario.size();
        int div = 100/size;
        for(int i=0;i<size;i++){
            ScenarioTest scen = listScenario.get(i);
            if(this.isCancelled()==false){
                jenaInterface = new Reasoner();
                jena = jenaInterface.getReasoner();
                if(jenaInterface.isCargado()==true){
                    if(scen.getTipoTest().name().equals("INST")){
                        f = new OntologyTestInstantiation();
                        InterfaceReasoner jenaAux = f.setUpOntology(scen, ont, ns, jena);
                        f.run(testResult, ont, ns, scen, jenaAux);
                        f.tearDownOntology(jenaAux);
                    }else if(scen.getTipoTest().name().equals("RET")){
                        f = new OntologyTestRetrieval();
                        InterfaceReasoner jenaAux = f.setUpOntology(scen, ont, ns, jena);
                        f.run(testResult, ont, ns, scen, jenaAux);
                        f.tearDownOntology(jenaAux);    
                    }else if(scen.getTipoTest().name().equals("REAL")){
                        f = new OntologyTestRealization();
                        InterfaceReasoner jenaAux = f.setUpOntology(scen, ont, ns, jena);
                        f.run(testResult, ont, ns, scen, jenaAux);
                        f.tearDownOntology(jenaAux);
                    }else if(scen.getTipoTest().name().equals("SAT")){
                        f = new OntologyTestSatisfactibility();
                        InterfaceReasoner jenaAux = f.setUpOntology(scen, ont, ns, jena);
                        f.run(testResult, ont, ns, scen, jenaAux);
                        f.tearDownOntology(jenaAux);
                    }else if(scen.getTipoTest().name().equals("CLAS")){
                        f = new OntologyTestClassification();
                        InterfaceReasoner jenaAux = f.setUpOntology(scen, ont, ns, jena);
                        f.run(testResult, ont, ns, scen, jenaAux);
                        f.tearDownOntology(jenaAux);
                    }else if(scen.getTipoTest().name().equals("SPARQL")){
                        f = new OntologyTestSparql();
                        InterfaceReasoner jenaAux = f.setUpOntology(scen, ont, ns, jena);
                        f.run(testResult, ont, ns, scen, jenaAux);
                        f.tearDownOntology(jenaAux);
                    }
                }
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

