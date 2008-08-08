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
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author sara.garcia
 */
public class ValidarConsultas {
    
    private ArrayList listInst = GroupTestsJPanel.getInst();
    private ArrayList listRet = GroupTestsJPanel.getRet();
    private ArrayList listReal = GroupTestsJPanel.getReal();
    private ArrayList listSat = GroupTestsJPanel.getSat();
    private ArrayList listClas = GroupTestsJPanel.getClas();
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
        
        /*JOptionPane.showMessageDialog(frame,"La query " +
        "introducida no es correcta. Los formatos posibles son:\n" +
        "clase,individuo\nclase.individuo\n" +
        "clase individuo\nclase(individuo)","Warning Message",JOptionPane.WARNING_MESSAGE);*/
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
        /*JOptionPane.showMessageDialog(frame,"La query " +
        "introducida no es correcta. Los formatos posibles son:\n" +
        "clase,individuo\nclase.individuo\n" +
        "clase individuo\nclase(individuo)","Warning Message",JOptionPane.WARNING_MESSAGE);*/
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
        /*JOptionPane.showMessageDialog(frame,"La query " +
        "introducida no es correcta. Los formatos posibles son:\n" +
        "clase,individuo\nclase.individuo\n" +
        "clase individuo\nclase(individuo)","Warning Message",JOptionPane.WARNING_MESSAGE);*/
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
        /*JOptionPane.showMessageDialog(frame,"La query " +
        "introducida no es correcta. Los formatos posibles son:\n" +
        "clase,individuo\nclase.individuo\n" +
        "clase individuo\nclase(individuo)","Warning Message",JOptionPane.WARNING_MESSAGE);*/
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
        /*JOptionPane.showMessageDialog(frame,"La query " +
        "introducida no es correcta. Los formatos posibles son:\n" +
        "clase,individuo\nclase.individuo\n" +
        "clase individuo\nclase(individuo)","Warning Message",JOptionPane.WARNING_MESSAGE);*/
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
        /*JOptionPane.showMessageDialog(frame,"La query " +
        "introducida no es correcta. Los formatos posibles son:\n" +
        "clase,individuo\nclase.individuo\n" +
        "clase individuo\nclase(individuo)","Warning Message",JOptionPane.WARNING_MESSAGE);*/
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
        /*JOptionPane.showMessageDialog(frame,"La query " +
        "introducida no es correcta. Los formatos posibles son:\n" +
        "clase,individuo\nclase.individuo\n" +
        "clase individuo\nclase(individuo)","Warning Message",JOptionPane.WARNING_MESSAGE);*/
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
        /*JOptionPane.showMessageDialog(frame,"La query " +
        "introducida no es correcta. Los formatos posibles son:\n" +
        "clase,individuo\nclase.individuo\n" +
        "clase individuo\nclase(individuo)","Warning Message",JOptionPane.WARNING_MESSAGE);*/
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
        /*JOptionPane.showMessageDialog(frame,"La query " +
        "introducida no es correcta. Los formatos posibles son:\n" +
        "clase,individuo\nclase.individuo\n" +
        "clase individuo\nclase(individuo)","Warning Message",JOptionPane.WARNING_MESSAGE);*/
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
        /*JOptionPane.showMessageDialog(frame,"La query " +
        "introducida no es correcta. Los formatos posibles son:\n" +
        "clase,individuo\nclase.individuo\n" +
        "clase individuo\nclase(individuo)","Warning Message",JOptionPane.WARNING_MESSAGE);*/
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
