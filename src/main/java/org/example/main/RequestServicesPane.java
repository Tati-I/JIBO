package org.example.main;

import bar.right.RightSideBar;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.Objects;

public class RequestServicesPane {

    // دالة لإنشاء وعرض صفحة الخدمات
    public Pane showServicesPage(Pane leftSidePane) {
        leftSidePane.getChildren().clear();
        VBox mainContainer = new VBox();
        mainContainer.setSpacing(20);
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setPadding(new Insets(15, 15, 0, 15));
        mainContainer.prefWidthProperty().bind(leftSidePane.widthProperty());
        mainContainer.prefHeightProperty().bind(leftSidePane.heightProperty());

        //انشاء كائن لأستخدام بعض الأشياء
        RightSideBar rightSideBar = new RightSideBar(leftSidePane);

        // Top Header (20% of mainContainer height)
        VBox topHeader = new VBox();
        topHeader.prefHeightProperty().bind(mainContainer.heightProperty().multiply(0.215));
        topHeader.prefWidthProperty().bind(mainContainer.widthProperty());

        HBox topButtons = new HBox();
        topButtons.setAlignment(Pos.CENTER);
        topButtons.setPadding(new Insets(0, 80, 0, 80));
        topButtons.prefWidthProperty().bind(topHeader.widthProperty());
        topButtons.prefHeightProperty().bind(topHeader.heightProperty());
        topButtons.setStyle("-fx-background-color: rgba(255,255,255,0.98); -fx-background-radius: 20; -fx-border-radius: 30");

        DropShadow shadow = new DropShadow();
        shadow.setRadius(5.0);
        shadow.setColor(Color.GRAY);
        topButtons.setEffect(shadow);
        // إنشاء زر الانتقال إلى الملف الشخصي

        Label welcomeLabel = new Label("Jibo");
        welcomeLabel.setPadding(new Insets(8, 0, 0, 0));
        welcomeLabel.setId("welcomeLabel");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));

        ImageView logoView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/logoBlack.png"))));
        logoView.setFitHeight(46);
        logoView.setFitWidth(46);

        HBox content = new HBox();
        content.setAlignment(Pos.CENTER_LEFT);
        content.getChildren().addAll(logoView, welcomeLabel);
        content.prefWidthProperty().bind(topButtons.widthProperty());

        Button goToProfile = goToProfile();
        goToProfile.setOnAction(_ -> {
            MyProfile myProfile = new MyProfile();
            myProfile.showMyProfilePage(leftSidePane);
        });
        HBox profileBox = new HBox();
        profileBox.setAlignment(Pos.CENTER_RIGHT);
        profileBox.getChildren().addAll(goToProfile);

        // إنشاء أزرار الخدمات
        String[][] topRightButtons = {
                {"خدماتي الحالية", "nowService"},
                {"خدماتي السابقة", "previousService"},
                {"تقييماتي", "myRate"}
        };

        HBox topRightButtonBox = new HBox(10);
        topRightButtonBox.setAlignment(Pos.CENTER_RIGHT);

        for (String[] topRightButton : topRightButtons) {
            Button topButton = createServicesButton(topRightButton[0], topRightButton[1]);
            topRightButtonBox.getChildren().add(topButton);
        }

        HBox rightBox = new HBox(10);
        rightBox.setAlignment(Pos.CENTER_RIGHT);
        rightBox.prefWidthProperty().bind(topButtons.widthProperty());
        rightBox.getChildren().addAll(topRightButtonBox, profileBox);

        topButtons.getChildren().addAll(content, rightBox);
        topHeader.getChildren().add(topButtons);

        // Middle Section (40% of mainContainer height)
        VBox middleSection = new VBox(15);
        middleSection.prefHeightProperty().bind(mainContainer.heightProperty().multiply(0.4));
        middleSection.prefWidthProperty().bind(mainContainer.widthProperty());
        middleSection.setStyle("-fx-background-color: linear-gradient(#E0E0E0,#E0E0E0);-fx-background-radius: 15");


        Label shortCuts = new Label("اختصارات");
        shortCuts.setId("shortCuts");
        HBox shortCutsBox = new HBox(shortCuts);
        shortCutsBox.setAlignment(Pos.CENTER_RIGHT);
        shortCutsBox.prefWidthProperty().bind(middleSection.widthProperty());
        shortCutsBox.prefHeightProperty().bind(middleSection.heightProperty().multiply(0.2));
        shortCutsBox.setPadding(new Insets(0, 30, 0, 0));

        HBox categoryButtonsBox = new HBox(10);
        categoryButtonsBox.setAlignment(Pos.CENTER);
        categoryButtonsBox.setPadding(new Insets(15, 50, 15, 50));
        categoryButtonsBox.prefWidthProperty().bind(middleSection.widthProperty());
        categoryButtonsBox.prefHeightProperty().bind(middleSection.heightProperty().multiply(0.7));

        middleSection.getChildren().addAll(shortCutsBox, categoryButtonsBox);

        String[][] shortcuts = {
                {"خدماتي", "services.png", "servicesButton"},
                {"عناويني", "map.png", "myLocationsButton"},
                {"كتالوج الخدمات", "category.png", "categoryButton"},
                {"خدماتي المفضلة", "favorite.png", "favoriteButton"}
        };

        for (String[] shortcut : shortcuts) {
            Button shortcutButton = createShortcutButton(shortcut[0], shortcut[1], shortcut[2], leftSidePane, rightSideBar);
            shortcutButton.prefHeightProperty().bind(categoryButtonsBox.heightProperty());
            shortcutButton.prefWidthProperty().bind(categoryButtonsBox.widthProperty().divide(shortcuts.length));
            shortcutButton.setEffect(shadow);
            categoryButtonsBox.getChildren().add(shortcutButton);
        }

        HBox topFields = new HBox();
        topFields.setPadding(new Insets(0, 30, 0, 30));
        topFields.prefWidthProperty().bind(middleSection.widthProperty());
        topFields.prefHeightProperty().bind(middleSection.heightProperty().multiply(0.2));

        Label trendService = new Label("الخدمات الشائعة");
        trendService.setAlignment(Pos.TOP_RIGHT);
        trendService.setId("trendService");

        HBox trendServiceBox = new HBox();
        trendServiceBox.setAlignment(Pos.TOP_RIGHT);
        trendServiceBox.getChildren().add(trendService);
        trendServiceBox.prefWidthProperty().bind(topFields.widthProperty());
        trendServiceBox.prefHeightProperty().bind(topFields.heightProperty());

        // إنشاء حقل البحث عن الخدمات
        TextField serviceSearch = new TextField();
        serviceSearch.setPromptText("البحث عن خدمة...");
        serviceSearch.setAlignment(Pos.TOP_RIGHT);
        serviceSearch.setPrefSize(230, 30);
        serviceSearch.setId("serviceSearch");

        HBox serviceSearchBox = new HBox();
        serviceSearchBox.setAlignment(Pos.TOP_LEFT);
        serviceSearchBox.getChildren().add(serviceSearch);
        serviceSearchBox.prefWidthProperty().bind(topFields.widthProperty());
        serviceSearchBox.prefHeightProperty().bind(topFields.heightProperty());

        topFields.getChildren().addAll(serviceSearchBox, trendServiceBox);
        HBox.setHgrow(trendService, Priority.ALWAYS);

        // Bottom Section with ScrollPane (40% of mainContainer height)
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background-color: transparent;");
        scrollPane.setFitToWidth(true);
        scrollPane.prefHeightProperty().bind(mainContainer.heightProperty().multiply(0.4));
        scrollPane.prefWidthProperty().bind(mainContainer.widthProperty());
        scrollPane.setPadding(new Insets(0, 30, 0, 30));

        VBox servicesBox = new VBox(10);

        servicesBox.prefWidthProperty().bind(scrollPane.widthProperty());
        servicesBox.prefHeightProperty().bind(scrollPane.heightProperty());
        servicesBox.setPadding(new Insets(15, 15, 15, 15));
        servicesBox.setStyle("-fx-background-color: linear-gradient(#e4e4e4,#E0E0E0);-fx-background-radius: 15");

        String[][] services = {
                {"كهربائي", "15$", "electrical.png", "electricPane"},
                {"سبّاك", "30$", "water_pane.png", "waterPane"},
                {"خياط", "45$", "tailor.png", "tailorPane"},
                {"نجار", "28$", "wood_man.png", "woodmenPane"},
                {"تنظيف", "20$", "clean.png", "cleaningPane"},
                {"تركيب أنظمة أمان", "50$", "security.png", "securityPane"},
                {"صيانة أجهزة كهربائية", "40$", "repair.png", "applianceRepairPane"}
        };

        for (String[] service : services) {
            Pane servicePane = createServicesPane(service[0], service[1], service[2], rightSideBar, service[3], leftSidePane);
            servicePane.prefWidthProperty().bind(servicesBox.widthProperty());
            servicesBox.getChildren().add(servicePane);
        }

        scrollPane.setContent(servicesBox);

        // Add all sections to mainContainer
        mainContainer.getChildren().addAll(topHeader, middleSection, topFields, scrollPane);

        // Fade-in effect
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), mainContainer);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        leftSidePane.getChildren().add(mainContainer);
        return leftSidePane;
    }

    private Button createShortcutButton(String text, String imagePath, String category, Pane requestServicePane, RightSideBar rightSideBar) {
        ImageView imageView = new ImageView(rightSideBar.loadImage(imagePath));
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);


        Button button = new Button(text, imageView);
        button.setOnAction(_ -> filterServices(category));
        button.setContentDisplay(ContentDisplay.TOP);
        button.setAlignment(Pos.CENTER);
        button.prefWidthProperty().bind(requestServicePane.widthProperty());
        button.setId(category);

        return button;
    }

    private void filterServices(String category) {

        System.out.println(category);
    }

    private Button goToProfile() {
        Button goToProfile = new Button();
        goToProfile.setStyle("-fx-padding: 10 22 14 22;-fx-background-color: #505050; -fx-border-radius: 10px; -fx-background-radius: 10px;-fx-cursor: hand;"
                + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 5, 0, 0, 0);");
        return goToProfile;
    }

    // دالة لإنشاء أزرار الخدمات
    private Button createServicesButton(String name, String idName) {
        Button servicesButton = new Button(name);
        servicesButton.setId(idName);
        servicesButton.setPrefWidth(1220);
        return servicesButton;
    }

    // دالة لإنشاء لوحة خدمة معينة
    private Pane createServicesPane(String title, String price, String imagePath, RightSideBar rightSideBar, String idName, Pane leftSidePane) {
        // إنشاء صورة الخدمة ووضعها على أقصى اليمين
        HBox pane = new HBox(20);

        ImageView imageView = new ImageView(rightSideBar.loadImage(imagePath));
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 12, 0, 0, 0);");

        HBox imageBox = new HBox();
        imageBox.getChildren().add(imageView);
        imageBox.setAlignment(Pos.CENTER);


        // إنشاء مستطيل مدور الحواف باللون الفضي ووضع العنوان بداخله
        Label titleLabel = new Label(title);
        titleLabel.setId("titleLabel");
        titleLabel.setAlignment(Pos.CENTER);

        StackPane titleContainer = new StackPane();
        Rectangle textRectangle = new Rectangle(450, 30);
        textRectangle.setArcWidth(10);
        textRectangle.setArcHeight(10);
        textRectangle.setFill(Color.rgb(94, 94, 94, 0.05));

        titleContainer.getChildren().addAll(textRectangle, titleLabel);
        titleContainer.prefHeightProperty().bind(titleContainer.heightProperty());
        titleContainer.setAlignment(Pos.TOP_RIGHT);

        // إضافة نظام التقييم بالنجوم بجانب السعر
        Label ratingLabel = new Label("★★★★★");
        ratingLabel.setStyle("-fx-text-fill: gold; -fx-font-size: 14px;");

        // وصف مختصر للخدمة تحت السعر
        Label serviceDescription = new Label("خدمة سريعة واحترافية لتلبية احتياجاتك.");
        serviceDescription.setStyle("-fx-text-fill: #666666; -fx-font-size: 12px;");

        // سعر الخدمة
        Label priceLabel = new Label(price);
        priceLabel.setId("priceLabel");

        VBox titlePriceBox = new VBox(5);
        titlePriceBox.setAlignment(Pos.CENTER_RIGHT);
        titlePriceBox.getChildren().addAll(titleContainer, priceLabel, ratingLabel, serviceDescription);

        // زر الطلب ووضعه على أقصى اليسار مع Tooltip
        Button requestButton = createRequestButton(leftSidePane, title, price, imagePath);
        Tooltip.install(requestButton, new Tooltip("أطلب الخدمة الآن!"));

        // إضافة Tagline تحت معلومات إضافية
        Label tagline = new Label("أفضل الخدمات بأقل الأسعار!");
        tagline.setStyle("-fx-text-fill: #0076a3; -fx-font-size: 12px;");

        VBox buttonInfoBox = new VBox(5);
        buttonInfoBox.setAlignment(Pos.CENTER_LEFT);
        buttonInfoBox.getChildren().addAll(requestButton, tagline);
        buttonInfoBox.prefWidthProperty().bind(pane.prefWidthProperty());

        // إنشاء HBox لاحتواء العناصر

        pane.setOnMouseEntered(_ -> {
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), pane);

            scaleUp.play();
        });
        pane.setOnMouseExited(_ -> {
            ScaleTransition scaleDown = new ScaleTransition(Duration.millis(300), pane);
            scaleDown.setToX(1);
            scaleDown.setToY(1);
            scaleDown.play();
        });

        // إضافة العناصر إلى اللوحة
        pane.getChildren().addAll(buttonInfoBox, titlePriceBox, imageBox);
        pane.setId(idName);

        // لضمان توسيع VBox وفقاً للمساحة المتاح

        return pane;
    }

    // دالة لإنشاء زر الطلب
    private Button createRequestButton(Pane leftSidePane, String title, String price, String imagePath) {
        Button requestButton = new Button("أطلب الاَن");
        requestButton.setId("requestButton");
        requestButton.setPrefSize(130, 110);
        requestButton.setOnMouseEntered(_ -> createUpAnimateButton(requestButton));
        requestButton.setOnMouseExited(_ -> createDownAnimateButton(requestButton));
        requestButton.setOnAction(_ -> {
            // Apply blur effect to the main pane
            leftSidePane.setEffect(new GaussianBlur(10));

            // Center the new stage on the screen
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double width = screenBounds.getWidth() * 0.4;
            double height = screenBounds.getHeight() * 0.6;

            // Create and show the service detail page with service-specific information
            ServiceDetailPage serviceDetailPage = new ServiceDetailPage(title, price, imagePath);
            Stage detailStage = new Stage();
            Scene detailScene = new Scene(serviceDetailPage, width, height);

            // Add CSS for rounded corners
            detailScene.getStylesheets().add(getClass().getResource("/styles/detailStage/detailStage.css").toExternalForm());
            detailStage.setScene(detailScene);

            // Make the new stage modal and undecorated
            detailStage.initModality(Modality.APPLICATION_MODAL);
            detailStage.initOwner(leftSidePane.getScene().getWindow());
            detailStage.initStyle(StageStyle.TRANSPARENT);

            detailStage.setWidth(width);
            detailStage.setHeight(height);

            // Show the detail stage and wait for it to be closed
            detailStage.showAndWait();

            // Remove blur when the detail stage is closed
            leftSidePane.setEffect(null);
        });

        return requestButton;
    }

    // دالة لإنشاء حركة تكبير الزر عند تمرير الماوس
    private void createUpAnimateButton(Button button) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(300), button);
        scaleUp.setToX(1.1);
        scaleUp.setToY(1.1);
        scaleUp.play();
    }

    // دالة لإنشاء حركة تصغير الزر عند إزالة الماوس
    private void createDownAnimateButton(Button button) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(300), button);
        scaleDown.setToX(1);
        scaleDown.setToY(1);
        scaleDown.play();
    }
}