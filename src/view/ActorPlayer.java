package view;

import java.util.ArrayList;

import control.Controller;
import javafx.stage.Stage;
import model.Board;
import model.Line;
import model.Player;
import model.WagonCard;

public class ActorPlayer {
	private static final int QUANTIDADE_JOGADORES = 2;
	public static final int SUCCESSUFUL = 0;
	public static final int ERRO = 1;

	private Controller controller;
	private GUIMain window;
	private Stage primaryStage;
	
	public ActorPlayer(Controller controller, Stage primaryStage) {
		this.controller = controller;
		this.primaryStage = primaryStage;
		this.window = new GUIMain(this, primaryStage);
	}

	// CASO DE USO CONECTAR 
	public boolean connect(String server, String name) {
		boolean connect = controller.connect(server, name);
		
		if (connect) {
			this.showMessage("Conectou", ActorPlayer.SUCCESSUFUL);
		} else {
			this.showMessage("Nao conectou", ActorPlayer.ERRO);
		}
		
		return connect;
	}
	
	// CASO DE USO INICIAR PARTIDA
	public void startGame() {
		controller.startGame(ActorPlayer.QUANTIDADE_JOGADORES);
	}
	
	public boolean getStart() {
		return controller.getStart();
	}
	
	// CASO DE USO COMPRAR OBJETIVOS
	public void drawObjetives() {
		this.controller.drawObjectives();
	}
	
	// CASO DE USO COMPRAR OBJETIVOS - ESCOLHER OBJETIVOS - POSI��ES ESCOLHIDA COM VERDADEIRA
	// ESCOLHER OBJETIVO RESTRI��O ESCOLHER NO MINIMO 2 OBJETIVOS (TRUE)
	public boolean[] showObjectives(String[] obj, boolean restriction) {
		GUIChooseObjectiveBox.display(obj);
		boolean[] choice = GUIChooseObjectiveBox.getChoice();
		window.buildLeft(window.getMainLayout());
		return choice;
	}
	
	// CASO DE USO COMPRAR CARTAS
	public void drawCards(boolean deck, int position) {
		this.controller.drawCards(deck, position);
	}

	// CASO DE USO CONSTRUIR LINHA
	public void buildLine(int line, int color) {
		this.controller.buildLine(line, color);
	}

	
	// CASO DE USO DESCONECTAR
	public void disconnect() {
		controller.disconnectGUI();
		this.showMessage("DESCONECTOU", ActorPlayer.SUCCESSUFUL);
	}

	public boolean isConnect() {
		return controller.isConnect();
	}
	
	// ---------------------------------------------------------------------------------------
	
	// MENSAGENS PARA NOTIFICAR USUARIO DE SUAS A��ES POR CAIXAS DE DIALOGO --- TESTAR CODIGO
	public void showMessage(String message, int type) {
		GUIMessageBox.display("Alerta", message);
	}
	
	// CASO DE USO RECEBER JOGADA - ATUALIZAR INTERFACE GRAFICA COM A JOGADA RECEBIDA
	public void updateInterface() {
	}

	public ArrayList<String> getPlayerObjectives() {
		return controller.getPlayerObjectives();
	}

	public String[] getObjectives() {
		// TODO Auto-generated method stub
		return null;
	}

	public void buyObjective() {
		// TODO Auto-generated method stub
		
	}

	public Board getBoard() {
		return controller.getBoard();
	}
	
	public Player getPlayer() {
		return controller.getPlayer();
	}
	
	public ArrayList<Line> getDummyArray() {
		return controller.getDummyArray();
	}
	
	public boolean getChooseObjectives() {
		return controller.getChooseObjectives();
	}
	
	public void chooseObjectives() {
		controller.chooseObjectives();
	}

	public ArrayList<GUIPlayer> getGUIPlayers(ArrayList<Player> playersToConvert) {
    	ArrayList<Player> players = playersToConvert;
    	ArrayList<GUIPlayer> guiPlayers = new ArrayList<>();
    	
    	for (Player player : players) {
    		guiPlayers.add(setGUIPlayer(player));
    	}
    	return guiPlayers;
    }
	
	public GUIPlayer setGUIPlayer(Player player) {
		GUIPlayer guiPlayer = new GUIPlayer();
		guiPlayer.setName(player.getName());
		guiPlayer.setColor(player.getColor());
		guiPlayer.setQttWagons(player.getWagons());
		guiPlayer.setQttCards(player.getCards().size());
		guiPlayer.setPoints(player.getPoints());
		for (WagonCard card : player.getCards()) {
			guiPlayer = addCardsGUIPlayer(guiPlayer, card);
		}
		
		return guiPlayer;
	}
	
	public GUIPlayer addCardsGUIPlayer(GUIPlayer guiPlayer, WagonCard card) {
		switch (card.getColor()) {
			case (0):
				guiPlayer.incBlack();
	            break;
	        case (1):
	        	guiPlayer.incBlue();
	            break;
	        case (2):
	        	guiPlayer.incGreen();
	            break;
	        case (3):
	        	guiPlayer.incOrange();
	            break;
	        case (4):
	        	guiPlayer.incPurple();
	            break;
	        case (5):
	        	guiPlayer.incMulticolor();
	            break;
	        case (6):
	        	guiPlayer.incRed();
	            break;
	        case (7):
	        	guiPlayer.incWhite();
	            break;
	        default:
	        	guiPlayer.incYellow();
	            break;
		}
		return guiPlayer;
	}
	
	public ArrayList<Player> getPlayers() {
		return controller.getPlayers();
	}
	
	public ArrayList<GUIPlayer> getGUIPlayers() {
		return getGUIPlayers(controller.getPlayers());
	}
	
	public ArrayList<GUIPlayer> getDummyGUIPlayers() {
		ArrayList<GUIPlayer> players = new ArrayList<>();
		players.add(new GUIPlayer("", "", 0,0,0));
		players.add(new GUIPlayer("", "", 0,0,0));
		return players;
	}
	
	public boolean getPlayerTurn() {
		return controller.getPlayerTurn();
	}
	
	public void endTurn() {
		controller.endTurn();
	}
	
	public boolean getMapReady() {
		return controller.getMapReady();
	}
	
	public void refreshGUI() {
		this.window = new GUIMain(this, primaryStage);
	}
	
	public String getColorName(int color) {
		return controller.getColorName(color);
	}
	
	public void endGame() {
		controller.endGame();
	}
}
