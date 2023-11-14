package com.assignment_02;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import static com.assignment_02.Controller.switchToScene;

public class MarathonController {

    @FXML
    private VBox tracks, statuses;
    @FXML
    private Button backBtn, startBtn, pauseBtn, exitBtn;
    private final ObservableList<Marathoner> marathoners = Marathoner.marathoners;
    @FXML
    public void initialize() {
        for (int i = 0; i < marathoners.size(); i++) {
            if (tracks.getChildren().get(i) instanceof VBox track)
                track.getChildren().add(marathoners.get(i));
            if (statuses.getChildren().get(i) instanceof Label statusLabel)
                statusLabel.textProperty().bind(marathoners.get(i).getStatus());
        }

        startBtn.setOnAction(e -> {
            if(marathoners.stream().allMatch(m -> m.getStatus().getValue().contains("finished")))
                marathoners.forEach(Marathoner::goToStartPosition);
            else marathoners.forEach(Marathoner::run);
        });
        pauseBtn.setOnAction(e -> marathoners.forEach(Marathoner::pause));
        exitBtn.setOnAction(e -> System.exit(0));
        backBtn.setOnAction(e -> switchToScene(e, "index.fxml"));
    }
}