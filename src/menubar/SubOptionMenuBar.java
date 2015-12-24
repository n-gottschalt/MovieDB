package menubar;

import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.*;
import tools.LambdaFunction;

public class SubOptionMenuBar {

	private String subOptionName;
	private JMenuItem subOption;

	public SubOptionMenuBar(String subOptionName, LambdaFunction whatToDoOption)
	{
		this.subOptionName = subOptionName;
		this.subOption = new JMenuItem(this.subOptionName);
		whatToDo(this.subOption, whatToDoOption);
	}
	
	public JMenuItem giveMeMySon()
	{
		return subOption;
	}
	
	private void whatToDo(JMenuItem subMenuOption, LambdaFunction action)
	{
		subMenuOption.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				try {
					action.run(new Object());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException
						| UnsupportedLookAndFeelException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}
}