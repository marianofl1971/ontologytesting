/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

import code.google.com.p.ontologytesting.model.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author sara.garcia
 */
public class UtilsTestCase {

    private ScenarioTest s = new ScenarioTest();
    private String muestra="";
    
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
            if(otf.getTipoTest().name().equals("INST")){
                if(varInst==0){
                    testInstIntro="TESTS DE INSTANCIACION\r\n\r\n";
                    varInst=1;
                }
                ScenarioTest scenario = s.buscarScenario(CollectionTest.getInstance().getScenariotest(),otf.getTestNameUsuario());
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
            }else if(otf.getTipoTest().name().equals("RET")){
                if(varRet==0){
                    testRetIntro="TESTS DE RECUPERACION\r\n\r\n";
                    varRet=1;
                }
                ScenarioTest scenario = s.buscarScenario(CollectionTest.getInstance().getScenariotest(),otf.getTestNameUsuario());
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
            }else if(otf.getTipoTest().name().equals("REAL")){
                if(varReal==0){
                    testRealIntro="TESTS DE REALIZACION\r\n\r\n";
                    varReal=1;
                }
                ScenarioTest scenario = s.buscarScenario(CollectionTest.getInstance().getScenariotest(),otf.getTestNameUsuario());
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
            }else if(otf.getTipoTest().name().equals("SAT")){
                if(varSat==0){
                    testSatIntro="TESTS DE SATISFACTIBILIDAD\r\n\r\n";
                    varSat=1;
                }
                ScenarioTest scenario = s.buscarScenario(CollectionTest.getInstance().getScenariotest(),otf.getTestNameUsuario());
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
            }else if(otf.getTipoTest().name().equals("CLAS")){
                if(varClas==0){
                    testClasIntro="TESTS DE CLASIFICACION\r\n\r\n";
                    varClas=1;
                }
                ScenarioTest scenario = s.buscarScenario(CollectionTest.getInstance().getScenariotest(),otf.getTestNameUsuario());
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
            if(otf.getTipoTest().name().equals("SPARQL")){
                if(varSparql==0){
                    testSparqlIntro="TESTS SPARQL\r\n\r\n";
                    varSparql=1;
                }
                ScenarioTest scenario = s.buscarScenario(CollectionTest.getInstance().getScenariotest(),otf.getTestNameUsuario());
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
        StringBuffer bufInstClas = new StringBuffer();
        StringBuffer bufInstProp = new StringBuffer();
        StringBuffer bufInstTotal = new StringBuffer();
        Instancias inst = scenario.getInstancias();
        List<ClassInstances> clas = inst.getClassInstances();
        List<PropertyInstances> prop = inst.getPropertyInstances();
        if(clas.size()!=0){
            bufInstTotal.append("Instancias de Clase");
            for(int j=0;j<clas.size();j++){
                String clase = clas.get(j).getClassInstance();
                String coment = clas.get(j).getComment();
                if(!clase.equals("")){
                    bufInstClas.append(clase);
                }
                if(!coment.equals("")){
                    bufInstClas.append("  ").append(coment).append("\r\n");
                }else{
                    bufInstClas.append("\r\n");
                }
            }
        }else{
            bufInstClas.append(bufInstTotal).append("\r\nTest sin instancias de clase\r\n");
        }
        bufInstTotal.append("\r\n").append(bufInstClas).append("\r\nInstancias de Propiedad");
        if(prop.size()!=0){
            for(int j=0;j<prop.size();j++){
                String propiedad = prop.get(j).getPropertyInstance();
                String coment = prop.get(j).getComment();
                if(!propiedad.equals("")){
                    bufInstProp.append(propiedad);
                }
                if(!coment.equals("")){
                    bufInstProp.append("  ").append(coment).append("\r\n");
                }else{
                    bufInstProp.append("\r\n");
                }
            }
        }else{
            bufInstProp.append("Test sin instancias de propiedad\r\n");
        }
        bufInstTotal.append("\r\n").append(bufInstProp).append("\r\n");
        return bufInstTotal.toString();
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
    
    public String getMuestra() {
        return muestra;
    }

    public void setMuestra(String amuestra) {
        muestra = amuestra;
    }
    
}
