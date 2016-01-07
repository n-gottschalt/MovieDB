package addWindow;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Iterator;
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
		super.getWindowGUI().setFrameClose(() -> compareDifference());
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
	
	private void compareDifference()
	{
		LinkedHashMap<String, Object> dataInFields = tools.getDataFromTextFields();
		Collection<Object> dataInFieldsCollection = dataInFields.values();
		Iterator<Object> dataInFieldsCollectionITR = dataInFieldsCollection.iterator();
		
		LinkedHashMap<String, Object> localData = data;
		//take care of the 'min' issue
		localData.replace("Runtime", localData.get("Runtime") + " min");
		Collection<Object> dataCollection = data.values();
		Iterator<Object> dataCollectionITR = dataCollection.iterator();

		for(int i = 0; i < dataInFieldsCollection.size(); i++)
		{
			if(!(dataInFieldsCollectionITR.next().toString()).equals(dataCollectionITR.next().toString()))
			{
				verifyChange();
				break;
			}
		}
	}
	
	private void verifyChange()
	{
		if(JOptionPane.showConfirmDialog(tools.getWindowGUI().getFrame(), "Do you want to save changes?") == 0)
			setModify();
	}
	
	
	private void setModify()
	{
		try {
			new Database().modifyData(tools.currentDataInTextFields(), 
					Integer.parseInt((String)data.get("MovieID")));
			JOptionPane.showMessageDialog(tools.getWindowGUI().getFrame(), "Movie modified!");
			tools.getWindowGUI().close();
			tools.getMainWindow().reDraw(tools.getMainWindow().getScroll().getVerticalScrollBar().getValue());
		} catch (ClassNotFoundException | SQLException | ParseException e1) {
			e1.printStackTrace();
		}
	}
	
	private void setDelete()
	{
		if(JOptionPane.showConfirmDialog(tools.getWindowGUI().getFrame(), "Are you sure?") == 0)
			try {
				new Database().deleteData(Integer.parseInt((String)data.get("MovieID")));
				JOptionPane.showMessageDialog(tools.getWindowGUI().getFrame(),
						(String)data.get("Title") + " has been deleted.");
				tools.getMainWindow().reDraw(tools.getMainWindow().getScroll().getVerticalScrollBar().getValue());
				tools.getWindowGUI().close();
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
	}
	
	private void setClose()
	{
		tools.getWindowGUI().close();
	}
}
