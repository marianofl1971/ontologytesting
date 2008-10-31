/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.gui.auxiliarclasess;

import code.google.com.p.ontologytesting.gui.*;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author sara.garcia
 */
public class FileChooserSelector {
    
    private JFileChooser filechooser;
    private File fileSelected;
    private static String pathSelected="";
    public final static String xml = "xml", owl="owl";

    public void fileChooser(boolean open,boolean onlyFiles){
        int option;
        filechooser = new JFileChooser(FileChooserSelector.getPathSelected());
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
          this.setFileSelected(fileSelected);
          FileChooserSelector.setPathSelected(selectedFile.getAbsolutePath());
        }
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


    public File getFileSelected() {
        return fileSelected;
    }

    public void setFileSelected(File fileSelected) {
        this.fileSelected = fileSelected;
    }

    public static String getPathSelected() {
        return pathSelected;
    }

    public static void setPathSelected(String apathSelected) {
        pathSelected = apathSelected;
    }

}
