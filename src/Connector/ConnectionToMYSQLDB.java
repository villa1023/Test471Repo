package Connector;

import java.sql.*;
import java.util.ArrayList;

public class ConnectionToMYSQLDB {
        public static void main(String[] args)throws Exception{
            getConnection();
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

        public static Connection getConnection() throws Exception{
            try{
                String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://localhost:3306/d_app"; //server can be seen as local host followed by the name of the schema
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
