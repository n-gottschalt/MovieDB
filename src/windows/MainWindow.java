package windows;

import java.awt.GridLayout;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.*;

import databaseJSON.Database;
import menubar.OptionMenuBar;

public class MainWindow extends WindowBuilder{

	private static final long serialVersionUID = 1L;

	public MainWindow() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		super(300, 300, "Main Window");
	}
	
	public MainWindow(ArrayList<LinkedHashMap<String, Object>> movies) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		super(400, 400, "Main Window");
		
	}
	
	public void buildWindow() throws IOException, ClassNotFoundException, SQLException
	{
		super.getFrame().setJMenuBar(menuBarBuilder(addToMenu()));
		getFrame().add(buildMoviePanel(new Database().getData()));
		super.make();
	}
	
	private ArrayList<OptionMenuBar> addToMenu()
	{
		ArrayList<OptionMenuBar> menuItems = new ArrayList<OptionMenuBar>();
		menuItems.add(new OptionMenuBar("Movies"));
		menuItems.get(0).add("Add", 1);
		menuItems.get(0).add("Remove", 2);
		menuItems.get(0).add("Update", 3);
		return menuItems;
	}
	
	private JScrollPane buildMoviePanel(ArrayList<LinkedHashMap<String, Object>> movies) throws IOException
	{
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0,6));
		JPanel subPanel;
		for(LinkedHashMap<String, Object> i : movies)
		{
			subPanel = new JPanel();
			subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.PAGE_AXIS));
			subPanel.add(super.pictureBuilder((byte[])i.get("Art"), 214, 317));
			subPanel.add(new JLabel((String)i.get("Title")));
			mainPanel.add(subPanel);
		}
		JScrollPane scroll = new JScrollPane(mainPanel);
		return scroll;
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, UnsupportedLookAndFeelException, SQLException
	{
		MainWindow test = new MainWindow();
		test.buildWindow();
	}

}
