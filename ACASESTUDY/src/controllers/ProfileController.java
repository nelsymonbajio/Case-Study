package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.UserServices;

public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserServices userServ = new UserServices();
	public ProfileController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getRequestURI().equals(request.getContextPath()+"/Profile/"))
		{
			request.setAttribute("userInfo", userServ.getUserInfo(request.getSession().getAttribute("username").toString()));
			request.getRequestDispatcher("/Profile.jsp").forward(request, response);
		}
		else if(request.getRequestURI().equals(request.getContextPath()+"/Profile/UpdateProfile"))
		{
			request.setAttribute("userInfo", userServ.getUserInfo(request.getSession().getAttribute("username").toString()));
			request.getRequestDispatcher("/UpdateProfile.jsp").forward(request, response);
		}


	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
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
			
			if(userServ.updateUser(id,userid,username,firstname,middlename,lastname,role,create,update,delete)) {
				System.out.println("Success");
				//lowercase the first letter of role for session
				role = Character.toLowerCase(role.charAt(0)) + role.substring(1);
				request.getSession().setAttribute("username", username);
				request.getSession().setAttribute("accessLevel",role);
				
				response.sendRedirect(request.getContextPath()+"/Profile/");
			}else {
				System.out.println("Update error");
			}
		}
		else if(request.getRequestURI().equals(request.getContextPath()+"/Profile/ChangePassword"))
		{
			String userid= request.getParameter("userID");
			String oldpass = request.getParameter("oldpass");
			String newpass = request.getParameter("newpass");
			
			if(userServ.changePassword(userid,oldpass,newpass)) {
				response.sendRedirect(request.getContextPath()+"/Profile/");
			}else {
				System.out.println("Old Password Incorrect");
				response.sendRedirect(request.getContextPath()+"/Profile/UpdateProfile");
			}
		}
	}

}
