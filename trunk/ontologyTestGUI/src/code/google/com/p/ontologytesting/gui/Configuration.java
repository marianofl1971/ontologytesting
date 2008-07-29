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
    
    private static String pathTestSimples;
    private static String pathTestSparql;
    private static String pathInstancias;

    public static String getPathTestSimples() {
        return pathTestSimples;
    }

    public static void setPathTestSimples(String pathTestSimples) {
        Configuration.pathTestSimples = pathTestSimples;
    }

    public static String getPathTestSparql() {
        return pathTestSparql;
    }

    public static void setPathTestSparql(String pathTestSparql) {
        Configuration.pathTestSparql = pathTestSparql;
    }

    public static String getPathInstancias() {
        return pathInstancias;
    }

    public static void setPathInstancias(String pathInstancias) {
        Configuration.pathInstancias = pathInstancias;
    }

}
