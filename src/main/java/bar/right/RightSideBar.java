package bar.right;

import auth.LogOut;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.example.main.*;

import java.util.Objects;

public class RightSideBar {
    private static final String RESOURCES_PATH = "/Pictures/";
    private Pane smallRightBar;
    private Button homeBtn;
    private Button requestServiceBtn;
    private Button myServicesBtn;
    private Button profileBtn;
    private Button settingsBtn;
    private Button logoutBtn;

    // المُنشئ الذي يقوم بإنشاء الشريط الجانبي الأيمن
    public RightSideBar(Pane sideBar) {
        createSmallRightBar(sideBar);
    }

    // دالة إنشاء الشريط الجانبي الأيمن
    private void createSmallRightBar(Pane leftSidePane) {
        smallRightBar = new Pane();
        smallRightBar.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/rightSideBar/rightSideBar.css")).toExternalForm());

        VBox topElements = new VBox();

        ImageView imageView = new ImageView(loadImage("logo1.png"));
        imageView.setFitHeight(34);
        imageView.setFitWidth(34);

        VBox imageVBox = new VBox();
        imageVBox.setAlignment(Pos.CENTER);
        imageVBox.getChildren().add(imageView);
        imageVBox.prefHeightProperty().bind(topElements.heightProperty().multiply(0.8));

        VBox smallRightBarContainer = menu(leftSidePane);
        smallRightBarContainer.setAlignment(Pos.TOP_RIGHT);
        smallRightBarContainer.setPadding(new Insets(0, 10, 0, 10));
        smallRightBarContainer.prefHeightProperty().bind(topElements.heightProperty().multiply(0.2));

        topElements.getChildren().addAll(smallRightBarContainer, imageVBox);

        VBox mainContainer = new VBox();
        mainContainer.setPadding(new Insets(10, 0, 10, 0));
        mainContainer.setAlignment(Pos.TOP_CENTER);

        topElements.prefHeightProperty().bind(mainContainer.heightProperty());

        VBox menuContainer = createMenuContainer(leftSidePane);
        menuContainer.setAlignment(Pos.TOP_CENTER);
        menuContainer.prefHeightProperty().bind(mainContainer.heightProperty());

        logoutBtn = createLogoutButton();
        VBox logoutContainer = new VBox(logoutBtn);
        logoutContainer.setAlignment(Pos.BOTTOM_CENTER);
        logoutContainer.prefHeightProperty().bind(mainContainer.heightProperty());

        LogOut logOut = new LogOut();
        logoutBtn.setOnAction(_ -> {
            leftSidePane.setEffect(new GaussianBlur(10));
            logOut.handleLogout();
            leftSidePane.setEffect(null);
        });

        mainContainer.getChildren().addAll(topElements, menuContainer, logoutContainer);
        assert smallRightBar != null;
        mainContainer.prefWidthProperty().bind(smallRightBar.widthProperty());
        mainContainer.prefHeightProperty().bind(smallRightBar.heightProperty());
        smallRightBar.getChildren().add(mainContainer);
        smallRightBar.setId("rightSideBar");
    }

    private VBox menu(Pane leftSidePane) {
        VBox menuContainer = new VBox();
        Button menuButton = createsmallRightBarButton("homeBtn", "menu-bar.png");
        menuContainer.getChildren().addAll(menuButton);
        menuButton.setPrefSize(24, 24);

        boolean[] isExpanded = {false};

        menuButton.setOnAction(_ -> {
            isExpanded[0] = !isExpanded[0];//هون كل ما اكبس على الزر بعكس قيمتو بدال ما اتحقق من كل مرة
            smallRightBar.prefWidthProperty().unbind();

            if (isExpanded[0]) {
                smallRightBar.prefWidthProperty().bind(leftSidePane.widthProperty().multiply(0.4));
                updateButtonsWithText(true);
            } else {
                smallRightBar.prefWidthProperty().bind(leftSidePane.widthProperty().multiply(0.085));
                updateButtonsWithText(false);
            }
        });

        return menuContainer;
    }

