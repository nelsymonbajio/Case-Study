package com.nel.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nel.services.LoginServices;
import com.nel.services.UserServices;

public class LoginController extends HttpServlet {

	private LoginServices logserv = new LoginServices();
	private UserServices userServ = new UserServices();
	private HttpSession session;

	public LoginController() {
		super();
	}
	/** URL REQUEST MAPPING IN LOGIN SERVLET */  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Logout Request
		if(request.getRequestURI().equals(request.getContextPath()+"/Login/Logout"))
		{
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath()+"/Login.jsp");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		logserv.login(request.getParameter("username"), request.getParameter("password"));
		session = request.getSession();
		
		//Login Request
		if(request.getRequestURI().equals(request.getContextPath()+"/Login"))
		{
			if(logserv.isUserExists()&&logserv.isAdmin())
			{
				session.setAttribute("username", request.getParameter("username"));
				session.setAttribute("accessLevel", "admin");
				//set privileges of session
				session.setAttribute("createPriv", true);
				session.setAttribute("updatePriv", true);
				session.setAttribute("deletePriv", true);
				request.getRequestDispatcher("AdminMain.jsp").forward(request, response);
			}
			else if(logserv.isUserExists()&&!logserv.isAdmin())
			{
				session.setAttribute("username", request.getParameter("username"));
				session.setAttribute("accessLevel", "user");
				//set privileges of session
				session.setAttribute("createPriv", userServ.getUserInfo(request.getParameter("username")).get(0).isCanCreate());
				session.setAttribute("updatePriv", userServ.getUserInfo(request.getParameter("username")).get(0).isCanUpdate());
				session.setAttribute("deletePriv", userServ.getUserInfo(request.getParameter("username")).get(0).isCanDelete());
				request.getRequestDispatcher("UserMain.jsp").forward(request, response);
			}
			else if(!logserv.isUserExists())
			{
				// redirect to login page
				String message = "Username or Password incorrect";
				String location ="Login.jsp";
				alertMessage(message,response,location);
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
