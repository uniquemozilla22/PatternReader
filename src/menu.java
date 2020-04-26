import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class menu extends JPanel {
	JMenuBar setupMenu()
    {
//		Calling the "AboutAction" Action Listner
		AboutAction abouta =new AboutAction();


//		Calling the "exitListener" Action Listener
		ActionListener exit = new exitListener();
		
//		Making the new JMenuBar
		JMenuBar menuBar= new JMenuBar();
		
//		 Setting the Menu File
    	JMenu file = new JMenu("File");
    	file.setToolTipText("File Menu Item");//setting the Tool Tip on the menu bar
    	file.setMnemonic(KeyEvent.VK_F);
//    	 Setting the Menu File
    	JMenu About = new JMenu("About");
    	file.setToolTipText("About The Programme");//setting the Tool Tip on the menu bar
    	file.setMnemonic(KeyEvent.VK_A);
		
		// Setting the Menu Item to select the file to load
    	JMenuItem LoadFile = new JMenuItem("1.Select File/Directory",new ImageIcon("Images/folder.png"));
//    	setting the Tool Tip on the menu bar
		LoadFile.setToolTipText("Click to load the file");
		LoadFile.addActionListener(new ActionListener() {
			//adding functionality to the item
			@Override
			public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                fileloading.loadFileButton.doClick();
                		
		
		}});
		LoadFile.setMnemonic(KeyEvent.VK_L);

		// Setting the Menu Item to load the pattern
		JMenuItem LoadPattern = new JMenuItem("2.Select Pattern",new ImageIcon("Images/folder.png"));
//    	setting the Tool Tip on the menu bar
		LoadPattern.setToolTipText("Click to load pattern");
		LoadPattern.addActionListener(new ActionListener() {
			//adding functionality to the item
			@Override
			public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                pattern.patternLoaderBtn.doClick();
                		
		
		}});
		LoadPattern.setMnemonic(KeyEvent.VK_P);
		
		// Setting the Menu Item to Search for pattern
		JMenuItem SearchPattern = new JMenuItem("3.Search Pattern",new ImageIcon("Images/folder.png"));
		//setting the Tool Tip on the menu bar
		SearchPattern.setToolTipText("Click to Search pattern");
		SearchPattern.addActionListener(new ActionListener() {
			//adding functionality to the item
			@Override
			public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                resultshower.showPattern.doClick();
                		
		
		}});
    	SearchPattern.setMnemonic(KeyEvent.VK_S);
    	
    	JMenuItem Exit = new JMenuItem("Exit",new ImageIcon("Images/logout.png"));
		Exit.setToolTipText("Click to load the file");//setting the Tool Tip on the menu bar
		Exit.addActionListener(exit);
    	LoadFile.setMnemonic(KeyEvent.VK_L);
    	
    	JMenuItem info = new JMenuItem("Info", new ImageIcon("Images/information.png"));
    	info.setToolTipText("Click to know more about the programme");
    	info.setMnemonic(KeyEvent.VK_I);
    	info.addActionListener(abouta);
    	
//    	adding Load and Exit Menu Items to File Menu
		file.add(LoadFile);
		file.add(LoadPattern);
		file.add(SearchPattern);
    	file.add(Exit);
    	
//    	adding info menu item to About Menu
    	About.add(info);
    	
//    	adding JMenu file and About to menuBar
    	menuBar.add(file);
    	menuBar.add(About);
		return menuBar;
    }
	
	
//	this class is a Action Listener used to create the information about the programme
	private class AboutAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null," The Programme is designed to provide support tools to\r\n" + 
					"identify known byte-patterns (signatures) within file contents.\n"  + 
					 
					"						Author: Yogesh Bhattarai\n"  + 
					"						\u00a9 All right reserved");
			
		}
	}
	  // Declaring the Exit Listener
	  class exitListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			}
		}
}

  
  
