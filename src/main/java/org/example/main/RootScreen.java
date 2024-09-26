package org.example.main;

import bar.right.RightSideBar;
import bar.right.SmallRightSideBar;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class RootScreen {
    private BorderPane root;
    private HBox contentArea;
    private Pane leftSidePane;
    private Pane rightSideBarPane;
    private Pane smallRightSideBarPane;

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
        rightSideBarPane = rightSideBar.getRightSideBar();
        rightSideBarPane.prefWidthProperty().bind(contentArea.widthProperty().multiply(0.35));
        rightSideBarPane.prefHeightProperty().bind(contentArea.heightProperty());

        SmallRightSideBar smallRightSideBar = new SmallRightSideBar(leftSidePane);
        smallRightSideBarPane = smallRightSideBar.getsmallRightBar();
        smallRightSideBarPane.prefWidthProperty().bind(contentArea.widthProperty().multiply(0.065));
        smallRightSideBarPane.prefHeightProperty().bind(contentArea.heightProperty());

        contentArea.getChildren().add(smallRightSideBarPane);

        smallRightSideBar.getMenuButton().setOnAction(e -> toggleSidebar());
        rightSideBar.getMenuButton().setOnAction(e -> toggleSidebar());
    }

    private void toggleSidebar() {
        if (contentArea.getChildren().contains(smallRightSideBarPane)) {
            contentArea.getChildren().remove(smallRightSideBarPane);
            contentArea.getChildren().add(rightSideBarPane);
        } else {
            contentArea.getChildren().remove(rightSideBarPane);
            contentArea.getChildren().add(smallRightSideBarPane);
        }
    }

    private void setupHomeScreen() {
        HomeScreen homeScreen = new HomeScreen();
        Pane homePane = homeScreen.RequestHomePane(leftSidePane);
        contentArea.getChildren().addFirst(homePane);

        homePane.prefWidthProperty().bind(contentArea.widthProperty().subtract(smallRightSideBarPane.widthProperty()));
        homePane.prefHeightProperty().bind(contentArea.heightProperty());
    }

    private void setupScene() {
        Scene scene = new Scene(root, 1200, 860);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/LightMode.css")).toExternalForm());

        Stage primaryStage = new Stage();
        primaryStage.setTitle("Jibo");
        primaryStage.getIcons().add(new RightSideBar(new Pane()).loadImage("icon.png"));
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(700);
        primaryStage.setMinWidth(820);
        primaryStage.show();
    }
}