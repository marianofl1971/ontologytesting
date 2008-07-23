/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

/**
 *
 * @author sara.garcia
 */
public class OntologyTestPassed {
    
    private String fTestName;
    private String fTestNameUsuario;
    
    void OntologyTestPassed(String testNameUsuario, String testName){
        this.fTestName=testName;
        this.fTestNameUsuario=testNameUsuario;
    }
    void OntologyTestPassed(){
    }
    
    void addOntologyPassedTestQuery(String testNameUsuario, String testName){
        this.fTestName=testName;
        this.fTestNameUsuario=testNameUsuario;
    }
    
    void addOntologyPassedTestSparql(String testNameUsuario, String testName){
        this.fTestName=testName;
        this.fTestNameUsuario=testNameUsuario;
    }

    public String getTestName() {
        return fTestName;
    }

    public String getTestNameUsuario() {
        return fTestNameUsuario;
    }

}
