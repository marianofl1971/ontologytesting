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

    private static boolean testInstSatGuardado;
    private static boolean testRetClasGuardado;
    private static boolean testRealGuardado;
    private static boolean testSparqlGuardado;
    private static boolean testInstSelect;
    private static boolean testRetSelect;
    private static boolean testRealSelect;
    private static boolean testSatSelect;
    private static boolean testClasSelect;
    private static boolean testSparqlSelect;

    public static int testSeleccionado(){
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
    
    public static void inicializarSeleccionados(){
        setTestClasSelect(false);
        setTestInstSelect(false);
        setTestRealSelect(false);
        setTestRetSelect(false);
        setTestSatSelect(false);
        setTestSparqlSelect(false);
    }
    
    public static void inicializarGuardados(){
        setTestInstSatGuardado(true);
        setTestRealGuardado(true);
        setTestRetClasGuardado(true);
        setTestSparqlGuardado(true);
    }
    
    public static int cualSinGuardar(){
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
    
    public static boolean algunTestSinGuardar(){
        if(getTestRetClasGuardado()==false || getTestInstSatGuardado()==false || getTestRealGuardado()==false
            || getTestSparqlGuardado()==false){
            return true;    
        }else{
            return false;
        }
    }
    
    public static boolean getTestInstSatGuardado() {
        return testInstSatGuardado;
    }

    public static void setTestInstSatGuardado(boolean aTestInstGuardado) {
        testInstSatGuardado = aTestInstGuardado;
    }

    public static boolean getTestRetClasGuardado() {
        return testRetClasGuardado;
    }

    public static void setTestRetClasGuardado(boolean aTestRetGuardado) {
        testRetClasGuardado = aTestRetGuardado;
    }

    public static boolean getTestRealGuardado() {
        return testRealGuardado;
    }

    public static void setTestRealGuardado(boolean aTestRealGuardado) {
        testRealGuardado = aTestRealGuardado;
    }

    public static boolean getTestSparqlGuardado() {
        return testSparqlGuardado;
    }

    public static void setTestSparqlGuardado(boolean aTestSparqlGuardado) {
        testSparqlGuardado = aTestSparqlGuardado;
    }

    public static boolean getTestInstSelect() {
        return testInstSelect;
    }

    public static void setTestInstSelect(boolean aTestInstSelect) {
        testInstSelect = aTestInstSelect;
    }

    public static boolean getTestRetSelect() {
        return testRetSelect;
    }

    public static void setTestRetSelect(boolean aTestRetSelect) {
        testRetSelect = aTestRetSelect;
    }

    public static boolean getTestRealSelect() {
        return testRealSelect;
    }

    public static void setTestRealSelect(boolean aTestRealSelect) {
        testRealSelect = aTestRealSelect;
    }

    public static boolean getTestSatSelect() {
        return testSatSelect;
    }

    public static void setTestSatSelect(boolean aTestSatSelect) {
        testSatSelect = aTestSatSelect;
    }

    public static boolean getTestClasSelect() {
        return testClasSelect;
    }

    public static void setTestClasSelect(boolean aTestClasSelect) {
        testClasSelect = aTestClasSelect;
    }

    public static boolean getTestSparqlSelect() {
        return testSparqlSelect;
    }

    public static void setTestSparqlSelect(boolean aTestSparqlSelect) {
        testSparqlSelect = aTestSparqlSelect;
    }
    


}
