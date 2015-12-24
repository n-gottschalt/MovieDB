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

import org.apache.commons.io.FileUtils;
import org.apache.http.util.ByteArrayBuffer;

import databaseJSON.Database;

public class AddWindowTools {

	private HashMap<String, JTextField> textFields = new HashMap<>();
	private String[] labels = {"Title", "Release", "Rating", "Director", "Genre",
			"Runtime", "Plot"};
	
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
	
	public HashMap<String, JTextField> getTextFields()
	{
		return textFields;
	}
	
	public String[] getLabels()
	{
		return labels;
	}
}
