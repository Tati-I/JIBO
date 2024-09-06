package org.example.main;

import javafx.animation.ScaleTransition;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.Objects;

public class RightSideBar {
    // تحديد مسار الموارد للصور
    private static final String RESOURCES_PATH = "/Pictures/";
    private Pane rightSideBar;

    // المُنشئ الذي يقوم بإنشاء الشريط الجانبي الأيمن
    public RightSideBar(Pane leftSideBar) {
        createRightSideBar(leftSideBar);
    }

    // دالة إنشاء الشريط الجانبي الأيمن
    private void createRightSideBar(Pane leftSidePane) {
        rightSideBar = new Pane();
        rightSideBar.setId("rightSideBar");

        // إنشاء حاوية للعناصر
        VBox mainContainer = new VBox();
        mainContainer.setPadding(new Insets(20, 10, 20, 10));
        mainContainer.setAlignment(Pos.TOP_CENTER);

        // شعار التطبيق
        ImageView logoImageView = createLogo();
        VBox logoContainer = new VBox(logoImageView);
        logoContainer.setAlignment(Pos.CENTER);

        // إنشاء حاوية لأزرار القائمة
        VBox menuContainer = createMenuContainer(leftSidePane);
        menuContainer.setAlignment(Pos.TOP_CENTER);

        // زر تسجيل الخروج
        Button logoutBtn = createLogoutButton();
        VBox logoutContainer = new VBox(logoutBtn);
        logoutContainer.setAlignment(Pos.BOTTOM_CENTER);
        logoutContainer.setPadding(new Insets(150, 0, 0, 0));

        // إضافة جميع العناصر إلى الحاوية الرئيسية
        mainContainer.getChildren().addAll(logoContainer, menuContainer, logoutContainer);

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
        logoImageView.setPreserveRatio(true);
        logoImageView.fitWidthProperty().bind(rightSideBar.widthProperty().multiply(0.90));
        return logoImageView;
    }

    // دالة إنشاء حاوية أزرار القائمة
    private VBox createMenuContainer(Pane leftSidePane) {
        VBox menuContainer = new VBox(10);
        menuContainer.setAlignment(Pos.TOP_CENTER);

        Button homeBtn = createRightSideBarButton("homeBtn", "الصفحة الرئيسية");
        Button requestServiceBtn = createRightSideBarButton("requestServiceBtn", "طلب خدمة");
        Button myServicesBtn = createRightSideBarButton("myServicesBtn", "خدماتي");
        Button profileBtn = createRightSideBarButton("profileBtn", "ملفي الشخصي");
        Button settingsBtn = createRightSideBarButton("settingsBtn", "إعدادات");

        menuContainer.getChildren().addAll(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn);

        // ربط الأزرار بالوظائف المناسبة
        requestLeftPanes(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn, leftSidePane);

        return menuContainer;
    }

    // دالة إنشاء زر في الشريط الجانبي الأيمن
    private Button createRightSideBarButton(String id, String name) {
        Button rightSideBarButton = new Button(name);
        rightSideBarButton.setId(id);
        rightSideBarButton.setPrefWidth(220);
        rightSideBarButton.setStyle("-fx-alignment: center");
        rightSideBarButton.setOnMouseEntered(_ -> createUpAnimateButton(rightSideBarButton));
        rightSideBarButton.setOnMouseExited(_ -> createDownAnimateButton(rightSideBarButton));
        return rightSideBarButton;
    }

    // دالة إنشاء زر تسجيل الخروج
    private Button createLogoutButton() {
        Button logoutBtn = new Button("تسجيل الخروج");
        logoutBtn.setId("logoutBtn");
        logoutBtn.setPrefWidth(220);
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
    public Pane getRightSideBar() {
        return rightSideBar;
    }

    // دالة لربط الأزرار بالوظائف المناسبة في الجانب الأيسر
    public void requestLeftPanes(Button homeBtn, Button requestServiceBtn, Button myServicesBtn, Button profileBtn, Button settingsBtn, Pane leftSidePane) {
        // مصفوفة تحتوي جميع الأزرار
        homeBtn.setOnMouseClicked(_ -> {
            resetButtonStyles(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn); // إعادة ضبط الأنماط
            homeBtn.setStyle("-fx-background-color: #fff;-fx-text-fill: black");
            homeBtn.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true); // تفعيل حالة التحديد


            HomeScreen homeScreen = new HomeScreen();
            homeScreen.RequestHomePane(leftSidePane);
        });

        requestServiceBtn.setOnMouseClicked(_ -> {
            resetButtonStyles(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn); // إعادة ضبط الأنماط
            requestServiceBtn.setStyle("-fx-background-color: #fff;-fx-text-fill: black");
            requestServiceBtn.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);

            RequestServicesPane requestServicesPane = new RequestServicesPane();
            requestServicesPane.showServicesPage(leftSidePane);// استدعاء صفحة طلب الخدمات
        });

        myServicesBtn.setOnMouseClicked(_ -> {
            resetButtonStyles(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn); // إعادة ضبط الأنماط
            myServicesBtn.setStyle("-fx-background-color: #fff;-fx-text-fill: black");
            myServicesBtn.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);

            MyServices myServices = new MyServices();
            myServices.MyServicePane(leftSidePane);

            // استدعاء الصفحة الخاصة بـ MyServices
        });

        profileBtn.setOnMouseClicked(_ -> {
            resetButtonStyles(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn); // إعادة ضبط الأنماط
            profileBtn.setStyle("-fx-background-color: #fff;-fx-text-fill: black");
            profileBtn.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);
            MyProfile myProfile = new MyProfile();
            myProfile.myProfilePane(leftSidePane);

            // استدعاء الصفحة الخاصة بـ Profile
        });

        settingsBtn.setOnMouseClicked(_ -> {
            resetButtonStyles(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn); // إعادة ضبط الأنماط
            settingsBtn.setStyle("-fx-background-color: #fff;-fx-text-fill: black");
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