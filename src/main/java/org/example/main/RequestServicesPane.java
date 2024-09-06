package org.example.main;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class RequestServicesPane {

    // دالة لإنشاء وعرض صفحة الخدمات
    public Pane showServicesPage(Pane leftSidePane) {
        // إنشاء لوحة المحتوى الرئيسية

        Pane contentPane = new Pane();
        leftSidePane.getChildren().clear();
        contentPane.setId("contentPane");

        // إنشاء زر الانتقال إلى الملف الشخصي
        Button goToProfile = goToProfile();

        // إنشاء أزرار الخدمات
        Button nowService = createServicesButton("خدماتي الحالية");
        nowService.setId("nowService");
        Button previousService = createServicesButton("خدماتي السابقة");
        previousService.setId("previousService");
        Button myRate = createServicesButton("تقييماتي");
        myRate.setId("myRate");

        Label shortCuts = new Label("اختصارات");
        shortCuts.setId("shortCuts");

        HBox topRightButtons = new HBox();
        topRightButtons.setSpacing(10.0);

        topRightButtons.getChildren().addAll(nowService, previousService, myRate);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS); // ملء المساحة المتاحة

        HBox topButtons = new HBox(10);
        topButtons.setAlignment(Pos.CENTER);
        topButtons.setPadding(new Insets(20, 30, 0, 30));
        topButtons.getChildren().addAll(goToProfile, spacer, topRightButtons);

        // إنشاء عنوان "الخدمات الشائعة"
        Label trendService = new Label("الخدمات الشائعة");
        trendService.setAlignment(Pos.TOP_RIGHT);
        trendService.setId("trendService");

        Line line = new Line();
        line.setId("line");
        line.setStartX(85);
        line.setStartY(360);
        line.setEndX(840);
        line.setEndY(360);

        // إنشاء حقل البحث عن الخدمات
        TextField serviceSearch = new TextField();
        serviceSearch.setPromptText("البحث عن خدمة...");
        serviceSearch.setAlignment(Pos.CENTER_RIGHT);
        serviceSearch.setPrefSize(230, 30);
        serviceSearch.setId("serviceSearch");

        HBox topFields = new HBox();
        topFields.setAlignment(Pos.CENTER);
        topFields.setSpacing(300);
        topFields.getChildren().addAll(serviceSearch, trendService);

        RightSideBar rightSideBar = new RightSideBar(leftSidePane);

        // إنشاء لوحات الخدمات المختلفة
        Pane electricPane = createServicesPane(
                "كهربائي",
                "15$",
                "electrical.png",
                rightSideBar
        );
        electricPane.setId("electricPane");

        Pane waterPane = createServicesPane(
                "سبّاك",
                "30$",
                "water_pane.png",
                rightSideBar
        );
        waterPane.setId("waterPane");

        Pane tailorPane = createServicesPane(
                "خياط",
                "45$",
                "tailor.png",
                rightSideBar
        );
        tailorPane.setId("tailorPane");

        Pane woodmenPane = createServicesPane(
                "نجار",
                "28$",
                "wood_man.png",
                rightSideBar
        );
        woodmenPane.setId("woodmenPane");

        Pane cleaningPane = createServicesPane(
                "تنظيف",
                "20$",
                "clean.png",
                rightSideBar
        );
        cleaningPane.setId("cleaningPane");

        Pane securityPane = createServicesPane(
                "تركيب أنظمة أمان",
                "50$",
                "security.png",
                rightSideBar
        );
        securityPane.setId("securityPane");

        Pane applianceRepairPane = createServicesPane(
                "صيانة أجهزة كهربائية",
                "40$",
                "repair.png",
                rightSideBar
        );
        applianceRepairPane.setId("applianceRepairPane");

        VBox servicesBox = new VBox(10); // استخدام VBox مع مسافة بين العناصر
        servicesBox.setStyle("-fx-background-color: transparent;");
        servicesBox.getChildren().addAll(electricPane, waterPane, tailorPane, woodmenPane, cleaningPane, securityPane, applianceRepairPane);

        // إنشاء ScrollPane لتغليف لوحات الخدمات
        ScrollPane scrollPane = new ScrollPane(servicesBox);
        scrollPane.setStyle("-fx-background-color: transparent;");
        scrollPane.setPrefHeight(570);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(0, 30, 0, 30));

        VBox mainContainer = new VBox();
        mainContainer.setSpacing(30);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setPadding(new Insets(0, 50, 0, 50)); // إضافة الفراغ 50px من الجانبين
        mainContainer.prefWidthProperty().bind(contentPane.widthProperty());
        mainContainer.prefHeightProperty().bind(contentPane.heightProperty());
        mainContainer.getChildren().addAll(topButtons, topFields, scrollPane);

        // إضافة جميع العناصر إلى لوحة المحتوى

        // إضافة أزرار الاختصار
        HBox categoryButtonsBox = new HBox(10);
        categoryButtonsBox.setAlignment(Pos.CENTER);
        categoryButtonsBox.setPadding(new Insets(0, 50, 0, 50));

        Button servicesButton = createShortcutButton(
                "خدماتي",
                "services.png",
                "خدماتي",
                contentPane,
                rightSideBar
        );
        servicesButton.setId("servicesButton");
        servicesButton.setOnMouseEntered(_ -> createUpAnimateButton(servicesButton));
        servicesButton.setOnMouseExited(_ -> createDownAnimateButton(servicesButton));

        Button myLocationsButton = createShortcutButton(
                "عناويني",
                "map.png",
                "عناويني",
                contentPane,
                rightSideBar
        );
        myLocationsButton.setId("myLocationsButton");
        myLocationsButton.setOnMouseEntered(_ -> createUpAnimateButton(myLocationsButton));
        myLocationsButton.setOnMouseExited(_ -> createDownAnimateButton(myLocationsButton));

        Button categoryButton = createShortcutButton(
                "كتالوج الخدمات",
                "category.png",
                "كتالوج الخدمات",
                contentPane,
                rightSideBar
        );
        categoryButton.setId("categoryButton");
        categoryButton.setOnMouseEntered(_ -> createUpAnimateButton(categoryButton));
        categoryButton.setOnMouseExited(_ -> createDownAnimateButton(categoryButton));

        Button favoriteButton = createShortcutButton(
                "خدماتي المفضلة",
                "favorite.png",
                "خدماتي المفضلة",
                contentPane,
                rightSideBar
        );
        favoriteButton.setId("favoriteButton");
        favoriteButton.setOnMouseEntered(_ -> createUpAnimateButton(favoriteButton));
        favoriteButton.setOnMouseExited(_ -> createDownAnimateButton(favoriteButton));

        categoryButtonsBox.getChildren().addAll(servicesButton, myLocationsButton, categoryButton, favoriteButton);

        mainContainer.getChildren().add(1,shortCuts);
        mainContainer.getChildren().add(2, categoryButtonsBox);
        mainContainer.getChildren().add(4,line);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), contentPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        contentPane.getChildren().addAll(mainContainer);
        contentPane.prefWidthProperty().bind(leftSidePane.widthProperty());
        contentPane.prefHeightProperty().bind(leftSidePane.heightProperty());
        leftSidePane.getChildren().add(contentPane);
        return leftSidePane;
    }

    private Button createShortcutButton(String text, String imagePath, String category, Pane contentPane, RightSideBar rightSideBar) {
        ImageView imageView = new ImageView(rightSideBar.loadImage(imagePath));
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        Button button = new Button(text, imageView);
        button.setOnAction(_ -> filterServices(category));
        button.setContentDisplay(ContentDisplay.TOP);
        button.setAlignment(Pos.CENTER);
        button.setPrefHeight(350);
        button.prefWidthProperty().bind(contentPane.widthProperty());

        return button;
    }

    private void filterServices(String category) {
        // من هنا يمكنك إضافة الكود الخاص بتصفية الخدمات حسب الفئة المحددة
        // مثال: عرض الخدمات المتعلقة بالتصنيف "تنظيف" فقط
    }

    private Button goToProfile() {
        Button goToProfile = new Button();
        goToProfile.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 10px; -fx-background-radius: 10px;-fx-cursor: hand;"
                + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 5, 0, 0, 0);");
        goToProfile.setPrefSize(50,50);
        return goToProfile;
    }

    // دالة لإنشاء أزرار الخدمات
    private Button createServicesButton(String name) {
        Button servicesButton = new Button(name);
        servicesButton.setPrefSize(120,40);
        return servicesButton;
    }

    // دالة لإنشاء لوحة خدمة معينة
    private Pane createServicesPane(String title, String price, String imagePath, RightSideBar rightSideBar) {
        // إنشاء صورة الخدمة ووضعها على أقصى اليمين
        ImageView imageView = new ImageView(rightSideBar.loadImage(imagePath));
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 12, 0, 0, 0);");

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
        Button requestButton = createRequestButton();
        Tooltip.install(requestButton, new Tooltip("أطلب الخدمة الآن!"));

        // إضافة Tagline تحت معلومات إضافية
        Label tagline = new Label("أفضل الخدمات بأقل الأسعار!");
        tagline.setStyle("-fx-text-fill: #0076a3; -fx-font-size: 12px;");

        VBox buttonInfoBox = new VBox(5);
        buttonInfoBox.setAlignment(Pos.CENTER_LEFT);
        buttonInfoBox.getChildren().addAll(requestButton, tagline);

        // إنشاء HBox لاحتواء العناصر
        HBox pane = new HBox(20);
        pane.setPrefSize(680, 110);
        pane.setPadding(new Insets(10, 50, 10, 50));
        pane.setAlignment(Pos.CENTER);

        pane.setOnMouseEntered(_ -> {
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(100), pane);
            scaleUp.setToX(1.02);
            scaleUp.setToY(1.02);
            scaleUp.play();
        });
        pane.setOnMouseExited(_ -> {
            ScaleTransition scaleDown = new ScaleTransition(Duration.millis(300), pane);
            scaleDown.setToX(1);
            scaleDown.setToY(1);
            scaleDown.play();
        });

        // إضافة العناصر إلى اللوحة
        pane.getChildren().addAll(buttonInfoBox, titlePriceBox, imageView);

        // لضمان توسيع VBox وفقاً للمساحة المتاحة
        HBox.setHgrow(buttonInfoBox, Priority.ALWAYS);
        HBox.setHgrow(titlePriceBox, Priority.ALWAYS);

        return pane;
    }

    // دالة لإنشاء زر الطلب
    private Button createRequestButton() {
        Button requestButton = new Button("أطلب الاَن");
        requestButton.setId("requestButton");
        requestButton.setPrefSize(130, 110);
        requestButton.setOnMouseEntered(_ -> createUpAnimateButton(requestButton));
        requestButton.setOnMouseExited(_ -> createDownAnimateButton(requestButton));

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