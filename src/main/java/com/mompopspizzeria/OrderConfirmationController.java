package com.mompopspizzeria;

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
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
/*
 * Controller class that the order confirmation.  At this point the current customer has become the last customer.
 * @author Russell Geary
 * @author Garrett Herrera
 * @version 7.1 11/15/2022
 */
public class OrderConfirmationController extends MomPopsPizzeriaMain implements Initializable {
    @FXML
    ListView<String> orderSummeryList;
    @FXML
    private Label currentUserTextGlobal;
    @FXML
    private Label orderConfirmationText;
    /*
     * Returns to the initial application screen with a new order primed.
     */
    @FXML
    private void returnHomeAction(ActionEvent event){
        try {
            orderReset();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home-view.fxml")));
            Scene scene = new Scene(root, 1200, 750);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Mom and Pop's Pizzeria - Home");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    /*
     * Uses the last customer global variables as the current customer was logged out at the end of the ordering process
     * Populates the last customers order confirmation list
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<LineItemModel> lineArray = lastOrder.getLineItems();
        for (int i = 0; i < lineArray.size(); i++) {
            if (lineArray.get(i).isPizza) {
                StringBuilder newLine = new StringBuilder((i + 1) + ".) Pizza: " + lineArray.get(i).pizza + ", ");
                ArrayList<String> toppingsArray = lineArray.get(i).toppings;
                for (String s : toppingsArray) {
                    newLine.append(s).append(", ");
                }
                newLine.append("Qty: 1").append(", Price: ").append(DecimalFormat.getCurrencyInstance().format(lineArray.get(i).lineTotal));
                orderSummeryList.getItems().add(newLine.toString());
            } else if (!lineArray.get(i).isDrink) {
                if (lineArray.get(i).isSide) {
                    String newLine = (i + 1) + ".) Side: " + lineArray.get(i).side + ", Qty: " + lineArray.get(i).sideQuantity
                            + ", Price: " + DecimalFormat.getCurrencyInstance().format(lineArray.get(i).lineTotal);
                    orderSummeryList.getItems().add(newLine);
                }
            } else {
                String newLine = (i + 1) + ".) Drink: " + lineArray.get(i).drink + ", Size: " + lineArray.get(i).drinkSize
                        + ", Qty: " + lineArray.get(i).drinkQuantity + ", Price:  "
                        + DecimalFormat.getCurrencyInstance().format(lineArray.get(i).lineTotal);
                orderSummeryList.getItems().add(newLine);
            }
        }
        String spaceLine = "-------------------------------------------------------------------";
        String totalLine = "                                                                 Order Total: "
                + DecimalFormat.getCurrencyInstance().format(lastOrder.orderTotal);
        orderSummeryList.getItems().add(spaceLine);
        orderSummeryList.getItems().add(totalLine);
        currentUserTextGlobal.setText(lastCustomer.getFirstName() + " " + lastCustomer.getLastName());
        String orderType = "order";
        if(lastOrder.carryOut){
            orderType = "carry-Out order";
        } else if (lastOrder.delivery){
            orderType = "delivery order";
        } else if (lastOrder.pickup){
            orderType = "pick-Up order";
        }
        orderConfirmationText.setText("Thank you for your " + orderType + ", It will be in your hands shortly!");
    }
}

