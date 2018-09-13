package com.nel.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nel.services.UserServices;

public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserServices userServ = new UserServices();
	public ProfileController() {
		super();
	}
	/** URL REQUEST MAPPING IN PROFILE SERVLET */  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Retrieve profile info
		if(request.getRequestURI().equals(request.getContextPath()+"/Profile/"))
		{
			request.setAttribute("userInfo", userServ.getUserInfo(request.getSession().getAttribute("username").toString()));
			request.getRequestDispatcher("/Profile.jsp").forward(request, response);
		}
		//Redirect to update profile form
		else if(request.getRequestURI().equals(request.getContextPath()+"/Profile/UpdateProfile"))
		{
			request.setAttribute("userInfo", userServ.getUserInfo(request.getSession().getAttribute("username").toString()));
			request.getRequestDispatcher("/UpdateProfile.jsp").forward(request, response);
		}


	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Update Profile request
		if(request.getRequestURI().equals(request.getContextPath()+"/Profile/Updated"))
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
			
			//check null value of select option
			if(role==null)
				role="User";
			
			if(userServ.updateUser(id,userid,username,firstname,middlename,lastname,role,create,update,delete)) {
				
				//lowercase the first letter of role for session
				role = Character.toLowerCase(role.charAt(0)) + role.substring(1);
				request.getSession().setAttribute("username", username);
				request.getSession().setAttribute("accessLevel",role);
				response.sendRedirect(request.getContextPath()+"/Profile/");
				
			}else {
				alertMessage("Update Failed try different userid or username",response,request.getContextPath()+"/Profile/UpdateProfile");
			}
		}
		//Update Profile Password
		else if(request.getRequestURI().equals(request.getContextPath()+"/Profile/ChangePassword"))
		{
			String userid= request.getParameter("userID");
			String oldpass = request.getParameter("oldpass");
			String newpass = request.getParameter("newpass");
			
			if(userServ.changePassword(userid,oldpass,newpass)) {
				response.sendRedirect(request.getContextPath()+"/Profile/");
			}else {
				alertMessage("Old Password Incorrect",response,request.getContextPath()+"/Profile/UpdateProfile");
			}
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
