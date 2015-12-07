package test;

import java.io.IOException;

import javax.swing.*;

public class MainWindow extends WindowBuilder{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 	subPanel.setLayout(new BoxLayout(subPanel,
				BoxLayout.PAGE_AXIS));
		subPanel2.setLayout(new BoxLayout(subPanel2,
				BoxLayout.PAGE_AXIS));
	 */
	public MainWindow() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		super(300, 300, "Main Window");
	}
	
	public void buildWindow() throws IOException
	{
		mainFrame.add(buildMoviePanel());
		super.make();
	}
	
	private JPanel buildMoviePanel() throws IOException
	{
		//Will need to call method in database class
		//then parse the data for the movie picture and the title
		//use for loop
		JPanel test = new JPanel();
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.PAGE_AXIS));
	//	subPanel.add(super.pictureBuilder("C:\\Users\\Nathanial\\Desktop\\fileicon.png"));
		subPanel.add(new JLabel("Movie Title"));
		test.add(subPanel);
		return test;
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, UnsupportedLookAndFeelException
	{
		MainWindow test = new MainWindow();
		test.buildWindow();
	}

}
