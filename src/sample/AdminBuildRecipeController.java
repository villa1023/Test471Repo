package sample;
import Builder.Recipe;
import Builder.RecipeBuilder;
import Command_and_FactoryMethod.*;
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
    TextField quant, ingName, directions;
    @FXML
    ListView ingredientListView, directionView;
    @FXML
    CheckBox Protein, Fruits_veg, Carbs, Sauce_other;
    @FXML
    MenuButton commandDirection, commandIngredient;
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
                //Pass in the text for the direction and the current command manager
                //AddDirectionCommand directionCommand = new AddDirectionCommand(directions.getText(), commandManager);
                Utility_Factory_Method utility_factory_method = new Utility_Factory_Method();
                CommandFactoryIF utility = utility_factory_method.createCommandFactoryObject();
                ArrayList<String> ingL = new ArrayList<>();
                ingL.add(typeIng);
                ingL.add(ingName.getText());
                ingL.add(quant.getText());
                CommandIF commandIF = (CommandIF) utility.createCommand("Ingredient", ingL, commandManager);
                //AddIngredientCommand ingredientCommand = new AddIngredientCommand(typeIng, ingName.getText(), quant.getText(), commandManager);
                //Create new menu item (The string for the add command we just created)
                MenuItem menuItem = new MenuItem();
                //Set the text of the menu item to the toString of the command
                menuItem.setText(commandIF.toString());
                //Need to set the current menu item to access the index later on for deletion from list
                commandIF.setMenuItem(menuItem);
                //Set an action listener for on click, call the undo command method
                menuItem.setOnAction(event -> {
                    commandIF.undo(commandIngredient, ingredientListView);
                    //We need to make sure every time we add or delete anything or direction list corresponds with out changes
                    //This way we can have a check that ensures the client does not add any empty data set to the DB
                    ingredientType.clear();
                    ingredientName.clear();
                    ingredientQuantity.clear();
                    List<AbstractCommand> commandTypeList = commandManager.getAddIngredientCommandList();
                    for(int i = 0; i < commandTypeList.size(); i++){
                        ingredientName.add(((AddIngredientCommand)commandTypeList.get(i)).getName());
                        ingredientType.add(((AddIngredientCommand)commandTypeList.get(i)).getType());
                        ingredientQuantity.add(((AddIngredientCommand)commandTypeList.get(i)).getQuantity());
                    }
                });
                //Add the current command we just added to the command manager
                commandManager.addIngredientCommand(commandIF);
                //Add the command string to the list view
                //directionView.getItems().add(directions.getText());
                ingredientListView.getItems().add("Ing. type: " + typeIng + "\t\tIng. name: " + ingName.getText() + "\t\tIng. quant: " + quant.getText());
                //Finally add to the menu item the command we just created (since this is the most recent)
                commandIngredient.getItems().add(0, menuItem);
                //We need to make sure every time we add or delete anything or direction list corresponds with out changes
                //This way we can have a check that ensures the client does not add any empty data set to the DB
                ingredientType.clear();
                ingredientName.clear();
                ingredientQuantity.clear();
                List<AbstractCommand> commandTypeList = commandManager.getAddIngredientCommandList();
                for(int i = 0; i < commandTypeList.size(); i++){
                    ingredientName.add(((AddIngredientCommand)commandTypeList.get(i)).getName());
                    ingredientType.add(((AddIngredientCommand)commandTypeList.get(i)).getType());
                    ingredientQuantity.add(((AddIngredientCommand)commandTypeList.get(i)).getQuantity());
                }
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
            //AddDirectionCommand directionCommand = new AddDirectionCommand(directions.getText(), commandManager);
            Utility_Factory_Method utility_factory_method = new Utility_Factory_Method();
            CommandFactoryIF utility = utility_factory_method.createCommandFactoryObject();
            ArrayList<String> dList = new ArrayList<>();
            dList.add(directions.getText());
            CommandIF commandIF = (CommandIF) utility.createCommand("Direction", dList, commandManager);
            //Create new menu item (The string for the add command we just created)
            MenuItem menuItem = new MenuItem();
            //Set the text of the menu item to the toString of the command
            menuItem.setText(commandIF.toString());
            //Need to set the current menu item to access the index later on for deletion from list
            commandIF.setMenuItem(menuItem);
            //Set an action listener for on click, call the undo command method
            menuItem.setOnAction(event -> {
                commandIF.undo(commandDirection, directionView);
                //We need to make sure every time we add or delete anything or direction list corresponds with out changes
                //This way we can have a check that ensures the client does not add any empty data set to the DB
                directionList.clear();
                List<AbstractCommand> commandList = commandManager.getAddDirectionCommandList();
                for(int i = 0; i < commandList.size(); i++){
                    directionList.add(((AddDirectionCommand)commandList.get(i)).getDirection());
                }
            });
            //Add the current command we just added to the command manager
            commandManager.addDirectionCommand(commandIF);
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
        if(authorRec != null || title != null || !directionList.isEmpty() || !ingredientType.isEmpty()){
            //Get the max primary keys, since they are auto incremented this works
            ArrayList<Integer> maxList = ConnectionToMYSQLDB.getMaxes();
            //Just add 1 to each to satisfy the new values
            int primaryRecipeDirections = maxList.get(0) + 1;
            int primaryRecipeID = maxList.get(1) + 1;
            int primaryIngredientID = maxList.get(2) + 1;
            //Build the recipeBuilder object
            Recipe recipeBuilder = new RecipeBuilder().setAuthor(authorRec).setTitle(title).
                    setDirectionList(directionList).setIngredientTypeList(ingredientType).setIngredientNameList(ingredientName).setIngredientQuantityList(ingredientQuantity).
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
