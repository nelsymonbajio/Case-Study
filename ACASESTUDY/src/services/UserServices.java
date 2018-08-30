package services;

import java.util.ArrayList;

import dao.UserDAO;
import models.User;

public class UserServices 
{
	UserDAO userDAO = new UserDAO();
	
	public boolean addUser(String username,String firstname,String middlename,String lastname,int userId,String role)
	{
		User user = new User();
		user.setUsername(username);
		user.setFirstname(firstname);
		user.setMiddlename(middlename);
		user.setLastname(lastname);
		user.setUserid(userId);
		user.setRole(role);
		
		if(!userDAO.userExists(user))
		{
			return userDAO.createUser(user);
		}
		else {
			System.out.println("username or userid already exists");
			return false;
		}
		
	}
	public boolean deleteUser(String username)
	{
		return userDAO.deleteUser(username);
	}
	public ArrayList<User> retrieveAllUsers()
	{
		return userDAO.getAllUsers();
	}
	public boolean updateUser(String id,String userid,String username,String firstname,String middlename,String lastname,String role) {
		return userDAO.updateUser(id,userid,username,firstname,middlename,lastname,role);
	}
	public ArrayList<User> getUserInfo(String username)
	{
		return userDAO.getSpecificUser(username);
	}
	public boolean changePassword(String userid,String oldpass,String newpass)
	{
		return userDAO.changePassword(userid,oldpass,newpass);
	}
}
