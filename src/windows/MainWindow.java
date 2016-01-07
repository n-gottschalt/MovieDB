package windows;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import javax.swing.*;

import addWindow.AddWindowTools;
import addWindow.ModifyWindow;
import databaseJSON.Database;
import menubar.OptionMenuBar;

public class MainWindow extends WindowBuilder{

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JScrollPane scroll;
	
	@SuppressWarnings("unused")
	private ModifyWindow modifyWindow;
	private MainWindow mainScreen;

	public MainWindow() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, SQLException
	{
		super(screenSize(), "Main Window");
		buildWindow();
		this.mainScreen = this;
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
		
		Collections.sort(movies, new Comparator<LinkedHashMap<String, Object>>()
				{

					@Override
					public int compare(LinkedHashMap<String, Object> arg0, LinkedHashMap<String, Object> arg1) {
						if (((String)arg0.get("Title")).compareTo(((String)arg1.get("Title"))) > 0)
							return 1;
						else if (((String)arg0.get("Title")).compareTo(((String)arg1.get("Title"))) < 0)
							return -1;
						else
							return 0;
					}
			
				});

		for(LinkedHashMap<String, Object> i : movies)
		{
			JPanel subPanel = new JPanel();
			subPanel.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{
					try {
						modifyWindow = new ModifyWindow(mainScreen, i);
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException
							| UnsupportedLookAndFeelException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			
			subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.PAGE_AXIS));
			subPanel.add(super.pictureBuilder((byte[])i.get("Art"), 214, 317));
			subPanel.add(new JLabel((String)i.get("Title")));
			
			mainPanel.add(subPanel);
		}
		scroll = new JScrollPane(mainPanel);
		return scroll;
	}
	
	public void reDraw(int setPosition) throws ClassNotFoundException, SQLException
	{
		clear();
		try {
			buildWindow();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scroll.getVerticalScrollBar().setValue(setPosition);
	}
	
	public void clear()
	{
		getFrame().remove(scroll);
	}
	
	public JScrollPane getScroll()
	{
		return scroll;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, UnsupportedLookAndFeelException, SQLException, ParseException
	{
		new MainWindow();
	}
}
