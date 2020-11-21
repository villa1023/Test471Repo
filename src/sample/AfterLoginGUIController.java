package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AfterLoginGUIController implements Initializable {
    FileInputStream fstreamObj1 = new FileInputStream("C:\\Users\\Owner\\Desktop\\ICookFinal\\Resources\\AboutICook.png");
    FileInputStream fstreamObj2 = new FileInputStream("C:\\Users\\Owner\\Desktop\\ICookFinal\\Resources\\EnterIngredients.png");
    FileInputStream fstreamObj3 = new FileInputStream("C:\\Users\\Owner\\Desktop\\ICookFinal\\Resources\\ViewPantry.png");
    Image topImage = new Image(fstreamObj1);
    Image middleImage = new Image(fstreamObj2);
    Image bottomImage = new Image(fstreamObj3);
    @FXML
    ImageView aboutICook;
    @FXML
    ImageView enterIngredients;
    @FXML
    ImageView viewPantry;

    public AfterLoginGUIController() throws FileNotFoundException {
    }
    public void goToViewPantry(){

    }
    public void goToEnterIngredients(){
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenuGUI.fxml"));
            Parent root = loader.load();
            //Get controller of scene2
            MainMenuGUIController firstSceneController = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("iCook");
            stage.show();
            ((Stage)aboutICook.getScene().getWindow()).close();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void goToAboutICook(){

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        aboutICook.setImage(topImage);
        enterIngredients.setImage(middleImage);
        viewPantry.setImage(bottomImage);
    }
}
