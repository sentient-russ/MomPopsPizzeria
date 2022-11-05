package com.mompopspizzeria;
/*
 * Object class to hold each historical transaction lines. This object is placed in an Array for reporting purposes.
 * @Author Russell Geary
 * @version 1.1 10/24/2022
 */
public class TransactionHistoryLineModel {
	String transDate;
	String cusName;
	String lineTotal;
	public TransactionHistoryLineModel() {
		
	}
	/*
	 * Default constructor to build out the transaction history line
	 */
	public TransactionHistoryLineModel(String dateIn, String cusNameIn, String lineTotalIn) {
		transDate = dateIn;
		cusName = cusNameIn;
		lineTotal = lineTotalIn;
	}
}	