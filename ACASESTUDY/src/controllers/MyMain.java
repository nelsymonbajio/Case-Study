package controllers;

import java.util.Iterator;

import dao.UserDAO;
import models.User;

public class MyMain {

	public static void main(String[] args) 
	{
		UserDAO userDAO = new UserDAO();

		//GET ALL USERS
		System.out.println("Retrieve all Users ");
		Iterator<User> it = userDAO.getAllUsers().iterator();
		while(it.hasNext())
		{
			User user = it.next();
			System.out.println("Name: " + user.getFirstname() + " " +user.getLastname());
		}

		//CREATE USER USING USERDAO
		System.out.println("Create User");
		User user = new User();
		user.setFirstname("Nel");
		user.setUserid(50);
		user.setMiddlename("Morado");
		user.setLastname("Bajio");
		user.setRole("User");
		user.setCanCreate(true);
		user.setCanDelete(true);
		user.setCanUpdate(true);
		user.setPassword("pass");
		user.setUsername("nel");
		userDAO.createUser(user);
		System.out.println("Success");

		//GET SPECIFIC USER
		System.out.println("Get Specific User");
		User specific = userDAO.getSpecificUser("nel").get(0);
		System.out.print("Username "+ specific.getUsername()+" ID"+specific.getId() +" \nName "+specific.getFirstname()+" "+ specific.getLastname());
		
		System.out.println("Update User");
		//UPDATE USER 
		if(userDAO.updateUser(String.valueOf(specific.getId()), "2222", "newusername", "new", "new", "new", "Admin", "createPriv", "updatePriv", "deletePriv"))
		{
			System.out.println("update success");
		}
		//DELETE USER
		System.out.println("Delete User");
		if(userDAO.deleteUser("newusername"))
		{
			System.out.println("delete success");
		}
	}

}
