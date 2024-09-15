package org.example.main;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.util.Objects;

public class HomeScreen {
    public Pane RequestHomePane(Pane leftSidePane) {
        leftSidePane.getChildren().clear();

        Pane homePane = new Pane();
        homePane.setId("homePane");

        VBox mainContainer = new VBox(20);
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setPadding(new Insets(30, 50, 30, 50));
        mainContainer.prefWidthProperty().bind(homePane.widthProperty());
        mainContainer.prefHeightProperty().bind(homePane.heightProperty());

        // text
        HBox header = createHeader();

        // Quick Actions
        GridPane quickActions = createQuickActions();

        // Featured Services
        VBox featuredServices = createFeaturedServices();

        mainContainer.getChildren().addAll(header, quickActions, featuredServices);
        homePane.getChildren().add(mainContainer);
        homePane.prefHeightProperty().bind(leftSidePane.heightProperty());
        homePane.prefWidthProperty().bind(leftSidePane.widthProperty());

        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), mainContainer);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        leftSidePane.getChildren().add(homePane);
        return leftSidePane;
    }

    private HBox createHeader() {
        HBox header = new HBox(10);
        header.setAlignment(Pos.CENTER);


        Label welcomeLabel = new Label("مرحباً بك في Jibo");
        welcomeLabel.setId("welcomeLabel");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));

        ImageView logoView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/logoBlack.png"))));


        logoView.setFitHeight(75);
        logoView.setFitWidth(75);

        header.getChildren().addAll(logoView, welcomeLabel);
        return header;
    }

    private GridPane createQuickActions() {
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setAlignment(Pos.CENTER);

        String[][] actions = {
                {"طلب خدمة", "request_services.png"},
                {"خدماتي", "services.png"},
                {"الملف الشخصي", "personal.png"},
                {"الإعدادات", "settings.png"}
        };

        for (int i = 0; i < actions.length; i++) {
            VBox actionBox = createActionBox(actions[i][0], actions[i][1]);
            grid.add(actionBox, i % 2, i / 2);
        }

        return grid;
    }

    private VBox createActionBox(String title, String iconName) {
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(60));
        box.setStyle("-fx-background-color: white; -fx-background-radius: 15;");
        box.setPrefSize(200, 150);

        ImageView icon = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/" + iconName))));
        icon.setFitHeight(50);
        icon.setFitWidth(50);

        Label label = new Label(title);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        box.getChildren().addAll(icon, label);

        DropShadow shadow = new DropShadow();
        shadow.setRadius(10.0);
        shadow.setOffsetX(3.0);
        shadow.setOffsetY(3.0);
        shadow.setColor(Color.color(0.4, 0.4, 0.4, 0.2));
        box.setEffect(shadow);

        box.setOnMouseEntered(_ -> {
            box.setStyle("-fx-background-color: #f8f8f8; -fx-background-radius: 15; -fx-cursor: hand;");
            box.setScaleX(1.05);
            box.setScaleY(1.05);
        });
        box.setOnMouseExited(_ -> {
            box.setStyle("-fx-background-color: white; -fx-background-radius: 15;");
            box.setScaleX(1);
            box.setScaleY(1);
        });

        return box;
    }

    private VBox createFeaturedServices() {
        VBox featuredBox = new VBox(20);
        featuredBox.setAlignment(Pos.CENTER);

        Label featuredLabel = new Label("الخدمات المميزة");
        featuredLabel.setStyle("-fx-font-size: 20");
        featuredLabel.setId("featuredLabel");

        HBox servicesBox = new HBox(20);
        servicesBox.setAlignment(Pos.CENTER);

        String[] services = {"كهربائي", "سبّاك", "خياط", "نجار"};
        for (String service : services) {
            Button serviceButton = new Button(service);
            serviceButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 20;");
            serviceButton.setOnMouseEntered(_ -> serviceButton.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 20; -fx-cursor: hand;"));
            serviceButton.setOnMouseExited(_ -> serviceButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 20;"));
            servicesBox.getChildren().add(serviceButton);
        }

        featuredBox.getChildren().addAll(featuredLabel, servicesBox);
        return featuredBox;
    }
}