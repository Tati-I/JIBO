package bar.right;

import auth.LogOut;
import javafx.animation.ScaleTransition;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
    private boolean smallSize = false;

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


        VBox menuButtonContainer = new VBox();
        menuButton = createsmallRightBarButton("homeBtn","menu-bar.png","");
        menuButton.setAlignment(Pos.TOP_RIGHT);
        mainContainer.setSpacing(100);
        mainContainer.setPadding(new Insets(20, 0, 0, 0));

        menuButton = createsmallRightBarButton("homeBtn","menu-bar.png","");
        menuButton.setAlignment(Pos.TOP_RIGHT);

        menuButton.setOnAction(_ -> {
            if (smallSize) {
                smallRightBar.prefWidthProperty().bind(leftSidePane.widthProperty().multiply(0.4));
                smallSize = false;
            } else {
                smallRightBar.prefWidthProperty().bind(leftSidePane.widthProperty().multiply(0.075));
                smallSize = true;
            }


            for (Node node : menuButtonContainer.getChildren()) {
                if (node instanceof Button) {
                    HBox content = (HBox) ((Button) node).getGraphic();
                    if (content != null && content.getChildren().get(1) instanceof Label) {
                        content.getChildren().get(1).setVisible(!smallSize);
                    }
                }
            }
        });

        menuButtonContainer.getChildren().addAll(menuButton);



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

        mainContainer.getChildren().addAll(menuButtonContainer,menuContainer, logoutContainer);

        // جعل الحاوية الرئيسية
        mainContainer.prefWidthProperty().bind(smallRightBar.widthProperty());
        mainContainer.prefHeightProperty().bind(smallRightBar.heightProperty());

        // إضافة الحاوية الرئيسية إلى الشريط الجانبي الأيمن
        smallRightBar.getChildren().add(mainContainer);
    }



    // دالة إنشاء حاوية أزرار القائمة
    private VBox createMenuContainer(Pane leftSidePane) {
        VBox menuContainer = new VBox(10);
        Button homeBtn = createsmallRightBarButton("homeBtn", "home-button.png", "الصفحة الرئيسية");
        Button requestServiceBtn = createsmallRightBarButton("requestServiceBtn", "add.png", "طلب خدمة");
        Button myServicesBtn = createsmallRightBarButton("myServicesBtn", "customer-service.png", "خدماتي");
        Button profileBtn = createsmallRightBarButton("profileBtn", "user.png", "ملفي الشخصي");
        Button settingsBtn = createsmallRightBarButton("settingsBtn", "settings.png", "الإعدادت");

        menuContainer.getChildren().addAll(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn);
        // ربط الأزرار بالوظائف المناسبة
        requestLeftPanes(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn, leftSidePane);

        menuContainer.setAlignment(Pos.CENTER);
        return menuContainer;
    }

    // دالة إنشاء زر في الشريط الجانبي الأيمن
    private Button createsmallRightBarButton(String id, String iconPath, String labelText) {
        ImageView imageView = new ImageView(loadImage(iconPath));


        HBox buttonContent = new HBox(10);
        buttonContent.setAlignment(Pos.CENTER_RIGHT);

        Label label = new Label(labelText);
        label.setStyle("-fx-text-fill: white;-fx-font-size: 14;");
        label.setVisible(!smallSize);


        buttonContent.getChildren().addAll(label, imageView);


        Button smallRightBarButton = new Button("", buttonContent);
        smallRightBarButton.setId(id);
        smallRightBarButton.setMaxWidth(Double.MAX_VALUE);
        smallRightBarButton.setAlignment(Pos.CENTER_RIGHT);


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
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), button);
        scaleUp.setToX(0.9);
        scaleUp.setToY(0.9);
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