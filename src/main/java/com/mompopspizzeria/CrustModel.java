package com.mompopspizzeria;

/*
 * this object class is used to build the menu crusts ArrayList for the purpose of populating drop down picklists
 * @author Russell Geary
 * @version 7.1 11/15/2022
 */
public class CrustModel {
	String description;
	double price;
	/*
	 * Constructor used to instantiate the crust menu options
	 */
	public CrustModel(String desc, double prc) {
		this.description = desc;
		this.price = prc;
	}
}