
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.DatatypeConverter;

/**
 * This class extends the JPanel and is used to read and show the pattern that will be choosenby the user
 * 
 * @author Yogesh Bhattarai
 *
 */
public class pattern extends JPanel {

  // ... Instance variables
	/**
	 * valid Byte patterns from pattern-file to scan in a file
	 */
	public static List<byte[]> bytes = new ArrayList<byte[]>();
  /**
	 * invalid lines from pattern-file
	 */
	public static List<String> FailedToScanLines = new ArrayList<String>(Arrays.asList("123RSADNXJ", "12 er s d sd"));

  JLabel patternShower;
  JTextArea patternTextArea;
  static JButton patternLoaderBtn;
  private JList<String> patternsLists;

  pattern() {
    // adding some designs
    setPreferredSize(new Dimension(400, 400));// setting the dimension of the applications.
    setBackground(Color.GREEN);// setting the background color

    // Initializing the action listener to the pattern class
    ActionListener patternLoad = new LoadPatternBtnListener();

    // adding label to the button
    JLabel patternLoaderLabel = new JLabel("Please select Your Pattern.");
    patternLoaderLabel.setToolTipText("Your can Select your Pattern File.");
    add(patternLoaderLabel);

    // adding JButton to select the pattern file
    patternLoaderBtn = new JButton("Select your Pattern.");
    patternLoaderBtn.setToolTipText("Click me to select the pattern.");
    patternLoaderBtn.addActionListener(patternLoad);
    add(patternLoaderBtn);

    // Setting the Label for fileLoading
    patternShower = new JLabel("Your Pattern will be shown here.");
    patternShower.setToolTipText("Your Pattern will be displayed here.");
    add(patternShower);

    patternTextArea = new JTextArea("---");
    add(patternTextArea);
    
    
    patternsLists = new JList<String>();
	patternsLists.setBounds(10, 57, 478, 98);
	add(patternsLists);
  }

  // ... When user requests to load pattern from file.
  private class LoadPatternBtnListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      
      pattern.bytes.clear();
				FailedToScanLines.clear();
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setAcceptAllFileFilterUsed(true);
				fileChooser.setCurrentDirectory(new java.io.File("."));
				fileChooser.setDialogTitle("Load Pattern");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.setMultiSelectionEnabled(false);

				try {
					if (fileChooser.showOpenDialog(Driver.frame) == JFileChooser.APPROVE_OPTION) {
						String patternFilePath = fileChooser.getSelectedFile().toString();
						readPattern(patternFilePath);
					}
				} catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null,
							"There seems be some issue with selection." + "\r\nERROR: " + ex.getMessage(), "Error!",
							JOptionPane.PLAIN_MESSAGE);
				}

				// Pattern.
				if (FailedToScanLines.size() > 0) {
					String errorMessage = "Not valid lines.:\r\n";
					for (String fLine : FailedToScanLines) {
						errorMessage += fLine + "\r\n";
					}
					JOptionPane.showMessageDialog(null, errorMessage + "Valid lines are imported.", "OOPS!", JOptionPane.PLAIN_MESSAGE);
				}
				
				// Display spaced byte pattern
				String[] bytePatterns = getSpacedPatternStrings(bytes);
				patternsLists.setListData(bytePatterns);
    }

  }

  /**
	 * The main task of this method is Reading the pattern 
	 * 
	 * @param filepathofpatternn The file path where the pattern file is situated
	 * @throws IOException Throws exception where it couldn't find the file
	 */
	public static void readPattern(String filepathofpatternn) throws IOException {
		BufferedReader bfreader = new BufferedReader(new FileReader(filepathofpatternn));
		String line = bfreader.readLine();

		while (line != null) {
			line = line.toUpperCase();
			if (checkingLine(line)) {
				line = line.replace(" ", "");
				bytes.add(DatatypeConverter.parseHexBinary(line));
			} else {
				FailedToScanLines.add(line);
			}
			// read next line
			line = bfreader.readLine();
		}

		bfreader.close();

	}
	
	/**
	 *This method returns a logical patterns in a format to display. 
	 * Returns the stored patterns in a format to display in the screen
	 * @param bytes2 
	 * 
	 * @return the array of string in format of two digits and space
	 */
	public static String[] getSpacedPatternStrings(List<byte[]> bytes2) {
		
		String[] result = new String[bytes2.size()];
		// result = bytes.toArray(result);
		for (int i = 0; i < result.length; i++) {
			result[i] = DatatypeConverter.printHexBinary(bytes.get(i)).replaceAll("..(?!$)", "$0 ");
			System.out.println(result[i]);
		}
		
		return result;
	}
	
	/** 
	 * This method simply checks the line if the line has hex digits by spacing.
	 * 
	 * @param sentence Passed the line to be validated in string 
	 * @return Boolean if the line is validated or not
	 */
	public static boolean checkingLine(String sentence) {
		sentence = sentence.trim();
		if (!checkPairedHex(sentence))
			return false;

		if (!checkHex(sentence))
			return false;
		
		if (sentence.length() < 2)
			return false;
		
		return true;

	}
	
	


	/**
	 * This method check whether there are hex digits or not.
	 * 
	 * @param line The sentence to be checked
	 * @return The Boolean to check hex
	 */
	public static boolean checkHex(String line) {
		for (char hexChar : line.toCharArray()) {
			if (!Character.toString(hexChar).matches("\\d|[A-F]| ")) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This method has the ability to check if the line contains only two digits separated by space
	 * 
	 * @param line the line to be checked
	 * @return the boo if the line passes or doesnot passes
	 */
	public static boolean checkPairedHex(String line) {
		String[] split = line.split(" ");
		for (int i = 0; i < split.length; i++) {
			if (split[i].length() > 2)
				return false;
		}
		return true;
	}


}
