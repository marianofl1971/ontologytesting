/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.exceptions;

import java.awt.Component;
import javax.swing.JOptionPane;


/**
 *
 * @author saruskas
 */
public class ExceptionReadOntology extends Exception {

    private Component frame;
    /**
     * Creates a new instance of <code>ExceptionReadOntology</code> without detail message.
     */
    public ExceptionReadOntology() {
        JOptionPane.showMessageDialog(frame,"La ontologia introducida no es valida." +
        "\nSolo pueden realizarse tests sobre documentos owl consistentes","Error Message",JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Constructs an instance of <code>ExceptionReadOntology</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ExceptionReadOntology(String msg) {
        super(msg);
    }
    
}
