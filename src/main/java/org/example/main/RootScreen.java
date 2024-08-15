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
        RightSideBar rightSideBar = new RightSideBar(leftSidePane);

        // إنشاء اللوحة الرئيسية للتطبيق
        AnchorPane root = new AnchorPane();
        root.setPrefSize(1200, 780);
        root.setStyle("-fx-background-color: #ffffff;");

        // إنشاء كائن من الشاشة الرئيسية
        HomeScreen homeScreen = new HomeScreen();

        // إضافة شريط الجانب الأيمن والشاشة الرئيسية إلى اللوحة الرئيسية
        root.getChildren().addAll(rightSideBar.getRightSideBar(), homeScreen.RequestHomePane(leftSidePane));

        // إنشاء المشهد وإضافة ملف الأنماط
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/style.css")).toExternalForm());

        // إعداد النافذة الرئيسية وعرضها
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Jibo");
        primaryStage.getIcons().add(rightSideBar.loadImage("icon.png"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}