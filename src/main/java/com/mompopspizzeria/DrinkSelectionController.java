package com.mompopspizzeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DrinkSelectionController extends MomPopsPizzeriaMain implements Initializable {

    static String smallDrink = "Small";
    static String mediumDrink = "Medium";
    static String largeDrink = "Large";
    static String pepsi = "Pepesi";
    static String dietPepsi = "Diet Pepsi";
    static String orange = "Orange";
    static String dietOrange = "Diet Orange";
    static String rootbeer = "Rootbeer";
    static String dietRootbeer = "Diet Rootbeer";
    static String sierraMist = "Sierra Mist";
    static String lemonade = "Lemonade";
    @FXML
    protected Button addSingleBtnDrinkSelectionScene1;
    @FXML
    protected Button addSingleBtnDrinkSelectionScene2;
    @FXML
    protected Button addSingleBtnDrinkSelectionScene3;
    @FXML
    protected Button addSingleBtnDrinkSelectionScene4;
    @FXML
    protected Button removeSingleBtnDrinkSelectionScene2;
    @FXML
    protected Button removeSingleBtnDrinkSelectionScene3;
    @FXML
    protected Button removeSingleBtnDrinkSelectionScene4;

    @FXML
    protected ComboBox<String> drinkMenuDropdownAction1;
    @FXML
    protected ComboBox<String> drinkMenuDropdownAction2;
    @FXML
    protected ComboBox<String> drinkMenuDropdownAction3;
    @FXML
    protected ComboBox<String> drinkMenuDropdownAction4;
    @FXML
    protected ComboBox<String> drinkSizeDropdown1;

    @FXML
    protected ComboBox<String> drinkSizeDropdown2;
    @FXML
    protected ComboBox<String> drinkSizeDropdown3;
    @FXML
    protected ComboBox<String> drinkSizeDropdown4;
    @FXML
    protected ComboBox<String> drinkQtyDropdown1;
    @FXML
    protected ComboBox<String> drinkQtyDropdown2;
    @FXML
    protected ComboBox<String> drinkQtyDropdown3;
    @FXML
    protected ComboBox<String> drinkQtyDropdown4;
    @FXML
    protected Label drinkSelectionValidationText;
    @FXML
    protected Label drinkSelectionTotalText;
    @FXML
    protected Label drinkFlavorText2;
    @FXML
    protected Label drinkFlavorText3;
    @FXML
    protected Label drinkFlavorText4;
    @FXML
    protected Label sizeText2;
    @FXML
    protected Label sizeText3;
    @FXML
    protected Label sizeText4;
    @FXML
    protected Label qtyText2;
    @FXML
    protected Label qtyText3;
    @FXML
    protected Label qtyText4;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;

    static LineItemModel line1 = new LineItemModel();
    static LineItemModel line2 = new LineItemModel();
    static LineItemModel line3 = new LineItemModel();
    static LineItemModel line4 = new LineItemModel();


    private int totalDrinkQtyOrdered = 0;
    private double pricePerDrink = 1.00;
    private void updateDisplayedTotal(){
        double currentDrinkTotal;
        currentDrinkTotal = totalDrinkQtyOrdered * pricePerDrink;
        String totalString = DecimalFormat.getCurrencyInstance().format(currentDrinkTotal);
    }


    @FXML
    private void homeBtnActionDrinkSelectionScene(ActionEvent event){
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
    private void backBtnActionDrinkSelectionScene(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("order-view.fxml"));
            Scene scene = new Scene(root, 1200, 750);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Mom and Pop's Pizzeria - Order Options");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    @FXML
    private void cancelBtnActionDrinkSelectionScene(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("order-view.fxml"));
            Scene scene = new Scene(root, 1200, 750);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Mom and Pop's Pizzeria - Order Options");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    @FXML
    private void addSingleBtnActionDrinkSelectionScene1(){
        if(drinkMenuDropdownAction1.getValue() == null || drinkSizeDropdown1.getValue() == null || drinkQtyDropdown1.getValue() == null) {
            drinkSelectionValidationText.setText("Flavor, Size and a Quantity must be selected.");
        }else{
            String lineDrinkSize = drinkSizeDropdown1.getValue();
            String lineDrinkSizeWithoutPrice = lineDrinkSize.replace(" $1.00", "");
            String lineFlavor = drinkMenuDropdownAction1.getValue();
            int lineQty = Integer.parseInt(drinkQtyDropdown1.getValue());
            totalDrinkQtyOrdered = totalDrinkQtyOrdered + lineQty;
            LineItemModel line1 = new LineItemModel();
            line1.addDrink(lineFlavor, lineDrinkSizeWithoutPrice, lineQty);
            this.line1 = line1;

            updateTextTotal();


            drinkMenuDropdownAction2.setVisible(true);
            drinkMenuDropdownAction2.setDisable(false);
            drinkSizeDropdown2.setVisible(true);
            drinkSizeDropdown2.setDisable(false);
            drinkQtyDropdown2.setVisible(true);
            drinkQtyDropdown2.setDisable(false);
            addSingleBtnDrinkSelectionScene2.setVisible(true);
            addSingleBtnDrinkSelectionScene2.setDisable(false);
            removeSingleBtnDrinkSelectionScene2.setVisible(true);
            removeSingleBtnDrinkSelectionScene2.setDisable(false);
            drinkFlavorText2.setVisible(true);
            sizeText2.setVisible(true);
            qtyText2.setVisible(true);
            drinkSelectionValidationText.setText("");

        }
    }

    @FXML
    private void addSingleBtnActionDrinkSelectionScene2(){
        if(drinkMenuDropdownAction2.getValue() == null || drinkSizeDropdown2.getValue() == null || drinkQtyDropdown2.getValue() == null) {
            drinkSelectionValidationText.setText("Flavor, Size and a Quantity must be selected.");
        }else{
            String lineDrinkSize = drinkSizeDropdown2.getValue();
            String lineDrinkSizeWithoutPrice = lineDrinkSize.replace(" $1.00", "");
            String lineFlavor = drinkMenuDropdownAction2.getValue();
            int lineQty = Integer.parseInt(drinkQtyDropdown2.getValue());
            totalDrinkQtyOrdered = totalDrinkQtyOrdered + lineQty;
            LineItemModel line2 = new LineItemModel();
            line2.addDrink(lineFlavor, lineDrinkSizeWithoutPrice, lineQty);
            this.line2 = line2;
            updateTextTotal();

            drinkMenuDropdownAction3.setVisible(true);
            drinkMenuDropdownAction3.setDisable(false);
            drinkSizeDropdown3.setVisible(true);
            drinkSizeDropdown3.setDisable(false);
            drinkQtyDropdown3.setVisible(true);
            drinkQtyDropdown3.setDisable(false);
            addSingleBtnDrinkSelectionScene3.setVisible(true);
            addSingleBtnDrinkSelectionScene3.setDisable(false);
            removeSingleBtnDrinkSelectionScene3.setVisible(true);
            removeSingleBtnDrinkSelectionScene3.setDisable(false);
            drinkFlavorText3.setVisible(true);
            sizeText3.setVisible(true);
            qtyText3.setVisible(true);
            drinkSelectionValidationText.setText("");
        }
    }

    @FXML
    private void addSingleBtnActionDrinkSelectionScene3(){
        if(drinkMenuDropdownAction3.getValue() == null || drinkSizeDropdown3.getValue() == null || drinkQtyDropdown3.getValue() == null) {
            drinkSelectionValidationText.setText("Flavor, Size and a Quantity must be selected.");
        }else{
            String lineDrinkSize = drinkSizeDropdown3.getValue();
            String lineDrinkSizeWithoutPrice = lineDrinkSize.replace(" $1.00", "");
            String lineFlavor = drinkMenuDropdownAction3.getValue();
            int lineQty = Integer.parseInt(drinkQtyDropdown3.getValue());
            totalDrinkQtyOrdered = totalDrinkQtyOrdered + lineQty;
            LineItemModel line3 = new LineItemModel();
            line3.addDrink(lineFlavor, lineDrinkSizeWithoutPrice, lineQty);
            this.line3 = line3;
            updateTextTotal();

            drinkSelectionValidationText.setText("");
            drinkMenuDropdownAction4.setVisible(true);
            drinkMenuDropdownAction4.setDisable(false);
            drinkSizeDropdown4.setVisible(true);
            drinkSizeDropdown4.setDisable(false);
            drinkQtyDropdown4.setVisible(true);
            drinkQtyDropdown4.setDisable(false);
            addSingleBtnDrinkSelectionScene4.setVisible(true);
            addSingleBtnDrinkSelectionScene4.setDisable(false);
            removeSingleBtnDrinkSelectionScene4.setVisible(true);
            removeSingleBtnDrinkSelectionScene4.setDisable(false);
            drinkFlavorText4.setVisible(true);
            sizeText4.setVisible(true);
            qtyText4.setVisible(true);

        }
    }
    @FXML
    private void addSingleBtnActionDrinkSelectionScene4(){
        if(drinkMenuDropdownAction4.getValue() == null || drinkSizeDropdown4.getValue() == null || drinkQtyDropdown4.getValue() == null) {
            drinkSelectionValidationText.setText("Flavor, Size and a Quantity must be selected.");
        }else{
            String lineDrinkSize = drinkSizeDropdown4.getValue();
            String lineDrinkSizeWithoutPrice = lineDrinkSize.replace(" $1.00", "");
            String lineFlavor = drinkMenuDropdownAction4.getValue();
            int lineQty = Integer.parseInt(drinkQtyDropdown4.getValue());
            totalDrinkQtyOrdered = totalDrinkQtyOrdered + lineQty;
            LineItemModel line4 = new LineItemModel();
            line4.addDrink(lineFlavor, lineDrinkSizeWithoutPrice, lineQty);
            this.line4 = line4;
            updateTextTotal();
            drinkSelectionValidationText.setText("");
        }
    }
    @FXML
    private void updateTextTotal(){
        double total;
        total = this.line1.lineTotal + this.line2.lineTotal + this.line3.lineTotal + this.line4.lineTotal;
        String stringTotal = DecimalFormat.getCurrencyInstance().format(total);
        drinkSelectionTotalText.setText(stringTotal);
    }
    @FXML
    private void removesingelLineActionDrinkSelectionScene2(ActionEvent event){
        LineItemModel blankLine = new LineItemModel();
        line2 = blankLine;//////////////////////////////////////////////////////////////////////////////////Look here.
        updateTextTotal();
        drinkMenuDropdownAction2.setVisible(false);
        drinkMenuDropdownAction2.setDisable(true);
        drinkSizeDropdown2.setVisible(false);
        drinkSizeDropdown2.setDisable(true);
        drinkQtyDropdown2.setVisible(false);
        drinkQtyDropdown2.setDisable(true);
        addSingleBtnDrinkSelectionScene2.setVisible(false);
        addSingleBtnDrinkSelectionScene2.setDisable(true);
        removeSingleBtnDrinkSelectionScene2.setVisible(false);
        removeSingleBtnDrinkSelectionScene2.setDisable(true);
        drinkFlavorText2.setVisible(false);
        sizeText2.setVisible(false);
        qtyText2.setVisible(false);
    }
    @FXML
    private void removesingelLineActionDrinkSelectionScene3(ActionEvent event){
        LineItemModel blankLine = new LineItemModel();
        line3 = blankLine;
        updateTextTotal();
        drinkMenuDropdownAction3.setVisible(false);
        drinkMenuDropdownAction3.setDisable(true);
        drinkSizeDropdown3.setVisible(false);
        drinkSizeDropdown3.setDisable(true);
        drinkQtyDropdown3.setVisible(false);
        drinkQtyDropdown3.setDisable(true);
        addSingleBtnDrinkSelectionScene3.setVisible(false);
        addSingleBtnDrinkSelectionScene3.setDisable(true);
        removeSingleBtnDrinkSelectionScene3.setVisible(false);
        removeSingleBtnDrinkSelectionScene3.setDisable(true);
        drinkFlavorText3.setVisible(false);
        sizeText3.setVisible(false);
        qtyText3.setVisible(false);
    }
    @FXML
    private void removesingelLineActionDrinkSelectionScene4(ActionEvent event){
        LineItemModel blankLine = new LineItemModel();
        line4 = blankLine;
        updateTextTotal();
        drinkMenuDropdownAction4.setVisible(false);
        drinkMenuDropdownAction4.setDisable(true);
        drinkSizeDropdown4.setVisible(false);
        drinkSizeDropdown4.setDisable(true);
        drinkQtyDropdown4.setVisible(false);
        drinkQtyDropdown4.setDisable(true);
        addSingleBtnDrinkSelectionScene4.setVisible(false);
        addSingleBtnDrinkSelectionScene4.setDisable(true);
        removeSingleBtnDrinkSelectionScene4.setVisible(false);
        removeSingleBtnDrinkSelectionScene4.setDisable(true);
        drinkFlavorText4.setVisible(false);
        sizeText4.setVisible(false);
        qtyText4.setVisible(false);
    }


    @FXML
    private void addAllBtnActionDrinkSelectionScene(ActionEvent event) {
        if(this.line1 == null){
            //error enter a drink selection
        }else if(this.line1  != null && this.line2 == null && this.line3 == null && this.line4 == null){
            currentOrder.addLineItem(this.line1);
            backToOrderOptions(event);
        }else if(this.line1  != null && this.line2 != null && this.line3 == null && this.line4 == null) {
            currentOrder.addLineItem(this.line1);
            currentOrder.addLineItem(this.line2);
            backToOrderOptions(event);
        }else if(this.line1  != null && this.line2 != null && this.line3 != null && this.line4 == null) {
            currentOrder.addLineItem(this.line1);
            currentOrder.addLineItem(this.line2);
            currentOrder.addLineItem(this.line3);
            backToOrderOptions(event);
        }else if(this.line1  != null && this.line2 != null && this.line3 != null && this.line4 != null) {
            currentOrder.addLineItem(this.line1);
            currentOrder.addLineItem(this.line2);
            currentOrder.addLineItem(this.line3);
            currentOrder.addLineItem(this.line4);
            backToOrderOptions(event);
        }

    }
    @FXML
    public void backToOrderOptions(ActionEvent event){

        try {
            Parent root = FXMLLoader.load(getClass().getResource("order-view.fxml"));
            Scene scene = new Scene(root, 1200, 750);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Mom and Pop's Pizzeria - Order Options");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hideFields();
        //add drinks
        ArrayList<DrinkModel> drinks = dataAccess.getDrinks();
        for(int i = 0; i < drinks.size(); i++){
            String menuItem = drinks.get(i).description;
            drinkMenuDropdownAction1.getItems().add(menuItem);
            drinkMenuDropdownAction2.getItems().add(menuItem);
            drinkMenuDropdownAction3.getItems().add(menuItem);
            drinkMenuDropdownAction4.getItems().add(menuItem);
        }
        //add sizes
        ArrayList<DrinkSizeModel> sizes = dataAccess.getDrinkSizes();
        for(int i = 0; i < sizes.size(); i++){
            String menuItem = sizes.get(i).description + " " + DecimalFormat.getCurrencyInstance().format(sizes.get(i).price);
            drinkSizeDropdown1.getItems().add(menuItem);
            drinkSizeDropdown2.getItems().add(menuItem);
            drinkSizeDropdown3.getItems().add(menuItem);
            drinkSizeDropdown4.getItems().add(menuItem);
        }
        //add qty values
        for(int i = 1;i <= 30;i++){
            drinkQtyDropdown1.getItems().add(String.valueOf(i));
            drinkQtyDropdown2.getItems().add(String.valueOf(i));
            drinkQtyDropdown3.getItems().add(String.valueOf(i));
            drinkQtyDropdown4.getItems().add(String.valueOf(i));
        }
    }
    @FXML
    private void hideFields(){
        drinkMenuDropdownAction2.setVisible(false);
        drinkMenuDropdownAction2.setDisable(true);
        drinkSizeDropdown2.setVisible(false);
        drinkSizeDropdown2.setDisable(true);
        drinkQtyDropdown2.setVisible(false);
        drinkQtyDropdown2.setDisable(true);
        addSingleBtnDrinkSelectionScene2.setVisible(false);
        addSingleBtnDrinkSelectionScene2.setDisable(true);
        removeSingleBtnDrinkSelectionScene2.setVisible(false);
        removeSingleBtnDrinkSelectionScene2.setDisable(true);
        drinkFlavorText2.setVisible(false);
        sizeText2.setVisible(false);
        qtyText2.setVisible(false);

        drinkMenuDropdownAction3.setVisible(false);
        drinkMenuDropdownAction3.setDisable(true);
        drinkSizeDropdown3.setVisible(false);
        drinkSizeDropdown3.setDisable(true);
        drinkQtyDropdown3.setVisible(false);
        drinkQtyDropdown3.setDisable(true);
        addSingleBtnDrinkSelectionScene3.setVisible(false);
        addSingleBtnDrinkSelectionScene3.setDisable(true);
        removeSingleBtnDrinkSelectionScene3.setVisible(false);
        removeSingleBtnDrinkSelectionScene3.setDisable(true);
        drinkFlavorText3.setVisible(false);
        sizeText3.setVisible(false);
        qtyText3.setVisible(false);

        drinkMenuDropdownAction4.setVisible(false);
        drinkMenuDropdownAction4.setDisable(true);
        drinkSizeDropdown4.setVisible(false);
        drinkSizeDropdown4.setDisable(true);
        drinkQtyDropdown4.setVisible(false);
        drinkQtyDropdown4.setDisable(true);
        addSingleBtnDrinkSelectionScene4.setVisible(false);
        addSingleBtnDrinkSelectionScene4.setDisable(true);
        removeSingleBtnDrinkSelectionScene4.setVisible(false);
        removeSingleBtnDrinkSelectionScene4.setDisable(true);
        drinkFlavorText4.setVisible(false);
        sizeText4.setVisible(false);
        qtyText4.setVisible(false);
    }
}
