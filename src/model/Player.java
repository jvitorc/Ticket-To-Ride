package model;

import java.util.ArrayList;

public class Player {

	private String name; 
	private ArrayList<ObjectiveCard> objectives;
	
	public Player(String name) {
		this.name = name;
		objectives = new ArrayList();
	}

	public void addObjectives(ObjectiveCard objectiveCard) {
		objectives.add(objectiveCard);
	}

	
}
