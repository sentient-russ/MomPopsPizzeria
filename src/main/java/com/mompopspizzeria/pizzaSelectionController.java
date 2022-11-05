package com.mompopspizzeria;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class pizzaSelectionController {
    @FXML
    private Label orderValidationText;
    @FXML
    private Label pizzaTotalText;
    @FXML
    private CheckBox extraCheeseToppingChkBox;
    @FXML
    private CheckBox pepperoniToppingChkBox;
    @FXML
    private CheckBox sausageToppingChkBox;
    @FXML
    private CheckBox hamToppingChkBox;
    @FXML
    private CheckBox greenPeppersToppingChkBox;
    @FXML
    private CheckBox onionsToppingChkBox;
    @FXML
    private CheckBox tomatoSlicesToppingChkBox;
    @FXML
    private CheckBox mushroomsToppingChkBox;
    @FXML
    private CheckBox pineappleToppingChkBox;
    @FXML
    private ComboBox pizzaSize;
    @FXML
    private ComboBox pizzaCrust;
    @FXML
    private Button backBtnOrderScene;
    @FXML
    private Button homeBtnOrderScene;
    @FXML
    private Button cancelBtnOrderScene;
    @FXML
    protected void cancelBtnActionOrderScene() {
        Stage stage = (Stage) cancelBtnOrderScene.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void homeBtnActionOrderScene() {
        Stage stage = (Stage) homeBtnOrderScene.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void backBtnActionOrderScene() {
        Stage stage = (Stage) backBtnOrderScene.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void addPizzaBtnActionPizzaSelection(){

    }

}
