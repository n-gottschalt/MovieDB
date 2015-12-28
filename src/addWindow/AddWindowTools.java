package addWindow;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.http.util.ByteArrayBuffer;

import databaseJSON.Database;

public class AddWindowTools {


	private HashMap<String, JTextField> textFields = new HashMap<>();
	public String[] labels = {"Title", "Release", "Rating", "Director", "Genre",
			"Runtime", "Plot"};
	
	LinkedHashMap<String, Object> test;
	
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
		dataToStore.put("Art", (byte[])test.get("Art"));
		
		storeData.saveData(dataToStore);
	}
	
	public void insertInputFieldData(LinkedHashMap<String, Object> data) throws IOException
	{
		test = data;
		textFields.get("Title").setText((String)data.get("Title"));
		textFields.get("Release").setText((String)data.get("Released"));
		textFields.get("Rating").setText((String)data.get("imdbRating"));
		textFields.get("Director").setText((String)data.get("Director"));
		textFields.get("Genre").setText((String)data.get("Genre"));
		textFields.get("Runtime").setText((String)data.get("Runtime"));
		textFields.get("Plot").setText((String)data.get("Plot"));
		screen.setPicture((byte[])data.get("Art"));
		screen.buildTextBox();
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
