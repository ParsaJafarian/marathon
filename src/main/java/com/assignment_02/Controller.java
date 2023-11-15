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

/**
 * This is the controller for the index.fxml.
 * Its sole purpose is to redirect the user to the show.fxml or the marathon.fxml.
 */
public class Controller {
    @FXML private Button showBtn, marathonBtn;

    @FXML
    public void initialize() {
        //Show the slide show when the showBtn is clicked.
        showBtn.setOnAction(e -> switchToScene(e, "show.fxml"));
        //Show the marathon when the marathonBtn is clicked.
        marathonBtn.setOnAction(e -> switchToScene(e, "marathon.fxml"));
    }

    /**
     * The purpose of this method is to switch to another scene with the given fxml file name.
     * It is static because it is used in MarathonController and ShowController.
     * @param event The event that triggers the switch to another scene
     * @param fxmlName The name of the fxml file to switch to
     */
    public static void switchToScene(@NotNull ActionEvent event, @NotNull String fxmlName) {
        //If the fxmlName does not end with .fxml, throw an exception.
        if(!fxmlName.contains(".fxml"))
            throw new IllegalArgumentException("fxmlName must be a valid fxml file name");
        //Surrounded with try catch because the FXMLLoader.load method throws an IOException.
        try {
            //Get the root from the fxml file and set the scene to the root.
            Parent root = FXMLLoader.load(Objects.requireNonNull(Controller.class.getResource(fxmlName)));
            Scene scene = new Scene(root);
            //Get the stage from the event and set the scene to the stage.
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            //Show the stage.
            stage.show();
        } catch(IOException exception) {
            //If the fxml file is not found, throw a RuntimeException to terminate the program.
            throw new RuntimeException(exception);
        }
    }
}
