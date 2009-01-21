/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model.reasonerinterfaz;

import code.google.com.p.ontologytesting.gui.Configuration;
import java.util.Properties;


/**
 *
 * @author sara.garcia
 */
public class Reasoner {

    private InterfaceReasoner reasoner;
    private boolean cargado = false;
    
    public InterfaceReasoner getReasoner(){
       Properties propiedades = Configuration.getPropiedades();
        try {
            reasoner = (InterfaceReasoner) Class.forName(propiedades.getProperty("DRIVER")).newInstance();
            this.setCargado(true);
        } catch (InstantiationException ex) {  
            System.out.println("Instantiation Exception");
        } catch (IllegalAccessException ex) {       
            System.out.println("Illegal Access Exception");
        } catch (ClassNotFoundException ex) {   
            System.out.println("ClassNotFoundException");
        }
        return reasoner;
    }
    
    public Reasoner(){
    }

    public boolean isCargado() {
        return cargado;
    }

    public void setCargado(boolean cargado) {
        this.cargado = cargado;
    }

}
