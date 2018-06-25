package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
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
        centerLayout.getStylesheets().add(getClass().getResource("WagonColor.css").toExternalForm());

        GridPane routes = buildCenterGrid();

        centerLayout.getChildren().addAll(routes);

        return centerLayout;
    }

    private GridPane buildCenterGrid() {
        GridPane routeGrid = new GridPane();
        routeGrid.setPadding(new Insets(5, 20,20,20));
        routeGrid.setVgap(5);
        routeGrid.setHgap(5);

        ToggleGroup routeGroup = new ToggleGroup();

        data.initRoutes();
        data.initRoutesSizes();
        Boolean consumed = false;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 13; j ++) {
                ToggleButton route = buildConstructOption(data.getRoutes().get(j) + " " + data.getRoutesSize().get(j));
                route.setUserData(consumed);
                route.setToggleGroup(routeGroup);
                GridPane.setConstraints(route, i, j);
                routeGrid.getChildren().add(route);
            }
        }

        Button button = new Button("Construir");
        button.setPadding(new Insets(50,10,50,10));
        GridPane.setConstraints(button, 7, 1, 1,13);
        button.setOnAction(e -> constructHandler(routeGroup));

        routeGrid.getChildren().add(button);
        routeGroup.getSelectedToggle();

        return routeGrid;
    }

    private ToggleButton buildConstructOption(String routeName) {
        ToggleButton route = new ToggleButton(routeName);
        Insets routePadding = new Insets(5, 20, 5, 20);

        route.setMinWidth(250);
        route.setPadding(routePadding);
        return route;
    }

    private void constructHandler(ToggleGroup group) {
        //pega dados do jogador e ve se ele tem o que precisa pra construir essa linha
        //se construir esse botao tem que ficar indisponivel

        Boolean consumed = false;

        try {
            consumed = (Boolean) group.getSelectedToggle().getUserData();

            if (!consumed) {
                consumeRoute(consumed, group);
            } else {
                GUIMessageBox.display("Alerta", "Rota já está ocupada");
            }
        } catch (NullPointerException e){
            GUIMessageBox.display("Alerta", "Trilha não selecionada");
        }
        // teste se possivel construir
        // pega quantidade de cartas do jogador e compara com a cor da rota
    }

    private void consumeRoute(Boolean consumed, ToggleGroup group) {
        consumed = true;
        group.getSelectedToggle().setUserData(consumed);
        ToggleButton button = (ToggleButton) group.getSelectedToggle();
        button.getStyleClass().add(data.getPlayers().get(0).getColor());
        button.setToggleGroup(group);
    }
}
