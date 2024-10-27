package org.example.main;

import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
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
    private Map<String, Color[]> themes;

    public SettingPage() {
        initializeThemes();
    }

    private void initializeThemes() {
        themes = new HashMap<>();
        themes.put("Default", new Color[]{Color.WHITE, Color.LIGHTGRAY});
        themes.put("Sunset", new Color[]{Color.web("#FFA07A"), Color.web("#FF4500")});
        themes.put("Abstract", new Color[]{Color.web("#87CEEB"), Color.web("#4682B4")});
        themes.put("Dotted Indigo", new Color[]{Color.web("#4B0082"), Color.web("#8A2BE2")});
        themes.put("Super Gradient", new Color[]{Color.web("#FF1493"), Color.web("#FF69B4")});
        themes.put("Dotted Purple", new Color[]{Color.web("#8A2BE2"), Color.web("#9370DB")});
        themes.put("Oranger", new Color[]{Color.web("#FFA500"), Color.web("#FF6347")});
        themes.put("Simple Pink", new Color[]{Color.web("#FFC0CB"), Color.web("#FFB6C1")});
        themes.put("Lagoon", new Color[]{Color.web("#20B2AA"), Color.web("#008080")});
        themes.put("Dark Nature", new Color[]{Color.web("#2F4F4F"), Color.web("#556B2F")});
        themes.put("Fall Gradient", new Color[]{Color.web("#D2691E"), Color.web("#8B4513")});
        themes.put("Sea Glass", new Color[]{Color.web("#5F9EA0"), Color.web("#2E8B57")});
        themes.put("Blue Horizon", new Color[]{Color.web("#4169E1"), Color.web("#1E90FF")});
        themes.put("Sunrise", new Color[]{Color.web("#FF4500"), Color.web("#FFA07A")});
        themes.put("Aubergine", new Color[]{Color.web("#4B0082"), Color.web("#483D8B")});
        themes.put("Barbie", new Color[]{Color.web("#FF69B4"), Color.web("#FF1493")});
        themes.put("Mystic Ocean", new Color[]{Color.web("#00B4DB"), Color.web("#0083B0")});
        themes.put("Blush Pink", new Color[]{Color.web("#FFAFBD"), Color.web("#ffc3a0")});
        themes.put("Golden Sunset", new Color[]{Color.web("#FFD700"), Color.web("#FFA500")});
        themes.put("Cool Breeze", new Color[]{Color.web("#1f4037"), Color.web("#99f2c8")});
        themes.put("Lavender Skies", new Color[]{Color.web("#3a1c71"), Color.web("#d76d77")});
        themes.put("Pink Dream", new Color[]{Color.web("#de6262"), Color.web("#ffb88c")});
        themes.put("Emerald Sea", new Color[]{Color.web("#34e89e"), Color.web("#0f3443")});
        themes.put("Berry Purple", new Color[]{Color.web("#a18cd1"), Color.web("#fbc2eb")});
        themes.put("Solar Flare", new Color[]{Color.web("#f12711"), Color.web("#f5af19")});
        themes.put("Nightfall", new Color[]{Color.web("#141E30"), Color.web("#243B55")});

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
        for (Map.Entry<String, Color[]> theme : themes.entrySet()) {
            Button themeButton = createThemeButton(theme.getKey(), theme.getValue());
            themeButtonsPane.getChildren().add(themeButton);
        }

        VBox presetThemesSection = new VBox(10, presetThemesLabel, presetThemesDescription, themeButtonsPane);
        settingPane.getChildren().add(presetThemesSection);
    }

    private Button createThemeButton(String themeName, Color[] colors) {
        Button button = new Button();
        button.getStyleClass().add("theme-button2");

        Color startColor = colors[0];
        Color endColor = colors[1];

        button.setStyle("-fx-background-color: radial-gradient( center 60% 20%, radius 50%, "
                + toHexString(startColor) + ", " + toHexString(endColor) + ");"
                + "-fx-background-radius: 50%;"
                + "-fx-min-width: 50px;"
                + "-fx-min-height: 50px;"
                + "-fx-max-width: 50px;"
                + "-fx-max-height: 50px;"
                + "-fx-border-color: #ffffff;"
                + "-fx-border-width: 2px;"
                + "-fx-border-radius: 50%;"
                + "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.2), 10, 0, 0, 5);");

        button.setOnAction(e -> updateBackgroundColor(colors)); // تمرير مصفوفة الألوان
        button.setTooltip(new Tooltip(themeName));
        return button;
    }




    private void updateBackgroundColor(Color[] colors) {
        if (scene != null && colors.length >= 2) {
            String gradientStyle = String.format(
                    "-fx-background-color: linear-gradient(from 0%% 0%% to 100%% 100%%, %s, %s);",
                    toHexString(colors[0]), toHexString(colors[1])
            );
            scene.lookup("#rightSideBar").setStyle(gradientStyle);
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

        Button instagramButton = createSocialMediaButton("تسجيل دخول Instagram", "instagram.png");
        Button facebookButton = createSocialMediaButton("تسجيل دخول Facebook", "facebook.png");
        Button twitterButton = createSocialMediaButton("تسجيل دخول Twitter", "twitter.png");
        instagramButton.prefWidthProperty().bind(socialMediaBox.widthProperty());
        instagramButton.setStyle("-fx-background-color: linear-gradient( to right ,#515bcb,#be308f,#ea6145,#f8ca6b);");
        facebookButton.prefWidthProperty().bind(socialMediaBox.widthProperty());
        facebookButton.setStyle("-fx-background-color: linear-gradient( to right ,#0859da,#48a8f3);");
        twitterButton.prefWidthProperty().bind(socialMediaBox.widthProperty());
        twitterButton.setStyle("-fx-background-color: linear-gradient( to right ,#000000,#343434);");





        socialMediaBox.getChildren().addAll(socialMediaLabel, instagramButton, facebookButton, twitterButton);

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