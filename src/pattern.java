import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.util.Stack;
import java.util.TreeMap;
import javax.swing.*;
import javax.swing.border.Border;

public class pattern extends JPanel {

    // ... Instance variables
    static Stack<byte[]> patternListArray;
    JLabel patternShower;
    JTextArea patternTextArea;
    static JButton patternLoaderBtn;

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

        patternTextArea= new JTextArea("---");
        add(patternTextArea);

    }

    // ... When user requests to load pattern from file.
    private class LoadPatternBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // ... Point the current directory.
            JFileChooser fileChooser = new JFileChooser(".//");
            fileChooser.showOpenDialog(null);
            // ... Get the selected file
            File file = fileChooser.getSelectedFile();
            if (file != null) {
                setPattern(file);

                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    patternTextArea.read(br,null);
                    br.close();

                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, "There is no such file.");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    
                    JOptionPane.showMessageDialog(null, "There is error in reading the file.");
                }
			}
		}
    }
    
    public void setPattern(File file) {
		patternListArray = readPatternFile(file);
    }
    

    public Stack<byte[]> readPatternFile(File file) {
		Stack<byte[]> patterns = new Stack<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] lineArray = line.trim().split("\\s");
				patterns.push(convertToByteArray(lineArray));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return patterns;
    }
    public byte[] convertToByteArray(String[] line) {
		String lineString = Arrays.toString(line).replaceAll("[,\\s+\\[\\]]", "");

		return hexStringToByteArray(lineString);
    }
    
	public byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}
}