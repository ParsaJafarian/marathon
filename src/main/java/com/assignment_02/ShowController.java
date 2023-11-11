package com.assignment_02;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class ShowController {
    private final ObservableList<Image> images = initializeImages();
    @FXML private ImageView imageView;
    @FXML private Button backBtn;

    @FXML
    public void initialize() {
        backBtn.setOnAction(e -> Controller.switchToScene(e, "index.fxml"));
        new Thread(() -> {
            while(true){
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

    private @NotNull ObservableList<Image> initializeImages(){
        ObservableList<Image> images = FXCollections.observableArrayList();
        images.add(new Image("file:src/main/resources/com/assignment_02/runner1.jpg"));
        images.add(new Image("file:src/main/resources/com/assignment_02/runner2.jpg"));
        images.add(new Image("file:src/main/resources/com/assignment_02/runner3.jpg"));
        images.add(new Image("file:src/main/resources/com/assignment_02/runner4.jpg"));
        return images;
    }
}
