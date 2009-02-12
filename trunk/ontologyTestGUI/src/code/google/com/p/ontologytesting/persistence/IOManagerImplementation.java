/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.persistence;

import code.google.com.p.ontologytesting.gui.auxiliarclasess.FileChooserSelector;
import code.google.com.p.ontologytesting.model.CollectionTest;
import code.google.com.p.ontologytesting.model.Instancias;
import code.google.com.p.ontologytesting.model.ScenarioTest;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author sara.garcia
 */
public class IOManagerImplementation{

    private XMLEncoder e;
    private XMLDecoder decoder;
    private boolean esNuevo=false;
    private boolean como=false;
    private String carpetaProy,nombreProy,fichero;
    
    public IOManagerImplementation(){}
    
    public IOManagerImplementation(boolean as,String carpetaProy, String nombreProy,String fichero,boolean esNuevo){
        this.como=as;
        this.carpetaProy=carpetaProy;
        this.nombreProy=nombreProy;
        this.fichero=fichero;
        this.esNuevo=esNuevo;
    }
    
    public boolean loadProject(String ubicOnto,String namespaceOnto){                                             
        if(!namespaceOnto.endsWith("#")){
            namespaceOnto = namespaceOnto.concat("#");
        }
        CollectionTest.getInstance().setNamespace(namespaceOnto);
        CollectionTest.getInstance().setOntology(ubicOnto);
        return true;
    }

    public CollectionTest loadProject() throws FileNotFoundException,ClassCastException,NoSuchElementException{
       decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(FileChooserSelector.getPathSelected())));
       return (CollectionTest) decoder.readObject();
    }

    public void prepareProject(CollectionTest collection){
        CollectionTest.getInstance().setInstancias(collection.getInstancias());
        CollectionTest.getInstance().setNamespace(collection.getNamespace());
        CollectionTest.getInstance().setOntology(collection.getOntology());
        CollectionTest.getInstance().setScenariotest(collection.getScenariotest());
    }

    public boolean saveProject(boolean as,String carpetaProy, String nombreProy,String fichero) throws FileNotFoundException{
        if(as==true){
            if(!fichero.endsWith(".xml")){
                e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fichero+".xml")));
            }else{
                e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fichero)));
            }
            e.writeObject(CollectionTest.getInstance());
            e.close();
            return true;
        }else{
            if(this.esNuevo==true){
                e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(carpetaProy+"/"+nombreProy+".xml")));
            }else{
                e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(carpetaProy)));
            }
            e.writeObject(CollectionTest.getInstance());
            e.close();
            return true;
        }
    }

    public void saveInstanciasInMemory(Instancias instancias){
        if(instanciasYaGuardadas(instancias)==false){
            CollectionTest.getInstance().getInstancias().add(instancias);
        }
    }

    public boolean instanciasYaGuardadas(Instancias inst){
        List<Instancias> instancias = CollectionTest.getInstance().getInstancias();
        for(int i=0; i<instancias.size(); i++){
            if(inst.getNombre().equals(instancias.get(i).getNombre())){
                if(!inst.getDescripcion().equals(instancias.get(i).getDescripcion()) || 
                !inst.getClassInstances().equals(instancias.get(i).getClassInstances()) || 
                !inst.getPropertyInstances().equals(instancias.get(i).getPropertyInstances())){
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }
    
    public boolean instanciasExisten(Instancias inst){
        List<Instancias> instancias = CollectionTest.getInstance().getInstancias();
        for(int i=0; i<instancias.size(); i++){
            if(inst.getNombre().equals(instancias.get(i).getNombre())){
                return true;
            }
        }
        return false;
    }

    public boolean replaceInstanciasLocally(Instancias inst){
        List<Instancias> instancias = CollectionTest.getInstance().getInstancias();
        for(int i=0; i<instancias.size(); i++){
            if(inst.getNombre().equals(instancias.get(i).getNombre())){
                instancias.remove(i);
                instancias.add(i,inst);
                return true;
            }
        }
        return false;
    }

    public void saveTestInMemory(ScenarioTest scenario){
        if(testYaGuardado(scenario)==false){
            CollectionTest.getInstance().getScenariotest().add(scenario);
        }
    }

    public boolean testYaGuardado(ScenarioTest scen){
        List<ScenarioTest> scenario = CollectionTest.getInstance().getScenariotest();
        for(int i=0; i<scenario.size(); i++){
            if(scen.getNombre().equals(scenario.get(i).getNombre())){
                if(!scen.getDescripcion().equals(scenario.get(i).getDescripcion()) ||
                   //!scen.getTipoTest().equals(scenario.get(i).getTipoTest()) ||
                   !scen.getInstancias().equals(scenario.get(i).getInstancias()) ||
                   !scen.getQueryTest().equals(scenario.get(i).getQueryTest()) ||
                   !scen.getSparqlQuerys().equals(scenario.get(i).getSparqlQuerys())){
                        return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }
    
    public boolean testExiste(ScenarioTest scen){
        List<ScenarioTest> scenario = CollectionTest.getInstance().getScenariotest();
        for(int i=0; i<scenario.size(); i++){
            if(scen.getNombre().equals(scenario.get(i).getNombre())){
                return true;
            }
        }
        return false;
    }
    
    public boolean nombreTestExiste(String nombre){
        List<ScenarioTest> scenario = CollectionTest.getInstance().getScenariotest();
        for(int i=0; i<scenario.size(); i++){
            if(nombre.equals(scenario.get(i).getNombre())){
                return true;
            }
        }
        return false;
    }
    
    public boolean nombreInstanciasExiste(String nombre){
        List<Instancias> instancias = CollectionTest.getInstance().getInstancias();
        for(int i=0; i<instancias.size(); i++){
            if(nombre.equals(instancias.get(i).getNombre())){
                return true;
            }
        }
        return false;
    }

    public boolean replaceScenarioLocally(ScenarioTest scen){
        List<ScenarioTest> scenario = CollectionTest.getInstance().getScenariotest();
        for(int i=0; i<scenario.size(); i++){
            if(scen.getNombre().equals(scenario.get(i).getNombre())){
                scenario.remove(i);
                scenario.add(i,scen);
                return true;
            }
        }
        return false;
    }

    public boolean getComo() {
        return como;
    }

    public void setComo(boolean como) {
        this.como = como;
    }

    public String getCarpetaProy() {
        return carpetaProy;
    }

    public void setCarpetaProy(String carpetaProy) {
        this.carpetaProy = carpetaProy;
    }

    public String getNombreProy() {
        return nombreProy;
    }

    public void setNombreProy(String nombreProy) {
        this.nombreProy = nombreProy;
    }

    public String getFichero() {
        return fichero;
    }

    public void setFichero(String fichero) {
        this.fichero = fichero;
    }
}
