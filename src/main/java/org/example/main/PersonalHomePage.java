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
    public static Button rightSideBarButton(String name,double x, double y) {
        Button rightSideBarButton = new Button(name);
        rightSideBarButton.setId("rightSideBarButton");
        rightSideBarButton.setPrefSize(200,30);
        rightSideBarButton.setLayoutX(x);
        rightSideBarButton.setLayoutY(y);
        return rightSideBarButton;
    }

    public static Pane servicesPane(String title, String price, double x, double y) {
        Pane pane = new Pane();
        pane.setPrefSize(400, 110);
        pane.setLayoutX(x);
        pane.setLayoutY(y);
        pane.setStyle("-fx-background-color: #ffffff; -fx-border-color: #d2d2d2;  -fx-border-radius: 10px; -fx-background-radius: 10");

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
        Image woodmen  = new Image(getClass().getResource("/Pictures/wood.png").toExternalForm());
        Image logo1 = new Image(getClass().getResource("/Pictures/logo1.png").toExternalForm());
        Image logo2 = new Image(getClass().getResource("/Pictures/logo2.png").toExternalForm());
        Image accountPhoto = new Image(getClass().getResource("/Pictures/accountPhoto.png").toExternalForm());



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
        woodmenImageView.setLayoutX(645);
        woodmenImageView.setLayoutY(610);

        ImageView logoImageView = new ImageView(logo1);
        logoImageView.setFitHeight(250);
        logoImageView.setFitWidth(250);
        logoImageView.setLayoutX(12);
        logoImageView.setLayoutY(-40);

        ImageView logoImageView2 = new ImageView(logo2);
        logoImageView2.setFitHeight(250);
        logoImageView2.setFitWidth(250);
        logoImageView2.setLayoutX(-50);
        logoImageView2.setLayoutY(-60);

        ImageView accountPhotoImageView = new ImageView(accountPhoto);
        accountPhotoImageView.setFitHeight(32);
        accountPhotoImageView.setFitWidth(32);



        Pane electricmenService = servicesPane("Electric Service", "123", 250, 200);
        Pane woodmenService = servicesPane("Watermen Service", "123", 250, 330);
        Pane ironmenService = servicesPane("Ironmen Service", "123", 250, 470);
        Pane watermenService = servicesPane("Woodmen Service", "123", 250, 610);

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

        Button goToProfile = new Button("",accountPhotoImageView);
        goToProfile.setStyle("-fx-background-color: #ffffff");
        goToProfile.setPrefSize(70,70);
        goToProfile.setLayoutX(120);
        goToProfile.setLayoutY(15);

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











        Button homeBtn = rightSideBarButton("الصفحة الرئيسية",37.5,200);

        Button requestServiceBtn = rightSideBarButton("طلب خدمة",37.5,250);

        Button myServicesBtn = rightSideBarButton("خدماتي",37.5,300);

        Button profileBtn = rightSideBarButton("ملفي الشخصي",37.5,350);

        Button settingsBtn = rightSideBarButton("إعدادات",37.5,400);

        Button logoutBtn = new Button("تسجيل الخروج");
        logoutBtn.setPrefSize(100, 30);
        logoutBtn.setLayoutX(137.5);
        logoutBtn.setLayoutY(690);

        rightSideBar.getChildren().addAll(logoImageView, homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn, logoutBtn);

        Pane mainLayout = new Pane(rightSideBar,electricmenService,myRate,previosService,nowService,goToProfile,logoImageView2,serviceSearch,trendService,woodmenService,watermenService,ironmenService,electricImageView,woodmenImageView,waterImageView,ironmenImageView,requestButton(140,200),requestButton(120,330),requestButton(120,470),requestButton(120,610),line);

        mainLayout.setStyle("-fx-background-color: #ffffff;");
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
