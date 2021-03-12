package com.epsilon.training.entity;


import com.epsilon.training.annotaions.XMLIgnored;
import com.epsilon.training.annotations.XMLProperty;
import com.epsilon.training.annotations.XMLSerializable;



@XMLSerializable
public class Customer {
	@XMLProperty(label = "fname")
	private String name;
	@XMLProperty
	private String email;
	@XMLProperty
	private String phone;
	@XMLProperty
	private Address address;

	
	public Customer()
	{}


	public Customer(String name, String email, String phone, Address address) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "Customer [name=" + name + ", email=" + email + ", phone=" + phone + ", address=" + address + "]";
	}
	
}

