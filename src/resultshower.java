import java.io.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.Stack;
import java.util.TreeMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * This class is extended version of JPanel whick will be further implemented on the frame.
 * 
 * This JPanel shows the results in a text area. 
 * 
 * @author Yogesh Bhattarai
 *
 */

public class resultshower extends JPanel{

	/**
	 * Declaring the static Variable 
	 */
	static JTextArea patternFound;
	static String scanResult = "";

	/**
	 * This is a constructor that will add the designs ,buttons and other actions to the Panel.
	 */
	resultshower() {
		// adding some designs
		setPreferredSize(new Dimension(600, 400));// setting the dimension of the applications.
		setBackground(Color.GRAY);// setting the background color

		// Implementing the Action Listener
		ActionListener search = new searchListener();

		//initializzing a button to run the scanning of the folder.
		JButton scanningButton = new JButton("Scan File / Folder");
		scanningButton.setToolTipText("Click me to Scan you file according to the pattern.");
		scanningButton.addActionListener(search);//adding the functionality to the button

		// this Text Area prints the result of the parsed file
		patternFound = new JTextArea("No Pattern Found",22,40);
		patternFound.setToolTipText("Your Pattern result will be shown here.");
				
		JScrollPane scrollingTextArea= new JScrollPane(patternFound);//for making the scroll on the text area.
		scrollingTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//implementing the vertical scroll
		
		//adding the variables to the panel.
		add(scrollingTextArea);
		add(scanningButton);
		
	}
	
	/**
	 * This class is a ActionListener class which adds the functionality to the search button.
	 * 
	 * This class shows the result of the given file by the user.
	 * 
	 * @author Yogesh Bhattarai
	 *
	 */

	public class searchListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			scanResult = "";//setting the scan result to nothing.
			try {
				String filelocation = fileloading.loadFileSelected.getText();//getting the path name from the loading screen
				patternFound.setText(scanFileFolder(filelocation));
			} catch (Exception ee) {
				ee.printStackTrace();
			}
			
		}

	}

	/**
	 * This Method accepts the pathname in string and scans the file / folder for parsing.
	 * 
	 * This method throws the IO Exception if not file is found on the process of implementing this method.
	 * @param path The string having the directory path.
	 * @return it returns the parsed result.
	 * @throws IOException if any input exception is shown
	 */
	public static String scanFileFolder(String path) throws IOException {
		
		//passing the path to a File 
		File folderorfile = new File(path);
		
		//Checking if it is a file
		if (folderorfile.isFile()) {
			FileScanner fs = new FileScanner(folderorfile);//scanning the file
			scanResult = fs.formattedResult;//implementing the result in the string 
		} else if (folderorfile.isDirectory()) {
			scanResult += "Directory: " + folderorfile.getPath() + "\r\n\r\n";//getting the directory path
			File[] files = folderorfile.listFiles(); //listing the files into a file array
			
			//using the loop for each file to scan
			for (File file : files) {
				if(file.isFile()) {
					//scanning the file
					FileScanner fs = new FileScanner(file);
					scanResult += fs.formattedResult;//implementing the result in the string
				}
			}
		}
		//returning the scanned result.
		return scanResult;
	}

}