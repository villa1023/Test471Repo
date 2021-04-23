package Command;
import java.util.ArrayList;
import java.util.List;
public class CommandManager {
    private List<AbstractCommand> addDirectionCommandList;
    private List<AbstractCommand> addIngredientCommandList;
    public CommandManager(){
        addDirectionCommandList = new ArrayList<AbstractCommand>();
        addIngredientCommandList = new ArrayList<AbstractCommand>();
    }
    public void addIngredientCommand(AbstractCommand addIngredientCommand){
        addIngredientCommandList.add(addIngredientCommand);
    }
    public void addDirectionCommand(AbstractCommand currentCommand){
        addDirectionCommandList.add(currentCommand);
    }
    public List<AbstractCommand> getAddDirectionCommandList(){
        return addDirectionCommandList;
    }
    public List<AbstractCommand> getAddIngredientCommandList(){
        return addIngredientCommandList;
    }
    public void updateAddDirectionCommandList(int index){
        //Need to remove up until the size minus the index
        //Since we want to keep whats at the beginning we must remove backwards
        //However, since the index is technically how many elements we want to remove going backwards
        //All we need to do is subtract the size minus the index and that will be our stopping point
        int newIndex = addDirectionCommandList.size() - index;
        for(int i = addDirectionCommandList.size() - 1; i >= newIndex; i--){
            addDirectionCommandList.remove(i);
        }
    }
    public void updateIngredientCommandList(int index){
        //Need to remove up until the size minus the index
        //Since we want to keep whats at the beginning we must remove backwards
        //However, since the index is technically how many elements we want to remove going backwards
        //All we need to do is subtract the size minus the index and that will be our stopping point
        int newIndex = addIngredientCommandList.size() - index;
        for(int i = addIngredientCommandList.size() - 1; i >= newIndex; i--){
            addIngredientCommandList.remove(i);
        }
    }

}
