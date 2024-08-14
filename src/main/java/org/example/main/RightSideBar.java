package org.example.main;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Objects;

public class RightSideBar {
    private static final String RESOURCES_PATH = "/Pictures/";
    private Pane rightSideBar;
    private Button logoutBtn;

    public RightSideBar() {
        createRightSideBar();
    }

    private void createRightSideBar() {
        rightSideBar = new Pane();
        rightSideBar.setId("rightSideBar");
        rightSideBar.setPrefSize(275, 784);
        rightSideBar.setLayoutX(927);
        rightSideBar.setLayoutY(-1);

        Button homeBtn = createRightSideBarButton("الصفحة الرئيسية", 200);
        Button requestServiceBtn = createRightSideBarButton("طلب خدمة",250);
        Button myServicesBtn = createRightSideBarButton("خدماتي", 300);
        Button profileBtn = createRightSideBarButton("ملفي الشخصي", 350);
        Button settingsBtn = createRightSideBarButton("إعدادات", 400);

        logoutBtn = new Button("تسجيل الخروج");
        logoutBtn.setId("logoutBtn");
        logoutBtn.setPrefSize(100, 30);
        logoutBtn.setLayoutX(137.5);
        logoutBtn.setLayoutY(690);
        logoutBtn.setOnMouseEntered(_ -> createUpAnimateButton(logoutBtn));
        logoutBtn.setOnMouseExited(_ -> createDownAnimateButton(logoutBtn));

        Image logo1 = loadImage("logo1.png");
        ImageView logoImageView = new ImageView(logo1);
        logoImageView.setFitHeight(250);
        logoImageView.setFitWidth(250);
        logoImageView.setLayoutX(12);
        logoImageView.setLayoutY(-40);

        requestServiceBtn.setOnAction(_ -> {
            RequestServicesPane requestServicesPane = new RequestServicesPane();
        });

        rightSideBar.getChildren().addAll(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn, logoutBtn, logoImageView);
    }

    private Button createRightSideBarButton(String name, double y) {
        Button rightSideBarButton = new Button(name);
        rightSideBarButton.setId("rightSideBarButton");
        rightSideBarButton.setPrefSize(200, 30);
        rightSideBarButton.setLayoutX(37.5);
        rightSideBarButton.setLayoutY(y);
        rightSideBarButton.setOnMouseEntered(_ -> rightSideBarButton.setStyle("-fx-background-color: #fff"));
        rightSideBarButton.setOnMouseExited(_ -> rightSideBarButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7);"));
        return rightSideBarButton;
    }

    private void createUpAnimateButton(Button button) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(250), button);
        scaleUp.setToX(0.9);
        scaleUp.setToY(0.9);
        scaleUp.play();
    }

    private void createDownAnimateButton(Button button) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(250), button);
        scaleDown.setToX(1);
        scaleDown.setToY(1);
        scaleDown.play();
    }

    public Image loadImage(String imageName) {
        return new Image(Objects.requireNonNull(getClass().getResource(RESOURCES_PATH + imageName)).toExternalForm());
    }

    public Pane getRightSideBar() {
        return rightSideBar;
    }

    public Button getLogoutBtn() {
        return logoutBtn;
    }
}