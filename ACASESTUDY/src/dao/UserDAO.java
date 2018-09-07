package dao;

import java.sql.*;
import java.util.ArrayList;

import models.User;

public class UserDAO extends DbConnection
{
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement ps;

	public boolean createUser(User user)
	{
		con = this.getConnection();
		String query="INSERT INTO users(userid,username,password,firstname,middlename,lastname,role,createPriv,deletePriv,updatePriv)"
				+ "VALUES ('"+user.getUserid()+"', '"+user.getUsername()+"','123','"+user.getFirstname()+"','"+user.getMiddlename()+"'"
				+ ",'"+user.getLastname()+"','"+user.getRole()+"','"+(user.isCanCreate()? 1 : 0)+"','"+(user.isCanDelete()? 1 : 0)+"','"+(user.isCanUpdate()? 1 : 0)+"')";
		try {
			stmt=con.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.closeConnection();
		}
		return false;
	}
	public boolean deleteUser(String username)
	{
		con = this.getConnection();
		String query = "DELETE FROM users WHERE username='"+username+"'";
		try{
			stmt=con.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.closeConnection();
		}
		return false;
	}
	public ArrayList<User> getSpecificUser(String username)
	{
		con = this.getConnection();
		
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
				user1.setCanCreate(rs.getBoolean("createPriv"));
				user1.setCanUpdate(rs.getBoolean("updatePriv"));
				user1.setCanDelete(rs.getBoolean("deletePriv"));
				user.add(user1);
			}
			rs.close();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.closeConnection();
		}
		return user;
	}
	public boolean updateUser(String id,String userid,String username,String firstname,String middlename,String lastname,String role,
			String create,String update,String delete)
	{
		con = this.getConnection();
		int c=0, u=0, d=0;
		
		if(role.equalsIgnoreCase("admin"))
		{
			c=1;
			u=1;
			d=1;
		}else {
			if(create!=null)
				c=1;
			if(update!=null)
				u=1;
			if(delete!=null)
				d=1;
		}


		String query="UPDATE users SET userid='"+userid+"', username='"+username+"', firstname='"+firstname+"'"
				+ ", middlename='"+middlename+"', lastname='"+lastname+"',role='"+role+"',createPriv="+c+", updatePriv="+u+",deletePriv="+d+" WHERE id="+id+"";
		try {
			stmt=con.createStatement();
			stmt.executeUpdate(query);
			
			stmt.close();
			
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.closeConnection();
		}
		return false;
	}
	public boolean changePassword(String userid,String oldpass, String newpass)
	{
		con = this.getConnection();
		String query1 = "SELECT * FROM users WHERE userid='"+userid+"'and password='"+oldpass+"'";
		String query2 = "UPDATE users SET password='"+newpass+"' WHERE userid="+userid+"";

		try {
			ps=con.prepareStatement(query1);
			rs=ps.executeQuery();
			while(rs.next())
			{
				stmt=con.createStatement();
				stmt.executeUpdate(query2);
				
				stmt.close();
				
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.closeConnection();
		}
		return false;
	}
	public boolean userExists(User user)
	{
		con = this.getConnection();
		String query="SELECT * FROM users WHERE username='"+user.getUsername()+"' or userid='"+user.getUserid()+"'";
		try {
			ps=con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next())
			{
				return true;
			}
			ps.close();
			rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			this.closeConnection();
		}
		return false;
	}
	public ArrayList<User> getAllUsers()
	{
		con = this.getConnection();
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
				user.setCanCreate(rs.getBoolean("createPriv"));
				user.setCanUpdate(rs.getBoolean("updatePriv"));
				user.setCanDelete(rs.getBoolean("deletePriv"));
				users.add(user);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.closeConnection();
		}
		return users;

	}
}
