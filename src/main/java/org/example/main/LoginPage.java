package org.example.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import java.util.Objects;

public class LoginPage extends Application {
    private static Pane pane;
    private Stage primaryStage;

    private static Button loginButton;
    private static Button signUpButton;
    private static ImageView passwordIconView;
    private static Image visibleIcon;
    private static Image hiddenIcon;
    private static  boolean isPasswordVisible = false;
    private static TextField visiblePasswordField;
    private static PasswordField passwordField;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        this.primaryStage = stage;



        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/icon.png"))));
        AnchorPane root = new AnchorPane();

        visibleIcon = new Image(Objects.requireNonNull(getClass().getResource("/Pictures/unlock.png")).toExternalForm());
        hiddenIcon = new Image(Objects.requireNonNull(getClass().getResource("/Pictures/lock.png")).toExternalForm());

        Image background = new Image(Objects.requireNonNull(getClass().getResource("/Pictures/logo.png")).toExternalForm());
        ImageView imageView = new ImageView(background);
        imageView.setFitWidth(700);
        imageView.setFitHeight(720);


        pane = new Pane();
        pane.setStyle("-fx-background-color: white; -fx-background-radius: 27px;");
        pane.setPrefSize(525, 470);
        pane.setLayoutX(88);

        createLoginView();

        root.getChildren().addAll(imageView, pane);

        Scene scene = new Scene(root, 700, 720);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/style.css")).toExternalForm());

