package Command;
public class CommandFactory {
    public AbstractCommand createAddDirectionCommand(String direction, CommandManager commandManager){
        return new AddDirectionCommand(direction, commandManager);
    }
    public AbstractCommand createAddIngredientCommand(String type, String name, String quantity){
        return new AddIngredientCommand(type, name, quantity);
    }
}
