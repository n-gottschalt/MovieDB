package addWindow;

import java.awt.GridLayout;
import java.awt.event.*;
import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import javax.swing.*;

import menubar.OptionMenuBar;
import windows.MainWindow;
import windows.WindowBuilder;

public class AddWindowGUI extends WindowBuilder {
	
	private static final long serialVersionUID = 1L;
	private AddWindowTools tools;
	private JLabel pictureHolder;
	private String pictureLives;
	private String defaultPicture = rootProjectDirectory + "standin.png";
	private static String rootProjectDirectory = 
			MainWindow.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	private JPanel fullPanel;
	private JPanel buttonPanel;

	public AddWindowGUI(AddWindowTools tools) throws ClassNotFoundException, InstantiationException, 
		IllegalAccessException, IOException, UnsupportedLookAndFeelException
	{
		super(500, 600, "Add Window");
		this.tools = tools;
		pictureLives = defaultPicture;
		//store picture into our data set
		tools.getData().put("Art", AddWindowTools.convertPictureToByte(pictureLives));
		pictureHolder = pictureBuilder(pictureLives, 214, 317);
		super.setFrameClose(() -> close());
		//Buttons built here for addWindow box, when using modify, need seperate buttons, own method
		buttonBuilder();
		addInputFields();
		addToMenu();
		buildTextBox();
	}
	
	private void addInputFields()
	{
		for(String i : AddWindowTools.labels)
			tools.getTextFields().put(i, new JTextField());
		JTextArea plotTextArea = new JTextArea(4, 40);
		plotTextArea.setLineWrap(true);
		tools.getTextFields().put("Plot", plotTextArea);
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
		pictureLives = rootProjectDirectory + picture;
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
		buttonPanel.add(ok);

		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(x -> close());
		buttonPanel.add(cancel);
	}
	
	public void okAction()
	{
		try {
			tools.storeData();
			JOptionPane.showMessageDialog(getFrame(), "Movie added!");
			tools.clearData(defaultPicture);
		} catch (ClassNotFoundException | SQLException | ParseException | IOException e) {
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
			e.printStackTrace();
		} 
	}
	
	public void clear()
	{
		super.getFrame().remove(fullPanel);
	}
	
	public void buildTextBox() throws IOException
	{
		JPanel leftPanel = new JPanel(new GridLayout(6, 2));
		for(String i : AddWindowTools.getLabels())
		{
			leftPanel.add(new JLabel(i));
			leftPanel.add(tools.getTextFields().get(i));
		}
		
		JPanel rightPanel = new JPanel();
		pictureHolder.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				JFileChooser clickPicture = new JFileChooser();
				clickPicture.showOpenDialog(fullPanel);
				try {
					String pictureLocation = clickPicture.getSelectedFile().getAbsolutePath();
					tools.updatePictureData(pictureLocation, tools.currentDataInTextFields(pictureLocation));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (NullPointerException e2){} //No option was picked from File Chooser
			}
		});
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
	
	public String getPictureLive()
	{
		return pictureLives;
	}
}
