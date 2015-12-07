package databaseJSON;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;

import test.WindowBuilder;

public class Database {

	private static Statement stmt;
	private static Connection c;
	public static Statement connection() throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:test.db");
		stmt = c.createStatement();
		return stmt;
	}
	
	public static ArrayList<LinkedHashMap<String, Object>> getData() throws ClassNotFoundException, SQLException
	{
		ResultSet rs = connection().executeQuery("SELECT * FROM movies");
		
		ArrayList<LinkedHashMap<String, Object>> dataToReturn = new ArrayList<LinkedHashMap<String, Object>>();
		
		int i = 0;
		while(rs.next())
		{
			dataToReturn.add(new LinkedHashMap<String, Object>());
			dataToReturn.get(i).put("MovieID", rs.getInt("MovieID"));
			dataToReturn.get(i).put("Title", rs.getString("Title"));
			dataToReturn.get(i).put("Release", rs.getString("Release"));
			dataToReturn.get(i).put("Review", rs.getLong("Review"));
			dataToReturn.get(i).put("Director", rs.getString("Director"));
			dataToReturn.get(i).put("Genre", rs.getString("Genre"));
			dataToReturn.get(i).put("Runtime", rs.getString("Runtime"));
			dataToReturn.get(i).put("Plot", rs.getString("Plot"));
			dataToReturn.get(i).put("Art", rs.getBytes("Art"));
			i++;
		}
		rs.close();
		connection().close();
		c.close();
		return dataToReturn;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException, UnsupportedLookAndFeelException
	{
		ArrayList<LinkedHashMap<String, Object>> test = getData();
		
		WindowBuilder newWindow = new WindowBuilder(400, 400, "TEST");
		JPanel panel1 = new JPanel();
		Image img = Toolkit.getDefaultToolkit().createImage((byte [])test.get(0).get("Art"));
		ImageIcon icon = new ImageIcon(img);
		JLabel lPhoto = new JLabel();
		lPhoto.setIcon(icon);
		panel1.add(lPhoto);
		newWindow.mainFrame.add(panel1);
		newWindow.make();
			
	}
}
