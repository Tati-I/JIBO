package login.pages;

import auth.EmailCheck;
import auth.PasswordCheck;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import auth.FileBasedAuthenticationSystem;
import auth.User;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.example.main.RootScreen;

import java.util.Objects;

public class LoginPage extends Application {
    Image visibleIcon = new Image(Objects.requireNonNull(getClass().getResource("/Pictures/unlock.png")).toExternalForm());
    Image hiddenIcon = new Image(Objects.requireNonNull(getClass().getResource("/Pictures/lock.png")).toExternalForm());
    private VBox loginForm;
    private VBox signupForm;
    private VBox welcomePanel;
    private VBox signInPanel;
    private HBox root;
    private HBox loginPage;
    private HBox signPage;
    private boolean isLoginView;
    public static User loggedInUser;

    @Override
    public void start(Stage primaryStage) {
        // Show splash screen first
        showSplashScreen(primaryStage);

    }

    private void showSplashScreen(Stage primaryStage) {
        // Create splash screen stage
        Stage splashStage = new Stage();
        splashStage.initStyle(StageStyle.UNDECORATED);
        splashStage.initModality(Modality.APPLICATION_MODAL);

        // Create splash screen layout
        StackPane splashLayout = new StackPane();
        splashLayout.getStyleClass().add("splash-root");
        splashLayout.setAlignment(Pos.CENTER);

        // Load logo
        Image logoImage = new Image(Objects.requireNonNull(getClass().getResource("/Pictures/logoBlack.png")).toExternalForm());
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(250);
        logoView.setFitHeight(250);
        logoView.setPreserveRatio(true);
        logoView.getStyleClass().add("splash-logo");

        // Create app name label
        Label appNameLabel = new Label("Jibo");
        appNameLabel.getStyleClass().add("app-name");

        // Create copyright label
        Label copyrightLabel = new Label("© 2024 Jibo. All Rights Reserved.");
        copyrightLabel.getStyleClass().add("copyright-label");

        // Create loading progress bar
        ProgressBar loadingBar = new ProgressBar();
        loadingBar.setProgress(-1.0); // Indeterminate progress
        loadingBar.getStyleClass().add("progress-bar");
        loadingBar.setPrefWidth(300);

        // Create layout for logo, app name, and loading
        VBox contentBox = new VBox(20);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.getChildren().addAll(logoView, appNameLabel, loadingBar, copyrightLabel);

        splashLayout.getChildren().add(contentBox);

        // Create splash scene and apply CSS
        Scene splashScene = new Scene(splashLayout, 600, 400);
        splashScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/loginPage/login-splash-screen.css")).toExternalForm());

        splashStage.setScene(splashScene);
        splashStage.centerOnScreen();
        splashStage.show();

        // Create fade transition for splash screen
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), splashLayout);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        // After 2 seconds, close splash screen and show login page
        new Thread(() -> {
            try {
                Thread.sleep(2000); // 2 seconds splash screen display
                Platform.runLater(() -> {
                    splashStage.close();
                    User previousUser = FileBasedAuthenticationSystem.checkPreviousLogin();
                    if (previousUser != null) {
                        // إذا وجد تسجيل دخول سابق، قم بتسجيل الدخول تلقائياً وافتح الشاشة الرئيسية
                        LoginPage.loggedInUser = previousUser;
                        RootScreen rootScreen = new RootScreen();
                        rootScreen.start();
                    } else {
                        // إذا لم يوجد تسجيل دخول سابق، اعرض شاشة تسجيل الدخول
                        showLoginPage(primaryStage);
                    }
                });
            } catch (InterruptedException e) {
                System.out.println(":)"+e.getMessage());
            }
        }).start();
    }

    private void showLoginPage(Stage primaryStage) {
        // Your existing login page setup code
        FileBasedAuthenticationSystem.loadUsers();

        // Create main container
        root = new HBox();
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 1000, 600);

        // Create forms and welcome panels
        loginPage = new HBox();
        loginPage.setAlignment(Pos.CENTER);

        signPage = new HBox();
        signPage.setAlignment(Pos.CENTER);

        welcomePanel = createWelcomePanel();
        loginForm = createLoginForm();
        loginPage.getChildren().addAll(loginForm, welcomePanel);

        signupForm = createSignupForm();
        signInPanel = createSignInPanel();
        signPage.getChildren().addAll(signupForm, signInPanel);
        signPage.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        root.getChildren().addAll(loginPage);

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/loginPage/login-page.css")).toExternalForm());

        primaryStage.setTitle("Jibo Sign");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(500);
        primaryStage.show();
    }


    private VBox createWelcomePanel() {
        VBox panel = new VBox(20);
        panel.setMaxHeight(550);
        panel.setAlignment(Pos.CENTER);
        panel.setPadding(new Insets(40));
        panel.getStyleClass().add("welcomePanel");

        Label welcomeTitle = new Label("أهلاً بك");
        welcomeTitle.getStyleClass().add("welcome-title");

        Label welcomeText = new Label("سجل حساباً جديداً للاستمتاع بجميع مزايا\n البرنامج");
        welcomeText.getStyleClass().add("welcome-text");
        welcomeText.setWrapText(true);

        Button signUpButton = new Button("إنشاء حساب");
        signUpButton.getStyleClass().add("welcome-button");
        signUpButton.setOnAction(_ -> transition(loginForm, welcomePanel,signupForm,signInPanel));

        panel.getChildren().addAll(welcomeTitle, welcomeText, signUpButton);
        return panel;
    }

    private VBox createSignInPanel() {
        VBox panel = new VBox(20);
        panel.setMaxHeight(550);
        panel.setAlignment(Pos.CENTER);
        panel.setPadding(new Insets(40));
        panel.getStyleClass().add("sign-in-panel");

        Label welcomeTitle = new Label("أهلاً مجدداً");
        welcomeTitle.getStyleClass().add("welcome-title");

        Label welcomeText = new Label("سجل دخول للاستمتاع بجميع مزايا البرنامج");
        welcomeText.getStyleClass().add("welcome-text");
        welcomeText.setWrapText(true);

        Button signInButton = new Button("تسجيل الدخول");
        signInButton.getStyleClass().add("welcome-button");
        signInButton.setOnAction(_ -> transition(loginForm, welcomePanel,signupForm,signInPanel));

        panel.getChildren().addAll(welcomeTitle, welcomeText, signInButton);
        return panel;
    }

    private VBox createLoginForm() {
        VBox form = new VBox(20);
        form.setAlignment(Pos.CENTER);
        form.setPadding(new Insets(40));

        form.getStyleClass().add("form-container");

        Label titleLabel = new Label("تسجيل الدخول");
        titleLabel.getStyleClass().add("title-label");

        HBox loginWays = new HBox(10);
        loginWays.getStyleClass().add("login-ways");
        loginWays.setAlignment(Pos.CENTER);

        Button fromGoogle = new Button();
        fromGoogle.getStyleClass().add("from-google");

        Button fromFacebook = new Button();
        fromFacebook.getStyleClass().add("from-facebook");

        Button fromTwitter = new Button();
        fromTwitter.getStyleClass().add("from-twitter");

        Button fromGitHub = new Button();
        fromGitHub.getStyleClass().add("from-github");
        loginWays.getChildren().addAll(fromGoogle, fromFacebook, fromTwitter, fromGitHub);

        Label label = new Label();
        label.getStyleClass().add("label");
        label.setText("أو أستخدم الأيميل وكلمة المرور");

        TextField emailField = new TextField();
        emailField.setPromptText("البريد الإلكتروني");
        emailField.getStyleClass().add("text-field");

        // PasswordField
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("كلمة المرور");
        passwordField.getStyleClass().add("text-field");

        // Visible Password Field
        TextField visiblePasswordField = new TextField();
        visiblePasswordField.getStyleClass().add("text-field");

        HBox passwordFieldContainer = new HBox(10);
        passwordFieldContainer.getStyleClass().add("field-container");
        passwordFieldContainer.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(passwordField, Priority.ALWAYS);

        Button forgetPassword = new Button();
        forgetPassword.getStyleClass().add("forget-password");
        forgetPassword.setText("هل نسيت كلمة المرور ?");

        // زر متغير الصور بكل ضغطة
        Button togglePasswordVisibilityButton = createPasswordToggleButton(passwordField, visiblePasswordField, passwordFieldContainer);
        togglePasswordVisibilityButton.getStyleClass().add("toggle-password-button");

        // HBox for PasswordField and button
        passwordFieldContainer.getChildren().addAll(passwordField, togglePasswordVisibilityButton);

        Button loginButton = new Button("تسجيل الدخول");
        loginButton.getStyleClass().add("primary-button");

        loginButton.setOnAction(_ -> handleLogin(emailField.getText(), passwordField, visiblePasswordField));

        form.getChildren().addAll(
                titleLabel,
                loginWays,
                label,
                emailField,
                passwordFieldContainer,
                forgetPassword,
                loginButton
        );

        return form;
    }

    private VBox createSignupForm() {
        VBox form = new VBox(20);
        form.setAlignment(Pos.CENTER);
        form.setPadding(new Insets(40));
        form.getStyleClass().add("form-container1");

        Label titleLabel = new Label("إنشاء حساب جديد");
        titleLabel.getStyleClass().add("title-label");

        HBox loginWays = new HBox(10);
        loginWays.getStyleClass().add("login-ways");
        loginWays.setAlignment(Pos.CENTER);

        Button fromGoogle = new Button();
        fromGoogle.getStyleClass().add("from-google");

        Button fromFacebook = new Button();
        fromFacebook.getStyleClass().add("from-facebook");

        Button fromTwitter = new Button();
        fromTwitter.getStyleClass().add("from-twitter");

        Button fromGitHub = new Button();
        fromGitHub.getStyleClass().add("from-github");
        loginWays.getChildren().addAll(fromGoogle, fromFacebook, fromTwitter, fromGitHub);


        // Name field
        HBox nameBox = new HBox(10);
        nameBox.getStyleClass().add("field-container");
        Label nameLabel = new Label("الاسم الكامل:");
        TextField nameField = new TextField();
        nameField.setPromptText("الاسم الكامل");
        nameField.getStyleClass().add("text-field");
        nameBox.getChildren().addAll(nameField, nameLabel);
        nameBox.setAlignment(Pos.CENTER_LEFT);

        // Email field
        HBox emailBox = new HBox(10);
        emailBox.getStyleClass().add("field-container");
        Label emailLabel = new Label("البريد الإلكتروني:");
        TextField emailField = new TextField();
        emailField.setPromptText("البريد الإلكتروني");
        emailField.getStyleClass().add("text-field");
        emailBox.getChildren().addAll(emailField, emailLabel);
        emailBox.setAlignment(Pos.CENTER_LEFT);

        // Phone field
        HBox phoneBox = new HBox(10);
        phoneBox.getStyleClass().add("field-container");
        Label phoneLabel = new Label("رقم الهاتف:");
        TextField phoneField = new TextField();
        phoneField.setPromptText("رقم الهاتف");
        phoneField.getStyleClass().add("text-field");
        phoneBox.getChildren().addAll(phoneField, phoneLabel);
        phoneBox.setAlignment(Pos.CENTER_LEFT);

        // Password fields
        HBox passwordBox = new HBox(10);
        passwordBox.getStyleClass().add("field-container");
        Label passwordLabel = new Label("كلمة المرور:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("كلمة المرور");
        passwordField.getStyleClass().add("text-field");
        passwordBox.getChildren().addAll(passwordField, passwordLabel);
        passwordBox.setAlignment(Pos.CENTER_LEFT);

        HBox confirmPasswordBox = new HBox(10);
        confirmPasswordBox.getStyleClass().add("field-container");
        Label confirmPasswordLabel = new Label("تأكيد كلمة المرور:");
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("تأكيد كلمة المرور");
        confirmPasswordField.getStyleClass().add("text-field");
        confirmPasswordBox.getChildren().addAll(confirmPasswordField, confirmPasswordLabel);
        confirmPasswordBox.setAlignment(Pos.CENTER_LEFT);

        // Set growth priorities
        HBox.setHgrow(nameField, Priority.ALWAYS);
        HBox.setHgrow(emailField, Priority.ALWAYS);
        HBox.setHgrow(phoneField, Priority.ALWAYS);
        HBox.setHgrow(passwordField, Priority.ALWAYS);
        HBox.setHgrow(confirmPasswordField, Priority.ALWAYS);

        // Account type selection

        HBox toggleGroupBox = new HBox(10);
        toggleGroupBox.getStyleClass().add("toggle-group");
        ToggleGroup accountTypeGroup = new ToggleGroup();
        RadioButton personalAccountButton = new RadioButton("حساب شخصي");
        personalAccountButton.setToggleGroup(accountTypeGroup);
        personalAccountButton.setSelected(true);
        personalAccountButton.getStyleClass().add("radio-button");

        RadioButton workerAccountButton = new RadioButton("حساب عامل");
        workerAccountButton.setToggleGroup(accountTypeGroup);
        workerAccountButton.getStyleClass().add("radio-button");

        toggleGroupBox.getChildren().addAll(personalAccountButton, workerAccountButton);

        // Buttons
        Button signupButton = new Button("إنشاء الحساب");
        signupButton.getStyleClass().add("primary-button");


        String userType = personalAccountButton.isSelected() ? "personal" : "worker";
        signupButton.setOnAction(_ -> handleSignup(
                nameField.getText(),
                emailField.getText(),
                passwordField.getText(),
                confirmPasswordField.getText(),
                phoneField.getText(),
                userType
        ));

        form.getChildren().addAll(
                titleLabel,
                loginWays,
                nameBox,
                emailBox,
                phoneBox,
                passwordBox,
                confirmPasswordBox,
                toggleGroupBox,
                signupButton
        );

        return form;
    }

    private void transition(VBox loginForm, VBox welcomePanel, VBox signupForm, VBox signInPanel) {
        isLoginView = !isLoginView;

        TranslateTransition exitTransition1, exitTransition2, enterTransition1, enterTransition2;

        if (isLoginView) {

            exitTransition1 = new TranslateTransition(Duration.seconds(0.7), signupForm);
            exitTransition1.setToX(0);

            enterTransition1 = new TranslateTransition(Duration.seconds(0.7), signInPanel);
            enterTransition1.setToX(0);

            exitTransition2 = new TranslateTransition(Duration.seconds(0.7), loginForm);
            exitTransition2.setToX(loginForm.getWidth());

            enterTransition2 = new TranslateTransition(Duration.seconds(0.7), welcomePanel);
            enterTransition2.setToX(-welcomePanel.getWidth());

            exitTransition2.play();
            exitTransition1.play();
            enterTransition2.play();
            enterTransition1.play();

            exitTransition2.setOnFinished(_ -> {
                root.getChildren().clear();
                root.getChildren().add(signPage);
            });
        } else {
            // Transition from signup to login
            exitTransition1 = new TranslateTransition(Duration.seconds(0.7), signupForm);
            exitTransition1.setToX(signupForm.getWidth());

            enterTransition1 = new TranslateTransition(Duration.seconds(0.7), signInPanel);
            enterTransition1.setToX(-signInPanel.getWidth());

            exitTransition2 = new TranslateTransition(Duration.seconds(0.7), loginForm);
            exitTransition2.setToX(0);

            enterTransition2 = new TranslateTransition(Duration.seconds(0.7), welcomePanel);
            enterTransition2.setToX(0);

            exitTransition1.play();
            exitTransition2.play();
            enterTransition1.play();
            enterTransition2.play();

            exitTransition1.setOnFinished(_ -> {
                root.getChildren().clear();
                root.getChildren().add(loginPage);
            });
        }
    }

    private Button createPasswordToggleButton(PasswordField passwordField, TextField visiblePasswordField, HBox container) {
        Button toggleButton = new Button();
        toggleButton.setGraphic(new ImageView(hiddenIcon));

        toggleButton.setOnAction(_ -> {
            if (passwordField.isVisible()) {
                // التبديل إلى الحقل المرئي
                String currentPassword = passwordField.getText();
                passwordField.setVisible(false);
                visiblePasswordField.setText(currentPassword);
                visiblePasswordField.setPromptText(passwordField.getPromptText());
                HBox.setHgrow(visiblePasswordField, Priority.ALWAYS);
                container.getChildren().set(0, visiblePasswordField);
                toggleButton.setGraphic(new ImageView(visibleIcon));
            } else {
                // التبديل إلى حقل كلمة المرور
                String currentPassword = visiblePasswordField.getText();
                passwordField.setVisible(true);
                passwordField.setText(currentPassword);
                container.getChildren().set(0, passwordField);
                toggleButton.setGraphic(new ImageView(hiddenIcon));
            }
        });

        return toggleButton;
    }



    private void handleLogin(String email, PasswordField passwordField, TextField visiblePasswordField) {
        String password = passwordField.isVisible() ? passwordField.getText() : visiblePasswordField.getText();

        loggedInUser = FileBasedAuthenticationSystem.loginUser(email, password);
        if (loggedInUser != null) {
            System.out.println("سجلت بنجاج"+ loggedInUser.getUsername());

            //اخذ اي عنصر لجلب ال stage الحالي
            Stage currentStage = (Stage) root.getScene().getWindow();
            currentStage.close();

            RootScreen rootScreen;
            rootScreen = new RootScreen();
            rootScreen.start();
        } else {
            showAlert(Alert.AlertType.ERROR, "خطأ", "فشل تسجيل الدخول",
                    "البريد الإلكتروني أو كلمة المرور غير صحيحة");
        }
    }

    private void handleSignup(String name, String email, String password, String confirmPassword,
                              String phoneNumber, String userType) {
        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "خطأ", "كلمات المرور غير متطابقة",
                    "يرجى التأكد من تطابق كلمتي المرور");
            return;
        }

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "خطأ", "بيانات غير مكتملة",
                    "يرجى ملء جميع الحقول المطلوبة");
            return;
        }

        if (!EmailCheck.isValidEmail(email)) {
            showAlert(Alert.AlertType.ERROR, "خطأ", "البريد الألكتروني غير صالح",
                    "يرجى التأكد من صحة البريد الألكتروني");
            return;
        }
        if (!PasswordCheck.isValidPassword(password)){
            showAlert(Alert.AlertType.ERROR, "خطأ", "كلمة المرور ضعيفة",
                    "1 يجب ان تكون من 8 خانات على الأقل\n 2 تحتوي على حرف كبير و صغير على الأقل \n 3 تحتوي على رمز على الأقل");
            return;
        }

        if (FileBasedAuthenticationSystem.registerUser(name, password, email, userType, phoneNumber)) {
            showAlert(Alert.AlertType.INFORMATION, "نجاح", "تم إنشاء الحساب",
                    "تم إنشاء الحساب بنجاح");
            //
        } else {
            showAlert(Alert.AlertType.ERROR, "خطأ", "الحساب",
                    "يرجى استخدام ايميل");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.getDialogPane().getStyleClass().add("alert");
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}