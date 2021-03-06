package model;

import java.util.ArrayList;
import java.util.Random;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Deck implements Jogada {
	// CARTAS DO BARALHO
	private ArrayList<WagonCard> cardsDeck;
	
	// CARTAS DA MESA
	private WagonCard[] cardsBoard;
	
	// CARTAS DE OBJETIVOS
	private ArrayList<ObjectiveCard> objectives;
		
	public Deck(ArrayList<City> cities) {
				
		// OBJETIVOS
		this.objectives = new ArrayList<ObjectiveCard>();
		for (int i = 5; i <= 30; i+=5) {
			this.objectives.add(new ObjectiveCard(1 ,cities.get(i), cities.get(i+1)));
			this.objectives.add(new ObjectiveCard(2 ,cities.get(i+2), cities.get(i)));
			this.objectives.add(new ObjectiveCard(3 ,cities.get(i), cities.get(i+3)));
			this.objectives.add(new ObjectiveCard(4 ,cities.get(i+4), cities.get(i)));
			this.objectives.add(new ObjectiveCard(5 ,cities.get(i), cities.get(i+5)));

		}
		// Adiciona as 14 cartas curinga
		this.cardsDeck = new ArrayList<WagonCard>();
		for (int i = 0; i < 14; i++) {
			cardsDeck.add(new WagonCard(WagonCard.MULTICOLOR));
		}
		
		// Adiciona as 12 outras cartas de cada uma das 8 cores de forma aleatoria
		Random rand = new Random();
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 12; j++) {
				int r = rand.nextInt(cardsDeck.size());
				cardsDeck.add(r, new WagonCard(i));
			}
		}
		
		// Adiciona 5 cartas ao Board
		cardsBoard = new WagonCard[5];
		for (int i = 0; i < 5; i++) {
			cardsBoard[i] = cardsDeck.remove(0);
		}
	}
	
	// COMPRA 3 OBJETIVOS
	public ObjectiveCard[] buyObjectivies() {
		ObjectiveCard[] buy = new ObjectiveCard[3];
		buy[0] = this.objectives.remove(0);
		buy[1] = this.objectives.remove(0);
		buy[2] = this.objectives.remove(0);
		
		return buy;
	}

	// COMPRAR UMA CARTA DO DECK
	public WagonCard drawWagonCard() {
		return this.cardsDeck.remove(0);
	}

	// ADICIONA OBJETIVOS AO FINAL DA LISTA
	public void addObjectives(ObjectiveCard objectiveCard) {
		this.objectives.add(objectiveCard);
	}


	public WagonCard[] getCardsBoard() {
		WagonCard[] cards = {cardsBoard[0], cardsBoard[1], cardsBoard[2], cardsBoard[3], cardsBoard[4]};
		return cards;
	}

	// CARTA DA MESA � UM CORINGA
	public boolean isJoker(int position) {
		return this.cardsBoard[position].isJoker();
	}

	public WagonCard drawCardBoard(int position) {
		WagonCard card = this.cardsBoard[position];
		this.cardsBoard[position] = this.drawWagonCard();
		return card;
	}

	public ArrayList<WagonCard> getCardsDeck() {
		return cardsDeck;
	}

	public ArrayList<ObjectiveCard> getObjectives() {
		return objectives;
	}
	
}
