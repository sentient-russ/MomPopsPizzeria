package com.mompopspizzeria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.nio.file.Paths;

/*
 * This is the main class for the 'Mom & Pop's Pizza' application. Default manager and employee pin/passwords can be changed
 * here. This class is extended by the controllers so that they can pass the current models from one view to the next
 * @author Russell Geary
 * @version 7.1 11/15/2022
 */
public class MomPopsPizzeriaMain extends Application {
    static DataAccessInterface<CustomerModel> dataAccess = new Data<>();
    static EmployeeModel authenticatedEmployee = new EmployeeModel();
    static CustomerModel currentCustomer = new CustomerModel();
    static CustomerModel lastCustomer = new CustomerModel();
    static OrderModel currentOrder = new OrderModel(currentCustomer);
    static OrderModel lastOrder = new OrderModel(lastCustomer);
    static MerchantServicesConnector<String> ccProcessor = new MerchantServicesConnector<>();
    static String employeeModelPassword = "2345"; //default employee pin/password
    static String managerModelPassword = "9876"; //default manager pin/password
    static String currentUserGlobal = "";
    static String employeePhoneNumber = "1234567890";
    static String employeePassword = "S1o2M3t4H5i6N7g8C9o0M1p2L3i4C5a6T7eD8"; //Internal use only change the employee pin listed above
    static String guestPhoneNumber = "1112224444";
    static String guestPassword = "ESM1Po2M3t4H5i6N7g8CoMpLiCaTeD"; //Internal use only
    public Stage parentStage;

    /*
     * The application begins here by initiating an order. The current customer is set to guest which can be updated
     * later if a customer logs in. Adds/Seeds the employee and customer to the db file the first time the application is run
     * and loads the initial boot up screen
     */
    public void start(Stage stage) {
        parentStage = stage;
        CustomerModel employee = dataAccess.authenticateCustomer(employeePhoneNumber,employeePassword);
        if(employee.customerId == -1){
            employee.firstName = "COMPANY";
            employee.lastName = "EMPLOYEE";
            employee.address1 = "680 Arntson Rd";
            employee.address2 = "suite 161";
            employee.city = "Marietta";
            employee.state = "GA";
            employee.zip = "30060";
            employee.phoneNumber = employeePhoneNumber;
            employee.password = employeePassword;
            dataAccess.addCustomer(employee);
        }
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
    /*
     * Initializes the program by passing in the application input Strings
     */
    public static void main(String[] args) {
        launch(args);
    }
    /*
     * Resets the global current customer and shared currentUserGlobal String for displaying the logged-in user in the various views.
     */
    public void updateCurrentCustomer(CustomerModel customerIn){
        currentCustomer = customerIn;
        currentUserGlobal = " " + currentCustomer.firstName + " " + currentCustomer.lastName;
    }
    /*
     * This method is called at the end of the ordering process or when the customer is changed from guest to another user.
     */
    public void  orderReset(){
        //reset global order and customer instances
        CustomerModel nextGuest = dataAccess.authenticateCustomer(guestPhoneNumber,guestPassword);
        updateCurrentCustomer(nextGuest);
        currentOrder = new OrderModel(currentCustomer);
    }
}
