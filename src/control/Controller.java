package control;

import java.util.ArrayList;

import br.ufsc.inf.leobr.cliente.Jogada;
import model.*;
import network.*;
import view.*;

public class Controller {
	
	// ATORES
	private ActorPlayer actor;
	private ActorNetGames netGames;

	// CONTROLE DA CONEXÃO
	private boolean connect;
	
	// CONTROLE DE TURNO
	private boolean playerTurn;
		
	// MESA - MAPA + BARALHO + JOGADORES - REGRAS DE JOGO
	private Board board;

	public Controller() {
		this.actor = new ActorPlayer(this);
		this.netGames = new ActorNetGames(this);
	}
	
	//////////////// TESTE /////////////////
	public ActorPlayer getActorPlayer() {
		return actor;
	}
	//--------------------------------------
	
	
	// CASO DE USO CONECTAR
	public boolean connect(String server, String name) {
		this.connect =  netGames.connect(server, name);
		return connect;
	}
	
	// CASO DE USO INICIAR PARTIDA
	public boolean startGame(int quantity) {
		return netGames.startGame(quantity);
	}

	// CASO DE USO RECEBER SOLICITÇÃO DE INICIO
	public void startNewGame(Integer position) {

		// CALCULAR POSIÇÃO DO OPONENTE
		int otherPosition;
		if (position == 1) {
			otherPosition = 2;
		} else {
			otherPosition = 1;
		}
		
		// CRIAR JOGADORES - POSIÇÃO
		Player player = new Player(netGames.getOtherPlayerName(position), position);
		Player otherPlayer = new Player(netGames.getOtherPlayerName(otherPosition), otherPosition);
		
		// VERIFICAR SE É TURNO DO JOGADOR
		if (position == 1) {
			this.playerTurn = true;
		} else {
			this.playerTurn = false;
		}
	
		// CORES DO JOGADORES - POSIÇÃO INICIAL DECIDE AS CORES
		player.setColor(position);
		otherPlayer.setColor(otherPosition);
		
		// CRIAR BOARD + BARALHO + MAPA - O PRIMEIRO JOGADOR CRIA O BARALHO
		this.board = new Board(this.playerTurn);

		// COLOCA JOGADOR NO JOGO
		this.board.setPlayer(player);
		this.board.setOtherPlayer(otherPlayer);
		
		
		// PRIMEIRO A JOGAR
		if (this.playerTurn) {
			// RECEBE MÃO INICIAL
			board.startHand();
			// CASO DE USO ESCOLHER OBJETIVOS
			this.chooseObjectives();
		}
		
		// TESTE
		actor.showMessage(player.getName() + " : " + player.getColor() + " - Esperar jogador " + otherPlayer.getName() + " : " + otherPlayer.getColor() , ActorPlayer.SUCCESSUFUL);
	}

	// CASO DE USO ESCOLHER OBJETIVO -- ESCOLHER NO MINIMO 2, FAZER RESTRIÇÃO NA INTERFACE GRAFICA
	private void chooseObjectives() {
		// COMPRA 3 CARTAS
		ObjectiveCard[] objectives = board.buyObjectivies();

		// INFORMAÇÃO DA CARTA PARA PASSAR PARA INTERFACE
		String[] obj = new String[3];
		obj[0] = objectives[0].toString();
		obj[1] = objectives[1].toString();
		obj[2] = objectives[2].toString();
		
		// CHAMA  INTERFACE GRAFICA, RECEBE OBJETIVOS ESCOLHIDOS
		boolean[] choose = actor.showObjectives(obj, true);
	
		// ADICIONA OS OBJETIVOS ESCOLHIDOS AO JOGADOR
		for (int i = 0; i < choose.length; i++) {
			if (choose[i] == true) {
				this.board.addPlayerObjectives(objectives[i]);
				
			}
		}
		
		// OBJETIVOS NÃO ESCOLHIDOS RETORNAM AO FINAL DA LISTA
		for (int i = 0; i < choose.length; i++) {
			if (choose[i] == true) {
				this.board.addObjectives(objectives[i]);
			}
		}
		

		// ENVIA PREPARAÇÃO PARA OPONENTE
		Action action = new Action(Action.CHOOSE_OBJECTIVE, board.getPlayer().getName());
		action.deck = board.getDeck();
		action.objectives = board.getPlayer().getObjectives();

		// CASO DE USO FINALIZAR TURNO
		this.endTurn(action);
		
	}
	
	// CASO DE USO COMPRAR OBJETIVO
	public void drawObjectives() {
		// COMPRA 3 CARTAS
		ObjectiveCard[] objectives = board.buyObjectivies();

		// INFORMAÇÃO DA CARTA PARA PASSAR PARA INTERFACE
		String[] obj = new String[3];
		obj[0] = objectives[0].toString();
		obj[1] = objectives[1].toString();
		obj[2] = objectives[2].toString();
		
		// CHAMA  INTERFACE GRAFICA, RECEBE OBJETIVOS ESCOLHIDOS
		boolean[] choice = actor.showObjectives(obj, false);
		
		// ADICIONA OS OBJETIVOS ESCOLHIDOS AO JOGADOR
		for (int i = 0; i < choice.length; i++) {
			if (choice[i] == true) {
				this.board.addPlayerObjectives(objectives[i]);
			}
		}
		
		// OBJETIVOS NÃO ESCOLHIDOS RETORNAM AO FINAL DA LISTA
		for (int i = 0; i < choice.length; i++) {
			if (choice[i] == true) {
				this.board.addObjectives(objectives[i]);
			}
		}

		// ENVIAR JOGADA
		Action action = new Action(Action.BUY_OBJECTIVECARD, board.getPlayer().getName());
		action.choice = choice;
		this.endTurn(action);
	}
	
