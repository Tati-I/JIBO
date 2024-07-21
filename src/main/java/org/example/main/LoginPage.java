package org.example.main;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root,650,700, Color.BLUEVIOLET);
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(435);
        rectangle.setWidth(485);
        rectangle.setX(82);
        rectangle.setY(133);
        rectangle.setFill(Color.WHITE);
        rectangle.setArcHeight(25);
        rectangle.setArcWidth(25);




        Label WelcomeMsg = new Label("مرحبًا بك في تطبيق JIBO");
        WelcomeMsg.setLayoutX(207);
        WelcomeMsg.setLayoutY(161);
        WelcomeMsg.setStyle("-fx-text-fill: #000000; -fx-font-weight: bold; -fx-font-size: 22");



        root.getChildren().addAll(rectangle, WelcomeMsg);




        stage.setTitle("Jibo");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}