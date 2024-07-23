package org.example.main;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        AnchorPane root = new AnchorPane();

        // تحميل الصورة
        Image background = new Image(getClass().getResource("/Pictures/background.png").toExternalForm());
        ImageView imageView = new ImageView(background);
        imageView.setFitWidth(700);
        imageView.setFitHeight(720);

        // إنشاء لوحة
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: white; -fx-background-radius: 27px;");
        pane.setPrefSize(525, 470);
        pane.setLayoutX(88);
        pane.setLayoutY(144);

        // إنشاء رسالة الترحيب
        Label WelcomeMsg = new Label("مرحبًا بك في تطبيق JIBO");
        WelcomeMsg.setLayoutX(140);
        WelcomeMsg.setLayoutY(30);
        WelcomeMsg.setStyle("-fx-text-fill: #000000; -fx-font-weight: bold; -fx-font-size: 24;");

        Label bioMsg = new Label("حِرفيُّكَ الخاص في جيبك ");
        bioMsg.setLayoutX(175);
        bioMsg.setLayoutY(60);
        bioMsg.setStyle("-fx-text-fill: #565454; -fx-font-size: 17");

        // إنشاء مستطيل تسجيل الدخول
        Rectangle loginRect = new Rectangle();
        loginRect.setHeight(43);
        loginRect.setWidth(464);
        loginRect.setX(30);
        loginRect.setY(100);
        loginRect.setFill(Color.web("F1F2F4FF"));
        loginRect.setArcHeight(13);
        loginRect.setArcWidth(13);

        Button loginButton = new Button("تسجيل الدخول");
        loginButton.setLayoutX(35);
        loginButton.setLayoutY(103.5);
        loginButton.setPrefSize(225, 36);
        loginButton.setStyle("-fx-border-color: #e3e3e3; -fx-border-radius: 5px; -fx-text-fill: #000000; -fx-font-size: 15; -fx-background-color: white; -fx-background-radius: 5px; -fx-cursor: hand;");

        Button signUpButton = new Button("إنشاء حساب");
        signUpButton.setLayoutX(262.5);
        signUpButton.setLayoutY(103.5);
        signUpButton.setPrefSize(225, 36);
        signUpButton.setStyle("-fx-text-fill: #000000; -fx-font-size: 15; -fx-background-color: #f1f2f4; -fx-background-radius: 5px; -fx-cursor: hand;");

        signUpButton.setOnAction(event -> {
            signUpButton.setStyle("-fx-border-color: #e3e3e3; -fx-border-radius: 5px; -fx-background-color: white; -fx-background-radius: 5px; -fx-cursor: hand; -fx-text-fill: #000000; -fx-font-size: 15;");
            loginButton.setStyle("-fx-background-color: #f1f2f4; -fx-background-radius: 5px; -fx-cursor: hand; -fx-text-fill: #000000; -fx-font-size: 15;");
        });

        loginButton.setOnAction(event -> {
            loginButton.setStyle("-fx-border-color: #e3e3e3; -fx-border-radius: 5px; -fx-background-color: #fdfdfd; -fx-background-radius: 5px; -fx-cursor: hand; -fx-text-fill: #000000; -fx-font-size: 15;");
            signUpButton.setStyle("-fx-text-fill: #000000; -fx-font-size: 15; -fx-background-color: #f1f2f4; -fx-background-radius: 5px; -fx-cursor: hand;");
        });

        // إضافة العناصر إلى اللوحة
        pane.getChildren().addAll(WelcomeMsg, bioMsg, loginRect, loginButton, signUpButton);

        // إضافة اللوحة إلى الجذر
        root.getChildren().addAll(imageView, pane);

        // إعداد المشهد
        Scene scene = new Scene(root, 700, 720);

        // إعداد المرحلة
        stage.setTitle("Jibo");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
