package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DbConnection 
{
	private Connection conn;
	private String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private String DB_CONNECTION = "jdbc:mysql://localhost:3306/casestudy";
	private String DB_USER = "root";
	private String DB_PASSWORD = "";
	
	public DbConnection() {
		try {
			Class.forName(DB_DRIVER);
			conn = (Connection) DriverManager.getConnection( DB_CONNECTION, DB_USER ,DB_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		
		return conn;
	}
}
