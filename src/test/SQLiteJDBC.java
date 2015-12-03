package test;

import java.sql.*;

public class SQLiteJDBC {

	public static void main(String[] args)
	{
		Statement stmt = null;
		Connection c = null;
		try
		{
			/*
			//creates our object
			Class.forName("org.sqlite.JDBC");
			
			//test.db can be changed to WHATEVER
			//saves in root of this directory
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("Opened database Successfully");
			
			stmt = c.createStatement();
			
			 Create a table
			String sql = "CREATE TABLE COMPANY " +
						"(ID INT PRIMARY KEY	NOT NULL," +
						"NAME			 TEXT	NOT NULL," +
						"AGE			 INT	NOT NULL," +
						"ADDRESS		 CHAR(50)," +
						"SALARY			 REAL)";
			
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
			*/
			//closing for proper saving
			
			
			/* Insert commands
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			
			stmt = c.createStatement();
			String sql = "INSERT INTO COMPANY (ID, NAME, AGE, ADDRESS, SALARY) " +
					"VALUES (1, 'Paul', 32, 'California', 20000.00);";
			stmt.execute(sql);
			
			sql = "INSERT INTO COMPANY (ID, NAME, AGE, ADDRESS, SALARY) " +
					"VALUES (2, 'Allen', 25, 'Texas', 15000.00);";
			stmt.execute(sql);
			
			sql = "INSERT INTO COMPANY (ID, NAME, AGE, ADDRESS, SALARY) " +
					"VALUES (3, 'Teddy', 23, 'Norway', 20000.00);";
			stmt.execute(sql);
			
			sql = "INSERT INTO COMPANY (ID, NAME, AGE, ADDRESS, SALARY) " + 
					"VALUES (4, 'MARK', 25, 'Rich-Mond', 65000.00);";
			stmt.execute(sql);
			
			stmt.close();
			//think this can prevent writing till data is ready
			c.commit();
			c.close();
			*/
		
		}catch (Exception e)
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}
}
