package control;

import model.*;
import network.*;
import view.*;

/// VERSAO APENAS 2 JOGADORES
public class Controller {
	
	// ATORES
	private ActorPlayer actor;
	private ActorNetGames netGames;

	// CONTROLE DA CONEXÃO
	private boolean connect;
	
	// INFORMAÇÃO DOS JOGADORES - 2 jogadores
	private Player player;
	private Player otherPlayer;

	// CONTROLE DE TURNO
	private boolean playerTurn;
	
	// MESA - MAPA + BARALHO
	private Board board;

	
	
	public Controller() {
		this.actor = new ActorPlayer(this);
		this.netGames = new ActorNetGames(this);
		this.connect = false;
	}
	
	////////// TESTE ///////////
	public ActorPlayer getActorPlayer() {
		return actor;
	}
	
	// CASO DE USO CONECTAR
	public boolean connect(String server, String name) {
		boolean connect =  netGames.connect(server, name);
		
		// CRIAR JOGADOR 
		if (connect) {
			player = new Player(name);
		} 
		
		return connect;
	}
	
	// CASO DE USO INICIAR PARTIDA
	public boolean startGame(int quantity) {
		return netGames.inicializeNewGame(quantity);
	}

	// CASO DE USO RECEBER SOLICITÇÃO DE INICIO -- INCOMPLETO --- TESTE
	public void startNewGame(Integer posicao) {
		// Criar oponente versão 2 jogadores
		String other;
		if (posicao == 1) {
			other = netGames.otherPlayer(2);
			this.playerTurn = true;
		} else {
			other = netGames.otherPlayer(1);
			this.playerTurn = false;
		}
		this.otherPlayer = new Player(other);
		
		
		// Só o primeiro jogador cria o baralho para sincronização
		this.board = new Board(this.playerTurn);
		
		// Primeira rodada é escolher objetivo e sincronizar baralho
		if (this.playerTurn) {
			// CASO DE USO ESCOLHER OBJETIVOS
			this.chooseObjectives();
		}
		
		// TESTE
		actor.showMessage("Esperar outro jogador", ActorPlayer.SUCCESSUFUL);
	}

	// CASO DE USO ESCOLHER OBJETIVO
	private void chooseObjectives() {
		ObjectiveCard[] objectives = board.buyObjectivies();
		String[] obj = new String[3];
		obj[0] = objectives[0].toString();
		obj[1] = objectives[1].toString();
		obj[2] = objectives[2].toString();
		
		boolean[] choose = actor.showObjectives(obj);
		
		for (int i = 0; i < 3; i++) {
			if (choose[i] == true) {
				player.addObjectives(objectives[i]);
			}
		}
		
		Action action = new Action();
		action.action = Action.CHOOSE_OBJECTIVES;
		action.deck = this.board.getDeck();
		
		this.endTurn(action);
	}

	// CASO DE USO FINALIZAR ACAO
	public boolean endTurn(Action action) {
		// TODO Auto-generated method stub
		return this.netGames.sendAction(action);
	}

	// CASO DE USO RECEBER JOGADA --- TESTE
	public void getPlayed(Action action) {
		// TESTE --- ATUALIZAR TELA 
		actor.updateInterface(action);
	}

	// CASO DE USO DESCONECTAR
	public void disconnectGUI() {
		netGames.disconnect();
	}

	public void disconnectNETWORK() {
		/// DESCONEXÃO POR ERRO - A IMPLEMENTAR
		
	}

	
}
