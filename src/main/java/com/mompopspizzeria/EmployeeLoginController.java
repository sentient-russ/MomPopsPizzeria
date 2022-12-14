package com.mompopspizzeria;

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
import java.util.ResourceBundle;
/*
 * This class initiates all login functions on the employee login screen in addition to the back, home, and cancel options
 * @author Russell Geary
 * @author Garrett Herrera
 * @version 7.1 11/15/2022
 */
public class EmployeeLoginController extends MomPopsPizzeriaMain implements Initializable {
    @FXML
    private Label empLoginValidationText;
    @FXML
    private RadioButton empLoginEmpRadioBtn;
    @FXML
    private RadioButton empLoginManagerRadioBtn;
    @FXML
    private ToggleGroup btnGroup = new ToggleGroup();//do not delete. Used by Gluon
    @FXML
    private PasswordField passwordEmployeeLogin;


    /*
     * Authentication implemented here. Executed when the login button is clicked.
     */
    @FXML
    protected void empLoginAction(ActionEvent event) {
        empLoginValidationText.setText("Please Wait!");
        try {
            String password = passwordEmployeeLogin.getText();
            if (empLoginEmpRadioBtn.isSelected() && authenticatedEmployee.checkEmployeePassword(password)) {
                authenticatedEmployee.isEmployee = true;
                empLoginValidationText.setText("Success! - Logged in as Employee");
                CustomerModel employee = dataAccess.authenticateCustomer(employeePhoneNumber,employeePassword);
                employee.isEmployee = true;
                updateCurrentCustomer(employee);
                currentCustomer.isEmployee =  true;
                orderEntryForm(event);
            } else if (empLoginManagerRadioBtn.isSelected() && authenticatedEmployee.checkManagerPassword(password)) {
                authenticatedEmployee.isManager = true;
                empLoginValidationText.setText("Success! - Logged in as Manager");
                CustomerModel employee = dataAccess.authenticateCustomer(employeePhoneNumber, employeePassword);
                employee.isManager = true;
                updateCurrentCustomer(employee);
                managementMenuAction(event);
            } else {
                empLoginValidationText.setText("Login Failed! - Temporary notification: Employee Password = 2345 Manager Password = 9876");
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    /*
     * This method takes the user to the home screen and optionally resets the order.
     */
    @FXML
    private void returnHomeAction(ActionEvent event){
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 22));
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL",Font.PLAIN,35)));
        JFrame jframe = new JFrame();
        int result = JOptionPane.showConfirmDialog(jframe, "Do you want to reset the current order? All selections will be lost!");
        if (result == 0){
            orderReset();
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
     * This method takes the employee to the order entry screen if their login has been authenticated.
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
     * This method takes the manager to the management screen if they have been authenticated.
     */
    @FXML
    public void managementMenuAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("manager-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root,1200, 750);
            stage.setTitle("Mom and Pop's Pizzeria - Management Menu");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    /*
     * This method is a placeholder for future implementation.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}

