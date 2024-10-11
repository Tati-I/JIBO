package bar.right;

import auth.LogOut;
import javafx.animation.ScaleTransition;
import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.example.main.*;

import java.util.Objects;

public class SmallRightSideBar {
    // تحديد مسار الموارد للصور
    private static final String RESOURCES_PATH = "/Pictures/";
    private Pane smallRightBar;

    private Button menuButton;

    public Button getMenuButton() {
        return menuButton;
    }

    // المُنشئ الذي يقوم بإنشاء الشريط الجانبي الأيمن
    public SmallRightSideBar(Pane sideBar) {
        sideBar.getChildren().clear();

        createSmallRightBar(sideBar);
    }

    // دالة إنشاء الشريط الجانبي الأيمن
    private void createSmallRightBar(Pane leftSidePane) {
        smallRightBar = new Pane();
        smallRightBar.setId("rightSideBar");

        // إنشاء حاوية للعناصر
        VBox mainContainer = new VBox();
        mainContainer.setId("mainContainer");
        mainContainer.setAlignment(Pos.CENTER);

        VBox smallRightBarContainer = menu();
        smallRightBarContainer.setAlignment(Pos.TOP_CENTER);
        smallRightBarContainer.prefHeightProperty().bind(mainContainer.heightProperty());

        // إنشاء حاوية لأزرار القائمة
        VBox menuContainer = createMenuContainer(leftSidePane);
        menuContainer.setAlignment(Pos.CENTER);
        menuContainer.prefWidthProperty().bind(mainContainer.widthProperty());

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

        mainContainer.getChildren().addAll(smallRightBarContainer,menuContainer, logoutContainer);

        // جعل الحاوية الرئيسية
        mainContainer.prefWidthProperty().bind(smallRightBar.widthProperty());
        mainContainer.prefHeightProperty().bind(smallRightBar.heightProperty());

        // إضافة الحاوية الرئيسية إلى الشريط الجانبي الأيمن
        smallRightBar.getChildren().add(mainContainer);
    }

    private VBox menu() {
        VBox menuContainer = new VBox();
        menuButton = createsmallRightBarButton("homeBtn","menu-bar.png");
        menuContainer.getChildren().addAll(menuButton);
        return menuContainer;
    }

    // دالة إنشاء حاوية أزرار القائمة
    private VBox createMenuContainer(Pane leftSidePane) {
        VBox menuContainer = new VBox(10);

        Button homeBtn = createsmallRightBarButton("homeBtn","home-button.png");
        Button requestServiceBtn = createsmallRightBarButton("requestServiceBtn","add.png");
        Button myServicesBtn = createsmallRightBarButton("myServicesBtn","customer-service.png");
        Button profileBtn = createsmallRightBarButton("profileBtn","user.png");
        Button settingsBtn = createsmallRightBarButton("settingsBtn","settings.png");

        menuContainer.getChildren().addAll(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn);
        // ربط الأزرار بالوظائف المناسبة
        requestLeftPanes(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn, leftSidePane);

        menuContainer.setAlignment(Pos.CENTER);
        return menuContainer;
    }

    // دالة إنشاء زر في الشريط الجانبي الأيمن
    private Button createsmallRightBarButton(String id ,String iconPath) {
        ImageView imageView = new ImageView(loadImage(iconPath));
        Button smallRightBarButton = new Button("",imageView);
        smallRightBarButton.setId(id);
        smallRightBarButton.setOnMouseEntered(_ -> createUpAnimateButton(smallRightBarButton));
        smallRightBarButton.setOnMouseExited(_ -> createDownAnimateButton(smallRightBarButton));
        return smallRightBarButton;
    }

    // دالة إنشاء زر تسجيل الخروج
    private Button createLogoutButton() {
        ImageView imageView = new ImageView(loadImage("logout.png"));
        Button logoutBtn = new Button("",imageView);
        logoutBtn.setId("logoutBtn");
        logoutBtn.setOnMouseEntered(_ -> createUpAnimateButton(logoutBtn));
        logoutBtn.setOnMouseExited(_ -> createDownAnimateButton(logoutBtn));
        return logoutBtn;
    }

    // دالة إنشاء حركة تكبير الزر عند تمرير الماوس
    public static void createUpAnimateButton(Button button) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(199), button);
        scaleUp.setToX(1.1);
        scaleUp.setToY(1.1);
        scaleUp.play();
    }

    // دالة إنشاء حركة تصغير الزر عند إزالة الماوس
    public static void createDownAnimateButton(Button button) {
        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), button);
        scaleDown.setToX(1);
        scaleDown.setToY(1);
        scaleDown.play();
    }

    // دالة تحميل الصور
    public Image loadImage(String imageName) {
        return new Image(Objects.requireNonNull(getClass().getResource(RESOURCES_PATH + imageName)).toExternalForm());
    }

    // دالة للحصول على الشريط الجانبي الأيمن
    public Pane getSmallRightBar() {
        return smallRightBar;
    }

    // دالة لربط الأزرار بالوظائف المناسبة في الجانب الأيسر
    public void requestLeftPanes(Button homeBtn, Button requestServiceBtn, Button myServicesBtn, Button profileBtn, Button settingsBtn, Pane leftSidePane) {
        // مصفوفة تحتوي جميع الأزرار
        homeBtn.setOnMouseClicked(_ -> {
            resetButtonStyles(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn); // إعادة ضبط الأنماط
            homeBtn.setStyle("-fx-background-color: rgba(255,255,255,0.12);-fx-text-fill: black");
            homeBtn.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true); // تفعيل حالة التحديد


            HomeScreen homeScreen = new HomeScreen();
            homeScreen.RequestHomePane(leftSidePane);
        });

        requestServiceBtn.setOnMouseClicked(_ -> {
            resetButtonStyles(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn); // إعادة ضبط الأنماط
            requestServiceBtn.setStyle("-fx-background-color: rgba(255,255,255,0.12);-fx-text-fill: black");
            requestServiceBtn.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);

            RequestServicesPane requestServicesPane = new RequestServicesPane();
            requestServicesPane.showServicesPage(leftSidePane);// استدعاء صفحة طلب الخدمات
        });

        myServicesBtn.setOnMouseClicked(_ -> {
            resetButtonStyles(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn); // إعادة ضبط الأنماط
            myServicesBtn.setStyle("-fx-background-color: rgba(255,255,255,0.12);-fx-text-fill: black");
            myServicesBtn.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);

            MyServices myServices = new MyServices();
            myServices.MyServicePane(leftSidePane);

            // استدعاء الصفحة الخاصة بـ MyServices
        });

        profileBtn.setOnMouseClicked(_ -> {
            resetButtonStyles(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn); // إعادة ضبط الأنماط
            profileBtn.setStyle("-fx-background-color: rgba(255,255,255,0.12);-fx-text-fill: black");
            profileBtn.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);
            MyProfile myProfile = new MyProfile();
            myProfile.showMyProfilePage(leftSidePane);

            // استدعاء الصفحة الخاصة بـ Profile
        });

        settingsBtn.setOnMouseClicked(_ -> {
            resetButtonStyles(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn); // إعادة ضبط الأنماط
            settingsBtn.setStyle("-fx-background-color: rgba(255,255,255,0.12);-fx-text-fill: black");
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