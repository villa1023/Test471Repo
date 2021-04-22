package sample;
import Connector.ConnectionToMYSQLDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Main extends Application {
    //Required to override, to effectively set the first scene
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginScreenGUI.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("iCook");
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        //System.out.println(ConnectionToMYSQLDB.getMaxes());
    }
    public static void main(String[] args) {
        launch(args);
    }
}