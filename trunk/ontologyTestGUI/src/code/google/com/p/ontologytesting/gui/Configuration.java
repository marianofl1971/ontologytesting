/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.gui;

/**
 *
 * @author sara.garcia
 */
public class Configuration {
    

    public static String getPathTestSimples() {
        return AlmacenPropiedadesConfig.getPropiedad("simpleTests");
    }

    public static String getPathTestSparql() {
        return AlmacenPropiedadesConfig.getPropiedad("sparqlTests");
    }

    public static String getPathInstancias() {
        return AlmacenPropiedadesConfig.getPropiedad("instancias");
    }
}
