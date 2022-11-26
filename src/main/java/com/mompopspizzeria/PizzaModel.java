package com.mompopspizzeria;
/*
 * Basic pizza object that is mainly used to house menu items and prices. It is not used for building orders.
 * @author Russell Geary
 * @version 7.1 11/15/2022
 */
public class PizzaModel {
	public String description;
	public double price;
	public PizzaModel(String desc, double prc) {
		this.description = desc;
		this.price = prc;
	}
}