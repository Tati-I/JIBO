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
        homePane.prefWidthProperty().bind(leftSidePane.widthProperty());
        homePane.prefHeightProperty().bind(leftSidePane.heightProperty());
        homePane.setId("homePane");

        rightLayout = new VBox();
        rightLayout.setAlignment(Pos.CENTER);

        // Bind width and height of rightLayout to leftSidePane
        rightLayout.prefWidthProperty().bind(homePane.widthProperty());
        rightLayout.prefHeightProperty().bind(homePane.heightProperty());

        // Featured Services
        VBox featuredServices = createFeaturedServices();
        featuredServices.prefWidthProperty().bind(rightLayout.widthProperty());
        featuredServices.prefHeightProperty().bind(rightLayout.heightProperty());
        rightLayout.getChildren().addAll(featuredServices);

        // Text
        VBox leftLayout = new VBox();

        VBox leftHead = leftLayoutElements();
        leftHead.prefWidthProperty().bind(leftLayout.widthProperty());
        leftHead.prefHeightProperty().bind(leftLayout.heightProperty());

        leftLayout.getChildren().addAll(leftHead);
        leftLayout.prefHeightProperty().bind(homePane.heightProperty());
        leftLayout.prefWidthProperty().bind(homePane.widthProperty());

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
        header.prefHeightProperty().bind(mainContainer.heightProperty().multiply(0.4));
        header.prefWidthProperty().bind(mainContainer.widthProperty());

        VBox titles = new VBox(20);

        Label title = new Label();
        title.setText("Your Work Guide\n");
        title.setStyle("-fx-text-fill: Black;-fx-font-weight: bold;-fx-font-size:20");

        Label title1 = new Label();
        title1.setText("Explore\nmust-see\nservices");
        title1.setStyle("-fx-text-fill: Black;-fx-font-weight: bold;-fx-font-size:60;-fx-font-family: Arial");

        Label title2 = new Label("Escape to Work : Unlock a World of\nservices");
        title2.setStyle("-fx-text-fill: #373737;-fx-font-weight: bold;-fx-font-size: 14");

        titles.getChildren().addAll(title, title1, title2);
        titles.prefHeightProperty().bind(mainContainer.heightProperty());
        titles.prefWidthProperty().bind(mainContainer.widthProperty());
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

        HBox content = new HBox();
        content.setAlignment(Pos.TOP_LEFT);
        content.getChildren().addAll(logoView, welcomeLabel);
        content.prefHeightProperty().bind(header.heightProperty());
        content.prefWidthProperty().bind(header.widthProperty());

        header.getChildren().addAll(content);
        return header;
    }

    private VBox createFeaturedServices() {
        VBox featuredBox = new VBox(20);
        featuredBox.setAlignment(Pos.CENTER);
        featuredBox.setPadding(new Insets(30, 50, 10, 0));

        // Large square
        Pane largeSquare = new Pane();
        largeSquare.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #cccccc;-fx-background-radius: 15; -fx-border-radius: 15;");
        largeSquare.prefWidthProperty().bind(featuredBox.widthProperty());
        largeSquare.prefHeightProperty().bind(featuredBox.heightProperty().multiply(1.2));

        ImageView largeImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/aaaa.jpg"))));
        largeImageView.fitWidthProperty().bind(largeSquare.widthProperty());
        largeImageView.fitHeightProperty().bind(largeSquare.heightProperty());

        Rectangle clip = new Rectangle();
        clip.setWidth(largeImageView.getFitWidth());
        clip.setHeight(largeImageView.getFitHeight());
        clip.setArcWidth(30); // Adjust this value to control the roundness of the corners
        clip.setArcHeight(30);

        clip.widthProperty().bind(largeImageView.fitWidthProperty());
        clip.heightProperty().bind(largeImageView.fitHeightProperty());

        largeImageView.setClip(clip);

        DropShadow shadow = new DropShadow();
        shadow.setRadius(5.0);
        shadow.setColor(Color.GRAY);
        largeSquare.setEffect(shadow);

        // Create a button
        Button discoverServicesButton = new Button("اكتشف الخدمات");
        discoverServicesButton.setStyle("-fx-background-color: #e8edf0; -fx-text-fill: #000000;-fx-font-weight: bold; -fx-font-size: 16;-fx-background-radius: 15; -fx-padding: 10 20;");
        HBox.setHgrow(discoverServicesButton, Priority.ALWAYS);

        // Create a label for service request time
        Label serviceTimeLabel = new Label("الوقت لطلب\nخدمة بشكل سهل\nوأحترافي");
        serviceTimeLabel.setStyle("-fx-font-size: 35; -fx-text-fill: white;-fx-font-family: Arial;-fx-font-weight: bold");
        serviceTimeLabel.setTextAlignment(TextAlignment.RIGHT);

        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/logo1.png"))));
        imageView.setFitHeight(62);
        imageView.setFitWidth(62);

        VBox largeSquareContent = new VBox();
        largeSquareContent.setPadding(new Insets(20, 20, 20, 20)); // Add padding to position elements
        largeSquareContent.prefWidthProperty().bind(largeSquare.widthProperty());
        largeSquareContent.prefHeightProperty().bind(largeSquare.heightProperty());

        HBox serviceTimeLabelHbox = new HBox();
        serviceTimeLabelHbox.setAlignment(Pos.TOP_RIGHT); // Align text to the right
        serviceTimeLabelHbox.getChildren().addAll(imageView, serviceTimeLabel);

        HBox.setHgrow(serviceTimeLabel, Priority.ALWAYS);

        largeSquareContent.getChildren().add(serviceTimeLabelHbox);

        HBox buttonContainer = new HBox();
        buttonContainer.setAlignment(Pos.BOTTOM_RIGHT); // Align button to bottom-left
        buttonContainer.getChildren().add(discoverServicesButton);
        VBox.setVgrow(buttonContainer, Priority.ALWAYS); // Ensure button sticks to bottom

        largeSquareContent.getChildren().add(buttonContainer);

        largeSquare.getChildren().addAll(largeImageView, largeSquareContent);

        // Top small squares
        HBox smallSquaresBoxTop = new HBox(20);
        smallSquaresBoxTop.setAlignment(Pos.CENTER);
        smallSquaresBoxTop.prefWidthProperty().bind(featuredBox.widthProperty());
        smallSquaresBoxTop.prefHeightProperty().bind(featuredBox.heightProperty());

        Pane smallSquare1 = createSmallSquare1();
        smallSquare1.prefWidthProperty().bind(smallSquaresBoxTop.widthProperty());

        Pane smallSquare2 = createSmallSquare2();
        smallSquare2.prefWidthProperty().bind(smallSquaresBoxTop.widthProperty());
        smallSquaresBoxTop.getChildren().addAll(smallSquare1,smallSquare2);

        // Bottom small squares
        HBox smallSquaresBoxBottom = new HBox(20);
        smallSquaresBoxBottom.setAlignment(Pos.CENTER);
        smallSquaresBoxBottom.prefWidthProperty().bind(featuredBox.widthProperty());
        smallSquaresBoxBottom.prefHeightProperty().bind(featuredBox.heightProperty());

        Pane smallSquare3 = createSmallSquare3();
        smallSquare3.prefWidthProperty().bind(smallSquaresBoxBottom.widthProperty());

        Pane smallSquare4 = createSmallSquare4();
        smallSquare4.prefWidthProperty().bind(smallSquaresBoxBottom.widthProperty());
        smallSquaresBoxBottom.getChildren().addAll(smallSquare3,smallSquare4);

        featuredBox.getChildren().addAll(largeSquare, smallSquaresBoxTop, smallSquaresBoxBottom);

        return featuredBox;
    }

    private Pane createSmallSquare1() {
        // Create small square pane
        Pane smallSquare = new Pane();
        smallSquare.setStyle("-fx-background-color: #d0d0d0; -fx-border-color: #aaaaaa; -fx-border-radius: 15; -fx-background-radius: 15;");

        // Create ImageView for the square
        ImageView squareImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/ddd.jpg"))));
        squareImageView.fitWidthProperty().bind(smallSquare.widthProperty());
        squareImageView.fitHeightProperty().bind(smallSquare.heightProperty());

        // Set rounded corners for the image
        Rectangle clip = new Rectangle();
        clip.setArcWidth(30);
        clip.setArcHeight(30);
        clip.setHeight(smallSquare.getPrefHeight());
        clip.setWidth(smallSquare.getPrefWidth());
        clip.heightProperty().bind(squareImageView.fitHeightProperty());
        clip.widthProperty().bind(squareImageView.fitWidthProperty());
        squareImageView.setClip(clip);

        DropShadow shadow = new DropShadow();
        shadow.setRadius(5.0);
        shadow.setColor(Color.GRAY);
        smallSquare.setEffect(shadow);

        // Create a label for "الخدمات الحالية"
        VBox labelBox = new VBox();
        labelBox.prefHeightProperty().bind(smallSquare.heightProperty());
        labelBox.prefWidthProperty().bind(smallSquare.widthProperty());
        labelBox.setAlignment(Pos.CENTER_RIGHT);

        Label smallLabel = new Label("  الخدمات\n  التي قيدت\n  التنفيذ");
        smallLabel.setStyle("-fx-font-size: 24; -fx-text-fill: #000000; -fx-font-weight: bold;");
        smallLabel.setTextAlignment(TextAlignment.RIGHT);
        labelBox.getChildren().addAll(smallLabel);

        // Create button for each small square with circular style

        ImageView buttonImg = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/menu.png"))));
        buttonImg.setPreserveRatio(false);

        Button discoverButton = new Button("",buttonImg);
        discoverButton.setStyle("-fx-background-color: #d37120; -fx-text-fill: #ffffff; -fx-background-radius: 50%; -fx-padding: 10;");

        VBox smallSquareContent = new VBox();

        // Make the button responsive to the size of the smallSquareContent and labelBox

        discoverButton.prefWidthProperty().bind(smallSquareContent.widthProperty().multiply(0.3)); // Adjust width relative to smallSquareContent (30%)
        discoverButton.prefHeightProperty().bind(discoverButton.prefWidthProperty()); // Keep button height proportional to its width

        // Create a VBox to hold the button and align it to the bottom left
        smallSquareContent.setPadding(new Insets(10)); // Add padding to position elements inside the small square
        smallSquareContent.setAlignment(Pos.CENTER_LEFT);
        smallSquareContent.prefHeightProperty().bind(smallSquare.heightProperty());
        smallSquareContent.prefWidthProperty().bind(smallSquare.widthProperty());
        smallSquareContent.getChildren().add(discoverButton);

        // Ensure button does not exceed the size of labelBox
        smallSquareContent.maxHeightProperty().bind(labelBox.heightProperty());
        smallSquareContent.maxWidthProperty().bind(labelBox.widthProperty());

        // Add label, image, and button to the pane
        smallSquare.getChildren().addAll(squareImageView, labelBox, smallSquareContent);

        return smallSquare;
    }
    private Pane createSmallSquare2() {
        // Create small square pane
        Pane smallSquare = new Pane();
        smallSquare.setStyle("-fx-background-color: #d0d0d0; -fx-border-color: #aaaaaa; -fx-border-radius: 15; -fx-background-radius: 15;");

        // Create ImageView for the square
        ImageView squareImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/greenPhoto.jpeg"))));
        squareImageView.fitWidthProperty().bind(smallSquare.widthProperty());
        squareImageView.fitHeightProperty().bind(smallSquare.heightProperty());

        // Set rounded corners for the image
        Rectangle clip = new Rectangle();
        clip.setArcWidth(30);
        clip.setArcHeight(30);
        clip.setHeight(smallSquare.getPrefHeight());
        clip.setWidth(smallSquare.getPrefWidth());
        clip.heightProperty().bind(squareImageView.fitHeightProperty());
        clip.widthProperty().bind(squareImageView.fitWidthProperty());
        squareImageView.setClip(clip);

        DropShadow shadow = new DropShadow();
        shadow.setRadius(5.0);
        shadow.setColor(Color.GRAY);
        smallSquare.setEffect(shadow);

        // Create a label for "الخدمات الحالية"
        VBox labelBox = new VBox();
        labelBox.prefHeightProperty().bind(smallSquare.heightProperty());
        labelBox.prefWidthProperty().bind(smallSquare.widthProperty());
        labelBox.setAlignment(Pos.CENTER_RIGHT);

        Label smallLabel = new Label("  خيارات البحث\n  عن عمل");
        smallLabel.setStyle("-fx-font-size: 24; -fx-text-fill: White; -fx-font-weight: bold;");
        smallLabel.setTextAlignment(TextAlignment.RIGHT);
        labelBox.getChildren().addAll(smallLabel);

        // Create button for each small square with circular style

        ImageView buttonImg = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/menu.png"))));
        buttonImg.setPreserveRatio(false);

        Button discoverButton = new Button("",buttonImg);
        discoverButton.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #ffffff; -fx-background-radius: 50%; -fx-padding: 10;");

        VBox smallSquareContent = new VBox();

        // Make the button responsive to the size of the smallSquareContent and labelBox

        discoverButton.prefWidthProperty().bind(smallSquareContent.widthProperty().multiply(0.3)); // Adjust width relative to smallSquareContent (30%)
        discoverButton.prefHeightProperty().bind(discoverButton.prefWidthProperty()); // Keep button height proportional to its width

        // Create a VBox to hold the button and align it to the bottom left
        smallSquareContent.setPadding(new Insets(10)); // Add padding to position elements inside the small square
        smallSquareContent.setAlignment(Pos.CENTER_LEFT);
        smallSquareContent.prefHeightProperty().bind(smallSquare.heightProperty());
        smallSquareContent.prefWidthProperty().bind(smallSquare.widthProperty());
        smallSquareContent.getChildren().add(discoverButton);

        // Ensure button does not exceed the size of labelBox
        smallSquareContent.maxHeightProperty().bind(labelBox.heightProperty());
        smallSquareContent.maxWidthProperty().bind(labelBox.widthProperty());

        // Add label, image, and button to the pane
        smallSquare.getChildren().addAll(squareImageView, labelBox, smallSquareContent);

        return smallSquare;
    }

    private Pane createSmallSquare3() {
        // Create small square pane
        Pane smallSquare = new Pane();
        VBox layout = new VBox();
        smallSquare.setStyle("-fx-background-color: #fcbe4b; -fx-border-color: #aaaaaa; -fx-border-radius: 15; -fx-background-radius: 15;");

        DropShadow shadow = new DropShadow();
        shadow.setRadius(5.0);
        shadow.setColor(Color.GRAY);
        smallSquare.setEffect(shadow);

        VBox topLeftBox = new VBox();
        // Create a transparent button for recent transactions
        Button recentTransactionsButton = new Button("المعاملات الأخيرة");
        recentTransactionsButton.setStyle("-fx-background-color: transparent; -fx-border-color: black;-fx-border-width: 2px;-fx-background-radius: 15px;-fx-border-radius: 15px");

        // Create a VBox to hold the year label and the button
        topLeftBox.setAlignment(Pos.TOP_LEFT);
        topLeftBox.getChildren().addAll(recentTransactionsButton);
        topLeftBox.prefHeightProperty().bind(layout.heightProperty());
        topLeftBox.prefWidthProperty().bind(layout.widthProperty());

        VBox topRightBox = new VBox();

        // Create label for the current year
        Label yearLabel = new Label("2024"); // استخدم السنة الحالية
        yearLabel.setStyle("-fx-text-fill: black;-fx-font-weight: bold;-fx-font-size: 16px");

        topRightBox.setAlignment(Pos.TOP_RIGHT);
        topRightBox.getChildren().addAll(yearLabel);
        topRightBox.prefHeightProperty().bind(layout.prefWidthProperty());
        topRightBox.prefWidthProperty().bind(layout.widthProperty());

        HBox top = new HBox();
        top.getChildren().addAll(topLeftBox, topRightBox);
        top.setPadding(new Insets(10));
        // Create a VBox for transactions count
        Label transactionsLabel = new Label("عدد الماملين");
        transactionsLabel.setStyle("-fx-text-fill: black;-fx-font-weight: bold;");
        VBox transactionsBox = new VBox();
        transactionsBox.getChildren().addAll(transactionsLabel);
        transactionsLabel.setAlignment(Pos.TOP_LEFT);

        Label transactionsCount = new Label("1M+");
        transactionsCount.setStyle("-fx-text-fill: black;-fx-font-weight: bold;-fx-font-size: 60px");

        VBox middleLeftBox = new VBox();
        middleLeftBox.setAlignment(Pos.TOP_LEFT);
        middleLeftBox.getChildren().addAll(transactionsBox, transactionsCount);
        middleLeftBox.prefHeightProperty().bind(layout.heightProperty());
        middleLeftBox.prefWidthProperty().bind(layout.widthProperty());
        middleLeftBox.setPadding(new Insets(10));

        // Create a layout to hold all elements

        layout.getChildren().addAll(top, middleLeftBox);
        layout.prefHeightProperty().bind(smallSquare.heightProperty());
        layout.prefWidthProperty().bind(smallSquare.widthProperty());

        // Add label, image, and layout to the pane
        smallSquare.getChildren().addAll(layout);
        return smallSquare;
    }

    private Pane createSmallSquare4() {
        // Create small square pane
        Pane smallSquare = new Pane();
        VBox layout = new VBox();
        smallSquare.setStyle("-fx-background-color: #ad9ea4; -fx-border-color: #aaaaaa; -fx-border-radius: 15; -fx-background-radius: 15;");

        DropShadow shadow = new DropShadow();
        shadow.setRadius(5.0);
        shadow.setColor(Color.GRAY);
        smallSquare.setEffect(shadow);

        VBox topLeftBox = new VBox();
        // Create a transparent button for recent transactions
        Button recentTransactionsButton = new Button("اضافة/ازالة عنوان");
        recentTransactionsButton.setStyle("-fx-text-fill: white; -fx-background-color: transparent; -fx-border-color: white; -fx-border-width: 2px; -fx-background-radius: 15px; -fx-border-radius: 15px");

        // Create a VBox to hold the year label and the button
        topLeftBox.setAlignment(Pos.TOP_LEFT);
        topLeftBox.getChildren().addAll(recentTransactionsButton);
        topLeftBox.prefWidthProperty().bind(layout.widthProperty());

        VBox topRightBox = new VBox();
        // Create label for the current year
        Label yearLabel = new Label("عناويتي");
        yearLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px");

        topRightBox.setAlignment(Pos.TOP_RIGHT);
        topRightBox.getChildren().addAll(yearLabel);
        topRightBox.prefHeightProperty().bind(layout.prefWidthProperty());
        topRightBox.prefWidthProperty().bind(layout.widthProperty());

        HBox top = new HBox();
        top.getChildren().addAll(topLeftBox, topRightBox);
        top.setPadding(new Insets(10));
        top.prefHeightProperty().bind(layout.heightProperty());
        top.prefWidthProperty().bind(layout.widthProperty());

        // Create circular button
        ImageView buttonImg = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/menu.png"))));
        buttonImg.setPreserveRatio(true);

        // Create a layout to hold all elements
        layout.getChildren().addAll(top);
        layout.prefHeightProperty().bind(smallSquare.heightProperty());
        layout.prefWidthProperty().bind(smallSquare.widthProperty());

        // Add label, image, and layout to the pane
        smallSquare.getChildren().addAll(layout);
        return smallSquare;
    }


}
