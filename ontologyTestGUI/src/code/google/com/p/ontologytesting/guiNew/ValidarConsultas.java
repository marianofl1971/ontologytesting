/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.guiNew;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author sara.garcia
 */
public class ValidarConsultas {
    
    private List listInst = new ArrayList();
    private List listRet = new ArrayList();
    private List listReal = new ArrayList();
    
    public boolean comprovarErrorEnAyudaInst(JPanel panelAyudaInst){
        int var=0;
        for(int j=1;j<getListInst().size();j++){
            if(getListInst().get(j).equals(1)){
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
    
    public boolean comprovarErrorEnAyudaRet(JPanel panelAyudaRet){
        int var=0;
        for(int j=0;j<getListRet().size();j++){
            if(getListRet().get(j).equals(1)){
                TestInstancesTextAreaJPanel test = (TestInstancesTextAreaJPanel) panelAyudaRet.getComponent(j);
                test.getQueryTextField().setForeground(Color.RED);
                var=1;
            }else if(getListRet().get(j).equals(2)){
                TestInstancesTextAreaJPanel test = (TestInstancesTextAreaJPanel) panelAyudaRet.getComponent(j);
                test.getResultTextArea().setForeground(Color.RED);
                var=1;
            }else if(getListRet().get(j).equals(3)){
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

    public boolean comprovarErrorEnAyudaReal(JPanel panelAyudaReal){
        int var=0;
        for(int j=0;j<getListReal().size();j++){   
            if(getListReal().get(j).equals(1)){
                TestInstancesQueryJPanel test = (TestInstancesQueryJPanel) panelAyudaReal.getComponent(j);
                test.getQueryTextField().setForeground(Color.RED);
                var=1;
            }else if(getListReal().get(j).equals(2)){
                TestInstancesQueryJPanel test = (TestInstancesQueryJPanel) panelAyudaReal.getComponent(j);
                test.getResultTextField().setForeground(Color.RED);
                var=1;
            }else if(getListReal().get(j).equals(3)){
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
    
    public boolean comprovarErrorQuerysInst(JPanel panelInst){
        int var=0;
        TestInstancesTextJPanel test = (TestInstancesTextJPanel) panelInst.getComponent(0); 
        for(int j=0;j<getListInst().size();j++){
            if(getListInst().get(j).equals(1)){
                test.getConsultaTextArea().setForeground(Color.RED);
                var=1;
            }else if(getListInst().get(j).equals(2)){
                test.getResultadoEsperadoTextArea().setForeground(Color.RED);
                var=1;
            }else if(getListInst().get(j).equals(3)){
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
    
    public boolean comprovarErrorQuerysRet(JPanel panelRet){
        int var=0;
        TestInstancesTextJPanel test = (TestInstancesTextJPanel) panelRet.getComponent(0);
        for(int j=0;j<getListRet().size();j++){
            if(getListRet().get(j).equals(1)){
                test.getConsultaTextArea().setForeground(Color.RED);
                var=1;
            }else if(getListRet().get(j).equals(2)){
                test.getResultadoEsperadoTextArea().setForeground(Color.RED);
                var=1;
            }else if(getListRet().get(j).equals(3)){
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
    
    public boolean comprovarErrorQuerysReal(JPanel panelReal){
        int var=0;
        TestInstancesTextJPanel test = (TestInstancesTextJPanel) panelReal.getComponent(0);
        for(int j=0;j<getListReal().size();j++){   
            if(getListReal().get(j).equals(1)){
                test.getConsultaTextArea().setForeground(Color.RED);
                var=1;
            }else if(getListReal().get(j).equals(2)){
                test.getResultadoEsperadoTextArea().setForeground(Color.RED);
                var=1;
            }else if(getListReal().get(j).equals(3)){
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

    public List getListInst() {
        return listInst;
    }

    public void setListInst(List listInst) {
        this.listInst = listInst;
    }

    public List getListRet() {
        return listRet;
    }

    public void setListRet(List listRet) {
        this.listRet = listRet;
    }

    public List getListReal() {
        return listReal;
    }

    public void setListReal(List listReal) {
        this.listReal = listReal;
    }

}
