package com.example.bankingsystem.handlers;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class CustomExceptionHandler implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Unexpected Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        });
    }
}
