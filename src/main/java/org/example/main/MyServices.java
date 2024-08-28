package org.example.main;

import javafx.scene.layout.Pane;

public class MyServices {
    public Pane MyServicePane(Pane leftSidePane) {
        leftSidePane.getChildren().clear();

        // إنشاء لوحة الشاشة الرئيسية
        Pane ServicePane = new Pane();
        ServicePane.setPrefSize(934, 784);
        ServicePane.setId("ServicePane");

        // إضافة لوحة الشاشة الرئيسية إلى لوحة الجانب الأيسر
        leftSidePane.getChildren().add(ServicePane);
        return leftSidePane;
    }
}
