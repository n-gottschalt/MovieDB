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
	
	private static ArrayList<LinkedHashMap<String, Object>> parseData(ResultSet rs) throws SQLException, ClassNotFoundException 
	{
		ArrayList<LinkedHashMap<String, Object>> dataToParse = new ArrayList<LinkedHashMap<String, Object>>();
		int i = 0;
		while(rs.next())
		{
			dataToParse.add(new LinkedHashMap<String, Object>());
			dataToParse.get(i).put("MovieID", rs.getInt("MovieID"));
			dataToParse.get(i).put("Title", rs.getString("Title"));
			dataToParse.get(i).put("Release", rs.getString("Release"));
			dataToParse.get(i).put("Review", rs.getLong("Review"));
			dataToParse.get(i).put("Director", rs.getString("Director"));
			dataToParse.get(i).put("Genre", rs.getString("Genre"));
			dataToParse.get(i).put("Runtime", rs.getString("Runtime"));
			dataToParse.get(i).put("Plot", rs.getString("Plot"));
			dataToParse.get(i).put("Art", rs.getBytes("Art"));
			i++;
		}
		rs.close();
		connection().close();
		c.close();
		return dataToParse;
	}
	
	public static ArrayList<LinkedHashMap<String, Object>> getData() 
			throws ClassNotFoundException, SQLException
	{
		ResultSet rs = connection().executeQuery("SELECT * FROM movies");
		return parseData(rs);
	}
	
	public static LinkedHashMap<String, Object> getData(String valueToLookFor,
			String typeOfData) throws ClassNotFoundException, SQLException
	{
		ResultSet rs = connection().executeQuery("SELECT * FROM movies WHERE " + typeOfData + " = \"" + valueToLookFor +"\"");
		return parseData(rs).get(0);
	}
	
	public static boolean storeData(LinkedHashMap<String, Object> valuesToStore)
	{
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException, UnsupportedLookAndFeelException
	{
		MainWindow test = new MainWindow();
		test.mainFrame.add(test.buildMoviePanel(getData()));
		test.make();
	}
}
