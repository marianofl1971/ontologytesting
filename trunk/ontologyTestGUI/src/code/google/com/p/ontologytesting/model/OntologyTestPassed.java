/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

import java.util.List;

/**
 *
 * @author sara.garcia
 */
public class OntologyTestPassed extends OntologyTestFailure{
    
    public OntologyTestPassed(){
    }   
    
    void addOntologyTestPassedQuery(String testNameUsuario, String testName,QueryOntology query, String resQueryObte){
        this.ftestNameUsuario=testNameUsuario;
        this.testName=testName;
        this.fquery = query.getQuery();
        this.fcommentquery = query.getComment();
        this.fresultexpected = query.getResultexpected();
        this.fresqueryobtenido = resQueryObte;
    }
    
    void addOntologyTestPassedSparql(String testNameUsuario, String testName, SparqlQueryOntology querysparql, 
            List<ExecQuerySparql> resQueryEspe,List<ExecQuerySparql> resQueryObte){
        this.ftestNameUsuario=testNameUsuario;
        this.testName=testName;
        this.fquerysparql = querysparql.getQuerySparql();
        this.fresultsparqlexpected = querysparql.getResultexpected();
        this.fressparqlobtenido = resQueryObte;
        this.fressparqlesperado = resQueryEspe;
    }

}
