/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.model;

import code.google.com.p.ontologytesting.gui.GroupTestsJPanel;
import code.google.com.p.ontologytesting.gui.TestInstancesQueryJPanel;
import code.google.com.p.ontologytesting.gui.TestInstancesTFJPanel;
import code.google.com.p.ontologytesting.gui.TestInstancesTextAreaJPanel;
import code.google.com.p.ontologytesting.gui.TestInstancesTextJPanel;
import java.awt.Color;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author sara.garcia
 */
public class ValidarConsultas {
    
    private List listInst = GroupTestsJPanel.getInst();
    private List listRet = GroupTestsJPanel.getRet();
    private List listReal = GroupTestsJPanel.getReal();
    private List listSat = GroupTestsJPanel.getSat();
    private List listClas = GroupTestsJPanel.getClas();
    private JPanel panelAyudaInst = GroupTestsJPanel.getInstAyudaPanel();
    private JPanel panelAyudaRet = GroupTestsJPanel.getRetAyudaPanel();
    private JPanel panelAyudaReal = GroupTestsJPanel.getRealAyudaPanel();
    private JPanel panelAyudaSat = GroupTestsJPanel.getSatAyudaPanel();
    private JPanel panelAyudaClas = GroupTestsJPanel.getClasAyudaPanel();
    private JPanel panelInst = GroupTestsJPanel.getOpcionTextInstPanel();
    private JPanel panelRet = GroupTestsJPanel.getOpcionTextRetPanel();
    private JPanel panelReal = GroupTestsJPanel.getOpcionTextRealPanel();
    private JPanel panelSat = GroupTestsJPanel.getOpcionTextSatPanel();
    private JPanel panelClas = GroupTestsJPanel.getOpcionTextClasPanel();
    
