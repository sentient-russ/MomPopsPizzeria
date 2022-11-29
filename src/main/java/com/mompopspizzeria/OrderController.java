package com.mompopspizzeria;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
/*
 * Controller class that handles functions related to the order summery screen.
 * @author Russell Geary
 * @author Garrett Herrera
 * @version 7.1 11/15/2022
 */
public class OrderController extends MomPopsPizzeriaMain implements Initializable {
    @FXML
    private final ToggleGroup orderTypeGroup = new ToggleGroup();//do not delete. Used by Gluon
    @FXML
    private ListView<String> orderSummeryList;
    @FXML
    private Label currentUserTextGlobal;
    @FXML
    private final String currentUserGlobal = MomPopsPizzeriaMain.currentUserGlobal;
    @FXML
    protected RadioButton carryOutRadioBtnOrderEntry;
    @FXML
    protected RadioButton deliveryRadioBtnOrderEntry;
    @FXML
    protected RadioButton pickupRadioBtnOrderEntry;
    static Stage stage;
    static Scene scene;
    /*
     * Transports the user to the pizza selection screen
     */
    @FXML
    public void addPizzaBtnActionOrderScene(ActionEvent event)  {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("pizzaSelection-view.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root,1200, 750);
            stage.setTitle("Mom and Pop's Pizzeria - Pizza Selection");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    /*
     * Transports the user to the drink selection screen
     */
    @FXML
    public void addDrinkBtnActionOrderScene(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("drinkSelection-view.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root,1200, 750);
            stage.setTitle("Mom and Pop's Pizzeria - Drink Selection");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    /*
     * Transports the user to the pizza selection screen
     */
    @FXML
    public void addSideBtnActionOrderScene(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sideSelection-view.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root,1200, 750);
            stage.setTitle("Mom and Pop's Pizzeria - Side Selection");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    /*
     * Transports the user to the home screen and optionally resets the order
     */
    @FXML
    private void returnHomeAction(ActionEvent event){
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 22));
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL",Font.PLAIN,35)));
        JFrame jframe = new JFrame();
        int result = JOptionPane.showConfirmDialog(jframe, "Do you want to reset this order? All selections will be lost!");
        if (result == 0){
            orderReset();
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home-view.fxml")));
                Scene scene = new Scene(root, 1200, 750);
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setTitle("Mom and Pop's Pizzeria - Home");
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        } else if(result == 1) {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home-view.fxml")));
                Scene scene = new Scene(root, 1200, 750);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Mom and Pop's Pizzeria - Home");
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }
    }
    /*
     * Transports the user to the employee payment screen if they logged in as an employee
     */
    @FXML
    private void completePayBtnOrderEntryAction(ActionEvent event){
        if(currentCustomer.isEmployee){
            try {
                Parent root = FXMLLoader.load(getClass().getResource("employeePayment-view.fxml"));
                Scene scene = new Scene(root, 1200, 750);
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setTitle("Mom and Pop's Pizzeria - Employee Payment Terminal");
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }else{
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("customerPayment-view.fxml")));
                Scene scene2 = new Scene(root, 1200, 750);
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setTitle("Mom and Pop's Pizzeria - Customer Payment");
                stage.setScene(scene2);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }
    }
    static private int removeIndex;
    /*
     * This method is called when the remove item button is clicked.  It will remove the most recent entry if one
     * has not been selected.
     */
    @FXML
    public void removeSelectedItemPayBtnOrderEntryAction(ActionEvent event){
        currentOrder.removeLine(removeIndex);
        orderEntryForm(event);
    }
    /*
     * Initializes the order summery window with the retained current orders parameters
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadOrderedList(url, resourceBundle);
        carryOutRadioBtnOrderEntry.setToggleGroup(orderTypeGroup);
        deliveryRadioBtnOrderEntry.setToggleGroup(orderTypeGroup);
        pickupRadioBtnOrderEntry.setToggleGroup(orderTypeGroup);
        getOrderType();
        orderTypeGroup.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            if(carryOutRadioBtnOrderEntry.isSelected()){
                currentOrder.carryOut = true;
                currentOrder.delivery = false;
                currentOrder.pickup = false;
                System.out.println("carry out selected");
            }
            if (deliveryRadioBtnOrderEntry.isSelected()){
                currentOrder.carryOut = false;
                currentOrder.delivery = true;
                currentOrder.pickup = false;
                System.out.println("delivery selected");
            }
            if (pickupRadioBtnOrderEntry.isSelected()){
                currentOrder.carryOut = false;
                currentOrder.delivery = false;
                currentOrder.pickup = true;
                System.out.println("pickup selected");
            }
        });
        orderSummeryList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                removeIndex =  orderSummeryList.getSelectionModel().getSelectedIndex();

            }
        });
        currentUserTextGlobal.setText(currentUserGlobal);
    }
    /*
     * Loads the summery list with the items that have been ordered.
     */
    public void loadOrderedList(URL url, ResourceBundle resourceBundle){
        ArrayList<LineItemModel> lineArray = currentOrder.getLineItems();
        for (int i = 0; i < lineArray.size(); i++) {

            if (lineArray.get(i).isPizza) {
                String newLine = (i + 1) + ".) Pizza: " + lineArray.get(i).pizza + ", ";
                ArrayList<String> toppingsArray = new ArrayList<>();
                toppingsArray = lineArray.get(i).toppings;
                for (int t = 0; t < toppingsArray.size(); t++) {
                    newLine = newLine + toppingsArray.get(t) + ", ";
                }
                newLine = newLine + "Qty: 1" + ", Price: " + DecimalFormat.getCurrencyInstance().format(lineArray.get(i).lineTotal);
                orderSummeryList.getItems().add(newLine);
            } else if (lineArray.get(i).isDrink) {

                String newLine = (i + 1) + ".) Drink: " + lineArray.get(i).drink + ", Size: " + lineArray.get(i).drinkSize + ", Qty: " + lineArray.get(i).drinkQuantity + ", Price:  " + DecimalFormat.getCurrencyInstance().format(lineArray.get(i).lineTotal);
                orderSummeryList.getItems().add(newLine);
            } else if (lineArray.get(i).isSide) {
                String newLine = (i + 1) + ".) Side: " + lineArray.get(i).side + ", Qty: " + lineArray.get(i).sideQuantity + ", Price: " + DecimalFormat.getCurrencyInstance().format(lineArray.get(i).lineTotal);
                orderSummeryList.getItems().add(newLine);
            }
        }
    }
    /*
     * Transports the user to the order entry screen
     */
    @FXML
    public void orderEntryForm(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("order-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root,1200, 750);
            stage.setTitle("Mom and Pop's Pizzeria - Order Entry");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    /*
     * Helper method that gets the current order type and sets it on the order controller view.
     */
    @FXML String getOrderType(){
        if(currentOrder.carryOut){
            carryOutRadioBtnOrderEntry.setSelected(true);
        }
        if (currentOrder.pickup){
            pickupRadioBtnOrderEntry.setSelected(true);
        }
        if (currentOrder.delivery){
            deliveryRadioBtnOrderEntry.setSelected(true);
        }
        String orderType = "";
        return orderType;
    }
}

