package com.mompopspizzeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.net.URL;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
/*
 *This controller class provides functionality to the customer payment screen.
 */
public class EmployeePaymentController extends MomPopsPizzeriaMain implements Initializable {
    @FXML
    private CheckBox cashEmployeePaymentSceneCheckBox;
    @FXML
    private CheckBox checkEmployeePaymentSceneCheckBox;
    @FXML
    private CheckBox creditCardEmployeePaymentSceneCheckBox;
    @FXML
    private Label currentUserTextGlobal;
    @FXML
    protected Label orderTotalEmpPaySceneText;
    @FXML
    protected Label amountPaidEmpPaySceneText;
    @FXML
    protected Label balEmpPaySceneText;
    @FXML
    protected Label changeEmPaySceneText;
    @FXML
    protected Label empPayValidationText;


    private Stage stage;
    private Scene scene;
    @FXML
    private TextField cashAmountEmployeePaymentSceneTextField;
    @FXML
    private Button calcBtnEmployeePaymentSceneButton;
    @FXML
    private TextField checkAmountEmployeePaymentSceneTextField;
    @FXML
    private TextField checkStateEmployeePaymentSceneTextField;
    @FXML
    protected DatePicker expCCDateEmployeePaymentDatePicker;
    @FXML
    protected DatePicker checkDateEmployeePaymentSceneDateField;

    @FXML
    private TextField checkNumEmployeePaymentSceneTextField;
    @FXML
    private TextField checkDLNumEmployeePaymentSceneTextField;
    @FXML
    private TextField creditAmountAmountEmployeePaymentSceneTextField;
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
    private boolean cashEnabled;
    private boolean checkEnabled;
    private boolean creditEnabled;
    private double cashAmountReceivedDouble = 0.00;
    private double checkAmountReceivedDouble = 0.00;
    private double creditAmountReceivedDouble = 0.00;
    // private double currentAmountDueDouble;
    private double orderTotalDouble;
    private double balanceDue;

