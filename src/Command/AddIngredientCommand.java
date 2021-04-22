package Command;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
public class AddIngredientCommand extends AbstractCommand{
    private String type;
    private String name;
    private String quantity;
    public AddIngredientCommand(String type, String name, String quantity){
        this.type = type;
        this.name = name;
        this.quantity = quantity;
    }
    @Override
    public void undo(MenuButton mb, ListView listView){

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
}
