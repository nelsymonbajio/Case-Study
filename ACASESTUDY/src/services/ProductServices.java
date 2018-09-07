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
	public ArrayList<Product> getProductInfo(String id)
	{
		return prodDAO.getSpecificProduct(id);
	}
	public boolean updateProduct(String id,String prodname, String prodcode, String prodType, int qty, double price, String expDate) 
	{
		if(expDate.equals("")||expDate==null||expDate.equals("0000-00-00"))
			expDate=null;
		return prodDAO.updateProduct(id,prodname,prodcode,prodType,qty,price,expDate);
	}

}
