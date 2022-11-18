package com.mompopspizzeria;
/*
 * This object is instantiated in MomPopsPizzeriaMain and remains accessible in the applications controllers through
 * the extension of MomPopsPizzeriaMain.
 * The order total is calculated automatically herein based on the lines objects that are passed to it.
 * @author Russell Geary
 * @version 1.1 10/24/2022
 */
import java.util.ArrayList;


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

	public OrderModel(CustomerModel customerIn) {
		orderId = getNextOrderId();
		customerId = customerIn.customerId;
		customerFirstName = customerIn.firstName;
		customerLastName = customerIn.lastName;		
	}
	public OrderModel addLineItem(LineItemModel lineIn) {
		lineItems.add(lineIn);
		calcOrderTotal();
		return this;
	}
	public double calcOrderTotal() {
		orderTotal = 0;
		for(int i = 0; i < lineItems.size(); i++) {
			orderTotal = orderTotal + lineItems.get(i).lineTotal;
		}
		return orderTotal;
	}
	public ArrayList<LineItemModel> getLineItems(){
		ArrayList<LineItemModel> resultsArray = new ArrayList<>();
		resultsArray = lineItems;
		return resultsArray;
	}

	public ArrayList<LineItemModel> removeLine(int indexToRemoveIn){
		lineItems.remove(indexToRemoveIn);
		return lineItems;
	}
}

	