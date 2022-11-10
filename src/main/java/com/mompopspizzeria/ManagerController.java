package com.mompopspizzeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerController extends MomPopsPizzeriaMain implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private void returnHomeAction(ActionEvent event){
        boolean confirmed = true;


        //Stub insert call to method to display confirmation dialog box


        if(confirmed){
            authenticatedEmployee.isEmployee = false;
            authenticatedEmployee.isManager = false;
            CustomerModel guest = dataAccess.authenticateCustomer(guestPhoneNumber,guestPassword);
            updateCurrentCustomer(guest);

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
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void jumpToorderEntryAction(ActionEvent event) {
        authenticatedEmployee.isEmployee = false;
        authenticatedEmployee.isManager = false;
        CustomerModel employee = dataAccess.authenticateCustomer(emplyeePhoneNumber,employeePassword);
        employee.isEmployee = true;
        updateCurrentCustomer(employee);

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

}

