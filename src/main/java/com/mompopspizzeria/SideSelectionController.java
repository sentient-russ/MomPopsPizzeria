
package com.mompopspizzeria;


        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.fxml.Initializable;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Label;
        import javafx.stage.Stage;

        import java.net.URL;
        import java.util.ResourceBundle;

public class SideSelectionController extends MomPopsPizzeriaMain implements Initializable {


    @FXML
    private Label sideSelectionValidationText;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
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
        //insert code for validation method
        boolean isValid = false;


        if(isValid){
            //insert code here to add drinks to order

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

        }else{
            sideSelectionValidationText.setText("Please make sure all line items are complete or have been removed and try again.");
        }


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
