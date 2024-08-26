package org.example.main;

import javafx.scene.layout.Pane;

public class MyProfile {
    public Pane myProfilePane(Pane leftSidePane) {
        // إنشاء لوحة الشاشة الرئيسية
        Pane profilePane = new Pane();
        profilePane.setPrefSize(934, 784);
        profilePane.setStyle("-fx-background-color: linear-gradient(to bottom right, #f0f0f0, #e0e0e0);-fx-background-radius: 0 20 20 0");

        // إضافة لوحة الشاشة الرئيسية إلى لوحة الجانب الأيسر
        leftSidePane.getChildren().add(profilePane);
        return leftSidePane;
    }
}
