package org.example.main;

import javafx.scene.layout.Pane;

public class MyServices {
    public Pane MyServicePane(Pane leftSidePane) {
        // إنشاء لوحة الشاشة الرئيسية
        Pane ServicePane = new Pane();
        ServicePane.setPrefSize(934, 784);
        ServicePane.setStyle("-fx-background-color: linear-gradient(to bottom right, #f0f0f0, #e0e0e0);-fx-background-radius: 0 20 20 0");

        // إضافة لوحة الشاشة الرئيسية إلى لوحة الجانب الأيسر
        leftSidePane.getChildren().add(ServicePane);
        return leftSidePane;
    }
}
