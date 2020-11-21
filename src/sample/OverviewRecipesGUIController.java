package sample;
import javafx.beans.Observable;
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

    public void setRecipeObject(UserIngredients obj1){
        obj2.setChosenRecipes(obj1.getChosenRecipes());
    }

    @FXML
    public void selectRecipe() throws Exception {
        ObservableList<Object> list1 = listView.getSelectionModel().getSelectedItems();
        if(list1.get(0) != "No Recipes were found. Try adding more ingredients!"){
            ObservableList<ImageView> images = listView.getSelectionModel().getSelectedItems();
            ArrayList<String> ingredientsForRecipe = dataObject.getRecipeInfo(images.get(0).getId());
            goToFinal(images.get(0).getId(), ingredientsForRecipe);
        }
    }
    @FXML
    public void test() {
        try {
            if(obj2.getChosenRecipes().size() == 0) {
                listView.getItems().add("No Recipes were found. Try adding more ingredients!");
            }else{
                for(int i = 0; i < obj2.getChosenRecipes().size(); i++) {
                    String str = obj2.getARecipe(i);
                    ImageView img = RecipePhotos.passBackImageView(str);
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
    public void goBack(){
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
            ((Stage)listView.getScene().getWindow()).close();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void goToFinal(String str, ArrayList<String> strList){
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayFinalRecipeGUI.fxml"));
            Parent root = loader.load();
            //Get controller of scene2
            DisplayFinalRecipeController scene3Controller = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            scene3Controller.initializeScene(str);
            scene3Controller.fillngredientsListView(strList);
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
