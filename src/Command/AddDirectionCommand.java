package Command;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import java.util.List;

public class AddDirectionCommand extends AbstractCommand{
    private String direction;
    private MenuItem menuItem;
    private CommandManager commandManager;
    public AddDirectionCommand(String direction, CommandManager commandManager){
        this.direction = direction;
        this.commandManager = commandManager;
    }
    @Override
    public void undo(MenuButton mb, ListView directionListView){
        //Get the current index of the menu button
        int index = mb.getItems().indexOf(menuItem);
        //Remove all previous items (all action including the previous ones up to this point will be un-done)
        mb.getItems().remove(0, index + 1);
        //Clear all items in the list view
        directionListView.getItems().clear();
        //Clear all directions (inclusive so need to do + 1)
        commandManager.updateAddDirectionCommandList(index + 1);
        //Get the list after all directions prior were removed
        List<AbstractCommand> addDirectionList = commandManager.getAddDirectionCommandList();
        //Add the new direction string to the list view
        for(int i = 0; i < addDirectionList.size(); i++){
            directionListView.getItems().add(((AddDirectionCommand)addDirectionList.get(i)).getDirection());
        }
    }
    public String getDirection(){
        return direction;
    }
    public String toString(){
        if(direction.length() > 7) {
            return "Dir: " + direction.substring(0, direction.length() - 4);
        }
        return "Dir: " + direction;
    }
    public void setMenuItem(MenuItem menuItem){
        this.menuItem = menuItem;
    }
}
