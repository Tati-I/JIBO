package org.example.main;

import javafx.scene.layout.Pane;

public class HomeScreen {
    public Pane RequestHomePane() {
        Pane HomePane = new Pane();
        HomePane.setPrefSize(925, 780);
        HomePane.setStyle("-fx-background-color: #ff0000");
        return HomePane;
    }
}
