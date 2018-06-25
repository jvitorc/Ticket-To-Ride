package control;

import java.util.ArrayList;

import br.ufsc.inf.leobr.cliente.Jogada;
import javafx.application.Application;
import javafx.stage.Stage;
import model.*;
import network.*;
import view.*;

public class Controller {
	
	// ATORES
	private ActorPlayer actor;
	private ActorNetGames netGames;
	private boolean start = false;

	private Action action = null;
	private boolean connect;
	private boolean playerTurn;
	private boolean lastTurn;	
	private Board board;
	private boolean chooseObjectives;
	
	public Controller(Stage primaryStage) {
		this.actor = new ActorPlayer(this, primaryStage);
		this.netGames = new ActorNetGames(this);
	}
	
	public ActorPlayer getActorPlayer() {
		return actor;
	}
	
	public boolean getChoosseObjectives() {
		return this.chooseObjectives;
	}
	// CASO DE USO CONECTAR
	public boolean connect(String server, String name) {
		this.connect =  netGames.connect(server, name);
		return connect;
	}
	
	// CASO DE USO INICIAR PARTIDA
	public void startGame(int quantity) {
		start = true;
		netGames.startGame(quantity);
	}
	
	public boolean getStart() {
		return this.start;
	}

	// CASO DE USO RECEBER SOLICIT��O DE INICIO
	public void startNewGame(Integer position) {

		// CALCULAR POSI��O DO OPONENTE
		int otherPosition;
		if (position == 1) {
			otherPosition = 2;
		} else {
			otherPosition = 1;
		}
		
		// CRIAR JOGADORES - POSI��O
		Player player = new Player(netGames.getOtherPlayerName(position), position);
		Player otherPlayer = new Player(netGames.getOtherPlayerName(otherPosition), otherPosition);
		
		// VERIFICAR SE � TURNO DO JOGADOR
		if (position == 1) {
			this.playerTurn = true;
		} else {
			this.playerTurn = false;
		}
	
		// CORES DO JOGADORES - POSI��O INICIAL DECIDE AS CORES
		player.setColor(position);
		otherPlayer.setColor(otherPosition);
		
		// CRIAR BOARD + BARALHO + MAPA - O PRIMEIRO JOGADOR CRIA O BARALHO
		this.board = new Board(true);

		// COLOCA JOGADOR NO JOGO
		this.board.setPlayer(player);
		this.board.setOtherPlayer(otherPlayer);
		
		// PRIMEIRO A JOGAR
		if (this.playerTurn) {
			this.chooseObjectives = true;
		} 
	}

	// CASO DE USO ESCOLHER OBJETIVO -- ESCOLHER NO MINIMO 2, FAZER RESTRI��O NA INTERFACE GRAFICA
	private void chooseObjectives() {
		if (this.chooseObjectives != true) {
			return;
		}
		this.chooseObjectives = false;
		// COMPRA 3 CARTAS
		ObjectiveCard[] objectives = board.buyObjectivies();

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
		
		// OBJETIVOS N�O ESCOLHIDOS RETORNAM AO FINAL DA LISTA
		for (int i = 0; i < choose.length; i++) {
			if (choose[i] == true) {
				this.board.addObjectives(objectives[i]);
			}
		}
		
		this.action = new Action(Action.CHOOSE_OBJECTIVE, board.getPlayer().getName());

		// RECEBE M�O INICIAL
		action.startHand = board.startHand(null);
		
		// ENVIA PREPARA��O PARA OPONENTE
		action.deck = board.getDeck();
		action.objectives = board.getPlayer().getObjectives();
	}
	
	// CASO DE USO COMPRAR OBJETIVO
	public void drawObjectives() {
		if (this.action != null) {
			return;
		}
		// COMPRA 3 CARTAS
		ObjectiveCard[] objectives = board.buyObjectivies();

		// INFORMA��O DA CARTA PARA PASSAR PARA INTERFACE
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
		
		// OBJETIVOS N�O ESCOLHIDOS RETORNAM AO FINAL DA LISTA
		for (int i = 0; i < choice.length; i++) {
			if (choice[i] == true) {
				this.board.addObjectives(objectives[i]);
			}
		}

		this.action = new Action(Action.BUY_OBJECTIVECARD, board.getPlayer().getName());
		action.choice = choice;
	}
	
