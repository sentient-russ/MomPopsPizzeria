package com.mompopspizzeria;
/*
 * This object is insantiated at the begining of the ordering process and passed from one view to the next until the order is complete.
 * The order total is calculated autmatically herein based on the lines objects that are passed to it.
 * @author Russell Geary
 * @version 1.1 10/24/2022
 */
import java.util.ArrayList;


@SuppressWarnings("rawtypes")
public class OrderModel extends Data {
	ArrayList<LineItemModel> lineItems = new ArrayList<>();
	int orderId;
	int customerId;
	String customerFirstName;
	String customerLastName;
	double orderTotal;
	boolean pickup = false;
	boolean delivery = false;

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
	
}

	