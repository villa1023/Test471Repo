package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RecipePhotos {
    private static String medSaladPhoto = "Resources/MedSalad.png";
    private static String sTacoPhoto = "Resources/Staco.png";
    private static String sBoil = "Resources/Sboil.png";
    private static Image image;
    private static ImageView img;
    private static FileInputStream input;
    public static ImageView passBackImageView(String str) throws FileNotFoundException {
        if(str.equalsIgnoreCase("Mediterranean Grilled Chicken Salad")){
            input = new FileInputStream(medSaladPhoto);
        }
        //Spicy shrimp tacos with garlic cilantro lime slaw
        else if(str.equalsIgnoreCase("Spicy shrimp tacos with garlic cilantro lime slaw")){
            input = new FileInputStream(sTacoPhoto);
        }else{
            input = new FileInputStream(sBoil);
        }
        image = new Image(input);
        img = new ImageView(image);
        return img;
    }
    public static Image passBackImage(String str) throws FileNotFoundException{
        if(str.equalsIgnoreCase("Mediterranean Grilled Chicken Salad")){
            input = new FileInputStream(medSaladPhoto);
        }
        //Spicy shrimp tacos with garlic cilantro lime slaw
        else if(str.equalsIgnoreCase("Spicy shrimp tacos with garlic cilantro lime slaw")){
            input = new FileInputStream(sTacoPhoto);
        }else{
            input = new FileInputStream(sBoil);
        }
        image = new Image(input);
        return image;
    }
}
