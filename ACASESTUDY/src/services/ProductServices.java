package services;

import java.util.ArrayList;

import dao.ProductDAO;
import models.Product;

public class ProductServices {
	
	private ProductDAO prodDAO = new ProductDAO();
	
	public ArrayList<Product> retrieveAllProducts()
	{
		return prodDAO.getAllProducts();
	}
	public boolean deleteProduct(String prodcode)
	{
		return prodDAO.deleteProduct(prodcode);
	}

}
