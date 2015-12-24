package addWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedHashMap;

import javax.swing.*;

import databaseJSON.JacksonAPI;
import windows.WindowBuilder;

public class SearchMovieGUI extends WindowBuilder{

	LinkedHashMap<String, Object> test;
	public SearchMovieGUI(AddWindowTools tool) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException,
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
					tool.insertInputFieldData(JacksonAPI.pullFromOMDB(field.getText()));
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		mainPanel.add(field);
		mainPanel.add(button);
		super.getFrame().add(mainPanel);
		super.make();
	}
}
