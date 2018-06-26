package model;

import java.util.ArrayList;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Line implements Jogada {

	private int id;

	private int color;

	private int size;

	private Player player;
	
	private City cityA;

	private City cityB;

	
	public Line(int id, City cityA, City cityB, int size, int color) {
		this.id = id;
		this.color = color;
		this.size = size;
		this.cityA = cityA;
		this.cityB = cityB;
		this.player = null;
	}
	
	public int getId() {
		return id;
	}

	public int getColor() {
		return color;
	}

	public int getSize() {
		return size;
	}

	public City getCityA() {
		return cityA;
	}

	public City getCityB() {
		return cityB;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return this.player;
	}

	@Override
	public String toString() {
		return "@" + id + "color = " + color + ", size = " + size + ", city " + cityA.getName() + ", city " + cityB.getName() + "]";
	}
	
	public String toString2() {
		return (size + ", " + cityA.getName() + " - " + cityB.getName());
	}
	
}
