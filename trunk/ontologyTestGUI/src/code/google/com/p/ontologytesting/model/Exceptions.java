/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author saruskas
 */
public class Exceptions extends Exception{
    private Component frame;
    private String mensaje;
    
    public Exceptions(String men){
        mensaje=men;
        JOptionPane.showMessageDialog(frame,mensaje,
                "Warning Message",JOptionPane.WARNING_MESSAGE);
    }
    
    public void ficheroOntologia(String mensaje) throws Exception{
        JOptionPane.showMessageDialog(frame,mensaje,
                "Warning Message",JOptionPane.WARNING_MESSAGE);
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
