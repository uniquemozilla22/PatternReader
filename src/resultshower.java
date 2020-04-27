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

    static JButton showPattern;
	JTextArea patternFound;


    resultshower()
    {
        //adding some designs
    setPreferredSize(new Dimension(600, 400));// setting the dimension of the applications.
    setBackground(Color.GRAY);// setting the background color

    // Initializing the Action Listener to the Class
    ActionListener search = new SearchPatternBtnListener();

    showPattern = new JButton("Show Pattern Results");
    showPattern.setToolTipText("Click me to show the results.");
    showPattern.addActionListener(search);
    add(showPattern);

	patternFound = new JTextArea("No Pattern Found");
	patternFound.setBounds(70,50,5,6);
	patternFound.setToolTipText("Your Pattern result will be shown here.");
    add(patternFound);

    }

    	// ... When user requests to search for pattern.
	private class SearchPatternBtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			TreeMap<Integer, byte[]> result = searchPattern();
			String pattern = "";
			String filename= "";
			String resultTxt = "";
			String hexValue = "";
			ArrayList<String> patternArr = new ArrayList<>();
			if (result.size() != 0) {
				Set<Map.Entry<Integer, byte[]>> enteries = result.entrySet();					
				for (Entry<Integer, byte[]> entry : enteries) {
					for (byte b : entry.getValue()) {
						patternArr.add(String.format("%x", b));
					}
					pattern = patternArr.toString().replaceAll("[,\\[\\]\\s+]", "");
					hexValue = String.format("0x%x", entry.getKey());
					resultTxt += "Pattern found: " + pattern + ", at offset: " + entry.getKey() + " ("
							+ hexValue + ") within the file.\n"+filename;
					patternArr.clear();
				}

			} else {
				resultTxt = "Pattern not found.";
			}

			resultTxt += "\n" ;

			// ... update the view.

			patternFound.setText(filename + resultTxt) ;
		}
    }


    public TreeMap<Integer, byte[]> searchPattern() {
		return indexOfPattern(fileloading.fileBytesArray, pattern.patternListArray);
    }

    
    public TreeMap<Integer, byte[]> indexOfPattern(ArrayList<Byte> source, byte[] pattern) {
		int sourceLength = source.size();
		int patternLength = pattern.length;
		int offset = 0;
		TreeMap<Integer, byte[]> answer = new TreeMap<>();

		int patternLoopIndex = 0;

		for (int index = 0; index < sourceLength; index++) {
			
			if (source.get(index) == pattern[patternLoopIndex]) {
				++patternLoopIndex;
			} else {
				if (source.get(index) == pattern[0]) {
					patternLoopIndex = 1;
				} else {
					patternLoopIndex = 0;
				}
			}
			if (patternLoopIndex == patternLength) {
				offset = index - patternLength + 1;
				answer.put(offset, pattern);
				patternLoopIndex = 0;
			}
		}
		return answer;
	}
	

    public TreeMap<Integer, byte[]> indexOfPattern(ArrayList<Byte> source, Stack<byte[]> patternList) {

		TreeMap<Integer, byte[]> answer = new TreeMap<>();
		TreeMap<Integer, byte[]> answerHolder = new TreeMap<>();

		for (byte[] pattern : patternList) {
			answerHolder = indexOfPattern(source, pattern);
			answer.putAll(answerHolder);
		}
		return answer;
	}
    

}