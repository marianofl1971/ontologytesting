/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author sara.garcia
 */
public class Configuration {
    
    private static Configuration config = null;
    
    private synchronized static void createConfiguration() {
        if (config == null) { 
            config = new Configuration();
        }
    }
 
    public static Configuration getInstance() {
        if (config == null) createConfiguration();
        return config;
    }
    
    public Properties cargarDriver(){
        String home = System.getProperty("user.home");
        String arch = "configuracion.properties";
        String directorio = "/.ontologyTestGUI/"+arch;
        String rutaDelArchivo = home + directorio;
        Properties propiedades = new Properties();
        File archivo = new File(rutaDelArchivo);
        try {
            if (!archivo.exists()) {
            Properties tmp = new Properties();
            tmp.setProperty("HOME",home);
            tmp.setProperty("DRIVER", "code.google.com.p.ontologytesting.model.reasonerinterfaz.driver.ReasonerImplementation");
            File directorio_file = new File(home+"/.ontologyTestGUI/");
            try{
                directorio_file.mkdir();
            }catch(SecurityException ex){
                System.out.println("No se pudo crear el directorio");
            }
            FileOutputStream out = new FileOutputStream(home+"/.ontologyTestGUI/"+arch);
            tmp.store(out, "Configuracion de OntologyTestGUI");
            out.close();
            }
            FileInputStream in =new FileInputStream(archivo);
            propiedades.load(in);
        }catch (IOException ex) {
            System.out.println("Error durante la configuracion");
        }
        return propiedades;
    }

}
