package pl.gazda.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pl.gazda.app.TeleBookController;
import pl.gazda.model.Contact;
import java.io.IOException;
import java.util.Map;
import java.util.Set;



public class MainPaneController {
    @FXML
    private ControlPaneController controlPaneController;
    @FXML
    private ContentPaneController contentPaneController;

    private TeleBookController teleBookController = new TeleBookController();
    public void initialize(){
        createTableRecordsFromDatabase();
        createNewSceneAdd(controlPaneController.getAddButton());
        createNewSceneDel(controlPaneController.getDeleteButton());
        createNewSceneSearch(controlPaneController.getAboutButton());
    }


    public void createTableRecordsFromDatabase(){
        Map<String,Contact> contactMap = teleBookController.createDatabase();
        Set<String> keySet = contactMap.keySet();
        for(String key:keySet){
            contentPaneController.getTableTableView().getItems()
                    .add(new Contact(contactMap.get(key).getId(),contactMap.get(key).getName(),contactMap.get(key).getNumber()));
        }
    }

    private void createNewSceneAdd(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Parent parent = FXMLLoader.load(getClass().getResource("/fxml/addPane.fxml"));
                    Scene gameScene = new Scene(parent);
                    Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
                    stage.setScene(gameScene);
                    stage.show();

                }catch(IOException e){
                    System.out.println("Zła ścieżka");
                }
            }
        });
    }
    private void createNewSceneDel(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Parent parent = FXMLLoader.load(getClass().getResource("/fxml/deletePane.fxml"));
                    Scene gameScene = new Scene(parent);
                    Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
                    stage.setScene(gameScene);
                    stage.show();

                }catch(IOException e){
                    System.out.println("Zła ścieżka");
                }
            }
        });
    }
    private void createNewSceneSearch(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Parent parent = FXMLLoader.load(getClass().getResource("/fxml/searchPane.fxml"));
                    Scene gameScene = new Scene(parent);
                    Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
                    stage.setScene(gameScene);
                    stage.show();

                }catch(IOException e){
                    System.out.println("Zła ścieżka");
                }
            }
        });
    }

    }




