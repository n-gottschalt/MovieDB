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

public class AddWindowGUI extends WindowBuilder {

	AddWindowTools tools;
	public AddWindowGUI(AddWindowTools tools) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, UnsupportedLookAndFeelException
	{
		super(500, 500, "Add Window");
		this.tools = tools;
		addInputFields();
		addToMenu();
		buildTextBox();
		System.out.println(tools.getClass());
	}
	
	private void addInputFields()
	{
		for(String i : tools.labels)
			tools.getTextFields().put(i, new JTextField());
	}
	
	private void addToMenu()
	{
		ArrayList<OptionMenuBar> menuItems = new ArrayList<>();
		menuItems.add(new OptionMenuBar("Search"));
		menuItems.get(0).add("Search", x -> new SearchMovieGUI(tools));
		super.getFrame().setJMenuBar(menuBarBuilder(menuItems));
	}
	
	public void reDrawScreen()
	{
		repaint();
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
					tools.storeData();
				} catch (ClassNotFoundException | SQLException | ParseException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton cancel = new JButton("Cancel");
		
		fullPanel.setLayout(new GridLayout(1, 2));
		leftPanel.setLayout(new GridLayout(7, 2));
		rightPanel.setLayout(new GridLayout(2, 1));
		
		for(String i : tools.getLabels())
		{
			leftPanel.add(new JLabel(i));
			leftPanel.add(tools.getTextFields().get(i));
		}
		
		buttonPanel.add(ok);
		buttonPanel.add(cancel);

		rightPanel.add(pictureBuilder("standin.png", 214, 317));
		rightPanel.add(buttonPanel);
		
		fullPanel.add(leftPanel);
		fullPanel.add(rightPanel);
		super.getFrame().add(fullPanel);
		super.make();
	}
	
}
