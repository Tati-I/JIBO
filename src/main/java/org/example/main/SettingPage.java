package org.example.main;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class SettingPage {

    private boolean isNightMode = false;

    public Pane SettingPane(Pane leftSidePane) {



        Pane SettingPane = new Pane();
        SettingPane.setPrefSize(934, 784);
        SettingPane.setStyle("-fx-background-color: linear-gradient(to bottom right, #f0f0f0, #e0e0e0);-fx-background-radius: 0 20 20 0");

        Button toggleButton = new Button("Toggle Night Mode");
        toggleButton.setLayoutX(20);
        toggleButton.setLayoutY(20);

        toggleButton.setOnAction(event -> {
            if (isNightMode) {
                Scene scene = new Scene(leftSidePane);
                scene.getStylesheets().remove(Objects.requireNonNull(getClass().getResource("/styles/NightMode.css")).toExternalForm());
                scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/LightMode.css")).toExternalForm());
                isNightMode = false;

            } else {
                Scene scene = new Scene(leftSidePane);
                scene.getStylesheets().remove(Objects.requireNonNull(getClass().getResource("/styles/LightMode.css")).toExternalForm());
                scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/NightMode.css")).toExternalForm());
                isNightMode = true;

            }
        });

        SettingPane.getChildren().add(toggleButton);

        leftSidePane.getChildren().add(SettingPane);
        return leftSidePane;
    }
}

