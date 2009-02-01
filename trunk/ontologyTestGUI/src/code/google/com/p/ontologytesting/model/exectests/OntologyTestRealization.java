/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model.exectests;

import code.google.com.p.ontologytesting.model.QueryOntology;
import code.google.com.p.ontologytesting.model.ScenarioTest;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.InterfaceReasoner;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.InvalidOntologyException;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author sara.garcia
 */
public class OntologyTestRealization extends OntologyTestCase{

    private String query,resQueryExpected="",resObtenidoRealiz="";
    private QueryOntology qo = null;
    private ListIterator liQuery;
    
    public OntologyTestRealization(){}

    @Override
    public void run(OntologyTestResult testresult, String ont, String ns, ScenarioTest scenario,InterfaceReasoner jena) throws InvalidOntologyException {
        if(scenario.getTipoTest().name().equals("REAL")){
            List<QueryOntology> queryTest = scenario.getQueryTest();
            liQuery = queryTest.listIterator();
            while(liQuery.hasNext()){         
                qo = (QueryOntology) liQuery.next();
                query = qo.getQuery();
                resQueryExpected = qo.getResultexpected();
                resObtenidoRealiz = jena.realization(ns, query);
                if(!resObtenidoRealiz.equals(resQueryExpected)){
                    testresult.addOntologyFailureQuery(scenario.getNombre(), qo, resObtenidoRealiz,scenario.getTipoTest());
                }else{
                    testresult.addOntologyPassedQuery(scenario.getNombre(), qo,resObtenidoRealiz, scenario.getTipoTest());
                }
            }
        }
    }
    
    
    
}
