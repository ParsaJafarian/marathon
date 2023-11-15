package com.assignment_02;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static com.assignment_02.Controller.switchToScene;
import static com.assignment_02.Marathoner.getUrl;

import javax.sound.sampled.*;

/**
 * This controller is for the show.fxml.
 * It displays the slide show of the marathoners with a crowd sound.
 */
public class ShowController {
    /**
     * An ObservableList of images of the marathoners
     */
    private final ObservableList<Image> images = initializeImages();
    @FXML
    private ImageView imageView;
    @FXML
    private Button backBtn;

    @FXML
    public void initialize() {
        Clip clip = getAudioClip("src/main/resources/com/assignment_02/sounds/crowd.wav");
        clip.loop(Clip.LOOP_CONTINUOUSLY);

        //Go back to the index page when the backBtn is clicked.
        backBtn.setOnAction(e -> {
            clip.stop();
            switchToScene(e, "index.fxml");
        });
        //Start a new thread to display the images in the imageView.
        //Thread is used because the images are displayed in a loop and the program would not respond otherwise.
        //Moreover, the program can sleep for 1 second between each image.

        new Thread(() -> {
            //Loop through the images and display them in the imageView indefinitely.
            while (true) {
                for (Image image : images) {
                    imageView.setImage(image);
                    //Surrounded with try catch because the TimeUnit.SECONDS.sleep method throws an InterruptedException.
                    try {
                        //Wait for 1 second before displaying the next image.
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    /**
     * The purpose of this method is to get a Clip of the audio file from the given path so
     * that it can be manipulated in any other controller. Hence, why it is static.
     * @param path The path of the audio file
     * @return A Clip of the audio file
     */
    public static @NotNull Clip getAudioClip(String path){
        Clip clip;
        //Surrounded with try catch because the AudioSystem.getAudioInputStream method throws an UnsupportedAudioFileException and an IOException.
        try {
            //Load the file through the path
            File audioFile = new File(path);
            //Get the audio stream from the audio file and open the clip
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            return clip;
        } catch (Exception e) {
            //If the file is not found, throw a RuntimeException to terminate the program.
            throw new RuntimeException(e);
        }
    }

    /**
     * @return An ObservableList of images of the marathoners
     */
    private @NotNull ObservableList<Image> initializeImages() {
        ObservableList<Image> images = FXCollections.observableArrayList();
        images.add(new Image(getUrl(1)));
        images.add(new Image(getUrl(2)));
        images.add(new Image(getUrl(3)));
        images.add(new Image(getUrl(4)));
        return images;
    }
}
