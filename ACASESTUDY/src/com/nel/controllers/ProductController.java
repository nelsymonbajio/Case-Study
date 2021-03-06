package com.nel.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nel.services.ProductServices;

public class ProductController extends HttpServlet {

	private ProductServices prodServ = new ProductServices();

	public ProductController() {
		super();
	}
	/** URL REQUEST MAPPING IN PRODUCT SERVLET */  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		//Retrieve All Products
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
		//DELETE PRODUCT
		else if(request.getRequestURI().equals(request.getContextPath()+"/Products/DeleteProduct"))
		{
			if(prodServ.deleteProduct(request.getParameter("p")))
			{
				response.sendRedirect(request.getContextPath()+"/Products/");
			}
			else {
				alertMessage("Delete Product Failed",response,request.getContextPath()+"/Products/");
			}
		}
		//UPDATE FORM
		else if(request.getRequestURI().equals(request.getContextPath()+"/Products/UpdateProductForm"))
		{
			request.setAttribute("prodInfo", prodServ.getProductInfo(request.getParameter("p")));
			request.getRequestDispatcher("/UpdateProductForm.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//INSERT NEW PRODUCT
		if(request.getRequestURI().equals(request.getContextPath()+"/Products/AddProduct"))
		{
			String prodname = request.getParameter("prodname");
			String prodcode = request.getParameter("prodcode");
			int qty = Integer.parseInt(request.getParameter("quantity"));
			double price = Double.parseDouble(request.getParameter("price"));
			String prodType = request.getParameter("productType");
			String expDate = request.getParameter("expiryDate");
			
			if(prodServ.addProduct(prodname, prodcode, prodType, qty, price, expDate)) 
			{
				response.sendRedirect(request.getContextPath()+"/Products/");
			}
			else {
				alertMessage("Add Product Failed",response,request.getContextPath()+"/Products/AddProductForm");
			}
		}
		//UPDATE PRODUCT
		else if(request.getRequestURI().equals(request.getContextPath()+"/Products/UpdateProduct"))
		{
			String id = request.getParameter("prodid");
			String prodname = request.getParameter("prodname");
			String prodcode = request.getParameter("prodcode");
			int qty = Integer.parseInt(request.getParameter("quantity"));
			double price = Double.parseDouble(request.getParameter("price"));
			String prodType = request.getParameter("productType");
			String expDate = request.getParameter("expiryDate");

			if(prodServ.updateProduct(id,prodname, prodcode, prodType, qty, price, expDate)) 
			{
				response.sendRedirect(request.getContextPath()+"/Products/");
			}
			else {
				alertMessage("Update Product Failed",response,request.getContextPath()+"/Products/");
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

