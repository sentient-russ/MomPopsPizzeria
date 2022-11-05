package com.mompopspizzeria;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class MomPopHomeController extends MomPopPizzaMain{


    public void orderEntryForm() throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("order-view.fxml"));
            Stage orderStage = new Stage();
            orderStage.setTitle("Mom and Pop's Pizza App - Order Entry");
            orderStage.setScene(new Scene(root, 1200, 750));
            orderStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void customerLoginForm() throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("custLogin-view.fxml"));
            Stage customerLoginStage = new Stage();
            customerLoginStage.setTitle("Mom and Pop's Pizza App - Customer Login");
            customerLoginStage.setScene(new Scene(root, 1200, 750));
            customerLoginStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void customerRegistrationForm() throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("registration-view.fxml"));
            Stage customerRegistrationStage = new Stage();
            customerRegistrationStage.setTitle("Mom and Pop's Pizza App - Customer Registration");
            customerRegistrationStage.setScene(new Scene(root, 1200, 750));
            customerRegistrationStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }
    public void employeeLoginForm() throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("empLogin-view.fxml"));
            Stage employeeLoginStage = new Stage();
            employeeLoginStage.setTitle("Mom and Pop's Pizza App - Employee Login");
            employeeLoginStage.setScene(new Scene(root, 1200, 750));
            employeeLoginStage.show();


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

}