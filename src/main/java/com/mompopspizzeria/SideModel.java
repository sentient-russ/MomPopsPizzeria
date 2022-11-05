package com.mompopspizzeria;
/*
 * this object class is used to build the menu sides.  It is not used to build orders.
 * @author Russell Geary
 * @version 1.1 10/24/2022
 */
public class SideModel {
	String description;
	double price;
	public SideModel(String desc, double prc) {
		this.description = desc;
		this.price = prc;
	}
}