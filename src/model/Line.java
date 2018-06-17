package model;

public class Line {
	// PRONTO
	private String id;

	private int color;

	private int size;
	
	public String getId() {
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

	private City cityA;

	private City cityB;

	public Line(String id, City cityA, City cityB, int size, int color) {
		this.id = id;
		this.color = color;
		this.size = size;
		this.cityA = cityA;
		this.cityB = cityB;
		
		this.cityA.addLine(this);
		this.cityB.addLine(this);
	}

	@Override
	public String toString() {
		return "Line [id=" + id + ", color=" + color + ", size=" + size + ", cityA=" + cityA + ", cityB=" + cityB + "]";
	}
}
