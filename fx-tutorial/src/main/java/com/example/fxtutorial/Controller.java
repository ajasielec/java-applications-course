package com.example.fxtutorial;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class Controller {

    @FXML
    // private Circle mycircle;
    private ImageView imageView;
    private double x;
    private double y;

    @FXML
    public void initialize() {
        x = imageView.getLayoutX();
        y = imageView.getLayoutY();
    }

    public void up(ActionEvent event) {
        imageView.setLayoutY(y-=5);
    }
    public void down(ActionEvent event) {
        imageView.setLayoutY(y+=5);
    }
    public void left(ActionEvent event) {
        imageView.setLayoutX(x-=5);
    }
    public void right(ActionEvent event) {
        imageView.setLayoutX(x+=5);
    }
}
