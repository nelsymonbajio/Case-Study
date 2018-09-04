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
			if(request.getRequestURI().equals(request.getContextPath()+"/Products/"))
			{	
				request.setAttribute("prodList", prodServ.retrieveAllProducts());
				request.getRequestDispatcher("/Products.jsp").forward(request, response);
			}
			//Redirect to add product form
			else if(request.getRequestURI().equals(request.getContextPath()+"/Products/AddProductForm"))
			{
				response.sendRedirect(request.getContextPath()+"/AddProductForm.jsp");
			}
			else if(request.getRequestURI().equals(request.getContextPath()+"/Products/DeleteProduct"))
			{
				if(prodServ.deleteProduct(request.getParameter("p")))
				{
					response.sendRedirect(request.getContextPath()+"/Products/");
					System.out.println("Delete success");
				}
				else {
					System.out.println("Failed");
				}
			}
		}
		else
		{
			System.out.println("no session");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getSession().getAttribute("username")!=null)
		{
			//Insert PRODUCT
			if(request.getRequestURI().equals(request.getContextPath()+"/Products/AddProduct"))
			{
				String prodname = request.getParameter("prodname");
				String prodcode = request.getParameter("prodcode");
				int qty = Integer.parseInt(request.getParameter("quantity"));
				double price = Double.parseDouble(request.getParameter("price"));
				String prodType = request.getParameter("productType");
				String expDate = request.getParameter("expiryDate");
				System.out.println(expDate);
				if(prodServ.addProduct(prodname, prodcode, prodType, qty, price, expDate)) {
					response.sendRedirect("/ACASESTUDY/Products/");
				}else {
					System.out.println("Failed");
				}
			}
		}
		else
		{
			System.out.println("No session");
		}
	}

}
