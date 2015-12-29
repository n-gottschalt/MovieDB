package databaseJSON;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class DatabaseTest {

	@Test
	public void testGetData() throws ClassNotFoundException, SQLException {
		assertEquals("Top Gun", new Database().getData().get(1).get("Title"));
	}
	
	@Test
	public void testGetDataString() throws ClassNotFoundException, SQLException
	{
		assertEquals("Inside Out", new Database().getData("Inside Out", "Title").get("Title"));
	}


}
