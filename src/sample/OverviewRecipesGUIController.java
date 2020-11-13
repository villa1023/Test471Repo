package sample;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.EventListener;
import java.util.ResourceBundle;

public class OverviewRecipesGUIController implements Initializable {
    @FXML
    public ListView listView;
    @FXML
    Button generate;

    public void setStage() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("iCook");
        stage.setWidth(533);
        stage.setHeight(638);
        Pane myPane;
        myPane = FXMLLoader.load(getClass().getResource("OverviewRecipesGUI.fxml"));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void test() {
        try{
            for(int i = 0; i < UserIngredients.chosenIngredients.size();i++) {
                System.out.println(UserIngredients.chosenIngredients.get(i));
                //list2.getItems().add(UserIngredients.chosenIngredients.get(i));
//                System.out.println(UserIngredients.chosenIngredients.get(i));
                ImageView img = RecipePhotos.passBackImageView(UserIngredients.chosenIngredients.get(i));
//                ImageView img2 = RecipePhotos.passBackImageView(UserIngredients.chosenIngredients.get(i));
//                ImageView img3 = RecipePhotos.passBackImageView(UserIngredients.chosenIngredients.get(i));
//                ImageView img4 = RecipePhotos.passBackImageView(UserIngredients.chosenIngredients.get(i));
//
                img.setOnMouseClicked(mouseEvent -> System.out.print("HRE"));
                listView.getItems().add(img);
//                listView.getItems().add(img2);
//                listView.getItems().add(img3);
//                listView.getItems().add(img4);
            }
        }catch (Exception e ){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
