package org.example.main;

import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
        contentPane.setStyle("-fx-background-color: #fdfdfd;"
                + "-fx-background-radius: 0 20 20 0; -fx-border-radius: 15px;"
                + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 15, 0, 0, 0);");

        leftSidePane.getChildren().clear();

        contentPane.setPrefSize(925, 784);

        // إنشاء خط فاصل
        Line line = new Line(100, 180, 840, 180);
        line.setStroke(Color.web("c0c0c0"));

        // إنشاء عنوان "الخدمات الشائعة"
        Label trendService = new Label("الخدمات الشائعة");
        trendService.setId("trendService");
        trendService.setLayoutX(620);
        trendService.setLayoutY(130);

        // إنشاء حقل البحث عن الخدمات
        TextField serviceSearch = new TextField();
        serviceSearch.setPromptText("البحث عن خدمة...");
        serviceSearch.setAlignment(Pos.CENTER_RIGHT);
        serviceSearch.setLayoutX(120);
        serviceSearch.setLayoutY(140);
        serviceSearch.setPrefSize(230, 30);
        serviceSearch.setId("serviceSearch");

        // إنشاء زر الانتقال إلى الملف الشخصي
        Button goToProfile = new Button("");
        goToProfile.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 10px; -fx-background-radius: 10px;-fx-cursor: hand;"
                + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 5, 0, 0, 0);");
        goToProfile.setPrefSize(50, 50);
        goToProfile.setLayoutX(120);
        goToProfile.setLayoutY(25);

        // إنشاء أزرار الخدمات
        Button nowService = createServicesButton("خدماتي الحالية", 735);
        Button previousService = createServicesButton("خدماتي السابقة", 610);
        Button myRate = createServicesButton("تقييماتي", 485);

        RightSideBar rightSideBar = new RightSideBar(leftSidePane);

        // إنشاء لوحات الخدمات المختلفة
        VBox servicesBox = new VBox(13); // استخدام VBox مع مسافة بين العناصر
        servicesBox.setLayoutX(120);
        servicesBox.setLayoutY(220);
        servicesBox.setStyle("-fx-background-color: #fbfbfb");

        Pane electricPane = createServicesPane("كهربائي", "15$", rightSideBar.loadImage("worker.png"), 0);
        electricPane.setId("electricPane");
        Pane waterPane = createServicesPane("سبّاك", "30$", rightSideBar.loadImage("worker.png"), 0);
        waterPane.setId("waterPane");
        Pane tailorPane = createServicesPane("خياط", "45$", rightSideBar.loadImage("worker.png"), 0);
        tailorPane.setId("tailorPane");
        Pane woodmenPane = createServicesPane("نجار", "28$", rightSideBar.loadImage("worker.png"), 0);
        woodmenPane.setId("woodmenPane");

        // إضافة الخدمات الجديدة
        Pane cleaningPane = createServicesPane("تنظيف", "20$", rightSideBar.loadImage("worker.png"), 0);
        cleaningPane.setId("cleaningPane");
        Pane securityPane = createServicesPane("تركيب أنظمة أمان", "50$", rightSideBar.loadImage("worker.png"), 0);
        securityPane.setId("securityPane");
        Pane applianceRepairPane = createServicesPane("صيانة أجهزة كهربائية", "40$", rightSideBar.loadImage("worker.png"), 0);
        applianceRepairPane.setId("applianceRepairPane");

        servicesBox.getChildren().addAll(electricPane, waterPane, tailorPane, woodmenPane, cleaningPane, securityPane, applianceRepairPane);

        // إنشاء ScrollPane لتغليف لوحات الخدمات
        ScrollPane scrollPane = new ScrollPane(servicesBox);
        scrollPane.setPrefSize(700, 550);
        scrollPane.setLayoutX(120);
        scrollPane.setLayoutY(200);
        scrollPane.setStyle("-fx-background-color: transparent;");

        // إضافة جميع العناصر إلى لوحة المحتوى
        contentPane.getChildren().addAll(line, trendService, serviceSearch, goToProfile, nowService, previousService, myRate, scrollPane);

        leftSidePane.getChildren().add(contentPane);
        return leftSidePane;
    }

    // دالة لإنشاء أزرار الخدمات
    private Button createServicesButton(String name, double x) {
        Button servicesButton = new Button(name);
        servicesButton.setId("servicesButton");
        servicesButton.setPrefSize(120, 40);
        servicesButton.setLayoutX(x);
        servicesButton.setLayoutY(25);
        return servicesButton;
    }

    // دالة لإنشاء لوحة خدمة معينة
    private Pane createServicesPane(String title, String price, Image image, double y) {
        Pane pane = new Pane();
        pane.setPrefSize(680, 110);

        // تحسين عنوان الخدمة
        Label titleLabel = new Label(title);
        titleLabel.setId("titleLabel");
        titleLabel.setPrefWidth(300);
        titleLabel.setLayoutX(250);
        titleLabel.setLayoutY(10);
        titleLabel.setAlignment(Pos.CENTER_RIGHT);
        titleLabel.setTextAlignment(TextAlignment.RIGHT);

        // تحسين سعر الخدمة
        Label priceLabel = new Label(price);
        priceLabel.setId("priceLabel");
        priceLabel.setLayoutX(520);
        priceLabel.setLayoutY(40);

        // تحسين صورة الخدمة
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setLayoutX(578);
        imageView.setLayoutY(5);
        imageView.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 12, 0, 0, 0);");

        // تحسين زر الطلب
        Button requestButton = createRequestButton();
        requestButton.setLayoutX(50); // تعديل موضع زر الطلب ليكون متناسقًا

        // إضافة نص توضيحي بجانب زر الطلب
        Label additionalInfo = new Label("استفسارات؟ اتصل بنا");
        additionalInfo.setId("additionalInfo");
        additionalInfo.setLayoutX(200); // تعديل موضع النص حسب الحاجة
        additionalInfo.setLayoutY(70);
        additionalInfo.setTextFill(Color.DARKGRAY);

        pane.getChildren().addAll(titleLabel, priceLabel, imageView, requestButton, additionalInfo);
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
        scaleUp.setToX(0.9);
        scaleUp.setToY(0.9);
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