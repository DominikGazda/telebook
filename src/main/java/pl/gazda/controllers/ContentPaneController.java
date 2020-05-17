package pl.gazda.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.gazda.model.Contact;

public class ContentPaneController {
    private static final String ID_COLUMN = "ID";
    private static final String NAME_COLUMN = "Nazwa";
    private static final String NUMBER_COLUMN= "Numer telefonu";

    @FXML
    private  TableView<Contact> tableTableView;


    @FXML
    private TextField errorTextField;

    public  TableView<Contact> getTableTableView() {
        return tableTableView;
    }

    public TextField getErrorTextField() {
        return errorTextField;
    }

    public void initialize(){
        createTable();
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
}
