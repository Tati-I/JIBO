package org.example.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class PersonalHomePage extends Application {
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 900, 720);

        Image background = new Image(Objects.requireNonNull(getClass().getResource("/Pictures/background.png")).toExternalForm());
        ImageView imageView = new ImageView(background);
        imageView.setFitWidth(900);
        imageView.setFitHeight(720);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Personal Home Page");
        primaryStage.setResizable(false);
        primaryStage.show();
        root.getChildren().add(imageView);



    }

    public static void main(String[] args) {
        launch(args);
    }

}
