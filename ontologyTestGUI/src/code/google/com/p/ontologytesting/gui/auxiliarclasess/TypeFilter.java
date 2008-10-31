/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.gui.auxiliarclasess;

/**
 *
 * @author sara.garcia
 */
import java.io.File;
import javax.swing.filechooser.*;

public class TypeFilter extends FileFilter{

    //Accept all directories and all gif, jpg, tiff, or png files.
    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        String extension = FileChooserSelector.getExtension(f);
        if (extension != null) {
            if (extension.equals(FileChooserSelector.xml) || extension.equals(FileChooserSelector.owl)) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    //The description of this filter
    @Override
    public String getDescription() {
        return ".xml .owl";
    }
}


