package test;

import java.io.*;
import java.sql.*;

public class DBAdapter {
	
	
	// HOW TO GET DATA TO THE DATABASE
	// Table is already made with this SCHEMA:
	//CREATE TABLE 'imagetable'( name TEXT, image BLOB);
	public static void main(String[] args) throws IOException, SQLException
	{
		Connection conn = null;
		FileInputStream fis = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("Opened Database Successfully");
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO imagetable (name, image) VALUES (?, ?);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "Picture");
			
			// get picture bits
			File image = new File("C:\\Users\\Nathanial\\Desktop\\test.png");
			fis = new FileInputStream(image);
			stmt.setBinaryStream(2, fis, (int) image.length());
			stmt.executeUpdate();
			
			conn.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				fis.close();
			if (conn != null && !conn.isClosed()) 
				conn.close();
		}
	}
}