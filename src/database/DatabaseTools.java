package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseTools 
{
	public Connection conn=null;
    String connectionUrl =
            "jdbc:sqlserver://localhost:1433;"
            + "database=library;"
            + "user=root;"
            + "password=123456;";
        
    public Connection getConn()
     {
	     try 
	     {
	    	 conn = DriverManager.getConnection(connectionUrl);
	     }
	     // Handle any errors that may have occurred.
	     catch (SQLException e) 
	     {
	         e.printStackTrace();
	     }
	        
	     return conn;
     }
        
}