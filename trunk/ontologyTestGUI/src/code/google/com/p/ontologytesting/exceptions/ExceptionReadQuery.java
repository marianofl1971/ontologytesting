/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.exceptions;

/**
 *
 * @author saruskas
 */
public class ExceptionReadQuery extends Exception {

    /**
     * Creates a new instance of <code>ExceptionReadQuery</code> without detail message.
     */
    public ExceptionReadQuery() {
    }


    /**
     * Constructs an instance of <code>ExceptionReadQuery</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ExceptionReadQuery(String msg) {
        super(msg);
    }
}
