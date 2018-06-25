package model;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Line implements Jogada {
	// PRONTO
	private int id;

	private int color;

	private int size;

	private City cityA;

	private City cityB;

	
	public Line(int id, City cityA, City cityB, int size, int color) {
		this.id = id;
		this.color = color;
		this.size = size;
		this.cityA = cityA;
		this.cityB = cityB;
		
		this.cityA.addLine(this);
		this.cityB.addLine(this);
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

	@Override
	public String toString() {
		return "Line [id = " + id + ", color = " + color + ", size = " + size + ", city " + cityA.getName() + ", city " + cityB.getName() + "]";
	}
}
