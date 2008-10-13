/*
 * OntologyTestFailure.java
 * 
 * Created on 16-feb-2008, 14:44:38
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

import code.google.com.p.ontologytesting.model.ScenarioTest.TipoTest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Saruskas
 */
public class OntologyTestFailure extends Object{

    protected String fresqueryobtenido="";
    protected String fquery="";
    protected String fcommentquery="";
    protected String fresultexpected="";
    protected String fresultsparqlexpected="";
    protected List<ExecQuerySparql> fressparqlobtenido = new ArrayList<ExecQuerySparql>();
    protected List<ExecQuerySparql> fressparqlesperado = new ArrayList<ExecQuerySparql>();
    protected String fcommentsparqlquery="";
    protected String fquerysparql="";
    protected String ftestNameUsuario="";
    protected String result="";
    protected TipoTest ftipoTest;
    
    public OntologyTestFailure(){
    }   
    
    void addOntologyTestFailureQuery(String testNameUsuario, QueryOntology query, String resQueryObte,TipoTest tipoTest){
        this.ftestNameUsuario=testNameUsuario;
        this.fquery = query.getQuery();
        this.fcommentquery = query.getComment();
        this.fresultexpected = query.getResultexpected();
        this.fresqueryobtenido = resQueryObte;
        this.ftipoTest=tipoTest;
    }
    
    void addOntologyTestFailureSparql(String testNameUsuario, SparqlQueryOntology querysparql, 
            List<ExecQuerySparql> resQueryEspe,List<ExecQuerySparql> resQueryObte,TipoTest tipoTest){
        this.ftestNameUsuario=testNameUsuario;
        this.fquerysparql = querysparql.getQuerySparql();
        this.fresultsparqlexpected = querysparql.getResultexpected();
        this.fressparqlobtenido = resQueryObte;
        this.fressparqlesperado = resQueryEspe;
        this.ftipoTest=tipoTest;
    }
    
    public String getResultQueryObtenido() {    
         return this.fresqueryobtenido;    
     }  
    
    public List<ExecQuerySparql> getResultSparqlQueryObtenido() {    
         return this.fressparqlobtenido;    
     }
    
    public List<ExecQuerySparql> getResultSparqlQueryEsperado() {    
         return this.fressparqlesperado;    
     }
    
    public String getQueryfComment(){
        return this.fcommentquery;
    }
    
    public String getSparqlfComment(){
        return this.fcommentsparqlquery;
    }
    
    public String getfQuery(){
        return this.fquery;
    }
    
    public String getfSparqlQuery(){
        return this.fquerysparql;
    }
    
    public String getfResultExpected(){
        return this.fresultexpected;
    }
    
    public String getfResultSparqlExpected(){
        return this.fresultsparqlexpected;
    }
    
    public String getTestNameUsuario(){
        return this.ftestNameUsuario;
    }
    
    public TipoTest getTipoTest() {
        return ftipoTest;
    }

    public void setTipoTest(TipoTest tipoTest) {
        this.ftipoTest = tipoTest;
    }
    
    public String showSimpleTest() {
        result = "<b>Consulta: </b>" +this.fquery+"<br><b>Resultado esperado: </b>" 
                +this.fresultexpected+"<br><b>Resultado obtenido: </b>" +this.fresqueryobtenido;
        return result;
    }
    
    public String mostrarResultadoSparql(){
        List<ExecQuerySparql> execQ = this.fressparqlobtenido;
        if(execQ.size()>0){
            String resultadoTotal="";
            for(int i=0;i<execQ.size();i++){
                String resultado = "";
                String nombre = execQ.get(i).getNombreSelect();
                List<String> datos = execQ.get(i).getDatos();
                resultado = nombre+"(";
                for(int j=0;j<datos.size();j++){
                    if(j!=datos.size()-1){
                        resultado = resultado+datos.get(j)+",";
                    }else{
                         resultado = resultado+datos.get(j)+")";
                    }
                }
                resultadoTotal=resultadoTotal+resultado+"\n";
            }
            return resultadoTotal;
        }else{
            return "No se han producido resultados";    
        }
    }
    
    public String showSparqlTest() {
        
        List<ExecQuerySparql> execQObte = this.fressparqlobtenido;
        List<ExecQuerySparql> execQEspe = this.fressparqlesperado;
        String resultadoTotalObte="";
        String nombreObte = "";
        String datosObte = "";
        String resultadoTotalEspe="";
        String nombreEspe = "";
        String datosEspe = "";
        
        if(execQObte.size()>0 && execQEspe.size()>0){
        
            for(int i=0;i<execQObte.size();i++){
                nombreObte = nombreObte + execQObte.get(i).getNombreSelect() + "&nbsp;&nbsp;&nbsp;";
            }
            nombreObte = nombreObte + "<br>--------------------<br>";

            int tam = execQObte.get(0).getDatos().size();
            for(int i=0; i<tam;i++){
                for(int j=0;j<execQObte.size();j++){
                        datosObte = datosObte + execQObte.get(j).getDatos().get(i) + "&nbsp;&nbsp;&nbsp;";
                }
                datosObte = datosObte + "<br>";
            }
            resultadoTotalObte = nombreObte + datosObte;

            for(int i=0;i<execQEspe.size();i++){
                nombreEspe = nombreEspe + execQEspe.get(i).getNombreSelect() + "&nbsp;&nbsp;&nbsp;";
            }
            nombreEspe = nombreEspe + "<br>------------------------------------------<br>";

            int tamEsp = execQEspe.get(0).getDatos().size();
            for(int i=0; i<tamEsp;i++){
                for(int j=0;j<execQEspe.size();j++){
                        datosEspe = datosEspe + execQEspe.get(j).getDatos().get(i) + "&nbsp;&nbsp;&nbsp;";
                }
                datosEspe = datosEspe + "<br>";
            }
            resultadoTotalEspe = nombreEspe + datosEspe;
            result = "<b>Consulta: </b>" +this.fquerysparql+"<br><br><b>Resultado esperado: </b><br>" 
                        +resultadoTotalEspe+"<br><b>Resultado obtenido: </b><br>" +resultadoTotalObte;
        }else if(execQObte.size()==0 && execQEspe.size()>0){
            result = "<b>Consulta: </b>" +this.fquerysparql+"<br><br><b>Resultado esperado: </b><br>" 
                    +resultadoTotalEspe+"<br><b>Resultado obtenido: </b><br>No se han producido resultados"; 
        }else if(execQObte.size()>0 && execQEspe.size()==0){
        result = "<b>Consulta: </b>" +this.fquerysparql+"<br><b>Resultado esperado: </b><br>No se han producido resultados."+
                    "<br><b>Resultado obtenido: </b><br>" +resultadoTotalObte;
        }else{
            result = "<b>Consulta: </b>" +this.fquerysparql+"<br><br><b>Resultado esperado: </b><br>No se han producido resultados"+
                    "<br><b>Resultado obtenido: </b><br>No se han producido resultados";   
        }
        
        return result; 
    }

}
