package view;

import java.util.ArrayList;

import control.Controller;
import network.Action;
/*
 * OBS: TIRAR BOTÃO FIM DO TURNO
 */
public class ActorPlayer {
	// QUANTIDADE DE JOGADORES
	private static final int QUANTIDADE_JOGADORES = 2;
	
	
	// TIPOS DE MENSAGEM
	public static final int SUCCESSUFUL = 0;
	public static final int ERRO = 1;
	
	// CONTROLADOR
	private Controller controller;

	// INTERFACE GRAFICA
	private Windows windows;
	
	public ActorPlayer(Controller controller){
		this.controller = controller;
		this.windows = new Windows();
	}
	
	
	// CASOS DE USO INICIADOS PELA INTERFACE GRAFICA ------------------------------------
	
	// CASO DE USO CONECTAR 
	public boolean connect(String server, String name) {
		boolean connect = controller.connect(server, name);
		
		if (connect) {
			this.showMessage("Conectou", ActorPlayer.SUCCESSUFUL);
		} else {
			this.showMessage("Não conectou", ActorPlayer.ERRO);
		}
		
		return connect;
	}
	
	// CASO DE USO INICIAR PARTIDA
	public boolean startGame() {
		 return controller.startGame(ActorPlayer.QUANTIDADE_JOGADORES);
	}
	
	// CASO DE USO COMPRAR OBJETIVOS
	public void drawObjetives() {
		this.controller.drawObjectives();
	}
	
	// CASO DE USO COMPRAR OBJETIVOS - ESCOLHER OBJETIVOS - POSIÇÕES ESCOLHIDA COM VERDADEIRA
	// ESCOLHER OBJETIVO RESTRIÇÃO ESCOLHER NO MINIMO 2 OBJETIVOS (TRUE)
	public boolean[] showObjectives(String[] obj, boolean restriction) {
		return null;
	}
	
	// CASO DE USO COMPRAR CARTAS
	public void drawCards() {
		this.controller.drawCards();
	}
	
	// CASO DE USO COMPRAR CARTA - ESCOLHER DECK(TRUE) OU MESA 
	public boolean chooseDeckOrBoard() {
		
		return false;
	}
	
	// CASO DE USO COMPRAR CARTA - 1 CARTA DA MESA - RETORNAR POSIÇÃO ESCOLHIDA 
	public int chooseCardsBoard(String[] cardsBoard) {
		return 0;
	}
	

	// CASO DE USO CONSTRUIR LINHA --- INCOMPLETO
	public void buildLine() {
		this.controller.buildLine();
	}
	
	// CASO DE USO CONTRUIR LINHA -- ....
	public String chooseLine(ArrayList<String> linesId) {
		// IMPLEMENTAR
		return null;
	}
	
	// CASO DE USO CONSTRUIR LINHA -- ....
	public ArrayList<String> chooseCards(ArrayList<String> cardsInfo) {
		// IMPLEMENTAR
		return null;
	}

	
	// CASO DE USO DESCONECTAR
	public void disconnect() {
		controller.disconnectGUI();
		this.showMessage("DESCONECTOU", ActorPlayer.SUCCESSUFUL);
	}
	
	// ---------------------------------------------------------------------------------------
	
	// MENSAGENS PARA NOTIFICAR USUARIO DE SUAS AÇÕES POR CAIXAS DE DIALOGO --- TESTAR CODIGO
	public void showMessage(String message, int type) {
		if(ActorPlayer.SUCCESSUFUL == type)
			System.out.println(message);
		else {
			System.err.println(message);
		}
	}
	
	// CASO DE USO RECEBER JOGADA - ATUALIZAR INTERFACE GRAFICA COM A JOGADA RECEBIDA
	public void updateInterface() {
		// IMPLEMENTAR
	}

}
