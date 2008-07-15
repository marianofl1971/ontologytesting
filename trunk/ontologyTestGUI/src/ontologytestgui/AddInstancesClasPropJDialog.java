/*
 * AddInstancesClasPropJDialog.java
 *
 * Created on 7 de junio de 2008, 19:55
 */

package ontologytestgui;

import java.awt.Component;
import java.awt.Frame;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.ClassInstances;
import model.PropertyInstances;
import model.ScenarioTest;
/**
 *
 * @author  Saruskas
 */
public class AddInstancesClasPropJDialog extends javax.swing.JDialog {

    static final int desktopWidth = 800;
    static final int desktopHeight = 580;
    static JFrame frame,parent;
    private XMLDecoder decoder;
    private AddComentJDialog commentPane;
    private ArrayList<ClassInstances> clasInst,clasFinal;
    private ArrayList<PropertyInstances> propInst,propFinal;
    private int indexVect;
    private String nombreFichero;
    public static boolean isSeleccionado() {
        return seleccionado;
    }
    public static void setSeleccionado(boolean aSeleccionado) {
        seleccionado = aSeleccionado;
    }
    public static boolean seleccionado;

    public AddInstancesClasPropJDialog(Frame parent, boolean modal,int num, int var) {
        
        super(parent, modal);
        this.setTitle("Asociar Instancias");
        initComponents();
        setSeleccionado(true);
        clasPanel.setLayout(new BoxLayout(clasPanel, BoxLayout.Y_AXIS));
        propPanel.setLayout(new BoxLayout(propPanel, BoxLayout.Y_AXIS));
        
        /*clasPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        propPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        classScrollPane.setMaximumSize(new Dimension(455,422));
        clasPanel.setMaximumSize(new Dimension(455,422));*/
        
        if(var==0){
            for (int i = 0; i <= num; i++) {
                clasPanel.add(new CreateInstancesJPanel());
                propPanel.add(new CreateInstancesJPanel());
        } 
        }else if(var==1){
            clasFinal = new ArrayList<ClassInstances>();
            propFinal = new ArrayList<PropertyInstances>();
            int contI=0,contP=0;

            ListIterator ci,pi;
            if(AddSPARQLJPanel.isSeleccionado()==false){
                clasInst = ContentMainJFrame.getConjuntoClassInstances().get(GroupTestsJPanel.getSelectedTabed());
            }else{
                clasInst = AddSPARQLJPanel.getScenarioTestQuery().getClassInstances();
            }
            ci = clasInst.listIterator();
            while(ci.hasNext()){ 
                ClassInstances cI = (ClassInstances) ci.next();
                CreateInstancesJPanel instClas = new CreateInstancesJPanel();
                instClas.setInstance(cI.getClassInstance());
                commentPane = instClas.getComment();
                commentPane.setComent(cI.getComment());
                instClas.setComment(commentPane);
                clasPanel.add(instClas);
                clasFinal.add(cI);
                contI++;
            }
  
            if(AddSPARQLJPanel.isSeleccionado()==false){
                propInst = ContentMainJFrame.getConjuntoPropInstances().get(GroupTestsJPanel.getSelectedTabed());
            }else{
                propInst = AddSPARQLJPanel.getScenarioTestQuery().getPropertyInstances();
            }
            pi = propInst.listIterator();
            while(pi.hasNext()){
                PropertyInstances pI = (PropertyInstances) pi.next();
                CreateInstancesJPanel instProp = new CreateInstancesJPanel();
                instProp.setInstance(pI.getPropertyInstance());
                commentPane = instProp.getComment();
                commentPane.setComent(pI.getComment());
                instProp.setComment(commentPane);
                propPanel.add(instProp);
                propFinal.add(pI);
                contP++;
            }
            
            for (int j = 0; j <= (num-contI); j++) {
                clasPanel.add(new CreateInstancesJPanel());
            }
            for (int k = 0; k <= (num-contP); k++) {
                propPanel.add(new CreateInstancesJPanel());
            }
        }
    }
    
