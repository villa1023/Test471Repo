package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class AfterLoginGUIController implements Initializable {
    FileInputStream fstreamObj1 = new FileInputStream("C:\\Users\\Owner\\Desktop\\ICookFinal\\Resources\\aboutIcook.png");
    FileInputStream fstreamObj2 = new FileInputStream("C:\\Users\\Owner\\Desktop\\ICookFinal\\Resources\\viewpantry.png");
    FileInputStream fstreamObj3 = new FileInputStream("C:\\Users\\Owner\\Desktop\\ICookFinal\\Resources\\ingredients.png");
    Image topImage = new Image(fstreamObj1);
    Image middleImage = new Image(fstreamObj2);
    Image bottomImage = new Image(fstreamObj3);
    @FXML
    ImageView img1;
    @FXML
    ImageView img2;
    @FXML
    ImageView img3;

    public AfterLoginGUIController() throws FileNotFoundException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        img1.setImage(topImage);
        img2.setImage(middleImage);
        img3.setImage(bottomImage);
    }
}
