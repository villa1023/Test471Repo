package sample;
import java.util.ArrayList;

public class UserIngredients {
    public static ArrayList<String> ingredients = new ArrayList<>();
    public static ArrayList<String> chosenIngredients = new ArrayList<>();
    public UserIngredients(){
    }
    public void addIngredient(String str){
        ingredients.add(str);
    }
    public void deleteAll(){
        ingredients.clear();
    }
}
