package menubar;

import javax.swing.JMenu; 
import tools.LambdaFunction;

public class OptionMenuBar {

	private JMenu menuOption;
	private SubOptionMenuBar subOption;
	
	public OptionMenuBar(String menuOptionTitle)
	{
		menuOption = new JMenu(menuOptionTitle);
	}

	public OptionMenuBar(String menuOptionTitle, String subMenuOptionTitle,
			LambdaFunction actionToRun)
	{
		menuOption = new JMenu(menuOptionTitle);
		subOption = new SubOptionMenuBar(subMenuOptionTitle, actionToRun);
		menuOption.add(subOption.giveMeMySon());
	}
	
	public void add(String subMenuOptionTitle, LambdaFunction actionToRun)
	{
		subOption = new SubOptionMenuBar(subMenuOptionTitle, actionToRun);
		menuOption.add(subOption.giveMeMySon());
	}

	public JMenu giveMeMySonsDad()
	{
		return menuOption;
	}
}
