package addWindow;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.util.ByteArrayBuffer;

import databaseJSON.Database;
import menubar.OptionMenuBar;
import windows.WindowBuilder;

public class TestAddWindow extends WindowBuilder {

	private HashMap<String, JTextField> textFields = new HashMap<>();
	private String[] labels = {"Title", "Release", "Rating", "Director", "Genre",
			"Runtime", "Plot"};
	
	public TestAddWindow() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, UnsupportedLookAndFeelException
	{
		super(500, 500, "Add Window");
		addInputFields();
		addToMenu();
		buildTextBox();
	}
	
	private void addInputFields()
	{
		for(String i : labels)
			textFields.put(i, new JTextField());
	}
	
	private void addToMenu()
	{
		ArrayList<OptionMenuBar> menuItems = new ArrayList<>();
		menuItems.add(new OptionMenuBar("Search"));
		menuItems.get(0).add("Search", 1);
		super.getFrame().setJMenuBar(menuBarBuilder(menuItems));
	}

	private void storeData() throws ClassNotFoundException, SQLException, ParseException, IOException
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
		
			InputStream is = FileUtils.openInputStream(new File("C:\\Users\\ngott_000\\git\\MovieDB\\standin.png"));
			BufferedInputStream bis = new BufferedInputStream(is);
			
			ByteArrayBuffer baf = new ByteArrayBuffer(500);
			int current = 0;
			while((current = bis.read()) != -1)
				baf.append((byte) current);
			
		dataToStore.put("Art", baf.toByteArray());
		
		storeData.saveData(dataToStore);
	}
	
	
	private void buildTextBox() throws IOException
	{
		JPanel leftPanel = new JPanel();
		JPanel fullPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		JButton ok = new JButton("Ok");
		ok.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					storeData();
				} catch (ClassNotFoundException | SQLException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		
		
		
		JButton cancel = new JButton("Cancel");
		
		fullPanel.setLayout(new GridLayout(1, 2));
		leftPanel.setLayout(new GridLayout(7, 2));
		rightPanel.setLayout(new GridLayout(2, 1));
		
		for(String i : labels)
		{
			leftPanel.add(new JLabel(i));
			leftPanel.add(textFields.get(i));
		}
		
		buttonPanel.add(ok);
		buttonPanel.add(cancel);

		rightPanel.add(pictureBuilder("C:\\Users\\ngott_000\\git\\MovieDB\\standin.png", 214, 317));
		rightPanel.add(buttonPanel);
		
		fullPanel.add(leftPanel);
		fullPanel.add(rightPanel);
		super.getFrame().add(fullPanel);
		super.make();
	}
	
	public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, UnsupportedLookAndFeelException
	{
		TestAddWindow test = new TestAddWindow();
	}
	

}
