package com.mompopspizzeria;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerLoginController extends MomPopsPizzeriaMain implements Initializable{
    @FXML
    Label custLoginValidationText;
    @FXML
    private Button backButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;
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
    protected void cancelBtnCustLogin() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void homeBtnCustLogin() {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void backBtnCustLogin() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void loginBtnCustLogin() {

        if(!checkPhoneIsValid(areaCustLogin.getText(), prefixCustLogin.getText(), postfixCustLogin.getText())){
            custLoginValidationText.setText("Invalid phone number. Please try again.");
        }else{
            custPhoneNum = areaCustLogin.getText() + prefixCustLogin.getText() + postfixCustLogin.getText();
            custPassword = passwordCustLogin.getText();
            CustomerModel currentCustomer = new CustomerModel();
            currentCustomer = dataAccess.authenticateCustomer(custPhoneNum, custPassword);

            if(currentCustomer.customerId >= 0){
                custLoginValidationText.setText("Customer Authenticated!");
                authenticatedCustomer = currentCustomer;
                //Stub call method for order entry window and pass customer model.

            } else {
                custLoginValidationText.setText("Invalid combination.  Please try again.");
            }
        }

    }
    @FXML
    protected void callKeyboard() {
        try {
            Runtime.getRuntime().exec("cmd /c osk");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    /*
     *Phone number validation number of characters that represent integers
     *@author Russell Geary
     *@param area the area code
     *@param prefix the second three characters of the number
     *@param postfix the last four characters of the number
     *@return true if the number is valid.  false if not
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

