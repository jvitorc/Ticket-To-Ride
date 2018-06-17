package model;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	// Cartas do baralho
	private ArrayList<WagonCard> cardsDeck;
	
	// Cartas da mesa
	private WagonCard[] cardsBoard;
	
	// Cartas de objetivos
	private ArrayList<ObjectiveCard> objectives;
		
	public Deck(ArrayList<City> cities) {
		
		// Criar objetivo card ---- TESTE -------
		for (City a: cities) {
			for (City b: cities) {
				if (a != b) {
					objectives.add(new ObjectiveCard(2, a, b));
				}
			}
		}	
		//-------------------------------------------
		
		// Adiciona as 14 cartas curinga
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

	public ObjectiveCard[] buyObjectivies() {
		ObjectiveCard[] buy = new ObjectiveCard[3];
		buy[0] = this.objectives.remove(0);
		buy[1] = this.objectives.remove(0);
		buy[2] = this.objectives.remove(0);
		
		return buy;
	}
	
}
