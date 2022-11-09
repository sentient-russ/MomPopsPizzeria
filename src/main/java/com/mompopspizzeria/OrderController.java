package com.mompopspizzeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderController extends MomPopsPizzeriaMain implements Initializable {

    @FXML
    private final ToggleGroup orderTypeGroup = new ToggleGroup();//do not delete. Used by Gluon

    @FXML
    private Button addPizzaBtn;
    @FXML
    private Label listItemSelectedText;

    @FXML
    private Button updateBtnOrderScene;
    @FXML
    ListView<String> orderSummeryList;
    @FXML
    private Label currentUserTextGlobal;
    private String currentUserGlobal = MomPopsPizzeriaMain.currentUserGlobal;
    private Stage stage;
    private Scene scene;
    @FXML
    public void addPizzaBtnActionOrderScene(ActionEvent event)  {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("pizzaSelection-view.fxml"));
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
    @FXML
    public void addDrinkBtnActionOrderScene(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("drinkSelection-view.fxml"));
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
    @FXML
    public void addSideBtnActionOrderScene(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("sideSelection-view.fxml"));
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
    private void completePayBtnOrderEntryAction(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("customerPayment-view.fxml"));
            Scene scene = new Scene(root, 1200, 750);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Mom and Pop's Pizzeria - Customer Payment");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        currentUserTextGlobal.setText(currentUserGlobal);
    }

}

