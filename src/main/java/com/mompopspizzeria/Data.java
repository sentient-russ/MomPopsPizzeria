package com.mompopspizzeria;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/*
 * Please do not access this class directly. Use the interface methods to prevent unintended results.
 * This is a temporary class aimed at providing functionality to the pizza shop application using
 * text files to save data rather than AWS which is to be implemented in a future version.
 * @author Russell Geary
 * @version 1.1, 10/24/2022
 */
public class Data<T> implements DataAccessInterface<T> {
	ArrayList<CustomerModel> customers = new ArrayList<CustomerModel>();
	ArrayList<PizzaModel> pizzas = new ArrayList<>();
	ArrayList<CrustModel> crusts = new ArrayList<>();
	ArrayList<ToppingModel> toppings = new ArrayList<>();
	ArrayList<SideModel> sides = new ArrayList<>();
	ArrayList<DrinkModel> drinks = new ArrayList<>();
	ArrayList<DrinkSizeModel> drinkSizes = new ArrayList<>();
	ArrayList<OrderModel> orders = new ArrayList<>();
	ArrayList<TransactionHistoryLineModel> transHistory = new ArrayList<>();

	/*
	 * Default constructor containing calls to load the various system data
	 */
	public Data() {
		
		createSystemFiles();
		loadCustomerData();
		loadPizzas();
		loadCrusts();
		loadToppings();
		loadSides();
		loadDrinks();
		loadDrinkSizes();
	}

