package com.example.bankingsystem;

import com.example.bankingsystem.handlers.CustomExceptionHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BankingSystemApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BankingSystemApplication.class.getResource("bank.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        Thread.setDefaultUncaughtExceptionHandler(new CustomExceptionHandler());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}