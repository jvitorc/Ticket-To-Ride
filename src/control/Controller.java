package control;

import java.util.ArrayList;

import br.ufsc.inf.leobr.cliente.Jogada;
import javafx.application.Application;
import javafx.stage.Stage;
import model.*;
import network.*;
import view.*;

public class Controller {
	
	private ActorPlayer actor;
	private ActorNetGames netGames;
	private boolean connect;
	private boolean start = false;

	private int status = -100;
	private Action action = null;
	private boolean playerTurn;
	private boolean lastTurn;	
	private Board board;
	private int drawCards = 0;

	public Controller(Stage primaryStage) {
		this.actor = new ActorPlayer(this, primaryStage);
		this.netGames = new ActorNetGames(this);
	}
	
	public ActorPlayer getActorPlayer() {
		return actor;
	}
	
	public boolean getChooseObjectives() {
		return (this.status == 0);
	}
	// CASO DE USO CONECTAR
	public boolean connect(String server, String name) {
		this.connect =  netGames.connect(server, name);
		return connect;
	}
	
	// CASO DE USO INICIAR PARTIDA
	public void startGame(int quantity) {
		start = netGames.startGame(quantity);
		actor.showMessage("Conexao: "+ start, 0);
	}
	
	public boolean getStart() {
		return this.start;
	}

	// CASO DE USO RECEBER SOLICITACAO DE INICIO
	public void startNewGame(Integer position) {

		int otherPosition;
		if (position == 1) {
			otherPosition = 2;
		} else {
			otherPosition = 1;
		}
		
		Player player = new Player(netGames.getOtherPlayerName(position), position);
		Player otherPlayer = new Player(netGames.getOtherPlayerName(otherPosition), otherPosition);
		
		if (position == 1) {
			this.playerTurn = true;
		} else {
			this.playerTurn = false;
		}
	
		player.setColor(position);
		otherPlayer.setColor(otherPosition);
		
		// CRIAR BOARD + BARALHO + MAPA - O PRIMEIRO JOGADOR CRIA O BARALHO
		this.board = new Board(true);

		// COLOCA JOGADOR NO JOGO
		this.board.setPlayer(player);
		this.board.setOtherPlayer(otherPlayer);
		
		if (this.playerTurn) {
			this.status = Action.CHOOSE_OBJECTIVE;
		} else {
			this.status = -100;
		}
	}

