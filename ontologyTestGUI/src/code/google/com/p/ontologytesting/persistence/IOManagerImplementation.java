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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sara.garcia
 */
public class IOManagerImplementation implements IOManager{

    private XMLEncoder e;
    private XMLDecoder decoder;
    private static boolean esNuevo=false;
    private boolean como=false;
    private String carpetaProy,nombreProy,fichero;
    
    public IOManagerImplementation(){}
    
    public IOManagerImplementation(boolean as,String carpetaProy, String nombreProy,String fichero){
        this.como=as;
        this.carpetaProy=carpetaProy;
        this.nombreProy=nombreProy;
        this.fichero=fichero;
    }
    
    @Override
    public boolean loadProject(String ubicOnto,String namespaceOnto){                                             
        if(!namespaceOnto.endsWith("#")){
            namespaceOnto = namespaceOnto.concat("#");
        }
        CollectionTest.getInstance().setNamespace(namespaceOnto);
        CollectionTest.getInstance().setOntology(ubicOnto);
        return true;
    }

    @Override
    public CollectionTest loadProject() throws FileNotFoundException,ClassCastException,NoSuchElementException{
       decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(FileChooserSelector.getPathSelected())));
       return (CollectionTest) decoder.readObject();
    }
    
    @Override
    public void prepareProject(CollectionTest collection){
        CollectionTest.getInstance().setInstancias(collection.getInstancias());
        CollectionTest.getInstance().setNamespace(collection.getNamespace());
        CollectionTest.getInstance().setOntology(collection.getOntology());
        CollectionTest.getInstance().setScenariotest(collection.getScenariotest());
    }

    @Override
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
            if(IOManagerImplementation.esNuevo==true){
                e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(carpetaProy+"/"+nombreProy+".xml")));
            }else{
                e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(carpetaProy)));
            }
            e.writeObject(CollectionTest.getInstance());
            e.close();
            return true;
        }
    }

    @Override
    public void saveInstanciasInMemory(Instancias instancias){
        if(instanciasYaGuardadas(instancias)==false){
            CollectionTest.getInstance().getInstancias().add(instancias);
        }
    }

    @Override
    public boolean instanciasYaGuardadas(Instancias inst){
        List<Instancias> instancias = CollectionTest.getInstance().getInstancias();
        for(int i=0; i<instancias.size(); i++){
            if(inst.getNombre().equals(instancias.get(i).getNombre())){
                return true;
            }
        }
        return false;
    }

    @Override
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

    @Override
    public void saveTestInMemory(ScenarioTest scenario){
        if(testYaGuardado(scenario)==false){
            CollectionTest.getInstance().getScenariotest().add(scenario);
        }
    }

    @Override
    public boolean testYaGuardado(ScenarioTest scen){
        List<ScenarioTest> scenario = CollectionTest.getInstance().getScenariotest();
        for(int i=0; i<scenario.size(); i++){
            if(scen.getNombre().equals(scenario.get(i).getNombre())){
                return true;
            }
        }
        return false;
    }

    @Override
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

    public static boolean isEsNuevo() {
        return esNuevo;
    }

    public static void setEsNuevo(boolean aesNuevo) {
        esNuevo= aesNuevo;
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
