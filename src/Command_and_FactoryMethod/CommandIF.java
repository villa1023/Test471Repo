package Command_and_FactoryMethod;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public interface CommandIF {
    void undo(MenuButton mb, ListView listView);
    String toString();
    void setMenuItem(MenuItem menuItem);
}
