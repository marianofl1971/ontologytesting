/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.gui.auxiliarclasess;

import code.google.com.p.ontologytesting.gui.auxiliarpanels.ProgressControlJDialog;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JProgressBar;

/**
 *
 * @author sara.garcia
 */
public class ProgressListener implements PropertyChangeListener {
        
        private JProgressBar progressBar;
        private ProgressControlJDialog execDialog;
        private boolean mostrarProgreso;
        
        public ProgressListener() {}
        
        public ProgressListener(JProgressBar progressBar, ProgressControlJDialog execDialog, boolean mostrarProgreso) {
            this.progressBar = progressBar;
            this.execDialog=execDialog;
            this.mostrarProgreso=mostrarProgreso;
            if(this.mostrarProgreso==true){
                this.progressBar.setValue(0);
            }
        }
        
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            String strPropertyName = evt.getPropertyName();
            if ("progress".equals(strPropertyName)) {
                int progress = (Integer) evt.getNewValue();
                if(this.mostrarProgreso==true){
                    this.progressBar.setStringPainted(true);
                    progressBar.setValue(progress);
                }
                if(progress==100){
                    this.execDialog.setVisible(false);
                }
            }
        }
    }
