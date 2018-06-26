package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GUIMain {

    private Stage window;
    private ActorPlayer actorPlayer;
    private GUIBottom bottom;
    private GUICenter center;
    private GUILeft left;
    private GUITop top;
    private GUIData data = new GUIData();
    private BorderPane mainLayout;
    
    GUIMain(ActorPlayer actorPlayer, Stage primaryStage) {
        this.actorPlayer = actorPlayer;
        this.start(primaryStage);
    }

    public void start(Stage primaryStage) {

        data.initPlayers();
        window = primaryStage;
        window.setTitle("Ticket to Ride");
        window.setOnCloseRequest(e -> {
            e.consume();
            top.closeProgram();
        });

        Scene mainScene = new Scene(buildMainLayout());

        window.setScene(mainScene);
        window.show();
    }

    private BorderPane buildMainLayout() {
        this.mainLayout = new BorderPane();

        buildTop(this.mainLayout);
        buildLeft(this.mainLayout);
        buildBottom(this.mainLayout);
        buildCenter(this.mainLayout);

        return this.mainLayout;
    }

    public BorderPane getMainLayout() {
        return this.mainLayout;
    }

    private void buildTop(BorderPane mainLayout) {
        top = new GUITop(this, mainLayout, actorPlayer, window);
        mainLayout.setTop(top.getTopLayout());
    }

    void buildLeft(BorderPane mainLayout) {
        left = new GUILeft(this, mainLayout, actorPlayer);
        mainLayout.setLeft(left.getLeft());
    }

    void buildBottom(BorderPane mainLayout) {
        bottom = new GUIBottom(this, mainLayout, data);
        mainLayout.setBottom(bottom.getBottom());
    }

    private void buildCenter(BorderPane mainLayout) {
        center = new GUICenter(actorPlayer);
        mainLayout.setCenter(center.getCenter());
    }
}

// drawCard actor player
// contructline actor player
// endAction controller
// setplayed controller atualizar interface inteira
// colocar label do controller getPlayerTurn