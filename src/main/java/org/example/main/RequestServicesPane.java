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

        leftSidePane.getChildren().clear();

        //انشاء كائن لأستخدام بعض الأشياء
        RightSideBar rightSideBar = new RightSideBar(leftSidePane);

        // إنشاء لوحة المحتوى الرئيسية
        Pane contentPane = new Pane();
        contentPane.setId("contentPane");
        contentPane.prefWidthProperty().bind(leftSidePane.widthProperty());
        contentPane.prefHeightProperty().bind(leftSidePane.heightProperty());

        // إنشاء زر الانتقال إلى الملف الشخصي
        Button goToProfile = goToProfile();

        // إنشاء أزرار الخدمات
        String [][] topRightButtons = {
                {"خدماتي الحالية","nowService"},
                {"خدماتي السابقة","previousService"},
                {"تقييماتي","myRate"}
        };

        HBox topRightButtonBox = new HBox();
        topRightButtonBox.setSpacing(10.0);

        for (String[] topRightButton : topRightButtons) {
         Button topButton = createServicesButton(topRightButton[0],topRightButton[1]);
         topRightButtonBox.getChildren().add(topButton);
        }

        // ملء المساحة المتاحة
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        //وضع قسم الازرار العلوي في box
        HBox topButtons = new HBox(10);
        topButtons.setAlignment(Pos.CENTER);
        topButtons.setPadding(new Insets(20, 30, 0, 30));
        topButtons.getChildren().addAll(goToProfile, spacer, topRightButtonBox);

        //نص
        Label shortCuts = new Label("اختصارات");
        shortCuts.setId("shortCuts");

        // إضافة أزرار الاختصار
        HBox categoryButtonsBox = new HBox(10);
        categoryButtonsBox.setAlignment(Pos.CENTER);
        categoryButtonsBox.setPadding(new Insets(0, 50, 0, 50));

        String[][] shortcuts = {
                {"خدماتي", "services.png", "servicesButton"},
                {"عناويني", "map.png", "myLocationsButton"},
                {"كتالوج الخدمات", "category.png", "categoryButton"},
                {"خدماتي المفضلة", "favorite.png", "favoriteButton"}
        };

        for (String[] shortcut : shortcuts) {
            Button shortcutButton = createShortcutButton(shortcut[0], shortcut[1], shortcut[2], contentPane, rightSideBar);
            categoryButtonsBox.getChildren().add(shortcutButton);
        }

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

        //وضع البحث والنص في box
        HBox topFields = new HBox();
        topFields.setAlignment(Pos.CENTER);
        topFields.setSpacing(300);
        topFields.getChildren().addAll(serviceSearch, trendService);

        // خط يفصل
        Line line = new Line();
        line.setId("line");
        line.setStartX(85);
        line.setStartY(360);
        line.setEndX(840);
        line.setEndY(360);

        // إنشاء لوحات الخدمات المختلفة
        String[][] services = {
                {"كهربائي", "15$", "electrical.png", "electricPane"},
                {"سبّاك", "30$", "water_pane.png", "waterPane"},
                {"خياط", "45$", "tailor.png", "tailorPane"},
                {"نجار", "28$", "wood_man.png", "woodmenPane"},
                {"تنظيف", "20$", "clean.png", "cleaningPane"},
                {"تركيب أنظمة أمان", "50$", "security.png", "securityPane"},
                {"صيانة أجهزة كهربائية", "40$", "repair.png", "applianceRepairPane"}
        };

        VBox servicesBox = new VBox(10);
        //وضعهم في Vbox
        for (String[] service : services) {
            Pane servicePane = createServicesPane(service[0], service[1], service[2], rightSideBar, service[3]);
            servicesBox.getChildren().add(servicePane);
        }

        // إنشاء ScrollPane لتغليف لوحات الخدمات
        ScrollPane scrollPane = new ScrollPane(servicesBox);
        scrollPane.setStyle("-fx-background-color: transparent;");
        scrollPane.setPrefHeight(570);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(0, 30, 0, 30));

        //انشاء حاوية الصفحة لوضع جميع القطع
        VBox mainContainer = new VBox();
        mainContainer.setSpacing(30);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setPadding(new Insets(0, 50, 0, 50)); // إضافة الفراغ 50px من الجانبين
        mainContainer.prefWidthProperty().bind(leftSidePane.widthProperty());
        mainContainer.prefHeightProperty().bind(leftSidePane.heightProperty());
        mainContainer.getChildren().addAll(topButtons,shortCuts,categoryButtonsBox, topFields,line, scrollPane);//اضافتهم بالترتيب

        //تأثير عند فتح الصفحة
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), contentPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        contentPane.getChildren().addAll(mainContainer);
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
        button.setId(category);

        return button;
    }

    private void filterServices(String category) {
        // من هنا يمكنك إضافة الكود الخاص بتصفية الخدمات حسب الفئة المحددة
        // مثال: عرض الخدمات المتعلقة بالتصنيف "تنظيف" فقط
        System.out.println(category);
    }

    private Button goToProfile() {
        Button goToProfile = new Button();
        goToProfile.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 10px; -fx-background-radius: 10px;-fx-cursor: hand;"
                + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 5, 0, 0, 0);");
        goToProfile.setPrefSize(50,50);
        return goToProfile;
    }

    // دالة لإنشاء أزرار الخدمات
    private Button createServicesButton(String name,String idName) {
        Button servicesButton = new Button(name);
        servicesButton.setId(idName);
        servicesButton.setPrefSize(120,40);
        return servicesButton;
    }

    // دالة لإنشاء لوحة خدمة معينة
    private Pane createServicesPane(String title, String price, String imagePath, RightSideBar rightSideBar,String idName) {
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
        pane.setId(idName);

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