/*
 * VistaTestJFrame.java
 *
 * Created on 16 de julio de 2008, 12:07
 */

package ontologytestgui;

import java.awt.Dimension;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ListIterator;
import model.ClassInstances;
import model.PropertyInstances;
import model.QueryOntology;
import model.ScenarioTest;
import model.SparqlQueryOntology;
/**
 *
 * @author  sara.garcia
 */
public class VistaTestJFrame extends javax.swing.JFrame {

    private XMLDecoder decoder;
    private ScenarioTest scenario;
    
    /** Creates new form VistaTestJFrame */
    public VistaTestJFrame(String path) {
        initComponents();
        this.setSize(new Dimension(600,600));
        abrirFichero(path);
    }

    public void abrirFichero(String path){
    try{
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(path)));
            
            if(path.contains("Simple Tests")){
                testEditorPane.setContentType("text/html");
                
                scenario = (ScenarioTest) decoder.readObject(); 
                String nombreTest = scenario.getNombre();
                String descripTest = scenario.getDescripcion();
                ArrayList<ClassInstances> clasInst = scenario.getClassInstances();
                ArrayList<PropertyInstances> propInst = scenario.getPropertyInstances();
                ArrayList<QueryOntology> queryOnt = scenario.getQueryTest();
                ListIterator qo;
                qo = queryOnt.listIterator();
                String clas = "", consulta="";  
              
                int sizeClas = clasInst.size();
                int sizeProp = propInst.size();

                if(sizeClas>sizeProp){
                    for(int i=0; i<sizeClas; i++){
                        clas=clas+"<tr><td><font face=\"arial\">"+clasInst.get(i).getClassInstance()+"</font></td>";
                        if(i<propInst.size()){
                            clas=clas+"<td><font face=\"arial\">"+propInst.get(i).getPropertyInstance()+"</font></td></tr>"; 
                        }
                        else{
                            clas = clas+"</tr>";
                        }
                    }
                }else{
                    for(int i=0; i<sizeProp; i++){
                        clas=clas+"<tr><td><font face=\"arial\">"+propInst.get(i).getPropertyInstance()+"</font></td>";
                        if(i<clasInst.size()){
                            clas=clas+"<td><font face=\"arial\">"+clasInst.get(i).getClassInstance()+"</font></td></tr>"; 
                        }
                        else{
                            clas = clas+"</tr>";
                        }
                    }
                }
                while(qo.hasNext()){
                    QueryOntology q = (QueryOntology) qo.next();
                    String query = q.getQuery();
                    String result = q.getResultexpected();
                    consulta=consulta+"<tr><td><font face=\"arial\">"+query+"</font></td>" +
                            "<td><font face=\"arial\">"+result+"</font></td></tr>"; 
                }

                if(sizeClas == 0 && sizeProp == 0){
                    testEditorPane.setText(
                        "<b>Nombre del Test: </b>"+nombreTest+"<br><br>"+
                        "<b>Descripción:</b>"+descripTest+"<br><br>"+
                        "<b>Instancias asociadas: </b><br><br>" +
                        "No hay instancias asociadas al Test.<br><br>" +
                        "<b>Pruebas realizadas: </b><br><br>" +
                        "<table border=1>" +
                        "<tr>" +
                            "<td><i><u>Consulta</u></i></td>" +
                            "<td><i><u>Resultado</u></i></td>" +
                        "</tr>"+consulta+"</table>");  
                }else{
                    testEditorPane.setText(
                        "<b>Nombre del Test: </b>"+nombreTest+"<br><br>"+
                        "<b>Descripción:</b>"+descripTest+"<br><br>"+
                        "<b>Instancias asociadas: </b><br><br>" +
                        "<table border=1>" +
                        "<tr>" +
                            "<td><i><u>De Clase</u></i></td>" +
                            "<td><i><u>De Propiedad</u></i></td>" +
                        "</tr>"+clas+"</table><br><br>" +
                        "<b>Pruebas realizadas: </b><br><br>" +
                        "<table border=1>" +
                        "<tr>" +
                            "<td><i><u>Consulta</u></i></td>" +
                            "<td><i><u>Resultado</u></i></td>" +
                        "</tr>"+consulta+"</table>");   
                }
                
            }else if(path.contains("Instancias")){
                testEditorPane.setContentType("text/html");
                
                ArrayList<ClassInstances> clasInst = (ArrayList<ClassInstances>) decoder.readObject(); 
                ArrayList<PropertyInstances> propInst = (ArrayList<PropertyInstances>) decoder.readObject(); 
                String clas = "";  
              
                int sizeClas = clasInst.size();
                int sizeProp = propInst.size();

                if(sizeClas>sizeProp){
                    for(int i=0; i<sizeClas; i++){
                        clas=clas+"<tr><td><font face=\"arial\">"+clasInst.get(i).getClassInstance()+"</font></td>";
                        if(i<propInst.size()){
                            clas=clas+"<td><font face=\"arial\">"+propInst.get(i).getPropertyInstance()+"</font></td></tr>"; 
                        }
                        else{
                            clas = clas+"</tr>";
                        }
                    }
                }else{
                    for(int i=0; i<sizeProp; i++){
                        clas=clas+"<tr><td><font face=\"arial\">"+propInst.get(i).getPropertyInstance()+"</font></td>";
                        if(i<clasInst.size()){
                            clas=clas+"<td><font face=\"arial\">"+clasInst.get(i).getClassInstance()+"</font></td></tr>"; 
                        }
                        else{
                            clas = clas+"</tr>";
                        }
                    }
                }
                if(sizeClas == 0 && sizeProp == 0){
                    testEditorPane.setText(
                        "<b>Instancias asociadas al test: </b><br><br>" +
                        "No hay instancias.");  
                }else{
                testEditorPane.setText(
                        "<b>Instancias asociadas al test: </b><br><br>" +
                        "<table border=1>" +
                        "<tr>" +
                            "<td><i><u>De Clase</u></i></td>" +
                            "<td><i><u>De Propiedad</u></i></td>" +
                        "</tr>"+clas+"</table><br><br>");                                   
                }
            }else if(path.contains("Sparql Tests")){
                
                testEditorPane.setContentType("text/html");
                
                scenario = (ScenarioTest) decoder.readObject(); 
                String nombreTest = scenario.getNombre();
                String descripTest = scenario.getDescripcion();
                ArrayList<ClassInstances> clasInst = scenario.getClassInstances();
                ArrayList<PropertyInstances> propInst = scenario.getPropertyInstances();
                ArrayList<SparqlQueryOntology> queryOnt = scenario.getSparqlQuerys();
                ListIterator qo;
                qo = queryOnt.listIterator();
                String clas = "", consulta="";  
              
                int sizeClas = clasInst.size();
                int sizeProp = propInst.size();
                
                if(sizeClas == 0 && sizeProp == 0){
                    clas="No hay instancias asociadas a este test";
                }else{
                if(sizeClas>sizeProp){
                    for(int i=0; i<sizeClas; i++){
                        clas=clas+"<tr><td><font face=\"arial\">"+clasInst.get(i).getClassInstance()+"</font></td>";
                        if(i<propInst.size()){
                            clas=clas+"<td><font face=\"arial\">"+propInst.get(i).getPropertyInstance()+"</font></td></tr>"; 
                        }
                        else{
                            clas = clas+"</tr>";
                        }
                    }
                }else{
                    for(int i=0; i<sizeProp; i++){
                        clas=clas+"<tr><td><font face=\"arial\">"+propInst.get(i).getPropertyInstance()+"</font></td>";
                        if(i<clasInst.size()){
                            clas=clas+"<td><font face=\"arial\">"+clasInst.get(i).getClassInstance()+"</font></td></tr>"; 
                        }
                        else{
                            clas = clas+"</tr>";
                        }
                    }
                }
                }
                while(qo.hasNext()){
                    SparqlQueryOntology q = (SparqlQueryOntology) qo.next();
                    String query = q.getQuerySparql();
                    String result = q.getResultexpected();
                    consulta=consulta+"<tr><td><font face=\"arial\">"+query+"</font></td>" +
                            "<td><font face=\"arial\">"+result+"</font></td></tr>"; 
                }
                if(sizeClas == 0 && sizeProp == 0){
                    testEditorPane.setText(
                        "<b>Nombre del Test: </b>"+nombreTest+"<br><br>"+
                        "<b>Descripción:</b>"+descripTest+"<br><br>"+
                        "<b>Instancias asociadas: </b><br><br>" +
                        "No hay instancias asociadas al Test.<br><br>" +
                        "<b>Pruebas realizadas: </b><br><br>" +
                        "<table border=1>" +
                        "<tr>" +
                            "<td><i><u>Consulta</u></i></td>" +
                            "<td><i><u>Resultado</u></i></td>" +
                        "</tr>"+consulta+"</table>");
                }else{
                testEditorPane.setText(
                        "<b>Nombre del Test: </b>"+nombreTest+"<br><br>"+
                        "<b>Descripción:</b>"+descripTest+"<br><br>"+
                        "<b>Instancias asociadas: </b><br><br>" +
                        "<table border=1>" +
                        "<tr>" +
                            "<td><i><u>De Clase</u></i></td>" +
                            "<td><i><u>De Propiedad</u></i></td>" +
                        "</tr>"+clas+"</table><br><br>" +
                        "<b>Pruebas realizadas: </b><br><br>" +
                        "<table border=1>" +
                        "<tr>" +
                            "<td><i><u>Consulta</u></i></td>" +
                            "<td><i><u>Resultado</u></i></td>" +
                        "</tr>"+consulta+"</table>");
                }

            }         
            decoder.close();    
        }catch(FileNotFoundException e){
        }    
        testEditorPane.setEditable(false);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        testScrollPane = new javax.swing.JScrollPane();
        testEditorPane = new javax.swing.JEditorPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        testScrollPane.setViewportView(testEditorPane);

        jMenu1.setText("File");

        jMenuItem1.setText("Imprimir");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(testScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(testScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JEditorPane testEditorPane;
    private javax.swing.JScrollPane testScrollPane;
    // End of variables declaration//GEN-END:variables

}
