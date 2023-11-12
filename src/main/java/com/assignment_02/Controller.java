package com.assignment_02;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

public class Controller {
    @FXML private Button showBtn, marathonBtn;

    @FXML
    public void initialize() {
        showBtn.setOnAction(e -> switchToScene(e, "show.fxml"));
        marathonBtn.setOnAction(e -> switchToScene(e, "marathon.fxml"));
    }

    public static void switchToScene(@NotNull ActionEvent event, @NotNull String fxmlName) {
        if(!fxmlName.contains(".fxml"))
            throw new IllegalArgumentException("fxmlName must be a valid fxml file name");
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Controller.class.getResource(fxmlName)));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch(IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
