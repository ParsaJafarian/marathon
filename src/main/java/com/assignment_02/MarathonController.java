package com.assignment_02;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MarathonController {

    @FXML
    private VBox tracks, statuses;
    @FXML
    private Button backBtn, startBtn, pauseBtn, exitBtn;
    private final ObservableList<Marathoner> marathoners = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        marathoners.add(new Marathoner("Bob", 1, "file:src/main/resources/com/assignment_02/runner1.jpg"));
        marathoners.add(new Marathoner("Rob", 2,  "file:src/main/resources/com/assignment_02/runner2.jpg"));
        marathoners.add(new Marathoner("Tom", 3,  "file:src/main/resources/com/assignment_02/runner3.jpg"));
        marathoners.add(new Marathoner("Ron", 4, "file:src/main/resources/com/assignment_02/runner4.jpg"));

        for (int i = 0; i < marathoners.size(); i++) {
            if (tracks.getChildren().get(i) instanceof VBox track)
                track.getChildren().add(marathoners.get(i));
            if (statuses.getChildren().get(i) instanceof Label statusLabel)
                statusLabel.textProperty().bind(marathoners.get(i).getStatus());
        }

        startBtn.setOnAction(e -> marathoners.forEach(Marathoner::run));
        pauseBtn.setOnAction(e -> marathoners.forEach(Marathoner::pause));
        exitBtn.setOnAction(e -> System.exit(0));
        backBtn.setOnAction(e -> {
            try {
                Controller.switchToScene(e, "index.fxml");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}