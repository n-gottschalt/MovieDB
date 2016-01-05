package addWindow;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.swing.*;

import databaseJSON.JacksonAPI;
import windows.WindowBuilder;

public class SearchMovieGUI extends WindowBuilder{
	
	private static final long serialVersionUID = -5206482244133103417L;
	private JPanel mainPanel = new JPanel();
	private JTextField nameField = new JTextField("", 25);
	private JButton button = new JButton("OK");
	private String[] options = {"Movie Name", "IMDB id"};
	private JComboBox<?> selector = new JComboBox<Object>(options);
	
	public SearchMovieGUI(AddWindowTools tool) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException 
	{
		super(400, 200, "Search");
		super.setFrameClose(() -> close());
		//enter
		nameField.addActionListener(x -> actionToDo(tool, nameField));
		
		//click
		button.addActionListener(x -> actionToDo(tool, nameField));
		
		mainPanel.add(selector);
		mainPanel.add(nameField);
		mainPanel.add(button);
		super.getFrame().add(mainPanel);
		super.make();
	}
	
	private void actionToDo(AddWindowTools tool, JTextField field)
	{
		try {
			tool.getWindowGUI().buttonBuilder();
			LinkedHashMap<String, Object> imdbData = JacksonAPI.pullFromOMDB(field.getText(), 
					selector.getSelectedIndex(), super.getFrame());
			if(!(imdbData.containsKey("ERROR")))
			{
				tool.insertInputFieldData(imdbData);
				super.close();
			}
			else
			{
				JOptionPane.showMessageDialog(super.getFrame(),
					"Unable to find movie. Please check the Spelling "
					+ "or try searching by imdb id.");
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException e1) {
			e1.printStackTrace();
		} 
	}
}
