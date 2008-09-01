/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.jenainterfaz;

import com.hp.hpl.jena.query.QueryException;
import java.awt.Component;
import javax.swing.JOptionPane;
import org.mindswap.pellet.exceptions.UnsupportedFeatureException;

/**
 *
 * @author sara.garcia
 */
public class ExceptionsImplementation extends QueryException implements JenaExceptions{
    private Component frame;
    private String mensaje;
    
    public ExceptionsImplementation(String mensaje){
        this.mensaje=mensaje;
    }
    
    @Override
    public void SparqlException(String mensaje) throws QueryException,UnsupportedFeatureException{
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
