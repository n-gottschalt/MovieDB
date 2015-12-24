package test;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.UnsupportedLookAndFeelException;

import addWindow.AddWindowGUI;
import windows.MainWindow;

public class LambdaTest {
	
	public LambdaTest(Lambda action) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, UnsupportedLookAndFeelException, SQLException
	{
		action.run(new Object());
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, UnsupportedLookAndFeelException, 
	SQLException
	{
		new LambdaTest(x -> new MainWindow());
		new LambdaTest(x -> new AddWindowGUI());
	}

}