    public AddInstancesClasPropJDialog(Frame parent, boolean modal,int num,
            ArrayList<ScenarioTest> scenarioT) {
        
        super(parent, modal);
        this.setTitle("Asociar Instancias");
        clasFinal = new ArrayList<ClassInstances>();
        propFinal = new ArrayList<PropertyInstances>();
        int contI=0,contP=0;
        initComponents();
        setSeleccionado(true);
        clasPanel.setLayout(new BoxLayout(clasPanel, BoxLayout.Y_AXIS));
        propPanel.setLayout(new BoxLayout(propPanel, BoxLayout.Y_AXIS));

        ListIterator ci,pi;
        ScenarioTest sT = scenarioT.get(this.indexVect);
        clasInst = sT.getClassInstances();
        ci = clasInst.listIterator();
        while(ci.hasNext()){ 
            ClassInstances cI = (ClassInstances) ci.next();
            CreateInstancesJPanel instClas = new CreateInstancesJPanel();
            instClas.setInstance(cI.getClassInstance());
            commentPane = instClas.getComment();
            commentPane.setComent(cI.getComment());
            instClas.setComment(commentPane);
            clasPanel.add(instClas);
            clasFinal.add(cI);
            contI++;
        }
  
        propInst = sT.getPropertyInstances();
        pi = propInst.listIterator();
        while(pi.hasNext()){
            PropertyInstances pI = (PropertyInstances) pi.next();
            CreateInstancesJPanel instProp = new CreateInstancesJPanel();
            instProp.setInstance(pI.getPropertyInstance());
            commentPane = instProp.getComment();
            commentPane.setComent(pI.getComment());
            instProp.setComment(commentPane);
            propPanel.add(instProp);
            propFinal.add(pI);
            contP++;
        }
            
        for (int j = 0; j <= (num-contI); j++) {
            clasPanel.add(new CreateInstancesJPanel());
        }
        for (int k = 0; k <= (num-contP); k++) {
            propPanel.add(new CreateInstancesJPanel());
        }
    }  
    
    public AddInstancesClasPropJDialog(Frame parent, boolean modal,int num,
        ScenarioTest sT) {
        
        super(parent, modal);
        this.setTitle("Asociar Instancias");
        clasFinal = new ArrayList<ClassInstances>();
        propFinal = new ArrayList<PropertyInstances>();
        int contI=0,contP=0;
        initComponents();
        setSeleccionado(true);
        clasPanel.setLayout(new BoxLayout(clasPanel, BoxLayout.Y_AXIS));
        propPanel.setLayout(new BoxLayout(propPanel, BoxLayout.Y_AXIS));

        ListIterator ci,pi;
        clasInst = sT.getClassInstances();
        ci = clasInst.listIterator();
        while(ci.hasNext()){ 
            ClassInstances cI = (ClassInstances) ci.next();
            CreateInstancesJPanel instClas = new CreateInstancesJPanel();
            instClas.setInstance(cI.getClassInstance());
            commentPane = instClas.getComment();
            commentPane.setComent(cI.getComment());
            instClas.setComment(commentPane);
            clasPanel.add(instClas);
            clasFinal.add(cI);
            contI++;
        }
  
        propInst = sT.getPropertyInstances();
        pi = propInst.listIterator();
        while(pi.hasNext()){
            PropertyInstances pI = (PropertyInstances) pi.next();
            CreateInstancesJPanel instProp = new CreateInstancesJPanel();
            instProp.setInstance(pI.getPropertyInstance());
            commentPane = instProp.getComment();
            commentPane.setComent(pI.getComment());
            instProp.setComment(commentPane);
            propPanel.add(instProp);
            propFinal.add(pI);
            contP++;
        }
            
        for (int j = 0; j <= (num-contI); j++) {
            clasPanel.add(new CreateInstancesJPanel());
        }
        for (int k = 0; k <= (num-contP); k++) {
            propPanel.add(new CreateInstancesJPanel());
        }
    }
    
