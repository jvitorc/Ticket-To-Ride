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
	private boolean endGame;

	public Controller(Stage primaryStage) {
		this.actor = new ActorPlayer(this, primaryStage);
		this.netGames = new ActorNetGames(this);
	}
	
	public ActorPlayer getActorPlayer() {
		return actor;
	}
	
	public boolean getEndGame() {
		return this.endGame;
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
		this.board = new Board(this.playerTurn);

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

			this.action = new Action(Action.CHOOSE_OBJECTIVE, board.getPlayer().getName());
			
			// RECEBE MAO INICIAL
			action.startHand = board.startHand(null);
			
			// ENVIA PREPARACAOO PARA OPONENTE
			action.deck = board.getDeck();
			
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
			
			action.choice = choose;
			this.endTurn();
		}
	}
	
	// CASO DE USO COMPRAR OBJETIVO
	public void drawObjectives() {
		if (this.playerTurn) {
			if ((this.status == -1) || (this.status == 20)) {
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
				this.status = 10;
				this.endTurn();
			}
		}
	}
	
	// CASOU DE USO COMPRAR CARTAS
	public void drawCards(boolean deck, int choice) {
		if (status == -1) {
			this.action = new Action(Action.BUY_WAGONCARD, this.board.getPlayer().getName());				
			this.status = 30;
			this.drawCardBoard(deck, choice, 0);
		} else if (status == 30) {
			this.status = 10;
			this.drawCardBoard(deck, choice, 1);
		}
	}
	
	private void drawCardBoard(boolean deck, int choice, int position) {
		if(deck) {
			this.board.buyWagonCard(true);
			this.action.buyDeckCard +=1;
			
		} else {
			this.board.drawBoardCard(choice, true);
			action.drawBoardCard[position] = choice;
		}
		
	}

	// CASO DE USO CONSTRUIR LINHA
	public void buildLine(int line, int color) {
		if (status == -1) {
			// CONSTRUIR LINHA SE POSSIVEL
			boolean build = this.board.buildLine(line, color, true);

			// ENVIAR JOGADA
			if (build) {
				this.action = new Action(Action.BUILD_LINE, board.getPlayer().getName());
				action.line = line;
				action.color = color;
				// ULTIMO TURNO DO PROXIMO JOGADOR - NÃO IMPLEMENTADO

				this.status = 10;
				this.endTurn();
			}
	
		}
	}
	
	
	// CASO DE USO FINALIZAR ACAO - ULTIMO TURNO N�O IMPLEMENTADO
	public void endTurn() {
		if (status == 10) {
			this.playerTurn = false;
			this.netGames.sendAction(this.action);
			this.status = -100;
		} 
	}

	// CASO DE USO RECEBER JOGADA --- TESTE --- ULTIMO TURNO N�O IMPLEMENTADO
	public void setPlayed(Action action) {
		
		switch(action.action) {
			case Action.CHOOSE_OBJECTIVE:
				// RECEBER MAO INICIAL

				this.board.startHand(action.startHand);
				// RECEBER DECK
				this.board.setDeck(action.deck);		
				
				// ADICIONAR OBJETIVOS AO OUTRO JOGADOR
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
				
				this.status = 20;
				
				break;
			
			case Action.BUY_OBJECTIVECARD:
				// COMPRA 3 CARTAS
				ObjectiveCard[] objectives1 = board.buyObjectivies();
				
				// RECEBER ESCOLHAS
				boolean[] choice1 = action.choice;

				// ADICIONA OS OBJETIVOS ESCOLHIDOS AO OUTRO JOGADOR
				for (int i = 0; i < choice1.length; i++) {
					if (choice1[i] == true) {
						this.board.addOtherPlayerObjectives(objectives1[i]);
					}
				}
				
				// OBJETIVOS NAO ESCOLHIDOS RETORNAM AO FINAL DA LISTA
				for (int i = 0; i < choice1.length; i++) {
					if (choice1[i] == false) {
						this.board.addObjectives(objectives1[i]);
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
						this.board.drawBoardCard(i, false);
					}	
				}
				this.status = -1;
			case Action.BUILD_LINE:
				if (action.line >= 0) {
					this.board.buildLine(action.line, action.color, false);	
				}
				this.status = -1;
		}

		// IMPLEMENTAR ULTIMO TURNO
				
		// ATUALIZAR VEZ DO JOGADOR
		this.playerTurn = true;
		
		this.actor.refreshGUI();

	}

	private void endGame() {
		// ENVIAR FIM JOGO

		// CALCULAR PONTUA��O DE CADA JOGADDOR
		
		// MOSTRAR PONTUA��O
		
		// RESTART MESA
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
		this.status = -100;
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
	
	public ArrayList<Player> getPlayers() {
		return board.getPlayers();
	}
	
	public ArrayList<Player> getDummyPlayers() {
		return board.getDummyPlayers();
	}
	
	public ArrayList<Line> getDummyArray() {
		return board.getDummyArray();
	}
	
	public boolean getPlayerTurn() {
		return playerTurn;
	}
	
	public boolean getMapReady() {
		return board.getMapReady();
	}
	
	public String getColorName(int color) {
		WagonCard cardName = new WagonCard(color);
		return cardName.getColorName();
	}

}
