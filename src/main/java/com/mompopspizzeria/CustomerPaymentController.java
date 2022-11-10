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
        import java.time.LocalDate;
        import java.net.URL;
        import java.text.DecimalFormat;
        import java.util.ArrayList;
        import java.util.ResourceBundle;

public class CustomerPaymentController extends MomPopsPizzeriaMain implements Initializable {


    @FXML
    ListView<String> orderSummeryList;
    @FXML
    private Label currentUserTextGlobal;
    @FXML
    protected Label customerPaymentTotalText;
    @FXML
    protected Label custPaymentValidationText;

    private Stage stage;
    private Scene scene;
    @FXML
    private TextField cardNumCustPaymentTextField;
    @FXML
    private TextField expDateCustPaymentTextField;
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
    @FXML
    private ListView orderSummeryCustomerPaymentList;
    private String currentUserGlobal = MomPopsPizzeriaMain.currentUserGlobal;

    @FXML
    public void payNowBtnActionCuPaymentScene(ActionEvent event) {
        String cardNum = cardNumCustPaymentTextField.getText();
        String expDate = expDateCustPaymentTextField.getText();
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
            //print reciepts
            //prompt to create account
            //save order details
            //reset global order and customer instances
            //display success animation
            //return to home screen


            try {
                Parent root = FXMLLoader.load(getClass().getResource("home-view.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root, 1200, 750);
                stage.setTitle("Mom and Pop's Pizzeria - Home");
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }
        custPaymentValidationText.setText("Valid information required.");
    }


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

    @FXML
    private void homeBtnActionCuPaymentScene(ActionEvent event) {
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
    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        double orderTotalDouble = currentOrder.orderTotal;
        customerPaymentTotalText.setText(DecimalFormat.getCurrencyInstance().format(orderTotalDouble));
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
     *Credit card numbers must be exactly 16 numeric characters
     *Expiration date must be in the future. It will be passed in as a string of characters
     *Must be three or four characters. No more, no less.
     *First name must be more than 1 character and less than or equal to 5 characters
     *Last name must be more than 1 character and less than or equal to 5 characters
     *Address must begin with a number character and be no less than 3 characters and less than or equal to 25 characters
     *City must be more than 2 characters and less than 25 characters with no numbers
     *State must be in the states array.
     *Zip code is exactly five numbers
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

            //CC Num
            if (cardNumIn.equals("") || cardNumIn.length() < 16 || cardNumIn.length() > 16) {
                custPaymentValidationText.setText("The credit card number is invalid.");
                result = false;
                return result;
            }
            //first name
            if (firstNameIn.equals("") || firstNameIn.length() < 2 || firstNameIn.length() > 20) {
                custPaymentValidationText.setText("Valid first name required.");
                result = false;
                return result;
            }
            //last name
            if (lastNameIn.equals("") || lastNameIn.length() < 2 || lastNameIn.length() > 20) {
                custPaymentValidationText.setText("Valid last name required.");
                result = false;
                return result;
            }
            //addr 1
            if (addr1In.equals("") || addr1In.length() < 2 || addr1In.length() > 25) {
                custPaymentValidationText.setText("Valid address line one required.");
                result = false;
                return result;
            }
            boolean firstCharacterIsNumber = false;
            String addrFirstCharacter = addr1In.split("")[0];
            try {
                int number = Integer.parseInt(addrFirstCharacter);
            } catch (NumberFormatException e) {
                e.getCause();
                return result = false;
            }
            //adr line 2
            if (addr2In.equals("") || addr2In.length() > 25) {
                custPaymentValidationText.setText("Address line 2 in invalid");
                result = false;
                return result;
            }
            //adr line 2
            if (cityIn.equals("") || cityIn.length() < 25) {
                custPaymentValidationText.setText("The city name is invalid");
                result = false;
                return result;
            }
            //state
            boolean stateFound = false;
            String upperCaseState = stateIn.toUpperCase();
            for (String s : states) {
                if (upperCaseState.equals(s)) {
                    stateFound = true;
                }
            }
            if (stateFound == false) {
                custPaymentValidationText.setText("Valid state required.");
                result = false;
                return result;
            }
            //zip
            boolean zipFound = false;
            String upperCaseZip = stateIn.toUpperCase();
            for (String n : letersAndSymbols) {
                if (upperCaseZip.equals(n)) {
                    return result = false;
                }
            }
            //Put in check for expiration date and your done with this.


        return result = true;
    }
}