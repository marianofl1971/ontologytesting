/*
 * SeeTestJDialog.java
 *
 * Created on 16 de septiembre de 2008, 13:25
 */

package code.google.com.p.ontologytesting.gui.menupanels;

import code.google.com.p.ontologytesting.model.*;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author  sara.garcia
 */
public class SeeTestJDialog extends javax.swing.JDialog {

    /** Creates new form SeeTestJDialog */
    public SeeTestJDialog(JFrame parent, boolean modal, ScenarioTest scenario) {
        super(parent, modal);
        initComponents();
        this.setTitle("Ver Test");
        this.setSize(new Dimension(650,600));
        this.setLocationRelativeTo(this.getParent());
        testDescriptionPane.setContentType("text/html");
        String print = verTest(scenario);
        testDescriptionPane.setText(print);
        testDescriptionPane.setEditable(false);
        scrollPane.getVerticalScrollBar().setValue(0);
    }
    
    public SeeTestJDialog(JFrame parent, boolean modal, Instancias instancias) {
        super(parent, modal);
        initComponents();
        this.setTitle("Ver Instancias");
        this.setSize(new Dimension(650,600));
        testDescriptionPane.setContentType("text/html");
        String print = verInstancias(instancias);
        testDescriptionPane.setText(print);
        testDescriptionPane.setEditable(false);
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        testDescriptionPane = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        scrollPane.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane.setAlignmentX(0.0F);
        scrollPane.setAlignmentY(0.0F);
        scrollPane.setHorizontalScrollBar(null);

        testDescriptionPane.setContentType("text/html");
        testDescriptionPane.setAutoscrolls(false);
        scrollPane.setViewportView(testDescriptionPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    public String verTest(ScenarioTest scenario){
        StringBuffer verTest= new StringBuffer();
        verTest.append("<html>");
        String testName = scenario.getNombre();
        String desc = scenario.getDescripcion();
        String consultas="";
        Instancias inst = scenario.getInstancias();
        List<QueryOntology> listQ = scenario.getQueryTest();
        List<SparqlQueryOntology> listQSparql = scenario.getSparqlQuerys();
        verTest.append("<b>Nombre del Test:</b> ").append(testName).append("<br><br><b>Descripcion:</b>")
                .append(desc).append("<br><br><b>Instancias Asociadas</b><br><br>");
        String instancias = generarInstancias(inst,0);
        if(!scenario.getTipoTest().name().equals("SPARQL")){
            consultas = generarConsultasSimples(listQ);
        }else{
            consultas = generarConsultasSparql(listQSparql);
        }
        verTest.append(instancias).append("<b>Consultas Realizadas:</b><br><br>")
                .append(consultas).append("</html>");
        return verTest.toString();
    } 
    
    public String verInstancias(Instancias instancias){
        String verTest="<html>";
        String inst = generarInstancias(instancias,1);
        verTest = inst+"</html>";
        return verTest;
    } 
    
    public String generarInstancias(Instancias instancias, int tipo){ 
        String desc = instancias.getDescripcion();
        String nom = instancias.getNombre();
        StringBuffer bufProp = new StringBuffer();
        StringBuffer bufClas = new StringBuffer();
        StringBuffer bufRes = new StringBuffer();
        List<PropertyInstances> propInst = instancias.getPropertyInstances();
        List<ClassInstances> clasInst = instancias.getClassInstances();
        
        if(propInst.size()==0){
            bufProp.append("Este test no tiene instancias de propiedad<br>");
        }else{
            for(int i=0;i<propInst.size();i++){
                bufProp.append(propInst.get(i).getPropertyInstance()).append("<br>");
            }
        }
        
        if(clasInst.size()==0){
            bufClas.append("Este test no tiene instancias de clase<br>");
        }else{
            for(int i=0;i<clasInst.size();i++){
                bufClas.append(clasInst.get(i).getClassInstance()).append("<br>");
            }
        }
        
        if(propInst.size()==0 && clasInst.size()==0){
            return "El test no tiene instancias asociadas<br><br>";
        }else{
                bufRes.append("Nombre: ").append(nom).append("<br><br>").append("Descripcion: ")
                        .append(desc).append("<br><br>").append("<u>De Clase</u><br><br>")
                        .append(bufClas).append("<br><u>De Propiedad</u><br><br>").append(bufProp)
                        .append("<br>");
                return bufRes.toString();
        }
        
    }
    
    public String generarConsultasSimples(List<QueryOntology> listaQ){
        StringBuffer bufRes = new StringBuffer();
        for(int i=0;i<listaQ.size();i++){
            bufRes.append("Consulta: ").append(listaQ.get(i).getQuery()).append("<br>Resultado: ")
                    .append(listaQ.get(i).getResultexpected()).append("<br>");
        }
        if(bufRes.length()==0){
            return "El test no tiene consultas<br>";
        }else{
            return bufRes.toString();
        }  
    }
    
    public String generarConsultasSparql(List<SparqlQueryOntology> listaQ){
        StringBuffer bufRes = new StringBuffer();
        for(int i=0;i<listaQ.size();i++){
            bufRes.append("Consulta: ").append(listaQ.get(i).getQuery()).append("<br>Resultado: ")
                    .append(listaQ.get(i).getResultexpected()).append("<br>");
        }
        if(bufRes.length()==0){
            return "El test no tiene consultas<br>";
        }else{
            return bufRes.toString();
        }  
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JEditorPane testDescriptionPane;
    // End of variables declaration//GEN-END:variables

}
