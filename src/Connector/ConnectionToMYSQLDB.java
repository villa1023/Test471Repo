package Connector;

import java.sql.*;
import java.util.ArrayList;

public class ConnectionToMYSQLDB {
        public static void main(String[] args)throws Exception{
            getConnection();
            get();
        }
        public static String get() throws Exception{
            try{
                Connection con = getConnection();
                PreparedStatement statement = con.prepareStatement("SELECT name,company FROM data");
                ResultSet result = statement.executeQuery();
                String str = " ";
                ArrayList<String> array = new ArrayList<String>();
                while ((result.next())){
                    str += "Name: ";
                    str += result.getString("name");
                    str += "Company: ";
                    str += result.getString("company");
                    //array.add(result.getString("Name"));
                }
                return str;
            }catch(Exception e){
                System.out.println(e);
                return null;
            }
        }
    public static ArrayList<ArrayList<String>> getTitles() throws Exception{
        try{
            Connection con = getConnectionToRecipes();
            PreparedStatement statement = con.prepareStatement("SELECT recipe_id,ing_name FROM ingredients");
            ResultSet result = statement.executeQuery();
            String str = "";
            int id;
            ArrayList<ArrayList<String>> list1 = new ArrayList<>();
            ArrayList<String> list2 = new ArrayList<>();
            ArrayList<String> list3 = new ArrayList<>();
            ArrayList<String> list4 = new ArrayList<>();
            list1.add(list2);
            list1.add(list3);
            list1.add(list4);

            while(result.next()){
                list1.get(result.getInt("recipe_id")).add(result.getString("ing_name"));
            }
           // int currentIndex = 0;
//            while (result.next()){
//                //get corresponding id, this will be the index to which the ingredient will be added, this way each ingredient gets added to it's correct recipe
//                id = result.getInt("recipe_id");
//                if(id == (currentIndex)){
//                    list2.add(result.getString("ing_name"));
//                    if(!result.next()){
//                        System.out.println("Adding the final ingredient: " + list2.get(0));
//                        list1.add(currentIndex, list2);
//                    }
//                }else{
//                    list1.add(currentIndex, list2);
//                    currentIndex = id;
////                    for(int i = 0; i < list2.size();i++){
////                        System.out.println(list2.get(i));
////                    }
//                    list2.clear();
//                    list2.add(result.getString("ing_name"));
//                }
//            }
//            System.out.println("All records have been selected");
            return list1;
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
            String password = "Keepyoheadup760!"; //your password goes here
            Connection connect = DriverManager.getConnection(url, username, password);
            System.out.println("Connected!");
            return connect;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    }
