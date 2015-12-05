package menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class SubOptionMenuBar {

	private String subOptionName;
	private JMenuItem subOption;

	public SubOptionMenuBar(String subOptionName, int whatToDoOption)
	{
		this.subOptionName = subOptionName;
		this.subOption = new JMenuItem(this.subOptionName);
		whatToDo(this.subOption, whatToDoOption);
	}
	
	public JMenuItem giveMeMySon()
	{
		return subOption;
	}
	
	//tells what to do with event
	//first parameter is for menuOption, second is what object
	//to call, is optional.
	public void whatToDo(JMenuItem subMenuOption, int whatToDo)
	{
		subMenuOption.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//This is where stuff happens
				actionToRun(whatToDo);
			}
		});
	}
	
	//logic for whatToDo
	private void actionToRun(int selector)
	{
		//Fill in later
	}
	
}