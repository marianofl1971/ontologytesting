/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontologytesting;

/**
 *
 * @author sara_garcia
 */
public class faltaPropiedadException extends Exception {

      private String nombreParametro;

      public faltaPropiedadException(String nombreParametro) {
        super("Falta parametro de configuracion: '" + nombreParametro + "'");
        this.nombreParametro = nombreParametro;
      }

      public String getNombreParametro() {
        return nombreParametro;
      }
}

