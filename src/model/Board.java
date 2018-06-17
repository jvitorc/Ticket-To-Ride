package model;

import java.util.ArrayList;

public class Board {

	private Map map;

	private Deck deck;

	public Board(boolean createDeck) {
		map = new Map();
		
		// O primeiro jogador cria o mapa para sincronização
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
	
	
	
}
