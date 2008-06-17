
/*
 * Main.java
 * 
 * Created on 16-feb-2008, 13:22:03
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontologytest;


import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import clases.OntologyTestCase;
import clases.OntologyTestResult;
import model.ClassInstances;
import model.CollectionTest;
import model.PropertyInstances;
import model.QueryOntology;
import model.ScenarioTest;
import model.SparqlQueryOntology;

/**
 *
 * @author Saruskas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws FileNotFoundException, IOException{
        
     String ontologyFisical="",ontologyURI="";

     try {

        ontologyFisical = almacenPropiedades.getPropiedad("ontologyFisical");
        ontologyURI = almacenPropiedades.getPropiedad("ontologyURI");      
        
        ArrayList<ClassInstances> myClassInstances = new ArrayList<ClassInstances>();
        ArrayList<PropertyInstances> myPropertyInstances = new ArrayList<PropertyInstances>();
        ArrayList<String> sparql_Res = new ArrayList<String>();
        ArrayList<QueryOntology> querys = new ArrayList<QueryOntology>();
        ArrayList<SparqlQueryOntology> sparql_querys = new 
                ArrayList<SparqlQueryOntology>();
        ArrayList<ScenarioTest> scenario = new ArrayList<ScenarioTest>();
        String testname = "instantiation";
        
        ClassInstances instClas1 = new ClassInstances("Male(john)","comentario1");
        ClassInstances instClas2 = new ClassInstances("Male(tom)","comentario2");
        ClassInstances instClas3 = new ClassInstances("Female(marry)","comentario3");
        ClassInstances instClas4 = new ClassInstances("Wife(tom)","comentario4");
        ClassInstances instClas5 = new ClassInstances("Male(john)","comentario5");

        PropertyInstances instProp1 = new PropertyInstances("hasChild(lee,tom)","comentario1");
        PropertyInstances instProp2 = new PropertyInstances("hasChild(marry,john)","comentario2");
        
        myClassInstances.add(instClas1);
        myClassInstances.add(instClas2);
        myClassInstances.add(instClas3);
        myClassInstances.add(instClas4);
        myClassInstances.add(instClas5);
        
        myPropertyInstances.add(instProp1);
        myPropertyInstances.add(instProp2);
        
        sparql_Res.add("Wife");
        sparql_Res.add("Lee");
      
        QueryOntology queryontology1 = new QueryOntology("Wife,marry","false",
                "query 1");
        QueryOntology queryontology2 = new QueryOntology("Wife,tom","false",
                "query_2");
        
        querys.add(queryontology1);
        querys.add(queryontology2);
        
        ScenarioTest scenariotest = new ScenarioTest(myClassInstances,
                myPropertyInstances, querys, testname);
        
        scenario.add(scenariotest);
        
        CollectionTest test = new CollectionTest(scenario, ontologyFisical, ontologyURI);
        
        OntologyTestResult testresult = new OntologyTestResult();
        OntologyTestCase testcase = new OntologyTestCase();       
        
        try {
            XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new 
                    FileOutputStream("instances.xml")));
            e.writeObject(test);
            e.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       testcase.run(testresult, test);
        
      }catch (faltaPropiedadException e){
            System.out.println(e);
      }  
    }

}
