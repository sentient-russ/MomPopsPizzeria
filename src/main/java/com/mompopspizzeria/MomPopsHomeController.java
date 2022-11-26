package com.mompopspizzeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/*
 * Controller class that handles the initial program screens buttons and scene changes
 * @author Russell Geary
 * @author Garrett Herrera
 * @version 7.1 11/15/2022
 */
public class MomPopsHomeController extends MomPopsPizzeriaMain {
    private Stage stage;
    private Scene scene;
    /*
     * Advances the user to the order entry scene
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
     * Advances the user to the customer login scene
     */
    @FXML
    public void customerLoginForm(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("custLogin-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root,1200, 750);
            stage.setTitle("Mom and Pop's Pizzeria - Customer Login");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    /*
     * Advances the user to the new customer registration scene
     */
    @FXML
    public void customerRegistrationForm(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("registration-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root,1200, 750);
            stage.setTitle("Mom and Pop's Pizzeria - Customer Registration");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    /*
     * Advances the user to the employee/manager login scene
     */
    @FXML
    public void employeeLoginForm(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("empLogin-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root,1200, 750);
            stage.setTitle("Mom and Pop's Pizzeria - Employee Login");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}