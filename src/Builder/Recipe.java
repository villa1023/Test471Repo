package Builder;

import java.util.List;

public class Recipe {
    private final List<String> directionList;
    private final List<String> ingredientList;
    private final String title;
    private final String author;
    private int primaryRecipeDirections;
    private int primaryRecipeID;
    private int primaryIngredientID;

    public Recipe(RecipeBuilder builder){
        this.directionList = builder.getDirectionList();
        this.ingredientList = builder.getIngredientList();
        this.title = builder.getTitle();
        this.author = builder.getAuthor();
        this.primaryRecipeDirections = builder.getPrimaryRecipeDirections();
        this.primaryRecipeID = builder.getPrimaryRecipeID();
        this.primaryIngredientID = builder.getPrimaryIngredientID();
    }
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Title: " + title + "\t\t");
        builder.append("Author: " + author + "\t\t");
        builder.append("Recipe ID: " + primaryRecipeID + "\n");
        //ConnectionToMYSQLDB.insertIntoRecipesAdmin(int recipeID, String desc, String author)
        builder.append("Ingredients: " + "\n");
        //ConnectionToMYSQLDB.insertIntoRecipesAdmin
        for(int i = 0; i < ingredientList.size(); i++){
            //ConnectionToMYSQLDB.insertIntoRecipeIngredientsAdmin(int ingID, String ingType, String quant, String ing_name, int recipeID)
            builder.append("Ingredient ID: " + (primaryIngredientID + i));
            builder.append("\t\t" + ingredientList.get(i));
            builder.append("\t\t" + "Recipe ID" + primaryRecipeID + "\n");
        }
        builder.append("Directions: " + "\n");
        for(int i = 0; i < directionList.size(); i++){
            //ConnectionToMYSQLDB.insertIntoRecipeDirectionsAdmin(int stepNum, String dir, int pri, String recipeName)
            builder.append("Step number: " + (i+1));
            builder.append("\t\t" + (directionList.get(i)));
            builder.append("\t\t" + "Primary direction ID: " + (primaryRecipeDirections + i) + "\n");
        }
        return builder.toString();
    }
}
