package com.mompopspizzeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
/*
 * this class contains the customer login functions
 * @author Russell Geary
 * @author Garrett Herrera
 * @version 7.1 11/15/2022
 */
public class CustomerLoginController extends MomPopsPizzeriaMain implements Initializable{
    @FXML
    private Label customerLoginValidationText;
    @FXML
    private TextField areaCustomerLogin;
    @FXML
    private TextField prefixCustomerLogin;
    @FXML
    private TextField postfixCustomerLogin;
    @FXML
    private PasswordField passwordCustomerLogin;
    @FXML
    public String customerPhoneNum;
    @FXML
    public String customerPassword = "";
    @FXML
    private Stage stage;
    /*
     * This method handles customer login authentication when the login button is clicked
     */
    @FXML
    protected void loginBtnCustomerLogin(ActionEvent event) {
        if(!checkPhoneIsValid(areaCustomerLogin.getText(), prefixCustomerLogin.getText(), postfixCustomerLogin.getText())){
            customerLoginValidationText.setText("Invalid phone number. Please try again.");
        }else{
            customerPhoneNum = areaCustomerLogin.getText() + prefixCustomerLogin.getText() + postfixCustomerLogin.getText();
            customerPassword = passwordCustomerLogin.getText();
            CustomerModel currentCustomer = dataAccess.authenticateCustomer(customerPhoneNum, customerPassword);
            if(currentCustomer.customerId >= 0){
                customerLoginValidationText.setText("Customer Authenticated!");
                updateCurrentCustomer(currentCustomer);
                proceedToOrder(event);
            } else {
                customerLoginValidationText.setText("Invalid combination.  Please try again.");
            }
        }
    }
    /*
     * If authenticated this method is called to advance the customer to the ordering screen
     */
    @FXML
    private void proceedToOrder(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("order-view.fxml")));
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
     * Returns the user to the home screen and optionally resets the order
     */
    @FXML
    private void returnHomeAction(ActionEvent event){
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 22));
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL",Font.PLAIN,35)));
        JFrame jframe = new JFrame();
        int result = JOptionPane.showConfirmDialog(jframe, "Do you want to reset this order? All selections will be lost!");
        if (result == 0 || result == 1){
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
        }
    }
    /*
     * Method required in order to implement initialize class
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {}
    /*
     * Phone number validation. Checks the number of characters and makes sure they represent integers.
     * @param area the 3 numbers characters representing the area code
     * @param prefix the second three characters of the number
     * @param postfix the last four characters of the number
     * @return true if the number is valid; false if not
     */
    static boolean checkPhoneIsValid(String areaIn, String preFixIn, String postFixIn ) {
        try{
            Integer.parseInt(areaIn);
        }catch (NumberFormatException e){
            return false;
        }
        try{
            Integer.parseInt(preFixIn);
        }catch (NumberFormatException e){
            return false;
        }
        try{
            Integer.parseInt(postFixIn);
        }catch (NumberFormatException e){
            return false;
        }
        if(areaIn.length() != 3){
            return false;
        } else if (preFixIn.length() != 3){
            return false;
        } else return postFixIn.length() == 4;
    }
}

