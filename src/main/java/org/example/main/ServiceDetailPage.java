package org.example.main;

import auth.FileBasedAuthenticationSystem;
import auth.User;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
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
        FileBasedAuthenticationSystem.loadUsers();
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

        leftSide.getChildren().addAll(serviceImage, titleLabel);

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

        // إنشاء VBox و ScrollPane لعرض قائمة الخدمات
        VBox servicesBox = new VBox();
        servicesBox.setSpacing(10); // وضع مسافة بين الـ Panes

        ScrollPane scrollPane = new ScrollPane(servicesBox);
        scrollPane.setFitToWidth(true);
        scrollPane.prefHeightProperty().bind(this.heightProperty());
        scrollPane.prefWidthProperty().bind(this.widthProperty());

        for (User user : FileBasedAuthenticationSystem.getUsers().values()) {
            if (user.getUserType().equalsIgnoreCase(serviceTitle)) {
                Pane userPane = createUserPane(user);
                servicesBox.getChildren().add(userPane);
                userPane.prefWidthProperty().bind(this.widthProperty());
            }
        }
        // Add all components to the main VBox
        this.getChildren().addAll(topBox, scrollPane, buttonsBox);
    }

    private Pane createUserPane(User user) {
        HBox pane = new HBox();
        pane.setId("electricPane");

        // زر الطلب ووضعه على أقصى اليسار مع Tooltip
        Button requestButton = new Button("أحجز الاَن");
        requestButton.setId("requestButton");
        requestButton.setPrefSize(90, 70);
        Tooltip.install(requestButton, new Tooltip("أطلب الخدمة الآن!"));

        // إضافة Tagline تحت معلومات إضافية
        Label tagline = new Label("أفضل الخدمات بأقل الأسعار!");
        tagline.setStyle("-fx-text-fill: #0076a3; -fx-font-size: 12px;");

        VBox buttonInfoBox = new VBox(5);
        buttonInfoBox.setAlignment(Pos.CENTER_LEFT);
        buttonInfoBox.getChildren().addAll(requestButton, tagline);
        buttonInfoBox.prefWidthProperty().bind(pane.prefWidthProperty());

        Label nameLabel = new Label("الاسم: " + user.getUsername());
        nameLabel.setTextAlignment(TextAlignment.RIGHT);
        nameLabel.setLayoutX(10);
        nameLabel.setLayoutY(10);

        Label emailLabel = new Label("البريد الإلكتروني: " + user.getEmail());
        emailLabel.setTextAlignment(TextAlignment.RIGHT);
        emailLabel.setLayoutX(10);
        emailLabel.setLayoutY(30);

        Label phoneLabel = new Label("رقم الهاتف: " + user.getNumPhone());
        phoneLabel.setTextAlignment(TextAlignment.RIGHT);
        phoneLabel.setLayoutX(10);
        phoneLabel.setLayoutY(50);

        VBox contactBox = new VBox(5);
        contactBox.setAlignment(Pos.CENTER_RIGHT);
        contactBox.getChildren().addAll(nameLabel, emailLabel, phoneLabel);
        contactBox.prefWidthProperty().bind(pane.prefWidthProperty());


        pane.getChildren().addAll(buttonInfoBox,contactBox);

        return pane;
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
