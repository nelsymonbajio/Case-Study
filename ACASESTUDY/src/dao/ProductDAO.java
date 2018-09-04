package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Product;

public class ProductDAO extends DbConnection{

	private Connection con = this.getConnection();
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement ps;

	public boolean addProduct(String prodname,String prodcode,String prodtype,int qty,double price,String expdate) 
	{
		String query="";
		
		//PRODUCT WITH NO EXP DATE "NULL"
		if(expdate==null)
		{
			query="INSERT INTO products(prodcode,prodname,producttype,qty,price,expirydate) VALUES ("
					+ "'"+prodcode+"','"+prodname+"','"+prodtype+"','"+qty+"','"+price+"',"+expdate+")";
		}
		//PRODUCT WITH EXP DATE
		else {
			query="INSERT INTO products(prodcode,prodname,producttype,qty,price,expirydate) VALUES ("
					+ "'"+prodcode+"','"+prodname+"','"+prodtype+"','"+qty+"','"+price+"','"+expdate+"')";
		}
		
		try {
			stmt=con.createStatement();
			stmt.executeUpdate(query);
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public ArrayList<Product> getAllProducts() {

		ArrayList<Product> prod = new ArrayList<Product>();
		String query="SELECT * FROM products ORDER BY id DESC";
		try {
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next())
			{
				Product prod1 = new Product();
				prod1.setProdid(rs.getString("id"));
				prod1.setProdCode(rs.getString("prodcode"));
				prod1.setName(rs.getString("prodname"));
				prod1.setProductType(rs.getString("producttype"));
				prod1.setQty(rs.getInt("qty"));
				prod1.setPrice(rs.getDouble("price"));
				prod1.setExpiryDate(rs.getDate("expiryDate"));
				prod.add(prod1);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prod;
	}
	public boolean deleteProduct(String id)
	{
		String query="DELETE FROM products WHERE id='"+id+"'";
		try{
			stmt=con.createStatement();
			stmt.executeUpdate(query);
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
