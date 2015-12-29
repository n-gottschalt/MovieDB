package addWindow;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	public JLabel pictureHolder = pictureBuilder("standin.png", 214, 317);
	private JPanel fullPanel;

	public AddWindowGUI(AddWindowTools tools) throws ClassNotFoundException, InstantiationException, 
		IllegalAccessException, IOException, UnsupportedLookAndFeelException
	{
		super(500, 500, "Add Window");
		this.tools = tools;
		addInputFields();
		addToMenu();
		buildTextBox();
	}
	
	private void addInputFields()
	{
		for(String i : AddWindowTools.labels)
			tools.getTextFields().put(i, new JTextField());
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
	
	public JFrame getFrame()
	{
		return super.getFrame();
	}
	
	public JPanel rightPanelBuilder()
	{
		JPanel buttonPanel = new JPanel();
		JButton ok = new JButton("Ok");
		ok.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					tools.storeData();
					JOptionPane.showMessageDialog(getFrame(), "Movie added!");
					tools.clearData("standin.png");
				} catch (ClassNotFoundException | SQLException | ParseException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					close();
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		buttonPanel.add(ok);
		buttonPanel.add(cancel);
		return buttonPanel;
	}

	public void close() throws ClassNotFoundException, IOException, SQLException
	{
		super.getFrame().setVisible(false);
		super.getFrame().dispose();
		tools.getMainWindow().clear();
		tools.getMainWindow().buildWindow(); 
	}
	
	public void clear()
	{
		super.getFrame().remove(fullPanel);
	}
	
	public void buildTextBox() throws IOException
	{
		JPanel leftPanel = new JPanel();
		fullPanel = new JPanel();
		JPanel rightPanel = new JPanel();

		fullPanel.setLayout(new GridLayout(1, 2));
		leftPanel.setLayout(new GridLayout(7, 2));
		rightPanel.setLayout(new GridLayout(2, 1));
		
		for(String i : AddWindowTools.getLabels())
		{
			leftPanel.add(new JLabel(i));
			leftPanel.add(tools.getTextFields().get(i));
		}

		rightPanel.add(pictureHolder);
		rightPanel.add(rightPanelBuilder());
		
		fullPanel.add(leftPanel);
		fullPanel.add(rightPanel);
		super.getFrame().add(fullPanel);
		super.make();
	}
	
}
