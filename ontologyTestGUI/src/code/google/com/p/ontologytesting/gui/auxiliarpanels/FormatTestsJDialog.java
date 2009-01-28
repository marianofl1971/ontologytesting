/*
 * FormatTestsJDialog.java
 *
 * Created on 27 de agosto de 2008, 8:54
 */

package code.google.com.p.ontologytesting.gui.auxiliarpanels;

import java.awt.Dimension;

/**
 *
 * @author  sara.garcia
 */
public class FormatTestsJDialog extends javax.swing.JDialog {

    /** Creates new form FormatTestsJDialog */
    public FormatTestsJDialog(java.awt.Frame parent, boolean modal, int tab) {
        super(parent, modal);
        initComponents();
        this.setModal(true);
        String formato="";
        descripcionEditorPane.setContentType("text/html");
        if(tab==0){
            formato = generarFormatoPermitidoInstanciacion();
        }else if(tab==1){
            formato = generarFormatoPermitidoRetrieval();
        }else if(tab==2){
            formato = generarFormatoPermitidoRealizacion();
        }else if(tab==3){
            formato = generarFormatoPermitidoSatisfactibilidad();
        }else if(tab==4){
            formato = generarFormatoPermitidoClasificacion();
        }else if(tab==5){
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
                "<h><b>Formatos para el Test de Instanciación</h></b><br><br>" +
                "<b>Consultas</b><br><br>" +
                "Clase,individuo<br>" +
                "Clase(individuo)<br><br>" +
                "<b>Resultado Esperado</b><br><br>" +
                "True/False";
        return formato;
    }
    
    private String generarFormatoPermitidoRetrieval(){
        this.setSize(new Dimension(500,410));
        String formato="<html>" +
                "<h><b>Formatos para el Test de Recuperación</h></b><br><br>" +
                "<b>Consultas</b><br><br>" +
                "Clase<br><br>" +
                "<b>Resultado Esperado</b><br><br>" +
                "Lista de individuos separados por coma";
        return formato;
    }
    
    private String generarFormatoPermitidoRealizacion(){
        this.setSize(new Dimension(500,410));
        String formato="<html>" +
                "<h><b>Formatos para el Test de Realización</h></b><br><br>" +
                "<b>CONSULTAS</b><br><br>" +
                "Indiviudo<br><br>" +
                "<b>RESULTADO ESPERADO</b><br><br>" +
                "Clase";
        return formato;
    }
    
    private String generarFormatoPermitidoSatisfactibilidad(){
        this.setSize(new Dimension(500,410));
        String formato="<html>" +
                "<h><b>Formatos para el Test De Satisfactibilidad</h></b><br><br>" +
                "<b>Consultas</b><br><br>" +
                "Individuo,Clase<br>" +
                "Individuo(Clase)<br><br>" +
                "<b>Resultado Esperado</b><br><br>" +
                "True/False";
        return formato;
    }
    
    private String generarFormatoPermitidoClasificacion(){
        this.setSize(new Dimension(500,410));
        String formato="<html>" +
                "<h><b>Formatos para el Test de Clasificación</h></b><br><br>" +
                "<b>Consultas</b><br><br>" +
                "Individuo<br><br>" +
                "<b>Resultado Esperado</b><br><br>" +
                "Lista de clases separados por coma";
        return formato;
    }
    
    private String generarFormatoPermitidoSPARQL(){
        this.setSize(new Dimension(600,410));
        String formato="<html>" +
                "<h><b>Formatos para el Test Sparql</h></b><br><br>" +
                "<b>Consultas</b><br><br>" +
                "Serán validas aquellas consultas que sean" +
                "gramaticalmente correctas siguiendo la sintaxis de SPARQL." +
                "Sólo estan permitidas consultas tipo SELECT.<br><br>" +
                "<b>Resultado Esperado</b><br><br>" +
                "En caso de un SELECT simple, el resultado deberá de ser el sujeto" +
                "que se indico en el SELECT, seguido de una lista " +
                "de objetos separados por coma o punto.<br><br>" +
                "En caso de un SELECT multiple el resultado deberá de especificar" +
                "en primer lugar el sujeto al que se refiere en SELECT y entre " +
                "parentesis el resultado o la lista de resultados separados por " +
                "coma o punto que se espera obtener.<br><br>" +
                "Por ejemplo, para la consulta:<br><br>" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> <br>" +
                "SELECT ?subject ?object <br>" +
                "FROM 'ruta a la ontologia' <br>" +
                "WHERE {?subject rdfs:subClassOf ?object } <br><br>" +
                "El resultado se debe de mostrar de la siguient forma:<br><br>" +
                "subject(a,b,c)<br>" +
                "object(a,b,c)"+
                "Los distintos SELECTS que se indicaron deben de aparecer en fila (separados" +
                "por un salto de linea o por un punto y coma, es decir:" +
                "subject(a,c);" +
                "object(a,b,c)";    
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
        descripcionEditorPane = new javax.swing.JEditorPane();
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
