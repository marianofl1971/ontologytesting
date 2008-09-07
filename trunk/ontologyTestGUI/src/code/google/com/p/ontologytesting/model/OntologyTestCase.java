/*
 * OntologyTestCase.java
 * 
 * Created on 16-feb-2008, 14:44:25
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

import code.google.com.p.ontologytesting.exceptions.*;
import code.google.com.p.ontologytesting.gui.MainJPanel;
import code.google.com.p.ontologytesting.jenainterfaz.Jena;
import code.google.com.p.ontologytesting.jenainterfaz.JenaInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Saruskas
 */
public class OntologyTestCase implements OntologyTest{
    
    public String ontologyname;
    boolean namepropIsUsed=false;
    boolean nameclasIsUsed=false;
    private JenaInterface jenaInterface = new JenaInterface();   
    private Jena jena;
    private static String patron1="[\\(|,|\n| ]",patron2="[\n| |\\)]",
            patron3="[\\(|\\)|,| |.]",patron4="[,|\n| ]",patron5="[\n|\\t|\\v|\\s]",
            patron6="[.,\\s\\)]";
    private int fallo=0;
    private static String muestra="";
    private static ArrayList<ExecQuerySparql> listaResultEsperada;
    
    public OntologyTestCase(){
    }

    protected void setUpOntology(ScenarioTest st, String ont, String ns)  throws ExceptionReadOntology{  

            ListIterator liClass;
            ListIterator liProperties;
            String[] ciClas;
            String[] ciInd;
            String[] piClas;
            String[] piInd;

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
            ScenarioTest scenariotest){
          
        ArrayList<String> esperado = new ArrayList<String>();
        ArrayList<String> obtenido = new ArrayList<String>();
        ListIterator liQuery,liSparql;
        String res[],clasF="",indF="",concepto="",loincluye="";
        String testName = scenariotest.getTestName();
        String nombreTestUsuario = scenariotest.getNombre();
        List<QueryOntology> queryTest = scenariotest.getQueryTest();
        List<SparqlQueryOntology> sparqlTest = scenariotest.getSparqlQuerys();
        
        int inst=0, sat=0, clas=0, ret=0, real=0, sparql=0;
        
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
                if(testName.equals("Instanciación")){
                    res = query.split(patron3);
                    clasF = res[0];
                    indF = res[1];
                    resObtenidoInst = jena.instantiation(ns, clasF, indF);
                    if(!resObtenidoInst.equals(resQueryExpected)){
                        inst=1;
                        testresult.addOntologyFailureQuery(nombreTestUsuario, 
                                testName,qo, resObtenidoInst);
                    }else{
                        testresult.addOntologyPassedTestQuery(nombreTestUsuario, testName);
                    }
                }else if(testName.equals("Retrieval")){
                    resObtenidoRet = jena.retieval(ns, query);
                    String[] queryMod = resQueryExpected.split(patron4);
                    ArrayList<String> queryRet = new ArrayList<String>();
                    for(int k=0;k<queryMod.length;k++){
                        queryRet.add(queryMod[k]);
                    }
                    if(resObtenidoRet==null){
                        ret=1;
                        testresult.addOntologyFailureQuery(nombreTestUsuario, 
                                testName,qo,"La clase introducida no es una " +
                                "instancia para el modelo");
                    }else{
                        Collections.sort(resObtenidoRet);
                        Collections.sort(queryRet);
                        if(!this.comparaArray(resObtenidoRet, queryRet)){
                            ret=1;
                            testresult.addOntologyFailureQuery(nombreTestUsuario, 
                                testName,qo,resObtenidoRet.toString());
                        }else{
                            testresult.addOntologyPassedTestQuery(nombreTestUsuario, testName);
                        }
                    }
                }else if(testName.equals("Realización")){
                    resObtenidoRealiz = jena.realization(ns, query);
                    if(!resObtenidoRealiz.equals(resQueryExpected)){
                        real=1;
                        testresult.addOntologyFailureQuery(nombreTestUsuario, 
                                testName,qo, resObtenidoRealiz);
                    }else{
                        testresult.addOntologyPassedTestQuery(nombreTestUsuario, testName);
                    }
                }else if(testName.equals("Satisfactibilidad")){
                    res = query.split(patron3);
                    concepto = res[0];
                    loincluye = res[1];
                    resObtenidoSatisf = jena.satisfactibility(ns, concepto, loincluye);
                    if(!resObtenidoSatisf.equals(resQueryExpected)){
                        sat=1;
                        testresult.addOntologyFailureQuery(nombreTestUsuario, 
                                testName,qo, resObtenidoSatisf);
                    }else{
                        testresult.addOntologyPassedTestQuery(nombreTestUsuario, testName);
                    }
                }else if(testName.equals("Clasificación")){
                    String[] queryMod = resQueryExpected.split(patron4);
                    ArrayList<String> querySat = new ArrayList<String>();
                    for(int k=0;k<queryMod.length;k++){
                        querySat.add(queryMod[k]);
                    }
                    resObtenidoClas = jena.classification(ns, query);
                    if(resObtenidoClas==null){
                        clas=1;
                        testresult.addOntologyFailureQuery(nombreTestUsuario, 
                                testName,qo, "El individuo introducido no es una" +
                                "instancia para el modelo");
                    }else{
                        Collections.sort(resObtenidoClas);
                        Collections.sort(querySat);
                        if(!this.comparaArray(querySat, resObtenidoClas)){
                            clas=1;
                            testresult.addOntologyFailureQuery(nombreTestUsuario, 
                                testName,qo, resObtenidoClas.toString());
                        }else{
                            testresult.addOntologyPassedTestQuery(nombreTestUsuario, testName);
                        }
                    }  
                }
        }
        
