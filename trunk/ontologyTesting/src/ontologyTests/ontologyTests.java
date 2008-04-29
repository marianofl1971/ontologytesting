/*
 * instantiationTest.java
 * 
 * Created on 21-feb-2008, 10:47:18
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontologyTests;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Resource;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author sara_garcia
 */
public class ontologyTests {
    
    //Saber si un individuo pertenece a una clase
    public String instantiation(String ns, String className, String individualName, 
            OntModel model){
        
        OntClass ontClass = model.getOntClass(ns + className);

        if(ontClass==null) 
        {
            return "false";
        }
      
        Iterator it = ontClass.listInstances();       
              
        while(it.hasNext()){
            String instanceName = it.next().toString();
            instanceName = instanceName.substring(instanceName.indexOf("#")+1);
            if(individualName.equals(instanceName)) {
                return "true";
            }
        }
        return "false";
    } 
    
    //Saber TODOS los individuos que pertenecen a una clase
    //TODOS los individuos que son instancias de un concepto    
    public StringBuilder retieval(String ns, String className, OntModel model){
        
        int cont=0;
        StringBuilder rval = new StringBuilder();
        OntClass ontClass = model.getOntClass(ns + className);         
        Iterator it = ontClass.listInstances();
        
        while(it.hasNext())
        {
            String instanceName=it.next().toString();
            instanceName=instanceName.substring(instanceName.indexOf("#")+1);
            
            if(!rval.equals("")){
                if(cont==0){
                    rval.append(instanceName);
                    cont=1;
                }else{
                    rval.append(","+instanceName);
                }
            }
        }
        return rval;
    }    

    //Saber la clase mas exacta a la que pertenece un individuo
    //Dado un individuo, encontrar el concepto más específico que lo contiene
    public String realization(String ns, String individualName, OntModel model){
         
        Individual individual = model.getIndividual(ns + individualName);
        
        if(individual==null) return "FAILED";
        
        Resource resource = individual.getRDFType(true); 
        String className = resource.toString().substring(resource.toString().indexOf("#")+1);
        
        return className;
    }  
    
    //Saber si un concepto (concepto) está incluido en otro (loIncluye)
    public String satisfactibility(String ns, OntModel model, String concepto, 
            String loIncluye){
                
        OntClass male = model.getOntClass(ns + loIncluye);
        for(Iterator i = male.listSubClasses(); i.hasNext();){
            OntClass c = (OntClass) i.next();
            if(c.getLocalName().equals(concepto))
                return "true";
        }
        return "false";
    }
    
    //Dado un individuo, deducir todas las clases a las que pertenece
    public ArrayList classification(String ns, OntModel model, String individuo){
        
        String pertenece;
        ArrayList clases = new ArrayList();
        Iterator it = model.listClasses();

        while(it.hasNext()){
            String[] instanceName = it.next().toString().split("#");
            pertenece = instantiation(ns, instanceName[1].toString(), individuo, model);
            if(pertenece.equals("true")){
                clases.add(instanceName[1]);
            }
        }       
        return clases;
    }
    
}
