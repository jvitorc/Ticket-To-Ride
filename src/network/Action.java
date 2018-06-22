package network;

import java.util.ArrayList;

import br.ufsc.inf.leobr.cliente.Jogada;
import model.Deck;
import model.ObjectiveCard;

public class Action implements Jogada {
	
	public static final int CHOOSE_OBJECTIVE = 0;
	public static final int BUILD_LINE = 1;
	public static final int BUY_WAGONCARD = 2;
	public static final int BUY_OBJECTIVECARD = 3;
		
	public Action(int action, String player) {
		this.action = action;
		this.player = player;
	}
	// TIPO DE AÇÃO
	public int action;
	
	// NOME DO JOGADOR
	public String player;
	
	// DECK
	public Deck deck;
	
	// OBJETIVOS COMPRADOS
	public ArrayList<ObjectiveCard> objectives;
	
	// CONTROLE ÚLTIMO TURNO
	public boolean lastTurn;
	
	// COMPRAR OBJETIVO
	public boolean[] choice;

	// QUANTIDADE DE CARTA COMPRADA DO DECK
	public int buyDeckCard;
	
	// POSIÇÃO DAS CARTAS COMPRADA NA MESA
	public int[] drawBoardCard;

	

	
}
