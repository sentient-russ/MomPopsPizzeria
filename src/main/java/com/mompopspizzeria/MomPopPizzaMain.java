package com.mompopspizzeria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * This is the driver class for the 'Mom & Pop's Pizza' application.
 * This class should only contain calls to methods and classes
 * This class extends the helper class. Place all algo in the helper class or create a new class.


 * Follow these instructions to be able to create exe https://www.youtube.com/watch?v=0-bG2h2Xh1E
 *
 * @author Russell Geary
 * @author
 * @author
 * @author
 * @version 1.2, 10/26/2022
 *
 */
public class MomPopPizzaMain extends Application {
    static DataAccessInterface<CustomerModel> dataAccess = new Data<>();
    static EmployeeModel authenticatedEmployee = new EmployeeModel();
    static CustomerModel authenticatedCustomer = new CustomerModel();
    static OrderModel currentOrder = new OrderModel(authenticatedCustomer);

    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(MomPopPizzaMain.class.getResource("home-view.fxml"));
        Scene homeScene = new Scene(fxmlLoader.load(), 1200, 750);
        primaryStage.setTitle("Mom and Pop's Pizza App - Home");
        primaryStage.setScene(homeScene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        OrderController.RussellTestMethod();//Note that the first test will fail until a new customer has been created.
        launch(args);

    }

}