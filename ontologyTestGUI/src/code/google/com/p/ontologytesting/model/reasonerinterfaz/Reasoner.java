/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model.reasonerinterfaz;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


/**
 *
 * @author sara.garcia
 */
public class Reasoner {

    private InterfaceReasoner reasoner;
    private boolean cargado = false;
    
    public InterfaceReasoner getReasoner(){
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
        try {
            reasoner = (InterfaceReasoner) Class.forName(propiedades.getProperty("DRIVER")).newInstance();
            this.setCargado(true);
        } catch (InstantiationException ex) {  
            System.out.println("Instantiation Exception");
        } catch (IllegalAccessException ex) {       
            System.out.println("Illegal Access Exception");
        } catch (ClassNotFoundException ex) {   
            System.out.println("ClassNotFoundException");
        }
        return reasoner;
    }
    
    public Reasoner(){
    }

    public boolean isCargado() {
        return cargado;
    }

    public void setCargado(boolean cargado) {
        this.cargado = cargado;
    }

}
