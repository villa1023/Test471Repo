package Command_and_FactoryMethod;
import java.util.ArrayList;
public interface CommandFactoryIF {
    public CommandIF createCommand(String request, ArrayList<String> typeList, CommandManager commandManager);
}
