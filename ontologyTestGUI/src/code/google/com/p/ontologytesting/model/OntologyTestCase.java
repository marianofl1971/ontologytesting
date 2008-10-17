/*
 * OntologyTestCase.java
 * 
 * Created on 16-feb-2008, 14:44:25
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

import code.google.com.p.ontologytesting.guiNew.Utils;
import code.google.com.p.ontologytesting.model.ScenarioTest.TipoTest;
import code.google.com.p.ontologytesting.model.jenainterfaz.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Saruskas
 */
public class OntologyTestCase implements OntologyTest{
    
    private JenaInterface jenaInterface;   
    private Jena jena;
    private String patron1="",patron2="",patron3="",patron4="",patron5="",patron6="";
    private int fallo=0;
    private static String muestra="";
    private List<ExecQuerySparql> listaResultEsperada = new ArrayList<ExecQuerySparql>();
    private List<ExecQuerySparql> listaResultObtenida = new ArrayList<ExecQuerySparql>();
    private Utils util = new Utils();
    
    public OntologyTestCase(){
    }

    protected void setUpOntology(ScenarioTest st, String ont, String ns) throws ExceptionReadOntology{  

            ListIterator liClass;
            ListIterator liProperties;
            String[] ciClas;
            String[] ciInd;
            String[] piClas;
            String[] piInd;

            patron1 = "[\\(|,|\n| ]";
            patron2 = "[\n| |\\)]";
            jenaInterface = new JenaInterface();
            jena = jenaInterface.getJena();
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
    
    protected void tearDownOntology(){
        jena.deleteEntries();
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
        int tipo = scenariotest.getTipoTest().getTipo();
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
                if(tipo==0){
                    res = query.split(patron3);
                    clasF = res[0];
                    indF = res[1];
                    resObtenidoInst = jena.instantiation(ns, clasF, indF);
                    if(!resObtenidoInst.equals(resQueryExpected)){
                        testresult.addOntologyFailureQuery(nombreTestUsuario, qo, resObtenidoInst, tip);
                    }else{
                        testresult.addOntologyPassedQuery(nombreTestUsuario, qo, resObtenidoInst, tip);
                    }
                }else if(tipo==1){
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
                        if(!this.comparaArray(resObtenidoRet, queryRet)){
                            testresult.addOntologyFailureQuery(nombreTestUsuario, 
                                qo,resObtenidoRet.toString(),tip);
                        }else{
                            testresult.addOntologyPassedQuery(nombreTestUsuario, qo,resObtenidoRet.toString(), tip);
                        }
                    }
                }else if(tipo==2){
                    resObtenidoRealiz = jena.realization(ns, query);
                    if(!resObtenidoRealiz.equals(resQueryExpected)){
                        testresult.addOntologyFailureQuery(nombreTestUsuario, qo, resObtenidoRealiz,tip);
                    }else{
                        testresult.addOntologyPassedQuery(nombreTestUsuario, qo,resObtenidoRealiz, tip);
                    }
                }else if(tipo==3){
                    res = query.split(patron3);
                    concepto = res[0];
                    loincluye = res[1];
                    resObtenidoSatisf = jena.satisfactibility(ns, concepto, loincluye);
                    if(!resObtenidoSatisf.equals(resQueryExpected)){
                        testresult.addOntologyFailureQuery(nombreTestUsuario, qo, resObtenidoSatisf,tip);
                    }else{
                        testresult.addOntologyPassedQuery(nombreTestUsuario, qo,resObtenidoSatisf, tip);
                    }
                }else if(tipo==4){
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
                        if(!this.comparaArray(querySat, resObtenidoClas)){
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
            String sparqlQuery = sparqlquery.getQuerySparql();
            resQueryExpected = sparqlquery.getResultexpected();
            res = resQueryExpected.split(patron5);
            for(int k=0; k<res.length;k++){
                if(!res[k].equals(""))
                {
                    ExecQuerySparql execQuery = new ExecQuerySparql();
                    String[] select = res[k].trim().split("\\(");
                    execQuery.setNombreSelect(select[0]); 
                    String[] subRes = select[1].split(patron6);
                    for(int s=0; s<subRes.length;s++){
                        execQuery.getDatos().add(subRes[s]);
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
                    for(int s=0;s<tam;s++){
                        for(int t=0; t<listaResultObtenida.size(); t++){
                            contenidoObtenido = contenidoObtenido+listaResultObtenida.get(t).getDatos().get(s);
                            contenidoEsperado = contenidoEsperado+listaResultEsperada.get(t).getDatos().get(s);
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
            if(contieneTodosIguales(esperado, obtenido)==false || fallo==1){
                testresult.addOntologyFailureSparql(nombreTestUsuario, 
                sparqlquery,listaResultEsperada,listaResultObtenida, tip);
            }else{
                testresult.addOntologyPassedSparql(nombreTestUsuario, sparqlquery,listaResultEsperada,listaResultObtenida, tip);
            }
        }
    } 

    public boolean contieneTodosIguales(List<String> array1, List<String> array2){
        if(array1.size()==array2.size()){
            for(int i=0;i<array1.size();i++){
                String dato1 = array1.get(i);
                if(!array2.contains(dato1)){
                    return false;
                }
            }
        }else return false;
        return true;
    }
    
    public ExecQuerySparql seleccionarLista(String nombreSelect,List<ExecQuerySparql> lista){
        for(int i=0;i<lista.size();i++){
            if(lista.get(i).getNombreSelect().equals(nombreSelect)){
                return lista.get(i);
            }
        }
        return new ExecQuerySparql();
    }
    
    public boolean perteneceALista(String nombre, List<ExecQuerySparql> lista){
        for(int i=0;i<lista.size();i++){
            String nombreAux = lista.get(i).getNombreSelect();
            if(nombreAux.equals(nombre)){
                return true;
            }
        }
        return false;
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
        crearFicheroDeResultados(testresult);
    }
    
    @Override
    public void runScenario(OntologyTestResult testresult, CollectionTest baterytest, ScenarioTest scenariotest) throws ExceptionReadOntology{ 

        String ont = baterytest.getOntology();
        String ns = baterytest.getNamespace();

        setUpOntology(scenariotest, ont, ns);
        runOntologyTest(testresult,ns,scenariotest);
        tearDownOntology(); 
        //crearFicheroDeResultados(testresult);
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
        //crearFicheroDeResultados(testresult);
    }
    
    public boolean comparaArray(List<String> array1, List<String> array2){
        if(array1.size() == array2.size()){
            for(int i=0;i<array1.size();i++){
                if(!array1.get(i).equals(array2.get(i))){
                    return false;
                }
            }
        }else{
            return false;
        }
        return true;
    }

    public void crearFicheroDeResultados(OntologyTestResult testresult){

        ListIterator liFailures,liSparql;
        List<OntologyTestFailure> f = testresult.getOntologyTestFailureQuery();
        List<OntologyTestFailure> fsparql = testresult.getOntologyTestFailureSparql();
        List<OntologyTestFailure> failures = ordenarLista(f);
        List<OntologyTestFailure> failuresSparql = ordenarLista(fsparql);
        int varInst=0,varRet=0,varReal=0,varSat=0,varClas=0,varSparql=0,auxInst=0,
                auxRet=0,auxReal=0,auxSat=0,auxClas=0,auxSparql=0;
        liFailures = failures.listIterator();
        liSparql = failuresSparql.listIterator();
        String testInstFailed="",testInstInstancias="",testInstIntro="",testInstTotal="";
        String testRetFailed="",testRetInstancias="",testRetIntro="",testRetTotal="";
        String testRealFailed="",testRealInstancias="",testRealIntro="",testRealTotal="";
        String testSatFailed="",testSatInstancias="",testSatIntro="",testSatTotal="";
        String testClasFailed="",testClasInstancias="",testClasIntro="",testClasTotal="";
        String testSparqlFailed="",testSparqlInstancias="",testSparqlIntro="",testSparqlTotal="";
        String actualInst="",actualRet="",actualReal="",actualClas="",actualSat="",actualSparql="";
        String resumen="";
        
        if(liFailures.hasNext()){
        while(liFailures.hasNext()){
            OntologyTestFailure otf = (OntologyTestFailure) liFailures.next();
            if(otf.getTipoTest().getTipo()==0){
                if(varInst==0){
                    testInstIntro="TESTS DE INSTANCIACION\r\n\r\n";
                    varInst=1;
                }
                ScenarioTest scenario = util.buscarScenario(CollectionTest.getInstance().getScenariotest(),otf.getTestNameUsuario());
                if(auxInst==0){
                    actualInst = otf.getTestNameUsuario();
                    testInstIntro = testInstIntro + "Nombre del Test: "+otf.getTestNameUsuario()+"\r\n"+"Descripcion: "+
                    scenario.getDescripcion()+"\r\n\r\n";
                    testInstInstancias = "INSTANCIAS ASOCIADAS\r\n\r\n" + instanciasAsociadas(scenario);
                    testInstTotal = testInstTotal + testInstIntro + testInstInstancias + testInstFailed;
                    auxInst=1;
                }
                if(!actualInst.equals(otf.getTestNameUsuario())){
                    testInstTotal = testInstTotal + testInstFailed;
                    testInstIntro = "\r\nNombre del Test: "+otf.getTestNameUsuario()+"\r\n"+"Descripcion: "+
                    scenario.getDescripcion()+"\r\n\r\n";
                    testInstInstancias = "INSTANCIAS ASOCIADAS\r\n\r\n" + instanciasAsociadas(scenario);
                    testInstFailed = "PRUEBAS QUE HAN FALLADO\r\n\r\n";
                    testInstTotal = testInstTotal + testInstIntro + testInstInstancias + testInstFailed;
                    actualInst = otf.getTestNameUsuario();
                    testInstFailed="";
                }
                testInstFailed = testInstFailed + "Prueba realizada: " +otf.getfQuery() +
                        "\r\nResultado esperado: "+otf.getfResultExpected()+"\r\nResultado Obtenido: "+
                        otf.getResultQueryObtenido()+"\r\n\r\n";
            }else if(otf.getTipoTest().getTipo()==1){
                if(varRet==0){
                    testRetIntro="TESTS DE RECUPERACION\r\n\r\n";
                    varRet=1;
                }
                ScenarioTest scenario = util.buscarScenario(CollectionTest.getInstance().getScenariotest(),otf.getTestNameUsuario());
                if(auxRet==0){
                    actualRet = otf.getTestNameUsuario();
                    testRetIntro= testRetIntro + "Nombre del Test: "+otf.getTestNameUsuario()+"\r\n"+"Descripcion: "+
                    scenario.getDescripcion()+"\r\n\r\n";
                    testRetInstancias = "INSTANCIAS ASOCIADAS\r\n\r\n" + instanciasAsociadas(scenario);
                    testRetTotal = testRetTotal + testRetIntro + testRetInstancias + testRetFailed;
                    auxRet=1;
                }
                if(!actualRet.equals(otf.getTestNameUsuario())){
                    testRetTotal = testRetTotal + testRetFailed;
                    testRetTotal = "\r\nNombre del Test: "+otf.getTestNameUsuario()+"\r\n"+"Descripcion: "+
                    scenario.getDescripcion()+"\r\n\r\n";
                    testRetTotal = "INSTANCIAS ASOCIADAS\r\n\r\n" + instanciasAsociadas(scenario);
                    testRetTotal = "PRUEBAS QUE HAN FALLADO\r\n\r\n";
                    testRetTotal = testRetTotal + testRetIntro + testRetInstancias + testRetFailed;
                    actualRet = otf.getTestNameUsuario();
                    testRetFailed="";
                }
                testRetFailed = testRetFailed + "Prueba realizada: " +otf.getfQuery() +
                        "\r\nResultado esperado: "+otf.getfResultExpected()+"\r\nResultado Obtenido: "+
                        otf.getResultQueryObtenido()+"\r\n";
            }else if(otf.getTipoTest().getTipo()==2){
                if(varReal==0){
                    testRealIntro="TESTS DE REALIZACION\r\n\r\n";
                    varReal=1;
                }
                ScenarioTest scenario = util.buscarScenario(CollectionTest.getInstance().getScenariotest(),otf.getTestNameUsuario());
                if(auxReal==0){
                    actualReal = otf.getTestNameUsuario();
                    testRealIntro= testRealIntro + "Nombre del Test: "+otf.getTestNameUsuario()+"\r\n"+"Descripcion: "+
                    scenario.getDescripcion()+"\r\n\r\n";
                    testRealInstancias = "INSTANCIAS ASOCIADAS\r\n\r\n" + instanciasAsociadas(scenario);
                    testRealTotal = testRealTotal + testRealIntro + testRealInstancias + testRealFailed;
                    auxReal=1;
                }
                if(!actualReal.equals(otf.getTestNameUsuario())){
                    testRealTotal = testRealTotal + testRealFailed;
                    testRealTotal = "\r\nNombre del Test: "+otf.getTestNameUsuario()+"\r\n"+"Descripcion: "+
                    scenario.getDescripcion()+"\r\n\r\n";
                    testRealTotal = "INSTANCIAS ASOCIADAS\r\n\r\n" + instanciasAsociadas(scenario);
                    testRealTotal = "PRUEBAS QUE HAN FALLADO\r\n\r\n";
                    testRealTotal = testRealTotal + testRealIntro + testRealInstancias + testRealFailed;
                    actualReal = otf.getTestNameUsuario();
                    testRealFailed="";
                }
                testRealFailed = testRealFailed + "Prueba realizada: " +otf.getfQuery() +
                        "\r\nResultado esperado: "+otf.getfResultExpected()+"\r\nResultado Obtenido: "+
                        otf.getResultQueryObtenido()+"\r\n";
            }else if(otf.getTipoTest().getTipo()==3){
                if(varSat==0){
                    testSatIntro="TESTS DE SATISFACTIBILIDAD\r\n\r\n";
                    varSat=1;
                }
                ScenarioTest scenario = util.buscarScenario(CollectionTest.getInstance().getScenariotest(),otf.getTestNameUsuario());
                if(auxSat==0){
                    actualSat = otf.getTestNameUsuario();
                    testSatIntro= testSatIntro + "Nombre del Test: "+otf.getTestNameUsuario()+"\r\n"+"Descripcion: "+
                    scenario.getDescripcion()+"\r\n\r\n";
                    testSatInstancias = "INSTANCIAS ASOCIADAS\r\n\r\n" + instanciasAsociadas(scenario);
                    testSatTotal = testSatTotal + testSatIntro + testSatInstancias + testSatFailed;
                    auxSat=1;
                }
                if(!actualSat.equals(otf.getTestNameUsuario())){
                    testSatTotal = testSatTotal + testSatFailed;
                    testSatTotal =  "\r\nNombre del Test: "+otf.getTestNameUsuario()+"\r\n"+"Descripcion: "+
                    scenario.getDescripcion()+"\r\n\r\n";
                    testSatTotal = "INSTANCIAS ASOCIADAS\r\n\r\n" + instanciasAsociadas(scenario);
                    testSatTotal = "PRUEBAS QUE HAN FALLADO\r\n\r\n";
                    testSatTotal = testSatTotal + testSatIntro + testSatInstancias + testSatFailed;
                    actualSat = otf.getTestNameUsuario();
                    testSatFailed="";
                }
                testSatFailed = testSatFailed + "Prueba realizada: " +otf.getfQuery() +
                        "\r\nResultado esperado: "+otf.getfResultExpected()+"\r\nResultado Obtenido: "+
                        otf.getResultQueryObtenido()+"\r\n";
            }else if(otf.getTipoTest().getTipo()==4){
                if(varClas==0){
                    testClasIntro="TESTS DE CLASIFICACION\r\n\r\n";
                    varClas=1;
                }
                ScenarioTest scenario = util.buscarScenario(CollectionTest.getInstance().getScenariotest(),otf.getTestNameUsuario());
                if(auxClas==0){
                    actualClas = otf.getTestNameUsuario();
                    testClasIntro= testClasIntro + "Nombre del Test: "+otf.getTestNameUsuario()+"\r\n"+"Descripcion: "+
                    scenario.getDescripcion()+"\r\n\r\n";
                    testClasInstancias = "INSTANCIAS ASOCIADAS\r\n\r\n" + instanciasAsociadas(scenario);
                    testClasTotal = testClasTotal + testClasIntro + testClasInstancias + testClasFailed;
                    auxClas=1;
                }
                if(!actualClas.equals(otf.getTestNameUsuario())){
                    testClasTotal = testClasTotal + testClasFailed;
                    testClasTotal = "\r\nNombre del Test: "+otf.getTestNameUsuario()+"\r\n"+"Descripcion: "+
                    scenario.getDescripcion()+"\r\n\r\n";
                    testClasTotal = "INSTANCIAS ASOCIADAS\r\n\r\n" + instanciasAsociadas(scenario);
                    testClasTotal = "PRUEBAS QUE HAN FALLADO\r\n\r\n";
                    testClasTotal = testClasTotal + testClasIntro + testClasInstancias + testClasFailed;
                    actualClas = otf.getTestNameUsuario();
                    testClasFailed="";
                }
                testClasFailed = testClasFailed + "Prueba realizada: " +otf.getfQuery() +
                        "\r\nResultado esperado: "+otf.getfResultExpected()+"\r\nResultado Obtenido: "+
                        otf.getResultQueryObtenido()+"\r\n";
            }
        }
        String inst="",ret="",real="",sat="",clas="";
        if(testInstTotal.equals("")){
            if(!testInstFailed.equals("")){
                inst = testInstIntro + testInstInstancias + testInstFailed + "\r\n";
            }else{
                inst = testInstIntro + testInstInstancias + "No se han producido fallos" + "\r\n";
            }
        }else{
            if(!testInstFailed.equals("")){
                inst = testInstTotal + testInstFailed + "\r\n";
            }else{
                inst = testInstTotal + "No se han producido fallos" + "\r\n";
            }
        }
        if(testRetTotal.equals("")){
            ret = testRetIntro + testRetInstancias + testRetFailed + "\r\n";
        }else{
            ret = testRetTotal + testRetFailed + "\r\n";
        }
        if(testRealTotal.equals("")){
            real = testRealIntro + testRealInstancias + testRealFailed + "\r\n";
        }else{
            real = testRealTotal + testRealFailed + "\r\n";
        }
        if(testSatTotal.equals("")){
            sat = testSatIntro + testSatInstancias + testSatFailed + "\r\n";
        }else{
            sat = testSatTotal + testSatFailed + "\r\n";
        }
        if(testClasTotal.equals("")){
            clas = testClasIntro + testClasInstancias + testClasFailed + "\r\n";
        }else{
            clas = testClasTotal + testClasFailed + "\r\n";
        }
        resumen =  inst + "\r\n" + ret + "\r\n" + real + "\r\n" + sat + "\r\n" + clas;
        setMuestra(resumen);
        }
 
      if(liSparql.hasNext()){
        while(liSparql.hasNext()){
            OntologyTestFailure otf = (OntologyTestFailure) liSparql.next();
            if(otf.getTipoTest().getTipo()==5){
                if(varSparql==0){
                    testSparqlIntro="TESTS SPARQL\r\n\r\n";
                    varSparql=1;
                }
                ScenarioTest scenario = util.buscarScenario(CollectionTest.getInstance().getScenariotest(),otf.getTestNameUsuario());
                if(auxSparql==0){
                    actualSparql = otf.getTestNameUsuario();
                    testSparqlIntro= testSparqlIntro + "Nombre del Test: "+otf.getTestNameUsuario()+"\r\n"+"Descripcion: "+
                    scenario.getDescripcion()+"\r\n\r\n";
                    testSparqlInstancias = "INSTANCIAS ASOCIADAS\r\n\r\n" + instanciasAsociadas(scenario);
                    testSparqlTotal = testSparqlTotal + testSparqlIntro + testSparqlInstancias + testSparqlFailed;
                    auxSparql=1;
                }
                if(!actualSparql.equals(otf.getTestNameUsuario())){
                    testSparqlTotal = testSparqlTotal + testSparqlFailed;
                    testSparqlTotal = "\r\nNombre del Test: "+otf.getTestNameUsuario()+"\r\n"+"Descripcion: "+
                    scenario.getDescripcion()+"\r\n\r\n";
                    testSparqlTotal = "INSTANCIAS ASOCIADAS\r\n\r\n" + instanciasAsociadas(scenario);
                    testInstTotal = "PRUEBAS QUE HAN FALLADO\r\n\r\n";
                    actualSparql = otf.getTestNameUsuario();
                    testSparqlFailed="";
                }
                testSparqlFailed = testSparqlFailed + "Prueba realizada:\r\n" +otf.getfSparqlQuery() +
                        "\r\n\r\nResultado esperado: "+otf.getfResultSparqlExpected()+"\r\n\r\nResultado Obtenido: "+
                        otf.mostrarResultadoSparql()+"\r\n";
            }
        }
           String sparql="";
           if(testSparqlTotal.equals("")){
                sparql = testSparqlIntro + testSparqlInstancias + testSparqlFailed + "\r\n";
           }else{
                sparql = testSparqlTotal + testSparqlFailed + "\r\n";
           }
           String muestraAux = getMuestra();
           resumen = muestraAux +"\r\n\r\n"+ sparql;
           setMuestra(resumen);
        }

    }
    
    public String instanciasAsociadas(ScenarioTest scenario){
        String instClas="",instProp="",instTotal="";
        Instancias inst = scenario.getInstancias();
        List<ClassInstances> clas = inst.getClassInstances();
        List<PropertyInstances> prop = inst.getPropertyInstances();
        if(clas.size()!=0){
        instTotal = "Instancias de Clase";
        for(int j=0;j<clas.size();j++){
            String clase = clas.get(j).getClassInstance();
            String coment = clas.get(j).getComment();
            if(!clase.equals("")){
                instClas = instClas + clase;
            }
            if(!coment.equals("")){
                instClas = instClas + "  " + coment + "\r\n";
            }else{
                instClas = instClas + "\r\n";
            }
        }
        }else{
            instClas = instTotal + "\r\nTest sin instancias de clase\r\n";
        }
        instTotal = instTotal + "\r\n" + instClas + "\r\nInstancias de Propiedad";
        if(prop.size()!=0){
        for(int j=0;j<prop.size();j++){
            String propiedad = prop.get(j).getPropertyInstance();
            String coment = prop.get(j).getComment();
            if(!propiedad.equals("")){
                instProp = instProp + propiedad;
            }
            if(!coment.equals("")){
                instProp = instProp + "  " + coment + "\r\n";
            }else{
                instProp = instProp + "\r\n";
            }
        }
        }else{
            instProp = "Test sin instancias de propiedad\r\n";
        }
        instTotal = instTotal + "\r\n" + instProp +"\r\n";
        return instTotal;
    }

    public List<OntologyTestFailure> ordenarLista(List<OntologyTestFailure> lista){
        ArrayList<String> nombres = new ArrayList<String>();
        List<OntologyTestFailure> listaOrdenada = new ArrayList<OntologyTestFailure>();
        for(int i=0;i<lista.size();i++){
            if(!nombres.contains(lista.get(i).getTestNameUsuario())){
                nombres.add(lista.get(i).getTestNameUsuario());
            }
        }
        Collections.sort(nombres);
        for(int j=0;j<nombres.size();j++){
            for(int k=0;k<lista.size();k++){
                if(nombres.get(j).equals(lista.get(k).getTestNameUsuario())){
                    listaOrdenada.add(lista.get(k));
                }
            }
        }
        return listaOrdenada;
    }

    public static String getMuestra() {
        return muestra;
    }

    public static void setMuestra(String amuestra) {
        muestra = amuestra;
    }
}    
