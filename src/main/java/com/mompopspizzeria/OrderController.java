package com.mompopspizzeria;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderController extends MomPopsPizzeriaMain implements Initializable {

    static String smallPizza = "Small Pizza";
    static String mediumPizza = "Medium Pizza";
    static String largePizza = "Large Pizza";
    static String xlargePizza = "Extra Large Pizza";
    static String thin = "Thin Crust";
    static String handTossed = "Hand Tossed";
    static String panned = "Panned";
    static String cheese = "Cheese";
    static String pepperonie = "Pepperonie";
    static String sausage = "Sausage";
    static String ham = "Ham";
    static String greenPeppers = "Green Peppers";
    static String onions = "Onions";
    static String tomatos = "Tomato Slices";
    static String mushrooms = "Mushrooms";
    static String pineapple = "Pineapple";
    static String smallDrink = "Small";
    static String mediumDrink = "Medium";
    static String largeDrink = "Large";
    static String pepsi = "Pepesi";
    static String dietPepsi = "Diet Pepsi";
    static String orange = "Orange";
    static String dietOrange = "Diet Orange";
    static String rootbeer = "Rootbeer";
    static String dietRootbeer = "Diet Rootbeer";
    static String sierraMist = "Sierra Mist";
    static String lemonade = "Lemonade";
    static String breadBites = "Bread Stick Bites";
    static String breadSticks = "Bread Sticks";
    static String cookie = "Big Chocolate Chip Cookie";
    @FXML
    private final ToggleGroup orderTypeGroup = new ToggleGroup();//do not delete. Used by Gluon

    @FXML
    private Button backBtnOrderScene;
    @FXML
    private Button homeBtnOrderScene;
    @FXML
    private Button cancelBtnOrderScene;
    @FXML
    private Button payBtnOrderScene;
    @FXML
    private Button addPizzaBtn;
    @FXML
    protected void cancelBtnActionOrderScene() {
        Stage stage = (Stage) cancelBtnOrderScene.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void homeBtnActionOrderScene() {
        Stage stage = (Stage) homeBtnOrderScene.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void backBtnActionOrderScene() {
        Stage stage = (Stage) backBtnOrderScene.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void payBtnActionOrderScene() {
        Stage stage = (Stage) payBtnOrderScene.getScene().getWindow();
        stage.close();
    }

    ///////////////////////////////////////////////////////////////////

    @FXML
    private Label listItemSelectedText;
    @FXML
    private Button updateBtnOrderScene;
    @FXML
    ListView<String> orderSummeryList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] list = {"Medium Pizza Panned Sausage Mushrooms Pineapple Onions $8.25", "Bread Sticks $4.00","Pepesi Small $1.00","Medium Pizza Panned Cheese $6.00"};
        for(String s: list){
            orderSummeryList.getItems().add(s);
        }

    }
    /////////////////////////////////////////////////////////////////
    @FXML
    public void addPizzaBtnActionOrderScene() throws IOException {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("pizzaSelection-view.fxml"));
            Stage pizzaSelectionStage = new Stage();
            pizzaSelectionStage.setTitle("Mom and Pop's Pizza App - Pizza Selection");
            pizzaSelectionStage.setScene(new Scene(root, 1200, 750));
            pizzaSelectionStage.show();



        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    static void RussellTestMethod(){


        // example of how to look up a customer. Returns a complete customerModel object
        // containing all of their info if they exist. customerId -1 if not values are
        // null
        CustomerModel resultModel1 = dataAccess.authenticateCustomer("4043227517","Password");

        consolePrintCustomer(resultModel1);


        //example of how to add a new customer. If they already exist returns a
        //response with a customerId of -1 and null values if they were added returns
        //the model with customerId >= 0
        /*
        CustomerModel newCustomer = new
                CustomerModel("Mark", "Twain", "345 Nowhere Lane", "", "Atlanta", "GA", "30001", "4045556666", "Password");
        CustomerModel resultModel2 =
                dataAccess.addCustomer(newCustomer);

        consolePrintCustomer(resultModel2);
        */

        //example of how to get the pizza options with prices. See helper class for
        //data manipulation example if desired.
        ArrayList<PizzaModel> pizzas =
                dataAccess.getPizzas();

        consolePrintPizzas(pizzas);

        //example of how to get the crust options with prices. See helper class for
        //data manipulation example if desired.
        ArrayList<CrustModel> crusts =
                dataAccess.getCrusts();

        consolePrintCrusts(crusts);

        //example of how to get the toppings options with prices. See helper class
        //for data manipulation example if desired.
        ArrayList<ToppingModel> toppings =
                dataAccess.getToppings();

        consolePrintToppings(toppings);

        //example of how to get the drinks options with prices. See helper class for
        //data manipulation example if desired.
        ArrayList<DrinkModel> drinks =
                dataAccess.getDrinks();

        consolePrintDrinks(drinks);

        //example of how to get the drink sizes options with prices. See helper class
        //for data manipulation example if desired.
        ArrayList<DrinkSizeModel>
                drinkSizes = dataAccess.getDrinkSizes();

        consolePrintDrinkSizes(drinkSizes);

        // creates an order
        OrderModel newOrder = new OrderModel(resultModel1); // note that this customer model was found in the above
        // example and returned with an customerId != -1 This is
        // required to create the order.

        ArrayList<String> newOrderToppings = new ArrayList<>();// pizza 1
        newOrderToppings.add(sausage);
        newOrderToppings.add(mushrooms);
        newOrderToppings.add(pineapple);
        newOrderToppings.add(onions);
        LineItemModel newLine1 = new LineItemModel();
        newLine1.addPizza(mediumPizza,panned,newOrderToppings);
        newOrder.addLineItem(newLine1);

        LineItemModel newLine2 = new LineItemModel();// side 1
        newLine2.addSide(breadSticks);
        newOrder.addLineItem(newLine2);

        LineItemModel newLine3 = new LineItemModel();// drink 1
        newLine3.addDrink(pepsi,smallDrink);
        newOrder.addLineItem(newLine3);

        LineItemModel newLine4 = new LineItemModel();// drink 2
        newLine4.addDrink(sierraMist,largeDrink);
        newOrder.addLineItem(newLine4);

        LineItemModel newLine5 = new LineItemModel();// side 2
        newLine5.addSide(breadBites);
        newOrder.addLineItem(newLine5);

        ArrayList<String> newOrderToppings2 = new ArrayList<>();// pizza 2
        newOrderToppings2.add(cheese);
        LineItemModel newLine6 = new LineItemModel();
        newLine6.addPizza(mediumPizza,panned,newOrderToppings2);
        newOrder.addLineItem(newLine6);

        dataAccess.addOrder(newOrder);

        consolePrintOrder(newOrder);

        printTransactionHistorySummery();


    }
    /*
     * helper method to print a CustmerModel to the console for testing
     * @author Russell Geary
     * @param customerModel to be printed
     */
    static void consolePrintCustomer(CustomerModel customerIn) {
        if (customerIn.customerId < 0) {
            System.out.println("Failure! - Customer model was not entered or found in the database. If adding a customer, check to see if the record already existed.");
        } else {
            System.out.println("Success! - Customer model was entered or found in the database.");
        }
        System.out.println("Id: " + customerIn.customerId);
        System.out.println("First Name: " + customerIn.firstName);
        System.out.println("Last Name: " + customerIn.lastName);
        System.out.println("Address Line 1: " + customerIn.address1);
        System.out.println("Address Line 2: " + customerIn.address2);
        System.out.println("City: " + customerIn.city);
        System.out.println("State: " + customerIn.state);
        System.out.println("Zip: " + customerIn.zip);
        System.out.println("Phone Number: " + customerIn.phoneNumber);
        System.out.println("");
    }

    /*
     * helper method to print the pizza menu options list to the console for testing
     * @author Russell Geary
     * @param menu PizzaModel to be printed
     */
    static void consolePrintPizzas(ArrayList<PizzaModel> menuIn) {
        System.out.println("The pizzas list includes");
        for (int i = 0; i < menuIn.size(); i++) {
            System.out.print(menuIn.get(i).description + " ");
            System.out.printf("$%,.2f\n", menuIn.get(i).price);
        }
        System.out.println("");
    }

    /*
     * helper method to print the crust menu options list to the console for testing
     * @author Russell Geary
     * @param menu CrustModel to be printed
     */
    static void consolePrintCrusts(ArrayList<CrustModel> menuIn) {
        System.out.println("The crusts list includes");
        for (int i = 0; i < menuIn.size(); i++) {
            System.out.print(menuIn.get(i).description + " ");
            System.out.printf("$%,.2f\n", menuIn.get(i).price);
        }
        System.out.println("");
    }

    /*
     * helper method to print the topping menu options list to the console for testing
     * @author Russell Geary
     * @param menu ToppingModel to be printed
     */
    static void consolePrintToppings(ArrayList<ToppingModel> menuIn) {
        System.out.println("The toppings list includes");
        for (int i = 0; i < menuIn.size(); i++) {
            System.out.print(menuIn.get(i).description + " ");
            System.out.printf("$%,.2f\n", menuIn.get(i).price);
        }
        System.out.println("");
    }

    /*
     * helper method to print the drink menu options list to the console for testing
     * @author Russell Geary
     * @param menu DrinkModel to be printed
     */
    static void consolePrintDrinks(ArrayList<DrinkModel> menuIn) {
        System.out.println("The drinks list includes");
        for (int i = 0; i < menuIn.size(); i++) {
            System.out.print(menuIn.get(i).description + " ");
            System.out.printf("$%,.2f\n", menuIn.get(i).price);
        }
        System.out.println("");
    }

    /*
     * helper method to print the drink size menu options list to the console for testing
     * @author Russell Geary
     * @param menu DrinkSize to be printed
     */
    static void consolePrintDrinkSizes(ArrayList<DrinkSizeModel> menuIn) {
        System.out.println("The drink sizes list includes");
        for (int i = 0; i < menuIn.size(); i++) {
            System.out.print(menuIn.get(i).description + " ");
            System.out.printf("$%,.2f\n", menuIn.get(i).price);
        }
        System.out.println("");
    }

    /*
     * helper method to print the pizza line item to the console for testing
     * @author Russell Geary
     * @param menu line to be printed
     */
    static void consolePrintLinePizza(LineItemModel lineIn) {
        System.out.print(lineIn.pizza + " " + lineIn.crust + " ");
        for (int i = 0; i < lineIn.toppings.size(); i++) {
            System.out.print(lineIn.toppings.get(i) + " ");
        }
        System.out.printf("$%,.2f", lineIn.lineTotal);
        System.out.println("");
    }

    /*
     * helper method to print the drink line item to the console for testing
     * @author Russell Geary
     * @param menu line to be printed
     */
    static void consolePrintLineDrink(LineItemModel lineIn) {
        System.out.print(lineIn.drink + " " + lineIn.drinkSize + " ");
        System.out.printf("$%,.2f", lineIn.lineTotal);
        System.out.println("");
    }

    /*
     * helper method to print the side line item to the console for testing
     * @author Russell Geary
     * @param menu line to be printed
     */
    static void consolePrintLineSide(LineItemModel lineIn) {
        System.out.print(lineIn.side + " ");
        System.out.printf("$%,.2f", lineIn.lineTotal);
        System.out.println("");
    }

    /*
     * helper method to print an order
     * @author Russell Geary
     * @param OrderModel to be printed
     */
    static void consolePrintOrder(OrderModel orderIn) {
        System.out.println("Customer Id: " + orderIn.customerId);
        System.out.println("Order Id: " + orderIn.orderId);
        for (int i = 0; i < orderIn.lineItems.size(); i++) {
            System.out.print("Line item " + i + ": ");
            if (orderIn.lineItems.get(i).isPizza) {
                consolePrintLinePizza(orderIn.lineItems.get(i));
            }
            if (orderIn.lineItems.get(i).isDrink) {
                consolePrintLineDrink(orderIn.lineItems.get(i));
            }
            if (orderIn.lineItems.get(i).isSide) {
                consolePrintLineSide(orderIn.lineItems.get(i));
            }
        }
        System.out.print("Order Total: ");
        System.out.printf("$%,.2f\n", orderIn.calcOrderTotal());
        System.out.println("");
    }

    /*
     * helper method to print transaction history for reporting
     * @author Russell Geary
     * @param OrderModel to be printed
     */
    static void printTransactionHistorySummery() {
        ArrayList<TransactionHistoryLineModel> transactionHistory = dataAccess.getAllTransactionHistory();
        System.out.println("------------------Transaction Summery Report------------------");
        for (TransactionHistoryLineModel e : transactionHistory) {
            System.out.println(e.transDate + " " + e.cusName + " " + e.lineTotal);
        }
        System.out.println("------------------Transaction Summery Report------------------");
    }



}