        while(liSparql.hasNext()){ 
            
            setListaResultEsperada(new ArrayList<ExecQuerySparql>());
            sparqlquery = (SparqlQueryOntology) liSparql.next();
            String sparqlQuery = sparqlquery.getQuerySparql();
            resQueryExpected = sparqlquery.getResultexpected();
            res = resQueryExpected.split(patron5);
            for(int k=0; k<res.length;k++){
                if(!res[k].equals(""))
                {
                    ExecQuerySparql execQuery = new ExecQuerySparql();
                    System.out.println("res "+res[k].split("\\("));
                    String[] select = res[k].trim().split("\\(");
                    execQuery.setNombreSelect(select[0]); 
                    for(int i=0;i<select.length;i++){
                        System.out.println("select[]"+i+" "+select[i]);
                    }
                    String[] subRes = select[1].split(patron6);
                    for(int s=0; s<subRes.length;s++){
                        execQuery.getDatos().add(subRes[s]);
                    }
                    getListaResultEsperada().add(execQuery);
                }
            }
            
            setListaResultEsperada(listaResultEsperada);
            ArrayList<ExecQuerySparql> listaResultObtenida = new ArrayList<ExecQuerySparql>();
            listaResultObtenida = jena.testSPARQL(sparqlQuery, true);
            if(listaResultObtenida.size()>0){
                esperado = new ArrayList<String>();
                obtenido = new ArrayList<String>();
                String contenidoObtenido = "",contenidoEsperado="";
                int tam = listaResultObtenida.get(0).getDatos().size();
                int tamBis = getListaResultEsperada().get(0).getDatos().size();
                fallo=0;
                if((getListaResultEsperada().size()==listaResultObtenida.size()) && (tam==tamBis)){
                    for(int s=0;s<tam;s++){
                        for(int t=0; t<listaResultObtenida.size(); t++){
                            contenidoObtenido = contenidoObtenido+listaResultObtenida.get(t).getDatos().get(s);
                            contenidoEsperado = contenidoEsperado+getListaResultEsperada().get(t).getDatos().get(s);
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
                testresult.addOntologyFailureSparql(nombreTestUsuario, testName,
                sparqlquery,listaResultObtenida);
                sparql=1;
            }else{
                testresult.addOntologyPassedTestSparql(nombreTestUsuario, testName);
            }
        }
    } 

    public boolean contieneTodosIguales(ArrayList<String> array1, ArrayList<String> array2){
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
    
    public ExecQuerySparql seleccionarLista(String nombreSelect,ArrayList<ExecQuerySparql> lista){
        for(int i=0;i<lista.size();i++){
            if(lista.get(i).getNombreSelect().equals(nombreSelect)){
                return lista.get(i);
            }
        }
        return new ExecQuerySparql();
    }
    
    public boolean perteneceALista(String nombre, ArrayList<ExecQuerySparql> lista){
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
        ArrayList<ScenarioTest> listscenario = baterytest.getScenariotest();
        liScenario = listscenario.listIterator();
 
        while(liScenario.hasNext()){
                scenariotest = (ScenarioTest) liScenario.next();
                setUpOntology(scenariotest, ont, ns);
                runOntologyTest(testresult,ns,scenariotest);
                tearDownOntology(); 
        }
        String datos = crearFicheroDeResultados(testresult);
        setMuestra(datos);
    }
    
    public boolean comparaArray(ArrayList<String> array1, ArrayList<String> array2){
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

    public String crearFicheroDeResultados(OntologyTestResult testresult){
        
        ListIterator liFailures,liSparql;
        ArrayList<OntologyTestFailure> failures = testresult.getOntologyTestFailureQuery();
        ArrayList<OntologyTestFailure> failuresSparql = testresult.getOntologyTestFailureSparql();
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
        String resumen=""; 
        String actualInst="",actualRet="",actualReal="",actualClas="",actualSat="",actualSparql="";
        
        if(MainJPanel.getSimpleTestSelect()==true){
        if(liFailures.hasNext()){
        while(liFailures.hasNext()){
            OntologyTestFailure otf = (OntologyTestFailure) liFailures.next();
            if(otf.getTestName().equals("Instanciación")){
                if(varInst==0){
                    testInstIntro="TESTS DE INSTANCIACION\r\n\r\n";
                    varInst=1;
                }
                ScenarioTest scenario = buscarScenario(otf.getTestNameUsuario());
                if(auxInst==0){
                    actualInst = otf.getTestNameUsuario();
                    testInstIntro= testInstIntro + "Nombre del Test: "+otf.getTestNameUsuario()+"\r\n"+"Descripcion: "+
                    scenario.getDescripcion()+"\r\n\r\n";
                    testInstInstancias = "INSTANCIAS ASOCIADAS\r\n\r\n" + instanciasAsociadas(scenario);
                    testInstFailed = "PRUEBAS QUE HAN FALLADO\r\n\r\n";
                    auxInst=1;
                }
                if(!actualInst.equals(otf.getTestNameUsuario())){
                    testInstTotal = testInstTotal + testInstIntro + testInstInstancias + testInstFailed;
                    testInstTotal = testInstTotal + "\r\nNombre del Test: "+otf.getTestNameUsuario()+"\r\n"+"Descripcion: "+
                    scenario.getDescripcion()+"\r\n\r\n";
                    testInstTotal = testInstTotal + "INSTANCIAS ASOCIADAS\r\n\r\n" + instanciasAsociadas(scenario);
                    testInstTotal = testInstTotal + "PRUEBAS QUE HAN FALLADO\r\n\r\n";
                    actualInst = otf.getTestNameUsuario();
                    testInstFailed="";
                }
                testInstFailed = testInstFailed + "Prueba realizada: " +otf.getfQuery() +
                        "\r\nResultado esperado: "+otf.getfResultExpected()+"\r\nResultado Obtenido: "+
                        otf.getResultQueryObtenido()+"\r\n";
            }else if(otf.getTestName().equals("Retrieval")){
                if(varRet==0){
                    testRetIntro="TESTS DE RECUPERACION\r\n\r\n";
                    varRet=1;
                }
                ScenarioTest scenario = buscarScenario(otf.getTestNameUsuario());
                if(auxRet==0){
                    actualRet = otf.getTestNameUsuario();
                    testRetIntro= testRetIntro + "Nombre del Test: "+otf.getTestNameUsuario()+"\r\n"+"Descripcion: "+
                    scenario.getDescripcion()+"\r\n\r\n";
                    testRetInstancias = "INSTANCIAS ASOCIADAS\r\n\r\n" + instanciasAsociadas(scenario);
                    testRetFailed = "PRUEBAS QUE HAN FALLADO\r\n\r\n";
                    auxRet=1;
                }
                if(!actualRet.equals(otf.getTestNameUsuario())){
                    testRetTotal = testRetTotal + testRetIntro + testRetInstancias + testRetFailed;
                    testRetTotal = testRetTotal + "\r\nNombre del Test: "+otf.getTestNameUsuario()+"\r\n"+"Descripcion: "+
                    scenario.getDescripcion()+"\r\n\r\n";
                    testRetTotal = testRetTotal + "INSTANCIAS ASOCIADAS\r\n\r\n" + instanciasAsociadas(scenario);
                    testRetTotal = testRetTotal + "PRUEBAS QUE HAN FALLADO\r\n\r\n";
                    actualRet = otf.getTestNameUsuario();
                    testRetFailed="";
                }
                testRetFailed = testRetFailed + "Prueba realizada: " +otf.getfQuery() +
                        "\r\nResultado esperado: "+otf.getfResultExpected()+"\r\nResultado Obtenido: "+
                        otf.getResultQueryObtenido()+"\r\n";
            }else if(otf.getTestName().equals("Realización")){
                if(varReal==0){
                    testRealIntro="TESTS DE REALIZACION\r\n\r\n";
                    varReal=1;
                }
                ScenarioTest scenario = buscarScenario(otf.getTestNameUsuario());
                if(auxReal==0){
                    actualReal = otf.getTestNameUsuario();
                    testRealIntro= testRealIntro + "Nombre del Test: "+otf.getTestNameUsuario()+"\r\n"+"Descripcion: "+
                    scenario.getDescripcion()+"\r\n\r\n";
                    testRealInstancias = "INSTANCIAS ASOCIADAS\r\n\r\n" + instanciasAsociadas(scenario);
                    testRealFailed = "PRUEBAS QUE HAN FALLADO\r\n\r\n";
                    auxReal=1;
                }
                if(!actualReal.equals(otf.getTestNameUsuario())){
                    testRealTotal = testRealTotal + testRealIntro + testRealInstancias + testRealFailed;
                    testRealTotal = testRealTotal + "\r\nNombre del Test: "+otf.getTestNameUsuario()+"\r\n"+"Descripcion: "+
                    scenario.getDescripcion()+"\r\n\r\n";
                    testRealTotal = testRealTotal + "INSTANCIAS ASOCIADAS\r\n\r\n" + instanciasAsociadas(scenario);
                    testRealTotal = testRealTotal + "PRUEBAS QUE HAN FALLADO\r\n\r\n";
                    actualReal = otf.getTestNameUsuario();
                    testRealFailed="";
                }
                testRealFailed = testRealFailed + "Prueba realizada: " +otf.getfQuery() +
                        "\r\nResultado esperado: "+otf.getfResultExpected()+"\r\nResultado Obtenido: "+
                        otf.getResultQueryObtenido()+"\r\n";
            }else if(otf.getTestName().equals("Satisfactibilidad")){
                if(varSat==0){
                    testSatIntro="TESTS DE SATISFACTIBILIDAD\r\n\r\n";
                    varSat=1;
                }
                ScenarioTest scenario = buscarScenario(otf.getTestNameUsuario());
                if(auxSat==0){
                    actualSat = otf.getTestNameUsuario();
                    testSatIntro= testSatIntro + "Nombre del Test: "+otf.getTestNameUsuario()+"\r\n"+"Descripcion: "+
                    scenario.getDescripcion()+"\r\n\r\n";
                    testSatInstancias = "INSTANCIAS ASOCIADAS\r\n\r\n" + instanciasAsociadas(scenario);
                    testSatFailed = "PRUEBAS QUE HAN FALLADO\r\n\r\n";
                    auxSat=1;
                }
                if(!actualSat.equals(otf.getTestNameUsuario())){
                    testSatTotal = testSatTotal + testSatIntro + testSatInstancias + testSatFailed;
                    testSatTotal = testSatTotal + "\r\nNombre del Test: "+otf.getTestNameUsuario()+"\r\n"+"Descripcion: "+
                    scenario.getDescripcion()+"\r\n\r\n";
                    testSatTotal = testInstTotal + "INSTANCIAS ASOCIADAS\r\n\r\n" + instanciasAsociadas(scenario);
                    testSatTotal = testInstTotal + "PRUEBAS QUE HAN FALLADO\r\n\r\n";
                    actualSat = otf.getTestNameUsuario();
                    testSatFailed="";
                }
                testSatFailed = testSatFailed + "Prueba realizada: " +otf.getfQuery() +
                        "\r\nResultado esperado: "+otf.getfResultExpected()+"\r\nResultado Obtenido: "+
                        otf.getResultQueryObtenido()+"\r\n";
            }else if(otf.getTestName().equals("Clasificación")){
                if(varClas==0){
                    testClasIntro="TESTS DE CLASIFICACION\r\n\r\n";
                    varClas=1;
                }
                ScenarioTest scenario = buscarScenario(otf.getTestNameUsuario());
                if(auxClas==0){
                    actualClas = otf.getTestNameUsuario();
                    testClasIntro= testClasIntro + "Nombre del Test: "+otf.getTestNameUsuario()+"\r\n"+"Descripcion: "+
                    scenario.getDescripcion()+"\r\n\r\n";
                    testClasInstancias = "INSTANCIAS ASOCIADAS\r\n\r\n" + instanciasAsociadas(scenario);
                    testClasFailed = "PRUEBAS QUE HAN FALLADO\r\n\r\n";
                    auxClas=1;
                }
                if(!actualClas.equals(otf.getTestNameUsuario())){
                    testClasTotal = testClasTotal + testClasIntro + testClasInstancias + testClasFailed;
                    testClasTotal = testInstTotal + "\r\nNombre del Test: "+otf.getTestNameUsuario()+"\r\n"+"Descripcion: "+
                    scenario.getDescripcion()+"\r\n\r\n";
                    testClasTotal = testInstTotal + "INSTANCIAS ASOCIADAS\r\n\r\n" + instanciasAsociadas(scenario);
                    testClasTotal = testInstTotal + "PRUEBAS QUE HAN FALLADO\r\n\r\n";
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
            inst = testInstIntro + testInstInstancias + testInstFailed + "\r\n";
        }else{
            inst = testInstTotal + testInstFailed + "\r\n";
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
        
        }else{
            resumen = "null";
        } 
      }else if(MainJPanel.getSparqlTestsSelect()==true){  
      if(liSparql.hasNext()){
        while(liSparql.hasNext()){
            OntologyTestFailure otf = (OntologyTestFailure) liSparql.next();
            if(otf.getTestName().equals("sparql")){
                if(varSparql==0){
                    testSparqlIntro="TESTS SPARQL\r\n\r\n";
                    varSparql=1;
                }
                ScenarioTest scenario = buscarScenario(otf.getTestNameUsuario());
                if(auxSparql==0){
                    actualSparql = otf.getTestNameUsuario();
                    testSparqlIntro= testSparqlIntro + "Nombre del Test: "+otf.getTestNameUsuario()+"\r\n"+"Descripcion: "+
                    scenario.getDescripcion()+"\r\n\r\n";
                    testSparqlInstancias = "INSTANCIAS ASOCIADAS\r\n\r\n" + instanciasAsociadas(scenario);
                    testSparqlFailed = "PRUEBAS QUE HAN FALLADO\r\n\r\n";
                    auxSparql=1;
                }
                if(!actualSparql.equals(otf.getTestNameUsuario())){
                    testSparqlTotal = testSparqlTotal + testSparqlIntro + testSparqlInstancias + testSparqlFailed;
                    testSparqlTotal = testSparqlTotal + "\r\nNombre del Test: "+otf.getTestNameUsuario()+"\r\n"+"Descripcion: "+
                    scenario.getDescripcion()+"\r\n\r\n";
                    testSparqlTotal = testSparqlTotal + "INSTANCIAS ASOCIADAS\r\n\r\n" + instanciasAsociadas(scenario);
                    testInstTotal = testInstTotal + "PRUEBAS QUE HAN FALLADO\r\n\r\n";
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
       resumen = sparql;
      }else{
        resumen = "null";
      }
      }  
      return resumen;
    }
    
    public ScenarioTest buscarScenario(String name){
        ArrayList<ScenarioTest> scenario = MainJPanel.getCollectionTest().getScenariotest();
        for(int i=0;i<scenario.size();i++){
            String nombre = scenario.get(i).getNombre();
            if(nombre.equals(name)){
                return scenario.get(i);
            }
        }
        return null;
    }
    
    public String instanciasAsociadas(ScenarioTest scenario){
        String instClas="",instProp="",instTotal="";
        Instancias inst = scenario.getInstancias();
        ArrayList<ClassInstances> clas = inst.getClassInstances();
        ArrayList<PropertyInstances> prop = inst.getPropertyInstances();
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

    public static String getMuestra() {
        return muestra;
    }

    public static void setMuestra(String amuestra) {
        muestra = amuestra;
    }

    public static ArrayList<ExecQuerySparql> getListaResultEsperada() {
        return listaResultEsperada;
    }

    public static  void setListaResultEsperada(ArrayList<ExecQuerySparql> alistaResultEsperada) {
        listaResultEsperada = alistaResultEsperada;
    }
}    

