package org.example.main;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import login.pages.LoginPage;

import java.io.File;
import java.util.Objects;

public class MyProfile {
    // معلومات المستخدم
    private final String name = LoginPage.loggedInUser.getUsername();
    private final String email = LoginPage.loggedInUser.getEmail();
    private final String phoneNum = LoginPage.loggedInUser.getNumPhone();
    private final String type = LoginPage.loggedInUser.getUserType();

    // عرض صفحة الملف الشخصي
    public Pane showMyProfilePage(Pane leftSidePane) {
        leftSidePane.getChildren().clear();

        VBox profilePane = createProfilePane();
        Pane topHeaderPane = createTopHeader();
        HBox mainContent = createMainContent();

        profilePane.getChildren().addAll(topHeaderPane, mainContent);
        profilePane.prefWidthProperty().bind(leftSidePane.widthProperty());
        profilePane.prefHeightProperty().bind(leftSidePane.heightProperty());

        // ضبط topHeaderPane ليأخذ 40% من ارتفاع الشاشة
        topHeaderPane.prefHeightProperty().bind(profilePane.heightProperty().multiply(0.2));

        // جعل mainContent يأخذ المساحة المتبقية (60% من ارتفاع الشاشة)
        mainContent.prefHeightProperty().bind(profilePane.heightProperty().multiply(0.8));

        topHeaderPane.prefWidthProperty().bind(profilePane.widthProperty());
        mainContent.prefWidthProperty().bind(profilePane.widthProperty());

        // إضافة تأثير تلاشي
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), profilePane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        leftSidePane.getChildren().add(profilePane);
        return leftSidePane;
    }

    // إنشاء شريط العنوان العلوي
    private Pane createTopHeader() {
        Pane topHeaderPane = new Pane();
        HBox topHeader = new HBox();

        ImageView topHeaderImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/topHeaderBg.png"))));
        topHeaderImageView.fitHeightProperty().bind(topHeaderPane.heightProperty());
        topHeaderImageView.fitWidthProperty().bind(topHeaderPane.widthProperty());

        Rectangle clip = new Rectangle();
        clip.setArcWidth(30);
        clip.setArcHeight(30);
        clip.heightProperty().bind(topHeaderImageView.fitHeightProperty());
        clip.widthProperty().bind(topHeaderImageView.fitWidthProperty());
        topHeaderImageView.setClip(clip);

        topHeader.getStyleClass().add("top-header");
        topHeader.prefHeightProperty().bind(topHeaderPane.heightProperty());
        topHeader.prefWidthProperty().bind(topHeaderPane.widthProperty());

        topHeaderPane.getChildren().addAll(topHeader, topHeaderImageView);
        return topHeaderPane;
    }

    // إنشاء لوحة الملف الشخصي
    private VBox createProfilePane() {
        VBox profilePane = new VBox(20);
        profilePane.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        profilePane.setId("myProfilePane");
        profilePane.setPadding(new Insets(15));
        return profilePane;
    }

    // إنشاء المحتوى الرئيسي
    private HBox createMainContent() {
        HBox mainContent = new HBox(20);
        VBox leftPanel = createLeftPanel();
        leftPanel.prefWidthProperty().bind(mainContent.widthProperty().multiply(0.7));
        leftPanel.prefHeightProperty().bind(mainContent.heightProperty());
        VBox rightPanel = createRightPanel();
        rightPanel.prefWidthProperty().bind(mainContent.widthProperty().multiply(0.3));
        rightPanel.prefHeightProperty().bind(mainContent.heightProperty());

        mainContent.getChildren().addAll(leftPanel, rightPanel);
        return mainContent;
    }

    // إنشاء اللوحة اليسرى
    private VBox createLeftPanel() {
        VBox leftPanel = new VBox(20);
        VBox createPersonalInfoSection =createPersonalInfoSection();
        VBox  createProfessionalInfoSection = createProfessionalInfoSection();
        createPersonalInfoSection.prefHeightProperty().bind(leftPanel.heightProperty());
        createProfessionalInfoSection.prefHeightProperty().bind(leftPanel.heightProperty());

        leftPanel.getChildren().addAll(
                createPersonalInfoSection,
                createProfessionalInfoSection
        );
        return leftPanel;
    }

    // إنشاء اللوحة اليمنى
    private VBox createRightPanel() {
        VBox rightPanel = new VBox(15);
        VBox createProfileCard = createProfileCard();
        VBox createLocationSection = createLocationSection();
        VBox createAboutYouSection = createAboutYouSection();
        rightPanel.setPadding(new Insets(10));
        rightPanel.getStyleClass().add("right-panel");
        createProfileCard.prefHeightProperty().bind(rightPanel.heightProperty());
        createLocationSection.prefHeightProperty().bind(rightPanel.heightProperty());
        createAboutYouSection.prefHeightProperty().bind(rightPanel.heightProperty());


        rightPanel.getChildren().addAll(
                createProfileCard,
                createLocationSection,
                createAboutYouSection
        );

        return rightPanel;
    }

    // إنشاء قسم المعلومات الشخصية
    private VBox createPersonalInfoSection() {
        VBox section = new VBox(10);
        section.setPadding(new Insets(10,10,10,10));
        section.getStyleClass().add("info-section");

        Label sectionTitle = new Label("معلومات شخصية");
        sectionTitle.getStyleClass().add("section-title");

        GridPane grid = new GridPane();
        grid.setHgap(25);
        grid.setVgap(30);

        // Create the text fields
        TextField nameField = new TextField(name);
        TextField emailField = new TextField(email);
        TextField phoneNumField = new TextField(phoneNum);
        TextField typeField = new TextField(type);

        // Make the fields non-editable by default
        nameField.setEditable(false);
        emailField.setEditable(false);
        phoneNumField.setEditable(false);
        typeField.setEditable(false);

        // Add a 'blocked' style class for hover effect
        nameField.getStyleClass().add("field-value");
        emailField.getStyleClass().add("field-value");
        phoneNumField.getStyleClass().add("field-value");
        typeField.getStyleClass().add("field-value");

        // Add fields to the grid in the desired layout
        addField(grid, 0, "الأسم", nameField, 0);         // First row, first column
        addField(grid, 0, "البريد الألكتروني", emailField, 2); // First row, second column
        addField(grid, 1, "رقم الهاتف", phoneNumField, 0); // Second row, first column
        addField(grid, 1, "نوع الحساب", typeField, 2);     // Second row, second column

        // Create the edit button
        Button editButton = new Button("تعديل");
        editButton.getStyleClass().add("edit-button");

        // Set an action to toggle the editability of the fields when clicking 'تعديل'
        editButton.setOnAction(e -> {
            boolean isEditable = nameField.isEditable();  // Check if currently editable
            nameField.setEditable(!isEditable);
            emailField.setEditable(!isEditable);
            phoneNumField.setEditable(!isEditable);
            typeField.setEditable(!isEditable);

            // Optionally change the button text to reflect the action
            if (isEditable) {
                editButton.setText("تعديل");
            } else {
                editButton.setText("حفظ");
            }
        });

        // Create a title box
        HBox titleBox = new HBox(sectionTitle, editButton);
        titleBox.setAlignment(Pos.CENTER_LEFT);
        titleBox.setSpacing(10);

        section.getChildren().addAll(titleBox, grid);
        return section;
    }



    // إنشاء قسم المعلومات المهنية
    private VBox createProfessionalInfoSection() {
        VBox section = new VBox(10);
        section.setPadding(new Insets(10));
        section.getStyleClass().add("info-section");

        Label sectionTitle = new Label("معلومات مهنية");
        sectionTitle.getStyleClass().add("section-title");

        GridPane grid = new GridPane();
        grid.setHgap(25);
        grid.setVgap(30);

        // Create the text fields (initially non-editable)
        TextField departmentField = new TextField(); // empty field for "القسم"
        departmentField.getStyleClass().add("field-value");
        departmentField.setEditable(false);

        TextField teamField = new TextField("Product hunt");
        teamField.getStyleClass().add("field-value");

        teamField.setEditable(false);

        TextField roleField = new TextField("UX UI Designer");
        roleField.getStyleClass().add("field-value");

        roleField.setEditable(false);

        TextField startDateField = new TextField("Aug 15, 2018");
        startDateField.getStyleClass().add("field-value");
        startDateField.setEditable(false);

        // Add fields to the grid using the updated addField method
        addField(grid, 0, "القسم", departmentField,0);
        addField(grid, 0, "الفريق", teamField,2);
        addField(grid, 1, "الدور", roleField,0);
        addField(grid, 1, "تاريخ البدء", startDateField,2);

        // Create the edit button
        Button editButton = new Button("تعديل");
        editButton.getStyleClass().add("edit-button");

        // Toggle the editable state of the text fields when clicking 'تعديل'
        editButton.setOnAction(e -> {
            boolean isEditable = departmentField.isEditable();
            departmentField.setEditable(!isEditable);
            teamField.setEditable(!isEditable);
            roleField.setEditable(!isEditable);
            startDateField.setEditable(!isEditable);

            // Optionally change the button text
            if (isEditable) {
                editButton.setText("تعديل");
            } else {
                editButton.setText("حفظ");
            }
        });

        HBox titleBox = new HBox(sectionTitle, editButton);
        titleBox.setAlignment(Pos.CENTER_LEFT);
        titleBox.setSpacing(10);

        section.getChildren().addAll(titleBox, grid);
        return section;
    }


    // إنشاء بطاقة الملف الشخصي
    private VBox createProfileCard() {
        VBox card = new VBox(10);
        card.setPadding(new Insets(10));
        card.getStyleClass().add("profile-card");

        ImageView profilePic = createProfilePicture();
        Label nameLabel = new Label(name);
        nameLabel.getStyleClass().add("profile-name");

        HBox roleTags = new HBox(10);
        Label studentTag = new Label("طالب");
        Label freelancerTag = new Label("مستقل");
        studentTag.getStyleClass().add("role-tag");
        freelancerTag.getStyleClass().add("role-tag");

        roleTags.getChildren().addAll(studentTag, freelancerTag);




        card.getChildren().addAll(profilePic, nameLabel, roleTags);
        return card;
    }

    // إنشاء صورة الملف الشخصي
    private ImageView createProfilePicture() {
        ImageView profilePic = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/Pictures/aaa.jpg")).toExternalForm()));
        profilePic.setFitHeight(100);
        profilePic.setFitWidth(100);

        Circle clip = new Circle(50, 50, 50);
        profilePic.setClip(clip);
        profilePic.getStyleClass().add("profile-pic");

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        profilePic.setOnMouseClicked(_ -> {
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                Image newImage = new Image(selectedFile.toURI().toString());
                profilePic.setImage(newImage);
            }
        });

        return profilePic;
    }

    // إنشاء قسم الموقع
    private VBox createLocationSection() {
        VBox section = new VBox(10);
        section.setPadding(new Insets(10));
        section.getStyleClass().add("info-section");

        Label sectionTitle = new Label("الموقع");
        sectionTitle.getStyleClass().add("section-title");

        TextField locationField = new TextField("Evergreen Meadows 12345");
        locationField.getStyleClass().add("location-field");

        section.getChildren().addAll(sectionTitle, locationField);
        return section;
    }
    private VBox createAboutYouSection() {
        VBox section = new VBox(10);
        section.setPadding(new Insets(10));
        section.getStyleClass().add("info-section");

        Label sectionTitle = new Label("نبذة عني");
        sectionTitle.getStyleClass().add("section-title");

        TextArea aboutYouArea = new TextArea();

        // Enable word wrapping
        aboutYouArea.setWrapText(true);

        // Set a preferred size for the text area
        aboutYouArea.setPrefColumnCount(6);
        aboutYouArea.setPrefRowCount(5);

        aboutYouArea.getStyleClass().add("about-you-area");

        // Limit the input to 250 characters
        aboutYouArea.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 100) {
                aboutYouArea.setText(newValue.substring(0, 100)); // Trim text to 250 characters
            }
        });

        section.getChildren().addAll(sectionTitle, aboutYouArea);
        return section;
    }


    // إضافة حقل إلى شبكة المعلومات
    private void addField(GridPane grid, int rowIndex, String label, TextField fieldValue, int columnIndex) {
        // Create a label for the field
        Label fieldLabel = new Label(label);
        fieldLabel.getStyleClass().add("field-label");

        // Add the label and field to the grid
        grid.add(fieldLabel, columnIndex, rowIndex);  // Dynamic column for the label
        grid.add(fieldValue, columnIndex + 1, rowIndex);  // Dynamic column for the field
    }

}
