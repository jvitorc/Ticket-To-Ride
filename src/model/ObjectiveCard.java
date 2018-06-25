package model;

import br.ufsc.inf.leobr.cliente.Jogada;

public class ObjectiveCard implements Jogada {

	private int points;
	
	private City cityA;
	
	private City cityB;
	
	private boolean complete;

	public ObjectiveCard(int points, City cityA, City cityB) {
		this.points = points;
		this.cityA = cityA;
		this.cityB = cityB;
		this.complete = false;
	}

	public int getPoints() {
		return points;
	}

	public City getCityA() {
		return cityA;
	}

	public City getCityB() {
		return cityB;
	}
	
	protected void setComplete(boolean complete) {
		this.complete = complete;
	}

	public boolean isComplete() {
		return complete;
	}

	@Override
	public String toString() {
		return points + " : " + cityA.getName() + " - " + cityB.getName();
	}
	
}
