package Connector;

import java.sql.*;
import java.util.ArrayList;

public class ConnectionToMYSQLDB {
        public static void main(String[] args)throws Exception{
        }
        public static int getTotalNumberRecipes() throws Exception {
            Connection con = getConnectionToRecipes();
            PreparedStatement statement = con.prepareStatement("SELECT recipe_id FROM recipe");
            ResultSet result = statement.executeQuery();
            int cap = 0;
            while(result.next()){
                cap = result.getInt("recipe_id");
            }
            return cap + 1;
        }
    public static ArrayList<ArrayList<String>> getTitles(int count) throws Exception{
        try{
            //
            Connection con = getConnectionToRecipes();
            PreparedStatement statement = con.prepareStatement("SELECT recipe_id,ing_name FROM ingredients");
            ResultSet result = statement.executeQuery();
            ArrayList<ArrayList<String>> list = new ArrayList<>();
            for(int i = 0; i < count; i++){
                ArrayList<String> list2 = new ArrayList<>();
                list.add(list2);
            }
            while(result.next()){
                list.get(result.getInt("recipe_id")).add(result.getString("ing_name"));
            }
            return list;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    public static ArrayList<String> getTitleFromID(ArrayList<Integer> obj) throws Exception{
        try{
            Connection con = getConnectionToRecipes();
            PreparedStatement statement = con.prepareStatement("SELECT recipe_id,description FROM recipe");
            ResultSet result = statement.executeQuery();
            int id;
            ArrayList<String> list1 = new ArrayList<>();
            while ((result.next())){
                //get corresponding id, this will be the index to which the ingredient will be added, this way each ingredient gets added to it's correct recipe
                id = result.getInt("recipe_id");
                for(int i = 0; i < obj.size();i++){
                    if(id == obj.get(i)){
                        String str = result.getString("description");
                        list1.add(str);
                    }
                }
            }
            System.out.println("All records have been selected");
            return list1;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
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
    public static boolean registerUser(String user, String pass) throws Exception {
        Connection connection = getConnectionToRecipes();
        String sql;
        PreparedStatement statement;
        sql = "INSERT INTO user_info(username, password) VALUES(?, ?)";
        statement = connection.prepareStatement(sql);
        try{
        statement.setString(1, user);
        statement.setString(2, pass);
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
                    finalReturnList.add("Recipe: " + desc);
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
            desc += "\t";
            desc += result.getString("ing_name");
            finalReturnList.add(desc);
        }
        return finalReturnList;
    }
    public static Connection getConnectionToRecipes() throws Exception{
        try{
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
