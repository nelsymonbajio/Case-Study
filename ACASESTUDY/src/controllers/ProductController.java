package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.ProductServices;

public class ProductController extends HttpServlet {
	
	private ProductServices prodServ = new ProductServices();
	
    public ProductController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("username")!=null)
		{
			if(request.getRequestURI().equals("/ACASESTUDY/Products/"))
			{	
				//request.setAttribute("prodList", prodServ.retrieveAllProducts());
				request.getRequestDispatcher("/Products.jsp").forward(request, response);
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	}

}
