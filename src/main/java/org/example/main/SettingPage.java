package org.example.main;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Objects;

public class SettingPage {
    private static boolean isNightMode = false;
    private Scene scene;

    public Pane SettingPane(Pane leftSidePane) {
        leftSidePane.getChildren().clear();

        VBox settingPane = createSettingPane();
        Pane topHeaderPane = createTopHeader();
        settingPane.prefHeightProperty().bind(leftSidePane.heightProperty());
        settingPane.prefWidthProperty().bind(leftSidePane.widthProperty());

        settingPane.getChildren().add(topHeaderPane);

        // إضافة زر التبديل بين وضع الليل والنهار
        addNightModeToggleButton(settingPane);

        // إضافة ColorPicker لتغيير لون الواجهة
        addColorPicker(settingPane);

        leftSidePane.getChildren().add(settingPane);

        scene = leftSidePane.getScene();
        if (scene == null) {
            leftSidePane.sceneProperty().addListener((_, _, newValue) -> {
                if (newValue != null) {
                    scene = newValue;
                    updateStyles(isNightMode);
                }
            });
        } else {
            updateStyles(isNightMode);
        }

        return leftSidePane;
    }

    private Pane createTopHeader() {
        Pane topHeaderPane = new Pane();
        StackPane topHeader = new StackPane();  // تغيير إلى StackPane

        // إضافة صورة الخلفية
        ImageView topHeaderImageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/ta.png"))));
        topHeaderImageView.fitHeightProperty().bind(topHeaderPane.heightProperty());
        topHeaderImageView.fitWidthProperty().bind(topHeaderPane.widthProperty());

        // إعداد الـ clip لجعل الحواف مستديرة
        Rectangle clip = new Rectangle();
        clip.setArcWidth(30);
        clip.setArcHeight(30);
        clip.heightProperty().bind(topHeaderImageView.fitHeightProperty());
        clip.widthProperty().bind(topHeaderImageView.fitWidthProperty());
        topHeaderImageView.setClip(clip);

        // إضافة كلمة "الاعدادات"
        Label profileLabel = new Label("الاعدادات");
        profileLabel.getStyleClass().add("section-title");
        profileLabel.setPadding(new Insets(10));

        // إعداد StackPane ووضع النص فوق الصورة
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

    // ميثود لإضافة زر التبديل بين وضع الليل والنهار
    private void addNightModeToggleButton(VBox settingPane) {
        ToggleButton toggleButton = new ToggleButton();

        // Binding width and height of the button
        toggleButton.prefWidthProperty().bind(settingPane.widthProperty().multiply(0.1));  // 10% of parent width
        toggleButton.prefHeightProperty().bind(settingPane.heightProperty().multiply(0.05));  // 5% of parent height

        toggleButton.getStyleClass().add("mode-toggle-button");

        Image sunImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/Sun.png")));
        Image moonImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/Moon.png")));

        ImageView imageView = new ImageView(sunImage);

        // Binding the image size to the button size
        imageView.fitWidthProperty().bind(toggleButton.prefWidthProperty().multiply(0.6)); // 60% of button width
        imageView.fitHeightProperty().bind(toggleButton.prefHeightProperty().multiply(0.6)); // 60% of button height

        toggleButton.setGraphic(imageView);

        // تعيين الصورة المناسبة بناءً على وضع الليل الحالي
        if (isNightMode) {
            imageView.setImage(moonImage);
        } else {
            imageView.setImage(sunImage);
        }

        toggleButton.setSelected(isNightMode);

        toggleButton.setOnAction(_ -> {
            isNightMode = !isNightMode;
            if (isNightMode) {
                imageView.setImage(moonImage);
            } else {
                imageView.setImage(sunImage);
            }
            updateStyles(isNightMode);
        });

        settingPane.getChildren().add(toggleButton);
    }

    // ميثود لإضافة ColorPicker لتغيير لون الواجهة
    private void addColorPicker(VBox settingPane) {
        ColorPicker colorPicker = new ColorPicker();

        // Bind the width of the ColorPicker to the parent width (e.g., 15% of the parent VBox width)
        colorPicker.prefWidthProperty().bind(settingPane.widthProperty().multiply(0.15));

        // Adjust the ColorPicker to stay proportionally in place, no fixed layout coordinates
        VBox.setMargin(colorPicker, new Insets(20, 0, 0, 0)); // Add some top margin for spacing

        colorPicker.setValue(Color.WHITE); // Default color

        colorPicker.setOnAction(event -> {
            Color selectedColor = colorPicker.getValue();
            updateBackgroundColor(selectedColor);
        });

        settingPane.getChildren().add(colorPicker);
    }

    // تحديث لون الخلفية بناءً على اللون المختار
    private void updateBackgroundColor(Color color) {
        String colorHex = String.format("#%02X%02X%02X",
                (int)(color.getRed()*255),
                (int)(color.getGreen()*255),
                (int)(color.getBlue()*255));

        if (scene != null) {
            scene.lookup("#rightSideBar").setStyle("-fx-background-color: " + colorHex + ";");
        }
    }

    // تحديث الأنماط بناءً على وضع الليل أو النهار
    private void updateStyles(boolean isNightMode) {
        String nightMode = "/styles/NightMode.css";
        String lightMode = "/styles/LightMode.css";
        if (scene != null) {
            scene.getStylesheets().clear();
            String mode;
            if (isNightMode) {
                mode = nightMode;
            } else {
                mode = lightMode;
            }
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(mode)).toExternalForm());
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/toggleSwitch.css")).toExternalForm());
        }
    }
}
