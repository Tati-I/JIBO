package bar.right;

import auth.LogOut;
import javafx.animation.ScaleTransition;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.main.*;

import java.util.Objects;

public class RightSideBar {
    // تحديد مسار الموارد للصور
    private static final String RESOURCES_PATH = "/Pictures/";
    static Pane rightSideBar;
    private Button menuButton;


    public Button getMenuButton() {
        return menuButton;
    }
    // المُنشئ الذي يقوم بإنشاء الشريط الجانبي الأيمن
    public RightSideBar(Pane leftSideBar) {
        createRightSideBar(leftSideBar);
    }

    // دالة إنشاء الشريط الجانبي الأيمن
    private void createRightSideBar(Pane leftSidePane) {
        rightSideBar = new Pane();
        rightSideBar.setPrefWidth(400);
        rightSideBar.setId("rightSideBar");

        // إنشاء حاوية للعناصر
        VBox mainContainer = new VBox();
        mainContainer.setPadding(new Insets(10, 0, 10, 0));
        mainContainer.setAlignment(Pos.TOP_CENTER);

        // شعار التطبيق
        ImageView logoImageView = createLogo();
        VBox logoContainer = new VBox(logoImageView);
        logoContainer.setAlignment(Pos.TOP_CENTER);

        logoContainer.prefHeightProperty().bind(mainContainer.heightProperty());

        // إنشاء حاوية لأزرار القائمة
        VBox menuContainer = createMenuContainer(leftSidePane);
        menuContainer.setAlignment(Pos.TOP_CENTER);

        mainContainer.prefHeightProperty().bind(mainContainer.heightProperty());
        // زر تسجيل الخروج
        Button logoutBtn = createLogoutButton();
        VBox logoutContainer = new VBox(logoutBtn);
        logoutContainer.setAlignment(Pos.BOTTOM_CENTER);

        logoutContainer.prefHeightProperty().bind(mainContainer.heightProperty());
        LogOut logOut = new LogOut();


        logoutBtn.setOnAction(e -> {
            leftSidePane.setEffect(new GaussianBlur(10));

            logOut.handleLogout();
            leftSidePane.setEffect(null);

        });

        // إضافة جميع العناصر إلى الحاوية الرئيسية
        VBox smallRightBarContainer = menu();
        smallRightBarContainer.setAlignment(Pos.TOP_RIGHT);

        smallRightBarContainer.prefHeightProperty().bind(mainContainer.heightProperty());

        mainContainer.getChildren().addAll(smallRightBarContainer, menuContainer, logoutContainer);

        // جعل الحاوية الرئيسية responsive
        mainContainer.prefWidthProperty().bind(rightSideBar.widthProperty());
        mainContainer.prefHeightProperty().bind(rightSideBar.heightProperty());
        // إضافة الحاوية الرئيسية إلى الشريط الجانبي الأيمن
        rightSideBar.getChildren().add(mainContainer);
    }

    // دالة إنشاء شعار التطبيق
    private ImageView createLogo() {
        Image logo = loadImage("logo1.png");
        ImageView logoImageView = new ImageView(logo);
        logoImageView.setFitHeight(120);
        logoImageView.setFitWidth(120);
        logoImageView.setPreserveRatio(true);
        return logoImageView;
    }

    private VBox menu() {
        VBox menuContainer = new VBox();
        menuButton = createRightSideBarButton("homeBtn","","menu-bar.png");

        menuButton.setStyle("-fx-background-color: transparent;");
        menuButton.setPrefSize(24, 24);
        menuContainer.getChildren().addAll(menuButton);
        return menuContainer;
    }

    // دالة إنشاء حاوية أزرار القائمة
    private VBox createMenuContainer(Pane leftSidePane) {
        VBox menuContainer = new VBox(10);
        menuContainer.setAlignment(Pos.TOP_CENTER);

        Button homeBtn = createRightSideBarButton("homeBtn", "الصفحة الرئيسية","home-button.png");
        Button requestServiceBtn = createRightSideBarButton("requestServiceBtn", "طلب خدمة","add.png");
        Button myServicesBtn = createRightSideBarButton("myServicesBtn", "خدماتي","customer-service.png");
        Button profileBtn = createRightSideBarButton("profileBtn", "ملفي الشخصي","user.png");
        Button settingsBtn = createRightSideBarButton("settingsBtn", "إعدادات","settings.png");

        menuContainer.getChildren().addAll(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn);
        // ربط الأزرار بالوظائف المناسبة
        requestLeftPanes(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn, leftSidePane);

        return menuContainer;
    }

