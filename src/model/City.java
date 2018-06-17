package model;

import java.util.ArrayList;

public class City {
	// PRONTO
	private String name;
	
	private ArrayList<Line> lines;
	
	public City(String name) {
		this.name = name;
		this.lines = new ArrayList<Line>();
	}

	public String getName() {
		return name;
	}

	public ArrayList<Line> getLines() {
		return lines;
	}
	
	protected void addLine(Line line) {
		lines.add(line);
	}

	@Override
	public String toString() {
		String lines = "";
		for(Line l: this.lines) {
			lines +=" " + l.getId(); 
		}
		return "City [name: " + name + ", lines: " + lines + "]";
	}
}
