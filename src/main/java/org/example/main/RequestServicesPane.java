package org.example.main;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class RequestServicesPane {

    // دالة لإنشاء وعرض صفحة الخدمات
    public Pane showServicesPage(Pane leftSidePane) {

        // إنشاء لوحة المحتوى الرئيسية
        Pane contentPane = new Pane();
        leftSidePane.getChildren().clear();
        contentPane.setPrefSize(934, 784);
        contentPane.setId("contentPane");

        // إنشاء زر الانتقال إلى الملف الشخصي
        Button goToProfile = goToProfile();

        // إنشاء أزرار الخدمات
        Button nowService = createServicesButton("خدماتي الحالية");
        Button previousService = createServicesButton("خدماتي السابقة");
        Button myRate = createServicesButton("تقييماتي");

        HBox topRightButtons = new HBox();
        topRightButtons.setSpacing(10.0);
        topRightButtons.getChildren().addAll(nowService, previousService, myRate);

        HBox topButtons = new HBox();
        topButtons.setPadding(new Insets(20, 20, 20, 120));
        topButtons.setSpacing(300);
        topButtons.getChildren().addAll(goToProfile, topRightButtons);


        ////////////////////////////////////////////////////////////////////////////////

        // إنشاء عنوان "الخدمات الشائعة"
        Label trendService = new Label("الخدمات الشائعة");
        trendService.setId("trendService");

        // إنشاء حقل البحث عن الخدمات
        TextField serviceSearch = new TextField();
        serviceSearch.setPromptText("البحث عن خدمة...");
        serviceSearch.setAlignment(Pos.CENTER_RIGHT);
        serviceSearch.setPrefSize(230, 30);
        serviceSearch.setId("serviceSearch");

        HBox topFields = new HBox();
        topFields.setPadding(new Insets(20, 20, 20, 120));
        topFields.setSpacing(300);
        topFields.getChildren().addAll(serviceSearch, trendService);


        ////////////////////////////////////////////////////////////////////////////////

        // إنشاء خط فاصل

        RightSideBar rightSideBar = new RightSideBar(leftSidePane);

        // إنشاء لوحات الخدمات المختلفة
        Pane electricPane = createServicesPane("كهربائي", "15$", rightSideBar.loadImage("worker.png"));
        electricPane.setId("electricPane");

        Pane waterPane = createServicesPane("سبّاك", "30$", rightSideBar.loadImage("worker.png"));
        waterPane.setId("waterPane");

        Pane tailorPane = createServicesPane("خياط", "45$", rightSideBar.loadImage("worker.png"));
        tailorPane.setId("tailorPane");

        Pane woodmenPane = createServicesPane("نجار", "28$", rightSideBar.loadImage("worker.png"));
        woodmenPane.setId("woodmenPane");

        Pane cleaningPane = createServicesPane("تنظيف", "20$", rightSideBar.loadImage("worker.png"));
        cleaningPane.setId("cleaningPane");

        Pane securityPane = createServicesPane("تركيب أنظمة أمان", "50$", rightSideBar.loadImage("worker.png"));
        securityPane.setId("securityPane");

        Pane applianceRepairPane = createServicesPane("صيانة أجهزة كهربائية", "40$", rightSideBar.loadImage("worker.png"));
        applianceRepairPane.setId("applianceRepairPane");

        VBox servicesBox = new VBox(10); // استخدام VBox مع مسافة بين العناصر
        servicesBox.setStyle("-fx-background-color: transparent;");
        servicesBox.getChildren().addAll(electricPane, waterPane, tailorPane, woodmenPane, cleaningPane, securityPane, applianceRepairPane);

        // إنشاء ScrollPane لتغليف لوحات الخدمات
        ScrollPane scrollPane = new ScrollPane(servicesBox);
        scrollPane.setStyle("-fx-background-color: transparent;");
        scrollPane.setPrefSize(934,570);
        scrollPane.setPadding(new Insets(0,20,20,133));

        VBox mainContainer = new VBox();
        mainContainer.setSpacing(30);
        mainContainer.getChildren().addAll(topButtons, topFields, scrollPane);

        // إضافة جميع العناصر إلى لوحة المحتوى
        contentPane.getChildren().addAll(mainContainer);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), contentPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        leftSidePane.getChildren().add(contentPane);
        return leftSidePane;
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
        servicesButton.setPrefSize(120, 40);
        return servicesButton;
    }

    // دالة لإنشاء لوحة خدمة معينة
    private Pane createServicesPane(String title, String price, Image image) {
        // عنوان الخدمة
        Label titleLabel = new Label(title);
        titleLabel.setId("titleLabel");
        titleLabel.setPrefWidth(295);
        titleLabel.setAlignment(Pos.CENTER_RIGHT);

        // سعر الخدمة
        Label priceLabel = new Label(price);
        priceLabel.setPrefWidth(295);
        priceLabel.setAlignment(Pos.CENTER_RIGHT);
        priceLabel.setId("priceLabel");

        VBox titlePriceBox = new VBox();
        titlePriceBox.setSpacing(10);
        titlePriceBox.getChildren().addAll(titleLabel, priceLabel);

        ////////////////////////////////////////////////////////////

        // صورة الخدمة
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 12, 0, 0, 0);");

        HBox imageBox = new HBox();
        imageBox.getChildren().addAll(imageView);

        ////////////////////////////////////////////////////////////

        // زر الطلب
        Button requestButton = createRequestButton();

        Label additionalInfo = new Label("استفسارات؟ اتصل بنا");
        additionalInfo.setId("additionalInfo");

        HBox leftButtons = new HBox();
        leftButtons.getChildren().addAll(requestButton, additionalInfo);

        // ضبط المسافات المتساوية
        HBox pane = new HBox();
        pane.setPrefSize(680, 110);

        pane.getChildren().addAll(leftButtons,titlePriceBox,imageBox);
        return pane;
    }


    // دالة لإنشاء زر الطلب
    private Button createRequestButton() {
        Button requestButton = new Button("أطلب الاَن");
        requestButton.setId("requestButton");
        requestButton.setPrefSize(130,110);
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