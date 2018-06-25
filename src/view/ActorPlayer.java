package view;

import java.util.ArrayList;
import java.util.Scanner;

import control.Controller;
import javafx.stage.Stage;
import network.Action;

public class ActorPlayer {
	private static final int QUANTIDADE_JOGADORES = 2;
	public static final int SUCCESSUFUL = 0;
	public static final int ERRO = 1;

	private Controller controller;
	private GUIMain window;
	
	public ActorPlayer(Controller controller, Stage primaryStage) {
		this.controller = controller;
		this.window = new GUIMain(this, primaryStage);
	}

	// CASO DE USO CONECTAR 
	public boolean connect(String server, String name) {
		boolean connect = controller.connect(server, name);
		
		if (connect) {
			this.showMessage("Conectou", ActorPlayer.SUCCESSUFUL);
		} else {
			this.showMessage("N�o conectou", ActorPlayer.ERRO);
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
	public void drawCards() {
		this.controller.drawCards();
	}
	
	// CASO DE USO COMPRAR CARTA - ESCOLHER DECK(TRUE) OU MESA 
	public boolean chooseDeckOrBoard() {
		
		return false;
	}
	
	// CASO DE USO COMPRAR CARTA - 1 CARTA DA MESA - RETORNAR POSI��O ESCOLHIDA 
	public int chooseCardsBoard(String[] cardsBoard) {
		return 0;
	}
	

	// CASO DE USO CONSTRUIR LINHA --- INCOMPLETO
	public void buildLine() {
		this.controller.buildLine();
	}
	
	// CASO DE USO CONTRUIR LINHA -- ....
	public int chooseLine(ArrayList<String> linesId) {
		// IMPLEMENTAR
		return -1;
	}
	
	// CASO DE USO CONSTRUIR LINHA -- ....
	public int chooseColor(ArrayList<String> cardsInfo) {
		// IMPLEMENTAR
		return 0;
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
		// Teste
		System.out.println("FIM...");
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
}
