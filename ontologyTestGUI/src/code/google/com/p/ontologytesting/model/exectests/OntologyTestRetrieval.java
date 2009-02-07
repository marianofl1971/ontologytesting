/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model.exectests;

import code.google.com.p.ontologytesting.model.QueryOntology;
import code.google.com.p.ontologytesting.model.ScenarioTest;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.InterfaceReasoner;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.InvalidOntologyException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author sara.garcia
 */
public class OntologyTestRetrieval extends OntologyTestCase {

    private String patron4="[,|\\n]";
    private String query,resQueryExpected="";
    private List<String> resObtenidoRet = new ArrayList<String>();
    private QueryOntology qo = null;
    private UtilsTestCase utils = new UtilsTestCase();
    private ListIterator liQuery;
    
    public OntologyTestRetrieval(){}

    @Override
    public void run(OntologyTestResult testresult, String ont, String ns, ScenarioTest scenario,InterfaceReasoner jena) throws InvalidOntologyException {
        List<QueryOntology> queryTest = scenario.getQueryTest();
        liQuery = queryTest.listIterator();
        while(liQuery.hasNext()){         
            qo = (QueryOntology) liQuery.next();
            query = qo.getQuery().trim();
            resQueryExpected = qo.getResultexpected().trim();
            if(resQueryExpected.startsWith("[") && resQueryExpected.endsWith("]")){
                resQueryExpected = resQueryExpected.substring(1);
                int tam = resQueryExpected.length();
                resQueryExpected = resQueryExpected.substring(0, tam-1);
            }
            resObtenidoRet = jena.retieval(ns, query);
            String[] queryMod = resQueryExpected.split(patron4);
            ArrayList<String> queryRet = new ArrayList<String>();
            for(int k=0;k<queryMod.length;k++){
                queryRet.add(queryMod[k].trim());
            }
            if(resObtenidoRet==null || resObtenidoRet.size()==0){
                testresult.addOntologyFailureQuery(scenario.getNombre(), 
                        qo,"La clase introducida no es una " +
                        "instancia para el modelo",scenario.getTipoTest());
            }else{
                Collections.sort(resObtenidoRet);
                Collections.sort(queryRet);
                if(!utils.comparaArray(resObtenidoRet, queryRet)){
                    testresult.addOntologyFailureQuery(scenario.getNombre(),qo,resObtenidoRet.toString(),scenario.getTipoTest());
                }else{
                    testresult.addOntologyPassedQuery(scenario.getNombre(), qo,resObtenidoRet.toString(),scenario.getTipoTest());
                }
            }
        }
    }
    
    

}
