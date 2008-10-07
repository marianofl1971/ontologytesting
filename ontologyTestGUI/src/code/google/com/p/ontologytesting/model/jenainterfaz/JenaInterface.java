/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model.jenainterfaz;

import code.google.com.p.ontologytesting.model.jenainterfaz.driverjena.JenaImplementation;

/**
 *
 * @author sara.garcia
 */
public class JenaInterface {

    private Jena jena = new JenaImplementation();
    
    public Jena getJena(){
        return jena;
    }
    
    public JenaInterface(){
    }

}
