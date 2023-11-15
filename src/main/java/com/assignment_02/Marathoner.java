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

import javax.sound.sampled.Clip;

import static com.assignment_02.ShowController.getAudioClip;

/**
 * This class represents a marathoner participating in the marathon.
 * It extends ImageView to display the image of the marathoner both in the slide show and the marathon itself.
 * It also has a TranslateTransition to move the marathoner to the right and to move it back to the left when the marathon is over.
 */
public class Marathoner extends ImageView {
    private final String name;
    private final TranslateTransition transition;
    private final StringProperty status = new SimpleStringProperty();
    private final int number;
    private double speed;
    //This is a list of all the marathoners participating in the marathon. It is static because it is used in MarathonController.
    //In this program there will only be one marathoner list, so it is not necessary to have multiple instances of it.
    //Its main purpose is to set the rankings of the marathoners.
    public static final ObservableList<Marathoner> marathoners = initializeMarathoners();

    /**
     * @param name The name of the marathoner
     * @param number The number of the marathoner
     * @param imageUrl The url of the image of the marathoner so that the image can be loaded and displayed
     */
    public Marathoner(String name, int number, String imageUrl) {
        super();
        this.name = name;
        this.number = number;
        //The speed of the marathoner is a random number between 5 and 20.
        this.speed = Math.random() * 15 + 5;
        //initialize the status of the marathoner to "is ready to run"
        this.setStatus("is ready to run");

        //The transition is initialized with a duration of 100 / speed * 100 milliseconds.
        transition = new TranslateTransition(new Duration(100 / speed * 500), this);
        //The marathoner moves to the right by 500 pixels. In other words, it runs until the end of the track.
        transition.setToX(500);
        transition.setOnFinished(e -> {
            //Get the number of marathoners that have finished the marathon.
            long numFinished = marathoners.stream().filter(m -> m.getStatus().getValue().contains("finished")).count();
            //Set the status of the marathoner to "finished 1st" if it is the first marathoner to finish the marathon.
            //Continue with the other rankings.
            if(numFinished == 0) this.setStatus("finished 1st");
            else if(numFinished == 1) this.setStatus("finished 2nd");
            else if(numFinished == 2) this.setStatus("finished 3rd");
            else this.setStatus("finished " + (numFinished + 1) + "th");

            Clip clip = getAudioClip("src/main/resources/com/assignment_02/sounds/bell.wav");
            clip.start();
        });

        //Load the image of the marathoner and set its height and width to 50 pixels.
        this.setImage(new Image(imageUrl));
        this.setFitHeight(50);
        this.setFitWidth(50);
    }

    public void run() {
        //If the marathoner has not finished the marathon, set its status to "is running" and start the transition.
        if(!status.getValue().contains("finished")){
            this.setStatus("is running");
            transition.play();
        }
    }

    public void goToStartPosition() {
        //Make a transition to move the marathoner back to the left by 500 pixels in a duration of 1 millisecond.
        //It is 1 millisecond because the marathoner should be at the start position immediately.
        TranslateTransition goBackTransition = new TranslateTransition(new Duration(1), this);
        goBackTransition.setByX(-500);
        goBackTransition.play();
        //When the transition is finished, set the status of the marathoner to "is ready to run" and regenerate its speed.
        goBackTransition.setOnFinished(e -> {
            this.setStatus("is ready to run");
            this.resetSpeed();
        });
    }


    /**
     * @return The status of the marathoner as a StringProperty
     */
    public ObservableValue<String> getStatus() {
        return status;
    }

    /**
     * @param status The status of the marathoner to be set
     */
    private void setStatus(String status){
        //The status always holds the player's number, name and the actual status.
        this.status.set(this.number + " " + this.name + " " + status);
    }

    public void pause() {
        //If the marathoner has not finished the marathon, set its status to "is paused" and pause the transition.
        if(!status.getValue().contains("finished")){
            status.set(this.number + " " + this.name + " is paused");
            transition.pause();
        }
    }

    private void resetSpeed() {
        //Regenerate the speed of the marathoner to a random number between 5 and 20.
        this.speed = Math.random() * 15 + 5;
        //Reset the duration of the transition with the new speed.
        transition.setDuration(new Duration(100 / speed * 500));
    }

    /**
     * @return An ObservableList of Marathoners to be initialized only once
     */
    private static @NotNull ObservableList<Marathoner> initializeMarathoners() {
        //This method is used to initialize the marathoners list.
        //Because it is static, it is only initialized once.
        ObservableList<Marathoner> marathoners = FXCollections.observableArrayList();
        marathoners.add(new Marathoner("Bob", 1, getUrl(1)));
        marathoners.add(new Marathoner("Rob", 2, getUrl(2)));
        marathoners.add(new Marathoner("Tom", 3, getUrl(3)));
        marathoners.add(new Marathoner("Ron", 4, getUrl(4)));
        return marathoners;
    }

    /**
     * @param i The number of the marathoner
     * @return The url of the image of the marathoner
     */
    @Contract(pure = true)
    public static @NotNull String getUrl(int i){
        //This method is used to get the url of the image of the marathoner.
        return "file:src/main/resources/com/assignment_02/images/marathoner" + i + ".jpg";
    }
}
