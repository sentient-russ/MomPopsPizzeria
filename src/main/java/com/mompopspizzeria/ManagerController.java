package com.mompopspizzeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
/*
 * This controller class is used by the manager view.
 * @author Russell Geary
 * @author Garrett Herrera
 * @version 7.1 11/15/2022
 */
public class ManagerController extends MomPopsPizzeriaMain  {
    private Stage stage;
    private Scene scene;
    /*
     * This method returns to the login screen while preserving any current order information
     */
    @FXML
    private void goBack(ActionEvent event){
        try {
            authenticatedEmployee.isEmployee = false;
            authenticatedEmployee.isManager = false;
            Parent root = FXMLLoader.load(getClass().getResource("empLogin-view.fxml"));
            Scene scene = new Scene(root, 1200, 750);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Mom and Pop's Pizzeria - Employee Login");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    /*
     * This action method returns the manager to the home screen and optionally resets the order.
     */
    @FXML
    private void returnHomeAction(ActionEvent event){
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 22));
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL",Font.PLAIN,35)));
        JFrame jframe = new JFrame();
        int result = JOptionPane.showConfirmDialog(jframe, "Do you want to reset this login instance? Manager will be logged out!");
        if (result == 0){
            orderReset();
            try {
                authenticatedEmployee.isEmployee = false;
                authenticatedEmployee.isManager = false;
                orderReset();
                CustomerModel guest = dataAccess.authenticateCustomer(guestPhoneNumber,guestPassword);
                updateCurrentCustomer(guest);
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
        } else if(result == 1) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("home-view.fxml"));
                Scene scene = new Scene(root, 1200, 750);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Mom and Pop's Pizzeria - Home");
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }else {
            //do nothing
        }
    }
    /*
     * This action method returns the manager to the home screen and optionally resets the order.
     */
    @FXML
    public void jumpToorderEntryAction(ActionEvent event) {
        authenticatedEmployee.isEmployee = false;
        authenticatedEmployee.isManager = false;
        CustomerModel employee = dataAccess.authenticateCustomer(employeePhoneNumber,employeePassword);
        employee.isEmployee = true;
        updateCurrentCustomer(employee);

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
}

