/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sara.garcia
 */
public class Tests {
    
    public enum TestName{Instanciacion,Retrieval,Realizacion,Satisfactibilidad,
                Clasificacion,Sparql};
    private final TestName testName;
    private static final List<Tests> listTestName = new ArrayList<Tests>();
    
    private Tests(TestName testName) {
        this.testName = testName;
    }

    public TestName testName() { 
        return testName; 
    }

    static {
        for (TestName testName : TestName.values())
            listTestName.add(new Tests(testName));

    }

    public static ArrayList<Tests> newTests() {
        return new ArrayList<Tests>(listTestName); // Return copy of prototype deck
    }

                
}
