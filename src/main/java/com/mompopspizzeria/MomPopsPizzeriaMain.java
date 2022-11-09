package com.mompopspizzeria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * This is the driver class for the 'Mom & Pop's Pizza' application.
 * This class extends the helper class. Place all algo in the helper class or create a new class.
 * Setup project IntelliJ IDE according to these instructions to be able to create executables .jar or .exe https://www.youtube.com/watch?v=0-bG2h2Xh1E
 * Follow All Steps!
 *
 * @author Russell Geary
 * @author
 * @author
 * @author
 * @version 1.4, 10.05.2022
 *
 */
public class MomPopsPizzeriaMain extends Application {
    static DataAccessInterface<CustomerModel> dataAccess = new Data<>();
    static EmployeeModel authenticatedEmployee = new EmployeeModel();
    static CustomerModel currentCustomer = new CustomerModel();
    static OrderModel currentOrder = new OrderModel(currentCustomer);
    static String currentUserGlobal = "";

    public void start(Stage stage) {

        //set current customer to guest which can be updated later if a customer logs in. Adds the customer to the db file
        //if they do not already exist.
        currentCustomer.firstName = "Guest";
        currentCustomer.lastName = "Customer";
        currentCustomer.phoneNumber = "1112224444";
        currentCustomer.password = "Password";
        currentCustomer.address1 = "123 Nowhere Ln";
        currentCustomer.city = "Hometown";
        currentCustomer.state = "GA";
        currentCustomer.zip = "30064";
        CustomerModel checkedCustomerModel = dataAccess.authenticateCustomer(currentCustomer.phoneNumber, currentCustomer.password);
        if(checkedCustomerModel.customerId == -1){
            dataAccess.addCustomer(currentCustomer);
        }else{
            currentCustomer = checkedCustomerModel;
        }
        currentUserGlobal = " " + currentCustomer.firstName + " " + currentCustomer.lastName;

        try {
            Parent root = FXMLLoader.load(getClass().getResource("home-view.fxml"));
            Scene scene = new Scene(root, 1200, 750);
            stage.setTitle("Mom and Pop's Pizzeria - Home");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void updateCurrentCustomer(CustomerModel customerIn){
        currentCustomer = customerIn;
        currentUserGlobal = " " + currentCustomer.firstName + " " + currentCustomer.lastName;
    }

}