	// CASO DE USO ESCOLHER OBJETIVO -- ESCOLHER NO MINIMO 2, FAZER RESTRI��O NA INTERFACE GRAFICA
	public void chooseObjectives() {
		if (this.status == Action.CHOOSE_OBJECTIVE) {			
			this.status = 10;

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
				if (choose[i]) {
					this.board.addPlayerObjectives(objectives[i]);
				}
			}
			
			// OBJETIVOS NAO ESCOLHIDOS RETORNAM AO FINAL DA LISTA
			for (int i = 0; i < choose.length; i++) {
				if (!choose[i]) {
					this.board.addObjectives(objectives[i]);
				}
			}
			
			this.action = new Action(Action.CHOOSE_OBJECTIVE, board.getPlayer().getName());
	
			// RECEBE MAO INICIAL
			action.startHand = board.startHand(null);
			
			// ENVIA PREPARACAOO PARA OPONENTE
			action.deck = board.getDeck();
			action.objectives = board.getPlayer().getObjectives();
			this.endTurn();
		}
	}
	
	// CASO DE USO COMPRAR OBJETIVO
	public void drawObjectives() {
		if (this.status == -1 || this.status == 20) {
			this.status = -100;
			// COMPRA 3 CARTAS
			ObjectiveCard[] objectives = board.buyObjectivies();
	
			// INFORMACAO DA CARTA PARA PASSAR PARA INTERFACE
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
			
			// OBJETIVOS NAO ESCOLHIDOS RETORNAM AO FINAL DA LISTA
			for (int i = 0; i < choice.length; i++) {
				if (choice[i] == false) {
					this.board.addObjectives(objectives[i]);
				}
			}
	
			this.action = new Action(Action.BUY_OBJECTIVECARD, board.getPlayer().getName());
			action.choice = choice;
			this.endTurn();
		}
	}
	
	// CASOU DE USO COMPRAR CARTAS
	public void drawCards(boolean deck, int choice) {
		if (status == -1) {
			this.action = new Action(Action.BUY_WAGONCARD, this.board.getPlayer().getName());				
			this.drawCards = 0;
			this.status = 30;
			this.drawCardBoard(deck, choice);
		} else if (status == 30) {
			this.status = 10;
			this.drawCardBoard(deck, choice);
		}
	}
	
	private void drawCardBoard(boolean deck, int choice) {
		if(deck) {
			this.board.buyWagonCard(true);
			this.action.buyDeckCard +=1;
			
		} else {
			this.board.drawBoardCard(choice, true);
			action.drawBoardCard[this.drawCards] = choice;
			
			boolean joker = board.getDeck().isJoker(choice);
			if(joker) {
				this.status = 10;
			}
		}
		
	}

	// CASO DE USO CONSTRUIR LINHA
	public void buildLine(int line, int color) {
		if (status == -1) {
			// CONSTRUIR LINHA SE POSSIVEL
			boolean build = this.board.buildLine(line, color, true);
	
			// ENVIAR JOGADA
			this.action = new Action(Action.BUILD_LINE, board.getPlayer().getName());
			if (build) {
				action.line = line;
			}
	
			// ULTIMO TURNO DO PROXIMO JOGADOR
			if (this.board.getPlayer().twoWagons()) {
				action.lastTurn = true;
			}
			this.status = -100;
			this.endTurn();
		}
	}
	
	
	// CASO DE USO FINALIZAR ACAO - ULTIMO TURNO N�O IMPLEMENTADO
	public void endTurn() {
		if (status == 10) {
			this.playerTurn = false;
			this.netGames.sendAction(this.action);
			actor.showMessage("Turno do oponente", ActorPlayer.SUCCESSUFUL);
		} 
	}

	// CASO DE USO RECEBER JOGADA --- TESTE --- ULTIMO TURNO N�O IMPLEMENTADO
	public void setPlayed(Action action) {
		switch(action.action) {
		
			case Action.CHOOSE_OBJECTIVE:
				// RECEBER DECK
				this.board.setDeck(action.deck);		
				
				// ADICIONAR OBJETIVOS AO OUTRO JOGADOR
				for (ObjectiveCard ob: action.objectives) {
					this.board.getOtherPlayer().addObjectives(ob);
				}
				
				// RECEBER MAO INICIAL
				this.board.startHand(action.startHand);
				this.status = 20;
				System.out.println("sdasda");
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
				
				// OBJETIVOS NAO ESCOLHIDOS RETORNAM AO FINAL DA LISTA
				for (int i = 0; i < choice.length; i++) {
					if (choice[i] == false) {
						this.board.addObjectives(objectives[i]);
					}
				}
				this.status = -1;
				break;
			
			case Action.BUY_WAGONCARD:
				for (int i: action.drawBoardCard) {
					if (i == -1) {
						if (action.buyDeckCard > 0) {
							this.board.buyWagonCard(false);
							action.buyDeckCard--;
						}
					} else {						
						this.board.drawBoardCard(action.drawBoardCard[0], false);
					}	
				}
				this.status = -1;
			case Action.BUILD_LINE:
				if (action.line >= 0) {
					this.board.buildLine(action.line, action.color, false);	
				}
				this.status = -1;
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

	public Board getBoard() {
		return board;
	}
	
	public Player getPlayer() {
		return board.getPlayer();
	}
	
	public ArrayList<Line> getDummyArray() {
		return board.getDummyArray();
	}
	
	public boolean getPlayerTurn() {
		return playerTurn;
	}

}