    public AddInstancesClasPropJDialog(Frame parent, boolean modal,String textName) {
        
        super(parent, modal);
        this.setTitle("Asociar Instancias");
        setNombreFichero(textName);
        setSeleccionado(true);
        initComponents();
        int contP=0,contC=0;
        clasFinal = new ArrayList<ClassInstances>();
        propFinal = new ArrayList<PropertyInstances>();
        ArrayList<ClassInstances> al;
        ArrayList<PropertyInstances> la;
        ListIterator li,il;
        clasPanel.setLayout(new BoxLayout(clasPanel, BoxLayout.Y_AXIS));
        propPanel.setLayout(new BoxLayout(propPanel, BoxLayout.Y_AXIS));   
        try{
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(textName)));
            al = (ArrayList<ClassInstances>) decoder.readObject();      
            li = al.listIterator();
            while(li.hasNext()){   
                ClassInstances cI = (ClassInstances) li.next();
                CreateInstancesJPanel instClas = new CreateInstancesJPanel();
                contC++;
                instClas.setInstance(cI.getClassInstance());
                commentPane = instClas.getComment();
                commentPane.setComent(cI.getComment());
                instClas.setComment(commentPane);
                clasPanel.add(instClas);
                clasFinal.add(cI);
            }
            la = (ArrayList<PropertyInstances>) decoder.readObject();
            il = la.listIterator();
            while(il.hasNext()){   
                PropertyInstances pI = (PropertyInstances) il.next();
                CreateInstancesJPanel instProp = new CreateInstancesJPanel();
                contP++;
                instProp.setInstance(pI.getPropertyInstance());
                commentPane = instProp.getComment();
                commentPane.setComent(pI.getComment());
                instProp.setComment(commentPane);
                propPanel.add(instProp);
                propFinal.add(pI);
            }
            decoder.close();    
        }catch(FileNotFoundException e){
        } 
        if(contC<8){
            for (int j=0; j<=(8-contC); j++) {
                clasPanel.add(new CreateInstancesJPanel());
            }
        }
        if(contP<8){
            for (int k=0; k<=(8-contP); k++) {
                propPanel.add(new CreateInstancesJPanel());
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentPanel = new javax.swing.JPanel();
        classScrollPane = new javax.swing.JScrollPane();
        clasPanel = new javax.swing.JPanel();
        propScrollPane = new javax.swing.JScrollPane();
        propPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        guardarInstButton = new javax.swing.JButton();
        cancelarInstButton = new javax.swing.JButton();
        limpiarInstButton = new javax.swing.JButton();
        newClasButton = new javax.swing.JButton();
        newPropButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        classScrollPane.setMaximumSize(new java.awt.Dimension(455, 422));
        classScrollPane.setMinimumSize(new java.awt.Dimension(455, 422));
        classScrollPane.setPreferredSize(new java.awt.Dimension(455, 422));

        clasPanel.setMaximumSize(new java.awt.Dimension(455, 422));
        clasPanel.setMinimumSize(new java.awt.Dimension(455, 422));

        javax.swing.GroupLayout clasPanelLayout = new javax.swing.GroupLayout(clasPanel);
        clasPanel.setLayout(clasPanelLayout);
        clasPanelLayout.setHorizontalGroup(
            clasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );
        clasPanelLayout.setVerticalGroup(
            clasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 579, Short.MAX_VALUE)
        );

        classScrollPane.setViewportView(clasPanel);

        propScrollPane.setMaximumSize(new java.awt.Dimension(455, 422));
        propScrollPane.setMinimumSize(new java.awt.Dimension(455, 422));
        propScrollPane.setPreferredSize(new java.awt.Dimension(455, 422));

        propPanel.setMaximumSize(new java.awt.Dimension(455, 422));
        propPanel.setMinimumSize(new java.awt.Dimension(455, 422));

        javax.swing.GroupLayout propPanelLayout = new javax.swing.GroupLayout(propPanel);
        propPanel.setLayout(propPanelLayout);
        propPanelLayout.setHorizontalGroup(
            propPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );
        propPanelLayout.setVerticalGroup(
            propPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 579, Short.MAX_VALUE)
        );

        propScrollPane.setViewportView(propPanel);

        jLabel1.setText("Añada las instancias que quiera a su ontología:");

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 12));
        jLabel2.setText("Instancias de Clase:");

        jLabel3.setFont(new java.awt.Font("Arial Black", 0, 12));
        jLabel3.setText("Instancias de Propiedad:");

        guardarInstButton.setText("Asociar");
        guardarInstButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarInstButtonActionPerformed(evt);
            }
        });

        cancelarInstButton.setText("Cancelar");
        cancelarInstButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarInstButtonActionPerformed(evt);
            }
        });

        limpiarInstButton.setText("Limpiar");
        limpiarInstButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarInstButtonActionPerformed(evt);
            }
        });

        newClasButton.setText("Nueva clase");
        newClasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newClasButtonActionPerformed(evt);
            }
        });

        newPropButton.setText("Nueva propiedad");
        newPropButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPropButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(limpiarInstButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(newClasButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(newPropButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelarInstButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(guardarInstButton))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(classScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contentPanelLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(263, 263, 263))
                            .addComponent(propScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        contentPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {classScrollPane, propScrollPane});

        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(propScrollPane, 0, 422, Short.MAX_VALUE)
                    .addComponent(classScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(guardarInstButton)
                        .addComponent(cancelarInstButton))
                    .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(limpiarInstButton)
                        .addComponent(newClasButton)
                        .addComponent(newPropButton)))
                .addContainerGap())
        );

        contentPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {classScrollPane, propScrollPane});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void guardarInstButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarInstButtonActionPerformed
