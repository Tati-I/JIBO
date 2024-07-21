package org.example.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        AnchorPane root = new AnchorPane();

        // تحميل الصورة
        Image background = new Image("file:/C:/Users/hp/Downloads/background.png");
        ImageView imageView = new ImageView(background);
        imageView.setFitWidth(655);
        imageView.setFitHeight(700);

        // إنشاء المستطيل
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(435);
        rectangle.setWidth(485);
        rectangle.setX(82);
        rectangle.setY(133);
        rectangle.setFill(Color.WHITE);
        rectangle.setArcHeight(25);
        rectangle.setArcWidth(25);
        rectangle.setStyle("-fx-border-color: #646262;");

        // إضافة ظل للمستطيل
        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(5);
        shadow.setOffsetY(5);
        shadow.setColor(Color.web("646262"));
        rectangle.setEffect(shadow);

        // إنشاء رسالة الترحيب
        Label WelcomeMsg = new Label("مرحبًا بك في تطبيق JIBOO");
        WelcomeMsg.setLayoutX(207);
        WelcomeMsg.setLayoutY(161);
        WelcomeMsg.setStyle("-fx-text-fill: #000000; -fx-font-weight: bold; -fx-font-size: 22; ");

        Label bioMsg = new Label("حِرفيُّكَ الخاص في جيبك ");
        bioMsg.setLayoutX(240);
        bioMsg.setLayoutY(193);
        bioMsg.setStyle("-fx-text-fill: #565454; -fx-font-size: 16");

        Rectangle loginRect = new Rectangle();
        loginRect.setHeight(40);
        loginRect.setWidth(429);
        loginRect.setX(110);
        loginRect.setY(236);
        loginRect.setFill(Color.web("DEDEDE"));
        loginRect.setArcHeight(25);
        loginRect.setArcWidth(25);
        loginRect.setStyle("-fx-border-color: #646262;");

        Button loginButton = new Button("تسجي3ل الدخول");
        loginButton.setLayoutX(117);
        loginButton.setLayoutY(241);
        loginButton.setPrefSize(206,26);
        loginButton.setStyle("-fx-text-fill: #000000; -fx-font-size: 14; -fx-background-color: white; -fx-background-radius: 10");


        Button signUpButton = new Button("إنشاء حساب");
        signUpButton.setLayoutX(326);
        signUpButton.setLayoutY(241);
        signUpButton.setPrefSize(206,26);
        signUpButton.setStyle("-fx-text-fill: #000000; -fx-font-size: 14; -fx-background-color: #b2b2b2; -fx-background-radius: 10");



        // إضافة العناصر إلى الجذر
        root.getChildren().addAll(imageView, rectangle, WelcomeMsg,bioMsg,loginRect,loginButton,signUpButton);


        // إعداد المشهد
        Scene scene = new Scene(root, 650, 700);

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
