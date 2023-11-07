package com.assignment_02;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class Controller {

    private ObservableList<Marathoner> marathoners = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        marathoners.add(new Marathoner("Bob", 1, 10.0, "file:src/main/resources/com/assignment_02/runner1.png"));
        marathoners.add(new Marathoner("Rob", 2, 20, "file:src/main/resources/com/assignment_02/runner2.png"));
        marathoners.add(new Marathoner("Tom", 3, 40, "file:src/main/resources/com/assignment_02/runner3.png"));
        marathoners.add(new Marathoner("Ron", 4, 10.0, "file:src/main/resources/com/assignment_02/runner4.png"));

        marathoners.forEach(Marathoner::run);

    }
}