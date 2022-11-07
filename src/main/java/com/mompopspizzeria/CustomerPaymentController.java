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

        import java.net.URL;
        import java.util.ArrayList;
        import java.util.ResourceBundle;

public class CustomerPaymentController extends MomPopsPizzeriaMain implements Initializable {


    @FXML
    ListView<String> orderSummeryList;
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


    @FXML
    public void payNowBtnActionCuPaymentScene(ActionEvent event) {
        //enter validation code
        boolean isValid = false;
        if (isValid) {
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

    }


    @FXML
    public void backBtnActionCuPaymentScene(ActionEvent event) {
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
    @FXML
    public void cancelBtnActionCuPaymentScene(ActionEvent event) {
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
    @FXML
    private void homeBtnActionCuPaymentScene(ActionEvent event){
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
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] list = {"Medium Pizza Panned Sausage Mushrooms Pineapple Onions $8.25", "Bread Sticks $4.00","Pepesi Small $1.00","Medium Pizza Panned Cheese $6.00"};
        for(String s: list){
            orderSummeryCustomerPaymentList.getItems().add(s);
        }

    }

}

