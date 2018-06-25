package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GUIBottom {

    private GUIMain main;
    private BorderPane mainLayout;
    private GUIData data;
    private HBox bottomLayout;

    GUIBottom(GUIMain main, BorderPane mainLayout, GUIData data) {
        this.main = main;
        this.mainLayout = mainLayout;
        this.data = data;
        this.bottomLayout = buildBottom();
    }

    public HBox getBottom() {
        return this.bottomLayout;
    }

    private HBox buildBottom() {
        this.bottomLayout = new HBox();

        VBox table = new VBox();
        table.getChildren().addAll(getPlayerTable());
        table.setMaxHeight(150);
        table.setMaxWidth(498);
        table.setAlignment(Pos.CENTER_LEFT);

        GridPane playerCards = buildPlayerCards();
        VBox endAction = buildEndAction();

        this.bottomLayout.setAlignment(Pos.CENTER);
        this.bottomLayout.getChildren().addAll(table, playerCards, endAction);

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

        // pegar jogadores de algum lugar e montar tabela

        players.addAll(this.data.getPlayers());

        return players;
    }

    private GridPane buildPlayerCards() {
        GridPane playerCardsLayout = new GridPane();
        playerCardsLayout.setPadding(new Insets(10, 20, 10, 20));
        playerCardsLayout.setVgap(8);
        playerCardsLayout.setHgap(10);

        Label header = new Label("Cartas do Jogador");
        GridPane.setConstraints(header,1,0, 5, 1);

        HBox blackCards = buildQttCards("Pretas", data.getPlayers().get(0).getBlack());
        GridPane.setConstraints(blackCards, 0,1);

        HBox blueCards = buildQttCards("Azuis", data.getPlayers().get(0).getBlue());
        GridPane.setConstraints(blueCards, 0,3);

        HBox greenCards = buildQttCards("Verdes", data.getPlayers().get(0).getGreen());
        GridPane.setConstraints(greenCards, 0,5);

        HBox orangeCards = buildQttCards("Laranjas", data.getPlayers().get(0).getOrange());
        GridPane.setConstraints(orangeCards, 2,1);

        HBox pinkCards = buildQttCards("Rosas", data.getPlayers().get(0).getPink());
        GridPane.setConstraints(pinkCards, 2,3);

        HBox rainbowCards = buildQttCards("Arco-Iris", data.getPlayers().get(0).getRainbow());
        GridPane.setConstraints(rainbowCards, 2,5);

        HBox redCards = buildQttCards("Vermelhas", data.getPlayers().get(0).getRed());
        GridPane.setConstraints(redCards, 4,1);

        HBox whiteCards = buildQttCards("Brancas", data.getPlayers().get(0).getWhite());
        GridPane.setConstraints(whiteCards, 4,3);

        HBox yellowCards = buildQttCards("Amarelas", data.getPlayers().get(0).getYellow());
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

        Button button = new Button("Finalizar Ação");
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

    }
}
