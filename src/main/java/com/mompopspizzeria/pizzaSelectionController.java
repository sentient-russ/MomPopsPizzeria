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
        boolean isValid = false;
        if(isValid){
            //add items to order
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

        } else{
            //give error to validation text
        }
    }
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
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

            String menuItem = pizzas.get(i).description + " $"+ pizzas.get(i).price;
            pizzaMenuDropdown.getItems().add(menuItem);
        }
        //add crust menu items to dropdown
        ArrayList<CrustModel> crusts = dataAccess.getCrusts();
        for(int i = 0; i < crusts.size(); i++){

            String menuItem = crusts.get(i).description + " $"+ crusts.get(i).price;
            pizzaCrust.getItems().add(menuItem);
        }

    }
}
