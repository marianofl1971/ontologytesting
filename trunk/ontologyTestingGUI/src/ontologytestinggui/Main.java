/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontologytestinggui;

import javax.swing.JFrame;

/**
 *
 * @author sara_garcia
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ontologyTestingJDialog dialog = new ontologyTestingJDialog
                        (new JFrame(),true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setTitle("Evaluador de Ontologías");
                dialog.setVisible(true);
            }
        });
    }

}
