/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.gui.auxiliarclasess;

import code.google.com.p.ontologytesting.gui.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 *
 * @author sara.garcia
 */
public class FileChooserSelector {

    private JFileChooser filechooser;
    private String linea,nsDefecto,nombreProyecto;
    private static String pathSelected="";
    public final static String xml = "xml", owl="owl";
    private File pathDirectorioProyecto = null;

    public boolean fileChooser(boolean open,boolean onlyFiles, boolean newProject,boolean primero){
        int option,var=0;
        if(primero==true){
            filechooser = new JFileChooser(Configuration.getPropiedades().getProperty("ULTIMOPATH"));
        }else{
            filechooser = new JFileChooser(FileChooserSelector.getPathSelected());
        }
        if(onlyFiles==false){
            filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        }else{
            filechooser.addChoosableFileFilter(new TypeFilter());
            filechooser.setAcceptAllFileFilterUsed(false);
            filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        }
        if(open==true){
            option = filechooser.showOpenDialog(MainApplicationJFrame.getInstance());
        }else{
            option = filechooser.showSaveDialog(MainApplicationJFrame.getInstance());
        }
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = filechooser.getSelectedFile();
            if(primero==true){
                Configuration.getInstance().cambiarPath(selectedFile.getParent());
            }
            setPathDirectorioProyecto(selectedFile.getParentFile());
            setNombreProyecto(selectedFile.getName());
            if(newProject==true){
                BufferedReader bf = null;
                try {
                    bf = new BufferedReader(new FileReader(selectedFile.getAbsolutePath()));
                    while ((linea = bf.readLine()) != null && var==0) {
                            if(linea.contains("xmlns=\"")) {
                                int indexBegin = linea.indexOf("xmlns=\"");
                                int indexEnd = linea.indexOf("#", indexBegin);
                                setNsDefecto(linea.substring(indexBegin+7, indexEnd).concat("#"));
                                var = 1;
                            }else{
                                setNsDefecto("http://www.owl-ontologies.com/unnamed.owl");
                            }
                    }
                    bf.close();
                } catch (FileNotFoundException ex) {
                }catch (IOException ex) {   
                }
            }
            FileChooserSelector.setPathSelected(selectedFile.getAbsolutePath());
            return true;
        }else return false;
    }

    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
    
    public boolean deleteDirectory(File path)
    {
        if(path.exists()){
          File[] files = path.listFiles();
          for(int i=0; i<files.length; i++) {
             if(files[i].isDirectory()) {
               deleteDirectory(files[i]);
             }
             else {
               files[i].delete();
             }
          }
          return path.delete();
        }
        return false;
    }

    public static String getPathSelected() {
        return pathSelected;
    }

    public static void setPathSelected(String apathSelected) {
        pathSelected = apathSelected;
    }
    
    public String getNsDefecto() {
        return nsDefecto;
    }

    public void setNsDefecto(String aNsDefecto) {
        nsDefecto = aNsDefecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public File getPathDirectorioProyecto() {
        return pathDirectorioProyecto;
    }

    public void setPathDirectorioProyecto(File pathDirectorioProyecto) {
        this.pathDirectorioProyecto = pathDirectorioProyecto;
    }

}
