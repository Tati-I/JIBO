package org.example.main;

import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.text.TextAlignment;

import java.util.Objects;

public class ServiceDetailPage extends VBox {
    public ServiceDetailPage(String serviceTitle, String price, String imagePath) {
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(20));
        this.getStyleClass().add("detail-pane");

        // Main top container
        HBox topBox = new HBox(20);
        topBox.setPrefHeight(100);  // تحديد ارتفاع ثابت للقسم العلوي
        topBox.setAlignment(Pos.TOP_CENTER);

        // Right side (Image and Title)
        VBox leftSide = new VBox(15);
        leftSide.setAlignment(Pos.TOP_LEFT);
        leftSide.setPrefWidth(200);  // عرض ثابت للجانب الأيمن
        leftSide.getStyleClass().add("right-side");

        // Service image
        ImageView serviceImage = new ImageView(
                new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/" + imagePath)))
        );
        serviceImage.setFitHeight(100);
        serviceImage.setFitWidth(100);
        serviceImage.getStyleClass().add("service-image");

        // Title with service name
        Label titleLabel = new Label("خدمة " + serviceTitle);
        titleLabel.getStyleClass().add("title-label");

        // Price label with specific styling
        Label priceLabel = new Label("السعر: " + price);
        priceLabel.getStyleClass().add("price-label");

        leftSide.getChildren().addAll(serviceImage, titleLabel, priceLabel);

        // Left side (Description and Features)
        VBox rightSide = new VBox(15);
        rightSide.setAlignment(Pos.TOP_RIGHT);
        rightSide.setPrefWidth(400);  // عرض ثابت للجانب الأيسر
        rightSide.getStyleClass().add("left-side");

        // Description
        String description = getServiceDescription(serviceTitle);
        Label descriptionLabel = new Label(description);
        descriptionLabel.setTextAlignment(TextAlignment.RIGHT);
        descriptionLabel.setWrapText(true);
        descriptionLabel.getStyleClass().add("description-label");

        // Features box
        VBox featuresBox = new VBox(10);
        featuresBox.setAlignment(Pos.TOP_RIGHT);
        featuresBox.getStyleClass().add("features-box");
        featuresBox.getChildren().addAll(
                new Label("مميزات الخدمة:"),
                new Label("✓ خدمة متوفرة على مدار 24 ساعة"),
                new Label("✓ فنيون معتمدون ومؤهلون"),
                new Label("✓ ضمان على جميع الأعمال"),
                new Label("✓ أسعار تنافسية")
        );

        rightSide.getChildren().addAll(descriptionLabel, featuresBox);

        // Add right and left sides to top box
        topBox.getChildren().addAll(leftSide, rightSide);

        // Buttons container
        HBox buttonsBox = new HBox(10);
        buttonsBox.setAlignment(Pos.CENTER);

        // Close button
        Button closeButton = new Button("إغلاق");
        closeButton.getStyleClass().add("close-button");
        closeButton.setOnAction(e -> getScene().getWindow().hide());

        buttonsBox.getChildren().addAll(closeButton);

        // Add all components to the main VBox
        this.getChildren().addAll(topBox, buttonsBox);
    }

    private String getServiceDescription(String serviceTitle) {
        return switch (serviceTitle) {
            case "كهربائي" -> "خدمات كهربائية احترافية تشمل التركيب والصيانة والإصلاح لجميع التمديدات الكهربائية المنزلية والتجارية.";
            case "سبّاك" -> "خدمات السباكة الشاملة لحل جميع مشاكل المياه والصرف الصحي، مع ضمان جودة العمل والمواد المستخدمة.";
            case "خياط" -> "خدمات خياطة وتعديل الملابس بدقة واحترافية، مع إمكانية التفصيل حسب الطلب وتقديم النصائح المتخصصة.";
            case "نجار" -> "خدمات نجارة متكاملة تشمل تصنيع وإصلاح الأثاث الخشبي، مع التركيز على الجودة والدقة في التنفيذ.";
            case "تنظيف" -> "خدمات تنظيف شاملة للمنازل والمكاتب، باستخدام أحدث المعدات ومواد التنظيف الآمنة والفعالة.";
            case "تركيب أنظمة أمان" -> "تركيب وصيانة أنظمة الأمان والمراقبة، مع توفير أحدث التقنيات لحماية ممتلكاتك.";
            case "صيانة أجهزة كهربائية" -> "خدمات صيانة وإصلاح جميع الأجهزة الكهربائية المنزلية، مع توفير قطع الغيار الأصلية.";
            default -> "خدمة احترافية لتلبية احتياجاتك بأعلى معايير الجودة.";
        };
    }
}