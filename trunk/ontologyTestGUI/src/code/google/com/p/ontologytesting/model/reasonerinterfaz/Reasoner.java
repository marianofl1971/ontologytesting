/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model.reasonerinterfaz;


/**
 *
 * @author sara.garcia
 */
public class Reasoner {

    private InterfaceReasoner reasoner;
    
    public InterfaceReasoner getReasoner(){
        try {
            reasoner = (InterfaceReasoner) Class.forName("code.google.com.p.ontologytesting.model.jenainterfaz.driverjena.JenaImplementation").newInstance();
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

}
