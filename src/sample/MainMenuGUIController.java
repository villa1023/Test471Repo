package sample;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class MainMenuGUIController implements Initializable {
        UserIngredients ingredientsObject = new UserIngredients();
        @FXML
        Pane p1;
        @FXML
        private ListView<String> pro;
        @FXML
        private ListView<String> veg;
        @FXML
        private ListView<String> carbs;
        @FXML
        private ListView<String> sauces;
        @FXML
        FlowPane proteinFlowPane;
        @FXML
        FlowPane vegFlowPane;
        @FXML
        FlowPane carbsFlowPane;
        @FXML
        FlowPane saucesFlowPane;
        @FXML
        private void updateIngredients () {
        ObservableList<Node> list1 = proteinFlowPane.getChildren();
        ObservableList<Node> list2 = vegFlowPane.getChildren();
        ObservableList<Node> list3 = carbsFlowPane.getChildren();
        ObservableList<Node> list4 = saucesFlowPane.getChildren();
        //create list of nodes for all ingredient flow panes
        //delete all of the previous objects data and clear the entire list view containing all ingredients
        ingredientsObject.deleteAll();
        clearTable();
        //loop through each corresponding list casting each node element to a radio button, then check if the button is selected, if selected then add that ingredient
        //to the object and list view
        for (int i = 0; i < list1.size(); i++) {
            Node n1 = list1.get(i);
            RadioButton c1 = (RadioButton) n1;
            if (c1.isSelected()) {
                pro.getItems().add(String.valueOf(list1.get(i).getId()));
                ingredientsObject.addIngredient(String.valueOf(list1.get(i).getId()));
            }
        }
        for (int i = 0; i < list2.size(); i++) {
            Node n1 = list2.get(i);
            RadioButton c1 = (RadioButton) n1;
            if (c1.isSelected()) {
                veg.getItems().add(String.valueOf(list2.get(i).getId()));
                ingredientsObject.addIngredient(String.valueOf(list2.get(i).getId()));
            }
        }
        for (int i = 0; i < list3.size(); i++) {
            Node n1 = list3.get(i);
            RadioButton c1 = (RadioButton) n1;
            if (c1.isSelected()) {
                carbs.getItems().add(String.valueOf(list3.get(i).getId()));
                ingredientsObject.addIngredient(String.valueOf(list3.get(i).getId()));
            }
        }
        for (int i = 0; i < list4.size(); i++) {
            Node n1 = list4.get(i);
            RadioButton c1 = (RadioButton) n1;
            if (c1.isSelected()) {
                sauces.getItems().add(String.valueOf(list4.get(i).getId()));
                ingredientsObject.addIngredient(String.valueOf(list4.get(i).getId()));
            }
        }
    }
        //clear table method in case user doesn't want to uncheck every radio button
        @FXML
        private void clearTable () {
        pro.getItems().clear();
        veg.getItems().clear();
        carbs.getItems().clear();
        sauces.getItems().clear();
        ingredientsObject.deleteAll();
    }
        @FXML
        private void clearTable2 () {
        ObservableList<Node> list1 = proteinFlowPane.getChildren();
        ObservableList<Node> list2 = vegFlowPane.getChildren();
        ObservableList<Node> list3 = carbsFlowPane.getChildren();
        ObservableList<Node> list4 = saucesFlowPane.getChildren();
        for (int i = 0; i < list1.size(); i++) {
            Node n1 = list1.get(i);
            RadioButton c1 = (RadioButton) n1;
            c1.setSelected(false);
        }
        for (int i = 0; i < list2.size(); i++) {
            Node n1 = list2.get(i);
            RadioButton c1 = (RadioButton) n1;
            c1.setSelected(false);
        }
        for (int i = 0; i < list3.size(); i++) {
            Node n1 = list3.get(i);
            RadioButton c1 = (RadioButton) n1;
            c1.setSelected(false);
        }
        for (int i = 0; i < list4.size(); i++) {
            Node n1 = list4.get(i);
            RadioButton c1 = (RadioButton) n1;
            c1.setSelected(false);
        }
        clearTable();
    }
    @FXML
    public void loadSecond(ActionEvent event) throws IOException {
        try{
            populate();
            //Create an object for the controller of the next scene call the objects setstage method to create the new scene, then call the close stage scene to close the window.
            //Otherwise the window will linger.
            OverviewRecipesGUIController cont2 = new OverviewRecipesGUIController();
            cont2.setStage();
            ((Stage)p1.getScene().getWindow()).close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void populate() throws Exception {
//        pass chosen ingredients to the sort titles method, the sort titles method then calls the database and compares the ingredients
//        which we have passed with the ingredients from each recipe, if there's a match then we add the recipe_id to another list.
//        That list then calls the database again, and returns the recipe_name which will then be displayed
        ArrayList<String> relevantRecipes = ORGUIContDAO.sortTitles(ingredientsObject.ingredients);
        for(int i = 0; i < relevantRecipes.size();i++){
            ingredientsObject.chosenIngredients.add(relevantRecipes.get(i));
        }
    }
        @Override
        public void initialize (URL url, ResourceBundle rb){
    }
}
