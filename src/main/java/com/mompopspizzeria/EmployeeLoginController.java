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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeLoginController extends MomPopsPizzeriaMain implements Initializable {


    @FXML
    private Label empLoginValidationText;
    @FXML
    private Button backButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button cancelButton;
    @FXML
    private RadioButton empLoginEmpRadioBtn;
    @FXML
    private RadioButton empLoginManagerRadioBtn;
    @FXML
    private final ToggleGroup btnGroup = new ToggleGroup();//do not delete. Used by Gluon

    @FXML
    private PasswordField passwordEmployeeLogin;

    public EmployeeLoginController() {
    }

    @FXML
    protected void cancelBtnCustLogin() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void homeBtnCustLogin() {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void backBtnCustLogin() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void empLoginAction() {
        empLoginValidationText.setText("Please Wait!");
        try {
            String password = passwordEmployeeLogin.getText();
            if (empLoginEmpRadioBtn.isSelected() && authenticatedEmployee.checkEmployeePassword(password)) {
                authenticatedEmployee.isEmployee = true;
                empLoginValidationText.setText("Success! - Logged in as Employee");

                //Stub call method to transition to order stage

            } else if (empLoginManagerRadioBtn.isSelected() && authenticatedEmployee.checkManagerPassword(password)) {
                authenticatedEmployee.isManager = true;
                empLoginValidationText.setText("Success! - Logged in as Manager");

                //Stub call method to open reports menu

            } else {
                empLoginValidationText.setText("Login Failed! - Temporary notification: Employee Password = 2345 Manager Password = 9876");
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private void returnHomeAction(ActionEvent event){
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




    @FXML
    protected void callKeyboard() {
        try {
            Runtime.getRuntime().exec("cmd /c osk");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}

