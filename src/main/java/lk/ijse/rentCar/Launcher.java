package lk.ijse.rentCar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.rentCar.util.HibernetUtill;
import org.hibernate.Session;

public class Launcher extends Application {
    public static void main(String[]args){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent root=FXMLLoader.load(this.getClass().getResource("/view/DashBoard_Form.fxml"));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.setTitle("DashBoard");
        stage.centerOnScreen();
        Session session = HibernetUtill.getSessionFactory().openSession();
        stage.show();

    }
}
