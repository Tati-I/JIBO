package org.example.main;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

public class PersonalHomePage extends Application {

    private static final String RESOURCES_PATH = "/Pictures/";
    private static final String CSS_PATH = "/styles/style.css";

    private Pane createRightSideBar() {
        Pane rightSideBar = new Pane();
        rightSideBar.setId("rightSideBar");
        rightSideBar.setPrefSize(275,784);
        rightSideBar.setLayoutX(927);
        rightSideBar.setLayoutY(-1);

        Button homeBtn = createRightSideBarButton("الصفحة الرئيسية", 200);
        Button myServicesBtn = createRightSideBarButton("خدماتي",  300);
        Button profileBtn = createRightSideBarButton("ملفي الشخصي",  350);
        Button settingsBtn = createRightSideBarButton("إعدادات",  400);

        Button requestServiceBtn = new Button("طلب خدمة");
        requestServiceBtn.setId("requestServiceBtn");
        requestServiceBtn.setPrefSize(200, 30);
        requestServiceBtn.setLayoutX(37.5);
        requestServiceBtn.setLayoutY(250);

        Button logoutBtn = new Button("تسجيل الخروج");
        logoutBtn.setPrefSize(100, 30);
        logoutBtn.setLayoutX(137.5);
        logoutBtn.setLayoutY(690);

        Image logo1 = loadImage("logo1.png");

        ImageView logoImageView = new ImageView(logo1);
        logoImageView.setFitHeight(250);
        logoImageView.setFitWidth(250);
        logoImageView.setLayoutX(12);
        logoImageView.setLayoutY(-40);

        rightSideBar.getChildren().addAll(homeBtn, myServicesBtn, profileBtn, settingsBtn, logoutBtn, requestServiceBtn, logoImageView);

        return rightSideBar;
    }

    private Button createRequestButton() {
        Button requestButton = new Button("أطلب الاَن");
        requestButton.setId("requestButton");
        requestButton.setPrefSize(130, 110);

        DropShadow shadow = new DropShadow(4.0, 4.0, 4.0, Color.rgb(255, 255, 255, 0.5));
        requestButton.setEffect(shadow);

        requestButton.setOnMouseEntered(_ -> {
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(30), requestButton);
            scaleUp.setToX(0.9);
            scaleUp.setToY(0.9);
            scaleUp.play();
        });

        requestButton.setOnMouseExited(_ -> {
            ScaleTransition scaleDown = new ScaleTransition(Duration.millis(30), requestButton);
            scaleDown.setToX(1);
            scaleDown.setToY(1);
            scaleDown.play();
        });

        return requestButton;
    }

    private Button createRightSideBarButton(String name, double y) {
        Button rightSideBarButton = new Button(name);
        rightSideBarButton.setId("rightSideBarButton");
        rightSideBarButton.setPrefSize(200, 30);
        rightSideBarButton.setLayoutX(37.5);
        rightSideBarButton.setLayoutY(y);
        return rightSideBarButton;
    }

    private Pane createServicesPane(String title, String price, Image image, double y) {
        Pane pane = new Pane();
        pane.setPrefSize(680, 110);
        pane.setLayoutX(120);
        pane.setLayoutY(y);
        pane.setStyle("-fx-background-color: #ffffff; -fx-border-color: #d2d2d2; -fx-border-radius: 10px; -fx-background-radius: 10");

        Label titleLabel = new Label(title);
        titleLabel.setId("titleLabel");
        titleLabel.setPrefWidth(300); // تحديد عرض ثابت للعنوان لتسهيل المحاذاة
        titleLabel.setLayoutX(250);
        titleLabel.setLayoutY(10);
        titleLabel.setAlignment(Pos.CENTER_RIGHT); // محاذاة النص إلى اليمين
        titleLabel.setTextAlignment(TextAlignment.RIGHT); // محاذاة النص داخل الـ Label

        Label priceLabel = new Label(price);
        priceLabel.setId("priceLabel");
        priceLabel.setLayoutX(520);
        priceLabel.setLayoutY(40);

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(110);
        imageView.setFitWidth(110);
        imageView.setLayoutX(578);
        imageView.setLayoutY(0);

        Button requestButton = createRequestButton();

        pane.getChildren().addAll(titleLabel, priceLabel, imageView, requestButton);
        return pane;
    }
    @Override
    public void start(Stage primaryStage) {
        Pane rightSideBar = createRightSideBar();

        Image electric = loadImage("worker.png");
        Image tailor = loadImage("worker.png");
        Image water = loadImage("worker.png");
        Image woodmen = loadImage("worker.png");
        Image logo2 = loadImage("logo2.png");
        Image accountPhoto = loadImage("me.png");

        Pane electricPane = createServicesPane("كهربائي", "15$", electric, 200);
        Pane waterPane = createServicesPane("سبّاك", "30$", water,  470);
        Pane tailorPane = createServicesPane("خياط", "45$", tailor,  330);
        Pane woodmenPane = createServicesPane("نجار", "28$", woodmen, 610);

        Line line = new Line(100, 180, 840, 180);
        line.setStroke(Color.web("d2d2d2"));

        Label trendService = new Label("الخدمات الشائعة");
        trendService.setId("trendService");
        trendService.setLayoutX(505);
        trendService.setLayoutY(115);

        TextField serviceSearch = new TextField("البحث عن خدمة...");
        serviceSearch.setId("serviceSearch");
        serviceSearch.setLayoutX(120);
        serviceSearch.setLayoutY(140);
        serviceSearch.setPrefSize(200, 30);

        ImageView accountPhotoImageView = new ImageView(accountPhoto);
        accountPhotoImageView.setFitHeight(40);
        accountPhotoImageView.setFitWidth(40);
        accountPhotoImageView.setStyle("-fx-arc-height: 10;-fx-arc-width: 10");

        Button goToProfile = new Button("", accountPhotoImageView);
        goToProfile.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        goToProfile.setPrefSize(70, 70);
        goToProfile.setLayoutX(120);
        goToProfile.setLayoutY(15);

        Button nowService = new Button("خدماتي الحالية");
        nowService.setPrefSize(100, 30);
        nowService.setLayoutX(750);
        nowService.setLayoutY(10);

        Button previousService = new Button("خدماتي السابقة");
        previousService.setPrefSize(100, 30);
        previousService.setLayoutX(630);
        previousService.setLayoutY(10);

        Button myRate = new Button("تقييماتي");
        myRate.setPrefSize(100, 30);
        myRate.setLayoutX(510);
        myRate.setLayoutY(10);

        ImageView logoImageView2 = new ImageView(logo2);
        logoImageView2.setFitHeight(250);
        logoImageView2.setFitWidth(250);
        logoImageView2.setLayoutX(-50);
        logoImageView2.setLayoutY(-60);

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.setPrefSize(1200,780);
        mainLayout.setStyle("-fx-background-color: #ffffff;");
        mainLayout.getChildren().addAll( rightSideBar, myRate, previousService, nowService, goToProfile,
                logoImageView2, serviceSearch, trendService, line, electricPane, waterPane, tailorPane, woodmenPane);
        Scene scene = new Scene(mainLayout);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(CSS_PATH)).toExternalForm());

        primaryStage.setTitle("Jibo");
        primaryStage.getIcons().add(loadImage("icon.png"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private Image loadImage(String imageName) {
        return new Image(Objects.requireNonNull(getClass().getResource(RESOURCES_PATH + imageName)).toExternalForm());
    }

    public static void main(String[] args) {
        launch(args);
    }
}