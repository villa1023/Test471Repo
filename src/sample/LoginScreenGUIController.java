package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenGUIController implements Initializable {
    CryptographerAES cryptoObject = new CryptographerAES("abcdefghijklmnos");
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Text errCompMessage;

    ControllersDAO obj = new ControllersDAO();

    public void login() throws Exception {
        errCompMessage.setText("");
        String user = username.getText();
        String pass = password.getText();
        pass = cryptoObject.encrypt(pass);
        if(obj.retrieveRecord(user,pass)){
            try {
                //Load second scene
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AfterLoginGUI.fxml"));
                Parent root = loader.load();
                //Get controller of scene2
                AfterLoginGUIController firstSceneController = loader.getController();
                //Pass whatever data you want. You can have multiple method calls here
                //Show scene 2 in new window
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("iCook");
                stage.show();
                ((Stage)username.getScene().getWindow()).close();
            } catch (IOException ex) {
                System.err.println(ex);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            errCompMessage.setText("Invalid entry: username or password incorrect.");
        }
    }
    public void register() throws Exception {
        String user = username.getText();
        String pass = password.getText();
        pass = cryptoObject.encrypt(pass);
        boolean state = obj.enterRecord(user,pass);
        errCompMessage.setText("Successfully created account, please login.");
        if(!state){
            errCompMessage.setText("Username already exists.");
        }
    }
//    public void registerUser(){
//        try {
//            //Load second scene
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterGUI.fxml"));
//            Parent root = loader.load();
//            //Get controller of scene2
//            RegisterGUIController scene2Controller = loader.getController();
//            //Pass whatever data you want. You can have multiple method calls here
//            //Show scene 2 in new window
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.setTitle("iCook");
//            stage.show();
//            ((Stage)username.getScene().getWindow()).close();
//        } catch (IOException ex) {
//            System.err.println(ex);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
