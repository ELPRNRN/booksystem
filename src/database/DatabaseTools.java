package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTools {
    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public static void main(String[] args) {
        String connectionUrl =
                "jdbc:sqlserver://localhost:1433;"
                        + "database=university;"
                        + "user=root;"
                        + "password=123456;";

        try (Connection con = DriverManager.getConnection(connectionUrl);Statement stmt = con.createStatement();) 
        {
        	 String SQL = "select * from department";
             ResultSet rs = stmt.executeQuery(SQL);

             // Iterate through the data in the result set and display it.
             while (rs.next()) {
                 System.out.println(rs.getString("dept_name") + " " + rs.getString("building"));
        }
             
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
}