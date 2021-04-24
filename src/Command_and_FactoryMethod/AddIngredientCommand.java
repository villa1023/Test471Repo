package Command;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import java.util.List;
public class AddIngredientCommand extends AbstractCommand{
    private String type;
    private String name;
    private String quantity;
    private CommandManager commandManager;
    private MenuItem menuItem;
    public AddIngredientCommand(String type, String name, String quantity, CommandManager commandManager){
        this.type = type;
        this.name = name;
        this.quantity = quantity;
        this.commandManager = commandManager;
    }
    @Override
    public void undo(MenuButton mb, ListView ingredientListView){
        //Get the current index of the menu button
        int index = mb.getItems().indexOf(menuItem);
        //Remove all previous items (all action including the previous ones up to this point will be un-done)
        mb.getItems().remove(0, index + 1);
        //Clear all items in the list view
        ingredientListView.getItems().clear();
        //Clear all directions (inclusive so need to do + 1)
        commandManager.updateIngredientCommandList(index + 1);
        //Get the list after all directions prior were removed
        List<AbstractCommand> addIngredientList = commandManager.getAddIngredientCommandList();
        //Add the new direction string to the list view
        String addString = "";
        for(int i = 0; i < addIngredientList.size(); i++){
            addString += "Ing. type: ";
            addString += ((AddIngredientCommand)addIngredientList.get(i)).getType();
            addString += "\t\tIng. name: ";
            addString += ((AddIngredientCommand)addIngredientList.get(i)).getName();
            addString += "\t\tIng. quant: ";
            addString += ((AddIngredientCommand)addIngredientList.get(i)).getQuantity();
            ingredientListView.getItems().add(addString);
            addString = "";
        }
    }
    public String getType(){
        return type;
    }
    public String getName(){
        return name;
    }
    public String getQuantity(){
        return quantity;
    }
    public void setMenuItem(MenuItem menuItem){
        this.menuItem = menuItem;
    }
    @Override
    public String toString() {
        return quantity + name;
    }
}