        stage.setTitle("Jibo");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    public void createLoginView() {


        pane.getChildren().clear();
        pane.setPrefSize(525, 470);
        pane.setLayoutY(130);

        Label WelcomeMsg = new Label("مرحبًا بك في تطبيق JIBO");
        WelcomeMsg.setLayoutX(140);
        WelcomeMsg.setLayoutY(30);
        WelcomeMsg.setStyle("-fx-text-fill: #000000; -fx-font-weight: bold; -fx-font-size: 24;");

        Label bioMsg = new Label("حِرفيُّكَ الخاص في جيبك ");
        bioMsg.setLayoutX(175);
        bioMsg.setLayoutY(60);
        bioMsg.setStyle("-fx-text-fill: #6e6d6d; -fx-font-size: 17");

        Rectangle loginRect = new Rectangle();
        loginRect.setHeight(43);
        loginRect.setWidth(464);
        loginRect.setX(30);
        loginRect.setY(100);
        loginRect.setFill(Color.web("F1F2F4FF"));
        loginRect.setArcHeight(25);
        loginRect.setArcWidth(25);

        loginButton = new Button("تسجيل الدخول");
        loginButton.setLayoutX(35);
        loginButton.setLayoutY(103.5);
        loginButton.setPrefSize(225, 36);
        loginButton.setStyle("-fx-border-color: #e3e3e3; -fx-border-radius: 10px; -fx-text-fill: #000000; -fx-font-size: 15; -fx-background-color: white; -fx-background-radius: 10px; -fx-cursor: hand;");

        signUpButton = new Button("إنشاء حساب");
        signUpButton.setLayoutX(262.5);
        signUpButton.setLayoutY(103.5);
        signUpButton.setPrefSize(225, 36);
        signUpButton.setStyle("-fx-text-fill: #000000; -fx-font-size: 15; -fx-background-color: #f1f2f4; -fx-background-radius: 10px; -fx-cursor: hand;");

        TranslateTransition loginTransition = new TranslateTransition(Duration.millis(300), loginButton);
        TranslateTransition signUpTransition = new TranslateTransition(Duration.millis(300), signUpButton);

        signUpButton.setOnAction(_ -> {
            signUpTransition.setToX(-228);
            signUpTransition.play();
            loginTransition.setToX(225);
            loginTransition.play();

            signUpButton.setStyle("-fx-border-color: #e3e3e3; -fx-border-radius: 10px; -fx-background-color: white; -fx-background-radius: 10px; -fx-cursor: hand; -fx-text-fill: #000000; -fx-font-size: 15;");
            loginButton.setStyle("-fx-background-color: #f1f2f4; -fx-background-radius: 10px;-fx-border-radius: 10px; -fx-cursor: hand; -fx-text-fill: #000000; -fx-font-size: 15;");
            SignUpPage signUpPage = new SignUpPage();
            signUpPage.display(pane, loginButton, signUpButton, this, loginRect, WelcomeMsg, bioMsg);
        });

        loginButton.setOnAction(_ -> {
            loginTransition.setToX(0);
            loginTransition.play();
            signUpTransition.setToX(0);
            signUpTransition.play();

            loginButton.setStyle("-fx-border-color: #e3e3e3; -fx-border-radius: 10px; -fx-background-color: #fdfdfd; -fx-background-radius: 10px; -fx-cursor: hand; -fx-text-fill: #000000; -fx-font-size: 15;");
            signUpButton.setStyle("-fx-text-fill: #000000; -fx-font-size: 15; -fx-background-color: #f1f2f4; -fx-background-radius: 10px;-fx-border-radius: 10px; -fx-cursor: hand;");
            createLoginView();
        });

        Label emailWord = new Label("البريد الإلكتروني");
        emailWord.setLayoutX(390);
        emailWord.setLayoutY(170);
        emailWord.setStyle("-fx-text-fill: #000000; -fx-font-size: 18; -fx-font-weight: bold;");

        TextField emailField = new TextField();
        emailField.setPromptText("البريد الألكتروني");
        emailField.setPrefSize(495, 40);
        emailField.setLayoutX(15);
        emailField.setLayoutY(210);
        emailField.setStyle("-fx-prompt-text-fill: #6E6D6DFF;-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #01012a;");

        Image emailIcon = new Image(Objects.requireNonNull(getClass().getResource("/Pictures/email.png")).toExternalForm());
        ImageView emailIconView = new ImageView(emailIcon);
        emailIconView.setFitWidth(20);
        emailIconView.setFitHeight(20);
        emailIconView.setLayoutX(480);
        emailIconView.setLayoutY(220);

        Label passwordWord = new Label("كلمة المرور");
        passwordWord.setLayoutX(425);
        passwordWord.setLayoutY(255);
        passwordWord.setStyle("-fx-text-fill: #000000; -fx-font-size: 18; -fx-font-weight: bold;");

        passwordField = new PasswordField();
        passwordField.setPromptText("كلمة المرور");
        passwordField.setPrefSize(495, 40);
        passwordField.setLayoutX(15);
        passwordField.setLayoutY(290);
        passwordField.setStyle("-fx-prompt-text-fill: #6E6D6DFF;-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #01012a;");

        visiblePasswordField = new TextField();
        visiblePasswordField.setPromptText("كلمة المرور");
        visiblePasswordField.setPrefSize(495, 40);
        visiblePasswordField.setLayoutX(15);
        visiblePasswordField.setLayoutY(290);
        visiblePasswordField.setStyle("-fx-prompt-text-fill: #6E6D6DFF;-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #01012a;");
        visiblePasswordField.setVisible(false);

        passwordIconView = new ImageView(hiddenIcon);
        passwordIconView.setFitWidth(20);
        passwordIconView.setFitHeight(20);
        passwordIconView.setLayoutX(480);
        passwordIconView.setLayoutY(300);

        passwordIconView.setOnMouseEntered(_ -> passwordIconView.setStyle("-fx-cursor: hand;"));

        passwordIconView.setOnMouseClicked(_ -> showPassword());

        Button Login = new Button("تسجيل الدخول");
        Login.setLayoutX(15);
        Login.setLayoutY(350);
        Login.setPrefSize(495, 40);
        Login.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-background-color: #01012a; -fx-text-fill: white; -fx-font-size: 16;");

        Label privacy = new Label("بالتسجيل، أنت توافق على الشروط والأحكام وسياسة الخصوصية");
        privacy.setStyle("-fx-text-fill: #6e6d6d; -fx-font-size: 16");
        privacy.setLayoutX(60);
        privacy.setLayoutY(410);

        Label errorLabelEmail = new Label("البريد الإلكتروني غير صالح");
        errorLabelEmail.setStyle("-fx-text-fill: red; -fx-font-size: 12;");
        errorLabelEmail.setLayoutX(15);
        errorLabelEmail.setLayoutY(250);
        errorLabelEmail.setVisible(false);

        Label errorLabelPassword = new Label("تحقق من كلمة المرور");
        errorLabelPassword.setLayoutX(15);
        errorLabelPassword.setLayoutY(330);
        errorLabelPassword.setVisible(false);
        errorLabelPassword.setStyle("-fx-text-fill: red; -fx-font-size: 12;");

        Login.setOnMouseEntered(_ -> Login.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-background-color: #090942; -fx-text-fill: white; -fx-font-size: 16; -fx-cursor: hand;"));

        Login.setOnMouseExited(_ -> Login.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-background-color: #01012a; -fx-text-fill: white; -fx-font-size: 16;"));

        Login.setOnMouseClicked(e -> {
            String adminEmail = "admin";
            String adminPassword = "admin";
            String email = emailField.getText();
            String password = passwordField.getText();

            if (email.equals(adminEmail) && password.equals(adminPassword)) {
                primaryStage.close();
                PersonalHomePage homePage = new PersonalHomePage();
                homePage.start(new Stage());
            }


                if (EmailCheck.isValidEmail(email)) {
                    errorLabelEmail.setVisible(false);
                    System.out.println("right");
                } else {
                    errorLabelEmail.setVisible(true);
                }

                if (PasswordCheck.isValidPassword(password)) {
                    errorLabelPassword.setVisible(false);
                    System.out.println("right");
                } else {
                    errorLabelPassword.setVisible(true);
                }


        });



        pane.getChildren().addAll(WelcomeMsg, bioMsg, loginRect, loginButton, signUpButton, emailWord, emailField, passwordWord, passwordField, visiblePasswordField, Login, privacy, emailIconView, passwordIconView, errorLabelEmail, errorLabelPassword);
    }

    public static void showPassword(){
        if (isPasswordVisible) {
            passwordField.setText(visiblePasswordField.getText());
            visiblePasswordField.setVisible(false);
            passwordField.setVisible(true);
            passwordIconView.setImage(hiddenIcon);
            isPasswordVisible = false;
        } else {
            visiblePasswordField.setText(passwordField.getText());
            passwordField.setVisible(false);
            visiblePasswordField.setVisible(true);
            passwordIconView.setImage(visibleIcon);
            isPasswordVisible = true;
        }

    }
}
