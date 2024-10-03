package org.example.main;

import bar.right.RightSideBar;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.util.Objects;

public class RequestServicesPane {

    // دالة لإنشاء وعرض صفحة الخدمات
    public Pane showServicesPage(Pane leftSidePane) {

        leftSidePane.getChildren().clear();
        VBox mainContainer = new VBox();

        //انشاء كائن لأستخدام بعض الأشياء
        RightSideBar rightSideBar = new RightSideBar(leftSidePane);

        // إنشاء لوحة المحتوى الرئيسية
        VBox requestServicePane = new VBox(30);
        requestServicePane.setId("requestServicePane");
        requestServicePane.prefWidthProperty().bind(leftSidePane.widthProperty());
        requestServicePane.prefHeightProperty().bind(leftSidePane.heightProperty());

        HBox topButtons = new HBox();
        topButtons.setAlignment(Pos.CENTER);
        topButtons.setPadding(new Insets(30, 80, 30, 80));
        topButtons.prefWidthProperty().bind(requestServicePane.widthProperty());
        topButtons.setStyle("-fx-background-color: rgba(255,255,255,0.98) ;-fx-background-radius: 0 15 15 0;-fx-border-radius: 15px");

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
        content.setAlignment(Pos.TOP_LEFT);
        content.getChildren().addAll(logoView, welcomeLabel);
        content.prefWidthProperty().bind(topButtons.widthProperty());

        Button goToProfile = goToProfile();
        goToProfile.setOnAction(_ ->{
            MyProfile myProfile = new MyProfile();
            myProfile.showMyProfilePage(leftSidePane);
        });
        HBox profileBox = new HBox();
        profileBox.setAlignment(Pos.TOP_RIGHT);
        profileBox.getChildren().addAll(goToProfile);

        // إنشاء أزرار الخدمات
        String [][] topRightButtons = {
                {"خدماتي الحالية","nowService"},
                {"خدماتي السابقة","previousService"},
                {"تقييماتي","myRate"}
        };

        HBox topRightButtonBox = new HBox(10);
        topRightButtonBox.setAlignment(Pos.TOP_RIGHT);

        for (String[] topRightButton : topRightButtons) {
         Button topButton = createServicesButton(topRightButton[0],topRightButton[1]);
         topRightButtonBox.getChildren().add(topButton);
        }

        HBox rightBox = new HBox(10);
        rightBox.setAlignment(Pos.TOP_RIGHT);
        rightBox.prefWidthProperty().bind(topButtons.widthProperty());
        rightBox.getChildren().addAll(topRightButtonBox,profileBox);

        topButtons.getChildren().addAll(content,rightBox);

        //نص
        Label shortCuts = new Label("اختصارات");
        shortCuts.setId("shortCuts");
        shortCuts.setAlignment(Pos.CENTER_RIGHT);

        HBox shortCutsBox = new HBox();
        shortCutsBox.setAlignment(Pos.CENTER_RIGHT);
        shortCutsBox.getChildren().add(shortCuts);
        shortCutsBox.setPadding(new Insets(0, 30, 0, 30));
        shortCutsBox.prefWidthProperty().bind(mainContainer.widthProperty());

        Line line1 = new Line();
        line1.setId("line");
        line1.setStartY(360);
        line1.setEndX(915);
        line1.setEndY(360);
        // إضافة أزرار الاختصار
        HBox categoryButtonsBox = new HBox(10);
        categoryButtonsBox.setAlignment(Pos.CENTER);
        categoryButtonsBox.setPadding(new Insets(0, 50, 0, 50));
        categoryButtonsBox.prefWidthProperty().bind(mainContainer.widthProperty());
        categoryButtonsBox.prefHeightProperty().bind(mainContainer.heightProperty());

        String[][] shortcuts = {
                {"خدماتي", "services.png", "servicesButton"},
                {"عناويني", "map.png", "myLocationsButton"},
                {"كتالوج الخدمات", "category.png", "categoryButton"},
                {"خدماتي المفضلة", "favorite.png", "favoriteButton"}
        };

        for (String[] shortcut : shortcuts) {
            Button shortcutButton = createShortcutButton(shortcut[0], shortcut[1], shortcut[2], requestServicePane, rightSideBar);
            shortcutButton.setEffect(shadow);
            categoryButtonsBox.getChildren().add(shortcutButton);
        }
        HBox topFields = new HBox();
        topFields.setPadding(new Insets(0,30,0,30));
        // إنشاء عنوان "الخدمات الشائعة"
        Label trendService = new Label("الخدمات الشائعة");
        trendService.setAlignment(Pos.TOP_RIGHT);
        trendService.setId("trendService");

        HBox trendServiceBox = new HBox();
        trendServiceBox.setAlignment(Pos.TOP_RIGHT);
        trendServiceBox.getChildren().add(trendService);
        trendServiceBox.prefWidthProperty().bind(topFields.widthProperty());

        // إنشاء حقل البحث عن الخدمات
        TextField serviceSearch = new TextField();
        serviceSearch.setPromptText("البحث عن خدمة...");
        serviceSearch.setAlignment(Pos.TOP_LEFT);
        serviceSearch.setPrefSize(230, 30);
        serviceSearch.setId("serviceSearch");

        HBox serviceSearchBox = new HBox();
        serviceSearchBox.setAlignment(Pos.TOP_LEFT);
        serviceSearchBox.getChildren().add(serviceSearch);
        serviceSearchBox.prefWidthProperty().bind(topFields.widthProperty());

        //وضع البحث والنص في box
        topFields.prefWidthProperty().bind(mainContainer.widthProperty());
        topFields.getChildren().addAll(serviceSearchBox, trendServiceBox);

        // خط يفصل
        Line line = new Line();
        line.setId("line");
        line.setStartY(360);
        line.setEndX(915);
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

        // إنشاء ScrollPane لتغليف لوحات الخدمات
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background-color: transparent;");
        scrollPane.setFitToWidth(true);
        scrollPane.prefWidthProperty().bind(mainContainer.widthProperty());
        scrollPane.setPadding(new Insets(0, 30, 0, 30));

        VBox servicesBox = new VBox(10);
        servicesBox.prefWidthProperty().bind(scrollPane.widthProperty());
        //وضعهم في Vbox
        for (String[] service : services) {
            Pane servicePane = createServicesPane(service[0], service[1], service[2], rightSideBar, service[3],leftSidePane);
            servicePane.prefWidthProperty().bind(servicesBox.widthProperty());

            servicesBox.getChildren().add(servicePane);
        }
        scrollPane.setContent(servicesBox);

        //انشاء حاوية الصفحة لوضع جميع القطع
        mainContainer.setSpacing(30);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setPadding(new Insets(0, 50, 0, 50)); // إضافة الفراغ 50px من الجانبين
        mainContainer.prefWidthProperty().bind(requestServicePane.widthProperty());
        mainContainer.prefHeightProperty().bind(requestServicePane.heightProperty());
        mainContainer.getChildren().addAll(shortCutsBox,categoryButtonsBox, topFields,scrollPane);//اضافتهم بالترتيب

        //تأثير عند فتح الصفحة
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), requestServicePane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        requestServicePane.getChildren().addAll(topButtons,mainContainer);
        leftSidePane.getChildren().add(requestServicePane);
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
        button.setPrefHeight(350);
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
    private Button createServicesButton(String name,String idName) {
        Button servicesButton = new Button(name);
        servicesButton.setId(idName);
        servicesButton.setPrefWidth(1220);
        return servicesButton;
    }

    // دالة لإنشاء لوحة خدمة معينة
    private Pane createServicesPane(String title, String price, String imagePath, RightSideBar rightSideBar,String idName,Pane leftSidePane) {
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
        Button requestButton = createRequestButton(leftSidePane);
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
    private Button createRequestButton(Pane leftSidePane) {
        Button requestButton = new Button("أطلب الاَن");
        requestButton.setId("requestButton");
        requestButton.setPrefSize(130, 110);
        requestButton.setOnMouseEntered(_ -> createUpAnimateButton(requestButton));
        requestButton.setOnMouseExited(_ -> createDownAnimateButton(requestButton));
        requestButton.setOnAction(_ ->{
            ServiceDetailPage serviceDetailPage = new ServiceDetailPage();
            serviceDetailPage.serviceDetailPage(leftSidePane);

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