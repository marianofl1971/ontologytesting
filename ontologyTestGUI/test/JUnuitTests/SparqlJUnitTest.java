/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package JUnuitTests;

import code.google.com.p.ontologytesting.model.ClassInstances;
import code.google.com.p.ontologytesting.model.CollectionTest;
import code.google.com.p.ontologytesting.model.Instancias;
import code.google.com.p.ontologytesting.model.PropertyInstances;
import code.google.com.p.ontologytesting.model.ScenarioTest;
import code.google.com.p.ontologytesting.model.ScenarioTest.TipoTest;
import code.google.com.p.ontologytesting.model.SparqlQueryOntology;
import code.google.com.p.ontologytesting.model.exectests.OntologyTestCase;
import code.google.com.p.ontologytesting.model.exectests.OntologyTestResult;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author sara.garcia
 */
public class SparqlJUnitTest extends TestCase {
    
    private ScenarioTest scenario = new ScenarioTest();
    private Instancias instancias = new Instancias();
    private ArrayList<ClassInstances> classInstances;
    private ArrayList<PropertyInstances> propertyInstances;
    private List<SparqlQueryOntology> sparqlQuerys;
    private TipoTest tipoTest;
    private OntologyTestCase testCase = new OntologyTestCase();
    private OntologyTestResult testResult = new OntologyTestResult();
    private CollectionTest baterytest = new CollectionTest();
    private ArrayList<ScenarioTest> scenariotest;
    
    public SparqlJUnitTest(String testName) {
        super(testName);
    }            

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        instancias.setNombre("Instancias Prueba 1");
        classInstances.add(new ClassInstances("Snake(piton)"));
        classInstances.add(new ClassInstances("Snake(vibora)"));
        classInstances.add(new ClassInstances("Snake(lagarto)"));
        classInstances.add(new ClassInstances("Bird(piolin)"));
        classInstances.add(new ClassInstances("Lion(tiger)"));
        classInstances.add(new ClassInstances("Ant(hormiga)"));
        classInstances.add(new ClassInstances("Bee(maya)"));
        classInstances.add(new ClassInstances("Crow(urraca)"));
        classInstances.add(new ClassInstances("Spider(viudanegra)")); 
        propertyInstances.add(new PropertyInstances());
        instancias.setClassInstances(classInstances);
        instancias.setPropertyInstances(propertyInstances);
        sparqlQuerys.add(new SparqlQueryOntology("PREFIX po:<http://nlp.shef.ac.uk/abraxas/ontologies/animals.owl#>" +
                "PREFIX  rdfs:<http://www.w3.org/2000/01/rdf-schema#>" +
                "SELECT ?nombre" +
                "WHERE {po:Snake rdfs:subClassOf ?nombre}","nombre(Organism,Thing,Animals,Reptile,Snake)"));
        sparqlQuerys.add(new SparqlQueryOntology("",""));
        sparqlQuerys.add(new SparqlQueryOntology("",""));
        scenario.setNombre("Scenario Prueba 1");
        scenario.setInstancias(instancias);
        scenario.setSparqlQuerys(sparqlQuerys);
        scenario.setTipoTest(tipoTest.SPARQL);
        baterytest.setNamespace("http://nlp.shef.ac.uk/abraxas/ontologies/animals.owl#");
        baterytest.setOntology("C:\\Documents and Settings\\sara_garcia\\Escritorio\\Proyectos\\Proyecto animales\\animals.owl");
        scenariotest.add(scenario);
        baterytest.setScenariotest(scenariotest);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testSparql() {
        testCase.run(testResult, baterytest, scenario);
    }

}
