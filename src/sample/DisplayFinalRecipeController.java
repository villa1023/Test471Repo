package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DisplayFinalRecipeController implements Initializable {
    @FXML
    ListView recipeIngredients;
    @FXML
    ImageView recipeImage;
    @FXML
    Spinner<Integer> spinner;
    final int initialVal = 1;
    SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,5,initialVal);
    UserIngredients obj3 = new UserIngredients();

    public void setRecipeObject(UserIngredients obj2){
        obj3.setChosenRecipes(obj2.getChosenRecipes());
    }
    public void fillngredientsListView(ArrayList<String> ingredientsList){
        for(String str: ingredientsList){
            recipeIngredients.getItems().add(str);
        }
    }
    public void getScore(){
        System.out.println(spinner.getValue());
    }
    public void initializeScene(String str) throws FileNotFoundException {
        Image img = RecipePhotos.passBackImage(str);
        recipeImage.setImage(img);
    }
    public void chooseIngredients(){
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenuGUI.fxml"));
            Parent root = loader.load();
            //Get controller of scene2
            MainMenuGUIController scene1Controller = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("iCook");
            stage.show();
            ((Stage)recipeIngredients.getScene().getWindow()).close();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void goToMainMenu(){
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfterLoginGUI.fxml"));
            Parent root = loader.load();
            //Get controller of scene2
            AfterLoginGUIController scene4Controller = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("iCook");
            stage.show();
            ((Stage)recipeIngredients.getScene().getWindow()).close();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void goToRecipesPage(){
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OverviewRecipesGUI.fxml"));
            Parent root = loader.load();
            //Get controller of scene2
            OverviewRecipesGUIController scene3Controller = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            scene3Controller.setRecipeObject(obj3);
            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("iCook");
            stage.show();
            scene3Controller.test();
            ((Stage)recipeIngredients.getScene().getWindow()).close();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        spinner.setValueFactory(svf);
    }
}
