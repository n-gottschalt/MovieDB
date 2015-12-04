package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class SubOptionMenuBar {

	private String subOptionName;
	private JMenuItem subOption;

	//constructor
	public SubOptionMenuBar(String subOptionName, String testOutput)
	{
		this.subOptionName = subOptionName;
		this.subOption = new JMenuItem(this.subOptionName);
		whatToDo(subOption, testOutput);
	}
	
	//returns the JMenuItem
	public JMenuItem giveMeMySon()
	{
		return subOption;
	}
	
	//tells what to do with event
	//first parameter is for menuOption, second is what object
	//to call, is optional.
	private void whatToDo(JMenuItem subMenuOption, Object... whatToDo)
	{
		subMenuOption.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//This is where stuff happens
				System.out.println(whatToDo[0]);
			}
		});
	}
	
}