package databaseJSON;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;

import test.MainWindow;

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
	
	public static LinkedHashMap<String, Object> getData(String valueToLookFor)
	{
		Result rs = connection().executeQuery()
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException, UnsupportedLookAndFeelException
	{
		MainWindow test = new MainWindow();
		test.mainFrame.add(test.buildMoviePanel(getData()));
		test.make();
	}
}
