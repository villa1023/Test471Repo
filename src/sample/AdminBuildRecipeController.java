package sample;
import Builder.Recipe;
import Builder.RecipeBuilder;
import Command.AbstractCommand;
import Command.AddDirectionCommand;
import Command.CommandManager;
import Connector.ConnectionToMYSQLDB;
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
    //Need to use there instead of ingredient list
    private List<String> ingredientType = new ArrayList<>();
    private List<String> ingredientName = new ArrayList<>();
    private List<String> ingredientQuantity = new ArrayList<>();
    private String title;
    private String authorRec;
    private CommandManager commandManager = new CommandManager();
    @FXML
    TextField recipeTitle, author;
    @FXML
    TextArea authorArea, titleArea;
    @FXML
    TextField quant, ingName;
    @FXML
    ListView ingListV, quantListV, ingNameListV;
    @FXML
    CheckBox Protein, Fruits_veg, Carbs, Sauce_other;
    @FXML
    TextField directions;
    @FXML
    ListView directionView;
    @FXML
    MenuButton commandDirection;
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
                ingListV.getItems().add(typeIng);
                ingNameListV.getItems().add(ingName.getText());
                quantListV.getItems().add(quant.getText());
                //display.getItems().add("Ing. type: " + typeIng + "\t\tIng. name: " + ingName.getText() + "\t\tIng. quant: " + quant.getText());
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
            //Pass in the text for the direction and the current command manager
            AddDirectionCommand directionCommand = new AddDirectionCommand(directions.getText(), commandManager);
            //Create new menu item (The string for the add command we just created)
            MenuItem menuItem = new MenuItem();
            //Set the text of the menu item to the toString of the command
            menuItem.setText(directionCommand.toString());
            //Need to set the current menu item to access the index later on for deletion from list
            directionCommand.setMenuItem(menuItem);
            //Set an action listener for on click, call the undo command method
            menuItem.setOnAction(event -> {
                directionCommand.undo(commandDirection, directionView);
                //We need to make sure every time we add or delete anything or direction list corresponds with out changes
                //This way we can have a check that ensures the client does not add any empty data set to the DB
                directionList.clear();
                List<AbstractCommand> commandList = commandManager.getAddDirectionCommandList();
                for(int i = 0; i < commandList.size(); i++){
                    directionList.add(((AddDirectionCommand)commandList.get(i)).getDirection());
                }
            });
            //Add the current command we just added to the command manager
            commandManager.addDirectionCommand(directionCommand);
            //Add the command string to the list view
            directionView.getItems().add(directions.getText());
            //Finally add to the menu item the command we just created (since this is the most recent)
            commandDirection.getItems().add(0, menuItem);
            //We need to make sure every time we add or delete anything or direction list corresponds with out changes
            //This way we can have a check that ensures the client does not add any empty data set to the DB
            directionList.clear();
            List<AbstractCommand> commandList = commandManager.getAddDirectionCommandList();
            for(int i = 0; i < commandList.size(); i++){
                directionList.add(((AddDirectionCommand)commandList.get(i)).getDirection());
            }
        }
    }
    @FXML
    public void addToDB() throws Exception {
        //Check if all fields are not empty i.e. no empty insertions into DB
        if(authorRec != null || title != null || !directionList.isEmpty() || !ingredientList.isEmpty()){
            //Get the max primary keys, since they are auto incremented this works
            ArrayList<Integer> maxList = ConnectionToMYSQLDB.getMaxes();
            //Just add 1 to each to satisfy the new values
            int primaryRecipeDirections = maxList.get(0) + 1;
            int primaryRecipeID = maxList.get(1) + 1;
            int primaryIngredientID = maxList.get(2) + 1;
            //Build the recipeBuilder object
            Recipe recipeBuilder = new RecipeBuilder().setAuthor(authorRec).setTitle(title).
                    setDirectionList(directionList).setIngredientList(ingredientList).
                    setPrimaryRecipeDirections(primaryRecipeDirections).setPrimaryIngredientID(primaryIngredientID).setPrimaryRecipeID(primaryRecipeID).build();
            //Confirm with the user if they would like to continue
            //This is the last check before adding to DB
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Are you sure you would like to add this?" + "\n");
            alert.setContentText(recipeBuilder.toString());
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);
            //This is where we would add an extra method to add
            //Lay off for now
            if(button == ButtonType.OK){
                Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                errorAlert.setHeaderText("Successfully added to the database");
                errorAlert.showAndWait();
            }
        }else{
            //When this block executes it means one of the above fields were empty
            errorAlert.setHeaderText("Invalid option!");
            errorAlert.setContentText("Cannot add finalize results with empty fields");
            errorAlert.showAndWait();
        }
    }
}
