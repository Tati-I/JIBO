package org.example.main;

import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class SettingPage {
    private boolean isNightMode = false;
    private Scene scene;

    public Pane SettingPane(Pane leftSidePane) {
        String nightMode = "/styles/NightMode.css";
        String lightMode = "/styles/LightMode.css";

        leftSidePane.getChildren().clear();

        Pane SettingPane = new Pane();
        SettingPane.setPrefSize(934, 784);
        SettingPane.setId("settingPane");

        ToggleButton toggleButton = new ToggleButton();
        toggleButton.setLayoutX(20);
        toggleButton.setLayoutY(20);
        toggleButton.setPrefSize(60, 30);
        toggleButton.getStyleClass().add("mode-toggle-button");

        Image sunImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/Sun.png")));
        Image moonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/Moon.png")));

        ImageView imageView = new ImageView(sunImage);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        toggleButton.setGraphic(imageView);

        toggleButton.setOnAction(_ -> {
            isNightMode = !isNightMode;
            imageView.setImage(isNightMode ? moonImage : sunImage);
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
        if (scene != null) {
            scene.getStylesheets().clear();
            String mode = isNightMode ? "/styles/NightMode.css" : "/styles/LightMode.css";
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(mode)).toExternalForm());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/toggleSwitch.css")).toExternalForm());
        }
    }
}