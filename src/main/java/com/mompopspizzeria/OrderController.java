package com.mompopspizzeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderController extends MomPopsPizzeriaMain implements Initializable {

    @FXML
    private final ToggleGroup orderTypeGroup = new ToggleGroup();//do not delete. Used by Gluon

    @FXML
    private Button backBtnOrderScene;
    @FXML
    private Button homeBtnOrderScene;
    @FXML
    private Button cancelBtnOrderScene;
    @FXML
    private Button payBtnOrderScene;
    @FXML
    private Button addPizzaBtn;
    @FXML
    private Label listItemSelectedText;
    @FXML
    private Button updateBtnOrderScene;
    @FXML
    ListView<String> orderSummeryList;
    private Stage stage;
    private Scene scene;
    @FXML
    public void addPizzaBtnActionOrderScene(ActionEvent event)  {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("pizzaSelection-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root,1200, 750);
            stage.setTitle("Mom and Pop's Pizzeria - Pizza Selection");
            stage.setScene(scene);
            stage.show();



        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
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
    protected void payBtnActionOrderScene() {
        Stage stage = (Stage) payBtnOrderScene.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] list = {"Medium Pizza Panned Sausage Mushrooms Pineapple Onions $8.25", "Bread Sticks $4.00","Pepesi Small $1.00","Medium Pizza Panned Cheese $6.00"};
        for(String s: list){
            orderSummeryList.getItems().add(s);
        }

    }

}

