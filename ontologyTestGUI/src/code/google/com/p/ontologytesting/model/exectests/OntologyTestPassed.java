/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model.exectests;

import code.google.com.p.ontologytesting.model.*;
import code.google.com.p.ontologytesting.model.ScenarioTest.TipoTest;
import java.util.List;

/**
 *
 * @author sara.garcia
 */
public class OntologyTestPassed extends OntologyTestFailure{
    
    public OntologyTestPassed(){
    }   
    
    void addOntologyTestPassedQuery(String testNameUsuario, QueryOntology query, String resQueryObte,TipoTest tipoTest){
        this.ftestNameUsuario=testNameUsuario;
        this.fquery = query.getQuery();
        this.fcommentquery = query.getComment();
        this.fresultexpected = query.getResultexpected();
        this.fresqueryobtenido = resQueryObte;
        this.ftipoTest=tipoTest;
    }
    
    void addOntologyTestPassedSparql(String testNameUsuario, SparqlQueryOntology querysparql, 
            List<ExecQuerySparql> resQueryEspe,List<ExecQuerySparql> resQueryObte,TipoTest tipoTest){
        this.ftestNameUsuario=testNameUsuario;
        this.fquerysparql = querysparql.getQuery();
        this.fresultsparqlexpected = querysparql.getResultexpected();
        this.fressparqlobtenido = resQueryObte;
        this.fressparqlesperado = resQueryEspe;
        this.ftipoTest=tipoTest;
    }

}
