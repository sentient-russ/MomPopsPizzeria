package com.mompopspizzeria;
/*
 * this object class is used to build the menu drinkSizes ArrayList
 * @author Russell Geary
 * @version 1.0 10/07/2022
 */
public class DrinkSizeModel {
	String description;
	double price;
	public DrinkSizeModel(String desc, double prc) {
		this.description = desc;
		this.price = prc;
	}
}