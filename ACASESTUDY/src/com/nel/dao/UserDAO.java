package com.nel.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

import com.nel.models.User;

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
				+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
		try {
			ps=con.prepareStatement(query);
			ps.setInt(1, user.getUserid());
			ps.setString(2, user.getUsername());
			ps.setString(3, encryptData("123"));
			ps.setString(4, user.getFirstname());
			ps.setString(5, user.getMiddlename());
			ps.setString(6, user.getLastname());
			ps.setString(7, user.getRole());
			ps.setInt(8, user.isCanCreate()? 1 : 0);
			ps.setInt(9, user.isCanDelete()? 1 : 0);
			ps.setInt(10, user.isCanUpdate()? 1 : 0);

			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				if(rs!=null);
					rs.close();
				if(ps!=null)
					ps.close();
				if(con!=null)
					this.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public boolean deleteUser(String username)
	{
		con = this.getConnection();
		String query = "DELETE FROM users WHERE username = ?";
		try{
			ps=con.prepareStatement(query);
			ps.setString(1, username);
			ps.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				if(ps!=null)
					ps.close();
				if(con!=null)
					this.closeConnection(con);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public ArrayList<User> getSpecificUser(String username)
	{
		con = this.getConnection();

		ArrayList<User> user = new ArrayList<User>();
		String query = "SELECT * FROM users WHERE username=?";
		try {
			ps=con.prepareStatement(query);
			ps.setString(1, username);
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
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				if(con!=null)
					this.closeConnection(con);
				if(rs!=null);
				rs.close();
				if(ps!=null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	public boolean updateUser(String id,String userid,String username,String firstname,String middlename,String lastname,String role,
			String create,String update,String delete)
	{
		con = this.getConnection();
		int c=0, u=0, d=0;
		//set privileges
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

		String query="UPDATE users SET userid= ? , username= ?, firstname=?, middlename= ?, "
				+ "lastname= ?,role= ?,createPriv= ?, updatePriv= ?, deletePriv= ? WHERE id= ?";
		try {
			ps=con.prepareStatement(query);
			ps.setString(1, userid);
			ps.setString(2, username);
			ps.setString(3, firstname);
			ps.setString(4, middlename);
			ps.setString(5, lastname);
			ps.setString(6, role);
			ps.setInt(7, c);
			ps.setInt(8, u);
			ps.setInt(9, d);
			ps.setString(10, id);
			ps.executeUpdate();

			return true;

		}catch(SQLIntegrityConstraintViolationException f) {
			return false;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				if(ps!=null)
					ps.close();
				if(con!=null)
					this.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public boolean changePassword(String userid,String oldpass, String newpass)
	{
		con = this.getConnection();

		String query1 = "SELECT * FROM users WHERE userid= ? and password = ?";
		String query2 = "UPDATE users SET password='"+encryptData(newpass)+"' WHERE userid="+userid+"";

		try {
			ps=con.prepareStatement(query1);
			ps.setString(1, userid);
			ps.setString(2, encryptData(oldpass));
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
		try {
			if(stmt!=null)
				stmt.close();
			if(rs!=null);
			rs.close();
			if(ps!=null)
				ps.close();
			if(con!=null)
				this.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean userExists(User user)
	{
		con = this.getConnection();
		String query="SELECT * FROM users WHERE username = ? or userid= ? ";
		try {
			ps=con.prepareStatement(query);
			ps.setString(1, user.getUsername());
			ps.setInt(2, user.getUserid());
			rs = ps.executeQuery();
			while(rs.next())
			{
				return true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		try {
			if(rs!=null);
			rs.close();
			if(ps!=null)
				ps.close();
			if(con!=null)
				this.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(rs!=null);
			rs.close();
			if(ps!=null)
				ps.close();
			if(con!=null)
				this.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;

	}
	public void resetUser(String userid) 
	{
		con = this.getConnection();
		String query = "UPDATE users SET password= '"+encryptData("123")+"' WHERE userid= ?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, userid);
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		try {
			if(ps!=null)
				ps.close();
			if(con!=null)
				this.closeConnection(con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
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
