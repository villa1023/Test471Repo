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
    protected boolean enterRecord(String user, String pass) throws Exception {
        if(!ConnectionToMYSQLDB.checkUser(user)){
            return false;
        }else{
            ConnectionToMYSQLDB.registerUser(user, pass);
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
