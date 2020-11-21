package sample;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserIngredients {
    //ingredients
    private ArrayList<String> ingredients;
    //chosen recipes
    private ArrayList<String> chosenRecipes;
    protected UserIngredients(){
        this.ingredients = new ArrayList<>();
        this.chosenRecipes = new ArrayList<>();
    }
    protected void addIngredient(String str){
        ingredients.add(str);
    }
    protected ArrayList<String> getIngredients(){
        return ingredients;
    }
    protected void setChosenRecipes(ArrayList<String> chosenList){
        this.chosenRecipes = chosenList;
    }
    protected String getARecipe(int index){
        return chosenRecipes.get(index);
    }
    protected ArrayList<String> getChosenRecipes(){
        return chosenRecipes;
    }
    protected void deleteAll(){
        ingredients.clear();
    }
}
