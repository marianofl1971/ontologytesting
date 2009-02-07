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
public class OntologyTestInstantiation extends OntologyTestCase{

    @Override
    public void run(OntologyTestResult testresult, String ont, String ns, ScenarioTest scenario,InterfaceReasoner jena) throws InvalidOntologyException {
        String patron3="[\\(|\\)|,]";
        String res[],clasF="",indF="",query,resQueryExpected="",resObtenidoInst="";
        QueryOntology qo = null;
        ListIterator liQuery;
        List<QueryOntology> queryTest = scenario.getQueryTest();
        liQuery = queryTest.listIterator();
        while(liQuery.hasNext()){  
            qo = (QueryOntology) liQuery.next();
            query = qo.getQuery();
            resQueryExpected = qo.getResultexpected();
            res = query.trim().split(patron3);
            clasF = res[0].trim();
            indF = res[1].trim();
            resObtenidoInst = jena.instantiation(ns, clasF, indF);
            if(!resObtenidoInst.equals(resQueryExpected)){
                testresult.addOntologyFailureQuery(scenario.getNombre(), qo, resObtenidoInst, scenario.getTipoTest());
            }else{
                testresult.addOntologyPassedQuery(scenario.getNombre(), qo, resObtenidoInst, scenario.getTipoTest());
            }
        }
    }

}

