/**
 * Breakout class initiates and controls the main game window.
 * It initializes the game panel and manages the primary JFrame setup.
 * 
 * Jeremy Stephens 
 * Student ID: 5071232
 * Course: BIT 504 
 * Assignment: 3
 */

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.JFrame;

public class Breakout extends JFrame {
    
    static final long serialVersionUID = 1L;
    
    private BreakoutPanel panel;
    
    public Breakout() {
        // TODO: Set the size of the screen (use Settings.WINDOW_WIDTH/HEIGHT)
        setSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
        
        // Adjust for the frame's insets
        Insets insets = getInsets();
        setSize(Settings.WINDOW_WIDTH + insets.left + insets.right, 
                Settings.WINDOW_HEIGHT + insets.top + insets.bottom);
        
        // TODO: Set the title
        setTitle(Settings.WINDOW_NAME);
        // TODO: Set the background color to white
        setBackground(Color.WHITE);
        // TODO: Set resizable to false
        setResizable(false);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new BreakoutPanel(this);
        add(panel);

        // Call pack() to adjust the JFrame's size to fit its content.
        pack();
        
        // TODO: Set visible to true
        setVisible(true);

        // Get the size (width and height) of the content pane, which is the actual drawable area inside the JFrame.
        Dimension contentPaneSize = getContentPane().getSize();

        // Get the size (width and height) of the entire JFrame, including its borders and title bar.
        Dimension frameSize = getSize();

        // print out insets to diagnose sizing discrepancies of the JFrame.
        System.out.println("Insets: " + insets);
        
        // Printing out the content pane size to ensure it matches game settings.
        System.out.println("ContentPane Size: " + contentPaneSize);
        
        // Printing out the JFrame size for comparison with content pane size.
        System.out.println("JFrame Size: " + frameSize);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
             public void run() {
                 new Breakout();    
             }
        });
    }
}
