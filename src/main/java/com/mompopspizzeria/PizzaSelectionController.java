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

public class PizzaSelectionController extends MomPopsPizzeriaMain implements Initializable {
    static String smallPizza = "Small Pizza";
    static String mediumPizza = "Medium Pizza";
    static String largePizza = "Large Pizza";
    static String xlargePizza = "Extra Large Pizza";
    static String thin = "Thin Crust";
    static String handTossed = "Hand Tossed";
    static String panned = "Panned";
    static String cheese = "Extra Cheese";
    static String pepperoni = "Pepperoni";
    static String sausage = "Sausage";
    static String ham = "Ham";
    static String greenPeppers = "Green Peppers";
    static String onions = "Onions";
    static String tomatos = "Tomato Slices";
    static String mushrooms = "Mushrooms";
    static String pineapple = "Pineapple";
    public int toppingsCount = 0;
    private String currentUserGlobal = MomPopsPizzeriaMain.currentUserGlobal;
    @FXML
    private Label currentUserTextGlobal;
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

    /*
     *Disables check boxes if called br a checkbox click to see if the dropdowns are null.  Displays
     * message and sets all checkboxes to unselected if null.
     *
     *
     */
    @FXML
    protected void checkboxValidationPizzaScene(){
        if(pizzaMenuDropdown.getValue() == null || pizzaCrust.getValue() == null){
            if (extraCheeseToppingChkBox.isSelected()) {extraCheeseToppingChkBox.setSelected(false);}
            if (pepperoniToppingChkBox.isSelected()) {pepperoniToppingChkBox.setSelected(false);}
            if (sausageToppingChkBox.isSelected()) {sausageToppingChkBox.setSelected(false);}
            if (hamToppingChkBox.isSelected()) {hamToppingChkBox.setSelected(false);}
            if (greenPeppersToppingChkBox.isSelected()) {greenPeppersToppingChkBox.setSelected(false);}
            if (onionsToppingChkBox.isSelected()) {onionsToppingChkBox.setSelected(false);}
            if (tomatoSlicesToppingChkBox.isSelected()) {tomatoSlicesToppingChkBox.setSelected(false);}
            if (mushroomsToppingChkBox.isSelected()) {mushroomsToppingChkBox.setSelected(false);}
            if (pineappleToppingChkBox.isSelected()) {pineappleToppingChkBox.setSelected(false);}
            pizzaSelectionValidationText.setText("Please select a size and crust first.");

        }else {
            pizzaSelectionValidationText.setText("");
            pizzaPriceUpdate();
        }
    }
    @FXML
    protected void pizzaPriceUpdate(){
        toppingsCount = 0;
        //forces pizza and crust selection before calculating toppings price.  not doing so results in a null pointer exception
        if(pizzaMenuDropdown.getValue() != null || pizzaCrust.getValue() != null) {
            try {
                if (extraCheeseToppingChkBox.isSelected()) {this.toppingsCount = this.toppingsCount + 1;}
                if (pepperoniToppingChkBox.isSelected()) {this.toppingsCount = this.toppingsCount + 1;}
                if (sausageToppingChkBox.isSelected()) {this.toppingsCount = this.toppingsCount + 1;}
                if (hamToppingChkBox.isSelected()) {this.toppingsCount = this.toppingsCount + 1;}
                if (greenPeppersToppingChkBox.isSelected()) {this.toppingsCount = this.toppingsCount + 1;}
                if (onionsToppingChkBox.isSelected()) {this.toppingsCount = this.toppingsCount + 1;}
                if (tomatoSlicesToppingChkBox.isSelected()) {this.toppingsCount = this.toppingsCount + 1;}
                if (mushroomsToppingChkBox.isSelected()) {this.toppingsCount = this.toppingsCount + 1;}
                if (pineappleToppingChkBox.isSelected()) {this.toppingsCount = this.toppingsCount + 1;}

                //removing the price text from the dropdown menu
                String pizzaWithPrice = pizzaMenuDropdown.getValue();
                String pizzaDiscription = pizzaWithPrice.replace(" $4.00", "").replace(" $6.00", "").replace(" $8.00", "").replace(" $10.00", "");

                //adjust toppings billed so the first topping is free.
                if (toppingsCount == 4) {
                    pizzaSelectionValidationText.setText("You have arrived; at the maximum number of toppings!");
                    if (!extraCheeseToppingChkBox.isSelected()) {extraCheeseToppingChkBox.setDisable(true);}
                    if (!pepperoniToppingChkBox.isSelected()) {pepperoniToppingChkBox.setDisable(true);}
                    if (!sausageToppingChkBox.isSelected()) {sausageToppingChkBox.setDisable(true);}
                    if (!hamToppingChkBox.isSelected()) {hamToppingChkBox.setDisable(true);}
                    if (!greenPeppersToppingChkBox.isSelected()) {greenPeppersToppingChkBox.setDisable(true);}
                    if (!onionsToppingChkBox.isSelected()) {onionsToppingChkBox.setDisable(true);}
                    if (!tomatoSlicesToppingChkBox.isSelected()) {tomatoSlicesToppingChkBox.setDisable(true);}
                    if (!mushroomsToppingChkBox.isSelected()) {mushroomsToppingChkBox.setDisable(true);}
                    if (!pineappleToppingChkBox.isSelected()) {pineappleToppingChkBox.setDisable(true);}
                }
                if (toppingsCount < 4) {
                    pizzaSelectionValidationText.setText("");
                    if (!extraCheeseToppingChkBox.isSelected()) {extraCheeseToppingChkBox.setDisable(false);}
                    if (!pepperoniToppingChkBox.isSelected()) {pepperoniToppingChkBox.setDisable(false);}
                    if (!sausageToppingChkBox.isSelected()) {sausageToppingChkBox.setDisable(false);}
                    if (!hamToppingChkBox.isSelected()) {hamToppingChkBox.setDisable(false);}
                    if (!greenPeppersToppingChkBox.isSelected()) {greenPeppersToppingChkBox.setDisable(false);}
                    if (!onionsToppingChkBox.isSelected()) {onionsToppingChkBox.setDisable(false);}
                    if (!tomatoSlicesToppingChkBox.isSelected()) {tomatoSlicesToppingChkBox.setDisable(false);}
                    if (!mushroomsToppingChkBox.isSelected()) {mushroomsToppingChkBox.setDisable(false);}
                    if (!pineappleToppingChkBox.isSelected()) {pineappleToppingChkBox.setDisable(false);}
                    pizzaSelectionValidationText.setText("");
                }
                if (toppingsCount >= 1) {
                    this.toppingsCount = this.toppingsCount - 1;
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

            } catch (NullPointerException e) {
                pizzaSelectionValidationText.setText("Select a pizza size and crust to see price updates.");
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
        currentUserTextGlobal.setText(currentUserGlobal);
    }
}