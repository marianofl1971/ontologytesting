/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package JUnuitTests;

import code.google.com.p.ontologytesting.model.QueryOntology;
import code.google.com.p.ontologytesting.model.ScenarioTest;
import code.google.com.p.ontologytesting.model.ValidarTests;
import junit.framework.TestCase;

/**
 *
 * @author sara.garcia
 */
public class ValidarTestsJUnitTest extends TestCase {
    
    ScenarioTest scenario;
    ValidarTests validar = new ValidarTests();
    QueryOntology query1;
    QueryOntology query2;
    QueryOntology query3;
    QueryOntology query4;
    QueryOntology query5;
    QueryOntology query6;
    QueryOntology query7;
    QueryOntology query8;
    boolean res=true;
    
    public ValidarTestsJUnitTest(String testName) {
        super(testName);
    }            

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        scenario = new ScenarioTest();
        query1 = new QueryOntology("sara","true,maria","");
        query2 = new QueryOntology("pedro","false.lucas,maria","");
        query3 = new QueryOntology("paco","true lucas","");
        query4 = new QueryOntology("sofia"," false.sofia","");
        query5 = new QueryOntology("sara lucas","false","");
        query6 = new QueryOntology("Wife paco lucia","false","");
        query7 = new QueryOntology("Wife.marry","true laos","");
        query8 = new QueryOntology("Wife marry)","truesar","");
        /*ArrayList<QueryOntology> listQuerys = new ArrayList<QueryOntology>();
        listQuerys.add(query1);
        listQuerys.add(query2);
        listQuerys.add(query3);
        listQuerys.add(query4);
        listQuerys.add(query5);
        listQuerys.add(query6);
        listQuerys.add(query7);
        listQuerys.add(query8);
        scenario.setQueryTest(listQuerys);*/
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        scenario = null;
    }

    // TODO add test methods here. The name must begin with 'test'. For example:
    public void testInstanciacion() {
        res = validar.validarTestInstanciacion(query1);
        assertTrue(res==true);
        res = validar.validarTestInstanciacion(query2);
        assertTrue(res==true);
        res = validar.validarTestInstanciacion(query3);
        assertTrue(res==true);
        res = validar.validarTestInstanciacion(query4);
        assertTrue(res==true);
        res = validar.validarTestInstanciacion(query5);
        assertTrue(res==false);
        res = validar.validarTestInstanciacion(query6);
        assertTrue(res==false);
        res = validar.validarTestInstanciacion(query7);
        assertTrue(res==false);
        res = validar.validarTestInstanciacion(query8);
        assertTrue(res==false);
    }
    
    public void testRetrieval() {
        res = validar.validarTestRetrieval(query1);
        assertTrue(res==true);
        res = validar.validarTestRetrieval(query2);
        assertTrue(res==true);
        res = validar.validarTestRetrieval(query3);
        assertTrue(res==true);
        res = validar.validarTestRetrieval(query4);
        assertTrue(res==true);
        res = validar.validarTestRetrieval(query5);
        assertTrue(res==false);
        res = validar.validarTestRetrieval(query6);
        assertTrue(res==false);
        res = validar.validarTestRetrieval(query7);
        assertTrue(res==false);
        res = validar.validarTestRetrieval(query8);
        assertTrue(res==false);
    }
    
    public void testRealizacion() {
        res = validar.validarTestRealizacion(query1);
        assertTrue(res==true);
        res = validar.validarTestRealizacion(query2);
        assertTrue(res==true);
        res = validar.validarTestRealizacion(query3);
        assertTrue(res==true);
        res = validar.validarTestRealizacion(query4);
        assertTrue(res==true);
        res = validar.validarTestRealizacion(query5);
        assertTrue(res==false);
        res = validar.validarTestRealizacion(query6);
        assertTrue(res==false);
        res = validar.validarTestRealizacion(query7);
        assertTrue(res==false);
        res = validar.validarTestRealizacion(query8);
        assertTrue(res==false);
    }
    
    public void testSatisfactibilidad() {
        res = validar.validarTestSatisfactibilidad(query1);
        assertTrue(res==true);
        res = validar.validarTestSatisfactibilidad(query2);
        assertTrue(res==true);
        res = validar.validarTestSatisfactibilidad(query3);
        assertTrue(res==true);
        res = validar.validarTestSatisfactibilidad(query4);
        assertTrue(res==true);
        res = validar.validarTestSatisfactibilidad(query5);
        assertTrue(res==false);
        res = validar.validarTestSatisfactibilidad(query6);
        assertTrue(res==false);
        res = validar.validarTestSatisfactibilidad(query7);
        assertTrue(res==false);
        res = validar.validarTestSatisfactibilidad(query8);
        assertTrue(res==false);
    }
    
    public void testClasificacion() {
        res = validar.validarTestClasificacion(query1);
        assertTrue(res==true);
        res = validar.validarTestClasificacion(query2);
        assertTrue(res==true);
        res = validar.validarTestClasificacion(query3);
        assertTrue(res==true);
        res = validar.validarTestClasificacion(query4);
        assertTrue(res==true);
        res = validar.validarTestClasificacion(query5);
        assertTrue(res==false);
        res = validar.validarTestClasificacion(query6);
        assertTrue(res==false);
        res = validar.validarTestClasificacion(query7);
        assertTrue(res==false);
        res = validar.validarTestClasificacion(query8);
        assertTrue(res==false);
    }

}
