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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AfterLoginGUIController implements Initializable {
    FileInputStream fstreamObj1 = new FileInputStream("C:\\Users\\Owner\\Desktop\\ICookFinal\\Resources\\AboutICook.png");
    FileInputStream fstreamObj2 = new FileInputStream("C:\\Users\\Owner\\Desktop\\ICookFinal\\Resources\\EnterIngredients.png");
    FileInputStream fstreamObj3 = new FileInputStream("C:\\Users\\Owner\\Desktop\\ICookFinal\\Resources\\ViewPantry.png");
    Image topImage = new Image(fstreamObj1);
    Image middleImage = new Image(fstreamObj2);
    Image bottomImage = new Image(fstreamObj3);
    ControllersDAO dataAO = new ControllersDAO();
    UserIngredients uiObj = new UserIngredients();
    @FXML
    ImageView aboutICook;
    @FXML
    ImageView enterIngredients;
    @FXML
    ImageView viewPantry;

    public AfterLoginGUIController() throws FileNotFoundException {
    }
    public void setUserName(String userName){
        uiObj.setUser(userName);
    }
    public void goToViewPantry() throws Exception {
        String userName = uiObj.getUser();
        ArrayList<String> recipeList = dataAO.getRecipeFromUser(userName);
        uiObj.setChosenRecipesForPantry(recipeList);
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OverviewRecipesGUI.fxml"));
            Parent root = loader.load();
            //Get controller of scene2
            OverviewRecipesGUIController overviewRecipesGUIControllerObj = loader.getController();
            overviewRecipesGUIControllerObj.setRecipeObjectForPantry(uiObj);
            overviewRecipesGUIControllerObj.setUserName(uiObj.getUser());
            //Pass whatever data you want. You can have multiple method calls here
            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("iCook");
            stage.show();
            overviewRecipesGUIControllerObj.fillListWithPantryItems();
            ((Stage)aboutICook.getScene().getWindow()).close();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void goToEnterIngredients(){
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenuGUI.fxml"));
            Parent root = loader.load();
            //Get controller of scene2
            MainMenuGUIController firstSceneController = loader.getController();
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
    public void goToAboutICook(){
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AboutUsGUI.fxml"));
            Parent root = loader.load();
            //Get controller of scene2
            AboutUsGUIController aboutICookController = loader.getController();
            aboutICookController.setUser(uiObj.getUser());
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
    //Initialize method will set the images after the scene has already been established.
    //Need to use this method to initialize image view in real time.
    //Allows the implementing class to perform any necessary post-processing on the content.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        aboutICook.setImage(topImage);
        enterIngredients.setImage(middleImage);
        viewPantry.setImage(bottomImage);
    }
}
