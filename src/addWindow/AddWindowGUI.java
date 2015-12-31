package addWindow;

import java.awt.GridLayout;
import java.awt.event.*;
import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import javax.swing.*;

import menubar.OptionMenuBar;
import windows.WindowBuilder;

public class AddWindowGUI extends WindowBuilder {
	
	private static final long serialVersionUID = 1L;
	public AddWindowTools tools;
	public JLabel pictureHolder;
	private JPanel fullPanel;
	private JPanel buttonPanel;

	public AddWindowGUI(AddWindowTools tools) throws ClassNotFoundException, InstantiationException, 
		IllegalAccessException, IOException, UnsupportedLookAndFeelException
	{
		super(500, 600, "Add Window");
		this.tools = tools;
		pictureHolder = pictureBuilder("standin.png", 214, 317);
		
		buttonBuilder();
		addInputFields();
		addToMenu();
		buildTextBox();
	}
	
	private void addInputFields()
	{
		for(String i : AddWindowTools.labels)
			tools.getTextFields().put(i, new JTextField());
		JTextArea temp = new JTextArea(4, 40);
		temp.setLineWrap(true);
		tools.getTextFields().put("Plot", temp);
	}
	
	private void addToMenu()
	{
		ArrayList<OptionMenuBar> menuItems = new ArrayList<>();
		menuItems.add(new OptionMenuBar("Search"));
		menuItems.get(0).add("Search", x -> new SearchMovieGUI(tools));
		super.getFrame().setJMenuBar(menuBarBuilder(menuItems));
	}
	
	public void setPicture(String picture) throws IOException
	{
		pictureHolder = pictureBuilder(picture, 214, 317);
	}
	
	public void setPicture(byte[] picture)
	{
		pictureHolder = pictureBuilder(picture, 214, 317);
	}

	public void buttonBuilder()
	{
		buttonPanel = new JPanel();
		JButton ok = new JButton("Ok");
		ok.addActionListener(x -> okAction());

		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(x -> close());
		
		buttonPanel.add(ok);
		buttonPanel.add(cancel);
	}
	
	public void okAction()
	{
		try {
			tools.storeData();
			JOptionPane.showMessageDialog(getFrame(), "Movie added!");
			tools.clearData("standin.png");
		} catch (ClassNotFoundException | SQLException | ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close() 
	{
		try {
			super.getFrame().setVisible(false);
			super.getFrame().dispose();
			tools.getMainWindow().clear();
			tools.getMainWindow().buildWindow();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void clear()
	{
		super.getFrame().remove(fullPanel);
	}
	
	public void buildTextBox() throws IOException
	{
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(6, 2));
		for(String i : AddWindowTools.getLabels())
		{
			if(!(i.equals("Plot")))
			{
				leftPanel.add(new JLabel(i));
				leftPanel.add(tools.getTextFields().get(i));
			}
		}
		
		JPanel rightPanel = new JPanel();
		rightPanel.add(pictureHolder);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.add(new JLabel("Plot"));
		bottomPanel.add(tools.getTextFields().get("Plot"));
		bottomPanel.add(new JPanel().add(buttonPanel));
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1, 2));
		topPanel.add(leftPanel);
		topPanel.add(rightPanel);
	
		fullPanel = new JPanel();
		fullPanel.setLayout(new GridLayout(2, 1));
		fullPanel.add(topPanel);
		fullPanel.add(bottomPanel);

		super.getFrame().add(fullPanel);
		super.make();
	}
	
	public void setButtonPanel(JPanel buttonPanel)
	{
		this.buttonPanel = buttonPanel;
	}
	
	public JFrame getFrame()
	{
		return super.getFrame();
	}
	
}
