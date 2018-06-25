package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUIChooseObjectiveBox {

    private static GUIData data;

    GUIChooseObjectiveBox(GUIData data) {
        GUIChooseObjectiveBox.data = data;
    }

    public static void display() {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Objetivos");

        StackPane pane = new StackPane();
        pane.setPrefSize(250,100);

        VBox confirmBoxLayout = new VBox();
        confirmBoxLayout.setPadding(new Insets(10));

        Label label = new Label();
        label.setPadding(new Insets(15));
        label.setText("Escolha os Objetivos");

        // pegar objetivos de uma lista de objetivos, cidades ++ pontos

        VBox objectives = new VBox();

        ToggleButton objective1 = new ToggleButton("Califa - São José 10");
        ToggleButton objective2 = new ToggleButton("Edinburgh - Glasgow 20");
        ToggleButton objective3 = new ToggleButton("Indaiatuba - Campinas 30");

        objective1.setMinWidth(250);
        objective2.setMinWidth(250);
        objective3.setMinWidth(250);

        objectives.setAlignment(Pos.CENTER);
        objectives.setSpacing(2);
        objectives.getChildren().addAll(objective1, objective2, objective3);

        Button buttonConfirm = new Button("Confirmar");
        buttonConfirm.setOnAction(e -> {
            if (objective1.isSelected()) {
                data.addObjective(objective1.getText());
            }
            if (objective2.isSelected()) {
                data.addObjective(objective2.getText());
            }
            if (objective3.isSelected()) {
                data.addObjective(objective3.getText());
            }
            window.close();
        });
        buttonConfirm.setPadding(new Insets(5, 20, 5, 20));

        Button buttonDecline = new Button("Cancelar");
        buttonDecline.setOnAction(e -> {
            window.close();
        });
        buttonDecline.setPadding(new Insets(5, 20, 5, 20));


        HBox buttons = new HBox();
        buttons.getChildren().addAll(buttonConfirm, buttonDecline);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(15);
        buttons.setPadding(new Insets(15, 0,0,0));

        confirmBoxLayout.setAlignment(Pos.CENTER);
        confirmBoxLayout.getChildren().addAll(label, objectives, buttons);
        pane.getChildren().add(confirmBoxLayout);
        StackPane.setAlignment(confirmBoxLayout, Pos.CENTER_LEFT);

        window.setScene(new Scene(pane));
        window.showAndWait();
    }
}
