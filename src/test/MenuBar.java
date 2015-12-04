package test;

import java.util.ArrayList;

import javax.swing.JMenuBar;

public class MenuBar {

	private JMenuBar menuBar;
	
	//default constructor
	public MenuBar()
	{
		menuBar = new JMenuBar();
		
	}
	
	//Overridden constructor
	public MenuBar(ArrayList<OptionMenuBar> subMenuAttributes)
	{
		menuBar = new JMenuBar();
		for(OptionMenuBar object : subMenuAttributes)
		{
			menuBar.add(object.giveMeMySonsDad());
		}
	}
	
	//Simple add
	public void add(OptionMenuBar itemToAdd)
	{
		menuBar.add(itemToAdd.giveMeMySonsDad());
	}
	
	public JMenuBar giveMeTheMenuBar()
	{
		return menuBar;
	}
}
