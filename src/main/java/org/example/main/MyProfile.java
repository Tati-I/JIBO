package org.example.main;

import javafx.scene.layout.Pane;

public class MyProfile {
    public Pane myProfilePane(Pane leftSidePane) {
        leftSidePane.getChildren().clear();

        // إنشاء لوحة الشاشة الرئيسية
        Pane profilePane = new Pane();
        profilePane.setPrefSize(934, 784);
        profilePane.setId("myProfilePane");

        // إضافة لوحة الشاشة الرئيسية إلى لوحة الجانب الأيسر
        leftSidePane.getChildren().add(profilePane);
        return leftSidePane;
    }
}
