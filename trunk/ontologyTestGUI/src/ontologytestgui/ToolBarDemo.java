/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ontologytestgui;

/**
 *
 * @author sara.garcia
 */
import javax.swing.JToolBar;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBarDemo extends JPanel implements ActionListener {
    
    protected JTextArea textArea;
    protected String newline = "\n";
    static final private String PREVIOUS = "previous";
    static final private String UP = "up";
    static final private String NEXT = "next";

    public ToolBarDemo() {
        super(new BorderLayout());

        //Create the toolbar.
        JToolBar toolBar = new JToolBar("Still draggable");
        addButtons(toolBar);

        //Create the text area used for output.  Request
        //enough space for 5 rows and 30 columns.
        textArea = new JTextArea(5, 30);
        //textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        //Lay out the main panel.
        setPreferredSize(new Dimension(450, 130));
        add(toolBar, BorderLayout.PAGE_START);
        add(scrollPane, BorderLayout.CENTER);
    }

    protected void addButtons(JToolBar toolBar) {
        JButton button = null;

        //first button
        button = makeNavigationButton(PREVIOUS,"Back","Previous");
        toolBar.add(button);

        //third button
        button = makeNavigationButton(NEXT,"Forward","Next");
        toolBar.add(button);
    }

    protected JButton makeNavigationButton(String actionCommand,String toolTipText,
                                           String altText) {
       
        //Create and initialize the button.
        JButton button = new JButton();
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.addActionListener(this);
        button.setText(altText);

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        String description = null;

        // Handle each button.
        if (PREVIOUS.equals(cmd)) { //first button clicked
            description = "taken you to the previous <something>.";
        } else if (UP.equals(cmd)) { // second button clicked
            description = "taken you up one level to <something>.";
        } else if (NEXT.equals(cmd)) { // third button clicked
            description = "taken you to the next <something>.";
        }

        displayResult("If this were a real app, it would have "
                        + description);
    }

    protected void displayResult(String actionDescription) {
        textArea.append(actionDescription + newline);
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {

        JFrame frame = new JFrame("ToolBarDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ToolBarDemo());
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Turn off metal's use of bold fonts
	        UIManager.put("swing.boldMetal", Boolean.FALSE);
	        createAndShowGUI();
            }
        });
    }
}