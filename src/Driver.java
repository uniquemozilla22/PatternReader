import java.awt.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;


public class Driver {
	static JFrame  frame;
	public static void main(String[] args) {
	   
	    	
	        frame = new JFrame("Pattern");// adding a new frame
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // commanding on to exit while clicking on the exit button
	 
	        menu menubar = new menu();// initializing a panel        
	        frame.setJMenuBar(menubar.setupMenu());//setting the menubar
	        
	        
	        // Initializing the panel Class fileloading
            fileloading Panel = new fileloading();
            // initializzing the panel class resultshower
            resultshower result = new resultshower();
            // initializzing the panel class resultshower
            pattern patt = new pattern();

	        //Setting the Panel to the Frame
	        frame.getContentPane().add(Panel, BorderLayout.NORTH); // adding the layout
            frame.getContentPane().add(result, BorderLayout.WEST); // adding the layout
            frame.getContentPane().add(patt, BorderLayout.EAST); // adding the layout
	        frame.pack();
	        frame.setVisible(true);//giving visibility	    
    }

}
