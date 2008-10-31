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
    private boolean cargado = false;
    
    public InterfaceReasoner getReasoner(){
        try {
            reasoner = (InterfaceReasoner) Class.forName("code.google.com.p.ontologytesting.model.reasonerinterfaz.driver.ReasonerImplementation").newInstance();
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
