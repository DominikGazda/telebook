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
import java.io.IOException;
import java.sql.SQLException;



public class AddPaneController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private Button addButton;
    @FXML
    private TextField errorTextField;


    public void initialize(){
        addUser();
    }

  private void addUser(){
      addButton.addEventHandler(MouseEvent.MOUSE_PRESSED,mouseEvent -> {
          String name = nameTextField.getText()+" "+surnameTextField.getText();
          String number = phoneTextField.getText();
          try {
              Database.createContactToDatabase(name,number);
              Database.createContactFromDatabase();
              createNewScene(addButton);
          }catch (SQLException  e) {
             showError("Nie udało się połączyć z bazą danych");
          }catch(NumberFormatException e){
              showError("Numer powinien składać się tylko z cyfr !");
          }catch(NullPointerException e){
              showError(e.getMessage());
          }catch(IllegalArgumentException e){
              showError(e.getMessage());
          }catch(IndexOutOfBoundsException e){
              showError("Pola nie mogą być puste");
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
