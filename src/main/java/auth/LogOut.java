package auth;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class LogOut {
    private static final String RESOURCES_PATH = "/Pictures/";

    public void handleLogout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("تسجيل الخروج");
        alert.setHeaderText("هل أنت متأكد أنك تريد تسجيل الخروج؟");
        alert.setContentText("اضغط موافق لتسجيل الخروج أو إلغاء للبقاء.");

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

        stage.setWidth(500);  // يمكنك تعديل العرض حسب الحاجة
        stage.setHeight(300);
        stage.getIcons().add(loadImage("logo1.png"));

        alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/logout-dialog.css")).toExternalForm());

        stage.initStyle(StageStyle.UNDECORATED);


        ButtonType logoutButton = new ButtonType("إغلاق");
        ButtonType cancelButton = new ButtonType("إلغاء");

        alert.getButtonTypes().setAll(logoutButton, cancelButton);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == logoutButton) {
                System.out.println("Logging out...");
                FileBasedAuthenticationSystem.logout();
                Platform.exit();
            }

        });
    }

    public Image loadImage(String imageName) {
        return new Image(Objects.requireNonNull(getClass().getResource(RESOURCES_PATH + imageName)).toExternalForm());
    }
}