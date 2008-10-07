/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model.jenainterfaz;

import java.awt.Component;
import javax.swing.JOptionPane;


/**
 *
 * @author saruskas
 */
public class ExceptionReadOntology extends RuntimeException {

    private Component frame;
    /**
     * Creates a new instance of <code>ExceptionReadOntology</code> without detail message.
     */
    public ExceptionReadOntology() {
    }

    /**
     * Constructs an instance of <code>ExceptionReadOntology</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ExceptionReadOntology(String msg) {
        super(msg);
        JOptionPane.showMessageDialog(frame,msg,"Error Message",JOptionPane.ERROR_MESSAGE);
    }
    
}
