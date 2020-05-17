module telebook {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires mysql.connector.java;
    requires java.sql;

    exports pl.gazda.app;
    opens pl.gazda.controllers to javafx.fxml;
    opens pl.gazda.model to javafx.base;
}