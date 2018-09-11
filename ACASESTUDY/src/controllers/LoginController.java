package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.LoginServices;
import services.UserServices;

public class LoginController extends HttpServlet {

	private LoginServices logserv = new LoginServices();
	private UserServices userServ = new UserServices();
	private HttpSession session;

	public LoginController() {
		super();
	}

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
				//set privileges
				session.setAttribute("createPriv", true);
				session.setAttribute("updatePriv", true);
				session.setAttribute("deletePriv", true);
				request.getRequestDispatcher("AdminMain.jsp").forward(request, response);
			}
			else if(logserv.isUserExists()&&!logserv.isAdmin())
			{
				session.setAttribute("username", request.getParameter("username"));
				session.setAttribute("accessLevel", "user");
				//set privileges
				session.setAttribute("createPriv", userServ.getUserInfo(request.getParameter("username")).get(0).isCanCreate());
				session.setAttribute("updatePriv", userServ.getUserInfo(request.getParameter("username")).get(0).isCanUpdate());
				session.setAttribute("deletePriv", userServ.getUserInfo(request.getParameter("username")).get(0).isCanDelete());
				request.getRequestDispatcher("UserMain.jsp").forward(request, response);
			}
			else if(!logserv.isUserExists())
			{
				// redirect to login page 
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('User or password incorrect');");
				out.println("location='Login.jsp';");
				out.println("</script>");
			}
		}


	}

}
