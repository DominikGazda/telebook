package pl.gazda.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.gazda.model.Database;
import pl.gazda.model.TeleBook;

import java.io.IOException;
import java.sql.SQLException;

public class DeletePaneController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private Button addButton;
    @FXML
    private TextField errorTextField;

    public void initialize(){
        deleteUser();
    }

    private void deleteUser(){
        addButton.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            String name = nameTextField.getText()+" "+surnameTextField.getText();
            try {
                Database.deleteContactFromDatabase(name);
                Database.createContactFromDatabase();
                TeleBook.deleteContact(name);
                createNewScene(addButton);
            } catch (SQLException e) {
                e.printStackTrace();
            }catch(IllegalArgumentException e){
                showError(e.getMessage());
            }catch(NullPointerException e){
                showError("Podana wartość nie może być null");
            }
        });
    }
    private void createNewScene(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Parent parent = FXMLLoader.load(getClass().getResource("/fxml/mainPane.fxml"));
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
    private void showError(String text){
        errorTextField.setText(text);
    }
}
