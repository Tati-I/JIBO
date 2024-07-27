package org.example.main;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import java.util.Objects;

public class SignUpPage {
    // to use in all methods+
    private TextField emailField;
    private Label errorLabel;

    public void display(Pane pane, Button loginButton, Button signUpButton, LoginPage loginPage, Rectangle loginRectangle, Label welcome, Label msg) {
        pane.getChildren().clear();
        pane.getChildren().addAll(loginRectangle,msg,loginButton,signUpButton,welcome);
        fieldsPackage(pane);
        pane.setPrefSize(525, 700);
        pane.setLayoutY(10);

        TranslateTransition loginTransition = new TranslateTransition(Duration.millis(300), loginButton);
        TranslateTransition signUpTransition = new TranslateTransition(Duration.millis(300), signUpButton);

        loginButton.setOnAction(_ -> {
            loginTransition.setToX(0);
            loginTransition.play();
            signUpTransition.setToX(0);
            signUpTransition.play();
            loginButton.setStyle("-fx-border-color: #e3e3e3; -fx-border-radius: 5px; -fx-background-color: #fdfdfd; -fx-background-radius: 5px; -fx-cursor: hand; -fx-text-fill: #000000; -fx-font-size: 15;");
            signUpButton.setStyle("-fx-text-fill: #000000; -fx-font-size: 15; -fx-background-color: #f1f2f4; -fx-background-radius: 5px; -fx-cursor: hand;");
            loginPage.createLoginView();
        });


    }
    private void fieldsPackage(Pane pane) {
        // css colors and settings
        String personStyle = "-fx-prompt-text-fill: #6E6D6DFF;-fx-border-color: #8e2cc8; -fx-background-radius: 10; -fx-border-radius: 10;";
        String workerStyle = "-fx-prompt-text-fill: #6E6D6DFF;-fx-border-color: #61d0e3; -fx-background-radius: 10; -fx-border-radius: 10;";
        // Account Type Label
        Label accountType = new Label("نوع الحساب");
        accountType.setStyle("-fx-text-fill: #000000; -fx-font-size: 18; -fx-font-weight: bold;");
        accountType.setLayoutY(145);
        accountType.setLayoutX(419);

        // RadioButtons for Account Type
        RadioButton personType = new RadioButton("شخصي");
        personType.setLayoutX(130);
        personType.setLayoutY(160);
        personType.setSelected(true);
        personType.setStyle("-fx-font-size: 16; -fx-padding: 10; -fx-text-fill: #000000;");
        // Adjusted Y position to avoid overlap
        RadioButton workerType = new RadioButton("عامل");
        workerType.setLayoutX(210);
        workerType.setLayoutY(190);
        workerType.setStyle("-fx-font-size: 16; -fx-padding: 10; -fx-text-fill: #000000;");

        // Group RadioButtons to ensure only one is selected at a time
        ToggleGroup accountTypeGroup = new ToggleGroup();
        workerType.setToggleGroup(accountTypeGroup);
        personType.setToggleGroup(accountTypeGroup);

        // Name Label
        Label nameLabel = new Label("الأسم");
        nameLabel.setLayoutX(464);
        nameLabel.setLayoutY(230);
        nameLabel.setStyle("-fx-text-fill: #000000; -fx-font-size: 18; -fx-font-weight: bold;");

        // Name TextField
        TextField nameField = new TextField();
        nameField.setPromptText("الأسم");
        nameField.setPrefSize(495, 40);
        nameField.setLayoutX(15);
        nameField.setLayoutY(270);
        nameField.setStyle(personStyle);

        // Name icon
        Image nameIcon = new Image(Objects.requireNonNull(getClass().getResource("/Pictures/user.png")).toExternalForm());
        ImageView nameIconView = new ImageView(nameIcon);
        nameIconView.setFitWidth(20);
        nameIconView.setFitHeight(20);
        nameIconView.setLayoutX(480);
        nameIconView.setLayoutY(280);


        // email Label
        Label emailLabel = new Label("البريد الألكتروني");
        emailLabel.setLayoutX(390);
        emailLabel.setLayoutY(315);
        emailLabel.setStyle("-fx-text-fill: #000000; -fx-font-size: 18; -fx-font-weight: bold;");

        // email TextField
        emailField = new TextField();
        emailField.setPromptText("البريد الألكتروني");
        emailField.setPrefSize(495, 40);
        emailField.setLayoutX(15);
        emailField.setLayoutY(350);
        emailField.setStyle(personStyle);

        // email Error Label
        errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14;");
        errorLabel.setLayoutX(15);
        errorLabel.setLayoutY(390);
        errorLabel.setVisible(false);

        // email icon
        Image emailIcon = new Image(Objects.requireNonNull(getClass().getResource("/Pictures/email.png")).toExternalForm());
        ImageView emailIconView = new ImageView(emailIcon);
        emailIconView.setFitWidth(20);
        emailIconView.setFitHeight(20);
        emailIconView.setLayoutX(480);
        emailIconView.setLayoutY(360);

        // password label
        Label passwordLabel = new Label("كلمة المرور");
        passwordLabel.setLayoutX(425);
        passwordLabel.setLayoutY(395);
        passwordLabel.setStyle("-fx-text-fill: #000000; -fx-font-size: 18; -fx-font-weight: bold;");

        // password field
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("كلمة المرور");
        passwordField.setPrefSize(495, 40);
        passwordField.setLayoutX(15);
        passwordField.setLayoutY(430);
        passwordField.setStyle(personStyle);

        // password icon
        Image passwordIcon = new Image(Objects.requireNonNull(getClass().getResource("/Pictures/lock.png")).toExternalForm());
        ImageView passwordIconView = new ImageView(passwordIcon);
        passwordIconView.setFitWidth(20);
        passwordIconView.setFitHeight(20);
        passwordIconView.setLayoutX(480);
        passwordIconView.setLayoutY(440);

        // Phone number label
        Label phoneNumLabel = new Label("رقم الهاتف");
        phoneNumLabel.setLayoutX(424);
        phoneNumLabel.setLayoutY(475);
        phoneNumLabel.setStyle("-fx-text-fill: #000000; -fx-font-size: 18; -fx-font-weight: bold;");

        // Phone number field
        TextField phoneNumField = new TextField();
        phoneNumField.setPromptText("رقم الهاتف");
        phoneNumField.setPrefSize(495, 40);
        phoneNumField.setLayoutX(15);
        phoneNumField.setLayoutY(510);
        phoneNumField.setStyle(personStyle);

        // Phone number icon
        Image phoneNumIcon = new Image(Objects.requireNonNull(getClass().getResource("/Pictures/phone.png")).toExternalForm());
        ImageView phoneNumIconView = new ImageView(phoneNumIcon);
        phoneNumIconView.setFitWidth(20);
        phoneNumIconView.setFitHeight(20);
        phoneNumIconView.setLayoutX(480);
        phoneNumIconView.setLayoutY(520);

        // Sign Up Button
        signUpButton(pane);

        // Privacy Label
        Label privacy = new Label("بانشاء الحساب، أنت توافق على الشروط والأحكام وسياسة الخصوصية");
        privacy.setStyle("-fx-text-fill: #6e6d6d; -fx-font-size: 16");
        privacy.setLayoutX(46);
        privacy.setLayoutY(630);

        personType.setOnAction(_ -> {
            nameField.setStyle(personStyle);
            emailField.setStyle(personStyle);
            passwordField.setStyle(personStyle);
            phoneNumField.setStyle(personStyle);
        });

        workerType.setOnAction(_ -> {
            nameField.setStyle(workerStyle);
            emailField.setStyle(workerStyle);
            passwordField.setStyle(workerStyle);
            phoneNumField.setStyle(workerStyle);
        });
        // Add components to the pane
        pane.getChildren().addAll(errorLabel,accountType, workerType, personType, nameLabel, nameField, emailField, emailLabel,passwordLabel, passwordField,phoneNumLabel,phoneNumField,privacy,passwordIconView,phoneNumIconView,emailIconView,nameIconView);
    }
    private void signUpButton(Pane pane) {
        Button signUpButton = new Button("أنشاء حساب");
        signUpButton.setLayoutX(15);
        signUpButton.setLayoutY(570);
        signUpButton.setPrefSize(495, 40);
        signUpButton.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-background-color: #01012a; -fx-text-fill: white; -fx-font-size: 16;");

        signUpButton.setOnMouseEntered(_ -> signUpButton.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-background-color: #090942; -fx-text-fill: white; -fx-font-size: 16; -fx-cursor: hand;"));

        signUpButton.setOnMouseExited(_ -> signUpButton.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-background-color: #01012a; -fx-text-fill: white; -fx-font-size: 16;"));

        signUpButton.setOnAction(_ -> {
            String email = emailField.getText();
            if (EmailCheck.isValidEmail(email)) {
                errorLabel.setVisible(false);
                // Proceed with sign up process
                System.out.println("Valid email, proceeding with sign up");
            } else {
                errorLabel.setText("البريد الإلكتروني غير صالح");
                errorLabel.setVisible(true);
            }
        });

        pane.getChildren().add(signUpButton);
    }

}