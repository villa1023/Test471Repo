package sample;

import javafx.application.Application;
//import Connector.ConnectionToMYSQLDB;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Con_1.getConnection();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainMenuGUI.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("iCook");
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}