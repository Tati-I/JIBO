package org.example.main;

import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class RequestServicesPane {
    private static final String CSS_PATH = "/styles/style.css";
    private final Pane contentPane;

    public RequestServicesPane() {
        this.contentPane = new Pane();
        contentPane.setPrefSize(925, 780);
        showServicesPage();
    }

    private void showServicesPage() {
        Line line = new Line(100, 180, 840, 180);
        line.setStroke(Color.web("c0c0c0"));

        Label trendService = new Label("الخدمات الشائعة");
        trendService.setId("trendService");
        trendService.setLayoutX(537);
        trendService.setLayoutY(115);

        TextField serviceSearch = new TextField();
        serviceSearch.setPromptText("البحث عن خدمة...");
        serviceSearch.setAlignment(Pos.CENTER_RIGHT);
        serviceSearch.setLayoutX(120);
        serviceSearch.setLayoutY(140);
        serviceSearch.setPrefSize(230, 30);
        serviceSearch.setId("serviceSearch");

        Circle clip = new Circle(20, 20, 20);

        Button goToProfile = new Button("");
        goToProfile.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 10px; -fx-background-radius: 10px;-fx-cursor: hand;");
        goToProfile.setPrefSize(50, 50);
        goToProfile.setLayoutX(120);
        goToProfile.setLayoutY(15);

        Button nowService = createServicesButton("خدماتي الحالية", 750);
        Button previousService = createServicesButton("خدماتي السابقة", 640);
        Button myRate = createServicesButton("تقييماتي", 530);

        RightSideBar rightSideBar = new RightSideBar();

        Pane electricPane = createServicesPane("كهربائي", "15$", rightSideBar.loadImage("worker.png"), 200);
        electricPane.setId("electricPane");
        Pane waterPane = createServicesPane("سبّاك", "30$", rightSideBar.loadImage("worker.png"), 470);
        waterPane.setId("waterPane");
        Pane tailorPane = createServicesPane("خياط", "45$", rightSideBar.loadImage("worker.png"), 330);
        tailorPane.setId("tailorPane");
        Pane woodmenPane = createServicesPane("نجار", "28$", rightSideBar.loadImage("worker.png"), 610);
        woodmenPane.setId("woodmenPane");

        contentPane.getChildren().addAll(line, trendService, serviceSearch, goToProfile, nowService, previousService, myRate,
                electricPane, waterPane, tailorPane, woodmenPane);
    }

    private Button createServicesButton(String name, double x) {
        Button servicesButton = new Button(name);
        servicesButton.setId("servicesButton");
        servicesButton.setPrefSize(90, 30);
        servicesButton.setLayoutX(x);
        servicesButton.setLayoutY(15);
        servicesButton.setOnMouseEntered(_ -> servicesButton.setStyle("-fx-background-color: linear-gradient( #9db4dc 10%, #7986f4 84%);"));
        servicesButton.setOnMouseExited(_ -> servicesButton.setStyle("-fx-background-color: linear-gradient( #6daaee 10%, #7986f4 84%);"));

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

    private Button createRequestButton() {
        Button requestButton = new Button("أطلب الاَن");
        requestButton.setId("requestButton");
        requestButton.setPrefSize(130, 110);

        requestButton.setOnMouseEntered(_ -> createUpAnimateButton(requestButton));
        requestButton.setOnMouseExited(_ -> createDownAnimateButton(requestButton));

        return requestButton;
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

    public Pane getContentPane() {
        return contentPane;
    }
}