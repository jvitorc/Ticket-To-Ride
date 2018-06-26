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
	private ArrayList<Line> lines = new ArrayList<Line>();
	
	public Player(String name, Integer color) {
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}
	
	public String getColor() {
		String colorName;
		switch (color) {
		case 0 :
			colorName = "blue";
			break;
		case 1 :
			colorName = "red";
			break;
		case 2 :
			colorName = "green";
			break;
		case 3 :
			colorName = "yellow";
			break;
		default :
			colorName = "black";
			break;
		}
		return colorName;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public int getWagons() {
		return this.wagons;
	}
	
	public int getPoints() {
		return this.points;
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

	public ArrayList<String> getCardsInfo() {
		ArrayList<String> cardsInfo = new ArrayList<String>();
		for (WagonCard card: this.cards) {
			cardsInfo.add(card.toString());
		}
		return cardsInfo;
	}
	
	public ArrayList<WagonCard> getCards() {
		return this.cards;
	}
	
	public ArrayList<Line> getLines() {
		return this.lines;
	}
	
	public boolean addLine(Line line, int color) {
		// TESTA SE TEM VAG�O
		if (line.getSize() < this.wagons ) {
			// TESTE SE COR E LINHA S�O DO TIPO CERTO
			boolean b1 = line.getColor() == WagonCard.MULTICOLOR;
			boolean b2 = color == WagonCard.MULTICOLOR;
			boolean b3 = line.getColor() == color;
			
			if (b1 || b2 || b3) {
				//TESTA SE TEM CARTAS
				ArrayList<WagonCard> list = new ArrayList<WagonCard>();
	
				for (WagonCard c: this.cards) {
					if (c.getColor() == color) {
						if (list.size() < line.getSize()) {
							list.add(c);
						}
					}
				}
				
				// SE TEM CARTAS E VAG�ES ADD LINE
				if (list.size() == line.getSize()) {
					for (WagonCard c: list) {
						this.cards.remove(c);
					}
					this.wagons -=  line.getSize();
					this.lines.add(line);
					// ATUALIZAR PONTUA��O
					this.updateLinePoints(line.getSize());
					return true;
				}
			}
		}
		return false;
	}

	public int updateObjectivePoints() {

		for (ObjectiveCard ob: this.objectives) {
			if(ob.isComplete() == true) {
				this.points += ob.getPoints();
			} else {
				this.points -= ob.getPoints();
			}
		}
		
		return this.points;
	}
	
	private void updateLinePoints(int sizeLine) {
		switch (sizeLine) {
			case 1:
				this.points += 1;
			case 2:
				this.points += 2;
			case 3:
				this.points += 4;
			case 4:
				this.points += 7;
			case 5:
				this.points += 10;
			case 6:
				this.points += 15;
			default:
				this.points += 0;
		}
	}

	public boolean twoWagons() {
		return this.wagons <= 2;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
