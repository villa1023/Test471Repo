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

public class AboutUsGUIController implements Initializable {
    //Create user ingredients object which will be primarily used to take in the username
    UserIngredients uiObj = new UserIngredients();
    //File input stream objects to take in the images paths stored in the resource folder
    FileInputStream fstreamObj1 = new FileInputStream("C:\\Users\\Owner\\Desktop\\ICookFinal\\Resources\\groc.jpg");
    FileInputStream fstreamObj2 = new FileInputStream("C:\\Users\\Owner\\Desktop\\ICookFinal\\Resources\\secure.png");
    FileInputStream fstreamObj3 = new FileInputStream("C:\\Users\\Owner\\Desktop\\ICookFinal\\Resources\\pantry.jpg");
    FileInputStream fstreamObj4 = new FileInputStream("C:\\Users\\Owner\\Desktop\\ICookFinal\\Resources\\diversity3.jpg");
    //Pass in the fstream object to create an image
    Image display = new Image(fstreamObj1);
    Image secureImg = new Image(fstreamObj2);
    Image pantryImg = new Image(fstreamObj3);
    Image divImg = new Image(fstreamObj4);
    @FXML
    ImageView aboutICook;
    @FXML
    ImageView security;
    @FXML
    ImageView pantry;
    @FXML
    ImageView diversity;
    //set the username to be passed to the next class the user navigates to
    public void setUser(String user){
        uiObj.setUser(user);
    }
    //When user clicks the back button, set the scene and the corresponding controller
    //then call the set user method on the controller instance to pass the username
    public void goToHomePage(){
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfterLoginGUI.fxml"));
            Parent root = loader.load();
            //Get controller of scene2
            AfterLoginGUIController firstSceneController = loader.getController();
            firstSceneController.setUserName(uiObj.getUser());
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
    //Need to create a constructor for this class in order for the functionality to be achieved
    public AboutUsGUIController() throws FileNotFoundException {
    }
    //Initialize method will set the images after the scene has already been established.
    //Need to use this method to initialize image view in real time.
    //Allows the implementing class to perform any necessary post-processing on the content.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        aboutICook.setImage(display);
        security.setImage(secureImg);
        pantry.setImage(pantryImg);
        diversity.setImage(divImg);
    }
}
