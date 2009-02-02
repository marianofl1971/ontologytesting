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
            setPropiedades(new Properties());
        }
    }
 
    public static Configuration getInstance() {
        if (config == null) createConfiguration();
        return config;
    }
    
    public Properties cargarDriver(){
        archivo = new File(rutaDelArchivo);
        try {
            Properties tmp = new Properties();
            if (!archivo.exists()) {
            tmp.setProperty("HOME",home);
            tmp.setProperty("DRIVER", "code.google.com.p.ontologytesting.model.reasonerinterfaz.driver.ReasonerImplementation");
            tmp.setProperty("IDIOMA", "code.google.com.p.ontologytesting.gui.internacionalization.Spanish");
            tmp.setProperty("LOCALE", "es");
            tmp.setProperty("ULTIMOPATH", home);
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
            getPropiedades().load(in);
            in.close();
        }catch (IOException ex) {
            System.out.println("Error durante la configuracion");
        }
        return getPropiedades();
    }
    
    public boolean cambiarIdioma(String idioma,String id){
        getPropiedades().remove("IDIOMA");
        getPropiedades().remove("LOCALE");
        getPropiedades().setProperty("IDIOMA", idioma);
        getPropiedades().setProperty("LOCALE", id);
        FileOutputStream out = null;
        try{
            out = new FileOutputStream(archivo);
            getPropiedades().store(out, "Configuracion de OntologyTestGUI");
            out.close();
            FileInputStream in =new FileInputStream(archivo);
            getPropiedades().load(in);
            in.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean cambiarPath(String nuevoPath){
        getPropiedades().remove("ULTIMOPATH");
        getPropiedades().setProperty("ULTIMOPATH", nuevoPath);
        FileOutputStream out = null;
        try{
            out = new FileOutputStream(archivo);
            getPropiedades().store(out, "Configuracion de OntologyTestGUI");
            out.close();
            FileInputStream in =new FileInputStream(archivo);
            getPropiedades().load(in);
            in.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public String obtenerIdioma(){
        return getPropiedades().getProperty("IDIOMA");
    }
    
    public static Properties getPropiedades() {
        if(propiedades==null){
            propiedades = Configuration.getInstance().cargarDriver();
        }
        return propiedades;
    }

    public static void setPropiedades(Properties aPropiedades) {
        propiedades = aPropiedades;
    }
    
    
}
