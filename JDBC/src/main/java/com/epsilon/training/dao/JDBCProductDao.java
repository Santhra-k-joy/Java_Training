package com.epsilon.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.epsilon.training.util.DButil;

import lombok.extern.slf4j.Slf4j;

import com.epsilon.training.entity.Product;
@Slf4j
public class JDBCProductDao implements ProductDao {

	public String name ;
	public String brand ;
	public String category;
public	 String quantity ;
	public String description ;
public	 String picture;
public	 double price;
public	double discount;
Product p =new Product();
public Product passP()
{
return p;	
}
	@Override
	public void addProduct(Product product) throws DaoException {
		
		String sql= String.format("insert into products values(null,'%s','%s','%s','%s','%s',%f,'%s',%f )",
				product.getCategory(),product.getName(),product.getBrand(),product.getDescription(),product.getQuantityPerUnit(),product.getUnitPrice(),product.getPicture(),product.getDiscount());
	
		try (Connection conn = DButil.createConnection();
				Statement stmt =conn.createStatement();)
		{
			System.out.println("hi check ");
			conn.setAutoCommit(false);
			stmt.execute(sql);
			conn.commit();
			System.out.println("Inserted sucessfully");
			
		}
		catch(Exception e)
		{
			System.out.println("There was a error "+e);
		}
	}

	@Override
	public Product getProduct(int id) throws DaoException {
		
		String sql= String.format("SELECT * FROM Products WHERE id = %d;",id);
				
		try(Connection conn = DButil.createConnection();
				Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery(sql);)
		{
			int i=0;
			while (rs.next()) {
				 name = rs.getString("name");
				 brand = rs.getString("brand");
				category = rs.getString("category");
				  quantity = rs.getString("quantity_per_unit");
				  description = rs.getString("description");
				  picture = rs.getString("picture");
				 price = rs.getDouble("unit_price");
				 discount = rs.getDouble("discount");
				
				 p.setName(name);
				 p.setBrand(brand);
				 p.setCategory(category);
				 p.setQuantityPerUnit(quantity);
				 p.setDescription(description);
				 p.setPicture(picture);
				 p.setUnitPrice(price);
				 p.setDiscount(discount);
				  System.out.println("Name: "+name+" Brand: "+brand+" Category: "+category+ " Quantity: "+quantity + " Description: "+ description + " Picture: "+picture+" Price: "+price+" discount:  "+discount);
				  i++;
				  return p;
				}
			if(i==0)
			{
				System.out.println("The id doen't exist");
			}
		}
		catch(Exception e)
		{
			System.out.println("There was a error "+e);
		}
		
		return null;
	}

	@Override
	public void updateProduct(Product product) throws DaoException {
	
		 System.out.println("id:"+product.getId()+"Name: "+product.getName()+" Brand: "+product.getBrand()+" Category: "+product.getCategory()+ " Quantity: "+product.getQuantityPerUnit() + " Description: "+ product.getDescription() + " Picture: "+product.getPicture()+" Price: "+product.getUnitPrice()+" discount:  "+product.getDiscount());
		  
		String sql = "update products set name=?, brand=?, category=?, description=?, quantity_per_unit=?,  picture=?, unit_price=?, discount=? where id=?";
		
		try(Connection conn = DButil.createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);){
			
			stmt.setString(1, product.getName());
			stmt.setString(2, product.getBrand());
			stmt.setString(3, product.getCategory());
			stmt.setString(4, product.getDescription());
			stmt.setString(5, product.getQuantityPerUnit());
			stmt.setString(6, product.getPicture());
			
			stmt.setDouble(7, product.getUnitPrice());
			stmt.setDouble(8, product.getDiscount());
			stmt.setInt(9, product.getId());
			 stmt.executeUpdate();
			System.out.println("done");
		}
		catch(Exception ex) {
			System.out.println("error"+ex);
		}
	}

	@Override
	public void deleteProduct(int id) throws DaoException {
		
	
			String sql = "Delete products where id=?";
			
			try(Connection conn = DButil.createConnection();
					PreparedStatement stmt = conn.prepareStatement(sql);){
				
				stmt.setInt(1, id);
				
			
				 stmt.executeUpdate();
				System.out.println("Product deleted");
			}
			catch(Exception ex) {
				System.out.println("error"+ex);
			}
	}

	@Override
	public List<Product> getAll() throws DaoException {
		String sql="select * from products";
		try(Connection conn = DButil.createConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);)
		{
			int c=0;
			while(rs.next())
			{
				 String name = rs.getString("name");
				 String brand = rs.getString("brand");
				 String category = rs.getString("category");
				 String quantity = rs.getString("quantity_per_unit");
				 String description = rs.getString("description");
				 String picture = rs.getString("picture");
				 double price = rs.getDouble("unit_price");
				double discount = rs.getDouble("discount");
				  System.out.println("Name: "+name+", Brand: "+brand+", Category: "+category+ ", Quantity: "+quantity + ", Description: "+ description + ", Picture: "+picture+", Price: "+price+", Discount:  "+discount);
				  
			}
		}
		catch(Exception e)
		
		{
		System.out.println("error :"+e);	
		}
