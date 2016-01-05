package addWindow;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.JTextComponent;

import databaseJSON.Database;
import windows.MainWindow;

public class AddWindowTools {

	private HashMap<String, JTextComponent> textFields = new HashMap<>();
	private MainWindow main;
	public static String[] labels = {"Title", "Released", "Rating", 
			"Director", "Genre", "Runtime"};
	
	LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
	
	AddWindowGUI screen;
	
	public AddWindowTools(MainWindow main)
	{
		this.main = main;
	}
 
	public void run() throws ClassNotFoundException, 
	InstantiationException, IllegalAccessException, IOException, UnsupportedLookAndFeelException
	{
		screen = new AddWindowGUI(this);
	}
	
	public void storeData() throws ClassNotFoundException, SQLException, ParseException, IOException
	{
		new Database().saveData(currentDataInTextFields());
	}
	
	public LinkedHashMap<String, Object> currentDataInTextFields()
	{
		LinkedHashMap<String, Object> dataToStore = getDataFromTextFields();
		dataToStore.put("Art", (byte[])data.get("Art"));
		
		return dataToStore;
	}
	
	public LinkedHashMap<String, Object> currentDataInTextFields(String wherePictureLives) 
			throws IOException
	{
		LinkedHashMap<String, Object> dataToStore = getDataFromTextFields();
		dataToStore.put("Art", convertPictureToByte(wherePictureLives));
			
		return dataToStore;
	}
	
	private LinkedHashMap<String, Object> getDataFromTextFields()
	{
		LinkedHashMap<String, Object> dataToStore = new LinkedHashMap<>();
		dataToStore.put("Title", textFields.get("Title").getText());
		dataToStore.put("Released", textFields.get("Released").getText());
		dataToStore.put("imdbRating", textFields.get("Rating").getText());
		dataToStore.put("Director", textFields.get("Director").getText());
		dataToStore.put("Genre", textFields.get("Genre").getText());
		dataToStore.put("Runtime", textFields.get("Runtime").getText());
		dataToStore.put("Plot", textFields.get("Plot").getText());
		return dataToStore;
	}
	
	public void clearData(byte[] picture) throws IOException
	{
		screen.clear();
		screen.setPicture(picture);
		for(String i : labels)
			textFields.get(i).setText("");
		screen.buildTextBox();
	}
	
	public void clearData(String picture) throws IOException
	{
		screen.clear();
		screen.setPicture(picture);
		for(String i : labels)
			textFields.get(i).setText("");
		textFields.get("Plot").setText("");
		screen.buildTextBox();
	}
	
	public void clearData(URL picture) throws IOException
	{
		screen.clear();
		screen.setPicture(picture);
		for(String i : labels)
			textFields.get(i).setText("");
		textFields.get("Plot").setText("");
		screen.buildTextBox();
	}
	
	public void insertInputFieldData(LinkedHashMap<String, Object> data) throws IOException
	{
		this.data = data;
		clearData((byte[])data.get("Art"));
		setTextFields();
	}
	
	public void updatePictureData(String picture, LinkedHashMap<String, Object> data) throws IOException
	{
		this.data = data;
		clearData(picture);
		setTextFields();
	}
	
	private void setTextFields()
	{
		textFields.get("Title").setText((String)data.get("Title"));
		textFields.get("Released").setText((String)data.get("Released"));
		if(data.containsKey("imdbRating"))
			textFields.get("Rating").setText((String)data.get("imdbRating"));
		else
			textFields.get("Rating").setText((String)data.get("Rating"));
		textFields.get("Director").setText((String)data.get("Director"));
		textFields.get("Genre").setText((String)data.get("Genre"));
		textFields.get("Runtime").setText((String)data.get("Runtime"));
		textFields.get("Plot").setText((String)data.get("Plot"));
	}
	
	public static byte[] convertPictureToByte(String wherePictureLives) throws IOException
	{
		BufferedImage image = ImageIO.read(new File(wherePictureLives));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "png", baos);
		baos.flush();
		byte[] imageInByte = baos.toByteArray();
		baos.close();
		return imageInByte;
	}
	
	public HashMap<String, JTextComponent> getTextFields()
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
	
	public AddWindowGUI getWindowGUI()
	{
		return screen;
	}
	
	public AddWindowTools getTools()
	{
		return this;
	}
	
	public LinkedHashMap<String, Object> getData()
	{
		return data;
	}
}