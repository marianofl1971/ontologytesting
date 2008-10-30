/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.guiNew.auxiliarclasess;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author sara.garcia
 */
public class AniadirPanelDeAviso {

    public void confirmAction(String msg, JDialog dialog){
        JOptionPane.showMessageDialog(dialog,msg,                                                  
        "Confirm Message",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void errorAction(String msg, JDialog dialog){
        JOptionPane.showMessageDialog(dialog,msg,                                                  
        "Error Message",JOptionPane.ERROR_MESSAGE);
    }
    
    public void warningAction(String msg, JDialog dialog){
        JOptionPane.showMessageDialog(dialog,msg,                                                  
        "Warning Message",JOptionPane.WARNING_MESSAGE);
    }
    
    public void confirmAction(String msg, JFrame dialog){
        JOptionPane.showMessageDialog(dialog,msg,                                                  
        "Confirm Message",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void errorAction(String msg, JFrame dialog){
        JOptionPane.showMessageDialog(dialog,msg,                                                  
        "Error Message",JOptionPane.ERROR_MESSAGE);
    }
    
    public void warningAction(String msg, JFrame dialog){
        JOptionPane.showMessageDialog(dialog,msg,                                                  
        "Warning Message",JOptionPane.WARNING_MESSAGE);
    }
}
