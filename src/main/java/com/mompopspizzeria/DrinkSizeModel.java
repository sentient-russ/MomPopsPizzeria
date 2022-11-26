package com.mompopspizzeria;
/*
 * this object class is used to build the menu drinkSizes ArrayList
 * @author Russell Geary
 * @version 7.1 11/15/2022
 */
public class DrinkSizeModel {
	String description;
	double price;
	public DrinkSizeModel(String desc, double prc) {
		this.description = desc;
		this.price = prc;
	}
}