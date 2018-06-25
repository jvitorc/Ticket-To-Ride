package network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ufsc.inf.leobr.cliente.Jogada;
import model.Deck;
import model.Line;
import model.ObjectiveCard;
import model.WagonCard;

public class Action implements Jogada {
	
	public static final int CHOOSE_OBJECTIVE = 0;
	public static final int BUILD_LINE = 1;
	public static final int BUY_WAGONCARD = 2;
	public static final int BUY_OBJECTIVECARD = 3;
	
	
	public Action(int action, String player) {
		this.action = action;
		this.player = player;
	}
	// TIPO DE A��O
	public int action;
	
	// NOME DO JOGADOR
	public String player;
	
	// DECK
	public Deck deck;
	
	// OBJETIVOS COMPRADOS
	public ArrayList<ObjectiveCard> objectives;
	
	// CONTROLE �LTIMO TURNO
	public boolean lastTurn;
	
	// COMPRAR OBJETIVO
	public boolean[] choice;

	// QUANTIDADE DE CARTA COMPRADA DO DECK
	public int buyDeckCard = 0;
	
	// POSI��O DAS CARTAS COMPRADA NA MESA
	public int[] drawBoardCard = {-1, -1};

	// MAO INICIAL
	public List<WagonCard> startHand;
	
	// LINHA COSTRUIDA
	public int line = -1;
	
	// COR DA LINHA
	public int color;

	@Override
	public String toString() {
		return "Action [action=" + action + ", player=" + player + ", deck=" + deck + ", objectives=" + objectives
				+ ", lastTurn=" + lastTurn + ", choice=" + Arrays.toString(choice) + ", buyDeckCard=" + buyDeckCard
				+ ", drawBoardCard=" + Arrays.toString(drawBoardCard) + "]";
	}

	

	
}
