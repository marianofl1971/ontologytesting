/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.guiNew;

import code.google.com.p.ontologytesting.model.CollectionTest;
import code.google.com.p.ontologytesting.model.Instancias;
import code.google.com.p.ontologytesting.model.OntologyTestCase;
import code.google.com.p.ontologytesting.model.OntologyTestResult;
import code.google.com.p.ontologytesting.model.ScenarioTest;
import code.google.com.p.ontologytesting.model.jenainterfaz.ExceptionReadOntology;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author sara.garcia
 */
public class Utils {
    
    private static OntologyTestResult testResult;
    private OntologyTestCase testCase;
    
    public ScenarioTest buscarScenario(List<ScenarioTest> scenario, String name){
        for(int i=0;i<scenario.size();i++){
            String nombre = scenario.get(i).getNombre();
            if(nombre.equals(name)){
                return scenario.get(i);
            }
        }
        return null;
    }
    
    public Instancias buscarInstancias(List<Instancias> inst, String name){
        for(int i=0;i<inst.size();i++){
            String nombre = inst.get(i).getNombre();
            if(nombre.equals(name)){
                return inst.get(i);
            }
        }
        return null;
    }
    
    public boolean testYaExiste(String nombre){
        ListIterator li;
        List<ScenarioTest> lista = CollectionTest.getInstance().getScenariotest();
        li = lista.listIterator();
        while(li.hasNext()){
            ScenarioTest s = (ScenarioTest) li.next();
            String n = s.getNombre();
            if(n.equals(nombre)){
                return true;
            }
        }
        return false;
    }
    
    public void ejecutarUnTest(ScenarioTest scenario){
        testCase = new OntologyTestCase();
        testResult = new OntologyTestResult();
        try{
            testCase.runScenario(testResult, CollectionTest.getInstance(), scenario);   
            new TreeResults(testResult);
        } catch (ExceptionReadOntology ex) {
            new ExceptionReadOntology("La ontologia introducida no es valida." +
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
            new ExceptionReadOntology("La ontologia introducida no es valida." +
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
            new ExceptionReadOntology("La ontologia introducida no es valida." +
            "\nSolo pueden realizarse tests sobre documentos owl consistentes");
        }
    }
    
    

}
