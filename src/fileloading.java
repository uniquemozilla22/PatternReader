
import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.TreeMap;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JList;

/**
 * This class extends the JPanel. 
 * 
 * This class contains the methods for choosing , loading scanning and reading the file.
 * 
 * 
 * @author Yogesh Bhattarai
 *
 */
public class fileloading extends JPanel{

	/**
	 * Initialige the static varaiables that are being used in other classes of the project.
	 */
    	
	static ArrayList<Byte> fileBytesArray;
	static JButton loadFileButton;
	static JButton loadFolderButton;

	static JLabel loadFileSelected;
	// ... Instance variables
	static String[] file2names=null;
	String filename ="FIle Name: ";
	String finalresult;
	
	/**
	 * This is the constructor for the fileloading class
	 */

	fileloading(){
            //adding some designs
            setPreferredSize(new Dimension(1000, 100));// setting the dimension of the applications.
			setBackground(Color.white);// setting the background color
			
			// Implementing the Action Listener for the button load
			ActionListener load = new loadListener();


            //Setting the Label for fileLoading and adding it to the Panel.
            JLabel loadFileLabel= new JLabel("Please Select a File or a Repository.");
            loadFileLabel.setToolTipText("You have to select the file");
            add(loadFileLabel);

            //Setting a Button that chooses the file from ropsitory and adding it to the Panel.
            loadFileButton= new JButton ("Select File/ Directory");
			loadFileButton.setToolTipText("Click to open file Chooser.");
			loadFileButton.addActionListener(load);
			add(loadFileButton);
			

            //Setting a label that shows the selected file and adding it to the Panel.
            loadFileSelected= new JLabel("---no file Selected---");
            loadFileSelected.setToolTipText("Please Select a file or path.");
            add(loadFileSelected);


	}
	
	/**
	 * This class is  a ActionListener implementing class that when called gives the user to select an option to choose the file in their directory and sends it for implementing.
	 *
	 * 
	 * @author Yogesh Bhattarai
	 *
	 */
	
	public class loadListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			//adding JFileChooser so that the user can select the file from his directory.
			JFileChooser fileChooser = new JFileChooser();
				fileChooser.setAcceptAllFileFilterUsed(true);//adding the accept all filter.
				fileChooser.setCurrentDirectory(new java.io.File("."));//setting the current directory the Chooser will open.
				fileChooser.setDialogTitle("Select File/Folder"); //setting the dialoge of the chooser
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // Setting the selection mode for the Chooser,

				// adding the try catch for any exception occured 
				try {
					if (fileChooser.showOpenDialog(Driver.frame) == JFileChooser.APPROVE_OPTION) {
						//setting the JLabel loadFileSelected the path of the file
						loadFileSelected.setText(fileChooser.getSelectedFile().toString());
					}
				} catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null,
							"There is an error in the selection" + "\r\nERROR: " + ex.getMessage(), "Error!",
							JOptionPane.PLAIN_MESSAGE);
				}

		}
		
	}


}