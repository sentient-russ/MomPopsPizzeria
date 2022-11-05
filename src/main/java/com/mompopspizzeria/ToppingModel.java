package com.mompopspizzeria;
/*
 * this object class is used to build the menu toppings.  It is not used for building orders.
 * @author Russell Geary
 * @version 10/24/2022
 */
public class ToppingModel {
	String description;
	double price;
	public ToppingModel(String desc, double prc) {
		this.description = desc;
		this.price = prc;
	}
}