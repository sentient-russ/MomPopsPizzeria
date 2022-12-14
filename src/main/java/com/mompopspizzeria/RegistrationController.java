package com.mompopspizzeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
/*
 * This controller handles functionality for the new customer registration window
 * @author Russell Geary
 * @author Garrett Herrera
 * @author Deja Hintzen
 * @version 7.1 11/15/2022
 */
public class RegistrationController extends MomPopsPizzeriaMain implements Initializable{
    @FXML
    private Label custLoginValidationText;
    @FXML
    private TextField regFirstName;
    @FXML
    private TextField regLastName;
    @FXML
    private TextField regAddress1;
    @FXML
    private TextField regAddress2;
    @FXML
    private TextField regCity;
    @FXML
    private TextField regState;
    @FXML
    private TextField regZip;
    @FXML
    private TextField regPhoneArea;
    @FXML
    private TextField regPhonePrefix;
    @FXML
    private TextField regPhonePostfix;
    @FXML
    private TextField regNewPassword;
    @FXML
    private TextField regConfirmPassword;
    private Stage stage;
    /*
     * This method calls for validation of the registration form, calls to make sure the user phone number does not already
     * exist and saves the new customer if applicable.
     */
    @FXML
    protected void regSaveAction(ActionEvent event) {

        String fName = regFirstName.getText();
        String lName = regLastName.getText();
        String addr1 = regAddress1.getText();
        String addr2 = regAddress2.getText();
        String city = regCity.getText();
        String state = regState.getText();
        String zip = regZip.getText();
        String phoneNum = regPhoneArea.getText() + regPhonePrefix.getText() + regPhonePostfix.getText();
        String phoneArea = regPhoneArea.getText();
        String phonePrefix = regPhonePrefix.getText();
        String phonePostfix = regPhonePostfix.getText();
        String password = regNewPassword.getText();
        String confirmPassword = regConfirmPassword.getText();
        boolean valid = checkFormIsValid(fName, lName, addr1, addr2, city, state, zip, phoneArea, phonePrefix,
                phonePostfix , password, confirmPassword);
            if(valid){
                CustomerModel newCustomer = new CustomerModel();
                newCustomer.firstName = fName;
                newCustomer.lastName = lName;
                newCustomer.address1 = addr1;
                newCustomer.address2 = addr2;
                newCustomer.city = city;
                newCustomer.state = state;
                newCustomer.zip = zip;
                newCustomer.phoneNumber = phoneNum;
                newCustomer.password = password;
                CustomerModel addedCustomer = dataAccess.addCustomer(newCustomer);
                if(addedCustomer.customerId == -1){
                    custLoginValidationText.setText("Phone number already exists in the data base. Please use another number or speak with the store manager.");
                }else {
                    updateCurrentCustomer(addedCustomer);
                    proceedToOrder(event);
                }
            }
        }
        /*
         * Transports the user to the order entry screen logged in if the registration is a success
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
         * Transports the user to the home screen and optionally resets the order if the home button is clicked
         */
        @FXML
        private void returnHomeAction(ActionEvent event){
            UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 22));
            UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL",Font.PLAIN,35)));
            JFrame jframe = new JFrame();
            int result = JOptionPane.showConfirmDialog(jframe, "Do you want to reset this order? All selections will be lost!");
            if (result == 0){
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
         *Method validates the text fields for the registration form.
         *@param self-explanatory
         *@return true if valid, false if not
         */
        public boolean checkFormIsValid(String fNameIn, String lNameIn, String addr1In, String addr2In, String cityIn,
                                        String stateIn, String zipIn, String phoneAreaIn, String phonePrefixIn,
                                        String phonePostfixIn, String passwordIn, String confirmPasswordIn){
            boolean result = false;
            if(fNameIn.equals("") || fNameIn.length() < 2 || fNameIn.length() > 20){
                custLoginValidationText.setText("Valid first name required.");
                result = false;
                return result;
            }
            if(lNameIn.equals("") || lNameIn.length() < 2 || lNameIn.length() > 20){
                custLoginValidationText.setText("Valid last name required.");
                result = false;
                return result;
            }
            if(addr1In.equals("") || addr1In.length() < 1 || addr1In.length() > 30){
                custLoginValidationText.setText("Valid address is required.");
                result = false;
                return result;
            }
            if(addr2In.length() > 30){
                custLoginValidationText.setText("Valid address required.");
                result = false;
                return result;
            }
            String[] states = {"AK","AL","AR","AZ","CA","CO","CT","DC","DE","FL","GA","HI","IA","ID","IL","IN","KS",
                    "KY","LA","MA","MD","ME", "MI","MN","MO","MS","MT","NC","ND","NE","NH","NJ","NM","NV","NY","OH",
                    "OK","OR","PA","RI","SC","SD","TN","TX","UT","VA","VT","WA","WI","WV","WY"};
            boolean stateFound = false;
            String state = stateIn.toUpperCase();

            for (String s : states) {
                if (state.equals(s)) {
                    stateFound = true;
                }

            }
            if(stateFound == false){
                custLoginValidationText.setText("Valid state required.");
                result = false;
                return result;
            }
            if(stateIn.length() > 2){
                custLoginValidationText.setText("Valid 2 character state required.");
                result = false;
                return result;
            }
            if(cityIn.length() > 20){
                custLoginValidationText.setText("No cities are larger that 20 characters. Try again.");
                result = false;
                return result;
            }

            if(zipIn.equals("") || zipIn.length() < 5 || zipIn.length() > 5){
                custLoginValidationText.setText("Valid zip code required.");
                result = false;
                return result;
            }
            try{
                int zipAsInt = Integer.parseInt(zipIn);
            }catch(NumberFormatException e){
                custLoginValidationText.setText("Valid integer zip code required.");
                result = false;
                return result;
            }
            if(!checkPhoneIsValid(phoneAreaIn,phonePrefixIn,phonePostfixIn)){
                custLoginValidationText.setText("Valid phone number required.");
                result = false;
                return result;
            }
            if(passwordIn.equals(confirmPasswordIn) && passwordIn.length() >= 5 && passwordIn.length() <= 25){
                custLoginValidationText.setText("Validation successful");
                result = true;
            }else{
                custLoginValidationText.setText("Passwords must match and be between 5 and 25 characters");
            }
            if(!result){
                custLoginValidationText.setText("Password validation failed");
            }

            return result;
        }
    /*
     *Phone number validation
     *@author Russell Geary
     *@param area the area code
     *@param prefix the second three characters of the number
     *@param postfix the last four characters of the number
     *@return true if the number is valid.  false if not
     */
    private boolean checkPhoneIsValid(String areaIn, String preFixIn, String postFixIn ) {
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

}