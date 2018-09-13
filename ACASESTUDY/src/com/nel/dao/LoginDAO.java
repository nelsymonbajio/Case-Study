package com.nel.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class LoginDAO extends DbConnection
{
	private Connection con;
	private Statement stmt = null;
	private PreparedStatement ps;
	private ResultSet rs;
	private boolean isAdmin = false;
	private boolean userExists = false;
	
	public void searchUser(String username, String password)
	{
		con = this.getConnection();
		
		String query="SELECT * FROM users WHERE username = ? AND password = ?";
		
		try {
			
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2,encryptData(password));
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				setUserExists(true);
				if(rs.getString("role").equalsIgnoreCase("Admin"))
				{
					setAdmin(true);
				}
			}
			rs.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeConnection(con);
		}
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isUserExists() {
		return userExists;
	}

	public void setUserExists(boolean userExists) {
		this.userExists = userExists;
	}
	public String encryptData(String data)
	{
		StringBuffer encrypted = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(data.getBytes());
			byte[] digest = md.digest();
			encrypted = new StringBuffer();
			for (byte b : digest) {
				encrypted.append(String.format("%02x", b & 0xff));
			}

		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		return encrypted.toString();
	}
}
