package org.example.main;

import javafx.scene.layout.Pane;

public class HomeScreen {
    // دالة لإنشاء وإرجاع لوحة الشاشة الرئيسية
    public Pane RequestHomePane(Pane leftSidePane) {
        // إنشاء لوحة الشاشة الرئيسية
        Pane HomePane = new Pane();
        HomePane.setPrefSize(925, 784);
        HomePane.setStyle("-fx-background-color: #6e8776;-fx-background-radius: 0 20 20 0");

        // إضافة لوحة الشاشة الرئيسية إلى لوحة الجانب الأيسر
        leftSidePane.getChildren().add(HomePane);
        return leftSidePane;
    }
}