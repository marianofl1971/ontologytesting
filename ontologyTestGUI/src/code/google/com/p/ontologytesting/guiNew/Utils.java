/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.guiNew;

import code.google.com.p.ontologytesting.model.ScenarioTest;
import java.util.List;

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
    
    

}
