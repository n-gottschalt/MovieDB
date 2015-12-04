package test;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.menu.WebMenuBar;


public class WindowTest extends JFrame{
	
	public void buildWindow() throws IOException
	{
		//this will be scrollable
		JPanel panel = new JPanel();
		
		//everything goes into this
		JFrame mainFrame = new JFrame("Movie DB");
		
		
		//????????MENU BAR??????
		//Menu bar
		JMenuBar menuBar = new JMenuBar();
		
		//Menu itesm
		JMenu menu = new JMenu("Movies");
		menu.setMnemonic(KeyEvent.VK_M);
		menu.getAccessibleContext().setAccessibleDescription(
				"Movies! Click this for more options");
		//add to the Menubar
		menuBar.add(menu);
		
		//MenuItem
		JMenuItem menuItem = new 
				JMenuItem("subMovie", KeyEvent.VK_T);
		menu.add(menuItem);
		
		//add to frame
		mainFrame.setJMenuBar(menuBar);
		//!!!!!! END MENUBAR
		
		
		
		//???????????PICTURES
		// ADD PICTURE AND TEXT TO A SUBPANEL
		// ADD ALL SUBPANELS TO MAIN PANEL
		// ADD MAIN PANEL TO FRAME
		BufferedImage image;
		image = ImageIO.read(new File("C:\\Users\\Nathanial\\Desktop\\fileicon.png"));
		JLabel picLabel = new JLabel(new ImageIcon(image));
		JLabel picLabel2 = new JLabel(new ImageIcon(image));
		
		//sub panel test with text and picture
		JPanel subPanel = new JPanel();
		JPanel subPanel2 = new JPanel();
		JLabel textTest = new JLabel("Movie Title Year");
		JLabel textTest2 = new JLabel("Movie Title Year");
		
		subPanel.setLayout(new BoxLayout(subPanel,
				BoxLayout.PAGE_AXIS));
		subPanel2.setLayout(new BoxLayout(subPanel2,
				BoxLayout.PAGE_AXIS));
		
		subPanel.add(picLabel);
		subPanel.add(textTest);
		
		subPanel2.add(picLabel2);
		subPanel2.add(textTest2);
		
		
		//Clicking ability
		picLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me) {
				System.out.println("CLICKED");}});
		
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.add(subPanel);
		mainPanel.add(subPanel2);
		
				
		//!!!!!!!!END PICTURES
		
		mainFrame.setSize(500, 500);
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException
	{
		UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
		WindowTest test = new WindowTest();
		test.buildWindow();
	}
}