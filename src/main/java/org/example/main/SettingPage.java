package org.example.main;

import javafx.scene.layout.Pane;

public class SettingPage {
    public Pane SettingPane(Pane leftSidePane) {
        // إنشاء لوحة الشاشة الرئيسية
        Pane SettingPane = new Pane();
        SettingPane.setPrefSize(925, 784);
        SettingPane.setStyle("-fx-background-color: #ead400;-fx-border-radius: 15px;-fx-background-radius: 15px");

        // إضافة لوحة الشاشة الرئيسية إلى لوحة الجانب الأيسر
        leftSidePane.getChildren().add(SettingPane);
        return leftSidePane;
    }
}
