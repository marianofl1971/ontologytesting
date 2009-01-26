/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model.exectests;

import code.google.com.p.ontologytesting.model.ScenarioTest;
import code.google.com.p.ontologytesting.model.SparqlQueryOntology;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.InterfaceReasoner;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.InvalidOntologyException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author sara.garcia
 */
public class OntologyTestSparql extends OntologyTestCase{

    private List<ExecQuerySparql> listaResultEsperada = new ArrayList<ExecQuerySparql>();
    private List<ExecQuerySparql> listaResultObtenida = new ArrayList<ExecQuerySparql>();
    private String patron5="[\\n|\\v]";
    private String patron6="[,\\)]";
    private ArrayList<String> esperado = new ArrayList<String>();
    private ArrayList<String> obtenido = new ArrayList<String>();
    private ListIterator liSparql;
    private String resQueryExpected="";
    private SparqlQueryOntology sparqlquery = null;
    private String[] res;
    private int fallo=0;
    private UtilsTestCase utils = new UtilsTestCase();

    public OntologyTestSparql(){}

    public void run(OntologyTestResult testresult, String ont, String ns, ScenarioTest scenario,InterfaceReasoner jena) throws InvalidOntologyException {
        if(scenario.getTipoTest().name().equals("SPARQL")){
            List<SparqlQueryOntology> sparqlTest = scenario.getSparqlQuerys();
            listaResultObtenida = new ArrayList<ExecQuerySparql>();
            liSparql = sparqlTest.listIterator();
            while(liSparql.hasNext()){    
                listaResultEsperada = new ArrayList<ExecQuerySparql>();
                sparqlquery = (SparqlQueryOntology) liSparql.next();
                String sparqlQuery = sparqlquery.getQuery();
                resQueryExpected = sparqlquery.getResultexpected();
                res = resQueryExpected.split(patron5);
                for(int k=0; k<res.length;k++){
                    if(!res[k].equals(""))
                    {
                        ExecQuerySparql execQuery = new ExecQuerySparql();
                        String[] select = res[k].trim().split("\\(");
                        execQuery.setNombreSelect(select[0]); 
                        String[] subRes = select[1].trim().split(patron6);
                        for(int m=0; m<subRes.length;m++){
                            execQuery.getDatos().add(subRes[m].trim());
                        }
                        listaResultEsperada.add(execQuery);
                    }
                }
                listaResultObtenida = jena.testSPARQL(sparqlQuery);
                if(listaResultObtenida!=null && listaResultObtenida.size() > 0){
                    esperado = new ArrayList<String>();
                    obtenido = new ArrayList<String>();
                    String contenidoObtenido = "",contenidoEsperado="";
                    fallo=0;
                    int tam = listaResultObtenida.get(0).getDatos().size();
                    int tamBis = listaResultEsperada.get(0).getDatos().size();
                    if((listaResultEsperada.size()==listaResultObtenida.size()) && (tam==tamBis)){
                        for(int n=0;n<tam;n++){
                            for(int t=0; t<listaResultObtenida.size(); t++){
                                contenidoObtenido = contenidoObtenido+listaResultObtenida.get(t).getDatos().get(n);
                                contenidoEsperado = contenidoEsperado+listaResultEsperada.get(t).getDatos().get(n);
                            } 
                            esperado.add(contenidoEsperado);
                            obtenido.add(contenidoObtenido);
                            contenidoObtenido = "";
                            contenidoEsperado = "";
                    }
                    }else{
                        fallo=1;
                    }
                }else if(listaResultObtenida.size()==0){
                    if((listaResultEsperada.size()!=listaResultObtenida.size())){
                        fallo=1;
                    }
                }
                if(utils.contieneTodosIguales(esperado, obtenido)==false || fallo==1){
                    if(listaResultObtenida!=null){
                        testresult.addOntologyFailureSparql(scenario.getNombre(),sparqlquery,listaResultEsperada,listaResultObtenida, scenario.getTipoTest());
                    }
                }else{
                    testresult.addOntologyPassedSparql(scenario.getNombre(),sparqlquery,listaResultEsperada,listaResultObtenida, scenario.getTipoTest());
                }
            }
        }
    }
}
