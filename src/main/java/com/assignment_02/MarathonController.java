package com.assignment_02;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import static com.assignment_02.Controller.switchToScene;

public class MarathonController {

    /**
     * Tracks is a Vbox that holds multiple VBoxes with the id track.
     * Status is a VBox that holds multiple Labels with the id statusLabel.
     */
    @FXML
    private VBox tracks, statuses;
    @FXML
    private Button backBtn, startBtn, pauseBtn, exitBtn;
    //Load the marathoners from the Marathoner class.
    private final ObservableList<Marathoner> marathoners = Marathoner.marathoners;
    @FXML
    public void initialize() {
        //Add the marathoners to the tracks and add their statuses to the statuses.
        for (int i = 0; i < marathoners.size(); i++) {
            if (tracks.getChildren().get(i) instanceof VBox track)
                track.getChildren().add(marathoners.get(i));
            if (statuses.getChildren().get(i) instanceof Label statusLabel)
                statusLabel.textProperty().bind(marathoners.get(i).getStatus());
        }

        //Set the actions of the buttons.
        startBtn.setOnAction(e -> {
            //If all the marathoners have finished the marathon, reset their statuses and move them to the start position.
            //Else, make each marathoner run.
            if(marathoners.stream().allMatch(m -> m.getStatus().getValue().contains("finished")))
                marathoners.forEach(Marathoner::goToStartPosition);
            else marathoners.forEach(Marathoner::run);
        });
        //Pause the marathoners.
        pauseBtn.setOnAction(e -> marathoners.forEach(Marathoner::pause));
        exitBtn.setOnAction(e -> System.exit(0));
        //Go back to the index page.
        backBtn.setOnAction(e -> switchToScene(e, "index.fxml"));
    }
}