    /*
     *This method initiates payment processing and prompts the user to save the cc information in a new account.
     */
    @FXML
    public void payNowBtnActionCuPaymentScene(ActionEvent event) {
        //make sure that the order is paid in full
        calcAmountDue(event);

        String cardNum = cardNumCustPaymentTextField.getText();
        String expDate = String.valueOf(expCCDateEmployeePaymentDatePicker.getValue());
        String cvvCode = cvvCodeCustPaymentTextField.getText();
        String firstName = firstNameCustPaymentTextField.getText();
        String lastName = lastNameCustPaymentTextField.getText();
        String addr1 = addr1CustPaymentTextField.getText();
        String addr2 = addr2CustPaymentTextField.getText();
        String city = cityCustPaymentTextField.getText();
        String state = stateCustPaymentTextField.getText();
        String zip = zipCustPaymentTextField.getText();
        String chargeAmount = creditAmountAmountEmployeePaymentSceneTextField.getText();
        String cash = cashAmountEmployeePaymentSceneTextField.getText();
        String checkAmount = checkAmountEmployeePaymentSceneTextField.getText();
        String checkNum = checkNumEmployeePaymentSceneTextField.getText();
        String checkDate = String.valueOf(checkDateEmployeePaymentSceneDateField.getValue());
        String checkState = checkStateEmployeePaymentSceneTextField.getText();
        String checkDL = checkDLNumEmployeePaymentSceneTextField.getText();

        //field validation calls
        boolean allValid = false;
            if (cashEmployeePaymentSceneCheckBox.isSelected()) {
                boolean cashValid = cashIsValid(cash);
                allValid = cashValid;
            }
            if (checkEmployeePaymentSceneCheckBox.isSelected()) {
                boolean checkValid = checkIsValid(checkAmount, checkNum, checkDate, checkState, checkDL);
                allValid = checkValid;
            }
            if (creditCardEmployeePaymentSceneCheckBox.isSelected()) {
                boolean cardValid = cardIsValid(cardNum, expDate, cvvCode, firstName,
                        lastName, addr1, addr2, city,
                        state, zip);
                allValid = cardValid;
            }

        if(allValid && orderPaidValidation()){
            currentCustomer.firstName = firstName;
            currentCustomer.lastName = lastName;
            currentCustomer.address1 = addr1;
            currentCustomer.address2 = addr2;
            currentCustomer.city = city;
            currentCustomer.state = state;
            currentCustomer.zip = zip;
            currentCustomer.cardNumber = cardNum;
            currentCustomer.expDate = expDate;
            currentCustomer.CVV = cvvCode;
            currentOrder.currentOrderChargeAmount = chargeAmount;
            currentOrder.customerFirstName = firstName;
            currentOrder.customerLastName = lastName;
            currentOrderSaveAndClose(event);
        }
    }
    @FXML
    public boolean orderPaidValidation(){
        boolean result = false;
        if(balanceDue > 0){
            empPayValidationText.setTextFill(Color.rgb(164,190,235,1));
            empPayValidationText.setText("Order cannot be completed until the balance of " + DecimalFormat.getCurrencyInstance().format(balanceDue) + " is paid!");
            return result;
        }
        result = true;
        return result;
    }
    @FXML
    public void currentOrderSaveAndClose(ActionEvent event){

        if(currentCustomer.firstName.equals("")){
            currentCustomer.firstName = "Company";
        }else{
            currentOrder.customerFirstName = currentCustomer.firstName; //adding billing first name to transaction file
        }
        if(currentCustomer.lastName.equals("")){
            currentCustomer.lastName = "Employee";
        } else {
            currentOrder.customerLastName = currentCustomer.lastName; //adding billing last name to transaction file
        }
        if(creditCardEmployeePaymentSceneCheckBox.isSelected()){
            ccProcessor.merchantServicesConnector(currentCustomer.firstName, currentCustomer.lastName,
                    currentCustomer.cardNumber, currentCustomer.expDate, currentCustomer.CVV, currentOrder.currentOrderChargeAmount);
        }
        //Stub open cash drawer here in future implementation.
        //makes sure transaction history matches the name on the card rather than the name on the customer account
        currentOrder.customerFirstName = currentCustomer.firstName;
        currentOrder.customerLastName = currentCustomer.lastName;
        //process CC payment
        String orderTotalString = DecimalFormat.getCurrencyInstance().format(currentOrder.orderTotal);
        ccProcessor.merchantServicesConnector(currentOrder.customerFirstName,currentOrder.customerLastName,currentCustomer.cardNumber,
                currentCustomer.expDate, currentCustomer.CVV, orderTotalString);
        //save the order
        dataAccess.saveOrder(currentOrder);
        //reset global order and customer instances
        lastCustomer = currentCustomer;
        lastOrder = currentOrder;
        CustomerModel nextGuest = dataAccess.authenticateCustomer(guestPhoneNumber, guestPassword);
        updateCurrentCustomer(nextGuest);
        OrderModel newOrder = new OrderModel(nextGuest);
        currentOrder = newOrder;
        //proceed to order confirmation
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

    @FXML
    public void calcAmountDue(ActionEvent event){

        if(cashAmountEmployeePaymentSceneTextField.getText().equals("")){
            cashAmountEmployeePaymentSceneTextField.setText("0.00");
        }
        if(checkAmountEmployeePaymentSceneTextField.getText().equals("")){
            checkAmountEmployeePaymentSceneTextField.setText("");
        }
        if(creditAmountAmountEmployeePaymentSceneTextField.getText().equals(null)){
            creditAmountAmountEmployeePaymentSceneTextField.setText("0.00");
        }

        double tempAmountDueDouble = currentOrder.orderTotal;

        cashAmountReceivedDouble = Double.parseDouble(cashAmountEmployeePaymentSceneTextField.getText());
        checkAmountReceivedDouble = Double.parseDouble(checkAmountEmployeePaymentSceneTextField.getText());
        creditAmountReceivedDouble = Double.parseDouble(creditAmountAmountEmployeePaymentSceneTextField.getText());

        orderTotalEmpPaySceneText.setText(DecimalFormat.getCurrencyInstance().format(currentOrder.orderTotal));
        double totalPaid = cashAmountReceivedDouble + checkAmountReceivedDouble + creditAmountReceivedDouble;
        amountPaidEmpPaySceneText.setText(DecimalFormat.getCurrencyInstance().format(totalPaid));
        balanceDue = currentOrder.orderTotal - totalPaid;
        balEmpPaySceneText.setText(DecimalFormat.getCurrencyInstance().format(balanceDue));

        if((cashAmountReceivedDouble + checkAmountReceivedDouble + creditAmountReceivedDouble) > tempAmountDueDouble){
            double dueCustomerDouble = (cashAmountReceivedDouble + checkAmountReceivedDouble + creditAmountReceivedDouble) - tempAmountDueDouble;
            String dueString = DecimalFormat.getCurrencyInstance().format(dueCustomerDouble);
            changeEmPaySceneText.setText(dueString);
            balEmpPaySceneText.setText("$0.00");
        }
        if ((cashAmountReceivedDouble + checkAmountReceivedDouble + creditAmountReceivedDouble) <= tempAmountDueDouble){
            tempAmountDueDouble = tempAmountDueDouble - (cashAmountReceivedDouble + checkAmountReceivedDouble + creditAmountReceivedDouble);
            String dueString = DecimalFormat.getCurrencyInstance().format(tempAmountDueDouble);
            balEmpPaySceneText.setText(dueString);
            changeEmPaySceneText.setText("$0.00");
        }

    }

    /*
     * Method causes the scene to change back to order entry from the customer payment scene
     */
    @FXML
    public void backBtnActionEmployeePaymentScene(ActionEvent event) {
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
    public void cancelBtnActionEmployeePaymentScene(ActionEvent event) {
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
    private void homeBtnActionEmployeePaymentScene(ActionEvent event) {
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
    @FXML
    public void sectionEnable(ActionEvent event){
        if(cashEmployeePaymentSceneCheckBox.isSelected()){
            cashAmountEmployeePaymentSceneTextField.setDisable(false);
            calcBtnEmployeePaymentSceneButton.setDisable(false);
            cashEnabled = true;
        }
        if(checkEmployeePaymentSceneCheckBox.isSelected()){
            checkAmountEmployeePaymentSceneTextField.setDisable(false);
            checkStateEmployeePaymentSceneTextField.setDisable(false);
            checkDateEmployeePaymentSceneDateField.setDisable(false);
            checkNumEmployeePaymentSceneTextField.setDisable(false);
            checkDLNumEmployeePaymentSceneTextField.setDisable(false);
            calcBtnEmployeePaymentSceneButton.setDisable(false);
            checkEnabled = true;
        }
        if(creditCardEmployeePaymentSceneCheckBox.isSelected()){
            creditAmountAmountEmployeePaymentSceneTextField.setDisable(false);
            cardNumCustPaymentTextField.setDisable(false);
            cvvCodeCustPaymentTextField.setDisable(false);
            firstNameCustPaymentTextField.setDisable(false);
            lastNameCustPaymentTextField.setDisable(false);
            addr1CustPaymentTextField.setDisable(false);
            addr2CustPaymentTextField.setDisable(false);
            cityCustPaymentTextField.setDisable(false);
            expCCDateEmployeePaymentDatePicker.setDisable(false);
            stateCustPaymentTextField.setDisable(false);
            zipCustPaymentTextField.setDisable(false);
            calcBtnEmployeePaymentSceneButton.setDisable(false);
            creditEnabled = true;
        }
        if(!cashEmployeePaymentSceneCheckBox.isSelected()){
            cashAmountEmployeePaymentSceneTextField.setDisable(true);
            cashEnabled = false;
            cashAmountReceivedDouble = 0.00;
        }
        if(!checkEmployeePaymentSceneCheckBox.isSelected()){
            checkAmountEmployeePaymentSceneTextField.setDisable(true);
            checkStateEmployeePaymentSceneTextField.setDisable(true);
            checkDateEmployeePaymentSceneDateField.setDisable(true);
            checkNumEmployeePaymentSceneTextField.setDisable(true);
            checkDLNumEmployeePaymentSceneTextField.setDisable(true);
            checkEnabled = false;
            checkAmountReceivedDouble = 0.00;
        }
        if(!creditCardEmployeePaymentSceneCheckBox.isSelected()){
            creditAmountAmountEmployeePaymentSceneTextField.setDisable(true);
            cardNumCustPaymentTextField.setDisable(true);
            cvvCodeCustPaymentTextField.setDisable(true);
            firstNameCustPaymentTextField.setDisable(true);
            lastNameCustPaymentTextField.setDisable(true);
            addr1CustPaymentTextField.setDisable(true);
            addr2CustPaymentTextField.setDisable(true);
            cityCustPaymentTextField.setDisable(true);
            expCCDateEmployeePaymentDatePicker.setDisable(true);
            stateCustPaymentTextField.setDisable(true);
            zipCustPaymentTextField.setDisable(true);
            creditEnabled = false;
            creditAmountReceivedDouble = 0.00;
        }

    }
    /*
     *Method populates the listview with the current order in addition to setting up the date field.
     */
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String todaysDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        checkDateEmployeePaymentSceneDateField.setValue(LocalDate.parse(todaysDate));
        String todaysDate2 = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        expCCDateEmployeePaymentDatePicker.setValue(LocalDate.parse(todaysDate2));


        cashAmountEmployeePaymentSceneTextField.setText("0.00");
        checkAmountEmployeePaymentSceneTextField.setText("0.00");
        creditAmountAmountEmployeePaymentSceneTextField.setText("0.00");
        checkDateEmployeePaymentSceneDateField.setValue(LocalDate.parse(todaysDate));
        orderTotalDouble = currentOrder.orderTotal;
        orderTotalString = DecimalFormat.getCurrencyInstance().format(orderTotalDouble);
        orderTotalEmpPaySceneText.setText(orderTotalString);
        currentUserTextGlobal.setText(currentUserGlobal);

        //disable all fields and await checkbox input
        creditAmountAmountEmployeePaymentSceneTextField.setDisable(true);
        cashAmountEmployeePaymentSceneTextField.setDisable(true);
        calcBtnEmployeePaymentSceneButton.setDisable(true);
        checkAmountEmployeePaymentSceneTextField.setDisable(true);
        checkStateEmployeePaymentSceneTextField.setDisable(true);
        checkDateEmployeePaymentSceneDateField.setDisable(true);
        checkNumEmployeePaymentSceneTextField.setDisable(true);
        checkDLNumEmployeePaymentSceneTextField.setDisable(true);
        cardNumCustPaymentTextField.setDisable(true);
        cvvCodeCustPaymentTextField.setDisable(true);
        firstNameCustPaymentTextField.setDisable(true);
        lastNameCustPaymentTextField.setDisable(true);
        addr1CustPaymentTextField.setDisable(true);
        addr2CustPaymentTextField.setDisable(true);
        cityCustPaymentTextField.setDisable(true);
        expCCDateEmployeePaymentDatePicker.setDisable(true);
        stateCustPaymentTextField.setDisable(true);
        zipCustPaymentTextField.setDisable(true);
    }
    /*
     * Check validation method for the customer payment form
     * @param checkAmountIn
     * @param checkNumIn
     * @param checkDateIn
     * @param stateIn
     * @param DLIn
     * @return true is valid and false if not
     */
    @FXML
    private boolean checkIsValid(String checkAmountIn, String checkNumIn, String checkDateIn, String stateIn, String DLIn){
        boolean result = false;
        if (checkAmountIn.equals("") || checkAmountIn.length() > 16) {
            empPayValidationText.setTextFill(Color.rgb(164,190,235,1));
            empPayValidationText.setText("The check amount is invalid.");
            return result;
        }
        if (checkNumIn.equals("") || checkNumIn.length() < 2 || checkNumIn.length() > 20) {
            empPayValidationText.setTextFill(Color.rgb(164,190,235,1));
            empPayValidationText.setText("The check number is invalid.");
            return result;
        }
        if (DLIn.equals("") || DLIn.length() < 5 || DLIn.length() > 20) {
            empPayValidationText.setTextFill(Color.rgb(164,190,235,1));
            empPayValidationText.setText("The drivers license number is invalid.");
            return result;
        }
        String upperCaseState = stateIn.toUpperCase();
        if (!upperCaseState.equals("GA")) {
            empPayValidationText.setTextFill(Color.rgb(164,190,235,1));
            empPayValidationText.setText("Unfortunately, only Georgia checks are accepted");
            return result;
        }
        String todaysDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        LocalDate currentDate = LocalDate.parse(todaysDate);
        LocalDate inputDate = LocalDate.parse(checkDateIn);
        if(currentDate.isAfter(inputDate) || currentDate.isBefore(inputDate) || currentDate.equals("")){
            empPayValidationText.setTextFill(Color.rgb(164,190,235,1));
            empPayValidationText.setText("Unfortunately, no pre or post dated checks are accepted.");
            return result;
        }
        empPayValidationText.setTextFill(Color.rgb(164,190,235,1));
        empPayValidationText.setText("Payment Accepted!");
        result = true;
        return result;
    }

    /*
     * Credit card validation method for the customer payment form
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
            empPayValidationText.setTextFill(Color.rgb(164,190,235,1));
            empPayValidationText.setText("Please go back and add items to your order.");
            return result;
        }else if (cardNumIn.equals("") || cardNumIn.length() < 16 || cardNumIn.length() > 16) {
            empPayValidationText.setTextFill(Color.rgb(164,190,235,1));
            empPayValidationText.setText("The credit card number is not invalid.");
            return result;
        } else if(cvvCodeIn.length() > 4 || cvvCodeIn.length() < 3 ) {
            empPayValidationText.setTextFill(Color.rgb(164,190,235,1));
            empPayValidationText.setText("The CVV is not invalid. It is the 3 or 4 digits on the back of the card.");
            return result;
        } else if (firstNameIn.equals("") || firstNameIn.length() < 2 || firstNameIn.length() > 20) {
            empPayValidationText.setTextFill(Color.rgb(164,190,235,1));
            empPayValidationText.setText("Valid first name required.");
                return result;
            } else if (lastNameIn.equals("") || lastNameIn.length() < 2 || lastNameIn.length() > 20) {
            empPayValidationText.setTextFill(Color.rgb(164,190,235,1));
            empPayValidationText.setText("Valid last name required.");
                return result;
            }else if (addr1In.equals("") || addr1In.length() < 2 || addr1In.length() > 25) {
            empPayValidationText.setTextFill(Color.rgb(164,190,235,1));
            empPayValidationText.setText("Valid address line one required.");
                return result;
            }else try {
                int number = Integer.parseInt(addrFirstCharacter);
            } catch (NumberFormatException e) {
            e.getCause();
            return result;
        }

        if (addr2In != ""){
            if(addr2In.length() > 25){
                empPayValidationText.setTextFill(Color.rgb(164,190,235,1));
                empPayValidationText.setText("Address line 2 in invalid.");
                return result;
            }
        } else if (cityIn.length() < 1 || cityIn.length() > 25) {
            empPayValidationText.setTextFill(Color.rgb(164,190,235,1));
            empPayValidationText.setText("The city name is invalid");
            return result;
        }
        String upperCaseState = stateIn.toUpperCase();
        for (String s : states) {
            if (upperCaseState.equals(s)) {
                stateFound = true;
            }
        }
        if (!stateFound) {
            empPayValidationText.setTextFill(Color.rgb(164,190,235,1));
            empPayValidationText.setText("Valid state required.");
            return result;
        }else try {
            int numeral = Integer.parseInt(zipIn);
            } catch (NumberFormatException e) {
                e.getCause();
                empPayValidationText.setTextFill(Color.rgb(164,190,235,1));
                empPayValidationText.setText("Valid zipcode required.");
                return result;
            }
        if(zipIn.length() < 5 || zipIn.length() > 5){
            empPayValidationText.setTextFill(Color.rgb(164,190,235,1));
            empPayValidationText.setText("Valid zipcode required.");
            return result;
        }
        String todaysDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        LocalDate currentDate = LocalDate.parse(todaysDate);
        LocalDate inputDate = LocalDate.parse(expDateIn);
        if(currentDate.isAfter(inputDate)){
            empPayValidationText.setTextFill(Color.rgb(164,190,235,1));
            empPayValidationText.setText("Expiration date must be in the future.");
            return result;
        }
        empPayValidationText.setTextFill(Color.rgb(164,190,235,1));
        empPayValidationText.setText("Payment Accepted!");
        result = true;
        return result;
    }
    /*
     * Cash validation method for the customer payment form
     * @param cashIn cash in to be validated as a string
     * @return true if amount is valid and false if not valid.
     */
    @FXML
    public boolean cashIsValid(String cashIn){
        boolean result = false;
        if (cashIn != "" || cashIn.length() > 6){
            return result;
        }
        empPayValidationText.setTextFill(Color.rgb(164,190,235,1));
        empPayValidationText.setText("Payment Accepted!");
        result = true;
     return result;
    }

    public void setValues(String newPhoneNumber, String newPassword) {

        currentCustomer.phoneNumber = newPhoneNumber; //Stub add number from dialog
        currentCustomer.password = newPassword; //Stub add number from dialog
        dataAccess.addCustomer(currentCustomer);


    }
}