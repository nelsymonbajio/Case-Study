package services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dao.ProductDAO;
import models.Product;

public class ProductServices {
	
	private ProductDAO prodDAO = new ProductDAO();
	
	public boolean addProduct(String prodname,String prodcode,String prodtype,int qty, double price,String expdate)
	{
		if(expdate.equals(""))
			expdate=null;
		return prodDAO.addProduct(prodname,prodcode,prodtype,qty,price,expdate);
	}
	public ArrayList<Product> retrieveAllProducts()
	{
		return prodDAO.getAllProducts();
	}
	public boolean deleteProduct(String id)
	{
		return prodDAO.deleteProduct(id);
	}

}
