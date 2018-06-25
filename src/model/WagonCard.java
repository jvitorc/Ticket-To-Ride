package model;

import br.ufsc.inf.leobr.cliente.Jogada;

public class WagonCard implements Jogada {
	// PRONTO
	public static final int MULTICOLOR = 0;
	public static final int PURPLE = 1;
	public static final int BLUE = 2;
	public static final int ORANGE = 3;
	public static final int WHITE = 4;
	public static final int GREEN = 5;
	public static final int YELLOW = 6;
	public static final int BLACK = 7;
	public static final int RED = 8;

	private int color;
	
	public WagonCard(int color) {
		this.color = color;
	}
	
	public int getColor() {
		return color;
	}
	
	public boolean isJoker() {
		return this.color == WagonCard.MULTICOLOR;
	}

	@Override
	public String toString() {
		return "WagonCard [color = " + color + "]";
	}
	

	
}
