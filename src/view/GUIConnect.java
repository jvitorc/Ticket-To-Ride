package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUIConnect {

    private static Stage window;
    private static ActorPlayer actorPlayer;

    public static void setActorPlayer(ActorPlayer actorPlayer) {
        GUIConnect.actorPlayer = actorPlayer;
    }

    public static void display() {
        window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Conectar");

        GridPane connectLayout = new GridPane();
        connectLayout.setPadding(new Insets(10, 10, 10, 10));
        connectLayout.setVgap(8);
        connectLayout.setHgap(10);

        Label nameLabel = new Label("Nome: ");
        GridPane.setConstraints(nameLabel, 0,0);

        Label serverLabel = new Label("Server: ");
        GridPane.setConstraints(serverLabel, 0,1);

        TextField nameInput = new TextField();
        nameInput.setPromptText("Nome");
        GridPane.setConstraints(nameInput, 1, 0, 10, 1);

        TextField serverInput = new TextField("127.0.0.1");
        GridPane.setConstraints(serverInput, 1, 1, 10 , 1);

        Button connectButton = new Button("Conectar");
        connectButton.setOnAction(e -> connect(nameInput.getText(),
                                               serverInput.getText()));
        GridPane.setConstraints(connectButton, 1, 2);

        Button closeButton = new Button("Fechar");
        closeButton.setOnAction(e -> window.close());
        GridPane.setConstraints(closeButton, 2, 2);

        connectLayout.getChildren().addAll(nameLabel,
                                           serverLabel,
                                           nameInput,
                                           serverInput,
                                           connectButton,
                                           closeButton);
        Scene scene = new Scene(connectLayout);
        window.setScene(scene);
        window.showAndWait();
    }

    private static void connect(String nameInput, String serverInput) {
        actorPlayer.connect(serverInput, nameInput);
        window.close();
    }
}
