package Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
    Queries and inserts data to and from the recipe schema of the MySQL database
*/
public class ConnectionToMYSQLDB {
    //Adds the recipe to the pantry table and returns true, if the recipe doesn't already exist in the users pantry
    //otherwise return false
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
    //String representation of each direction according to recipe are stored and in array list
    //and passed back to the user
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
    //The user specified list of ingredients is searched, if a match is found the recipe id is then stored
    //in an array list and passed back to the caller
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
    //Registers the user, returns true if successful
    public static boolean registerUser(String user, String pass, int id) throws Exception {
        Connection connection = getConnectionToRecipes();
        String sql;
        PreparedStatement statement;
        sql = "INSERT INTO user_info(username, password, user_id, admin) VALUES(?, ?, ?, ?)";
        statement = connection.prepareStatement(sql);
        try{
        statement.setString(1, user);
        statement.setString(2, pass);
        statement.setInt(3, id);
        statement.setInt(4, 0);
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
    //Checks if the user exists in the table
    //If the user is already in the table this means they have already registered an account so return false
    //otherwise return true
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
    //Verifies if the username and encrypted password match the username and password in the table
    //If a match return true, otherwise return false
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
    //Get the recipe titles comparing against the recipe ID
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
    //Gets the recipe ID, ingredient + quantity, and author from the recipe table
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
    //The entry is already checked if it exists, so just delete the entry and return true
    public static boolean deleteFromPantry(String username, String recipe) throws Exception {
        Connection con = getConnectionToRecipes();
        PreparedStatement statement = con.prepareStatement("DELETE FROM user_recipes WHERE username = ? and recipe_name = ?");
        statement.setString(1, username);
        statement.setString(2, recipe);
        try{
            statement.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        con.close();
        return true;
    }
    public static boolean checkAdministrator(String user) throws Exception {
        Connection con = getConnectionToRecipes();
        PreparedStatement statement = con.prepareStatement("SELECT * FROM user_info WHERE username = ? and admin = ?" );
        statement.setString(1, user);
        statement.setInt(2, 1);
        ResultSet result = statement.executeQuery();
        while (result.next()){
            return true;
        }
        return false;
    }
    //get all recipes when compared against the username
    //return the list of recipes back to the caller
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
    //username and recipe name are to be checked if the entry exists, if both exist in the same row
    //this means the user has already stored the recipe in their pantry so true
    //otherwise it doesn't exist so return false
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
    public static ArrayList<Integer> getMaxes() throws Exception {
        int rDirections = 0;
        int maxRID = 0;
        int maxIngID = 0;
        ArrayList<Integer> listMaxes = new ArrayList<>();
        try{
            Connection con = getConnectionToRecipes();
            //Max PK of recipe directions
            PreparedStatement statement = con.prepareStatement("SELECT MAX(pmary) as 'Pmary_Max' FROM recipe_directions");
            //Max PK of recipe
            PreparedStatement statement2 = con.prepareStatement("SELECT MAX(recipe_id) as 'Recipe_ID' FROM recipe");
            //Max PK of ingredients
            PreparedStatement statement3 = con.prepareStatement("SELECT MAX(ingredient_id) as 'Ing_ID' FROM ingredients");
            ResultSet result = statement.executeQuery();
            ResultSet result2 = statement2.executeQuery();
            ResultSet result3 = statement3.executeQuery();
            result.next();
            rDirections = result.getInt("Pmary_Max");
            result2.next();
            maxRID = result2.getInt("Recipe_ID");
            result3.next();
            maxIngID = result3.getInt("Ing_ID");
            listMaxes.add(rDirections);
            listMaxes.add(maxRID);
            listMaxes.add(maxIngID);
            con.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return listMaxes;
    }
    public boolean insertIntoRecipesAdmin(int recipeID, String desc, String author) throws Exception {
//        for(String str: stringList){
//            String[] Res = str.split("\\s+");
//        }
        Connection connection = getConnectionToRecipes();
        String sql;
        PreparedStatement statement;
        sql = "INSERT INTO recipe(recipe_id, description, author) VALUES(?, ?, ?)";
        statement = connection.prepareStatement(sql);
        try{
            statement.setInt(1, recipeID);
            statement.setString(2, desc);
            statement.setString(3, author);
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
    public static boolean insertIntoRecipeDirectionsAdmin(int stepNum, String dir, int pri, String recipeName) throws Exception {
        Connection connection = getConnectionToRecipes();
        String sql;
        PreparedStatement statement;
        sql = "INSERT INTO recipe_directions(step_number, directions, pmary, recipe_name) VALUES(?, ?, ?, ?)";
        statement = connection.prepareStatement(sql);
        try{
            statement.setInt(1, stepNum);
            statement.setString(2, dir);
            statement.setInt(3, pri);
            statement.setString(4, recipeName);
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
    public static boolean insertIntoRecipeIngredientsAdmin(int ingID, String ingType, String quant, String ing_name, int recipeID) throws Exception {
        Connection connection = getConnectionToRecipes();
        String sql;
        PreparedStatement statement;
        sql = "INSERT INTO recipe_directions(ingredient_id, ing_type, quantity, ing_name, recipe_id) VALUES(?, ?, ?, ?, ?)";
        statement = connection.prepareStatement(sql);
        try{
            statement.setInt(1, ingID);
            statement.setString(2, ingType);
            statement.setString(3, quant);
            statement.setString(4, ing_name);
            statement.setInt(5, recipeID);
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
    //Most important method of the class
    //Establishes the connection to the recipe schema for the following methods to utilize
    //Inform the user if successful, otherwise the connection is unsuccessful
    public static Connection getConnectionToRecipes() throws Exception{
        try{
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://127.0.0.1:3306/recipes"; //server can be seen as local host followed by the name of the schema
            String username = "root"; //username goes here
            String password = "password"; //your password goes here
            Connection connect = DriverManager.getConnection(url, username, password);
            System.out.println("Connected!");
            return connect;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    }