	// CASOU DE USO COMPRAR CARTAS
	public void drawCards() {
		if (this.action != null) {
			return;
		}

		Action action = new Action(Action.BUY_WAGONCARD, this.board.getPlayer().getName());
		
		if(true) {
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
			// CARTA DA MESA � UM CORINGA
			boolean joker = board.getDeck().isJoker(choice);

			if(!joker) {
				// ESCOLHER COMPRAR DO DECK OU MESA
				boolean deck = actor.chooseDeckOrBoard();
				
				if(deck) {
					// COMPRAR 1 CARTA DO DECK
					this.board.buyWagonCard(true);
					action.buyDeckCard = 1;
				} else {
					// ESCOLHER CARTA DA MESA
					choice = actor.chooseCardsBoard(this.board.getDeck().getCardsBoard());
					
					// CARTA DA MESA � UM CORINGA
					joker = board.getDeck().isJoker(choice);

					if(!joker) {
						// COMPRA CARTA DA MESA
						this.board.drawBoardCard(choice, true);						
						action.drawBoardCard[1] = choice;
					}
				}
				
			}
		}
	}

	// CASO DE USO CONSTRUIR LINHA
	public void buildLine() {
		ArrayList<String> lines = this.board.getLinesInfo();

		// ESCOLHER LINHA
		int line = actor.chooseLine(lines);

		// PEGA A INFORMA��ES DAS CARTAS DOS JOGADORES
		ArrayList<String> cardsInfo = this.board.getPlayerCards();

		// ESCOLHE AS CARTAS
		int choice = actor.chooseColor(cardsInfo);
		
		// CONSTRUIR LINHA SE POSSIVEL
		boolean build = this.board.buildLine(line, choice, true);

		// ENVIAR JOGADA
		Action action = new Action(Action.BUILD_LINE, board.getPlayer().getName());
		if (build) {
			action.line = line;
		}

		// ULTIMO TURNO DO PROXIMO JOGADOR
		if (this.board.getPlayer().twoWagons()) {
			action.lastTurn = true;
		}

	}
	
	
	// CASO DE USO FINALIZAR ACAO - ULTIMO TURNO N�O IMPLEMENTADO
	public boolean endTurn() {

		// ATUALIZAR PROXIMO JOGADOR
		this.playerTurn = false;
		if (action != null) {
			this.action = null;
			return this.netGames.sendAction(this.action);
		} else {
			return false;
		}
	}

	// CASO DE USO RECEBER JOGADA --- TESTE --- ULTIMO TURNO N�O IMPLEMENTADO
	public void setPlayed(Action action) {
		switch(action.action) {
		
			case Action.CHOOSE_OBJECTIVE:
				// RECEBER DECK
				this.board.setDeck(action.deck);		
				
				// ADICIONAR OBJETIVOS AO OUTRO JOGADOR
				for (int i = 0; i < action.objectives.size(); i++) {
					this.board.getOtherPlayer().addObjectives(action.objectives.remove(0));
				}
				
				// RECEBER M�O INICIAL
				this.board.startHand(action.startHand);
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
				
				// OBJETIVOS N�O ESCOLHIDOS RETORNAM AO FINAL DA LISTA
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
		
			case Action.BUILD_LINE:
				// CONSTROI LINHA
				if (action.line >= 0) {
					this.board.buildLine(action.line, action.color, false);	
				}
		}
		
		if (action.lastTurn == true) {
			if (this.lastTurn == true) {
				// ACABOU O JOGO
				this.endGame();
			} else {
				// OUTRO JOGADOR TEM MAIS UM TURNO
				this.lastTurn = true;
			}
		}
		
		
		// ATUALIZAR VEZ DO JOGADOR
		this.playerTurn = true;

		// TESTE --- ATUALIZAR TELA 
		actor.updateInterface();
	}

	private void endGame() {
		// CALCULAR PONTUA��O DE CADA JOGADDOR
		
		// MOSTRAR PONTUA��O
		
		// RESTART
		this.clear();
		
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

	public boolean isConnect() {
		return connect;
	}

	public ArrayList<String> getPlayerObjectives() {
		ArrayList<ObjectiveCard> obj = this.board.getPlayer().getObjectives();
		ArrayList<String> list = new ArrayList<String>();
		for (ObjectiveCard card: obj) {
			list.add(card.toString());
		}
		return list;
	}

}
