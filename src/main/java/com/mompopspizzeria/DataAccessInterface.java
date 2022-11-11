package com.mompopspizzeria;
import java.util.ArrayList;

/*
 * Instantiate this interface to interact with the system data
 * @author Russell Geary
 * @version 1.1, 10/24/2022
 */
public interface DataAccessInterface<T> {
	
	
	/*
	 * pass in the phone number and password as strings to check to see if they are found in the database
	 * @param customerPhoneNum the customers number as a string
	 * @param customerPassword the customers password as a string
	 * @return the complete CustomerModel of the customer that was found if not found customer id will be -1
	 */
	public CustomerModel authenticateCustomer(String customerPhoneNumber, String customerPassword);
	
	
	/*
	 * pass in the customer model to add to the database
	 * @param complete CustomerModel to add to the database
	 * @return complete CustomerModel with an updated customerId >= 1 If custmerId < 1 the record was not added
	 */
	public CustomerModel addCustomer(CustomerModel customerIn);

	/*
	 * gets the pizza menu options with pricing
	 * @return ArrayList of pizza menu objects
	 */
	public ArrayList<PizzaModel> getPizzas();
	/*
	 * gets the pizza menu options with pricing
	 * @return ArrayList of pizza menu objects
	 */
	public ArrayList<CrustModel> getCrusts();
	/*
	 * gets the crust menu options with pricing
	 * @return ArrayList of crust menu objects
	 */
	public ArrayList<ToppingModel> getToppings();
	/*
	 * gets the topping menu options with pricing
	 * @return ArrayList of topping menu objects
	 */
	public ArrayList<SideModel> getSides();
	/*
	 * gets the side menu options with pricing
	 * @return ArrayList of side menu objects
	 */
	public ArrayList<DrinkModel> getDrinks();
	/*
	 * gets the drink size menu options with pricing
	 * @return ArrayList of drink size menu objects
	 */
	public ArrayList<DrinkSizeModel> getDrinkSizes();
	/*
	 * saves an order
	 * @return true if added, false if not
	 */
	public boolean saveOrder(OrderModel orderIn);
	/*
	 *retreives all transaction history from thransactionlist.txt. Note data stored as strings so they will need to be converted to be filtered and sorted
	 * @return an array list of transaction history models containing Date, Customer Name, Amount
	 */
	public ArrayList<TransactionHistoryLineModel> getAllTransactionHistory();
	
}
