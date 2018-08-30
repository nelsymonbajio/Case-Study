package services;

import dao.LoginDAO;

public class LoginServices 
{
	private boolean isAdmin;
	private boolean userExists;
	
	public void login(String username, String password)
	{
		LoginDAO logDAO = new LoginDAO();
		logDAO.searchUser(username,password);
		
		isAdmin = logDAO.isAdmin();
		userExists = logDAO.isUserExists();
		
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
