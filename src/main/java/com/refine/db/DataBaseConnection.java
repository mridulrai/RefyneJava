package com.refine.db;
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException; 


public class DataBaseConnection {
	
	public static Connection initializeDatabase() 
	        throws SQLException, ClassNotFoundException 
	    { 
	        // Initialize all the information regarding 
	        // Database Connection			
	        String dbDriver = "com.mysql.cj.jdbc.Driver"; 
	        String dbURL = "jdbc:mysql:// localhost:3306/"; 
	        String dbName = "world"; 
	        String dbUsername = "root"; 
	        String dbPassword = "12345678"; 
	  
	        Class.forName(dbDriver);	        
	        Connection con = DriverManager.getConnection(dbURL + dbName, 
	                                                     dbUsername,  
	                                                     dbPassword); 
	        return con; 
	    } 	

}
