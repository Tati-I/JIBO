package org.example.main;

import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SettingPage {
    private Scene scene;
    private Map<String, Color> themes;

    public SettingPage() {
        initializeThemes();
    }

    private void initializeThemes() {
        themes = new HashMap<>();
        themes.put("Default", Color.WHITE);
        themes.put("Sunset", Color.web("#FFA07A"));
        themes.put("Abstract", Color.web("#87CEEB"));
        themes.put("Dotted Indigo", Color.web("#4B0082"));
        themes.put("Super Gradient", Color.web("#FF1493"));
        themes.put("Dotted Purple", Color.web("#8A2BE2"));
        themes.put("Oranger", Color.web("#FFA500"));
        themes.put("Simple Pink", Color.web("#FFC0CB"));
        themes.put("Lagoon", Color.web("#20B2AA"));
        themes.put("Dark Nature", Color.web("#2F4F4F"));
        themes.put("Fall Gradient", Color.web("#D2691E"));
        themes.put("Sea Glass", Color.web("#5F9EA0"));
        themes.put("Blue Horizon", Color.web("#4169E1"));
        themes.put("Sunrise", Color.web("#FF4500"));
        themes.put("Aubergine", Color.web("#4B0082"));
        themes.put("Barbie", Color.web("#FF69B4"));
    }

    public Pane SettingPane(Pane leftSidePane) {
        leftSidePane.getChildren().clear();

        VBox settingPane = createSettingPane();
        Pane topHeaderPane = createTopHeader();
        settingPane.prefHeightProperty().bind(leftSidePane.heightProperty());
        settingPane.prefWidthProperty().bind(leftSidePane.widthProperty());

        settingPane.getChildren().add(topHeaderPane);

        addPresetThemesSection(settingPane);
        addAppsIntegrationSection(settingPane);

        leftSidePane.getChildren().add(settingPane);

        scene = leftSidePane.getScene();
        if (scene == null) {
            leftSidePane.sceneProperty().addListener((_, _, newValue) -> {
                if (newValue != null) {
                    scene = newValue;
                }
            });
        }

        return leftSidePane;
    }

    private Pane createTopHeader() {
        Pane topHeaderPane = new Pane();
        StackPane topHeader = new StackPane();

        ImageView topHeaderImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/ta.png"))));
        topHeaderImageView.fitHeightProperty().bind(topHeaderPane.heightProperty());
        topHeaderImageView.fitWidthProperty().bind(topHeaderPane.widthProperty());

        Rectangle clip = new Rectangle();
        clip.setArcWidth(30);
        clip.setArcHeight(30);
        clip.heightProperty().bind(topHeaderImageView.fitHeightProperty());
        clip.widthProperty().bind(topHeaderImageView.fitWidthProperty());
        topHeaderImageView.setClip(clip);

        Label profileLabel = new Label("الاعدادات");
        profileLabel.getStyleClass().add("section-title");
        profileLabel.setPadding(new Insets(10));

        topHeader.getChildren().addAll(topHeaderImageView, profileLabel);
        StackPane.setAlignment(profileLabel, Pos.CENTER_LEFT);

        topHeader.getStyleClass().add("top-header");
        topHeader.prefHeightProperty().bind(topHeaderPane.heightProperty());
        topHeader.prefWidthProperty().bind(topHeaderPane.widthProperty());

        topHeaderPane.getChildren().add(topHeader);
        return topHeaderPane;
    }

    private VBox createSettingPane() {
        VBox profilePane = new VBox(20);
        profilePane.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        profilePane.setId("myProfilePane");
        profilePane.setPadding(new Insets(15));
        return profilePane;
    }

    private void addPresetThemesSection(VBox settingPane) {
        Label presetThemesLabel = new Label("سمات خاصة");
        presetThemesLabel.getStyleClass().add("section-title");

        Label presetThemesDescription = new Label("اختر لونك بعناية فصحة عيونك تهمنا");
        presetThemesDescription.getStyleClass().add("section-description");

        FlowPane themeButtonsPane = new FlowPane();
        themeButtonsPane.prefWidthProperty().bind(settingPane.widthProperty().subtract(30));
        themeButtonsPane.setHgap(10);
        themeButtonsPane.setVgap(10);

        for (Map.Entry<String, Color> theme : themes.entrySet()) {
            Button themeButton = createThemeButton(theme.getKey(), theme.getValue());
            themeButtonsPane.getChildren().add(themeButton);
        }

        VBox presetThemesSection = new VBox(10, presetThemesLabel, presetThemesDescription, themeButtonsPane);
        settingPane.getChildren().add(presetThemesSection);
    }

    private Button createThemeButton(String themeName, Color color) {
        Button button = new Button(themeName);
        button.setStyle("-fx-background-color: " + toHexString(color) + ";");
        button.setOnAction(e -> updateBackgroundColor(color));
        return button;
    }

    private void updateBackgroundColor(Color color) {
        if (scene != null) {
            scene.lookup("#rightSideBar").setStyle("-fx-background-color: " + toHexString(color) + ";");
        }
    }

    private String toHexString(Color color) {
        return String.format("#%02X%02X%02X",
                (int)(color.getRed()*255),
                (int)(color.getGreen()*255),
                (int)(color.getBlue()*255));
    }

    private void addAppsIntegrationSection(VBox settingPane) {
        Label appsIntegrationLabel = new Label("ربط حسابك على التواصل الاجتماعي");
        appsIntegrationLabel.getStyleClass().add("section-title");

        Label appsIntegrationDescription = new Label("قم بالضغط على الحساب الذي تريد ربطه");
        appsIntegrationDescription.getStyleClass().add("section-description");

        VBox socialMediaBox = new VBox(10);
        socialMediaBox.getStyleClass().add("social-media-box");

        Label socialMediaLabel = new Label("التواصل الاجتماعي");
        socialMediaLabel.getStyleClass().add("subsection-title");

        Button linkedInButton = createSocialMediaButton("تسجيل دخول LinkedIn", "Clock.png");
        Button indeedButton = createSocialMediaButton("تسجيل دخول Facebook", "Clock.png");
        Button twitterButton = createSocialMediaButton("تسجيل دخول X", "Clock.png");
        linkedInButton.prefWidthProperty().bind(socialMediaBox.widthProperty());
        indeedButton.prefWidthProperty().bind(socialMediaBox.widthProperty());
        twitterButton.prefWidthProperty().bind(socialMediaBox.widthProperty());




        socialMediaBox.getChildren().addAll(socialMediaLabel, linkedInButton, indeedButton, twitterButton);

        VBox appsIntegrationSection = new VBox(10, appsIntegrationLabel, appsIntegrationDescription, socialMediaBox);
        settingPane.getChildren().add(appsIntegrationSection);
    }

    private Button createSocialMediaButton(String text, String iconPath) {
        Button button = new Button(text);
        button.getStyleClass().add("social-media-button");

        ImageView icon = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/" + iconPath))));
        icon.setFitHeight(20);
        icon.setFitWidth(20);

        button.setGraphic(icon);
        button.setGraphicTextGap(10);

        return button;
    }
}