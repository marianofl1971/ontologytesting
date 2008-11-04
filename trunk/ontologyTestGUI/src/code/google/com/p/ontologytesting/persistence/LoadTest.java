/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.persistence;

import code.google.com.p.ontologytesting.model.CollectionTest;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.*;

/**
 *
 * @author sara.garcia
 */
public class LoadTest {
    
    private Reasoner jena = new Reasoner();

    public boolean loadProject(String ubicOnto,String namespaceOnto) throws ExceptionReadOntology{                                             
        InterfaceReasoner j = jena.getReasoner();
        if(jena.isCargado()==false){
            return false;
        }else{
            if(!namespaceOnto.endsWith("#")){
                namespaceOnto = namespaceOnto.concat("#");
            }
            j.addReasoner(ubicOnto);
            CollectionTest.getInstance().setNamespace(namespaceOnto);
            CollectionTest.getInstance().setOntology(ubicOnto);
            return true;
        }
    }
    
    public void prepareProject(CollectionTest collection){
        CollectionTest.getInstance().setInstancias(collection.getInstancias());
        CollectionTest.getInstance().setNamespace(collection.getNamespace());
        CollectionTest.getInstance().setOntology(collection.getOntology());
        CollectionTest.getInstance().setScenariotest(collection.getScenariotest());
    }
}
