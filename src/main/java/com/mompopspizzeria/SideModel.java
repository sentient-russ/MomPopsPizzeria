package com.mompopspizzeria;
/*
 * This object class is used to build the menu sides list.  It is not used to build orders.
 * @author Russell Geary
 * @version 7.1 11/15/2022
 */
public class SideModel {
	String description;
	double price;
	public SideModel(String desc, double prc) {
		this.description = desc;
		this.price = prc;
	}
}