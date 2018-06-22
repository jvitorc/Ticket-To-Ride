package model;

import java.util.ArrayList;

public class Board {

	// INFORMAÇÃO DOS JOGADORES
	private Player player;
	private Player otherPlayer;

	// MAPA
	private Map map;

	// CONTROLADOR DO BARALHO - CARTAS: MESA + BARALHO + OBJETIVOS
	private Deck deck;
	
	// CONTROLE DE ULTIMO TURNO
	private boolean lastTurn;


	public Board(boolean createDeck) {
		map = new Map();
		
		// PRIMEIRO JOGADOR CRIA O DECK
		if (createDeck) {
			deck = new Deck(map.getCities());
		}
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		if (deck == null) {
			this.deck = deck;
		}
	}
	
	public ObjectiveCard[] buyObjectivies() {
		return deck.buyObjectivies();
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getOtherPlayer() {
		return otherPlayer;
	}

	public void setOtherPlayer(Player otherPlayer) {
		this.otherPlayer = otherPlayer;
	}

	// MÃO INICIAL - 4 CARTAS
	public void startHand() {
		player.addCards(deck.drawWagonCard());
		player.addCards(deck.drawWagonCard());
		player.addCards(deck.drawWagonCard());
		player.addCards(deck.drawWagonCard());
	}

	// ADICIOA OBJETIVOS AO JOGADOR
	public void addPlayerObjectives(ObjectiveCard objectiveCard) {
		player.addObjectives(objectiveCard);
	}

	public void addObjectives(ObjectiveCard objectiveCard) {
		this.deck.addObjectives(objectiveCard);
	}

	public void setLastTurn(boolean lastTurn) {
		this.lastTurn = lastTurn;
	}
	
	public boolean getLastTurn() {
		return this.lastTurn;
	}
	
	public ArrayList<String> getLines() {
		return this.map.getLines();
	}

	public void addOtherPlayerObjectives(ObjectiveCard objectiveCard) {
		this.otherPlayer.addObjectives(objectiveCard);
	}

	public void buyWagonCard(boolean player) {
		if(player) {
			this.player.addCards(this.deck.drawWagonCard());
		} else {
			this.otherPlayer.addCards(this.deck.drawWagonCard());
		}
	}

	public void drawBoardCard(int position, boolean player) {
		if (player) {
			this.player.addCards(this.deck.drawCardBoard(position));
		} else {
			this.otherPlayer.addCards(this.deck.drawCardBoard(position));
		}
	}

	public ArrayList<String> getPlayerCards() {
		return this.player.getCards();
	}

	public boolean buildLine(String line, ArrayList<String> cards, boolean player) {
		Player p;
		if (player) {
			p = this.player;
		} else {
			p = this.otherPlayer;
		}
		
		ArrayList<WagonCard> wagonCards = p.getWagonCards(cards);
		
		return false;
	}

}
