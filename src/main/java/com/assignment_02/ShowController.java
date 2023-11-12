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

import javax.sound.sampled.*;

public class ShowController {
    private final ObservableList<Image> images = initializeImages();
    @FXML
    private ImageView imageView;
    @FXML
    private Button backBtn;

    @FXML
    public void initialize() {
        Clip clip;
        try {
            File audioFile = new File("src/main/resources/com/assignment_02/crowd.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        backBtn.setOnAction(e -> {
            clip.stop();
            switchToScene(e, "index.fxml");
        });
        new Thread(() -> {
            while (true) {
                for (Image image : images) {
                    imageView.setImage(image);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    private @NotNull ObservableList<Image> initializeImages() {
        ObservableList<Image> images = FXCollections.observableArrayList();
        images.add(new Image("file:src/main/resources/com/assignment_02/runner1.jpg"));
        images.add(new Image("file:src/main/resources/com/assignment_02/runner2.jpg"));
        images.add(new Image("file:src/main/resources/com/assignment_02/runner3.jpg"));
        images.add(new Image("file:src/main/resources/com/assignment_02/runner4.jpg"));
        return images;
    }
}
