package test;

import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

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
	//	mainFrame.add(buildMoviePanel());
		super.make();
	}
	
	public JScrollPane buildMoviePanel(ArrayList<LinkedHashMap<String, Object>> movies) throws IOException
	{
		JPanel mainPanel = new JPanel();
		JPanel subPanel;
		for(LinkedHashMap<String, Object> i : movies)
		{
			subPanel = new JPanel();
			subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.PAGE_AXIS));
			subPanel.add(super.pictureBuilder((byte[])i.get("Art")));
			subPanel.add(new JLabel((String)i.get("Title")));
			mainPanel.add(subPanel);
		}
		JScrollPane scroll = new JScrollPane(mainPanel);
		return scroll;
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, UnsupportedLookAndFeelException
	{
		MainWindow test = new MainWindow();
		test.buildWindow();
	}

}
