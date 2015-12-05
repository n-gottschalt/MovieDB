package menubar;

import javax.swing.JMenu;

public class OptionMenuBar {

	private JMenu menuOption;
	private SubOptionMenuBar subOption;
	
	//Default constructor
	public OptionMenuBar(String menuOptionTitle)
	{
		menuOption = new JMenu(menuOptionTitle);
	}
	
	//Overloaded
	public OptionMenuBar(String menuOptionTitle, String subMenuOptionTitle,
			int actionToRun)
	{
		menuOption = new JMenu(menuOptionTitle);
		subOption = new SubOptionMenuBar(subMenuOptionTitle, actionToRun);
		menuOption.add(subOption.giveMeMySon());
	}
	
	//add new SubOption
	public void add(String subMenuOptionTitle, int actionToRun)
	{
		subOption = new SubOptionMenuBar(subMenuOptionTitle, actionToRun);
		menuOption.add(subOption.giveMeMySon());
	}

	//returns JMenu
	public JMenu giveMeMySonsDad()
	{
		return menuOption;
	}
}
