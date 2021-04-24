package Command;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
public abstract class AbstractCommand {
    public void undo(MenuButton mb, ListView listView){
        //Do nothing here
    }
    public String toString(){
        return "";
    }
}

