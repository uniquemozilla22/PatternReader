import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.util.Stack;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.DatatypeConverter;

public class pattern extends JPanel {

  // ... Instance variables
	/**
	 * valid Byte patterns from pattern-file to scan in a file
	 */
	public static List<byte[]> bytesToScan = new ArrayList<byte[]>(
			Arrays.asList(DatatypeConverter.parseHexBinary("1ADF942853")));
  /**
	 * invalid lines from pattern-file
	 */
	public static List<String> failedLines = new ArrayList<String>(Arrays.asList("123RSADNXJ", "12 er s d sd"));

	
	
  static Stack<byte[]> patternListArray;
  JLabel patternShower;
  JTextArea patternTextArea;
  static JButton patternLoaderBtn;
  private JList<String> lstPatterns;

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
    
    
    lstPatterns = new JList<String>();
	lstPatterns.setListData(getSpacedPatternStrings());
	lstPatterns.setBounds(10, 57, 478, 98);
	add(lstPatterns);
  }

  // ... When user requests to load pattern from file.
  private class LoadPatternBtnListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      
      pattern.bytesToScan.clear();
				failedLines.clear();
				
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
						readPatternFile(patternFilePath);
					}
				} catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null,
							"There seems be some issue with selection." + "\r\nERROR: " + ex.getMessage(), "Error!",
							JOptionPane.PLAIN_MESSAGE);
				}

				// Pattern.
				if (failedLines.size() > 0) {
					String errorMessage = "These lines are not valid:\r\n";
					for (String fLine : failedLines) {
						errorMessage += fLine + "\r\n";
					}
					JOptionPane.showMessageDialog(null, errorMessage + "Valid lines are imported.", "OOPS!", JOptionPane.PLAIN_MESSAGE);
				}
				
				// Display spaced byte pattern
				String[] bytePatterns = getSpacedPatternStrings();
				lstPatterns.setListData(bytePatterns);
    }

  }

  /**
	 * Read and save patterns from a pattern file
	 * 
	 * @param patternFilePath the file path of pattern file
	 * @throws IOException if the file could be read form the given path
	 */
	public static void readPatternFile(String patternFilePath) throws IOException {
		BufferedReader reader;
		reader = new BufferedReader(new FileReader(patternFilePath));
		String line = reader.readLine();

		while (line != null) {
			line = line.toUpperCase();
			if (checkLine(line)) {
				line = line.replace(" ", "");
				bytesToScan.add(DatatypeConverter.parseHexBinary(line));
			} else {
				failedLines.add(line);
			}
			// read next line
			line = reader.readLine();
		}

		reader.close();

	}
	
	/**
	 * Returns the stored patterns in a format to display in the screen
	 * 
	 * @return the array of string in format of two digits and space
	 */
	public static String[] getSpacedPatternStrings() {
		String[] result = new String[bytesToScan.size()];
		// result = bytesToScan.toArray(result);
		for (int i = 0; i < result.length; i++) {
			result[i] = DatatypeConverter.printHexBinary(bytesToScan.get(i)).replaceAll("..(?!$)", "$0 ");
		}
		return result;
	}
	
	/**
	 * Validate if the line containes paired hex digits separate by space
	 * 
	 * @param line the line to be validated
	 * @return if the line is valid or not
	 */
	private static boolean checkLine(String line) {
		line = line.trim();
		if (!checkPairedHex(line))
			return false;

		if (!checkHex(line))
			return false;
		
		if (line.length() < 2)
			return false;
		
		return true;

	}
	
	


	/**
	 * Check if all the characters are hex digits
	 * 
	 * @param line the line to be checked
	 * @return the bool if the line passes the test
	 */
	private static boolean checkHex(String line) {
		for (char hexChar : line.toCharArray()) {
			if (!Character.toString(hexChar).matches("\\d|[A-F]| ")) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check if the line contains only two digits separated by space
	 * 
	 * @param line the line to be checked
	 * @return the boo if the line passes
	 */
	private static boolean checkPairedHex(String line) {
		String[] split = line.split(" ");
		for (int i = 0; i < split.length; i++) {
			if (split[i].length() > 2)
				return false;
		}
		return true;
	}


}
