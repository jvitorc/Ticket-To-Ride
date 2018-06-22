package model;

import java.util.ArrayList;

public class Player {

	public static final int BLUE = 0;
	public static final int RED = 1;
	public static final int GREEN = 2;
	public static final int YELLOW = 3;
	public static final int BLACK = 4;
	
	private String name; 	
	
	private int color;
	
	private int wagons = 45;
	private int points = 0; 
	
	private ArrayList<ObjectiveCard> objectives = new ArrayList<ObjectiveCard>();
	private ArrayList<WagonCard> cards = new ArrayList<WagonCard>();
	
	public Player(String name, Integer color) {
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public int getColor() {
		return color;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public void addObjectives(ObjectiveCard objectiveCard) {
		objectives.add(objectiveCard);
	}


	public ArrayList<ObjectiveCard> getObjectives() {
		return objectives;
	}
	
	public void addCards(WagonCard card) {
		cards.add(card);
	}

	public ArrayList<String> getCards() {
		ArrayList<String> cardsInfo = new ArrayList<String>();
		for (WagonCard card: this.cards) {
			cardsInfo.add(card.toString());
		}
		return cardsInfo;
	}

	public ArrayList<WagonCard> getWagonCards(ArrayList<String> cards) {
	
			/// IMPLEMENTAR
		return null;
	}
	
}
