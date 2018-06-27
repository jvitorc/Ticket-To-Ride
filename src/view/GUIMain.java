package view;

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
    private BorderPane mainLayout;
    
    GUIMain(ActorPlayer actorPlayer, Stage primaryStage) {
        this.actorPlayer = actorPlayer;
        this.start(primaryStage);
    }

    public void start(Stage primaryStage) {

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
        top = new GUITop(actorPlayer, window);
        mainLayout.setTop(top.getTopLayout());
    }

    void buildLeft(BorderPane mainLayout) {
        left = new GUILeft(actorPlayer);
        mainLayout.setLeft(left.getLeft());
    }

    void buildBottom(BorderPane mainLayout) {
        bottom = new GUIBottom(actorPlayer);
        mainLayout.setBottom(bottom.getBottom());
    }

    void buildCenter(BorderPane mainLayout) {
        center = new GUICenter(actorPlayer);
        mainLayout.setCenter(center.getCenter());
    }
}
