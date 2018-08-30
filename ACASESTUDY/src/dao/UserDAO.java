package dao;

import java.sql.*;
import java.util.ArrayList;

import models.User;

public class UserDAO extends DbConnection
{
	private Connection con = this.getConnection();
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement ps;
	
	public boolean createUser(User user)
	{
		String query="INSERT INTO users(userid,username,password,firstname,middlename,lastname,role)"
				+ "VALUES ('"+user.getUserid()+"', '"+user.getUsername()+"','123','"+user.getFirstname()+"','"+user.getMiddlename()+"'"
						+ ",'"+user.getLastname()+"','"+user.getRole()+"')";
		try {
			stmt=con.createStatement();
			stmt.executeUpdate(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean deleteUser(String username)
	{
		String query = "DELETE FROM users WHERE username='"+username+"'";
		try{
			stmt=con.createStatement();
			stmt.executeUpdate(query);
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public ArrayList<User> getSpecificUser(String username)
	{
		ArrayList<User> user = new ArrayList<User>();
		String query = "SELECT * FROM users WHERE username='"+username+"'";
		try {
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next())
			{
				User user1 = new User();
				user1.setId(rs.getInt("id"));
				user1.setUserid(rs.getInt("userid"));
				user1.setUsername(rs.getString("username"));
				user1.setFirstname(rs.getString("firstname"));
				user1.setMiddlename(rs.getString("middlename"));
				user1.setLastname(rs.getString("lastname"));
				user1.setRole(rs.getString("role"));
				user.add(user1);
			}
			rs.close();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	public boolean updateUser(String id,String userid,String username,String firstname,String middlename,String lastname,String role)
	{
		String query="UPDATE users SET userid='"+userid+"', username='"+username+"', firstname='"+firstname+"'"
				+ ", middlename='"+middlename+"', lastname='"+lastname+"',role='"+role+"' WHERE id="+id+"";
		try {
			stmt=con.createStatement();
			stmt.executeUpdate(query);
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean changePassword(String userid,String oldpass, String newpass)
	{
		String query1 = "SELECT * FROM users WHERE userid='"+userid+"'and password='"+oldpass+"'";
		String query2 = "UPDATE users SET password='"+newpass+"' WHERE userid="+userid+"";
		
		try {
			ps=con.prepareStatement(query1);
			rs=ps.executeQuery();
			while(rs.next())
			{
				stmt=con.createStatement();
				stmt.executeUpdate(query2);
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean userExists(User user)
	{
		String query="SELECT * FROM users WHERE username='"+user.getUsername()+"' or userid='"+user.getUserid()+"'";
		try {
			ps=con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next())
			{
				return true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	public ArrayList<User> getAllUsers()
	{
		ArrayList<User> users = new ArrayList<User>();
		String query="SELECT * FROM users ORDER BY id DESC";
		try {
			
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next())
			{
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUserid(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));
				user.setFirstname(rs.getString("firstname"));
				user.setMiddlename(rs.getString("middlename"));
				user.setLastname(rs.getString("lastname"));
				user.setRole(rs.getString("role"));
				users.add(user);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
		
	}
}
