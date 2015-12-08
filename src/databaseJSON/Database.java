package databaseJSON;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.*;
import java.text.ParseException;
import java.util.*;

import com.fasterxml.jackson.core.JsonParseException;

import tools.DateConversion;

public class Database {

	private static String[] types = {"Title", "Released", "imdbRating", "Director", "Genre", "Runtime", "Plot", "Poster"};
	private Statement stmt;
	private Connection c;
	private Statement connection() throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:test.db");
		stmt = c.createStatement();
		return stmt;
	}
	
	private PreparedStatement connectionP(String sql) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:test.db");
		c.setAutoCommit(false);
		return c.prepareStatement(sql);
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
	
	
	public void saveData(LinkedHashMap<String, Object> dataToSave) throws ClassNotFoundException, SQLException, ParseException
	{
		PreparedStatement pstmt = connectionP("INSERT INTO MOVIES (Title, Release, Review, Director"
				+ ",Genre, Runtime, Plot, Art)" 
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?);");
		
		
		pstmt.setString(1, (String)dataToSave.get("Title"));
		pstmt.setString(2,  DateConversion.parseDate((String)dataToSave.get("Released")));
		pstmt.setDouble(3, Double.valueOf((String)dataToSave.get("imdbRating")));
		pstmt.setString(4, (String)dataToSave.get("Director"));
		pstmt.setString(5, (String)dataToSave.get("Genre"));
		pstmt.setInt(6, Integer.valueOf(((String)dataToSave.get("Runtime"))
				.substring(0, (((String)dataToSave.get("Runtime")).indexOf('m') - 1))));
		pstmt.setString(7, (String)dataToSave.get("Plot"));
		pstmt.setBytes(8, (byte[])dataToSave.get("Art"));
		pstmt.executeUpdate();
		c.commit();
		c.close();
	}
	
	public void saveData(byte[] picture) throws ClassNotFoundException, SQLException
	{	
		PreparedStatement pstmt = connectionP("INSERT INTO Movies (Art) VALUES (?);");
		
		pstmt.setBytes(1,picture);
		pstmt.executeUpdate();
		c.commit();
		c.close();
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, JsonParseException, MalformedURLException, InstantiationException, IllegalAccessException, IOException, ParseException
	{
		Database test = new Database();
		test.saveData(new JacksonAPI().pullFromOMDB("Cobra"));
	}
}
