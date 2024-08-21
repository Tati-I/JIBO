package org.example.main;

import javafx.scene.layout.Pane;

public class MyServices {
    public Pane MyServicePane(Pane leftSidePane) {
        // إنشاء لوحة الشاشة الرئيسية
        Pane ServicePane = new Pane();
        ServicePane.setPrefSize(925, 784);
        ServicePane.setStyle("-fx-background-color: #00f651;-fx-border-radius: 15px;-fx-background-radius: 15px");

        // إضافة لوحة الشاشة الرئيسية إلى لوحة الجانب الأيسر
        leftSidePane.getChildren().add(ServicePane);
        return leftSidePane;
    }
}
