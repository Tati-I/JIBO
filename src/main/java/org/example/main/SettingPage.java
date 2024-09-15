package org.example.main;

import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Objects;

public class SettingPage {
    private static boolean isNightMode = false;
    private Scene scene;

    public Pane SettingPane(Pane leftSidePane) {


        leftSidePane.getChildren().clear();

        Pane SettingPane = new Pane();
        SettingPane.setId("settingPane");
        SettingPane.prefWidthProperty().bind(leftSidePane.widthProperty());
        SettingPane.prefHeightProperty().bind(leftSidePane.heightProperty());

        ToggleButton toggleButton = new ToggleButton();
        toggleButton.setLayoutX(400);
        toggleButton.setLayoutY(300);
        toggleButton.setPrefSize(60, 30);
        toggleButton.getStyleClass().add("mode-toggle-button");

        Image sunImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/Sun.png")));
        Image moonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/Moon.png")));

        ImageView imageView = new ImageView(sunImage);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        toggleButton.setGraphic(imageView);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(500),SettingPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();


        if (isNightMode) {
            imageView.setImage(moonImage);
        } else {
            imageView.setImage(sunImage);
        }

        toggleButton.setSelected(isNightMode);

        toggleButton.setOnAction(_ -> {
            isNightMode = !isNightMode;
            if (isNightMode) {
                imageView.setImage(moonImage);
            } else {
                imageView.setImage(sunImage);
            }
            updateStyles(isNightMode);
        });


        SettingPane.getChildren().add(toggleButton);
        leftSidePane.getChildren().add(SettingPane);

        scene = leftSidePane.getScene();
        if (scene == null) {
            leftSidePane.sceneProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    scene = newValue;
                    updateStyles(isNightMode);
                }
            });
        } else {
            updateStyles(isNightMode);
        }

        return leftSidePane;
    }

    private void updateStyles(boolean isNightMode) {
        String nightMode = "/styles/NightMode.css";
        String lightMode = "/styles/LightMode.css";
        if (scene != null) {
            scene.getStylesheets().clear();
            String mode;
            if (isNightMode) {
                mode = nightMode;
            } else {
                mode = lightMode;
            }
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(mode)).toExternalForm());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/toggleSwitch.css")).toExternalForm());
        }
    }
}
