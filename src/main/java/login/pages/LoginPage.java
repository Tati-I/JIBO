package login.pages;

import auth.EmailCheck;
import auth.FileBasedAuthenticationSystem;
import auth.PasswordCheck;
import auth.User;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import bar.right.RightSideBar;
import org.example.main.RootScreen;

import java.util.Objects;

public class LoginPage extends Application {
    private Stage primaryStage;

    private static Pane pane;
    private static Button loginButton;
    private static Button signUpButton;
    private static ImageView passwordIconView;
    private static Image visibleIcon;
    private static Image hiddenIcon;
    private static boolean isPasswordVisible = false;
    private static TextField visiblePasswordField;
    private static PasswordField passwordField;
    public static User loggedInUser;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        // استدعاء كائن
        RightSideBar rightSideBar = new RightSideBar(null);
        //وضع icon
        primaryStage = stage;
        stage.getIcons().add(rightSideBar.loadImage("icon.png"));
        //الحاوية الرئيسية
        AnchorPane mainContainer = new AnchorPane();
        mainContainer.setStyle("-fx-background-color: linear-gradient( #3675bd , #002750 )");

        visibleIcon = new Image(Objects.requireNonNull(getClass().getResource("/Pictures/unlock.png")).toExternalForm());
        hiddenIcon = new Image(Objects.requireNonNull(getClass().getResource("/Pictures/lock.png")).toExternalForm());

        pane = new Pane();
        createLoginView();
        HBox rightAndLeft = new HBox(50);
        rightAndLeft.setAlignment(Pos.CENTER);
        rightAndLeft.getChildren().addAll(leftView(),pane);
        rightAndLeft.prefWidthProperty().bind(mainContainer.widthProperty());
        rightAndLeft.prefHeightProperty().bind(mainContainer.heightProperty());

        mainContainer.getChildren().addAll(rightAndLeft);
        Scene scene = new Scene(mainContainer, 1200, 780);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/LightMode.css")).toExternalForm());

        stage.setTitle("Jibo");
        stage.setMinWidth(920);  // الحد الأدنى لعرض النافذة
        stage.setMinHeight(700); // الحد الأدنى لارتفاع النافذة
        stage.setScene(scene);
        stage.show();

    }

    public Pane leftView() {
        RightSideBar rightSideBar = new RightSideBar(null);

        Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Pictures/logo1.png")));
        ImageView logoView = new ImageView(logo);


        ImageView clockView = new ImageView(rightSideBar.loadImage("Clock.png"));
        clockView.setFitHeight(28);
        clockView.setFitWidth(28);

        ImageView eventsView = new ImageView(rightSideBar.loadImage("events.png"));
        eventsView.setFitHeight(28);
        eventsView.setFitWidth(28);

        ImageView multiuserView = new ImageView(rightSideBar.loadImage("multiuser.png"));
        multiuserView.setFitHeight(24);
        multiuserView.setFitWidth(24);

        ImageView serviceRequestView = new ImageView(rightSideBar.loadImage("services.png"));
        serviceRequestView.setFitHeight(28);
        serviceRequestView.setFitWidth(28);

        Label mainText = new Label("كل ما تحتاجه لخدماتك، في مكان واحد");
        mainText.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        mainText.setStyle("-fx-text-fill: #FFFFFF;");

        Label secondaryText1 = new Label("إدارة الخدمات بكل سهولة ومرونة.", serviceRequestView);
        Label secondaryText2 = new Label("الدعم متاح على مدار الساعة لضمان تجربة سلسة.", clockView);
        Label secondaryText3 = new Label("استفد من أحدث الأدوات لتقديم أو طلب الخدمات.", eventsView);
        Label secondaryText4 = new Label("انضم إلى مجتمع من المستخدمين المحترفين والمتخصصين.", multiuserView);

        secondaryText1.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        secondaryText2.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        secondaryText3.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        secondaryText4.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        secondaryText1.setStyle("-fx-text-fill: #FFFFFF;");
        secondaryText2.setStyle("-fx-text-fill: #FFFFFF;");
        secondaryText3.setStyle("-fx-text-fill: #FFFFFF;");
        secondaryText4.setStyle("-fx-text-fill: #FFFFFF;");

        VBox textsvbox = new VBox(15);
        textsvbox.getChildren().addAll(mainText,secondaryText1, secondaryText2, secondaryText3, secondaryText4);

        VBox mainVbox = new VBox(30);
        mainVbox.getChildren().addAll(logoView,textsvbox);
        mainVbox.setAlignment(Pos.CENTER_LEFT);
        return mainVbox;
    }

    public void createLoginView() {
        pane.getChildren().clear();
        pane.setStyle("-fx-background-color: white; -fx-background-radius: 27px;");
        pane.setPrefSize(525, 470);
        pane.setMaxHeight(470);

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
            signUpPage.display(pane, loginButton, signUpButton, loginRect, WelcomeMsg, bioMsg,this);
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

        Login.setOnMouseClicked(_ -> {

            String email = emailField.getText();
            String password = passwordField.getText();

            loggedInUser = FileBasedAuthenticationSystem.loginUser(email, password);
            if (loggedInUser != null) {
                System.out.println("تم تسجيل الدخول بنجاح");
                RootScreen rootScreen = new RootScreen();
                rootScreen.start();
                primaryStage.close();
            } else {
                // تسجيل الدخول فشل
                System.out.println("خطأ في الإيميل أو كلمة المرور");
                errorLabelEmail.setVisible(true);
                errorLabelPassword.setVisible(true);
            }
        });


        pane.getChildren().addAll(WelcomeMsg, bioMsg, loginRect, loginButton, signUpButton, emailWord, emailField, passwordWord, passwordField, visiblePasswordField, Login, privacy, emailIconView, passwordIconView, errorLabelEmail, errorLabelPassword);
    }

    public static void showPassword() {
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