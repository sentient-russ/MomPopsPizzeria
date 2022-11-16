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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderConfirmationController extends MomPopsPizzeriaMain implements Initializable {

    @FXML
    ListView<String> orderSummeryList;
    @FXML
    private Label currentUserTextGlobal;
    private String currentUserGlobal = MomPopsPizzeriaMain.currentUserGlobal;
    private Stage stage;
    private Scene scene;

    @FXML
    private void returnHomeAction(ActionEvent event){
        try {
            orderReset();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<LineItemModel> lineArray = lastOrder.getLineItems();
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

                String newLine = (i + 1) + ".) Drink: " + lineArray.get(i).drink + ", Size: " + lineArray.get(i).drinkSize
                        + ", Qty: " + lineArray.get(i).drinkQuantity + ", Price:  "
                        + DecimalFormat.getCurrencyInstance().format(lineArray.get(i).lineTotal);
                orderSummeryList.getItems().add(newLine);
            } else if (lineArray.get(i).isSide) {
                String newLine = (i + 1) + ".) Side: " + lineArray.get(i).side + ", Qty: " + lineArray.get(i).sideQuantity
                        + ", Price: " + DecimalFormat.getCurrencyInstance().format(lineArray.get(i).lineTotal);
                orderSummeryList.getItems().add(newLine);
            }

        }
        String spaceLine = "------------------------------------------------------------------";
        String totalLine = "                                                                 Order Total: "
                + DecimalFormat.getCurrencyInstance().format(lastOrder.orderTotal);
        orderSummeryList.getItems().add(spaceLine);
        orderSummeryList.getItems().add(totalLine);
        currentUserTextGlobal.setText(currentUserGlobal);
    }
}

