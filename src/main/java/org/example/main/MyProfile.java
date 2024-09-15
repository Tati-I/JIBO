package org.example.main;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.Objects;

public class MyProfile {
    public Pane showMyProfilePage(Pane leftSidePane) {
        leftSidePane.getChildren().clear();


        // إنشاء لوحة الشاشة الرئيسية
        Pane profilePane = new Pane();
        profilePane.setId("myProfilePane");
        profilePane.prefWidthProperty().bind(leftSidePane.widthProperty());
        profilePane.prefHeightProperty().bind(leftSidePane.heightProperty());

        Button profileButton = new Button("Profile");
        profileButton.setAlignment(Pos.CENTER);



        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/Moon.png")));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(250);
        imageView.setFitWidth(250);
        imageView.setLayoutX(500);
        imageView.setLayoutY(300);


        FadeTransition fadeIn = new FadeTransition(Duration.millis(500),profilePane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();


        profilePane.getChildren().addAll(profileButton,imageView);





        // إضافة لوحة الشاشة الرئيسية إلى لوحة الجانب الأيسر
        leftSidePane.getChildren().add(profilePane);
        return leftSidePane;
    }
}
