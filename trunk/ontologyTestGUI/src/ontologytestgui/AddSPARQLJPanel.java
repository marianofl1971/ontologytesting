/*
 * AddSPARQLJPanel.java
 *
 * Created on 19 de mayo de 2008, 19:12
 */

package ontologytestgui;

/**
 *
 * @author  Saruskas
 */
public class AddSPARQLJPanel extends javax.swing.JPanel {

    /** Creates new form AddSPARQLJPanel */
    public AddSPARQLJPanel() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sparqlTextArea = new javax.swing.JTextArea();
        ejecutarButton = new javax.swing.JButton();
        limpiarButton = new javax.swing.JButton();
        borrarButton = new javax.swing.JButton();

        jLabel1.setText("Introduzca la consulta en SPARQL:");

        sparqlTextArea.setColumns(20);
        sparqlTextArea.setRows(5);
        jScrollPane1.setViewportView(sparqlTextArea);

        ejecutarButton.setText("Ejecutar");

        limpiarButton.setText("Limpiar");

        borrarButton.setText("Borrar");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(limpiarButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 492, Short.MAX_VALUE)
                        .add(borrarButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(ejecutarButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 220, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 15, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(ejecutarButton)
                    .add(limpiarButton)
                    .add(borrarButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton borrarButton;
    private javax.swing.JButton ejecutarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton limpiarButton;
    private javax.swing.JTextArea sparqlTextArea;
    // End of variables declaration//GEN-END:variables

    public void setSPARQLQuery(String sparql){
        sparqlTextArea.setText(sparql);
    }
    
    public String getSPARQLQuery(){
        return sparqlTextArea.getText();
    }
}
