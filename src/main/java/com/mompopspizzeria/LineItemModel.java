package com.mompopspizzeria;
/*
 * This object is inserted into the order object. Line total calculation is handled herein.
 * @author Russell Geary
 * @version 1.1 10/24/2022
 */
import java.util.ArrayList;

public class LineItemModel extends MomPopsPizzeriaMain{
	public String pizza;
	public String crust;
	public ArrayList<String> toppings = new ArrayList<>();
	public String side;
	public String drink;
	public String drinkSize;
	private double pizzaPrice;
	private double crustPrice;
	private double toppingPrice;
	private double sidePrice;
	private double drinkPrice;
	public double lineTotal;
	public int drinkQuantity;
	public int sideQuantity;
	public boolean drinkOrdered = false;
	public boolean isPizza = false;
	public boolean isDrink = false;
	public boolean isSide = false;
	/*
	 * Default constructor
	 */
	public LineItemModel() {
	}
	/*
	 * Adds a pizza to the current line. Calculates line total as the pizza is added.
	 * @param pizzaIn the pizza size as a String
	 * @param crustIn the crust size as a String
	 * @param toppingsIn the crust type as a String
	 * @param toppingsIn an ArrayList of toppings as Strings
	 */
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
	/*
	 * Adds a side to the current line. Calculates line total as the side is added.
	 * @param sideIn the side description as a String
	 * @param qtyIn the quantity as an integer
	 */
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
	/*
	 * Adds a side to the current line. Calculates line total as the side is added.
	 * @param sideIn the side description as a String
	 * @param qtyIn the quantity as an integer
	 */
	public double addDrink(String drinkIn, String drinkSizeIn, int drinkQuantityIn) {
		this.drink = drinkIn;
		this.drinkSize = drinkSizeIn;
			drinkPrice = 1.00;
			drinkQuantity = drinkQuantityIn;
			isDrink = true;
			lineTotal = drinkPrice * drinkQuantity;
			drinkOrdered = true;
			return lineTotal;
	}
}
