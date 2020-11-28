package sample;

import Connector.ConnectionToMYSQLDB;

import java.util.ArrayList;

public class ControllersDAO {
    protected ArrayList<String> getRecipeTitles(ArrayList<String> ingredientList) throws Exception {
        ArrayList<String> recipeString = ConnectionToMYSQLDB.getRecipeFromIngredients(ingredientList);
        return recipeString;
    }
    protected ArrayList<String> getRecipeInfo(String str) throws Exception {
        //call the database here and retrieve the ingredients, picture and directions
        ArrayList<String> finalList = ConnectionToMYSQLDB.getIngredientsFromRecipe(str);
        return finalList;
    }
    protected ArrayList<String> getRecipeFromUser(String user) throws Exception {
        return ConnectionToMYSQLDB.getAllRecipesForPantry(user);
    }
    protected boolean enterRecord(String user, String pass) throws Exception {
        if(!ConnectionToMYSQLDB.checkUser(user)){
            return false;
        }else{
            //incase wanting to use in the future, this will generate a random key for each new registered user, stored in the user table.
            int max = 999999;
            int min = 1;
            int random_int = (int)(Math.random() * (max - min + 1) + min);
            System.out.println(random_int);
            ConnectionToMYSQLDB.registerUser(user, pass, random_int);
            return true;
        }
    }
    protected ArrayList<String> getRecipeDirections(String recipeName) throws Exception {
        return ConnectionToMYSQLDB.getDirections(recipeName);
    }
    protected boolean addToUserPantry(String user, String recipe) throws Exception {
        boolean flag = ConnectionToMYSQLDB.addToPantryTable(user, recipe);
        if(!flag){
            return false;
        }else{
            return true;
        }
    }
    protected boolean retrieveRecord(String user, String pass) throws Exception {
        if(ConnectionToMYSQLDB.checkUserAndPass(user,pass)){
            return true;
        }
        return false;
    }
}
