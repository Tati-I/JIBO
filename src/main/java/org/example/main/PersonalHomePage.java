package org.example.main;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PersonalHomePage extends Application {

    private static Pane rightSideBar;

    public static void rightSideBar() {
        rightSideBar = new Pane();
        rightSideBar.setPrefWidth(275);
        rightSideBar.setPrefHeight(784);
        rightSideBar.setLayoutX(927);
        rightSideBar.setLayoutY(-1);
        rightSideBar.setStyle("-fx-background-color: #4e79de; -fx-border-color: #ffffff; -fx-border-width: 1px;");

    }

    public void start(Stage primaryStage) {
        rightSideBar();




        Label logo = new Label("LOGO");
        logo.setMinSize(100, 100);
        logo.setStyle("-fx-background-color: white; -fx-alignment: center;");
        logo.setLayoutX(87.5);
        logo.setLayoutY(50);


        Button homeBtn = new Button("الصفحة الرئيسية");
        homeBtn.setPrefSize(200,30);
        homeBtn.setLayoutX(37.5);
        homeBtn.setLayoutY(200);


        Button requestServiceBtn = new Button("طلب خدمة");
        requestServiceBtn.setPrefSize(200,30);
        requestServiceBtn.setLayoutX(37.5);
        requestServiceBtn.setLayoutY(250);


        Button myServicesBtn = new Button("خدماتي");
        myServicesBtn.setPrefSize(200,30);
        myServicesBtn.setLayoutX(37.5);
        myServicesBtn.setLayoutY(300);


        Button profileBtn = new Button("ملفي الشخصي");
        profileBtn.setPrefSize(200,30);
        profileBtn.setLayoutX(37.5);
        profileBtn.setLayoutY(350);


        Button settingsBtn = new Button("إعدادات");
        settingsBtn.setPrefSize(200,30);
        settingsBtn.setLayoutX(37.5);
        settingsBtn.setLayoutY(400);


        Button logoutBtn = new Button("تسجيل الخروج");
        logoutBtn.setPrefSize(100,30);
        logoutBtn.setLayoutX(137.5);
        logoutBtn.setLayoutY(700);

        rightSideBar.getChildren().addAll(logo, homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn, logoutBtn);





        Pane mainLayout = new Pane(rightSideBar);

        Scene scene = new Scene(mainLayout, 1200, 780);
        primaryStage.setTitle("Jibo");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
