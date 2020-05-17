package pl.gazda.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.gazda.model.Contact;
import pl.gazda.model.TeleBook;
import java.io.IOException;
import java.util.List;


public class SearchPaneController {

    @FXML
    private TableView<Contact> tableTableView;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button searchButton;
    @FXML
    private Button backButton;

    private static final String ID_COLUMN = "ID";
    private static final String NAME_COLUMN = "Nazwa";
    private static final String NUMBER_COLUMN= "Numer telefonu";
    private TeleBook teleBook = new TeleBook();

    public void initialize(){
        createTable();
        configureButtons();
    }

    private void createTable(){

        TableColumn<Contact,Integer> idColumn = new TableColumn<>(ID_COLUMN);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Contact,String> nameColumn = new TableColumn<>(NAME_COLUMN);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Contact,String> telColumn = new TableColumn<>(NUMBER_COLUMN);
        telColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        tableTableView.getColumns().add(idColumn);
        tableTableView.getColumns().add(nameColumn);
        tableTableView.getColumns().add(telColumn);
    }

    private void search(){
        List<Contact> list = TeleBook.getContactByNameOrNumber(searchTextField.getText());
        for(Contact contact:list){
            tableTableView.getItems().add(contact);
        }
    }

    private void configureButtons(){
       searchButton.addEventHandler(MouseEvent.MOUSE_PRESSED,mouseEvent -> {
           search();
       });

       backButton.addEventHandler(MouseEvent.MOUSE_PRESSED,mouseEvent -> {
           try {
               Parent parent = FXMLLoader.load(getClass().getResource("/fxml/mainPane.fxml"));
               Scene gameScene = new Scene(parent);
               Stage stage = (Stage)((Node) mouseEvent.getSource()).getScene().getWindow();
               stage.setScene(gameScene);
               stage.show();

           }catch(IOException e){
               System.out.println("Zła ścieżka");
           }
       });
    }
}