	/*
	 * Checks to see is data files exist and creates them if they do not
	 */
	public void createSystemFiles() {
		if (new File("customerlist.txt").isFile()) {
			// do nothing
		} else {
			try {
				saveToFile("customerlist.txt", "", false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (new File("transactionlist.txt").isFile()) {
			// do nothing
		} else {
			try {
				saveToFile("transactionlist.txt", "", false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * pass in the phone number as a string to check to see if it is found in the database
	 * @param the customers number as a string
	 * @return the complete CustomerModel of the customer that was found if not
	 * found customer id will be -1
	 */
	@Override
	public CustomerModel authenticateCustomer(String customerPhoneNumber, String customerPassword) {
		for (CustomerModel evalCustomer : customers) {
			if (evalCustomer.getPhoneNumber().equals(customerPhoneNumber) && evalCustomer.getPassword().equals(customerPassword)) {
				// returns the customer model for the customer if found with a customerId >= 1
				return evalCustomer;
			}
		}
		// sends back a blant customer model with a customerId of -1 if not found
		CustomerModel returnCustomerModel = new CustomerModel();
		return returnCustomerModel;
	}
	@Override
	public CustomerModel customerCheckPhoneNum(String customerPhoneNumberIn){
		for (CustomerModel evalCustomer : customers) {
			if (evalCustomer.getPhoneNumber().equals(customerPhoneNumberIn)) {
				// returns the customer model for the customer if found with a customerId >= 1
				return evalCustomer;
			}
		}
		// sends back a blant customer model with a customerId of -1 if not found
		CustomerModel returnCustomerModel = new CustomerModel();
		return returnCustomerModel;
	}

	/*
	 * pass in the customer model to add to the database
	 * @param complete CustomerModel to add to the database
	 * @return complete CustomerModel with an updated customerId >= 0 If customerId < 0 the record was not added
	 */
	@Override
	public CustomerModel addCustomer(CustomerModel customerIn) {
		// adds the customer with an updated customerId
		// checks to make sure the customer is not already in the list based on phone number and firstname. Returns a blank
		// model if they are found
		for (CustomerModel evalCu : customers) {
			if (evalCu.phoneNumber.equals(customerIn.phoneNumber)) {
				evalCu.customerId = -1;
				return evalCu;
			}
		}

		customerIn.customerId = customers.size();
		customers.add(customerIn);

		// finds the newly added customer in the list and returns a customer model
		// with the updated customerId
		for (CustomerModel evalCustomer : customers) {
			if (evalCustomer.getPhoneNumber().equals(customerIn.phoneNumber)) {

				// save new record to file
				String saveString = String.valueOf(evalCustomer.customerId) + "," + evalCustomer.firstName + ","
						+ evalCustomer.lastName + "," + evalCustomer.address1 + "," + evalCustomer.address2 + ","
						+ evalCustomer.city + "," + evalCustomer.state + "," + evalCustomer.zip + ","
						+ evalCustomer.phoneNumber + "," + evalCustomer.password + "\n";
				try {
					saveToFile("customerlist.txt", saveString, true);
				} catch (IOException e) {
					e.printStackTrace();
				}
				// returns the customer model for the customer if found with a customerId >= 1
				return evalCustomer;
			}
		}
		// sends back a blant customer model with a customerId of -1 if not added
		CustomerModel returnCustomerModel = new CustomerModel();
		return returnCustomerModel;
	}
	
	/*
	 * this method imports the customerlist.txt into memory (customers Array) for
	 * use in the program
	 */
	public void loadCustomerData() {

		File importFile = new File("customerlist.txt");
		try {
			Scanner scanner = new Scanner(importFile);
			while (scanner.hasNext()) {
				CustomerModel importedCustomer = new CustomerModel();
				String nextLine = scanner.nextLine();
				String[] items = nextLine.split("\\,");
				importedCustomer.customerId = Integer.parseInt(items[0]);
				importedCustomer.firstName = items[1];
				importedCustomer.lastName = items[2];
				importedCustomer.address1 = items[3];
				importedCustomer.address2 = items[4];
				importedCustomer.city = items[5];
				importedCustomer.state = items[6];
				importedCustomer.zip = items[7];
				importedCustomer.phoneNumber = items[8];
				importedCustomer.password = items[9];
				customers.add(importedCustomer);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * this method imports the transactionlist.txt into memory (transHistoryArray)
	 * for reporting functions
	 */
	public void loadTransactionData() {

		File importFile = new File("transactionlist.txt");
		try {
			Scanner scanner = new Scanner(importFile);
			while (scanner.hasNext()) {
				TransactionHistoryLineModel importedTransactionLineModel = new TransactionHistoryLineModel();
				String nextLine = scanner.nextLine();
				String[] items = nextLine.split("\\,");
				importedTransactionLineModel.transDate = items[0];
				importedTransactionLineModel.cusName = items[1];
				importedTransactionLineModel.lineTotal = items[2];
				transHistory.add(importedTransactionLineModel);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/*
	 *retreives all transaction history from thransactionlist.txt 
	 * @return an array list of transaction history models containing Date, Customer Name, Amount
	 */
	public ArrayList<TransactionHistoryLineModel> getAllTransactionHistory(){
		loadTransactionData();
		return this.transHistory;
	}
	
	/*
	 * this method can be called to initiate a customer data save before closing the
	 * application
	 */
	public void saveAllCustomerData() throws IOException {
		for (int i = 0; i < customers.size(); i++) {
			String outputText = String.valueOf(customers.get(i).customerId) + "," + customers.get(i).firstName + ","
					+ customers.get(i).lastName + "," + customers.get(i).address1 + "," + customers.get(i).address2
					+ "," + customers.get(i).city + "," + customers.get(i).state + "," + customers.get(i).zip + ","
					+ customers.get(i).phoneNumber + "," + customers.get(i).password;
			saveToFile("customerlist.txt", outputText, true);
		}
	}

	/*
	 * This method is intended to save the customer list
	 * @param the filename to save the data in, as a string
	 * @param one line of text to save in the file, as String 
	 * @param pass true if the file should be apended; false if only one line should be saved
	 */
	public static void saveToFile(String fileName, String text, boolean append) throws IOException {

		try {
			String str = text;
			File newTextFile = new File(fileName);
			FileWriter fw = new FileWriter(newTextFile, append);
			fw.write(str);
			fw.close();

		} catch (IOException iox) {
			iox.printStackTrace();
		}
	}

	/*
	 * this object class is used to build the static menu pizzas ArrayList
	 */
	public void loadPizzas() {
		PizzaModel small = new PizzaModel("Small Pizza", 4.00);
		PizzaModel medium = new PizzaModel("Medium Pizza", 6.00);
		PizzaModel large = new PizzaModel("Large Pizza", 8.00);
		PizzaModel xlarge = new PizzaModel("Extra Large Pizza", 10.00);
		pizzas.add(small);
		pizzas.add(medium);
		pizzas.add(large);
		pizzas.add(xlarge);
	}

	/*
	 * this object class is used to build the static menu crusts ArrayList
	 */
	public void loadCrusts() {
		CrustModel thin = new CrustModel("Thin", 0.00);
		CrustModel regular = new CrustModel("Regular", 0.00);
		CrustModel panned = new CrustModel("Panned", 0.00);
		crusts.add(thin);
		crusts.add(regular);
		crusts.add(panned);
	}

	/*
	 * this object class is used to build the static menu toppings ArrayList
	 */
	public void loadToppings() {
		ToppingModel cheese = new ToppingModel("Extra Cheese", 0.50);
		ToppingModel pepperoni = new ToppingModel("Pepperoni", 0.50);
		ToppingModel sausage = new ToppingModel("Sausage", 0.50);
		ToppingModel ham = new ToppingModel("Ham", 0.50);
		ToppingModel greenPepper = new ToppingModel("Green Peppers", 0.50);
		ToppingModel onion = new ToppingModel("Onions", 0.50);
		ToppingModel tomato = new ToppingModel("Tomato Slices", 0.50);
		ToppingModel mushroom = new ToppingModel("Mushrooms", 0.50);
		ToppingModel pineapple = new ToppingModel("Pineapple", 0.50);
		toppings.add(cheese);
		toppings.add(pepperoni);
		toppings.add(sausage);
		toppings.add(ham);
		toppings.add(greenPepper);
		toppings.add(onion);
		toppings.add(tomato);
		toppings.add(mushroom);
		toppings.add(pineapple);
	}

	/*
	 * this object class is used to build the static menu sides ArrayList
	 */
	public void loadSides() {
		SideModel bites = new SideModel("Bread Stick Bites", 2.00);
		SideModel sticks = new SideModel("Bread Sticks", 4.00);
		SideModel cookie = new SideModel("Big Chocolate Chip Cookie", 4.00);
		sides.add(bites);
		sides.add(sticks);
		sides.add(cookie);
	}

	/*
	 * this object class is used to build the static menu drinks ArrayList
	 */
	public void loadDrinks() {
		DrinkModel pepsi = new DrinkModel("Pepsi", 0.00);
		DrinkModel dietPepsi = new DrinkModel("Diet Pepsi", 0.00);
		DrinkModel orange = new DrinkModel("Orange", 0.00);
		DrinkModel dietOrange = new DrinkModel("Diet Orange", 0.00);
		DrinkModel rootbeer = new DrinkModel("Rootbeer", 0.00);
		DrinkModel dietRootbeer = new DrinkModel("Diet Rootbeer", 0.00);
		DrinkModel sierramist = new DrinkModel("Sierra Mist", 0.00);
		DrinkModel lemonade = new DrinkModel("Lemonade", 0.00);

		drinks.add(pepsi);
		drinks.add(dietPepsi);
		drinks.add(orange);
		drinks.add(dietOrange);
		drinks.add(rootbeer);
		drinks.add(dietRootbeer);
		drinks.add(sierramist);
		drinks.add(lemonade);
	}

	/*
	 * this object class is used to build the static menu drink size ArrayList
	 */
	public void loadDrinkSizes() {
		DrinkSizeModel small = new DrinkSizeModel("Small", 1.00);
		DrinkSizeModel medium = new DrinkSizeModel("Medium", 1.00);
		DrinkSizeModel large = new DrinkSizeModel("Large", 1.00);
		drinkSizes.add(small);
		drinkSizes.add(medium);
		drinkSizes.add(large);
	}

	/*
	 * method to get the pizza options 
	 * @return the list of pizza objects
	 */
	@Override
	public ArrayList<PizzaModel> getPizzas() {
		return this.pizzas;
	}

	/*
	 * method to get the crusts array 
	 * @return the list of crust objects
	 */
	@Override
	public ArrayList<CrustModel> getCrusts() {
		return this.crusts;
	}

	/*
	 * method to get the toppings array 
	 * @return the list of topping objects
	 */
	@Override
	public ArrayList<ToppingModel> getToppings() {
		return this.toppings;
	}

	/*
	 * method to get the sides array 
	 * @return the list of side objects
	 */
	@Override
	public ArrayList<SideModel> getSides() {
		return this.sides;
	}

	/*
	 * method to get the drink array 
	 * @return the list of drink objects
	 */
	@Override
	public ArrayList<DrinkModel> getDrinks() {
		return this.drinks;
	}

	/*
	 * method to get the drink sizes array 
	 * @return the list of drink size objects
	 */
	@Override
	public ArrayList<DrinkSizeModel> getDrinkSizes() {
		return this.drinkSizes;
	}

	/*
	 * gets the next order number for the orderModel 
	 * @return the next order number as an int
	 */
	public int getNextOrderId() {

		return orders.size();
	}

	/*
	 * adds and saves an order 
	 * @return true if added, false if not
	 */
	@Override
	public boolean saveOrder(OrderModel orderIn) {
		boolean result = false;
		orders.add(orderIn);
		saveToTransactionFile(orderIn);
		return result;
	}
	/*
	 * gets the current list of system transactions  
	 * @return ArrayList of Transactions containing ID, Date, Customer, Transaction
	 * Total Amount
	 */
	public ArrayList<TransactionHistoryLineModel> getTransactionHistory() {
		return transHistory;
	}

	/*
	 * Saves the transaction as Date, Customer, Amount to the transactionlist.txt record file.
	 */
	public void saveToTransactionFile(OrderModel orderIn){
			Date date = Calendar.getInstance().getTime();  
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
			String strDate = dateFormat.format(date); 
			String outputText = strDate + "," + orderIn.customerFirstName + " " + orderIn.customerLastName + ","
					+ DecimalFormat.getCurrencyInstance().format(orderIn.orderTotal) + "\n";
			try {
				saveToFile("transactionlist.txt", outputText, true);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