	// CASOU DE USO COMPRAR CARTAS
	public void drawCards() {
		Action action = new Action(Action.BUY_WAGONCARD, this.board.getPlayer().getName());
		
		// ESCOLHER COMPRAR DO DECK OU MESA
		boolean deck = actor.chooseDeckOrBoard();
		if(deck) {
			// COMPRAR 2 CARTAS DO DECK
			this.board.buyWagonCard(true);
			this.board.buyWagonCard(true);
			action.buyDeckCard = 2;
			
		} else {
			// ESCOLHER CARTA DA MESA
			int choice = actor.chooseCardsBoard(this.board.getDeck().getCardsBoard());
			
			// COMPRA CARTA DA MESA
			this.board.drawBoardCard(choice, true);
			action.drawBoardCard = new int[2];
			action.drawBoardCard[0] = choice;
			action.drawBoardCard[1] = -1;
			// CARTA DA MESA É UM CORINGA
			boolean joker = board.getDeck().isJoker(choice);

			if(!joker) {
				// ESCOLHER COMPRAR DO DECK OU MESA
				deck = actor.chooseDeckOrBoard();
				
				if(deck) {
					// COMPRAR 1 CARTA DO DECK
					this.board.buyWagonCard(true);
					action.buyDeckCard = 1;
				} else {
					// ESCOLHER CARTA DA MESA
					choice = actor.chooseCardsBoard(this.board.getDeck().getCardsBoard());
					
					// CARTA DA MESA É UM CORINGA
					joker = board.getDeck().isJoker(choice);

					if(!joker) {
						// COMPRA CARTA DA MESA
						this.board.drawBoardCard(choice, true);						
						action.drawBoardCard[1] = choice;
					}
				}
				
			}
		}
		
		this.endTurn(action);
	}

	// CASO DE USO CONSTRUIR LINHA
	public void buildLine() {
		ArrayList<String> lines = this.board.getLines();
		
		Action action = new Action(Action.BUILD_LINE, this.board.getPlayer().getName());
		
		boolean end = false;
		boolean build = false;
		
		while (end || build) {
			// ESCOLHER LINHA
			String line = actor.chooseLine(lines);

			// PEGA A INFORMAÇÕES DAS CARTAS DOS JOGADORES
			ArrayList<String> cardsInfo = this.board.getPlayerCards();

			// ESCOLHE AS CARTAS
			ArrayList<String> choice = actor.chooseCards(cardsInfo);
			
			// CONSTRUIR LINHA SE POSSIVEL
			// ....... IMPLEMTENTAR ..........................
			build = this.board.buildLine(line, choice, true);
		}
	}
	
	
	// CASO DE USO FINALIZAR ACAO - ULTIMO TURNO NÃO IMPLEMENTADO
	public boolean endTurn(Action action) {
		// ATUALIZAR PROXIMO JOGADOR
		this.playerTurn = false;
		return this.netGames.sendAction(action);
	}

	
	
	
	// CASO DE USO RECEBER JOGADA --- TESTE --- ULTIMO TURNO NÃO IMPLEMENTADO
	public void setPlayed(Action action) {
		switch(action.action) {
		
			case Action.CHOOSE_OBJECTIVE:
				// RECEBER DECK
				this.board.setDeck(action.deck);		
				
				// ADICIONAR OBJETIVOS AO OUTRO JOGADOR
				for (int i = 0; i < action.objectives.size(); i++) {
					this.board.getOtherPlayer().addObjectives(action.objectives.remove(0));
				}
				
				// RECEBER MÃO INICIAL
				this.board.startHand();
				break;
			
			case Action.BUY_OBJECTIVECARD:
				// COMPRA 3 CARTAS
				ObjectiveCard[] objectives = board.buyObjectivies();
				
				// RECEBER ESCOLHAS
				boolean[] choice = action.choice;

				// ADICIONA OS OBJETIVOS ESCOLHIDOS AO OUTRO JOGADOR
				for (int i = 0; i < choice.length; i++) {
					if (choice[i] == true) {
						this.board.addOtherPlayerObjectives(objectives[i]);
					}
				}
				
				// OBJETIVOS NÃO ESCOLHIDOS RETORNAM AO FINAL DA LISTA
				for (int i = 0; i < choice.length; i++) {
					if (choice[i] == true) {
						this.board.addObjectives(objectives[i]);
					}
				}
				break;
			
			case Action.BUY_WAGONCARD:
				// VERIFICA SE COMPROU DUAS CARTAS DO DECK
				if (action.buyDeckCard == 2) {
					this.board.buyWagonCard(false);
					this.board.buyWagonCard(false);
				} else {
					// 	COMPROU 1 CARTA DO DECK
					this.board.drawBoardCard(action.drawBoardCard[0], false);
					// PODE TER COMPRADO 1 CARTA DO DECK
					if (action.buyDeckCard == 1) {
						this.board.buyWagonCard(false);
					} else {
						// PODE TER COMPRADO 1 CARTA DA MESA
						this.board.drawBoardCard(action.drawBoardCard[1], false);
					}
				}
		
				
		
		}
		
		// ATUALIZAR VEZ DO JOGADOR
		this.playerTurn = true;

		// TESTE --- ATUALIZAR TELA 
		actor.updateInterface();
	}

	// CASO DE USO DESCONECTAR
	public void disconnectGUI() {
		netGames.disconnect();
		this.clear();
		this.actor.showMessage("Partida desconectada", ActorPlayer.ERRO);
	}

	public void disconnectNETWORK() {
		this.clear();
		this.actor.showMessage("Partida desconectada", ActorPlayer.ERRO);
	}
	
	public void clear() {
		this.board = null;
		this.playerTurn = false;
	}
	// --------------------------
	
}
