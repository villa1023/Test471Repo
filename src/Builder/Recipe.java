package Builder;

import java.util.List;

public class Recipe {
    private final List<String> directionList;
    private final List<String> ingredientList;
    private final String title;
    private final String author;
    public Recipe(RecipeBuilder builder){
        this.directionList = builder.getDirectionList();
        this.ingredientList = builder.getIngredientList();
        this.title = builder.getTitle();
        this.author = builder.getAuthor();
    }
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Title: " + title + "\t");
        builder.append("Author: " + author + "\n");
        builder.append("Ingredients: ");
        for(String i: ingredientList){
            builder.append("\n" + i);
        }
        builder.append("Directions: ");
        for(String i: directionList){
            builder.append("\n" + i);
        }
        return builder.toString();
    }
}
