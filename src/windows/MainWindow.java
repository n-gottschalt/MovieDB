package windows;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import javax.swing.*;

import addWindow.AddWindowTools;
import databaseJSON.Database;
import menubar.OptionMenuBar;

public class MainWindow extends WindowBuilder{

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JScrollPane scroll;

	public MainWindow() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, SQLException
	{
		super(screenSize(), "Main Window");
		buildWindow();
	}

	public static Dimension screenSize()
	{
		return Toolkit.getDefaultToolkit().getScreenSize();
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
		menuItems.get(0).add("Add", x -> new AddWindowTools(this).run());
		return menuItems;
	}
	
	//Loads in the movies to be displayed on the main screen
	//the 'movies' object is pulled from the database
	public JScrollPane buildMoviePanel(ArrayList<LinkedHashMap<String, Object>> movies) throws IOException
	{
		mainPanel = new JPanel();
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
		scroll = new JScrollPane(mainPanel);
		return scroll;
	}
	
	public void clear()
	{
		getFrame().remove(scroll);
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, UnsupportedLookAndFeelException, SQLException, ParseException
	{
		new MainWindow();
	}
}
