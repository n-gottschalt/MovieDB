package menubar;

import java.util.ArrayList;

import javax.swing.JMenuBar;

public class MenuBar {

	private JMenuBar menuBar;
	
	public MenuBar()
	{
		menuBar = new JMenuBar();
		
	}
	
	public MenuBar(ArrayList<OptionMenuBar> subMenuAttributes)
	{
		menuBar = new JMenuBar();
		for(OptionMenuBar object : subMenuAttributes)
		{
			menuBar.add(object.giveMeMySonsDad());
		}
	}
	
	public void add(OptionMenuBar itemToAdd)
	{
		menuBar.add(itemToAdd.giveMeMySonsDad());
	}
	
	public JMenuBar giveMeTheMenuBar()
	{
		return menuBar;
	}
}