// TODO add your handling code here:
    
    clasInst = new ArrayList<ClassInstances>();
    propInst = new ArrayList<PropertyInstances>();
    int aux=0;
    int totalClas = clasPanel.getComponentCount();
    
    for(int i=0; i<totalClas; i++){
        CreateInstancesJPanel panelInst = (CreateInstancesJPanel) clasPanel.getComponent(i);
        String query = panelInst.getQuery();
        AddComentJDialog comentPane = panelInst.getComment();
        String coment = comentPane.getComent();
        if(!query.equals("") && !coment.equals("")){
            ClassInstances cI = new ClassInstances(query,coment);
            clasInst.add(cI);
        }else if(!query.equals("") && coment.equals("")){
            ClassInstances cI = new ClassInstances(query);
            clasInst.add(cI);
        }else if(query.equals("") && !coment.equals("")){
            aux=1;
        }
    }
    int totalProp = propPanel.getComponentCount();
    for(int i=0; i< totalProp; i++){
        CreateInstancesJPanel panelInst = (CreateInstancesJPanel) propPanel.getComponent(i);
        String query = panelInst.getQuery();
        AddComentJDialog comentPane = panelInst.getComment();
        String coment = comentPane.getComent();
        if(!query.equals("") && !coment.equals("")){
            PropertyInstances pI = new PropertyInstances(query,coment);
            propInst.add(pI);
        }else if(!query.equals("") && coment.equals("")){
            PropertyInstances pI = new PropertyInstances(query);
            propInst.add(pI);
        }else if(query.equals("") && !coment.equals("")){
            aux=1;
        }
    }
    
    if(aux==1){
        JOptionPane.showMessageDialog(frame,"Si no añade ninguna instancia a sus comentarios," +
                "éstos se perderán","Warning Message",JOptionPane.WARNING_MESSAGE);
    }else{
        if((AddInstancesJPanel.isStateAsociar()==true)
                || (AddInstancesJPanel.isStateAsociar()==true && AddSPARQLJPanel.isSeleccionado()==true)){
            Component comp = null;
            int n = JOptionPane.showConfirmDialog(comp, "¿Quiere guardar este conjunto de" +
            " instancias para futuras pruebas?", "Guardar Instancias",
            JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                crearArchivoDeInstancias();
                this.setInstances(clasInst, propInst);
                this.setVisible(false);
            }else{
                this.setInstances(clasInst, propInst);
                this.setVisible(false);
            }
      }else{  
          if(this.compararListaClase(clasInst, clasFinal) && 
                  this.compararListaPropiedad(propInst, propFinal)){
                this.setInstances(clasInst, propInst);
                this.setVisible(false);
            }else{
                Object[] options = {"Sobreescribir","Crear nuevo","No guardar"};
                    int n = JOptionPane.showOptionDialog(frame,"El conjunto de " +
                    "instancias ha cambiado. ¿Qué desea hacer?","Question",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,null,options,options[2]);
                if (n == JOptionPane.YES_OPTION) {
                        this.setInstances(clasInst, propInst);
                        crearArchivoDeInstancias(getNombreFichero());
                        this.setVisible(false);
                }else if (n == JOptionPane.NO_OPTION) {
                     crearArchivoDeInstancias(); 
                     this.setInstances(clasInst, propInst);
                     this.setVisible(false);
                }else if (n == JOptionPane.CANCEL_OPTION) {
                        this.setInstances(clasInst, propInst);
                        this.setVisible(false);
                }             
            }
      }
    }
}//GEN-LAST:event_guardarInstButtonActionPerformed

public void crearArchivoDeInstancias(){
    
    String nombreArch=null;
    String nameInstances=null;
    
    nombreArch = JOptionPane.showInputDialog(null,"Introduzca el nombre para el " +
                "archivo con este conjunto de instancias","Nombre del archivo",
                JOptionPane.QUESTION_MESSAGE);
            
    if(nombreArch.endsWith(".xml")){
        nameInstances=nombreArch;
    }else{
        nameInstances=nombreArch.concat(".xml");
    }
            
    try{
        XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new 
                            FileOutputStream("./Instances/"+nameInstances)));
        e.writeObject(clasInst);
        e.writeObject(propInst);
        e.close();
    }catch (FileNotFoundException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
     }
}

public void crearArchivoDeInstancias(String nombreFichero){
        
    try{
        XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new 
                            FileOutputStream("./Instances/"+nombreFichero)));
        e.writeObject(clasInst);
        e.writeObject(propInst);
        e.close();
    }catch (FileNotFoundException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
     }
}

