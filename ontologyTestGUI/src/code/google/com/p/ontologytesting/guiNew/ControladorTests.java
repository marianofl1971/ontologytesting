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

    private static boolean testInstGuardado;
    private static boolean testRetGuardado;
    private static boolean testRealGuardado;
    private static boolean testSatGuardado;
    private static boolean testClasGuardado;
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
        setTestClasGuardado(true);
        setTestInstGuardado(true);
        setTestRealGuardado(true);
        setTestRetGuardado(true);
        setTestSatGuardado(true);
        setTestSparqlGuardado(true);
    }
    
    public static int cualSinGuardar(){
        if(getTestInstGuardado()==false && getTestInstSelect()==true){
            return 0;
        }else if(getTestRetGuardado()==false && getTestRetSelect()==true){
            return 1;
        }else if(getTestRealGuardado()==false && getTestRealSelect()==true){
            return 2;
        }else if(getTestSatGuardado()==false && getTestSatSelect()==true){
            return 3;
        }else if(getTestClasGuardado()==false && getTestClasSelect()==true){
            return 4;
        }else{
            return 5;
        }
    }
    
    public static boolean algunTestSinGuardar(){
        if(getTestClasGuardado()==false || getTestInstGuardado()==false || getTestRealGuardado()==false
            || getTestRetGuardado()==false || getTestSatGuardado()==false
            || getTestSparqlGuardado()==false){
            return true;    
        }else{
            return false;
        }
    }
    
    public static boolean getTestInstGuardado() {
        return testInstGuardado;
    }

    public static void setTestInstGuardado(boolean aTestInstGuardado) {
        testInstGuardado = aTestInstGuardado;
    }

    public static boolean getTestRetGuardado() {
        return testRetGuardado;
    }

    public static void setTestRetGuardado(boolean aTestRetGuardado) {
        testRetGuardado = aTestRetGuardado;
    }

    public static boolean getTestRealGuardado() {
        return testRealGuardado;
    }

    public static void setTestRealGuardado(boolean aTestRealGuardado) {
        testRealGuardado = aTestRealGuardado;
    }

    public static boolean getTestSatGuardado() {
        return testSatGuardado;
    }

    public static void setTestSatGuardado(boolean aTestSatGuardado) {
        testSatGuardado = aTestSatGuardado;
    }

    public static boolean getTestClasGuardado() {
        return testClasGuardado;
    }

    public static void setTestClasGuardado(boolean aTestClasGuardado) {
        testClasGuardado = aTestClasGuardado;
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
