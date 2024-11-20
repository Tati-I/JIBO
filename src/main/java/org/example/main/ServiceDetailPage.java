package org.example.main;

import auth.FileBasedAuthenticationSystem;
import auth.User;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.text.TextAlignment;
import java.util.Objects;
import javafx.stage.Stage;

public class ServiceDetailPage extends VBox {
    private int currentPage = 0;
    private final int itemsPerPage = 10;
    private final String serviceTitle;

    public ServiceDetailPage(String serviceTitle, String imagePath) {
        this.serviceTitle = serviceTitle;
        FileBasedAuthenticationSystem.loadUsers();

        // إعدادات VBox الرئيسية
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(20));
        this.getStyleClass().add("detail-pane");

        // الحاوية العلوية
        HBox topBox = createTopBox(imagePath);

        // صندوق قائمة الخدمات داخل ScrollPane
        VBox servicesBox = new VBox(10);
        servicesBox.setPadding(new Insets(15));
        servicesBox.setStyle("-fx-background-color: linear-gradient(#e4e4e4,#E0E0E0);-fx-background-radius: 15");
        ScrollPane scrollPane = new ScrollPane(servicesBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");
        scrollPane.prefHeightProperty().bind(this.heightProperty().subtract(100));
        scrollPane.prefWidthProperty().bind(this.widthProperty().subtract(20));
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        loadServiceProviders(servicesBox);

        Button showMoreButton = new Button("عرض المزيد");
        showMoreButton.setOnAction(_ -> loadServiceProviders(servicesBox));

        Button stageCloseBtn = new Button("اغلاق");
        stageCloseBtn.setOnAction(_ ->{
            Stage currentStage = (Stage) stageCloseBtn.getScene().getWindow();
            // Close the current Stage
            currentStage.close();
        });

        HBox bottomBox = new HBox(10);
        bottomBox.setAlignment(Pos.TOP_CENTER);
        bottomBox.setSpacing(20);
        bottomBox.setStyle("-fx-background-color: transparent;");
        bottomBox.getChildren().addAll(showMoreButton, stageCloseBtn);

        this.getChildren().addAll(topBox, scrollPane, bottomBox);
    }

    private HBox createTopBox(String imagePath) {
        HBox topBox = new HBox();
        topBox.setSpacing(10);
        topBox.setAlignment(Pos.TOP_CENTER);
        topBox.prefWidthProperty().bind(this.widthProperty());

        // القسم الأيمن: الصورة والعنوان
        VBox leftSide = new VBox(10);
        leftSide.setAlignment(Pos.TOP_LEFT);
        //leftSide.prefWidthProperty().bind(this.widthProperty());

        leftSide.setId("leftSide");

        ImageView serviceImage = new ImageView(
                new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/" + imagePath)))
        );
        serviceImage.setPreserveRatio(true);
        serviceImage.setFitHeight(160);
        serviceImage.setFitWidth(160);

        Label titleLabel = new Label("خدمة " + serviceTitle);
        titleLabel.setId("titleLabel");

        leftSide.getChildren().addAll(serviceImage);

        // القسم الأيسر: الوصف والميزات
        VBox rightSide = new VBox(15);
        rightSide.setId("rightSide");
        rightSide.setAlignment(Pos.TOP_RIGHT);
        //rightSide.prefWidthProperty().bind(this.widthProperty());

        Label descriptionLabel = new Label(getServiceDescription(serviceTitle));
        descriptionLabel.setWrapText(true);
        descriptionLabel.setTextAlignment(TextAlignment.RIGHT);
        descriptionLabel.setId("descriptionLabel");

        VBox featuresBox = createFeaturesBox();

        rightSide.getChildren().addAll(descriptionLabel, featuresBox);
        HBox.setHgrow(leftSide, Priority.ALWAYS);
        HBox.setHgrow(rightSide, Priority.ALWAYS);

        topBox.getChildren().addAll(leftSide, rightSide);
        return topBox;
    }

    private VBox createFeaturesBox() {
        VBox featuresBox = new VBox(10);
        featuresBox.setAlignment(Pos.TOP_RIGHT);
        featuresBox.getChildren().addAll(
                new Label("مميزات الخدمة:"),
                new Label("✓ خدمة متوفرة على مدار 24 ساعة"),
                new Label("✓ فنيون معتمدون ومؤهلون"),
                new Label("✓ ضمان على جميع الأعمال"),
                new Label("✓ أسعار تنافسية")
        );
        return featuresBox;
    }

    private void loadServiceProviders(VBox servicesBox) {
        int startIndex = currentPage * itemsPerPage;
        int count = 0;

        for (User user : FileBasedAuthenticationSystem.getUsers().values()) {
            if (user.getUserType().equalsIgnoreCase(serviceTitle)) {
                if (count >= startIndex && count < startIndex + itemsPerPage) {
                    Pane userPane = createUserPane(user);
                    servicesBox.getChildren().add(userPane);
                }
                count++;
            }
        }
        currentPage++;
    }

    private Pane createUserPane(User user) {
        HBox pane = new HBox(10);
        pane.setId("electricPane");
        pane.setPadding(new Insets(10));

        VBox infoBox = new VBox(5);
        infoBox.setAlignment(Pos.CENTER_RIGHT);

        Label nameLabel = new Label("الاسم: " + user.getUsername());
        Label emailLabel = new Label("البريد الإلكتروني: " + user.getEmail());
        Label phoneLabel = new Label("رقم الهاتف: " + user.getNumPhone());

        infoBox.getChildren().addAll(nameLabel, emailLabel, phoneLabel);

        Button requestButton = new Button("أحجز الآن");
        requestButton.setId("requestButton");
        requestButton.setOnAction(_ -> showServiceRequestDialog(user));

        pane.getChildren().addAll(requestButton,infoBox);
        HBox.setHgrow(infoBox, Priority.ALWAYS);
        HBox.setHgrow(requestButton, Priority.ALWAYS);

        return pane;
    }

    private void showServiceRequestDialog(User serviceProvider) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("طلب خدمة");
        dialog.setHeaderText("طلب خدمة من " + serviceProvider.getUsername());

        ButtonType confirmButtonType = new ButtonType("تأكيد الطلب", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField address = new TextField();
        address.setPromptText("العنوان");
        DatePicker datePicker = new DatePicker();

        grid.add(new Label("العنوان:"), 0, 0);
        grid.add(address, 1, 0);
        grid.add(new Label("التاريخ:"), 0, 1);
        grid.add(datePicker, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == confirmButtonType) {
                return address.getText() + "," + datePicker.getValue();
            }
            return null;
        });

        dialog.showAndWait().ifPresent(result -> System.out.println("تم تقديم طلب الخدمة: " + result));
    }

    private String getServiceDescription(String serviceTitle) {
        return switch (serviceTitle) {
            case "كهربائي" -> "خدمات كهربائية احترافية تشمل التركيب والصيانة والإصلاح.";
            case "سبّاك" -> "حل جميع مشاكل المياه والصرف الصحي.";
            case "خياط" -> "خياطة وتعديل الملابس بدقة.";
            default -> "خدمة احترافية لتلبية احتياجاتك.";
        };
    }
}