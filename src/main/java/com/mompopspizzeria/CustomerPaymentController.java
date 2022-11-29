package com.mompopspizzeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
/*
 * This controller class provides functionality to the customer payment screen.
 * @author Russell Geary
 * @author Garrett Herrera
 * @version 7.1 11/15/2022
 */
public class CustomerPaymentController extends MomPopsPizzeriaMain implements Initializable {
    @FXML
    protected ListView<String> orderSummeryList;
    @FXML
    protected Label currentUserTextGlobal;
    @FXML
    protected Label customerPaymentTotalText;
    @FXML
    protected Label custPaymentValidationText;
    @FXML
    protected DatePicker dateCustomerPaymentDatePicker;
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField cardNumCustPaymentTextField;

    @FXML
    private TextField cvvCodeCustPaymentTextField;
    @FXML
    private TextField firstNameCustPaymentTextField;
    @FXML
    private TextField lastNameCustPaymentTextField;
    @FXML
    private TextField addr1CustPaymentTextField;
    @FXML
    private TextField addr2CustPaymentTextField;
    @FXML
    private TextField cityCustPaymentTextField;
    @FXML
    private TextField stateCustPaymentTextField;
    @FXML
    private TextField zipCustPaymentTextField;
    private String currentUserGlobal = MomPopsPizzeriaMain.currentUserGlobal;
    private String orderTotalString;
    /*
     *This method initiates payment processing and prompts the user to save the cc information in a new account.
     */
    @FXML
    public void payNowBtnActionCuPaymentScene(ActionEvent event) {
        String cardNum = cardNumCustPaymentTextField.getText();
        String expDate = String.valueOf(dateCustomerPaymentDatePicker.getValue());
        String cvvCode = cvvCodeCustPaymentTextField.getText();
        String firstName = firstNameCustPaymentTextField.getText();
        String lastName = lastNameCustPaymentTextField.getText();
        String addr1 = addr1CustPaymentTextField.getText();
        String addr2 = addr2CustPaymentTextField.getText();
        String city = cityCustPaymentTextField.getText();
        String state = stateCustPaymentTextField.getText();
        String zip = zipCustPaymentTextField.getText();
        if (cardIsValid(cardNum, expDate, cvvCode, firstName,
                lastName, addr1, addr2, city,
                state, zip)) {
            String newPhoneNumber = "";
            String newPassword = "";
            currentOrder.customerFirstName = firstName; //adding billing first name to transaction file
            currentOrder.customerLastName = lastName; //adding billing last name to transaction file
            lastCustomer = currentCustomer;
            lastOrder = currentOrder;
            String orderTotalString = DecimalFormat.getCurrencyInstance().format(currentOrder.orderTotal);
            ccProcessor.merchantServicesConnector(firstName, lastName, cardNum, expDate, cvvCode, orderTotalString);
            dataAccess.saveOrder(currentOrder);
            orderReset();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("orderConfirmation-view.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root, 1200, 750);
                stage.setTitle("Mom and Pop's Pizzeria - Success!  Here is your order confirmation");
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }
    }
    /*
     * Method causes the scene to change back to order entry from the customer payment scene
     */
    @FXML
    public void backBtnActionCuPaymentScene(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("order-view.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, 1200, 750);
            stage.setTitle("Mom and Pop's Pizzeria - Order Entry");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    /*
     * Method cancels the payment and returns the customer to the order scene.
     */
    @FXML
    public void cancelBtnActionCuPaymentScene(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("order-view.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, 1200, 750);
            stage.setTitle("Mom and Pop's Pizzeria - Order Entry");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    /*
     *Method returns the user to the home scene after confirmation to make sure that they wish to cancel the order.
     */
    @FXML
    private void homeBtnActionCuPaymentScene(ActionEvent event) {
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
     *Method populates the listview with the current order in addition too setting up the date field.
     */
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String todaysDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        dateCustomerPaymentDatePicker.setValue(LocalDate.parse(todaysDate));
        double orderTotalDouble = currentOrder.orderTotal;
        orderTotalString = DecimalFormat.getCurrencyInstance().format(orderTotalDouble);
        customerPaymentTotalText.setText(orderTotalString);
        ArrayList<LineItemModel> lineArray = currentOrder.getLineItems();
        for (int i = 0; i < lineArray.size(); i++) {

            if (lineArray.get(i).isPizza) {
                String newLine = (i + 1) + ".) Pizza: " + lineArray.get(i).pizza + ", ";
                ArrayList<String> toppingsArray = new ArrayList<>();
                toppingsArray = lineArray.get(i).toppings;
                for (int t = 0; t < toppingsArray.size(); t++) {
                    newLine = newLine + toppingsArray.get(t) + ", ";
                }
                newLine = newLine + " Price: " + DecimalFormat.getCurrencyInstance().format(lineArray.get(i).lineTotal);
                orderSummeryList.getItems().add(newLine);
            } else if (lineArray.get(i).isDrink) {

                String newLine = (i + 1) + ".) Drink: " + lineArray.get(i).drink + " " + lineArray.get(i).drinkSize + " Price:  " + DecimalFormat.getCurrencyInstance().format(lineArray.get(i).lineTotal);
                orderSummeryList.getItems().add(newLine);
            } else if (lineArray.get(i).isSide) {
                String newLine = (i + 1) + ".) Side: " + lineArray.get(i).side + ", Qty: " + lineArray.get(i).sideQuantity + ", Price: " + DecimalFormat.getCurrencyInstance().format(lineArray.get(i).lineTotal);
                orderSummeryList.getItems().add(newLine);
            }
        }
        currentUserTextGlobal.setText(currentUserGlobal);
    }
    /*
     * Validation method for the customer payment form
     * @param cardNumIn card number to be validated as a string
     * @param expDateIn expiration date of the credit card as a string
     * @param cvvCodeIn 3-4 digit numerical value representing the cvv code per product specifications
     * @param firstNameIn the cardholders first name as a string
     * @param lastnameIn the cardholders last name as a string
     * @param addr1In the address line one as a string
     * @param addr2Ln the address line two as a string
     * @param cityIn the city name as a string
     * @param state the two character state abbreviation as a string
     * @param zipIn the five-digit zip code as a string
     * @return true if the card is validated and false if not
     */
    @FXML
    public boolean cardIsValid(String cardNumIn, String expDateIn, String cvvCodeIn, String firstNameIn,
                               String lastNameIn, String addr1In, String addr2In, String cityIn,
                               String stateIn, String zipIn) {
        boolean result = false;
        String[] states = {"AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "HI", "IA", "ID", "IL", "IN", "KS",
                "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH",
                "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV", "WY"};
        String[] letersAndSymbols = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
                "R", "S", "T", "U", "V", "W", "Z", "Y", "z", "", "\"", "\'", "`", "~", "!", "@", "#", "#", "$", "%", "^", "&",
                "*", "(", ")", "[", "]", "{", "}", "\\", ":", ":", "<", ">", ".", "/", "?", "", "", ""};
        String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        boolean firstCharacterIsNumber = false;
        String addrFirstCharacter = addr1In.split("")[0];
        boolean stateFound = false;
        if(currentOrder.lineItems.size() == 0){
            custPaymentValidationText.setText("Please go back and add items to your order.");
            return result;
        }else if (cardNumIn.equals("") || cardNumIn.length() < 16 || cardNumIn.length() > 16) {
            custPaymentValidationText.setText("The credit card number is not invalid.");
            return result;
        } else if(cvvCodeIn.length() > 4 || cvvCodeIn.length() < 3 ) {
            custPaymentValidationText.setText("The CVV is not invalid. It is the 3 or 4 digits on the back of the card.");
            return result;
        } else if (firstNameIn.equals("") || firstNameIn.length() < 2 || firstNameIn.length() > 20) {
                custPaymentValidationText.setText("Valid first name required.");
                return result;
            } else if (lastNameIn.equals("") || lastNameIn.length() < 2 || lastNameIn.length() > 20) {
                custPaymentValidationText.setText("Valid last name required.");
                return result;
            }else if (addr1In.equals("") || addr1In.length() < 2 || addr1In.length() > 25) {
                custPaymentValidationText.setText("Valid address line one required.");
                return result;
            }else try {
                int number = Integer.parseInt(addrFirstCharacter);
            } catch (NumberFormatException e) {
            e.getCause();
            return result;
        }
        if (addr2In != ""){
            if(addr2In.length() > 25){
                custPaymentValidationText.setText("Address line 2 in invalid.");
                return result;
            }
        } else if (cityIn.length() < 1 || cityIn.length() > 25) {
            custPaymentValidationText.setText("The city name is invalid");
            return result;
        }
        String upperCaseState = stateIn.toUpperCase();
        for (String s : states) {
            if (upperCaseState.equals(s)) {
                stateFound = true;
            }
        }
        if (!stateFound) {
            custPaymentValidationText.setText("Valid state required.");
            return result;
        }else try {
            int numeral = Integer.parseInt(zipIn);
            } catch (NumberFormatException e) {
                e.getCause();
                custPaymentValidationText.setText("Valid zipcode required.");
                return result;
            }
        if(zipIn.length() < 5 || zipIn.length() > 5){
            custPaymentValidationText.setText("Valid zipcode required.");
            return result;
        }
        String todaysDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        LocalDate currentDate = LocalDate.parse(todaysDate);
        LocalDate inputDate = LocalDate.parse(expDateIn);
        if(currentDate.isAfter(inputDate)){
            custPaymentValidationText.setText("Expiration date must be in the future.");
        }
        custPaymentValidationText.setText("Valid");
        result = true;
        return result;
    }
}