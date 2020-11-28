package sample;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OverviewRecipesGUIController implements Initializable {
    @FXML
    public ListView listView;
    ControllersDAO dataObject = new ControllersDAO();
    UserIngredients obj2 = new UserIngredients();
    public void setUserName(String userName){
        obj2.setUser(userName);
    }
    public void setRecipeObject(UserIngredients obj1){
        obj2.setChosenRecipes(obj1.getChosenRecipes());
    }
    public void setRecipeObjectForPantry(UserIngredients obj1){
        obj2.setChosenRecipesForPantry(obj1.getChosenRecipesFromPantry());
    }

    @FXML
    public void selectRecipe() throws Exception {
        ObservableList<Object> list1 = listView.getSelectionModel().getSelectedItems();
        //get the list from the list view, if no recipes were generated don't go into this block condition, which will cause an error.
        if(list1.get(0) != "No Recipes were found."){
            //get the selected recipe
            ObservableList<ImageView> images = listView.getSelectionModel().getSelectedItems();
            //call the get recipe info on the image that was selected ID which is the recipe name.
            ArrayList<String> ingredientsForRecipe = dataObject.getRecipeInfo(images.get(0).getId());
            ArrayList<String> directionsForRecipe = dataObject.getRecipeDirections(images.get(0).getId());
            goToFinal(images.get(0).getId(), ingredientsForRecipe, directionsForRecipe);
        }
    }
    @FXML
    public void test() {
        try {
            if(obj2.getChosenRecipes().size() == 0) {
                listView.getItems().add("No Recipes were found.");
            }else{
                for(int i = 0; i < obj2.getChosenRecipes().size(); i++) {
                    String str = obj2.getARecipe(i);
                    ImageView img = RecipePhotos.passBackImageView(str);
                    //set the images ID equal to the string name of the recipe
                    img.setId(str);
                    listView.getItems().add(img);
                }
//                listView.setOnMouseClicked(MouseEvent -> {
//                    System.out.println("HERE");
//                });
            }
            } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    public void fillListWithPantryItems() {
        try {
            if(obj2.getChosenRecipesFromPantry().size() == 0) {
                listView.getItems().add("No Recipes were found.");
            }else{
                for(int i = 0; i < obj2.getChosenRecipesFromPantry().size(); i++) {
                    System.out.println(obj2.getARecipeFromPantry(i));
                    String str = obj2.getARecipeFromPantry(i);
                    ImageView img = RecipePhotos.passBackImageView(str);
                    img.setId(str);
                    listView.getItems().add(img);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    public void goBack(){
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenuGUI.fxml"));
            Parent root = loader.load();
            //Get controller of scene2
            MainMenuGUIController scene1Controller = loader.getController();
            scene1Controller.setUserName(obj2.getUser());
            //Pass whatever data you want. You can have multiple method calls here
            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("iCook");
            stage.show();
            ((Stage)listView.getScene().getWindow()).close();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void goToFinal(String str, ArrayList<String> strList, ArrayList<String> directions){
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayFinalRecipeGUI.fxml"));
            Parent root = loader.load();
            //Get controller of scene2
            DisplayFinalRecipeController scene3Controller = loader.getController();
            scene3Controller.setUserName(obj2.getUser());
            //Pass whatever data you want. You can have multiple method calls here
            scene3Controller.initializeScene(str);
            scene3Controller.fillIngredientsAndDirections(strList, directions);
            //scene3Controller.setRecipeDirections(directions);
            scene3Controller.setRecipeObject(obj2);
            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("iCook");
            stage.show();
            ((Stage)listView.getScene().getWindow()).close();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
