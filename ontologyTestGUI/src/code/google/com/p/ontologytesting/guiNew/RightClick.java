/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.p.ontologytesting.guiNew;

import com.hp.hpl.jena.rdf.model.Container;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author sara.garcia
 */
public class RightClick extends JComponent implements MouseListener, MouseMotionListener {

    JPopupMenu popup;
    java.awt.Container contentPane;

    public RightClick(java.awt.Container contentPane) {
        addMouseListener(this);
        addMouseMotionListener(this);
        this.contentPane = contentPane;

        popup = new JPopupMenu();
        popup.add(new JMenuItem("Dogs"));
        popup.add(new JMenuItem("Cats"));
        popup.add(new JMenuItem("Mass Hysteria"));
    }

    // draw some text just so we know the glass pane
    // is installed and visible
    @Override
    public void paint(Graphics g) {
        g.drawString("I'm a glass pane",50,50);
    }


    // catch all mouse events and redispatch them
    @Override
    public void mouseMoved(MouseEvent e) {
        redispatchMouseEvent(e, false);
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        redispatchMouseEvent(e, false);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        p("mouse clicked");
        redispatchMouseEvent(e, false);
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        redispatchMouseEvent(e, false);
    }
    @Override
    public void mouseExited(MouseEvent e) {
        redispatchMouseEvent(e, false);
    }
    @Override
    public void mousePressed(MouseEvent e) {
        redispatchMouseEvent(e, false);
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        redispatchMouseEvent(e, false);
    }


    private void redispatchMouseEvent(MouseEvent e,
        boolean repaint) {

        // if it's a popup
        if(e.isPopupTrigger()) {
            p("it's a popup");
            // show the popup and return
            popup.show(e.getComponent(), e.getX(), e.getY());

        } else {
            // since it's not a popup we need to redispatch it.

            // get the mouse click point relative to the content pane
            Point containerPoint = SwingUtilities.convertPoint(this,
                e.getPoint(),(Component) contentPane);

            // find the component that under this point
            Component component = SwingUtilities.getDeepestComponentAt(
                        (Component) contentPane,
                        containerPoint.x,
                        containerPoint.y);

            // return if nothing was found
            if (component == null) {
                return;
            }

            // convert point relative to the target component
            Point componentPoint = SwingUtilities.convertPoint(
                this,
                e.getPoint(),
                component);

            // redispatch the event
            component.dispatchEvent(new MouseEvent(component,
                e.getID(),
                e.getWhen(),
                e.getModifiers(),
                componentPoint.x,
                componentPoint.y,
                e.getClickCount(),
                e.isPopupTrigger()));
        }
    }



    public static void main(String[] args) {
        // create a frame with some components in it
        JFrame frame = new JFrame("Right Click Test");
        JButton button = new JButton("this is a button");
        JTextField tf = new JTextField("this is a textfield");
        JPanel panel = new JPanel();
        panel.add(button);
        panel.add(tf);
        frame.getContentPane().add(panel);


        // create the right click glass pane.
        RightClick rc = new RightClick(frame.getContentPane());
        // set as glasspane and make it visible
        frame.setGlassPane(rc);
        rc.setVisible(true);

        // pack and show the frame
        frame.pack();
        frame.setSize(400,200);
        frame.show();
    }

    // utiltity function
    public static void p(String str) {
        System.out.println(str);
    }
}