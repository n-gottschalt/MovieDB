package databaseJSON;

import java.sql.*;
import java.util.*;

public class Database {

	private Statement stmt;
	private Connection c;
	private Statement connection() throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:test.db");
		stmt = c.createStatement();
		return stmt;
	}
	
	private ArrayList<LinkedHashMap<String, Object>> parseData(ResultSet rs) throws SQLException, ClassNotFoundException 
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
	
	public ArrayList<LinkedHashMap<String, Object>> getData() 
			throws ClassNotFoundException, SQLException
	{
		ResultSet rs = connection().executeQuery("SELECT * FROM movies");
		return parseData(rs);
	}
	
	public LinkedHashMap<String, Object> getData(String valueToLookFor,
			String typeOfData) throws ClassNotFoundException, SQLException
	{
		ResultSet rs = connection().executeQuery("SELECT * FROM movies WHERE " + typeOfData + " = \"" + valueToLookFor +"\"");
		return parseData(rs).get(0);
	}
	
	public void saveData(String title) throws ClassNotFoundException, SQLException
	{	
		String sql = "INSERT INTO Movies (Title) VALUES(\"" + title + "\")";
		connection().execute(sql);
		stmt.close();
		c.close();	
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		Database test = new Database();
		test.saveData("Poland");
	}
}
