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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author sara.garcia
 */
public class OntologyTestClassification extends OntologyTestCase{

    private String patron4="[,|\\n| ]";
    private String query,resQueryExpected="";
    private List<String> resObtenidoClas = new ArrayList<String>();
    private QueryOntology qo = null;
    private UtilsTestCase utils = new UtilsTestCase();
    private ListIterator liQuery;
    
    public OntologyTestClassification(){}

    @Override
    public void run(OntologyTestResult testresult, String ont, String ns, ScenarioTest scenario,InterfaceReasoner jena) throws InvalidOntologyException {
       if(scenario.getTipoTest().name().equals("CLAS")){
            List<QueryOntology> queryTest = scenario.getQueryTest();
            liQuery = queryTest.listIterator();
            while(liQuery.hasNext()){         
                qo = (QueryOntology) liQuery.next();
                query = qo.getQuery();
                resQueryExpected = qo.getResultexpected();
                String[] queryMod = resQueryExpected.split(patron4);
                ArrayList<String> querySat = new ArrayList<String>();
                for(int k=0;k<queryMod.length;k++){
                    querySat.add(queryMod[k]);
                }
                resObtenidoClas = jena.classification(ns, query);
                if(resObtenidoClas==null || resObtenidoClas.size()==0){
                    testresult.addOntologyFailureQuery(scenario.getNombre(),qo,"El individuo introducido no es una instancia para el modelo",scenario.getTipoTest());
                }else{
                    Collections.sort(resObtenidoClas);
                    Collections.sort(querySat);
                    if(!utils.comparaArray(querySat, resObtenidoClas)){
                        testresult.addOntologyFailureQuery(scenario.getNombre(), qo, resObtenidoClas.toString(),scenario.getTipoTest());
                    }else{
                        testresult.addOntologyPassedQuery(scenario.getNombre(), qo,resObtenidoClas.toString(), scenario.getTipoTest());
                    }
                }
            }
        }
    }
}

