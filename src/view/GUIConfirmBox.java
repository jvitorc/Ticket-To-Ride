package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class GUIConfirmBox {

    private static boolean answer;

    public static boolean display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        StackPane pane = new StackPane();
        pane.setPrefSize(250,100);

        VBox confirmBoxLayout = new VBox();
        confirmBoxLayout.setPadding(new Insets(10));

        Label label = new Label();
        label.setText(message);

        Button buttonConfirm = new Button("Sim");
        buttonConfirm.setOnAction(e -> {
            answer = true;
            window.close();
        });
        buttonConfirm.setPadding(new Insets(5, 20, 5, 20));

        Button buttonDecline = new Button("Não");
        buttonDecline.setOnAction(e -> {
            answer = false;
            window.close();
        });
        buttonDecline.setPadding(new Insets(5, 20, 5, 20));


        HBox buttons = new HBox();
        buttons.getChildren().addAll(buttonConfirm, buttonDecline);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(15);
        buttons.setPadding(new Insets(15, 0,0,0));

        confirmBoxLayout.setAlignment(Pos.CENTER);
        confirmBoxLayout.getChildren().addAll(label, buttons);
        pane.getChildren().add(confirmBoxLayout);
        StackPane.setAlignment(confirmBoxLayout, Pos.CENTER_LEFT);

        window.setScene(new Scene(pane));
        window.showAndWait();

        return  answer;
    }
}
