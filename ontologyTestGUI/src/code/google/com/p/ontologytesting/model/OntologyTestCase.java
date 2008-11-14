/*
 * OntologyTestCase.java
 * 
 * Created on 16-feb-2008, 14:44:25
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

import code.google.com.p.ontologytesting.model.ScenarioTest.TipoTest;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Saruskas
 */
public class OntologyTestCase implements OntologyTest{
    
    private Reasoner jenaInterface;   
    private InterfaceReasoner jena;
    private String patron1="",patron2="",patron3="",patron4="",patron5="",patron6="";
    private int fallo=0;
    private UtilsTestCase utils = new UtilsTestCase();
    private List<ExecQuerySparql> listaResultEsperada = new ArrayList<ExecQuerySparql>();
    private List<ExecQuerySparql> listaResultObtenida = new ArrayList<ExecQuerySparql>();

    protected void setUpOntology(ScenarioTest st, String ont, String ns) throws ExceptionReadOntology{  

        ListIterator liClass;
        ListIterator liProperties;
        String[] ciClas;
        String[] ciInd;
        String[] piClas;
        String[] piInd;

        patron1 = "[\\(|,|\n| ]";
        patron2 = "[\n| |\\)]";
        jenaInterface = new Reasoner();
        jena = jenaInterface.getReasoner();
        if(jenaInterface.isCargado()==true){
            jena.addReasoner(ont);
            Instancias instancias = st.getInstancias();
            List<ClassInstances> classInstances = instancias.getClassInstances();
            List<PropertyInstances> propertyInstances = instancias.getPropertyInstances();
            liClass = classInstances.listIterator();
            liProperties = propertyInstances.listIterator();

            while (liClass.hasNext()) {
                ClassInstances cla = (ClassInstances) liClass.next();
                String ci = cla.getClassInstance();
                ciClas = ci.split(patron1);
                ciInd = ciClas[1].split(patron2);
                jena.addInstanceClass(ns, ciClas[0], ciInd[0]);
            }

            while (liProperties.hasNext()) {
                PropertyInstances p = (PropertyInstances) liProperties.next();
                String pi = p.getPropertyInstance();
                piClas = pi.split(patron1);
                piInd = piClas[1].split(patron2);
                jena.addInstanceProperty(ns, piClas[0], piInd[0]);
            }       
        }
    }
    
