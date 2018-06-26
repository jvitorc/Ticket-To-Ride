package view;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.City;
import model.Line;

public class GUICenter {

	private GUIMain main;
    private BorderPane mainLayout;
    private ActorPlayer actorPlayer;
    private Pane centerLayout;
    private ArrayList<Line> lines;


    GUICenter(GUIMain main, BorderPane mainLayout, ActorPlayer actorPlayer) {
    	this.main = main;
        this.mainLayout = mainLayout;
        this.actorPlayer = actorPlayer;
        this.lines = actorPlayer.getDummyArray();
        this.centerLayout = buildCenter();
    }

    public Pane getCenter() {
        return this.centerLayout;
    }

    private Pane buildCenter() {
        Pane centerLayout = new Pane();
        centerLayout.getStylesheets().add(getClass().getResource("WagonColor.css").toExternalForm());

        
        try {
        	if (actorPlayer.getBoard().getLines() != null) {
        		this.lines = actorPlayer.getBoard().getLines();
        	}
        } catch (Exception e ) { 
        	this.lines = actorPlayer.getDummyArray();
        }
        
       //System.out.println(x);
        GridPane routes = buildCenterGrid();

        centerLayout.getChildren().addAll(routes);

        return centerLayout;
    }

    private GridPane buildCenterGrid() {
        GridPane routeGrid = new GridPane();
        routeGrid.setPadding(new Insets(5, 20,20,20));
        routeGrid.setVgap(5);
        routeGrid.setHgap(5);
        
        GUILine guiLine;

        ToggleGroup routeGroup = new ToggleGroup();
        	
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 13; j ++) {
            	ToggleButton route = buildConstructOption(this.lines.get((j)+(i*13)).toString2());
            	if ((this.lines.get((j)+(i*13)).getPlayer() != null)) {
            		guiLine = new GUILine(true, (j)+(i*13), this.lines.get((j)+(i*13)).getColor());
            		guiLine.setOwner(actorPlayer.setGUIPlayer(this.lines.get((j)+(i*13)).getPlayer()));
            		route.getStyleClass().add(guiLine.getOwner().getColor());
            		route.setUserData(guiLine);
            	} else {
            		route.setUserData(new GUILine(false, (j)+(i*13), this.lines.get((j)+(i*13)).getColor()));
            	}
                route.setToggleGroup(routeGroup);
                route.getStyleClass().add("button");
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

        route.setMinWidth(200);
        route.setPadding(routePadding);
        return route;
    }

    private void constructHandler(ToggleGroup group) {
        GUILine lineData = ((GUILine) group.getSelectedToggle().getUserData());

        try {
            if (!lineData.getConsumed()) {
                actorPlayer.buildLine(lineData.getLineIndex(), lineData.getColor());
                consumeRoute(lineData, group, actorPlayer.getGUIPlayers().get(0));
            } else {
                GUIMessageBox.display("Alerta", "Rota já está ocupada");
            }
        } catch (NullPointerException e){
            GUIMessageBox.display("Alerta", "Trilha não selecionada");
        }
    }

    private void consumeRoute(GUILine lineData, ToggleGroup group, GUIPlayer guiPlayer) {
    	lineData.setConsumed(true);
    	lineData.setOwner(guiPlayer);
        group.getSelectedToggle().setUserData(lineData);
        ToggleButton button = (ToggleButton) group.getSelectedToggle();
        button.getStyleClass().add(actorPlayer.getPlayer().getColor());
        button.setToggleGroup(group);
    }
   
}
