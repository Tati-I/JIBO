package org.example.main;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class RequestServicesPane {

    // دالة لإنشاء وعرض صفحة الخدمات
    public Pane showServicesPage(Pane leftSidePane) {
        // إنشاء لوحة المحتوى الرئيسية

        Pane contentPane = new Pane();
        leftSidePane.getChildren().clear();
        contentPane.prefWidthProperty().bind(leftSidePane.widthProperty());
        contentPane.prefHeightProperty().bind(leftSidePane.heightProperty());
        contentPane.setId("contentPane");

        // إنشاء زر الانتقال إلى الملف الشخصي
        Button goToProfile = goToProfile();

        // إنشاء أزرار الخدمات
        Button nowService = createServicesButton("خدماتي الحالية");
        Button previousService = createServicesButton("خدماتي السابقة");
        Button myRate = createServicesButton("تقييماتي");

        HBox topRightButtons = new HBox();
        topRightButtons.setSpacing(10.0);
        topRightButtons.setPadding(new Insets(10.0, 0, 10.0, 0));

        topRightButtons.getChildren().addAll(nowService, previousService, myRate);

        HBox topButtons = new HBox(300);
        topButtons.setAlignment(Pos.CENTER);
        topButtons.setPadding(new Insets(10.0));
        topButtons.getChildren().addAll(goToProfile, topRightButtons);

        // إنشاء عنوان "الخدمات الشائعة"
        Label trendService = new Label("الخدمات الشائعة");
        trendService.setAlignment(Pos.TOP_RIGHT);
        trendService.setId("trendService");

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
        Pane electricPane = createServicesPane("كهربائي", "15$", rightSideBar.loadImage("electrical.png"));
        electricPane.setId("electricPane");

        Pane waterPane = createServicesPane("سبّاك", "30$", rightSideBar.loadImage("water_pane.png"));
        waterPane.setId("waterPane");

        Pane tailorPane = createServicesPane("خياط", "45$", rightSideBar.loadImage("tailor.png"));
        tailorPane.setId("tailorPane");

        Pane woodmenPane = createServicesPane("نجار", "28$", rightSideBar.loadImage("wood_man.png"));
        woodmenPane.setId("woodmenPane");

        Pane cleaningPane = createServicesPane("تنظيف", "20$", rightSideBar.loadImage("clean.png"));
        cleaningPane.setId("cleaningPane");

        Pane securityPane = createServicesPane("تركيب أنظمة أمان", "50$", rightSideBar.loadImage("security.png"));
        securityPane.setId("securityPane");

        Pane applianceRepairPane = createServicesPane("صيانة أجهزة كهربائية", "40$", rightSideBar.loadImage("repair.png"));
        applianceRepairPane.setId("applianceRepairPane");

        VBox servicesBox = new VBox(10); // استخدام VBox مع مسافة بين العناصر
        servicesBox.setStyle("-fx-background-color: transparent;");
        servicesBox.getChildren().addAll(electricPane, waterPane, tailorPane, woodmenPane, cleaningPane, securityPane, applianceRepairPane);

        // إنشاء ScrollPane لتغليف لوحات الخدمات
        ScrollPane scrollPane = new ScrollPane(servicesBox);
        scrollPane.setStyle("-fx-background-color: transparent;");
        scrollPane.setPrefHeight(570);
        scrollPane.setPadding(new Insets(0,80,0,80));
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        VBox mainContainer = new VBox();
        mainContainer.setSpacing(30);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.prefWidthProperty().bind(contentPane.widthProperty());
        mainContainer.prefHeightProperty().bind(contentPane.heightProperty());
        mainContainer.getChildren().addAll(topButtons, topFields, scrollPane);

        // إضافة جميع العناصر إلى لوحة المحتوى

        // إضافة أزرار الاختصار
        HBox categoryButtonsBox = new HBox(10);
        categoryButtonsBox.setAlignment(Pos.CENTER);
        categoryButtonsBox.setPadding(new Insets(0,110,0,110));

        Button cleaningButton = new Button("تنظيف");
        cleaningButton.setOnAction(_ -> filterServices("تنظيف"));
        cleaningButton.setPrefHeight(350);
        cleaningButton.prefWidthProperty().bind(contentPane.widthProperty());

        Button mechanicsButton = new Button("ميكانيك");
        mechanicsButton.setOnAction(_ -> filterServices("ميكانيك"));
        mechanicsButton.setPrefHeight(350);
        mechanicsButton.prefWidthProperty().bind(contentPane.widthProperty());

        Button electricalButton = new Button("كهرباء");
        electricalButton.setOnAction(_ -> filterServices("كهرباء"));
        electricalButton.setPrefHeight(350);
        electricalButton.prefWidthProperty().bind(contentPane.widthProperty());

        Button carpentryButton = new Button("نجارة");
        carpentryButton.setOnAction(_ -> filterServices("نجارة"));
        carpentryButton.setPrefHeight(350);
        carpentryButton.prefWidthProperty().bind(contentPane.widthProperty());


        categoryButtonsBox.getChildren().addAll(cleaningButton, mechanicsButton, electricalButton, carpentryButton);

        mainContainer.getChildren().add(1, categoryButtonsBox);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), contentPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        contentPane.getChildren().addAll(mainContainer);
        leftSidePane.getChildren().add(contentPane);
        return leftSidePane;
    }

    private void filterServices(String category) {
        // من هنا يمكنك إضافة الكود الخاص بتصفية الخدمات حسب الفئة المحددة
        // مثال: عرض الخدمات المتعلقة بالتصنيف "تنظيف" فقط
    }

    private Button goToProfile() {
        Button goToProfile = new Button();
        goToProfile.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 10px; -fx-background-radius: 10px;-fx-cursor: hand;"
                + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 5, 0, 0, 0);");
        goToProfile.setPrefSize(50, 50);
        return goToProfile;
    }

    // دالة لإنشاء أزرار الخدمات
    private Button createServicesButton(String name) {
        Button servicesButton = new Button(name);
        servicesButton.setId("servicesButton");
        servicesButton.setPrefSize(120,40);
        return servicesButton;
    }

    // دالة لإنشاء لوحة خدمة معينة
    private Pane createServicesPane(String title, String price, Image image) {
        // إنشاء صورة الخدمة ووضعها على أقصى اليمين
        ImageView imageView = new ImageView(image);
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
        textRectangle.setFill(Color.rgb(94, 94, 94, 0.1));

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
        pane.setPadding(new Insets(10));

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