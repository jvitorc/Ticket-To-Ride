package view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GUIBottom {

    private ActorPlayer actorPlayer;
    private HBox bottomLayout;
    private ArrayList<GUIPlayer> guiPlayers;
    private boolean startGame;

    GUIBottom(ActorPlayer actorPlayer) {
        this.actorPlayer = actorPlayer;
        this.guiPlayers = actorPlayer.getDummyGUIPlayers();
        this.startGame = true;
        this.bottomLayout = buildBottom();
    }

    public HBox getBottom() {
        return this.bottomLayout;
    }

    private HBox buildBottom() {
        this.bottomLayout = new HBox();
        
        try {
        	if (actorPlayer.getGUIPlayers() != null) {
        		this.guiPlayers = actorPlayer.getGUIPlayers();
        		this.startGame = false;
        	}
        } catch (Exception e ) {
        	this.guiPlayers = actorPlayer.getDummyGUIPlayers();
        }

        VBox table = new VBox();
        table.getChildren().addAll(getPlayerTable());
        table.setMaxHeight(150);
        table.setMaxWidth(498);
        table.setAlignment(Pos.CENTER_LEFT);
        
        if ((guiPlayers.get(0).getQttWagons() < 4 || guiPlayers.get(0).getQttWagons() < 4) && !startGame) {
        	endGame();
        }

        GridPane playerCards = buildPlayerCards();
        VBox endAction = buildEndAction();

        this.bottomLayout.setAlignment(Pos.CENTER);
        this.bottomLayout.getChildren().addAll(buildRefreshButton(), table, playerCards, endAction, buildLabelTurn());

        return bottomLayout;
    }

	private TableView<GUIPlayer> getPlayerTable() {
        TableView<GUIPlayer> table = new TableView<>();
        table.setItems(getPlayer());
        table.getColumns().addAll(playerColumn("Nome Jogador", 150, "name"),
								                playerColumn("Cor", 150, "color"),
								                playerColumn("Pontos", 50, "points"),
								                playerColumn("Vagoes", 50, "qttWagons"),
								                playerColumn("Cartas", 50, "qttCards"));

        return table;
    }

    private TableColumn<GUIPlayer, String> playerColumn(String columnName, int columnSize, String columnVariable) {
        TableColumn<GUIPlayer, String> column = new TableColumn<>(columnName);
        column.setMinWidth(columnSize);
        column.setCellValueFactory(new PropertyValueFactory<>(columnVariable));
        column.setStyle( "-fx-alignment: CENTER;");

        return column;
    }

    private ObservableList<GUIPlayer> getPlayer() {
        ObservableList<GUIPlayer> players = FXCollections.observableArrayList();
        
        players.addAll(guiPlayers);

        return players;
    }

    private GridPane buildPlayerCards() {
        GridPane playerCardsLayout = new GridPane();
        playerCardsLayout.setPadding(new Insets(10, 20, 10, 20));
        playerCardsLayout.setVgap(8);
        playerCardsLayout.setHgap(10);

        Label header = new Label("Cartas do Jogador");
        GridPane.setConstraints(header,1,0, 5, 1);

        HBox blackCards = buildQttCards("Pretas", this.guiPlayers.get(0).getBlack());
        GridPane.setConstraints(blackCards, 0,1);

        HBox blueCards = buildQttCards("Azuis", this.guiPlayers.get(0).getBlue());
        GridPane.setConstraints(blueCards, 0,3);

        HBox greenCards = buildQttCards("Verdes", this.guiPlayers.get(0).getGreen());
        GridPane.setConstraints(greenCards, 0,5);

        HBox orangeCards = buildQttCards("Laranjas", this.guiPlayers.get(0).getOrange());
        GridPane.setConstraints(orangeCards, 2,1);

        HBox pinkCards = buildQttCards("Roxas", this.guiPlayers.get(0).getPurple());
        GridPane.setConstraints(pinkCards, 2,3);

        HBox rainbowCards = buildQttCards("Multi-color", this.guiPlayers.get(0).getMulticolor());
        GridPane.setConstraints(rainbowCards, 2,5);

        HBox redCards = buildQttCards("Vermelhas", this.guiPlayers.get(0).getRed());
        GridPane.setConstraints(redCards, 4,1);

        HBox whiteCards = buildQttCards("Brancas", this.guiPlayers.get(0).getWhite());
        GridPane.setConstraints(whiteCards, 4,3);

        HBox yellowCards = buildQttCards("Amarelas", this.guiPlayers.get(0).getYellow());
        GridPane.setConstraints(yellowCards, 4,5);

        Separator horizontalSeparator1 = new Separator();
        GridPane.setConstraints(horizontalSeparator1, 0,2,5,1);

        Separator horizontalSeparator2 = new Separator();
        GridPane.setConstraints(horizontalSeparator2, 0,4,5,1);

        Separator verticalSeparator1 = new Separator();
        verticalSeparator1.setOrientation(Orientation.VERTICAL);
        GridPane.setConstraints(verticalSeparator1, 1,1,1,5);

        Separator verticalSeparator2 = new Separator();
        verticalSeparator2.setOrientation(Orientation.VERTICAL);
        GridPane.setConstraints(verticalSeparator2, 3,1,1,5);

        playerCardsLayout.getChildren().addAll(header,
                blackCards,
                blueCards,
                greenCards,
                orangeCards,
                pinkCards,
                rainbowCards,
                redCards,
                whiteCards,
                yellowCards,
                verticalSeparator1,
                verticalSeparator2,
                horizontalSeparator1,
                horizontalSeparator2);

        return playerCardsLayout;
    }

    private HBox buildQttCards(String cardColor, int qtt) {
        HBox cardLayout = new HBox();

        Label title = new Label(cardColor);
        Label qttCards = new Label("" + qtt);
        cardLayout.setSpacing(10);
        cardLayout.getChildren().addAll(title, qttCards);

        title.setAlignment(Pos.CENTER_LEFT);
        title.setMinWidth(80);
        qttCards.setMinWidth(10);
        qttCards.setAlignment(Pos.CENTER_RIGHT);

        return cardLayout;
    }

    private VBox buildEndAction() {
        VBox endActionOption = new VBox();

        Insets boxPadding = new Insets(0, 0 ,10 ,0);
        Insets buttonPadding = new Insets(30);

        Button button = new Button("Finalizar A��o");
        button.setOnAction(e -> endActionHandler());
        button.setPadding(buttonPadding);
        button.setAlignment(Pos.CENTER);

        endActionOption.getChildren().addAll(button);
        endActionOption.setAlignment(Pos.CENTER);
        endActionOption.setPadding(boxPadding);
        endActionOption.setMaxHeight(20);

        return endActionOption;
    }

    private void endActionHandler() {
    	actorPlayer.endTurn();
    	actorPlayer.refreshGUI();
    }
    
    private StackPane buildLabelTurn() {
    	StackPane pane = new StackPane();
    	pane.setPadding(new Insets(10, 20, 10, 20));
    	Label label;
    	
    	if (actorPlayer.getPlayerTurn()) {
    		label = new Label("Seu Turno");
    	} else {
    		label = new Label("Turno do Outro");
    	}
    	
    	label.setAlignment(Pos.CENTER);
    	pane.getChildren().add(label);
    	
    	return pane;
    }
    
    private StackPane buildRefreshButton() {
    	StackPane pane = new StackPane();
    	pane.setPadding(new Insets(10, 20, 10, 20));
    	Button button = new Button("Atualiza GUI");
    	
    	button.setOnAction(e -> actorPlayer.refreshGUI());
    	button.setPadding(new Insets(20));
    	pane.getChildren().add(button);
    	
    	return pane;
    }
    
    private void endGame() {
		if (guiPlayers.get(0).getPoints() > guiPlayers.get(1).getPoints()) {
			GUIMessageBox.display("Final do Jogo", "O Vencedor �: " + guiPlayers.get(0).getName());
		} else {
			GUIMessageBox.display("Final do Jogo", "O Vencedor �: " + guiPlayers.get(1).getName());
		}
		actorPlayer.endGame();
    }
}
