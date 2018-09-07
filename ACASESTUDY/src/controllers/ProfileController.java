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
		request.setAttribute("userInfo", userServ.getUserInfo(request.getSession().getAttribute("username").toString()));
		request.getRequestDispatcher("/Profile.jsp").forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
