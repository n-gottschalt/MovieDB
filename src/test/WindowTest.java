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

import menubar.MenuBar;
import menubar.OptionMenuBar;


public class WindowTest extends JFrame{
	
	protected int windowSizeX;
	protected int windowSizeY;
	
	protected String title;
	
	//Everything goes into this
	protected JFrame mainFrame;
	
	//Default constructor
	public WindowTest()
	{
		this.windowSizeX = 500;
		this.windowSizeY = 500;
		
		this.title = "Movie DB";
		mainFrame = new JFrame(this.title);
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Override constructor
	public WindowTest(int windowSizeX, int windowSizeY, String title)
	{
		this.windowSizeX = windowSizeX;
		this.windowSizeY = windowSizeY;
		
		this.title = title;
		mainFrame = new JFrame(this.title);
	}
	
	public void init() throws IOException
	{
		buildWindow();
	}
	
	public void buildWindow() throws IOException
	{
		//????????MENU BAR??????
		//Menu bar
		/*
		ArrayList<OptionMenuBar> listOfMenuItems = new ArrayList<OptionMenuBar>();

		listOfMenuItems.add(new OptionMenuBar("Movies", "SubMovie", 1));
		listOfMenuItems.get(0).add("sub movie2", 1);
		listOfMenuItems.get(0).add("sub movies 3", 1);
		listOfMenuItems.add(new OptionMenuBar("People"));
		listOfMenuItems.get(1).add("sub people 1", 1);
		
		mainFrame.setJMenuBar( new MenuBar(listOfMenuItems).giveMeTheMenuBar());
		*/
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
		
		//can store into an array !!!
		MouseAdapter mouseAction = new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				System.out.println("CLICKED");}};
		
		picLabel.addMouseListener(mouseAction);
		
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.add(subPanel);
		mainPanel.add(subPanel2);
		
				
		//!!!!!!!!END PICTURES
		
		mainFrame.setSize(500, 500);
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
	}
	
	//Menubar with 3d array
	public void buildWindowBar(String [][][] menuOptions)
	{
		
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException
	{
		UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
		WindowTest test = new WindowTest();
		test.init();
	}
}