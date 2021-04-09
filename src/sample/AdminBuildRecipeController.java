package sample;
import Builder.Recipe;
import Builder.RecipeBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdminBuildRecipeController {
    private Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    private List<String> directionList = new ArrayList<>();
    private List<String> ingredientList = new ArrayList<>();
    private String title;
    private String authorRec;
    @FXML
    TextField recipeTitle;
    @FXML
    TextField author;
    @FXML
    TextArea authorArea;
    @FXML
    TextArea titleArea;
    @FXML
    TextField quant;
    @FXML
    TextField ingName;
    @FXML
    ListView display;
    @FXML
    CheckBox Protein;
    @FXML
    CheckBox Fruits_veg;
    @FXML
    CheckBox Carbs;
    @FXML
    CheckBox Sauce_other;
    @FXML
    TextField directions;
    @FXML
    ListView directionView;
    @FXML
    public void addRAToFlow(){
        String raRecipeTitle = recipeTitle.getText();
        String raAuthor = author.getText();
        if(raAuthor.equals("") || raRecipeTitle.equals("")){
            errorAlert.setHeaderText("Invalid option!");
            errorAlert.setContentText("Both fields must be full.");
            errorAlert.showAndWait();
        }
        authorArea.setText(raAuthor);
        titleArea.setText(raRecipeTitle);
        authorArea.setFont(Font.font("cambria", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        titleArea.setFont(Font.font("cambria", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        title = raRecipeTitle;
        authorRec = raAuthor;

    }
    @FXML
    public void addToIngFlow(){
        int check = 0;
        String typeIng = "";
        if(Protein.isSelected()){
            check+=1;
            typeIng = Protein.getId();
        }
        if(Fruits_veg.isSelected()){
            check+=1;
            typeIng = Fruits_veg.getId();
        }
        if(Carbs.isSelected()){
            check+=1;
            typeIng = Carbs.getId();
        }
        if(Sauce_other.isSelected()){
            check+=1;
            typeIng = Sauce_other.getId();
        }
        if(check > 1 || check == 0){
            errorAlert.setHeaderText("Invalid option!");
            errorAlert.setContentText("Must only have one checkbox displayed.");
            errorAlert.showAndWait();
        }else{
            if(ingName.getText().equals("") || quant.getText().equals("")){
                errorAlert.setHeaderText("Invalid option!");
                errorAlert.setContentText("Cannot add empty name or quantity.");
                errorAlert.showAndWait();
            }else{
                display.getItems().add("Ing. type: " + typeIng + "\t\tIng. name: " + ingName.getText() + "\t\tIng. quant: " + quant.getText());
                ingredientList.add(typeIng + " " + ingName.getText() + " " + quant.getText());
            }
        }
    }
    @FXML
    public void addToDirectionView(){
        if(directions.getText().equals("")){
            errorAlert.setHeaderText("Invalid option!");
            errorAlert.setContentText("Cannot add empty direction");
            errorAlert.showAndWait();
        }else{
            directionView.getItems().add(directions.getText());
            directionList.add(directions.getText());
        }
    }
    @FXML
    public void addToDB(){
        if(authorRec != null || title != null || !directionList.isEmpty() || !ingredientList.isEmpty()){
            Recipe recipeBuilder = new RecipeBuilder().setAuthor(authorRec).setTitle(title).
                    setDirectionList(directionList).setIngredientList(ingredientList).build();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Are you sure you would like to add this?" + "\n");
            alert.setContentText(recipeBuilder.toString());
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);
            if(button == ButtonType.OK){
                Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                errorAlert.setHeaderText("Successfully added to the database");
                errorAlert.showAndWait();
            }
        }else{
            errorAlert.setHeaderText("Invalid option!");
            errorAlert.setContentText("Cannot add finalize results with empty fields");
            errorAlert.showAndWait();
        }
    }
}
