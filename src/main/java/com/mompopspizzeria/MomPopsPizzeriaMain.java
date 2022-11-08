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
    static CustomerModel authenticatedCustomer = new CustomerModel();
    static OrderModel currentOrder = new OrderModel(authenticatedCustomer);

    public void start(Stage stage) {
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

}