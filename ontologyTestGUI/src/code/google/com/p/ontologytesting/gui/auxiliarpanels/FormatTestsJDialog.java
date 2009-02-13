/*
 * FormatTestsJDialog.java
 *
 * Created on 27 de agosto de 2008, 8:54
 */

package code.google.com.p.ontologytesting.gui.auxiliarpanels;

import code.google.com.p.ontologytesting.model.ScenarioTest;
import java.awt.Dimension;

/**
 *
 * @author  sara.garcia
 */
public class FormatTestsJDialog extends javax.swing.JDialog {

    /** Creates new form FormatTestsJDialog */
    public FormatTestsJDialog(java.awt.Frame parent, boolean modal, String tipo) {
        super(parent, modal);
        initComponents();
        this.setModal(true);
        String formato="";
        descripcionEditorPane.setContentType("text/html");
        if(tipo.equals("INST")){
            formato = generarFormatoPermitidoInstanciacion();
        }else if(tipo.equals("RET")){
            formato = generarFormatoPermitidoRetrieval();
        }else if(tipo.equals("REAL")){
            formato = generarFormatoPermitidoRealizacion();
        }else if(tipo.equals("SAT")){
            formato = generarFormatoPermitidoSatisfactibilidad();
        }else if(tipo.equals("CLAS")){
            formato = generarFormatoPermitidoClasificacion();
        }else if(tipo.equals("SPARQL")){
            formato = generarFormatoPermitidoSPARQL();
        }
        descripcionEditorPane.setText(formato);
        descripcionEditorPane.setEditable(false);
        descripcionEditorPane.setCaretPosition(0);
        this.setTitle("Formatos de Tests Permitidos");
    }

    private String generarFormatoPermitidoInstanciacion(){
        this.setSize(new Dimension(500,410));
        String formato="<html>" +
                "<b>TEST DE INSTANCIACIÓN</b><br><br>Este test consiste en deducir si un individuo " +
                "pertenece a una clase.<br>" +
                "Ejemplo:<br>" +
                "Consulta: Wife,marry <br> Resultado esperado: True<br>" +
                "En este caso el test no fallaría, ya que el individuo " +
                "'marry' aparece definido como Wife en el conjunto de instancias " +
                "de la ontología. <br><br>" +
                "<h><b>Formatos Permitidos</h></b><br><br>" +
                "<b>Consultas</b><br>" +
                "Clase,individuo<br>" +
                "Clase(individuo)<br><br>" +
                "<b>Resultado Esperado</b><br>" +
                "True/False";
        return formato;
    }
    
    private String generarFormatoPermitidoRetrieval(){
        this.setSize(new Dimension(500,410));
        String formato="<html>" +
                "<b>TEST DE RECUPERACIÓN</b><br><br>Este test deduce, dada una clase, todos los individuos que " +
                "pertenecen a dicha clase (todos los individuos que son instancias " +
                "de ese concepto).<br>" +
                "Ejemplo:<br>" +
                "Consulta: Wife <br> Resultado esperado: marry,lee<br>" +
                "En este caso el test no fallaría, ya que para la clase 'Wife'" +
                " todos los individuos que le pertenecen son los contemplados: " +
                "'marry,lee'.<br><br>" +
                "<h><b>Formatos Permitidos</h></b><br><br>" +
                "<b>Consultas</b><br>" +
                "Clase<br><br>" +
                "<b>Resultado Esperado</b><br>" +
                "Lista de individuos que pertenecen a la clase especificada " +
                "separados por coma.<br>" +
                "Ejemplo: [individuo1,individuo2,...,individuoN]<br>" +
                "Ejemplo2: individuo1,individuo2,...,individuoN";
        return formato;
    }
    
    private String generarFormatoPermitidoRealizacion(){
        this.setSize(new Dimension(500,410));
        String formato="<html>" +
                "<b>TEST DE REALIZACIÓN</b><br><br>Este test deduce, dado un individuo, cual es la clase más exacta " +
                "o específica a la que pertenece (dado un individuo, encuentra el concepto " +
                "más específico que lo contiene).<br>" +
                "Ejemplo:<br>" +
                "Consulta: marry <br> Resultado esperado: Wife<br>" +
                "En este caso el test no fallaria, ya que la clase mas especifica que " +
                "contiene al individuo 'marry' es 'Wife'.<br><br>" +
                "<h><b>Formatos Permitidos</h></b><br><br>" +
                "<b>Consultas</b><br>" +
                "Indiviudo<br><br>" +
                "<b>Resultado Esperado</b><br>" +
                "Clase";
        return formato;
    }
    
