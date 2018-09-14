package com.nel.services;

import java.util.ArrayList;

import com.nel.dao.UserDAO;
import com.nel.models.User;

public class UserServices {
	UserDAO userDAO = new UserDAO();

	public boolean addUser(String username, String firstname, String middlename, String lastname, int userId,
			String role, String create, String update, String delete) {
		User user = new User();
		user.setUsername(username);
		user.setFirstname(firstname);
		user.setMiddlename(middlename);
		user.setLastname(lastname);
		user.setUserid(userId);
		user.setRole(role);

		if (role.equalsIgnoreCase("admin")) {
			user.setCanCreate(true);
			user.setCanUpdate(true);
			user.setCanDelete(true);
		} else {
			// check checkboxes value
			if (create != null)
				user.setCanCreate(true);
			if (update != null)
				user.setCanUpdate(true);
			if (delete != null)
				user.setCanDelete(true);
		}
		if (!userDAO.userExists(user)) {
			return userDAO.createUser(user);
		} else {
			return false;
		}

	}

	public boolean deleteUser(String username) {
		return userDAO.deleteUser(username);
	}

	public ArrayList<User> retrieveAllUsers() {
		return userDAO.getAllUsers();
	}

	public boolean updateUser(String id, String userid, String username, String firstname, String middlename,
			String lastname, String role, String create, String update, String delete) {
		
		return userDAO.updateUser(id, userid, username, firstname, middlename, lastname, role, create, update, delete);
	}

	public ArrayList<User> getUserInfo(String username) {
		return userDAO.getSpecificUser(username);
	}

	public boolean changePassword(String userid, String oldpass, String newpass) {
		return userDAO.changePassword(userid, oldpass, newpass);
	}

	public void resetUser(String userid) {
		userDAO.resetUser(userid);
	}
}
