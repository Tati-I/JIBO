package org.example.main;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class SignUpPage {
    public void display(Pane pane, Button loginButton, Button signUpButton, LoginPage loginPage, Rectangle loginRectangle, Label welcome, Label msg) {
        pane.getChildren().clear();
        pane.getChildren().addAll(loginRectangle,msg,loginButton,signUpButton,welcome);
        pane.setPrefSize(525, 700);
        pane.setLayoutY(10);

        TranslateTransition loginTransition = new TranslateTransition(Duration.millis(300), loginButton);
        TranslateTransition signUpTransition = new TranslateTransition(Duration.millis(300), signUpButton);

        loginButton.setOnAction(event -> {
            loginTransition.setToX(0);
            loginTransition.play();
            signUpTransition.setToX(0);
            signUpTransition.play();
            loginButton.setStyle("-fx-border-color: #e3e3e3; -fx-border-radius: 5px; -fx-background-color: #fdfdfd; -fx-background-radius: 5px; -fx-cursor: hand; -fx-text-fill: #000000; -fx-font-size: 15;");
            signUpButton.setStyle("-fx-text-fill: #000000; -fx-font-size: 15; -fx-background-color: #f1f2f4; -fx-background-radius: 5px; -fx-cursor: hand;");
        });
    }
}