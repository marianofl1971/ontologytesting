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
public class OntologyTestSatisfactibility extends OntologyTestCase{

    private String patron3="[\\(|\\)|,]";
    private String res[],concepto="",loincluye="",query,resQueryExpected="",resObtenidoSatisf="";
    private QueryOntology qo = null;
    private ListIterator liQuery;
    
    public OntologyTestSatisfactibility(){}

    @Override
    public void run(OntologyTestResult testresult, String ont, String ns, ScenarioTest scenario,InterfaceReasoner jena) throws InvalidOntologyException {
        List<QueryOntology> queryTest = scenario.getQueryTest();
        liQuery = queryTest.listIterator();
        while(liQuery.hasNext()){  
            qo = (QueryOntology) liQuery.next();
            query = qo.getQuery();
            resQueryExpected = qo.getResultexpected();
            res = query.trim().split(patron3);
            concepto = res[0].trim();
            loincluye = res[1].trim();
            resObtenidoSatisf = jena.satisfactibility(ns, concepto, loincluye);
            if(!resObtenidoSatisf.equals(resQueryExpected)){
                testresult.addOntologyFailureQuery(scenario.getNombre(), qo, resObtenidoSatisf,scenario.getTipoTest());
            }else{
                testresult.addOntologyPassedQuery(scenario.getNombre(), qo,resObtenidoSatisf, scenario.getTipoTest());
            }
        }
    }

}

