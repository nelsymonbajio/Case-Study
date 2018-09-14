package com.nel.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nel.services.UserServices;

public class UserController extends HttpServlet {
	public UserController() {
		super();
	}
	private UserServices userServ = new UserServices();

	/** URL REQUEST MAPPING IN USER SERVLET */  

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
				response.sendRedirect(request.getContextPath()+"/Users/");
			}
		}
		//Redirect to Update User Form Page
		else if(request.getRequestURI().equals(request.getContextPath()+"/Users/UpdateUserForm"))
		{
			//updating self from the users table will redirect to profile page
			if(request.getParameter("u").equals(request.getSession().getAttribute("username")))
			{
				response.sendRedirect(request.getContextPath()+"/Profile/");
			}
			else {
				request.setAttribute("userInfo", userServ.getUserInfo(request.getParameter("u")));
				request.getRequestDispatcher("/UpdateUserForm.jsp").forward(request, response);
			}

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
				//System.out.println("Add Successful");
				response.sendRedirect(request.getContextPath()+"/Users/");
			}else {
				alertMessage("Registration Failed, Try again with different userid or username"
						,response,request.getContextPath()+"/AddUserForm.jsp");
			}

		}
		//CHANGE PASSWORD REQUEST
		else if(request.getRequestURI().equals(request.getContextPath()+"/Users/UserChangePass"))
		{
			String userid= request.getParameter("userID");
			String oldpass = request.getParameter("oldpass");
			String newpass = request.getParameter("newpass");

			if(userServ.changePassword(userid,oldpass,newpass)) {
				//System.out.println("Change password successful");
				response.sendRedirect(request.getContextPath()+"/Users/");
			}else {
				alertMessage("Old Password Incorrect",response,request.getContextPath()+"/Users/");
				//System.out.println("Old Password Incorrect");
				//response.sendRedirect(request.getContextPath()+"/Users/");
			}

		}
		//UPDATE USER REQUEST
		else if(request.getRequestURI().equals(request.getContextPath()+"/Users/UpdateUser"))
		{
			String u = request.getParameter("u");
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
				//System.out.println("Success");
				response.sendRedirect(request.getContextPath()+"/Users/");
			}else {
				//				System.out.println("Update Failed");
				//				System.out.println("Try again");
				//				response.sendRedirect(request.getContextPath()+"/Users/");
				alertMessage("Update Failed try different userid or username",response,request.getContextPath()+"/Users/UpdateUserForm?u="+u);

			}
		}
		//RESET USER PASSWORD
		else if(request.getRequestURI().equals(request.getContextPath()+"/Users/ResetUser"))
		{
			String userid = request.getParameter("userID");
			userServ.resetUser(userid);
			response.sendRedirect(request.getContextPath()+"/Users/");
		}
	}
	public void alertMessage(String message,HttpServletResponse response,String location) throws IOException
	{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script type=\"text/javascript\">");
		out.println("alert('"+message+"');");
		out.println("location='"+location+"';");
		out.println("</script>");
	}
}
