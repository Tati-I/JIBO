package org.example.main;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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

        Button toggleButton = new Button("Toggle Night Mode");
        toggleButton.setLayoutX(20);
        toggleButton.setLayoutY(20);

        toggleButton.setOnAction(_ -> {
            isNightMode = !isNightMode;
            if (scene != null) {
                scene.getStylesheets().clear();
                if (isNightMode) {
                    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(nightMode)).toExternalForm());
                } else {
                    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(lightMode)).toExternalForm());
                }
            }
        });

        SettingPane.getChildren().add(toggleButton);
        leftSidePane.getChildren().add(SettingPane);


        scene = leftSidePane.getScene();
        if (scene == null) {

            leftSidePane.sceneProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    scene = newValue;

                    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(lightMode)).toExternalForm());
                }
            });
        } else {

            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(lightMode)).toExternalForm());
        }

        return leftSidePane;
    }
}