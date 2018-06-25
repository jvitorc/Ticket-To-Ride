package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GUIMain extends Application {

    private Stage window;
    private GUIData data = new GUIData();
    private GUIBottom bottom;
    private GUICenter center;
    private GUILeft left;
    private GUITop top;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

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
        BorderPane mainLayout = new BorderPane();

        buildTop(mainLayout);
        buildLeft(mainLayout);
        buildBottom(mainLayout);
        buildCenter(mainLayout);

        return mainLayout;
    }

    private void buildTop(BorderPane mainLayout) {
        top = new GUITop(this, mainLayout, data, window);
        mainLayout.setTop(top.getTopLayout());
    }

    void buildLeft(BorderPane mainLayout) {
        left = new GUILeft(this, mainLayout, data);
        mainLayout.setLeft(left.getLeft());
    }

    void buildBottom(BorderPane mainLayout) {
        bottom = new GUIBottom(this, mainLayout, data);
        mainLayout.setBottom(bottom.getBottom());
    }

    private void buildCenter(BorderPane mainLayout) {
        center = new GUICenter(data);
        mainLayout.setCenter(center.getCenter());
    }
}
