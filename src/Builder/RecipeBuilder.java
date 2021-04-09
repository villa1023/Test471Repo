package Builder;

import java.util.List;

public class RecipeBuilder {
    private List<String> directionList;
    private List<String> ingredientList;
    private String title;
    private String author;

    public RecipeBuilder setDirectionList(List<String> directionList) {
        this.directionList = directionList;
        return this;
    }

    public RecipeBuilder setIngredientList(List<String> ingredientList) {
        this.ingredientList = ingredientList;
        return this;
    }

    public List<String> getDirectionList() {
        return directionList;
    }

    public List<String> getIngredientList() {
        return ingredientList;
    }

    public String getAuthor() {
        return author;
    }

    public RecipeBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }

    public RecipeBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Recipe build(){
        return new Recipe(this);
    }
}
