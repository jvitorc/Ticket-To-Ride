package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUIMessageBox {

    public static void display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        StackPane pane = new StackPane();
        pane.setPrefSize(250,100);

        Label label = new Label();
        label.setText(message);

        label.setAlignment(Pos.CENTER);
        pane.getChildren().add(label);
        StackPane.setAlignment(label, Pos.CENTER);

        window.setScene(new Scene(pane));
        window.showAndWait();
    }
}
