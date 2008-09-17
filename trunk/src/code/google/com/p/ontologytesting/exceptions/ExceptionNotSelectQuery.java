/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.exceptions;

/**
 *
 * @author saruskas
 */
public class ExceptionNotSelectQuery extends Exception {

    /**
     * Creates a new instance of <code>ExceptionNotSelectQuery</code> without detail message.
     */
    public ExceptionNotSelectQuery() {
    }


    /**
     * Constructs an instance of <code>ExceptionNotSelectQuery</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ExceptionNotSelectQuery(String msg) {
        super(msg);
    }
}
