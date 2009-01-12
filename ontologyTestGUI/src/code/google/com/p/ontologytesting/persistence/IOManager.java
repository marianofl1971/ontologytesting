/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.persistence;

import code.google.com.p.ontologytesting.model.CollectionTest;
import code.google.com.p.ontologytesting.model.Instancias;
import code.google.com.p.ontologytesting.model.ScenarioTest;
import code.google.com.p.ontologytesting.model.reasonerinterfaz.InvalidOntologyException;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

/**
 *
 * @author sara.garcia
 */
public interface IOManager {
    
    public boolean loadProject(String ubicOnto,String namespaceOnto) throws InvalidOntologyException;
    
    public CollectionTest loadProject() throws FileNotFoundException,ClassCastException,NoSuchElementException;
    
    public void prepareProject(CollectionTest collection);
    
    public boolean saveProject(boolean as,String carpetaProy, String nombreProy,String fichero) throws FileNotFoundException;
    
    public void saveInstanciasInMemory(Instancias instancias);
    
    public boolean instanciasYaGuardadas(Instancias inst);
    
    public boolean replaceInstanciasLocally(Instancias inst);
    
    public void saveTestInMemory(ScenarioTest scenario);
    
    public boolean testYaGuardado(ScenarioTest scen);
    
    public boolean replaceScenarioLocally(ScenarioTest scen);
    
    public boolean isEsNuevo();

    public void setEsNuevo(boolean aesNuevo);

    public boolean getComo();

    public void setComo(boolean como);

    public String getCarpetaProy();

    public void setCarpetaProy(String carpetaProy);

    public String getNombreProy();

    public void setNombreProy(String nombreProy);

    public String getFichero();

    public void setFichero(String fichero);

}
