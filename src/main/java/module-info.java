module org.example.main {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.main to javafx.fxml;
    exports org.example.main;
    exports auth;
    opens auth to javafx.fxml;
    exports login.pages;
    opens login.pages to javafx.fxml;
    exports bar.right;
    opens bar.right to javafx.fxml;
}