package Command_and_FactoryMethod;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
public abstract class AbstractCommand implements CommandIF{
    @Override
    public void undo(MenuButton mb, ListView listView){
        //Do nothing here
    }
    @Override
    public String toString(){
        return "";
    }
}

