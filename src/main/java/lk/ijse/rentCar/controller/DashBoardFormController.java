package lk.ijse.rentCar.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {
    public AnchorPane dashBoardRoot;
    public AnchorPane dashBoardBodyRoot;

    public void btnCarCategoryOnAction(ActionEvent actionEvent) throws IOException {
       Parent root=FXMLLoader.load(this.getClass().getResource("/view/CarCatogery_Form.fxml"));
       this.dashBoardBodyRoot.getChildren().clear();
       this.dashBoardBodyRoot.getChildren().add(root);
    }

    public void btnCarsOnAction(ActionEvent actionEvent) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/view/Cars_Form.fxml"));
        this.dashBoardBodyRoot.getChildren().clear();
        this.dashBoardBodyRoot.getChildren().add(root);
    }

    public void btnCustOnAction(ActionEvent actionEvent) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/view/Customer_Form.fxml"));

        this.dashBoardBodyRoot.getChildren().clear();
        this.dashBoardBodyRoot.getChildren().add(root);
    }

    public void btnRentOnAction(ActionEvent actionEvent) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/view/Rent_Form.fxml"));
        this.dashBoardBodyRoot.getChildren().clear();
        this.dashBoardBodyRoot.getChildren().add(root);
    }

    public void btnUsersOnAction(ActionEvent actionEvent) {
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/view/Login_Form.fxml"));

        Scene scene=new Scene(root);
        Stage primaryStage= (Stage) this.dashBoardRoot.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.centerOnScreen();

    }
}
