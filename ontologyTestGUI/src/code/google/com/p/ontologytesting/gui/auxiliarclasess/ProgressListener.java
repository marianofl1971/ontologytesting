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
        
        public JProgressBar progressBar;
        public ProgressControlJDialog execDialog;
        
        public ProgressListener() {}
        
        public ProgressListener(JProgressBar progressBar, ProgressControlJDialog execDialog) {
            this.progressBar = progressBar;
            this.execDialog=execDialog;
            this.progressBar.setValue(0);
        }
        
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            String strPropertyName = evt.getPropertyName();
            this.progressBar.setStringPainted(true);
            if ("progress".equals(strPropertyName)) {
                int progress = (Integer) evt.getNewValue();
                progressBar.setValue(progress);
                if(progress==100){
                    this.execDialog.setVisible(false);
                }
            }
        }
    }
