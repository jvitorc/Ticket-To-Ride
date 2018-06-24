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

        window = primaryStage;
        window.setTitle("Ticket to Ride");
        window.setOnCloseRequest(e -> {
            e.consume();
            top.closeProgram();
        });

        Scene mainScene = new Scene(buildMainLayout());
        mainScene.getStylesheets().add(getClass().getResource("WagonColor.css").toExternalForm());

        window.setScene(mainScene);
        window.show();
    }

    private BorderPane buildMainLayout() {
        BorderPane mainLayout = new BorderPane();

        bottom = new GUIBottom(data);
        center = new GUICenter(data);
        left = new GUILeft(data);
        top = new GUITop(data, window);

        mainLayout.setTop(top.getTopLayout());
        mainLayout.setLeft(left.getLeft());
        mainLayout.setCenter(center.getCenter());
        mainLayout.setBottom(bottom.getBottom());

        return mainLayout;
    }
}
