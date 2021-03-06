package model;

import br.ufsc.inf.leobr.cliente.Jogada;

public class WagonCard implements Jogada {

	public static final int BLACK = 0;
	public static final int BLUE = 1;
	public static final int GREEN = 2;
	public static final int ORANGE = 3;
	public static final int PURPLE = 4;
	public static final int MULTICOLOR = 5;
	public static final int RED = 6;
	public static final int WHITE = 7;
	public static final int YELLOW = 8;

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
		return color + "";
	}
	
	public String getColorName() {
		String colorName;
		switch(this.color) {
		case 0:
			colorName = "Preta";
			break;
		case 1:
			colorName = "Azul";
			break;
		case 2:
			colorName = "Verde";
			break;
		case 3:
			colorName = "Laranja";
			break;
		case 4:
			colorName = "Roxa";
			break;
		case 5:
			colorName = "Multi-color";
			break;
		case 6:
			colorName = "Vermelha";
			break;
		case 7:
			colorName = "Branca";
			break;
		default:
			colorName = "Amarela";
			break;
		}
		return colorName;
	}

	
}
