package com.mompopspizzeria;
/*
 * Basic pizza object that is mainly used to house menu items and prices. It is not used for building orders.
 * @author Russell Geary
 * @version 1.1 10/24/2022
 */
public class PizzaModel {
	String description;
	double price;
	public PizzaModel(String desc, double prc) {
		this.description = desc;
		this.price = prc;
	}
}