    public boolean comprovarErrorEnAyudaInst(){
        
        int var=0;
        for(int j=0;j<listInst.size();j++){
            if(listInst.get(j).equals(1)){
                TestInstancesTFJPanel test = (TestInstancesTFJPanel) panelAyudaInst.getComponent(j);
                test.getQueryTextField().setForeground(Color.RED);
                var=1;
            }
        }
        if(var==0){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean comprovarErrorEnAyudaRet(){

        int var=0;
        for(int j=0;j<listRet.size();j++){
            if(listRet.get(j).equals(1)){
                TestInstancesTextAreaJPanel test = (TestInstancesTextAreaJPanel) panelAyudaRet.getComponent(j);
                test.getQueryTextField().setForeground(Color.RED);
                var=1;
            }else if(listRet.get(j).equals(2)){
                TestInstancesTextAreaJPanel test = (TestInstancesTextAreaJPanel) panelAyudaRet.getComponent(j);
                test.getResultTextArea().setForeground(Color.RED);
                var=1;
            }else if(listRet.get(j).equals(3)){
                TestInstancesTextAreaJPanel test = (TestInstancesTextAreaJPanel) panelAyudaRet.getComponent(j);
                test.getQueryTextField().setForeground(Color.RED);
                test.getResultTextArea().setForeground(Color.RED); 
                var=1;
            }
        }
        if(var==0){
            return true;
        }else{
            return false;
        }
    }

    public boolean comprovarErrorEnAyudaReal(){

        int var=0;
        for(int j=0;j<listReal.size();j++){   
            if(listReal.get(j).equals(1)){
                TestInstancesQueryJPanel test = (TestInstancesQueryJPanel) panelAyudaReal.getComponent(j);
                test.getQueryTextField().setForeground(Color.RED);
                var=1;
            }else if(listReal.get(j).equals(2)){
                TestInstancesQueryJPanel test = (TestInstancesQueryJPanel) panelAyudaReal.getComponent(j);
                test.getResultTextField().setForeground(Color.RED);
                var=1;
            }else if(listReal.get(j).equals(3)){
                TestInstancesQueryJPanel test = (TestInstancesQueryJPanel) panelAyudaReal.getComponent(j);
                test.getQueryTextField().setForeground(Color.RED);
                test.getResultTextField().setForeground(Color.RED);
                var=1;
            }
        }
        if(var==0){
            return true;
        }else{
            return false;
        }
    }

    public boolean comprovarErrorEnAyudaSat(){

        int var=0;
        for(int j=0;j<listSat.size();j++){  
            if(listSat.get(j).equals(1)){
                TestInstancesTFJPanel test = (TestInstancesTFJPanel) panelAyudaSat.getComponent(j);
                test.getQueryTextField().setForeground(Color.RED);
                var=1;
            }
        }
        if(var==0){
            return true;
        }else{
            return false;
        }
    }

    public boolean comprovarErrorEnAyudaClas(){

        int var=0;
        for(int j=0;j<listClas.size();j++){
            if(listClas.get(j).equals(1)){
                TestInstancesTextAreaJPanel test = (TestInstancesTextAreaJPanel) panelAyudaClas.getComponent(j);
                test.getQueryTextField().setForeground(Color.RED);
                var=1;
            }else if(listClas.get(j).equals(2)){
                TestInstancesTextAreaJPanel test = (TestInstancesTextAreaJPanel) panelAyudaClas.getComponent(j);
                test.getResultTextArea().setForeground(Color.RED);
                var=1;
            }else if(listClas.get(j).equals(3)){
                TestInstancesTextAreaJPanel test = (TestInstancesTextAreaJPanel) panelAyudaClas.getComponent(j);
                test.getQueryTextField().setForeground(Color.RED);
                test.getResultTextArea().setForeground(Color.RED);
                var=1;
            }
        }
        if(var==0){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean comprovarErrorQuerysInst(){

        int var=0;
        TestInstancesTextJPanel test = (TestInstancesTextJPanel) panelInst.getComponent(0); 
        for(int j=0;j<listInst.size();j++){
            if(listInst.get(j).equals(1)){
                test.getConsultaTextArea().setForeground(Color.RED);
                var=1;
            }else if(listInst.get(j).equals(2)){
                test.getResultadoEsperadoTextArea().setForeground(Color.RED);
                var=1;
            }else if(listInst.get(j).equals(3)){
                test.getConsultaTextArea().setForeground(Color.RED);
                test.getResultadoEsperadoTextArea().setForeground(Color.RED);
                var=1;
            }
        }
        if(var==0){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean comprovarErrorQuerysRet(){

        int var=0;
        TestInstancesTextJPanel test = (TestInstancesTextJPanel) panelRet.getComponent(0);
        for(int j=0;j<listRet.size();j++){
            if(listRet.get(j).equals(1)){
                test.getConsultaTextArea().setForeground(Color.RED);
                var=1;
            }else if(listRet.get(j).equals(2)){
                test.getResultadoEsperadoTextArea().setForeground(Color.RED);
                var=1;
            }else if(listRet.get(j).equals(3)){
                test.getConsultaTextArea().setForeground(Color.RED);
                test.getResultadoEsperadoTextArea().setForeground(Color.RED); 
                var=1;
            }
        }
        if(var==0){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean comprovarErrorQuerysReal(){

        int var=0;
        TestInstancesTextJPanel test = (TestInstancesTextJPanel) panelReal.getComponent(0);
        for(int j=0;j<listReal.size();j++){   
            if(listReal.get(j).equals(1)){
                test.getConsultaTextArea().setForeground(Color.RED);
                var=1;
            }else if(listReal.get(j).equals(2)){
                test.getResultadoEsperadoTextArea().setForeground(Color.RED);
                var=1;
            }else if(listReal.get(j).equals(3)){
                test.getConsultaTextArea().setForeground(Color.RED);
                test.getResultadoEsperadoTextArea().setForeground(Color.RED);
                var=1;
            }
        }
        if(var==0){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean comprovarErrorQuerysSat(){

        int var=0;
        TestInstancesTextJPanel test = (TestInstancesTextJPanel) panelSat.getComponent(0);
        for(int j=0;j<listSat.size();j++){  
            if(listSat.get(j).equals(1)){
                test.getConsultaTextArea().setForeground(Color.RED);
                var=1;
            }else if(listSat.get(j).equals(2)){
                test.getResultadoEsperadoTextArea().setForeground(Color.RED);
                var=1;
            }else if(listSat.get(j).equals(3)){
                test.getConsultaTextArea().setForeground(Color.RED);
                test.getResultadoEsperadoTextArea().setForeground(Color.RED);
                var=1;
            }
        }
        if(var==0){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean comprovarErrorQuerysClas(){

        int var=0;
        TestInstancesTextJPanel test = (TestInstancesTextJPanel) panelClas.getComponent(0);
        for(int j=0;j<listClas.size();j++){
            if(listClas.get(j).equals(1)){
                test.getConsultaTextArea().setForeground(Color.RED);
                var=1;
            }else if(listClas.get(j).equals(2)){
                test.getResultadoEsperadoTextArea().setForeground(Color.RED);
                var=1;
            }else if(listClas.get(j).equals(3)){
                test.getConsultaTextArea().setForeground(Color.RED);
                test.getResultadoEsperadoTextArea().setForeground(Color.RED);
                var=1;
            }
        }
        if(var==0){
            return true;
        }else{
            return false;
        }
    }

}
