package org.example.main;

import javafx.scene.layout.Pane;

public class MyProfile {
    public Pane myProfilePane(Pane leftSidePane) {
        // إنشاء لوحة الشاشة الرئيسية
        Pane profilePane = new Pane();
        profilePane.setPrefSize(925, 784);
        profilePane.setStyle("-fx-background-color: #920fa8;-fx-border-radius: 15px;-fx-background-radius: 15px");

        // إضافة لوحة الشاشة الرئيسية إلى لوحة الجانب الأيسر
        leftSidePane.getChildren().add(profilePane);
        return leftSidePane;
    }
}
