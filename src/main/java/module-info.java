module com.example.bankingsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires static lombok;

    opens com.example.bankingsystem to javafx.fxml;
    exports com.example.bankingsystem;
    exports com.example.bankingsystem.controllers;
    opens com.example.bankingsystem.controllers to javafx.fxml;
    exports com.example.bankingsystem.models;
    opens com.example.bankingsystem.models to javafx.fxml;
}