package com.mompopspizzeria;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.ComboBox;
        import javafx.scene.control.Label;
        import javafx.stage.Stage;
        import java.net.URL;
        import java.text.DecimalFormat;
        import java.util.ResourceBundle;

public class SideSelectionController extends MomPopsPizzeriaMain implements Initializable {

    static String breadBites = "Bread Stick Bites";
    static String breadSticks = "Bread Sticks";
    static String cookie = "Big Chocolate Chip Cookie";
    static double sideTotalDouble;
    static String sideTotalString = "$0.00";

    LineItemModel lineBreadSticks = new LineItemModel();
    LineItemModel lineBreadBites = new LineItemModel();
    LineItemModel lineBigCookie = new LineItemModel();
    @FXML
    protected ComboBox<Integer> sideQtyDropdown1;
    @FXML
    protected ComboBox<Integer> sideQtyDropdown2;
    @FXML
    protected ComboBox<Integer> sideQtyDropdown3;
    @FXML
    private Label sideTotalText;
    @FXML
    private Stage stage;
    @FXML
    private void homeBtnActionSideSelectionScene(ActionEvent event){
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
    private void backBtnActionSideSelectionScene(ActionEvent event){
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
    private void cancelBtnActionSideSelectionScene(ActionEvent event){
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
    private void addBtnActionSidSelectionScene(ActionEvent event){
        if(lineBreadSticks.lineTotal > 0){
            currentOrder.addLineItem(lineBreadSticks);
        }
        if(lineBreadBites.lineTotal > 0){
            currentOrder.addLineItem(lineBreadBites);
        }
        if(lineBigCookie.lineTotal > 0){
            currentOrder.addLineItem(lineBigCookie);
        }

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
    protected void adjustSideQty(ActionEvent event){
        if(sideQtyDropdown1.getValue() >= 1){
            int qty = sideQtyDropdown1.getValue();
            lineBreadSticks.addSide(breadSticks,qty);
        }else{
            LineItemModel newLine = new LineItemModel();
            lineBreadSticks = newLine;
        }

        if(sideQtyDropdown2.getValue() >= 1){
            int qty = sideQtyDropdown2.getValue();
            lineBreadBites.addSide(breadBites,qty);
        }else{
            LineItemModel newLine = new LineItemModel();
            lineBreadBites = newLine;
        }

        if(sideQtyDropdown3.getValue() >= 1){
            int qty = sideQtyDropdown3.getValue();
            lineBigCookie.addSide(cookie,qty);
        }else{
            LineItemModel newLine = new LineItemModel();
            lineBigCookie = newLine;
        }

        updateTotalText();
    }
    @FXML
    public void updateTotalText(){
        sideTotalDouble = lineBreadSticks.lineTotal + lineBreadBites.lineTotal + lineBigCookie.lineTotal;
        sideTotalString = DecimalFormat.getCurrencyInstance().format(sideTotalDouble);
        sideTotalText.setText(sideTotalString);

    }
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sideTotalText.setText(sideTotalString);
        //add qty values
        for(int i = 0;i <= 30;i++){
            sideQtyDropdown1.getItems().add(i);
            sideQtyDropdown2.getItems().add(i);
            sideQtyDropdown3.getItems().add(i);
        }
        //required to prevent null pointer exception
        sideQtyDropdown1.setValue(0);
        sideQtyDropdown2.setValue(0);
        sideQtyDropdown3.setValue(0);
    }
}
