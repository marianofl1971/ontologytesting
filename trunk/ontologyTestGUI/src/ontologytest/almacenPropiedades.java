/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontologytest;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.HashMap;
/**
 *
 * @author sara_garcia
 */
    public class almacenPropiedades {

    private static final String CONFIGURATION_FILE = "Configuracion.properties";
    private static HashMap propiedades;

    /* Bloque de inicializacion
    */
    static {

    try {
        
      FileInputStream f = new FileInputStream(CONFIGURATION_FILE);
      Properties propiedadesTemporales = new Properties();
      propiedadesTemporales.load(f);
      f.close();

      propiedades = new HashMap(propiedadesTemporales);

    } catch (Exception e) {
    }
  }

  private almacenPropiedades() { }

  public static String getPropiedad(String nombre) throws faltaPropiedadException {

    String valor = (String) propiedades.get(nombre);
    if (valor == null) throw new faltaPropiedadException(nombre);
    return valor;
  }

}
