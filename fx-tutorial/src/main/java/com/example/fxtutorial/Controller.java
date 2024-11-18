package com.example.fxtutorial;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class Controller {

    @FXML
    // private Circle mycircle;
    private ImageView imageView;
    private Image image1;
    private Image image2;
    private boolean isShowingImage1 = true;

    Button button;

    private double x;
    private double y;

    @FXML
    public void initialize() {
        image1 = new Image(getClass().getResourceAsStream("/silly.jpg"));
        image2 = new Image(getClass().getResourceAsStream("/pupper.jpg"));
        imageView.setImage(image1);

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

    public void displayImage(){
        if(isShowingImage1){
            imageView.setImage(image2);
        } else {
            imageView.setImage(image1);
        }
        isShowingImage1 = !isShowingImage1;
    }
}
