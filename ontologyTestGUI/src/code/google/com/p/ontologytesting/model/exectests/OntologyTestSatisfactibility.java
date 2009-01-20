/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model.exectests;

import code.google.com.p.ontologytesting.model.QueryOntology;
import code.google.com.p.ontologytesting.model.ScenarioTest;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.InterfaceReasoner;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.InvalidOntologyException;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.Reasoner;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author sara.garcia
 */
public class OntologyTestSatisfactibility implements OntologyTest{

    private Reasoner jenaInterface = new Reasoner();
    private InterfaceReasoner jena = jenaInterface.getReasoner();
    private String patron3="[\\(|\\)|,| |.]";
    private String res[],concepto="",loincluye="",query,resQueryExpected="",resObtenidoSatisf="";
    private QueryOntology qo = null;
    private ListIterator liQuery;
    
    public OntologyTestSatisfactibility(){}

    @Override
    public void run(OntologyTestResult testresult, String ont, String ns, ScenarioTest scenario) throws InvalidOntologyException {
        if(scenario.getTipoTest().name().equals("SAT")){
            List<QueryOntology> queryTest = scenario.getQueryTest();
            liQuery = queryTest.listIterator();
            while(liQuery.hasNext()){  
                qo = (QueryOntology) liQuery.next();
                query = qo.getQuery();
                resQueryExpected = qo.getResultexpected();
                res = query.split(patron3);
                concepto = res[0];
                loincluye = res[1];
                resObtenidoSatisf = jena.satisfactibility(ns, concepto, loincluye);
                if(!resObtenidoSatisf.equals(resQueryExpected)){
                    testresult.addOntologyFailureQuery(scenario.getNombre(), qo, resObtenidoSatisf,scenario.getTipoTest());
                }else{
                    testresult.addOntologyPassedQuery(scenario.getNombre(), qo,resObtenidoSatisf, scenario.getTipoTest());
                }
            }
        }
    }

}

