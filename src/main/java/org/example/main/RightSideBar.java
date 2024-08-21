package org.example.main;

import javafx.animation.ScaleTransition;
import javafx.css.PseudoClass;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Objects;

public class RightSideBar {
    // تحديد مسار الموارد للصور
    private static final String RESOURCES_PATH = "/Pictures/";
    private Pane rightSideBar;
    private Button logoutBtn;

    // المُنشئ الذي يقوم بإنشاء الشريط الجانبي الأيمن
    public RightSideBar(Pane leftSideBar) {
        createRightSideBar(leftSideBar);
    }

    // دالة إنشاء الشريط الجانبي الأيمن
    private void createRightSideBar(Pane leftSidePane) {
        rightSideBar = new Pane();
        rightSideBar.setId("rightSideBar");
        rightSideBar.setPrefSize(275, 785);
        rightSideBar.setLayoutX(927);
        rightSideBar.setLayoutY(-1);

        // إنشاء وتحسين أزرار القائمة الرئيسية
        Button homeBtn = createRightSideBarButton("homeBtn", "الصفحة الرئيسية", 200);
        Button requestServiceBtn = createRightSideBarButton("requestServiceBtn", "طلب خدمة", 250);
        Button myServicesBtn = createRightSideBarButton("myServicesBtn", "خدماتي", 300);
        Button profileBtn = createRightSideBarButton("profileBtn", "ملفي الشخصي", 350);
        Button settingsBtn = createRightSideBarButton("settingsBtn", "إعدادات", 400);

        // تحسين تصميم زر تسجيل الخروج
        logoutBtn = new Button("تسجيل الخروج");
        logoutBtn.setId("logoutBtn");
        logoutBtn.setPrefSize(100, 30);
        logoutBtn.setLayoutX(87.5); // تعديل الموضع ليكون متناسقاً
        logoutBtn.setLayoutY(690);
        logoutBtn.setOnMouseEntered(_ -> createUpAnimateButton(logoutBtn));
        logoutBtn.setOnMouseExited(_ -> createDownAnimateButton(logoutBtn));

        // إضافة شعار التطبيق مع تصميم محسّن
        Image logo1 = loadImage("logo1.png");
        ImageView logoImageView = new ImageView(logo1);
        logoImageView.setFitHeight(240);
        logoImageView.setFitWidth(240);
        logoImageView.setLayoutX(23); // تعديل الموضع ليكون في الوسط
        logoImageView.setLayoutY(10);

        // ربط الأزرار بالوظائف المناسبة
        requestLeftPanes(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn, leftSidePane);

        // إضافة جميع العناصر إلى الشريط الجانبي الأيمن
        rightSideBar.getChildren().addAll(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn, logoutBtn, logoImageView);
    }

    // دالة إنشاء زر في الشريط الجانبي الأيمن
    private Button createRightSideBarButton(String id,String name, double y) {
        Button rightSideBarButton = new Button(name);
        rightSideBarButton.setId(id);
        rightSideBarButton.setPrefSize(220, 30);
        rightSideBarButton.setLayoutX(27.5);
        rightSideBarButton.setLayoutY(y);
        return rightSideBarButton;
    }

    // دالة إنشاء حركة تكبير الزر عند تمرير الماوس
    private void createUpAnimateButton(Button button) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), button);
        scaleUp.setToX(1.1);
        scaleUp.setToY(1.1);
        scaleUp.play();
    }

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

            RequestServicesPane requestServicesPane = new RequestServicesPane();
            requestServicesPane.showServicesPage(leftSidePane);// استدعاء صفحة طلب الخدمات
        });

        myServicesBtn.setOnMouseClicked(_ -> {
            resetButtonStyles(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn); // إعادة ضبط الأنماط
            myServicesBtn.setStyle("-fx-background-color: #fff;-fx-text-fill: black");

            MyServices myServices = new MyServices();
            myServices.MyServicePane(leftSidePane);

            // استدعاء الصفحة الخاصة بـ MyServices
        });

        profileBtn.setOnMouseClicked(_ -> {
            resetButtonStyles(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn); // إعادة ضبط الأنماط
            profileBtn.setStyle("-fx-background-color: #fff;-fx-text-fill: black");

            MyProfile myProfile = new MyProfile();
            myProfile.myProfilePane(leftSidePane);

            // استدعاء الصفحة الخاصة بـ Profile
        });

        settingsBtn.setOnMouseClicked(_ -> {
            resetButtonStyles(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn); // إعادة ضبط الأنماط
            settingsBtn.setStyle("-fx-background-color: #fff;-fx-text-fill: black");
            SettingPage settingPage = new SettingPage();
            settingPage.SettingPane(leftSidePane);


            // استدعاء الصفحة الخاصة بـ Settings
        });
    }

    void resetButtonStyles(Button homeBtn, Button requestServiceBtn, Button myServicesBtn, Button profileBtn, Button settingsBtn) {
        Button[] buttons = {homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn};
        for (Button btn : buttons) {
            btn.setStyle(""); // إعادة تعيين الأنماط الحالية للزر
            btn.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), false); // إلغاء حالة التحديد
        }
    }

}