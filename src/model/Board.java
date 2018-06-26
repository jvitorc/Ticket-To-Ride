package model;

import java.util.ArrayList;
import java.util.List;

public class Board {

	// INFORMAÇÃO DOS JOGADORES
	private static Player player;
	private static Player otherPlayer;

	// MAPA
	private Map map;

	// CONTROLADOR DO BARALHO - CARTAS: MESA + BARALHO + OBJETIVOS
	private Deck deck;
	
	// CONTROLE DE ULTIMO TURNO
	private boolean lastTurn;


	public Board(boolean createDeck) {
		this.map = new Map();
		
		// PRIMEIRO JOGADOR CRIA O DECK
		if (createDeck) {
			deck = new Deck(this.map.getCities());
		}
	}
	
	public boolean getMapReady() {
		return this.map.getReady();
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		if (this.deck == null) {
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
	public List<WagonCard> startHand(List<WagonCard> list) {
		Player first = this.otherPlayer;
		Player second = this.player;
		if (list == null) {
			list = new ArrayList<WagonCard>();
			
			list.add(deck.drawWagonCard());
			list.add(deck.drawWagonCard());
			list.add(deck.drawWagonCard());
			list.add(deck.drawWagonCard());
			list.add(deck.drawWagonCard());
			list.add(deck.drawWagonCard());
			list.add(deck.drawWagonCard());
			list.add(deck.drawWagonCard());
			
			first = this.player;
			second = this.otherPlayer;
		}
		first.addCards(list.get(0));
		first.addCards(list.get(1));
		first.addCards(list.get(2));
		first.addCards(list.get(3));
		second.addCards(list.get(4));
		second.addCards(list.get(5));
		second.addCards(list.get(6));
		second.addCards(list.get(7));
		
		return list;
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
	
	public ArrayList<String> getLinesInfo() {
		return this.map.getLinesInfo();
	}
	
	public ArrayList<Line> getLines() {
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
		return this.player.getCardsInfo();
	}

	public boolean buildLine(int line, int color, boolean player) {
		Player p;
		if (player) {
			p = this.player;
		} else {
			p = this.otherPlayer;
		}
		
		boolean build =  p.addLine(this.getLine(line), color);
		if (build) {
			this.getLine(line).setPlayer(p.getName());
		}
		return build;
	}
	
	public Line getLine(int line) {
		Line it = null;
		for (Line l: this.map.getLines()) {
			if (l.getId() == line) {
				it = l;
			}
		}
		return it;
	}

	public Map getMap() {
		return this.map;
	}
	
	public static ArrayList<Line> getDummyArray() {
    	ArrayList<Line> lines = new ArrayList<>();
    	for (int i = 0; i < 78; i++) {
    		lines.add(new Line(0, new City(""), new City(""), 0, 0));
    	}
    	
    	return lines;
    }
	
	public static ArrayList<Player> getPlayers() {
		ArrayList<Player> players = new ArrayList<>();
		players.add(player);
		players.add(otherPlayer);
		return players;
	}
	
	public static ArrayList<Player> getDummyPlayers() {
		ArrayList<Player> players = new ArrayList<>();
		players.add(new Player("", 0));
		players.add(new Player("", 0));	
		return players;
	}
}
