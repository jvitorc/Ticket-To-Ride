package network;

import br.ufsc.inf.leobr.cliente.Jogada;
import model.Deck;

public class Action implements Jogada {
	
	public static final int CHOOSE_OBJECTIVES = 0;
	public static final int BUILD_LINE = 1;
	public static final int BUY_WAGONCARD = 0;
	public static final int BUY_OBJECTIVECARD = 0;
	
	String player;
	
	// Primeiro turno sincronizar deck	
	public Deck deck;
	
	public int action;

	
}
