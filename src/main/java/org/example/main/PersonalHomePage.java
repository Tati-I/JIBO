package org.example.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class PersonalHomePage extends Application {
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 1200, 780);

        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());


        Image settingIcon = new Image(Objects.requireNonNull(getClass().getResource("/Pictures/settings.png")).toExternalForm());
        ImageView settingIconView = new ImageView(settingIcon);
        settingIconView.setFitWidth(17);
        settingIconView.setFitHeight(17);
        settingIconView.setLayoutX(1);
        settingIconView.setLayoutY(114);

        Image aboutIcon = new Image(Objects.requireNonNull(getClass().getResource("/Pictures/about.png")).toExternalForm());
        ImageView aboutIconView = new ImageView(aboutIcon);
        aboutIconView.setFitWidth(17);
        aboutIconView.setFitHeight(17);
        aboutIconView.setLayoutX(1);
        aboutIconView.setLayoutY(165);

        Image profileIcon = new Image(Objects.requireNonNull(getClass().getResource("/Pictures/profile.png")).toExternalForm());
        ImageView profileIconView = new ImageView(profileIcon);
        profileIconView.setFitWidth(18);
        profileIconView.setFitHeight(18);
        profileIconView.setLayoutX(1);
        profileIconView.setLayoutY(11);

        Image logoutIcon = new Image(Objects.requireNonNull(getClass().getResource("/Pictures/logout.png")).toExternalForm());
        ImageView logoutIconView = new ImageView(logoutIcon);
        logoutIconView.setFitWidth(18);
        logoutIconView.setFitHeight(18);
        logoutIconView.setLayoutX(1);
        logoutIconView.setLayoutY(63);

        VBox leftMenu = new VBox();
        leftMenu.getStyleClass().add("left-menu");
        leftMenu.setSpacing(10);
        Button settingsBtn = new Button("Settings");
        Button aboutBtn = new Button("About");
        Button profileBtn = new Button("Profile");
        Button logoutBtn = new Button("Logout");





        leftMenu.getChildren().addAll(profileBtn, logoutBtn, settingsBtn, aboutBtn);




        primaryStage.setScene(scene);
        primaryStage.setTitle("Personal Home Page");
        primaryStage.setResizable(false);
        primaryStage.show();
        root.getChildren().addAll(leftMenu,settingIconView,aboutIconView,profileIconView,logoutIconView);




    }

    public static void main(String[] args) {
        launch(args);
    }

}
