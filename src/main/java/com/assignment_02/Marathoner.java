package com.assignment_02;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Marathoner extends ImageView {
    private String name;
    private int number;
    private double speed = 10.0;

    public Marathoner(String name, int number, double speed, String url){
        this.name = name;
        this.number = number;
        this.speed = speed;
        this.setImage(new Image(url));
    }

    public void run() {
        TranslateTransition transition = new TranslateTransition(new Duration(10000/speed), this);
        transition.setByX(500);
        transition.play();
    }
}
