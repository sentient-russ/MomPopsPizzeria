package com.mompopspizzeria;
/*
 * This object is inserted into the order object. Line total calculation is handled herein.
 * @author Russell Geary
 * @version 1.1 10/24/2022
 */
import java.util.ArrayList;

public class LineItemModel extends MomPopsPizzeriaMain{
	

	
	String pizza;
	String crust;
	ArrayList<String> toppings = new ArrayList<>();
	String side;
	String drink;		
	String drinkSize;		
	double pizzaPrice;
	double crustPrice;
	double toppingPrice;
	double sidePrice;
	double drinkPrice;			
	double lineTotal;
	int drinkQuantity;
	int sideQuantity;
	
	boolean isPizza = false;
	boolean isDrink = false;
	boolean isSide = false;
	
	
	public LineItemModel() {

		
	}
	public void addPizza(String pizzaIn, String crustIn, ArrayList<String> toppingsIn) {
		this.pizza = pizzaIn;
		this.crust = crustIn;
		this.crustPrice = 0.00;
		this.toppings = toppingsIn;
		
		if(pizzaIn.equals("Small Pizza")) {
			pizzaPrice = 4.00;
			toppingPrice = 1*0.50;
		} 
		if(pizzaIn.equals("Medium Pizza")) {
			pizzaPrice = 6.00;
			toppingPrice = 1.5*0.50;
		} 
		if(pizzaIn.equals("Large Pizza")) {
			pizzaPrice = 8.00;
			toppingPrice = 2*0.50;
		} 
		if(pizzaIn.equals("Extra Large Pizza")) {
			pizzaPrice = 10.00;
			toppingPrice = 2.5*0.50;
		} 
		int toppingsCount = toppingsIn.size() - 1;
		isPizza = true;
		lineTotal = toppingsCount * this.toppingPrice + pizzaPrice;	
		
	}
	public void addSide(String sideIn, int qtyIn) {
		this.side = sideIn;
		if(sideIn.equals("Bread Stick Bites")) {
			sidePrice = 2.00;
			sideQuantity = qtyIn;
		}
		if(sideIn.equals("Bread Sticks")) {
			sidePrice = 4.00;
			sideQuantity = qtyIn;
		} 
		if(sideIn.equals("Big Chocolate Chip Cookie")) {
			sidePrice = 4.00;
			sideQuantity = qtyIn;
		} 
		lineTotal = sidePrice * qtyIn;
		isSide = true;
	}
	public double addDrink(String drinkIn, String drinkSizeIn, int drinkQuantityIn) {
		this.drink = drinkIn;
		this.drinkSize = drinkSizeIn;
			drinkPrice = 1.00;
			drinkQuantity = drinkQuantityIn;
			isDrink = true;
			lineTotal = drinkPrice * drinkQuantity;
			return lineTotal;
	}

}
