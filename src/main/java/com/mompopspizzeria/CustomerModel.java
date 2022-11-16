package com.mompopspizzeria;
/*
 * This class is the customer object that is instantiated for each new customer.  It will also be passed
 * to and returned from the DataAccessInterface
 * @author Russell Geary
 * @version 1.1, 10/24/2022 
 */
public class CustomerModel {
	public int customerId;
	public String firstName;
	public String lastName;
	public String address1;
	public String address2;
	public String city;
	public String state;
	public String zip;
	public String phoneNumber;
	public String password;
	public String cardNumber;
	public String expDate;
	public String CVV;
	public boolean isEmployee = false;
	public boolean isManager = false;
	
	//default constructor.  Sets customerId to -1 to indicate the customer is not in the database
	public CustomerModel() {
		this.customerId = -1;
	}
	//Overloaded constructor without customer id for new customers.
	public CustomerModel(String firstNameIn, String lastNameIn, String address1In, String address2In,
			String cityIn, String stateIn, String zipIn, String phoneNumberIn, String passwordIn) {
				this.customerId = -1;
				this.firstName = firstNameIn;
				this.lastName = lastNameIn;
				this.address1 = address1In;
				this.address2 = address2In;
				this.city = cityIn;
				this.state = stateIn;
				this.zip = zipIn;
				this.phoneNumber = phoneNumberIn;
				this.password = passwordIn;
				
	}	
	public String getPhoneNumber() {
				
		return this.phoneNumber;
	}
	public String getPassword() {

		return this.password;
	}
	public String getFirstName(){
		return this.firstName;
	}
	public String getLastName(){
		return this.lastName;
	}
}
