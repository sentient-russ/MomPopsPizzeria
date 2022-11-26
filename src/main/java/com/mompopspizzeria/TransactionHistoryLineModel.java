package com.mompopspizzeria;
/*
 * Object class to hold each historical transaction lines. This object is placed in an Array for reporting purposes.
 * @author Russell Geary
 * @version 7.1 11/15/2022
 */
public class TransactionHistoryLineModel {
	public String transDate;
	public String cusName;
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