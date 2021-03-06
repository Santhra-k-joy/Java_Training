package com.epsilon.training.programs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;


import com.epsilon.training.dao.DaoFactory;
import com.epsilon.training.dao.JDBCProductDao;
import com.epsilon.training.dao.ProductDao;
import com.epsilon.training.entity.KeyboardUtil;
import com.epsilon.training.entity.Product;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductManagement{

	ProductDao dao;

	void start() {
		// tight coupling; must be avoided
	//	dao = new DummyProductDao();
		
		dao = DaoFactory.getProductDao();
		
	  
		while (true) {
			menu();
			try {
				int choice = KeyboardUtil.getInt("Enter your choice: ");
				if (choice == 0) {
					System.out.println("Thank you and have a nice day.");
					break;
				}

				switch (choice) {
				case 1:
						acceptAndAddProduct();
					break;
				case 2:
					getProductWithId();
					break;
				case 3:
					updateProduct();
					break;
				case 4:
					deleteProduct();
					break;
				case 5:
					getAlllist();
					break;
				case 6:
					getByPrice();
					break;
				case 7:
					getByBrand();
					break;
				case 8:
					getByCategory();
					break;
				default:
					System.out.println("Invalid choice! Please try again.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid choice! Please try again.");
			}
		}
	}

	  private void getByCategory() {
		  try {
				String maxi = KeyboardUtil.getString("Enter the Category name ");
				
				
				 dao.getByCategory(maxi.replaceAll("\\s+"," ").trim());
			 }
				catch(Exception ex) {
					log.warn("There was an error while trying to retrieve products of that Category");
					log.warn(ex.getMessage());
				}
		
	}

	private void getByBrand() {

		try {
			String maxi = KeyboardUtil.getString("Enter the Brand name ");
			
			
			 dao.getByBrand(maxi.replaceAll("\\s+"," ").trim());  //makes double space single then trims space from beginning and end 
		 }
			catch(Exception ex) {
				log.warn("There was an error while trying to retrieve products of that brand");
				log.warn(ex.getMessage());
			}
	}

	private void getByPrice() {
		
				try {
					
					double mini = KeyboardUtil.getDouble("Enter the minimum rate ");
					double maxi = KeyboardUtil.getDouble("Enter the maximum rate ");
					if(maxi<mini)
					{
						log.warn("Maximum value should be less than minimum");
					}
					else
					 dao.getByPriceRange(mini,maxi);
				 }
					catch(Exception ex) {
						log.warn("There was an error while trying to add a product");
						log.warn(ex.getMessage());
					}
		
	}

	private void getAlllist() {
		try {
			 
			
			 dao.getAll();
		 }
			catch(Exception ex) {
				log.warn("There was an error while trying to retrieve product within the range");
				log.warn(ex.getMessage());
			}
	}

	void deleteProduct()
	  {try {
			 
			 int id = KeyboardUtil.getInt("Enter id: ");
				
			 dao.deleteProduct(id);
		 }
			catch(Exception ex) {
				log.warn("There was an error while trying to delete a product");
				log.warn(ex.getMessage());
			}
		}

	void updateProduct() {
		
		
	
		 try {
				
			 int id = KeyboardUtil.getInt("Enter id: ");
				
			Product p= dao.getProduct(id);
			p.setId(id);
				String name = KeyboardUtil.getStringPass("Enter product name: ",p.getName());
				String description = KeyboardUtil.getStringPass("Enter product description: ",p.getDescription());
				String brand = KeyboardUtil.getStringPass("Enter brand name: ",p.getBrand());
				String category = KeyboardUtil.getStringPass("Enter category: ",p.getCategory());
				String picture = KeyboardUtil.getStringPass("Enter picture URL: ",p.getPicture());
				String  quantityPerUnit = KeyboardUtil.getStringPass("Enter quantity:",p.getQuantityPerUnit());
				String  unitPrice = KeyboardUtil.getDoublePass("Enter Price", p.getUnitPrice());
				String  discount =  KeyboardUtil.getDoublePass("Enter Discount", p.getDiscount());
				
			//	Product p = new Product();
				//p.setId(id);
				
				p.setName(checkNull(p.getName(),name));
				p.setDescription(checkNull(p.getDescription(),description));
				p.setBrand(checkNull(p.getBrand(),brand));
				p.setCategory( checkNull(p.getCategory(),category));
				p.setPicture(checkNull(p.getPicture(),picture));
				p.setQuantityPerUnit(checkNull(p.getQuantityPerUnit(),quantityPerUnit));
				p.setUnitPrice(Double.parseDouble(checkNull(Double. toString(p.getUnitPrice()),unitPrice)));
				p.setDiscount(Double.parseDouble(checkNull(Double. toString(p.getDiscount()),discount)));
			
			
			
				dao.updateProduct(p);
				
			}
			catch(Exception ex) {
				log.warn("There was an error while trying to add a product");
				log.warn(ex.getMessage());
			}
		
	}

	

	private String checkNull(String pval, String newval) {
		// TODO Auto-generated method stub
		if(newval=="")
		return pval;
		else
			return newval;
	}

	void getProductWithId() {
	 try {
		 
		 int id = KeyboardUtil.getInt("Enter id: ");
			
		 dao.getProduct(id);
	 }
		catch(Exception ex) {
			log.warn("There was an error while trying to add a product");
			log.warn(ex.getMessage());
		}
	}

	void acceptAndAddProduct() {
		
		// create a product object
			// use the addProduct function in dao object
			
			try {
				// create variables for all product fields
				// accept value for each variable from the user
			//	int id = KeyboardUtil.getInt("Enter id: ");
				
				String name = KeyboardUtil.getString("Enter product name: ");
				String description = KeyboardUtil.getString("Enter product description: ");
				String brand = KeyboardUtil.getString("Enter brand name: ");
				String category = KeyboardUtil.getString("Enter category: ");
				String picture = KeyboardUtil.getString("Enter picture URL: ");
				String  quantityPerUnit = KeyboardUtil.getString("Enter quantity:");
				double  unitPrice = KeyboardUtil.getInt("Enter cost for each unit:");
				double  discount = KeyboardUtil.getInt("Enter the discount:");
				
				Product p = new Product();
			//	p.setId(id);
				p.setName(name);
				p.setDescription(description);
				p.setBrand(brand);
				p.setCategory(category);
				p.setPicture(picture);
				p.setQuantityPerUnit(quantityPerUnit);
				
				p.setUnitPrice(unitPrice);
				p.setDiscount(discount);
				
				// TODO do the same for rest of the fields
			
				dao.addProduct(p);
				
				System.out.println("New product details added to successfully!");
			}
			catch(Exception ex) {
				log.warn("There was an error while trying to add a product");
				log.warn(ex.getMessage());
			}
		
	}

	void menu() {
		log.debug("hi");
		System.out.println("*** Main Menu ***");
	
		System.out.println("0. Exit");
		System.out.println("1. Add a new product");
		System.out.println("2. Retrieve a product by id");
		System.out.println("3. Modify details of a product");
		System.out.println("4. Remove product details");
		System.out.println("5. Get all");
		System.out.println("6. Get by price range");
		System.out.println("7. Get by brand");
		System.out.println("8. Get by category");
	}

	public static void main(String[] args) {
		new ProductManagement().start();
	}

}
