package org.example.main;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class SignUpPage {
    public void display(Pane pane, Button loginButton, Button signUpButton, LoginPage loginPage, Rectangle loginRectangle, Label welcome, Label msg) {
        pane.getChildren().clear();
        pane.getChildren().addAll(loginRectangle,msg,loginButton,signUpButton,welcome);
        fieldsPackage(pane);
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
            loginPage.createLoginView();
        });

    }
    private void fieldsPackage(Pane pane){

        Label emailWord = new Label("البريد الإلكتروني");
        emailWord.setLayoutX(390);
        emailWord.setLayoutY(230);
        emailWord.setStyle("-fx-text-fill: #000000; -fx-font-size: 18; -fx-font-weight: bold;");

        TextField emailField = new TextField();
        emailField.setPromptText("البريد الألكتروني");
        emailField.setPrefSize(495, 40);
        emailField.setLayoutX(15);
        emailField.setLayoutY(270);
        emailField.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #d0caca;");


        Label passwordWord = new Label("كلمة المرور");
        passwordWord.setLayoutX(425);
        passwordWord.setLayoutY(315);
        passwordWord.setStyle("-fx-text-fill: #000000; -fx-font-size: 18; -fx-font-weight: bold;");

        TextField passwordField = new TextField();
        passwordField.setPromptText("كلمة المرور");
        passwordField.setPrefSize(495, 40);
        passwordField.setLayoutX(15);
        passwordField.setLayoutY(350);
        passwordField.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #D0CACAFF;");
        pane.getChildren().addAll(emailWord, emailField, passwordWord, passwordField);
    }
}