    // دالة إنشاء زر في الشريط الجانبي الأيمن
    private Button createRightSideBarButton(String id, String name,String iconPath) {
        ImageView imageView = new ImageView(loadImage(iconPath));
        Button rightSideBarButton = new Button(name,imageView);
        rightSideBarButton.setId(id);
        rightSideBarButton.setPrefWidth(220);
        rightSideBarButton.setAlignment(Pos.TOP_LEFT);
        rightSideBarButton.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        rightSideBarButton.setOnMouseEntered(_ -> createUpAnimateButton(rightSideBarButton));
        rightSideBarButton.setOnMouseExited(_ -> createDownAnimateButton(rightSideBarButton));
        return rightSideBarButton;
    }

    // دالة إنشاء زر تسجيل الخروج
    private Button createLogoutButton() {
        Button logoutBtn = new Button("تسجيل الخروج");
        logoutBtn.setId("logoutBtn");
        logoutBtn.setPrefWidth(200);
        logoutBtn.setOnMouseEntered(_ -> createUpAnimateButton(logoutBtn));
        logoutBtn.setOnMouseExited(_ -> createDownAnimateButton(logoutBtn));
        return logoutBtn;
    }



    // دالة إنشاء حركة تكبير الزر عند تمرير الماوس
    private void createUpAnimateButton(Button button) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), button);
        scaleUp.setToX(1.1);
        scaleUp.setToY(1.1);
        scaleUp.play();
    }

    // دالة إنشاء حركة تصغير الزر عند إزالة الماوس
    private void createDownAnimateButton(Button button) {
        SmallRightSideBar.createDownAnimateButton(button);
    }

    // دالة تحميل الصور
    public Image loadImage(String imageName) {
        return new Image(Objects.requireNonNull(getClass().getResource(RESOURCES_PATH + imageName)).toExternalForm());
    }

    // دالة للحصول على الشريط الجانبي الأيمن
    public Pane getRightSideBar() {
        return rightSideBar;
    }

    // دالة لربط الأزرار بالوظائف المناسبة في الجانب الأيسر
    public void requestLeftPanes(Button homeBtn, Button requestServiceBtn, Button myServicesBtn, Button profileBtn, Button settingsBtn, Pane leftSidePane) {
        // مصفوفة تحتوي جميع الأزرار
        homeBtn.setOnMouseClicked(_ -> {
            resetButtonStyles(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn); // إعادة ضبط الأنماط
            homeBtn.setStyle("-fx-background-color: rgba(255,255,255,0.12);-fx-text-fill: white");
            homeBtn.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true); // تفعيل حالة التحديد


            HomeScreen homeScreen = new HomeScreen();
            homeScreen.RequestHomePane(leftSidePane);
        });

        requestServiceBtn.setOnMouseClicked(_ -> {
            resetButtonStyles(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn); // إعادة ضبط الأنماط
            requestServiceBtn.setStyle("-fx-background-color: rgba(255,255,255,0.12);-fx-text-fill: white");
            requestServiceBtn.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);

            RequestServicesPane requestServicesPane = new RequestServicesPane();
            requestServicesPane.showServicesPage(leftSidePane);// استدعاء صفحة طلب الخدمات
        });

        myServicesBtn.setOnMouseClicked(_ -> {
            resetButtonStyles(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn); // إعادة ضبط الأنماط
            myServicesBtn.setStyle("-fx-background-color: rgba(255,255,255,0.12);-fx-text-fill: white");
            myServicesBtn.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);

            MyServices myServices = new MyServices();
            myServices.MyServicePane(leftSidePane);

            // استدعاء الصفحة الخاصة بـ MyServices
        });

        profileBtn.setOnMouseClicked(_ -> {
            resetButtonStyles(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn); // إعادة ضبط الأنماط
            profileBtn.setStyle("-fx-background-color: rgba(255,255,255,0.12);-fx-text-fill: white");
            profileBtn.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);
            MyProfile myProfile = new MyProfile();
            myProfile.showMyProfilePage(leftSidePane);

            // استدعاء الصفحة الخاصة بـ Profile
        });

        settingsBtn.setOnMouseClicked(_ -> {
            resetButtonStyles(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn); // إعادة ضبط الأنماط
            settingsBtn.setStyle("-fx-background-color: rgba(255,255,255,0.12);-fx-text-fill: white");
            settingsBtn.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);
            SettingPage settingPage = new SettingPage();
            settingPage.SettingPane(leftSidePane);


            // استدعاء الصفحة الخاصة بـ Settings
        });

    }

    // دالة لإعادة تعيين أنماط الأزرار
    void resetButtonStyles(Button... buttons) {
        for (Button btn : buttons) {
            btn.setStyle("");
        }
    }



}