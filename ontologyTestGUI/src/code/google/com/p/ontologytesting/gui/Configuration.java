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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sara.garcia
 */
public class Configuration {
    
    private static Configuration config = null;
    private String home = System.getProperty("user.home");
    private String arch = "configuracion.properties";
    private String directorio = "/.ontologyTestGUI/"+arch;
    private String rutaDelArchivo = home + directorio;
    private static Properties propiedades = null;
    private File archivo;
    
    private synchronized static void createConfiguration() {
        if (config == null) { 
            config = new Configuration();
            propiedades = new Properties();
        }
    }
 
    public static Configuration getInstance() {
        if (config == null) createConfiguration();
        return config;
    }
    
    public Properties cargarDriver(){
        archivo = new File(rutaDelArchivo);
        try {
            if (!archivo.exists()) {
            Properties tmp = new Properties();
            tmp.setProperty("HOME",home);
            tmp.setProperty("DRIVER", "code.google.com.p.ontologytesting.model.reasonerinterfaz.driver.ReasonerImplementation");
            tmp.setProperty("IDIOMA", "code.google.com.p.ontologytesting.gui.internacionalization.Spanish");
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
    
    public boolean cambiarIdioma(String idioma){
        propiedades.remove("IDIOMA");
        propiedades.setProperty("IDIOMA", idioma);
        FileOutputStream out = null;
        try{
            out = new FileOutputStream(archivo);
            propiedades.store(out, "Configuracion de OntologyTestGUI");
            out.close();
            FileInputStream in =new FileInputStream(archivo);
            propiedades.load(in);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public String obtenerIdioma(){
        return propiedades.getProperty("IDIOMA");
        /*FileOutputStream out = null;
        try{
            out = new FileOutputStream(archivo);
            propiedades.store(out, "Configuracion de OntologyTestGUI");
            out.close();
            FileInputStream in =new FileInputStream(archivo);
            propiedades.load(in);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
}
