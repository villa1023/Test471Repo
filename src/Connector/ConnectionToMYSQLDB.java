package Connector;

import java.sql.*;
import java.util.ArrayList;

public class ConnectionToMYSQLDB {
    public static boolean addToPantryTable(String username, String recipeName) throws Exception {
        Connection con = getConnectionToRecipes();
        PreparedStatement statement = con.prepareStatement("SELECT * FROM user_recipes WHERE username = ?");
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();
        try {
            while (result.next()){
                String desc = result.getString("recipe_name");
                if(desc.equals(recipeName)){
                    con.close();
                    return false;
                }
            }
        }catch (Exception e ){
            System.out.println(e.getMessage());
        }
        con.close();
        Connection connection = getConnectionToRecipes();
        String sql;
        sql = "INSERT INTO user_recipes(username, recipe_name) VALUES(?, ?)";
        statement = connection.prepareStatement(sql);
        try{
            statement.setString(1, username);
            statement.setString(2, recipeName);
            statement.executeUpdate();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            System.out.println(e.getMessage());
        }
        connection.close();
        return true;
    }
    public static ArrayList<String> getDirections(String recipeName) throws Exception {
        ArrayList<String> chosenRecipes = new ArrayList<>();
        Connection con = getConnectionToRecipes();
        PreparedStatement statement = con.prepareStatement("SELECT * FROM recipe_directions WHERE recipe_name = ?");
        statement.setString(1, recipeName);
        ResultSet result = statement.executeQuery();
        String str = "";
        try {
            while (result.next()){
                //do the hyper link here
                str = "Step ";
                str += result.getInt("step_number");
                str += ": ";
                str += result.getString("directions");
                str += "\n";
                String str2 = result.getString("hyperlink");
                chosenRecipes.add(str);
                if(str2 != null){
                    chosenRecipes.add(str2);
                }
            }
        }catch (Exception e ){
            System.out.println(e.getMessage());
        }
        con.close();
        return chosenRecipes;
    }
     public static ArrayList<String> getRecipeFromIngredients(ArrayList<String> obj) throws Exception {
        ArrayList<Integer> chosenRecipes = new ArrayList<>();
        Connection con = getConnectionToRecipes();
            for(String name: obj) {
                PreparedStatement statement = con.prepareStatement("SELECT * FROM ingredients WHERE ing_name = ?");
                statement.setString(1, name);
                ResultSet result = statement.executeQuery();
                try{
                    while (result.next()){
                        int rec = result.getInt("recipe_id");
                        chosenRecipes.add(rec);
                    }
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            con.close();
            return getRecipeFromId(chosenRecipes);
      }
    public static boolean registerUser(String user, String pass, int id) throws Exception {
        Connection connection = getConnectionToRecipes();
        String sql;
        PreparedStatement statement;
        sql = "INSERT INTO user_info(username, password, user_id) VALUES(?, ?, ?)";
        statement = connection.prepareStatement(sql);
        try{
        statement.setString(1, user);
        statement.setString(2, pass);
        statement.setInt(3, id);
        statement.executeUpdate();

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            System.out.println(e.getMessage());
        }
        connection.close();
        return true;
    }
    public static boolean checkUser(String user) throws Exception {
        Connection con = getConnectionToRecipes();
        PreparedStatement statement = con.prepareStatement("SELECT * FROM user_info WHERE username = ?");
        statement.setString(1, user);
        ResultSet result = statement.executeQuery();
            try {
                while (result.next()){
                    String desc = result.getString("username");
                    if(desc.equals(user)){
                        con.close();
                        return false;
                    }
                }
            }catch (Exception e ){
                System.out.println(e.getMessage());
            }
        con.close();
        return true;
    }
    public static boolean checkUserAndPass(String username, String password) throws Exception {
        Connection con = getConnectionToRecipes();
        PreparedStatement statement = con.prepareStatement("SELECT * FROM user_info WHERE username = ?");
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();
        try {
            while (result.next()){
                String user = result.getString("username");
                String pass = result.getString("password");
                if(user.equals(username) && pass.equals(password)){
                    con.close();
                    return true;
                }
            }
        }catch (Exception e ){
            System.out.println(e.getMessage());
        }
        con.close();
        return false;
    }
      public static ArrayList<String> getRecipeFromId(ArrayList<Integer> obj) throws Exception {
          ArrayList<String> chosenRecipes = new ArrayList<>();
          Connection con = getConnectionToRecipes();
          for(int ID: obj) {
              PreparedStatement statement = con.prepareStatement("SELECT * FROM recipe WHERE recipe_id = ?");
              statement.setInt(1, ID);
              ResultSet result = statement.executeQuery();
              try {
                  while (result.next()){
                      String desc = result.getString("description");
                      if(!chosenRecipes.contains(desc)){
                          chosenRecipes.add(desc);
                      }
                  }
              }catch (Exception e ){
                  System.out.println(e.getMessage());
              }
          }
          con.close();
          return chosenRecipes;
      }
    public static ArrayList<String> getIngredientsFromRecipe(String str) throws Exception {
        Connection con = getConnectionToRecipes();
        ArrayList<String> finalReturnList = new ArrayList<>();
            PreparedStatement statement = con.prepareStatement("SELECT * FROM recipe WHERE description = ?");
            statement.setString(1, str);
            ResultSet result = statement.executeQuery();
            int iD = 0;
            String desc;
            String author;
            try {
                while (result.next()){
                    iD = result.getInt("recipe_id");
                    desc = result.getString("description");
                    author = result.getString("author");
                    finalReturnList.add(desc);
                    finalReturnList.add("Author: " + author);
                    finalReturnList.add(" ");
                    finalReturnList.add("Ingredients: ");
                }
            }catch (Exception e ){
                System.out.println(e.getMessage());
            }
        Connection con2 = getConnectionToRecipes();
        PreparedStatement statement2 = con2.prepareStatement("SELECT * FROM ingredients WHERE recipe_id = ?");
        statement2.setString(1, String.valueOf(iD));
        result = statement2.executeQuery();
        while(result.next()){
            desc = result.getString("quantity");
            desc += " ";
            desc += result.getString("ing_name");
            finalReturnList.add(desc);
        }
        return finalReturnList;
    }
    public static boolean deleteFromPantry(String username, String recipe) throws Exception {
        Connection con = getConnectionToRecipes();
        PreparedStatement statement = con.prepareStatement("DELETE FROM user_recipes WHERE username = ? and recipe_name = ?");
        statement.setString(1, username);
        statement.setString(2, recipe);
        try{
            statement.executeUpdate();
        }catch (Exception e ){
            System.out.println(e.getMessage());
        }
        con.close();
        return true;
    }
    public static ArrayList<String> getAllRecipesForPantry(String user) throws Exception {
        Connection con2 = getConnectionToRecipes();
        PreparedStatement statement2 = con2.prepareStatement("SELECT * FROM user_recipes WHERE username = ?");
        statement2.setString(1, user);
        ResultSet result = statement2.executeQuery();
        ArrayList<String> recipeList = new ArrayList<>();
        while(result.next()){
            recipeList.add(result.getString("recipe_name"));
        }
        return recipeList;
    }
    public static boolean checkIfEntryExists(String username, String recipeName) throws Exception {
        Connection con = getConnectionToRecipes();
        PreparedStatement statement = con.prepareStatement("SELECT * FROM user_recipes WHERE username = ?");
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();
        try {
            while (result.next()){
                String user = result.getString("username");
                String recipe = result.getString("recipe_name");
                if(user.equals(username) && recipe.equals(recipeName)){
                    con.close();
                    return true;
                }
            }
        }catch (Exception e ){
            System.out.println(e.getMessage());
        }
        con.close();
        return false;
    }
    public static Connection getConnectionToRecipes() throws Exception{
        try{
            //
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/recipes"; //server can be seen as local host followed by the name of the schema
            String username = "root"; //username goes here
            String password = ""; //your password goes here
            Connection connect = DriverManager.getConnection(url, username, password);
            System.out.println("Connected!");
            return connect;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    }
