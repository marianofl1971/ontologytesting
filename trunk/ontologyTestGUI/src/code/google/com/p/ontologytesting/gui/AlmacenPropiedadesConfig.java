/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package code.google.com.p.ontologytesting.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.HashMap;
/**
 *
 * @author sara_garcia
 */
    public class AlmacenPropiedadesConfig {

    private static final String CONFIGURATION_FILE = "Configuracion.properties";
    private static HashMap propiedades;
    private static File configurationFile;
    private static FileWriter config;

    /* Bloque de inicializacion
    */
    static {
    try {   
      FileInputStream f = new FileInputStream(CONFIGURATION_FILE);
      configurationFile = new File(CONFIGURATION_FILE);
      
      Properties propiedadesTemporales = new Properties();
      propiedadesTemporales.load(f);
      f.close();
      propiedades = new HashMap(propiedadesTemporales);
    } catch (Exception e) {
        e.printStackTrace();
    }
  }

  private AlmacenPropiedadesConfig() { }

  public static String getPropiedad(String nombre){
    String valor = (String) propiedades.get(nombre);
    if (valor == null) return "";
    return valor;
  }
  
  public static void setConfiguracion(String simple,String sparql,String inst){
    String dataConfig = "simpleTests = "+simple+"\n"+"sparqlTests = "+sparql+"\n"+"instancias = "+inst;
      try {
        config = new FileWriter(configurationFile);
        config.write(dataConfig);
        config.close();
    } catch (IOException e) { 
        e.printStackTrace();
    }
  }


}
