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


public class resultshower extends JPanel{

	static JTextArea patternFound;
	static String scanResult = "";

	resultshower() {
		// adding some designs
		setPreferredSize(new Dimension(600, 400));// setting the dimension of the applications.
		setBackground(Color.GRAY);// setting the background color

		// Implementing the Action Listener
		ActionListener search = new searchListener();

		JButton scanningButton = new JButton("Scan File / Folder");
		scanningButton.setToolTipText("Click me to Scan you file according to the pattern.");
		add(scanningButton);
		scanningButton.addActionListener(search);

		patternFound = new JTextArea("No Pattern Found");
		patternFound.setBounds(70, 50, 5, 6);
		patternFound.setToolTipText("Your Pattern result will be shown here.");
		add(patternFound);

	}

	public class searchListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			scanResult = "";
			try {
				String pathName = fileloading.loadFileSelected.getText();
				patternFound.setText(scanFileFolder(pathName));
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}

	}

	public static String scanFileFolder(String pathName) throws IOException {
		File fileFolder = new File(pathName);
		// boolean isDirectory = Files.isDirectory(file); // Check if it's a directory
		if (fileFolder.isFile()) {
			FileScanner fs = new FileScanner(fileFolder);
			scanResult = fs.fileResult;
		} else if (fileFolder.isDirectory()) {
			scanResult += "Directory: " + fileFolder.getName() + "\r\n\r\n";
			File[] files = fileFolder.listFiles();
			for (File file : files) {
				if(file.isFile()) {
					FileScanner fs = new FileScanner(file);
					scanResult += fs.fileResult;
				}
			}
		}
		return scanResult;
	}

}