return null;
	}	
	

	@Override
	public List<Product> getByPriceRange(double min, double max) throws DaoException {

		String sql = "select * from products where unit_price between ? and ? order by unit_price";
		try(Connection conn = DButil.createConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);)
			
		{
			stmt.setDouble(1,min);
			stmt.setDouble(2,max);
			try(ResultSet rs = stmt.executeQuery())
			{while(rs.next())
			{
				 String name = rs.getString("name");
				 String brand = rs.getString("brand");
				 String category = rs.getString("category");
				 String quantity = rs.getString("quantity_per_unit");
				 String description = rs.getString("description");
				 String picture = rs.getString("picture");
				 double price = rs.getDouble("unit_price");
				double discount = rs.getDouble("discount");
				  System.out.println("Name: "+name+" Brand: "+brand+" Category: "+category+ " Quantity: "+quantity + " Description: "+ description + " Picture: "+picture+" Price: "+price+" discount:  "+discount);
				  
			}
			}
			
			
		}
catch(Exception e)
		
		{
		System.out.println("error :"+e);	
		}
	
		return null;
	}

	@Override
	public List<Product> getByBrand(String brand) throws DaoException {
		String sql= String.format("SELECT * FROM Products WHERE LOWER(brand) = '%s';",brand.toLowerCase());
		
		try(Connection conn = DButil.createConnection();
				Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery(sql);)
		{
			int i=0;
			while (rs.next()) {
				 name = rs.getString("name");
				 brand = rs.getString("brand");
				category = rs.getString("category");
				  quantity = rs.getString("quantity_per_unit");
				  description = rs.getString("description");
				  picture = rs.getString("picture");
				 price = rs.getDouble("unit_price");
				 discount = rs.getDouble("discount");
				i++;
				  System.out.println("Name: "+name+" Brand: "+brand+" Category: "+category+ " Quantity: "+quantity + " Description: "+ description + " Picture: "+picture+" Price: "+price+" discount:  "+discount);
				}
			if(i==0)
			{
				System.out.println("No product available of that brand!!");
			}
		}
		catch(Exception e)
		{
			System.out.println("There was a error "+e);
		}
		
		return null;
	}

	@Override
	public List<Product> getByCategory(String category) throws DaoException {
	String sql= String.format("SELECT * FROM Products WHERE LOWER(category) = '%s';",category.toLowerCase());
		
		try(Connection conn = DButil.createConnection();
				Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery(sql);)
		{
			int i=0;
			while (rs.next()) {
				 name = rs.getString("name");
				 brand = rs.getString("brand");
				category = rs.getString("category");
				  quantity = rs.getString("quantity_per_unit");
				  description = rs.getString("description");
				  picture = rs.getString("picture");
				 price = rs.getDouble("unit_price");
				 discount = rs.getDouble("discount");
				i++;
				  System.out.println("Name: "+name+" Brand: "+brand+" Category: "+category+ " Quantity: "+quantity + " Description: "+ description + " Picture: "+picture+" Price: "+price+" discount:  "+discount);
				}
			if(i==0)
			{
				System.out.println("No product available of that Category!!");
			}
		}
		catch(Exception e)
		{
			System.out.println("There was a error: "+e);
		}
		
		return null;
	}
}
