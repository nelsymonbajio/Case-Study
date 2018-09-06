package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.UserServices;

public class UserController extends HttpServlet {
	public UserController() {
		super();
	}
	private UserServices userServ = new UserServices();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Retrieve All Users
		if(request.getRequestURI().equals(request.getContextPath()+"/Users/"))
		{	
			request.setAttribute("usersList", userServ.retrieveAllUsers());
			request.getRequestDispatcher("/Users.jsp").forward(request, response);
		}
		//Redirect to Add User Form Page
		else if(request.getRequestURI().equals(request.getContextPath()+"/Users/AddUserForm"))
		{
			response.sendRedirect(request.getContextPath()+"/AddUserForm.jsp");
		}
		//Delete User
		else if(request.getRequestURI().equals(request.getContextPath()+"/Users/DeleteUser"))
		{
			if(userServ.deleteUser(request.getParameter("u")))
			{
				System.out.println("delete successful");
				response.sendRedirect(request.getContextPath()+"/Users/");
			}
		}
		//Redirect to Update User Form Page
		else if(request.getRequestURI().equals(request.getContextPath()+"/Users/UpdateUserForm"))
		{
			request.setAttribute("userInfo", userServ.getUserInfo(request.getParameter("u")));
			request.getRequestDispatcher("/UpdateUserForm.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		//REGISTER USER
		if(request.getRequestURI().equals(request.getContextPath()+"/Users/RegisterUser"))
		{
			String username = request.getParameter("username");
			String firstname = request.getParameter("firstname");
			String middlename = request.getParameter("middlename");
			String lastname = request.getParameter("lastname");
			int userid = Integer.parseInt(request.getParameter("userid"));
			String role = request.getParameter("role");

			if(userServ.addUser(username, firstname,middlename,lastname , userid,
					role,request.getParameter("Create"),
					request.getParameter("Update"),
					request.getParameter("Delete"))) {
				System.out.println("Add Successful");
				response.sendRedirect(request.getContextPath()+"/Users/");
			}else {
				response.sendRedirect(request.getContextPath()+"/AddUserForm.jsp");
			}

		}
		//CHANGE PASSWORD REQUEST
		else if(request.getRequestURI().equals(request.getContextPath()+"/Users/UserChangePass"))
		{
			String userid= request.getParameter("userID");
			String oldpass = request.getParameter("oldpass");
			String newpass = request.getParameter("newpass");

			if(userServ.changePassword(userid,oldpass,newpass)) {
				System.out.println("Change password successful");
				response.sendRedirect(request.getContextPath()+"/Users/");
			}else {
				System.out.println("Old Password Incorrect");
			}

		}
		//UPDATE USER REQUEST
		else if(request.getRequestURI().equals(request.getContextPath()+"/Users/UpdateUser"))
		{
			String id = request.getParameter("id");
			String userid = request.getParameter("userid");
			String username = request.getParameter("username");
			String firstname = request.getParameter("firstname");
			String middlename = request.getParameter("middlename");
			String lastname = request.getParameter("lastname");
			String role = request.getParameter("role");
			String create = request.getParameter("Create");
			String update = request.getParameter("Update");
			String delete = request.getParameter("Delete");
			
			if(userServ.updateUser(id,userid,username,firstname,middlename,lastname,role,create,update,delete)) {
				System.out.println("Success");
				response.sendRedirect(request.getContextPath()+"/Users/");
			}else {
				System.out.println("Update error");
			}
		}
	}
}
