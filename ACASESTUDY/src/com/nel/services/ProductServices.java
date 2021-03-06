package com.nel.services;

import java.util.ArrayList;

import com.nel.dao.ProductDAO;
import com.nel.models.Product;

public class ProductServices {
	
	private ProductDAO prodDAO = new ProductDAO();
	
	public boolean addProduct(String prodname,String prodcode,String prodtype,int qty, double price,String expdate)
	{
		if(expdate.equals("")||expdate==null)
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
	public ArrayList<Product> getProductInfo(String id)
	{
		return prodDAO.getSpecificProduct(id);
	}
	public boolean updateProduct(String id,String prodname, String prodcode, String prodType, int qty, double price, String expDate) 
	{
		if(expDate.equals("")||expDate==null)
			expDate=null;
		
		return prodDAO.updateProduct(id,prodname,prodcode,prodType,qty,price,expDate);
	}

}
