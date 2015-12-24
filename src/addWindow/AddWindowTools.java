package addWindow;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.io.FileUtils;
import org.apache.http.util.ByteArrayBuffer;

import databaseJSON.Database;

public class AddWindowTools {


	private HashMap<String, JTextField> textFields = new HashMap<>();
	public String[] labels = {"Title", "Release", "Rating", "Director", "Genre",
			"Runtime", "Plot"};
	
	AddWindowGUI screen;
	
	public void run() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, UnsupportedLookAndFeelException
	{
		screen = new AddWindowGUI(this);
	}
	
	public void storeData() throws ClassNotFoundException, SQLException, ParseException, IOException
	{
		Database storeData = new Database();
		LinkedHashMap<String, Object> dataToStore = new LinkedHashMap<>();
		dataToStore.put("Title", textFields.get("Title").getText());
		dataToStore.put("Released", textFields.get("Release").getText());
		dataToStore.put("imdbRating", textFields.get("Rating").getText());
		dataToStore.put("Director", textFields.get("Director").getText());
		dataToStore.put("Genre", textFields.get("Genre").getText());
		dataToStore.put("Runtime", textFields.get("Runtime").getText());
		dataToStore.put("Plot", textFields.get("Plot").getText());
		
			InputStream is = FileUtils.openInputStream(new File("C:\\Users\\Nathanial\\Desktop\\standin.png"));
			BufferedInputStream bis = new BufferedInputStream(is);
			
			ByteArrayBuffer baf = new ByteArrayBuffer(500);
			int current = 0;
			while((current = bis.read()) != -1)
				baf.append((byte) current);
			
		dataToStore.put("Art", baf.toByteArray());
		
		storeData.saveData(dataToStore);
	}
	
	public void insertInputFieldData(LinkedHashMap data)
	{
		textFields.get("Title").setText((String)data.get("Title"));
		getTextFields().get("Release").setText((String)data.get("Released"));
		getTextFields().get("Rating").setText((String)data.get("imdbRating"));
		getTextFields().get("Director").setText((String)data.get("Director"));
		getTextFields().get("Genre").setText((String)data.get("Genre"));
		getTextFields().get("Runtime").setText((String)data.get("Runtime"));
		getTextFields().get("Plot").setText((String)data.get("Plot"));
		screen.repaint();
	}
	
	public void output()
	{
		System.out.println("TEST");
	}
	
	public HashMap<String, JTextField> getTextFields()
	{
		return textFields;
	}
	
	public String[] getLabels()
	{
		return labels;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, UnsupportedLookAndFeelException
	{
		AddWindowTools test = new AddWindowTools();
		test.run();
	}
}
