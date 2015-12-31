package addWindow;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedHashMap;

import javax.swing.*;
import databaseJSON.Database;
import windows.MainWindow;

public class ModifyWindow extends AddWindowTools {

	private LinkedHashMap<String, Object> data;
	private AddWindowTools tools;
	
	public ModifyWindow(MainWindow main, LinkedHashMap<String, Object> i) throws ClassNotFoundException, InstantiationException, 
	IllegalAccessException, IOException, UnsupportedLookAndFeelException, SQLException {
		super(main);
		super.run();
		tools = super.getTools();
		//change buttons on AddWindowGUI
		JPanel buttonPanel = new JPanel();
		
		buttonPanel.add(new JButton("Modify"));
		((JButton)buttonPanel.getComponent(0)).addActionListener(
				x -> setModify());
		
		buttonPanel.add(new JButton("Delete"));
		((JButton)buttonPanel.getComponent(1)).addActionListener(
				x -> setDelete());
		
		buttonPanel.add(new JButton("Cancel"));
		((JButton)buttonPanel.getComponent(2)).addActionListener(
				x -> setClose());
		
		super.getWindowGUI().setButtonPanel(buttonPanel);
		data = new Database().getData(Integer.valueOf((String)i.get("MovieID")), "MovieID");
		super.insertInputFieldData(data);
		super.getTextFields().get("Runtime").setText(
				super.getTextFields().get("Runtime").getText() + " min");
	}
	
	private void setModify()
	{
		try {
			new Database().modifyData(tools.modifyTest(), 
					Integer.parseInt((String)data.get("MovieID")));
			JOptionPane.showMessageDialog(tools.getWindowGUI().getFrame(), "Movie modified!");
			tools.getWindowGUI().close();
		} catch (ClassNotFoundException | SQLException | ParseException e1) {
			e1.printStackTrace();
		}
	}
	
	private void setDelete()
	{
		try {
			new Database().deleteData(Integer.parseInt((String)data.get("MovieID")));
			JOptionPane.showMessageDialog(tools.getWindowGUI().getFrame(),
					(String)data.get("Title") + " has been deleted.");
			tools.getWindowGUI().close();
		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setClose()
	{
		tools.getWindowGUI().close();
	}
}
