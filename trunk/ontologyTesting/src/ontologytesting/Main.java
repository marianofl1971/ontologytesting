
/*
 * Main.java
 * 
 * Created on 16-feb-2008, 13:22:03
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontologytesting;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ontologyClasses.OntologyTestCase;
import ontologyClasses.OntologyTestResult;
import ontologyModel.CollectionTest;
import ontologyModel.QueryOntology;
import ontologyModel.ScenarioTest;

/**
 *
 * @author Saruskas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException{
        
     String ontologyFisical="",ontologyURI="";

     try {

      ontologyFisical = almacenPropiedades.getPropiedad("ontologyFisical");
      ontologyURI = almacenPropiedades.getPropiedad("ontologyURI");      
        
        ArrayList<String> myClassInstances = new ArrayList<String>();
        ArrayList<String> myPropertyInstances = new ArrayList<String>();
        ArrayList<QueryOntology> querys = new ArrayList<QueryOntology>();
        ArrayList<ScenarioTest> scenario = new ArrayList<ScenarioTest>();

        myClassInstances.add("Male(john)");
        myClassInstances.add("Male(tom)");
        myClassInstances.add("Female(marry)");
        myClassInstances.add("Wife(lee)");
        myPropertyInstances.add("hasChild(lee,tom)");
        myPropertyInstances.add("hasChild(marry,john)");

        QueryOntology queryontology1 = new QueryOntology("Wife,marry","true",
                "query 1");
        QueryOntology queryontology2 = new QueryOntology("Wife,tom","true",
                "query_2");
        
        querys.add(queryontology1);
        querys.add(queryontology2); 
        
        ScenarioTest scenariotest = new ScenarioTest(myClassInstances,
                myPropertyInstances, querys);
        
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
        
      }catch (faltaPropiedadException e){
            System.out.println(e);
      }  
    }

}