    private void runOntologyTest(OntologyTestResult testresult, String ns, 
            ScenarioTest scenariotest) throws ExceptionReadOntology{
          
        patron3="[\\(|\\)|,| |.]";
        patron4="[,|\n| ]";
        patron5="[\n|\\t|\\v|\\s]";
        patron6="[.,\\s\\)]";
        ArrayList<String> esperado = new ArrayList<String>();
        ArrayList<String> obtenido = new ArrayList<String>();
        ListIterator liQuery,liSparql;
        String res[],clasF="",indF="",concepto="",loincluye="";
        TipoTest tip = scenariotest.getTipoTest();
        String nombreTestUsuario = scenariotest.getNombre();
        List<QueryOntology> queryTest = scenariotest.getQueryTest();
        List<SparqlQueryOntology> sparqlTest = scenariotest.getSparqlQuerys();
        listaResultObtenida = new ArrayList<ExecQuerySparql>();
        
        String resObtenidoInst="",resQueryExpected="", resObtenidoRealiz="",
                resObtenidoSatisf="";
        ArrayList<String> resObtenidoRet = new ArrayList<String>();
        ArrayList<String> resObtenidoClas = new ArrayList<String>();
        QueryOntology qo = null;
        SparqlQueryOntology sparqlquery = null;
                
        liQuery = queryTest.listIterator();
        liSparql = sparqlTest.listIterator();
        
        while(liQuery.hasNext()){         
                qo = (QueryOntology) liQuery.next();
                String query = qo.getQuery();
                resQueryExpected = qo.getResultexpected();
                if(tip.name().equals("INST")){
                    res = query.split(patron3);
                    clasF = res[0];
                    indF = res[1];
                    resObtenidoInst = jena.instantiation(ns, clasF, indF);
                    if(!resObtenidoInst.equals(resQueryExpected)){
                        testresult.addOntologyFailureQuery(nombreTestUsuario, qo, resObtenidoInst, tip);
                    }else{
                        testresult.addOntologyPassedQuery(nombreTestUsuario, qo, resObtenidoInst, tip);
                    }
                }else if(tip.name().equals("RET")){
                    resObtenidoRet = jena.retieval(ns, query);
                    String[] queryMod = resQueryExpected.split(patron4);
                    ArrayList<String> queryRet = new ArrayList<String>();
                    for(int k=0;k<queryMod.length;k++){
                        queryRet.add(queryMod[k]);
                    }
                    if(resObtenidoRet==null){
                        testresult.addOntologyFailureQuery(nombreTestUsuario, 
                                qo,"La clase introducida no es una " +
                                "instancia para el modelo",tip);
                    }else{
                        Collections.sort(resObtenidoRet);
                        Collections.sort(queryRet);
                        if(!utils.comparaArray(resObtenidoRet, queryRet)){
                            testresult.addOntologyFailureQuery(nombreTestUsuario, 
                                qo,resObtenidoRet.toString(),tip);
                        }else{
                            testresult.addOntologyPassedQuery(nombreTestUsuario, qo,resObtenidoRet.toString(), tip);
                        }
                    }
                }else if(tip.name().equals("REAL")){
                    resObtenidoRealiz = jena.realization(ns, query);
                    if(!resObtenidoRealiz.equals(resQueryExpected)){
                        testresult.addOntologyFailureQuery(nombreTestUsuario, qo, resObtenidoRealiz,tip);
                    }else{
                        testresult.addOntologyPassedQuery(nombreTestUsuario, qo,resObtenidoRealiz, tip);
                    }
                }else if(tip.name().equals("SAT")){
                    res = query.split(patron3);
                    concepto = res[0];
                    loincluye = res[1];
                    resObtenidoSatisf = jena.satisfactibility(ns, concepto, loincluye);
                    if(!resObtenidoSatisf.equals(resQueryExpected)){
                        testresult.addOntologyFailureQuery(nombreTestUsuario, qo, resObtenidoSatisf,tip);
                    }else{
                        testresult.addOntologyPassedQuery(nombreTestUsuario, qo,resObtenidoSatisf, tip);
                    }
                }else if(tip.name().equals("CLAS")){
                    String[] queryMod = resQueryExpected.split(patron4);
                    ArrayList<String> querySat = new ArrayList<String>();
                    for(int k=0;k<queryMod.length;k++){
                        querySat.add(queryMod[k]);
                    }
                    resObtenidoClas = jena.classification(ns, query);
                    if(resObtenidoClas==null){
                        testresult.addOntologyFailureQuery(nombreTestUsuario, 
                                qo, "El individuo introducido no es una" +
                                "instancia para el modelo",tip);
                    }else{
                        Collections.sort(resObtenidoClas);
                        Collections.sort(querySat);
                        if(!utils.comparaArray(querySat, resObtenidoClas)){
                            testresult.addOntologyFailureQuery(nombreTestUsuario, qo, resObtenidoClas.toString(),tip);
                        }else{
                            testresult.addOntologyPassedQuery(nombreTestUsuario, qo,resObtenidoClas.toString(), tip);
                        }
                    }  
                }
        }        
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
                    String[] subRes = select[1].split(patron6);
                    for(int m=0; m<subRes.length;m++){
                        execQuery.getDatos().add(subRes[m]);
                    }
                    listaResultEsperada.add(execQuery);
                }
            }
            listaResultObtenida = jena.testSPARQL(sparqlQuery, true);
            if(listaResultObtenida.size()>0){
                esperado = new ArrayList<String>();
                obtenido = new ArrayList<String>();
                String contenidoObtenido = "",contenidoEsperado="";
                int tam = listaResultObtenida.get(0).getDatos().size();
                int tamBis = listaResultEsperada.get(0).getDatos().size();
                fallo=0;
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
            }
            if(utils.contieneTodosIguales(esperado, obtenido)==false || fallo==1){
                testresult.addOntologyFailureSparql(nombreTestUsuario, 
                sparqlquery,listaResultEsperada,listaResultObtenida, tip);
            }else{
                testresult.addOntologyPassedSparql(nombreTestUsuario, sparqlquery,listaResultEsperada,listaResultObtenida, tip);
            }
        }
    } 
    
    protected void tearDownOntology(){
        jena.deleteEntries();
    }
    
    @Override
    public void run(OntologyTestResult testresult, CollectionTest baterytest) throws ExceptionReadOntology{ 
        String ont = baterytest.getOntology();
        String ns = baterytest.getNamespace(); 
        ListIterator liScenario;
        ScenarioTest scenariotest;
        List<ScenarioTest> listscenario = baterytest.getScenariotest();
        liScenario = listscenario.listIterator();
        while(liScenario.hasNext()){
                scenariotest = (ScenarioTest) liScenario.next();
                setUpOntology(scenariotest, ont, ns);
                runOntologyTest(testresult,ns,scenariotest);
                tearDownOntology(); 
        }
    }
    
    @Override
    public void runScenario(OntologyTestResult testresult, CollectionTest baterytest, ScenarioTest scenariotest) throws ExceptionReadOntology{ 

        String ont = baterytest.getOntology();
        String ns = baterytest.getNamespace();

        setUpOntology(scenariotest, ont, ns);
        runOntologyTest(testresult,ns,scenariotest);
        tearDownOntology(); 
    }
    
    @Override
    public void runListaScenario(OntologyTestResult testresult, CollectionTest baterytest, List<ScenarioTest> liScenario) throws ExceptionReadOntology{ 

        String ont = baterytest.getOntology();
        String ns = baterytest.getNamespace();
 
        for(int i=0;i<liScenario.size();i++){
                setUpOntology(liScenario.get(i), ont, ns);
                runOntologyTest(testresult,ns,liScenario.get(i));
                tearDownOntology(); 
        }
    }
}    

