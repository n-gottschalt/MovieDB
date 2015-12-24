package windows;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.*;

import addWindow.AddWindowGUI;
import databaseJSON.Database;
import databaseJSON.JacksonAPI;
import menubar.OptionMenuBar;

public class MainWindow extends WindowBuilder{

	private static final long serialVersionUID = 1L;

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
		menuItems.get(0).add("Add", x -> new AddWindowGUI());
		menuItems.get(0).add("Remove", x -> new AddWindowGUI());
		menuItems.get(0).add("Update", x -> new AddWindowGUI());
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
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, UnsupportedLookAndFeelException, SQLException, ParseException
	{
		new MainWindow();
	}
}
