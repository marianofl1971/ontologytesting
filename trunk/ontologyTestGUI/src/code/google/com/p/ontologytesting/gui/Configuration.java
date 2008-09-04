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
    
    private static String pathTestSimples="";
    private static String pathTestSparql="";
    private static String pathInstancias="";

    public static String getPathTestSimples() {
        if(pathTestSimples.equals("")){
            return AlmacenPropiedadesConfig.getPropiedad("simpleTests");
        }else{
            return pathTestSimples;
        }
    }

    public static void setPathTestSimples(String apathTestSimples) {
        pathTestSimples = apathTestSimples;
    }

    public static String getPathTestSparql() {
        if(pathTestSparql.equals("")){
            return AlmacenPropiedadesConfig.getPropiedad("sparqlTests");
        }else{
            return pathTestSparql;
        }
    }

    public static void setPathTestSparql(String apathTestSparql) {
        pathTestSparql = apathTestSparql;
    }

    public static String getPathInstancias() {
        if(pathInstancias.equals("")){
            return AlmacenPropiedadesConfig.getPropiedad("instancias");
        }else{
            return pathInstancias;
        }
    }

    public static void setPathInstancias(String apathInstancias) {
        pathInstancias = apathInstancias;
    }

}
