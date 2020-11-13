package sample;

import Connector.ConnectionToMYSQLDB;

import java.util.ArrayList;

public class ORGUIContDAO {
    public static ArrayList<String> sortTitles(ArrayList<String> ingredientList) throws Exception {
        //Get all ingredients with their corresponding recipe_id
        ArrayList<ArrayList<String>> titles = ConnectionToMYSQLDB.getTitles();
        //This list will contain all the matching ingredients Id's
        ArrayList<Integer> returnList = new ArrayList<>();
        for(int i = 0; i < ingredientList.size();i++){
            for(int j = 0; j < titles.size(); j++){
                for(int x = 0; x < titles.get(j).size();x++){
                    //if the ingredient in the recipe matches the ingredient in your ingredients list, go onto the next recipe
                    //otherwise if no matches keep checking
                    if(titles.get(j).get(x).equalsIgnoreCase(ingredientList.get(i))){
                        //return the index position which is the recipe ID
                        //the first recipe will start at 0
                        if(!returnList.contains(j)){
                            returnList.add(j);
                        }
                    }
                }
            }
        }
        return getTitle(returnList);
    }
    public static ArrayList<String> getTitle(ArrayList<Integer> getList) throws Exception {
        ArrayList<String> desc = ConnectionToMYSQLDB.getTitleFromID(getList);
        return desc;
    }
}
