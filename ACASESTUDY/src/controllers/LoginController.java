package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.LoginServices;

public class LoginController extends HttpServlet {
	
	private LoginServices logserv = new LoginServices();
	private HttpSession session;
	
    public LoginController() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
    	//Logout add destroy session
		if(request.getRequestURI().equals("/ACASESTUDY/Login/Logout"))
		{
			request.getSession().invalidate();
			response.sendRedirect("/ACASESTUDY/Login.jsp");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		logserv.login(request.getParameter("username"), request.getParameter("password"));
		session = request.getSession();
		
		if(request.getRequestURI().equals("/ACASESTUDY/Login"))
		{
			if(logserv.isUserExists()&&logserv.isAdmin())
			{
				session.setAttribute("username", request.getParameter("username"));
				session.setAttribute("accessLevel", "admin");
				request.getRequestDispatcher("AdminMain.jsp").forward(request, response);
			}
			else if(logserv.isUserExists()&&!logserv.isAdmin())
			{
				System.out.println("asd");
				session.setAttribute("username", request.getParameter("username"));
				session.setAttribute("accessLevel", "user");
				request.getRequestDispatcher("UserMain.jsp").forward(request, response);
			}
			else if(!logserv.isUserExists())
			{
				System.out.println("Incorrect username or password");
				request.getRequestDispatcher("Login.jsp").forward(request,response);
			}
		}
		
		
	}

}
