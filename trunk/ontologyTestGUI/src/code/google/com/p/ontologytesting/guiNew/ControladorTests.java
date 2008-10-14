/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.guiNew;

/**
 *
 * @author saruskas
 */
public class ControladorTests {

    private boolean testInstSatGuardado;
    private boolean testRetClasGuardado;
    private boolean testRealGuardado;
    private boolean testSparqlGuardado;
    private boolean testInstSelect;
    private boolean testRetSelect;
    private boolean testRealSelect;
    private boolean testSatSelect;
    private boolean testClasSelect;
    private boolean testSparqlSelect;
    private static ControladorTests controlador = null;
 
    private ControladorTests() {
        this.testInstSatGuardado = true;
        this.testRetClasGuardado = true;
        this.testRealGuardado = true;
        this.testSparqlGuardado = true;
        this.testInstSelect = true;
        this.testRetSelect = true;
        this.testRealSelect = true;
        this.testSatSelect = true;
        this.testClasSelect = true;
        this.testSparqlSelect = true;
    }
 
    private synchronized static void createControlador() {
        if (controlador == null) { 
            controlador = new ControladorTests();
        }
    }
 
    public static ControladorTests getInstance() {
        if (controlador == null) createControlador();
        return controlador;
    }
    
    public int testSeleccionado(){
        if(getTestInstSelect()==true){
            return 0;
        }else if(getTestRetSelect()==true){
            return 1;
        }else if(getTestRealSelect()==true){
            return 2;
        }else if(getTestSatSelect()==true){
            return 3;
        }else if(getTestClasSelect()==true){
            return 4;
        }else if(getTestSparqlSelect()==true){
            return 5;
        }else{
            return 10;
        }
    }
    
    public void inicializarSeleccionados(){
        setTestClasSelect(false);
        setTestInstSelect(false);
        setTestRealSelect(false);
        setTestRetSelect(false);
        setTestSatSelect(false);
        setTestSparqlSelect(false);
    }
    
    public void inicializarGuardados(){
        setTestInstSatGuardado(true);
        setTestRealGuardado(true);
        setTestRetClasGuardado(true);
        setTestSparqlGuardado(true);
    }
    
    public int cualSinGuardar(){
        if(getTestInstSatGuardado()==false && getTestInstSelect()==true){
            return 0;
        }else if(getTestRetClasGuardado()==false && getTestRetSelect()==true){
            return 1;
        }else if(getTestRealGuardado()==false && getTestRealSelect()==true){
            return 2;
        }else if(getTestInstSatGuardado()==false && getTestSatSelect()==true){
            return 3;
        }else if(getTestRetClasGuardado()==false && getTestClasSelect()==true){
            return 4;
        }else{
            return 5;
        }
    }
    
    public boolean algunTestSinGuardar(){
        if(getTestRetClasGuardado()==false || getTestInstSatGuardado()==false || getTestRealGuardado()==false
            || getTestSparqlGuardado()==false){
            return true;    
        }else{
            return false;
        }
    }
    
    public void prepararTestInst(){
        inicializarGuardados();
        inicializarSeleccionados();
        setTestInstSatGuardado(false);
        setTestInstSelect(true);
    }
    
    public void prepararTestRet(){
        inicializarGuardados();
        inicializarSeleccionados();
        this.setTestRetClasGuardado(false);
        setTestRetSelect(true);
    }
    public void prepararTestReal(){
        inicializarGuardados();
        inicializarSeleccionados();
        setTestRealGuardado(false);
        setTestRealSelect(true);
    }
    
    public void prepararTestSat(){
        inicializarGuardados();
        inicializarSeleccionados();
        setTestInstSatGuardado(false);
        setTestSatSelect(true);
    }
    
    public void prepararTestClas(){
        inicializarGuardados();
        inicializarSeleccionados();
        setTestRetClasGuardado(false);
        setTestClasSelect(true);
    }
    
    public void prepararTestSparql(){
        inicializarGuardados();
        inicializarSeleccionados();
        setTestSparqlGuardado(false);
        setTestSparqlSelect(true);
    }
    
    public boolean getTestInstSatGuardado() {
        return testInstSatGuardado;
    }

    public void setTestInstSatGuardado(boolean aTestInstGuardado) {
        testInstSatGuardado = aTestInstGuardado;
    }

    public boolean getTestRetClasGuardado() {
        return testRetClasGuardado;
    }

    public void setTestRetClasGuardado(boolean aTestRetGuardado) {
        testRetClasGuardado = aTestRetGuardado;
    }

    public boolean getTestRealGuardado() {
        return testRealGuardado;
    }

    public void setTestRealGuardado(boolean aTestRealGuardado) {
        testRealGuardado = aTestRealGuardado;
    }

    public boolean getTestSparqlGuardado() {
        return testSparqlGuardado;
    }

    public void setTestSparqlGuardado(boolean aTestSparqlGuardado) {
        testSparqlGuardado = aTestSparqlGuardado;
    }

    public boolean getTestInstSelect() {
        return testInstSelect;
    }

    public void setTestInstSelect(boolean aTestInstSelect) {
        testInstSelect = aTestInstSelect;
    }

    public boolean getTestRetSelect() {
        return testRetSelect;
    }

    public void setTestRetSelect(boolean aTestRetSelect) {
        testRetSelect = aTestRetSelect;
    }

    public boolean getTestRealSelect() {
        return testRealSelect;
    }

    public void setTestRealSelect(boolean aTestRealSelect) {
        testRealSelect = aTestRealSelect;
    }

    public boolean getTestSatSelect() {
        return testSatSelect;
    }

    public void setTestSatSelect(boolean aTestSatSelect) {
        testSatSelect = aTestSatSelect;
    }

    public boolean getTestClasSelect() {
        return testClasSelect;
    }

    public void setTestClasSelect(boolean aTestClasSelect) {
        testClasSelect = aTestClasSelect;
    }

    public boolean getTestSparqlSelect() {
        return testSparqlSelect;
    }

    public void setTestSparqlSelect(boolean aTestSparqlSelect) {
        testSparqlSelect = aTestSparqlSelect;
    }
}