    private String generarFormatoPermitidoSatisfactibilidad(){
        this.setSize(new Dimension(500,410));
        String formato="<html>" +
                "<b>TEST DE SATISFACTIBILIDAD</b><br><br>Este test consiste en deducir si una nueva clase puede ser añadida a la " +
                "ontología sin que entre en conflicto con el conjunto de instancias " +
                "ya definido para esa ontología.<br>" +
                "Ejemplo:<br>" +
                "Consulta: sara,Wife <br> Resultado esperado: True<br>" +
                "En este caso el test no fallaría, ya que el individuo" +
                " 'sara' no estaba definido aún en la ontologia. <br><br>" +
                "<h><b>Formatos Permitidos</h></b><br><br>" +
                "<b>Consultas</b><br>" +
                "Individuo,Clase<br>" +
                "Individuo(Clase)<br><br>" +
                "<b>Resultado Esperado</b><br>" +
                "True/False";
        return formato;
    }
    
    private String generarFormatoPermitidoClasificacion(){
        this.setSize(new Dimension(500,410));
        String formato="<html>" +
                "<b>TEST DE CLASIFICACIÓN</b><br><br>Este test deduce, dado un individuo, todas las clases a las que pertenece.<br>" +
                "Ejemplo:<br>" +
                "Consulta: marry <br> Resultado esperado: Wife,Female,Person<br>" +
                "En este caso el test no fallaría, ya que todas las clases a las que puede" +
                " pertenecer 'marry' son las especificadas. <br><br>" +
                "<h><b>Formatos Permitidos</h></b><br><br>" +
                "<b>Consultas</b><br>" +
                "Individuo<br><br>" +
                "<b>Resultado Esperado</b><br>" +
                "Lista de clases a las que pertenece el individuo separados por coma.<br>" +
                "Ejemplo1: [individuo1,individuo2,...,individuoN]<br>" +
                "Ejemplo2: individuo1,individuo2,...,individuoN";
        return formato;
    }
    
    private String generarFormatoPermitidoSPARQL(){
        this.setSize(new Dimension(600,410));
        String formato="<html>" +
                "<b>TEST SPARQL</b><br><br>Este test ejecuta una consulta Sparql válida. " +
                "Para más información acerca del lenguaje de consultas SPARQL visite:<br>" +
                "http://www.w3.org/TR/rdf-sparql-query/<br><br>" +
                "<h><b>Formatos Permitidos</h></b><br><br>" +
                "<b>Consultas</b><br><br>" +
                "Serán válidas aquellas consultas que sean " +
                "gramaticalmente correctas, siguiendo la sintaxis de SPARQL. " +
                "Sólo estan permitidas consultas tipo SELECT.<br><br>" +
                "<b>Resultado Esperado</b><br><br>" +
                "En caso de un SELECT simple, el resultado deberá ser el sujeto " +
                "que se indicó en el SELECT, seguido de una lista " +
                "de objetos separados por coma y encerrados todos ellos entre paréntesis.<br><br>" +
                "En caso de un SELECT múltiple, el resultado deberá de especificar " +
                "en primer lugar el sujeto al que se refiere en el SELECT y, entre " +
                "paréntesis, el resultado o la lista de resultados separados por " +
                "coma que se espera obtener.<br><br>" +
                "Por ejemplo, para la consulta:<br><br>" +
                "PREFIX rdfs:&lt;http://www.w3.org/2000/01/rdf-schema#> <br>" +
                "SELECT ?subject ?object <br>" +
                "FROM 'ruta a la ontologia' (opcional)<br>" +
                "WHERE {?subject rdfs:subClassOf ?object } <br><br>" +
                "El resultado se debe de mostrar de la siguiente forma:<br>" +
                "subject(a,b,c)<br>" +
                "object(a,b,c)<br>"+
                "Los distintos SELECTS que se indicaron deben de aparecer en fila (separados " +
                "<b>por un salto de linea).</b>";    
        return formato;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcionEditorPane = new code.google.com.p.ontologytesting.gui.JEditorPaneCopyPaste();
        cerrarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        descripcionEditorPane.setFont(new java.awt.Font("Arial", 0, 11));
        jScrollPane1.setViewportView(descripcionEditorPane);

        cerrarButton.setText("Cerrar");
        cerrarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(cerrarButton)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cerrarButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void cerrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarButtonActionPerformed
// TODO add your handling code here:
    this.setVisible(false);//GEN-LAST:event_cerrarButtonActionPerformed
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cerrarButton;
    private javax.swing.JEditorPane descripcionEditorPane;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
