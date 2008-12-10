/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model.reasonerinterfaz;


/**
 *
 * @author saruskas
 */
public class InvalidOntologyException extends RuntimeException {

    /**
     * Creates a new instance of <code>ExceptionReadOntology</code> without detail message.
     */
    public InvalidOntologyException() {
    }

    /**
     * Constructs an instance of <code>ExceptionReadOntology</code> with the specified detail message.
     * @param msg the detail message.
     */
    public InvalidOntologyException(String msg) {
        super(msg);
        
    }   
    
}
