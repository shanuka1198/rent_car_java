package lk.ijse.rentCar.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lk.ijse.rentCar.db.Db;

import java.io.IOException;

public class LoginFormController {
    public TextField txtUserPassword;
    public TextField txtUserName;
    public ImageView rootLogin;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {

        String user= txtUserName.getText();
        String pw= txtUserPassword.getText();

        if(user.equals(Db.username)&& pw.equals(Db.password)){
            navigate();
        }else{
            cler();
            new Alert(Alert.AlertType.ERROR,"User name or Password Incorrect").show();
        }

    }
    public void navigate() throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/view/DashBoard_Form.fxml"));

        Scene scene=new Scene(root);

        Stage primaryStage= (Stage) this.rootLogin.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("DashBoard");
        primaryStage.centerOnScreen();

    }
    public void cler(){
        txtUserName.setText("");
        txtUserPassword.setText("");
    }
}
