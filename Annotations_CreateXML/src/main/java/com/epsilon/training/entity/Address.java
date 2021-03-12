package com.epsilon.training.entity;



import com.epsilon.training.annotations.XMLProperty;
import com.epsilon.training.annotations.XMLSerializable;

@XMLSerializable
public class Address {
	@XMLProperty(label = "Streets")
	private String street;
	@XMLProperty
	private String city;
	@XMLProperty
	private String state;
	@XMLProperty
	private String country ;
	public Address()
	{}

	public Address(String street, String city, String state, String country) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
	}
	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", state=" + state + ", country=" + country + "]";
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
