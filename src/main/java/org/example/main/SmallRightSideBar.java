package org.example.main;

import javafx.animation.ScaleTransition;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.Objects;

public class SmallRightSideBar {
    // تحديد مسار الموارد للصور
    private static final String RESOURCES_PATH = "/Pictures/";
    private Pane smallRightBar;


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
        mainContainer.setPadding(new Insets(0, 10, 20, 10));
        mainContainer.setAlignment(Pos.TOP_CENTER);

        // شعار التطبيق
        ImageView logoImageView = createLogo();
        VBox logoContainer = new VBox(logoImageView);
        logoContainer.setPadding(new Insets(50,0 , 100, 0));
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
        VBox smallRightBarContainer = menu();



        mainContainer.getChildren().addAll(smallRightBarContainer,logoContainer,menuContainer, logoutContainer);

        // جعل الحاوية الرئيسية responsive
        mainContainer.prefWidthProperty().bind(smallRightBar.widthProperty());
        mainContainer.prefHeightProperty().bind(smallRightBar.heightProperty());

        // إضافة الحاوية الرئيسية إلى الشريط الجانبي الأيمن
        smallRightBar.getChildren().add(mainContainer);
    }

    // دالة إنشاء شعار التطبيق
    private ImageView createLogo() {
        Image logo = loadImage("logo1.png");
        ImageView logoImageView = new ImageView(logo);
        logoImageView.setFitHeight(120);
        logoImageView.setFitWidth(120);
        logoImageView.setPreserveRatio(true);
        logoImageView.fitWidthProperty().bind(smallRightBar.widthProperty().multiply(0.90));
        return logoImageView;
    }

    private VBox menu (){
        VBox menuContainer = new VBox();
        Button menu = createsmallRightBarButton("homeBtn","","menu-bar.png");

        menu.setPrefSize(24,24);
        menuContainer.getChildren().addAll(menu);
        menuContainer.setAlignment(Pos.TOP_RIGHT);
        menu.setOnAction(_ ->{
            RightSideBar rightSideBar = new RightSideBar(menuContainer);
            rightSideBar.getRightSideBar();
        });
        return menuContainer;
    }

    // دالة إنشاء حاوية أزرار القائمة
    private VBox createMenuContainer(Pane leftSidePane) {
        VBox menuContainer = new VBox(10);
        menuContainer.setAlignment(Pos.TOP_CENTER);

        Button homeBtn = createsmallRightBarButton("homeBtn", "الصفحة الرئيسية","home-button.png");
        Button requestServiceBtn = createsmallRightBarButton("requestServiceBtn", "طلب خدمة","add.png");
        Button myServicesBtn = createsmallRightBarButton("myServicesBtn", "خدماتي","customer-service.png");
        Button profileBtn = createsmallRightBarButton("profileBtn", "ملفي الشخصي","user.png");
        Button settingsBtn = createsmallRightBarButton("settingsBtn", "إعدادات","settings.png");

        menuContainer.getChildren().addAll(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn);
        // ربط الأزرار بالوظائف المناسبة
        requestLeftPanes(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn, leftSidePane);

        return menuContainer;
    }

    // دالة إنشاء زر في الشريط الجانبي الأيمن
    private Button createsmallRightBarButton(String id, String name,String iconPath) {
        ImageView imageView = new ImageView(loadImage(iconPath));
        Button smallRightBarButton = new Button(name,imageView);
        smallRightBarButton.setId(id);
        smallRightBarButton.setPrefWidth(220);
        smallRightBarButton.setAlignment(Pos.TOP_LEFT);
        smallRightBarButton.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        smallRightBarButton.setOnMouseEntered(_ -> createUpAnimateButton(smallRightBarButton));
        smallRightBarButton.setOnMouseExited(_ -> createDownAnimateButton(smallRightBarButton));
        return smallRightBarButton;
    }

    // دالة إنشاء زر تسجيل الخروج
    private Button createLogoutButton() {
        Button logoutBtn = new Button("تسجيل الخروجfcgdgfd");
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
    public Pane getsmallRightBar() {
        return smallRightBar;
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
            myProfile.showMyProfilePage(leftSidePane);

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