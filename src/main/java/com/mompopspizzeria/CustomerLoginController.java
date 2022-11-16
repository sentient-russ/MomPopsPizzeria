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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/*
 * this object class is used to build the menu crusts ArrayList
 * @author Russell Geary
 * @version 7.1 11/15/2022
 */
public class CustomerLoginController extends MomPopsPizzeriaMain implements Initializable{
    @FXML
    private Label custLoginValidationText;
    @FXML
    private TextField areaCustLogin;
    @FXML
    private TextField prefixCustLogin;
    @FXML
    private TextField postfixCustLogin;
    @FXML
    private PasswordField passwordCustLogin;
    @FXML
    private String custPhoneNum;
    @FXML
    private String custPassword = "";
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    /*
     *Action method for handling the login button on action click event
     */
    @FXML
    protected void loginBtnCustLogin(ActionEvent event) {
        if(!checkPhoneIsValid(areaCustLogin.getText(), prefixCustLogin.getText(), postfixCustLogin.getText())){
            custLoginValidationText.setText("Invalid phone number. Please try again.");
        }else{
            custPhoneNum = areaCustLogin.getText() + prefixCustLogin.getText() + postfixCustLogin.getText();
            custPassword = passwordCustLogin.getText();
            CustomerModel currentCustomer = new CustomerModel();
            currentCustomer = dataAccess.authenticateCustomer(custPhoneNum, custPassword);
            if(currentCustomer.customerId >= 0){
                custLoginValidationText.setText("Customer Authenticated!");
                updateCurrentCustomer(currentCustomer);
                proceedToOrder(event);
            } else {
                custLoginValidationText.setText("Invalid combination.  Please try again.");
            }
        }
    }
    /*
     * Action method for handling the proceed to order action event.  It is called if the customer is authenticated
     * @param event ActionEvent
     */
    @FXML
    private void proceedToOrder(ActionEvent event){
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
     * Action method for handling the home button onAction event.  Returns the user to the home screen.
     * @param event FXML on ActionEvent
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
     * Method required in order to impliment initilizable class
     * @param location the FXML URL object
     * @param resources the ResourceBundle object
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
    /*
     *Phone number validation. Checks the number of characters and makes sure they represent integers.
     *@param area the area code
     *@param prefix the second three characters of the number
     *@param postfix the last four characters of the number
     *@return true if the number is valid; false if not
     */
    static boolean checkPhoneIsValid(String areaIn, String preFixIn, String postFixIn ) {
        boolean result = false;
        int areaInAsInt;
        int preFixInAsInt;
        int postFixInAsInt;

        try{
            areaInAsInt = Integer.parseInt(areaIn);
        }catch (NumberFormatException e){
            result = false;
            return result;
        }
        try{
            preFixInAsInt = Integer.parseInt(preFixIn);
        }catch (NumberFormatException e){
            result = false;
            return result;
        }
        try{
            postFixInAsInt = Integer.parseInt(postFixIn);
        }catch (NumberFormatException e){
            result = false;
            return result;
        }
        if(areaIn.length() != 3){
            result = false;
            return result;
        }
        if(preFixIn.length() != 3){
            result = false;
            return result;
        }
        if(postFixIn.length() != 4){
            result = false;
            return result;
        }
        result = true;
        return result;
    }

}

