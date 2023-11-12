package com.assignment_02;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(@NotNull Stage stage) throws IOException {
        stage.setOnCloseRequest(e -> System.exit(0) );
        FXMLLoader loader = new FXMLLoader(getClass().getResource("index.fxml"));
        Scene scene = new Scene(loader.load(), 745,400);
        stage.setResizable(false);
        stage.setTitle("Assignment 02");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}