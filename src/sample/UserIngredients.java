package sample;
import java.util.ArrayList;

public class UserIngredients {
    //ingredients
    private ArrayList<String> ingredients;
    //chosen recipes
    private ArrayList<String> chosenRecipes;
    private ArrayList<String> pantryList;
    private String userName;
    private String tempRecipe;
    protected UserIngredients(){
        this.ingredients = new ArrayList<>();
        this.chosenRecipes = new ArrayList<>();
    }
    protected void setTempRecipe(String recipe){
        this.tempRecipe = recipe;
    }
    protected String getTempRecipe(){
        return tempRecipe;
    }
    protected void setUser(String userName){
        this.userName = userName;
    }
    protected String getUser(){
        return userName;
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
    protected void setChosenRecipesForPantry(ArrayList<String> chosenList){
        this.pantryList = chosenList;
    }
    protected String getARecipe(int index){
        return chosenRecipes.get(index);
    }
    protected String getARecipeFromPantry(int index){
        return pantryList.get(index);
    }
    protected ArrayList<String> getChosenRecipesFromPantry(){
        return pantryList;
    }
    protected ArrayList<String> getChosenRecipes(){
        return chosenRecipes;
    }
    protected void deleteAll(){
        ingredients.clear();
    }
}
