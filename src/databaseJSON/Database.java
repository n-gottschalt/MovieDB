package databaseJSON;

import java.sql.*;
import java.text.ParseException;
import java.util.*;

import addWindow.AddWindowTools;
import tools.DateConversion;

public class Database {

	private Statement stmt;
	private Connection conn;
	private Statement connection() throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:test.db");
		stmt = conn.createStatement();
		return stmt;
	}
	
	private PreparedStatement connectionPrepared(String sql) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:test.db");
		conn.setAutoCommit(false);
		return conn.prepareStatement(sql);
	}
	
	private ArrayList<LinkedHashMap<String, Object>> parseData(ResultSet rs) throws 
		SQLException, ClassNotFoundException 
	{
		ArrayList<LinkedHashMap<String, Object>> dataToParse = new ArrayList<LinkedHashMap<String, Object>>();
		int i = 0;
		while(rs.next())
		{
			AddWindowTools.getLabels();
			dataToParse.add(new LinkedHashMap<String, Object>());
			for(String objectTitles : AddWindowTools.getLabels())
				dataToParse.get(i).put(objectTitles, rs.getString(objectTitles));
			dataToParse.get(i).put("Plot", rs.getString("Plot"));
			dataToParse.get(i).put("Art", rs.getBytes("Art"));
			dataToParse.get(i).put("MovieID", rs.getString("MovieID"));
			i++;
		}
		rs.close();
		connection().close();
		conn.close();
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
	
	public LinkedHashMap<String, Object> getData(int valueToLookFor,
			String typeOfData) throws ClassNotFoundException, SQLException
	{
		ResultSet rs = connection().executeQuery("SELECT * FROM movies WHERE " + typeOfData + " = " + valueToLookFor + ";" );
		return parseData(rs).get(0);
	}
	
	public void saveData(LinkedHashMap<String, Object> dataToSave) throws ClassNotFoundException, SQLException, ParseException
	{
		PreparedStatement pstmt = connectionPrepared("INSERT INTO MOVIES (Title, Released, Rating, Director"
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
		conn.commit();
		conn.close();
	}
	
	public void removeData(String movieTitle) throws ClassNotFoundException, SQLException
	{
		PreparedStatement pstmt = connectionPrepared("DELETE FROM MOVIES "
				+ "WHERE Title=\'" + movieTitle + "\';");
		pstmt.executeUpdate();
		conn.commit();
		conn.close();
	}
	
	public void modifyData(LinkedHashMap<String, Object> dataToSave, int movieID) 
			throws ClassNotFoundException, SQLException, ParseException
	{
		PreparedStatement pstmt = connectionPrepared("UPDATE MOVIES"
				+ " SET Title=?, Released=?, Rating=?, Director=?,"
				+ " Genre=?, Runtime=?, Plot=?, Art=?"
				+ " WHERE MovieID=" + movieID + ";");
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
		conn.commit();
		conn.close();
	}
	
	public void deleteData(int movieID)
	{
		PreparedStatement pstmt = "DELETE FROM MOVIES"
				+ " "
	}
}
