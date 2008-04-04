
/*
 * Main.java
 * 
 * Created on 16-feb-2008, 13:22:03
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontologytesting;


import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ontologyClasses.OntologyTestCase;
import ontologyClasses.OntologyTestResult;
import ontologyModel.CollectionTest;
import ontologyModel.QueryOntology;
import ontologyModel.ScenarioTest;
/*
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import java.io.File;
import java.io.InputStream; 
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
 */
import ontologyModel.SparqlQueryOntology;

/**
 *
 * @author Saruskas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException{
        
     String ontologyFisical="",ontologyURI="";

     try {

        ontologyFisical = almacenPropiedades.getPropiedad("ontologyFisical");
        ontologyURI = almacenPropiedades.getPropiedad("ontologyURI");      
        
        ArrayList<String> myClassInstances = new ArrayList<String>();
        ArrayList<String> myPropertyInstances = new ArrayList<String>();
        ArrayList<String> sparql_Res = new ArrayList<String>();
        ArrayList<QueryOntology> querys = new ArrayList<QueryOntology>();
        ArrayList<SparqlQueryOntology> sparql_querys = new ArrayList<SparqlQueryOntology>();
        ArrayList<ScenarioTest> scenario = new ArrayList<ScenarioTest>();

        myClassInstances.add("Male(john)");
        myClassInstances.add("Male(tom)");
        myClassInstances.add("Female(marry)");
        myClassInstances.add("Wife(lee)");
        myPropertyInstances.add("hasChild(lee,tom)");
        myPropertyInstances.add("hasChild(marry,john)");
        String sparql_query = 
	"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
	"SELECT ?subject ?object " +
                "WHERE { ?subject rdfs:subClassOf ?object }";
        sparql_Res.add("Wife");
        sparql_Res.add("Lee");
       
        SparqlQueryOntology sparql_q = new SparqlQueryOntology(sparql_query, 
                sparql_Res);
        QueryOntology queryontology1 = new QueryOntology("Wife,marry","true",
                "query 1");
        QueryOntology queryontology2 = new QueryOntology("Wife,tom","true",
                "query_2");
        
        querys.add(queryontology1);
        querys.add(queryontology2); 
        sparql_querys.add(sparql_q);
        
        ScenarioTest scenariotest = new ScenarioTest(myClassInstances,
                myPropertyInstances, querys, sparql_querys);
        
        scenario.add(scenariotest);
        
        CollectionTest test = new CollectionTest(scenario, ontologyFisical, ontologyURI);
        
        OntologyTestResult testresult = new OntologyTestResult();
        OntologyTestCase testcase = new OntologyTestCase();       
        
        try {
            XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("instances.xml")));
            e.writeObject(scenariotest); 
            e.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       testcase.run(testresult, test);
       
       /*Añado el ppio de las querys
        // Open the bloggers RDF graph from the filesystem
        InputStream in = new FileInputStream(new File("data/family.owl"));

        // Create an empty in-memory model and populate it from the graph
        Model model_q = ModelFactory.createMemModelMaker().createModel("");
        model_q.read(in,null); // null base URI, since model URIs are absolute
        in.close();

        String queryString = 
	"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
	"SELECT ?subject ?object " +
                "WHERE { ?subject rdfs:subClassOf ?object }";
        
        Query query = QueryFactory.create(queryString);

        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        // Output query results	
        ResultSetFormatter.out(System.out, results, query);

        // Important - free up resources used running the query
        qe.close();
        
*/
        
      }catch (faltaPropiedadException e){
            System.out.println(e);
      }  
    }

}
