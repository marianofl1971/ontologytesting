/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.guiNew;

import code.google.com.p.ontologytesting.model.CollectionTest;
import code.google.com.p.ontologytesting.model.Instancias;
import code.google.com.p.ontologytesting.model.ScenarioTest;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author sara.garcia
 */
public class Utils {
    
    public ScenarioTest buscarScenario(List<ScenarioTest> scenario, String name){
        for(int i=0;i<scenario.size();i++){
            String nombre = scenario.get(i).getNombre();
            if(nombre.equals(name)){
                return scenario.get(i);
            }
        }
        return null;
    }
    
    public Instancias buscarInstancias(List<Instancias> inst, String name){
        for(int i=0;i<inst.size();i++){
            String nombre = inst.get(i).getNombre();
            if(nombre.equals(name)){
                return inst.get(i);
            }
        }
        return null;
    }
    
    public boolean testYaExiste(String nombre){
        ListIterator li;
        List<ScenarioTest> lista = CollectionTest.getInstance().getScenariotest();
        li = lista.listIterator();
        while(li.hasNext()){
            ScenarioTest s = (ScenarioTest) li.next();
            String n = s.getNombre();
            if(n.equals(nombre)){
                return true;
            }
        }
        return false;
    }
    
    

}
