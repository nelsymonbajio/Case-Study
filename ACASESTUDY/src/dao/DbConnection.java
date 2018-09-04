package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class DbConnection 
{
	private Connection conn;

	protected Connection getConnection() {
		try 
		{
			Properties properties = new Properties();
			properties.load(getClass().getResourceAsStream("config.properties"));
			Class.forName(properties.getProperty("DB_DRIVER"));
			conn = DriverManager.getConnection(	properties.getProperty("DB_URL"),
					properties.getProperty("DB_USER"),
					properties.getProperty("DB_PASS"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conn;
	}
	protected void closeConnection()
	{
		try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
