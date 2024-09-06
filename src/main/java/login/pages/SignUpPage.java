package login.pages;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import auth.EmailCheck;
import auth.FileBasedAuthenticationSystem;
import auth.PasswordCheck;

import java.util.Objects;

public class SignUpPage {
    // to use in all methods+
    private TextField emailField,nameField;
    private PasswordField passwordField;
    private Label errorLabelEmail;
    private Label errorLabelPassword;
    private RadioButton personType,workerType;
    private boolean isPasswordVisible = false;
    // css colors and settings
    private final String personUiStyle = "-fx-prompt-text-fill: #6E6D6DFF;-fx-border-color: #01012a; -fx-background-radius: 10; -fx-border-radius: 10;";
    private final String workerUiStyle = "-fx-prompt-text-fill: #6E6D6DFF;-fx-border-color: #01012a; -fx-background-radius: 10; -fx-border-radius: 10;";
    private final String textStyle = "-fx-text-fill: #000000; -fx-font-size: 18; -fx-font-weight: bold;";
    private final String errorTextStyle = "-fx-text-fill: red; -fx-font-size: 12;";

    public void display(Pane pane, Button loginButton, Button signUpButton, LoginPage loginPage, Rectangle loginRectangle, Label welcome, Label msg) {
        pane.getChildren().clear();
        pane.getChildren().addAll(loginRectangle,msg,loginButton,signUpButton,welcome);
        fieldsPackage(pane,loginPage);
        pane.setPrefSize(525, 700);
        pane.setLayoutY(10);

        TranslateTransition loginTransition = new TranslateTransition(Duration.millis(300), loginButton);
        TranslateTransition signUpTransition = new TranslateTransition(Duration.millis(300), signUpButton);

        loginButton.setOnAction(_ -> {
            loginTransition.setToX(0);
            loginTransition.play();
            signUpTransition.setToX(0);
            signUpTransition.play();
            loginButton.setStyle("-fx-border-color: #e3e3e3; -fx-border-radius: 10px; -fx-background-color: #fdfdfd; -fx-background-radius:10px; -fx-cursor: hand; -fx-text-fill: #000000; -fx-font-size: 15;");
            signUpButton.setStyle("-fx-text-fill: #000000; -fx-font-size: 15; -fx-border-radius: 10; -fx-background-color: #f1f2f4; -fx-background-radius: 10px; -fx-cursor: hand;");
            loginPage.createLoginView();

        });


    }
    private void fieldsPackage(Pane pane,LoginPage loginPage) {
        // Account Type Label
        Label accountType = new Label("نوع الحساب");
        accountType.setStyle(textStyle);
        accountType.setLayoutY(145);
        accountType.setLayoutX(419);

        // Name Label
        Label nameLabel = new Label("الأسم");
        nameLabel.setLayoutX(464);
        nameLabel.setLayoutY(230);
        nameLabel.setStyle(textStyle);

        // Name TextField
        nameField = new TextField();
        nameField.setPromptText("الأسم");
        nameField.setPrefSize(495, 40);
        nameField.setLayoutX(15);
        nameField.setLayoutY(270);
        nameField.setStyle(personUiStyle);

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
        emailLabel.setStyle(textStyle);

        // email TextField
        emailField = new TextField();
        emailField.setPromptText("البريد الألكتروني");
        emailField.setPrefSize(495, 40);
        emailField.setLayoutX(15);
        emailField.setLayoutY(350);
        emailField.setStyle(personUiStyle);

        // email Error Label
        errorLabelEmail = new Label("البريد الإلكتروني غير صالح");
        errorLabelEmail.setStyle(errorTextStyle);
        errorLabelEmail.setLayoutX(15);
        errorLabelEmail.setLayoutY(390);
        errorLabelEmail.setVisible(false);

        // email icon
        Image emailIcon = new Image(Objects.requireNonNull(getClass().getResource("/Pictures/email.png")).toExternalForm());
        ImageView emailIconView = new ImageView(emailIcon);
        emailIconView.setFitWidth(20);
        emailIconView.setFitHeight(20);
        emailIconView.setLayoutX(480);
        emailIconView.setLayoutY(360);

        passwordActions(pane);

        // Phone number label
        Label phoneNumLabel = new Label("رقم الهاتف");
        phoneNumLabel.setLayoutX(424);
        phoneNumLabel.setLayoutY(475);
        phoneNumLabel.setStyle(textStyle);

        // Phone number field
        TextField phoneNumField = new TextField();
        phoneNumField.setPromptText("رقم الهاتف");
        phoneNumField.setPrefSize(495, 40);
        phoneNumField.setLayoutX(15);
        phoneNumField.setLayoutY(510);
        phoneNumField.setStyle(personUiStyle);

        // Phone number icon
        Image phoneNumIcon = new Image(Objects.requireNonNull(getClass().getResource("/Pictures/phone.png")).toExternalForm());
        ImageView phoneNumIconView = new ImageView(phoneNumIcon);
        phoneNumIconView.setFitWidth(20);
        phoneNumIconView.setFitHeight(20);
        phoneNumIconView.setLayoutX(480);
        phoneNumIconView.setLayoutY(520);

        // Sign Up Button
        signUpButton(pane,loginPage);
        setupAccountTypeSelection(pane);

        // Privacy Label
        Label privacy = new Label("بانشاء الحساب، أنت توافق على الشروط والأحكام وسياسة الخصوصية");
        String privacyStyle = "-fx-text-fill: #6e6d6d; -fx-font-size: 16";
        privacy.setStyle(privacyStyle);
        privacy.setLayoutX(46);
        privacy.setLayoutY(630);

        personType.setOnAction(_ -> {
            nameField.setStyle(personUiStyle);
            emailField.setStyle(personUiStyle);
            passwordField.setStyle(personUiStyle);
            phoneNumField.setStyle(personUiStyle);
        });

        workerType.setOnAction(_ -> {
            nameField.setStyle(workerUiStyle);
            emailField.setStyle(workerUiStyle);
            passwordField.setStyle(workerUiStyle);
            phoneNumField.setStyle(workerUiStyle);
        });
        // Add components to the pane
        pane.getChildren().addAll(errorLabelEmail,accountType,nameLabel, nameField, emailField, emailLabel,phoneNumLabel,phoneNumField,privacy,phoneNumIconView,emailIconView,nameIconView);
    }
    private void setupAccountTypeSelection(Pane pane) {

        personType = new RadioButton("شخصي");
        personType.setPrefSize(100,30);
        personType.setLayoutX(400);
        personType.setLayoutY(180);
        personType.setSelected(true);


        workerType = new RadioButton("عامل");
        workerType.setPrefSize(100,30);
        workerType.setLayoutX(280);
        workerType.setLayoutY(180);

        ToggleGroup accountTypeGroup = new ToggleGroup();
        workerType.setToggleGroup(accountTypeGroup);
        personType.setToggleGroup(accountTypeGroup);

        pane.getChildren().addAll(workerType, personType);
    }
    private void signUpButton(Pane pane,LoginPage loginPage) {
        Button signUpButton = new Button("أنشاء حساب");
        signUpButton.setLayoutX(15);
        signUpButton.setLayoutY(570);
        signUpButton.setPrefSize(495, 40);
        signUpButton.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-background-color: #01012a; -fx-text-fill: white; -fx-font-size: 16;");

        signUpButton.setOnMouseEntered(_ -> signUpButton.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-background-color: #090942; -fx-text-fill: white; -fx-font-size: 16; -fx-cursor: hand;"));

        signUpButton.setOnMouseExited(_ -> signUpButton.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-background-color: #01012a; -fx-text-fill: white; -fx-font-size: 16;"));

        signUpButton.setOnAction(_ -> {
            String username = nameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String userType = personType.isSelected() ? "personal" : "worker";

            if (EmailCheck.isValidEmail(email) && PasswordCheck.isValidPassword(password)) {
                if (FileBasedAuthenticationSystem.registerUser(username, password, email, userType) &&EmailCheck.isValidEmail(email) && PasswordCheck.isValidPassword(password) ) {
                    errorLabelEmail.setVisible(false);
                    // إظهار رسالة نجاح وربما الانتقال إلى صفحة تسجيل الدخول
                    System.out.println("تم تسجيل المستخدم بنجاح");
                    loginPage.createLoginView();
                } else {
                    // إظهار رسالة خطأ
                    errorLabelEmail.setText("فشل التسجيل. ربما البريد الإلكتروني مستخدم بالفعل أو كلمة المرور ضعيفة");
                    errorLabelEmail.setVisible(true);
                    System.out.println("فشل التسجيل. ربما البريد الإلكتروني مستخدم بالفعل");
                }
            } else {
                errorLabelEmail.setVisible(!EmailCheck.isValidEmail(email));
                errorLabelPassword.setVisible(!PasswordCheck.isValidPassword(password));
            }
        });

        pane.getChildren().add(signUpButton);
    }

    private void passwordActions(Pane pane){
        Label passwordLabel = new Label("كلمة المرور");
        passwordLabel.setLayoutX(425);
        passwordLabel.setLayoutY(395);
        passwordLabel.setStyle(textStyle);

        // password field
        passwordField = new PasswordField();
        passwordField.setPromptText("كلمة المرور");
        passwordField.setPrefSize(495, 40);
        passwordField.setLayoutX(15);
        passwordField.setLayoutY(430);
        passwordField.setStyle(personUiStyle);

        TextField passwordTextField = new TextField();
        passwordTextField.setPromptText("كلمة المرور");
        passwordTextField.setPrefSize(495, 40);
        passwordTextField.setLayoutX(15);
        passwordTextField.setLayoutY(430);
        passwordTextField.setStyle(personUiStyle);
        passwordTextField.setVisible(false);

        errorLabelPassword = new Label("تحقق من كلمة المرور");
        errorLabelPassword.setStyle(errorTextStyle);
        errorLabelPassword.setLayoutX(15);
        errorLabelPassword.setLayoutY(470);
        errorLabelPassword.setVisible(false);

        Image passwordUn_LockIcon = new Image(Objects.requireNonNull(getClass().getResource("/Pictures/unlock.png")).toExternalForm());
        Image passwordLockIcon = new Image(Objects.requireNonNull(getClass().getResource("/Pictures/lock.png")).toExternalForm());

        ImageView passwordIconView = new ImageView(passwordLockIcon);
        passwordIconView.setFitWidth(20);
        passwordIconView.setFitHeight(20);
        passwordIconView.setLayoutX(480);
        passwordIconView.setLayoutY(440);

        passwordIconView.setOnMouseEntered(_ -> passwordIconView.setStyle("-fx-cursor: hand;"));

        passwordIconView.setOnMouseClicked(_ -> {
            isPasswordVisible = !isPasswordVisible;
            if (isPasswordVisible) {
                passwordIconView.setImage(passwordUn_LockIcon);
                passwordField.setVisible(false);
                passwordTextField.setVisible(true);
                passwordTextField.setText(passwordField.getText());
            } else {
                passwordIconView.setImage(passwordLockIcon);
                passwordField.setVisible(true);
                passwordTextField.setVisible(false);
                passwordField.setText(passwordTextField.getText());
            }
        });
        pane.getChildren().addAll(passwordLabel,passwordTextField,passwordField,passwordIconView,errorLabelPassword);
    }

}