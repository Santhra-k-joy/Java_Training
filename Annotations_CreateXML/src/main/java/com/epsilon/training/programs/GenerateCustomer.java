package com.epsilon.training.programs;


import com.epsilon.training.annotations.processors.XMLSerializer;
import com.epsilon.training.entity.Address;
import com.epsilon.training.entity.Customer;

public class GenerateCustomer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		Address a1 =new Address("#333","bangalore","karnataka","India");
		
		Customer p1 =new Customer("santhra","santhra2gmail.com","9632688753",a1);
		
	System.out.println(p1);
	XMLSerializer js =new XMLSerializer();
	String json=js.serialize(p1,0);
	System.out.println(json);
	//FileWriter file = new FileWriter("p1.json");

	}

}
