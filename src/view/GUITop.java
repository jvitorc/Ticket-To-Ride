package view;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

class GUITop {

    private GUIMain main;
    private BorderPane mainLayout;
    private GUIData data;
    private BorderPane topLayout;
    private Stage window;
    private Stage map;
    private ActorPlayer actorPlayer;

    GUITop(GUIMain main, BorderPane mainLayout, ActorPlayer actorPlayer, Stage window) {
        this.main = main;
        this.mainLayout = mainLayout;
        this.actorPlayer = actorPlayer;
        this.window = window;
        this.topLayout = buildTop();
        GUIConnect.setActorPlayer(actorPlayer);

    }

    BorderPane getTopLayout() {
        return topLayout;
    }

    private BorderPane buildTop() {
        BorderPane topLayout = new BorderPane();

        topLayout.setTop(buildMenuBar());

        return topLayout;
    }

    private MenuBar buildMenuBar() {
        Menu optionsMenu = new Menu("Options");

        MenuItem conectar = new MenuItem("Conecetar...");
        conectar.setOnAction(e -> connect());
        optionsMenu.getItems().add(conectar);

        MenuItem desconectar = new MenuItem("Desconectar...");
        desconectar.setOnAction(e -> disconnect());
        optionsMenu.getItems().add(desconectar);

        MenuItem iniciarPartida = new MenuItem("Iniciar Partida");
        iniciarPartida.setOnAction(e -> initializeGame());
        optionsMenu.getItems().add(iniciarPartida);

        MenuItem mapa = new MenuItem("Mapa");
        mapa.setOnAction(e -> openMap());
        optionsMenu.getItems().add(mapa);

        MenuItem fechar = new MenuItem("Fechar");
        fechar.setOnAction(e -> closeProgram());
        optionsMenu.getItems().add(fechar);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(optionsMenu);

        return menuBar;
    }

    private void connect() {
        GUIConnect.display();
    }

    private void disconnect() {
        if (actorPlayer.isConnect()) {
            actorPlayer.disconnect();
        }
    }

    private void initializeGame() {
    	
    	actorPlayer.startGame();

        if (actorPlayer.getStart()) {
            GUIMessageBox.display("Alerta", "Jogo iniciado");
        } else {
            GUIMessageBox.display("Alerta", "Jogo não iniciado");
        }
    }

    private void openMap() {
        map = new Stage();
        map.setTitle("Mapa");

        Pane pane = new Pane();
        pane.setMinSize(942,592);

        BackgroundImage myBI= new BackgroundImage(new Image("./images/board.png",942,592,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        pane.setBackground(new Background(myBI));

        Scene mapScene = new Scene(pane);
        map.setScene(mapScene);
        map.show();

    }

    public void closeProgram() {
//        Boolean answer = GUIConfirmBox.display("Confirmação", "Tem certeza que deseja sair?");
//        if (answer) {
//            disconnect();
//            window.close();
//            try {
//                map.close();
//            } catch (NullPointerException e) { System.out.println("mapa nao tava aberto"); }
//        }
        window.close();
    }
}
