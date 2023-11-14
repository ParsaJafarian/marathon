package com.assignment_02;

import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Marathoner extends ImageView {
    private final String name;
    private final TranslateTransition transition;
    private final StringProperty status = new SimpleStringProperty();
    private final int number;
    private double speed;
    public static final ObservableList<Marathoner> marathoners = initializeMarathoners();

    public Marathoner(String name, int number, String imageUrl) {
        super();
        this.name = name;
        this.number = number;
        this.speed = Math.random() * 15 + 5;
        this.setStatus("is ready to run");

        transition = new TranslateTransition(new Duration(100 / speed * 100), this);
        transition.setToX(500);
        transition.setOnFinished(e -> this.setStatus("finished"));

        this.setImage(new Image(imageUrl));
        this.setFitHeight(50);
        this.setFitWidth(50);
    }

    public void run() {
        if(!status.getValue().contains("finished")){
            this.setStatus("is running");
            transition.play();
        }
    }

    public void goToStartPosition() {
        TranslateTransition goBackTransition = new TranslateTransition(new Duration(1), this);
        goBackTransition.setByX(-500);
        goBackTransition.play();
        goBackTransition.setOnFinished(e -> {
            this.setStatus("is ready to run");
            this.resetSpeed();
        });
    }

    public ObservableValue<String> getStatus() {
        return status;
    }

    private void setStatus(String status){
        this.status.set(this.number + " " + this.name + " " + status);
    }

    public void pause() {
        if(!status.getValue().contains("finished"))
            status.set(this.number + " " + this.name + " is paused");
        transition.pause();
    }

    private void resetSpeed() {
        this.speed = Math.random() * 15 + 5;
        transition.setDuration(new Duration(100 / speed * 100));
    }

    private static @NotNull ObservableList<Marathoner> initializeMarathoners() {
        ObservableList<Marathoner> marathoners = FXCollections.observableArrayList();
        marathoners.add(new Marathoner("Bob", 1, getUrl(1)));
        marathoners.add(new Marathoner("Rob", 2, getUrl(2)));
        marathoners.add(new Marathoner("Tom", 3, getUrl(3)));
        marathoners.add(new Marathoner("Ron", 4, getUrl(4)));
        return marathoners;
    }

    @Contract(pure = true)
    private static @NotNull String getUrl(int i){
        return "file:src/main/resources/com/assignment_02/images/marathoner" + i + ".jpg";
    }
}
