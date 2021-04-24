package Command_and_FactoryMethod;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public interface CommandIF {
    public void undo(MenuButton mb, ListView listView);
    public String toString();
    public void setMenuItem(MenuItem menuItem);
}
