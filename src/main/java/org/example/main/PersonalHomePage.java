package org.example.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.Objects;

public class PersonalHomePage extends Application {

    public static Pane rightSideBar;

    public static void rightSideBar() {
        rightSideBar = new Pane();
        rightSideBar.setId("rightSideBar");
        rightSideBar.setPrefWidth(275);
        rightSideBar.setPrefHeight(784);
        rightSideBar.setLayoutX(927);
        rightSideBar.setLayoutY(-1);

    }
    public static Button requestButton(double x, double y) {
        Button requestButton = new Button("أطلب الاَن");
        requestButton.setId("requestButton");
        requestButton.setPrefSize(130,110);
        requestButton.setLayoutX(x);
        requestButton.setLayoutY(y);
        return requestButton;
    }

    public static Pane servicesPane(String title, String price, double x, double y) {
        Pane pane = new Pane();
        pane.setPrefSize(400, 110);
        pane.setLayoutX(x);
        pane.setLayoutY(y);
        pane.setStyle("-fx-background-color: #4e79de; -fx-border-color: #4e79de;  -fx-border-radius: 10px; -fx-background-radius: 10");

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-text-fill: #000000;");
        titleLabel.setLayoutX(20);  // Adjusted positions
        titleLabel.setLayoutY(20);

        Label priceLabel = new Label(price);
        priceLabel.setStyle("-fx-text-fill: #000000;");
        priceLabel.setLayoutX(20);  // Adjusted positions
        priceLabel.setLayoutY(50);




        pane.getChildren().addAll(titleLabel, priceLabel);

        return pane;
    }

    @Override
    public void start(Stage primaryStage) {
        rightSideBar();

        Image electric = new Image(getClass().getResource("/Pictures/Electric.jpeg").toExternalForm());
        Image ironmen = new Image(getClass().getResource("/Pictures/ironmen.jpeg").toExternalForm());
        Image water = new Image(getClass().getResource("/Pictures/watermen.jpeg").toExternalForm());
        Image woodmen  = new Image(getClass().getResource("/Pictures/wood.jpeg").toExternalForm());



        ImageView electricImageView = new ImageView(electric);
        electricImageView.setFitHeight(110);
        electricImageView.setFitWidth(110);
        electricImageView.setLayoutX(651);
        electricImageView.setLayoutY(200);

        ImageView ironmenImageView = new ImageView(ironmen);
        ironmenImageView.setFitHeight(110);
        ironmenImageView.setFitWidth(110);
        ironmenImageView.setLayoutX(651);
        ironmenImageView.setLayoutY(330);

        ImageView waterImageView = new ImageView(water);
        waterImageView.setFitHeight(110);
        waterImageView.setFitWidth(110);
        waterImageView.setLayoutX(651);
        waterImageView.setLayoutY(470);

        ImageView woodmenImageView = new ImageView(woodmen);
        woodmenImageView.setFitHeight(110);
        woodmenImageView.setFitWidth(110);
        woodmenImageView.setLayoutX(651);
        woodmenImageView.setLayoutY(610);



        Pane electricmenService = servicesPane("Electric Service", "123", 250, 200);
        Pane woodmenService = servicesPane("Woodmen Service", "123", 250, 330);
        Pane ironmenService = servicesPane("Ironmen Service", "123", 250, 470);
        Pane watermenService = servicesPane("Watermen Service", "123", 250, 610);

        Line line = new Line();
        line.setStartX(100);
        line.setEndX(780);
        line.setStartY(180);
        line.setEndY(180);
        line.setStroke(Color.BLACK);

        Label trendService = new Label("الخدمات الشائعة");
        trendService.setStyle("-fx-text-fill: #000000;-fx-font-size: 44;");
        trendService.setLayoutX(485);
        trendService.setLayoutY(120);

        TextField serviceSearch = new TextField("البحث عن خدمة...");
        serviceSearch.setStyle("-fx-text-fill: #000000;");
        serviceSearch.setLayoutX(120);
        serviceSearch.setLayoutY(140);
        serviceSearch.setPrefSize(200,30);

        Button goToProfile = new Button("صورة للملف الشخصي");
        goToProfile.setPrefSize(70,70);
        goToProfile.setLayoutX(140);
        goToProfile.setLayoutY(0);

        Button nowService = new Button("خدماتي الحالية");
        nowService.setPrefSize(100,30);
        nowService.setLayoutX(750);
        nowService.setLayoutY(10);

        Button previosService = new Button("خدماتي السابقة");
        previosService.setPrefSize(100,30);
        previosService.setLayoutX(630);
        previosService.setLayoutY(10);

        Button myRate = new Button("تقييماتي");
        myRate.setPrefSize(100,30);
        myRate.setLayoutX(510);
        myRate.setLayoutY(10);







        Label logo = new Label("LOGO");
        logo.setMinSize(100, 100);
        logo.setStyle("-fx-background-color: white; -fx-alignment: center;");
        logo.setLayoutX(87.5);
        logo.setLayoutY(50);

        Label logo2 = new Label("LOGO");
        logo2.setMinSize(70, 70);
        logo2.setStyle("-fx-background-color: white; -fx-alignment: center;");
        logo2.setLayoutX(50);
        logo2.setLayoutY(0);

        Button homeBtn = new Button("الصفحة الرئيسية");
        homeBtn.setPrefSize(200, 30);
        homeBtn.setLayoutX(37.5);
        homeBtn.setLayoutY(200);

        Button requestServiceBtn = new Button("طلب خدمة");
        requestServiceBtn.setPrefSize(200, 30);
        requestServiceBtn.setLayoutX(37.5);
        requestServiceBtn.setLayoutY(250);

        Button myServicesBtn = new Button("خدماتي");
        myServicesBtn.setPrefSize(200, 30);
        myServicesBtn.setLayoutX(37.5);
        myServicesBtn.setLayoutY(300);

        Button profileBtn = new Button("ملفي الشخصي");
        profileBtn.setPrefSize(200, 30);
        profileBtn.setLayoutX(37.5);
        profileBtn.setLayoutY(350);

        Button settingsBtn = new Button("إعدادات");
        settingsBtn.setPrefSize(200, 30);
        settingsBtn.setLayoutX(37.5);
        settingsBtn.setLayoutY(400);

        Button logoutBtn = new Button("تسجيل الخروج");
        logoutBtn.setPrefSize(100, 30);
        logoutBtn.setLayoutX(137.5);
        logoutBtn.setLayoutY(690);

        rightSideBar.getChildren().addAll(logo, homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn, logoutBtn);

        Pane mainLayout = new Pane(rightSideBar,electricmenService,myRate,previosService,nowService,goToProfile,logo2,serviceSearch,trendService,woodmenService,watermenService,ironmenService,electricImageView,woodmenImageView,waterImageView,ironmenImageView,requestButton(120,200),requestButton(120,330),requestButton(120,470),requestButton(120,610),line);

        Scene scene = new Scene(mainLayout, 1200, 780);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/style.css")).toExternalForm());

        primaryStage.setTitle("Jibo");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
