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
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.util.Objects;

public class MyProfile {
    public Pane showMyProfilePage(Pane leftSidePane) {
        leftSidePane.getChildren().clear();

        // Create the main profile pane
        VBox profilePane = new VBox(20);
        profilePane.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        profilePane.setId("myProfilePane");
        profilePane.prefWidthProperty().bind(leftSidePane.widthProperty());
        profilePane.prefHeightProperty().bind(leftSidePane.heightProperty());
        profilePane.setPadding(new Insets(20));
        profilePane.setAlignment(Pos.TOP_CENTER);


        HBox topHeader = createTopHeader();
        topHeader.prefHeightProperty().bind(leftSidePane.heightProperty().multiply(0.2));
        topHeader.prefWidthProperty().bind(leftSidePane.prefWidthProperty());



        HBox profileHeader = createProfileHeader();
        profileHeader.prefWidthProperty().bind(leftSidePane.widthProperty());
        profileHeader.prefHeightProperty().bind(leftSidePane.heightProperty().multiply(0.3));

        HBox infoBox = new HBox(20);
        infoBox.prefWidthProperty().bind(leftSidePane.widthProperty());
        infoBox.prefHeightProperty().bind(leftSidePane.heightProperty().multiply(0.6));

        VBox personalInfo = createPersonalInfoSection();
        personalInfo.prefWidthProperty().bind(leftSidePane.widthProperty().multiply(0.5));
        personalInfo.prefHeightProperty().bind(leftSidePane.heightProperty());

        VBox addressInfo = createAddressSection();
        addressInfo.prefWidthProperty().bind(leftSidePane.widthProperty().multiply(0.5));
        addressInfo.prefHeightProperty().bind(leftSidePane.heightProperty());

        infoBox.getChildren().addAll(addressInfo, personalInfo);
        infoBox.setStyle("-fx-background-color: #BBEAFF21");

        profilePane.getChildren().addAll(topHeader, profileHeader,infoBox);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), profilePane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        leftSidePane.getChildren().add(profilePane);
        return leftSidePane;
    }

    private HBox createTopHeader(){
        HBox topHeader = new HBox();
        topHeader.getStyleClass().add("top-header");
        VBox label = new VBox();
        Label profileLabel = new Label("ملفي الشخصي");
        profileLabel.getStyleClass().add("topHeader-title");
        profileLabel.setAlignment(Pos.CENTER_RIGHT);
        label.getChildren().add(profileLabel);

        topHeader.getChildren().add(label);
        return topHeader;
    }

    private HBox createProfileHeader() {
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(10));
        header.getStyleClass().add("profile-header");

        // Profile Picture Section
        HBox profileBox = new HBox();
        profileBox.setAlignment(Pos.CENTER_RIGHT);

        // ImageView for profile picture
        ImageView profilePic = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/Pictures/aaa.jpg")).toExternalForm()));
        profilePic.setFitHeight(40);
        profilePic.setFitWidth(40);
        Circle clip = new Circle(20, 20, 20); // Circle clip for rounded image
        profilePic.setClip(clip);
        profilePic.getStyleClass().add("profile-pic");
        profileBox.getChildren().addAll(profilePic);

        // FileChooser to choose a new profile picture
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        // Set action to open FileChooser when profile picture is clicked
        profilePic.setOnMouseClicked(e -> {
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                Image newImage = new Image(selectedFile.toURI().toString());
                profilePic.setImage(newImage); // Update the ImageView with the new image
            }
        });

        // Profile Info Section
        VBox profileInfo = new VBox(5);
        Label nameLabel = new Label("احمد قصاب");
        Label titleLabel = new Label("مطور العاب");
        Label locationLabel = new Label("الموقع");
        profileInfo.getChildren().addAll(nameLabel, titleLabel, locationLabel);
        profileInfo.setAlignment(Pos.CENTER);

        // Edit button for profile info
        Button editButton = new Button("تعديل");
        editButton.getStyleClass().add("edit-button");

        // Add all elements to the header
        header.getChildren().addAll(profileBox, profileInfo, editButton);
        return header;
    }



    private VBox createPersonalInfoSection() {
        VBox section = new VBox(10);
        section.getStyleClass().add("info-section");

        Label sectionTitle = new Label("المعلومات الشخصية");
        sectionTitle.getStyleClass().add("section-title");

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(10);

        addField(grid, 0, "الاسم", "احمد");
        addField(grid, 1, "الكنية", "قصاب");
        addField(grid, 2, "الايميل", "abcd1234@gmail.com");
        addField(grid, 3, "رقم الهاتف", "1234567890");
        addField(grid, 4, "السيرة الذاتية", "انا مهندس حاسوب ومصمم");

        Button editButton = new Button("تعديل");
        editButton.getStyleClass().add("edit-button");

        HBox titleBox = new HBox(sectionTitle, editButton);
        titleBox.setAlignment(Pos.CENTER_LEFT);
        titleBox.setSpacing(10);


        section.getChildren().addAll(titleBox, grid);
        return section;
    }

    private VBox createAddressSection() {
        VBox section = new VBox(10);
        section.getStyleClass().add("info-section");

        Label sectionTitle = new Label("العنوان");
        sectionTitle.getStyleClass().add("section-title");

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(10);

        addField(grid, 0, "المدينة", "اسطنبول");
        addField(grid, 1, "الدولة", "تركيا");
        addField(grid, 2, "رقم المنشئ", "5123648728");
        addField(grid, 3, "رقم الشخصي", "324A3DR2B3");

        Button editButton = new Button("تعديل");
        editButton.getStyleClass().add("edit-button");

        HBox titleBox = new HBox(sectionTitle, editButton);
        titleBox.setAlignment(Pos.CENTER_LEFT);
        titleBox.setSpacing(10);

        section.getChildren().addAll(titleBox, grid);
        return section;
    }

    private void addField(GridPane grid, int row, String label, String value) {
        Label fieldLabel = new Label(label);
        fieldLabel.getStyleClass().add("field-label");
        Label fieldValue = new Label(value);
        fieldValue.getStyleClass().add("field-value");

        grid.add(fieldLabel, 0, row);
        grid.add(fieldValue, 1, row);
    }
}