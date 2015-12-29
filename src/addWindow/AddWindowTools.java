package addWindow;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import javax.swing.*;
import databaseJSON.Database;
import windows.MainWindow;

public class AddWindowTools {

	private HashMap<String, JTextField> textFields = new HashMap<>();
	private MainWindow main;
	public static String[] labels = {"Title", "Released", "Rating", "Director", "Genre",
			"Runtime", "Plot"};
	
	LinkedHashMap<String, Object> data;
	
	AddWindowGUI screen;
	
	
	public AddWindowTools(MainWindow main)
	{
		this.main = main;
	}

	public void run() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, UnsupportedLookAndFeelException
	{
		screen = new AddWindowGUI(this);
	}
	
	public void storeData() throws ClassNotFoundException, SQLException, ParseException, IOException
	{
		Database storeData = new Database();
		LinkedHashMap<String, Object> dataToStore = new LinkedHashMap<>();
		dataToStore.put("Title", textFields.get("Title").getText());
		dataToStore.put("Released", textFields.get("Released").getText());
		dataToStore.put("imdbRating", textFields.get("Rating").getText());
		dataToStore.put("Director", textFields.get("Director").getText());
		dataToStore.put("Genre", textFields.get("Genre").getText());
		dataToStore.put("Runtime", textFields.get("Runtime").getText());
		dataToStore.put("Plot", textFields.get("Plot").getText());
		dataToStore.put("Art", (byte[])data.get("Art"));
	
		storeData.saveData(dataToStore);
	}
	
	public void clearData(String picture) throws IOException
	{
		screen.clear();
		screen.setPicture(picture);
		for(String i : labels)
			textFields.get(i).setText("");
		screen.buildTextBox();
	}
	
	public void clearData(byte[] picture) throws IOException
	{
		screen.clear();
		screen.setPicture(picture);
		for(String i : labels)
			textFields.get(i).setText("");
		screen.buildTextBox();
	}
	
	
	public void insertInputFieldData(LinkedHashMap<String, Object> data) throws IOException
	{
		this.data = data;
		clearData((byte[])data.get("Art"));
		textFields.get("Title").setText((String)data.get("Title"));
		textFields.get("Released").setText((String)data.get("Released"));
		textFields.get("Rating").setText((String)data.get("imdbRating"));
		textFields.get("Director").setText((String)data.get("Director"));
		textFields.get("Genre").setText((String)data.get("Genre"));
		textFields.get("Runtime").setText((String)data.get("Runtime"));
		textFields.get("Plot").setText((String)data.get("Plot"));
	}
	
	public HashMap<String, JTextField> getTextFields()
	{
		return textFields;
	}
	
	public static String[] getLabels()
	{
		return labels;
	}
	
	public MainWindow getMainWindow()
	{
		return main;
	}
	
}
