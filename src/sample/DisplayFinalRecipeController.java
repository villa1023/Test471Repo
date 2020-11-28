package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DisplayFinalRecipeController implements Initializable {
    ControllersDAO daoObj = new ControllersDAO();
    @FXML
    ListView recipeIngredients;
    @FXML
    ImageView recipeImage;
//    @FXML
//    Spinner<Integer> spinner;
    @FXML
    TextArea directions;
    @FXML
    Hyperlink hyperlink;
    @FXML
    Text message;
//    final int initialVal = 1;
//    SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,5,initialVal);
    UserIngredients obj3 = new UserIngredients();
    public void setUserName(String userName){
        obj3.setUser(userName);
    }
    public void addToPantry() throws Exception {
        boolean flag = daoObj.addToUserPantry(obj3.getUser(), obj3.getTempRecipe());
        if(!flag){
            message.setText("This recipe already exists in your pantry.");
        }else{
            message.setText("Successfully added to pantry!");
        }
    }
    public void setRecipeObject(UserIngredients obj2){
        obj3.setChosenRecipes(obj2.getChosenRecipes());
    }
    public void fillIngredientsAndDirections(ArrayList<String> ingredientsList, ArrayList<String> directionSteps){
        //the hyper link will be stored in the last index of the arraylist
        hyperlink.setText(directionSteps.get(directionSteps.size() - 1));
        try{
            for(int i = 0; i < directionSteps.size() - 1; i++){
                directions.setText(directions.getText().concat(directionSteps.get(i)));
                directions.setText(directions.getText().concat("\n"));
                System.out.println(directionSteps.get(i));
            }
            obj3.setTempRecipe(ingredientsList.get(0));
            for(int i = 1; i < ingredientsList.size(); i++){
                recipeIngredients.getItems().add(ingredientsList.get(i));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
    @FXML
    public void goToWebPage(){
        //need to make an instance of application in order to have the hyperlink pop up in a web browser
        Application a = new Application() {
            @Override
            public void start(Stage stage) throws Exception {

            }
        };
        a.getHostServices().showDocument(hyperlink.getText());
    }
//    public void getScore(){
//        System.out.println(spinner.getValue());
//    }
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
            scene1Controller.setUserName(obj3.getUser());
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
            scene4Controller.setUserName(obj3.getUser());
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
            scene3Controller.setUserName(obj3.getUser());
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
//        spinner.setValueFactory(svf);
    }
}
