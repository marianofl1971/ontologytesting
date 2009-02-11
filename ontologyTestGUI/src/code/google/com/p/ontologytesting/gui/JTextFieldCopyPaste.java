/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.gui;

import com.borland.dbswing.DBTextDataBinder;
import com.borland.dbswing.IntlSwingSupport;
import java.util.Locale;
import javax.swing.JTextField;

/**
 *
 * @author a
 */
public class JTextFieldCopyPaste extends JTextField{
    
  DBTextDataBinder dBTextDataBinder1 = new DBTextDataBinder();
    public JTextFieldCopyPaste(){
        super();
        IntlSwingSupport intlSwingSupport1 = new IntlSwingSupport(new Locale("en","US"));
        dBTextDataBinder1.setJTextComponent(this);
        dBTextDataBinder1.setEnableFileLoading(false);
        dBTextDataBinder1.setEnableFileSaving(false);
        dBTextDataBinder1.setEnableColorChange(false);
        dBTextDataBinder1.setEnableFontChange(false);
        dBTextDataBinder1.setEnableURLLoading(false);
        dBTextDataBinder1.setEnableURLAutoCache(false);
    }

}
