
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

public class fileloading extends JPanel{

    	// ... Instance variables
	static ArrayList<Byte> fileBytesArray;
	static JButton loadFileButton;
	static JButton loadFolderButton;

	static JLabel loadFileSelected;
	
	static String[] file2names=null;
	String filename ="FIle Name: ";
	String finalresult;

	fileloading(){
            //adding some designs
            setPreferredSize(new Dimension(1000, 100));// setting the dimension of the applications.
			setBackground(Color.white);// setting the background color
			
			// Implementing the Action Listener
			ActionListener load = new loadListener();


            //Setting the Label for fileLoading
            JLabel loadFileLabel= new JLabel("Please Select a File or a Repository.");
            loadFileLabel.setToolTipText("You have to select the file");
            add(loadFileLabel);

            //Setting a Button that chooses the file from ropsitory.
            loadFileButton= new JButton ("Select File ");
			loadFileButton.setToolTipText("Click to open file Chooser.");
			loadFileButton.addActionListener(load);
			add(loadFileButton);
			
			loadFolderButton= new JButton ("Select Repository");
            loadFolderButton.setToolTipText("Click to open Folder Chooser.");
            add(loadFolderButton);

            //Setting a label that shows the selected file.
            loadFileSelected= new JLabel("---no file Selected---");
            loadFileSelected.setToolTipText("Please Select a file or path.");
            add(loadFileSelected);


	}
	
	public class loadListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			JFileChooser fileChooser = new JFileChooser();
				fileChooser.setAcceptAllFileFilterUsed(true);
				fileChooser.setCurrentDirectory(new java.io.File("."));
				fileChooser.setDialogTitle("Select File/Folder");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

				try {
					if (fileChooser.showOpenDialog(Driver.frame) == JFileChooser.APPROVE_OPTION) {
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