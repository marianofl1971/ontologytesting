/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.gui.auxiliarclasess;

import code.google.com.p.ontologytesting.gui.MainApplicationJFrame;
import code.google.com.p.ontologytesting.gui.auxiliarpanels.AbrirProyectoJDialog;
import code.google.com.p.ontologytesting.gui.auxiliarpanels.NewProjectJDialog;
import code.google.com.p.ontologytesting.gui.auxiliarpanels.ProjectNameSituJPanel;
import code.google.com.p.ontologytesting.model.CollectionTest;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.InterfaceReasoner;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.Reasoner;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author sara.garcia
 */
public class LoadOntology extends SwingWorker<Boolean, Void>{
    
    private String ubicFisica,namespace,nombreProy;
    private Reasoner jena = new Reasoner();
    private InterfaceReasoner j = jena.getReasoner();
    private AbrirProyectoJDialog abrirProy = null;
    private NewProjectJDialog newProy = null;
    private OpcionesMenu opMenu = new OpcionesMenu();
    private boolean abrir=false;
    private ProjectNameSituJPanel project = null;
    private AniadirPanelDeAviso panelAviso = new AniadirPanelDeAviso();
    
    private LoadOntology() {}
    
    public LoadOntology(String ontology,AbrirProyectoJDialog abrirProy) {
        this.ubicFisica=ontology;
        this.abrirProy=abrirProy;
        this.abrir=true;
    }
    
    public LoadOntology(String ontology,String namespace,NewProjectJDialog newProy,String nombreProy,ProjectNameSituJPanel project) {
        this.ubicFisica=ontology;
        this.namespace=namespace;
        this.newProy=newProy;
        this.nombreProy=nombreProy;
        this.project=project;
        this.abrir=false;
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        setProgress(0);
        j.addReasoner(this.ubicFisica);
        setProgress(100);
        return true;
    }

    
    @Override
    protected void done() {
        boolean res =false;
        if(this.isCancelled()==false){
            Toolkit.getDefaultToolkit().beep();
            try {
                res = get();
            } catch (Exception ignore) {
                ignore.printStackTrace();
            }
            if(this.abrir==true){
                if(res==true){
                    opMenu.actualizarListaDeInstancias();
                    opMenu.actualizarListaDeTestsSimples(CollectionTest.getInstance().getScenariotest());
                    opMenu.actualizarListaDeTestsSparql(CollectionTest.getInstance().getScenariotest());
                    abrirProy.setProyectoCargado(true);
                    abrirProy.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(MainApplicationJFrame.getInstance(),"Error en la Aplicación","Error Message",JOptionPane.ERROR_MESSAGE);
                }
            }else{
                CollectionTest.getInstance().setNamespace(this.namespace);
                CollectionTest.getInstance().setOntology(this.ubicFisica);
                File directorio = new File(project.getCarpetaProyectoTextField());
                boolean result = directorio.mkdir(); 
                if(result==true){
                    MainApplicationJFrame.getInstance().setCarpetaProyecto(project.getCarpetaProyectoTextField());
                    MainApplicationJFrame.getInstance().setNombreProyecto(nombreProy);
                    newProy.setProyectoCreado(true); 
                    newProy.setVisible(false);
                }else{
                    panelAviso.errorAction("No se puedo crear un directorio para el proyecto", newProy);
                }
            }
        }
    }

    public String getName() {
        return ubicFisica;
    }

    public void setName(String name) {
        this.ubicFisica = name;
    }
}

