package com.mompopspizzeria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.*;
import java.nio.file.Paths;

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
    static merchantServicesConnector<String> ccProcessor = new merchantServicesConnector<>();


    static String currentUserGlobal = "";
    static String emplyeePhoneNumber= "1234567890";
    static String employeePassword = "S1o2M3t4H5i6N7g8C9o0M1p2L3i4C5a6T7eD8";
    static String guestPhoneNumber = "1112224444";
    static String guestPassword = "ESM1Po2M3t4H5i6N7g8CoMpLiCaTeD";

    public void start(Stage stage) {

        boolean employeeExists;
        CustomerModel employee = dataAccess.authenticateCustomer(emplyeePhoneNumber,employeePassword);
        if(employee.customerId == -1){
            employee.firstName = "COMPANY";
            employee.lastName = "EMPLOYEE";
            employee.address1 = "680 Arntson Rd";
            employee.address2 = "suite 161";
            employee.city = "Marietta";
            employee.state = "GA";
            employee.zip = "30060";
            employee.phoneNumber = emplyeePhoneNumber;
            employee.password = employeePassword;
            dataAccess.addCustomer(employee);

        }
        //set current customer to guest which can be updated later if a customer logs in. Adds/Seeds the customer to the db file
        //if they do not already exist.
        currentCustomer.firstName = "Guest";
        currentCustomer.lastName = "Customer";
        currentCustomer.phoneNumber = guestPhoneNumber;
        currentCustomer.password = guestPassword;
        currentCustomer.address1 = "680 Arntson Rd";
        currentCustomer.address2 = "suite 161";
        currentCustomer.city = "Marietta";
        currentCustomer.state = "GA";
        currentCustomer.zip = "30060";
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
            stage.getIcons().add(new Image("file:"+String.valueOf(Paths.get(System.getProperty("user.dir"),"res","MomAndPopsPizzeriaIcon.png"))));
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
