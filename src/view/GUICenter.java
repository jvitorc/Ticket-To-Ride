package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GUICenter {

    private GUIData data;
    private Pane centerLayout;

    GUICenter(GUIData data) {
        this.data = data;
        this.centerLayout = buildCenter();
    }

    public Pane getCenter() {
        return this.centerLayout;
    }

    private Pane buildCenter() {
        Pane centerLayout = new Pane();

        GridPane routes = buildCenterGrid();

        centerLayout.getChildren().addAll(routes);

        return centerLayout;
    }

    private GridPane buildCenterGrid() {
        GridPane routeGrid = new GridPane();
        routeGrid.setPadding(new Insets(5, 20,20,20));

        ToggleGroup routeGroup = new ToggleGroup();

        data.initRoutes();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 13; j ++) {
                RadioButton route = buildConstructOption(data.getRoutes().get(j));
                route.setToggleGroup(routeGroup);
                GridPane.setConstraints(route, i, j);
                routeGrid.getChildren().add(route);
            }
        }

        Button button = new Button("Construir");
        button.setPadding(new Insets(50,10,50,10));
        GridPane.setConstraints(button, 7, 1, 1,13);
        button.setOnAction(e -> constructHandler());

        routeGrid.getChildren().add(button);
        routeGroup.getSelectedToggle();

        return routeGrid;
    }

    private RadioButton buildConstructOption(String routeName) {
        RadioButton route = new RadioButton(routeName);
        Insets routePadding = new Insets(5, 20, 5, 20);

        route.setPadding(routePadding);
        return route;
    }

    private void constructHandler() {

    }
}