public void crearArchivoDeTests(String nombreFichero){
    
    try{
        XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new 
                            FileOutputStream(nombreFichero)));
        e.writeObject(MainJPanel.getCollectionTest());
        e.close();
    }catch (FileNotFoundException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
     }
}

private boolean compararListaClase(ArrayList<ClassInstances> arrayList1, 
        ArrayList<ClassInstances> arrayList2) {
      
    if(arrayList1.size() == arrayList2.size()){
        for(int i=0;i<arrayList1.size();i++){
            String clasInstance1 = arrayList1.get(i).getClassInstance();
            String coment1 = arrayList1.get(i).getComment();
            String clasInstance2 = arrayList2.get(i).getClassInstance();
            String coment2 = arrayList2.get(i).getComment();
            if(!clasInstance1.equals(clasInstance2)){
                return false;
            }
            if(!coment1.equals(coment2)){
                return false;
            }
        }
    }else{
        return false;
    }
    return true;
}  

private boolean compararListaPropiedad(ArrayList<PropertyInstances> arrayList1, 
        ArrayList<PropertyInstances> arrayList2) {
         
    if(arrayList1.size() == arrayList2.size()){
        for(int i=0;i<arrayList1.size();i++){
            String clasInstance1 = arrayList1.get(i).getPropertyInstance();
            String coment1 = arrayList1.get(i).getComment();
            String clasInstance2 = arrayList2.get(i).getPropertyInstance();
            String coment2 = arrayList2.get(i).getComment();
            if(!clasInstance1.equals(clasInstance2)){
                return false;
            }
            if(!coment1.equals(coment2)){
                return false;
            }
        }
    }else{
        return false;
    }
    return true;
} 

private void cancelarInstButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarInstButtonActionPerformed
// TODO add your handling code here:
    this.setVisible(false);
}//GEN-LAST:event_cancelarInstButtonActionPerformed

private void limpiarInstButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarInstButtonActionPerformed
// TODO add your handling code here:
    int totalClas = clasPanel.getComponentCount();
    for(int i=0; i<totalClas; i++){
        CreateInstancesJPanel panelInst = (CreateInstancesJPanel) clasPanel.getComponent(i);
        panelInst.setInstance("");
        AddComentJDialog comentPane = panelInst.getComment();
        comentPane.setComent("");
        ClassInstances cI = new ClassInstances();
        clasInst.add(cI);
    }
    int totalProp = clasPanel.getComponentCount();
    for(int i=0; i<totalProp; i++){
        CreateInstancesJPanel panelInst = (CreateInstancesJPanel) propPanel.getComponent(i);
        panelInst.setInstance("");
        AddComentJDialog comentPane = panelInst.getComment();
        comentPane.setComent("");
        PropertyInstances pI = new PropertyInstances();
        propInst.add(pI);
    }
}//GEN-LAST:event_limpiarInstButtonActionPerformed

private void newClasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newClasButtonActionPerformed
// TODO add your handling code here:
    clasPanel.add(new CreateInstancesJPanel());
    this.validate();
}//GEN-LAST:event_newClasButtonActionPerformed

private void newPropButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPropButtonActionPerformed
// TODO add your handling code here:
    propPanel.add(new CreateInstancesJPanel());
    this.validate();
}//GEN-LAST:event_newPropButtonActionPerformed

private void setInstances(ArrayList<ClassInstances> clasinst,ArrayList<PropertyInstances> propinst)
{
    if(AddSPARQLJPanel.isSeleccionado()==false) {     
        int tab = GroupTestsJPanel.getSelectedTabed();
        ContentMainJFrame.getConjuntoClassInstances().set(tab, clasinst);
        ContentMainJFrame.getConjuntoPropInstances().set(tab, propinst);
    }else{
        AddSPARQLJPanel.getScenarioTestQuery().setClassInstances(clasinst);
        AddSPARQLJPanel.getScenarioTestQuery().setPropertyInstances(propinst);
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelarInstButton;
    private javax.swing.JPanel clasPanel;
    private javax.swing.JScrollPane classScrollPane;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JButton guardarInstButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton limpiarInstButton;
    private javax.swing.JButton newClasButton;
    private javax.swing.JButton newPropButton;
    private javax.swing.JPanel propPanel;
    private javax.swing.JScrollPane propScrollPane;
    // End of variables declaration//GEN-END:variables

    public int getIndexVect() {
        return indexVect;
    }

    public void setIndexVect(int indexVect) {
        this.indexVect = indexVect;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public String getNombreFichero() {
        return nombreFichero;
    }

    public void setNombreFichero(String nombreFichero) {
        this.nombreFichero = nombreFichero;
    }

}
