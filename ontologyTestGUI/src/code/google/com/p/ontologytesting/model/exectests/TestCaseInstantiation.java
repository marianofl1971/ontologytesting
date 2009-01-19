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
public class TestCaseInstantiation extends OntologyTestCase{

    private Reasoner jenaInterface = new Reasoner();
    private InterfaceReasoner jena = jenaInterface.getReasoner();
    private String patron3="[\\(|\\)|,| |.]";
    private String res[],clasF="",indF="",query,resQueryExpected="",resObtenidoInst="";
    private QueryOntology qo = null;
    private ListIterator liQuery;
    
    public TestCaseInstantiation(InterfaceReasoner jena){
        this.jena=jena;
    }
    
    @Override
    public void run(OntologyTestResult testresult, String ont, String ns, ScenarioTest scenario) throws InvalidOntologyException {
        List<QueryOntology> queryTest = scenario.getQueryTest();
        liQuery = queryTest.listIterator();
        while(liQuery.hasNext()){  
            qo = (QueryOntology) liQuery.next();
            query = qo.getQuery();
            resQueryExpected = qo.getResultexpected();
            res = query.split(patron3);
            clasF = res[0];
            indF = res[1];
            resObtenidoInst = jena.instantiation(ns, clasF, indF);
            if(!resObtenidoInst.equals(resQueryExpected)){
                testresult.addOntologyFailureQuery(scenario.getNombre(), qo, resObtenidoInst, scenario.getTipoTest());
            }else{
                testresult.addOntologyPassedQuery(scenario.getNombre(), qo, resObtenidoInst, scenario.getTipoTest());
            }
        }
    }

}
