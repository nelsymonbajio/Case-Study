package dao;

import java.sql.*;

public class LoginDAO extends DbConnection
{
	private Connection con = this.getConnection();
	private Statement stmt = null;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private boolean isAdmin = false;
	private boolean userExists = false;
	
	public void searchUser(String username, String password)
	{
		String query="SELECT * FROM users WHERE username='"+username+"' AND password='"+password+"'";
		
		try {
			ps = con.prepareStatement(query);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
}
