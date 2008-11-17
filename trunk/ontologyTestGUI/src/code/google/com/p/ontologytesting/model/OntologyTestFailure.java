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
    protected StringBuffer result= new StringBuffer();
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
        this.fquerysparql = querysparql.getQuery();
        this.fresultsparqlexpected = querysparql.getResultexpected();
        this.fressparqlobtenido = resQueryObte;
        this.fressparqlesperado = resQueryEspe;
        this.ftipoTest=tipoTest;
    }
    
    public String showSimpleTest() {
        result= new StringBuffer();
        result.append("<b>Consulta: </b>").append(this.fquery).append("<br><b>Resultado esperado: </b>") 
                .append(this.fresultexpected).append("<br><b>Resultado obtenido: </b>").append(this.fresqueryobtenido);
        return result.toString();
    }
    
    public String mostrarResultadoSparql(){
        List<ExecQuerySparql> execQ = this.fressparqlobtenido;
        if(execQ.size()>0){
            StringBuffer bufResultadoTotal = new StringBuffer();
            for(int i=0;i<execQ.size();i++){
                StringBuffer bufResultado = new StringBuffer();
                String nombre = execQ.get(i).getNombreSelect();
                List<String> datos = execQ.get(i).getDatos();
                bufResultado.append(nombre).append("(");
                for(int j=0;j<datos.size();j++){
                    if(j!=datos.size()-1){
                        bufResultado.append(datos.get(j)).append(",");
                    }else{
                         bufResultado.append(datos.get(j)).append(")");
                    }
                }
                bufResultadoTotal.append(bufResultado).append("\n");
            }
            return bufResultadoTotal.toString();
        }else{
            return "No se han producido resultados";    
        }
    }
    
    public String showSparqlTest() {
        
        List<ExecQuerySparql> execQObte = this.fressparqlobtenido;
        List<ExecQuerySparql> execQEspe = this.fressparqlesperado;
        StringBuffer resultadoTotalObte = new StringBuffer();
        StringBuffer nombreObte = new StringBuffer();
        StringBuffer datosObte = new StringBuffer();
        StringBuffer resultadoTotalEspe = new StringBuffer();
        StringBuffer nombreEspe = new StringBuffer();
        StringBuffer datosEspe = new StringBuffer();
        result= new StringBuffer();
        
        if(execQObte!=null && execQEspe!=null){
            if(execQObte.size()>0 && execQEspe.size()>0){

                for(int i=0;i<execQObte.size();i++){
                    nombreObte.append(execQObte.get(i).getNombreSelect()).append("&nbsp;&nbsp;&nbsp;");
                }
                nombreObte.append("<br>--------------------<br>");

                int tam = execQObte.get(0).getDatos().size();
                for(int i=0; i<tam;i++){
                    for(int j=0;j<execQObte.size();j++){
                        if(i<execQObte.get(j).getDatos().size()){
                            datosObte.append(execQObte.get(j).getDatos().get(i)).append("&nbsp;&nbsp;&nbsp;");
                        }
                    }
                    datosObte.append("<br>");
                }
                resultadoTotalObte.append(nombreObte).append(datosObte);

                for(int i=0;i<execQEspe.size();i++){
                    nombreEspe.append(execQEspe.get(i).getNombreSelect()).append("&nbsp;&nbsp;&nbsp;");
                }
                nombreEspe.append("<br>------------------------------------------<br>");

                int tamEsp = execQEspe.get(0).getDatos().size();
                for(int i=0; i<tamEsp;i++){
                    for(int j=0;j<execQEspe.size();j++){
                        if(i<execQEspe.get(j).getDatos().size()){
                            datosEspe.append(execQEspe.get(j).getDatos().get(i)).append("&nbsp;&nbsp;&nbsp;");
                        }
                    }
                    datosEspe.append("<br>");
                }
                resultadoTotalEspe.append(nombreEspe).append(datosEspe);

                result.append("<b>Consulta: </b>").append(this.fquerysparql).append("<br><br><b>Resultado esperado: </b><br>") 
                            .append(resultadoTotalEspe).append("<br><b>Resultado obtenido: </b><br>").append(resultadoTotalObte);
            }else if(execQObte.size()==0 && execQEspe.size()>0){
                for(int i=0;i<execQEspe.size();i++){
                    nombreEspe.append(execQEspe.get(i).getNombreSelect()).append("&nbsp;&nbsp;&nbsp;");
                }
                nombreEspe.append("<br>------------------------------------------<br>");

                int tamEsp = execQEspe.get(0).getDatos().size();
                for(int i=0; i<tamEsp;i++){
                    for(int j=0;j<execQEspe.size();j++){
                        if(i<execQEspe.get(j).getDatos().size()){
                            datosEspe.append(execQEspe.get(j).getDatos().get(i)).append("&nbsp;&nbsp;&nbsp;");
                        }
                    }
                    datosEspe.append("<br>");
                }
                resultadoTotalEspe.append(nombreEspe).append(datosEspe);

                result.append("<b>Consulta: </b>").append(this.fquerysparql).append("<br><br><b>Resultado esperado: </b><br>") 
                            .append(resultadoTotalEspe).append("<br><b>Resultado obtenido: </b><br>").append("No se han obtenido resultados pare esta consulta.");
            }else{
                result.append("<b>Consulta: </b>").append(this.fquerysparql).append("<br><br><b>Resultado esperado: </b><br>No se han producido resultados")
                        .append("<br><b>Resultado obtenido: </b><br>No se han producido resultados");   
            }
        }else{
            result.append("No hay datos para este test");
        }
        return result.toString(); 
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

}