    // دالة إنشاء حاوية أزرار القائمة
    private VBox createMenuContainer(Pane leftSidePane) {
        VBox menuContainer = new VBox(10);
        menuContainer.setAlignment(Pos.TOP_CENTER);

        homeBtn = createsmallRightBarButton("homeBtn", "home-button.png");
        homeBtn.setStyle("-fx-background-color: rgba(255,255,255,0.12);-fx-text-fill: white");
        requestServiceBtn = createsmallRightBarButton("requestServiceBtn", "add.png");
        myServicesBtn = createsmallRightBarButton("myServicesBtn", "customer-service.png");
        profileBtn = createsmallRightBarButton("profileBtn", "user.png");
        settingsBtn = createsmallRightBarButton("settingsBtn", "settings.png");

        menuContainer.getChildren().addAll(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn);
        // ربط الأزرار بالوظائف المناسبة
        requestLeftPanes(homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn, leftSidePane);
        return menuContainer;
    }

    // دالة إنشاء زر في الشريط الجانبي الأيمن
    private Button createsmallRightBarButton(String id, String iconPath) {
        ImageView imageView = new ImageView(loadImage(iconPath));
        Button smallRightBarButton = new Button("", imageView);
        smallRightBarButton.setId(id);
        smallRightBarButton.setAlignment(Pos.TOP_LEFT);
        smallRightBarButton.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        return smallRightBarButton;
    }

    // دالة إنشاء زر تسجيل الخروج
    private Button createLogoutButton() {
        ImageView imageView = new ImageView(loadImage("logout.png"));
        Button logoutBtn = new Button("", imageView);
        logoutBtn.setPrefWidth(24);
        logoutBtn.setId("logoutBtn");
        return logoutBtn;
    }

    // دالة تحميل الصور
    public Image loadImage(String imageName) {
        return new Image(Objects.requireNonNull(getClass().getResource(RESOURCES_PATH + imageName)).toExternalForm());
    }

    // دالة للحصول على الشريط الجانبي الأيمن
    public Pane getSmallRightBar() {
        return smallRightBar;
    }

    // دالة حديثة ربط الأزرار بالوظائف المناسبة في الجانب الأيسر
    public void requestLeftPanes(Button homeBtn, Button requestServiceBtn, Button myServicesBtn,
                                 Button profileBtn, Button settingsBtn, Pane leftSidePane) {

        Button[] buttons = {homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn};
        Runnable[] pageLoaders = { // هي مصفوفة صفحات مرتبين متل ترتيب الأزرار اللي فوق
                () -> new HomeScreen().RequestHomePane(leftSidePane),
                () -> new RequestServicesPane().showServicesPage(leftSidePane),
                () -> new MyServices().MyServicePane(leftSidePane),
                () -> new MyProfile().showMyProfilePage(leftSidePane),
                () -> new SettingPage().SettingPane(leftSidePane)
        };

        // إعداد الحدث لكل زر باستخدام حلقة
        for (int i = 0; i < buttons.length; i++) {
            Button button = buttons[i];
            Runnable loadPage = pageLoaders[i];
            button.setOnMouseClicked(_ -> {
                resetButtonStyles(buttons); // إعادة ضبط الأنماط
                button.setStyle("-fx-background-color: rgba(255,255,255,0.12); -fx-text-fill: white");
                loadPage.run(); // استدعاء الصفحة المقابلة
            });
        }
    }

    // دالة لإعادة تعيين أنماط الأزرار
    void resetButtonStyles(Button... buttons) {
        for (Button btn : buttons) {
            btn.setStyle("");
        }
    }
    private void updateButtonsWithText(boolean showText) {
        // مصفوفة تحتوي على جميع الأزرار
        Button[] buttons = {homeBtn, requestServiceBtn, myServicesBtn, profileBtn, settingsBtn, logoutBtn};
        String[] texts = {"الرئيسية", "طلب خدمة", "خدماتي", "الملف الشخصي", "الإعدادات", "تسجيل خروج"};
        int width = showText ? 185 : 24;
        // تحديث الأزرار باستخدام حلقة
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText(showText ? texts[i] : "");
            buttons[i].setPrefWidth(width);
        }
    }
}