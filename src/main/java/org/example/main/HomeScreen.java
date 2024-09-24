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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.util.Objects;

public class HomeScreen {
    public VBox rightLayout;

    public Pane RequestHomePane(Pane leftSidePane) {
        leftSidePane.getChildren().clear();

        Pane homePane = new Pane();
        homePane.setId("homePane");

        rightLayout = new VBox();
        rightLayout.setAlignment(Pos.CENTER);

        // Bind width and height of rightLayout to leftSidePane
        rightLayout.prefWidthProperty().bind(leftSidePane.widthProperty());
        rightLayout.prefHeightProperty().bind(leftSidePane.heightProperty());

        // Featured Services
        VBox featuredServices = createFeaturedServices();
        rightLayout.getChildren().addAll(featuredServices);

        // Text
        VBox leftHead = leftLayoutElements();

        VBox leftLayout = new VBox();
        leftLayout.getChildren().addAll(leftHead);
        leftLayout.prefHeightProperty().bind(leftSidePane.heightProperty());
        leftLayout.prefWidthProperty().bind(leftSidePane.widthProperty());

        // Create HBox and add layouts
        HBox layouts = new HBox(20);
        layouts.getChildren().addAll(leftLayout, rightLayout);
        layouts.prefHeightProperty().bind(leftSidePane.heightProperty());
        layouts.prefWidthProperty().bind(leftSidePane.widthProperty());

        // Allow leftLayout to take space according to its content
        HBox.setHgrow(leftLayout, Priority.ALWAYS);
        HBox.setHgrow(rightLayout, Priority.ALWAYS);

        // Add layouts to homePane
        homePane.getChildren().add(layouts);

        // Add fade transition
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), homePane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        leftSidePane.getChildren().add(homePane);
        return leftSidePane;
    }

    private VBox leftLayoutElements() {
        VBox mainContainer = new VBox(20);
        mainContainer.setPadding(new Insets(30, 0, 10, 50));
        mainContainer.setStyle("-fx-background-color: transparent;");

        HBox header = createHeader();
        header.setPadding(new Insets(0, 0, 35, 0));

        Label title = new Label();
        title.setText("Your Work Guide\n");
        title.setStyle("-fx-text-fill: Black;-fx-font-weight: bold;-fx-font-size:20");

        Label title1 = new Label();
        title1.setText("Explore\nmust-see\nservices");
        title1.setStyle("-fx-text-fill: Black;-fx-font-weight: bold;-fx-font-size:60;-fx-font-family: Arial");

        Label title2 = new Label("Escape to Work : Unlock a World of\nservices");
        title2.setStyle("-fx-text-fill: #373737;-fx-font-weight: bold;-fx-font-size: 14");

        VBox titles = new VBox(20);
        titles.getChildren().addAll(title, title1, title2);
        titles.setAlignment(Pos.TOP_LEFT);

        mainContainer.getChildren().addAll(header, titles);
        return mainContainer;
    }

    private HBox createHeader() {
        HBox header = new HBox();
        header.setAlignment(Pos.TOP_LEFT);

        Label welcomeLabel = new Label("Jibo");
        welcomeLabel.setPadding(new Insets(12, 0, 0, 0));
        welcomeLabel.setId("welcomeLabel");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        ImageView logoView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/logoBlack.png"))));
        logoView.setFitHeight(62);
        logoView.setFitWidth(62);

        header.getChildren().addAll(logoView, welcomeLabel);
        return header;
    }

    private VBox createFeaturedServices() {
        VBox featuredBox = new VBox(20);
        featuredBox.setAlignment(Pos.CENTER);
        featuredBox.setPadding(new Insets(30, 50, 10, 0));

        // Large square
        Pane largeSquare = new Pane();
        largeSquare.setPrefSize(480, 400);
        largeSquare.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #cccccc;-fx-background-radius: 15; -fx-border-radius: 15;");
        largeSquare.prefWidthProperty().bind(featuredBox.widthProperty());

        ImageView largeImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/wwa.jpg"))));
        largeImageView.fitWidthProperty().bind(largeSquare.widthProperty());
        largeImageView.fitHeightProperty().bind(largeSquare.heightProperty());
        largeImageView.setOpacity(0.9);

        Rectangle clip = new Rectangle();
        clip.setWidth(largeImageView.getFitWidth());
        clip.setHeight(largeImageView.getFitHeight());
        clip.setArcWidth(30); // Adjust this value to control the roundness of the corners
        clip.setArcHeight(30);

// Bind the clip size to the ImageView size
        clip.widthProperty().bind(largeImageView.fitWidthProperty());
        clip.heightProperty().bind(largeImageView.fitHeightProperty());

// Set the clip to the ImageView
        largeImageView.setClip(clip);

        // Create a button
        Button discoverServicesButton = new Button("اكتشف الخدمات");
        discoverServicesButton.setStyle("-fx-background-color: #e8edf0; -fx-text-fill: #000000;-fx-font-weight: bold; -fx-font-size: 16;-fx-background-radius: 15; -fx-padding: 10 20;");
        HBox.setHgrow(discoverServicesButton, Priority.ALWAYS);

        // Create a label for service request time
        Label serviceTimeLabel = new Label("الوقت لطلب\nخدمة بشكل سهل\nوأحترافي");
        serviceTimeLabel.setStyle("-fx-font-size: 35; -fx-text-fill: black;-fx-font-family: Arial;-fx-font-weight: bold");
        serviceTimeLabel.setTextAlignment(TextAlignment.RIGHT);

        // Position the button and label in the large square
        VBox largeSquareContent = new VBox(155);
        largeSquareContent.setAlignment(Pos.TOP_RIGHT);
        largeSquareContent.setPadding(new Insets(20, 20, 10, 20)); // Add padding to position the button
        largeSquareContent.getChildren().addAll(serviceTimeLabel, discoverServicesButton);
        largeSquareContent.prefWidthProperty().bind(largeSquare.widthProperty());
        largeSquare.getChildren().addAll(largeImageView,largeSquareContent);

        // Top small squares
        HBox smallSquaresBoxTop = new HBox(20);
        smallSquaresBoxTop.setAlignment(Pos.CENTER);
        for (int i = 1; i <= 2; i++) {
            Pane smallSquare = createSmallSquare("مربع " + i);
            smallSquaresBoxTop.getChildren().add(smallSquare);
        }

        VBox smallSquaresVBox = new VBox(10, smallSquaresBoxTop);

        // Bottom small squares
        HBox smallSquaresBoxBottom = new HBox(20);
        smallSquaresBoxBottom.setAlignment(Pos.CENTER);
        for (int i = 3; i <= 4; i++) {
            Pane smallSquare = createSmallSquare("مربع " + i);
            smallSquaresBoxBottom.getChildren().add(smallSquare);
        }

        VBox bottomSquaresVBox = new VBox(10, smallSquaresBoxBottom);

        featuredBox.getChildren().addAll(largeSquare, smallSquaresVBox, bottomSquaresVBox);

        return featuredBox;
    }


    private Pane createSmallSquare(String labelText) {
        Pane smallSquare = new Pane();
        smallSquare.setPrefSize(240, 200);
        smallSquare.setStyle("-fx-background-color: #d0d0d0; -fx-border-color: #aaaaaa; -fx-border-radius: 10;");

        Label smallLabel = new Label(labelText);
        smallLabel.setStyle("-fx-font-size: 16; -fx-text-fill: black;");
        smallLabel.setAlignment(Pos.CENTER);
        smallLabel.setPrefSize(240, 200);
        smallSquare.getChildren().add(smallLabel);
        return smallSquare;
    }
}
