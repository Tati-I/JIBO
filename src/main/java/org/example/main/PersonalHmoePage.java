package org.example.main;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.util.Objects;

public class PersonalHomePage  {

    private static final String RESOURCES_PATH = "/Pictures/";
    private static final String CSS_PATH = "/styles/style.css";
    private Window primaryStage;


    private void createUpAnimateButton(Button button){
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(250), button);
        scaleUp.setToX(0.9);
        scaleUp.setToY(0.9);
        scaleUp.play();
    }

    public void logoutSure() {
        Stage logoutStage = new Stage();
        logoutStage.initModality(Modality.APPLICATION_MODAL);
        logoutStage.initOwner(primaryStage);

        Pane logoutPane = new Pane();
        logoutPane.setStyle("-fx-background-color: linear-gradient(#39d0d0 10%, #8723f1 84%);");
        logoutPane.setPrefSize(400, 200);

        Label sureLabel = new Label("هل أنت متأكد؟");
        sureLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-family: Dubai; -fx-font-size: 18;");
        sureLabel.setLayoutX(150);
        sureLabel.setLayoutY(50);

        Button yesButton = new Button("Yes");
        yesButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 10; -fx-background-radius: 10;");
        yesButton.setPrefSize(80, 30);
        yesButton.setLayoutX(100);
        yesButton.setLayoutY(120);

        Button noButton = new Button("No");
        noButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 10; -fx-background-radius: 10;");
        noButton.setPrefSize(80, 30);
        noButton.setLayoutX(220);
        noButton.setLayoutY(120);

        yesButton.setOnMouseEntered(_ -> createUpAnimateButton(yesButton));
        yesButton.setOnMouseExited(_ -> createDownAnimateButton(yesButton));
        noButton.setOnMouseEntered(_ -> createUpAnimateButton(noButton));
        noButton.setOnMouseExited(_ -> createDownAnimateButton(noButton));

        yesButton.setOnAction(_ -> {
            logoutStage.close();
            Platform.exit();

        });

        noButton.setOnAction(_ -> logoutStage.close());

        logoutPane.getChildren().addAll(sureLabel, yesButton, noButton);

        Scene logoutScene = new Scene(logoutPane);
        logoutStage.setScene(logoutScene);
        logoutStage.setTitle("تأكيد تسجيل الخروج");
        logoutStage.setResizable(false);
        logoutStage.show();
    }



    private void createDownAnimateButton(Button button){
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(250), button);
        scaleDown.setToX(1);
        scaleDown.setToY(1);
        scaleDown.play();
    }


    public Pane createRightSideBar() {
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

        requestServiceBtn.setOnMouseEntered(_->createUpAnimateButton(requestServiceBtn));
        requestServiceBtn.setOnMouseExited(_->createDownAnimateButton(requestServiceBtn));

        Button logoutBtn = new Button("تسجيل الخروج");
        logoutBtn.setId("logoutBtn");
        logoutBtn.setPrefSize(100, 30);
        logoutBtn.setLayoutX(137.5);
        logoutBtn.setLayoutY(690);
        logoutBtn.setOnMouseEntered(_->createUpAnimateButton(logoutBtn));
        logoutBtn.setOnMouseExited(_->createDownAnimateButton(logoutBtn));

        logoutBtn.setOnAction(_-> logoutSure());

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

        requestButton.setOnMouseEntered(_ -> createUpAnimateButton(requestButton)
        );

        requestButton.setOnMouseExited(_ -> createDownAnimateButton(requestButton)
        );

        return requestButton;
    }

    private Button createRightSideBarButton(String name, double y) {
        Button rightSideBarButton = new Button(name);
        rightSideBarButton.setId("rightSideBarButton");
        rightSideBarButton.setPrefSize(200, 30);
        rightSideBarButton.setLayoutX(37.5);
        rightSideBarButton.setLayoutY(y);

        rightSideBarButton.setOnMouseEntered(_ -> createUpAnimateButton(rightSideBarButton)
        );

        rightSideBarButton.setOnMouseExited(_ -> createDownAnimateButton(rightSideBarButton)
        );

        return rightSideBarButton;
    }
    private Button createServicesButton(String name, double x) {
        Button servicesButton = new Button(name);
        servicesButton.setId("servicesButton");
        servicesButton.setPrefSize(100, 30);
        servicesButton.setLayoutX(x);
        servicesButton.setLayoutY(10);

        servicesButton.setOnMouseEntered(_ -> createUpAnimateButton(servicesButton));
        servicesButton.setOnMouseExited(_ -> createDownAnimateButton(servicesButton));
        return servicesButton;
    }

    private Pane createServicesPane(String title, String price, Image image, double y) {
        Pane pane = new Pane();
        pane.setPrefSize(680, 110);
        pane.setLayoutX(120);
        pane.setLayoutY(y);

        Label titleLabel = new Label(title);
        titleLabel.setId("titleLabel");
        titleLabel.setPrefWidth(300);
        titleLabel.setLayoutX(250);
        titleLabel.setLayoutY(10);
        titleLabel.setAlignment(Pos.CENTER_RIGHT);
        titleLabel.setTextAlignment(TextAlignment.RIGHT);

        Label priceLabel = new Label(price);
        priceLabel.setId("priceLabel");
        priceLabel.setLayoutX(520);
        priceLabel.setLayoutY(40);

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setLayoutX(578);
        imageView.setLayoutY(5);

        Button requestButton = createRequestButton();

        pane.getChildren().addAll(titleLabel, priceLabel, imageView, requestButton);
        return pane;
    }
    public void start() {
        Stage primaryStage = new Stage();
        Pane rightSideBar = createRightSideBar();

        Image electric = loadImage("worker.png");
        Image tailor = loadImage("worker.png");
        Image water = loadImage("worker.png");
        Image woodmen = loadImage("worker.png");
        Image logo2 = loadImage("logo2.png");
        Image accountPhoto = loadImage("me.png");

        Pane electricPane = createServicesPane("كهربائي", "15$", electric, 200);
        electricPane.setId("electricPane");
        Pane waterPane = createServicesPane("سبّاك", "30$", water,  470);
        waterPane.setId("waterPane");
        Pane tailorPane = createServicesPane("خياط", "45$", tailor,  330);
        tailorPane.setId("tailorPane");
        Pane woodmenPane = createServicesPane("نجار", "28$", woodmen, 610);
        woodmenPane.setId("woodmenPane");

        Line line = new Line(100, 180, 840, 180);
        line.setStroke(Color.web("c0c0c0"));

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

        Circle clip = new Circle(20, 20, 20);
        accountPhotoImageView.setClip(clip);
        accountPhotoImageView.setStyle("-fx-arc-height: 20; -fx-arc-width: 20;");

        Button goToProfile = new Button("", accountPhotoImageView);
        goToProfile.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        goToProfile.setPrefSize(70, 70);
        goToProfile.setLayoutX(120);
        goToProfile.setLayoutY(15);

        Button nowService = createServicesButton("خدماتي الحالية",750);

        Button previousService = createServicesButton("خدماتي السابقة",630);

        Button myRate = createServicesButton("تقييماتي",510);


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
}