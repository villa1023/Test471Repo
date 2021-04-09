package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.*;

public class AdminBuildRecipeController {
    @FXML
    TextField recipeTitle;
    @FXML
    TextField author;
    @FXML
    TextArea authorArea;
    @FXML
    TextArea titleArea;

    @FXML
    public void addRAToFlow(){
        String raRecipeTitle = recipeTitle.getText();
        String raAuthor = author.getText();
        authorArea.setText(raRecipeTitle);
        titleArea.setText(raRecipeTitle);
        authorArea.setFont(Font.font("cambria", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        titleArea.setFont(Font.font("cambria", FontWeight.NORMAL, FontPosture.REGULAR, 20));
    }


}
