/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.validations;

import code.google.com.p.ontologytesting.guiNew.TestInstancesQueryJPanel;
import code.google.com.p.ontologytesting.guiNew.TestInstancesTFJPanel;
import code.google.com.p.ontologytesting.guiNew.TestInstancesTextAreaJPanel;
import code.google.com.p.ontologytesting.guiNew.TestInstancesTextJPanel;
import code.google.com.p.ontologytesting.guiNew.TestSimpleInstSat;
import code.google.com.p.ontologytesting.guiNew.TestSimpleReal;
import code.google.com.p.ontologytesting.guiNew.TestSimpleRetClas;
import code.google.com.p.ontologytesting.controller.Auxiliar;
import java.awt.Color;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author sara.garcia
 */
public class ValidarConsultas {
    
    private List listInst;
    private List listRet;
    private List listReal;
    private JPanel panelAyudaInst;
    private JPanel panelAyudaRet;
    private JPanel panelAyudaReal;
    private JPanel panelInst;
    private JPanel panelRet;
    private JPanel panelReal;
    
    public boolean comprovarErrorEnAyudaInst(){
        listInst = TestSimpleInstSat.getInst();
        panelAyudaInst = Auxiliar.getTestSimpleInstSat().getInstAyudaPanel();
        int var=0;
        for(int j=1;j<listInst.size();j++){
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
        listRet = TestSimpleRetClas.getRet();
        panelAyudaRet = Auxiliar.getTestSimpleRetClas().getRetAyudaPanel();
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
        listReal = TestSimpleReal.getReal();
        panelAyudaReal = Auxiliar.getTestSimpleReal().getRealAyudaPanel();
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

    public boolean comprovarErrorEnAyudaClas(){
        listRet = TestSimpleRetClas.getRet();
        panelAyudaRet = Auxiliar.getTestSimpleRetClas().getRetAyudaPanel();
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
    
    public boolean comprovarErrorQuerysInst(){
        listInst = TestSimpleInstSat.getInst();
        panelInst = Auxiliar.getTestSimpleInstSat().getOpcionTextInstPanel();
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
        listRet = TestSimpleRetClas.getRet();
        panelRet = Auxiliar.getTestSimpleRetClas().getOpcionTextRetPanel();
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
        listReal = TestSimpleReal.getReal();
        panelReal = Auxiliar.getTestSimpleReal().getOpcionTextRealPanel();
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
    
    public boolean comprovarErrorQuerysClas(){
        listRet = TestSimpleRetClas.getRet();
        panelRet = Auxiliar.getTestSimpleRetClas().getOpcionTextRetPanel();
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

}
