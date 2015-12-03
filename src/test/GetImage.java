package test;

import java.io.*;
import java.sql.*;

public class GetImage {
	
	//gets image from a table "imagetable" in the test database

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException
	{
		Class.forName("org.sqlite.JDBC");
		
		Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
		
		String sql = "SELECT name, image FROM imagetable;";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet resultSet = stmt.executeQuery();
		while (resultSet.next())
		{
			String name = resultSet.getString(1);
			
			//location to save to
			File image = new File("C:\\Users\\Nathanial\\workspace\\test1.png");
			FileOutputStream fos = new FileOutputStream(image);
			
			byte[] buffer = new byte[1];
			
			//the 2 is for the 2nd result from the resultSet array, aka the image
			//will need to change on production version
			InputStream is = resultSet.getBinaryStream(2);
			while(is.read(buffer) > 0)
				fos.write(buffer);
		fos.close();
		}
	conn.close();
	}
}
