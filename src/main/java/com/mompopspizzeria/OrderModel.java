package com.mompopspizzeria;

import java.util.ArrayList;
/*
 * This object is instantiated in MomPopsPizzeriaMain and remains accessible in the applications controllers through
 * the extension of MomPopsPizzeriaMain.
 * The order total is calculated automatically herein based on the lines objects that are passed to it.
 * @author Russell Geary
 * @version 7.1 11/15/2022
 */
@SuppressWarnings("rawtypes")
public class OrderModel extends Data {
	ArrayList<LineItemModel> lineItems = new ArrayList<>();
	public int orderId;
	public int customerId;
	String customerFirstName;
	String customerLastName;
	String currentOrderChargeAmount;
	public double orderTotal;
	public boolean pickup = false;
	public boolean delivery = false;
	public boolean carryOut = false;
	/*
	 * Adds a current customer object so that it is associated with the current order.
	 * @param customerIn The customer model associated with the order.
	 */
	public OrderModel(CustomerModel customerIn) {
		orderId = getNextOrderId();
		customerId = customerIn.customerId;
		customerFirstName = customerIn.firstName;
		customerLastName = customerIn.lastName;		
	}
	/*
	 * Adds the line item objects to the order
	 * @param lineIn The line to be added
	 * @return the order with an updated total
	 */
	public OrderModel addLineItem(LineItemModel lineIn) {
		lineItems.add(lineIn);
		calcOrderTotal();
		return this;
	}
	/*
	 * Adds the line item objects to the order
	 * @param lineIn The line to be added
	 * @return The new order total
	 */
	public double calcOrderTotal() {
		orderTotal = 0;
		for(int i = 0; i < lineItems.size(); i++) {
			orderTotal = orderTotal + lineItems.get(i).lineTotal;
		}
		return orderTotal;
	}
	/*
	 * Getter method for the line items
	 * @return An array of line items
	 */
	public ArrayList<LineItemModel> getLineItems(){
		ArrayList<LineItemModel> resultsArray = lineItems;
		return resultsArray;
	}
	/*
	 * Removes a line based on its index
	 * @param The index as an int to be removed
	 * @return An array consisting of the remaining line items
	 */
	public ArrayList<LineItemModel> removeLine(int indexToRemoveIn){
		lineItems.remove(indexToRemoveIn);
		calcOrderTotal();
		return lineItems;
	}
}

	