import control.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	public static final String server =  "127.0.0.1";
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
    public void start(Stage primaryStage) {
        Controller controller = new Controller(primaryStage);
    }
}
