package com.mompopspizzeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class pizzaSelectionController extends MomPopsPizzeriaMain implements Initializable {
    static String smallPizza = "Small Pizza";
    static String mediumPizza = "Medium Pizza";
    static String largePizza = "Large Pizza";
    static String xlargePizza = "Extra Large Pizza";
    static String thin = "Thin Crust";
    static String handTossed = "Hand Tossed";
    static String panned = "Panned";
    static String cheese = "Cheese";
    static String pepperoni = "Pepperoni";
    static String sausage = "Sausage";
    static String ham = "Ham";
    static String greenPeppers = "Green Peppers";
    static String onions = "Onions";
    static String tomatos = "Tomato Slices";
    static String mushrooms = "Mushrooms";
    static String pineapple = "Pineapple";

    @FXML
    private Label pizzaSelectionValidationText;
    @FXML
    private Label pizzaTotalText;
    @FXML
    private CheckBox extraCheeseToppingChkBox;
    @FXML
    private CheckBox pepperoniToppingChkBox;
    @FXML
    private CheckBox sausageToppingChkBox;
    @FXML
    private CheckBox hamToppingChkBox;
    @FXML
    private CheckBox greenPeppersToppingChkBox;
    @FXML
    private CheckBox onionsToppingChkBox;
    @FXML
    private CheckBox tomatoSlicesToppingChkBox;
    @FXML
    private CheckBox mushroomsToppingChkBox;
    @FXML
    private CheckBox pineappleToppingChkBox;
    @FXML
    private ComboBox<String> pizzaMenuDropdown;
    @FXML
    private ComboBox<String> pizzaCrust;

    @FXML
    protected void  addPizzaBtnActionPizzaSelection(ActionEvent event){

        String pizzaWithPrice = pizzaMenuDropdown.getValue();
        String pizzaDiscription = pizzaWithPrice.replace(" $4.00","")
                .replace(" $6.00","").replace(" $8.00", "")
                .replace(" $10.00","");
        String crustWithPrice = pizzaCrust.getValue();
        String crustDiscription = pizzaWithPrice.replace(" $0.00", "");

        ArrayList<String> newOrderToppings = new ArrayList<>();
        if(extraCheeseToppingChkBox.isSelected()){
            newOrderToppings.add(cheese);
        }
        if(pepperoniToppingChkBox.isSelected()){
            newOrderToppings.add(pepperoni);
        }
        if(sausageToppingChkBox.isSelected()){
            newOrderToppings.add(sausage);
        }
        if(hamToppingChkBox.isSelected()){
            newOrderToppings.add(ham);
        }
        if(greenPeppersToppingChkBox.isSelected()){
            newOrderToppings.add(greenPeppers);
        }
        if(onionsToppingChkBox.isSelected()){
            newOrderToppings.add(onions);
        }
        if(tomatoSlicesToppingChkBox.isSelected()){
            newOrderToppings.add(tomatos);
        }
        if(mushroomsToppingChkBox.isSelected()){
            newOrderToppings.add(mushrooms);
        }
        if(pineappleToppingChkBox.isSelected()){
            newOrderToppings.add(pineapple);
        }
        LineItemModel newLine = new LineItemModel();
        newLine.addPizza(pizzaDiscription, crustDiscription, newOrderToppings);
        currentOrder.addLineItem(newLine);
        String total = String.valueOf(currentOrder.orderTotal);
        pizzaTotalText.setText(total);

        try {
            Parent root = FXMLLoader.load(getClass().getResource("order-view.fxml"));
            Scene scene = new Scene(root, 1200, 750);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Mom and Pop's Pizzeria - Order Entry");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    protected void pizzaPriceUpdate(){

        //forces pizza and crust selection before calculating toppings price.  not doing so results in a null pointer exception
        if(pizzaMenuDropdown.getValue() == null || pizzaCrust.getValue() == null) {
            pizzaSelectionValidationText.setText("Please select a pizza size and crust before selecting your toppings");
            if (extraCheeseToppingChkBox.isSelected()) {
                extraCheeseToppingChkBox.setSelected(false);
            }
            if (pepperoniToppingChkBox.isSelected()) {
                pepperoniToppingChkBox.setSelected(false);
            }
            if (sausageToppingChkBox.isSelected()) {
                sausageToppingChkBox.setSelected(false);
            }
            if (hamToppingChkBox.isSelected()) {
                hamToppingChkBox.setSelected(false);
            }
            if (greenPeppersToppingChkBox.isSelected()) {
                greenPeppersToppingChkBox.setSelected(false);
            }
            if (onionsToppingChkBox.isSelected()) {
                onionsToppingChkBox.setSelected(false);
            }
            if (tomatoSlicesToppingChkBox.isSelected()) {
                tomatoSlicesToppingChkBox.setSelected(false);
            }
            if (mushroomsToppingChkBox.isSelected()) {
                mushroomsToppingChkBox.setSelected(false);
            }
            if (pineappleToppingChkBox.isSelected()) {
                pineappleToppingChkBox.setSelected(false);
            }

        }else {
            try {
                int toppingsCount = 0;

                if (extraCheeseToppingChkBox.isSelected()) {
                    toppingsCount = toppingsCount + 1;
                }
                if (pepperoniToppingChkBox.isSelected()) {
                    toppingsCount = toppingsCount + 1;
                }
                if (sausageToppingChkBox.isSelected()) {
                    toppingsCount = toppingsCount + 1;
                }
                if (hamToppingChkBox.isSelected()) {
                    toppingsCount = toppingsCount + 1;
                }
                if (greenPeppersToppingChkBox.isSelected()) {
                    toppingsCount = toppingsCount + 1;
                }
                if (onionsToppingChkBox.isSelected()) {
                    toppingsCount = toppingsCount + 1;
                }
                if (tomatoSlicesToppingChkBox.isSelected()) {
                    toppingsCount = toppingsCount + 1;
                }
                if (mushroomsToppingChkBox.isSelected()) {
                    toppingsCount = toppingsCount + 1;
                }
                if (pineappleToppingChkBox.isSelected()) {
                    toppingsCount = toppingsCount + 1;
                }
                //removing the price text from the dropdown menu
                String pizzaWithPrice = pizzaMenuDropdown.getValue();
                String pizzaDiscription = pizzaWithPrice.replace(" $4.00", "")
                        .replace(" $6.00", "").replace(" $8.00", "")
                        .replace(" $10.00", "");
                //adjust toppings billed so the first topping is free.
                if(toppingsCount >= 1){
                    toppingsCount = toppingsCount - 1;
                }
                double pizzaPrice;
                double toppingPrice;
                if (pizzaDiscription.equals("Small Pizza")) {
                    pizzaPrice = 4.00;
                    toppingPrice = 1 * 0.50;
                    double total = toppingsCount * toppingPrice + pizzaPrice;
                    String totalAsString = String.valueOf(DecimalFormat.getCurrencyInstance().format(total));
                    pizzaTotalText.setText(totalAsString);
                    pizzaSelectionValidationText.setText("");
                }
                if (pizzaDiscription.equals("Medium Pizza")) {
                    pizzaPrice = 6.00;
                    toppingPrice = 1.5 * 0.50;
                    double total = toppingsCount * toppingPrice + pizzaPrice;
                    String totalAsString = String.valueOf(DecimalFormat.getCurrencyInstance().format(total));
                    pizzaTotalText.setText(totalAsString);
                    pizzaSelectionValidationText.setText("");
                }
                if (pizzaDiscription.equals("Large Pizza")) {
                    pizzaPrice = 8.00;
                    toppingPrice = 2 * 0.50;
                    double total = toppingsCount * toppingPrice + pizzaPrice;
                    String totalAsString = String.valueOf(DecimalFormat.getCurrencyInstance().format(total));
                    pizzaTotalText.setText(totalAsString);
                    pizzaSelectionValidationText.setText("");
                }
                if (pizzaDiscription.equals("Extra Large Pizza")) {
                    pizzaPrice = 10.00;
                    toppingPrice = 2.5 * 0.50;
                    double total = toppingsCount * toppingPrice + pizzaPrice;
                    String totalAsString = String.valueOf(DecimalFormat.getCurrencyInstance().format(total));
                    pizzaTotalText.setText(totalAsString);
                    pizzaSelectionValidationText.setText("");
                }
            }catch (NullPointerException e){
                pizzaSelectionValidationText.setText("Please select a pizza size and crust to see price updates.");
            }
        }
    }


    @FXML
    private Stage stage;
    @FXML
    private void returnHomeAction(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("home-view.fxml"));
            Scene scene = new Scene(root, 1200, 750);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Mom and Pop's Pizzeria - Home");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    @FXML
    private void backBtnActionPizzaSelectionScene(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("order-view.fxml"));
            Scene scene = new Scene(root, 1200, 750);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Mom and Pop's Pizzeria - Order Entry");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    @FXML
    protected void cancelBtnActionPizzaSelectionScene(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("order-view.fxml"));
            Scene scene = new Scene(root, 1200, 750);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Mom and Pop's Pizzeria - Order Entry");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //add pizza menu items to dropdown
        ArrayList<PizzaModel> pizzas = dataAccess.getPizzas();
        for(int i = 0; i < pizzas.size(); i++){

            String menuItem = pizzas.get(i).description + " " + DecimalFormat.getCurrencyInstance().format(pizzas.get(i).price);
            pizzaMenuDropdown.getItems().add(menuItem);
        }
        //add crust menu items to dropdown
        ArrayList<CrustModel> crusts = dataAccess.getCrusts();
        for(int i = 0; i < crusts.size(); i++){

            String menuItem = crusts.get(i).description + " " + DecimalFormat.getCurrencyInstance().format(crusts.get(i).price);
            pizzaCrust.getItems().add(menuItem);
        }
    }
}
