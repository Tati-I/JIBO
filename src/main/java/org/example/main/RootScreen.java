package org.example.main;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class RootScreen {
    // الدالة الرئيسية لبدء تشغيل الواجهة
    public void start() {
        // إنشاء لوحة الجانب الأيسر
        Pane leftSidePane = new Pane();
        // إنشاء شريط الجانب الأيمن وربطه بلوحة الجانب الأيسر

        // إنشاء اللوحة الرئيسية للتطبيق
        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background-color: #ffffff;");

        // إنشاء لوحة الجانب الأيسر


        // إنشاء شريط الجانب الأيمن وربطه بلوحة الجانب الأيسر
        RightSideBar rightSideBar = new RightSideBar(leftSidePane);
        Pane rightSideBarPane = rightSideBar.getRightSideBar();

        // إنشاء كائن من الشاشة الرئيسية
        HomeScreen homeScreen = new HomeScreen();
        Pane homePane = homeScreen.RequestHomePane(leftSidePane);

        // إضافة شريط الجانب الأيمن والشاشة الرئيسية إلى اللوحة الرئيسية
        root.getChildren().addAll(rightSideBarPane, homePane);

        // جعل العناصر responsive
        rightSideBarPane.prefWidthProperty().bind(root.widthProperty().multiply(0.22)); // 22% من عرض النافذة
        rightSideBarPane.prefHeightProperty().bind(root.heightProperty());

        AnchorPane.setRightAnchor(rightSideBarPane, 0.0);
        AnchorPane.setTopAnchor(rightSideBarPane, 0.0);
        AnchorPane.setBottomAnchor(rightSideBarPane, 0.0);

        homePane.prefWidthProperty().bind(root.widthProperty().multiply(0.78)); // 78% من عرض النافذة
        homePane.prefHeightProperty().bind(root.heightProperty());

        AnchorPane.setLeftAnchor(homePane, 0.0);
        AnchorPane.setTopAnchor(homePane, 0.0);
        AnchorPane.setBottomAnchor(homePane, 0.0);


        // إنشاء المشهد وإضافة ملف الأنماط
        Scene scene = new Scene(root, 1200, 780);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/style.css")).toExternalForm());

        // إعداد النافذة الرئيسية وعرضها
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Jibo");
        primaryStage.getIcons().add(rightSideBar.loadImage("icon.png"));
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(920);  // الحد الأدنى لعرض النافذة
        primaryStage.setMinHeight(700); // الحد الأدنى لارتفاع النافذة
        primaryStage.show();
    }
}