package org.example.main;

import auth.User;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.util.Objects;

public class MyServices {
    public Pane MyServicePane(Pane leftSidePane) {
        leftSidePane.getChildren().clear();

        // إنشاء لوحة الشاشة الرئيسية
        Pane ServicePane = new Pane();
        ServicePane.setId("ServicePane");
        ServicePane.prefWidthProperty().bind(leftSidePane.widthProperty());
        ServicePane.prefHeightProperty().bind(leftSidePane.heightProperty());

        // إضافة صورة القمر
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/Moon.png")));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(250);
        imageView.setFitWidth(250);
        imageView.setLayoutX(500);
        imageView.setLayoutY(300);
        ServicePane.getChildren().add(imageView);

        // إنشاء VBox لعرض طلبات الخدمة
        VBox requestsBox = new VBox(10);
        requestsBox.setPadding(new Insets(20));
        requestsBox.setAlignment(Pos.TOP_CENTER);

        // إضافة عنوان
        Label titleLabel = new Label("طلبات الخدمة");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        requestsBox.getChildren().add(titleLabel);
        // إضافة ScrollPane لـ VBox
        ScrollPane scrollPane = new ScrollPane(requestsBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefWidth(400);
        scrollPane.setPrefHeight(500);
        scrollPane.setLayoutX(50);
        scrollPane.setLayoutY(50);

        ServicePane.getChildren().add(scrollPane);

        // إضافة تأثير الظهور التدريجي
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), ServicePane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        // إضافة لوحة الشاشة الرئيسية إلى لوحة الجانب الأيسر
        leftSidePane.getChildren().add(ServicePane);
        return leftSidePane;
    }
}