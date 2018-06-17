package view;

import control.Controller;
import network.Action;

public class ActorPlayer {
	// VERSÃO DOIS JOGADORES
	private static final int QUANTIDADE_JOGADORES = 2;
	
	
	// TIPOS DE MENSAGEM
	public static final int SUCCESSUFUL = 0;
	public static final int ERRO = 1;
	
	// Controle - Adicionar Interface Grafica
	private Controller controller;

	public ActorPlayer(Controller controller){
		this.controller = controller;
		// Instanciar interface grafica
	}
	
		
	// CASO DE USO CONECTAR - INICIADO PELA INTERFACE GRAFICA - RETORNA BOOLEAN POIS PODE SER PRECISO DESATIVAR BOTÕES ETC...
	public boolean connect(String server, String name) {
		boolean connect = controller.connect(server, name);
		
		if (connect) {
			this.showMessage("Conectou", ActorPlayer.SUCCESSUFUL);
		} else {
			this.showMessage("Não conectou", ActorPlayer.ERRO);
		}
		
		return connect;
	}
	
	// CASO DE USO INICIAR PARTIDA - INICIADO PELA INTERFACE GRAFICA - RETORNA BOOLEAN POIS PODE SER PRECISO DESATIVAR BOTÕES ETC...
	public boolean startGame() {
		 return controller.startGame(ActorPlayer.QUANTIDADE_JOGADORES);
	}
	
	// CASO DE USO FINALIZAR AÇÃO - INICIADO PELA INTERFACE GRAFICA - RETORNA BOOLEAN POIS PODE SER PRECISO DESATIVAR BOTÕES ETC...
	public boolean endTurn(Action action) {
		boolean sucessuful = controller.endTurn(action);
		
		if (sucessuful) {
			this.showMessage("Jogada enviada", ActorPlayer.SUCCESSUFUL);
		} else {
			this.showMessage("Jogada não enviada", ActorPlayer.ERRO);
		}
		
		return sucessuful;
	}
	
	// MENSAGENS PARA NOTIFICAR USUARIO DE SUAS AÇÕES POR CAIXAS DE DIALOGO --- VERSAO TESTE
	public void showMessage(String message, int type) {
		if(ActorPlayer.SUCCESSUFUL == type)
			System.out.println(message);
		else {
			System.err.println(message);
		}
	}
	


	// CASO DE USO RECEBER JOGADA
	public void updateInterface(Action action) {
		// TODO Auto-generated method stub
	}

	
	// CASO DE USO DESCONECTAR --- NÃO FINALIZADO
	public void disconnect() {
		controller.disconnectGUI();
		this.showMessage("DESCONECTOU", ActorPlayer.SUCCESSUFUL);
	}


	public boolean[] showObjectives(String[] objetives) {
		/*
		 * Chamar Interface grafica
		 * retorna true para as posiçoes a qual o jogar escolheu o objetivo
		 * 
		 */

		boolean[] b = {false, true, false};
		return b; 
	}
}
