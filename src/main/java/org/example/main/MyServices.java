package org.example.main;

import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import javax.swing.text.IconView;
import java.util.Objects;

public class MyServices {
    public Pane MyServicePane(Pane leftSidePane) {
        leftSidePane.getChildren().clear();

        // إنشاء لوحة الشاشة الرئيسية
        Pane ServicePane = new Pane();
        ServicePane.setId("ServicePane");
        ServicePane.prefWidthProperty().bind(leftSidePane.widthProperty());
        ServicePane.prefHeightProperty().bind(leftSidePane.heightProperty());

        Button button = new Button("");
        ServicePane.getChildren().add(button);

        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/Moon.png")));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(250);
        imageView.setFitWidth(250);
        imageView.setLayoutX(500);
        imageView.setLayoutY(300);
        ServicePane.getChildren().add(imageView);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(500),ServicePane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        // إضافة لوحة الشاشة الرئيسية إلى لوحة الجانب الأيسر
        leftSidePane.getChildren().add(ServicePane);
        return leftSidePane;
    }
}
