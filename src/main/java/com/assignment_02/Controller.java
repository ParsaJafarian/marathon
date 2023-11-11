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
    @FXML
    private Button showBtn, marathonBtn;

    @FXML
    public void initialize() {
        showBtn.setOnAction(e -> {
            try {
                switchToScene(e, "show.fxml");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        marathonBtn.setOnAction(e -> {
            try {
                switchToScene(e, "marathon.fxml");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static void switchToScene(@NotNull ActionEvent e, @NotNull String fxmlName) throws IOException {
        if(!fxmlName.contains(".fxml"))
            throw new IllegalArgumentException("fxmlName must be a valid fxml file name");

        Parent root = FXMLLoader.load(Objects.requireNonNull(Controller.class.getResource(fxmlName)));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
