package addWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedHashMap;

import javax.swing.*;

import databaseJSON.JacksonAPI;
import windows.WindowBuilder;

public class SearchMovieGUI extends WindowBuilder{

	AddWindowGUI adder = new AddWindowGUI();
	LinkedHashMap<String, Object> test;
	public SearchMovieGUI() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		super(400, 200, "Search");
		JPanel mainPanel = new JPanel();
		JTextField field = new JTextField("", 25);
		JButton button = new JButton("OK");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try {
					adder.insertInputFieldData(JacksonAPI.pullFromOMDB(field.getText()));
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mainPanel.add(field);
		mainPanel.add(button);
		super.getFrame().add(mainPanel);
		super.make();
	}
	
	public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, UnsupportedLookAndFeelException
	{
		new SearchMovieGUI();
	}
}
