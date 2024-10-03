package org.example.main;
import javafx.scene.layout.*;


public class ServiceDetailPage extends VBox {

    public Pane serviceDetailPage(Pane leftSidePane) {
        leftSidePane.getChildren().clear();
        Pane mainPane = new Pane();
        mainPane.prefWidthProperty().bind(leftSidePane.widthProperty());
        mainPane.prefHeightProperty().bind(leftSidePane.heightProperty());
        leftSidePane.getChildren().add(mainPane);



        return leftSidePane;
    }
}
