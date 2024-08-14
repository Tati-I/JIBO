package org.example.main;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RootScreen {
    private final RightSideBar rightSideBar;
    private final Pane mainLayout;

    public RootScreen() {
        this.rightSideBar = new RightSideBar();
        this.mainLayout = new AnchorPane();
        setupLayout();
    }

    private void setupLayout() {
        mainLayout.setPrefSize(1200, 780);
        mainLayout.setStyle("-fx-background-color: #ffffff;");
    }

    public void start() {
        RequestServicesPane requestServicesPane = new RequestServicesPane();
        HomeScreen homeScreen = new HomeScreen();
        Stage primaryStage = new Stage();

        // Add both the right sidebar and the request services pane to the main layout
        mainLayout.getChildren().addAll(rightSideBar.getRightSideBar(),requestServicesPane.getContentPane());

        Scene scene = new Scene(mainLayout);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        primaryStage.setTitle("Jibo");
        primaryStage.getIcons().add(rightSideBar.loadImage("icon.png"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}