package windows;

import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import menubar.*;

public class WindowBuilder extends JFrame{
	
	private static final long serialVersionUID = 1L;
	protected int windowSizeX;
	protected int windowSizeY;
	
	protected String title;
	
	protected JFrame mainFrame;
	
	public WindowBuilder() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		this.windowSizeX = 500;
		this.windowSizeY = 500;
		
		this.title = "Movie DB";
		mainFrame = new JFrame(this.title);
		mainFrame.setSize(this.windowSizeX, this.windowSizeY);
		UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
		menuBarBuilder();
	}
	
	protected WindowBuilder(int windowSizeX, int windowSizeY, String title) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		this.windowSizeX = windowSizeX;
		this.windowSizeY = windowSizeY;
		
		this.title = title;
		mainFrame = new JFrame(this.title);
		mainFrame.setSize(this.windowSizeX, this.windowSizeY);
		UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
	}

	protected JLabel pictureBuilder(String wherePictureLives, int width, int height) throws IOException
	{
		BufferedImage image = ImageIO.read(new File(wherePictureLives));
		return new JLabel(new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
	}
	
	protected JLabel pictureBuilder(byte[] pictureFromDB, int width, int height)
	{	
		Image image = Toolkit.getDefaultToolkit().createImage(pictureFromDB);
		return new JLabel(new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
	}
	
	public void make()
	{
		mainFrame.setVisible(true);
	}
	
	protected JMenuBar menuBarBuilder(ArrayList<OptionMenuBar> menuItems)
	{
		return new MenuBar(menuItems).giveMeTheMenuBar();
	}
	
	protected JMenuBar menuBarBuilder()
	{
		return new MenuBar().giveMeTheMenuBar();
	}
	public JFrame getFrame()
	{
		return mainFrame;
	}
}
