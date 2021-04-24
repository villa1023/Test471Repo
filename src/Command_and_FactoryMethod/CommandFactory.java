package Command_and_FactoryMethod;
import java.util.ArrayList;
public class CommandFactory implements CommandFactoryIF{
//    public AbstractCommand createAddDirectionCommand(String direction, CommandManager commandManager){
//        return new AddDirectionCommand(direction, commandManager);
//    }
//    public AbstractCommand createAddIngredientCommand(String type, String name, String quantity, CommandManager commandManager){
//        return new AddIngredientCommand(type, name, quantity, commandManager);
//    }
    @Override
    public CommandIF createCommand(String request, ArrayList<String> type, CommandManager commandManager) {
        //Make sure the type is just a common class here
        //Then use a cast and get fields to get the correct info
        if(request.equalsIgnoreCase("Direction")){
            return new AddDirectionCommand(type.get(0), commandManager);
        }else if(request.equalsIgnoreCase("Ingredient")){
            return new AddIngredientCommand(type.get(0), type.get(1), type.get(2), commandManager);
        }
        throw new IllegalArgumentException();
    }
}
