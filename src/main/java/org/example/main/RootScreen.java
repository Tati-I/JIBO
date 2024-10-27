package org.example.main;

import bar.right.RightSideBar;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Objects;

public class RootScreen {
    private BorderPane root;
    public HBox contentArea;
    private Pane leftSidePane;

    public void start() {
        initializeLayout();
        setupSidebars();
        setupHomeScreen();
        setupScene();
    }

    private void initializeLayout() {
        root = new BorderPane();
        contentArea = new HBox();
        leftSidePane = new Pane();
        root.setCenter(contentArea);
    }

    private void setupSidebars() {

        RightSideBar rightSideBar = new RightSideBar(leftSidePane);
        Pane smallRightSideBarPane = rightSideBar.getSmallRightBar();
        smallRightSideBarPane.prefWidthProperty().bind(contentArea.widthProperty().multiply(0.075));
        smallRightSideBarPane.prefHeightProperty().bind(contentArea.heightProperty());

        contentArea.getChildren().add(smallRightSideBarPane);

    }

    private void setupHomeScreen() {
        HomeScreen homeScreen = new HomeScreen();
        Pane homePane = homeScreen.RequestHomePane(leftSidePane);
        contentArea.getChildren().addFirst(homePane);

        homePane.prefWidthProperty().bind(contentArea.widthProperty().multiply(0.925));
        homePane.prefHeightProperty().bind(contentArea.heightProperty());
    }

    public void setupScene() {
        // الحصول على أبعاد الشاشة الحالية
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // حساب العرض والارتفاع بشكل نسبي مع أبعاد الشاشة
        double width = screenBounds.getWidth() * 0.7;  // نسبة 80% من عرض الشاشة
        double height = screenBounds.getHeight() * 0.8;  // نسبة 80% من ارتفاع الشاشة

        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/LightMode.css")).toExternalForm());

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Jibo");
        primaryStage.getIcons().add(new RightSideBar(new Pane()).loadImage("icon.png"));

        // ضبط الحجم الأدنى للنافذة لضمان استجابة أفضل
        primaryStage.setMinHeight(700);
        primaryStage.setMinWidth(820);

        // تعيين الحجم القياسي بناءً على أبعاد الشاشة
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}