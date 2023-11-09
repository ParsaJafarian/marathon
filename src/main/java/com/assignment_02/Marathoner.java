package com.assignment_02;

import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Marathoner extends ImageView {
    private final String name;
    private final TranslateTransition transition;
    private final StringProperty status;

    public Marathoner(String name, int number, double speed, String imageUrl){
        super();
        this.name = name;
        this.status = new SimpleStringProperty(number + " " + this.name + " is running");

        transition = new TranslateTransition(new Duration(100/speed * 1000), this);
        transition.setToX(500);
        transition.setOnFinished(e -> this.status.set(number + " " + this.name + " has finished"));

        this.setImage(new Image(imageUrl));
        this.setFitHeight(50);
        this.setFitWidth(50);
    }

    public void run() {
        transition.play();
    }

    public ObservableValue<String> getStatus(){
        return status;
    }

    public void pause(){
        transition.pause();
    }
}
